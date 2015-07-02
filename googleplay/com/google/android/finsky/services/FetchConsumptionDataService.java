package com.google.android.finsky.services;

import android.app.AlarmManager;
import android.app.IntentService;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import com.google.android.finsky.utils.BackgroundThreadFactory;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.ParcelUtils;
import com.google.android.finsky.widget.consumption.ConsumptionAppDocList;
import com.google.android.play.IUserContentService;
import com.google.android.play.IUserContentService.Stub;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class FetchConsumptionDataService extends IntentService {
    private static final ExecutorService sFetchThread;

    private class ConsumptionAppServiceConn implements ServiceConnection {
        private final int mBackendId;
        private final Semaphore mLock;
        private IUserContentService mService;

        public ConsumptionAppServiceConn(int backendId, Semaphore lock) {
            this.mBackendId = backendId;
            this.mLock = lock;
        }

        public void onServiceConnected(ComponentName name, final IBinder service) {
            FetchConsumptionDataService.sFetchThread.execute(new Runnable() {
                public void run() {
                    ConsumptionAppServiceConn.this.mService = Stub.asInterface(service);
                    ConsumptionAppServiceConn.this.getDataFromService();
                    ConsumptionAppServiceConn.this.mLock.release();
                }
            });
        }

        public void onServiceDisconnected(ComponentName name) {
            this.mService = null;
            this.mLock.release();
        }

        private void getDataFromService() {
            try {
                List whatsNext = this.mService.getDocuments(0, 20);
                if (whatsNext != null) {
                    FinskyLog.d("retrieved %d documents from [%d]", Integer.valueOf(whatsNext.size()), Integer.valueOf(this.mBackendId));
                    ConsumptionAppDataCache dataCache = ConsumptionAppDataCache.get();
                    Context context = FetchConsumptionDataService.this;
                    dataCache.setConsumptionAppData(context, this.mBackendId, whatsNext);
                    ConsumptionAppDocList docList = ConsumptionAppDocList.createFromBundles(this.mBackendId, whatsNext);
                    try {
                        ParcelUtils.writeToDisk(ConsumptionAppDataCache.getCacheFile(context, docList.getBackend()), docList);
                        return;
                    } catch (IOException e) {
                        FinskyLog.e("Failed to cache %s: %s", Integer.valueOf(this.mBackendId), e.getMessage());
                        return;
                    }
                }
                FinskyLog.d("Error fetching data from service for backend=[%s]", Integer.valueOf(this.mBackendId));
            } catch (Exception e2) {
                FinskyLog.e(e2, "Exception during data fetch: %s", e2.getMessage());
            }
        }
    }

    static {
        sFetchThread = Executors.newSingleThreadExecutor(new BackgroundThreadFactory());
    }

    public FetchConsumptionDataService() {
        super(FetchConsumptionDataService.class.getSimpleName());
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected void onHandleIntent(android.content.Intent r13) {
        /*
        r12 = this;
        r11 = 1;
        r10 = 0;
        r8 = "backendId";
        r0 = r13.getIntExtra(r8, r10);
        r3 = com.google.android.finsky.utils.IntentUtils.getPackageName(r0);
        r2 = new java.util.concurrent.Semaphore;
        r2.<init>(r10);
        r8 = "Starting ServiceConnection to consumption app: %s";
        r9 = new java.lang.Object[r11];
        r9[r10] = r3;
        com.google.android.finsky.utils.FinskyLog.d(r8, r9);
        r5 = new android.content.Intent;
        r8 = "com.google.android.play.IUserContentService.BIND";
        r5.<init>(r8);
        r5.setPackage(r3);
        r4 = new com.google.android.finsky.services.FetchConsumptionDataService$ConsumptionAppServiceConn;
        r4.<init>(r0, r2);
        r12.bindService(r5, r4, r11);
        r8 = com.google.android.finsky.config.G.consumptionAppTimeoutMs;
        r8 = r8.get();
        r8 = (java.lang.Long) r8;
        r6 = r8.longValue();
        r8 = java.util.concurrent.TimeUnit.MILLISECONDS;	 Catch:{ InterruptedException -> 0x005a }
        r8 = r2.tryAcquire(r6, r8);	 Catch:{ InterruptedException -> 0x005a }
        if (r8 != 0) goto L_0x0056;
    L_0x0040:
        r8 = "Service connection for %d timed out after %d";
        r9 = 2;
        r9 = new java.lang.Object[r9];	 Catch:{ InterruptedException -> 0x005a }
        r10 = 0;
        r11 = java.lang.Integer.valueOf(r0);	 Catch:{ InterruptedException -> 0x005a }
        r9[r10] = r11;	 Catch:{ InterruptedException -> 0x005a }
        r10 = 1;
        r11 = java.lang.Long.valueOf(r6);	 Catch:{ InterruptedException -> 0x005a }
        r9[r10] = r11;	 Catch:{ InterruptedException -> 0x005a }
        com.google.android.finsky.utils.FinskyLog.e(r8, r9);	 Catch:{ InterruptedException -> 0x005a }
    L_0x0056:
        r12.unbindService(r4);
    L_0x0059:
        return;
    L_0x005a:
        r1 = move-exception;
        r8 = "Interrupted while connecting to remote service";
        r9 = 0;
        r9 = new java.lang.Object[r9];	 Catch:{ all -> 0x0067 }
        com.google.android.finsky.utils.FinskyLog.d(r8, r9);	 Catch:{ all -> 0x0067 }
        r12.unbindService(r4);
        goto L_0x0059;
    L_0x0067:
        r8 = move-exception;
        r12.unbindService(r4);
        throw r8;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.finsky.services.FetchConsumptionDataService.onHandleIntent(android.content.Intent):void");
    }

    public static void fetch(Context context, int backendId) {
        Intent intent = new Intent(context, FetchConsumptionDataService.class);
        intent.putExtra("backendId", backendId);
        intent.setAction(FetchConsumptionDataService.class.getSimpleName() + ":" + backendId);
        ((AlarmManager) context.getSystemService("alarm")).set(1, System.currentTimeMillis() + 500, PendingIntent.getService(context, 0, intent, 0));
    }
}
