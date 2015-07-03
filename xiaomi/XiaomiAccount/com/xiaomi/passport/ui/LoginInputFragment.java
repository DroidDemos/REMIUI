package com.xiaomi.passport.ui;

import android.accounts.Account;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.AsyncTask.Status;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.xiaomi.account.R;
import com.xiaomi.accountsdk.account.data.AccountInfo;
import com.xiaomi.accountsdk.account.data.ExtendedAuthToken;
import com.xiaomi.accountsdk.account.data.MetaLoginData;
import com.xiaomi.accountsdk.account.exception.InvalidCredentialException;
import com.xiaomi.accountsdk.account.exception.InvalidUserNameException;
import com.xiaomi.accountsdk.account.exception.NeedCaptchaException;
import com.xiaomi.accountsdk.account.exception.NeedNotificationException;
import com.xiaomi.accountsdk.account.exception.NeedVerificationException;
import com.xiaomi.accountsdk.request.AccessDeniedException;
import com.xiaomi.accountsdk.request.AuthenticationFailureException;
import com.xiaomi.accountsdk.request.InvalidResponseException;
import com.xiaomi.accountsdk.request.SimpleRequest.StringContent;
import com.xiaomi.miui.analyticstracker.AnalyticsTracker;
import com.xiaomi.passport.Build;
import com.xiaomi.passport.Constants;
import com.xiaomi.passport.accountmanager.MiAccountManager;
import com.xiaomi.passport.data.LoginResult;
import com.xiaomi.passport.ui.SimpleDialogFragment.AlertDialogFragmentBuilder;
import com.xiaomi.passport.utils.AccountHelper;
import com.xiaomi.passport.utils.AnalyticsHelper;
import com.xiaomi.passport.utils.FriendlyFragmentUtils;
import com.xiaomi.passport.utils.PhoneNumUtil;
import com.xiaomi.passport.utils.PhoneNumUtil.CountryPhoneNumData;
import com.xiaomi.passport.utils.SysHelper;

public class LoginInputFragment extends Fragment implements OnClickListener {
    private static final String AREA_ISO_DEFAULT = "CN";
    private static final int ERROR_CAPTCHA = 5;
    private static final int ERROR_FORBIDDEN = 4;
    private static final int ERROR_ILLEGAL_DEVICE_ID = 9;
    private static final int ERROR_NEED_NOTIFICATION = 8;
    private static final int ERROR_NETWORK = 2;
    private static final int ERROR_PASSWORD = 1;
    private static final int ERROR_SERVER = 3;
    private static final int ERROR_SSL_HAND_SHAKE = 10;
    private static final int ERROR_USER_NAME = 7;
    private static final int ERROR_VERIFICATION = 6;
    private static final String LANGUAGE_DEFAULT = "zh_CN";
    public static final int REQUEST_CODE_FOREIGN_LOGIN = 1;
    public static final int REQUEST_NOTIFICATION_LOGIN = 2;
    private static final String TAG = "LoginInputFragment";
    private EditText mAccountNameView;
    private EditText mAccountPwdView;
    private AnalyticsTracker mAnalyticsTracker;
    private View mAreaCodeLayout;
    private TextView mAreaCodeView;
    private View mCaptchaAreaView;
    private TextView mCaptchaCodeView;
    private ImageView mCaptchaImageView;
    private String mCaptchaUrl;
    private CountryPhoneNumData mCountryPhoneNumData;
    private DownloadCaptchaTask mDownloadCaptchaTask;
    private boolean mFindPasswordOnPc;
    private boolean mForeignEmailOrIdLoginState;
    private TextView mForeignEmailOrIdLoginView;
    private TextView mForgetPwdView;
    private volatile String mIck;
    private boolean mInWarningState;
    private CheckBox mLicenseCheckBox;
    private Button mLoginButton;
    private StringContent mLoginContent;
    private TextView mLoginStatusView;
    private AsyncTask mLoginTask;
    private volatile MetaLoginData mMetaLoginData;
    private OnLoginInterface mOnLoginInterface;
    private String mRegionChosen;
    private String mServiceId;
    private String mServiceTokenLocation;
    private boolean mShowPassword;
    private ImageView mShowPwdImageView;
    final TextWatcher mTextWatcher;
    private String mUserId;

