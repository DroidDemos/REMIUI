package com.xiaomi.mipush.sdk;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.database.ContentObserver;
import android.os.Handler;
import android.os.Looper;
import com.xiaomi.channel.commonutils.logger.MyLog;
import com.xiaomi.channel.commonutils.network.Network;
import com.xiaomi.channel.commonutils.string.XMStringUtils;
import com.xiaomi.push.service.PushConstants;
import com.xiaomi.push.service.ServiceClient;
import com.xiaomi.push.service.XMPushService;
import com.xiaomi.xmpush.thrift.ActionType;
import com.xiaomi.xmpush.thrift.XmPushActionRegistration;
import com.xiaomi.xmpush.thrift.XmPushActionUnRegistration;
import com.xiaomi.xmpush.thrift.XmPushThriftSerializeUtils;
import com.xiaomi.xmsf.push.service.log.LogProvider;
import java.util.ArrayList;
import java.util.Iterator;
import org.apache.thrift.TBase;

public class PushServiceClient {
    private static final int MIN_MIUI_PUSH_VERSION = 105;
    private static final int MIN_MIUI_PUSH_VERSION_JAR = 106;
    private static final int REQUEST_CACHE_SIZE = 10;
    private static PushServiceClient sInstance;
    private static final ArrayList<BufferedRequest> sPendingRequest;
    private Context mContext;
    private Integer mDeviceProvisioned;
    private boolean mIsMiuiPushServiceEnabled;
    private String mSession;
    private Intent registerTask;

    static class BufferedRequest<T extends TBase<T, ?>> {
        ActionType actionType;
        boolean encrypt;
        T message;

        BufferedRequest() {
        }
    }

    static {
        sPendingRequest = new ArrayList();
    }

