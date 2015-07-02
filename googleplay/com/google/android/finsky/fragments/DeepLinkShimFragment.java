package com.google.android.finsky.fragments;

import android.accounts.Account;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.analytics.BackgroundEventBuilder;
import com.google.android.finsky.analytics.FinskyEventLog;
import com.google.android.finsky.analytics.PlayStore.AppData;
import com.google.android.finsky.analytics.PlayStore.PlayStoreUiElement;
import com.google.android.finsky.api.AccountHandler;
import com.google.android.finsky.appstate.AppStates;
import com.google.android.finsky.appstate.AppStates.AppState;
import com.google.android.finsky.appstate.InstallerDataStore;
import com.google.android.finsky.appstate.InstallerDataStore.InstallerData;
import com.google.android.finsky.config.G;
import com.google.android.finsky.protos.Common.Docid;
import com.google.android.finsky.protos.ResolveLink.DirectPurchase;
import com.google.android.finsky.protos.ResolveLink.ResolvedLink;
import com.google.android.finsky.receivers.Installer.InstallerState;
import com.google.android.finsky.utils.DocUtils;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.Sha1Util;
import com.google.android.finsky.utils.Utils;
import java.util.List;

public class DeepLinkShimFragment extends UrlBasedPageFragment implements Listener<ResolvedLink> {
    private String mReferringPackage;
    private ResolvedLink mResponse;
    private Uri mUri;

    public static Fragment newInstance(Uri uri, String referringPackage) {
        DeepLinkShimFragment fragment = new DeepLinkShimFragment();
        uri = unwrapRedirectUri(uri);
        String overrideAccount = findInstallAccount(AccountHandler.getAccounts(FinskyApp.get()), uri);
        if (!TextUtils.isEmpty(overrideAccount)) {
            fragment.setDfeAccount(overrideAccount);
            fragment.setArgument("DeepLinkShimFragment.overrideAccount", overrideAccount);
        }
        fragment.setDfeTocAndUrl(FinskyApp.get().getToc(), uri.toString());
        fragment.mReferringPackage = referringPackage;
        return fragment;
    }

    static Uri unwrapRedirectUri(Uri uri) {
        if (!"market".equals(uri.getScheme()) || !"webstoreredirect".equals(uri.getHost())) {
            return uri;
        }
        String redirectString = uri.getQueryParameter("uri");
        if (!TextUtils.isEmpty(redirectString)) {
            Uri redirect = Uri.parse(Uri.decode(redirectString));
            if ("play.google.com".equals(redirect.getHost())) {
                return redirect;
            }
        }
        FinskyLog.w("Unrecognized redirect URI: %s", FinskyLog.scrubPii(uri.toString()));
        return Uri.parse("http://play.google.com/store");
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.mUri = Uri.parse(this.mUrl);
        requestData();
    }

    protected int getLayoutRes() {
        return 0;
    }

    public boolean isDataReady() {
        return false;
    }

    protected void onInitViewBinders() {
    }

    protected void rebindViews() {
    }

    protected void requestData() {
        this.mDfeApi.resolveLink(this.mUrl, this.mReferringPackage, this, this);
        switchToLoading();
    }

    public void onResponse(ResolvedLink response) {
        this.mResponse = response;
        onDataChanged();
    }

    public void onErrorResponse(VolleyError error) {
        this.mNavigationManager.goToAggregatedHome(getToc());
    }

