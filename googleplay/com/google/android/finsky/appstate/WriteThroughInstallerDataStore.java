package com.google.android.finsky.appstate;

import android.os.Handler;
import com.google.android.finsky.appstate.InstallerDataStore.AutoUpdateState;
import com.google.android.finsky.appstate.InstallerDataStore.InstallerData;
import com.google.android.finsky.protos.AndroidAppDelivery.AndroidAppDeliveryData;
import java.util.ArrayList;
import java.util.Collection;

public class WriteThroughInstallerDataStore implements InstallerDataStore {
    private final InstallerDataStore mInMemoryStore;
    private boolean mIsLoaded;
    private Collection<Runnable> mLoadedCallbacks;
    private final Handler mNotificationHandler;
    private final InstallerDataStore mPersistentStore;
    private final Handler mWriteThroughHandler;

    public WriteThroughInstallerDataStore(InstallerDataStore inMemoryStore, InstallerDataStore persistingStore, Handler writeThroughHandler, Handler notificationHandler) {
        this.mLoadedCallbacks = new ArrayList();
        this.mInMemoryStore = inMemoryStore;
        this.mPersistentStore = persistingStore;
        this.mWriteThroughHandler = writeThroughHandler;
        this.mNotificationHandler = notificationHandler;
    }

    public synchronized boolean isLoaded() {
        return this.mIsLoaded;
    }

    public synchronized void load() {
        if (!this.mIsLoaded) {
            for (InstallerData installerDataSnapshot : this.mPersistentStore.getAll()) {
                this.mInMemoryStore.put(installerDataSnapshot);
            }
            this.mIsLoaded = true;
        }
        for (Runnable loadedCallback : this.mLoadedCallbacks) {
            if (loadedCallback != null) {
                this.mNotificationHandler.post(loadedCallback);
            }
        }
        this.mLoadedCallbacks.clear();
    }

    public synchronized boolean load(Runnable callback) {
        boolean z;
        if (this.mIsLoaded) {
            if (callback != null) {
                this.mNotificationHandler.post(callback);
            }
            z = true;
        } else {
            this.mLoadedCallbacks.add(callback);
            if (this.mLoadedCallbacks.size() < 2) {
                this.mWriteThroughHandler.post(new Runnable() {
                    public void run() {
                        WriteThroughInstallerDataStore.this.load();
                    }
                });
            }
            z = false;
        }
        return z;
    }

    public synchronized InstallerData get(String packageName) {
        return this.mInMemoryStore.get(packageName);
    }

    public synchronized Collection<InstallerData> getAll() {
        return this.mInMemoryStore.getAll();
    }

    public synchronized void put(final InstallerData state) {
        this.mInMemoryStore.put(state);
        this.mWriteThroughHandler.post(new Runnable() {
            public void run() {
                WriteThroughInstallerDataStore.this.mPersistentStore.put(state);
            }
        });
    }

    public synchronized void setAutoUpdate(final String packageName, final AutoUpdateState autoUpdate) {
        this.mInMemoryStore.setAutoUpdate(packageName, autoUpdate);
        this.mWriteThroughHandler.post(new Runnable() {
            public void run() {
                WriteThroughInstallerDataStore.this.mPersistentStore.setAutoUpdate(packageName, autoUpdate);
            }
        });
    }

    public synchronized void setDesiredVersion(final String packageName, final int desiredVersion) {
        this.mInMemoryStore.setDesiredVersion(packageName, desiredVersion);
        this.mWriteThroughHandler.post(new Runnable() {
            public void run() {
                WriteThroughInstallerDataStore.this.mPersistentStore.setDesiredVersion(packageName, desiredVersion);
            }
        });
    }

    public synchronized void setLastNotifiedVersion(final String packageName, final int lastNotifiedVersion) {
        this.mInMemoryStore.setLastNotifiedVersion(packageName, lastNotifiedVersion);
        this.mWriteThroughHandler.post(new Runnable() {
            public void run() {
                WriteThroughInstallerDataStore.this.mPersistentStore.setLastNotifiedVersion(packageName, lastNotifiedVersion);
            }
        });
    }

    public synchronized void setDeliveryData(String packageName, AndroidAppDeliveryData deliveryData, long timestampMs) {
        this.mInMemoryStore.setDeliveryData(packageName, deliveryData, timestampMs);
        final String str = packageName;
        final AndroidAppDeliveryData androidAppDeliveryData = deliveryData;
        final long j = timestampMs;
        this.mWriteThroughHandler.post(new Runnable() {
            public void run() {
                WriteThroughInstallerDataStore.this.mPersistentStore.setDeliveryData(str, androidAppDeliveryData, j);
            }
        });
    }

    public synchronized void setInstallerState(final String packageName, final int installerState, final String downloadUri) {
        this.mInMemoryStore.setInstallerState(packageName, installerState, downloadUri);
        this.mWriteThroughHandler.post(new Runnable() {
            public void run() {
                WriteThroughInstallerDataStore.this.mPersistentStore.setInstallerState(packageName, installerState, downloadUri);
            }
        });
    }

