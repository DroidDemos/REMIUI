package com.xiaomi.push.service;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.os.Build;
import android.os.Build.VERSION;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.MyLog;
import com.xiaomi.channel.commonutils.misc.BuildSettings;
import com.xiaomi.channel.commonutils.network.Network;
import com.xiaomi.channel.commonutils.string.XMStringUtils;
import com.xiaomi.xmsf.push.service.log.LogProvider;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

public class MIPushAccountUtils {
    public static final String DEVICE_PREFIX = "a-";
    private static final String MIPUSH_ACCOUNT_HOST_P = "register.xmpush.xiaomi.com";
    private static final String MIPUSH_ACCOUNT_HOST_S = "sandbox.xmpush.xiaomi.com";
    public static final String MIPUSH_MIUI_APPID = "1000271";
    public static final String MIPUSH_MIUI_APP_TOKEN = "420100086271";
    private static final String PREF_KEY_ACCOUNT = "uuid";
    private static final String PREF_KEY_APP_ID = "app_id";
    private static final String PREF_KEY_APP_TOKEN = "app_token";
    private static final String PREF_KEY_DEVICE_ID = "device_id";
    private static final String PREF_KEY_ENV_TYPE = "env_type";
    private static final String PREF_KEY_PACKAGENAME = "package_name";
    private static final String PREF_KEY_SECURITY = "security";
    private static final String PREF_KEY_TOKEN = "token";
    private static final String PREF_NAME = "mipush_account";
    private static MIPushAccount sAccount;
    private static String sCachedDeviceId;
    private static String sCachedSimpleDeviceId;

    static {
        sCachedDeviceId = null;
        sCachedSimpleDeviceId = null;
    }

    public static synchronized MIPushAccount getMIPushAccount(Context context) {
        MIPushAccount mIPushAccount = null;
        synchronized (MIPushAccountUtils.class) {
            if (sAccount != null) {
                mIPushAccount = sAccount;
            } else {
                SharedPreferences sp = context.getSharedPreferences(PREF_NAME, 0);
                String uuid = sp.getString(PREF_KEY_ACCOUNT, null);
                String token = sp.getString(PREF_KEY_TOKEN, null);
                String security = sp.getString(PREF_KEY_SECURITY, null);
                String appId = sp.getString(PREF_KEY_APP_ID, null);
                String appToken = sp.getString(PREF_KEY_APP_TOKEN, null);
                String packageName = sp.getString(PREF_KEY_PACKAGENAME, null);
                String deviceId = sp.getString(PREF_KEY_DEVICE_ID, null);
                int envType = sp.getInt(PREF_KEY_ENV_TYPE, 1);
                if (!TextUtils.isEmpty(deviceId) && deviceId.startsWith(DEVICE_PREFIX)) {
                    deviceId = getSimpleDeviceId(context);
                    sp.edit().putString(PREF_KEY_DEVICE_ID, deviceId).commit();
                }
                if (!(TextUtils.isEmpty(uuid) || TextUtils.isEmpty(token) || TextUtils.isEmpty(security))) {
                    String currentDeviceId = getSimpleDeviceId(context);
                    if (LogProvider.AUTHORITY.equals(context.getPackageName()) || TextUtils.isEmpty(currentDeviceId) || TextUtils.isEmpty(deviceId) || deviceId.equals(currentDeviceId)) {
                        sAccount = new MIPushAccount(uuid, token, security, appId, appToken, packageName, envType);
                        mIPushAccount = sAccount;
                    } else {
                        MyLog.e("erase the old account.");
                        clearAccount(context);
                    }
                }
            }
        }
        return mIPushAccount;
    }

