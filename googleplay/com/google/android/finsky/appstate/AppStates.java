package com.google.android.finsky.appstate;

import com.google.android.finsky.appstate.InstallerDataStore.InstallerData;
import com.google.android.finsky.appstate.PackageStateRepository.PackageState;
import com.google.android.finsky.library.AccountLibrary;
import com.google.android.finsky.library.Libraries;
import com.google.android.finsky.library.LibraryAppEntry;
import com.google.android.finsky.utils.Lists;
import com.google.android.finsky.utils.Maps;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class AppStates {
    private final PackageStateRepository mPackageManager;
    private final WriteThroughInstallerDataStore mStateStore;

    public static class AppState {
        public final InstallerData installerData;
        public final PackageState packageManagerState;
        public final String packageName;

        public AppState(String packageName, PackageState packageManagerState, InstallerData installerData) {
            this.packageName = packageName;
            this.packageManagerState = packageManagerState;
            this.installerData = installerData;
        }

        public String toString() {
            int i = -1;
            String str = "{package=%s installed=%d desired=%d}";
            Object[] objArr = new Object[3];
            objArr[0] = this.packageName;
            objArr[1] = Integer.valueOf(this.packageManagerState != null ? this.packageManagerState.installedVersion : -1);
            if (this.installerData != null) {
                i = this.installerData.getDesiredVersion();
            }
            objArr[2] = Integer.valueOf(i);
            return String.format(str, objArr);
        }
    }

    public AppStates(WriteThroughInstallerDataStore stateStore, PackageStateRepository packageManager) {
        this.mStateStore = stateStore;
        this.mPackageManager = packageManager;
    }

    public boolean isLoaded() {
        return this.mStateStore.isLoaded();
    }

    public void blockingLoad() {
        this.mStateStore.load();
    }

    public boolean load(Runnable callback) {
        return this.mStateStore.load(callback);
    }

    public InstallerDataStore getInstallerDataStore() {
        return this.mStateStore;
    }

    public PackageStateRepository getPackageStateRepository() {
        return this.mPackageManager;
    }

    public AppState getApp(String packageName) {
        InstallerData installerData = this.mStateStore.get(packageName);
        PackageState pkgState = this.mPackageManager.get(packageName);
        if (installerData == null && pkgState == null) {
            return null;
        }
        return new AppState(packageName, pkgState, installerData);
    }

    public List<AppState> getAppsToInstall() {
        List<AppState> result = Lists.newArrayList();
        for (InstallerData installerData : this.mStateStore.getAll()) {
            if (installerData.getDesiredVersion() != -1) {
                PackageState packageManagerState = this.mPackageManager.get(installerData.getPackageName());
                if (packageManagerState == null || installerData.getDesiredVersion() > packageManagerState.installedVersion) {
                    result.add(new AppState(installerData.getPackageName(), packageManagerState, installerData));
                }
            }
        }
        return result;
    }

    public static String[] getCertificateHashes(PackageState packageState) {
        return packageState == null ? LibraryAppEntry.ANY_CERTIFICATE_HASHES : packageState.certificateHashes;
    }

    private Collection<AppState> getAllBlocking(boolean onlyInstalled) {
        List<AppState> result = Lists.newArrayList();
        Map<String, InstallerData> installerDataMap = Maps.newHashMap();
        for (InstallerData installerData : this.mStateStore.getAll()) {
            installerDataMap.put(installerData.getPackageName(), installerData);
        }
        for (PackageState packageState : this.mPackageManager.getAllBlocking()) {
            result.add(new AppState(packageState.packageName, packageState, (InstallerData) installerDataMap.remove(packageState.packageName)));
        }
        if (!onlyInstalled) {
            for (InstallerData installerData2 : installerDataMap.values()) {
                result.add(new AppState(installerData2.getPackageName(), null, installerData2));
            }
        }
        return result;
    }

    public Map<String, List<String>> getOwnedByAccountBlocking(Libraries libraries, boolean installed) {
        Map<String, List<String>> result = Maps.newHashMap();
        for (AccountLibrary library : libraries.getAccountLibraries()) {
            result.put(library.getAccount().name, Lists.newArrayList());
        }
        for (AppState appState : getAllBlocking(installed)) {
            for (LibraryAppEntry appEntry : libraries.getAppEntries(appState.packageName, getCertificateHashes(appState.packageManagerState))) {
                ((List) result.get(appEntry.getAccountName())).add(appState.packageName);
            }
        }
        return result;
    }
}
