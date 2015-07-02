package com.google.android.finsky.billing.carrierbilling.flow;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;
import com.android.vending.R;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.analytics.FinskyEventLog;
import com.google.android.finsky.billing.BillingFlow;
import com.google.android.finsky.billing.BillingFlowContext;
import com.google.android.finsky.billing.BillingFlowListener;
import com.google.android.finsky.billing.BillingLocator;
import com.google.android.finsky.billing.BillingPreferences;
import com.google.android.finsky.billing.carrierbilling.action.CarrierCredentialsAction;
import com.google.android.finsky.billing.carrierbilling.action.CarrierProvisioningAction;
import com.google.android.finsky.billing.carrierbilling.fragment.CarrierBillingPasswordDialogFragment;
import com.google.android.finsky.billing.carrierbilling.fragment.CarrierBillingPasswordDialogFragment.CarrierBillingPasswordResultListener;
import com.google.android.finsky.billing.carrierbilling.fragment.CarrierBillingPasswordDialogFragment.CarrierBillingPasswordResultListener.PasswordResult;
import com.google.android.finsky.billing.carrierbilling.fragment.CarrierTosDialogFragment;
import com.google.android.finsky.billing.carrierbilling.fragment.CarrierTosDialogFragment.CarrierTosResultListener;
import com.google.android.finsky.billing.carrierbilling.fragment.CarrierTosDialogFragment.CarrierTosResultListener.TosResult;
import com.google.android.finsky.billing.carrierbilling.model.CarrierBillingCredentials;
import com.google.android.finsky.billing.carrierbilling.model.CarrierBillingParameters;
import com.google.android.finsky.billing.carrierbilling.model.CarrierBillingProvisioning;
import com.google.android.finsky.billing.carrierbilling.model.CarrierBillingStorage;
import com.google.android.finsky.config.G;
import com.google.android.finsky.utils.FinskyLog;

public class CompleteCarrierBillingFlow extends BillingFlow implements CarrierBillingPasswordResultListener, CarrierTosResultListener {
    private final String mAccountName;
    private final BillingFlowContext mContext;
    private CarrierCredentialsAction mCredentialsAction;
    private boolean mCredentialsCheckPerformed;
    private final FinskyEventLog mEventLog;
    private CarrierBillingParameters mParams;
    private String mPassword;
    private CarrierBillingPasswordDialogFragment mPasswordFragment;
    private CarrierBillingProvisioning mProvisioning;
    private CarrierProvisioningAction mProvisioningAction;
    private State mState;
    private final CarrierBillingStorage mStorage;
    private CarrierTosDialogFragment mTosFragment;
    private int mTosNumber;
    private String mTosVersion;