    public interface OnLoginInterface {
        void onForeignEmailOrIdLogin();

        void onLoginSuccess(String str, String str2);

        void onNeedStep2(String str, String str2, String str3, String str4, String str5, String str6);

        void onSkipLogin();

        void onStartLogin();
    }

    private class DownloadCaptchaTask extends AsyncTask<Void, Void, Pair<Bitmap, String>> {
        private final String mCaptchaUrl;

        public DownloadCaptchaTask(String captchaUrl) {
            this.mCaptchaUrl = captchaUrl;
        }

        protected Pair<Bitmap, String> doInBackground(Void... voids) {
            return AccountHelper.getCaptchaImage(this.mCaptchaUrl);
        }

        protected void onPostExecute(Pair<Bitmap, String> result) {
            if (result != null) {
                LoginInputFragment.this.mCaptchaImageView.setImageBitmap((Bitmap) result.first);
                LoginInputFragment.this.mIck = (String) result.second;
            }
        }
    }

    private class LoginTask extends AsyncTask<String, Void, LoginResult> {
        private String captchaCode;
        private String password;
        private String serviceId;
        private String username;

        private LoginTask(String username, String password, String captchaCode, String serviceId) {
            this.username = username;
            this.password = password;
            this.captchaCode = captchaCode;
            this.serviceId = serviceId;
        }

        protected void onPreExecute() {
            LoginInputFragment.this.mAccountNameView.removeTextChangedListener(LoginInputFragment.this.mTextWatcher);
            LoginInputFragment.this.mAccountPwdView.removeTextChangedListener(LoginInputFragment.this.mTextWatcher);
            if (LoginInputFragment.this.mInWarningState) {
                LoginInputFragment.this.resetViewsStatus();
            }
            LoginInputFragment.this.setViewsEnabled(false);
            LoginInputFragment.this.mLoginStatusView.setVisibility(0);
            LoginInputFragment.this.mLoginStatusView.setText(R.string.passport_login_notice);
            LoginInputFragment.this.mLoginStatusView.setTextAppearance(LoginInputFragment.this.getActivity(), R.style.Passport.LoginInfoNoticeAppearance);
            if (LoginInputFragment.this.mOnLoginInterface != null) {
                LoginInputFragment.this.mOnLoginInterface.onStartLogin();
            }
        }

