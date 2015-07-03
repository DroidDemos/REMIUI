package com.xiaomi.passport.ui;

import android.accounts.Account;
import android.app.Fragment;
import android.content.Context;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.AsyncTask.Status;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import com.xiaomi.account.R;
import com.xiaomi.accountsdk.account.data.AccountInfo;
import com.xiaomi.accountsdk.account.data.ExtendedAuthToken;
import com.xiaomi.accountsdk.account.data.MetaLoginData;
import com.xiaomi.accountsdk.account.exception.InvalidCredentialException;
import com.xiaomi.accountsdk.account.exception.InvalidUserNameException;
import com.xiaomi.accountsdk.account.exception.NeedVerificationException;
import com.xiaomi.accountsdk.request.AccessDeniedException;
import com.xiaomi.accountsdk.request.AuthenticationFailureException;
import com.xiaomi.accountsdk.request.InvalidResponseException;
import com.xiaomi.miui.analyticstracker.AnalyticsTracker;
import com.xiaomi.passport.Build;
import com.xiaomi.passport.Constants;
import com.xiaomi.passport.accountmanager.MiAccountManager;
import com.xiaomi.passport.exception.IllegalDeviceException;
import com.xiaomi.passport.utils.AccountHelper;
import com.xiaomi.passport.utils.AnalyticsHelper;
import com.xiaomi.passport.utils.FriendlyFragmentUtils;
import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;

public class LoginStep2InputFragment extends Fragment implements OnClickListener {
    private static final boolean DEBUG = true;
    private static final int ERROR_FORBIDDEN = 4;
    private static final int ERROR_ILLEGAL_DEVICE_ID = 7;
    private static final int ERROR_NETWORK = 2;
    private static final int ERROR_PASSWORD = 1;
    private static final int ERROR_SERVER = 3;
    private static final int ERROR_USER_NAME = 6;
    private static final int ERROR_VERIFICATION = 5;
    private static final String TAG = "LoginInputFragment";
    private AnalyticsTracker mAnalyticsTracker;
    private boolean mInWarningState;
    private TextView mLoginStatusView;
    private AsyncTask mLoginStep2Task;
    private MetaLoginData mMetaLoginData;
    private OnLoginInterface mOnLoginInterface;
    private String mServiceId;
    private String mStep1Token;
    final TextWatcher mTextWatcher;
    private CheckBox mTrustDeviceView;
    private String mUserName;
    private EditText mVCodeView;
    private Button mVerifyBtn;

    public interface OnLoginInterface {
        void onBackToStep1();

        void onLoginSuccessByStep2(String str, String str2);
    }

    private class LoginResult {
        final AccountInfo accountInfo;
        final int error;
        final Map<String, String> extras;

        private LoginResult(AccountInfo accountInfo, int error, Map<String, String> extras) {
            this.accountInfo = accountInfo;
            this.error = error;
            this.extras = extras;
        }

        private LoginResult(LoginStep2InputFragment loginStep2InputFragment, AccountInfo accountInfo, int error) {
            this(accountInfo, error, null);
        }
    }

    private class LoginStep2Task extends AsyncTask<String, Void, LoginResult> {
        private final boolean trust;
        private final String vcode;

        private LoginStep2Task(String vcode, boolean trust) {
            this.vcode = vcode;
            this.trust = trust;
        }

        protected void onPreExecute() {
            LoginStep2InputFragment.this.mVCodeView.removeTextChangedListener(LoginStep2InputFragment.this.mTextWatcher);
            if (LoginStep2InputFragment.this.mInWarningState) {
                LoginStep2InputFragment.this.resetViewsStatus();
            }
            LoginStep2InputFragment.this.setViewsEnabled(false);
            LoginStep2InputFragment.this.mLoginStatusView.setVisibility(0);
            LoginStep2InputFragment.this.mLoginStatusView.setText(R.string.passport_login_notice);
            LoginStep2InputFragment.this.mLoginStatusView.setTextAppearance(LoginStep2InputFragment.this.getActivity(), R.style.Passport.LoginInfoNoticeAppearance);
        }

        protected void onPostExecute(LoginResult result) {
            LoginStep2InputFragment.this.processLoginResult(result);
        }