    static /* synthetic */ class AnonymousClass1 {
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

    private class AfterCredentials implements Runnable {
        private AfterCredentials() {
        }

        public void run() {
            if (CompleteCarrierBillingFlow.this.mPasswordFragment != null) {
                CompleteCarrierBillingFlow.this.mPasswordFragment.hideProgressIndicator();
            }
            CompleteCarrierBillingFlow.this.mCredentialsCheckPerformed = true;
            CompleteCarrierBillingFlow.this.performNext();
        }
    }

    private class AfterError implements Runnable {
        private AfterError() {
        }

        public void run() {
            if (CompleteCarrierBillingFlow.this.mPasswordFragment != null) {
                CompleteCarrierBillingFlow.this.mPasswordFragment.dismiss();
            }
            FinskyLog.d("Fetching info from carrier failed", new Object[0]);
            CompleteCarrierBillingFlow.this.fail(FinskyApp.get().getString(R.string.generic_purchase_completion_error));
        }
    }

    private class AfterProvisioning implements Runnable {
        private AfterProvisioning() {
        }

        public void run() {
            if (CompleteCarrierBillingFlow.this.mPasswordFragment != null) {
                CompleteCarrierBillingFlow.this.mPasswordFragment.hideProgressIndicator();
            }
            CompleteCarrierBillingFlow.this.mProvisioning = CompleteCarrierBillingFlow.this.mStorage.getProvisioning();
            CompleteCarrierBillingFlow.this.performNext();
        }
    }

    public enum State {
        CHECK_CARRIER_TOS_VERSION,
        CHECK_VALID_CREDENTIALS,
        CHECK_VALID_PASSWORD,
        CARRIER_CREDENTIALS_REQUEST,
        PASSWORD_REQUEST
    }

    public CompleteCarrierBillingFlow(String accountName, BillingFlowContext billingFlowContext, BillingFlowListener listener, FinskyEventLog eventLog, Bundle parameters) {
        this(accountName, billingFlowContext, listener, BillingLocator.getCarrierBillingStorage(), eventLog, parameters);
        this.mState = State.CHECK_CARRIER_TOS_VERSION;
        this.mCredentialsCheckPerformed = false;
        this.mProvisioningAction = new CarrierProvisioningAction();
    }

    CompleteCarrierBillingFlow(String accountName, BillingFlowContext billingFlowContext, BillingFlowListener listener, CarrierBillingStorage dcbStorage, FinskyEventLog eventLog, Bundle parameters) {
        super(billingFlowContext, listener, parameters);
        this.mTosNumber = 0;
        this.mAccountName = accountName;
        this.mContext = billingFlowContext;
        this.mStorage = dcbStorage;
        this.mParams = this.mStorage.getParams();
        this.mProvisioning = this.mStorage.getProvisioning();
        this.mEventLog = eventLog;
        this.mCredentialsAction = new CarrierCredentialsAction(dcbStorage);
    }

    public void start() {
        if (this.mParams == null || this.mProvisioning == null) {
            FinskyLog.d("Cannot run this BillingFlow since params or provisioning are null.", new Object[0]);
            fail(FinskyApp.get().getString(R.string.generic_purchase_completion_error));
            return;
        }
        performNext();
    }

    public void resumeFromSavedState(Bundle bundle) {
        if (this.mState != State.CHECK_CARRIER_TOS_VERSION) {
            throw new IllegalStateException();
        }
        this.mState = State.valueOf(bundle.getString("state"));
        if (this.mParams == null || this.mProvisioning == null) {
            FinskyLog.d("Cannot run this BillingFlow since params or provisioning are null.", new Object[0]);
            fail(FinskyApp.get().getString(R.string.generic_purchase_completion_error));
        }
        if (bundle.containsKey("tos_version")) {
            this.mTosVersion = bundle.getString("tos_version");
        }
        if (bundle.containsKey("tos_fragment")) {
            this.mTosFragment = (CarrierTosDialogFragment) this.mContext.restoreFragment(bundle, "tos_fragment");
            this.mTosFragment.setOnResultListener(this);
        }
        if (bundle.containsKey("password_fragment")) {
            this.mPasswordFragment = (CarrierBillingPasswordDialogFragment) this.mContext.restoreFragment(bundle, "password_fragment");
            this.mPasswordFragment.setOnResultListener(this);
        }
    }

    public void saveState(Bundle bundle) {
        bundle.putString("state", this.mState.name());
        if (this.mTosVersion != null) {
            bundle.putString("tos_version", this.mTosVersion);
        }
        if (this.mTosFragment != null) {
            this.mContext.persistFragment(bundle, "tos_fragment", this.mTosFragment);
        }
        if (this.mPasswordFragment != null) {
            this.mContext.persistFragment(bundle, "password_fragment", this.mPasswordFragment);
        }
    }

    protected void performNext() {
        if (this.mState == State.CHECK_CARRIER_TOS_VERSION) {
            if (this.mParams.showCarrierTos()) {
                String acceptedTosVersion = (String) BillingPreferences.ACCEPTED_CARRIER_TOS_VERSION.get();
                String currentTosVersion = this.mProvisioning.getTosVersion();
                if (acceptedTosVersion == null || !acceptedTosVersion.equals(currentTosVersion)) {
                    createAndShowTosFragment();
                    return;
                }
                this.mState = State.CHECK_VALID_CREDENTIALS;
                performNext();
                return;
            }
            this.mState = State.CHECK_VALID_CREDENTIALS;
            performNext();
        } else if (this.mState == State.CHECK_VALID_CREDENTIALS) {
            if (this.mProvisioning.isPasswordRequired()) {
                this.mState = State.PASSWORD_REQUEST;
                createAndShowPasswordFragment();
                return;
            }
            credentials = null;
            if (!this.mParams.perTransactionCredentialsRequired() || this.mCredentialsCheckPerformed) {
                credentials = this.mStorage.getCredentials();
            }
            if (credentialsStillValid(credentials)) {
                finish();
            } else if (this.mCredentialsCheckPerformed) {
                FinskyLog.d("Credentials already fetched once and still not valid.", new Object[0]);
                fail(FinskyApp.get().getString(R.string.generic_purchase_completion_error));
            } else {
                this.mState = State.CARRIER_CREDENTIALS_REQUEST;
                this.mCredentialsAction.run(this.mProvisioning.getProvisioningId(), null, new AfterCredentials(), new AfterError());
                if (this.mPasswordFragment != null) {
                    this.mPasswordFragment.showProgressIndicator();
                }
            }
        } else if (this.mState == State.CHECK_VALID_PASSWORD) {
            credentials = this.mStorage.getCredentials();
            Boolean invalidPassword = Boolean.valueOf(credentials.invalidPassword());
            if (invalidPassword == null || !invalidPassword.booleanValue()) {
                if (this.mPasswordFragment != null) {
                    this.mPasswordFragment.dismiss();
                }
                if (credentialsStillValid(credentials)) {
                    finish();
                    return;
                }
                FinskyLog.d("Valid password, but invalid credentials.", new Object[0]);
                fail(FinskyApp.get().getString(R.string.generic_purchase_completion_error));
                return;
            }
            Toast.makeText(FinskyApp.get(), R.string.invalid_password, 1).show();
            if (this.mPasswordFragment != null) {
                this.mPasswordFragment.clearPasswordField();
            }
            this.mState = State.PASSWORD_REQUEST;
        } else if (this.mState == State.CARRIER_CREDENTIALS_REQUEST) {
            credentials = this.mStorage.getCredentials();
            this.mState = State.CHECK_VALID_CREDENTIALS;
            if (credentials.isProvisioned()) {
                performNext();
                return;
            }
            this.mProvisioningAction.forceRun(new AfterProvisioning(), new AfterError(), (String) BillingPreferences.ACCEPTED_CARRIER_TOS_VERSION.get());
            if (this.mPasswordFragment != null) {
                this.mPasswordFragment.showProgressIndicator();
            }
        } else if (this.mState == State.PASSWORD_REQUEST) {
            this.mState = State.CHECK_VALID_PASSWORD;
            this.mCredentialsAction.run(this.mProvisioning.getProvisioningId(), this.mPassword, new AfterCredentials(), new AfterError());
            if (this.mPasswordFragment != null) {
                this.mPasswordFragment.showProgressIndicator();
            }
        } else {
            throw new IllegalStateException("Unexpected state: " + this.mState);
        }
    }

    void createAndShowPasswordFragment() {
        this.mPasswordFragment = CarrierBillingPasswordDialogFragment.newInstance(this.mAccountName, this.mProvisioning.getPasswordPrompt(), this.mProvisioning.getPasswordForgotUrl());
        this.mPasswordFragment.setOnResultListener(this);
        this.mBillingFlowContext.showDialogFragment(this.mPasswordFragment, "PasswordDialog");
    }

    void createAndShowTosFragment() {
        this.mTosVersion = this.mProvisioning.getTosVersion();
        this.mTosFragment = CarrierTosDialogFragment.newInstance(this.mAccountName, this.mProvisioning.getTosUrl(), this.mParams.getName());
        this.mTosFragment.setOnResultListener(this);
        this.mBillingFlowContext.showDialogFragment(this.mTosFragment, "TosDialog" + this.mTosNumber);
        this.mTosNumber++;
    }

    boolean credentialsStillValid(CarrierBillingCredentials credentials) {
        if (credentials == null || !credentials.isProvisioned() || TextUtils.isEmpty(credentials.getCredentials())) {
            return false;
        }
        return credentialTimeStillValid(credentials.getExpirationTime(), ((Long) G.vendingCarrierCredentialsBufferMs.get()).longValue(), System.currentTimeMillis());
    }

    boolean credentialTimeStillValid(long expirationTime, long buffer, long now) {
        return expirationTime - buffer > now;
    }

    public void onCarrierBillingPasswordResult(PasswordResult result, String password) {
        if (!(result.equals(PasswordResult.SUCCESS) || this.mPasswordFragment == null)) {
            this.mPasswordFragment.dismiss();
            this.mPasswordFragment = null;
        }
        switch (AnonymousClass1.$SwitchMap$com$google$android$finsky$billing$carrierbilling$fragment$CarrierBillingPasswordDialogFragment$CarrierBillingPasswordResultListener$PasswordResult[result.ordinal()]) {
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                this.mPassword = password;
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
        this.mTosFragment.dismiss();
        this.mTosFragment = null;
        switch (AnonymousClass1.$SwitchMap$com$google$android$finsky$billing$carrierbilling$fragment$CarrierTosDialogFragment$CarrierTosResultListener$TosResult[result.ordinal()]) {
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                BillingPreferences.ACCEPTED_CARRIER_TOS_VERSION.put(this.mTosVersion);
                this.mProvisioningAction.forceRun(new AfterProvisioning(), new AfterError(), (String) BillingPreferences.ACCEPTED_CARRIER_TOS_VERSION.get());
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

    State getState() {
        return this.mState;
    }

    void setState(State state) {
        this.mState = state;
    }

    void setCredentialsCheckPerformed(boolean credentialsCheckPerformed) {
        this.mCredentialsCheckPerformed = credentialsCheckPerformed;
    }

    void setProvisioningAction(CarrierProvisioningAction provisioningAction) {
        this.mProvisioningAction = provisioningAction;
    }

    void setCredentialsAction(CarrierCredentialsAction credentialsAction) {
        this.mCredentialsAction = credentialsAction;
    }
}
