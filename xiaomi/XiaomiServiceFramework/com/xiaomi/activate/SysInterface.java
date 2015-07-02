package com.xiaomi.activate;

import android.app.PendingIntent;
import android.telephony.CellLocation;
import android.telephony.PhoneStateListener;
import java.util.List;

public interface SysInterface {
    boolean allowAutoActivation();

    boolean allowAutoUplinkActivation();

    String blockingGetDeviceId(long j);

    String blockingGetIccid(int i);

    String blockingGetImsi(int i);

    String blockingGetSimId(int i);

    int getAppIconRes();

    CellLocation getCellLocation(int i);

    String getLine1Number(int i);

    String getNetworkOperator(int i);

    int getPhoneType(int i);

    int getSimCount();

    String getSimOperator(int i);

    boolean isCalllogSyncEnabled();

    boolean isCdmaDevice();

    boolean isCloudMessagingEnabled();

    boolean isMmsSyncEnabled();

    boolean isSimInserted(int i);

    void listen(PhoneStateListener phoneStateListener, int i, int i2);

    void readAndRemoveOldActivateInfo(List<ActivateInfo> list);

    void sendTextMessage(int i, String str, String str2, String str3, PendingIntent pendingIntent, PendingIntent pendingIntent2);
}
