package com.xiaomi.passport.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import com.xiaomi.miui.analyticstracker.AnalyticsTracker;
import com.xiaomi.passport.Build;
import com.xiaomi.passport.Constants;
import com.xiaomi.passport.data.SetupData;
import com.xiaomi.passport.ui.WelcomeFragment.WelcomeActionListener;
import com.xiaomi.passport.utils.AccountHelper;
import com.xiaomi.passport.utils.AnalyticsHelper;
import com.xiaomi.passport.utils.FriendlyFragmentUtils;
import com.xiaomi.passport.utils.SysHelper;

public class WelcomeActivity extends BaseActivity implements WelcomeActionListener {
    private static final int LOGIN_ACTIVITY = 1;
    private static final int REGISTER_ACTIVITY = 2;
    private AnalyticsTracker mAnalyticsTracker;
    private boolean mDisableBackKey;

    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        super.onCreate(savedInstanceState);
        this.mDisableBackKey = intent.getBooleanExtra(Constants.EXTRA_DISABLE_BACK_KEY, false);
        this.mAnalyticsTracker = AnalyticsTracker.getInstance();
        this.mAnalyticsTracker.startSession(this);
        checkAccountExist();
        WelcomeFragment f = new WelcomeFragment();
        f.setArguments(intent.getExtras());
        f.setWelcomeActionListener(this);
        FriendlyFragmentUtils.addFragment(getFragmentManager(), 16908290, f);
    }

    private boolean checkAccountExist() {
        if (AccountHelper.getXiaomiAccount(this) != null) {
            AccountHelper.setLoginResultAndFinish(this, 0);
            return true;
        } else if (SetupData.getRegAccount() == null) {
            return false;
        } else {
            startUnActivatedActivity();
            finish();
            return true;
        }
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
        this.mAnalyticsTracker.endSession();
        super.onDestroy();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == -1) {
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
        checkAccountExist();
    }

    protected void onResume() {
        super.onResume();
        if (!Build.IS_TABLET) {
            SysHelper.setOrientationPortrait(this);
        }
    }

    private void startUnActivatedActivity() {
        Intent unactivateIntent = new Intent(this, AccountUnactivatedActivity.class);
        unactivateIntent.putExtras(getIntent());
        startActivity(unactivateIntent);
    }

    public void onStartSignIn(String serviceId) {
        Intent intent = new Intent(Constants.ACTION_LOGIN);
        intent.setPackage(getPackageName());
        intent.putExtras(getIntent());
        startActivityForResult(intent, LOGIN_ACTIVITY);
    }

    public void onStartSignUp() {
        Intent intent = new Intent(Constants.ACTION_REG);
        intent.putExtras(getIntent());
        intent.setPackage(getPackageName());
        startActivityForResult(intent, REGISTER_ACTIVITY);
    }

    public void onSkipLogin() {
        AnalyticsHelper.trackEvent(this.mAnalyticsTracker, AnalyticsHelper.USER_SKIP_ON_SETUP_GUIDE);
        AccountHelper.setLoginResultAndFinish(this, -1);
    }

    public void onGoBack() {
        AnalyticsHelper.trackEvent(this.mAnalyticsTracker, AnalyticsHelper.USER_SKIP_ON_SETUP_GUIDE);
        AccountHelper.setLoginResultAndFinish(this, 0);
    }
}
