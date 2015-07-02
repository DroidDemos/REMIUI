package com.xiaomi.xmsf.activate;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.PendingIntent;
import android.content.ContentResolver;
import android.content.Context;
import android.provider.Settings.Secure;
import android.telephony.CellLocation;
import android.telephony.PhoneStateListener;
import com.xiaomi.activate.ActivateInfo;
import com.xiaomi.activate.SysInterface;
import com.xiaomi.xmsf.R;
import java.util.List;
import miui.accounts.ExtraAccountManager;
import miui.os.Build;
import miui.telephony.CloudTelephonyManager;
import miui.telephony.exception.IllegalDeviceException;

public class ActivateSysImpl implements SysInterface {
    private static final String EXTRA_FIND_DEVICE_ENABLED = "extra_find_device_enabled";
    private static final String TRUE = "true";
    private final Context mContext;

    public ActivateSysImpl(Context context) {
        this.mContext = context;
    }

    public boolean isCdmaDevice() {
        return Build.MODEL.endsWith("C");
    }

    public int getPhoneType(int simIndex) {
        return CloudTelephonyManager.getPhoneType(this.mContext, simIndex);
    }

    public int getSimCount() {
        return CloudTelephonyManager.getMultiSimCount();
    }

    public String getLine1Number(int simIndex) {
        return CloudTelephonyManager.getLine1Number(this.mContext, simIndex);
    }

    public boolean isSimInserted(int simIndex) {
        return CloudTelephonyManager.isSimInserted(this.mContext, simIndex);
    }

    public void listen(PhoneStateListener listener, int events, int simIndex) {
        CloudTelephonyManager.listen(this.mContext, listener, events, simIndex);
    }

    public String blockingGetDeviceId(long timeout) {
        try {
            return CloudTelephonyManager.blockingGetDeviceId(this.mContext, timeout);
        } catch (IllegalDeviceException e) {
            return null;
        }
    }

    public String blockingGetIccid(int simIndex) {
        try {
            return CloudTelephonyManager.blockingGetIccId(this.mContext, simIndex);
        } catch (IllegalDeviceException e) {
            return null;
        }
    }

    public String blockingGetImsi(int simIndex) {
        try {
            return CloudTelephonyManager.blockingGetImsi(this.mContext, simIndex);
        } catch (IllegalDeviceException e) {
            return null;
        }
    }

    public String blockingGetSimId(int simIndex) {
        try {
            return CloudTelephonyManager.blockingGetSimId(this.mContext, simIndex);
        } catch (IllegalDeviceException e) {
            return null;
        }
    }

    public String getNetworkOperator(int simIndex) {
        return CloudTelephonyManager.getNetworkOperator(this.mContext, simIndex);
    }

    public String getSimOperator(int simIndex) {
        return CloudTelephonyManager.getSimOperator(this.mContext, simIndex);
    }

    public CellLocation getCellLocation(int simIndex) {
        return CloudTelephonyManager.getCellLocation(this.mContext, simIndex);
    }

    public void sendTextMessage(int simIndex, String destAddress, String scAddress, String text, PendingIntent sentIntent, PendingIntent deliveryIntent) {
        CloudTelephonyManager.sendTextMessage(simIndex, destAddress, scAddress, text, sentIntent, deliveryIntent);
    }

    public boolean isMmsSyncEnabled() {
        return ContentResolver.getSyncAutomatically(ExtraAccountManager.getXiaomiAccount(this.mContext), "mms");
    }

    public boolean isCalllogSyncEnabled() {
        return ContentResolver.getSyncAutomatically(ExtraAccountManager.getXiaomiAccount(this.mContext), "call_log");
    }

    public boolean allowAutoActivation() {
        return isFindDeviceEnabled();
    }

    public boolean allowAutoUplinkActivation() {
        return isFindDeviceEnabled();
    }

    private boolean isFindDeviceEnabled() {
        Account account = ExtraAccountManager.getXiaomiAccount(this.mContext);
        return account == null ? false : TRUE.equals(((AccountManager) this.mContext.getSystemService("account")).getUserData(account, EXTRA_FIND_DEVICE_ENABLED));
    }

    public int getAppIconRes() {
        return R.drawable.icon;
    }

    public boolean isCloudMessagingEnabled() {
        return Secure.getInt(this.mContext.getContentResolver(), "cloud_messaging_on", 0) > 0;
    }

    public void readAndRemoveOldActivateInfo(List<ActivateInfo> infoList) {
        if (!ActivateOldInfoReader.readActivateInfoV2(this.mContext, infoList)) {
            ActivateOldInfoReader.readActivateInfoV1(this.mContext, infoList);
        }
        ActivateOldInfoReader.clearActivateInfoV2(this.mContext);
        ActivateOldInfoReader.clearActivateInfoV1(this.mContext);
    }
}
