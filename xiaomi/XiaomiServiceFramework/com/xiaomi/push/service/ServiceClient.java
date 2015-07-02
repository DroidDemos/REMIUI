package com.xiaomi.push.service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.provider.Settings.Global;
import android.provider.Settings.Secure;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.MyLog;
import com.xiaomi.channel.commonutils.misc.BuildSettings;
import com.xiaomi.channel.commonutils.network.Network;
import com.xiaomi.channel.commonutils.string.XMStringUtils;
import com.xiaomi.mipush.sdk.MiPushClient;
import com.xiaomi.push.service.profile.MessageProfiling;
import com.xiaomi.smack.packet.CommonPacketExtension;
import com.xiaomi.smack.packet.IQ;
import com.xiaomi.smack.packet.Message;
import com.xiaomi.smack.packet.Presence;
import com.xiaomi.xmsf.push.service.log.LogProvider;
import java.util.List;
import org.apache.http.NameValuePair;

public class ServiceClient {
    private static final int MIN_MIUI_PUSH_VERSION = 104;
    private static final int MIN_MIUI_PUSH_VERSION_JAR = 106;
    private static ServiceClient sInstance;
    private static String sSession;
    private Context mContext;
    private boolean mIsMiuiPushServiceEnabled;
    private int mProvisioned;

    static {
        sSession = XMStringUtils.generateRandomString(6);
    }

