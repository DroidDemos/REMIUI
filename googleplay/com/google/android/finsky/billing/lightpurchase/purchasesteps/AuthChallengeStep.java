package com.google.android.finsky.billing.lightpurchase.purchasesteps;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.accounts.AuthenticatorException;
import android.accounts.OperationCanceledException;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import com.android.vending.R;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.activities.SettingsActivity;
import com.google.android.finsky.analytics.BackgroundEventBuilder;
import com.google.android.finsky.analytics.FinskyEventLog;
import com.google.android.finsky.analytics.PlayStore.AuthContext;
import com.google.android.finsky.analytics.PlayStore.PlayStoreUiElement;
import com.google.android.finsky.analytics.PlayStore.PlayStoreUiElementInfo;
import com.google.android.finsky.api.AccountHandler;
import com.google.android.finsky.auth.AuthResponseListener;
import com.google.android.finsky.auth.GmsCoreAuthApi;
import com.google.android.finsky.billing.challenge.ClientLoginApi;
import com.google.android.finsky.billing.lightpurchase.PurchaseFragment;
import com.google.android.finsky.billing.lightpurchase.multistep.StepFragment;
import com.google.android.finsky.config.G;
import com.google.android.finsky.config.PreferenceFile.SharedPreference;
import com.google.android.finsky.config.PurchaseAuth;
import com.google.android.finsky.layout.AuthChallengeDialogDocumentInfoLayout;
import com.google.android.finsky.layout.play.PlayStoreUiElementNode;
import com.google.android.finsky.protos.ChallengeProto.AuthenticationChallenge;
import com.google.android.finsky.protos.ChallengeProto.FormCheckbox;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.FinskyPreferences;
import com.google.android.finsky.utils.ParcelableProto;
import com.google.android.finsky.utils.UiUtils;
import com.google.android.play.utils.UrlSpanUtils;
import com.google.android.play.utils.UrlSpanUtils.Listener;
import java.io.IOException;

public class AuthChallengeStep extends StepFragment<PurchaseFragment> implements OnClickListener, OnCheckedChangeListener, AuthResponseListener, PlayStoreUiElementNode {
    private String mAccountName;
    private AuthenticationChallenge mChallenge;
    private ClientLoginApi mClientLoginApi;
    private String mErrorMessage;
    private TextView mErrorView;
    private FinskyEventLog mEventLog;
    private int mFailedCount;
    private ImageView mHelpToggle;
    private boolean mIsOptOutChecked;
    private boolean mIsPasswordHelpExpanded;
    private View mMainView;
    private CheckBox mOptOutCheckbox;
    private TextView mOptOutInfo;
    private TextView mPasswordRecoveryView;
    private EditText mPasswordView;
    private TextView mPurchaseDisclaimer;
    private final PlayStoreUiElement mUiElement;
    private boolean mUseGmsCoreForAuth;
    private boolean mUsePinBasedAuth;

    public AuthChallengeStep() {
        this.mUiElement = FinskyEventLog.obtainPlayStoreUiElement(750);
    }

    public static AuthChallengeStep newInstance(String accountName, AuthenticationChallenge challenge, boolean useGmsCoreForAuth, boolean usePinBasedAuth) {
        Bundle args = new Bundle();
        args.putString("authAccount", accountName);
        args.putParcelable("AuthChallengeStep.challenge", ParcelableProto.forProto(challenge));
        args.putBoolean("AuthChallengeStep.useGmsCoreForAuth", useGmsCoreForAuth);
        args.putBoolean("AuthChallengeStep.usePinBasedAuth", usePinBasedAuth);
        AuthChallengeStep result = new AuthChallengeStep();
        result.setArguments(args);
        return result;
    }

