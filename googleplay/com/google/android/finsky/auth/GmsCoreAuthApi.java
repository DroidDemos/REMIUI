package com.google.android.finsky.auth;

import android.os.AsyncTask;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.utils.Utils;
import com.google.android.gms.auth.firstparty.dataservice.ConfirmCredentialsRequest;
import com.google.android.gms.auth.firstparty.dataservice.GoogleAccountDataServiceClient;
import com.google.android.gms.auth.firstparty.dataservice.TokenResponse;
import com.google.android.gms.auth.firstparty.dataservice.VerifyPinRequest;
import com.google.android.gms.auth.firstparty.dataservice.VerifyPinResponse;
import com.google.android.gms.auth.firstparty.shared.AccountCredentials;
import com.google.android.gms.auth.firstparty.shared.Status;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.wallet.instrumentmanager.R;

public class GmsCoreAuthApi {

    static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] $SwitchMap$com$google$android$gms$auth$firstparty$shared$Status;

        static {
            $SwitchMap$com$google$android$gms$auth$firstparty$shared$Status = new int[Status.values().length];
            try {
                $SwitchMap$com$google$android$gms$auth$firstparty$shared$Status[Status.SUCCESS.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$google$android$gms$auth$firstparty$shared$Status[Status.NEEDS_BROWSER.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$google$android$gms$auth$firstparty$shared$Status[Status.BAD_PASSWORD.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$google$android$gms$auth$firstparty$shared$Status[Status.BAD_AUTHENTICATION.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$com$google$android$gms$auth$firstparty$shared$Status[Status.NEEDS_2F.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$com$google$android$gms$auth$firstparty$shared$Status[Status.CAPTCHA.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                $SwitchMap$com$google$android$gms$auth$firstparty$shared$Status[Status.NETWORK_ERROR.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                $SwitchMap$com$google$android$gms$auth$firstparty$shared$Status[Status.UNKNOWN_ERROR.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
            try {
                $SwitchMap$com$google$android$gms$auth$firstparty$shared$Status[Status.UNKNOWN.ordinal()] = 9;
            } catch (NoSuchFieldError e9) {
            }
        }
    }

    public static boolean isAvailable() {
        if (FinskyApp.get().getExperiments().isEnabled("cl:auth.use_gms_core_based_auth") && GooglePlayServicesUtil.isGooglePlayServicesAvailable(FinskyApp.get()) == 0) {
            return true;
        }
        return false;
    }

    public void validateUserPassword(final String accountName, final String gaiaPassword, final AuthResponseListener listener) {
        Utils.executeMultiThreaded(new AsyncTask<Void, Void, TokenResponse>() {
            protected TokenResponse doInBackground(Void... voids) {
                return new GoogleAccountDataServiceClient(FinskyApp.get()).confirmCredentials(new ConfirmCredentialsRequest().setAccountCredentials(new AccountCredentials().setAccountName(accountName).setPassword(gaiaPassword)));
            }

            protected void onPostExecute(TokenResponse response) {
                GmsCoreAuthApi.this.handleVerifyPasswordResponse(listener, response);
            }
        }, new Void[0]);
    }

    public void validateUserPin(final String accountName, final String gaiaPin, final AuthResponseListener listener) {
        Utils.executeMultiThreaded(new AsyncTask<Void, Void, VerifyPinResponse>() {
            protected VerifyPinResponse doInBackground(Void... voids) {
                return new GoogleAccountDataServiceClient(FinskyApp.get()).verifyPin(new VerifyPinRequest(accountName, gaiaPin));
            }

            protected void onPostExecute(VerifyPinResponse response) {
                GmsCoreAuthApi.this.handleVerifyPinResponse(listener, response);
            }
        }, new Void[0]);
    }

    private void handleVerifyPasswordResponse(AuthResponseListener authResponseListener, TokenResponse response) {
        int errorType;
        switch (AnonymousClass3.$SwitchMap$com$google$android$gms$auth$firstparty$shared$Status[(response == null ? Status.UNKNOWN_ERROR : response.getStatus()).ordinal()]) {
            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                errorType = 0;
                break;
            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                errorType = 6;
                break;
            case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
            case R.styleable.WalletImFormEditText_required /*4*/:
                errorType = 4;
                break;
            case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                errorType = 2;
                break;
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                errorType = 3;
                break;
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                errorType = 5;
                break;
            default:
                errorType = 1;
                break;
        }
        if (errorType != 0) {
            authResponseListener.onAuthFailure(errorType);
        } else {
            authResponseListener.onAuthSuccess();
        }
    }

    private void handleVerifyPinResponse(AuthResponseListener authResponseListener, VerifyPinResponse response) {
        int errorType;
        switch (response == null ? 1 : response.status) {
            case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                errorType = 0;
                break;
            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                errorType = 5;
                break;
            case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                errorType = 4;
                break;
            default:
                errorType = 1;
                break;
        }
        if (errorType != 0) {
            authResponseListener.onAuthFailure(errorType);
        } else {
            authResponseListener.onAuthSuccess();
        }
    }
}
