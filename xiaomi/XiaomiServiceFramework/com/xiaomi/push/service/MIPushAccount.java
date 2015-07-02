package com.xiaomi.push.service;

import android.content.Context;
import com.xiaomi.push.service.PushClientsManager.ClientLoginInfo;
import com.xiaomi.xmsf.push.service.log.LogProvider;
import java.util.Locale;

public class MIPushAccount {
    public static final String PREF_KEY_APP_ID = "app_id";
    public static final String PREF_KEY_APP_TOKEN = "app_token";
    public static final String PREF_KEY_DEVICE_ID = "device_id";
    public static final String PREF_KEY_PACKAGENAME = "package_name";
    public static final String PREF_KEY_SECURITY = "security";
    public static final String PREF_KEY_TOKEN = "token";
    public static final String PREF_KEY_UUID = "uuid";
    public final String account;
    protected final String appId;
    protected final String appToken;
    protected final int envType;
    protected final String packageName;
    protected final String security;
    protected final String token;

    public MIPushAccount(String account, String token, String security, String appId, String appToken, String packageName, int type) {
        this.account = account;
        this.token = token;
        this.security = security;
        this.appId = appId;
        this.appToken = appToken;
        this.packageName = packageName;
        this.envType = type;
    }

    private static boolean isMIUIPush(Context context) {
        return context.getPackageName().equals(LogProvider.AUTHORITY);
    }

    public ClientLoginInfo toClientLoginInfo(XMPushService pushService) {
        ClientLoginInfo clientLoginInfo = new ClientLoginInfo(pushService);
        clientLoginInfo.pkgName = pushService.getPackageName();
        clientLoginInfo.userId = this.account;
        clientLoginInfo.security = this.security;
        clientLoginInfo.token = this.token;
        clientLoginInfo.chid = PushConstants.MIPUSH_CHANNEL;
        clientLoginInfo.authMethod = "XMPUSH-PASS";
        clientLoginInfo.kick = false;
        clientLoginInfo.clientExtra = "sdk_ver:2";
        String finalAppId = isMIUIPush(pushService) ? MIPushAccountUtils.MIPUSH_MIUI_APPID : this.appId;
        clientLoginInfo.cloudExtra = String.format("%1$s:%2$s,%3$s:%4$s,%5$s:%6$s", new Object[]{"dev_id", MIPushAccountUtils.getDeviceId(pushService), PushServiceConstants.EXTENSION_ATTRIBUTE_OPENPLATFORM_APPID, finalAppId, "locale", Locale.getDefault().toString()});
        clientLoginInfo.mClientEventDispatcher = pushService.getClientEventDispatcher();
        return clientLoginInfo;
    }
}