    public void onCreate(Bundle savedInstanceState) {
        this.mUsePinBasedAuth = getArguments().getBoolean("AuthChallengeStep.usePinBasedAuth", false);
        if (this.mUiElement.clientLogsCookie == null) {
            this.mUiElement.clientLogsCookie = new PlayStoreUiElementInfo();
        }
        this.mUiElement.clientLogsCookie.authContext = getAuthContext();
        super.onCreate(savedInstanceState);
        this.mAccountName = getArguments().getString("authAccount");
        this.mChallenge = (AuthenticationChallenge) ParcelableProto.getProtoFromBundle(getArguments(), "AuthChallengeStep.challenge");
        this.mUseGmsCoreForAuth = getArguments().getBoolean("AuthChallengeStep.useGmsCoreForAuth", false);
        this.mIsOptOutChecked = false;
        this.mEventLog = FinskyApp.get().getEventLogger(((PurchaseFragment) getMultiStepFragment()).getAccount());
        if (savedInstanceState != null) {
            this.mFailedCount = savedInstanceState.getInt("AuthChallengeStep.retryCount");
            this.mIsOptOutChecked = savedInstanceState.getBoolean("AuthChallengeStep.optOutSelected");
            this.mIsPasswordHelpExpanded = savedInstanceState.getBoolean("AuthChallengeStep.passwordHelpExpanded");
            this.mErrorMessage = savedInstanceState.getString("AuthChallengeStep.errorMessage");
        }
        this.mClientLoginApi = new ClientLoginApi(FinskyApp.get().getRequestQueue());
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("AuthChallengeStep.retryCount", this.mFailedCount);
        outState.putBoolean("AuthChallengeStep.optOutSelected", this.mIsOptOutChecked);
        outState.putBoolean("AuthChallengeStep.passwordHelpExpanded", this.mIsPasswordHelpExpanded);
        outState.putString("AuthChallengeStep.errorMessage", this.mErrorMessage);
    }

    public String getContinueButtonLabel(Resources resources) {
        return resources.getString(R.string.confirm);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        int editTextInputResourceId;
        int titleViewStringResourceId;
        int helpToggleContentDescriptionResourceId;
        int recoveryMessageResourceId;
        this.mMainView = inflater.inflate(R.layout.light_purchase_password_confirm, container, false);
        if (this.mUsePinBasedAuth) {
            editTextInputResourceId = R.id.pin;
            titleViewStringResourceId = R.string.pin_dialog_title;
            helpToggleContentDescriptionResourceId = R.string.content_description_pin_help;
            recoveryMessageResourceId = R.string.reset_pin_text;
        } else {
            editTextInputResourceId = R.id.password;
            titleViewStringResourceId = R.string.password_dialog_title;
            helpToggleContentDescriptionResourceId = R.string.content_description_password_help;
            recoveryMessageResourceId = R.string.password_help_text;
        }
        this.mPasswordView = (EditText) this.mMainView.findViewById(editTextInputResourceId);
        this.mPasswordView.setVisibility(0);
        this.mPasswordView.setOnEditorActionListener(new OnEditorActionListener() {
            public boolean onEditorAction(TextView tv, int id, KeyEvent event) {
                if (id != 6) {
                    return false;
                }
                AuthChallengeStep.this.logClickAndVerifyGaia(true);
                return true;
            }
        });
        this.mPasswordView.setHintTextColor(getResources().getColor(R.color.play_secondary_text));
        ((TextView) this.mMainView.findViewById(R.id.title)).setText(getString(titleViewStringResourceId));
        this.mHelpToggle = (ImageView) this.mMainView.findViewById(R.id.help_toggle);
        this.mHelpToggle.setOnClickListener(this);
        this.mHelpToggle.setContentDescription(getString(helpToggleContentDescriptionResourceId));
        String recoveryMessageHtml = getString(recoveryMessageResourceId, getRecoveryUrl());
        this.mPasswordRecoveryView = (TextView) this.mMainView.findViewById(R.id.password_help);
        this.mPasswordRecoveryView.setMovementMethod(LinkMovementMethod.getInstance());
        this.mPasswordRecoveryView.setText(Html.fromHtml(recoveryMessageHtml));
        this.mPurchaseDisclaimer = (TextView) this.mMainView.findViewById(R.id.purchase_disclaimer);
        this.mPurchaseDisclaimer.setMovementMethod(LinkMovementMethod.getInstance());
        this.mErrorView = (TextView) this.mMainView.findViewById(R.id.error_message);
        if (this.mErrorMessage != null) {
            showErrorMessage(this.mErrorMessage);
        }
        this.mOptOutInfo = (TextView) this.mMainView.findViewById(R.id.opt_out_info);
        this.mOptOutInfo.setText(getPurchaseChallengeText(R.string.purchase_auth_message_never));
        this.mOptOutInfo.setMovementMethod(LinkMovementMethod.getInstance());
        this.mOptOutCheckbox = (CheckBox) this.mMainView.findViewById(R.id.opt_out_checkbox);
        if (isGaiaChallengeOverrideExperimentEnabled()) {
            this.mOptOutCheckbox.setVisibility(8);
        } else {
            FormCheckbox checkboxFromDfe = this.mChallenge.gaiaOptOutCheckbox;
            this.mOptOutCheckbox.setOnCheckedChangeListener(this);
            this.mOptOutCheckbox.setText(checkboxFromDfe.description.toUpperCase());
            this.mIsOptOutChecked = checkboxFromDfe.checked;
            this.mOptOutCheckbox.setChecked(this.mIsOptOutChecked);
        }
        ((TextView) this.mMainView.findViewById(R.id.account)).setText(Html.fromHtml(this.mChallenge.gaiaDescriptionTextHtml));
        AuthChallengeDialogDocumentInfoLayout documentInfoLayout = (AuthChallengeDialogDocumentInfoLayout) this.mMainView.findViewById(R.id.item_info);
        documentInfoLayout.setDocumentInfo(this.mChallenge.documentTitle, this.mChallenge.formattedPrice);
        boolean showChallengeContextView = documentInfoLayout.getVisibility() == 0;
        if (!TextUtils.isEmpty(this.mChallenge.instrumentDisplayTitle)) {
            TextView instrumentTitle = (TextView) this.mMainView.findViewById(R.id.instrument_title);
            instrumentTitle.setText(this.mChallenge.instrumentDisplayTitle);
            instrumentTitle.setVisibility(0);
            showChallengeContextView = true;
        }
        if (showChallengeContextView) {
            this.mMainView.findViewById(R.id.challenge_context).setVisibility(0);
        }
        return this.mMainView;
    }

