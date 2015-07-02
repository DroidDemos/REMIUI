package com.google.android.finsky.activities;

import android.app.ActivityManager;
import android.app.ActivityManager.RecentTaskInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Configuration;
import android.net.Uri.Builder;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentManager.OnBackStackChangedListener;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.android.vending.R;
import com.android.volley.AuthFailureError;
import com.android.volley.VolleyError;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.activities.SimpleAlertDialog.Listener;
import com.google.android.finsky.api.AccountHandler;
import com.google.android.finsky.api.DfeApi;
import com.google.android.finsky.api.DfeUtils;
import com.google.android.finsky.api.model.DfeToc;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.appstate.PackageStateRepository.PackageState;
import com.google.android.finsky.billing.CheckPromoOffersAction;
import com.google.android.finsky.billing.GetBillingCountriesAction;
import com.google.android.finsky.billing.PromptForFopHelper;
import com.google.android.finsky.billing.carrierbilling.CarrierBillingUtils;
import com.google.android.finsky.billing.giftcard.RedeemCodeResult;
import com.google.android.finsky.config.G;
import com.google.android.finsky.fragments.PageFragment;
import com.google.android.finsky.fragments.PageFragmentHost;
import com.google.android.finsky.layout.actionbar.ActionBarController;
import com.google.android.finsky.layout.actionbar.ActionBarController.ActionBarSearchModeListener;
import com.google.android.finsky.layout.actionbar.ActionBarHelper;
import com.google.android.finsky.layout.actionbar.FinskySearchToolbar;
import com.google.android.finsky.layout.play.FinskyDrawerLayout;
import com.google.android.finsky.navigationmanager.NavigationManager;
import com.google.android.finsky.previews.PreviewController;
import com.google.android.finsky.protos.ResolveLink.ResolvedLink;
import com.google.android.finsky.providers.RecentSuggestionsProvider;
import com.google.android.finsky.utils.BgDataDisabledError;
import com.google.android.finsky.utils.ErrorStrings;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.FinskyPreferences;
import com.google.android.finsky.utils.GPlusUtils;
import com.google.android.finsky.utils.IntentUtils;
import com.google.android.finsky.utils.Lists;
import com.google.android.finsky.utils.NotificationListener;
import com.google.android.finsky.utils.SessionStatsLogger;
import com.google.android.finsky.utils.hats.HappinessSurveyController;
import com.google.android.finsky.utils.hats.HatsUtils;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient.ConnectionCallbacks;
import com.google.android.gms.common.GooglePlayServicesClient.OnConnectionFailedListener;
import com.google.android.gms.people.PeopleClient;
import com.google.android.play.image.BitmapLoader;
import java.util.List;

public class MainActivity extends AuthenticatedActivity implements Listener, PageFragmentHost, ActionBarController {
    private static boolean IS_HC_OR_ABOVE;
    private static boolean sBillingInitialized;
    private int mBitmapSequenceNumberToDrainFrom;
    public ViewGroup mContentFrame;
    private FinskyDrawerLayout mDrawerLayout;
    private final Handler mHandler;
    private HappinessSurveyController mHatsSurveyController;
    private int mLastShownErrorHash;
    private NavigationManager mNavigationManager;
    private final NotificationListener mNotificationListener;
    private boolean mPageNeedsRefresh;
    private PeopleClient mPeopleClient;
    private Bundle mSavedInstanceState;
    private ActionBarSearchModeListener mSearchModeListener;
    private int mSequenceNumberToDrainFrom;
    private final Runnable mStopPreviewsRunnable;

    public MainActivity() {
        this.mPageNeedsRefresh = false;
        this.mSequenceNumberToDrainFrom = -1;
        this.mBitmapSequenceNumberToDrainFrom = -1;
        this.mHandler = new Handler();
        this.mSearchModeListener = null;
        this.mNotificationListener = new NotificationListener() {
            public boolean showDocAlert(String docId, String title, String htmlMessage, String detailsUrl) {
                if (MainActivity.this.mNavigationManager.getCurrentDocument() == null) {
                    return false;
                }
                if (!MainActivity.this.mNavigationManager.getCurrentDocument().getDocId().equals(docId) && !DfeUtils.isSameDocumentDetailsUrl(detailsUrl, MainActivity.this.mNavigationManager.getCurrentDocument().getDetailsUrl())) {
                    return false;
                }
                MainActivity.this.showErrorDialog(title, htmlMessage, false);
                return true;
            }

            public boolean showAppAlert(String packageName, String title, String htmlMessage, int returnCode) {
                Document doc = MainActivity.this.mNavigationManager.getCurrentDocument();
                if (doc != null && doc.getBackend() == 3 && doc.getAppDetails().packageName.equals(packageName)) {
                    return MainActivity.this.showErrorDialogForCode(title, htmlMessage, returnCode, packageName);
                }
                return false;
            }

            public boolean showAppNotification(String packageName, String title, String htmlMessage) {
                Document doc = MainActivity.this.mNavigationManager.getCurrentDocument();
                if (doc != null && doc.getBackend() == 3 && doc.getAppDetails().packageName.equals(packageName)) {
                    return true;
                }
                return false;
            }
        };
        this.mStopPreviewsRunnable = new Runnable() {
            public void run() {
                PreviewController.reset();
            }
        };
    }

