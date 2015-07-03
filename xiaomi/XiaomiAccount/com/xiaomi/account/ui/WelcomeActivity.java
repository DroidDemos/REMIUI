package com.xiaomi.account.ui;

import android.accounts.Account;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import com.xiaomi.account.R;
import com.xiaomi.account.utils.FriendlyFragmentUtils;
import com.xiaomi.account.utils.SysHelper;
import com.xiaomi.accounts.AccountManager;
import com.xiaomi.passport.Constants;
import com.xiaomi.passport.data.SetupData;
import com.xiaomi.passport.ui.WelcomeFragment;
import com.xiaomi.passport.ui.WelcomeFragment.WelcomeActionListener;
import com.xiaomi.passport.utils.AccountHelper;
import com.xiaomi.passport.utils.AnalyticsHelper;
import java.util.HashMap;
import java.util.Map;
import miui.accounts.ExtraAccountManager;
import miui.analytics.Analytics;
import miui.app.Activity;
import miui.os.Build;

public class WelcomeActivity extends Activity implements WelcomeActionListener {
    private static final int LOGIN_ACTIVITY = 1;
    private static final int REGISTER_ACTIVITY = 2;
    private static final int SHOW_SYNC_REQUEST_CODE = 1;
    private Analytics mAnalytics;
    private boolean mDisableBackKey;
    private Intent mIntent;
    private Map<String, Object> mLogParams;
    private boolean mOnSetupGuide;
    private String mPackageName;

    public WelcomeActivity() {
        this.mLogParams = new HashMap();
    }

    protected void onCreate(Bundle savedInstanceState) {
        this.mIntent = getIntent();
        this.mOnSetupGuide = this.mIntent.getBooleanExtra(Constants.EXTRA_SHOW_SKIP_LOGIN, false);
        if (this.mOnSetupGuide) {
            setTheme(R.style.Theme.FullScreen.Provision);
        }
        super.onCreate(savedInstanceState);
        this.mDisableBackKey = this.mIntent.getBooleanExtra(Constants.EXTRA_DISABLE_BACK_KEY, false);
        checkAccountState();
        Bundle extras = this.mIntent.getExtras();
        if (this.mOnSetupGuide) {
            ProvisionWelcomeFragment f = new ProvisionWelcomeFragment();
            f.setArguments(extras);
            f.setWelcomeActionListener(this);
            FriendlyFragmentUtils.addFragment(getFragmentManager(), 16908290, f);
        } else {
            WelcomeFragment f2 = new WelcomeFragment();
            f2.setArguments(extras);
            f2.setWelcomeActionListener(this);
            FriendlyFragmentUtils.addFragment(getFragmentManager(), 16908290, f2);
        }
        if (extras != null) {
            this.mPackageName = extras.getString(AccountManager.KEY_ANDROID_PACKAGE_NAME);
        }
        this.mLogParams.put(AnalyticsHelper.PACKAGE_NAME, this.mPackageName);
        if (Build.IS_TABLET && this.mOnSetupGuide) {
            SysHelper.setOrientationPortrait(this);
        }
        this.mAnalytics = Analytics.getInstance();
        this.mAnalytics.startSession(this);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 16908332:
                AccountHelper.setLoginResultAndFinish(this, 0);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    protected void onDestroy() {
        this.mAnalytics.endSession();
        super.onDestroy();
    }

    public void finish() {
        super.finish();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == SHOW_SYNC_REQUEST_CODE) {
            if (resultCode == -1) {
                AccountHelper.setLoginResultAndFinish(this, -1);
            }
        } else if (resultCode == -1) {
            finish();
        }
    }

    public void onBackPressed() {
        if (!this.mDisableBackKey) {
            AccountHelper.setLoginResultAndFinish(this, 0);
        }
    }

    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        checkAccountState();
    }

    protected void onResume() {
        super.onResume();
        if (!Build.IS_TABLET) {
            SysHelper.setOrientationPortrait(this);
        }
    }

    private void checkAccountState() {
        Account account = ExtraAccountManager.getXiaomiAccount(this);
        if (account != null) {
            AccountHelper.setLoginResultAndFinish(this, -1, account.name, null);
        } else if (SetupData.getRegAccount() != null) {
            startUnActivatedActivity();
            finish();
        }
    }

    private void startUnActivatedActivity() {
        Intent unactivateIntent = new Intent(this, AccountUnactivatedActivity.class);
        unactivateIntent.putExtras(getIntent());
        startActivity(unactivateIntent);
        finish();
    }

    public void onStartSignIn(String serviceId) {
        Intent intent = new Intent(Constants.ACTION_LOGIN);
        intent.setPackage(getPackageName());
        intent.putExtras(this.mIntent);
        intent.putExtra(Constants.EXTRA_BUILD_REGION_INFO, Build.REGION);
        startActivityForResult(intent, SHOW_SYNC_REQUEST_CODE);
        if (this.mOnSetupGuide) {
            com.xiaomi.account.utils.AnalyticsHelper.trackEvent(this.mAnalytics, AnalyticsHelper.USER_START_SIGN_IN_ON_GUIDE, this.mLogParams);
        } else {
            com.xiaomi.account.utils.AnalyticsHelper.trackEvent(this.mAnalytics, AnalyticsHelper.USER_START_SIGN_IN, this.mLogParams);
        }
    }

    public void onStartSignUp() {
        Intent intent = new Intent(Constants.ACTION_REG);
        intent.putExtras(this.mIntent);
        intent.setPackage(getPackageName());
        startActivityForResult(intent, REGISTER_ACTIVITY);
        if (this.mOnSetupGuide) {
            com.xiaomi.account.utils.AnalyticsHelper.trackEvent(this.mAnalytics, AnalyticsHelper.USER_START_SIGN_UP_ON_GUIDE, this.mLogParams);
        } else {
            com.xiaomi.account.utils.AnalyticsHelper.trackEvent(this.mAnalytics, AnalyticsHelper.USER_START_SIGN_UP, this.mLogParams);
        }
    }

    public void onSkipLogin() {
        com.xiaomi.account.utils.AnalyticsHelper.trackEvent(this.mAnalytics, AnalyticsHelper.USER_SKIP_ON_SETUP_GUIDE, this.mLogParams);
        AccountHelper.setLoginResultAndFinish(this, -1);
    }

    public void onGoBack() {
        com.xiaomi.account.utils.AnalyticsHelper.trackEvent(this.mAnalytics, AnalyticsHelper.USER_SKIP_ON_SETUP_GUIDE);
        AccountHelper.setLoginResultAndFinish(this, 0);
    }
}
