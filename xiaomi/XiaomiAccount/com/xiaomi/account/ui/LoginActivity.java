package com.xiaomi.account.ui;

import android.accounts.AccountAuthenticatorResponse;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import com.xiaomi.account.R;
import com.xiaomi.account.XiaomiAccountTaskService;
import com.xiaomi.account.utils.FriendlyFragmentUtils;
import com.xiaomi.account.utils.SysHelper;
import com.xiaomi.accounts.AccountManager;
import com.xiaomi.passport.Constants;
import com.xiaomi.passport.accountmanager.MiAccountManager;
import com.xiaomi.passport.accountmanager.SystemAccountAuthenticatorResponse;
import com.xiaomi.passport.data.SetupData;
import com.xiaomi.passport.ui.LoginInputFragment;
import com.xiaomi.passport.ui.LoginInputFragment.OnLoginInterface;
import com.xiaomi.passport.ui.LoginStep2InputFragment;
import com.xiaomi.passport.utils.AccountHelper;
import miui.analytics.Analytics;
import miui.app.ActionBar;
import miui.app.Activity;
import miui.os.Build;

public class LoginActivity extends Activity implements OnLoginInterface, LoginStep2InputFragment.OnLoginInterface {
    private static final boolean DEBUG = true;
    private static final int SHOW_SYNC_REQUEST_CODE = 1;
    private static final String TAG = "LoginActivity";
    private View mActionBarView;
    private boolean mActivityStatusSaved;
    private Analytics mAnalytics;
    private String mAuthToken;
    private int mBindSnsType;
    private boolean mDisableBackKey;
    private boolean mLoginSuccess;
    private boolean mOnSetupGuide;
    private String mPackageName;
    private boolean mShowAccountSettings;
    private boolean mShowSyncSettings;
    private String mUserId;

    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        this.mOnSetupGuide = intent.getBooleanExtra(Constants.EXTRA_SHOW_SKIP_LOGIN, false);
        super.onCreate(savedInstanceState);
        this.mShowAccountSettings = intent.getBooleanExtra(Constants.EXTRA_SHOW_ACCOUNT_SETTINGS, false);
        this.mShowSyncSettings = intent.getBooleanExtra(Constants.EXTRA_SHOW_SYNC_SETTINGS, DEBUG);
        this.mDisableBackKey = intent.getBooleanExtra(Constants.EXTRA_DISABLE_BACK_KEY, false);
        this.mBindSnsType = intent.getIntExtra(Constants.EXTRA_BIND_SNS_TYPE, -1);
        this.mPackageName = intent.getStringExtra(AccountManager.KEY_ANDROID_PACKAGE_NAME);
        AccountAuthenticatorResponse response = (AccountAuthenticatorResponse) intent.getParcelableExtra(MiAccountManager.KEY_ACCOUNT_AUTHENTICATOR_RESPONSE);
        if (response != null) {
            SetupData.setAccountAuthenticatorResponse(new SystemAccountAuthenticatorResponse(response));
        }
        if (Build.IS_TABLET) {
            setContentView(R.layout.passport_activity_login_reg_layout);
            ((TextView) findViewById(16908310)).setText(R.string.login_title);
        } else {
            ActionBar actionBar = getActionBar();
            if (actionBar != null) {
                actionBar.setTitle(R.string.login_title);
            }
        }
        if (getFragmentManager().findFragmentByTag(LoginStep2InputFragment.class.getSimpleName()) == null) {
            LoginInputFragment f = new LoginInputFragment();
            f.setArguments(intent.getExtras());
            f.setOnLoginInterface(this);
            if (Build.IS_TABLET) {
                FriendlyFragmentUtils.addFragment(getFragmentManager(), R.id.content_fragment, f);
            } else {
                FriendlyFragmentUtils.addFragment(getFragmentManager(), 16908290, f);
            }
        }
        SetupData.setRegAccount(null);
        this.mAnalytics = Analytics.getInstance();
        this.mAnalytics.startSession(this);
    }

    protected void onResume() {
        super.onResume();
        this.mActivityStatusSaved = false;
        if (!Build.IS_TABLET) {
            SysHelper.setOrientationPortrait(this);
        }
    }

    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        this.mActivityStatusSaved = DEBUG;
    }

    protected void onDestroy() {
        this.mAnalytics.endSession();
        super.onDestroy();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case SHOW_SYNC_REQUEST_CODE /*1*/:
                if (resultCode == -1) {
                    finishAndReturn();
                    break;
                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void onBackPressed() {
        if (!this.mDisableBackKey) {
            super.onBackPressed();
        }
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 16908332:
                onBackPressed();
                return DEBUG;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void finish() {
        if (this.mLoginSuccess) {
            setResult(-1);
        }
        super.finish();
    }

    private void skipLogin() {
        AccountHelper.setLoginResultAndFinish(this, -1);
    }

    private void popupFragment() {
        getFragmentManager().popBackStack();
    }

    private void replaceFragment(Fragment f, boolean popupTop, boolean addToBackStack) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        if (popupTop) {
            popupFragment();
        }
        transaction.setTransition(4099);
        if (Build.IS_TABLET) {
            transaction.replace(R.id.content_fragment, f, f.getClass().getSimpleName());
        } else {
            transaction.replace(16908290, f, f.getClass().getSimpleName());
        }
        if (addToBackStack) {
            transaction.addToBackStack(null);
        }
        transaction.commitAllowingStateLoss();
    }

    private void processLoginResult(String userId, String authToken) {
        this.mUserId = userId;
        this.mAuthToken = authToken;
        XiaomiAccountTaskService.startQueryUserData(this);
        if (!this.mActivityStatusSaved) {
            if (this.mShowSyncSettings) {
                Log.i(TAG, "Sync Settings: start!");
                Bundle args = new Bundle();
                args.putBoolean(Constants.EXTRA_SHOW_SKIP_LOGIN, this.mOnSetupGuide);
                args.putString(AccountManager.KEY_ANDROID_PACKAGE_NAME, this.mPackageName);
                Intent showSyncIntent = new Intent("com.xiaomi.action.MICLOUD_SYNC_DATA_SETTINGS");
                showSyncIntent.putExtras(args);
                startActivityForResult(showSyncIntent, SHOW_SYNC_REQUEST_CODE);
                return;
            }
            finishAndReturn();
        }
    }

    private void finishAndReturn() {
        if (this.mShowAccountSettings) {
            startActivity(new Intent(this, AccountSettingsActivity.class));
        }
        AccountHelper.setLoginResultAndFinish(this, -1, this.mUserId, this.mAuthToken);
    }

    public void onStartLogin() {
        this.mLoginSuccess = false;
    }

    public void onLoginSuccess(String userId, String authToken) {
        this.mLoginSuccess = DEBUG;
        processLoginResult(userId, authToken);
    }

    public void onNeedStep2(String userName, String sign, String qs, String callback, String step1Token, String serviceId) {
        Bundle args = new Bundle();
        args.putString(Constants.EXTRA_USER_NAME, userName);
        args.putString(Constants.EXTRA_SERVICE_URL, serviceId);
        args.putString(Constants.EXTRA_SIGN, sign);
        args.putString(Constants.EXTRA_QS, qs);
        args.putString(Constants.EXTRA_CALLBACK, callback);
        args.putString(Constants.EXTRA_STEP1_TOKEN, step1Token);
        LoginStep2InputFragment f = new LoginStep2InputFragment();
        f.setArguments(args);
        f.setOnLoginInterface(this);
        replaceFragment(f, DEBUG, false);
    }

    public void onLoginSuccessByStep2(String userId, String authToken) {
        this.mLoginSuccess = DEBUG;
        processLoginResult(userId, authToken);
    }

    public void onBackToStep1() {
        LoginInputFragment f = new LoginInputFragment();
        f.setArguments(getIntent().getExtras());
        f.setOnLoginInterface(this);
        replaceFragment(f, DEBUG, false);
    }

    public void onSkipLogin() {
        skipLogin();
    }

    public void onForeignEmailOrIdLogin() {
        LoginInputFragment f = new LoginInputFragment();
        f.setArguments(getIntent().getExtras());
        f.setOnLoginInterface(this);
        f.setForeignEmailOrIdLoginState(DEBUG);
        replaceFragment(f, false, DEBUG);
    }
}
