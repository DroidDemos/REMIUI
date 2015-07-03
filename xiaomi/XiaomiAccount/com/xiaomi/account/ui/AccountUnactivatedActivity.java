package com.xiaomi.account.ui;

import android.accounts.AccountAuthenticatorResponse;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import com.xiaomi.account.R;
import com.xiaomi.account.utils.SysHelper;
import com.xiaomi.accounts.AccountManager;
import com.xiaomi.passport.Constants;
import com.xiaomi.passport.accountmanager.MiAccountManager;
import com.xiaomi.passport.accountmanager.SystemAccountAuthenticatorResponse;
import com.xiaomi.passport.data.SetupData;
import com.xiaomi.passport.ui.AccountUnactivatedFragment;
import com.xiaomi.passport.utils.AccountHelper;
import miui.os.Build;
import miui.preference.PreferenceActivity;

public class AccountUnactivatedActivity extends PreferenceActivity {
    private static final String TAG = "AccountUnactivatedActivity";
    private static final String TAG_FRM_UNACTIVATED = "unactivated";
    private boolean mDisableBackKey;
    private boolean mOnSetupGuide;
    private String mPackageName;

    public void onCreate(Bundle savedInstanceState) {
        if (!Build.IS_TABLET) {
            setTheme(R.style.Theme.Main);
        }
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        handleIntent(intent);
        this.mDisableBackKey = intent.getBooleanExtra(Constants.EXTRA_DISABLE_BACK_KEY, false);
        this.mOnSetupGuide = intent.getBooleanExtra(Constants.EXTRA_SHOW_SKIP_LOGIN, false);
        this.mPackageName = intent.getStringExtra(AccountManager.KEY_ANDROID_PACKAGE_NAME);
        FragmentManager fm = getFragmentManager();
        if (fm.findFragmentByTag(TAG_FRM_UNACTIVATED) == null) {
            addUnActivatedFragment(fm);
        }
    }

    protected void onResume() {
        super.onResume();
        if (!Build.IS_TABLET) {
            SysHelper.setOrientationPortrait(this);
        }
    }

    protected void onPause() {
        super.onPause();
    }

    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
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

    public void onBackPressed() {
        if (!this.mDisableBackKey) {
            AccountHelper.setLoginResultAndFinish(this, 0);
        }
    }

    private void addUnActivatedFragment(FragmentManager fm) {
        Fragment f = new AccountUnactivatedFragment();
        Bundle args = new Bundle();
        args.putBoolean(Constants.EXTRA_SHOW_SKIP_LOGIN, this.mOnSetupGuide);
        args.putString(AccountManager.KEY_ANDROID_PACKAGE_NAME, this.mPackageName);
        f.setArguments(args);
        fm.beginTransaction().setTransition(4099).replace(16908290, f, TAG_FRM_UNACTIVATED).commit();
    }

    private void handleIntent(Intent intent) {
        AccountAuthenticatorResponse response = (AccountAuthenticatorResponse) intent.getParcelableExtra(MiAccountManager.KEY_ACCOUNT_AUTHENTICATOR_RESPONSE);
        if (response != null) {
            SetupData.setAccountAuthenticatorResponse(new SystemAccountAuthenticatorResponse(response));
        }
    }
}
