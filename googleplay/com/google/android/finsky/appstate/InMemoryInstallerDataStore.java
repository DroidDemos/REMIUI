package com.google.android.finsky.appstate;

import com.google.android.finsky.appstate.InstallerDataStore.AutoUpdateState;
import com.google.android.finsky.appstate.InstallerDataStore.InstallerData;
import com.google.android.finsky.appstate.InstallerDataStore.InstallerData.Builder;
import com.google.android.finsky.protos.AndroidAppDelivery.AndroidAppDeliveryData;
import com.google.android.finsky.utils.Maps;
import java.util.Collection;
import java.util.Map;

public class InMemoryInstallerDataStore implements InstallerDataStore {
    private final Map<String, InstallerData> mAppStates;

    public InMemoryInstallerDataStore() {
        this.mAppStates = Maps.newHashMap();
    }

    public synchronized InstallerData get(String packageName) {
        return (InstallerData) this.mAppStates.get(packageName);
    }

    public synchronized Collection<InstallerData> getAll() {
        return this.mAppStates.values();
    }

    public synchronized void put(InstallerData state) {
        this.mAppStates.put(state.getPackageName(), state);
    }

    public synchronized void setAutoUpdate(String packageName, AutoUpdateState autoUpdate) {
        this.mAppStates.put(packageName, Builder.buildUpon(get(packageName), packageName).setAutoUpdate(autoUpdate).build());
    }

    public synchronized void setDeliveryData(String packageName, AndroidAppDeliveryData deliveryData, long timestampMs) {
        this.mAppStates.put(packageName, Builder.buildUpon(get(packageName), packageName).setDeliveryData(deliveryData, timestampMs).build());
    }

    public synchronized void setDesiredVersion(String packageName, int desiredVersion) {
        this.mAppStates.put(packageName, Builder.buildUpon(get(packageName), packageName).setDesiredVersion(desiredVersion).build());
    }

    public synchronized void setLastNotifiedVersion(String packageName, int lastNotifiedVersion) {
        this.mAppStates.put(packageName, Builder.buildUpon(get(packageName), packageName).setLastNotifiedVersion(lastNotifiedVersion).build());
    }

    public synchronized void setInstallerState(String packageName, int installerState, String downloadUri) {
        this.mAppStates.put(packageName, Builder.buildUpon(get(packageName), packageName).setInstallerState(installerState).setDownloadUri(downloadUri).build());
    }

    public synchronized void setFirstDownloadMs(String packageName, long firstDownloadMs) {
        this.mAppStates.put(packageName, Builder.buildUpon(get(packageName), packageName).setFirstDownloadMs(firstDownloadMs).build());
    }

    public synchronized void setExternalReferrer(String packageName, String externalReferrer) {
        this.mAppStates.put(packageName, Builder.buildUpon(get(packageName), packageName).setExternalReferrer(externalReferrer).build());
    }

    public synchronized void setFlags(String packageName, int flags) {
        this.mAppStates.put(packageName, Builder.buildUpon(get(packageName), packageName).setFlags(flags).build());
    }

    public void setContinueUrl(String packageName, String continueUrl) {
        this.mAppStates.put(packageName, Builder.buildUpon(get(packageName), packageName).setContinueUrl(continueUrl).build());
    }

    public void setLastUpdateTimestampMs(String packageName, long lastUpdateTimestampMs) {
        this.mAppStates.put(packageName, Builder.buildUpon(get(packageName), packageName).setLastUpdateTimestampMs(lastUpdateTimestampMs).build());
    }

    public synchronized void setAccountForUpdate(String packageName, String accountForUpdate) {
        this.mAppStates.put(packageName, Builder.buildUpon(get(packageName), packageName).setAccountForUpdate(accountForUpdate).build());
    }

    public synchronized void setAutoAcquireTags(String packageName, String[] autoAcquireTags) {
        this.mAppStates.put(packageName, Builder.buildUpon(get(packageName), packageName).setAutoAcquireTags(autoAcquireTags).build());
    }

    public void setExternalReferrerTimestampMs(String packageName, long externalReferrerTimestampMs) {
        this.mAppStates.put(packageName, Builder.buildUpon(get(packageName), packageName).setExternalReferrerTimestampMs(externalReferrerTimestampMs).build());
    }

    public void setPersistentFlags(String packageName, int persistentFlags) {
        this.mAppStates.put(packageName, Builder.buildUpon(get(packageName), packageName).setPersistentFlags(persistentFlags).build());
    }

    public void setPermissionsVersion(String packageName, int permissionsVersion) {
        this.mAppStates.put(packageName, Builder.buildUpon(get(packageName), packageName).setPermissionsVersion(permissionsVersion).build());
    }

    public void setDeliveryToken(String packageName, String deliveryToken) {
        this.mAppStates.put(packageName, Builder.buildUpon(get(packageName), packageName).setDeliveryToken(deliveryToken).build());
    }

    public synchronized void removeLocalAppState(String packageName) {
        this.mAppStates.remove(packageName);
    }
}