    private String getRecoveryUrl() {
        return (this.mUsePinBasedAuth ? (String) G.resetPinUrl.get() : (String) G.passwordRecoveryUrl.get()).replace("%email%", this.mAccountName);
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (!((PurchaseFragment) getMultiStepFragment()).isLoading() && this.mPasswordView.getVisibility() == 0) {
            UiUtils.showKeyboard(getActivity(), this.mPasswordView);
        }
    }

    public void onResume() {
        super.onResume();
        UiUtils.sendAccessibilityEventWithText(this.mMainView.getContext(), getString(this.mUsePinBasedAuth ? R.string.pin_dialog_title : R.string.password_dialog_title), this.mMainView);
    }

    public void onStart() {
        super.onStart();
        if (PurchaseAuth.getPurchaseAuthType(this.mAccountName) != 0 || isGaiaChallengeOverrideExperimentEnabled()) {
            updatePasswordHelpAndPurchaseDisclaimer();
            return;
        }
        FinskyLog.d("PurchaseAuth changed to never prompt for password", new Object[0]);
        ((PurchaseFragment) getMultiStepFragment()).preparePurchase();
    }

    private boolean isGaiaChallengeOverrideExperimentEnabled() {
        return this.mChallenge.gaiaOptOutCheckbox == null;
    }

    private void logClickAndVerifyGaia(boolean isImeAction) {
        logClick(751, isImeAction);
        UiUtils.hideKeyboard(getActivity(), this.mPasswordView);
        ((PurchaseFragment) getMultiStepFragment()).showLoading();
        String userInput = this.mPasswordView.getText().toString();
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
        this.mClientLoginApi.validateUser(this.mAccountName, userInput, this);
    }

    public void onContinueButtonClicked() {
        logClickAndVerifyGaia(false);
    }

