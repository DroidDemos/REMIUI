package com.google.android.finsky.billing.carrierbilling.flow;

import android.os.Bundle;
import android.text.TextUtils;
import com.android.vending.R;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.api.DfeApi;
import com.google.android.finsky.billing.BillingFlowContext;
import com.google.android.finsky.billing.BillingFlowListener;
import com.google.android.finsky.billing.BillingLocator;
import com.google.android.finsky.billing.BillingPreferences;
import com.google.android.finsky.billing.BillingUtils;
import com.google.android.finsky.billing.BillingUtils.AddressMode;
import com.google.android.finsky.billing.InstrumentFlow;
import com.google.android.finsky.billing.carrierbilling.PhoneCarrierBillingUtils;
import com.google.android.finsky.billing.carrierbilling.action.CarrierBillingAction;
import com.google.android.finsky.billing.carrierbilling.action.CarrierProvisioningAction;
import com.google.android.finsky.billing.carrierbilling.fragment.AddCarrierBillingFragment;
import com.google.android.finsky.billing.carrierbilling.fragment.AddCarrierBillingFragment.AddCarrierBillingResultListener;
import com.google.android.finsky.billing.carrierbilling.fragment.AddCarrierBillingFragment.AddCarrierBillingResultListener.AddResult;
import com.google.android.finsky.billing.carrierbilling.fragment.AddCarrierBillingFragment.Type;
import com.google.android.finsky.billing.carrierbilling.fragment.CarrierBillingErrorDialog;
import com.google.android.finsky.billing.carrierbilling.fragment.CarrierBillingErrorDialog.CarrierBillingErrorListener;
import com.google.android.finsky.billing.carrierbilling.fragment.EditCarrierBillingFragment;
import com.google.android.finsky.billing.carrierbilling.fragment.EditCarrierBillingFragment.EditCarrierBillingResultListener;
import com.google.android.finsky.billing.carrierbilling.model.CarrierBillingParameters;
import com.google.android.finsky.billing.carrierbilling.model.CarrierBillingProvisioning;
import com.google.android.finsky.billing.carrierbilling.model.CarrierBillingStorage;
import com.google.android.finsky.billing.carrierbilling.model.SubscriberInfo;
import com.google.android.finsky.config.G;
import com.google.android.finsky.protos.BuyInstruments.UpdateInstrumentRequest;
import com.google.android.finsky.protos.BuyInstruments.UpdateInstrumentResponse;
import com.google.android.finsky.protos.ChallengeProto.InputValidationError;
import com.google.android.finsky.protos.CommonDevice.CarrierBillingCredentials;
import com.google.android.finsky.protos.CommonDevice.CarrierBillingInstrument;
import com.google.android.finsky.protos.CommonDevice.Instrument;
import com.google.android.finsky.protos.VendingProtos.PurchaseMetadataResponseProto.Countries.Country;
import com.google.android.finsky.utils.ErrorStrings;
import com.google.android.finsky.utils.FinskyLog;
import java.util.ArrayList;
import java.util.List;

public class CreateCarrierBillingFlow extends InstrumentFlow implements ErrorListener, AddCarrierBillingResultListener, CarrierBillingErrorListener, EditCarrierBillingResultListener {
    private String mAccountName;
    private AddCarrierBillingFragment mAddFragment;
    private boolean mAddFragmentShown;
    private AddResult mAddResult;
    private AddressAvailable mAddressAvailable;
    private AddressMode mAddressMode;
    private final BillingFlowContext mContext;
    private final DfeApi mDfeApi;
    private EditCarrierBillingFragment mEditFragment;
    private CarrierBillingErrorDialog mErrorFragment;
    private CarrierBillingParameters mParams;
    private CarrierBillingProvisioning mProvisioning;
    private boolean mSavingScreenShown;
    private State mState;
    private final CarrierBillingStorage mStorage;
    private String mTitle;
    private String mTosVersion;
    private UpdateInstrumentResponse mUpdateInstrumentResponse;
    private SubscriberInfo mUserProvidedAddress;

