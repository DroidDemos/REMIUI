package com.google.android.finsky.analytics;

import android.accounts.Account;
import android.content.Context;
import android.net.NetworkInfo;
import android.os.Handler;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.ParseError;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.analytics.PlayStore.AppData;
import com.google.android.finsky.analytics.PlayStore.NlpRepairStatus;
import com.google.android.finsky.analytics.PlayStore.PlayStoreBackgroundActionEvent;
import com.google.android.finsky.analytics.PlayStore.PlayStoreClickEvent;
import com.google.android.finsky.analytics.PlayStore.PlayStoreDeepLinkEvent;
import com.google.android.finsky.analytics.PlayStore.PlayStoreImpressionEvent;
import com.google.android.finsky.analytics.PlayStore.PlayStoreLogEvent;
import com.google.android.finsky.analytics.PlayStore.PlayStoreRpcReport;
import com.google.android.finsky.analytics.PlayStore.PlayStoreSearchEvent;
import com.google.android.finsky.analytics.PlayStore.PlayStoreSessionData;
import com.google.android.finsky.analytics.PlayStore.PlayStoreUiElement;
import com.google.android.finsky.analytics.PlayStore.PlayStoreUiElementInfo;
import com.google.android.finsky.analytics.PlayStore.ReviewData;
import com.google.android.finsky.analytics.PlayStore.SearchSuggestionReport;
import com.google.android.finsky.analytics.PlayStore.WifiAutoUpdateAttempt;
import com.google.android.finsky.api.DfeApiConfig;
import com.google.android.finsky.config.G;
import com.google.android.finsky.experiments.FinskyExperiments;
import com.google.android.finsky.layout.play.PlayStoreUiElementNode;
import com.google.android.finsky.layout.play.RootUiElementNode;
import com.google.android.finsky.utils.ArrayUtils;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.Lists;
import com.google.android.play.analytics.ClientAnalytics.ActiveExperiments;
import com.google.android.play.analytics.ClientAnalytics.LogEvent;
import com.google.android.play.analytics.EventLogger;
import com.google.android.play.analytics.EventLogger.Configuration;
import com.google.android.play.analytics.EventLogger.LogSource;
import com.google.android.play.layout.PlayCardViewBase;
import com.google.android.wallet.instrumentmanager.R;
import com.google.protobuf.nano.MessageNano;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FinskyEventLog {
    private static boolean sInitializedImpressionId;
    private static LogListener sLogTestListener;
    private static long sNextImpressionId;
    private final EventLogger mEventLogger;
    private final FinskyExperiments mExperiments;
    private final EventProtoCache mProtoCache;

    public interface LogListener {
        void write(byte[] bArr);
    }

    static {
        sInitializedImpressionId = false;
    }

    public FinskyEventLog(Context context, Account account, FinskyExperiments experiments) {
        EventLogger logger = null;
        this.mExperiments = experiments;
        if (account != null && ((Boolean) G.enablePlayLogs.get()).booleanValue()) {
            Configuration config = new Configuration();
            config.maxStorageSize = ((Long) G.playLogMaxStorageSize.get()).longValue();
            config.recommendedLogFileSize = ((Long) G.playLogRecommendedFileSize.get()).longValue();
            config.delayBetweenUploadsMs = ((Long) G.playLogDelayBetweenUploadsMs.get()).longValue();
            config.minDelayBetweenUploadsMs = ((Long) G.playLogMinDelayBetweenUploadsMs.get()).longValue();
            config.mServerUrl = (String) G.playLogServerUrl.get();
            String mccmnc = ((TelephonyManager) context.getSystemService("phone")).getSimOperator();
            Context context2 = context;
            logger = new EventLogger(context2, null, "androidmarket", LogSource.MARKET, null, ((Long) DfeApiConfig.androidId.get()).longValue(), Integer.toString(FinskyApp.get().getVersionCode()), mccmnc, config, account);
        }
        this.mEventLogger = logger;
        this.mProtoCache = EventProtoCache.getInstance();
    }

    private void serializeAndWrite(String tag, PlayStoreLogEvent logEvent) {
        byte[] eventBytes = MessageNano.toByteArray(logEvent);
        writeLogToListener(tag, eventBytes);
        if (this.mEventLogger != null) {
            this.mEventLogger.logEvent(tag, getActiveExperiments(), eventBytes, new String[0]);
            this.mProtoCache.recycle(logEvent);
        }
    }

    private static synchronized void writeLogToListener(String tag, byte[] eventBytes) {
        synchronized (FinskyEventLog.class) {
            if (sLogTestListener != null) {
                LogEvent event = new LogEvent();
                event.eventTimeMs = System.currentTimeMillis();
                event.tag = tag;
                if (eventBytes != null) {
                    event.sourceExtension = eventBytes;
                }
                sLogTestListener.write(MessageNano.toByteArray(event));
            }
        }
    }

    public static synchronized void setLogTestListener(LogListener listener) {
        synchronized (FinskyEventLog.class) {
            sLogTestListener = listener;
        }
    }

    public static PlayStoreUiElement obtainPlayStoreUiElement(int type) {
        PlayStoreUiElement element = EventProtoCache.getInstance().obtainPlayStoreUiElement();
        element.type = type;
        element.hasType = true;
        return element;
    }

    public static PlayStoreBackgroundActionEvent obtainPlayStoreBackgroundActionEvent() {
        return EventProtoCache.getInstance().obtainPlayStoreBackgroundActionEvent();
    }

    public static PlayStoreClickEvent obtainPlayStoreClickEvent() {
        return EventProtoCache.getInstance().obtainPlayStoreClickEvent();
    }

    public static PlayStoreImpressionEvent obtainPlayStoreImpressionEvent() {
        return EventProtoCache.getInstance().obtainPlayStoreImpressionEvent();
    }

    public static PlayStoreSearchEvent obtainPlayStoreSearchEvent() {
        return EventProtoCache.getInstance().obtainPlayStoreSearchEvent();
    }

    public static SearchSuggestionReport obtainPlayStoreSearchSuggestionReport() {
        return EventProtoCache.getInstance().obtainPlayStoreSearchSuggestionReport();
    }

    public static PlayStoreUiElement setServerLogCookie(PlayStoreUiElement element, byte[] serverLogsCookie) {
        if (serverLogsCookie != null) {
            element.serverLogsCookie = serverLogsCookie;
            element.hasServerLogsCookie = true;
        }
        return element;
    }

    public void logClickEvent(PlayStoreUiElementNode clickLogNode) {
        if (clickLogNode != null) {
            PlayStoreClickEvent clickEvent = this.mProtoCache.obtainPlayStoreClickEvent();
            addClickPath(clickLogNode, clickEvent);
            logClickEvent(clickEvent);
        }
    }

    public void logClickEvent(int leafType, byte[] leafServerLogsCookie, PlayStoreUiElementNode clickLogParentNode) {
        PlayStoreClickEvent clickEvent = this.mProtoCache.obtainPlayStoreClickEvent();
        PlayStoreUiElement leafElement = this.mProtoCache.obtainPlayStoreUiElement();
        leafElement.type = leafType;
        leafElement.hasType = true;
        if (leafServerLogsCookie != null) {
            leafElement.serverLogsCookie = leafServerLogsCookie;
            leafElement.hasServerLogsCookie = true;
        }
        clickEvent.elementPath = (PlayStoreUiElement[]) safeAddElementToArray(clickEvent.elementPath, leafElement);
        if (clickLogParentNode != null) {
            addClickPath(clickLogParentNode, clickEvent);
        }
        logClickEvent(clickEvent);
    }

    private static <T> T[] safeAddElementToArray(T[] array, T newElement) {
        if (newElement == null) {
            FinskyLog.wtf("Adding null to element array.", new Object[0]);
            return array;
        }
        T[] newArray = (Object[]) ((Object[]) Array.newInstance(newElement.getClass(), array.length + 1));
        System.arraycopy(array, 0, newArray, 0, array.length);
        newArray[newArray.length - 1] = newElement;
        return newArray;
    }

    public void logClickEventWithClientCookie(int leafType, PlayStoreUiElementInfo leafClientLogsCookie, PlayStoreUiElementNode clickLogParentNode) {
        PlayStoreClickEvent clickEvent = this.mProtoCache.obtainPlayStoreClickEvent();
        PlayStoreUiElement leafElement = this.mProtoCache.obtainPlayStoreUiElement();
        leafElement.type = leafType;
        leafElement.hasType = true;
        if (leafClientLogsCookie != null) {
            leafElement.clientLogsCookie = leafClientLogsCookie;
        }
        clickEvent.elementPath = (PlayStoreUiElement[]) safeAddElementToArray(clickEvent.elementPath, leafElement);
        if (clickLogParentNode != null) {
            addClickPath(clickLogParentNode, clickEvent);
        }
        logClickEvent(clickEvent);
    }

    public void logClickEvent(PlayStoreClickEvent clickEvent) {
        PlayStoreLogEvent logEvent = this.mProtoCache.obtainPlayStoreLogEvent();
        logEvent.click = clickEvent;
        serializeAndWrite("3", logEvent);
    }

    private ActiveExperiments getActiveExperiments() {
        return this.mExperiments != null ? this.mExperiments.getActiveExperiments() : null;
    }

    private static void addClickPath(PlayStoreUiElementNode leaf, PlayStoreClickEvent clickEvent) {
        while (leaf != null) {
            PlayStoreUiElement treeElement = leaf.getPlayStoreUiElement();
            if (treeElement == null) {
                FinskyLog.w("Unexpected null PlayStoreUiElement from node %s", leaf);
                return;
            }
            clickEvent.elementPath = (PlayStoreUiElement[]) safeAddElementToArray(clickEvent.elementPath, cloneElement(treeElement));
            leaf = leaf.getParentNode();
        }
    }

    public void logPathImpression(long impressionId, int leafType, PlayStoreUiElementNode parentPath) {
        PlayStoreImpressionEvent impressionEvent = this.mProtoCache.obtainPlayStoreImpressionEvent();
        if (impressionId != 0) {
            impressionEvent.id = impressionId;
            impressionEvent.hasId = true;
        }
        ArrayList<PlayStoreUiElement> path = Lists.newArrayList();
        PlayStoreUiElement leafElement = this.mProtoCache.obtainPlayStoreUiElement();
        leafElement.type = leafType;
        leafElement.hasType = true;
        path.add(leafElement);
        while (parentPath != null) {
            path.add(parentPath.getPlayStoreUiElement());
            parentPath = parentPath.getParentNode();
        }
        PlayStoreUiElement tree = pathToTree(path);
        if (tree != null) {
            impressionEvent.tree = tree;
        } else {
            FinskyLog.wtf("Encountered empty tree.", new Object[0]);
        }
        logImpressionEvent(impressionEvent);
    }

    public void logPathImpression(long impressionId, PlayStoreUiElementNode leaf) {
        PlayStoreImpressionEvent impressionEvent = this.mProtoCache.obtainPlayStoreImpressionEvent();
        if (impressionId != 0) {
            impressionEvent.id = impressionId;
            impressionEvent.hasId = true;
        }
        ArrayList<PlayStoreUiElement> pathList = Lists.newArrayList();
        for (PlayStoreUiElementNode parent = leaf; parent != null; parent = parent.getParentNode()) {
            pathList.add(parent.getPlayStoreUiElement());
        }
        PlayStoreUiElement tree = pathToTree(pathList);
        if (tree != null) {
            impressionEvent.tree = tree;
        } else {
            FinskyLog.wtf("Encountered empty tree.", new Object[0]);
        }
        logImpressionEvent(impressionEvent);
    }

    public static PlayStoreUiElement pathToTree(List<PlayStoreUiElement> path) {
        PlayStoreUiElement previousNode = null;
        for (PlayStoreUiElement current : path) {
            PlayStoreUiElement currentCopy = cloneElement(current);
            if (previousNode != null) {
                currentCopy.child = (PlayStoreUiElement[]) safeAddElementToArray(currentCopy.child, previousNode);
            }
            previousNode = currentCopy;
        }
        return previousNode;
    }

    public void logImpressionEvent(PlayStoreImpressionEvent impressionEvent) {
        PlayStoreLogEvent logEvent = this.mProtoCache.obtainPlayStoreLogEvent();
        logEvent.impression = impressionEvent;
        serializeAndWrite("1", logEvent);
    }

    public static void childImpression(PlayStoreUiElementNode receiverNode, PlayStoreUiElementNode childNode) {
        PlayStoreUiElement newChild = childNode.getPlayStoreUiElement();
        if (newChild == null) {
            throw new IllegalArgumentException("childNode has null element");
        } else if (!findOrAddChild(receiverNode, newChild) || newChild.child.length != 0) {
            receiverNode.getParentNode().childImpression(receiverNode);
        }
    }

    public static boolean findOrAddChild(PlayStoreUiElementNode receiverNode, PlayStoreUiElement newChild) {
        boolean foundChild = false;
        PlayStoreUiElement receiverElement = receiverNode.getPlayStoreUiElement();
        for (PlayStoreUiElement receiverChild : receiverElement.child) {
            if (isEqual(newChild, receiverChild)) {
                foundChild = true;
                break;
            }
        }
        if (!foundChild) {
            receiverElement.child = (PlayStoreUiElement[]) safeAddElementToArray(receiverElement.child, newChild);
        }
        return foundChild;
    }

    public static boolean removeChild(PlayStoreUiElement parentNode, PlayStoreUiElement childToRemove) {
        PlayStoreUiElement[] children = parentNode.child;
        for (int i = 0; i < children.length; i++) {
            if (isEqual(children[i], childToRemove)) {
                parentNode.child = (PlayStoreUiElement[]) ArrayUtils.remove(children, i);
                return true;
            }
        }
        return false;
    }

    public static void rootImpression(Handler handler, long impressionId, PlayStoreUiElementNode rootNode, PlayStoreUiElementNode childNode) {
        if (childNode == null || childNode.getPlayStoreUiElement() == null) {
            throw new IllegalArgumentException("null child node or element");
        }
        rootImpressionImpl(handler, impressionId, rootNode, childNode);
    }

    private static void rootImpressionImpl(Handler handler, final long impressionId, final PlayStoreUiElementNode rootNode, PlayStoreUiElementNode childNode) {
        PlayStoreUiElement rootElement = rootNode.getPlayStoreUiElement();
        if (childNode != null) {
            findOrAddChild(rootNode, childNode.getPlayStoreUiElement());
        }
        handler.removeCallbacksAndMessages(null);
        handler.postDelayed(new Runnable() {
            public void run() {
                FinskyEventLog.sendImpression(impressionId, rootNode, false);
            }
        }, ((Long) G.playLogImpressionSettleTimeMs.get()).longValue());
    }

    private static void sendImpression(long impressionId, PlayStoreUiElementNode rootNode, boolean dropIfEmpty) {
        if (!dropIfEmpty || rootNode.getPlayStoreUiElement().child.length != 0) {
            PlayStoreImpressionEvent impression = EventProtoCache.getInstance().obtainPlayStoreImpressionEvent();
            PlayStoreUiElement impressionRootElement = obtainPlayStoreUiElement(0);
            deepCloneAndWipeChildren(rootNode.getPlayStoreUiElement(), impressionRootElement);
            impression.tree = impressionRootElement;
            if (impressionId != 0) {
                impression.id = impressionId;
                impression.hasId = true;
            }
            FinskyApp.get().getEventLogger().logImpressionEvent(impression);
        }
    }

    public static void flushImpressionAtRoot(PlayStoreUiElementNode parentNode) {
        while (parentNode != null) {
            if (parentNode instanceof RootUiElementNode) {
                ((RootUiElementNode) parentNode).flushImpression();
                return;
            }
            parentNode = parentNode.getParentNode();
        }
        FinskyLog.wtf("No RootUiElementNode found in parent chain", new Object[0]);
    }

    public static void flushImpression(Handler handler, long impressionId, RootUiElementNode rootNode) {
        PlayStoreUiElement rootElement = rootNode.getPlayStoreUiElement();
        handler.removeCallbacksAndMessages(null);
        sendImpression(impressionId, rootNode, true);
    }

    private static void deepCloneAndWipeChildren(PlayStoreUiElement from, PlayStoreUiElement to) {
        cloneElement(from, to);
        for (PlayStoreUiElement fromChild : from.child) {
            PlayStoreUiElement toChild = obtainPlayStoreUiElement(0);
            deepCloneAndWipeChildren(fromChild, toChild);
            to.child = (PlayStoreUiElement[]) safeAddElementToArray(to.child, toChild);
        }
        from.child = PlayStoreUiElement.emptyArray();
    }

    public static PlayStoreUiElement cloneElement(PlayStoreUiElement original) {
        PlayStoreUiElement copy = obtainPlayStoreUiElement(0);
        cloneElement(original, copy);
        return copy;
    }

    protected static void cloneElement(PlayStoreUiElement from, PlayStoreUiElement to) {
        to.type = from.type;
        to.hasType = from.hasType;
        to.clientLogsCookie = from.clientLogsCookie;
        to.serverLogsCookie = from.serverLogsCookie;
        to.hasServerLogsCookie = to.serverLogsCookie.length != 0;
    }

    public static boolean isEqual(PlayStoreUiElement a, PlayStoreUiElement b) {
        if (a == b) {
            return true;
        }
        if (a == null || b == null) {
            return false;
        }
        if (a.type != b.type) {
            return false;
        }
        if (Arrays.equals(a.serverLogsCookie, b.serverLogsCookie)) {
            return true;
        }
        return false;
    }

    public static void requestImpressions(ViewGroup view) {
        if (view != null) {
            requestImpressionsImpl(view);
        }
    }

    private static void requestImpressionsImpl(ViewGroup view) {
        int numChildren = view.getChildCount();
        for (int i = 0; i < numChildren; i++) {
            View child = view.getChildAt(i);
            if (child instanceof ViewGroup) {
                requestImpressionsImpl((ViewGroup) child);
            }
        }
        if (view instanceof PlayStoreUiElementNode) {
            requestImpression((PlayStoreUiElementNode) view);
        } else if (view instanceof PlayCardViewBase) {
            PlayStoreUiElementNode loggingData = (PlayStoreUiElementNode) ((PlayCardViewBase) view).getLoggingData();
            if (loggingData != null) {
                requestImpression(loggingData);
            }
        } else if (view.getTag() instanceof PlayStoreUiElementNode) {
            requestImpression((PlayStoreUiElementNode) view.getTag());
        }
    }

    private static void requestImpression(PlayStoreUiElementNode node) {
        PlayStoreUiElementNode parent = node.getParentNode();
        if (parent != null) {
            parent.childImpression(node);
        }
    }

    public static long getNextImpressionId() {
        if (!sInitializedImpressionId) {
            sNextImpressionId = System.currentTimeMillis() ^ System.nanoTime();
            sInitializedImpressionId = true;
        }
        long j = sNextImpressionId + 1;
        sNextImpressionId = j;
        if (j == 0) {
            sNextImpressionId++;
        }
        return sNextImpressionId;
    }

    public static void startNewImpression(PlayStoreUiElementNode node) {
        PlayStoreUiElementNode parent = node.getParentNode();
        while (parent != null) {
            node = parent;
            parent = node.getParentNode();
        }
        if (node instanceof RootUiElementNode) {
            ((RootUiElementNode) node).startNewImpression();
        }
    }

    public void logBackgroundEvent(int type, String docId, String reason, int errorCode, String exceptionType, AppData appData) {
        sendBackgroundEventToSinks(new BackgroundEventBuilder(type).setDocument(docId).setReason(reason).setErrorCode(errorCode).setExceptionType(exceptionType).setAppData(appData).build());
    }

    public void logBackgroundEvent(int type, byte[] serverLogsCookie) {
        sendBackgroundEventToSinks(new BackgroundEventBuilder(type).setServerLogsCookie(serverLogsCookie).build());
    }

    public void logPurchaseBackgroundEvent(int type, String docId, int offerType, String exceptionType, int errorCode, byte[] serverLogsCookie, long serverLatencyMs, long clientLatencyMs) {
        sendBackgroundEventToSinks(new BackgroundEventBuilder(type).setDocument(docId).setErrorCode(errorCode).setExceptionType(exceptionType).setServerLogsCookie(serverLogsCookie).setOfferType(offerType).setServerLatencyMs(serverLatencyMs).setClientLatencyMs(clientLatencyMs).build());
    }

    public void logSettingsBackgroundEvent(int type, Integer toSetting, Integer fromSetting, String reason) {
        sendBackgroundEventToSinks(new BackgroundEventBuilder(type).setToSetting(toSetting).setFromSetting(fromSetting).setReason(reason).build());
    }

    public void logOperationSuccessBackgroundEvent(int type, boolean operationSuccess) {
        sendBackgroundEventToSinks(new BackgroundEventBuilder(type).setOperationSuccess(operationSuccess).build());
    }

    public void logSettingsForPackageEvent(int type, int setting, Integer previousSetting, String documentId) {
        sendBackgroundEventToSinks(new BackgroundEventBuilder(type).setToSetting(Integer.valueOf(setting)).setFromSetting(previousSetting).setDocument(documentId).build());
    }

    public void logSessionData(PlayStoreSessionData sessionData) {
        sendBackgroundEventToSinks(new BackgroundEventBuilder(1).setSessionInfo(sessionData).build());
    }

    public void logNlpCleanupData(NlpRepairStatus report) {
        sendBackgroundEventToSinks(new BackgroundEventBuilder(2).setNlpRepairStatus(report).build());
    }

    public void logRpcReportEvent(PlayStoreRpcReport rpcReport) {
        sendBackgroundEventToSinks(new BackgroundEventBuilder(5).setRpcReport(rpcReport).build());
    }

    public void logBackgroundEvent(PlayStoreBackgroundActionEvent event) {
        sendBackgroundEventToSinks(event);
    }

    private void sendBackgroundEventToSinks(PlayStoreBackgroundActionEvent event) {
        PlayStoreLogEvent logEvent = this.mProtoCache.obtainPlayStoreLogEvent();
        logEvent.backgroundAction = event;
        serializeAndWrite("4", logEvent);
    }

    public void logSearchEvent(PlayStoreSearchEvent searchEvent) {
        PlayStoreLogEvent logEvent = this.mProtoCache.obtainPlayStoreLogEvent();
        logEvent.search = searchEvent;
        serializeAndWrite("5", logEvent);
    }

    public void logDeepLinkEvent(int resolvedType, String externalUrl, String packageName, String externalReferrer, byte[] serverLogsCookie) {
        PlayStoreDeepLinkEvent deepLinkEvent = new PlayStoreDeepLinkEvent();
        deepLinkEvent.resolvedType = resolvedType;
        deepLinkEvent.hasResolvedType = true;
        if (!TextUtils.isEmpty(externalUrl)) {
            deepLinkEvent.externalUrl = externalUrl;
            deepLinkEvent.hasExternalUrl = true;
        }
        if (!TextUtils.isEmpty(packageName)) {
            deepLinkEvent.packageName = packageName;
            deepLinkEvent.hasPackageName = true;
        }
        if (!TextUtils.isEmpty(externalReferrer)) {
            deepLinkEvent.externalReferrer = externalReferrer;
            deepLinkEvent.hasExternalReferrer = true;
        }
        if (serverLogsCookie != null) {
            deepLinkEvent.serverLogsCookie = serverLogsCookie;
            deepLinkEvent.hasServerLogsCookie = true;
        }
        PlayStoreLogEvent logEvent = this.mProtoCache.obtainPlayStoreLogEvent();
        logEvent.deepLink = deepLinkEvent;
        serializeAndWrite("6", logEvent);
    }

    public void logDeepLinkEvent(String externalUrl, String packageName, int minVersion, boolean newEnough, boolean canResolve) {
        PlayStoreDeepLinkEvent deepLinkEvent = new PlayStoreDeepLinkEvent();
        deepLinkEvent.resolvedType = 7;
        deepLinkEvent.hasResolvedType = true;
        deepLinkEvent.externalUrl = externalUrl;
        deepLinkEvent.hasExternalUrl = true;
        deepLinkEvent.packageName = packageName;
        deepLinkEvent.hasPackageName = true;
        deepLinkEvent.minVersion = minVersion;
        deepLinkEvent.hasMinVersion = true;
        deepLinkEvent.newEnough = newEnough;
        deepLinkEvent.hasNewEnough = true;
        deepLinkEvent.canResolve = canResolve;
        deepLinkEvent.hasCanResolve = true;
        PlayStoreLogEvent logEvent = this.mProtoCache.obtainPlayStoreLogEvent();
        logEvent.deepLink = deepLinkEvent;
        serializeAndWrite("6", logEvent);
    }

    public void logWifiAutoUpdateAttempt(WifiAutoUpdateAttempt attempt, String reason) {
        sendBackgroundEventToSinks(new BackgroundEventBuilder(115).setReason(reason).setWifiAutoUpdateAttempt(attempt).build());
    }

    public void logReviewAdded(int reviewSource, int rating, boolean containsText) {
        ReviewData reviewData = new ReviewData();
        reviewData.reviewSource = reviewSource;
        reviewData.hasReviewSource = true;
        reviewData.rating = rating;
        reviewData.hasRating = true;
        reviewData.containsText = containsText;
        reviewData.hasContainsText = true;
        sendBackgroundEventToSinks(new BackgroundEventBuilder(513).setReviewData(reviewData).build());
    }

    private int getConnectionType(int networkType, int networkSubType) {
        if (networkType == 1) {
            return 3;
        }
        if (networkType == 0) {
            switch (networkSubType) {
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                case R.styleable.MapAttrs_uiZoomControls /*11*/:
                    return 4;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                    return 5;
                case R.styleable.MapAttrs_useViewLifecycle /*13*/:
                case R.styleable.MapAttrs_uiMapToolbar /*15*/:
                    return 6;
                default:
                    return 0;
            }
        } else if (networkType == 9) {
            return 2;
        } else {
            return 1;
        }
    }

    public void logRpcReport(String url, long networkTimeMs, long serverLatencyMs, int numAttempts, int timeoutMs, float backoffMultiplier, boolean wasSuccessful, VolleyError volleyError, NetworkInfo startNetworkInfo, NetworkInfo currentNetworkInfo, int responseBodySizeBytes) {
        int errorCode = 0;
        if (volleyError != null) {
            if (volleyError instanceof TimeoutError) {
                errorCode = 1;
            } else if (volleyError instanceof NetworkError) {
                errorCode = 2;
            } else if (volleyError instanceof ParseError) {
                errorCode = 3;
            } else if (volleyError instanceof AuthFailureError) {
                errorCode = 4;
            } else if (volleyError instanceof ServerError) {
                errorCode = 5;
            }
            PlayStoreRpcReport rpcReport = new PlayStoreRpcReport();
            if (!TextUtils.isEmpty(url)) {
                rpcReport.url = url;
                rpcReport.hasUrl = true;
            }
            if (networkTimeMs >= 0) {
                rpcReport.clientLatencyMs = networkTimeMs;
                rpcReport.hasClientLatencyMs = true;
            }
            if (serverLatencyMs >= 0) {
                rpcReport.serverLatencyMs = serverLatencyMs;
                rpcReport.hasServerLatencyMs = true;
            }
            if (numAttempts >= 0) {
                rpcReport.numAttempts = numAttempts;
                rpcReport.hasNumAttempts = true;
            }
            if (timeoutMs >= 0) {
                rpcReport.timeoutMs = timeoutMs;
                rpcReport.hasTimeoutMs = true;
            }
            if (backoffMultiplier > 0.0f) {
                rpcReport.backoffMultiplier = backoffMultiplier;
                rpcReport.hasBackoffMultiplier = true;
            }
            rpcReport.wasSuccessful = wasSuccessful;
            rpcReport.hasWasSuccessful = true;
            if (!wasSuccessful) {
                rpcReport.volleyErrorType = errorCode;
                rpcReport.hasVolleyErrorType = true;
            }
            if (startNetworkInfo != null) {
                rpcReport.startConnectionType = getConnectionType(startNetworkInfo.getType(), startNetworkInfo.getSubtype());
                rpcReport.hasStartConnectionType = true;
            }
            if (currentNetworkInfo != null) {
                rpcReport.endConnectionType = getConnectionType(currentNetworkInfo.getType(), currentNetworkInfo.getSubtype());
                rpcReport.hasEndConnectionType = true;
            }
            if (responseBodySizeBytes >= 0) {
                rpcReport.responseBodySizeBytes = responseBodySizeBytes;
                rpcReport.hasResponseBodySizeBytes = true;
            }
            logRpcReportEvent(rpcReport);
        }
    }
}
