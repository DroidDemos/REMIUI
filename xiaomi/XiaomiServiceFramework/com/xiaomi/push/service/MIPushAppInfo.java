package com.xiaomi.push.service;

import android.content.Context;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.string.XMStringUtils;
import com.xiaomi.mipush.sdk.MiPushClient;
import java.util.ArrayList;
import java.util.List;

public class MIPushAppInfo {
    private static final String PREF_KEY_UNREGISTERED_PKGS = "unregistered_pkg_names";
    private static final String PREF_NAME = "mipush_app_info";
    private static MIPushAppInfo sInstance;
    private Context appContext;
    private List<String> unRegisteredPkg;

    static {
        sInstance = null;
    }

    public static MIPushAppInfo getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new MIPushAppInfo(context);
        }
        return sInstance;
    }

    private MIPushAppInfo(Context context) {
        this.unRegisteredPkg = new ArrayList();
        this.appContext = context.getApplicationContext();
        if (this.appContext == null) {
            this.appContext = context;
        }
        for (String pkg : this.appContext.getSharedPreferences(PREF_NAME, 0).getString(PREF_KEY_UNREGISTERED_PKGS, "").split(MiPushClient.ACCEPT_TIME_SEPARATOR)) {
            if (TextUtils.isEmpty(pkg)) {
                this.unRegisteredPkg.add(pkg);
            }
        }
    }

    public boolean isUnRegistered(String packageName) {
        boolean contains;
        synchronized (this.unRegisteredPkg) {
            contains = this.unRegisteredPkg.contains(packageName);
        }
        return contains;
    }

    public void addUnRegisteredPkg(String packageName) {
        synchronized (this.unRegisteredPkg) {
            if (!this.unRegisteredPkg.contains(packageName)) {
                this.unRegisteredPkg.add(packageName);
                this.appContext.getSharedPreferences(PREF_NAME, 0).edit().putString(PREF_KEY_UNREGISTERED_PKGS, XMStringUtils.join(this.unRegisteredPkg, MiPushClient.ACCEPT_TIME_SEPARATOR)).commit();
            }
        }
    }

    public void removeUnRegisteredPkg(String packageName) {
        synchronized (this.unRegisteredPkg) {
            if (this.unRegisteredPkg.contains(packageName)) {
                this.unRegisteredPkg.remove(packageName);
                this.appContext.getSharedPreferences(PREF_NAME, 0).edit().putString(PREF_KEY_UNREGISTERED_PKGS, XMStringUtils.join(this.unRegisteredPkg, MiPushClient.ACCEPT_TIME_SEPARATOR)).commit();
            }
        }
    }
}
