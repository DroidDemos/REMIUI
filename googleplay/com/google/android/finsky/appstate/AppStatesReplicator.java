package com.google.android.finsky.appstate;

import android.accounts.Account;
import android.os.Build;
import android.os.Handler;
import com.android.volley.Response.ErrorListener;
import com.android.volley.VolleyError;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.api.AccountHandler;
import com.google.android.finsky.appstate.PackageStateRepository.PackageState;
import com.google.android.finsky.config.PreferenceFile.SharedPreference;
import com.google.android.finsky.library.Accounts;
import com.google.android.finsky.library.Libraries;
import com.google.android.finsky.local.AssetUtils;
import com.google.android.finsky.protos.VendingProtos.ContentSyncRequestProto;
import com.google.android.finsky.protos.VendingProtos.ContentSyncRequestProto.AssetInstallState;
import com.google.android.finsky.protos.VendingProtos.ContentSyncRequestProto.SystemApp;
import com.google.android.finsky.protos.VendingProtos.ContentSyncResponseProto;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.FinskyPreferences;
import com.google.android.finsky.utils.Lists;
import com.google.android.finsky.utils.Maps;
import com.google.android.gms.ads.identifier.AdIdProvider;
import com.google.android.vending.remoting.api.VendingApiFactory;
import com.google.protobuf.nano.MessageNano;
import com.google.protobuf.nano.MessageNanoPrinter;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class AppStatesReplicator {
    private final Accounts mAccounts;
    private final AdIdProvider mAdIdProvider;
    private final AppStates mAppStates;
    private final Handler mBackgroundHandler;
    private final Libraries mLibraries;
    private final Handler mNotificationHandler;
    private final List<Listener> mReplicationListeners;
    private final VendingApiFactory mVendingApiFactory;

    public interface Listener {
        void onFinished(boolean z);
    }

    public AppStatesReplicator(Accounts accounts, Libraries libraries, AppStates appStates, VendingApiFactory vendingApiFactory, Handler notificationHandler, Handler backgroundHandler, AdIdProvider adIdProvider) {
        this.mReplicationListeners = Lists.newArrayList();
        this.mAccounts = accounts;
        this.mLibraries = libraries;
        this.mAppStates = appStates;
        this.mVendingApiFactory = vendingApiFactory;
        this.mNotificationHandler = notificationHandler;
        this.mBackgroundHandler = backgroundHandler;
        this.mAdIdProvider = adIdProvider;
    }

    public void load(final Runnable callback) {
        Runnable countdown = new Runnable() {
            private int mCount;

            public void run() {
                this.mCount++;
                if (this.mCount == 2 && callback != null) {
                    AppStatesReplicator.this.mNotificationHandler.post(callback);
                }
            }
        };
        this.mAppStates.load(countdown);
        this.mLibraries.load(countdown);
    }

    public synchronized void replicate(Listener listener) {
        this.mReplicationListeners.add(listener);
        if (this.mReplicationListeners.size() <= 1) {
            this.mBackgroundHandler.post(new Runnable() {
                public void run() {
                    AppStatesReplicator.this.internalReplicate();
                }
            });
        }
    }

    private void internalReplicate() {
        List<Account> accounts = this.mAccounts.getAccounts();
        int configurationHash = computeConfigurationHash(accounts, Build.FINGERPRINT);
        Collection<PackageState> all = this.mAppStates.getPackageStateRepository().getAllBlocking();
        Map<Account, List<PackageState>> statesByAccount = Maps.newHashMap();
        List<PackageState> systemApps = Lists.newArrayList();
        int sideloadedCount = bucketAppsByOwner(all, statesByAccount, systemApps);
        int currentSystemAppsHash = computeHash(systemApps, configurationHash);
        long nowMs = System.currentTimeMillis();
        final List<Account> accountsToReplicate = Lists.newArrayList();
        final int[] finishedReplications = new int[]{0};
        final int[] successfulReplications = new int[]{0};
        List<ContentSyncRequestProto> requests = Lists.newArrayList();
        List<com.android.volley.Response.Listener<ContentSyncResponseProto>> responseListeners = Lists.newArrayList();
        for (Account account : accounts) {
            List<PackageState> accountApps = (List) statesByAccount.get(account);
            int currentAccountAppsHash = computeHash(accountApps, configurationHash);
            final SharedPreference<Integer> accountAppsPref = FinskyPreferences.replicatedAccountAppsHash.get(account.name);
            final SharedPreference<Integer> systemAppsPref = FinskyPreferences.replicatedSystemAppsHash.get(account.name);
            ContentSyncRequestProto request = makeContentSyncRequest(currentSystemAppsHash, ((Integer) systemAppsPref.get()).intValue(), systemApps, currentAccountAppsHash, ((Integer) accountAppsPref.get()).intValue(), accountApps, sideloadedCount, nowMs);
            Object[] objArr;
            if (request != null) {
                if (FinskyLog.DEBUG) {
                    objArr = new Object[4];
                    objArr[0] = FinskyLog.scrubPii(account.name);
                    objArr[1] = Integer.valueOf(request.systemApp.length);
                    objArr[2] = Integer.valueOf(request.assetInstallState.length);
                    objArr[3] = Integer.valueOf(request.sideloadedAppCount);
                    FinskyLog.v("Replicating installation states: account=%s, numSystemApps=%d, numAccountApps=%d, numSideloaded=%d", objArr);
                }
                accountsToReplicate.add(account);
                requests.add(request);
                final int i = currentSystemAppsHash;
                final int i2 = currentAccountAppsHash;
                responseListeners.add(new com.android.volley.Response.Listener<ContentSyncResponseProto>() {
                    public void onResponse(ContentSyncResponseProto contentSyncResponseProto) {
                        systemAppsPref.put(Integer.valueOf(i));
                        accountAppsPref.put(Integer.valueOf(i2));
                        int[] iArr = finishedReplications;
                        iArr[0] = iArr[0] + 1;
                        iArr = successfulReplications;
                        iArr[0] = iArr[0] + 1;
                        AppStatesReplicator.this.handleContentSyncResponse(accountsToReplicate.size(), finishedReplications[0], successfulReplications[0]);
                    }
                });
            } else if (FinskyLog.DEBUG) {
                objArr = new Object[1];
                objArr[0] = FinskyLog.scrubPii(account.name);
                FinskyLog.v("No installation states replication required: account=%s", objArr);
            }
        }
        performRequests(accountsToReplicate, finishedReplications, successfulReplications, requests, responseListeners);
    }

    int computeConfigurationHash(List<Account> accounts, String systemFingerprint) {
        int hash = systemFingerprint.hashCode();
        for (Account account : accounts) {
            hash ^= account.hashCode();
        }
        return hash;
    }

    private void performRequests(List<Account> accounts, int[] finishedReplications, int[] successfulReplications, List<ContentSyncRequestProto> requests, List<com.android.volley.Response.Listener<ContentSyncResponseProto>> responseListeners) {
        if (accounts.size() == 0) {
            handleContentSyncResponse(0, 0, 0);
            return;
        }
        for (int i = 0; i < accounts.size(); i++) {
            Account account = (Account) accounts.get(i);
            if (FinskyLog.DEBUG) {
                Object[] objArr = new Object[1];
                objArr[0] = FinskyLog.scrubPii(account.name);
                FinskyLog.d("ContentSyncRequestProto for account %s:", objArr);
                int len$ = MessageNanoPrinter.print((MessageNano) requests.get(i)).split("\n").length;
                for (int i$ = 0; i$ < len$; i$++) {
                    FinskyLog.d(": %s", arr$[i$]);
                }
            }
            final int[] iArr = finishedReplications;
            final List<Account> list = accounts;
            final int[] iArr2 = successfulReplications;
            this.mVendingApiFactory.getApi(account.name).syncContent((ContentSyncRequestProto) requests.get(i), this.mAdIdProvider, getAccountOrdinal(account), (com.android.volley.Response.Listener) responseListeners.get(i), new ErrorListener() {
                public void onErrorResponse(VolleyError volleyError) {
                    int[] iArr = iArr;
                    iArr[0] = iArr[0] + 1;
                    AppStatesReplicator.this.handleContentSyncResponse(list.size(), iArr[0], iArr2[0]);
                }
            });
        }
    }

    private String getAccountOrdinal(Account account) {
        Account[] accounts = AccountHandler.getAccounts(FinskyApp.get());
        for (int i = 0; i < accounts.length; i++) {
            if (accounts[i].equals(account)) {
                return Integer.toString(i);
            }
        }
        return null;
    }

    private synchronized void handleContentSyncResponse(final int expectedCount, final int finished, int successful) {
        if (successful == expectedCount) {
            for (final Listener replicationListener : this.mReplicationListeners) {
                if (replicationListener != null) {
                    this.mNotificationHandler.post(new Runnable() {
                        public void run() {
                            replicationListener.onFinished(finished == expectedCount);
                        }
                    });
                }
            }
            this.mReplicationListeners.clear();
        }
    }

    ContentSyncRequestProto makeContentSyncRequest(int currentSystemAppsHash, int replicatedSystemAppsHash, List<PackageState> systemApps, int currentAccountAppsHash, int replicatedAccountAppsHash, List<PackageState> accountApps, int sideloadedAppCount, long nowMs) {
        boolean needsSystemAppsUpload = currentSystemAppsHash != replicatedSystemAppsHash;
        boolean needsAccountAppsUpload = needsSystemAppsUpload || currentAccountAppsHash != replicatedAccountAppsHash;
        if (!needsAccountAppsUpload && !needsSystemAppsUpload) {
            return null;
        }
        int i;
        ContentSyncRequestProto request = new ContentSyncRequestProto();
        if (accountApps != null) {
            int numAccountApps = accountApps.size();
            if (numAccountApps > 0) {
                request.assetInstallState = new AssetInstallState[numAccountApps];
                for (i = 0; i < numAccountApps; i++) {
                    request.assetInstallState[i] = makeInstallState((PackageState) accountApps.get(i), nowMs);
                }
            }
        }
        if (needsSystemAppsUpload) {
            int numSysApps = systemApps.size();
            if (numSysApps > 0) {
                request.systemApp = new SystemApp[systemApps.size()];
                for (i = 0; i < numSysApps; i++) {
                    request.systemApp[i] = makeSystemApp((PackageState) systemApps.get(i));
                }
            }
        }
        request.sideloadedAppCount = sideloadedAppCount;
        request.hasSideloadedAppCount = true;
        return request;
    }

    int bucketAppsByOwner(Collection<PackageState> packageStates, Map<Account, List<PackageState>> byAccount, List<PackageState> systemApps) {
        if (byAccount.size() == 0 && systemApps.size() == 0) {
            int unaccounted = 0;
            for (PackageState packageState : packageStates) {
                boolean isUserApp;
                if (!packageState.isSystemApp || packageState.isUpdatedSystemApp) {
                    isUserApp = true;
                } else {
                    isUserApp = false;
                }
                if (isUserApp) {
                    List<Account> owners = this.mLibraries.getAppOwners(packageState.packageName, packageState.certificateHashes);
                    if (owners.size() > 0) {
                        getAccountList(byAccount, (Account) owners.get(0)).add(packageState);
                    } else {
                        unaccounted++;
                    }
                }
                if (packageState.isSystemApp && !packageState.isDisabled) {
                    systemApps.add(packageState);
                }
            }
            for (Account account : this.mAccounts.getAccounts()) {
                if (!byAccount.containsKey(account)) {
                    byAccount.put(account, Collections.emptyList());
                }
            }
            return unaccounted;
        }
        throw new IllegalArgumentException("Buckets must be empty");
    }

    private static List<PackageState> getAccountList(Map<Account, List<PackageState>> accountLists, Account account) {
        List<PackageState> accountList = (List) accountLists.get(account);
        if (accountList != null) {
            return accountList;
        }
        accountList = Lists.newArrayList();
        accountLists.put(account, accountList);
        return accountList;
    }

    int computeHash(Collection<PackageState> packageStates, int configurationHash) {
        return packageStates.hashCode() ^ configurationHash;
    }

    private AssetInstallState makeInstallState(PackageState packageState, long nowMs) {
        AssetInstallState assetInstallState = new AssetInstallState();
        assetInstallState.assetId = AssetUtils.makeAssetId(packageState.packageName, packageState.installedVersion);
        assetInstallState.hasAssetId = true;
        assetInstallState.assetState = 2;
        assetInstallState.hasAssetState = true;
        assetInstallState.installTime = nowMs;
        assetInstallState.hasInstallTime = true;
        assetInstallState.packageName = packageState.packageName;
        assetInstallState.hasPackageName = true;
        assetInstallState.versionCode = packageState.installedVersion;
        assetInstallState.hasVersionCode = true;
        return assetInstallState;
    }

    private SystemApp makeSystemApp(PackageState packageState) {
        SystemApp systemApp = new SystemApp();
        systemApp.packageName = packageState.packageName;
        systemApp.hasPackageName = true;
        systemApp.versionCode = packageState.installedVersion;
        systemApp.hasVersionCode = true;
        List<String> certsToSort = Lists.newArrayList(packageState.certificateHashes);
        Collections.sort(certsToSort);
        systemApp.certificateHash = (String[]) certsToSort.toArray(new String[certsToSort.size()]);
        return systemApp;
    }
}