    public synchronized void setFirstDownloadMs(final String packageName, final long firstDownloadMs) {
        this.mInMemoryStore.setFirstDownloadMs(packageName, firstDownloadMs);
        this.mWriteThroughHandler.post(new Runnable() {
            public void run() {
                WriteThroughInstallerDataStore.this.mPersistentStore.setFirstDownloadMs(packageName, firstDownloadMs);
            }
        });
    }

    public synchronized void setExternalReferrer(final String packageName, final String externalReferrer) {
        this.mInMemoryStore.setExternalReferrer(packageName, externalReferrer);
        this.mWriteThroughHandler.post(new Runnable() {
            public void run() {
                WriteThroughInstallerDataStore.this.mPersistentStore.setExternalReferrer(packageName, externalReferrer);
            }
        });
    }

    public synchronized void setFlags(final String packageName, final int flags) {
        this.mInMemoryStore.setFlags(packageName, flags);
        this.mWriteThroughHandler.post(new Runnable() {
            public void run() {
                WriteThroughInstallerDataStore.this.mPersistentStore.setFlags(packageName, flags);
            }
        });
    }

    public synchronized void setContinueUrl(final String packageName, final String continueUrl) {
        this.mInMemoryStore.setContinueUrl(packageName, continueUrl);
        this.mWriteThroughHandler.post(new Runnable() {
            public void run() {
                WriteThroughInstallerDataStore.this.mPersistentStore.setContinueUrl(packageName, continueUrl);
            }
        });
    }

    public void setLastUpdateTimestampMs(final String packageName, final long lastUpdateTimestampMs) {
        this.mInMemoryStore.setLastUpdateTimestampMs(packageName, lastUpdateTimestampMs);
        this.mWriteThroughHandler.post(new Runnable() {
            public void run() {
                WriteThroughInstallerDataStore.this.mPersistentStore.setLastUpdateTimestampMs(packageName, lastUpdateTimestampMs);
            }
        });
    }

    public synchronized void setAccountForUpdate(final String packageName, final String accountForUpdate) {
        this.mInMemoryStore.setAccountForUpdate(packageName, accountForUpdate);
        this.mWriteThroughHandler.post(new Runnable() {
            public void run() {
                WriteThroughInstallerDataStore.this.mPersistentStore.setAccountForUpdate(packageName, accountForUpdate);
            }
        });
    }

    public synchronized void setAutoAcquireTags(final String packageName, final String[] autoAcquireTags) {
        this.mInMemoryStore.setAutoAcquireTags(packageName, autoAcquireTags);
        this.mWriteThroughHandler.post(new Runnable() {
            public void run() {
                WriteThroughInstallerDataStore.this.mPersistentStore.setAutoAcquireTags(packageName, autoAcquireTags);
            }
        });
    }

    public void setExternalReferrerTimestampMs(final String packageName, final long externalReferrerTimestampMs) {
        this.mInMemoryStore.setExternalReferrerTimestampMs(packageName, externalReferrerTimestampMs);
        this.mWriteThroughHandler.post(new Runnable() {
            public void run() {
                WriteThroughInstallerDataStore.this.mPersistentStore.setExternalReferrerTimestampMs(packageName, externalReferrerTimestampMs);
            }
        });
    }

    public void setPersistentFlags(final String packageName, final int persistentFlags) {
        this.mInMemoryStore.setPersistentFlags(packageName, persistentFlags);
        this.mWriteThroughHandler.post(new Runnable() {
            public void run() {
                WriteThroughInstallerDataStore.this.mPersistentStore.setPersistentFlags(packageName, persistentFlags);
            }
        });
    }

    public void setPermissionsVersion(final String packageName, final int permissionsVersion) {
        this.mInMemoryStore.setPermissionsVersion(packageName, permissionsVersion);
        this.mWriteThroughHandler.post(new Runnable() {
            public void run() {
                WriteThroughInstallerDataStore.this.mPersistentStore.setPermissionsVersion(packageName, permissionsVersion);
            }
        });
    }

    public void setDeliveryToken(final String packageName, final String deliveryToken) {
        this.mInMemoryStore.setDeliveryToken(packageName, deliveryToken);
        this.mWriteThroughHandler.post(new Runnable() {
            public void run() {
                WriteThroughInstallerDataStore.this.mPersistentStore.setDeliveryToken(packageName, deliveryToken);
            }
        });
    }

    public synchronized void removeLocalAppState(final String packageName) {
        this.mInMemoryStore.removeLocalAppState(packageName);
        this.mWriteThroughHandler.post(new Runnable() {
            public void run() {
                WriteThroughInstallerDataStore.this.mPersistentStore.removeLocalAppState(packageName);
            }
        });
    }
}