    public static ServiceClient getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new ServiceClient(context);
        }
        return sInstance;
    }

    public boolean isMiuiPushServiceEnabled() {
        return this.mIsMiuiPushServiceEnabled;
    }

    private ServiceClient(Context context) {
        this.mIsMiuiPushServiceEnabled = false;
        this.mProvisioned = 0;
        this.mContext = context.getApplicationContext();
        if (serviceInstalled()) {
            MyLog.v("use miui push service");
            this.mIsMiuiPushServiceEnabled = true;
        }
    }

    public int openChannel(String userId, String chid, String token, String authMethod, String security, boolean kick, List<NameValuePair> clientExtras, List<NameValuePair> cloudExtras) {
        Intent intent = createServiceIntent();
        intent.setAction(PushConstants.ACTION_OPEN_CHANNEL);
        putOpenParamsIntoIntent(intent, userId, chid, token, authMethod, security, kick, clientExtras, cloudExtras);
        this.mContext.startService(intent);
        return 0;
    }

    public boolean sendMessage(Message message, boolean encrypt) {
        if (!Network.hasNetwork(this.mContext)) {
            return false;
        }
        Intent intent = createServiceIntent();
        String perfData = MessageProfiling.getPrefString();
        if (!TextUtils.isEmpty(perfData)) {
            CommonPacketExtension perfExt = new CommonPacketExtension("pf", null, (String[]) null, (String[]) null);
            CommonPacketExtension sentPerfExt = new CommonPacketExtension(PushServiceConstants.SEND_MSG_EVENT_TYPE_SENT, null, (String[]) null, (String[]) null);
            sentPerfExt.setText(perfData);
            perfExt.appendChild(sentPerfExt);
            message.addExtension(perfExt);
        }
        Bundle messageBundle = message.toBundle();
        if (messageBundle == null) {
            return false;
        }
        MyLog.v("SEND:" + message.toXML());
        intent.setAction(PushConstants.ACTION_SEND_MESSAGE);
        intent.putExtra(PushConstants.EXTRA_SESSION, sSession);
        intent.putExtra(PushConstants.EXTRA_PACKET, messageBundle);
        intent.putExtra(PushConstants.EXTRA_ENCYPT, encrypt);
        this.mContext.startService(intent);
        return true;
    }

    public boolean batchSendMessage(Message[] messages, boolean encrypt) {
        if (!Network.hasNetwork(this.mContext)) {
            return false;
        }
        Intent intent = createServiceIntent();
        Bundle[] messageBundles = new Bundle[messages.length];
        for (int i = 0; i < messages.length; i++) {
            String perfData = MessageProfiling.getPrefString();
            if (!TextUtils.isEmpty(perfData)) {
                CommonPacketExtension perfExt = new CommonPacketExtension("pf", null, (String[]) null, (String[]) null);
                CommonPacketExtension sentPerfExt = new CommonPacketExtension(PushServiceConstants.SEND_MSG_EVENT_TYPE_SENT, null, (String[]) null, (String[]) null);
                sentPerfExt.setText(perfData);
                perfExt.appendChild(sentPerfExt);
                messages[i].addExtension(perfExt);
            }
            MyLog.v("SEND:" + messages[i].toXML());
            messageBundles[i] = messages[i].toBundle();
        }
        if (messageBundles.length <= 0) {
            return false;
        }
        intent.setAction(PushConstants.ACTION_BATCH_SEND_MESSAGE);
        intent.putExtra(PushConstants.EXTRA_SESSION, sSession);
        intent.putExtra(PushConstants.EXTRA_PACKETS, messageBundles);
        intent.putExtra(PushConstants.EXTRA_ENCYPT, encrypt);
        this.mContext.startService(intent);
        return true;
    }

    public boolean sendIQ(IQ iq) {
        if (!Network.hasNetwork(this.mContext)) {
            return false;
        }
        Intent intent = createServiceIntent();
        Bundle iqBundle = iq.toBundle();
        if (iqBundle == null) {
            return false;
        }
        MyLog.v("SEND:" + iq.toXML());
        intent.setAction(PushConstants.ACTION_SEND_IQ);
        intent.putExtra(PushConstants.EXTRA_SESSION, sSession);
        intent.putExtra(PushConstants.EXTRA_PACKET, iqBundle);
        this.mContext.startService(intent);
        return true;
    }

    public boolean sendPresence(Presence presence) {
        if (!Network.hasNetwork(this.mContext)) {
            return false;
        }
        Intent intent = createServiceIntent();
        Bundle presBundle = presence.toBundle();
        if (presBundle == null) {
            return false;
        }
        MyLog.v("SEND:" + presence.toXML());
        intent.setAction(PushConstants.ACTION_SEND_PRESENCE);
        intent.putExtra(PushConstants.EXTRA_SESSION, sSession);
        intent.putExtra(PushConstants.EXTRA_PACKET, presBundle);
        this.mContext.startService(intent);
        return true;
    }

    public boolean closeChannel() {
        Intent intent = createServiceIntent();
        intent.setAction(PushConstants.ACTION_CLOSE_CHANNEL);
        this.mContext.startService(intent);
        return true;
    }

    public boolean closeChannel(String chid) {
        Intent intent = createServiceIntent();
        intent.setAction(PushConstants.ACTION_CLOSE_CHANNEL);
        intent.putExtra(PushConstants.EXTRA_CHANNEL_ID, chid);
        this.mContext.startService(intent);
        return true;
    }

    public boolean closeChannel(String chid, String userId) {
        Intent intent = createServiceIntent();
        intent.setAction(PushConstants.ACTION_CLOSE_CHANNEL);
        intent.putExtra(PushConstants.EXTRA_CHANNEL_ID, chid);
        intent.putExtra(PushConstants.EXTRA_USER_ID, userId);
        this.mContext.startService(intent);
        return true;
    }

    public boolean forceReconnection(String userId, String chid, String token, String authMethod, String security, boolean kick, List<NameValuePair> clientExtras, List<NameValuePair> cloudExtras) {
        Intent intent = createServiceIntent();
        intent.setAction(PushConstants.ACTION_FORCE_RECONNECT);
        putOpenParamsIntoIntent(intent, userId, chid, token, authMethod, security, kick, clientExtras, cloudExtras);
        this.mContext.startService(intent);
        return true;
    }

    public void resetConnection(String userId, String chid, String token, String authMethod, String security, boolean kick, List<NameValuePair> clientExtras, List<NameValuePair> cloudExtras) {
        Intent intent = createServiceIntent();
        intent.setAction(PushConstants.ACTION_RESET_CONNECTION);
        putOpenParamsIntoIntent(intent, userId, chid, token, authMethod, security, kick, clientExtras, cloudExtras);
        this.mContext.startService(intent);
    }

    public void updateChannelInfo(String chid, List<NameValuePair> clientExtras, List<NameValuePair> cloudExtras) {
        Intent intent = createServiceIntent();
        intent.setAction(PushConstants.ACTION_UPDATE_CHANNEL_INFO);
        if (clientExtras != null) {
            String clientExtStr = joinAttributes(clientExtras);
            if (!TextUtils.isEmpty(clientExtStr)) {
                intent.putExtra(PushConstants.EXTRA_CLIENT_ATTR, clientExtStr);
            }
        }
        if (cloudExtras != null) {
            String cloudExtStr = joinAttributes(cloudExtras);
            if (!TextUtils.isEmpty(cloudExtStr)) {
                intent.putExtra(PushConstants.EXTRA_CLOUD_ATTR, cloudExtStr);
            }
        }
        intent.putExtra(PushConstants.EXTRA_CHANNEL_ID, chid);
        this.mContext.startService(intent);
    }

    private void putOpenParamsIntoIntent(Intent intent, String userId, String chid, String token, String authMethod, String security, boolean kick, List<NameValuePair> clientExtras, List<NameValuePair> cloudExtras) {
        intent.putExtra(PushConstants.EXTRA_USER_ID, userId);
        intent.putExtra(PushConstants.EXTRA_CHANNEL_ID, chid);
        intent.putExtra(PushConstants.EXTRA_TOKEN, token);
        intent.putExtra(PushConstants.EXTRA_SECURITY, security);
        intent.putExtra(PushConstants.EXTRA_AUTH_METHOD, authMethod);
        intent.putExtra(PushConstants.EXTRA_KICK, kick);
        intent.putExtra(PushConstants.EXTRA_SESSION, sSession);
        if (clientExtras != null) {
            String clientExtStr = joinAttributes(clientExtras);
            if (!TextUtils.isEmpty(clientExtStr)) {
                intent.putExtra(PushConstants.EXTRA_CLIENT_ATTR, clientExtStr);
            }
        }
        if (cloudExtras != null) {
            String cloudExtStr = joinAttributes(cloudExtras);
            if (!TextUtils.isEmpty(cloudExtStr)) {
                intent.putExtra(PushConstants.EXTRA_CLOUD_ATTR, cloudExtStr);
            }
        }
    }

    private String joinAttributes(List<NameValuePair> attr) {
        StringBuilder builder = new StringBuilder();
        int i = 1;
        for (NameValuePair pair : attr) {
            builder.append(pair.getName()).append(":").append(pair.getValue());
            if (i < attr.size()) {
                builder.append(MiPushClient.ACCEPT_TIME_SEPARATOR);
            }
            i++;
        }
        return builder.toString();
    }

    private boolean serviceInstalled() {
        if (BuildSettings.IsTestBuild) {
            return false;
        }
        try {
            PackageInfo pkgInfo = this.mContext.getPackageManager().getPackageInfo(LogProvider.AUTHORITY, 4);
            if (pkgInfo == null || pkgInfo.versionCode < MIN_MIUI_PUSH_VERSION) {
                return false;
            }
            return true;
        } catch (Exception e) {
            return false;
        }
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

    private Intent createServiceIntent() {
        if (isMiuiPushServiceEnabled()) {
            Intent intent = new Intent();
            intent.setPackage(LogProvider.AUTHORITY);
            intent.setClassName(LogProvider.AUTHORITY, getPushServiceName());
            intent.putExtra(PushConstants.EXTRA_PACKAGE_NAME, this.mContext.getPackageName());
            disableMyPushService();
            return intent;
        }
        intent = new Intent(this.mContext, XMPushService.class);
        intent.putExtra(PushConstants.EXTRA_PACKAGE_NAME, this.mContext.getPackageName());
        enableMyPushService();
        return intent;
    }

    public static String getSession() {
        return sSession;
    }

    public static void setSession(String session) {
        sSession = session;
    }

    private void disableMyPushService() {
        this.mContext.getPackageManager().setComponentEnabledSetting(new ComponentName(this.mContext, XMPushService.class), 2, 1);
    }

    private void enableMyPushService() {
        this.mContext.getPackageManager().setComponentEnabledSetting(new ComponentName(this.mContext, XMPushService.class), 1, 1);
    }

    public void checkAlive() {
        Intent intent = createServiceIntent();
        intent.setAction(PushServiceConstants.ACTION_CHECK_ALIVE);
        this.mContext.startService(intent);
    }

    public boolean checkProvisioned() {
        return BuildSettings.ReleaseChannel.contains(BuildSettings.ReleaseChannel) || BuildSettings.ReleaseChannel.contains("xiaomi") || BuildSettings.ReleaseChannel.contains(d.MIUI_SYSTEM_APK_NAME);
    }

    public int getProvisioned() {
        if (this.mProvisioned != 0) {
            return this.mProvisioned;
        }
        if (VERSION.SDK_INT >= 17) {
            this.mProvisioned = Global.getInt(this.mContext.getContentResolver(), "device_provisioned", 0);
            return this.mProvisioned;
        }
        this.mProvisioned = Secure.getInt(this.mContext.getContentResolver(), "device_provisioned", 0);
        return this.mProvisioned;
    }

    public Uri getProvisionedUri() {
        if (VERSION.SDK_INT >= 17) {
            return Global.getUriFor("device_provisioned");
        }
        return Secure.getUriFor("device_provisioned");
    }
}
