package com.google.android.finsky.library;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.util.Base64;
import android.util.Log;
import com.android.volley.Response.ErrorListener;
import com.android.volley.VolleyError;
import com.google.android.finsky.api.DfeApi;
import com.google.android.finsky.config.G;
import com.google.android.finsky.protos.LibraryReplication.LibraryReplicationRequest;
import com.google.android.finsky.protos.LibraryReplication.LibraryReplicationResponse;
import com.google.android.finsky.protos.LibraryUpdateProto.ClientLibraryState;
import com.google.android.finsky.protos.LibraryUpdateProto.LibraryAppDetails;
import com.google.android.finsky.protos.LibraryUpdateProto.LibraryInAppDetails;
import com.google.android.finsky.protos.LibraryUpdateProto.LibraryMutation;
import com.google.android.finsky.protos.LibraryUpdateProto.LibrarySubscriptionDetails;
import com.google.android.finsky.protos.LibraryUpdateProto.LibraryUpdate;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.FinskyPreferences;
import com.google.android.finsky.utils.Lists;
import com.google.android.finsky.utils.Maps;
import com.google.android.finsky.utils.Utils;
import com.google.android.wallet.instrumentmanager.R;
import com.google.protobuf.nano.MessageNanoPrinter;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class LibraryReplicator {
    private static final long RESCHEDULE_REPLICATION_MS;
    private final AccountLibrary mAccountLibrary;
    private final Handler mBackgroundHandler;
    private ReplicationRequest mCurrentRequest;
    private Queue<DebugEvent> mDebugEvents;
    private final DfeApi mDfeApi;
    private final boolean mEnableDebugging;
    private final List<Listener> mListeners;
    private final Handler mNotificationHandler;
    private final List<ReplicationRequest> mReplicationRequests;
    private final Runnable mReplicationRunnable;
    private final SQLiteLibrary mSQLiteLibrary;

    private static class DebugEvent {
        private LibraryUpdate libraryUpdate;
        private String tag;
        private long timestampMs;
        private int type;
        private VolleyError volleyError;

        private DebugEvent() {
        }

        private static String typeToString(int type) {
            switch (type) {
                case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                    return "APPLY_LIBRARY_UPDATE";
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    return "SCHEDULE_REPLICATION";
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    return "REPLICATE";
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    return "ERROR_VOLLEY";
                case R.styleable.WalletImFormEditText_required /*4*/:
                    return "ERROR_TOKEN_CHANGED";
                case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                    return "ERROR_UNSUPPORTED_LIBRARY";
                default:
                    return String.valueOf(type) + " (FIXME)";
            }
        }

        public void dumpState(String indent) {
            Log.d("FinskyLibrary", indent + "Event {");
            Log.d("FinskyLibrary", indent + "  type=" + typeToString(this.type));
            Log.d("FinskyLibrary", indent + "  timestampMs=" + this.timestampMs);
            Log.d("FinskyLibrary", indent + "  timestamp=" + DateFormat.format("MM-dd hh:mm:ss", this.timestampMs));
            if (this.tag != null) {
                Log.d("FinskyLibrary", indent + "  tag=" + this.tag);
            }
            if (this.libraryUpdate != null) {
                String[] libraryUpdateLines = MessageNanoPrinter.print(this.libraryUpdate).split("\n");
                Log.d("FinskyLibrary", indent + "  libraryUpdate=");
                for (String libraryUpdateLine : libraryUpdateLines) {
                    Log.d("FinskyLibrary", indent + "    " + libraryUpdateLine);
                }
            }
            if (this.volleyError != null) {
                Log.d("FinskyLibrary", indent + "  volleyError=" + this.volleyError);
            }
            Log.d("FinskyLibrary", indent + "}");
        }
    }

    private class LibraryUpdateListener implements ErrorListener, com.android.volley.Response.Listener<LibraryReplicationResponse> {
        private final Map<String, byte[]> mOriginalTokens;

        public LibraryUpdateListener() {
            this.mOriginalTokens = Maps.newHashMap();
            for (String libraryId : AccountLibrary.LIBRARY_IDS) {
                this.mOriginalTokens.put(libraryId, LibraryReplicator.this.mAccountLibrary.getServerToken(libraryId));
            }
        }

        public void onResponse(final LibraryReplicationResponse libraryReplicationResponse) {
            LibraryReplicator.this.mBackgroundHandler.post(new Runnable() {
                public void run() {
                    if (LibraryReplicator.this.mCurrentRequest == null) {
                        FinskyLog.wtf("Expected pending replication request.", new Object[0]);
                        return;
                    }
                    List<String> hasMoreLibraries = Lists.newArrayList();
                    LibraryReplicator.this.mSQLiteLibrary.reopen();
                    for (LibraryUpdate libraryUpdate : libraryReplicationResponse.update) {
                        String libraryId;
                        if (TextUtils.isEmpty(libraryUpdate.libraryId)) {
                            libraryId = AccountLibrary.getLibraryIdFromBackend(libraryUpdate.corpus);
                        } else {
                            libraryId = libraryUpdate.libraryId;
                        }
                        if (!Arrays.equals((byte[]) LibraryUpdateListener.this.mOriginalTokens.get(libraryId), LibraryReplicator.this.mAccountLibrary.getServerToken(libraryId))) {
                            if (LibraryReplicator.this.mEnableDebugging) {
                                LibraryReplicator.this.recordDebugEvent(4, null, null, null);
                            }
                            FinskyLog.wtf("Tokens changed, not applying library update for libraryId=%s", libraryId);
                        } else if (LibraryReplicator.this.internalApplyLibraryUpdate(libraryUpdate, LibraryReplicator.this.mCurrentRequest.debugTag)) {
                            hasMoreLibraries.add(libraryId);
                        }
                    }
                    LibraryReplicator.this.mSQLiteLibrary.close();
                    String[] autoAcquireTags = libraryReplicationResponse.autoAcquireFreeAppIfHigherVersionAvailableTag;
                    LibraryReplicator.this.mAccountLibrary.setAutoAcquireTags(autoAcquireTags);
                    FinskyPreferences.autoAcquireTags.get(LibraryReplicator.this.mAccountLibrary.getAccount().name).put(Utils.commaPackStrings(autoAcquireTags));
                    if (!hasMoreLibraries.isEmpty()) {
                        String[] continuationLibraries = new String[hasMoreLibraries.size()];
                        for (int i = 0; i < hasMoreLibraries.size(); i++) {
                            continuationLibraries[i] = (String) hasMoreLibraries.get(i);
                        }
                        LibraryReplicator.this.scheduleRequestAtFront(new ReplicationRequest(continuationLibraries, LibraryReplicator.this.mCurrentRequest.finishRunnable, LibraryReplicator.this.mCurrentRequest.debugTag + "[c]"));
                    } else if (LibraryReplicator.this.mCurrentRequest.finishRunnable != null) {
                        LibraryReplicator.this.mNotificationHandler.post(LibraryReplicator.this.mCurrentRequest.finishRunnable);
                    }
                    LibraryReplicator.this.mCurrentRequest = null;
                    LibraryReplicator.this.handleNextRequest(LibraryReplicator.RESCHEDULE_REPLICATION_MS);
                }
            });
        }

        public void onErrorResponse(VolleyError volleyError) {
            FinskyLog.w("Library replication failed: %s", volleyError);
            if (LibraryReplicator.this.mEnableDebugging) {
                LibraryReplicator.this.recordDebugEvent(3, null, volleyError, null);
            }
            LibraryReplicator.this.mBackgroundHandler.post(new Runnable() {
                public void run() {
                    if (LibraryReplicator.this.mCurrentRequest == null) {
                        FinskyLog.wtf("Expected pending replication request.", new Object[0]);
                        return;
                    }
                    if (LibraryReplicator.this.mCurrentRequest.finishRunnable != null) {
                        LibraryReplicator.this.mNotificationHandler.post(LibraryReplicator.this.mCurrentRequest.finishRunnable);
                    }
                    LibraryReplicator.this.mCurrentRequest = null;
                    LibraryReplicator.this.handleNextRequest(LibraryReplicator.RESCHEDULE_REPLICATION_MS);
                }
            });
        }
    }

    public interface Listener {
        void onMutationsApplied(String str);
    }

    private static class ReplicationRequest {
        public String debugTag;
        public final Runnable finishRunnable;
        public final String[] libraryIds;

        public ReplicationRequest(String[] libraryIds, Runnable finishRunnable, String debugTag) {
            this.libraryIds = libraryIds;
            this.finishRunnable = finishRunnable;
            this.debugTag = debugTag;
        }
    }

    static {
        RESCHEDULE_REPLICATION_MS = (long) ((Integer) G.libraryReplicatorRescheduleDelayMs.get()).intValue();
    }

    public LibraryReplicator(DfeApi dfeApi, SQLiteLibrary sqLiteLibrary, AccountLibrary accountLibrary, Handler notificationHandler, Handler backgroundHandler, boolean enableDebugging) {
        this.mListeners = Lists.newArrayList();
        this.mReplicationRequests = Lists.newArrayList();
        this.mReplicationRunnable = new Runnable() {
            public void run() {
                if (LibraryReplicator.this.mCurrentRequest == null) {
                    synchronized (this) {
                        if (LibraryReplicator.this.mReplicationRequests.size() == 0) {
                            return;
                        }
                        LibraryReplicator.this.mCurrentRequest = (ReplicationRequest) LibraryReplicator.this.mReplicationRequests.remove(0);
                        if (LibraryReplicator.this.mEnableDebugging) {
                            LibraryReplicator.this.recordDebugEvent(2, null, null, LibraryReplicator.this.mCurrentRequest.debugTag);
                        }
                        LibraryReplicationRequest request = new LibraryReplicationRequest();
                        int numLibraryIds = LibraryReplicator.this.mCurrentRequest.libraryIds.length;
                        request.libraryState = new ClientLibraryState[numLibraryIds];
                        for (int i = 0; i < numLibraryIds; i++) {
                            request.libraryState[i] = LibraryReplicator.this.buildLibraryState(LibraryReplicator.this.mCurrentRequest.libraryIds[i]);
                        }
                        LibraryUpdateListener listener = new LibraryUpdateListener();
                        LibraryReplicator.this.mDfeApi.replicateLibrary(request, listener, listener);
                    }
                } else if (FinskyLog.DEBUG) {
                    FinskyLog.v("Replication request pending.", new Object[0]);
                }
            }
        };
        this.mDfeApi = dfeApi;
        this.mSQLiteLibrary = sqLiteLibrary;
        this.mAccountLibrary = accountLibrary;
        this.mNotificationHandler = notificationHandler;
        this.mBackgroundHandler = backgroundHandler;
        this.mEnableDebugging = enableDebugging;
    }

    public synchronized void addListener(Listener listener) {
        this.mListeners.add(listener);
    }

    public synchronized void replicate(String[] libraryIds, Runnable finishRunnable, String debugTag) {
        if (this.mEnableDebugging) {
            debugTag = debugTag + " libraryIds=" + Arrays.toString(libraryIds);
            recordDebugEvent(1, null, null, debugTag);
        }
        libraryIds = getSupportedLibraries(libraryIds);
        if (libraryIds == null) {
            FinskyLog.d("Skipping replication request since all libraries are unsupported.", new Object[0]);
            this.mNotificationHandler.post(finishRunnable);
        } else {
            this.mReplicationRequests.add(new ReplicationRequest(libraryIds, finishRunnable, debugTag));
            handleNextRequest(0);
        }
    }

    private String[] getSupportedLibraries(String[] libraryIds) {
        int unsupportedLibraries = 0;
        for (String libraryId : libraryIds) {
            if (!this.mAccountLibrary.supportsLibrary(libraryId)) {
                unsupportedLibraries++;
            }
        }
        if (unsupportedLibraries == libraryIds.length) {
            return null;
        }
        if (unsupportedLibraries <= 0) {
            return libraryIds;
        }
        String[] result = new String[(libraryIds.length - unsupportedLibraries)];
        int supportedLibraryIndex = 0;
        for (String libraryId2 : libraryIds) {
            if (this.mAccountLibrary.supportsLibrary(libraryId2)) {
                result[supportedLibraryIndex] = libraryId2;
                supportedLibraryIndex++;
            }
        }
        return result;
    }

    private synchronized void scheduleRequestAtFront(ReplicationRequest request) {
        if (this.mEnableDebugging) {
            recordDebugEvent(1, null, null, request.debugTag);
        }
        this.mReplicationRequests.add(0, request);
    }

    private synchronized void handleNextRequest(long delayMs) {
        this.mBackgroundHandler.removeCallbacks(this.mReplicationRunnable);
        this.mBackgroundHandler.postDelayed(this.mReplicationRunnable, delayMs);
    }

    private ClientLibraryState buildLibraryState(String libraryId) {
        HashingLibrary library = this.mAccountLibrary.getLibrary(libraryId);
        ClientLibraryState result = new ClientLibraryState();
        result.corpus = AccountLibrary.getBackendFromLibraryId(libraryId);
        result.hasCorpus = true;
        result.libraryId = libraryId;
        result.hasLibraryId = true;
        result.hashCodeSum = library.getHash();
        result.hasHashCodeSum = true;
        result.librarySize = library.size();
        result.hasLibrarySize = true;
        byte[] token = this.mAccountLibrary.getServerToken(libraryId);
        if (token != null) {
            result.serverToken = token;
            result.hasServerToken = true;
        }
        return result;
    }

    public void applyLibraryUpdates(final String debugTag, final LibraryUpdate... libraryUpdates) {
        if (libraryUpdates != null && libraryUpdates.length != 0) {
            this.mBackgroundHandler.post(new Runnable() {
                public void run() {
                    LibraryReplicator.this.mSQLiteLibrary.reopen();
                    for (LibraryUpdate update : libraryUpdates) {
                        if (update != null) {
                            LibraryReplicator.this.internalApplyLibraryUpdate(update, debugTag);
                        }
                    }
                    LibraryReplicator.this.mSQLiteLibrary.close();
                }
            });
        }
    }

    private boolean internalApplyLibraryUpdate(LibraryUpdate libraryUpdate, String debugTag) {
        String libraryId;
        if (this.mEnableDebugging) {
            recordDebugEvent(0, libraryUpdate, null, debugTag);
        }
        checkOnBackgroundHandler();
        if (TextUtils.isEmpty(libraryUpdate.libraryId)) {
            libraryId = AccountLibrary.getLibraryIdFromBackend(libraryUpdate.corpus);
        } else {
            libraryId = libraryUpdate.libraryId;
        }
        if (this.mAccountLibrary.supportsLibrary(libraryId)) {
            this.mAccountLibrary.disableListeners();
            try {
                switch (libraryUpdate.status) {
                    case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                        if (FinskyLog.DEBUG) {
                            FinskyLog.v("NOT_MODIFIED received for libraryId=%s", libraryId);
                        }
                        this.mAccountLibrary.enableListeners();
                        return false;
                    case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                        this.mSQLiteLibrary.resetAccountLibrary(this.mAccountLibrary.getAccount(), libraryId);
                        this.mAccountLibrary.resetLibrary(libraryId);
                    case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                        List<LibraryEntry> additions = Lists.newArrayList(libraryUpdate.mutation.length);
                        boolean hasRemovals = false;
                        for (LibraryMutation mutation : libraryUpdate.mutation) {
                            LibraryEntry libraryEntry = createLibraryEntry(mutation, libraryId);
                            if (mutation.deleted) {
                                this.mAccountLibrary.remove(libraryEntry);
                                this.mSQLiteLibrary.remove(libraryEntry);
                                hasRemovals = true;
                            } else {
                                additions.add(libraryEntry);
                            }
                        }
                        this.mAccountLibrary.addAll(additions);
                        this.mSQLiteLibrary.addAll(additions);
                        if (!additions.isEmpty() || hasRemovals) {
                            notifyMutationListeners(libraryId);
                            break;
                        }
                    default:
                        Object[] objArr = new Object[2];
                        objArr[0] = libraryId;
                        objArr[1] = Integer.valueOf(libraryUpdate.status);
                        FinskyLog.wtf("Unknown LibraryUpdate.status: libraryId=%s, status=%d", objArr);
                        break;
                }
                if (libraryUpdate.serverToken.length > 0) {
                    byte[] token = libraryUpdate.serverToken;
                    this.mAccountLibrary.setServerToken(libraryId, token);
                    FinskyPreferences.getLibraryServerToken(libraryId).get(this.mAccountLibrary.getAccount().name).put(Base64.encodeToString(token, 0));
                    if (FinskyLog.DEBUG) {
                        FinskyLog.v("Updated server token: libraryId=%s serverToken=%s", libraryId, encodedToken);
                    }
                }
                this.mAccountLibrary.enableListeners();
                return libraryUpdate.hasMore;
            } catch (Throwable th) {
                this.mAccountLibrary.enableListeners();
            }
        } else {
            FinskyLog.d("Ignoring library update for unsupported library %s", libraryId);
            recordDebugEvent(5, null, null, debugTag);
            return false;
        }
    }

    private synchronized void notifyMutationListeners(final String libraryId) {
        for (final Listener listener : this.mListeners) {
            this.mNotificationHandler.post(new Runnable() {
                public void run() {
                    listener.onMutationsApplied(libraryId);
                }
            });
        }
    }

    private void checkOnBackgroundHandler() {
        if (Looper.myLooper() != this.mBackgroundHandler.getLooper()) {
            FinskyLog.wtf("This method must be called from the background handler.", new Object[0]);
        }
    }

    private LibraryEntry createLibraryEntry(LibraryMutation mutation, String libraryId) {
        String accountName = this.mAccountLibrary.getAccount().name;
        String docId = mutation.docid.backendDocid;
        int backend = mutation.docid.backend;
        int docType = mutation.docid.type;
        int offerType = mutation.offerType;
        long documentHash = mutation.documentHash;
        Long validUntilTimestampMs = null;
        if (mutation.hasValidUntilTimestampMsec) {
            validUntilTimestampMs = Long.valueOf(mutation.validUntilTimestampMsec);
        }
        if (!("u-wl".equals(libraryId) || mutation.deleted)) {
            if (docType == 1) {
                LibraryAppDetails appDetails = mutation.appDetails;
                String[] certificateHashes = appDetails.certificateHash;
                long refundPreDeliveryEndtimeMs = 0;
                if (appDetails.hasRefundTimeoutTimestampMsec) {
                    refundPreDeliveryEndtimeMs = appDetails.refundTimeoutTimestampMsec;
                }
                long refundPostDeliveryWindowMs = 0;
                if (appDetails.hasPostDeliveryRefundWindowMsec) {
                    refundPostDeliveryWindowMs = appDetails.postDeliveryRefundWindowMsec;
                }
                return new LibraryAppEntry(accountName, docId, offerType, documentHash, certificateHashes, refundPreDeliveryEndtimeMs, refundPostDeliveryWindowMs);
            } else if (docType == 15) {
                LibrarySubscriptionDetails subscriptionDetails = mutation.subscriptionDetails;
                long initiationTimeStampMs = subscriptionDetails.initiationTimestampMsec;
                if (validUntilTimestampMs == null) {
                    validUntilTimestampMs = Long.valueOf(subscriptionDetails.deprecatedValidUntilTimestampMsec);
                }
                long trialUntilTimestampMs = subscriptionDetails.hasTrialUntilTimestampMsec ? subscriptionDetails.trialUntilTimestampMsec : 0;
                boolean isAutoRenewing = subscriptionDetails.autoRenewing;
                if (backend != 3) {
                    return new LibrarySubscriptionEntry(accountName, libraryId, backend, docId, offerType, documentHash, validUntilTimestampMs, initiationTimeStampMs, trialUntilTimestampMs, isAutoRenewing);
                }
                return new LibraryInAppSubscriptionEntry(accountName, libraryId, backend, docId, offerType, documentHash, validUntilTimestampMs.longValue(), initiationTimeStampMs, trialUntilTimestampMs, isAutoRenewing, subscriptionDetails.signedPurchaseData, subscriptionDetails.signature);
            } else if (docType == 11 && mutation.inAppDetails != null) {
                LibraryInAppDetails inAppDetails = mutation.inAppDetails;
                return new LibraryInAppEntry(accountName, libraryId, docId, offerType, inAppDetails.signedPurchaseData, inAppDetails.signature, documentHash);
            }
        }
        return new LibraryEntry(accountName, libraryId, backend, docId, docType, offerType, documentHash, validUntilTimestampMs != null ? validUntilTimestampMs.longValue() : Long.MAX_VALUE, mutation.preordered);
    }

    private synchronized void recordDebugEvent(int type, LibraryUpdate libraryUpdate, VolleyError volleyError, String tag) {
        if (this.mDebugEvents == null) {
            this.mDebugEvents = Lists.newLinkedList();
        }
        DebugEvent debugEvent = new DebugEvent();
        debugEvent.timestampMs = System.currentTimeMillis();
        debugEvent.type = type;
        debugEvent.tag = tag;
        debugEvent.libraryUpdate = libraryUpdate;
        debugEvent.volleyError = volleyError;
        this.mDebugEvents.add(debugEvent);
        if (this.mDebugEvents.size() > 100) {
            this.mDebugEvents.remove();
        }
    }

    public void dumpState(String indent) {
        String scrubbedAccount = FinskyLog.scrubPii(this.mAccountLibrary.getAccount().name);
        Log.d("FinskyLibrary", indent + "LibraryReplicator (account=" + scrubbedAccount + ") {");
        if (this.mDebugEvents != null) {
            Log.d("FinskyLibrary", indent + "  eventsCount=" + this.mDebugEvents.size());
            for (DebugEvent debugEvent : this.mDebugEvents) {
                debugEvent.dumpState(indent);
            }
        } else {
            Log.d("FinskyLibrary", indent + "  eventsCount=0");
        }
        Log.d("FinskyLibrary", indent + "} (account=" + scrubbedAccount + ")");
    }
}
