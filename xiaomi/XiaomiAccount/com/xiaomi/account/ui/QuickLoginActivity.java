package com.xiaomi.account.ui;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Notification.Builder;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.AsyncTask.Status;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.xiaomi.account.R;
import com.xiaomi.account.utils.CloudHelper;
import com.xiaomi.account.utils.SysHelper;
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
import com.xiaomi.passport.Constants;
import com.xiaomi.passport.accountmanager.IAccountAuthenticatorResponse;
import com.xiaomi.passport.accountmanager.MiAccountManager;
import com.xiaomi.passport.data.SetupData;
import com.xiaomi.passport.exception.IllegalDeviceException;
import com.xiaomi.passport.ui.AccountUnactivatedFragment;
import com.xiaomi.passport.ui.NotificationActivity;
import com.xiaomi.passport.ui.SimpleDialogFragment;
import com.xiaomi.passport.ui.SimpleDialogFragment.AlertDialogFragmentBuilder;
import com.xiaomi.passport.utils.AccountHelper;
import java.io.IOException;
import javax.net.ssl.SSLHandshakeException;
import miui.app.Activity;
import miui.app.AlertDialog;
import miui.os.Build;

public class QuickLoginActivity extends Activity implements OnClickListener {
    private static final boolean DEBUG = true;
    private static final int ERROR_CAPTCHA = 5;
    private static final int ERROR_FORBIDDEN = 4;
    private static final int ERROR_ILLEGAL_DEVICE_ID = 8;
    private static final int ERROR_NEED_NOTIFICATION = 9;
    private static final int ERROR_NETWORK = 2;
    private static final int ERROR_PASSWORD = 1;
    private static final int ERROR_SERVER = 3;
    private static final int ERROR_SSL_HAND_SHAKE = 10;
    private static final int ERROR_USER_NAME = 7;
    private static final int ERROR_VERIFICATION = 6;
    private static final int NOTIFICATION_ID = 0;
    private static final int REQUEST_NOTIFICATION_LOGIN = 2;
    private static final String TAG = "QuickLoginActivity";
    private Account mAccount;
    private IAccountAuthenticatorResponse mAccountAuthenticatorResponse;
    private Button mButtonCancel;
    private Button mButtonConfirm;
    private View mCaptchaArea;
    private EditText mCaptchaCodeView;
    private ImageView mCaptchaImageView;
    private String mCaptchaUrl;
    private DownloadCaptchaTask mDownloadCaptchaTask;
    private TextView mForgetPwdView;
    private volatile String mIck;
    private View mInnerContentStep2View;
    private View mInnerContentView;
    private StringContent mLoginContent;
    private LoginStep2Task mLoginStep2Task;
    private LoginTask mLoginTask;
    private volatile MetaLoginData mMetaLoginData;
    private volatile MetaLoginData mMetaLoginDataStep2;
    private EditText mPasswordView;
    private String mServiceTokenLocation;
    private String mServiceUrl;
    private boolean mShowPassword;
    private ImageView mShowPwdImageView;
    private volatile String mStep1Token;
    private String mTitle;
    private TextView mTitleView;
    private CheckBox mTrustDeviceView;
    private String mUserId;
    private EditText mVCodeView;
    private boolean mVerifyOnly;

    private class DownloadCaptchaTask extends AsyncTask<Void, Void, Pair<Bitmap, String>> {
        private final String mCaptchaUrl;

        public DownloadCaptchaTask(String captchaUrl) {
            this.mCaptchaUrl = captchaUrl;
        }

        protected Pair<Bitmap, String> doInBackground(Void... voids) {
            return CloudHelper.getCaptchaImage(this.mCaptchaUrl);
        }

        protected void onPostExecute(Pair<Bitmap, String> result) {
            if (result != null) {
                QuickLoginActivity.this.mCaptchaImageView.setImageBitmap((Bitmap) result.first);
                QuickLoginActivity.this.mIck = (String) result.second;
            }
        }
    }