    public void onDataChanged() {
        if (this.mResponse != null && canChangeFragmentManagerState()) {
            this.mNavigationManager.popBackStack();
            FinskyEventLog logger = FinskyApp.get().getEventLogger();
            byte[] serverLogsCookie = this.mResponse.serverLogsCookie;
            if (this.mResponse.detailsUrl.length() > 0) {
                logger.logDeepLinkEvent(1, this.mUrl, null, this.mReferringPackage, serverLogsCookie);
                saveExternalReferrerForUrl(this.mUri, this.mResponse.detailsUrl);
                this.mNavigationManager.goToDocPage(this.mResponse.detailsUrl, getContinueUrl(this.mUri), getArguments().getString("DeepLinkShimFragment.overrideAccount"));
            } else if (this.mResponse.browseUrl.length() > 0) {
                logger.logDeepLinkEvent(2, this.mUrl, null, this.mReferringPackage, serverLogsCookie);
                this.mNavigationManager.goBrowse(this.mResponse.browseUrl, null, -1, getToc(), null);
            } else if (this.mResponse.searchUrl.length() > 0) {
                logger.logDeepLinkEvent(3, this.mUrl, null, this.mReferringPackage, serverLogsCookie);
                this.mNavigationManager.goToSearch(this.mResponse.searchUrl, this.mResponse.query, this.mResponse.backend, null);
            } else if (this.mResponse.wishlistUrl.length() > 0) {
                logger.logDeepLinkEvent(8, this.mUrl, null, this.mReferringPackage, serverLogsCookie);
                this.mNavigationManager.goToMyWishlist();
            } else if (this.mResponse.myAccountUrl.length() > 0) {
                logger.logDeepLinkEvent(10, this.mUrl, null, this.mReferringPackage, serverLogsCookie);
                this.mNavigationManager.goToMyAccount();
            } else if (this.mResponse.directPurchase != null) {
                FinskyLog.d("Direct purchase deprecated.", new Object[0]);
                DirectPurchase directPurchase = this.mResponse.directPurchase;
                logger.logDeepLinkEvent(4, this.mUrl, null, this.mReferringPackage, serverLogsCookie);
                saveExternalReferrerForDocId(this.mUri, this.mResponse.directPurchase.purchaseDocid);
                this.mNavigationManager.goToDocPage(directPurchase.detailsUrl, getContinueUrl(this.mUri), getArguments().getString("DeepLinkShimFragment.overrideAccount"));
            } else if (this.mResponse.homeUrl.length() > 0) {
                logger.logDeepLinkEvent(5, this.mUrl, null, this.mReferringPackage, serverLogsCookie);
                this.mNavigationManager.goToAggregatedHome(getToc(), this.mResponse.homeUrl);
            } else if (this.mResponse.redeemGiftCard != null) {
                logger.logDeepLinkEvent(6, this.mUrl, null, this.mReferringPackage, serverLogsCookie);
                this.mNavigationManager.goToAggregatedHome(getToc());
                this.mNavigationManager.goRedeem(this.mDfeApi.getAccountName(), this.mResponse.redeemGiftCard);
            } else {
                logger.logDeepLinkEvent(0, this.mUrl, null, this.mReferringPackage, serverLogsCookie);
                Intent browse = new Intent("android.intent.action.VIEW");
                browse.setData(Uri.parse(this.mUrl));
                browse.addFlags(268435456);
                browse.putExtra("dont_resolve_again", true);
                List<ResolveInfo> activities = getActivity().getPackageManager().queryIntentActivities(browse, 0);
                if (activities.size() == 2) {
                    ActivityInfo activityInfo = ((ResolveInfo) activities.get(0)).activityInfo;
                    if (activityInfo.packageName.equals(getActivity().getPackageName())) {
                        activityInfo = ((ResolveInfo) activities.get(1)).activityInfo;
                    }
                    browse.setPackage(activityInfo.packageName);
                }
                startActivity(browse);
            }
        }
    }

    private static void saveExternalReferrerForUrl(Uri incomingUri, String detailsUrl) {
        saveExternalReferrerForDocId(incomingUri, Uri.parse(detailsUrl).getQueryParameter("doc"));
    }

    private static void saveExternalReferrerForDocId(Uri incomingUri, String doc) {
        if (!TextUtils.isEmpty(doc) && DocUtils.docidToBackend(doc) == 3) {
            saveExternalReferrer(getExternalReferrer(incomingUri), DocUtils.createDocid(3, 1, doc));
        }
    }