        protected LoginResult doInBackground(String... args) {
            try {
                AccountInfo accountInfo = AccountHelper.getServiceTokenByStep2(LoginStep2InputFragment.this.mUserName, this.vcode, LoginStep2InputFragment.this.mMetaLoginData, this.trust, LoginStep2InputFragment.this.mStep1Token, LoginStep2InputFragment.this.mServiceId);
                AnalyticsHelper.trackEvent(LoginStep2InputFragment.this.mAnalyticsTracker, AnalyticsHelper.LOGIN_SUCCESS_BY_VERIFICATION);
                if (accountInfo != null && !isCancelled()) {
                    return new LoginResult(accountInfo, -1, null);
                }
                Log.w(LoginStep2InputFragment.TAG, "login failure");
                Log.w(LoginStep2InputFragment.TAG, "failed to get service token");
                return new LoginResult(null, (int) LoginStep2InputFragment.ERROR_SERVER);
            } catch (InvalidCredentialException e) {
                Log.e(LoginStep2InputFragment.TAG, "invalid pwd", e);
                AnalyticsHelper.trackEvent(LoginStep2InputFragment.this.mAnalyticsTracker, AnalyticsHelper.LOGIN_ERROR_PWD);
                return new LoginResult(null, (int) LoginStep2InputFragment.ERROR_PASSWORD);
            } catch (InvalidResponseException e2) {
                Log.e(LoginStep2InputFragment.TAG, "invalid response", e2);
                AnalyticsHelper.trackEvent(LoginStep2InputFragment.this.mAnalyticsTracker, AnalyticsHelper.LOGIN_ERROR_INVALID_RESPONSE, e2);
                return new LoginResult(null, (int) LoginStep2InputFragment.ERROR_SERVER);
            } catch (IOException e3) {
                Log.e(LoginStep2InputFragment.TAG, "io exception", e3);
                AnalyticsHelper.trackEvent(LoginStep2InputFragment.this.mAnalyticsTracker, AnalyticsHelper.LOGIN_ERROR_IO, e3);
                return new LoginResult(null, (int) LoginStep2InputFragment.ERROR_NETWORK);
            } catch (IllegalDeviceException e4) {
                Log.e(LoginStep2InputFragment.TAG, "IllegalDeviceException", e4);
                AnalyticsHelper.trackEvent(LoginStep2InputFragment.this.mAnalyticsTracker, AnalyticsHelper.LOGIN_ERROR_IO, e4);
                return new LoginResult(null, (int) LoginStep2InputFragment.ERROR_ILLEGAL_DEVICE_ID);
            } catch (AccessDeniedException e5) {
                Log.e(LoginStep2InputFragment.TAG, "access denied");
                AnalyticsHelper.trackEvent(LoginStep2InputFragment.this.mAnalyticsTracker, AnalyticsHelper.LOGIN_ERROR_FORBIDDEN);
                return new LoginResult(null, (int) LoginStep2InputFragment.ERROR_FORBIDDEN);
            } catch (AuthenticationFailureException e6) {
                Log.e(LoginStep2InputFragment.TAG, "auth failure");
                AnalyticsHelper.trackEvent(LoginStep2InputFragment.this.mAnalyticsTracker, AnalyticsHelper.LOGIN_ERROR_AUTH_FAILURE);
                return new LoginResult(null, (int) LoginStep2InputFragment.ERROR_SERVER);
            } catch (NeedVerificationException e7) {
                AnalyticsHelper.trackEvent(LoginStep2InputFragment.this.mAnalyticsTracker, AnalyticsHelper.LOGIN_ERROR_VERIFICATION, e7);
                return new LoginResult(null, (int) LoginStep2InputFragment.ERROR_VERIFICATION);
            } catch (InvalidUserNameException e8) {
                AnalyticsHelper.trackEvent(LoginStep2InputFragment.this.mAnalyticsTracker, AnalyticsHelper.LOGIN_ERROR_USER_NAME, e8);
                return new LoginResult(null, (int) LoginStep2InputFragment.ERROR_USER_NAME);
            }
        }
    }

