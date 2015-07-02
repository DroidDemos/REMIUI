package com.google.android.finsky.billing.carrierbilling.flow;

import android.os.Bundle;
import android.text.TextUtils;
import com.android.vending.R;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.analytics.FinskyEventLog;
import com.google.android.finsky.api.DfeApi;
import com.google.android.finsky.api.utils.AndroidKeyczarReader;
import com.google.android.finsky.billing.BillingFlowContext;
import com.google.android.finsky.billing.BillingFlowListener;
import com.google.android.finsky.billing.BillingLocator;
import com.google.android.finsky.billing.InstrumentFlow;
import com.google.android.finsky.billing.carrierbilling.flow.association.AssociationAction;
import com.google.android.finsky.billing.carrierbilling.flow.association.CarrierOutAssociation;
import com.google.android.finsky.billing.carrierbilling.fragment.CarrierBillingPasswordDialogFragment;
import com.google.android.finsky.billing.carrierbilling.fragment.CarrierBillingPasswordDialogFragment.CarrierBillingPasswordResultListener;
import com.google.android.finsky.billing.carrierbilling.fragment.CarrierBillingPasswordDialogFragment.CarrierBillingPasswordResultListener.PasswordResult;
import com.google.android.finsky.billing.carrierbilling.fragment.CarrierTosDialogFragment;
import com.google.android.finsky.billing.carrierbilling.fragment.CarrierTosDialogFragment.CarrierTosResultListener;
import com.google.android.finsky.billing.carrierbilling.fragment.CarrierTosDialogFragment.CarrierTosResultListener.TosResult;
import com.google.android.finsky.billing.carrierbilling.fragment.VerifyAssociationDialogFragment;
import com.google.android.finsky.billing.carrierbilling.fragment.VerifyAssociationDialogFragment.VerifyAssociationListener;
import com.google.android.finsky.billing.carrierbilling.model.CarrierBillingStorage;
import com.google.android.finsky.config.G;
import com.google.android.finsky.protos.BuyInstruments.UpdateInstrumentRequest;
import com.google.android.finsky.protos.BuyInstruments.UpdateInstrumentResponse;
import com.google.android.finsky.protos.CarrierBilling.VerifyAssociationResponse;
import com.google.android.finsky.protos.CommonDevice.CarrierBillingInstrument;
import com.google.android.finsky.protos.CommonDevice.CarrierBillingInstrumentStatus;
import com.google.android.finsky.protos.CommonDevice.CarrierTos;
import com.google.android.finsky.protos.CommonDevice.CarrierTosEntry;
import com.google.android.finsky.protos.CommonDevice.Instrument;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.FixBrokenCipherSpiProvider;
import com.google.android.finsky.utils.ParcelableProto;
import java.io.File;
import org.json.JSONObject;
import org.keyczar.Encrypter;
import org.keyczar.SignedSessionEncrypter;
import org.keyczar.Signer;
import org.keyczar.util.Base64Coder;

public class CompleteDcb3Flow extends InstrumentFlow implements ErrorListener, Listener<VerifyAssociationResponse>, CarrierBillingPasswordResultListener, CarrierTosResultListener, VerifyAssociationListener {
    private static final String DCB_PIN_SIGNING_KEYS;
    private static final String GOOGLE_ENCRYPTION_KEYS_V1;
    private static final String GOOGLE_ENCRYPTION_KEYS_V2;
    private AssociationAction mAssociationAction;
    private String mAssociationAddress;
    private String mAssociationPrefix;
    private boolean mAssociationRequired;
    private String mCarrierName;
    private final BillingFlowContext mContext;
    private boolean mDcbTosAcceptanceRequired;
    private String mDcbTosUrl;
    private String mDcbTosVersion;
    private final DfeApi mDfeApi;
    private String mEncryptedPassword;
    private final FinskyEventLog mEventLog;
    private Instrument mInstrument;
    private boolean mInstrumentUpdateRequired;
    private String mPasswordForgotUrl;
    private CarrierBillingPasswordDialogFragment mPasswordFragment;
    private String mPasswordPrompt;
    private boolean mPasswordRequired;
    private State mState;
    private final CarrierBillingStorage mStorage;
    private CarrierTosDialogFragment mTosFragment;
    private int mTosNumber;
    private UpdateInstrumentResponse mUpdateInstrumentResponse;
    private VerifyAssociationDialogFragment mVerifyAssociationFragment;

    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$google$android$finsky$billing$carrierbilling$fragment$CarrierBillingPasswordDialogFragment$CarrierBillingPasswordResultListener$PasswordResult;
        static final /* synthetic */ int[] $SwitchMap$com$google$android$finsky$billing$carrierbilling$fragment$CarrierTosDialogFragment$CarrierTosResultListener$TosResult;

