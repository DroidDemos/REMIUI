package com.google.android.finsky.billing.carrierbilling.flow;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.telephony.PhoneNumberUtils;
import android.text.TextUtils;
import com.android.vending.R;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.analytics.FinskyEventLog;
import com.google.android.finsky.api.DfeApi;
import com.google.android.finsky.billing.BillingFlowContext;
import com.google.android.finsky.billing.BillingFlowListener;
import com.google.android.finsky.billing.BillingLocator;
import com.google.android.finsky.billing.BillingUtils;
import com.google.android.finsky.billing.BillingUtils.AddressMode;
import com.google.android.finsky.billing.InstrumentFlow;
import com.google.android.finsky.billing.carrierbilling.CarrierBillingUtils;
import com.google.android.finsky.billing.carrierbilling.PhoneCarrierBillingUtils;
import com.google.android.finsky.billing.carrierbilling.action.CarrierBillingAction;
import com.google.android.finsky.billing.carrierbilling.flow.association.AssociationAction;
import com.google.android.finsky.billing.carrierbilling.flow.association.CarrierOutAssociation;
import com.google.android.finsky.billing.carrierbilling.fragment.AddCarrierBillingFragment;
import com.google.android.finsky.billing.carrierbilling.fragment.AddCarrierBillingFragment.AddCarrierBillingResultListener;
import com.google.android.finsky.billing.carrierbilling.fragment.AddCarrierBillingFragment.AddCarrierBillingResultListener.AddResult;
import com.google.android.finsky.billing.carrierbilling.fragment.AddCarrierBillingFragment.Type;
import com.google.android.finsky.billing.carrierbilling.fragment.CarrierBillingErrorDialog;
import com.google.android.finsky.billing.carrierbilling.fragment.CarrierBillingErrorDialog.CarrierBillingErrorListener;
import com.google.android.finsky.billing.carrierbilling.fragment.EditCarrierBillingFragment;
import com.google.android.finsky.billing.carrierbilling.fragment.EditCarrierBillingFragment.EditCarrierBillingResultListener;
import com.google.android.finsky.billing.carrierbilling.fragment.SetupWizardVerifyAssociationFragment;
import com.google.android.finsky.billing.carrierbilling.fragment.VerifyAssociationDialogFragment;
import com.google.android.finsky.billing.carrierbilling.fragment.VerifyAssociationDialogFragment.VerifyAssociationListener;
import com.google.android.finsky.billing.carrierbilling.model.CarrierBillingStorage;
import com.google.android.finsky.billing.carrierbilling.model.SubscriberInfo;
import com.google.android.finsky.config.G;
import com.google.android.finsky.protos.BillingAddress.Address;
import com.google.android.finsky.protos.BuyInstruments.UpdateInstrumentRequest;
import com.google.android.finsky.protos.BuyInstruments.UpdateInstrumentResponse;
import com.google.android.finsky.protos.CarrierBilling.VerifyAssociationResponse;
import com.google.android.finsky.protos.CommonDevice.CarrierBillingInstrument;
import com.google.android.finsky.protos.CommonDevice.CarrierBillingInstrumentStatus;
import com.google.android.finsky.protos.CommonDevice.CarrierTos;
import com.google.android.finsky.protos.CommonDevice.CarrierTosEntry;
import com.google.android.finsky.protos.CommonDevice.Instrument;
import com.google.android.finsky.protos.VendingProtos.PurchaseMetadataResponseProto.Countries.Country;
import com.google.android.finsky.utils.ErrorStrings;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.Utils;
import java.util.ArrayList;
import java.util.List;

