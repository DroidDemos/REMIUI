package com.google.android.finsky.appstate;

import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import com.google.android.finsky.appstate.PackageStateRepository.PackageState;
import com.google.android.finsky.receivers.PackageMonitorReceiver;
import com.google.android.finsky.receivers.PackageMonitorReceiver.PackageStatusListener;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.Lists;
import com.google.android.finsky.utils.Maps;
import com.google.android.finsky.utils.Sha1Util;
import com.google.android.finsky.utils.Utils;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class PackageManagerRepository implements PackageStateRepository, PackageStatusListener {
    private static final PackageState NOT_INSTALLED_MARKER;
    private final DevicePolicyManager mDevicePolicyManager;
    private final PackageManager mPackageManager;
    private final Map<String, PackageState> mPackageStates;

    static {
        NOT_INSTALLED_MARKER = new PackageState(null, null, false, false, false, false, -1, false);
    }

    private static String[] computeCertificateHashes(PackageInfo packageInfo) {
        int numCerts = packageInfo.signatures.length;
        String[] certHashes = new String[numCerts];
        for (int i = 0; i < numCerts; i++) {
            certHashes[i] = Sha1Util.secureHash(packageInfo.signatures[i].toByteArray());
        }
        return certHashes;
    }

    public PackageManagerRepository(PackageManager packageManager, PackageMonitorReceiver packageMonitorReceiver, DevicePolicyManager deviceManager) {
        this.mPackageStates = Maps.newHashMap();
        this.mPackageManager = packageManager;
        this.mDevicePolicyManager = deviceManager;
        if (packageMonitorReceiver != null) {
            packageMonitorReceiver.attach(this);
        }
    }

    public synchronized PackageState get(String packageName) {
        PackageState packageState;
        packageState = (PackageState) this.mPackageStates.get(packageName);
        if (packageState == null) {
            packageState = refreshEntry(packageName, false);
        }
        if (packageState == NOT_INSTALLED_MARKER) {
            packageState = null;
        }
        return packageState;
    }

    public Collection<PackageState> getAllBlocking() {
        Utils.ensureNotOnMainThread();
        List<PackageInfo> installedPackages = this.mPackageManager.getInstalledPackages(64);
        List<PackageState> states = Lists.newArrayList(installedPackages.size());
        for (PackageInfo installedPackage : installedPackages) {
            PackageState packageState = createPackageState(installedPackage);
            if (packageState != NOT_INSTALLED_MARKER) {
                states.add(packageState);
            }
        }
        return states;
    }

    public synchronized void invalidate(String packageName) {
        this.mPackageStates.remove(packageName);
    }

    public boolean canLaunch(String packageName) {
        return this.mPackageManager.getLaunchIntentForPackage(packageName) != null;
    }

    public String getVersionName(String packageName) {
        try {
            return this.mPackageManager.getPackageInfo(packageName, 0).versionName;
        } catch (NameNotFoundException e) {
            return null;
        }
    }

    private PackageState createPackageState(PackageInfo packageInfo) {
        try {
            boolean isSystemApp;
            boolean isUpdatedSystemApp;
            boolean isDisabledByUser;
            boolean isDisabled;
            int installedVersion = packageInfo.versionCode;
            if ((packageInfo.applicationInfo.flags & 1) != 0) {
                isSystemApp = true;
            } else {
                isSystemApp = false;
            }
            if ((packageInfo.applicationInfo.flags & 128) != 0) {
                isUpdatedSystemApp = true;
            } else {
                isUpdatedSystemApp = false;
            }
            int state = this.mPackageManager.getApplicationEnabledSetting(packageInfo.packageName);
            if (state == 0) {
                isDisabledByUser = false;
                if (packageInfo.applicationInfo.enabled) {
                    isDisabled = false;
                } else {
                    isDisabled = true;
                }
            } else {
                if (state == 3 || state == 4) {
                    isDisabledByUser = true;
                } else {
                    isDisabledByUser = false;
                }
                if (isDisabledByUser || state == 2) {
                    isDisabled = true;
                } else {
                    isDisabled = false;
                }
            }
            return new PackageState(packageInfo.packageName, computeCertificateHashes(packageInfo), isSystemApp, isUpdatedSystemApp, isDisabled, isDisabledByUser, installedVersion, isActiveDeviceAdmin(packageInfo.packageName));
        } catch (IllegalArgumentException e) {
            FinskyLog.w("Package %s not installed", packageInfo.packageName);
            return NOT_INSTALLED_MARKER;
        }
    }

    private boolean isActiveDeviceAdmin(String packageName) {
        List<ComponentName> admins = this.mDevicePolicyManager.getActiveAdmins();
        if (admins == null) {
            return false;
        }
        for (ComponentName componentName : admins) {
            if (componentName.getPackageName().equals(packageName)) {
                return true;
            }
        }
        return false;
    }

    private PackageState refreshEntry(String packageName, boolean removal) {
        if (removal) {
            this.mPackageStates.put(packageName, NOT_INSTALLED_MARKER);
            return NOT_INSTALLED_MARKER;
        }
        try {
            PackageState packageState = createPackageState(this.mPackageManager.getPackageInfo(packageName, 64));
            this.mPackageStates.put(packageName, packageState);
            return packageState;
        } catch (NameNotFoundException e) {
            this.mPackageStates.put(packageName, NOT_INSTALLED_MARKER);
            return NOT_INSTALLED_MARKER;
        }
    }

    public void onPackageAdded(String packageName) {
        refreshEntry(packageName, false);
    }

    public void onPackageRemoved(String packageName, boolean replacing) {
        refreshEntry(packageName, !replacing);
    }

    public void onPackageChanged(String packageName) {
        refreshEntry(packageName, false);
    }

    public void onPackageAvailabilityChanged(String[] packageNames, boolean available) {
        for (String packageName : packageNames) {
            refreshEntry(packageName, false);
        }
    }

    public void onPackageFirstLaunch(String packageName) {
    }
}
