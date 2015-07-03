package com.xiaomi.account;

import android.accounts.Account;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.xiaomi.account.data.SetupData;
import com.xiaomi.account.ui.QuickLoginActivity;
import com.xiaomi.account.utils.CloudHelper;
import com.xiaomi.passport.Constants;
import com.xiaomi.passport.interfaces.ActivityInterface;
import com.xiaomi.passport.interfaces.PassportInterface;
import miui.telephony.CloudTelephonyManager;
import miui.telephony.exception.IllegalDeviceException;

public class PassportImplementation implements ActivityInterface, PassportInterface {
    private static final String TAG;
    private final Context mContext;

    static {
        TAG = PassportImplementation.class.getSimpleName();
    }

    public PassportImplementation(Context context) {
        this.mContext = context;
    }

    public void onPreCreate(Activity activity) {
    }

    public void onPostCreate(Activity activity) {
    }

    public boolean useTranslucentStatusBar(Activity activity) {
        return true;
    }

    public void onPreAddAccount(Account account) {
        Intent intent = new Intent("android.accounts.LOGIN_ACCOUNTS_PRE_CHANGED");
        intent.putExtra(Constants.EXTRA_ACCOUNT, account);
        intent.putExtra("extra_update_type", 2);
        this.mContext.sendBroadcast(intent);
    }

    public void onPostAddAccount(Account account) {
        Intent intent = new Intent("android.accounts.LOGIN_ACCOUNTS_POST_CHANGED");
        intent.putExtra(Constants.EXTRA_ACCOUNT, account);
        intent.putExtra("extra_update_type", 2);
        this.mContext.sendBroadcast(intent);
        Log.i(TAG, "add Xiaomi account: " + account);
    }

    public boolean onGetRemoveAccountAllowed(Account account) {
        return SetupData.isUserVerified();
    }

    public void onPreRemoveAccount(Account account) {
        Log.i(TAG, "removing Xiaomi account, " + account.name);
        Intent intent = new Intent("android.accounts.LOGIN_ACCOUNTS_PRE_CHANGED");
        intent.putExtra(Constants.EXTRA_ACCOUNT, account);
        intent.putExtra("extra_update_type", 1);
        this.mContext.sendBroadcast(intent);
    }

    public void onPostRemoveAccount(Account account) {
        Log.i(TAG, "Xiaomi account removed: " + account.name);
        Intent accountChangedIntent = new Intent("android.accounts.LOGIN_ACCOUNTS_POST_CHANGED");
        accountChangedIntent.putExtra(Constants.EXTRA_ACCOUNT, account);
        accountChangedIntent.putExtra("extra_update_type", 1);
        this.mContext.sendBroadcast(accountChangedIntent);
        SetupData.setUserVerified(false);
    }

    public void onPostRefreshAccount(Account account) {
        Intent intent = new Intent("android.accounts.LOGIN_ACCOUNTS_POST_CHANGED");
        intent.putExtra(Constants.EXTRA_ACCOUNT, account);
        intent.putExtra("extra_update_type", 2);
        this.mContext.sendBroadcast(intent);
    }

    public String getDeviceId() {
        try {
            return CloudHelper.safeGetHashedDeviceId();
        } catch (IllegalDeviceException e) {
            Log.e(TAG, "get device id error", e);
            return null;
        }
    }

    public boolean useUplinkRegister() {
        return CloudTelephonyManager.getAvailableSimCount() > 0;
    }

    public int getSimCount() {
        return CloudTelephonyManager.getAvailableSimCount();
    }

    public int getSlotCount() {
        return CloudTelephonyManager.getMultiSimCount();
    }

    public String getSimOperatorName(int index) {
        return CloudTelephonyManager.getSimOperatorName(this.mContext, index);
    }

    public boolean isSimInserted(int slotIndex) {
        return CloudTelephonyManager.isSimInserted(this.mContext, slotIndex);
    }

    public String getActivatePackageName() {
        return "com.xiaomi.xmsf";
    }

    public Intent getConfirmCredentialIntent() {
        return new Intent(this.mContext, QuickLoginActivity.class);
    }

    @Deprecated
    public boolean verifyCallerUidAgainstWebSso(int callerUid) {
        return false;
    }
}
