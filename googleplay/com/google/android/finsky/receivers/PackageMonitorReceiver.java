package com.google.android.finsky.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.text.TextUtils;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.analytics.BackgroundEventBuilder;
import com.google.android.finsky.analytics.PlayStore.AppData;
import com.google.android.finsky.appstate.AppStates;
import com.google.android.finsky.appstate.AppStates.AppState;
import com.google.android.finsky.appstate.InstallerDataStore;
import com.google.android.finsky.appstate.InstallerDataStore.InstallerData;
import com.google.android.finsky.appstate.PackageStateRepository;
import com.google.android.finsky.config.G;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.ParameterizedRunnable;
import java.util.ArrayList;
import java.util.List;

public class PackageMonitorReceiver {
    private static final boolean ICE_CREAM_SANDWICH_OR_GREATER;
    private final List<PackageStatusListener> mListeners;

    public interface PackageStatusListener {
        void onPackageAdded(String str);

        void onPackageAvailabilityChanged(String[] strArr, boolean z);

        void onPackageChanged(String str);

        void onPackageFirstLaunch(String str);

        void onPackageRemoved(String str, boolean z);
    }

    private static class ReferrerRebroadcaster implements PackageStatusListener {
        private ReferrerRebroadcaster() {
        }

        public void onPackageAdded(String packageName) {
            broadcastInstallReferrer(packageName, true);
        }

        public void onPackageRemoved(String packageName, boolean replacing) {
        }

        public void onPackageChanged(String packageName) {
        }

        public void onPackageAvailabilityChanged(String[] packageNames, boolean available) {
        }

        public void onPackageFirstLaunch(String packageName) {
            broadcastInstallReferrer(packageName, false);
        }

        private void broadcastInstallReferrer(final String packageName, final boolean packageAdded) {
            Runnable continueRunnable = new Runnable() {
                private int mLoaded;

                public void run() {
                    this.mLoaded++;
                    if (this.mLoaded == 2) {
                        ReferrerRebroadcaster.this.doBroadcastInstallReferrer(packageName, packageAdded);
                    }
                }
            };
            FinskyApp.get().getLibraries().load(continueRunnable);
            FinskyApp.get().getAppStates().load(continueRunnable);
        }

