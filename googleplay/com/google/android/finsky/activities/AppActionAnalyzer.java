package com.google.android.finsky.activities;

import android.accounts.Account;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.text.TextUtils;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.appstate.AppStates;
import com.google.android.finsky.appstate.AppStates.AppState;
import com.google.android.finsky.appstate.InstallerDataStore.InstallerData;
import com.google.android.finsky.appstate.PackageStateRepository.PackageState;
import com.google.android.finsky.library.Libraries;
import com.google.android.finsky.library.LibraryAppEntry;
import com.google.android.finsky.protos.DocDetails.CertificateSet;
import com.google.android.finsky.receivers.Installer.InstallerState;
import com.google.android.finsky.utils.DocUtils;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.Sha1Util;
import java.util.List;

public class AppActionAnalyzer {
    public String[] certificateHashes;
    public int installedVersion;
    public boolean isActiveDeviceAdmin;
    public boolean isContinueLaunch;
    public boolean isDisabled;
    public boolean isDisabledByUser;
    public boolean isInstalled;
    public boolean isInstalledOwnedPackage;
    public boolean isInstalledSystemApp;
    public boolean isLaunchable;
    public boolean isRefundable;
    public boolean isUninstallable;
    public boolean isUpdatedSystemApp;
    public String refundAccount;

    public AppActionAnalyzer(String packageName, AppStates appStates, Libraries libraries) {
        boolean z;
        InstallerData installerData = null;
        boolean z2 = true;
        this.isInstalled = false;
        this.isInstalledOwnedPackage = false;
        this.isInstalledSystemApp = false;
        this.isUpdatedSystemApp = false;
        this.isUninstallable = false;
        this.installedVersion = -1;
        this.isActiveDeviceAdmin = false;
        this.certificateHashes = LibraryAppEntry.ANY_CERTIFICATE_HASHES;
        this.isRefundable = false;
        this.refundAccount = null;
        this.isLaunchable = false;
        this.isContinueLaunch = false;
        this.isDisabled = false;
        this.isDisabledByUser = false;
        AppState appState = appStates.getApp(packageName);
        if (!(appState == null || appState.packageManagerState == null)) {
            this.isInstalled = true;
            PackageState packageManagerState = appState.packageManagerState;
            this.installedVersion = packageManagerState.installedVersion;
            this.isInstalledSystemApp = packageManagerState.isSystemApp;
            this.isUpdatedSystemApp = packageManagerState.isUpdatedSystemApp;
            this.isActiveDeviceAdmin = packageManagerState.isActiveDeviceAdmin;
            if (this.isActiveDeviceAdmin || (this.isInstalledSystemApp && !this.isUpdatedSystemApp)) {
                z = false;
            } else {
                z = true;
            }
            this.isUninstallable = z;
            boolean canLaunch = appStates.getPackageStateRepository().canLaunch(packageName);
            this.isDisabled = packageManagerState.isDisabled;
            this.isDisabledByUser = packageManagerState.isDisabledByUser;
            if (!canLaunch || this.isDisabled) {
                z = false;
            } else {
                z = true;
            }
            this.isLaunchable = z;
        }
        if (this.isInstalled) {
            this.certificateHashes = appState.packageManagerState.certificateHashes;
        }
        List<LibraryAppEntry> libraryEntries = libraries.getAppEntries(packageName, this.certificateHashes);
        boolean ownedByAnyAccount;
        if (libraryEntries.isEmpty()) {
            ownedByAnyAccount = false;
        } else {
            ownedByAnyAccount = true;
        }
        if (this.isInstalled && ownedByAnyAccount) {
            z = true;
        } else {
            z = false;
        }
        this.isInstalledOwnedPackage = z;
        if (appState != null) {
            installerData = appState.installerData;
        }
        this.refundAccount = getRefundAccount(installerData, libraryEntries);
        if (this.refundAccount != null) {
            z = true;
        } else {
            z = false;
        }
        this.isRefundable = z;
        if (!(!this.isInstalled || ownedByAnyAccount || libraries.getAppOwners(packageName).isEmpty())) {
            FinskyLog.d("%s is installed but certificate mistmatch", packageName);
        }
        if (appState != null && appState.installerData != null) {
            if (TextUtils.isEmpty(appState.installerData.getContinueUrl())) {
                z2 = false;
            }
            this.isContinueLaunch = z2;
        }
    }

    public static Account getInstallAccount(String packageName, Account preferredAccount, AppStates appStates, Libraries libraries) {
        List<Account> owners = libraries.getAppOwners(packageName);
        if (owners.contains(preferredAccount)) {
            return preferredAccount;
        }
        InstallerData installerData = appStates.getInstallerDataStore().get(packageName);
        if (installerData != null) {
            for (Account owner : owners) {
                if (owner.name.equals(installerData.getAccountName())) {
                    return owner;
                }
            }
        }
        if (owners.isEmpty()) {
            return preferredAccount;
        }
        return (Account) owners.get(0);
    }