public class CreateDcb3Flow extends InstrumentFlow implements ErrorListener, Listener<UpdateInstrumentResponse>, AddCarrierBillingResultListener, CarrierBillingErrorListener, EditCarrierBillingResultListener, VerifyAssociationListener {
    private final String mAccountName;
    private UpdateInstrumentResponse mAddCarrierBillingResponse;
    private AddCarrierBillingFragment mAddFragment;
    private boolean mAddFragmentShown;
    private AddResult mAddResult;
    private AddressMode mAddressMode;
    private AssociationAction mAssociationAction;
    private String mAssociationAddress;
    private AssociationListener mAssociationListener;
    private String mAssociationPrefix;
    private boolean mAssociationRequired;
    private int mBillingUiMode;
    private String mCarrierName;
    private final BillingFlowContext mContext;
    private String mDcbTosUrl;
    private String mDcbTosVersion;
    private final DfeApi mDfeApi;
    private EditCarrierBillingFragment mEditFragment;
    private CarrierBillingErrorDialog mErrorFragment;
    private final FinskyEventLog mEventLog;
    private String mPiiTosUrl;
    private String mPiiTosVersion;
    private State mState;
    private final CarrierBillingStorage mStorage;
    private Address mSubscriberAddress;
    private String mTitle;
    private SubscriberInfo mUserProvidedAddress;
    private Fragment mVerifyFragment;