        private void doBroadcastInstallReferrer(String packageName, boolean packageAdded) {
            AppStates appStates = FinskyApp.get().getAppStates();
            AppState appState = appStates.getApp(packageName);
            if (appState != null && appState.installerData != null) {
                boolean forceLaunch;
                boolean sendIntent;
                String externalReferrer;
                long externalReferrerTimestampMs;
                InstallerDataStore installerDataStore;
                Context context;
                Intent intent;
                List<ResolveInfo> resolvedReceivers;
                boolean sent;
                Intent broadcastIntent;
                InstallerData installerData = appState.installerData;
                if (installerData.getDeliveryData() != null) {
                    if (installerData.getDeliveryData().immediateStartNeeded) {
                        forceLaunch = true;
                        sendIntent = forceLaunch || (VERSION.SDK_INT < 12) != packageAdded;
                        if (sendIntent) {
                            if (forceLaunch) {
                                externalReferrer = installerData.getExternalReferrer();
                                externalReferrerTimestampMs = installerData.getExternalReferrerTimestampMs();
                                if (externalReferrerTimestampMs > 0 && ((Long) G.externalReferrerLifespanMs.get()).longValue() + externalReferrerTimestampMs < System.currentTimeMillis()) {
                                    externalReferrer = null;
                                    FinskyLog.d("Dropped referrer for %s because expired", packageName);
                                    logExternalReferrer(516, packageName, appState.packageManagerState.installedVersion, "expired");
                                }
                            } else {
                                externalReferrer = "forced-launch";
                            }
                            if (!TextUtils.isEmpty(externalReferrer)) {
                                if (FinskyApp.get().getLibraries().getAppOwners(packageName).size() <= 0) {
                                    externalReferrer = null;
                                    FinskyLog.d("Dropped referrer for %s because not-owned", packageName);
                                    logExternalReferrer(516, packageName, appState.packageManagerState.installedVersion, "not-owned");
                                }
                            }
                            installerDataStore = appStates.getInstallerDataStore();
                            installerDataStore.setExternalReferrer(packageName, null);
                            installerDataStore.setExternalReferrerTimestampMs(packageName, 0);
                            if (!TextUtils.isEmpty(externalReferrer)) {
                                context = FinskyApp.get();
                                intent = new Intent("com.android.vending.INSTALL_REFERRER");
                                if (forceLaunch && VERSION.SDK_INT >= 13) {
                                    intent.addFlags(32);
                                    FinskyLog.d("Forcing %s to wake up", packageName);
                                }
                                intent.putExtra("referrer", externalReferrer);
                                intent.setPackage(packageName);
                                resolvedReceivers = context.getPackageManager().queryBroadcastReceivers(intent, 0);
                                if (resolvedReceivers.size() > 0) {
                                    sent = false;
                                    if (PackageMonitorReceiver.ICE_CREAM_SANDWICH_OR_GREATER) {
                                        for (ResolveInfo resolvedReceiver : resolvedReceivers) {
                                            if (packageName.equals(resolvedReceiver.activityInfo.packageName)) {
                                                broadcastIntent = new Intent(intent);
                                                broadcastIntent.setClassName(packageName, resolvedReceiver.activityInfo.name);
                                                context.sendBroadcast(broadcastIntent);
                                                sent = true;
                                            }
                                        }
                                    } else {
                                        context.sendBroadcast(intent);
                                        sent = true;
                                    }
                                    if (sent) {
                                        FinskyLog.d("Couldn't find referrer receiver for %s", packageName);
                                        return;
                                    }
                                    FinskyLog.d("Delivered referrer for %s", packageName);
                                    logExternalReferrer(517, packageName, appState.packageManagerState.installedVersion, null);
                                }
                            }
                        }
                    }
                }
                forceLaunch = false;
                if (VERSION.SDK_INT < 12) {
                }
                if (!forceLaunch) {
                }
                if (sendIntent) {
                    if (forceLaunch) {
                        externalReferrer = installerData.getExternalReferrer();
                        externalReferrerTimestampMs = installerData.getExternalReferrerTimestampMs();
                        externalReferrer = null;
                        FinskyLog.d("Dropped referrer for %s because expired", packageName);
                        logExternalReferrer(516, packageName, appState.packageManagerState.installedVersion, "expired");
                    } else {
                        externalReferrer = "forced-launch";
                    }
                    if (TextUtils.isEmpty(externalReferrer)) {
                        if (FinskyApp.get().getLibraries().getAppOwners(packageName).size() <= 0) {
                            externalReferrer = null;
                            FinskyLog.d("Dropped referrer for %s because not-owned", packageName);
                            logExternalReferrer(516, packageName, appState.packageManagerState.installedVersion, "not-owned");
                        }
                    }
                    installerDataStore = appStates.getInstallerDataStore();
                    installerDataStore.setExternalReferrer(packageName, null);
                    installerDataStore.setExternalReferrerTimestampMs(packageName, 0);
                    if (!TextUtils.isEmpty(externalReferrer)) {
                        context = FinskyApp.get();
                        intent = new Intent("com.android.vending.INSTALL_REFERRER");
                        intent.addFlags(32);
                        FinskyLog.d("Forcing %s to wake up", packageName);
                        intent.putExtra("referrer", externalReferrer);
                        intent.setPackage(packageName);
                        resolvedReceivers = context.getPackageManager().queryBroadcastReceivers(intent, 0);
                        if (resolvedReceivers.size() > 0) {
                            sent = false;
                            if (PackageMonitorReceiver.ICE_CREAM_SANDWICH_OR_GREATER) {
                                for (ResolveInfo resolvedReceiver2 : resolvedReceivers) {
                                    if (packageName.equals(resolvedReceiver2.activityInfo.packageName)) {
                                        broadcastIntent = new Intent(intent);
                                        broadcastIntent.setClassName(packageName, resolvedReceiver2.activityInfo.name);
                                        context.sendBroadcast(broadcastIntent);
                                        sent = true;
                                    }
                                }
                            } else {
                                context.sendBroadcast(intent);
                                sent = true;
                            }
                            if (sent) {
                                FinskyLog.d("Couldn't find referrer receiver for %s", packageName);
                                return;
                            }
                            FinskyLog.d("Delivered referrer for %s", packageName);
                            logExternalReferrer(517, packageName, appState.packageManagerState.installedVersion, null);
                        }
                    }
                }
            }
        }