    static {
        IS_HC_OR_ABOVE = VERSION.SDK_INT >= 11;
    }

    public static Intent getMyDownloadsIntent(Context context) {
        return new Intent("com.google.android.finsky.VIEW_MY_DOWNLOADS").setClass(context, MainActivity.class);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.main);
        setSupportActionBar((Toolbar) findViewById(R.id.action_bar));
        this.mSavedInstanceState = savedInstanceState;
        this.mContentFrame = (ViewGroup) findViewById(R.id.content_frame);
        this.mNavigationManager = new NavigationManager(this);
        this.mNavigationManager.init(this);
        this.mNavigationManager.addOnBackStackChangedListener(new OnBackStackChangedListener() {
            private boolean initialCleanupDone;

            public void onBackStackChanged() {
                if (!this.initialCleanupDone) {
                    MainActivity.this.hideLoadingIndicator();
                    MainActivity.this.hideErrorMessage();
                    this.initialCleanupDone = true;
                }
                if (MainActivity.this.mNavigationManager.isActionBarOverlayEnabledForCurrent()) {
                    MainActivity.this.enableActionBarOverlay();
                    return;
                }
                MainActivity.this.setActionBarAlpha(255, false);
                MainActivity.this.disableActionBarOverlay();
            }
        });
        if (savedInstanceState != null) {
            this.mLastShownErrorHash = savedInstanceState.getInt("last_shown_error_hash");
        }
        if (!this.mNavigationManager.isBackStackEmpty()) {
            hideLoadingIndicator();
            hideErrorMessage();
        }
        this.mDrawerLayout = (FinskyDrawerLayout) findViewById(R.id.drawer_layout);
        this.mActionBarHelper = new ActionBarHelper(this.mNavigationManager, this, this);
        this.mDrawerLayout.configure(this, savedInstanceState);
        setDefaultKeyMode(3);
        this.mPeopleClient = new PeopleClient(getApplicationContext(), new ConnectionCallbacks() {
            public void onDisconnected() {
            }

            public void onConnected(Bundle arg) {
            }
        }, new OnConnectionFailedListener() {
            public void onConnectionFailed(ConnectionResult arg) {
            }
        }, 121);
    }

    public ActionBarHelper getActionBarHelper() {
        return this.mActionBarHelper;
    }

    public Object onRetainCustomNonConfigurationInstance() {
        this.mHandler.removeCallbacks(this.mStopPreviewsRunnable);
        return super.onRetainCustomNonConfigurationInstance();
    }

    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        this.mDrawerLayout.syncDrawerIndicator();
    }

    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        this.mDrawerLayout.onConfigurationChanged(newConfig);
    }

    private void handleIntent() {
        hideLoadingIndicator();
        hideErrorMessage();
        Intent currentIntent = getIntent();
        String newAccountName = currentIntent.getStringExtra("authAccount");
        if (newAccountName != null) {
            currentIntent.removeExtra("authAccount");
            FinskyLog.d("b/5160617: Switching account to %s due to intent", FinskyLog.scrubPii(newAccountName));
            switchAccount(newAccountName, currentIntent);
        } else if (!maybeShowDownloadManagerDisabledDialog() && !maybeShowGmsCoreDisabledDialog()) {
            maybeShowErrorDialog(currentIntent);
            if (this.mDrawerLayout != null) {
                this.mDrawerLayout.closeDrawer();
            }
            String intentAction = currentIntent.getAction();
            if ("android.intent.action.SEARCH".equals(intentAction)) {
                handleSearchIntent(currentIntent);
            } else if ("com.google.android.finsky.NAVIGATIONAL_SUGGESTION".equals(intentAction)) {
                String docId = RecentSuggestionsProvider.getDocIdFromNavigationalQuery(currentIntent);
                if (!TextUtils.isEmpty(docId)) {
                    sendSuggestionsReport(currentIntent, docId);
                }
                handleViewIntent(currentIntent);
            } else if ("android.intent.action.VIEW".equals(intentAction) || "android.nfc.action.NDEF_DISCOVERED".equals(intentAction)) {
                this.mNavigationManager.clear();
                handleViewIntent(currentIntent);
            } else if ("com.google.android.finsky.DETAILS".equals(intentAction)) {
                this.mNavigationManager.goToDocPage(currentIntent.getDataString());
            } else if ("com.google.android.finsky.VIEW_MY_DOWNLOADS".equals(intentAction)) {
                this.mNavigationManager.clear();
                this.mNavigationManager.goToMyDownloads(FinskyApp.get().getToc());
            } else if ("com.google.android.finsky.CORPUS_HOME".equals(intentAction)) {
                backendId = currentIntent.getIntExtra("backend_id", 0);
                title = currentIntent.getStringExtra("title");
                if (backendId == 0) {
                    this.mNavigationManager.goToAggregatedHome(FinskyApp.get().getToc());
                } else {
                    this.mNavigationManager.goToCorpusHome(currentIntent.getDataString(), title, backendId, FinskyApp.get().getToc());
                }
            } else if ("com.google.android.finsky.VIEW_BROWSE".equals(intentAction)) {
                backendId = currentIntent.getIntExtra("backend_id", 0);
                DfeToc dfeToc = FinskyApp.get().getToc();
                if (dfeToc.getCorpus(backendId) == null) {
                    this.mNavigationManager.goToAggregatedHome(dfeToc);
                    return;
                }
                title = currentIntent.getStringExtra("title");
                String listUrl = currentIntent.getDataString();
                if (currentIntent.getBooleanExtra("clear_back_stack", false)) {
                    this.mNavigationManager.clear();
                }
                this.mNavigationManager.goBrowse(listUrl, title, backendId, FinskyApp.get().getToc(), null);
            } else if (this.mNavigationManager.isBackStackEmpty()) {
                this.mNavigationManager.goToAggregatedHome(FinskyApp.get().getToc());
            }
        }
    }

    private String getReferringPackage() {
        try {
            List<RecentTaskInfo> tasks = ((ActivityManager) getSystemService("activity")).getRecentTasks(1, 0);
            if (tasks.size() > 0) {
                return ((RecentTaskInfo) tasks.get(0)).baseIntent.getComponent().getPackageName();
            }
        } catch (Exception e) {
            FinskyLog.e(e, "Exception while getting package.", new Object[0]);
        }
        return null;
    }

    private void handleViewIntent(Intent currentIntent) {
        FinskyApp.get().getAnalytics().logAdMobPageView(buildAnalyticsUrl("deepLink", currentIntent));
        if (getIntent().getBooleanExtra("dont_resolve_again", false)) {
            openInBrowser(currentIntent);
        } else {
            this.mNavigationManager.handleDeepLink(currentIntent.getDataString(), getReferringPackage());
        }
    }

    private void openInBrowser(Intent intent) {
        PackageManager pm = getPackageManager();
        intent.setComponent(null);
        List<ResolveInfo> activities = pm.queryIntentActivities(intent, 0);
        int N = activities.size();
        int i = 0;
        while (i < N) {
            ActivityInfo ai = ((ResolveInfo) activities.get(i)).activityInfo;
            if (ai.packageName.equals(getPackageName())) {
                i++;
            } else {
                intent.setComponent(new ComponentName(ai.packageName, ai.name));
                startActivity(intent);
                finish();
                return;
            }
        }
    }

    private String buildAnalyticsUrl(String tag, Intent intent) {
        return new Builder().path(tag).appendQueryParameter("url", intent.getDataString()).appendQueryParameter("action", intent.getAction()).build().toString();
    }

    private void handleSearchIntent(Intent intent) {
        if (isTosAccepted()) {
            String query = intent.getStringExtra("query");
            sendSuggestionsReport(intent, query);
            FinskyApp.get().getRecentSuggestions().saveRecentQuery(query, null);
            this.mNavigationManager.goToSearch(query, getCurrentBackend(), null);
        }
    }

    private void sendSuggestionsReport(Intent intent, String suggestedQuery) {
        String suggestionData = intent.getStringExtra("intent_extra_data_key");
        if (!TextUtils.isEmpty(suggestionData)) {
            RecentSuggestionsProvider.sendSuggestionClickedLog(suggestionData, suggestedQuery);
        }
    }

    public int getCurrentBackend() {
        return this.mActionBarHelper.getCurrentBackendId();
    }

    protected void onReady(final boolean shouldHandleIntent) {
        SessionStatsLogger.logSessionStatsIfNecessary(this);
        GaiaRecoveryHelper.prefetchAndCacheGaiaAuthRecoveryIntent(this, FinskyApp.get().getCurrentAccountName());
        PromptForFopHelper.refreshHasFopCacheIfNecessary(FinskyApp.get().getDfeApi());
        checkHasPromoOffers(new Runnable() {
            public void run() {
                boolean intentHandled = false;
                if ((MainActivity.this.mSavedInstanceState == null || !MainActivity.this.mNavigationManager.deserialize(MainActivity.this.mSavedInstanceState)) && shouldHandleIntent) {
                    MainActivity.this.handleIntent();
                    intentHandled = true;
                }
                if (!intentHandled) {
                    MainActivity.this.checkBackstackExpired();
                }
                MainActivity.this.mDrawerLayout.refresh();
                MainActivity.this.mSavedInstanceState = null;
                MainActivity.this.initializeBilling();
            }
        });
        this.mDrawerLayout.refresh();
    }

    private void checkBackstackExpired() {
        if (!this.mNavigationManager.isBackStackEmpty() && FinskyApp.get().getExperiments().isEnabled("cl:ui.clear_backstack_after_timeout") && ((Long) FinskyPreferences.lastTimeBackStackUpdatedMs.get()).longValue() < System.currentTimeMillis() - 57600000) {
            this.mNavigationManager.clear();
            this.mNavigationManager.goToAggregatedHome(FinskyApp.get().getToc());
        }
    }

    private void initializeBilling() {
        if (!sBillingInitialized) {
            sBillingInitialized = true;
            FinskyLog.d("Optimistically initializing billing parameters.", new Object[0]);
            CarrierBillingUtils.initializeCarrierBillingStorage(new Runnable() {
                public void run() {
                    new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                        public void run() {
                            CarrierBillingUtils.initializeCarrierBillingParams(MainActivity.this, true, new Runnable() {
                                public void run() {
                                    MainActivity.this.initializeBillingCountries();
                                }
                            });
                        }
                    }, (long) ((Integer) G.initializeBillingDelayMs.get()).intValue());
                }
            });
        }
    }

    private void checkHasPromoOffers(Runnable callback) {
        new CheckPromoOffersAction(this, FinskyApp.get().getDfeApi()).run(callback);
    }

    private void initializeBillingCountries() {
        new GetBillingCountriesAction().run(FinskyApp.get().getCurrentAccountName(), null);
    }

    protected void onSaveInstanceState(Bundle outState) {
        if (this.mSavedInstanceState != null) {
            outState.putAll(this.mSavedInstanceState);
        } else {
            this.mNavigationManager.serialize(outState);
        }
        super.onSaveInstanceState(outState);
        outState.putInt("last_shown_error_hash", this.mLastShownErrorHash);
        this.mDrawerLayout.onSaveInstanceState(outState);
    }

    public NavigationManager getNavigationManager() {
        return this.mNavigationManager;
    }

    public void updateBreadcrumb(String breadcrumb) {
        this.mActionBarHelper.updateDefaultTitle(breadcrumb);
    }

    public void updateCurrentBackendId(int backend, boolean ignoreActionBarBackground) {
        this.mActionBarHelper.updateCurrentBackendId(backend, ignoreActionBarBackground);
        this.mDrawerLayout.updateCurrentBackendId(backend);
    }

    public void updateActionBarMode(boolean searchBoxOnly) {
        this.mActionBarHelper.updateActionBarMode(searchBoxOnly);
    }

    public void maybeShowSatisfactionSurvey(PageFragment fragment, boolean skipPrompt) {
        boolean isCorpusHome;
        boolean isBrowsePage = true;
        int currentPageType = this.mNavigationManager.getCurrentPageType();
        if (currentPageType == 2) {
            isCorpusHome = true;
        } else {
            isCorpusHome = false;
        }
        boolean isDetailsPage;
        if (currentPageType == 5) {
            isDetailsPage = true;
        } else {
            isDetailsPage = false;
        }
        boolean isSearchPage;
        if (currentPageType == 7) {
            isSearchPage = true;
        } else {
            isSearchPage = false;
        }
        if (currentPageType != 4) {
            isBrowsePage = false;
        }
        if (isCorpusHome || isDetailsPage || isSearchPage || isBrowsePage || skipPrompt) {
            String siteId = (!skipPrompt || this.mHatsSurveyController == null) ? HatsUtils.getSiteId(this.mNavigationManager) : this.mHatsSurveyController.getSiteId();
            if (fragment != null && siteId != null) {
                this.mHatsSurveyController = new HappinessSurveyController(fragment, siteId, skipPrompt);
                this.mHatsSurveyController.start();
            }
        }
    }

    private void resetCurrentBackendId() {
        if (this.mActionBarHelper != null) {
            this.mActionBarHelper.updateCurrentBackendId(0, false);
        }
        if (this.mDrawerLayout != null) {
            this.mDrawerLayout.updateCurrentBackendId(0);
        }
    }

    public void setStatusBarBackgroundColor(int color) {
        this.mDrawerLayout.setStatusBarBackgroundColor(color);
    }

    public ActionBarController getActionBarController() {
        return this;
    }

    public void enableActionBarOverlay() {
        this.mNavigationManager.setActionBarOverlayEnabledForCurrent(true);
        if (this.mContentFrame.getPaddingTop() != 0) {
            this.mContentFrame.setPadding(this.mContentFrame.getPaddingLeft(), 0, this.mContentFrame.getPaddingRight(), this.mContentFrame.getPaddingBottom());
        }
    }

    public void disableActionBarOverlay() {
        this.mContentFrame.clearAnimation();
        this.mActionBarHelper.cancelCurrentActionBarAlphaAnimation();
        this.mNavigationManager.setActionBarOverlayEnabledForCurrent(false);
        int currentTopPadding = this.mContentFrame.getPaddingTop();
        int contentFrameOriginalTopPadding = Math.max(FinskySearchToolbar.getToolbarHeight(this), getSupportActionBar().getHeight());
        if (currentTopPadding != contentFrameOriginalTopPadding) {
            this.mContentFrame.setPadding(this.mContentFrame.getPaddingLeft(), contentFrameOriginalTopPadding, this.mContentFrame.getPaddingRight(), this.mContentFrame.getPaddingBottom());
        }
    }

    public void setActionBarAlpha(int alpha, boolean isTransient) {
        this.mActionBarHelper.setActionBarAlpha(alpha, isTransient);
    }

    public void enterActionBarSearchMode() {
        this.mActionBarHelper.enterActionBarSearchMode(this.mContentFrame);
        if (this.mSearchModeListener != null) {
            this.mSearchModeListener.onEnterActionBarSearchMode();
        }
        if (IS_HC_OR_ABOVE) {
            this.mContentFrame.setLayerType(2, null);
        }
    }

    public void exitActionBarSearchMode() {
        this.mActionBarHelper.exitActionBarSearchMode(this.mContentFrame);
        if (this.mSearchModeListener != null) {
            this.mSearchModeListener.onExitActionBarSearchMode();
        }
        if (IS_HC_OR_ABOVE) {
            this.mContentFrame.setLayerType(0, null);
        }
    }

    public void setActionBarSearchModeListener(ActionBarSearchModeListener listener) {
        this.mSearchModeListener = listener;
    }

    public void enterActionBarSectionExpandedMode(CharSequence expandedModeTitle, TextSectionTranslatable expandedModeTranslatable) {
        this.mDrawerLayout.setDrawerLockMode(1);
        this.mActionBarHelper.enterActionBarSectionExpandedMode(this.mContentFrame, expandedModeTitle, expandedModeTranslatable);
    }

    public void exitActionBarSectionExpandedMode() {
        this.mDrawerLayout.setDrawerLockMode(0);
        this.mActionBarHelper.exitActionBarSectionExpandedMode(this.mContentFrame);
    }

    public void enterDrawerOpenMode(CharSequence drawerOpenModeTitle) {
        if (IS_HC_OR_ABOVE) {
            this.mContentFrame.setLayerType(2, null);
        }
    }

    public void exitDrawerOpenMode() {
        if (IS_HC_OR_ABOVE) {
            this.mContentFrame.setLayerType(0, null);
        }
    }

    public void goBack() {
        onBackPressed();
    }

    protected void onResume() {
        super.onResume();
        if (this.mPageNeedsRefresh) {
            this.mNavigationManager.refreshPage();
            this.mPageNeedsRefresh = false;
        }
        FinskyApp.get().getNotifier().setNotificationListener(this.mNotificationListener);
        if (this.mDrawerLayout != null) {
            this.mDrawerLayout.syncDrawerIndicator();
        }
    }

    protected void onPause() {
        super.onPause();
        FinskyApp.get().getNotifier().setNotificationListener(null);
        this.mSequenceNumberToDrainFrom = FinskyApp.get().getRequestQueue().getSequenceNumber();
        this.mBitmapSequenceNumberToDrainFrom = FinskyApp.get().getBitmapRequestQueue().getSequenceNumber();
        FinskyPreferences.lastTimeBackStackUpdatedMs.put(Long.valueOf(System.currentTimeMillis()));
    }

    protected void onRestart() {
        super.onRestart();
        this.mPageNeedsRefresh = true;
    }

    public DfeApi getDfeApi(String dfeAccount) {
        return FinskyApp.get().getDfeApi(dfeAccount);
    }

    public BitmapLoader getBitmapLoader() {
        return FinskyApp.get().getBitmapLoader();
    }

    protected void onCleanup() {
        FinskyApp.get().drainAllRequests(FinskyApp.get().getRequestQueue().getSequenceNumber(), FinskyApp.get().getBitmapRequestQueue().getSequenceNumber());
        FinskyApp.get().clearCacheAsync(null);
        if (this.mStateSaved) {
            FinskyLog.wtf("Should not be here after state was saved", new Object[0]);
            return;
        }
        if (this.mNavigationManager != null) {
            this.mNavigationManager.clear();
            this.mNavigationManager.flush();
        }
        if (this.mContentFrame != null) {
            int childCount = this.mContentFrame.getChildCount();
            List<View> childrenToRemove = Lists.newArrayList();
            for (int i = 0; i < childCount; i++) {
                View child = this.mContentFrame.getChildAt(i);
                int childId = child.getId();
                if (!(childId == R.id.placeholder_loading || childId == R.id.placeholder_error)) {
                    childrenToRemove.add(child);
                }
            }
            for (View childToRemove : childrenToRemove) {
                this.mContentFrame.removeView(childToRemove);
            }
        }
        showLoadingIndicator();
    }

    protected void onNewIntent(Intent intent) {
        setIntent(intent);
        if (this.mStateSaved) {
            this.mStateSaved = false;
            super.onNewIntent(intent);
            return;
        }
        handleIntent();
        onNewIntentDirect(intent);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        boolean success = false;
        if (requestCode == 31 && resultCode == 40) {
            FinskyLog.d("b/5160617: Reinitialize with null accountafter user changed content level", new Object[0]);
            runOrScheduleActiveStateRunnable(new Runnable() {
                public void run() {
                    MainActivity.this.reinitialize(null, null, true);
                }
            });
            return;
        }
        if (requestCode == 33) {
            int backendId = -1;
            boolean hasShownHeavyDialog = true;
            boolean isGame = false;
            if (intent != null) {
                backendId = intent.getIntExtra("backend", -1);
                hasShownHeavyDialog = intent.getBooleanExtra("involved_heavy_dialogs", true);
                isGame = PlayGamesInstallHelper.isGameIntent(intent);
                intent = null;
            }
            if (resultCode == -1 && backendId == 3 && !hasShownHeavyDialog) {
                if (GaiaRecoveryHelper.shouldShowGaiaRecoveryDialog()) {
                    new Handler().post(new Runnable() {
                        public void run() {
                            GaiaRecoveryHelper.launchGaiaRecoveryDialog(MainActivity.this.getResources(), MainActivity.this.getSupportFragmentManager(), 37, "dialog.gaia_recovery_migration");
                        }
                    });
                } else if (AutoUpdateMigrationHelper.shouldShowAutoUpdateMigrationDialog()) {
                    new Handler().post(new Runnable() {
                        public void run() {
                            if (!MainActivity.this.isStateSaved()) {
                                AutoUpdateMigrationHelper.launchAutoUpdateMigrationDialog(MainActivity.this.getSupportFragmentManager(), 36, "dialog.auto_update_migration");
                            }
                        }
                    });
                } else if (isGame && PlayGamesInstallHelper.shouldSuggestPlayGames()) {
                    new Handler().post(new Runnable() {
                        public void run() {
                            if (!MainActivity.this.isStateSaved()) {
                                PlayGamesInstallHelper.launchPlayGamesInstallDialog(MainActivity.this.getSupportFragmentManager(), 38, "dialog.play_games_install_suggestion");
                            }
                        }
                    });
                }
            }
        } else if (requestCode == 34) {
            if (intent != null) {
                RedeemCodeResult redeemCodeResult = (RedeemCodeResult) intent.getParcelableExtra("RedeemCodeBaseActivity.redeem_code_result");
                if (!(redeemCodeResult == null || redeemCodeResult.getLink() == null)) {
                    ResolvedLink resolvedLink = redeemCodeResult.getLink().resolvedLink;
                    if (resolvedLink == null) {
                        FinskyLog.wtf("Unexpected missing resolvedLink", new Object[0]);
                    } else if (TextUtils.isEmpty(resolvedLink.browseUrl)) {
                        FinskyLog.wtf("Unexpected missing browseUrl", new Object[0]);
                    } else {
                        startActivity(IntentUtils.createBrowseIntent(this, resolvedLink.browseUrl, null, resolvedLink.backend, false));
                    }
                }
            }
        } else if (requestCode == 35) {
            if (resultCode == -1) {
                success = true;
            }
            FinskyApp.get().getEventLogger().logOperationSuccessBackgroundEvent(503, success);
            if (success) {
                FinskyApp.get().getPlayDfeApi().invalidatePlusProfile(true);
                this.mHandler.postDelayed(new Runnable() {
                    public void run() {
                        if (!MainActivity.this.mStateSaved) {
                            MainActivity.this.mDrawerLayout.refresh();
                        }
                    }
                }, 3000);
            }
        } else if (requestCode == 39) {
            GPlusUtils.circlePickerOnActivityResult(resultCode, intent);
        }
        super.onActivityResult(requestCode, resultCode, intent);
    }

    protected void onDestroy() {
        this.mNavigationManager.terminate();
        super.onDestroy();
    }

    protected void onStop() {
        super.onStop();
        this.mHandler.post(this.mStopPreviewsRunnable);
        if (this.mSequenceNumberToDrainFrom == -1) {
            this.mSequenceNumberToDrainFrom = FinskyApp.get().getRequestQueue().getSequenceNumber();
        }
        if (this.mBitmapSequenceNumberToDrainFrom == -1) {
            this.mBitmapSequenceNumberToDrainFrom = FinskyApp.get().getBitmapRequestQueue().getSequenceNumber();
        }
        FinskyApp.get().drainAllRequests(this.mSequenceNumberToDrainFrom, this.mBitmapSequenceNumberToDrainFrom);
        this.mSequenceNumberToDrainFrom = -1;
        this.mBitmapSequenceNumberToDrainFrom = -1;
        this.mPeopleClient.disconnect();
    }

    public void onBackPressed() {
        if (this.mDrawerLayout.isDrawerOpen()) {
            this.mDrawerLayout.closeDrawer();
            return;
        }
        PageFragment currPage = this.mNavigationManager.getActivePage();
        if ((currPage == null || !currPage.onBackPressed()) && !this.mNavigationManager.goBack()) {
            super.onBackPressed();
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.actions, menu);
        this.mActionBarHelper.configureMenu(this, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (this.mDrawerLayout.onOptionsItemSelected(item)) {
            return true;
        }
        switch (item.getItemId()) {
            case 16908332:
                this.mDrawerLayout.closeDrawer();
                PageFragment currPage = this.mNavigationManager.getActivePage();
                if ((currPage != null && currPage.onBackPressed()) || this.mNavigationManager.goUp()) {
                    return true;
                }
                super.onBackPressed();
                return true;
            case R.id.translate_button:
                this.mActionBarHelper.translateButtonClicked();
                return true;
            case R.id.auto_update_button:
                this.mActionBarHelper.autoUpdateButtonClicked(this);
                return true;
            case R.id.env_button:
                Toast.makeText(this, "Environment indicator (not visible externally)", 0).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean showErrorDialogForCode(java.lang.String r6, java.lang.String r7, int r8, java.lang.String r9) {
        /*
        r5 = this;
        r3 = 0;
        r2 = 1;
        r4 = com.google.android.finsky.FinskyApp.get();
        r4 = r4.getPackageInfoRepository();
        r1 = r4.get(r9);
        switch(r8) {
            case 1: goto L_0x0015;
            default: goto L_0x0011;
        };
    L_0x0011:
        r5.showErrorDialog(r6, r7, r3);
    L_0x0014:
        return r2;
    L_0x0015:
        if (r1 == 0) goto L_0x0022;
    L_0x0017:
        r4 = r1.isSystemApp;
        if (r4 == 0) goto L_0x0022;
    L_0x001b:
        r0 = r2;
    L_0x001c:
        if (r0 != 0) goto L_0x0011;
    L_0x001e:
        r5.showMismatchedCertificatesDialog(r9);
        goto L_0x0014;
    L_0x0022:
        r0 = r3;
        goto L_0x001c;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.finsky.activities.MainActivity.showErrorDialogForCode(java.lang.String, java.lang.String, int, java.lang.String):boolean");
    }

    private boolean maybeShowDownloadManagerDisabledDialog() {
        PackageState state = FinskyApp.get().getPackageInfoRepository().get("com.android.providers.downloads");
        if (state == null) {
            FinskyLog.wtf("Cannot find com.android.providers.downloads", new Object[0]);
            return false;
        } else if (!state.isDisabled && !state.isDisabledByUser) {
            return false;
        } else {
            FinskyLog.w("Detected disabled com.android.providers.downloads", new Object[0]);
            FragmentManager fragmentManager = getSupportFragmentManager();
            if (fragmentManager.findFragmentByTag("download_manager_disabled") == null) {
                SimpleAlertDialog.Builder builder = new SimpleAlertDialog.Builder();
                builder.setCallback(null, 40, null).setMessageId(R.string.download_manager_disabled).setPositiveId(R.string.ok).setNegativeId(R.string.cancel).setCanceledOnTouchOutside(false);
                builder.build().show(fragmentManager, "download_manager_disabled");
            }
            return true;
        }
    }

    private boolean maybeShowGmsCoreDisabledDialog() {
        if (VERSION.SDK_INT < 19) {
            return false;
        }
        PackageState state = FinskyApp.get().getPackageInfoRepository().get("com.google.android.gms");
        if (state == null) {
            FinskyLog.wtf("Cannot find com.google.android.gms", new Object[0]);
            return false;
        } else if (!state.isDisabled && !state.isDisabledByUser) {
            return false;
        } else {
            FinskyLog.w("Detected disabled com.google.android.gms", new Object[0]);
            FragmentManager fragmentManager = getSupportFragmentManager();
            if (fragmentManager.findFragmentByTag("gms_core_disabled") == null) {
                SimpleAlertDialog.Builder builder = new SimpleAlertDialog.Builder();
                builder.setCallback(null, 42, null).setMessageId(R.string.gms_core_disabled).setPositiveId(R.string.ok).setNegativeId(R.string.cancel).setCanceledOnTouchOutside(false);
                builder.build().show(fragmentManager, "gms_core_disabled");
            }
            return true;
        }
    }

    private void showRestartRequiredDialog() {
        SimpleAlertDialog.Builder builder = new SimpleAlertDialog.Builder();
        builder.setCallback(null, 41, null).setMessageId(R.string.restart_required).setPositiveId(R.string.ok).setCanceledOnTouchOutside(false);
        builder.build().show(getSupportFragmentManager(), "restart_required");
    }

    private void maybeShowErrorDialog(Intent intent) {
        if (intent.hasExtra("error_html_message")) {
            String title = null;
            if (intent.hasExtra("error_title")) {
                title = intent.getStringExtra("error_title");
            }
            String message = intent.getStringExtra("error_html_message");
            String docId = intent.getStringExtra("error_doc_id");
            int errorHash = (message + title + docId).hashCode();
            if (this.mLastShownErrorHash != errorHash) {
                showErrorDialogForCode(title, message, intent.getIntExtra("error_return_code", -1), docId);
                this.mLastShownErrorHash = errorHash;
            }
        }
    }

    public void showErrorDialog(String title, String message, boolean goBack) {
        if (TextUtils.isEmpty(message)) {
            FinskyLog.wtf("Unknown error with empty error message.", new Object[0]);
        } else if (this.mStateSaved) {
            FinskyLog.e(message, new Object[0]);
            PageFragment activePage = this.mNavigationManager.getActivePage();
            if (activePage != null) {
                activePage.refreshOnResume();
            }
        } else {
            ErrorDialog.show(getSupportFragmentManager(), null, message, goBack);
        }
    }

    protected void handleAuthenticationError(VolleyError error) {
        if (error instanceof AuthFailureError) {
            Intent intent = ((AuthFailureError) error).getResolutionIntent();
            if (intent != null) {
                handleUserAuthentication(intent);
                return;
            }
        }
        hideLoadingIndicator();
        showErrorMessage(error);
        this.mDrawerLayout.openDrawer();
    }

    private void hideErrorMessage() {
        findViewById(R.id.placeholder_error).setVisibility(8);
    }

    private void showErrorMessage(VolleyError error) {
        if (this.mStateSaved) {
            PageFragment activePage = this.mNavigationManager.getActivePage();
            if (activePage != null) {
                activePage.refreshOnResume();
                return;
            }
        }
        if (error instanceof BgDataDisabledError) {
            showBackgroundDataDialog();
            return;
        }
        if (!this.mNavigationManager.isBackStackEmpty()) {
            this.mNavigationManager.clear();
        }
        String errorMessageHtml = ErrorStrings.get(this, error);
        View errorUi = findViewById(R.id.placeholder_error);
        errorUi.setVisibility(0);
        ((TextView) errorUi.findViewById(R.id.error_msg)).setText(errorMessageHtml);
        errorUi.findViewById(R.id.retry_button).setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                String accountName = FinskyApp.get().getCurrentAccountName();
                if (accountName == null) {
                    FinskyLog.d("No account, restarting activity after network error", new Object[0]);
                    MainActivity.this.restart();
                    return;
                }
                FinskyLog.d("b/5160617: Reinitialize account %s on retry button click", FinskyLog.scrubPii(AccountHandler.findAccount(accountName, MainActivity.this).name));
                MainActivity.this.reinitialize(current, null, false);
                MainActivity.this.hideErrorMessage();
                MainActivity.this.showLoadingIndicator();
            }
        });
    }

    private void showMismatchedCertificatesDialog(String packageName) {
        SimpleAlertDialog.Builder builder = new SimpleAlertDialog.Builder();
        builder.setMessageId(R.string.install_parse_failed_mismatched_certificates).setPositiveId(R.string.ok).setNegativeId(R.string.uninstall);
        Bundle data = new Bundle();
        data.putString("error_package_name", packageName);
        builder.setCallback(null, 32, data);
        builder.build().show(getSupportFragmentManager(), "mismatched_certificates");
    }

    public void onPositiveClick(int requestCode, Bundle extraArguments) {
        switch (requestCode) {
            case com.google.android.play.R.styleable.Theme_actionModeSelectAllDrawable /*32*/:
                return;
            case com.google.android.play.R.styleable.Theme_actionModePopupWindowStyle /*36*/:
                AutoUpdateMigrationHelper.handlePositiveClick();
                return;
            case com.google.android.play.R.styleable.Theme_textAppearanceLargePopupMenu /*37*/:
                GaiaRecoveryHelper.onPositiveGaiaRecoveryDialogResponse();
                return;
            case com.google.android.play.R.styleable.Theme_textAppearanceSmallPopupMenu /*38*/:
                PlayGamesInstallHelper.handlePositiveClick(this.mNavigationManager);
                return;
            case com.google.android.play.R.styleable.Theme_dropdownListPreferredItemHeight /*40*/:
                FinskyLog.d("Attempting to enable download manager", new Object[0]);
                getPackageManager().setApplicationEnabledSetting("com.android.providers.downloads", 1, 0);
                showRestartRequiredDialog();
                return;
            case com.google.android.play.R.styleable.Theme_spinnerStyle /*41*/:
                FinskyLog.d("Shutting down after download manager or gms core re-enabled", new Object[0]);
                System.exit(0);
                return;
            case com.google.android.play.R.styleable.Theme_spinnerDropDownItemStyle /*42*/:
                FinskyLog.d("Attempting to enable gms core", new Object[0]);
                getPackageManager().setApplicationEnabledSetting("com.google.android.gms", 1, 0);
                showRestartRequiredDialog();
                return;
            default:
                if (this.mNavigationManager != null) {
                    this.mNavigationManager.onPositiveClick(requestCode, extraArguments);
                    return;
                }
                return;
        }
    }

    public void onNegativeClick(int requestCode, Bundle extraArguments) {
        switch (requestCode) {
            case com.google.android.play.R.styleable.Theme_actionModeSelectAllDrawable /*32*/:
                FinskyApp.get().getInstaller().uninstallAssetSilently(extraArguments.getString("error_package_name"));
                return;
            case com.google.android.play.R.styleable.Theme_actionModePopupWindowStyle /*36*/:
                AutoUpdateMigrationHelper.handleNegativeClick();
                return;
            case com.google.android.play.R.styleable.Theme_dropdownListPreferredItemHeight /*40*/:
                FinskyLog.d("Shutting down because download manager remains disabled", new Object[0]);
                System.exit(0);
                return;
            case com.google.android.play.R.styleable.Theme_spinnerDropDownItemStyle /*42*/:
                FinskyLog.d("Shutting down because gms core remains disabled", new Object[0]);
                System.exit(0);
                return;
            default:
                return;
        }
    }

    public boolean onSearchRequested() {
        if (!isTosAccepted()) {
            return false;
        }
        if (this.mActionBarHelper.searchButtonClicked() || super.onSearchRequested()) {
            return true;
        }
        return false;
    }

    public void openSettings() {
        startActivityForResult(new Intent(this, SettingsActivity.class), 31);
    }

    public PeopleClient getPeopleClient() {
        return this.mPeopleClient;
    }

    protected void switchAccount(String accountName, Intent intentToReuse) {
        super.switchAccount(accountName, intentToReuse);
        resetCurrentBackendId();
    }
}