    static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] $SwitchMap$com$google$android$finsky$billing$carrierbilling$flow$CreateDcb3Flow$State;

        static {
            $SwitchMap$com$google$android$finsky$billing$carrierbilling$flow$CreateDcb3Flow$State = new int[State.values().length];
            try {
                $SwitchMap$com$google$android$finsky$billing$carrierbilling$flow$CreateDcb3Flow$State[State.INIT.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$google$android$finsky$billing$carrierbilling$flow$CreateDcb3Flow$State[State.SHOWING_PII_TOS.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$google$android$finsky$billing$carrierbilling$flow$CreateDcb3Flow$State[State.ASSOCIATING_PIN.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$google$android$finsky$billing$carrierbilling$flow$CreateDcb3Flow$State[State.SHOWING_EDIT_USERINFO.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$com$google$android$finsky$billing$carrierbilling$flow$CreateDcb3Flow$State[State.SHOWING_DCB_TOS_AND_USERINFO.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$com$google$android$finsky$billing$carrierbilling$flow$CreateDcb3Flow$State[State.SENDING_REQUEST.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
        }
    }

    class AssociationListener implements Listener<VerifyAssociationResponse> {
        AssociationListener() {
        }

        public void onResponse(VerifyAssociationResponse response) {
            CreateDcb3Flow.this.hideVerifyAssociationFragment();
            if (response.status == 1) {
                if (response.billingAddress != null) {
                    CreateDcb3Flow.this.mSubscriberAddress = response.billingAddress;
                    if (Utils.isEmptyOrSpaces(CreateDcb3Flow.this.mSubscriberAddress.phoneNumber)) {
                        CreateDcb3Flow.this.mSubscriberAddress.phoneNumber = PhoneNumberUtils.formatNumber(BillingLocator.getLine1NumberFromTelephony());
                        CreateDcb3Flow.this.mSubscriberAddress.hasPhoneNumber = true;
                    }
                    if (!(TextUtils.isEmpty(CreateDcb3Flow.this.mSubscriberAddress.addressLine1) || TextUtils.isEmpty(CreateDcb3Flow.this.mSubscriberAddress.city))) {
                        CreateDcb3Flow.this.mAddressMode = AddressMode.FULL_ADDRESS;
                    }
                }
                if (response.carrierTos != null) {
                    CarrierTosEntry dcbTos = response.carrierTos.dcbTos;
                    CreateDcb3Flow.this.mDcbTosUrl = dcbTos.url;
                    CreateDcb3Flow.this.mDcbTosVersion = dcbTos.version;
                }
                CreateDcb3Flow.this.performNext();
                return;
            }
            String error;
            String logError = "UNKNOWN";
            if (response.status == 3) {
                error = FinskyApp.get().getString(R.string.associating_device_timeout_message);
                logError = "ASSOCIATION_TIMEOUT";
            } else if (response.status == 2) {
                if (!response.hasCarrierErrorMessage || TextUtils.isEmpty(response.carrierErrorMessage)) {
                    error = FinskyApp.get().getString(R.string.device_association_failed);
                } else {
                    error = response.carrierErrorMessage;
                }
                logError = "INVALID_USER";
            } else {
                error = FinskyApp.get().getString(R.string.generic_purchase_completion_error);
            }
            CreateDcb3Flow.this.showError(error, logError, error, true);
        }
    }

    enum State {
        INIT,
        SHOWING_PII_TOS,
        ASSOCIATING_PIN,
        SHOWING_EDIT_USERINFO,
        SHOWING_DCB_TOS_AND_USERINFO,
        SENDING_REQUEST,
        DONE
    }

    public CreateDcb3Flow(BillingFlowContext billingFlowContext, BillingFlowListener listener, DfeApi dfeApi, CarrierBillingInstrumentStatus status, int mode) {
        this(billingFlowContext, listener, BillingLocator.getCarrierBillingStorage(), dfeApi, status, mode);
    }

    CreateDcb3Flow(BillingFlowContext billingFlowContext, BillingFlowListener listener, CarrierBillingStorage storage, DfeApi dfeApi, CarrierBillingInstrumentStatus status, int mode) {
        super(billingFlowContext, listener, null);
        this.mState = State.INIT;
        this.mAddResult = null;
        this.mAddressMode = AddressMode.FULL_ADDRESS;
        this.mContext = billingFlowContext;
        this.mStorage = storage;
        this.mDfeApi = dfeApi;
        this.mBillingUiMode = mode;
        if (((Boolean) G.enableDcbReducedBillingAddress.get()).booleanValue()) {
            List<Country> countries = BillingLocator.getBillingCountries();
            if (countries != null) {
                Country carrierBillingCountry = BillingUtils.findCountry(BillingUtils.getDefaultCountry(FinskyApp.get(), null), countries);
                if (carrierBillingCountry != null) {
                    this.mAddressMode = carrierBillingCountry.allowsReducedBillingAddress ? AddressMode.REDUCED_ADDRESS : AddressMode.FULL_ADDRESS;
                }
            }
        }
        this.mAccountName = dfeApi.getAccountName();
        this.mEventLog = FinskyApp.get().getEventLogger(dfeApi.getAccount());
        if (status.deviceAssociation != null) {
            this.mAssociationAddress = status.deviceAssociation.userTokenRequestAddress;
            this.mAssociationPrefix = status.deviceAssociation.userTokenRequestMessage;
        }
        if (status.hasName) {
            this.mCarrierName = status.name;
            this.mTitle = FinskyApp.get().getString(R.string.dcb_setup_title, new Object[]{this.mCarrierName});
        } else {
            FinskyLog.wtf("No carrier name available in status.", new Object[0]);
        }
        if (status.carrierTos != null) {
            CarrierTos carrierTos = status.carrierTos;
            if (carrierTos.dcbTos != null) {
                CarrierTosEntry dcbTos = carrierTos.dcbTos;
                this.mDcbTosUrl = dcbTos.url;
                this.mDcbTosVersion = dcbTos.version;
            }
            if (carrierTos.piiTos != null) {
                CarrierTosEntry piiTos = carrierTos.piiTos;
                this.mPiiTosUrl = piiTos.url;
                this.mPiiTosVersion = piiTos.version;
            }
        }
        this.mAssociationRequired = status.associationRequired;
    }

    private void logDcbAddError(VolleyError error) {
        logDcbAddError(1, error.getClass().getCanonicalName());
    }

    private void logDcbAddError(int errorCode, String exceptionType) {
        this.mEventLog.logBackgroundEvent(342, null, null, errorCode, exceptionType, null);
    }

    private void logError(String logError) {
    }

    public void start() {
        if (initParams()) {
            new CarrierBillingAction().run(new Runnable() {
                public void run() {
                    CreateDcb3Flow.this.performNext();
                }
            });
        }
    }

    boolean initParams() {
        if (this.mCarrierName != null && this.mDfeApi != null) {
            return true;
        }
        FinskyLog.d("Cannot run this BillingFlow since carrier name or DFE api is null.", new Object[0]);
        fail(FinskyApp.get().getString(R.string.generic_purchase_prepare_error));
        return false;
    }

    public boolean canGoBack() {
        return true;
    }

    public void back() {
        cancel();
    }

    public void cancel() {
        super.cancel();
    }

    public void resumeFromSavedState(final Bundle bundle) {
        if (initParams()) {
            new CarrierBillingAction().run(new Runnable() {
                public void run() {
                    CreateDcb3Flow.this.continueResume(bundle);
                }
            });
        }
    }

    public void onActivityResume() {
        if (this.mAssociationAction != null && this.mAssociationListener != null) {
            this.mAssociationAction.setListener(this.mAssociationListener, this);
        }
    }

    private void continueResume(Bundle bundle) {
        if (this.mState != State.INIT) {
            throw new IllegalStateException();
        }
        this.mState = State.valueOf(bundle.getString("state"));
        if (this.mState == State.SENDING_REQUEST) {
            finish();
        }
        this.mAddFragmentShown = bundle.getBoolean("add_fragment_shown");
        this.mUserProvidedAddress = (SubscriberInfo) bundle.getParcelable("user_provided_address");
        if (bundle.containsKey("dcb_tos_url")) {
            this.mDcbTosUrl = bundle.getString("dcb_tos_url");
        }
        if (bundle.containsKey("dcb_tos_version")) {
            this.mDcbTosVersion = bundle.getString("dcb_tos_version");
        }
        if (bundle.containsKey("pii_tos_url")) {
            this.mPiiTosUrl = bundle.getString("pii_tos_url");
        }
        if (bundle.containsKey("pii_tos_version")) {
            this.mPiiTosVersion = bundle.getString("pii_tos_version");
        }
        if (bundle.containsKey("error_dialog")) {
            this.mErrorFragment = (CarrierBillingErrorDialog) this.mContext.restoreFragment(bundle, "error_dialog");
            this.mErrorFragment.setOnResultListener(this);
        }
        if (bundle.containsKey("add_fragment")) {
            this.mAddFragment = (AddCarrierBillingFragment) this.mContext.restoreFragment(bundle, "add_fragment");
            this.mAddFragment.setOnResultListener(this);
        }
        if (bundle.containsKey("edit_fragment")) {
            this.mEditFragment = (EditCarrierBillingFragment) this.mContext.restoreFragment(bundle, "edit_fragment");
            this.mEditFragment.setOnResultListener(this);
        }
        if (bundle.containsKey("verify_dialog")) {
            this.mAssociationAction = new CarrierOutAssociation(this.mDfeApi, this.mAssociationAddress, this.mAssociationPrefix, this.mPiiTosVersion, true);
            this.mAssociationListener = new AssociationListener();
            this.mAssociationAction.resumeState(bundle, this.mAssociationListener, this);
            this.mVerifyFragment = this.mContext.restoreFragment(bundle, "verify_dialog");
            if (this.mVerifyFragment instanceof VerifyAssociationDialogFragment) {
                ((VerifyAssociationDialogFragment) this.mVerifyFragment).setOnResultListener(this);
            } else if (this.mVerifyFragment instanceof SetupWizardVerifyAssociationFragment) {
                ((SetupWizardVerifyAssociationFragment) this.mVerifyFragment).setOnResultListener(this);
            }
        }
    }

    public void saveState(Bundle bundle) {
        bundle.putString("state", this.mState.name());
        bundle.putBoolean("add_fragment_shown", this.mAddFragmentShown);
        if (this.mErrorFragment != null) {
            this.mContext.persistFragment(bundle, "error_dialog", this.mErrorFragment);
        }
        if (this.mAddFragment != null) {
            this.mContext.persistFragment(bundle, "add_fragment", this.mAddFragment);
        }
        if (this.mEditFragment != null) {
            this.mContext.persistFragment(bundle, "edit_fragment", this.mEditFragment);
        }
        if (this.mVerifyFragment != null) {
            this.mContext.persistFragment(bundle, "verify_dialog", this.mVerifyFragment);
            if (this.mAssociationAction != null) {
                this.mAssociationAction.saveState(bundle);
            }
        }
        if (this.mUserProvidedAddress != null) {
            bundle.putParcelable("user_provided_address", this.mUserProvidedAddress);
        }
        if (!TextUtils.isEmpty(this.mDcbTosUrl)) {
            bundle.putString("dcb_tos_url", this.mDcbTosUrl);
            bundle.putString("dcb_tos_version", this.mDcbTosVersion);
        }
        if (!TextUtils.isEmpty(this.mPiiTosUrl)) {
            bundle.putString("pii_tos_version", this.mPiiTosVersion);
            bundle.putString("pii_tos_url", this.mPiiTosUrl);
        }
    }

    void performNext() {
        switch (AnonymousClass3.$SwitchMap$com$google$android$finsky$billing$carrierbilling$flow$CreateDcb3Flow$State[this.mState.ordinal()]) {
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                this.mState = State.SHOWING_PII_TOS;
                if (TextUtils.isEmpty(this.mPiiTosUrl)) {
                    performNext();
                    return;
                } else {
                    showTosFragment(null, this.mPiiTosUrl, null);
                    return;
                }
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                if (this.mAssociationRequired) {
                    this.mState = State.ASSOCIATING_PIN;
                    startAssociation();
                    return;
                }
                this.mState = State.SHOWING_EDIT_USERINFO;
                showEditAddressFragment(null);
                return;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                if (isSubscriberAddressValid(this.mSubscriberAddress)) {
                    this.mState = State.SHOWING_DCB_TOS_AND_USERINFO;
                    if (TextUtils.isEmpty(this.mSubscriberAddress.addressLine1)) {
                        showTosFragment(PhoneCarrierBillingUtils.getSubscriberInfo(this.mSubscriberAddress), this.mDcbTosUrl, null);
                        return;
                    } else {
                        showTosFragment(null, this.mDcbTosUrl, this.mSubscriberAddress.addressLine1);
                        return;
                    }
                }
                this.mState = State.SHOWING_EDIT_USERINFO;
                showEditAddressFragment(null);
                return;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_required /*4*/:
                this.mState = State.SHOWING_DCB_TOS_AND_USERINFO;
                showTosFragment(this.mUserProvidedAddress, this.mDcbTosUrl, null);
                return;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                if (this.mAddResult == AddResult.EDIT_ADDRESS) {
                    this.mState = State.SHOWING_EDIT_USERINFO;
                    showEditAddressFragment(null);
                    return;
                }
                this.mState = State.SENDING_REQUEST;
                showProgress();
                performRequest();
                return;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                if (this.mAddCarrierBillingResponse == null) {
                    FinskyLog.wtf("Update instrument response is null.", new Object[0]);
                    showGenericError("Update instrument response is null.", "UNKNOWN");
                    return;
                } else if (this.mAddCarrierBillingResponse.result == 0) {
                    this.mEventLog.logBackgroundEvent(341, null, null, 0, null, null);
                    this.mState = State.DONE;
                    finishWithUpdateInstrumentResponse(this.mAddCarrierBillingResponse);
                    return;
                } else if (this.mAddCarrierBillingResponse.checkoutTokenRequired) {
                    FinskyLog.wtf("Unexpected checkout_token_required.", new Object[0]);
                    showGenericError("Unexpected checkout_token_required.", "UNKNOWN");
                    return;
                } else {
                    ArrayList<Integer> retriableErrors = CarrierBillingUtils.getRetriableErrors(this.mAddCarrierBillingResponse);
                    if (retriableErrors != null) {
                        logDcbAddError(2, null);
                        this.mState = State.SHOWING_EDIT_USERINFO;
                        showEditAddressFragment(retriableErrors);
                        return;
                    } else if (this.mAddCarrierBillingResponse.hasUserMessageHtml) {
                        logDcbAddError(3, null);
                        showError("Update carrier billing instrument had error", "UNKNOWN", this.mAddCarrierBillingResponse.userMessageHtml, false);
                        return;
                    } else {
                        logDcbAddError(0, null);
                        showGenericError("Could not add carrier billing instrument.", "UNKNOWN");
                        return;
                    }
                }
            default:
                throw new IllegalStateException("Unexpected state: " + this.mState);
        }
    }

    private boolean isSubscriberAddressValid(Address subscriberAddress) {
        boolean requiresPhoneNumber = PhoneCarrierBillingUtils.isPhoneNumberRequired(this.mAddressMode, this.mStorage);
        if (subscriberAddress == null || TextUtils.isEmpty(subscriberAddress.postalCode) || TextUtils.isEmpty(subscriberAddress.name) || (requiresPhoneNumber && TextUtils.isEmpty(subscriberAddress.phoneNumber))) {
            return false;
        }
        if (this.mAddressMode != AddressMode.FULL_ADDRESS) {
            return true;
        }
        if (TextUtils.isEmpty(subscriberAddress.addressLine1) || TextUtils.isEmpty(subscriberAddress.city)) {
            return false;
        }
        return true;
    }

    private Instrument createCarrierBillingInstrument() {
        CarrierBillingInstrument dcbInstrument = new CarrierBillingInstrument();
        dcbInstrument.instrumentKey = this.mStorage.getCurrentSimIdentifier();
        dcbInstrument.hasInstrumentKey = true;
        CarrierTos carrierTos = new CarrierTos();
        if (!TextUtils.isEmpty(this.mDcbTosVersion)) {
            carrierTos.dcbTos = new CarrierTosEntry();
            carrierTos.dcbTos.version = this.mDcbTosVersion;
            carrierTos.dcbTos.hasVersion = true;
        }
        if (!TextUtils.isEmpty(this.mPiiTosVersion)) {
            carrierTos.piiTos = new CarrierTosEntry();
            carrierTos.piiTos.version = this.mPiiTosVersion;
            carrierTos.piiTos.hasVersion = true;
        }
        dcbInstrument.acceptedCarrierTos = carrierTos;
        Instrument instrument = new Instrument();
        if (this.mUserProvidedAddress != null) {
            instrument.billingAddress = PhoneCarrierBillingUtils.subscriberInfoToAddress(this.mUserProvidedAddress, this.mAddressMode);
        } else if (this.mSubscriberAddress == null) {
            FinskyLog.wtf("No Subscriber address available.", new Object[0]);
        } else {
            instrument.billingAddress = this.mSubscriberAddress;
        }
        instrument.carrierBilling = dcbInstrument;
        return instrument;
    }

    protected void performRequest() {
        this.mEventLog.logBackgroundEvent(340, null, null, 0, null, null);
        UpdateInstrumentRequest request = new UpdateInstrumentRequest();
        request.instrument = createCarrierBillingInstrument();
        this.mDfeApi.updateInstrument(request, this, this);
    }

    void startAssociation() {
        showVerifyAssociationFragment();
        this.mAssociationAction = new CarrierOutAssociation(this.mDfeApi, this.mAssociationAddress, this.mAssociationPrefix, this.mPiiTosVersion, true);
        this.mAssociationListener = new AssociationListener();
        this.mAssociationAction.start(this.mAssociationListener, this);
    }

    void showTosFragment(SubscriberInfo editedAddress, String url, String snippet) {
        Type type;
        if (TextUtils.isEmpty(url)) {
            if (editedAddress != null) {
                type = Type.FULL_ADDRESS;
            } else if (TextUtils.isEmpty(snippet)) {
                FinskyLog.w("showTosFragment has no address and tos. wrong fragment.", new Object[0]);
                return;
            } else {
                type = Type.ADDRESS_SNIPPET;
            }
        } else if (editedAddress != null) {
            type = Type.FULL_ADDRESS_AND_TOS;
        } else if (TextUtils.isEmpty(snippet)) {
            type = Type.TOS;
        } else {
            type = Type.ADDRESS_SNIPPET_AND_TOS;
        }
        this.mAddFragment = AddCarrierBillingFragment.newInstance(type, editedAddress, url, snippet, this.mCarrierName, this.mAccountName, this.mBillingUiMode);
        this.mAddFragment.setOnResultListener(this);
        this.mAddFragmentShown = true;
        this.mContext.showFragment(this.mAddFragment, this.mTitle, false);
    }

    private void hideTosFragment() {
        if (this.mAddFragment != null) {
            this.mContext.hideFragment(this.mAddFragment, false);
            this.mAddFragment = null;
        }
    }

    void showEditAddressFragment(ArrayList<Integer> errorList) {
        this.mEditFragment = EditCarrierBillingFragment.newInstance(this.mAccountName, this.mAddressMode, this.mUserProvidedAddress != null ? this.mUserProvidedAddress : PhoneCarrierBillingUtils.getSubscriberInfo(this.mSubscriberAddress), errorList, this.mBillingUiMode);
        this.mEditFragment.setOnResultListener(this);
        this.mContext.showFragment(this.mEditFragment, this.mTitle, false);
    }

    private void hideEditFragment() {
        if (this.mEditFragment != null) {
            this.mContext.hideFragment(this.mEditFragment, false);
            this.mEditFragment = null;
        }
    }

    private void showVerifyAssociationFragment() {
        if (this.mBillingUiMode == 0) {
            this.mVerifyFragment = VerifyAssociationDialogFragment.newInstance(this.mAccountName);
            ((VerifyAssociationDialogFragment) this.mVerifyFragment).setOnResultListener(this);
            this.mContext.showDialogFragment((VerifyAssociationDialogFragment) this.mVerifyFragment, "verifying pin");
            return;
        }
        this.mVerifyFragment = SetupWizardVerifyAssociationFragment.newInstance(this.mAccountName);
        ((SetupWizardVerifyAssociationFragment) this.mVerifyFragment).setOnResultListener(this);
        this.mContext.showFragment(this.mVerifyFragment, FinskyApp.get().getString(R.string.verify_pin_title), false);
        showProgress();
    }

    private void hideVerifyAssociationFragment() {
        if (this.mVerifyFragment != null) {
            if (this.mBillingUiMode == 0) {
                ((VerifyAssociationDialogFragment) this.mVerifyFragment).dismiss();
            } else {
                this.mContext.hideFragment(this.mVerifyFragment, false);
                hideProgress();
            }
            this.mVerifyFragment = null;
        }
    }

    private void showErrorDialog(String message, boolean fatal) {
        this.mErrorFragment = CarrierBillingErrorDialog.newInstance(message, fatal);
        this.mErrorFragment.setOnResultListener(this);
        this.mContext.showDialogFragment(this.mErrorFragment, "error");
    }

    void showError(String finskyLogString, String logString, String message, boolean fatal) {
        FinskyLog.w(finskyLogString, new Object[0]);
        logError(logString);
        showErrorDialog(message, fatal);
    }

    void showGenericError(String finskyLogString, String logString) {
        showError(finskyLogString, logString, FinskyApp.get().getString(R.string.add_carrier_billing_error, new Object[]{this.mCarrierName}), false);
    }

    private void showProgress() {
        this.mContext.showProgress(R.string.saving);
        if (this.mAddFragment != null) {
            this.mAddFragment.enableUi(false);
        }
    }

    private void hideProgress() {
        this.mContext.hideProgress();
        if (this.mAddFragment != null) {
            this.mAddFragment.enableUi(true);
        }
    }

    public void onResponse(UpdateInstrumentResponse response) {
        this.mAddCarrierBillingResponse = response;
        hideProgress();
        performNext();
    }

    public void onAddCarrierBillingResult(AddResult result) {
        this.mAddResult = result;
        hideTosFragment();
        if (result == AddResult.SUCCESS) {
            performNext();
        } else if (result == AddResult.EDIT_ADDRESS) {
            performNext();
        } else if (result == AddResult.CANCELED) {
            cancel();
        } else {
            showGenericError("Invalid error code.", "UNKNOWN");
        }
    }

    public void onEditCarrierBillingResult(SubscriberInfo returnAddress) {
        hideEditFragment();
        if (returnAddress != null) {
            this.mUserProvidedAddress = returnAddress;
            performNext();
            return;
        }
        cancel();
    }

    public void onVerifyCancel() {
        if (this.mAssociationAction != null) {
            this.mAssociationAction.cancel();
        }
        cancel();
    }

    public void onErrorDismiss(boolean errorFatal) {
        if (this.mAssociationAction != null) {
            this.mAssociationAction.cancel();
        }
        if (errorFatal) {
            fail(FinskyApp.get().getString(R.string.generic_purchase_prepare_error));
        } else {
            cancel();
        }
    }

    public void onErrorResponse(VolleyError error) {
        hideProgress();
        FinskyLog.w("Error received: %s", error);
        logDcbAddError(error);
        showErrorDialog(ErrorStrings.get(FinskyApp.get(), error), false);
    }

    void setState(State state) {
        this.mState = state;
    }

    State getState() {
        return this.mState;
    }
}