    private class LoginResult {
        final AccountInfo accountInfo;
        final String captchaUrl;
        final int error;
        final MetaLoginData metaLoginDataStep2;
        final String notificationUrl;
        final String step1Token;

        private LoginResult(AccountInfo accountInfo, int error, String captchaUrl, MetaLoginData metaLoginDataStep2, String step1Token, String notificationUrl) {
            this.accountInfo = accountInfo;
            this.error = error;
            this.captchaUrl = captchaUrl;
            this.metaLoginDataStep2 = metaLoginDataStep2;
            this.step1Token = step1Token;
            this.notificationUrl = notificationUrl;
        }

        private LoginResult(QuickLoginActivity quickLoginActivity, AccountInfo accountInfo, int error, String captchaUrl, MetaLoginData metaLoginDataStep2, String step1Token) {
            this(accountInfo, error, captchaUrl, metaLoginDataStep2, step1Token, null);
        }

        private LoginResult(QuickLoginActivity quickLoginActivity, AccountInfo accountInfo, int error) {
            this(quickLoginActivity, accountInfo, error, null, null, null);
        }
    }

    private abstract class LoginTaskBase extends AsyncTask<Void, Void, LoginResult> {
        private SimpleDialogFragment mProgressDialog;

        private LoginTaskBase() {
        }

        protected void onPreExecute() {
            this.mProgressDialog = new AlertDialogFragmentBuilder(QuickLoginActivity.REQUEST_NOTIFICATION_LOGIN).setMessage(QuickLoginActivity.this.getString(R.string.checking_account)).create();
            this.mProgressDialog.setOnDismissListener(new OnDismissListener() {
                public void onDismiss(DialogInterface dialog) {
                    LoginTaskBase.this.cancel(QuickLoginActivity.DEBUG);
                }
            });
            this.mProgressDialog.show(QuickLoginActivity.this.getFragmentManager(), "LoginProgress");
        }

