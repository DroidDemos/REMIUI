package com.google.android.finsky.billing.lightpurchase;

import android.accounts.Account;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.android.vending.R;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.activities.AddressChallengeActivity;
import com.google.android.finsky.activities.WebViewChallengeActivity;
import com.google.android.finsky.api.DfeApi.GaiaAuthParameters;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.auth.GmsCoreAuthApi;
import com.google.android.finsky.billing.BillingFlowFragment;
import com.google.android.finsky.billing.BillingFlowFragment.BillingFlowHost;
import com.google.android.finsky.billing.BillingUtils;
import com.google.android.finsky.billing.DownloadSizeDialogFragment;
import com.google.android.finsky.billing.carrierbilling.CarrierBillingUtils;
import com.google.android.finsky.billing.carrierbilling.Dcb2Util;
import com.google.android.finsky.billing.carrierbilling.Dcb3Util;
import com.google.android.finsky.billing.giftcard.RedeemCodeResult;
import com.google.android.finsky.billing.lightpurchase.CheckoutPurchaseSidecar.CheckoutPurchaseError;
import com.google.android.finsky.billing.lightpurchase.billingprofile.BillingProfileActivity;
import com.google.android.finsky.billing.lightpurchase.billingprofile.instruments.RedeemCodeActivity;
import com.google.android.finsky.billing.lightpurchase.multistep.MultiStepFragment;
import com.google.android.finsky.billing.lightpurchase.purchasesteps.AuthChallengeStep;
import com.google.android.finsky.billing.lightpurchase.purchasesteps.CartDetailsStep;
import com.google.android.finsky.billing.lightpurchase.purchasesteps.ChangeSubscriptionStep;
import com.google.android.finsky.billing.lightpurchase.purchasesteps.CvcChallengeStep;
import com.google.android.finsky.billing.lightpurchase.purchasesteps.ErrorStep;
import com.google.android.finsky.billing.lightpurchase.purchasesteps.InstrumentManagerPurchaseStep;
import com.google.android.finsky.billing.lightpurchase.purchasesteps.LightPurchaseSuccessStep;
import com.google.android.finsky.billing.lightpurchase.purchasesteps.SuccessStepWithAuthChoices;
import com.google.android.finsky.config.G;
import com.google.android.finsky.config.PurchaseAuth;
import com.google.android.finsky.fragments.SidecarFragment;
import com.google.android.finsky.layout.IconButtonGroup;
import com.google.android.finsky.layout.play.PlayStoreUiElementNode;
import com.google.android.finsky.navigationmanager.ConsumptionUtils;
import com.google.android.finsky.protos.Acquisition.PostSuccessAction;
import com.google.android.finsky.protos.Acquisition.SuccessInfo;
import com.google.android.finsky.protos.ChallengeProto.Challenge;
import com.google.android.finsky.protos.CommonDevice.Instrument;
import com.google.android.finsky.utils.ErrorStrings;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.FinskyPreferences;
import com.google.android.finsky.utils.IntentUtils;
import com.google.android.finsky.utils.Maps;
import com.google.android.finsky.utils.Utils;
import com.google.android.finsky.utils.VoucherUtils;
import com.google.android.gms.auth.firstparty.dataservice.GoogleAccountDataServiceClient;
import com.google.android.gms.auth.firstparty.dataservice.ReauthSettingsRequest;
import com.google.android.gms.auth.firstparty.dataservice.ReauthSettingsResponse;
import java.util.Map;

public class PurchaseFragment extends MultiStepFragment implements com.google.android.finsky.activities.SimpleAlertDialog.Listener, BillingFlowHost, com.google.android.finsky.billing.DownloadSizeDialogFragment.Listener, com.google.android.finsky.fragments.SidecarFragment.Listener {
    private Bundle mAppDownloadSizeWarningParameters;
    private Bundle mCommitChallengeResponses;
    private BillingFlowFragment mCompleteFlowFragment;
    private Bundle mCompleteFlowResult;
    private boolean mDcbInitialized;
    private byte[] mDocServerLogsCookie;
    private PurchaseError mError;
    private String mEscrowHandleParameterKey;
    private Bundle mExtraPurchaseData;
    private int mHandledPurchaseStateInstance;
    private PurchaseParams mParams;
    private Bundle mPrepareChallengeResponses;
    private int mPreviousState;
    private int mPreviousSubstate;
    private String mSelectedInstrumentId;
    private boolean mShowWalletIcon;
    private CheckoutPurchaseSidecar mSidecar;
    private boolean mSkipCheckout;
    private boolean mSucceeded;
    private boolean mUsePinBasedAuth;
    private VoucherParams mVoucherParams;