        static {
            $SwitchMap$com$google$android$finsky$billing$carrierbilling$fragment$CarrierTosDialogFragment$CarrierTosResultListener$TosResult = new int[TosResult.values().length];
            try {
                $SwitchMap$com$google$android$finsky$billing$carrierbilling$fragment$CarrierTosDialogFragment$CarrierTosResultListener$TosResult[TosResult.SUCCESS.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$google$android$finsky$billing$carrierbilling$fragment$CarrierTosDialogFragment$CarrierTosResultListener$TosResult[TosResult.FAILURE.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$google$android$finsky$billing$carrierbilling$fragment$CarrierTosDialogFragment$CarrierTosResultListener$TosResult[TosResult.CANCELED.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            $SwitchMap$com$google$android$finsky$billing$carrierbilling$fragment$CarrierBillingPasswordDialogFragment$CarrierBillingPasswordResultListener$PasswordResult = new int[PasswordResult.values().length];
            try {
                $SwitchMap$com$google$android$finsky$billing$carrierbilling$fragment$CarrierBillingPasswordDialogFragment$CarrierBillingPasswordResultListener$PasswordResult[PasswordResult.SUCCESS.ordinal()] = 1;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$com$google$android$finsky$billing$carrierbilling$fragment$CarrierBillingPasswordDialogFragment$CarrierBillingPasswordResultListener$PasswordResult[PasswordResult.CANCELED.ordinal()] = 2;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$com$google$android$finsky$billing$carrierbilling$fragment$CarrierBillingPasswordDialogFragment$CarrierBillingPasswordResultListener$PasswordResult[PasswordResult.FAILURE.ordinal()] = 3;
            } catch (NoSuchFieldError e6) {
            }
        }
    }

    public enum State {
        CHECK_INSTRUMENT_STATUS,
        CHECK_DCB_TOS_VERSION,
        PASSWORD_REQUEST,
        SENDING_REQUEST
    }

    static {
        FixBrokenCipherSpiProvider.insertIfNeeded();
        DCB_PIN_SIGNING_KEYS = "keys" + File.separator + "dcb-pin-sign";
        GOOGLE_ENCRYPTION_KEYS_V1 = "keys" + File.separator + "dcb-pin-encrypt-v1";
        GOOGLE_ENCRYPTION_KEYS_V2 = "keys" + File.separator + "dcb-pin-encrypt-v2";
    }

    public CompleteDcb3Flow(BillingFlowContext billingFlowContext, DfeApi dfeApi, BillingFlowListener listener, FinskyEventLog eventLog, Bundle parameters) {
        this(billingFlowContext, dfeApi, listener, BillingLocator.getCarrierBillingStorage(), eventLog, parameters);
    }

    CompleteDcb3Flow(BillingFlowContext billingFlowContext, DfeApi dfeApi, BillingFlowListener listener, CarrierBillingStorage dcbStorage, FinskyEventLog eventLog, Bundle parameters) {
        super(billingFlowContext, listener, parameters);
        this.mTosNumber = 0;
        this.mContext = billingFlowContext;
        this.mDfeApi = dfeApi;
        this.mStorage = dcbStorage;
        this.mEventLog = eventLog;
        this.mState = State.CHECK_INSTRUMENT_STATUS;
        if (parameters != null) {
            this.mInstrument = (Instrument) ParcelableProto.getProtoFromBundle(parameters, "dcb_instrument");
            if (this.mInstrument.carrierBillingStatus != null) {
                CarrierBillingInstrumentStatus status = this.mInstrument.carrierBillingStatus;
                this.mPasswordRequired = status.passwordRequired;
                if (status.carrierPasswordPrompt != null) {
                    this.mPasswordPrompt = status.carrierPasswordPrompt.prompt;
                    this.mPasswordForgotUrl = status.carrierPasswordPrompt.forgotPasswordUrl;
                }
                this.mAssociationRequired = status.associationRequired;
                if (status.deviceAssociation != null) {
                    this.mAssociationAddress = status.deviceAssociation.userTokenRequestAddress;
                    this.mAssociationPrefix = status.deviceAssociation.userTokenRequestMessage;
                }
                if (status.carrierTos != null) {
                    this.mDcbTosAcceptanceRequired = status.carrierTos.needsDcbTosAcceptance;
                    if (this.mDcbTosAcceptanceRequired) {
                        this.mDcbTosUrl = status.carrierTos.dcbTos.url;
                        this.mDcbTosVersion = status.carrierTos.dcbTos.version;
                    }
                }
                if (status.hasName) {
                    this.mCarrierName = status.name;
                }
                if (this.mDcbTosAcceptanceRequired && TextUtils.isEmpty(this.mCarrierName)) {
                    FinskyLog.wtf("Carrier name is empty. Can't proceed with the flow.", new Object[0]);
                    fail(FinskyApp.get().getString(R.string.generic_purchase_completion_error));
                }
            }
        }
    }

    public void start() {
        performNext();
    }

    protected void performNext() {
        if (this.mState == State.CHECK_INSTRUMENT_STATUS) {
            if (this.mAssociationRequired) {
                startAssociation();
                return;
            }
            this.mState = State.CHECK_DCB_TOS_VERSION;
            performNext();
        } else if (this.mState == State.CHECK_DCB_TOS_VERSION) {
            if (this.mDcbTosAcceptanceRequired) {
                createAndShowTosFragment(this.mDcbTosUrl);
            } else if (this.mInstrumentUpdateRequired) {
                this.mState = State.SENDING_REQUEST;
                updateCarrierBillingInstrument();
            } else if (this.mPasswordRequired) {
                this.mState = State.PASSWORD_REQUEST;
                createAndShowPasswordFragment();
            } else {
                finish();
            }
        } else if (this.mState == State.SENDING_REQUEST) {
            this.mContext.hideProgress();
            if (this.mUpdateInstrumentResponse == null) {
                FinskyLog.w("Failed to get update instrument response.", new Object[0]);
                fail(FinskyApp.get().getString(R.string.generic_purchase_completion_error));
            } else if (this.mUpdateInstrumentResponse.result == 0) {
                if (this.mPasswordRequired) {
                    this.mState = State.PASSWORD_REQUEST;
                    createAndShowPasswordFragment();
                    return;
                }
                finish();
            } else if (this.mUpdateInstrumentResponse.checkoutTokenRequired) {
                FinskyLog.wtf("Unexpected checkout_token_required.", new Object[0]);
                fail(FinskyApp.get().getString(R.string.generic_purchase_completion_error));
            } else {
                FinskyLog.w("Updating DCB instrument failed.", new Object[0]);
                fail(FinskyApp.get().getString(R.string.generic_purchase_completion_error));
            }
        } else if (this.mState == State.PASSWORD_REQUEST) {
            dissmissPasswordFragment();
            Bundle result = new Bundle();
            result.putString("encrypted_passphrase", this.mEncryptedPassword);
            finish(result);
        } else {
            throw new IllegalStateException("Unexpected state: " + this.mState);
        }
    }

    private void dissmissPasswordFragment() {
        if (this.mPasswordFragment != null) {
            this.mPasswordFragment.dismiss();
            this.mPasswordFragment = null;
        }
    }

    void updateCarrierBillingInstrument() {
        this.mContext.showProgress(R.string.saving);
        CarrierBillingInstrument dcbInstrument = new CarrierBillingInstrument();
        dcbInstrument.instrumentKey = this.mStorage.getCurrentSimIdentifier();
        dcbInstrument.hasInstrumentKey = true;
        if (!TextUtils.isEmpty(this.mDcbTosVersion)) {
            CarrierTos carrierTos = new CarrierTos();
            carrierTos.dcbTos = new CarrierTosEntry();
            carrierTos.dcbTos.version = this.mDcbTosVersion;
            carrierTos.dcbTos.hasVersion = true;
            dcbInstrument.acceptedCarrierTos = carrierTos;
        }
        UpdateInstrumentRequest request = new UpdateInstrumentRequest();
        request.instrument = this.mInstrument;
        request.instrument.carrierBilling = dcbInstrument;
        this.mDfeApi.updateInstrument(request, new Listener<UpdateInstrumentResponse>() {
            public void onResponse(UpdateInstrumentResponse response) {
                CompleteDcb3Flow.this.mUpdateInstrumentResponse = response;
                CompleteDcb3Flow.this.performNext();
            }
        }, this);
    }

    public void resumeFromSavedState(Bundle bundle) {
        if (this.mState != State.CHECK_INSTRUMENT_STATUS) {
            throw new IllegalStateException();
        }
        this.mState = State.valueOf(bundle.getString("state"));
        if (this.mState == State.SENDING_REQUEST) {
            if (this.mPasswordRequired) {
                this.mState = State.PASSWORD_REQUEST;
                createAndShowPasswordFragment();
            } else {
                finish();
            }
        }
        this.mDcbTosAcceptanceRequired = bundle.getBoolean("tos_required");
        if (bundle.containsKey("tos_version")) {
            this.mDcbTosVersion = bundle.getString("tos_version");
        }
        if (bundle.containsKey("tos_url")) {
            this.mDcbTosUrl = bundle.getString("tos_url");
        }
        if (bundle.containsKey("tos_fragment")) {
            this.mTosFragment = (CarrierTosDialogFragment) this.mContext.restoreFragment(bundle, "tos_fragment");
            this.mTosFragment.setOnResultListener(this);
        }
        if (bundle.containsKey("password_fragment")) {
            this.mPasswordFragment = (CarrierBillingPasswordDialogFragment) this.mContext.restoreFragment(bundle, "password_fragment");
            this.mPasswordFragment.setOnResultListener(this);
        }
        if (bundle.containsKey("verify_association_dialog")) {
            this.mVerifyAssociationFragment = (VerifyAssociationDialogFragment) this.mContext.restoreFragment(bundle, "verify_association_dialog");
            this.mVerifyAssociationFragment.setOnResultListener(this);
            this.mAssociationAction = new CarrierOutAssociation(this.mDfeApi, this.mAssociationAddress, this.mAssociationPrefix, null, false);
            this.mAssociationAction.resumeState(bundle, this, this);
        }
    }

    public void saveState(Bundle bundle) {
        bundle.putString("state", this.mState.name());
        if (this.mTosFragment != null) {
            this.mContext.persistFragment(bundle, "tos_fragment", this.mTosFragment);
        }
        if (this.mPasswordFragment != null) {
            this.mContext.persistFragment(bundle, "password_fragment", this.mPasswordFragment);
        }
        if (this.mVerifyAssociationFragment != null) {
            this.mContext.persistFragment(bundle, "verify_association_dialog", this.mVerifyAssociationFragment);
            if (this.mAssociationAction != null) {
                this.mAssociationAction.saveState(bundle);
                this.mAssociationAction.cancel();
            }
        }
        bundle.putBoolean("tos_required", this.mDcbTosAcceptanceRequired);
        if (this.mDcbTosAcceptanceRequired) {
            bundle.putString("tos_version", this.mDcbTosVersion);
            bundle.putString("tos_url", this.mDcbTosUrl);
        }
    }

    void createAndShowPasswordFragment() {
        this.mPasswordFragment = CarrierBillingPasswordDialogFragment.newInstance(this.mDfeApi.getAccountName(), this.mPasswordPrompt, this.mPasswordForgotUrl);
        this.mPasswordFragment.setOnResultListener(this);
        this.mBillingFlowContext.showDialogFragment(this.mPasswordFragment, "PasswordDialog");
    }

    void createAndShowTosFragment(String tosUrl) {
        this.mTosFragment = CarrierTosDialogFragment.newInstance(this.mDfeApi.getAccountName(), tosUrl, this.mCarrierName);
        this.mTosFragment.setOnResultListener(this);
        this.mBillingFlowContext.showDialogFragment(this.mTosFragment, "TosDialog" + this.mTosNumber);
        this.mTosNumber++;
    }

    private void startAssociation() {
        showVerifyAssociationDialog();
        this.mAssociationAction = new CarrierOutAssociation(this.mDfeApi, this.mAssociationAddress, this.mAssociationPrefix, null, false);
        this.mAssociationAction.start(this, this);
    }

    private void showVerifyAssociationDialog() {
        this.mVerifyAssociationFragment = VerifyAssociationDialogFragment.newInstance(this.mDfeApi.getAccountName());
        this.mVerifyAssociationFragment.setOnResultListener(this);
        this.mContext.showDialogFragment(this.mVerifyAssociationFragment, "verifying pin");
    }

    private void hideVerifyAssociationDialog() {
        if (this.mVerifyAssociationFragment != null) {
            this.mVerifyAssociationFragment.dismiss();
            this.mVerifyAssociationFragment = null;
        }
    }

    public void onCarrierBillingPasswordResult(PasswordResult result, String password) {
        if (!result.equals(PasswordResult.SUCCESS)) {
            dissmissPasswordFragment();
        }
        switch (AnonymousClass2.$SwitchMap$com$google$android$finsky$billing$carrierbilling$fragment$CarrierBillingPasswordDialogFragment$CarrierBillingPasswordResultListener$PasswordResult[result.ordinal()]) {
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                try {
                    this.mEncryptedPassword = encryptPassword(password);
                } catch (Exception e) {
                    FinskyLog.w("Exception thrown: %s", e);
                }
                if (TextUtils.isEmpty(this.mEncryptedPassword)) {
                    fail(FinskyApp.get().getString(R.string.generic_purchase_completion_error));
                }
                performNext();
                return;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                cancel();
                return;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                FinskyLog.d("Getting password info failed.", new Object[0]);
                fail(FinskyApp.get().getString(R.string.generic_purchase_completion_error));
                return;
            default:
                FinskyLog.wtf("enum %s", result);
                return;
        }
    }

    public void onCarrierTosResult(TosResult result) {
        if (this.mTosFragment != null) {
            this.mTosFragment.dismiss();
            this.mTosFragment = null;
        }
        switch (AnonymousClass2.$SwitchMap$com$google$android$finsky$billing$carrierbilling$fragment$CarrierTosDialogFragment$CarrierTosResultListener$TosResult[result.ordinal()]) {
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                setAcceptedTos();
                performNext();
                return;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                FinskyLog.d("Showing TOS to user failed.", new Object[0]);
                fail(FinskyApp.get().getString(R.string.generic_purchase_completion_error));
                return;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                cancel();
                return;
            default:
                FinskyLog.wtf("enum %s", result);
                return;
        }
    }

    public void onVerifyCancel() {
        if (this.mAssociationAction != null) {
            this.mAssociationAction.cancel();
        }
        cancel();
    }

    private void setAcceptedTos() {
        this.mInstrumentUpdateRequired = true;
        this.mDcbTosAcceptanceRequired = false;
    }

    public void onErrorResponse(VolleyError error) {
        dissmissPasswordFragment();
        if (this.mAssociationAction != null) {
            this.mAssociationAction.cancel();
        }
        hideVerifyAssociationDialog();
        fail(FinskyApp.get().getString(R.string.generic_purchase_completion_error));
    }

    public void onResponse(VerifyAssociationResponse response) {
        hideVerifyAssociationDialog();
        if (response.status == 1) {
            this.mAssociationRequired = false;
            this.mInstrumentUpdateRequired = true;
            if (response.carrierTos != null && response.carrierTos.needsDcbTosAcceptance) {
                this.mDcbTosAcceptanceRequired = true;
                this.mDcbTosUrl = response.carrierTos.dcbTos.url;
                this.mDcbTosVersion = response.carrierTos.dcbTos.version;
            }
            performNext();
            return;
        }
        String error;
        if (response.status == 3) {
            error = FinskyApp.get().getString(R.string.associating_device_timeout_message);
        } else if (response.status != 2) {
            error = FinskyApp.get().getString(R.string.generic_purchase_completion_error);
        } else if (TextUtils.isEmpty(response.carrierErrorMessage)) {
            error = FinskyApp.get().getString(R.string.device_association_failed);
        } else {
            error = response.carrierErrorMessage;
        }
        fail(error);
    }

    void setState(State state) {
        this.mState = state;
    }

    State getState() {
        return this.mState;
    }

    private String encryptPassword(String password) throws Exception {
        String path;
        switch (((Integer) G.dcb3PassphraseKeyVersion.get()).intValue()) {
            case -1:
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                break;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                path = GOOGLE_ENCRYPTION_KEYS_V1;
                break;
            default:
                FinskyLog.d("Unrecognized passphrase key version %d, using default", Integer.valueOf(((Integer) G.dcb3PassphraseKeyVersion.get()).intValue()));
                break;
        }
        path = GOOGLE_ENCRYPTION_KEYS_V2;
        FinskyLog.d("Using passphrase key path: %s", path);
        SignedSessionEncrypter signedSessionEncrypter = new SignedSessionEncrypter(new Encrypter(new AndroidKeyczarReader(FinskyApp.get().getResources(), path)), new Signer(new AndroidKeyczarReader(FinskyApp.get().getResources(), DCB_PIN_SIGNING_KEYS)));
        String efeData = signedSessionEncrypter.newSession();
        byte[] fieldData = signedSessionEncrypter.encrypt(password.getBytes());
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("efeData", efeData);
        jsonObject.put("fieldData", Base64Coder.encodeWebSafe(fieldData));
        return jsonObject.toString();
    }
}