        protected void onPostExecute(LoginResult result) {
            boolean noDialog = QuickLoginActivity.DEBUG;
            if (!QuickLoginActivity.this.isFinishing()) {
                this.mProgressDialog.dismissAllowingStateLoss();
                AccountInfo accountInfo = result.accountInfo;
                Bundle bundle;
                Intent intent;
                if (accountInfo != null) {
                    AccountManager am = AccountManager.get(QuickLoginActivity.this);
                    QuickLoginActivity.this.mStep1Token = null;
                    QuickLoginActivity.this.mMetaLoginDataStep2 = null;
                    QuickLoginActivity.this.mCaptchaUrl = null;
                    String serviceToken = accountInfo.getServiceToken();
                    String extToken = null;
                    if (serviceToken != null) {
                        extToken = ExtendedAuthToken.build(serviceToken, accountInfo.getSecurity()).toPlain();
                    }
                    am.setPassword(QuickLoginActivity.this.mAccount, ExtendedAuthToken.build(accountInfo.getPassToken(), accountInfo.getPsecurity()).toPlain());
                    if (extToken == null || QuickLoginActivity.this.mServiceUrl == null) {
                        Log.i(QuickLoginActivity.TAG, "account authenticated without service id");
                    } else {
                        am.setAuthToken(QuickLoginActivity.this.mAccount, QuickLoginActivity.this.mServiceUrl, extToken);
                    }
                    Log.i(QuickLoginActivity.TAG, "quick login success, account:" + accountInfo.getUserId() + ", service token:" + accountInfo.getServiceToken());
                    bundle = new Bundle();
                    bundle.putString(MiAccountManager.KEY_ACCOUNT_NAME, accountInfo.getUserId());
                    bundle.putString(MiAccountManager.KEY_ACCOUNT_TYPE, MiAccountManager.XIAOMI_ACCOUNT_TYPE);
                    bundle.putString(MiAccountManager.KEY_AUTHTOKEN, extToken);
                    bundle.putBoolean(MiAccountManager.KEY_BOOLEAN_RESULT, QuickLoginActivity.DEBUG);
                    QuickLoginActivity.this.handleResponse(bundle);
                    QuickLoginActivity.this.setResult(-1);
                    if (!QuickLoginActivity.this.mVerifyOnly) {
                        intent = new Intent("android.accounts.LOGIN_ACCOUNTS_POST_CHANGED");
                        intent.putExtra(Constants.EXTRA_ACCOUNT, QuickLoginActivity.this.mAccount);
                        intent.putExtra("extra_update_type", QuickLoginActivity.REQUEST_NOTIFICATION_LOGIN);
                        QuickLoginActivity.this.sendBroadcast(intent);
                    }
                    SysHelper.setSoftInputVisibility(QuickLoginActivity.this, QuickLoginActivity.REQUEST_NOTIFICATION_LOGIN);
                    QuickLoginActivity.this.finish();
                    return;
                }
                String reason;
                Log.v(QuickLoginActivity.TAG, "login failure");
                switch (result.error) {
                    case QuickLoginActivity.ERROR_PASSWORD /*1*/:
                        reason = QuickLoginActivity.this.getString(R.string.bad_authentication);
                        QuickLoginActivity.this.applyCaptchaUrl(result.captchaUrl);
                        QuickLoginActivity.this.mStep1Token = null;
                        QuickLoginActivity.this.mMetaLoginDataStep2 = null;
                        QuickLoginActivity.this.switchStage();
                        break;
                    case QuickLoginActivity.REQUEST_NOTIFICATION_LOGIN /*2*/:
                        reason = QuickLoginActivity.this.getString(R.string.error_network);
                        break;
                    case QuickLoginActivity.ERROR_SERVER /*3*/:
                        reason = QuickLoginActivity.this.getString(R.string.error_server);
                        break;
                    case QuickLoginActivity.ERROR_FORBIDDEN /*4*/:
                        reason = QuickLoginActivity.this.getString(R.string.access_denied);
                        break;
                    case QuickLoginActivity.ERROR_CAPTCHA /*5*/:
                        reason = QuickLoginActivity.this.getString(R.string.wrong_captcha);
                        QuickLoginActivity.this.applyCaptchaUrl(result.captchaUrl);
                        break;
                    case QuickLoginActivity.ERROR_VERIFICATION /*6*/:
                        if (QuickLoginActivity.this.mVerifyOnly) {
                            bundle = new Bundle();
                            bundle.putString(MiAccountManager.KEY_ACCOUNT_NAME, QuickLoginActivity.this.mAccount.name);
                            bundle.putBoolean(MiAccountManager.KEY_BOOLEAN_RESULT, QuickLoginActivity.DEBUG);
                            QuickLoginActivity.this.handleResponse(bundle);
                            QuickLoginActivity.this.setResult(-1);
                            SysHelper.setSoftInputVisibility(QuickLoginActivity.this, QuickLoginActivity.REQUEST_NOTIFICATION_LOGIN);
                            QuickLoginActivity.this.finish();
                            return;
                        }
                        reason = QuickLoginActivity.this.getString(R.string.wrong_vcode);
                        if (QuickLoginActivity.this.mStep1Token != null) {
                            noDialog = false;
                        }
                        QuickLoginActivity.this.mStep1Token = result.step1Token;
                        QuickLoginActivity.this.mMetaLoginDataStep2 = result.metaLoginDataStep2;
                        QuickLoginActivity.this.switchStage();
                        if (noDialog) {
                            return;
                        }
                        break;
                    case QuickLoginActivity.ERROR_USER_NAME /*7*/:
                        reason = QuickLoginActivity.this.getString(R.string.error_user_name);
                        QuickLoginActivity.this.mStep1Token = null;
                        QuickLoginActivity.this.mMetaLoginDataStep2 = null;
                        QuickLoginActivity.this.switchStage();
                        break;
                    case QuickLoginActivity.ERROR_ILLEGAL_DEVICE_ID /*8*/:
                        reason = QuickLoginActivity.this.getString(R.string.error_device_id);
                        break;
                    case QuickLoginActivity.ERROR_NEED_NOTIFICATION /*9*/:
                        intent = new Intent(QuickLoginActivity.this, NotificationActivity.class);
                        intent.putExtra(com.xiaomi.account.Constants.EXTRA_NOTIFICATON_URL, result.notificationUrl);
                        QuickLoginActivity.this.startActivityForResult(intent, QuickLoginActivity.REQUEST_NOTIFICATION_LOGIN);
                        return;
                    case QuickLoginActivity.ERROR_SSL_HAND_SHAKE /*10*/:
                        reason = QuickLoginActivity.this.getString(R.string.error_ssl_hand_shake);
                        break;
                    default:
                        reason = QuickLoginActivity.this.getString(R.string.error_unknown);
                        break;
                }
                QuickLoginActivity.this.showAlertDialog(reason);
            }
        }
    }

