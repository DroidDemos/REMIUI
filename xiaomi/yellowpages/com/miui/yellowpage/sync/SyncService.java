package com.miui.yellowpage.sync;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import com.miui.yellowpage.base.utils.Log;

public class SyncService extends Service {
    private static final String AUTH_TYPE = "micloud";
    private static final String TAG = "SyncService";
    private static final Object sLock;
    private static a sSyncAdapter;

    static {
        sSyncAdapter = null;
        sLock = new Object();
    }

    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind");
        return sSyncAdapter.getSyncAdapterBinder();
    }

    public void onCreate() {
        Log.d(TAG, "onCreate");
        synchronized (sLock) {
            if (sSyncAdapter == null) {
                sSyncAdapter = new a(getApplicationContext(), true);
            }
        }
    }
}