    public static void saveExternalReferrer(final String externalReferrer, Docid docId) {
        if (docId.backend == 3 && docId.type == 1 && !TextUtils.isEmpty(externalReferrer)) {
            final String packageName = docId.backendDocid;
            final AppStates appStates = FinskyApp.get().getAppStates();
            appStates.load(new Runnable() {
                public void run() {
                    AppState appState = appStates.getApp(packageName);
                    InstallerDataStore installerDataStore = appStates.getInstallerDataStore();
                    String dropReason = DeepLinkShimFragment.saveReferrer(externalReferrer, packageName, appState, FinskyApp.get().getInstaller().getState(packageName), installerDataStore);
                    if (dropReason == null) {
                        FinskyLog.d("Capture referrer for %s", packageName);
                        DeepLinkShimFragment.logExternalReferrer(515, packageName, -1, null);
                        return;
                    }
                    FinskyLog.d("Dropped referrer for %s because %s", packageName, dropReason);
                    int installedVersion = -1;
                    if (!(appState == null || appState.packageManagerState == null)) {
                        installedVersion = appState.packageManagerState.installedVersion;
                    }
                    DeepLinkShimFragment.logExternalReferrer(516, packageName, installedVersion, dropReason);
                }
            });
        }
    }

    static String saveReferrer(String externalReferrer, String packageName, AppState appState, InstallerState installerState, InstallerDataStore installerDataStore) {
        boolean capture;
        boolean erase = false;
        String dropReason = null;
        boolean isInstalling = installerState.isDownloadingOrInstalling();
        boolean isInstalled = (appState == null || appState.packageManagerState == null) ? false : true;
        if (isInstalling || isInstalled) {
            String storedReferrer = null;
            if (!(appState == null || appState.installerData == null)) {
                InstallerData installerData = appState.installerData;
                storedReferrer = installerData.getExternalReferrer();
                if (!TextUtils.isEmpty(storedReferrer)) {
                    long referrerTimestamp = installerData.getExternalReferrerTimestampMs();
                    long lifespan = ((Long) G.externalReferrerLifespanMs.get()).longValue();
                    if (lifespan > 0 && referrerTimestamp + lifespan < System.currentTimeMillis()) {
                        storedReferrer = null;
                        erase = true;
                    }
                }
            }
            if (TextUtils.isEmpty(storedReferrer)) {
                capture = false;
                dropReason = "already-installed";
            } else if (storedReferrer.equals(externalReferrer)) {
                capture = true;
            } else {
                capture = false;
                dropReason = "already-captured";
            }
        } else {
            capture = true;
        }
        if (erase) {
            installerDataStore.setExternalReferrer(packageName, null);
            installerDataStore.setExternalReferrerTimestampMs(packageName, 0);
        }
        if (capture) {
            installerDataStore.setExternalReferrer(packageName, externalReferrer);
            installerDataStore.setExternalReferrerTimestampMs(packageName, System.currentTimeMillis());
        }
        return dropReason;
    }

    private static void logExternalReferrer(int backgroundEventType, String packageName, int installedVersion, String reason) {
        BackgroundEventBuilder builder = new BackgroundEventBuilder(backgroundEventType).setDocument(packageName).setReason(reason);
        if (installedVersion >= 0) {
            AppData appData = new AppData();
            appData.version = installedVersion;
            appData.hasVersion = true;
            builder.setAppData(appData);
        }
        FinskyApp.get().getEventLogger().logBackgroundEvent(builder.build());
    }

    public static String getExternalReferrer(Uri uri) {
        String externalReferrer = uri.getQueryParameter("referrer");
        if (TextUtils.isEmpty(externalReferrer)) {
            String gclid = uri.getQueryParameter("gclid");
            if (TextUtils.isEmpty(gclid)) {
                return null;
            }
            externalReferrer = Uri.encode("gclid=" + gclid);
        }
        return externalReferrer;
    }

    public static String getContinueUrl(Uri uri) {
        String url = uri.getQueryParameter("url");
        if (TextUtils.isEmpty(url)) {
            return null;
        }
        return Utils.urlDecode(url);
    }

    protected static String findInstallAccount(Account[] accounts, Uri uri) {
        String accountHash = uri.getQueryParameter("ah");
        if (TextUtils.isEmpty(accountHash)) {
            return null;
        }
        for (Account account : accounts) {
            if (Sha1Util.secureHash(account.name).equals(accountHash)) {
                return account.name;
            }
        }
        return null;
    }

    public PlayStoreUiElement getPlayStoreUiElement() {
        return null;
    }
}