    public void onAuthSuccess() {
        if (isResumed()) {
            PurchaseFragment purchaseFragment = (PurchaseFragment) getMultiStepFragment();
            Bundle challengeResponse = new Bundle();
            challengeResponse.putString(this.mChallenge.responseAuthenticationTypeParam, String.valueOf(this.mChallenge.authenticationType));
            challengeResponse.putString(this.mChallenge.responseRetryCountParam, String.valueOf(this.mFailedCount));
            if (this.mIsOptOutChecked) {
                int previousSetting = PurchaseAuth.getPurchaseAuthType(this.mAccountName);
                if (previousSetting == 0) {
                    FinskyLog.wtf("Got through auth while opted out? Previous=%d", Integer.valueOf(previousSetting));
                }
                PurchaseAuth.setAndLogPurchaseAuth(this.mAccountName, 0, Integer.valueOf(previousSetting), this.mEventLog, "purchase-auth-screen");
            }
            SharedPreference<Long> lastGaiaAuthTimestamp = FinskyPreferences.lastGaiaAuthTimestamp.get(this.mAccountName);
            if (PurchaseAuth.getPurchaseAuthType(this.mAccountName) == 1) {
                lastGaiaAuthTimestamp.put(Long.valueOf(System.currentTimeMillis()));
            } else if (lastGaiaAuthTimestamp.exists()) {
                lastGaiaAuthTimestamp.remove();
            }
            logBackgroundEvent(508, true);
            purchaseFragment.answerChallenge(challengeResponse);
            return;
        }
        FinskyLog.d("Not resumed, ignoring auth challenge success.", new Object[0]);
    }

    public void onAuthFailure(int errorType) {
        if (!isResumed()) {
            FinskyLog.d("Not resumed, ignoring auth challenge failure.", new Object[0]);
        } else if (errorType == 2 || errorType == 6) {
            onAuthSuccess();
        } else if (errorType == 4) {
            changePasswordHelpAndPurchaseDisclaimer(true);
            fail(this.mUsePinBasedAuth ? R.string.invalid_account_pin_purchase_flow : R.string.invalid_account_password_purchase_flow);
        } else if (errorType != 3) {
            fail();
        } else {
            Account account = AccountHandler.findAccount(this.mAccountName, FinskyApp.get());
            AccountManager accountManager = AccountManager.get(FinskyApp.get());
            Bundle options = new Bundle();
            options.putString("password", this.mPasswordView.getText().toString());
            accountManager.confirmCredentials(account, options, getActivity(), new AccountManagerCallback<Bundle>() {
                public void run(AccountManagerFuture<Bundle> bundleAccountManagerFuture) {
                    if (AuthChallengeStep.this.isResumed()) {
                        try {
                            Bundle result = (Bundle) bundleAccountManagerFuture.getResult();
                            if (result.getBoolean("booleanResult")) {
                                AuthChallengeStep.this.onAuthSuccess();
                                return;
                            } else if (result.containsKey("intent")) {
                                AuthChallengeStep.this.startActivityForResult((Intent) result.getParcelable("intent"), 101);
                                return;
                            } else {
                                AuthChallengeStep.this.fail();
                                return;
                            }
                        } catch (OperationCanceledException e) {
                            FinskyLog.w("OperationCanceledException: %s", e);
                            AuthChallengeStep.this.fail();
                            return;
                        } catch (IOException e2) {
                            FinskyLog.w("IOException: %s", e2);
                            AuthChallengeStep.this.fail();
                            return;
                        } catch (AuthenticatorException e3) {
                            FinskyLog.w("AuthenticatorException: %s", e3);
                            AuthChallengeStep.this.fail();
                            return;
                        }
                    }
                    FinskyLog.d("Not resumed, ignoring account manager auth.", new Object[0]);
                }
            }, null);
        }
    }

    private void fail() {
        fail(R.string.generic_purchase_prepare_error);
    }

    private void fail(int errorStringId) {
        this.mFailedCount++;
        logBackgroundEvent(508, false);
        if (this.mFailedCount >= ((Integer) G.passwordMaxFailedAttempts.get()).intValue()) {
            failWithMaxAttemptsExceeded();
            return;
        }
        this.mPasswordView.setText("");
        showErrorMessage(getResources().getString(errorStringId));
        ((PurchaseFragment) getMultiStepFragment()).hideLoading();
        UiUtils.showKeyboard(getActivity(), this.mPasswordView);
    }

    private void failWithMaxAttemptsExceeded() {
        String recoveryUrl = getRecoveryUrl();
        ((PurchaseFragment) getMultiStepFragment()).showStep(ErrorStep.newInstance(getString(R.string.error), getString(this.mUsePinBasedAuth ? R.string.invalid_account_pin_max_attempts_exceeded : R.string.invalid_account_password_max_attempts_exceeded, recoveryUrl), true));
    }

