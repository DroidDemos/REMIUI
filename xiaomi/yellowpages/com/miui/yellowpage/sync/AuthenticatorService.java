package com.miui.yellowpage.sync;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class AuthenticatorService extends Service {
    private b mAuthenticator;

    public void onCreate() {
        this.mAuthenticator = new b(this);
    }

    public IBinder onBind(Intent intent) {
        return this.mAuthenticator.getIBinder();
    }
}
