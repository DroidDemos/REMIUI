package com.xiaomi.passport.accountmanager;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import com.xiaomi.miui.analyticstracker.AnalyticsTracker;

public class MiAuthenticatorService extends Service {
    private static final String TAG = "MiAuthenticatorService";
    private AnalyticsTracker mAnalyticsTracker;
    private LocalAccountAuthenticator mLocalAuth;
    private SystemAccountAuthenticator mSystemAuth;

    public MiAuthenticatorService() {
        this.mLocalAuth = null;
        this.mSystemAuth = null;
    }

    private synchronized LocalAccountAuthenticator ensureLocalAuth() {
        if (this.mLocalAuth == null) {
            this.mLocalAuth = new LocalAccountAuthenticator(this);
            this.mLocalAuth.setAnalytics(this.mAnalyticsTracker);
        }
        return this.mLocalAuth;
    }

    private synchronized SystemAccountAuthenticator ensureSystemAuth() {
        if (this.mSystemAuth == null) {
            this.mSystemAuth = new SystemAccountAuthenticator(this);
            this.mSystemAuth.setAnalytics(this.mAnalyticsTracker);
        }
        return this.mSystemAuth;
    }

    public IBinder onBind(Intent intent) {
        if (MiAccountManager.get(getApplicationContext()).isUseLocal()) {
            return ensureLocalAuth().getIBinder();
        }
        if (MiAccountManager.get(getApplicationContext()).isUseSystem()) {
            return ensureSystemAuth().getIBinder();
        }
        Log.v(TAG, "null IBinder returned");
        return null;
    }

    public void onCreate() {
        super.onCreate();
        this.mAnalyticsTracker = AnalyticsTracker.getInstance();
        this.mAnalyticsTracker.startSession(this);
    }

    public void onDestroy() {
        this.mAnalyticsTracker.endSession();
        super.onDestroy();
    }
}