    private class LoginStep2Task extends LoginTaskBase {
        private boolean trust;
        private String userName;
        private String vcode;

        private LoginStep2Task(String userName, String vcode, boolean trust) {
            super();
            this.userName = userName;
            this.vcode = vcode;
            this.trust = trust;
        }

        protected LoginResult doInBackground(Void... voids) {
            try {
                AccountInfo accountInfo = AccountHelper.getServiceTokenByStep2(this.userName, this.vcode, QuickLoginActivity.this.mMetaLoginDataStep2, this.trust, QuickLoginActivity.this.mStep1Token, QuickLoginActivity.this.mServiceUrl);
                if (accountInfo != null) {
                    return new LoginResult(accountInfo, -1);
                }
                return new LoginResult(null, QuickLoginActivity.ERROR_SERVER);
            } catch (SSLHandshakeException e) {
                Log.w(QuickLoginActivity.TAG, "LoginStep2Task", e);
                return new LoginResult(null, QuickLoginActivity.ERROR_SSL_HAND_SHAKE);
            } catch (IOException e2) {
                Log.w(QuickLoginActivity.TAG, "LoginStep2Task", e2);
                return new LoginResult(null, QuickLoginActivity.REQUEST_NOTIFICATION_LOGIN);
            } catch (IllegalDeviceException e3) {
                Log.w(QuickLoginActivity.TAG, "LoginStep2Task", e3);
                return new LoginResult(null, QuickLoginActivity.ERROR_ILLEGAL_DEVICE_ID);
            } catch (InvalidResponseException e4) {
                Log.w(QuickLoginActivity.TAG, "LoginStep2Task", e4);
                e4.printStackTrace();
                return new LoginResult(null, QuickLoginActivity.ERROR_SERVER);
            } catch (InvalidCredentialException e5) {
                String captchaUrl = e5.getCaptchaUrl();
                Log.w(QuickLoginActivity.TAG, "InvalidCredentialException with captchaUrl=" + captchaUrl);
                return new LoginResult(null, (int) QuickLoginActivity.ERROR_PASSWORD, captchaUrl, null, null);
            } catch (AccessDeniedException e6) {
                Log.w(QuickLoginActivity.TAG, "LoginStep2Task", e6);
                e6.printStackTrace();
                return new LoginResult(null, QuickLoginActivity.ERROR_FORBIDDEN);
            } catch (AuthenticationFailureException e7) {
                Log.w(QuickLoginActivity.TAG, "LoginStep2Task", e7);
                return new LoginResult(null, QuickLoginActivity.ERROR_SERVER);
            } catch (NeedVerificationException e8) {
                Log.w(QuickLoginActivity.TAG, "NeedVerificationException with step1Token");
                String step1Token = e8.getStep1Token();
                return new LoginResult(null, (int) QuickLoginActivity.ERROR_VERIFICATION, null, e8.getMetaLoginData(), step1Token);
            } catch (InvalidUserNameException e9) {
                Log.w(QuickLoginActivity.TAG, "LoginStep2Task", e9);
                return new LoginResult(null, QuickLoginActivity.ERROR_USER_NAME);
            }
        }
    }

