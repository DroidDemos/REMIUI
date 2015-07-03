package com.xiaomi.account;

import android.app.Application;
import com.xiaomi.account.utils.AnalyticsAdapter;
import com.xiaomi.accountsdk.account.XMPassportSettings;
import com.xiaomi.micloudsdk.request.Request;
import com.xiaomi.passport.PassportExternal;
import miui.analytics.Analytics;
import miui.cloud.CloudManager;
import miui.external.ApplicationDelegate;

public class XiaomiAccountAppDelegate extends ApplicationDelegate {
    private static volatile Application sApp;

    public static Application getApp() {
        return sApp;
    }

    public void onCreate() {
        sApp = getApplication();
        XMPassportSettings.setUserAgent(CloudManager.getUserAgent());
        Request.init(sApp);
        PassportExternal.setPassportInterface(new PassportImplementation(this));
        super.onCreate();
        PassportExternal.customizeAnalyticsTrackerInstance(new AnalyticsAdapter(Analytics.getInstance()));
    }
}