    public interface Listener {
        void onFinished();
    }

    public static class PurchaseError implements Parcelable {
        public static final Creator<PurchaseError> CREATOR;
        public final int errorSubtype;
        public final int errorType;

        public PurchaseError(int errorType, int errorSubtype) {
            this.errorType = errorType;
            this.errorSubtype = errorSubtype;
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.errorType);
            parcel.writeInt(this.errorSubtype);
        }

        public String toString() {
            return "PurchaseError{type=" + this.errorType + " subtype=" + this.errorSubtype + "}";
        }

        static {
            CREATOR = new Creator<PurchaseError>() {
                public PurchaseError createFromParcel(Parcel parcel) {
                    return new PurchaseError(parcel.readInt(), parcel.readInt());
                }

                public PurchaseError[] newArray(int size) {
                    return new PurchaseError[size];
                }
            };
        }
    }

    public PurchaseFragment() {
        this.mHandledPurchaseStateInstance = -1;
        this.mPrepareChallengeResponses = new Bundle();
        this.mCommitChallengeResponses = new Bundle();
    }

    public static PurchaseFragment newInstance(Account account, PurchaseParams params, byte[] docServerLogsCookie, Bundle appDownloadSizeWarningArgs) {
        Bundle args = new Bundle();
        args.putParcelable("MultiStepFragment.account", account);
        args.putParcelable("PurchaseFragment.params", params);
        args.putByteArray("PurchaseFragment.docServerLogsCookie", docServerLogsCookie);
        args.putBundle("PurchaseFragment.appDownloadSizeWarningArgs", appDownloadSizeWarningArgs);
        PurchaseFragment result = new PurchaseFragment();
        result.setArguments(args);
        return result;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            this.mSidecar = (CheckoutPurchaseSidecar) getFragmentManager().findFragmentByTag("PurchaseFragment.purchaseFragment");
            this.mHandledPurchaseStateInstance = savedInstanceState.getInt("PurchaseFragment.handledStateInstance");
            this.mPreviousState = savedInstanceState.getInt("PurchaseFragment.previousState");
            this.mPreviousSubstate = savedInstanceState.getInt("PurchaseFragment.previousSubstate");
        }
        Bundle args = getArguments();
        this.mParams = (PurchaseParams) args.getParcelable("PurchaseFragment.params");
        this.mDocServerLogsCookie = args.getByteArray("PurchaseFragment.docServerLogsCookie");
        this.mAppDownloadSizeWarningParameters = args.getBundle("PurchaseFragment.appDownloadSizeWarningArgs");
        if (savedInstanceState != null) {
            this.mSelectedInstrumentId = savedInstanceState.getString("PurchaseFragment.selectedInstrumentId");
            this.mVoucherParams = (VoucherParams) savedInstanceState.getParcelable("PurchaseFragment.voucherParams");
            this.mPrepareChallengeResponses = savedInstanceState.getBundle("PurchaseFragment.prepareChallengeResponses");
            this.mCommitChallengeResponses = savedInstanceState.getBundle("PurchaseFragment.commitChallengeResponses");
            this.mError = (PurchaseError) savedInstanceState.getParcelable("PurchaseFragment.error");
            this.mSucceeded = savedInstanceState.getBoolean("PurchaseFragment.succeeded");
            this.mSkipCheckout = savedInstanceState.getBoolean("PurchaseFragment.skipCheckout");
            this.mExtraPurchaseData = savedInstanceState.getBundle("PurchaseFragment.extraPurchaseData");
            this.mEscrowHandleParameterKey = savedInstanceState.getString("PurchaseFragment.escrowHandleParameterKey");
            this.mUsePinBasedAuth = savedInstanceState.getBoolean("PurchaseFragment.usePinBasedAuth");
        } else if (TextUtils.isEmpty(this.mParams.voucherId)) {
            this.mVoucherParams = new VoucherParams(null, true, VoucherUtils.hasVouchers(FinskyApp.get().getLibraries().getAccountLibrary(this.mAccount)));
        } else {
            this.mVoucherParams = new VoucherParams(this.mParams.voucherId, true, true);
        }
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (!(activity instanceof PlayStoreUiElementNode)) {
            throw new IllegalStateException("Activity must implement PlayStoreUiElementNode");
        }
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("PurchaseFragment.handledStateInstance", this.mHandledPurchaseStateInstance);
        outState.putInt("PurchaseFragment.previousState", this.mPreviousState);
        outState.putInt("PurchaseFragment.previousSubstate", this.mPreviousSubstate);
        outState.putBundle("PurchaseFragment.prepareChallengeResponses", this.mPrepareChallengeResponses);
        outState.putBundle("PurchaseFragment.commitChallengeResponses", this.mCommitChallengeResponses);
        outState.putString("PurchaseFragment.selectedInstrumentId", this.mSelectedInstrumentId);
        outState.putParcelable("PurchaseFragment.voucherParams", this.mVoucherParams);
        outState.putBoolean("PurchaseFragment.succeeded", this.mSucceeded);
        outState.putParcelable("PurchaseFragment.error", this.mError);
        outState.putBoolean("PurchaseFragment.skipCheckout", this.mSkipCheckout);
        outState.putBundle("PurchaseFragment.extraPurchaseData", this.mExtraPurchaseData);
        outState.putString("PurchaseFragment.escrowHandleParameterKey", this.mEscrowHandleParameterKey);
        outState.putBoolean("PurchaseFragment.usePinBasedAuth", this.mUsePinBasedAuth);
    }

    public void onStart() {
        super.onStart();
        this.mCompleteFlowFragment = (BillingFlowFragment) getChildFragmentManager().findFragmentByTag("PurchaseFragment.completeFlowFragment");
        initializeDcb(new Runnable() {
            public void run() {
                PurchaseFragment.this.onInitialized();
            }
        });
    }

    public void onStop() {
        if (this.mSidecar != null) {
            this.mSidecar.setListener(null);
        }
        super.onStop();
    }

    private void initializeDcb(final Runnable finishRunnable) {
        if (this.mDcbInitialized) {
            finishRunnable.run();
            return;
        }
        this.mDcbInitialized = true;
        Runnable successRunnable = new Runnable() {
            public void run() {
                PurchaseFragment.this.mDcbInitialized = true;
                if (FinskyLog.DEBUG) {
                    FinskyLog.v("DCB initialized.", new Object[0]);
                }
                finishRunnable.run();
            }
        };
        if (FinskyLog.DEBUG) {
            FinskyLog.v("Initializing DCB.", new Object[0]);
        }
        CarrierBillingUtils.initializeStorageAndParams(getActivity(), false, successRunnable);
    }

    private void onInitialized() {
        if (getFragmentManager() == null) {
            FinskyLog.d("Not ready, ignoring.", new Object[0]);
            return;
        }
        if (this.mSidecar == null) {
            this.mSidecar = CheckoutPurchaseSidecar.newInstance(this.mAccount.name);
            getFragmentManager().beginTransaction().add(this.mSidecar, "PurchaseFragment.purchaseFragment").commit();
        }
        this.mSidecar.setListener(this);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.light_purchase, container, false);
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (FinskyApp.get().getExperiments(this.mAccount.name).isEnabled("cl:billing.purchase_button_show_wallet_3d_icon")) {
            this.mShowWalletIcon = true;
            ((IconButtonGroup) this.mContinueButton).setIconDrawable(getResources().getDrawable(R.drawable.purchase_wallet));
        }
        ((IconButtonGroup) this.mContinueButton).setBackendForLabel(this.mParams.docid.backend);
    }

    private void showError(String errorMessage, boolean purchaseFailed) {
        showError(getString(R.string.error), errorMessage, purchaseFailed);
    }

    private void showError(String errorTitle, String errorMessage, boolean purchaseFailed) {
        showStep(ErrorStep.newInstance(errorTitle, errorMessage, purchaseFailed));
    }

    public void onStateChange(SidecarFragment fragment) {
        int stateInstance = fragment.getStateInstance();
        if (FinskyLog.DEBUG) {
            FinskyLog.v("Received state change: state=%d, stateInstance=%d", Integer.valueOf(fragment.getState()), Integer.valueOf(stateInstance));
        }
        if (stateInstance != this.mHandledPurchaseStateInstance) {
            this.mHandledPurchaseStateInstance = stateInstance;
            nextStep();
            this.mPreviousState = fragment.getState();
            this.mPreviousSubstate = fragment.getSubstate();
        } else if (FinskyLog.DEBUG) {
            FinskyLog.d("Already handled state %d", Integer.valueOf(this.mHandledPurchaseStateInstance));
        }
    }

    public void nextStep() {
        switch (this.mSidecar.getState()) {
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                handleInit();
                return;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                showLoading();
                return;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                handleSuccess();
                return;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                handleError();
                return;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_required /*4*/:
                handlePreparedForChangeSubscription();
                return;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                handlePreparedForCartDetails(false);
                return;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                handlePrepareChallenge();
                return;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                handleCompleteChallenge();
                return;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                handleCvcEscrowed();
                return;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoTextColor /*9*/:
                handleInstrumentManager();
                return;
            default:
                return;
        }
    }

    private void handleInit() {
        preparePurchase();
    }

    public void preparePurchase() {
        Map<String, String> extraPostParams = Maps.newHashMap();
        GaiaAuthParameters gaiaAuthParams = new GaiaAuthParameters(((Long) FinskyPreferences.lastGaiaAuthTimestamp.get(this.mAccount.name).get()).longValue(), PurchaseAuth.getPurchaseAuthType(this.mAccount.name));
        CarrierBillingUtils.addPrepareOrBillingProfileParams(false, ((Boolean) G.lightPurchaseOptimisticProvisioning.get()).booleanValue(), extraPostParams);
        this.mSidecar.prepare(this.mParams, this.mSelectedInstrumentId, this.mVoucherParams, this.mPrepareChallengeResponses, gaiaAuthParams, extraPostParams);
    }

    private void handlePreparedForChangeSubscription() {
        showStep(ChangeSubscriptionStep.newInstance(this.mParams.docid.backend, this.mSidecar.getChangeSubscription()));
    }

    private void handlePreparedForCartDetails(boolean continueToInstrumentManager) {
        showStep(CartDetailsStep.newInstance(this.mParams.docid.backend, this.mSidecar.getCart(), continueToInstrumentManager));
    }

    private void handleInstrumentManager() {
        showStep(InstrumentManagerPurchaseStep.newInstance(this.mAccount.name, this.mSidecar.getPrepareChallenge().paymentsUpdateChallenge.paymentsIntegratorContext));
    }

    private void handlePrepareChallenge() {
        Challenge challenge = this.mSidecar.getPrepareChallenge();
        if (challenge.paymentsUpdateChallenge == null) {
            startChallenge(challenge);
        } else if (challenge.paymentsUpdateChallenge.useClientCart) {
            handlePreparedForCartDetails(true);
        } else {
            handleInstrumentManager();
        }
    }

    private void handleCompleteChallenge() {
        Challenge challenge = this.mSidecar.getCompleteChallenge();
        if (challenge.authenticationChallenge != null) {
            setupAuthChallengeStep(challenge);
        } else if (challenge.cvnChallenge != null) {
            showStep(CvcChallengeStep.newInstance(this.mAccount.name, challenge.cvnChallenge));
        } else {
            startChallenge(challenge);
        }
    }

    private void setupAuthChallengeStep(final Challenge challenge) {
        if (GmsCoreAuthApi.isAvailable()) {
            new AsyncTask<Void, Void, ReauthSettingsResponse>() {
                protected ReauthSettingsResponse doInBackground(Void... params) {
                    return new GoogleAccountDataServiceClient(FinskyApp.get()).getReauthSettings(new ReauthSettingsRequest(PurchaseFragment.this.mAccount.name, false));
                }

                protected void onPostExecute(ReauthSettingsResponse response) {
                    if (!PurchaseFragment.this.isAdded()) {
                        return;
                    }
                    if (response != null) {
                        PurchaseFragment.this.handleReauthSettingsResponse(response, challenge);
                    } else {
                        PurchaseFragment.this.getReauthSettingsOverNetwork(challenge);
                    }
                }
            }.execute(new Void[0]);
        } else {
            showAuthChallengeStep(challenge, false);
        }
    }

    private void getReauthSettingsOverNetwork(final Challenge challenge) {
        showLoading();
        Utils.executeMultiThreaded(new AsyncTask<Void, Void, ReauthSettingsResponse>() {
            protected ReauthSettingsResponse doInBackground(Void... params) {
                return new GoogleAccountDataServiceClient(FinskyApp.get()).getReauthSettings(new ReauthSettingsRequest(PurchaseFragment.this.mAccount.name, true));
            }

            protected void onPostExecute(ReauthSettingsResponse response) {
                if (PurchaseFragment.this.isAdded()) {
                    PurchaseFragment.this.handleReauthSettingsResponse(response, challenge);
                }
            }
        }, new Void[0]);
    }

    private void handleReauthSettingsResponse(ReauthSettingsResponse response, Challenge challenge) {
        boolean useGmsCoreForAuth = false;
        this.mUsePinBasedAuth = false;
        if (response != null && response.status == 0) {
            useGmsCoreForAuth = true;
            if (response.pin != null && "ACTIVE".equals(response.pin.status)) {
                this.mUsePinBasedAuth = true;
            }
        }
        if (isLoading()) {
            hideLoading();
        }
        showAuthChallengeStep(challenge, useGmsCoreForAuth);
    }

    private void showAuthChallengeStep(Challenge challenge, boolean useGmsCoreForAuth) {
        showStep(AuthChallengeStep.newInstance(this.mAccount.name, challenge.authenticationChallenge, useGmsCoreForAuth, this.mUsePinBasedAuth));
    }

    private void startChallenge(Challenge challenge) {
        Intent intent;
        Bundle params = new Bundle();
        params.putString("authAccount", this.mAccount.name);
        if (challenge.addressChallenge != null) {
            intent = AddressChallengeActivity.getIntent(this.mParams.docid.backend, challenge.addressChallenge, params);
        } else if (challenge.webViewChallenge != null) {
            intent = WebViewChallengeActivity.createIntent(this.mAccount.name, null, challenge.webViewChallenge);
        } else {
            FinskyLog.e("Don't know how to handle prepare challenge for doc: %s", this.mParams.docid);
            showError(getString(R.string.generic_purchase_prepare_error), false);
            return;
        }
        startActivityForResult(intent, 2);
    }

    private void handleCvcEscrowed() {
        Bundle response = new Bundle();
        response.putString(this.mEscrowHandleParameterKey, this.mSidecar.getCvcEscrowHandle());
        answerChallenge(response);
    }

    private void handleError() {
        boolean failPurchase;
        PurchaseError error = new PurchaseError(0, 0);
        String errorMessage = getString(R.string.generic_purchase_prepare_error);
        String errorTitle = getString(R.string.error);
        if (this.mPreviousState == 1 && this.mPreviousSubstate == 1) {
            failPurchase = true;
        } else {
            failPurchase = false;
        }
        switch (this.mSidecar.getSubstate()) {
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                error = new PurchaseError(2, 0);
                errorMessage = ErrorStrings.get(getActivity(), this.mSidecar.getVolleyError());
                errorTitle = ErrorStrings.getTitle(getActivity(), this.mSidecar.getVolleyError());
                break;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_required /*4*/:
                error = new PurchaseError(2, 0);
                errorMessage = getString(R.string.auth_required_error);
                break;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                this.mCommitChallengeResponses.clear();
                CheckoutPurchaseError checkoutPurchaseError = this.mSidecar.getCheckoutPurchaseError();
                error = new PurchaseError(3, checkoutPurchaseError.permissionError);
                errorMessage = checkoutPurchaseError.errorMessageHtml;
                break;
        }
        FinskyLog.d("Error: %s", error);
        if (failPurchase) {
            fail(error);
        }
        showError(errorTitle, errorMessage, failPurchase);
    }

    public void switchFromChangeSubscriptionToCart() {
        this.mSidecar.switchFromChangeSubscriptionToCart();
    }

    public void switchToInstrumentManager() {
        this.mSidecar.switchToInstrumentManager();
    }

    public void confirmPurchase() {
        if (this.mParams.docid.type == 1) {
            FinskyApp.get().getAnalytics(this.mAccount.name).logAdMobPageView("completePurchase?doc=" + this.mParams.docidStr);
        }
        showAppDownloadWarningAndContinue();
    }

    private void showAppDownloadWarningAndContinue() {
        if (this.mParams.docid.type != 1 || this.mAppDownloadSizeWarningParameters == null) {
            runCompleteFlowAndContinue();
        } else {
            DownloadSizeDialogFragment.newInstance(this, this.mAppDownloadSizeWarningParameters).show(getFragmentManager(), "PurchaseFragment.appDownloadSizeWarningDialog");
        }
    }

    private void runCompleteFlowAndContinue() {
        if (this.mSkipCheckout) {
            this.mSidecar.installApp();
            finish();
            return;
        }
        Instrument instrument = this.mSidecar.getCart().instrument;
        if (instrument.instrumentFamily == 2) {
            int fopVersion = BillingUtils.getFopVersion(instrument);
            if (this.mCompleteFlowFragment != null) {
                FinskyLog.wtf("No complete flow fragment expected.", new Object[0]);
                return;
            }
            if (fopVersion == 2) {
                FinskyLog.d("Start complete flow for DCB2 instrument.", new Object[0]);
                this.mCompleteFlowFragment = CompleteDcb2FlowFragment.newInstance(this.mAccount.name);
            } else if (fopVersion == 3) {
                FinskyLog.d("Start complete flow for DCB3 instrument.", new Object[0]);
                this.mCompleteFlowFragment = CompleteDcb3FlowFragment.newInstance(this.mAccount, instrument);
            }
            if (this.mCompleteFlowFragment != null) {
                showLoading();
                getChildFragmentManager().beginTransaction().add(this.mCompleteFlowFragment, "PurchaseFragment.completeFlowFragment").commit();
                return;
            }
        }
        completeCheckoutPurchase();
    }

    public boolean isDismissable(int dismissType) {
        boolean z = true;
        if (this.mCurrentFragment instanceof SuccessStepWithAuthChoices) {
            return false;
        }
        if (this.mSidecar != null) {
            if (this.mSidecar.getState() == 7) {
                if (dismissType != 1) {
                    z = false;
                }
                return z;
            } else if (this.mSidecar.getState() == 1 && this.mSidecar.getSubstate() == 2) {
                return false;
            }
        }
        return true;
    }

    public void finish() {
        ((Listener) getActivity()).onFinished();
    }

    private void completeCheckoutPurchase() {
        Map<String, String> extraPostParams = null;
        Instrument instrument = this.mSidecar.getCart().instrument;
        if (instrument.instrumentFamily == 2) {
            int fopVersion = BillingUtils.getFopVersion(instrument);
            if (fopVersion == 2) {
                extraPostParams = Dcb2Util.getCompleteParameters();
            } else if (fopVersion == 3) {
                extraPostParams = Dcb3Util.getCompleteParameters(this.mCompleteFlowResult);
            }
        }
        this.mSidecar.commit(this.mCommitChallengeResponses, extraPostParams);
    }

    public void launchBillingProfile() {
        startActivityForResult(BillingProfileActivity.createIntent(this.mAccount, this.mSidecar.getCart().purchaseContextToken, this.mParams.docid, this.mParams.offerType), 1);
    }

    public void launchRedeem() {
        startActivityForResult(RedeemCodeActivity.createBuyFlowIntent(this.mAccount.name, this.mParams.docid.backend, this.mParams.docid, this.mParams.offerType), 3);
    }

    public void switchVoucher(String voucherId) {
        if (TextUtils.isEmpty(voucherId)) {
            this.mVoucherParams = new VoucherParams(null, false, true);
        } else {
            this.mVoucherParams = new VoucherParams(voucherId, true, true);
        }
        preparePurchase();
    }

    public void escrowCvcCode(String cvc, String escrowHandleParameterKey) {
        this.mEscrowHandleParameterKey = escrowHandleParameterKey;
        this.mSidecar.escrowCvcCode(cvc);
    }

    public boolean answerChallenge(Bundle response) {
        switch (this.mSidecar.getState()) {
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                this.mPrepareChallengeResponses.putAll(response);
                preparePurchase();
                return true;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                this.mCommitChallengeResponses.putAll(response);
                completeCheckoutPurchase();
                return true;
            default:
                FinskyLog.wtf("Cannot answer challenge in state %d", Integer.valueOf(this.mSidecar.getState()));
                return false;
        }
    }

    public final void onActivityResult(int requestCode, int resultCode, Intent data) {
        boolean handledResult = false;
        switch (requestCode) {
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                if (resultCode == -1) {
                    handledResult = handleInstrumentSelected(data.getStringExtra("BillingProfileActivity.selectedInstrumentId"));
                    if (!handledResult) {
                        handledResult = handlePromoRedeemed((RedeemCodeResult) data.getParcelableExtra("BillingProfileActivity.redeemPromoCodeResult"));
                        break;
                    }
                }
                break;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                if (resultCode != -1) {
                    finish();
                    handledResult = true;
                    break;
                }
                handledResult = answerChallenge(data.getBundleExtra("challenge_response"));
                break;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                if (resultCode == -1) {
                    RedeemCodeResult redeemCodeResult = (RedeemCodeResult) data.getParcelableExtra("RedeemCodeBaseActivity.redeem_code_result");
                    if (redeemCodeResult != null) {
                        handledResult = handleInstrumentSelected(redeemCodeResult.getStoredValueInstrumentId());
                        if (!handledResult) {
                            handledResult = handlePromoRedeemed(redeemCodeResult);
                            break;
                        }
                    }
                }
                break;
            case 101:
                if (this.mCurrentFragment != null) {
                    this.mCurrentFragment.onActivityResult(requestCode, resultCode, data);
                }
                handledResult = true;
                break;
        }
        if (!handledResult) {
            logImpression(this.mCurrentFragment);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private boolean handleInstrumentSelected(String instrumentId) {
        if (TextUtils.isEmpty(instrumentId)) {
            return false;
        }
        this.mSelectedInstrumentId = instrumentId;
        preparePurchase();
        return true;
    }

    private boolean handlePromoRedeemed(RedeemCodeResult redeemPromoCodeResult) {
        if (redeemPromoCodeResult == null) {
            return false;
        }
        if (redeemPromoCodeResult.isInstallAppDeferred()) {
            this.mSkipCheckout = true;
            showAppDownloadWarningAndContinue();
            return true;
        }
        this.mSucceeded = true;
        this.mExtraPurchaseData = redeemPromoCodeResult.getExtraPurchaseData();
        finish();
        return true;
    }

    public byte[] getServerLogsCookie() {
        if (this.mSidecar == null || this.mSidecar.getServerLogsCookie() == null) {
            return this.mDocServerLogsCookie;
        }
        return this.mSidecar.getServerLogsCookie();
    }

    private void fail(PurchaseError error) {
        FinskyLog.d("Purchase failed: %s", error);
        this.mError = error;
    }

    private void handleSuccess() {
        switch (this.mSidecar.getSubstate()) {
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                FinskyLog.d("Purchase succeeded", new Object[0]);
                this.mSucceeded = true;
                if (shouldShowAuthChoicesDialog()) {
                    showStep(SuccessStepWithAuthChoices.newInstance(this.mAccount.name, this.mParams.docid.backend, this.mUsePinBasedAuth));
                    FinskyPreferences.hasSeenPurchaseSessionMessage.get(this.mAccount.name).put(Boolean.valueOf(true));
                    return;
                }
                confirmAuthChoiceSelected();
                return;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                SuccessInfo successInfo = this.mSidecar.getSuccessInfo();
                if (TextUtils.isEmpty(successInfo.buttonLabel) && TextUtils.isEmpty(successInfo.messageHtml)) {
                    performSuccessActionAndFinish();
                    return;
                } else {
                    showStep(LightPurchaseSuccessStep.newInstance(successInfo));
                    return;
                }
            default:
                throw new IllegalStateException("handleSuccess() was called from substate " + this.mSidecar.getSubstate());
        }
    }

    private boolean shouldShowAuthChoicesDialog() {
        if (((Integer) FinskyPreferences.purchaseAuthType.get(this.mAccount.name).get()).intValue() != -1 || ((Integer) G.defaultPurchaseAuthentication.get()).intValue() == 0 || ((Boolean) FinskyPreferences.hasSeenPurchaseSessionMessage.get(this.mAccount.name).get()).booleanValue()) {
            return false;
        }
        return true;
    }

    private boolean performSuccessAction(PostSuccessAction postSuccessAction) {
        if (postSuccessAction.installApp != null) {
            return false;
        }
        if (postSuccessAction.openDoc != null) {
            return ConsumptionUtils.openItem(getActivity(), this.mAccount, new Document(postSuccessAction.openDoc.doc), getFragmentManager(), this, 4);
        } else if (postSuccessAction.openHome != null) {
            FinskyLog.wtf("Invalid PostSuccessAction. Should not go to home from purchase flow.", new Object[0]);
            return false;
        } else if (postSuccessAction.purchaseFlow != null) {
            FinskyLog.wtf("Invalid PostSuccessAction. Cannot enter purchase flow from purchase flow.", new Object[0]);
            return false;
        } else if (postSuccessAction.openContainer != null) {
            FinskyLog.wtf("Invalid PostSuccessAction. Cannot open container from purchase flow.", new Object[0]);
            return false;
        } else {
            FinskyLog.w("Unsupported PostSuccessAction.", new Object[0]);
            return false;
        }
    }

    public void performSuccessActionAndFinish() {
        if (hasSucceeded()) {
            SuccessInfo successInfo = this.mSidecar.getSuccessInfo();
            if (successInfo.postSuccessAction != null && performSuccessAction(successInfo.postSuccessAction)) {
                FinskyLog.d("Dialog shown, waiting for user input.", new Object[0]);
                return;
            }
        }
        finish();
    }

    public void confirmAuthChoiceSelected() {
        this.mSidecar.confirmAuthChoiceSelected();
    }

    public boolean hasSucceeded() {
        return this.mSucceeded;
    }

    public boolean hasFailed() {
        return this.mError != null;
    }

    public PurchaseError getError() {
        return this.mError;
    }

    public Bundle getExtraPurchaseData() {
        if (this.mExtraPurchaseData != null) {
            return this.mExtraPurchaseData;
        }
        return this.mSidecar.getExtraPurchaseData();
    }

    public void setHostTitle(int titleId) {
        throw new UnsupportedOperationException();
    }

    public void showProgress(int messageId) {
        throw new UnsupportedOperationException();
    }

    public void hideProgress() {
        throw new UnsupportedOperationException();
    }

    public void onFlowFinished(BillingFlowFragment flow, Bundle result) {
        if (isResumed()) {
            removeCompleteFlowFragment();
            this.mCompleteFlowResult = result;
            completeCheckoutPurchase();
            return;
        }
        FinskyLog.d("Not resumed, ignoring onFlowFinished.", new Object[0]);
    }

    public void onFlowCanceled(BillingFlowFragment flow) {
        if (isResumed()) {
            removeCompleteFlowFragment();
            hideLoading();
            return;
        }
        FinskyLog.d("Not resumed, ignoring onFlowCanceled.", new Object[0]);
    }

    public void onFlowError(BillingFlowFragment flow, String error) {
        if (isResumed()) {
            showError(error, true);
            removeCompleteFlowFragment();
            hideLoading();
            return;
        }
        FinskyLog.d("Not resumed, ignoring onFlowError.", new Object[0]);
    }

    private void removeCompleteFlowFragment() {
        if (this.mCompleteFlowFragment != null) {
            getChildFragmentManager().beginTransaction().remove(this.mCompleteFlowFragment).commit();
            this.mCompleteFlowFragment = null;
        }
    }

    public void onDownloadOk(boolean wifiOnly) {
        FinskyLog.d("Will download %s using wifi only = %b", this.mParams.docid.backendDocid, Boolean.valueOf(wifiOnly));
        if (!wifiOnly) {
            FinskyApp.get().getInstaller().setMobileDataAllowed(packageName);
        }
        runCompleteFlowAndContinue();
    }

    public void onSetupWifi() {
        startActivity(new Intent("android.settings.WIFI_SETTINGS"));
    }

    public void onDownloadCancel() {
        FinskyLog.d("Download size warning dismissed for app = %s", this.mParams.docid.backendDocid);
    }

    public void onPositiveClick(int requestCode, Bundle extraArguments) {
        if (requestCode == 4) {
            startActivity(IntentUtils.createViewDocumentUrlIntent(getActivity(), extraArguments.getString("dialog_details_url")));
            finish();
        }
    }

    public void onNegativeClick(int requestCode, Bundle extraArguments) {
        if (requestCode == 4) {
            finish();
        }
    }
}
