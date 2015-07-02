package com.xiaomi.activate;

import android.content.Context;
import com.xiaomi.miui.analyticstracker.AnalyticsTracker;

public class ActivateExternal {
    public static Context sApp;
    public static SysInterface sSysInterface;

    public static void setApp(Context app) {
        sApp = app;
    }

    public static Context getApp() {
        return sApp;
    }

    public static void setSysInterface(SysInterface sysInterface) {
        sSysInterface = sysInterface;
    }

    public static SysInterface getSysInteface() {
        return sSysInterface;
    }

    public static void customizeAnalyticsTrackerInstance(AnalyticsTracker analyticsTracker) {
        AnalyticsTracker.customizeAnalyticsTrackerInstance(analyticsTracker);
    }
}