    private class LoginTask extends LoginTaskBase {
        private String captchaCode;
        private String password;
        private String userName;

        private LoginTask(String userName, String password, String captchaCode) {
            super();
            this.userName = userName;
            this.password = password;
            this.captchaCode = captchaCode;
        }

        protected LoginResult doInBackground(Void... voids) {
            String captchaUrl;
            try {
                AccountInfo accountInfo;
                if (QuickLoginActivity.this.mMetaLoginData == null) {
                    QuickLoginActivity.this.mMetaLoginData = AccountHelper.getMetaLoginData(QuickLoginActivity.this.mAccount.name, QuickLoginActivity.this.mServiceUrl);
                    if (QuickLoginActivity.this.mMetaLoginData == null) {
                        Log.w(QuickLoginActivity.TAG, "Empty meta login data");
                        return new LoginResult(null, QuickLoginActivity.ERROR_SERVER);
                    }
                }
                if (QuickLoginActivity.this.mServiceTokenLocation == null) {
                    accountInfo = AccountHelper.getServiceTokenByPassword(this.userName, this.password, this.captchaCode, QuickLoginActivity.this.mIck, QuickLoginActivity.this.mMetaLoginData, QuickLoginActivity.this.mServiceUrl, QuickLoginActivity.DEBUG);
                } else {
                    accountInfo = AccountHelper.parseLoginResult(QuickLoginActivity.this.mUserId, QuickLoginActivity.this.mLoginContent, QuickLoginActivity.this.mServiceUrl, QuickLoginActivity.this.mServiceTokenLocation);
                }
                if (accountInfo != null) {
                    return new LoginResult(accountInfo, -1);
                }
                return new LoginResult(null, QuickLoginActivity.ERROR_SERVER);
            } catch (Throwable e) {
                Log.w(QuickLoginActivity.TAG, "LoginTask", e);
                return new LoginResult(null, QuickLoginActivity.ERROR_SSL_HAND_SHAKE);
            } catch (Throwable e2) {
                Log.w(QuickLoginActivity.TAG, "LoginTask", e2);
                return new LoginResult(null, QuickLoginActivity.REQUEST_NOTIFICATION_LOGIN);
            } catch (Throwable e22) {
                Log.w(QuickLoginActivity.TAG, "LoginTask", e22);
                return new LoginResult(null, QuickLoginActivity.ERROR_ILLEGAL_DEVICE_ID);
            } catch (InvalidResponseException e3) {
                Log.w(QuickLoginActivity.TAG, "LoginTask", e3);
                return new LoginResult(null, QuickLoginActivity.ERROR_SERVER);
            } catch (InvalidCredentialException e4) {
                captchaUrl = e4.getCaptchaUrl();
                Log.w(QuickLoginActivity.TAG, "InvalidCredentialException with captchaUrl=" + captchaUrl);
                return new LoginResult(null, (int) QuickLoginActivity.ERROR_PASSWORD, captchaUrl, null, null);
            } catch (AccessDeniedException e5) {
                Log.w(QuickLoginActivity.TAG, "LoginTask", e5);
                return new LoginResult(null, QuickLoginActivity.ERROR_FORBIDDEN);
            } catch (AuthenticationFailureException e6) {
                Log.w(QuickLoginActivity.TAG, "LoginTask", e6);
                return new LoginResult(null, QuickLoginActivity.ERROR_SERVER);
            } catch (NeedVerificationException e7) {
                Log.w(QuickLoginActivity.TAG, "NeedVerificationException with step1Token");
                String step1Token = e7.getStep1Token();
                return new LoginResult(null, (int) QuickLoginActivity.ERROR_VERIFICATION, null, e7.getMetaLoginData(), step1Token);
            } catch (NeedCaptchaException e8) {
                captchaUrl = e8.getCaptchaUrl();
                Log.w(QuickLoginActivity.TAG, "NeedCaptchaException with captchaUrl=" + captchaUrl);
                return new LoginResult(null, (int) QuickLoginActivity.ERROR_CAPTCHA, captchaUrl, null, null);
            } catch (InvalidUserNameException e9) {
                Log.w(QuickLoginActivity.TAG, "InvalidUserNameException", e9);
                return new LoginResult(null, QuickLoginActivity.ERROR_USER_NAME);
            } catch (NeedNotificationException e10) {
                String notificationUrl = e10.getNotificationUrl();
                QuickLoginActivity.this.mUserId = e10.getUserId();
                QuickLoginActivity.this.mLoginContent = e10.getLoginContent();
                Log.w(QuickLoginActivity.TAG, "login exception");
                return new LoginResult(null, QuickLoginActivity.ERROR_NEED_NOTIFICATION, null, null, null, notificationUrl);
            }
        }
    }

