package com.google.android.finsky.billing.lightpurchase;

import android.accounts.Account;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import com.android.vending.R;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.activities.AppsPermissionsActivity;
import com.google.android.finsky.activities.AuthenticatedActivity;
import com.google.android.finsky.activities.PlayGamesInstallHelper;
import com.google.android.finsky.activities.SimpleAlertDialog;
import com.google.android.finsky.activities.SimpleAlertDialog.Builder;
import com.google.android.finsky.activities.SimpleAlertDialog.Listener;
import com.google.android.finsky.analytics.BackgroundEventBuilder;
import com.google.android.finsky.analytics.FinskyEventLog;
import com.google.android.finsky.analytics.PlayStore.PlayStoreBackgroundActionEvent;
import com.google.android.finsky.api.AccountHandler;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.billing.DownloadSizeDialogFragment;
import com.google.android.finsky.billing.PromptForFopHelper;
import com.google.android.finsky.billing.lightpurchase.OfferResolutionActivity.AvailableOffer;
import com.google.android.finsky.billing.promptforfop.PromptForFopActivity;
import com.google.android.finsky.config.G;
import com.google.android.finsky.library.AccountLibrary;
import com.google.android.finsky.library.Libraries;
import com.google.android.finsky.library.Library;
import com.google.android.finsky.library.LibraryEntry;
import com.google.android.finsky.library.LibrarySubscriptionEntry;
import com.google.android.finsky.navigationmanager.ConsumptionUtils;
import com.google.android.finsky.protos.Common.Docid;
import com.google.android.finsky.protos.Common.Offer;
import com.google.android.finsky.utils.DocUtils;
import com.google.android.finsky.utils.DocUtils.OfferFilter;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.IntentUtils;
import com.google.android.finsky.utils.LibraryUtils;
import com.google.android.finsky.utils.ParcelableProto;
import com.google.android.finsky.utils.PermissionsBucketer;
import com.google.android.finsky.utils.PurchaseInitiator;
import com.google.android.finsky.utils.PurchaseInitiator.SuccessListener;
import com.google.android.finsky.utils.SignatureUtils;

public class LightPurchaseFlowActivity extends AuthenticatedActivity implements Listener, DownloadSizeDialogFragment.Listener {
    private Account mAccount;
    private String mAppTitle;
    private int mAppVersionCode;
    private String mAppsContinueUrl;
    private boolean mCalledByFirstPartyPackage;
    private Document mDoc;
    private Docid mDocid;
    private String mDocidStr;
    private boolean mFailed;
    private final Handler mHandler;
    private boolean mHeavyDialogShown;
    private OfferFilter mOfferFilter;
    private boolean mOfferRequiresCheckout;
    private int mOfferType;
    private String mReferralUrl;
    private Bundle mSavedInstanceState;

    public LightPurchaseFlowActivity() {
        this.mHandler = new Handler();
    }

    public static Intent createIntent(Account account, Document doc, int offerType, OfferFilter offerFilter, byte[] serverLogsCookie, String appsContinueUrl) {
        Intent intent = new Intent(FinskyApp.get(), LightPurchaseFlowActivity.class);
        intent.putExtra("LightPurchaseFlowActivity.account", account);
        intent.putExtra("LightPurchaseFlowActivity.doc", doc);
        intent.putExtra("LightPurchaseFlowActivity.offerType", offerType);
        if (offerFilter != null) {
            intent.putExtra("LightPurchaseFlowActivity.offerFilter", offerFilter.name());
        }
        intent.putExtra("LightPurchaseFlowActivity.appsContinueUrl", appsContinueUrl);
        intent.putExtra("LightPurchaseFlowActivity.serverLogsCookie", serverLogsCookie);
        return intent;
    }