    private void logBackgroundEvent(int eventType, boolean operationSuccess) {
        this.mEventLog.logBackgroundEvent(new BackgroundEventBuilder(eventType).setOperationSuccess(operationSuccess).setAuthContext(getAuthContext()).build());
    }

    private void logClick(int leafType, boolean isImeAction) {
        PlayStoreUiElementInfo clientLogsCookie = new PlayStoreUiElementInfo();
        if (isImeAction) {
            clientLogsCookie.isImeAction = true;
            clientLogsCookie.hasIsImeAction = true;
        }
        clientLogsCookie.authContext = getAuthContext();
        logClick(leafType, clientLogsCookie);
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

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode != 101) {
            super.onActivityResult(requestCode, resultCode, data);
        } else if (resultCode == -1) {
            onAuthSuccess();
        } else {
            fail();
        }
    }

    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (buttonView == this.mOptOutCheckbox) {
            logClick(753, false);
            this.mIsOptOutChecked = isChecked;
            if (isChecked) {
                changePasswordHelpAndPurchaseDisclaimer(false);
                this.mOptOutInfo.setVisibility(0);
                return;
            }
            this.mOptOutInfo.setVisibility(8);
        }
    }

    public void onClick(View v) {
        if (v == this.mHelpToggle) {
            logClick(752, false);
            changePasswordHelpAndPurchaseDisclaimer(true);
        }
    }

    private void changePasswordHelpAndPurchaseDisclaimer(boolean expanded) {
        this.mIsPasswordHelpExpanded = expanded;
        updatePasswordHelpAndPurchaseDisclaimer();
    }

    private void updatePasswordHelpAndPurchaseDisclaimer() {
        int i;
        int i2 = 8;
        int purchaseAuth = PurchaseAuth.getPurchaseAuthType(this.mAccountName);
        boolean canShowDisclaimer = true;
        if (purchaseAuth == 0) {
            canShowDisclaimer = false;
        } else {
            int resId;
            if (purchaseAuth == 1) {
                resId = R.string.purchase_auth_message_session;
            } else if (purchaseAuth == 2) {
                resId = R.string.purchase_auth_message_always;
            } else {
                FinskyLog.wtf("Unexpected value for PurchaseAuth message %d", Integer.valueOf(purchaseAuth));
                resId = R.string.purchase_auth_message_always;
                canShowDisclaimer = false;
            }
            this.mPurchaseDisclaimer.setText(getPurchaseChallengeText(resId));
        }
        TextView textView = this.mPurchaseDisclaimer;
        if (canShowDisclaimer && this.mIsPasswordHelpExpanded) {
            i = 0;
        } else {
            i = 8;
        }
        textView.setVisibility(i);
        textView = this.mPasswordRecoveryView;
        if (this.mIsPasswordHelpExpanded) {
            i = 0;
        } else {
            i = 8;
        }
        textView.setVisibility(i);
        ImageView imageView = this.mHelpToggle;
        if (!this.mIsPasswordHelpExpanded) {
            i2 = 0;
        }
        imageView.setVisibility(i2);
    }

    private void showErrorMessage(String errorMessage) {
        this.mErrorMessage = errorMessage;
        this.mErrorView.setText(errorMessage);
        this.mErrorView.setVisibility(0);
        UiUtils.sendAccessibilityEventWithText(getActivity(), errorMessage, this.mErrorView);
    }

    public PlayStoreUiElement getPlayStoreUiElement() {
        return this.mUiElement;
    }

    private CharSequence getPurchaseChallengeText(int resId) {
        CharSequence html = Html.fromHtml(getString(resId, "SETTINGS_ACTIVITY_SENTINEL", G.gaiaOptOutLearnMoreLink.get()));
        UrlSpanUtils.selfishifyUrlSpans(html, "SETTINGS_ACTIVITY_SENTINEL", new Listener() {
            public void onClick(View view, String url) {
                AuthChallengeStep.this.logClick(754, false);
                AuthChallengeStep.this.startActivity(new Intent(view.getContext(), SettingsActivity.class));
            }
        });
        return html;
    }
}
