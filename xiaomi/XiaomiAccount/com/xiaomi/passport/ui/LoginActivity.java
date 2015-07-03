package com.xiaomi.passport.ui;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import com.xiaomi.account.R;
import com.xiaomi.miui.analyticstracker.AnalyticsTracker;
import com.xiaomi.passport.Build;
import com.xiaomi.passport.Constants;
import com.xiaomi.passport.data.SetupData;
import com.xiaomi.passport.ui.LoginInputFragment.OnLoginInterface;
import com.xiaomi.passport.utils.AccountHelper;
import com.xiaomi.passport.utils.FriendlyFragmentUtils;
import com.xiaomi.passport.utils.SysHelper;

public class LoginActivity extends BaseActivity implements OnLoginInterface, LoginStep2InputFragment.OnLoginInterface {
    private static final String TAG = "LoginActivity";
    private boolean mActivityStatusSaved;
    private AnalyticsTracker mAnalyticsTracker;
    private boolean mLoginSuccess;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.setTitle(R.string.passport_login_title);
        }
        if (getFragmentManager().findFragmentByTag(LoginStep2InputFragment.class.getSimpleName()) == null) {
            LoginInputFragment f = new LoginInputFragment();
            f.setArguments(intent.getExtras());
            f.setOnLoginInterface(this);
            FriendlyFragmentUtils.addFragment(getFragmentManager(), 16908290, f);
        }
        SetupData.setRegAccount(null);
        this.mAnalyticsTracker = AnalyticsTracker.getInstance();
        this.mAnalyticsTracker.startSession(this);
    }

    protected void onResume() {
        super.onResume();
        this.mActivityStatusSaved = false;
        if (!Build.IS_TABLET) {
            SysHelper.setOrientationPortrait(this);
        }
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 16908332:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        this.mActivityStatusSaved = true;
    }

    protected void onDestroy() {
        this.mAnalyticsTracker.endSession();
        super.onDestroy();
    }

    public void finish() {
        if (this.mLoginSuccess) {
            setResult(-1);
        }
        super.finish();
    }

    private void processLoginResult(String userId, String authToken) {
        if (!this.mActivityStatusSaved) {
            AccountHelper.setLoginResultAndFinish(this, -1, userId, authToken);
        }
    }

    public void onStartLogin() {
        this.mLoginSuccess = false;
    }

    public void onLoginSuccess(String userId, String authToken) {
        this.mLoginSuccess = true;
        processLoginResult(userId, authToken);
    }

    public void onSkipLogin() {
        AccountHelper.setLoginResultAndFinish(this, -1);
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
        SysHelper.replaceToFragment(this, f, true);
    }

    public void onLoginSuccessByStep2(String userId, String authToken) {
        this.mLoginSuccess = true;
        processLoginResult(userId, authToken);
    }

    public void onBackToStep1() {
        LoginInputFragment f = new LoginInputFragment();
        f.setArguments(getIntent().getExtras());
        f.setOnLoginInterface(this);
        SysHelper.replaceToFragment(this, f, true);
    }

    public void onForeignEmailOrIdLogin() {
        LoginInputFragment f = new LoginInputFragment();
        f.setArguments(getIntent().getExtras());
        f.setOnLoginInterface(this);
        f.setForeignEmailOrIdLoginState(true);
        SysHelper.replaceToFragment(this, f, false);
    }
}