    public QuickLoginActivity() {
        this.mServiceTokenLocation = null;
    }

    protected void onCreate(Bundle savedInstanceState) {
        String string;
        if (Build.IS_TABLET) {
            setTheme(R.style.Theme.Dialog.Alert);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.micloud_quick_login);
        this.mButtonCancel = (Button) findViewById(R.id.cancel);
        this.mButtonConfirm = (Button) findViewById(R.id.confirm);
        this.mPasswordView = (EditText) findViewById(R.id.password);
        this.mForgetPwdView = (TextView) findViewById(R.id.tv_forget_pwd);
        this.mShowPwdImageView = (ImageView) findViewById(R.id.show_password_img);
        this.mCaptchaArea = findViewById(R.id.captcha_area);
        this.mCaptchaCodeView = (EditText) findViewById(R.id.captcha_code);
        this.mCaptchaImageView = (ImageView) findViewById(R.id.captcha_image);
        this.mInnerContentView = findViewById(R.id.inner_content);
        this.mInnerContentStep2View = findViewById(R.id.inner_content_step2);
        this.mVCodeView = (EditText) findViewById(R.id.vcode);
        this.mTrustDeviceView = (CheckBox) findViewById(R.id.trust_device);
        this.mTitleView = (TextView) findViewById(16908310);
        this.mButtonCancel.setOnClickListener(this);
        this.mButtonConfirm.setOnClickListener(this);
        this.mForgetPwdView.setOnClickListener(this);
        this.mShowPwdImageView.setOnClickListener(this);
        this.mShowPassword = false;
        updateShowPasswordState();
        Intent intent = getIntent();
        this.mVerifyOnly = intent.getBooleanExtra(Constants.EXTRA_VERIFY_ONLY, false);
        this.mServiceUrl = intent.getStringExtra(Constants.EXTRA_SERVICE_URL);
        this.mStep1Token = intent.getStringExtra(Constants.EXTRA_STEP1_TOKEN);
        if (intent.getStringExtra(Constants.EXTRA_TITLE) == null) {
            string = getString(R.string.quick_login_title);
        } else {
            string = intent.getStringExtra(Constants.EXTRA_TITLE);
        }
        this.mTitle = string;
        String sign = intent.getStringExtra(Constants.EXTRA_SIGN);
        String qs = intent.getStringExtra(Constants.EXTRA_QS);
        String callback = intent.getStringExtra(Constants.EXTRA_CALLBACK);
        if (!(sign == null || qs == null || callback == null)) {
            this.mMetaLoginDataStep2 = new MetaLoginData(sign, qs, callback);
        }
        applyCaptchaUrl(intent.getStringExtra(Constants.EXTRA_CAPTCHA_URL));
        Account[] accounts = ((AccountManager) getSystemService(AccountUnactivatedFragment.EXTRA_ACCOUNT)).getAccountsByType(MiAccountManager.XIAOMI_ACCOUNT_TYPE);
        if (accounts.length > 0) {
            this.mAccount = accounts[NOTIFICATION_ID];
            TextView textView = (TextView) findViewById(R.id.account_name);
            Object[] objArr = new Object[ERROR_PASSWORD];
            objArr[NOTIFICATION_ID] = this.mAccount.name;
            textView.setText(getString(R.string.account_name, objArr));
        } else {
            finish();
        }
        this.mAccountAuthenticatorResponse = SetupData.getAccountAuthenticatorResponse();
        if (this.mAccountAuthenticatorResponse != null) {
            this.mAccountAuthenticatorResponse.onRequestContinued();
        }
        this.mCaptchaImageView.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                if (QuickLoginActivity.this.mCaptchaUrl != null) {
                    QuickLoginActivity.this.startDownloadingCaptcha();
                }
            }
        });
        switchStage();
        setDialogSize();
    }

    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        setDialogSize();
    }

    public void setDialogSize() {
        LayoutParams p = getWindow().getAttributes();
        if (Build.IS_TABLET) {
            p.width = getResources().getDimensionPixelSize(R.dimen.quick_login_dialog_width);
        } else {
            p.gravity = 80;
            p.width = -1;
        }
        getWindow().setAttributes(p);
    }

    private void switchStage() {
        if (this.mStep1Token == null) {
            this.mInnerContentStep2View.setVisibility(ERROR_ILLEGAL_DEVICE_ID);
            this.mInnerContentView.setVisibility(NOTIFICATION_ID);
            this.mTitleView.setText(this.mTitle);
            return;
        }
        this.mInnerContentView.setVisibility(ERROR_ILLEGAL_DEVICE_ID);
        this.mInnerContentStep2View.setVisibility(NOTIFICATION_ID);
        this.mTitleView.setText(R.string.quick_login_step2_title);
    }

    private void applyCaptchaUrl(String captchaUrl) {
        if (!TextUtils.equals(captchaUrl, this.mCaptchaUrl)) {
            this.mCaptchaUrl = captchaUrl;
            if (this.mCaptchaUrl != null) {
                this.mCaptchaArea.setVisibility(NOTIFICATION_ID);
                startDownloadingCaptcha();
                return;
            }
            this.mCaptchaArea.setVisibility(ERROR_ILLEGAL_DEVICE_ID);
        }
    }

    private void updateShowPasswordState() {
        this.mPasswordView.setInputType(SysHelper.getEditViewInputType(this.mShowPassword));
        this.mPasswordView.setSelection(this.mPasswordView.getText().length());
        this.mShowPwdImageView.setImageResource(this.mShowPassword ? R.drawable.passport_password_show : R.drawable.passport_password_not_show);
    }

    public void onClick(View view) {
        if (this.mButtonCancel == view) {
            finish();
        } else if (this.mButtonConfirm == view) {
            confirm();
        } else if (this.mForgetPwdView == view) {
            SysHelper.getbackPassword(this);
        } else if (this.mShowPwdImageView == view) {
            this.mShowPassword = !this.mShowPassword ? DEBUG : false;
            updateShowPasswordState();
        }
    }

    private void confirm() {
        if (this.mStep1Token == null) {
            String password = this.mPasswordView.getText().toString();
            String captchaCode = null;
            if (this.mCaptchaUrl != null) {
                captchaCode = this.mCaptchaCodeView.getText().toString();
            }
            if (TextUtils.isEmpty(password)) {
                this.mPasswordView.setError(getString(R.string.micloud_error_empty_pwd));
                return;
            } else if (this.mCaptchaUrl != null && TextUtils.isEmpty(captchaCode)) {
                this.mCaptchaCodeView.setError(getString(R.string.micloud_error_empty_captcha_code));
                return;
            } else if (this.mLoginTask == null || Status.FINISHED.equals(this.mLoginTask.getStatus())) {
                this.mLoginTask = new LoginTask(this.mAccount.name, password, captchaCode);
                this.mLoginTask.execute(new Void[NOTIFICATION_ID]);
                return;
            } else {
                return;
            }
        }
        String vcode = this.mVCodeView.getText().toString();
        boolean trust = this.mTrustDeviceView.isChecked();
        if (TextUtils.isEmpty(vcode)) {
            this.mVCodeView.setError(getString(R.string.micloud_error_empty_pwd));
        } else if (this.mLoginStep2Task == null || Status.FINISHED.equals(this.mLoginStep2Task.getStatus())) {
            this.mLoginStep2Task = new LoginStep2Task(this.mAccount.name, vcode, trust);
            this.mLoginStep2Task.execute(new Void[NOTIFICATION_ID]);
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_NOTIFICATION_LOGIN /*2*/:
                if (resultCode == -1) {
                    this.mServiceTokenLocation = data.getStringExtra("location");
                    confirm();
                    return;
                }
                return;
            default:
                return;
        }
    }

    protected void onStop() {
        if (this.mStep1Token != null) {
            Intent intent = new Intent(this, QuickLoginActivity.class);
            intent.putExtra(Constants.EXTRA_SERVICE_URL, this.mServiceUrl);
            intent.putExtra(Constants.EXTRA_STEP1_TOKEN, this.mStep1Token);
            intent.putExtra(Constants.EXTRA_SIGN, this.mMetaLoginDataStep2.sign);
            intent.putExtra(Constants.EXTRA_QS, this.mMetaLoginDataStep2.qs);
            intent.putExtra(Constants.EXTRA_CALLBACK, this.mMetaLoginDataStep2.callback);
            ((NotificationManager) getSystemService("notification")).notify(NOTIFICATION_ID, new Builder(this).setAutoCancel(false).setSmallIcon(17301642).setContentIntent(PendingIntent.getActivity(this, NOTIFICATION_ID, intent, 134217728)).setContentTitle(getString(R.string.vcode_notification_title)).setContentText(getString(R.string.vcode_prompt_long)).build());
        }
        super.onStop();
    }

    protected void onStart() {
        super.onStart();
        ((NotificationManager) getSystemService("notification")).cancel(NOTIFICATION_ID);
    }

    protected void onDestroy() {
        if (this.mLoginTask != null) {
            this.mLoginTask.cancel(DEBUG);
            this.mLoginTask = null;
        }
        if (this.mLoginStep2Task != null) {
            this.mLoginStep2Task.cancel(DEBUG);
            this.mLoginStep2Task = null;
        }
        if (this.mDownloadCaptchaTask != null) {
            this.mDownloadCaptchaTask.cancel(DEBUG);
            this.mDownloadCaptchaTask = null;
        }
        handleResponse(null);
        super.onDestroy();
    }

    public void finish() {
        handleResponse(null);
        super.finish();
        overridePendingTransition(NOTIFICATION_ID, NOTIFICATION_ID);
    }

    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(NOTIFICATION_ID, NOTIFICATION_ID);
    }

    public boolean onTouchEvent(MotionEvent event) {
        if (!super.onTouchEvent(event)) {
            return false;
        }
        overridePendingTransition(NOTIFICATION_ID, NOTIFICATION_ID);
        return DEBUG;
    }

    protected void handleResponse(Bundle resultBundle) {
        if (this.mAccountAuthenticatorResponse != null) {
            if (resultBundle != null) {
                this.mAccountAuthenticatorResponse.onResult(resultBundle);
            } else {
                this.mAccountAuthenticatorResponse.onError(ERROR_FORBIDDEN, "canceled");
            }
            this.mAccountAuthenticatorResponse = null;
        }
    }

    private void showAlertDialog(String reason) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        if (getIntent() != null) {
            builder.setTitle(R.string.verification_failed);
        } else {
            builder.setTitle(R.string.login_failed);
        }
        builder.setMessage(reason);
        builder.setPositiveButton(17039370, null);
        builder.show();
    }

    private void startDownloadingCaptcha() {
        if (this.mDownloadCaptchaTask != null) {
            this.mDownloadCaptchaTask.cancel(DEBUG);
        }
        this.mDownloadCaptchaTask = new DownloadCaptchaTask(this.mCaptchaUrl);
        this.mDownloadCaptchaTask.execute(new Void[NOTIFICATION_ID]);
    }
}