    public static PushServiceClient getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new PushServiceClient(context);
        }
        return sInstance;
    }

    private PushServiceClient(Context context) {
        this.mIsMiuiPushServiceEnabled = false;
        this.registerTask = null;
        this.mDeviceProvisioned = null;
        this.mContext = context.getApplicationContext();
        this.mSession = XMStringUtils.generateRandomString(6);
        this.mIsMiuiPushServiceEnabled = serviceInstalled();
    }

    public final void register(XmPushActionRegistration regData, boolean isEnvChanage) {
        this.registerTask = null;
        Intent intent = createServiceIntent();
        byte[] msgBytes = XmPushThriftSerializeUtils.convertThriftObjectToBytes(PushContainerHelper.generateRequestContainer(this.mContext, regData, ActionType.Registration));
        if (msgBytes == null) {
            MyLog.warn("register fail, because msgBytes is null.");
            return;
        }
        intent.setAction(PushConstants.MIPUSH_ACTION_REGISTER_APP);
        intent.putExtra(PushConstants.MIPUSH_EXTRA_APP_ID, AppInfoHolder.getInstance(this.mContext).getAppID());
        intent.putExtra(PushConstants.MIPUSH_EXTRA_PAYLOAD, msgBytes);
        intent.putExtra(PushConstants.MIPUSH_EXTRA_SESSION, this.mSession);
        intent.putExtra(PushConstants.MIPUSH_EXTRA_ENV_CHANAGE, isEnvChanage);
        intent.putExtra(PushConstants.MIPUSH_EXTRA_ENV_TYPE, AppInfoHolder.getInstance(this.mContext).getEnvType());
        if (Network.hasNetwork(this.mContext) && isProvisioned()) {
            this.mContext.startService(intent);
        } else {
            this.registerTask = intent;
        }
    }

    public void awakePushService() {
        this.mContext.startService(createServiceIntent());
    }

    public final void unregister(XmPushActionUnRegistration unregData) {
        Intent intent = createServiceIntent();
        byte[] msgBytes = XmPushThriftSerializeUtils.convertThriftObjectToBytes(PushContainerHelper.generateRequestContainer(this.mContext, unregData, ActionType.UnRegistration));
        if (msgBytes == null) {
            MyLog.warn("unregister fail, because msgBytes is null.");
            return;
        }
        intent.setAction(PushConstants.MIPUSH_ACTION_UNREGISTER_APP);
        intent.putExtra(PushConstants.MIPUSH_EXTRA_APP_ID, AppInfoHolder.getInstance(this.mContext).getAppID());
        intent.putExtra(PushConstants.MIPUSH_EXTRA_PAYLOAD, msgBytes);
        this.mContext.startService(intent);
    }

    public final <T extends TBase<T, ?>> void sendMessage(T packet, ActionType actionType) {
        sendMessage(packet, actionType, !actionType.equals(ActionType.Registration));
    }

    public final <T extends TBase<T, ?>> void sendMessage(T packet, ActionType actionType, boolean encrypt) {
        sendMessage(packet, actionType, encrypt, true);
    }

    private final <T extends TBase<T, ?>> void sendMessage(T packet, ActionType actionType, boolean encrypt, boolean pendingIfNecessary) {
        if (AppInfoHolder.getInstance(this.mContext).appRegistered()) {
            Intent intent = createServiceIntent();
            byte[] msgBytes = XmPushThriftSerializeUtils.convertThriftObjectToBytes(PushContainerHelper.generateRequestContainer(this.mContext, packet, actionType, encrypt));
            if (msgBytes == null) {
                MyLog.warn("send message fail, because msgBytes is null.");
                return;
            }
            intent.setAction(PushConstants.MIPUSH_ACTION_SEND_MESSAGE);
            intent.putExtra(PushConstants.MIPUSH_EXTRA_PAYLOAD, msgBytes);
            this.mContext.startService(intent);
        } else if (pendingIfNecessary) {
            addPendRequest(packet, actionType, encrypt);
        } else {
            MyLog.warn("drop the message before initialization.");
        }
    }

    private boolean serviceInstalled() {
        try {
            PackageInfo pkgInfo = this.mContext.getPackageManager().getPackageInfo(LogProvider.AUTHORITY, 4);
            if (pkgInfo != null && pkgInfo.versionCode >= MIN_MIUI_PUSH_VERSION) {
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    private Intent createServiceIntent() {
        String pkgName = this.mContext.getPackageName();
        if (!shouldUseMIUIPush() || LogProvider.AUTHORITY.equals(pkgName)) {
            enableMyPushService();
            Intent intent = new Intent(this.mContext, XMPushService.class);
            intent.putExtra(PushConstants.MIPUSH_EXTRA_APP_PACKAGE, pkgName);
            return intent;
        }
        intent = new Intent();
        intent.setPackage(LogProvider.AUTHORITY);
        intent.setClassName(LogProvider.AUTHORITY, getPushServiceName());
        intent.putExtra(PushConstants.MIPUSH_EXTRA_APP_PACKAGE, pkgName);
        disableMyPushService();
        return intent;
    }

    private String getPushServiceName() {
        try {
            if (this.mContext.getPackageManager().getPackageInfo(LogProvider.AUTHORITY, 4).versionCode >= MIN_MIUI_PUSH_VERSION_JAR) {
                return PushConstants.PUSH_SERVICE_CLASS_NAME_JAR;
            }
        } catch (Exception e) {
        }
        return PushConstants.PUSH_SERVICE_CLASS_NAME;
    }

    private void disableMyPushService() {
        this.mContext.getPackageManager().setComponentEnabledSetting(new ComponentName(this.mContext, XMPushService.class), 2, 1);
    }

    private void enableMyPushService() {
        this.mContext.getPackageManager().setComponentEnabledSetting(new ComponentName(this.mContext, XMPushService.class), 1, 1);
    }

    public boolean shouldUseMIUIPush() {
        return this.mIsMiuiPushServiceEnabled && 1 == AppInfoHolder.getInstance(this.mContext).getEnvType();
    }

    public void processRegisterTask() {
        if (this.registerTask != null) {
            this.mContext.startService(this.registerTask);
            this.registerTask = null;
        }
    }

    public <T extends TBase<T, ?>> void addPendRequest(T packet, ActionType actionType, boolean encrypt) {
        BufferedRequest<T> br = new BufferedRequest();
        br.message = packet;
        br.actionType = actionType;
        br.encrypt = encrypt;
        synchronized (sPendingRequest) {
            sPendingRequest.add(br);
            if (sPendingRequest.size() > REQUEST_CACHE_SIZE) {
                sPendingRequest.remove(0);
            }
        }
    }

    public void processPendRequest() {
        synchronized (sPendingRequest) {
            Iterator i$ = sPendingRequest.iterator();
            while (i$.hasNext()) {
                BufferedRequest br = (BufferedRequest) i$.next();
                sendMessage(br.message, br.actionType, br.encrypt, false);
            }
            sPendingRequest.clear();
        }
    }

    public void clearNotification(int notifyId) {
        Intent intent = createServiceIntent();
        intent.setAction(PushConstants.MIPUSH_ACTION_CLEAR_NOTIFICATION);
        intent.putExtra(PushConstants.EXTRA_PACKAGE_NAME, this.mContext.getPackageName());
        intent.putExtra(PushConstants.EXTRA_NOTIFY_ID, notifyId);
        this.mContext.startService(intent);
    }

    public boolean isProvisioned() {
        if (!shouldUseMIUIPush() || !ServiceClient.getInstance(this.mContext).checkProvisioned()) {
            return true;
        }
        if (this.mDeviceProvisioned == null) {
            this.mDeviceProvisioned = Integer.valueOf(ServiceClient.getInstance(this.mContext).getProvisioned());
            if (this.mDeviceProvisioned.intValue() == 0) {
                this.mContext.getContentResolver().registerContentObserver(ServiceClient.getInstance(this.mContext).getProvisionedUri(), false, new ContentObserver(new Handler(Looper.getMainLooper())) {
                    public void onChange(boolean selfChange) {
                        PushServiceClient.this.mDeviceProvisioned = Integer.valueOf(ServiceClient.getInstance(PushServiceClient.this.mContext).getProvisioned());
                        if (PushServiceClient.this.mDeviceProvisioned.intValue() != 0) {
                            PushServiceClient.this.mContext.getContentResolver().unregisterContentObserver(this);
                            if (Network.hasNetwork(PushServiceClient.this.mContext)) {
                                PushServiceClient.this.processRegisterTask();
                            }
                        }
                    }
                });
            }
        }
        if (this.mDeviceProvisioned.intValue() != 0) {
            return true;
        }
        return false;
    }
}
