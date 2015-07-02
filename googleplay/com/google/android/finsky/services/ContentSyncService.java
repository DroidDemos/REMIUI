package com.google.android.finsky.services;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.appstate.AppStatesReplicator;
import com.google.android.finsky.appstate.AppStatesReplicator.Listener;
import com.google.android.finsky.library.AccountLibrary;
import com.google.android.finsky.library.LibraryReplicators;
import com.google.android.finsky.receivers.PackageMonitorReceiver;
import com.google.android.finsky.receivers.PackageMonitorReceiver.PackageStatusListener;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.FinskyPreferences;

public class ContentSyncService extends Service {
    private static final Intent WAKE_ME_UP;
    private final AppStatesReplicator mAppStatesReplicator;
    private boolean mReplicating;
    private Listener mReplicationListener;
    private boolean mReplicationRequested;

    public interface Facade {
        void scheduleSync();
    }

    public ContentSyncService() {
        this.mAppStatesReplicator = FinskyApp.get().getAppStatesReplicator();
        this.mReplicationListener = new Listener() {
            public void onFinished(boolean success) {
                ContentSyncService.this.mReplicating = false;
                if (success) {
                    FinskyLog.d("Installation state replication succeeded.", new Object[0]);
                    FinskyPreferences.installationReplicationRetries.remove();
                    if (ContentSyncService.this.mReplicationRequested) {
                        FinskyLog.d("Another replication has been requested, rescheduling.", new Object[0]);
                        ContentSyncService.scheduleReplication();
                    }
                } else {
                    FinskyLog.d("Installation state replication failed.", new Object[0]);
                    int nextRetryNum = ((Integer) FinskyPreferences.installationReplicationRetries.get()).intValue() + 1;
                    if (nextRetryNum > 5) {
                        FinskyLog.d("Giving up after %d failures.", Integer.valueOf(nextRetryNum));
                        FinskyPreferences.installationReplicationRetries.remove();
                    } else {
                        FinskyLog.d("Scheduling replication attempt %d.", Integer.valueOf(nextRetryNum));
                        FinskyPreferences.installationReplicationRetries.put(Integer.valueOf(nextRetryNum));
                        ContentSyncService.scheduleReplication();
                    }
                }
                ContentSyncService.this.stopSelf();
            }
        };
    }

    static {
        WAKE_ME_UP = new Intent(FinskyApp.get(), ContentSyncService.class);
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        if (this.mReplicating) {
            this.mReplicationRequested = true;
        } else {
            this.mReplicationRequested = false;
            this.mAppStatesReplicator.load(new Runnable() {
                public void run() {
                    ContentSyncService.this.mAppStatesReplicator.replicate(ContentSyncService.this.mReplicationListener);
                }
            });
        }
        return 2;
    }

    public IBinder onBind(Intent intent) {
        return null;
    }

    public static Facade get() {
        return new Facade() {
            public void scheduleSync() {
                ContentSyncService.scheduleReplication();
            }
        };
    }

    public static void setupListeners(LibraryReplicators libraryReplicators, PackageMonitorReceiver packageMonitorReceiver) {
        libraryReplicators.addListener(new LibraryReplicators.Listener() {
            public void onMutationsApplied(AccountLibrary library, String libraryId) {
                if (AccountLibrary.LIBRARY_ID_APPS.equals(libraryId)) {
                    FinskyLog.d("App library has changed, requesting content sync.", new Object[0]);
                    ContentSyncService.get().scheduleSync();
                }
            }
        });
        packageMonitorReceiver.attach(new PackageStatusListener() {
            public void onPackageAdded(String packageName) {
                ContentSyncService.get().scheduleSync();
            }

            public void onPackageRemoved(String packageName, boolean replacing) {
                ContentSyncService.get().scheduleSync();
            }

            public void onPackageChanged(String packageName) {
            }

            public void onPackageAvailabilityChanged(String[] packageNames, boolean available) {
                ContentSyncService.get().scheduleSync();
            }

            public void onPackageFirstLaunch(String packageName) {
            }
        });
    }

    private static void scheduleReplication() {
        ((AlarmManager) FinskyApp.get().getSystemService("alarm")).set(0, System.currentTimeMillis() + (((Integer) FinskyPreferences.installationReplicationRetries.get()).intValue() == 0 ? 15000 : 20000), PendingIntent.getService(FinskyApp.get(), 0, WAKE_ME_UP, 0));
    }
}