        protected LoginResult doInBackground(String... args) {
            String captchaUrl;
            try {
                AccountInfo accountInfo;
                if (LoginInputFragment.this.mServiceTokenLocation == null) {
                    accountInfo = AccountHelper.getServiceTokenByPassword(this.username, this.password, this.captchaCode, LoginInputFragment.this.mIck, LoginInputFragment.this.mMetaLoginData, this.serviceId, true);
                } else {
                    accountInfo = AccountHelper.parseLoginResult(LoginInputFragment.this.mUserId, LoginInputFragment.this.mLoginContent, this.serviceId, LoginInputFragment.this.mServiceTokenLocation);
                }
                AnalyticsHelper.trackEvent(LoginInputFragment.this.mAnalyticsTracker, AnalyticsHelper.LOGIN_SUCCESS_BY_PWD);
                if (this.captchaCode != null) {
                    AnalyticsHelper.trackEvent(LoginInputFragment.this.mAnalyticsTracker, AnalyticsHelper.LOGIN_CORRECT_CAPTCHA);
                }
                if (accountInfo != null && !isCancelled()) {
                    return new LoginResult(accountInfo, -1, this.serviceId, this.username, this.password);
                }
                Log.w(LoginInputFragment.TAG, "login failure");
                Log.w(LoginInputFragment.TAG, "failed to get service token");
                return new LoginResult(null, LoginInputFragment.ERROR_SERVER, this.serviceId, this.username, this.password);
            } catch (InvalidCredentialException e) {
                Log.e(LoginInputFragment.TAG, "invalid pwd", e);
                AnalyticsHelper.trackEvent(LoginInputFragment.this.mAnalyticsTracker, AnalyticsHelper.LOGIN_ERROR_PWD);
                captchaUrl = e.getCaptchaUrl();
                if (captchaUrl != null) {
                    AnalyticsHelper.trackEvent(LoginInputFragment.this.mAnalyticsTracker, AnalyticsHelper.LOGIN_NEED_CAPTCHA);
                }
                return new LoginResult(null, LoginInputFragment.REQUEST_CODE_FOREIGN_LOGIN, this.serviceId, this.username, this.password, captchaUrl, null, null);
            } catch (InvalidResponseException e2) {
                Log.e(LoginInputFragment.TAG, "invalid response", e2);
                AnalyticsHelper.trackEvent(LoginInputFragment.this.mAnalyticsTracker, AnalyticsHelper.LOGIN_ERROR_INVALID_RESPONSE, e2);
                return new LoginResult(null, LoginInputFragment.ERROR_SERVER, this.serviceId, this.username, this.password);
            } catch (Throwable e3) {
                Log.e(LoginInputFragment.TAG, "SSLHandshakeException exception", e3);
                AnalyticsHelper.trackEvent(LoginInputFragment.this.mAnalyticsTracker, AnalyticsHelper.LOGIN_ERROR_SSL_HANDSHAKE, e3);
                return new LoginResult(null, LoginInputFragment.ERROR_SSL_HAND_SHAKE, this.serviceId, this.username, this.password);
            } catch (Throwable e32) {
                Log.e(LoginInputFragment.TAG, "io exception", e32);
                AnalyticsHelper.trackEvent(LoginInputFragment.this.mAnalyticsTracker, AnalyticsHelper.LOGIN_ERROR_IO, e32);
                return new LoginResult(null, LoginInputFragment.REQUEST_NOTIFICATION_LOGIN, this.serviceId, this.username, this.password);
            } catch (Throwable e322) {
                Log.e(LoginInputFragment.TAG, "IllegalDeviceException", e322);
                AnalyticsHelper.trackEvent(LoginInputFragment.this.mAnalyticsTracker, AnalyticsHelper.LOGIN_ERROR_IO, e322);
                return new LoginResult(null, LoginInputFragment.ERROR_ILLEGAL_DEVICE_ID);
            } catch (AccessDeniedException e4) {
                Log.e(LoginInputFragment.TAG, "access denied");
                AnalyticsHelper.trackEvent(LoginInputFragment.this.mAnalyticsTracker, AnalyticsHelper.LOGIN_ERROR_FORBIDDEN);
                return new LoginResult(null, LoginInputFragment.ERROR_FORBIDDEN, this.serviceId, this.username, this.password);
            } catch (AuthenticationFailureException e5) {
                Log.e(LoginInputFragment.TAG, "auth failure");
                AnalyticsHelper.trackEvent(LoginInputFragment.this.mAnalyticsTracker, AnalyticsHelper.LOGIN_ERROR_AUTH_FAILURE);
                return new LoginResult(null, LoginInputFragment.ERROR_SERVER, this.serviceId, this.username, this.password);
            } catch (NeedVerificationException e6) {
                String step1Token = e6.getStep1Token();
                MetaLoginData metaLoginDataStep2 = e6.getMetaLoginData();
                AnalyticsHelper.trackEvent(LoginInputFragment.this.mAnalyticsTracker, AnalyticsHelper.LOGIN_NEED_VERIFICATION_AFTER_PASSWORD, e6);
                return new LoginResult(null, LoginInputFragment.ERROR_VERIFICATION, this.serviceId, this.username, this.password, null, metaLoginDataStep2, step1Token);
            } catch (NeedCaptchaException e7) {
                captchaUrl = e7.getCaptchaUrl();
                AnalyticsHelper.trackEvent(LoginInputFragment.this.mAnalyticsTracker, AnalyticsHelper.LOGIN_ERROR_CAPTCHA, e7);
                return new LoginResult(null, LoginInputFragment.ERROR_CAPTCHA, this.serviceId, this.username, this.password, captchaUrl, null, null);
            } catch (InvalidUserNameException e8) {
                Log.e(LoginInputFragment.TAG, "invalid username", e8);
                AnalyticsHelper.trackEvent(LoginInputFragment.this.mAnalyticsTracker, AnalyticsHelper.LOGIN_ERROR_USER_NAME);
                return new LoginResult(null, LoginInputFragment.ERROR_USER_NAME, this.serviceId, this.username, this.password, null, null, null);
            } catch (NeedNotificationException e9) {
                String notificationUrl = e9.getNotificationUrl();
                LoginInputFragment.this.mUserId = e9.getUserId();
                LoginInputFragment.this.mLoginContent = e9.getLoginContent();
                return new LoginResult(null, LoginInputFragment.ERROR_NEED_NOTIFICATION, this.serviceId, this.username, this.password, null, null, null, notificationUrl);
            }
        }

