package com.google.android.finsky.fragments;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.accounts.AuthenticatorException;
import android.accounts.OperationCanceledException;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import com.android.vending.R;
import com.android.volley.Request;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.activities.SimpleAlertDialog.Builder;
import com.google.android.finsky.analytics.BackgroundEventBuilder;
import com.google.android.finsky.analytics.FinskyEventLog;
import com.google.android.finsky.analytics.PlayStore.AuthContext;
import com.google.android.finsky.analytics.PlayStore.PlayStoreUiElementInfo;
import com.google.android.finsky.api.AccountHandler;
import com.google.android.finsky.auth.AuthResponseListener;
import com.google.android.finsky.auth.GmsCoreAuthApi;
import com.google.android.finsky.billing.challenge.ClientLoginApi;
import com.google.android.finsky.config.G;
import com.google.android.finsky.layout.play.GenericUiElementNode;
import com.google.android.finsky.layout.play.PlayStoreUiElementNode;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.UiUtils;
import java.io.IOException;

public class GaiaAuthFragment extends Fragment implements OnClickListener, com.google.android.finsky.activities.SimpleAlertDialog.Listener, AuthResponseListener {
    private String mAccountName;
    private Request<?> mAuthRequest;
    private FinskyEventLog mEventLogger;
    private int mFailedCount;
    private EditText mInput;
    private Listener mListener;
    private PlayStoreUiElementNode mNode;
    private boolean mShowWarning;
    private boolean mUseGmsCoreForAuth;
    private boolean mUsePinBasedAuth;

    public interface Listener {
        void onCancel();

        void onSuccess(int i, int i2);
    }

    public GaiaAuthFragment() {
        this.mAuthRequest = null;
        this.mNode = new GenericUiElementNode(314, null, null, null);
    }

    public static GaiaAuthFragment newInstance(String account, boolean showWarning, boolean useGmsCoreForAuth, boolean usePinBasedAuth) {
        Bundle arguments = new Bundle();
        arguments.putString("authAccount", account);
        arguments.putBoolean("GaiaAuthFragment_useGmsCoreForAuth", useGmsCoreForAuth);
        arguments.putBoolean("GaiaAuthFragment_usePinBasedAuth", usePinBasedAuth);
        arguments.putBoolean("GaiaAuthFragment_showWarning", showWarning);
        GaiaAuthFragment fragment = new GaiaAuthFragment();
        fragment.setArguments(arguments);
        return fragment;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mAccountName = getArguments().getString("authAccount");
        this.mUseGmsCoreForAuth = getArguments().getBoolean("GaiaAuthFragment_useGmsCoreForAuth", false);
        this.mUsePinBasedAuth = getArguments().getBoolean("GaiaAuthFragment_usePinBasedAuth", false);
        this.mShowWarning = getArguments().getBoolean("GaiaAuthFragment_showWarning");
        if (savedInstanceState != null) {
            this.mFailedCount = savedInstanceState.getInt("GaiaAuthFragment_retryCount");
        }
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("GaiaAuthFragment_retryCount", this.mFailedCount);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View result = inflater.inflate(R.layout.gaia_auth_fragment, null);
        this.mEventLogger = FinskyApp.get().getEventLogger(this.mAccountName);
        if (savedInstanceState == null) {
            this.mNode.getPlayStoreUiElement().clientLogsCookie = new PlayStoreUiElementInfo();
            this.mNode.getPlayStoreUiElement().clientLogsCookie.authContext = getAuthContext();
            this.mEventLogger.logPathImpression(0, this.mNode);
        }
        ((TextView) result.findViewById(R.id.challenge_description)).setText(this.mAccountName);
        this.mInput = (EditText) result.findViewById(R.id.authentication_gaia_input);
        if (this.mUsePinBasedAuth) {
            this.mInput.setHint(R.string.google_pin_hint);
            this.mInput.setContentDescription(getResources().getString(R.string.google_pin_hint));
            this.mInput.setInputType(18);
        } else {
            this.mInput.setHint(R.string.google_password_hint);
            this.mInput.setContentDescription(getResources().getString(R.string.google_password_hint));
            this.mInput.setInputType(129);
        }
        this.mInput.setVisibility(0);
        this.mInput.setOnEditorActionListener(new OnEditorActionListener() {
            public boolean onEditorAction(TextView tv, int id, KeyEvent event) {
                if (id != 6) {
                    return false;
                }
                GaiaAuthFragment.this.logClickAndVerifyInput(true);
                return true;
            }
        });
        TextView footer = (TextView) result.findViewById(R.id.challenge_footer);
        if (getRecoveryUrl() != null) {
            footer.setText(Html.fromHtml(getString(this.mUsePinBasedAuth ? R.string.reset_pin_text : R.string.password_help_text, getRecoveryUrl())));
            footer.setMovementMethod(LinkMovementMethod.getInstance());
            footer.setVisibility(0);
        } else {
            footer.setVisibility(8);
        }
        if (this.mShowWarning) {
            TextView optOutInfo = (TextView) result.findViewById(R.id.opt_out_info);
            optOutInfo.setText(Html.fromHtml(getString(R.string.auth_challenge_opt_out_info, G.gaiaOptOutLearnMoreLink.get())));
            optOutInfo.setMovementMethod(LinkMovementMethod.getInstance());
            optOutInfo.setVisibility(0);
        }
        Button okButton = (Button) result.findViewById(R.id.positive_button);
        okButton.setText(R.string.ok);
        okButton.setOnClickListener(this);
        Button cancelButton = (Button) result.findViewById(R.id.negative_button);
        cancelButton.setText(R.string.cancel);
        cancelButton.setOnClickListener(this);
        return result;
    }