        private void logExternalReferrer(int backgroundEventType, String packageName, int installedVersion, String reason) {
            BackgroundEventBuilder builder = new BackgroundEventBuilder(backgroundEventType).setDocument(packageName).setReason(reason);
            if (installedVersion >= 0) {
                AppData appData = new AppData();
                appData.version = installedVersion;
                appData.hasVersion = true;
                builder.setAppData(appData);
            }
            FinskyApp.get().getEventLogger().logBackgroundEvent(builder.build());
        }
    }

    public static class RegisteredReceiver extends BroadcastReceiver {
        public void onReceive(Context context, Intent intent) {
            FinskyApp.get().getPackageMonitorReceiver().onReceive(context, intent);
        }
    }

    static {
        ICE_CREAM_SANDWICH_OR_GREATER = VERSION.SDK_INT >= 14;
    }

    public PackageMonitorReceiver() {
        this.mListeners = new ArrayList();
        this.mListeners.add(new ReferrerRebroadcaster());
    }

    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        PackageStateRepository repository = FinskyApp.get().getPackageInfoRepository();
        final boolean available = "android.intent.action.EXTERNAL_APPLICATIONS_AVAILABLE".equals(action);
        boolean unavailable = "android.intent.action.EXTERNAL_APPLICATIONS_UNAVAILABLE".equals(action);
        final String packageName;
        if (available || unavailable) {
            final String[] changedPackages = intent.getStringArrayExtra("android.intent.extra.changed_package_list");
            for (String packageName2 : changedPackages) {
                repository.invalidate(packageName2);
            }
            notifyListeners(new ParameterizedRunnable<PackageStatusListener>() {
                public void run(PackageStatusListener packageStatusListener) {
                    packageStatusListener.onPackageAvailabilityChanged(changedPackages, available);
                }
            });
            return;
        }
        packageName2 = getPackageName(intent);
        if (packageName2 != null) {
            repository.invalidate(packageName2);
            if ("android.intent.action.PACKAGE_REMOVED".equals(action)) {
                Bundle extras = intent.getExtras();
                final boolean replacing = extras != null && extras.getBoolean("android.intent.extra.REPLACING", false);
                notifyListeners(new ParameterizedRunnable<PackageStatusListener>() {
                    public void run(PackageStatusListener packageStatusListener) {
                        packageStatusListener.onPackageRemoved(packageName2, replacing);
                    }
                });
            } else if ("android.intent.action.PACKAGE_ADDED".equals(action)) {
                notifyListeners(new ParameterizedRunnable<PackageStatusListener>() {
                    public void run(PackageStatusListener packageStatusListener) {
                        packageStatusListener.onPackageAdded(packageName2);
                    }
                });
                ExpireLaunchUrlReceiver.schedule(context, packageName2);
            } else if ("android.intent.action.PACKAGE_CHANGED".equals(action)) {
                notifyListeners(new ParameterizedRunnable<PackageStatusListener>() {
                    public void run(PackageStatusListener packageStatusListener) {
                        packageStatusListener.onPackageChanged(packageName2);
                    }
                });
            } else if ("android.intent.action.PACKAGE_FIRST_LAUNCH".equals(action)) {
                notifyListeners(new ParameterizedRunnable<PackageStatusListener>() {
                    public void run(PackageStatusListener packageStatusListener) {
                        packageStatusListener.onPackageFirstLaunch(packageName2);
                    }
                });
            } else {
                FinskyLog.w("Unhandled intent type action type: %s", action);
            }
        }
    }

    private void notifyListeners(ParameterizedRunnable<PackageStatusListener> listenerCaller) {
        for (int i = this.mListeners.size() - 1; i >= 0; i--) {
            listenerCaller.run(this.mListeners.get(i));
        }
    }

    public void attach(PackageStatusListener listener) {
        this.mListeners.add(listener);
    }

    public void detach(PackageStatusListener listener) {
        this.mListeners.remove(listener);
    }

    private static String getPackageName(Intent intent) {
        Uri data = intent.getData();
        return data != null ? data.getSchemeSpecificPart() : null;
    }
}