    public static String getAppDetailsAccount(String packageName, String currentAccountName, AppStates appStates, Libraries libraries) {
        boolean isInstalledAndOwnedPackage;
        InstallerData installerData = appStates.getInstallerDataStore().get(packageName);
        PackageState packageState = appStates.getPackageStateRepository().get(packageName);
        List<LibraryAppEntry> libraryEntries = libraries.getAppEntries(packageName, AppStates.getCertificateHashes(packageState));
        if (packageState == null || libraryEntries.isEmpty()) {
            isInstalledAndOwnedPackage = false;
        } else {
            isInstalledAndOwnedPackage = true;
        }
        if (isInstalledAndOwnedPackage && installerData != null) {
            String accountForUpdate = installerData.getAccountForUpdate();
            if (findAccountInOwners(accountForUpdate, libraryEntries)) {
                return accountForUpdate;
            }
        }
        if (isInstalledAndOwnedPackage && installerData != null) {
            String installerAccount = installerData.getAccountName();
            if (findAccountInOwners(installerAccount, libraryEntries)) {
                return installerAccount;
            }
        }
        if (findAccountInOwners(currentAccountName, libraryEntries) || libraryEntries.size() <= 0) {
            return currentAccountName;
        }
        return ((LibraryAppEntry) libraryEntries.get(0)).getAccountName();
    }

    private static boolean findAccountInOwners(String accountName, List<LibraryAppEntry> libraryEntries) {
        if (!TextUtils.isEmpty(accountName)) {
            for (int i = 0; i < libraryEntries.size(); i++) {
                if (((LibraryAppEntry) libraryEntries.get(i)).getAccountName().equals(accountName)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static String getRefundAccount(InstallerData installerData, List<LibraryAppEntry> libraryEntries) {
        return internalGetRefundAccount(installerData, libraryEntries, System.currentTimeMillis());
    }

    static String internalGetRefundAccount(InstallerData installerData, List<LibraryAppEntry> libraryEntries, long nowMs) {
        long firstDownloadMs = 0;
        if (installerData != null) {
            firstDownloadMs = installerData.getFirstDownloadMs();
        }
        int libraryEntryCount = libraryEntries.size();
        for (int i = 0; i < libraryEntryCount; i++) {
            LibraryAppEntry appEntry = (LibraryAppEntry) libraryEntries.get(i);
            long refundEndtimeMs = appEntry.refundPreDeliveryEndtimeMs;
            if (firstDownloadMs != 0) {
                refundEndtimeMs = Math.min(refundEndtimeMs, appEntry.refundPostDeliveryWindowMs + firstDownloadMs);
            }
            if (refundEndtimeMs >= nowMs) {
                return appEntry.getAccountName();
            }
        }
        return null;
    }

    public static boolean isMultiUserCertificateConflict(Document document) {
        if (!FinskyApp.get().getUsers().supportsMultiUser() || FinskyApp.get().getPackageInfoRepository().get(document.getDocId()) != null) {
            return false;
        }
        try {
            PackageInfo info = FinskyApp.get().getPackageManager().getPackageInfo(document.getDocId(), 8256);
            int numSignatures = info.signatures.length;
            String[] hashes = new String[numSignatures];
            for (int i = 0; i < numSignatures; i++) {
                hashes[i] = Sha1Util.secureHash(info.signatures[i].toByteArray());
            }
            if (findCertificateMatch(hashes, document.getAppDetails().certificateSet)) {
                return false;
            }
            return true;
        } catch (NameNotFoundException e) {
            return false;
        }
    }

    public static boolean canRemoveFromLibrary(Document doc) {
        if (doc.getDocumentType() != 1) {
            FinskyLog.wtf("Method invalid for non-ANDROID_APP docs.", new Object[0]);
            return false;
        }
        Libraries libraries = FinskyApp.get().getLibraries();
        AppActionAnalyzer analyzer = new AppActionAnalyzer(doc.getBackendDocId(), FinskyApp.get().getAppStates(), libraries);
        if (analyzer.isInstalled || analyzer.isRefundable || DocUtils.hasAutoRenewingSubscriptions(libraries, doc.getAppDetails().packageName) || FinskyApp.get().getInstaller().getState(doc.getAppDetails().packageName) != InstallerState.NOT_TRACKED) {
            return false;
        }
        return true;
    }

    public boolean hasUpdateAvailable(Document document) {
        return (this.isInstalledOwnedPackage || this.isInstalledSystemApp) && document.getAppDetails().hasVersionCode && document.getAppDetails().versionCode > this.installedVersion;
    }

    public boolean hasConversionUpdateAvailable(Document document) {
        boolean z = true;
        if (!this.isInstalled || this.isInstalledSystemApp || this.isInstalledOwnedPackage) {
            return false;
        }
        if (!findCertificateMatch(this.certificateHashes, document.getAppDetails().certificateSet) || !document.getAppDetails().hasVersionCode || document.getAppDetails().versionCode <= this.installedVersion) {
            return false;
        }
        if (document.needsCheckoutFlow(1)) {
            z = false;
        }
        return z;
    }

    protected static boolean findCertificateMatch(String[] installedHashes, CertificateSet[] documentCertificateSets) {
        if (documentCertificateSets.length == 0 || installedHashes.length == 0) {
            return false;
        }
        for (CertificateSet documentCertificateSet : documentCertificateSets) {
            if (documentCertificateSet.certificateHash.length == installedHashes.length) {
                boolean found = true;
                for (int i = 0; i < documentCertificateSet.certificateHash.length; i++) {
                    if (!documentCertificateSet.certificateHash[i].equals(installedHashes[i])) {
                        found = false;
                        break;
                    }
                }
                if (found) {
                    return true;
                }
            }
        }
        return false;
    }
}