    public static synchronized MIPushAccount register(Context context, String packageName, String appId, String appToken) throws IOException, JSONException {
        MIPushAccount account;
        synchronized (MIPushAccountUtils.class) {
            String finalAppId;
            String finalAppToken;
            String finalPackageName;
            List<NameValuePair> params = new ArrayList();
            params.add(new BasicNameValuePair("devid", getDeviceId(context)));
            if (isMIUIPush(context)) {
                finalAppId = MIPUSH_MIUI_APPID;
            } else {
                finalAppId = appId;
            }
            if (isMIUIPush(context)) {
                finalAppToken = MIPUSH_MIUI_APP_TOKEN;
            } else {
                finalAppToken = appToken;
            }
            if (isMIUIPush(context)) {
                finalPackageName = LogProvider.AUTHORITY;
            } else {
                finalPackageName = packageName;
            }
            params.add(new BasicNameValuePair(PushServiceConstants.EXTENSION_ATTRIBUTE_OPENPLATFORM_APPID, finalAppId));
            params.add(new BasicNameValuePair("apptoken", finalAppToken));
            PackageInfo info = null;
            try {
                info = context.getPackageManager().getPackageInfo(finalPackageName, 16384);
            } catch (Throwable e) {
                MyLog.e(e);
            }
            params.add(new BasicNameValuePair("appversion", info != null ? String.valueOf(info.versionCode) : "0"));
            params.add(new BasicNameValuePair("sdkversion", PushConstants.MIPUSH_SDK_VERSION));
            params.add(new BasicNameValuePair("packagename", finalPackageName));
            params.add(new BasicNameValuePair("model", Build.MODEL));
            params.add(new BasicNameValuePair(Constants.JSON_TAG_IMEI, blockingGetIMEI(context)));
            params.add(new BasicNameValuePair("os", VERSION.RELEASE + "-" + VERSION.INCREMENTAL));
            String result = Network.doHttpPost(context, getAccountURL(), params);
            if (!TextUtils.isEmpty(result)) {
                JSONObject json = new JSONObject(result);
                if (json.getInt("code") == 0) {
                    JSONObject data = json.getJSONObject("data");
                    account = new MIPushAccount(data.getString(Constants.JSON_TAG_USERID) + "@xiaomi.com/an" + XMStringUtils.generateRandomString(6), data.getString(PREF_KEY_TOKEN), data.getString("ssecurity"), finalAppId, finalAppToken, finalPackageName, BuildSettings.getEnvType());
                    persist(context, account);
                    sAccount = account;
                } else {
                    MIPushClientManager.notifyRegisterError(context, json.getInt("code"), json.optString("description"));
                    MyLog.warn(result);
                }
            }
            account = null;
        }
        return account;
    }

    public static String getAccountURL() {
        if (BuildSettings.IsOneBoxBuild()) {
            return "http://10.237.12.17:9085/pass/register";
        }
        return "https://" + (BuildSettings.IsSandBoxBuild() ? MIPUSH_ACCOUNT_HOST_S : MIPUSH_ACCOUNT_HOST_P) + "/pass/register";
    }

    private static boolean isMIUIPush(Context context) {
        return context.getPackageName().equals(LogProvider.AUTHORITY);
    }

    protected static String getDeviceId(Context context) {
        if (sCachedDeviceId == null) {
            String deviceId = blockingGetIMEI(context);
            String androidId = null;
            try {
                androidId = Secure.getString(context.getContentResolver(), "android_id");
            } catch (Throwable e) {
                MyLog.e(e);
            }
            String serialNum = null;
            if (VERSION.SDK_INT > 8) {
                serialNum = Build.SERIAL;
            }
            sCachedDeviceId = DEVICE_PREFIX + XMStringUtils.getSHA1Digest(deviceId + androidId + serialNum);
        }
        return sCachedDeviceId;
    }

    private static String blockingGetIMEI(Context context) {
        try {
            TelephonyManager tm = (TelephonyManager) context.getSystemService("phone");
            String deviceId = tm.getDeviceId();
            int cnt = 10;
            while (deviceId == null) {
                int cnt2 = cnt - 1;
                if (cnt <= 0) {
                    return deviceId;
                }
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                }
                deviceId = tm.getDeviceId();
                cnt = cnt2;
            }
            return deviceId;
        } catch (Throwable e2) {
            MyLog.e(e2);
            return null;
        }
    }

    public static synchronized String getSimpleDeviceId(Context context) {
        String str;
        synchronized (MIPushAccountUtils.class) {
            if (sCachedSimpleDeviceId != null) {
                str = sCachedSimpleDeviceId;
            } else {
                String androidId = null;
                try {
                    androidId = Secure.getString(context.getContentResolver(), "android_id");
                } catch (Throwable e) {
                    MyLog.e(e);
                }
                String serialNum = null;
                if (VERSION.SDK_INT > 8) {
                    serialNum = Build.SERIAL;
                }
                sCachedSimpleDeviceId = XMStringUtils.getSHA1Digest(androidId + serialNum);
                str = sCachedSimpleDeviceId;
            }
        }
        return str;
    }

    private static void persist(Context context, MIPushAccount pushAccount) {
        Editor edit = context.getSharedPreferences(PREF_NAME, 0).edit();
        edit.putString(PREF_KEY_ACCOUNT, pushAccount.account);
        edit.putString(PREF_KEY_SECURITY, pushAccount.security);
        edit.putString(PREF_KEY_TOKEN, pushAccount.token);
        edit.putString(PREF_KEY_APP_ID, pushAccount.appId);
        edit.putString(PREF_KEY_PACKAGENAME, pushAccount.packageName);
        edit.putString(PREF_KEY_APP_TOKEN, pushAccount.appToken);
        edit.putString(PREF_KEY_DEVICE_ID, getSimpleDeviceId(context));
        edit.putInt(PREF_KEY_ENV_TYPE, pushAccount.envType);
        edit.commit();
    }

    public static void clearAccount(Context context) {
        context.getSharedPreferences(PREF_NAME, 0).edit().clear().commit();
        sAccount = null;
    }
}