        protected void onPostExecute(LoginResult result) {
            LoginInputFragment.this.processLoginResult(result);
        }
    }

    public LoginInputFragment() {
        this.mDownloadCaptchaTask = null;
        this.mFindPasswordOnPc = false;
        this.mServiceTokenLocation = null;
        this.mTextWatcher = new TextWatcher() {
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            public void afterTextChanged(Editable s) {
                if (LoginInputFragment.this.mInWarningState) {
                    LoginInputFragment.this.resetViewsStatus();
                    LoginInputFragment.this.mInWarningState = false;
                }
            }
        };
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            this.mServiceId = args.getString(Constants.EXTRA_SERVICE_URL);
            this.mFindPasswordOnPc = args.getBoolean(Constants.EXTRA_FIND_PASSWORD_ON_PC, false);
            this.mRegionChosen = args.getString(Constants.EXTRA_BUILD_REGION_INFO, AREA_ISO_DEFAULT);
        }
        this.mAnalyticsTracker = AnalyticsTracker.getInstance();
        this.mAnalyticsTracker.startSession(getActivity());
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.passport_login, container, false);
        this.mAccountNameView = (EditText) v.findViewById(R.id.et_account_name);
        this.mAccountPwdView = (EditText) v.findViewById(R.id.et_account_password);
        this.mForgetPwdView = (TextView) v.findViewById(R.id.tv_forget_pwd);
        this.mAreaCodeView = (TextView) v.findViewById(R.id.tv_area_code);
        this.mAreaCodeLayout = v.findViewById(R.id.area_code_layout);
        this.mLoginStatusView = (TextView) v.findViewById(R.id.tv_status);
        this.mShowPwdImageView = (ImageView) v.findViewById(R.id.show_password_img);
        this.mCaptchaAreaView = v.findViewById(R.id.et_captcha_area);
        this.mCaptchaCodeView = (EditText) v.findViewById(R.id.et_captcha_code);
        this.mCaptchaImageView = (ImageView) v.findViewById(R.id.et_captcha_image);
        this.mLoginButton = (Button) v.findViewById(R.id.btn_login);
        this.mLicenseCheckBox = (CheckBox) v.findViewById(R.id.license);
        this.mForeignEmailOrIdLoginView = (TextView) v.findViewById(R.id.login_with_email_or_id);
        SysHelper.setLicense(getActivity(), this.mLicenseCheckBox);
        this.mLicenseCheckBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                LoginInputFragment.this.mLoginButton.setEnabled(isChecked);
            }
        });
        this.mAccountPwdView.setOnEditorActionListener(new SimpleEditorActionListenerImpl(ERROR_VERIFICATION, new Runnable() {
            public void run() {
                LoginInputFragment.this.startLogin();
            }
        }));
        this.mShowPassword = false;
        updateShowPasswordState();
        this.mLoginButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                LoginInputFragment.this.startLogin();
            }
        });
        this.mAreaCodeView.setOnClickListener(this);
        this.mForgetPwdView.setOnClickListener(this);
        this.mShowPwdImageView.setOnClickListener(this);
        this.mForeignEmailOrIdLoginView.setOnClickListener(this);
        this.mCaptchaImageView.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                if (LoginInputFragment.this.mCaptchaUrl != null) {
                    LoginInputFragment.this.startDownloadingCaptcha();
                }
            }
        });
        if (Build.IS_TABLET) {
            ((Button) v.findViewById(R.id.btn_cancel)).setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                    FriendlyFragmentUtils.popUpFragment(LoginInputFragment.this);
                }
            });
        }
        resetViewsStatus();
        if (TextUtils.isEmpty(this.mRegionChosen)) {
            this.mRegionChosen = AREA_ISO_DEFAULT;
        }
        return v;
    }

    private boolean isForeignLogin() {
        return (isCNLanguage() && isCNCountry()) ? false : true;
    }

    private boolean isCNLanguage() {
        return LANGUAGE_DEFAULT.equals(getActivity().getResources().getConfiguration().locale.toString());
    }

    private boolean isCNCountry() {
        return !TextUtils.isEmpty(this.mRegionChosen) && AREA_ISO_DEFAULT.equals(this.mRegionChosen);
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        Bundle args = getArguments();
        if (args != null) {
            String loginName = args.getString(Constants.EXTRA_AUTO_LOGIN_NAME);
            String password = args.getString(Constants.EXTRA_AUTO_LOGIN_PWD);
            boolean autoLogin = args.getBoolean(Constants.EXTRA_AUTO_LOGIN);
            args.remove(Constants.EXTRA_AUTO_LOGIN);
            args.remove(Constants.EXTRA_AUTO_LOGIN_NAME);
            args.remove(Constants.EXTRA_AUTO_LOGIN_PWD);
            if (autoLogin) {
                this.mForeignEmailOrIdLoginState = true;
                this.mAccountNameView.setText(loginName);
                this.mAccountPwdView.setText(password);
                startLogin();
            }
        }
        if (isForeignLogin()) {
            setForeignLoginView();
            AnalyticsHelper.trackEvent(this.mAnalyticsTracker, AnalyticsHelper.USER_FOREIGN_LOGIN);
        }
    }

    public void onDestroy() {
        if (this.mLoginTask != null) {
            this.mLoginTask.cancel(true);
            this.mLoginTask = null;
        }
        if (this.mDownloadCaptchaTask != null) {
            this.mDownloadCaptchaTask.cancel(true);
            this.mDownloadCaptchaTask = null;
        }
        this.mAnalyticsTracker.endSession();
        super.onDestroy();
    }

    public void onResume() {
        super.onResume();
        if (!isForeignLogin()) {
            getActivity().getWindow().setSoftInputMode(20);
        } else if (this.mForeignEmailOrIdLoginState) {
            SysHelper.setSoftInputVisibility(getActivity(), REQUEST_NOTIFICATION_LOGIN);
        }
    }

    public void onPause() {
        super.onPause();
        ((InputMethodManager) getActivity().getSystemService("input_method")).hideSoftInputFromWindow(this.mAccountNameView.getWindowToken(), REQUEST_NOTIFICATION_LOGIN);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_CODE_FOREIGN_LOGIN /*1*/:
                if (-1 == resultCode) {
                    refreshViewStateByISO(data.getStringExtra(AreaCodePickerFragment.KEY_COUNTRY_ISO));
                    return;
                }
                return;
            case REQUEST_NOTIFICATION_LOGIN /*2*/:
                if (-1 == resultCode) {
                    this.mServiceTokenLocation = data.getStringExtra("location");
                    startLogin();
                    return;
                } else if (resultCode == 0) {
                    this.mLoginStatusView.setText(R.string.passport_relogin_notice);
                    return;
                } else {
                    return;
                }
            default:
                return;
        }
    }

    public void setOnLoginInterface(OnLoginInterface onLoginInterface) {
        this.mOnLoginInterface = onLoginInterface;
    }

    private void resetViewsStatus() {
        this.mLoginStatusView.setVisibility(ERROR_NEED_NOTIFICATION);
        this.mAccountPwdView.setBackgroundResource(this.mCaptchaAreaView.getVisibility() == 0 ? R.drawable.passport_group_middle_item_normal_bg : R.drawable.passport_group_last_item_normal_bg);
        this.mAccountPwdView.setPadding(this.mAccountPwdView.getPaddingLeft(), this.mAccountPwdView.getPaddingTop(), getResources().getDimensionPixelSize(R.dimen.passport_password_alert_icon_padding_right), this.mAccountPwdView.getPaddingBottom());
        Resources res = getResources();
        this.mAccountNameView.setTextColor(res.getColor(R.color.passport_text_color_black));
        this.mAccountPwdView.setTextColor(res.getColor(R.color.passport_text_color_black));
        this.mCaptchaCodeView.setTextColor(res.getColor(R.color.passport_text_color_black));
    }

    private void refreshViewStateByISO(String areaISO) {
        if (TextUtils.isEmpty(areaISO)) {
            Log.d(TAG, " region info is null, and set China as the default area iso");
            areaISO = AREA_ISO_DEFAULT;
        }
        this.mCountryPhoneNumData = PhoneNumUtil.getCounrtyPhoneDataFromIso(areaISO);
        if (this.mAreaCodeView != null) {
            this.mAreaCodeView.setText(this.mCountryPhoneNumData.countryName + "(+" + this.mCountryPhoneNumData.countryCode + ")");
        }
    }

    private void setViewsEnabled(boolean enabled) {
        if (this.mAccountNameView != null) {
            this.mAccountNameView.setEnabled(enabled);
        }
        if (this.mAccountPwdView != null) {
            this.mAccountPwdView.setEnabled(enabled);
        }
        if (this.mForgetPwdView != null) {
            this.mForgetPwdView.setEnabled(enabled);
        }
        if (this.mLicenseCheckBox != null) {
            this.mLicenseCheckBox.setEnabled(enabled);
        }
        if (this.mForeignEmailOrIdLoginView != null) {
            this.mForeignEmailOrIdLoginView.setEnabled(enabled);
        }
        if (this.mAreaCodeView != null) {
            this.mAreaCodeView.setEnabled(enabled);
        }
    }

    public void onClick(View v) {
        boolean z = true;
        if (v == this.mForgetPwdView) {
            if (this.mFindPasswordOnPc) {
                showFindPasswordOnWebDialog();
            } else {
                SysHelper.getbackPassword(getActivity());
            }
            AnalyticsHelper.trackEvent(this.mAnalyticsTracker, AnalyticsHelper.USER_FORGOT_PASSWORD);
        } else if (v == this.mShowPwdImageView) {
            if (this.mShowPassword) {
                z = false;
            }
            this.mShowPassword = z;
            updateShowPasswordState();
        } else if (v == this.mAreaCodeView) {
            Intent intent = new Intent(Constants.ACTION_AREA_CODE);
            intent.setPackage(getActivity().getPackageName());
            startActivityForResult(intent, REQUEST_CODE_FOREIGN_LOGIN);
        } else if (v == this.mForeignEmailOrIdLoginView) {
            this.mOnLoginInterface.onForeignEmailOrIdLogin();
        }
    }

    public void setForeignEmailOrIdLoginState(boolean isForeignEmailOrIdLogin) {
        this.mForeignEmailOrIdLoginState = isForeignEmailOrIdLogin;
    }

    private void updateShowPasswordState() {
        this.mAccountPwdView.setInputType(SysHelper.getEditViewInputType(this.mShowPassword));
        this.mAccountPwdView.setSelection(this.mAccountPwdView.getText().length());
        this.mShowPwdImageView.setImageResource(this.mShowPassword ? R.drawable.passport_password_show : R.drawable.passport_password_not_show);
    }

    private void setForeignLoginView() {
        if (this.mForeignEmailOrIdLoginState) {
            this.mAccountNameView.setHint(R.string.passport_email_or_id_hint_text);
            return;
        }
        PhoneNumUtil.initializeCountryPhoneData(getActivity());
        this.mAreaCodeLayout.setVisibility(0);
        this.mForeignEmailOrIdLoginView.setVisibility(0);
        refreshViewStateByISO(this.mRegionChosen);
        this.mAccountNameView.setHint(getString(R.string.passport_phone_num_hint_text));
    }

    private void showFindPasswordOnWebDialog() {
        SimpleDialogFragment dialog = new AlertDialogFragmentBuilder(REQUEST_CODE_FOREIGN_LOGIN).setTitle(getString(R.string.passport_forget_password)).setMessage(getString(R.string.passport_find_password_on_web_msg)).create();
        dialog.setNegativeButton(R.string.passport_relogin, null);
        dialog.setPositiveButton(R.string.passport_skip_login, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                if (LoginInputFragment.this.mOnLoginInterface != null) {
                    LoginInputFragment.this.mOnLoginInterface.onSkipLogin();
                }
            }
        });
        dialog.show(getFragmentManager(), "FindPassword");
    }

    private void startLogin() {
        String username = this.mAccountNameView.getText().toString();
        String pwd = this.mAccountPwdView.getText().toString();
        String captchaCode = null;
        if (this.mCaptchaUrl != null) {
            captchaCode = this.mCaptchaCodeView.getText().toString();
        }
        if (TextUtils.isEmpty(username)) {
            this.mAccountNameView.setError(getString(R.string.passport_error_empty_username));
        } else if (TextUtils.isEmpty(pwd)) {
            this.mAccountPwdView.setError(getString(R.string.passport_error_empty_pwd));
        } else if (this.mCaptchaUrl == null || !TextUtils.isEmpty(captchaCode)) {
            if (this.mCountryPhoneNumData != null) {
                username = PhoneNumUtil.checkNumber(username, this.mCountryPhoneNumData);
                if (TextUtils.isEmpty(username)) {
                    this.mAccountNameView.setError(getString(R.string.passport_error_invalid_phone_num));
                    return;
                }
            }
            if (this.mLoginTask == null || Status.FINISHED == this.mLoginTask.getStatus()) {
                this.mLoginTask = new LoginTask(username, pwd, captchaCode, this.mServiceId).execute(new String[0]);
            }
        } else {
            this.mCaptchaCodeView.setError(getString(R.string.passport_error_empty_captcha_code));
        }
    }

    private void processLoginResult(LoginResult result) {
        setViewsEnabled(true);
        AccountInfo accountInfo = result.getAccountInfo();
        Context context = getActivity();
        if (accountInfo != null) {
            this.mLoginStatusView.setVisibility(ERROR_NEED_NOTIFICATION);
            MiAccountManager am = MiAccountManager.get(context);
            String userId = accountInfo.getUserId();
            Account account = new Account(userId, MiAccountManager.XIAOMI_ACCOUNT_TYPE);
            Bundle data = new Bundle();
            data.putString(Constants.EXTRA_USER_ID, userId);
            data.putString(Constants.EXTRA_XIAOMI_ACCOUNT_NAME, result.getUsername());
            data.putString(Constants.KEY_ENCRYPTED_USER_ID, accountInfo.getEncryptedUserId());
            ExtendedAuthToken eat = ExtendedAuthToken.build(accountInfo.getServiceToken(), accountInfo.getSecurity());
            ExtendedAuthToken extPass = ExtendedAuthToken.build(accountInfo.getPassToken(), accountInfo.getPsecurity());
            String authToken = eat.toPlain();
            AccountHelper.addActiveXiaomiAccount(context, account, extPass.toPlain(), data);
            if (!TextUtils.isEmpty(result.getServiceId())) {
                am.setAuthToken(account, result.getServiceId(), authToken);
            }
            if (this.mOnLoginInterface != null) {
                this.mOnLoginInterface.onLoginSuccess(userId, authToken);
                return;
            }
            return;
        }
        String reason;
        Log.w(TAG, "login failure");
        switch (result.getError()) {
            case REQUEST_CODE_FOREIGN_LOGIN /*1*/:
                reason = getString(R.string.passport_bad_authentication);
                processCaptchaInResult(result);
                break;
            case REQUEST_NOTIFICATION_LOGIN /*2*/:
                reason = getString(R.string.passport_error_network);
                break;
            case ERROR_SERVER /*3*/:
                reason = getString(R.string.passport_error_server);
                break;
            case ERROR_FORBIDDEN /*4*/:
                reason = getString(R.string.passport_access_denied);
                break;
            case ERROR_CAPTCHA /*5*/:
                reason = getString(R.string.passport_wrong_captcha);
                processCaptchaInResult(result);
                break;
            case ERROR_VERIFICATION /*6*/:
                if (this.mOnLoginInterface != null) {
                    this.mOnLoginInterface.onNeedStep2(result.getUsername(), result.getMetaLoginDataStep2().sign, result.getMetaLoginDataStep2().qs, result.getMetaLoginDataStep2().callback, result.getStep1Token(), result.getServiceId());
                    return;
                }
                return;
            case ERROR_USER_NAME /*7*/:
                reason = getString(R.string.passport_error_user_name);
                break;
            case ERROR_NEED_NOTIFICATION /*8*/:
                reason = getString(R.string.passport_error_login);
                Intent intent = new Intent("android.intent.action.VIEW");
                intent.setClass(getActivity(), NotificationActivity.class);
                intent.putExtra(com.xiaomi.account.Constants.EXTRA_NOTIFICATON_URL, result.getNotificationUrl());
                startActivityForResult(intent, REQUEST_NOTIFICATION_LOGIN);
                break;
            case ERROR_ILLEGAL_DEVICE_ID /*9*/:
                reason = getString(R.string.passport_error_device_id);
                break;
            case ERROR_SSL_HAND_SHAKE /*10*/:
                reason = getString(R.string.passport_error_ssl_hand_shake);
                break;
            default:
                reason = getString(R.string.passport_error_unknown);
                break;
        }
        Resources res = getResources();
        this.mLoginStatusView.setTextAppearance(context, R.style.Passport.LoginErrorNoticeAppearance);
        this.mLoginStatusView.setText(reason);
        this.mAccountNameView.setTextColor(res.getColor(R.color.passport_text_color_warn));
        this.mAccountPwdView.setTextColor(res.getColor(R.color.passport_text_color_warn));
        this.mCaptchaCodeView.setTextColor(res.getColor(R.color.passport_text_color_warn));
        this.mAccountNameView.setBackgroundResource(R.drawable.passport_group_first_item_warn_bg);
        this.mAccountPwdView.setBackgroundResource(this.mCaptchaAreaView.getVisibility() == 0 ? R.drawable.passport_group_middle_item_warn_bg : R.drawable.passport_group_last_item_warn_bg);
        this.mCaptchaAreaView.setBackgroundResource(R.drawable.passport_group_last_item_warn_bg);
        this.mAccountNameView.addTextChangedListener(this.mTextWatcher);
        this.mAccountPwdView.addTextChangedListener(this.mTextWatcher);
        this.mCaptchaCodeView.addTextChangedListener(this.mTextWatcher);
        this.mInWarningState = true;
    }

    private void processCaptchaInResult(LoginResult result) {
        if (!TextUtils.equals(result.getCaptchaUrl(), this.mCaptchaUrl)) {
            this.mCaptchaUrl = result.getCaptchaUrl();
            if (this.mCaptchaUrl != null) {
                this.mCaptchaAreaView.setVisibility(0);
                startDownloadingCaptcha();
                return;
            }
            this.mCaptchaAreaView.setVisibility(ERROR_NEED_NOTIFICATION);
        }
    }

    private void startDownloadingCaptcha() {
        if (this.mDownloadCaptchaTask != null) {
            this.mDownloadCaptchaTask.cancel(true);
        }
        this.mDownloadCaptchaTask = new DownloadCaptchaTask(this.mCaptchaUrl);
        this.mDownloadCaptchaTask.execute(new Void[0]);
    }
}
