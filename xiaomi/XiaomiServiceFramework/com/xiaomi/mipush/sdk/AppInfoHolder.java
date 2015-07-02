package com.xiaomi.mipush.sdk;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.MyLog;
import com.xiaomi.push.service.MIPushAccountUtils;

public class AppInfoHolder {
    private static final String PREF_KEY_APP_ID = "appId";
    private static final String PREF_KEY_APP_TOKEN = "appToken";
    private static final String PREF_KEY_DEVICE_ID = "devId";
    private static final String PREF_KEY_ENV_TYPE = "envType";
    private static final String PREF_KEY_PAUSED = "paused";
    private static final String PREF_KEY_REG_ID = "regId";
    private static final String PREF_KEY_REG_SECRET = "regSec";
    private static final String PREF_KEY_VALID = "valid";
    private static final String PREF_KEY_VERSION_NAME = "vName";
    private static AppInfoHolder sInstance;
    private Context mContext;
    private ClientInfoData mInfoData;

    private class ClientInfoData {
        public String appID;
        public String appToken;
        public String deviceId;
        public int envType;
        public boolean isPaused;
        public boolean isValid;
        public String regID;
        public String regSecret;
        public String versionName;

        private ClientInfoData() {
            this.isValid = true;
            this.isPaused = false;
            this.envType = 1;
        }

        public void setIdAndToken(String appId, String appToken) {
            this.appID = appId;
            this.appToken = appToken;
            Editor editor = AppInfoHolder.this.getSharedPreferences().edit();
            editor.putString(AppInfoHolder.PREF_KEY_APP_ID, this.appID);
            editor.putString(AppInfoHolder.PREF_KEY_APP_TOKEN, appToken);
            editor.commit();
        }

        public void setRegIdAndSecret(String regID, String regSecret) {
            this.regID = regID;
            this.regSecret = regSecret;
            this.deviceId = MIPushAccountUtils.getSimpleDeviceId(AppInfoHolder.this.mContext);
            this.versionName = getVersionName();
            this.isValid = true;
            Editor editor = AppInfoHolder.this.getSharedPreferences().edit();
            editor.putString(AppInfoHolder.PREF_KEY_REG_ID, regID);
            editor.putString(AppInfoHolder.PREF_KEY_REG_SECRET, regSecret);
            editor.putString(AppInfoHolder.PREF_KEY_DEVICE_ID, this.deviceId);
            editor.putString(AppInfoHolder.PREF_KEY_VERSION_NAME, getVersionName());
            editor.putBoolean(AppInfoHolder.PREF_KEY_VALID, true);
            editor.commit();
        }

        public boolean isVaild(String appId, String appToken) {
            return TextUtils.equals(this.appID, appId) && TextUtils.equals(this.appToken, appToken) && !TextUtils.isEmpty(this.regID) && !TextUtils.isEmpty(this.regSecret) && TextUtils.equals(this.deviceId, MIPushAccountUtils.getSimpleDeviceId(AppInfoHolder.this.mContext));
        }

        public boolean isVaild() {
            return isVaild(this.appID, this.appToken);
        }

        private String getVersionName() {
            return AppInfoHolder.getVersionName(AppInfoHolder.this.mContext, AppInfoHolder.this.mContext.getPackageName());
        }

        public void clear() {
            AppInfoHolder.this.getSharedPreferences().edit().clear().commit();
            this.appID = null;
            this.appToken = null;
            this.regID = null;
            this.regSecret = null;
            this.deviceId = null;
            this.versionName = null;
            this.isValid = false;
            this.isPaused = false;
            this.envType = 1;
        }

        public void setPaused(boolean paused) {
            this.isPaused = paused;
        }

        public void setEnvType(int type) {
            this.envType = type;
        }

        public void invalidate() {
            this.isValid = false;
            AppInfoHolder.this.getSharedPreferences().edit().putBoolean(AppInfoHolder.PREF_KEY_VALID, this.isValid).commit();
        }
    }

    public static AppInfoHolder getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new AppInfoHolder(context);
        }
        return sInstance;
    }

    private AppInfoHolder(Context context) {
        this.mContext = context;
        init();
    }

    private void init() {
        this.mInfoData = new ClientInfoData();
        SharedPreferences sp = getSharedPreferences();
        this.mInfoData.appID = sp.getString(PREF_KEY_APP_ID, null);
        this.mInfoData.appToken = sp.getString(PREF_KEY_APP_TOKEN, null);
        this.mInfoData.regID = sp.getString(PREF_KEY_REG_ID, null);
        this.mInfoData.regSecret = sp.getString(PREF_KEY_REG_SECRET, null);
        this.mInfoData.deviceId = sp.getString(PREF_KEY_DEVICE_ID, null);
        if (!TextUtils.isEmpty(this.mInfoData.deviceId) && this.mInfoData.deviceId.startsWith(MIPushAccountUtils.DEVICE_PREFIX)) {
            this.mInfoData.deviceId = MIPushAccountUtils.getSimpleDeviceId(this.mContext);
            sp.edit().putString(PREF_KEY_DEVICE_ID, this.mInfoData.deviceId).commit();
        }
        this.mInfoData.versionName = sp.getString(PREF_KEY_VERSION_NAME, null);
        this.mInfoData.isValid = sp.getBoolean(PREF_KEY_VALID, true);
        this.mInfoData.isPaused = sp.getBoolean(PREF_KEY_PAUSED, false);
        this.mInfoData.envType = sp.getInt(PREF_KEY_ENV_TYPE, 1);
    }

    public boolean checkAppInfo() {
        if (this.mInfoData.isVaild()) {
            return true;
        }
        MyLog.warn("Don't send message before initialization succeeded!");
        return false;
    }

    public String getAppID() {
        return this.mInfoData.appID;
    }

    public String getAppToken() {
        return this.mInfoData.appToken;
    }

    public String getRegID() {
        return this.mInfoData.regID;
    }

    public String getRegSecret() {
        return this.mInfoData.regSecret;
    }

    public boolean appRegistered(String appID, String appToken) {
        return this.mInfoData.isVaild(appID, appToken);
    }

    public void putAppIDAndToken(String appID, String appToken) {
        this.mInfoData.setIdAndToken(appID, appToken);
    }

    public void putRegIDAndSecret(String regID, String regSecret) {
        this.mInfoData.setRegIdAndSecret(regID, regSecret);
    }

    public void clear() {
        this.mInfoData.clear();
    }

    public boolean appRegistered() {
        return this.mInfoData.isVaild();
    }

    public SharedPreferences getSharedPreferences() {
        return this.mContext.getSharedPreferences("mipush", 0);
    }

    public void invalidate() {
        this.mInfoData.invalidate();
    }

    public static String getVersionName(Context context, String packageName) {
        PackageInfo info = null;
        try {
            info = context.getPackageManager().getPackageInfo(packageName, 16384);
        } catch (Throwable e) {
            MyLog.e(e);
        }
        return info != null ? info.versionName : "1.0";
    }

    public boolean isPaused() {
        return this.mInfoData.isPaused;
    }

    public int getEnvType() {
        return this.mInfoData.envType;
    }

    public void setPaused(boolean paused) {
        this.mInfoData.setPaused(paused);
        getSharedPreferences().edit().putBoolean(PREF_KEY_PAUSED, paused).commit();
    }

    public void setEnvType(int type) {
        this.mInfoData.setEnvType(type);
        getSharedPreferences().edit().putInt(PREF_KEY_ENV_TYPE, type).commit();
    }

    public boolean invalidated() {
        return !this.mInfoData.isValid;
    }
}