    static /* synthetic */ class AnonymousClass5 {
        static final /* synthetic */ int[] $SwitchMap$com$google$android$finsky$billing$carrierbilling$flow$CreateCarrierBillingFlow$State;

        static {
            $SwitchMap$com$google$android$finsky$billing$carrierbilling$flow$CreateCarrierBillingFlow$State = new int[State.values().length];
            try {
                $SwitchMap$com$google$android$finsky$billing$carrierbilling$flow$CreateCarrierBillingFlow$State[State.SHOWING_TOS.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$google$android$finsky$billing$carrierbilling$flow$CreateCarrierBillingFlow$State[State.SHOWING_TOS_AND_USERINFO.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$google$android$finsky$billing$carrierbilling$flow$CreateCarrierBillingFlow$State[State.SHOWING_EDIT_USERINFO.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$google$android$finsky$billing$carrierbilling$flow$CreateCarrierBillingFlow$State[State.SENDING_REQUEST.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$com$google$android$finsky$billing$carrierbilling$flow$CreateCarrierBillingFlow$State[State.INIT.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
        }
    }

    private enum AddressAvailable {
        FULL_ADDRESS,
        ADDRESS_SNIPPET,
        NO_ADDRESS
    }

    class AfterError implements Runnable {
        AfterError() {
        }

        public void run() {
            CreateCarrierBillingFlow.this.hideProgress();
            CreateCarrierBillingFlow.this.showGenericError("Fetching provisioning from carrier failed", "UNKNOWN");
        }
    }

    class AfterProvisioning implements Runnable {
        AfterProvisioning() {
        }

        public void run() {
            CreateCarrierBillingFlow.this.hideProgress();
            CreateCarrierBillingFlow.this.mProvisioning = CreateCarrierBillingFlow.this.mStorage.getProvisioning();
            CreateCarrierBillingFlow.this.setAddressAvailability();
            CreateCarrierBillingFlow.this.performNext();
        }
    }

    enum State {
        INIT,
        SHOWING_TOS,
        SHOWING_TOS_AND_USERINFO,
        SHOWING_EDIT_USERINFO,
        SENDING_REQUEST,
        DONE
    }

    public CreateCarrierBillingFlow(BillingFlowContext billingFlowContext, BillingFlowListener listener, DfeApi dfeApi) {
        this(billingFlowContext, listener, BillingLocator.getCarrierBillingStorage(), dfeApi);
    }

    CreateCarrierBillingFlow(BillingFlowContext billingFlowContext, BillingFlowListener listener, CarrierBillingStorage storage, DfeApi dfeApi) {
        super(billingFlowContext, listener, null);
        this.mState = State.INIT;
        this.mAddressAvailable = AddressAvailable.NO_ADDRESS;
        this.mAddResult = null;
        this.mAddressMode = AddressMode.FULL_ADDRESS;
        this.mContext = billingFlowContext;
        this.mStorage = storage;
        this.mDfeApi = dfeApi;
        this.mParams = this.mStorage.getParams();
        this.mProvisioning = this.mStorage.getProvisioning();
        if (((Boolean) G.enableDcbReducedBillingAddress.get()).booleanValue()) {
            List<Country> countries = BillingLocator.getBillingCountries();
            if (countries != null) {
                Country carrierBillingCountry = BillingUtils.findCountry(BillingUtils.getDefaultCountry(FinskyApp.get(), null), countries);
                if (carrierBillingCountry != null) {
                    this.mAddressMode = carrierBillingCountry.allowsReducedBillingAddress ? AddressMode.REDUCED_ADDRESS : AddressMode.FULL_ADDRESS;
                }
            }
        }
        if (this.mDfeApi != null) {
            this.mAccountName = this.mDfeApi.getAccountName();
        }
        if (this.mParams == null || this.mParams.getName() == null) {
            FinskyLog.e("No carrier name available in params.", new Object[0]);
        } else {
            this.mTitle = FinskyApp.get().getString(R.string.dcb_setup_title, new Object[]{this.mParams.getName()});
        }
    }

    private void logTosAndAddress(boolean hasTos, boolean hasAddressSnippet, boolean hasFullAddress) {
    }

    private void logEditAddress(boolean hasAddressSnippet) {
    }

    private void logError(VolleyError error) {
    }

    private void logError(String logError) {
    }

    public void start() {
        if (initParams()) {
            new CarrierBillingAction().run(new Runnable() {
                public void run() {
                    CreateCarrierBillingFlow.this.performNext();
                }
            });
        }
    }

    boolean initParams() {
        if (this.mParams == null || this.mProvisioning == null) {
            FinskyLog.d("Cannot run this BillingFlow since params or provisioning is null.", new Object[0]);
            fail(FinskyApp.get().getString(R.string.generic_purchase_prepare_error));
            return false;
        }
        setAddressAvailability();
        return true;
    }

    public boolean canGoBack() {
        return true;
    }

    public void back() {
        if (this.mState == State.SHOWING_EDIT_USERINFO) {
            hideEditFragment();
            onEditCancel();
            return;
        }
        cancel();
    }

    public void cancel() {
        super.cancel();
    }

    public void resumeFromSavedState(final Bundle bundle) {
        if (initParams()) {
            new CarrierBillingAction().run(new Runnable() {
                public void run() {
                    CreateCarrierBillingFlow.this.continueResume(bundle);
                }
            });
        }
    }

    private void continueResume(Bundle bundle) {
        if (this.mState != State.INIT) {
            throw new IllegalStateException();
        }
        this.mState = State.valueOf(bundle.getString("state"));
        this.mAddFragmentShown = bundle.getBoolean("add_fragment_shown");
        this.mUserProvidedAddress = (SubscriberInfo) bundle.getParcelable("user_provided_address");
        this.mSavingScreenShown = bundle.getBoolean("saving_dialog_fragment");
        if (this.mSavingScreenShown) {
            finish();
            return;
        }
        setAddressAvailability();
        if (bundle.containsKey("error_dialog")) {
            this.mErrorFragment = (CarrierBillingErrorDialog) this.mContext.restoreFragment(bundle, "error_dialog");
            this.mErrorFragment.setOnResultListener(this);
        }
        switch (AnonymousClass5.$SwitchMap$com$google$android$finsky$billing$carrierbilling$flow$CreateCarrierBillingFlow$State[this.mState.ordinal()]) {
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                if (bundle.containsKey("add_fragment")) {
                    this.mAddFragment = (AddCarrierBillingFragment) this.mContext.restoreFragment(bundle, "add_fragment");
                    this.mAddFragment.setOnResultListener(this);
                    return;
                }
                return;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                if (bundle.containsKey("edit_fragment")) {
                    this.mEditFragment = (EditCarrierBillingFragment) this.mContext.restoreFragment(bundle, "edit_fragment");
                    this.mEditFragment.setOnResultListener(this);
                    return;
                }
                return;
            default:
                if (this.mErrorFragment != null) {
                    cancel();
                    return;
                } else {
                    finish();
                    return;
                }
        }
    }

    public void saveState(Bundle bundle) {
        bundle.putString("state", this.mState.name());
        bundle.putBoolean("add_fragment_shown", this.mAddFragmentShown);
        bundle.putBoolean("saving_dialog_fragment", this.mSavingScreenShown);
        if (this.mErrorFragment != null) {
            this.mContext.persistFragment(bundle, "error_dialog", this.mErrorFragment);
        }
        if (this.mUserProvidedAddress != null) {
            bundle.putParcelable("user_provided_address", this.mUserProvidedAddress);
        }
        switch (AnonymousClass5.$SwitchMap$com$google$android$finsky$billing$carrierbilling$flow$CreateCarrierBillingFlow$State[this.mState.ordinal()]) {
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                if (this.mAddFragment != null) {
                    this.mContext.persistFragment(bundle, "add_fragment", this.mAddFragment);
                    return;
                }
                return;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                if (this.mEditFragment != null) {
                    this.mContext.persistFragment(bundle, "edit_fragment", this.mEditFragment);
                    return;
                }
                return;
            default:
                return;
        }
    }

    void performNext() {
        switch (AnonymousClass5.$SwitchMap$com$google$android$finsky$billing$carrierbilling$flow$CreateCarrierBillingFlow$State[this.mState.ordinal()]) {
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                if (this.mAddressAvailable == AddressAvailable.NO_ADDRESS) {
                    this.mState = State.SHOWING_EDIT_USERINFO;
                    showEditAddressFragment(null);
                    return;
                }
                this.mState = State.SHOWING_TOS_AND_USERINFO;
                showTosFragment();
                return;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                if (this.mAddResult == AddResult.EDIT_ADDRESS) {
                    this.mState = State.SHOWING_EDIT_USERINFO;
                    showEditAddressFragment(null);
                    return;
                }
                this.mState = State.SENDING_REQUEST;
                showProgress();
                performRequest();
                return;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                this.mState = State.SHOWING_TOS_AND_USERINFO;
                showTosFragment();
                return;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_required /*4*/:
                if (this.mUpdateInstrumentResponse.result == 0) {
                    this.mState = State.DONE;
                    finishWithUpdateInstrumentResponse(this.mUpdateInstrumentResponse);
                    return;
                } else if (this.mUpdateInstrumentResponse.checkoutTokenRequired) {
                    performRequest();
                    return;
                } else {
                    ArrayList<Integer> errorList = getRetriableErrorList();
                    if (errorList == null || errorList.isEmpty()) {
                        showGenericError("Could not add carrier billing instrument.", "UNKNOWN");
                        return;
                    }
                    this.mState = State.SHOWING_EDIT_USERINFO;
                    logError("INVALID_INPUT");
                    showEditAddressFragment(errorList);
                    return;
                }
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                BillingPreferences.ACCEPTED_CARRIER_TOS_VERSION.remove();
                if (shouldShowTos() || this.mAddressAvailable != AddressAvailable.NO_ADDRESS) {
                    this.mState = this.mAddressAvailable == AddressAvailable.NO_ADDRESS ? State.SHOWING_TOS : State.SHOWING_TOS_AND_USERINFO;
                    showTosFragment();
                    return;
                }
                this.mState = State.SHOWING_EDIT_USERINFO;
                showEditAddressFragment(null);
                return;
            default:
                showGenericError("Invalid Dcb state.", "UNKNOWN");
                return;
        }
    }

    private ArrayList<Integer> getInvalidEntries(InputValidationError[] inputErrors) {
        ArrayList<Integer> errors = new ArrayList();
        for (InputValidationError error : inputErrors) {
            int inputField = error.inputField;
            switch (inputField) {
                case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_required /*4*/:
                case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                case com.google.android.wallet.instrumentmanager.R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                case com.google.android.wallet.instrumentmanager.R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                case com.google.android.wallet.instrumentmanager.R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                case com.google.android.wallet.instrumentmanager.R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoTextColor /*9*/:
                case com.google.android.wallet.instrumentmanager.R.styleable.MapAttrs_useViewLifecycle /*13*/:
                    errors.add(Integer.valueOf(inputField));
                    break;
                default:
                    FinskyLog.d("InputValidationError that can't be edited: type=%d, message=%s", Integer.valueOf(error.inputField), error.errorMessage);
                    break;
            }
        }
        return errors;
    }

    private ArrayList<Integer> getRetriableErrorList() {
        if (2 == this.mUpdateInstrumentResponse.result) {
            return getInvalidEntries(this.mUpdateInstrumentResponse.errorInputField);
        }
        return null;
    }

    private boolean shouldShowTos() {
        if (!this.mParams.showCarrierTos()) {
            return false;
        }
        String tosVersion = (String) BillingPreferences.ACCEPTED_CARRIER_TOS_VERSION.get();
        if (tosVersion == null || !tosVersion.equals(this.mProvisioning.getTosVersion())) {
            return true;
        }
        return false;
    }

    private void showTosFragment() {
        Type type;
        if (!shouldShowTos()) {
            type = this.mAddressAvailable == AddressAvailable.FULL_ADDRESS ? Type.FULL_ADDRESS : Type.ADDRESS_SNIPPET;
        } else if (this.mAddressAvailable == AddressAvailable.NO_ADDRESS) {
            type = Type.TOS;
        } else {
            type = this.mAddressAvailable == AddressAvailable.FULL_ADDRESS ? Type.FULL_ADDRESS_AND_TOS : Type.ADDRESS_SNIPPET_AND_TOS;
        }
        showAddFragment(type, this.mUserProvidedAddress);
    }

    void showAddFragment(Type type, SubscriberInfo editedAddress) {
        boolean hasTos;
        boolean hasAddressSnippet;
        boolean hasFullAddress;
        if (type == Type.TOS || type == Type.ADDRESS_SNIPPET_AND_TOS || type == Type.FULL_ADDRESS_AND_TOS) {
            hasTos = true;
        } else {
            hasTos = false;
        }
        if (type == Type.ADDRESS_SNIPPET || type == Type.ADDRESS_SNIPPET_AND_TOS) {
            hasAddressSnippet = true;
        } else {
            hasAddressSnippet = false;
        }
        if (type == Type.FULL_ADDRESS || type == Type.FULL_ADDRESS_AND_TOS) {
            hasFullAddress = true;
        } else {
            hasFullAddress = false;
        }
        logTosAndAddress(hasTos, hasAddressSnippet, hasFullAddress);
        hideTosFragment();
        this.mTosVersion = this.mProvisioning.getTosVersion();
        this.mAddFragment = AddCarrierBillingFragment.newInstance(type, editedAddress, this.mProvisioning.getTosUrl(), this.mProvisioning.getAddressSnippet(), this.mParams.getName(), this.mAccountName, 0);
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

    private void showEditAddressFragment(ArrayList<Integer> errorList) {
        if (this.mAddressAvailable == AddressAvailable.FULL_ADDRESS) {
            showEditFragment(this.mUserProvidedAddress != null ? this.mUserProvidedAddress : this.mProvisioning.getSubscriberInfo(), errorList);
        } else {
            showEditFragment(null, null);
        }
    }

    void showEditFragment(SubscriberInfo prefillAddress, ArrayList<Integer> errorList) {
        hideTosFragment();
        logEditAddress(prefillAddress != null);
        this.mEditFragment = EditCarrierBillingFragment.newInstance(this.mAccountName, this.mAddressMode, prefillAddress, errorList, 0);
        this.mEditFragment.setOnResultListener(this);
        this.mContext.showFragment(this.mEditFragment, this.mTitle, false);
    }

    private void hideEditFragment() {
        if (this.mEditFragment != null) {
            this.mContext.hideFragment(this.mEditFragment, false);
            this.mEditFragment = null;
        }
    }

    private void showErrorDialog(String message) {
        this.mErrorFragment = CarrierBillingErrorDialog.newInstance(message, false);
        this.mErrorFragment.setOnResultListener(this);
        this.mContext.showDialogFragment(this.mErrorFragment, "error");
    }

    private void showError(String finskyLogString, String logString, String message) {
        FinskyLog.w(finskyLogString, new Object[0]);
        logError(logString);
        showErrorDialog(message);
    }

    private void showNetworkError(VolleyError error) {
        FinskyLog.w("Error received: %s", error);
        logError(error);
        showErrorDialog(ErrorStrings.get(FinskyApp.get(), error));
    }

    private void showGenericError(String finskyLogString, String logString) {
        showError(finskyLogString, logString, FinskyApp.get().getString(R.string.add_carrier_billing_error, new Object[]{this.mParams.getName()}));
    }

    private void showProgress() {
        this.mSavingScreenShown = true;
        this.mContext.showProgress(R.string.saving);
        if (this.mAddFragment != null) {
            this.mAddFragment.enableUi(false);
        }
    }

    private void hideProgress() {
        this.mSavingScreenShown = false;
        this.mContext.hideProgress();
        if (this.mAddFragment != null) {
            this.mAddFragment.enableUi(true);
        }
    }

    protected void performRequest() {
        UpdateInstrumentRequest request = new UpdateInstrumentRequest();
        request.instrument = createCarrierBillingInstrument();
        this.mDfeApi.updateInstrument(request, new Listener<UpdateInstrumentResponse>() {
            public void onResponse(UpdateInstrumentResponse response) {
                CreateCarrierBillingFlow.this.mUpdateInstrumentResponse = response;
                CreateCarrierBillingFlow.this.hideProgress();
                CreateCarrierBillingFlow.this.performNext();
            }
        }, new ErrorListener() {
            public void onErrorResponse(VolleyError error) {
                CreateCarrierBillingFlow.this.showNetworkError(error);
                CreateCarrierBillingFlow.this.hideProgress();
            }
        });
    }

    private Instrument createCarrierBillingInstrument() {
        Instrument instrument = new Instrument();
        CarrierBillingInstrument dcbInstrument = new CarrierBillingInstrument();
        dcbInstrument.instrumentKey = this.mStorage.getCurrentSimIdentifier();
        dcbInstrument.hasInstrumentKey = true;
        dcbInstrument.accountType = this.mProvisioning.getAccountType();
        dcbInstrument.hasAccountType = true;
        dcbInstrument.currencyCode = this.mProvisioning.getSubscriberCurrency();
        dcbInstrument.hasCurrencyCode = true;
        dcbInstrument.transactionLimit = this.mProvisioning.getTransactionLimit();
        dcbInstrument.hasTransactionLimit = true;
        if (this.mProvisioning.getSubscriberInfo() != null) {
            dcbInstrument.subscriberIdentifier = this.mProvisioning.getSubscriberInfo().getIdentifier();
            dcbInstrument.hasSubscriberIdentifier = true;
        }
        CarrierBillingCredentials credentials = new CarrierBillingCredentials();
        if (this.mStorage.getCredentials() != null) {
            credentials.value = this.mStorage.getCredentials().getCredentials();
            credentials.hasValue = true;
            credentials.expiration = this.mStorage.getCredentials().getExpirationTime();
            credentials.hasExpiration = true;
        }
        dcbInstrument.credentials = credentials;
        if (this.mUserProvidedAddress != null) {
            instrument.billingAddress = PhoneCarrierBillingUtils.subscriberInfoToAddress(this.mUserProvidedAddress, this.mAddressMode);
        } else if (this.mProvisioning.getSubscriberInfo() != null) {
            instrument.billingAddress = PhoneCarrierBillingUtils.subscriberInfoToAddress(this.mProvisioning.getSubscriberInfo(), this.mAddressMode);
        } else if (this.mProvisioning.getEncryptedSubscriberInfo() != null) {
            dcbInstrument.encryptedSubscriberInfo = this.mProvisioning.getEncryptedSubscriberInfo().toProto();
        }
        instrument.carrierBilling = dcbInstrument;
        return instrument;
    }

    private boolean isSnippetValid() {
        String snippet = this.mProvisioning.getAddressSnippet();
        return (TextUtils.isEmpty(snippet) || "null".equals(snippet)) ? false : true;
    }

    private void setAddressAvailability() {
        if (this.mUserProvidedAddress != null || isSubscriberInfoValid()) {
            this.mAddressAvailable = AddressAvailable.FULL_ADDRESS;
        } else if (isSnippetValid()) {
            this.mAddressAvailable = AddressAvailable.ADDRESS_SNIPPET;
        } else {
            this.mAddressAvailable = AddressAvailable.NO_ADDRESS;
        }
    }

    private void getProvisioning(Runnable onSuccess, Runnable onError) {
        new CarrierProvisioningAction().forceRun(onSuccess, onError, (String) BillingPreferences.ACCEPTED_CARRIER_TOS_VERSION.get());
    }

    public void onAddCarrierBillingResult(AddResult result) {
        this.mAddResult = result;
        if (result == AddResult.SUCCESS) {
            BillingPreferences.ACCEPTED_CARRIER_TOS_VERSION.put(this.mTosVersion);
            showProgress();
            getProvisioning(new AfterProvisioning(), new AfterError());
        } else if (result == AddResult.EDIT_ADDRESS) {
            performNext();
        } else if (result == AddResult.CANCELED) {
            cancel();
        } else {
            showGenericError("Invalid error code.", "UNKNOWN");
        }
    }

    private boolean isSubscriberInfoValid() {
        return (this.mProvisioning.getSubscriberInfo() == null || TextUtils.isEmpty(this.mProvisioning.getSubscriberInfo().getAddress1()) || TextUtils.isEmpty(this.mProvisioning.getSubscriberInfo().getName()) || TextUtils.isEmpty(this.mProvisioning.getSubscriberInfo().getCity())) ? false : true;
    }

    private void onEditSuccess(SubscriberInfo returnAddress) {
        this.mUserProvidedAddress = returnAddress;
        this.mAddressAvailable = AddressAvailable.FULL_ADDRESS;
        performNext();
    }

    private void onEditCancel() {
        if (this.mAddressAvailable == AddressAvailable.NO_ADDRESS) {
            cancel();
        } else {
            performNext();
        }
    }

    public void onEditCarrierBillingResult(SubscriberInfo returnAddress) {
        hideEditFragment();
        if (returnAddress != null) {
            onEditSuccess(returnAddress);
        } else {
            onEditCancel();
        }
    }

    public void onErrorResponse(VolleyError error) {
        showNetworkError(error);
    }

    public void onErrorDismiss(boolean fatal) {
        this.mState = State.DONE;
        cancel();
    }

    State getState() {
        return this.mState;
    }

    boolean isErrorFragmentShown() {
        return this.mErrorFragment != null;
    }

    void setAddResult(AddResult result) {
        this.mAddResult = result;
    }

    SubscriberInfo getUserProvidedAddress() {
        return this.mUserProvidedAddress;
    }

    protected void setUpdateResponse(UpdateInstrumentResponse response) {
        this.mUpdateInstrumentResponse = response;
    }
}