    public static Intent createExternalPurchaseIntent(Document doc, int offerType) {
        Intent intent = new Intent(FinskyApp.get(), LightPurchaseFlowActivity.class);
        intent.setAction("com.android.vending.billing.PURCHASE");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.putExtra("backend", doc.getBackend());
        intent.putExtra("document_type", doc.getDocumentType());
        intent.putExtra("backend_docid", doc.getBackendDocId());
        intent.putExtra("full_docid", doc.getDocId());
        if (offerType != 0) {
            intent.putExtra("offer_type", offerType);
        }
        return intent;
    }

    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        if (isExternalPurchaseIntent()) {
            if (!setupFromExternalPurchaseIntent(intent)) {
                fail();
            }
        } else if (!setupFromInternalIntent(intent)) {
            fail();
        }
        if (savedInstanceState != null) {
            this.mDocid = (Docid) ParcelableProto.getProtoFromBundle(savedInstanceState, "LightPurchaseFlowActivity.docid");
            this.mDocidStr = savedInstanceState.getString("LightPurchaseFlowActivity.docidStr");
            this.mDoc = (Document) savedInstanceState.getParcelable("LightPurchaseFlowActivity.doc");
            this.mOfferType = savedInstanceState.getInt("LightPurchaseFlowActivity.offerType", 0);
            this.mOfferRequiresCheckout = savedInstanceState.getBoolean("LightPurchaseFlowActivity.offerRequiresCheckout");
            this.mAppTitle = savedInstanceState.getString("LightPurchaseFlowActivity.appTitle");
            this.mAppVersionCode = savedInstanceState.getInt("LightPurchaseFlowActivity.appVersionCode");
            this.mFailed = savedInstanceState.getBoolean("LightPurchaseFlowActivity.failed");
            this.mHeavyDialogShown = savedInstanceState.getBoolean("LightPurchaseFlowActivity.purchasePerformed");
        }
        this.mSavedInstanceState = savedInstanceState;
        super.onCreate(savedInstanceState);
    }

    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable("LightPurchaseFlowActivity.docid", ParcelableProto.forProto(this.mDocid));
        outState.putString("LightPurchaseFlowActivity.docidStr", this.mDocidStr);
        outState.putParcelable("LightPurchaseFlowActivity.doc", this.mDoc);
        outState.putInt("LightPurchaseFlowActivity.offerType", this.mOfferType);
        outState.putBoolean("LightPurchaseFlowActivity.offerRequiresCheckout", this.mOfferRequiresCheckout);
        outState.putBoolean("LightPurchaseFlowActivity.purchasePerformed", this.mHeavyDialogShown);
        outState.putString("LightPurchaseFlowActivity.appTitle", this.mAppTitle);
        outState.putInt("LightPurchaseFlowActivity.appVersionCode", this.mAppVersionCode);
        outState.putBoolean("LightPurchaseFlowActivity.failed", this.mFailed);
    }

    private boolean setupFromInternalIntent(Intent intent) {
        String caller = getCallingPackage();
        if (caller == null || !caller.equals(getPackageName())) {
            FinskyLog.w("Blocked request from external package: %s", caller);
            return false;
        }
        this.mCalledByFirstPartyPackage = true;
        this.mAccount = (Account) intent.getParcelableExtra("LightPurchaseFlowActivity.account");
        this.mDocid = (Docid) ParcelableProto.getProtoFromIntent(intent, "LightPurchaseFlowActivity.docid");
        this.mDoc = (Document) intent.getParcelableExtra("LightPurchaseFlowActivity.doc");
        this.mDocid = this.mDoc.getFullDocid();
        this.mDocidStr = this.mDoc.getDocId();
        this.mOfferType = intent.getIntExtra("LightPurchaseFlowActivity.offerType", 0);
        if (intent.hasExtra("LightPurchaseFlowActivity.offerFilter")) {
            this.mOfferFilter = OfferFilter.valueOf(intent.getStringExtra("LightPurchaseFlowActivity.offerFilter"));
        }
        if (this.mOfferType != 0) {
            Offer offer = this.mDoc.getOffer(this.mOfferType);
            if (offer == null) {
                FinskyLog.e("Offer type not available: %d", Integer.valueOf(this.mOfferType));
                return false;
            }
            this.mOfferRequiresCheckout = offer.checkoutFlowRequired;
        }
        this.mAppsContinueUrl = intent.getStringExtra("LightPurchaseFlowActivity.appsContinueUrl");
        return true;
    }

    private boolean setupFromExternalPurchaseIntent(Intent intent) {
        this.mCalledByFirstPartyPackage = SignatureUtils.isCalledByFirstPartyPackage(this);
        if (!((Boolean) G.enableThirdPartyDirectPurchases.get()).booleanValue() && !this.mCalledByFirstPartyPackage) {
            FinskyLog.w("Called from untrusted package.", new Object[0]);
            return false;
        } else if (intent.hasExtra("backend") && intent.hasExtra("document_type") && intent.hasExtra("backend_docid") && intent.hasExtra("full_docid")) {
            if (intent.hasExtra("authAccount")) {
                this.mAccount = AccountHandler.findAccount(intent.getStringExtra("authAccount"), this);
                if (this.mAccount == null) {
                    FinskyLog.d("Invalid account passed: %s", accountName);
                    return false;
                }
            }
            this.mAccount = FinskyApp.get().getCurrentAccount();
            this.mDocid = DocUtils.createDocid(intent.getIntExtra("backend", 0), intent.getIntExtra("document_type", 0), intent.getStringExtra("backend_docid"));
            this.mDocidStr = intent.getStringExtra("full_docid");
            this.mDoc = null;
            this.mOfferType = intent.getIntExtra("offer_type", 0);
            this.mOfferRequiresCheckout = true;
            String offerFilter = intent.getStringExtra("offer_filter");
            if (offerFilter != null) {
                try {
                    this.mOfferFilter = OfferFilter.valueOf(offerFilter);
                } catch (IllegalArgumentException e) {
                    FinskyLog.e("Invalid offer types passed: %s", offerFilter);
                    return false;
                }
            }
            this.mReferralUrl = intent.getStringExtra("referral_url");
            return true;
        } else {
            FinskyLog.e("Missing argument.", new Object[0]);
            return false;
        }
    }

    private boolean isExternalPurchaseIntent() {
        return "com.android.vending.billing.PURCHASE".equals(getIntent().getAction());
    }

    private void acquire(Bundle appDownloadSizeWarningArgs) {
        Library accountLibrary = FinskyApp.get().getLibraries().getAccountLibrary(this.mAccount);
        if (LibraryUtils.isOfferOwned(this.mDocid, accountLibrary, this.mOfferType)) {
            if (this.mDocid.type == 1) {
                if (appDownloadSizeWarningArgs != null) {
                    showDownloadSizeWarning(appDownloadSizeWarningArgs);
                    return;
                }
                logConfirmFreeDownload();
                FinskyApp.get().getInstallerDataStore().setContinueUrl(this.mDocid.backendDocid, this.mAppsContinueUrl);
                FinskyApp.get().getInstaller().requestInstall(this.mDocid.backendDocid, this.mAppVersionCode, this.mAccount.name, this.mAppTitle, false, "single_install", 2);
                success();
                return;
            } else if (!(this.mOfferRequiresCheckout && isCanceledMusicSubscription(accountLibrary) && ((Boolean) G.musicAppSubscriptionResignupEnabled.get()).booleanValue())) {
                showError(getString(this.mDocid.type == 15 ? R.string.subscription_already_owned : R.string.document_already_owned));
                return;
            }
        }
        if (this.mOfferRequiresCheckout) {
            startActivityForResult(PurchaseActivity.createIntent(this.mAccount, PurchaseParams.builder().setDocid(this.mDocid).setDocidStr(this.mDocidStr).setOfferType(this.mOfferType).setAppData(this.mAppVersionCode, this.mAppTitle, this.mAppsContinueUrl).build(), getIntent().getByteArrayExtra("LightPurchaseFlowActivity.serverLogsCookie"), appDownloadSizeWarningArgs), 1);
        } else if (appDownloadSizeWarningArgs != null) {
            showDownloadSizeWarning(appDownloadSizeWarningArgs);
        } else {
            PurchaseInitiator.makeFreePurchase(this.mAccount, this.mDoc, this.mOfferType, this.mAppsContinueUrl, new SuccessListener() {
                public void onFreePurchaseSuccess(Account account, Document doc) {
                    if (LightPurchaseFlowActivity.this.mDocid.type == 1) {
                        LightPurchaseFlowActivity.this.logConfirmFreeDownload();
                        LightPurchaseFlowActivity.this.success();
                    } else if (LightPurchaseFlowActivity.this.mDocid.backend == 6) {
                        if (!ConsumptionUtils.showAppNeededDialogIfNeeded(LightPurchaseFlowActivity.this, LightPurchaseFlowActivity.this.mAccount, LightPurchaseFlowActivity.this.mDoc, LightPurchaseFlowActivity.this.getSupportFragmentManager(), null, 5)) {
                            LightPurchaseFlowActivity.this.success();
                        }
                    } else if (!ConsumptionUtils.openItem(LightPurchaseFlowActivity.this, LightPurchaseFlowActivity.this.mAccount, LightPurchaseFlowActivity.this.mDoc, LightPurchaseFlowActivity.this.getSupportFragmentManager(), null, 5)) {
                        LightPurchaseFlowActivity.this.success();
                    }
                }
            }, true, true);
        }
    }

    private boolean isCanceledMusicSubscription(AccountLibrary accountLibrary) {
        if (this.mDocid.backend == 2 && this.mDocid.type == 15) {
            String musicLibraryId = AccountLibrary.getLibraryIdFromBackend(2);
            LibraryEntry libraryEntry = accountLibrary.getLibrary(musicLibraryId).get(new LibraryEntry(this.mAccount.name, musicLibraryId, 2, this.mDocid.backendDocid, 15, 1));
            if (!(libraryEntry == null || ((LibrarySubscriptionEntry) libraryEntry).isAutoRenewing)) {
                return true;
            }
        }
        return false;
    }

    private void launchAppsPermissions() {
        startActivityForResult(AppsPermissionsActivity.createIntent(this.mAccount.name, this.mDocidStr, this.mDoc, false), 2);
    }

    private void fail() {
        this.mFailed = true;
        setResult(0);
        logFinish(false);
        finish();
    }

    private void success() {
        if (this.mCalledByFirstPartyPackage) {
            Intent intent = new Intent();
            intent.putExtra("authAccount", this.mAccount.name);
            intent.putExtra("backend", this.mDocid.backend);
            intent.putExtra("document_type", this.mDocid.type);
            intent.putExtra("backend_docid", this.mDocid.backendDocid);
            intent.putExtra("offer_type", this.mOfferType);
            intent.putExtra("involved_heavy_dialogs", this.mHeavyDialogShown);
            PlayGamesInstallHelper.addGameIntentExtras(this.mDoc, intent);
            setResult(-1, intent);
        } else {
            setResult(-1);
        }
        logFinish(true);
        finish();
    }

    private void logConfirmFreeDownload() {
        FinskyApp.get().getAnalytics(this.mAccount.name).logAdMobPageView("confirmFreeDownload?doc=" + this.mDocidStr);
    }

    protected void onActivityResult(int requestCode, final int resultCode, final Intent data) {
        switch (requestCode) {
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                this.mHandler.post(new Runnable() {
                    public void run() {
                        LightPurchaseFlowActivity.this.handlePaymentResult(resultCode);
                    }
                });
                return;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                this.mHandler.post(new Runnable() {
                    public void run() {
                        LightPurchaseFlowActivity.this.handleAppPermissionResult(resultCode, data);
                    }
                });
                return;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                this.mHandler.post(new Runnable() {
                    public void run() {
                        LightPurchaseFlowActivity.this.handleOfferResolutionResult(resultCode, data);
                    }
                });
                return;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                this.mHandler.post(new Runnable() {
                    public void run() {
                        LightPurchaseFlowActivity.this.handlePromptForFopResult(resultCode);
                    }
                });
                return;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                this.mHandler.post(new Runnable() {
                    public void run() {
                        LightPurchaseFlowActivity.this.handleAgeVerificationResult(resultCode);
                    }
                });
                return;
            default:
                super.onActivityResult(requestCode, resultCode, data);
                return;
        }
    }

    private void handleAgeVerificationResult(int resultCode) {
        if (resultCode == -1) {
            FinskyLog.d("Age verification activity success: %s", this.mDocidStr);
            startFopRequiredOrAcquisitionFlow();
            return;
        }
        FinskyLog.d("Age verification activity canceled: %s", this.mDocidStr);
        fail();
    }

    private void handlePromptForFopResult(int resultCode) {
        if (resultCode == -1) {
            FinskyLog.d("Prompt for FOP activity success: %s", this.mDocidStr);
            startAcquisitionFlow();
            return;
        }
        FinskyLog.d("Prompt for FOP activity canceled: %s", this.mDocidStr);
        fail();
    }

    private void handleAppPermissionResult(int resultCode, Intent data) {
        if (resultCode == -1) {
            FinskyLog.d("Permissions accepted: %s", this.mDocidStr);
            this.mDoc = AppsPermissionsActivity.extractDoc(data);
            Offer offer = this.mDoc.getOffer(this.mOfferType);
            if (offer == null) {
                FinskyLog.e("Offer not found: type=%d", Integer.valueOf(this.mOfferType));
                fail();
                return;
            }
            this.mOfferRequiresCheckout = offer.checkoutFlowRequired;
            this.mAppVersionCode = AppsPermissionsActivity.extractVersionCode(data);
            this.mAppTitle = AppsPermissionsActivity.extractTitle(data);
            Bundle downloadSizeWarningArguments = AppsPermissionsActivity.getDownloadSizeWarningArguments(data);
            boolean acceptedNewBuckets = AppsPermissionsActivity.extractAcceptedNewBuckets(data);
            String docId = this.mDocid.backendDocid;
            if (acceptedNewBuckets) {
                PermissionsBucketer.setAcceptedNewBuckets(docId);
            }
            if (downloadSizeWarningArguments == null) {
                FinskyApp.get().getInstaller().setMobileDataAllowed(docId);
            }
            acquire(downloadSizeWarningArguments);
            return;
        }
        FinskyLog.d("Permissions declined: %s", this.mDocidStr);
        fail();
    }

    private void showDownloadSizeWarning(Bundle arguments) {
        DownloadSizeDialogFragment.newInstance(null, arguments).show(getSupportFragmentManager(), "LightPurchaseFlowActivity.appDownloadSizeWarningDialog");
    }

    private void handleOfferResolutionResult(int resultCode, Intent data) {
        if (resultCode == -1) {
            AvailableOffer selectedOffer = OfferResolutionActivity.extractAvailableOffer(data);
            this.mDoc = selectedOffer.doc;
            this.mDocid = this.mDoc.getFullDocid();
            this.mDocidStr = this.mDoc.getDocId();
            Offer offer = selectedOffer.offer;
            this.mOfferType = offer.offerType;
            this.mOfferRequiresCheckout = offer.checkoutFlowRequired;
            FinskyLog.d("Offer resolution: %s, offerType=%d, checkoutFlowRequired=%b", this.mDocidStr, Integer.valueOf(this.mOfferType), Boolean.valueOf(this.mOfferRequiresCheckout));
            acquire(null);
            return;
        }
        FinskyLog.d("Offer resolution canceled: %s", this.mDocidStr);
        fail();
    }

    private void handlePaymentResult(int resultCode) {
        FinskyLog.d("Payment screen finished with result: %d", Integer.valueOf(resultCode));
        if (resultCode == -1) {
            this.mHeavyDialogShown = true;
            success();
            return;
        }
        fail();
    }

    protected void onReady(boolean shouldHandleIntent) {
        if (!this.mFailed && this.mSavedInstanceState == null) {
            logStart();
            if (shouldStartAgeVerificationFlow()) {
                startAgeVerificationFlow();
            } else {
                startFopRequiredOrAcquisitionFlow();
            }
        }
    }

    private void startFopRequiredOrAcquisitionFlow() {
        if (shouldStartFopRequiredFlow()) {
            startFopRequiredFlow();
        } else {
            startAcquisitionFlow();
        }
    }

    private boolean shouldStartAgeVerificationFlow() {
        if (!FinskyApp.get().getClientMutationCache(this.mAccount.name).isAgeVerificationRequired()) {
            return false;
        }
        Libraries libraries = FinskyApp.get().getLibraries();
        if (this.mDocid.type == 1) {
            if (!libraries.getAppOwners(this.mDocidStr).isEmpty()) {
                return false;
            }
        } else if (LibraryUtils.isOwned(this.mDocid, libraries.getAccountLibrary(this.mAccount))) {
            return false;
        }
        if (this.mDoc == null) {
            return true;
        }
        return this.mDoc.isMature();
    }

    private boolean shouldStartFopRequiredFlow() {
        if (isExternalPurchaseIntent() || this.mDoc == null || this.mDoc.getDocumentType() != 1 || this.mOfferRequiresCheckout || !FinskyApp.get().getLibraries().getAppOwners(this.mDocidStr).isEmpty()) {
            return false;
        }
        return PromptForFopHelper.shouldPromptForFop(this.mAccount.name);
    }

    private void logStart() {
        PlayStoreBackgroundActionEvent event = getBackgroundEvent(600);
        FinskyEventLog eventLog = FinskyApp.get().getEventLogger(this.mAccount);
        eventLog.logBackgroundEvent(event);
        if (!TextUtils.isEmpty(this.mReferralUrl)) {
            eventLog.logDeepLinkEvent(9, this.mReferralUrl, null, null, null);
        }
    }

    private void logFinish(boolean success) {
        PlayStoreBackgroundActionEvent event = getBackgroundEvent(601);
        event.operationSuccess = success;
        event.hasOperationSuccess = true;
        FinskyApp.get().getEventLogger(this.mAccount).logBackgroundEvent(event);
    }

    private PlayStoreBackgroundActionEvent getBackgroundEvent(int type) {
        BackgroundEventBuilder builder = new BackgroundEventBuilder(type).setDocument(this.mDocidStr).setCallingPackage(getCallingPackage());
        if (this.mOfferType != 0) {
            builder.setOfferType(this.mOfferType);
            builder.setOfferCheckoutFlowRequired(this.mOfferRequiresCheckout);
        }
        return builder.build();
    }

    private void startAgeVerificationFlow() {
        startActivityForResult(AgeVerificationActivity.createIntent(this.mAccount.name, this.mDocid.backend, this.mDoc == null ? this.mDocidStr : null), 8);
    }

    private void startFopRequiredFlow() {
        this.mHeavyDialogShown = true;
        Builder builder = new Builder();
        builder.setTitleId(R.string.setup_account_title).setMessageId(R.string.review_account_message).setPositiveId(R.string.continue_text).setCallback(null, 6, null).setEventLog(1000, getIntent().getByteArrayExtra("LightPurchaseFlowActivity.serverLogsCookie"), -1, -1, this.mAccount);
        SimpleAlertDialog sad = builder.build();
        PromptForFopHelper.recordDialogImpression(this.mAccount.name);
        sad.show(getSupportFragmentManager(), "LightPurchaseFlowActivity.fopRequiredDialog");
    }

    private void startAcquisitionFlow() {
        if (this.mDocid.type == 1) {
            launchAppsPermissions();
        } else if (this.mOfferType == 0) {
            startActivityForResult(OfferResolutionActivity.createIntent(FinskyApp.get().getToc(), this.mAccount, this.mDocidStr, this.mDoc, this.mOfferFilter), 3);
        } else {
            acquire(null);
        }
    }

    public void showError(String errorMessage) {
        Builder builder = new Builder();
        builder.setMessage(errorMessage).setPositiveId(R.string.ok).setCallback(null, 4, null);
        builder.build().show(getSupportFragmentManager(), "LightPurchaseFlowActivity.errorDialog");
    }

    public void onPositiveClick(int requestCode, Bundle extraArguments) {
        switch (requestCode) {
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_required /*4*/:
                fail();
                return;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                startActivity(IntentUtils.createViewDocumentUrlIntent(this, extraArguments.getString("dialog_details_url")));
                fail();
                return;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                startActivityForResult(PromptForFopActivity.createIntent(FinskyApp.get().getCurrentAccount(), getIntent().getByteArrayExtra("LightPurchaseFlowActivity.serverLogsCookie")), 7);
                return;
            default:
                FinskyLog.wtf("Unknown dialog callback: %d", Integer.valueOf(requestCode));
                return;
        }
    }

    public void onNegativeClick(int requestCode, Bundle extraArguments) {
        fail();
    }

    public void onDownloadOk(boolean wifiOnly) {
        FinskyLog.d("Will download %s using wifi only = %b", this.mDocid.backendDocid, Boolean.valueOf(wifiOnly));
        if (!wifiOnly) {
            FinskyApp.get().getInstaller().setMobileDataAllowed(packageName);
        }
        acquire(null);
    }

    public void onSetupWifi() {
        startActivity(new Intent("android.settings.WIFI_SETTINGS"));
        fail();
    }

    public void onDownloadCancel() {
        FinskyLog.d("Download size warning dismissed for app = %s", this.mDoc.getBackendDocId());
        fail();
    }
}