    public LoginStep2InputFragment() {
        this.mTextWatcher = new TextWatcher() {
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            public void afterTextChanged(Editable s) {
                if (LoginStep2InputFragment.this.mInWarningState) {
                    LoginStep2InputFragment.this.resetViewsStatus();
                    LoginStep2InputFragment.this.mInWarningState = false;
                }
            }
        };
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            this.mUserName = args.getString(Constants.EXTRA_USER_NAME);
            this.mStep1Token = args.getString(Constants.EXTRA_STEP1_TOKEN);
            this.mMetaLoginData = new MetaLoginData(args.getString(Constants.EXTRA_SIGN), args.getString(Constants.EXTRA_QS), args.getString(Constants.EXTRA_CALLBACK));
            this.mServiceId = args.getString(Constants.EXTRA_SERVICE_URL);
        }
        this.mAnalyticsTracker = AnalyticsTracker.getInstance();
        this.mAnalyticsTracker.startSession(getActivity());
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.passport_login_step2, container, false);
        this.mVerifyBtn = (Button) v.findViewById(R.id.btn_verify);
        this.mVCodeView = (EditText) v.findViewById(R.id.et_vcode);
        this.mTrustDeviceView = (CheckBox) v.findViewById(R.id.passport_trust_device);
        this.mLoginStatusView = (TextView) v.findViewById(R.id.tv_status);
        this.mVerifyBtn.setOnClickListener(this);
        if (Build.IS_TABLET) {
            ((Button) v.findViewById(R.id.btn_cancel)).setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                    FriendlyFragmentUtils.popUpFragment(LoginStep2InputFragment.this);
                }
            });
        }
        resetViewsStatus();
        return v;
    }

    public void onDestroy() {
        if (this.mLoginStep2Task != null) {
            this.mLoginStep2Task.cancel(DEBUG);
            this.mLoginStep2Task = null;
        }
        this.mAnalyticsTracker.endSession();
        super.onDestroy();
    }

    public void onResume() {
        super.onResume();
        getActivity().getWindow().setSoftInputMode(20);
        this.mVCodeView.requestFocus();
    }

    public void setOnLoginInterface(OnLoginInterface onLoginInterface) {
        this.mOnLoginInterface = onLoginInterface;
    }

    private void resetViewsStatus() {
        this.mLoginStatusView.setVisibility(8);
        this.mVCodeView.setBackgroundResource(R.drawable.passport_group_single_item_normal_bg);
        this.mVCodeView.setTextColor(getResources().getColor(R.color.passport_text_color_black));
    }

    private void setViewsEnabled(boolean enabled) {
        this.mVCodeView.setEnabled(enabled);
        this.mVerifyBtn.setEnabled(enabled);
    }

    public void onClick(View v) {
        if (v == this.mVerifyBtn) {
            startLoginStep2();
        }
    }

    private void startLoginStep2() {
        String vcode = this.mVCodeView.getText().toString();
        boolean trust = this.mTrustDeviceView.isChecked();
        if (TextUtils.isEmpty(vcode)) {
            this.mVCodeView.setError(getString(R.string.passport_error_empty_vcode));
        } else if (this.mLoginStep2Task == null || Status.FINISHED == this.mLoginStep2Task.getStatus()) {
            this.mLoginStep2Task = new LoginStep2Task(vcode, trust).execute(new String[0]);
        }
    }

    private void processLoginResult(LoginResult result) {
        setViewsEnabled(DEBUG);
        AccountInfo accountInfo = result.accountInfo;
        Context context = getActivity();
        if (accountInfo != null) {
            this.mLoginStatusView.setVisibility(8);
            MiAccountManager am = MiAccountManager.get(context);
            String userId = accountInfo.getUserId();
            Account account = new Account(userId, MiAccountManager.XIAOMI_ACCOUNT_TYPE);
            Bundle data = new Bundle();
            data.putString(Constants.EXTRA_USER_ID, userId);
            data.putString(Constants.EXTRA_XIAOMI_ACCOUNT_NAME, this.mUserName);
            data.putString(Constants.KEY_ENCRYPTED_USER_ID, accountInfo.getEncryptedUserId());
            ExtendedAuthToken eat = ExtendedAuthToken.build(accountInfo.getServiceToken(), accountInfo.getSecurity());
            ExtendedAuthToken extPass = ExtendedAuthToken.build(accountInfo.getPassToken(), accountInfo.getPsecurity());
            String authToken = eat.toPlain();
            String pass = extPass.toPlain();
            Map<String, String> extras = result.extras;
            if (extras != null) {
                for (Entry<String, String> entry : extras.entrySet()) {
                    data.putString((String) entry.getKey(), (String) entry.getValue());
                }
            }
            AccountHelper.addActiveXiaomiAccount(context, account, pass, data);
            if (!TextUtils.isEmpty(this.mServiceId)) {
                am.setAuthToken(account, this.mServiceId, authToken);
            }
            if (this.mOnLoginInterface != null) {
                this.mOnLoginInterface.onLoginSuccessByStep2(userId, authToken);
                return;
            }
            return;
        }
        String reason;
        Log.w(TAG, "login failure");
        switch (result.error) {
            case ERROR_PASSWORD /*1*/:
                if (this.mOnLoginInterface != null) {
                    this.mOnLoginInterface.onBackToStep1();
                    return;
                }
                return;
            case ERROR_NETWORK /*2*/:
                reason = getString(R.string.passport_error_network);
                break;
            case ERROR_SERVER /*3*/:
                reason = getString(R.string.passport_error_server);
                break;
            case ERROR_FORBIDDEN /*4*/:
                reason = getString(R.string.passport_access_denied);
                break;
            case ERROR_VERIFICATION /*5*/:
                reason = getString(R.string.passport_wrong_vcode);
                break;
            case ERROR_USER_NAME /*6*/:
                if (this.mOnLoginInterface != null) {
                    this.mOnLoginInterface.onBackToStep1();
                    return;
                }
                return;
            case ERROR_ILLEGAL_DEVICE_ID /*7*/:
                reason = getString(R.string.passport_error_device_id);
                break;
            default:
                reason = getString(R.string.passport_error_unknown);
                break;
        }
        Resources res = getResources();
        this.mLoginStatusView.setTextAppearance(context, R.style.Passport.LoginErrorNoticeAppearance);
        this.mLoginStatusView.setText(reason);
        this.mVCodeView.setTextColor(res.getColor(R.color.passport_text_color_warn));
        this.mVCodeView.setBackgroundResource(R.drawable.passport_group_single_item_warn_bg);
        this.mInWarningState = DEBUG;
    }
}