    private String getRecoveryUrl() {
        return (this.mUsePinBasedAuth ? (String) G.resetPinUrl.get() : (String) G.passwordRecoveryUrl.get()).replace("%email%", this.mAccountName);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.positive_button:
                logClickAndVerifyInput(false);
                return;
            case R.id.negative_button:
                logClick(266, false);
                if (this.mListener != null) {
                    this.mListener.onCancel();
                    return;
                }
                return;
            default:
                return;
        }
    }

    public void setListener(Listener listener) {
        this.mListener = listener;
    }

    private void logClickAndVerifyInput(boolean isImeAction) {
        logClick(265, isImeAction);
        String input = this.mInput.getText().toString();
        this.mInput.setEnabled(false);
        verifyGaia(input);
    }

    private void verifyGaia(String userInput) {
        if (this.mUseGmsCoreForAuth) {
            GmsCoreAuthApi authApi = new GmsCoreAuthApi();
            if (this.mUsePinBasedAuth) {
                authApi.validateUserPin(this.mAccountName, userInput, this);
                return;
            } else {
                authApi.validateUserPassword(this.mAccountName, userInput, this);
                return;
            }
        }
        this.mAuthRequest = new ClientLoginApi(FinskyApp.get().getRequestQueue()).validateUser(this.mAccountName, userInput, this);
    }

    public void onAuthSuccess() {
        if (isResumed()) {
            success();
        } else {
            FinskyLog.d("Not resumed, ignoring auth challenge success.", new Object[0]);
        }
    }

    public void onAuthFailure(int err) {
        if (!isResumed()) {
            FinskyLog.d("Not resumed, ignoring auth challenge failure.", new Object[0]);
        } else if (err == 2 || err == 6) {
            success();
        } else if (err == 4) {
            failure(this.mUsePinBasedAuth ? R.string.invalid_pin : R.string.invalid_account_password);
        } else if (err != 3) {
            failure();
        } else {
            Account account = AccountHandler.findAccount(this.mAccountName, FinskyApp.get());
            AccountManager accountManager = AccountManager.get(FinskyApp.get());
            Bundle options = new Bundle();
            options.putString("password", this.mInput.getText().toString());
            accountManager.confirmCredentials(account, options, getActivity(), new AccountManagerCallback<Bundle>() {
                public void run(AccountManagerFuture<Bundle> bundleAccountManagerFuture) {
                    if (GaiaAuthFragment.this.isResumed()) {
                        try {
                            Bundle result = (Bundle) bundleAccountManagerFuture.getResult();
                            if (result.getBoolean("booleanResult")) {
                                GaiaAuthFragment.this.success();
                                return;
                            } else if (result.containsKey("intent")) {
                                GaiaAuthFragment.this.startActivityForResult((Intent) result.getParcelable("intent"), 100);
                                return;
                            } else {
                                GaiaAuthFragment.this.failure();
                                return;
                            }
                        } catch (OperationCanceledException e) {
                            FinskyLog.w("OperationCanceledException: %s", e);
                            GaiaAuthFragment.this.failure();
                            return;
                        } catch (IOException e2) {
                            FinskyLog.w("IOException: %s", e2);
                            GaiaAuthFragment.this.failure();
                            return;
                        } catch (AuthenticatorException e3) {
                            FinskyLog.w("AuthenticatorException: %s", e3);
                            GaiaAuthFragment.this.failure();
                            return;
                        }
                    }
                    FinskyLog.d("Not resumed, ignoring account manager auth.", new Object[0]);
                }
            }, null);
        }
    }

    public void onDestroyView() {
        if (this.mAuthRequest != null) {
            this.mAuthRequest.cancel();
        }
        super.onDestroyView();
    }

    private void failure() {
        failure(R.string.generic_purchase_prepare_error);
    }

    private void failure(int errorStringId) {
        this.mFailedCount++;
        logBackgroundEvent(502, false);
        if (this.mFailedCount >= ((Integer) G.passwordMaxFailedAttempts.get()).intValue()) {
            failWithMaxAttemptsExceeded();
            return;
        }
        this.mInput.setText("");
        this.mInput.setEnabled(true);
        UiUtils.showKeyboard(getActivity(), this.mInput);
        UiUtils.setErrorOnTextView(this.mInput, this.mUsePinBasedAuth ? getString(R.string.google_pin_hint) : getString(R.string.google_password_hint), getString(errorStringId));
    }

    private void failWithMaxAttemptsExceeded() {
        String recoveryUrl = getRecoveryUrl();
        String maxAttemptsExceededHtml = getString(this.mUsePinBasedAuth ? R.string.invalid_account_pin_max_attempts_exceeded : R.string.invalid_account_password_max_attempts_exceeded, recoveryUrl);
        Builder builder = new Builder();
        builder.setMessageHtml(maxAttemptsExceededHtml).setPositiveId(R.string.ok).setCallback(this, 1, null);
        builder.build().show(getFragmentManager(), "GaiaAuthFragment.errorDialog");
    }

    private void success() {
        this.mInput.setEnabled(true);
        this.mInput.setError(null);
        if (this.mListener != null) {
            this.mListener.onSuccess(2, this.mFailedCount);
        }
        logBackgroundEvent(502, true);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode != 100) {
            super.onActivityResult(requestCode, resultCode, data);
        } else if (resultCode == -1) {
            success();
        } else {
            failure();
        }
    }

    public void onPositiveClick(int requestCode, Bundle extraArguments) {
        if (requestCode == 1) {
            this.mListener.onCancel();
        }
    }

    public void onNegativeClick(int requestCode, Bundle extraArguments) {
        if (requestCode == 1) {
            this.mListener.onCancel();
        }
    }

    private void logBackgroundEvent(int eventType, boolean operationSuccess) {
        this.mEventLogger.logBackgroundEvent(new BackgroundEventBuilder(eventType).setOperationSuccess(operationSuccess).setAuthContext(getAuthContext()).build());
    }

    private void logClick(int leafType, boolean isImeAction) {
        PlayStoreUiElementInfo clientLogsCookie = new PlayStoreUiElementInfo();
        if (isImeAction) {
            clientLogsCookie.isImeAction = true;
            clientLogsCookie.hasIsImeAction = true;
        }
        clientLogsCookie.authContext = getAuthContext();
        this.mEventLogger.logClickEventWithClientCookie(leafType, clientLogsCookie, this.mNode);
    }

    private AuthContext getAuthContext() {
        int i;
        AuthContext authContext = new AuthContext();
        if (this.mUsePinBasedAuth) {
            i = 2;
        } else {
            i = 1;
        }
        authContext.mode = i;
        authContext.hasMode = true;
        return authContext;
    }
}
