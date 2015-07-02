package com.xiaomi.xmsf;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.Application;
import android.content.BroadcastReceiver;
import android.os.Process;
import com.xiaomi.activate.ActivateExternal;
import com.xiaomi.channel.commonutils.logger.LoggerInterface;
import com.xiaomi.channel.commonutils.logger.MyLog;
import com.xiaomi.push.service.PushServiceConstants;
import com.xiaomi.xmsf.activate.ActivateSysImpl;
import com.xiaomi.xmsf.activate.AnalyticsAdapter;
import com.xiaomi.xmsf.activate.SimStateReceiver;
import com.xiaomi.xmsf.push.service.BusinessApp;
import com.xiaomi.xmsf.push.service.MiuiPushActivateService;
import com.xiaomi.xmsf.push.service.log.ConfigureLog4J;
import miui.analytics.Analytics;
import miui.external.ApplicationDelegate;
import miui.os.Build;

public class XmsfApp extends ApplicationDelegate {
    private static final String PREFIX_STARTUP = "xmsf_startup";
    private static final String PREF_EXTRA = "mipush_extra";
    private static volatile Application sApp;
    private BroadcastReceiver mSimStateReceiver;

    private boolean isAppMainProc() {
        for (RunningAppProcessInfo proc : ((ActivityManager) getSystemService("activity")).getRunningAppProcesses()) {
            if (proc.pid == Process.myPid() && proc.processName.equals(getPackageName())) {
                return true;
            }
        }
        return false;
    }

    public static Application getApp() {
        return sApp;
    }

    public void onCreate() {
        sApp = getApplication();
        super.onCreate();
        MyLog.setLogger(new LoggerInterface() {
            public void setTag(String tag) {
            }

            public void log(String content, Throwable t) {
                com.xiaomi.xmsf.push.service.MyLog.w(PushServiceConstants.LOG_TAG, content, t);
            }

            public void log(String content) {
                com.xiaomi.xmsf.push.service.MyLog.w(content);
            }
        });
        if (Build.IS_DEVELOPMENT_VERSION || android.os.Build.IS_DEBUGGABLE) {
            MyLog.setLogLevel(0);
        } else {
            MyLog.setLogLevel(2);
        }
        ConfigureLog4J.configure(getApplicationContext());
        if (isAppMainProc()) {
            BusinessApp.getInstance(this).init();
        }
        this.mSimStateReceiver = SimStateReceiver.register(sApp);
        ActivateExternal.setApp(this);
        ActivateExternal.setSysInterface(new ActivateSysImpl(this));
        ActivateExternal.customizeAnalyticsTrackerInstance(new AnalyticsAdapter(Analytics.getInstance()));
        long currentTs = System.currentTimeMillis();
        long lastStartupTime = getLastStartupTime();
        if (currentTs - lastStartupTime > 300000 || currentTs - lastStartupTime < 0) {
            MiuiPushActivateService.awakePushActivateService(this, MiuiPushActivateService.ACTION_SCAN);
            setStartupTime(currentTs);
        }
    }

    public void onLowMemory() {
        super.onLowMemory();
        if (isAppMainProc()) {
            BusinessApp.getInstance(this).release();
        }
    }

    public void onTerminate() {
        if (this.mSimStateReceiver != null) {
            SimStateReceiver.unregister(sApp, this.mSimStateReceiver);
            this.mSimStateReceiver = null;
        }
        super.onTerminate();
    }

    private long getLastStartupTime() {
        return getSharedPreferences(PREF_EXTRA, 0).getLong(PREFIX_STARTUP, 0);
    }

    private void setStartupTime(long ts) {
        getSharedPreferences(PREF_EXTRA, 0).edit().putLong(PREFIX_STARTUP, ts).commit();
    }
}
