package com.google.android.finsky.api.model;

import android.accounts.Account;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.analytics.PlayStore.AppData;
import com.google.android.finsky.api.AccountHandler;
import com.google.android.finsky.appstate.GmsCoreHelper;
import com.google.android.finsky.appstate.InstallerDataStore;
import com.google.android.finsky.appstate.InstallerDataStore.InstallerData;
import com.google.android.finsky.library.AccountLibrary;
import com.google.android.finsky.library.Libraries;
import com.google.android.finsky.protos.Common.Offer;
import com.google.android.finsky.protos.DocDetails.AppDetails;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.LibraryUtils;
import com.google.android.finsky.utils.Lists;
import com.google.android.finsky.utils.Maps;
import com.google.android.finsky.utils.Objects;
import com.google.android.finsky.utils.PurchaseInitiator;
import com.google.android.finsky.utils.Sets;
import com.google.android.finsky.utils.Utils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class MultiWayUpdateController extends MultiDfeBulkDetails {
    private static final String[] EMPTY_STRING_ARRAY;
    private static Set<String> sPackageBlacklist;
    private List<AppToAccountTagMatch> mAppToAccountTagMatchList;
    private List<Document> mCollatedDocuments;
    private InstallerDataStore mInstallerDataStore;
    private Libraries mLibraries;

    protected static class AccountAutoAcquireTags {
        final String accountName;
        final String[] tags;

        public AccountAutoAcquireTags(String accountName, String[] tags) {
            this.accountName = accountName;
            this.tags = tags;
        }
    }

    protected static class AccountVersionDocument {
        protected String account;
        protected Document document;
        protected boolean needAccountForUpdate;
        protected int versionCode;

        public AccountVersionDocument(String account, int versionCode, Document document) {
            this.account = account;
            this.versionCode = versionCode;
            this.document = document;
            this.needAccountForUpdate = false;
        }
    }

    private static class AppToAccountTagMatch {
        public final String accountName;
        public final String docId;

        public AppToAccountTagMatch(String docId, String accountName) {
            this.docId = docId;
            this.accountName = accountName;
        }
    }

    static {
        sPackageBlacklist = null;
        EMPTY_STRING_ARRAY = new String[0];
    }

    public MultiWayUpdateController(InstallerDataStore installerDataStore, Libraries libraries) {
        this.mAppToAccountTagMatchList = null;
        this.mInstallerDataStore = installerDataStore;
        this.mLibraries = libraries;
    }

    public static void selectAccountsForUpdateChecks(InstallerDataStore installerDataStore, String currentAccountName, Map<String, List<String>> accountPackageMap) {
        if (accountPackageMap.size() != 1) {
            String packageName;
            InstallerData installerData;
            boolean variesByAccount;
            List<String> currentAccountList = (List) accountPackageMap.get(currentAccountName);
            if (currentAccountList == null) {
                currentAccountList = (List) accountPackageMap.get((String) accountPackageMap.keySet().iterator().next());
            }
            Set<String> markedPackages = Sets.newHashSet();
            for (String packageName2 : currentAccountList) {
                if (!GmsCoreHelper.isGmsCore(packageName2)) {
                    installerData = installerDataStore.get(packageName2);
                    if (installerData == null || (installerData.getPersistentFlags() & 1) == 0) {
                        variesByAccount = false;
                    } else {
                        variesByAccount = true;
                    }
                    if (!variesByAccount) {
                        markedPackages.add(packageName2);
                    }
                }
            }
            Iterator<Entry<String, List<String>>> accountIterator = accountPackageMap.entrySet().iterator();
            while (accountIterator.hasNext()) {
                List<String> appList = (List) ((Entry) accountIterator.next()).getValue();
                if (appList != currentAccountList) {
                    Iterator<String> iterator = appList.iterator();
                    while (iterator.hasNext()) {
                        packageName2 = (String) iterator.next();
                        if (!GmsCoreHelper.isGmsCore(packageName2)) {
                            installerData = installerDataStore.get(packageName2);
                            if (installerData == null || (installerData.getPersistentFlags() & 1) == 0) {
                                variesByAccount = false;
                            } else {
                                variesByAccount = true;
                            }
                            if (!variesByAccount) {
                                if (markedPackages.contains(packageName2)) {
                                    iterator.remove();
                                } else {
                                    markedPackages.add(packageName2);
                                }
                            }
                        }
                    }
                    if (appList.isEmpty()) {
                        accountIterator.remove();
                    }
                }
            }
        }
    }

    public void addRequests(Map<String, List<String>> docIdsByAccount) {
        AccountAutoAcquireTags[] accountsWithTags = collectAccountAutoAcquireTags();
        if (accountsWithTags != null) {
            collectAutoAcquireApps(docIdsByAccount, accountsWithTags);
            addAutoAcquireAppsToRequests(docIdsByAccount);
        }
        for (Entry<String, List<String>> entry : docIdsByAccount.entrySet()) {
            String accountName = (String) entry.getKey();
            List<String> docIds = (List) entry.getValue();
            if (docIds.size() != 0) {
                addRequest(FinskyApp.get().getDfeApi(accountName), docIds);
            }
        }
    }

    protected AccountAutoAcquireTags[] collectAccountAutoAcquireTags() {
        List<AccountAutoAcquireTags> resultList = null;
        List<AccountLibrary> accountLibraries = this.mLibraries.getAccountLibraries();
        if (accountLibraries.size() > 1) {
            for (AccountLibrary library : accountLibraries) {
                String[] accountTags = library.getAutoAcquireTags();
                if (accountTags != null && accountTags.length > 0) {
                    if (resultList == null) {
                        resultList = new ArrayList();
                    }
                    resultList.add(new AccountAutoAcquireTags(library.getAccount().name, accountTags));
                }
            }
        }
        if (resultList == null) {
            return null;
        }
        return (AccountAutoAcquireTags[]) resultList.toArray(new AccountAutoAcquireTags[resultList.size()]);
    }

    protected void collectAutoAcquireApps(Map<String, List<String>> docIdsByAccount, AccountAutoAcquireTags[] accountAutoAcquireTags) {
        for (Entry<String, List<String>> entry : docIdsByAccount.entrySet()) {
            String accountName = (String) entry.getKey();
            for (String docId : (List) entry.getValue()) {
                if (sPackageBlacklist == null || !sPackageBlacklist.contains(docId)) {
                    InstallerData installerData = this.mInstallerDataStore.get(docId);
                    if (!(installerData == null || (installerData.getPersistentFlags() & 1) == 0)) {
                        String[] appAutoAcquireTags = installerData.getAutoAcquireTags();
                        if (appAutoAcquireTags.length != 0) {
                            String accountWithMatchingTag = findAccountWithMatchingTags(appAutoAcquireTags, accountAutoAcquireTags);
                            if (!(accountWithMatchingTag == null || accountWithMatchingTag.equals(accountName))) {
                                List<String> acquiringAccountPackages = (List) docIdsByAccount.get(accountWithMatchingTag);
                                if (!(acquiringAccountPackages == null || acquiringAccountPackages.contains(docId))) {
                                    addToAutoAcquireWorkList(docId, accountWithMatchingTag);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    protected String findAccountWithMatchingTags(String[] appAutoAcquireTags, AccountAutoAcquireTags[] accountsWithTags) {
        for (AccountAutoAcquireTags accountWithTags : accountsWithTags) {
            for (String accountTag : accountWithTags.tags) {
                for (String appTag : appAutoAcquireTags) {
                    if (appTag.equals(accountTag)) {
                        return accountWithTags.accountName;
                    }
                }
            }
        }
        return null;
    }

    protected void addToAutoAcquireWorkList(String docId, String accountName) {
        if (this.mAppToAccountTagMatchList == null) {
            this.mAppToAccountTagMatchList = Lists.newArrayList();
        }
        this.mAppToAccountTagMatchList.add(new AppToAccountTagMatch(docId, accountName));
    }

    protected void addAutoAcquireAppsToRequests(Map<String, List<String>> docIdsByAccount) {
        if (this.mAppToAccountTagMatchList != null) {
            for (AppToAccountTagMatch match : this.mAppToAccountTagMatchList) {
                FinskyLog.d("Add %s to check for potential auto-acquire by %s", match.docId, FinskyLog.scrubPii(match.accountName));
                ((List) docIdsByAccount.get(match.accountName)).add(match.docId);
            }
        }
    }

    private boolean isAutoAcquireAttempt(String docId, String accountName) {
        if (this.mAppToAccountTagMatchList == null) {
            return false;
        }
        for (int i = 0; i < this.mAppToAccountTagMatchList.size(); i++) {
            AppToAccountTagMatch entry = (AppToAccountTagMatch) this.mAppToAccountTagMatchList.get(i);
            if (entry.docId.equals(docId) && entry.accountName.equals(accountName)) {
                return true;
            }
        }
        return false;
    }

    protected void collateResponses() {
        if (this.mCollatedDocuments != null) {
            FinskyLog.w("Unexpected repeat collation", new Object[0]);
        }
        Map<String, AccountVersionDocument> packageMap = generatePackageMap(false);
        if (packageMap != null) {
            String packageName;
            this.mCollatedDocuments = Lists.newArrayList(packageMap.size());
            for (Entry<String, AccountVersionDocument> entry : packageMap.entrySet()) {
                packageName = (String) entry.getKey();
                AccountVersionDocument packageInfo = (AccountVersionDocument) entry.getValue();
                Document document = packageInfo.document;
                captureVariesByAccount(document);
                captureAutoAcquireTags(document);
                if (document.getAppDetails().variesByAccount && packageInfo.needAccountForUpdate) {
                    captureUpdateAccount(packageName, packageInfo.account);
                } else {
                    captureUpdateAccount(packageName, null);
                }
                this.mCollatedDocuments.add(document);
            }
            Map<String, AccountVersionDocument> autoUpdateMap = generatePackageMap(true);
            if (autoUpdateMap != null && autoUpdateMap.size() != 0) {
                for (Entry<String, AccountVersionDocument> entry2 : autoUpdateMap.entrySet()) {
                    packageName = (String) entry2.getKey();
                    int versionCode = ((AccountVersionDocument) entry2.getValue()).versionCode;
                    String accountName = ((AccountVersionDocument) entry2.getValue()).account;
                    Offer documentOffer = ((AccountVersionDocument) entry2.getValue()).document.getOffer(1);
                    if (documentOffer == null || !documentOffer.checkoutFlowRequired) {
                        AccountVersionDocument nonAutoDocument = (AccountVersionDocument) packageMap.get(packageName);
                        if (nonAutoDocument == null || versionCode <= nonAutoDocument.versionCode) {
                            FinskyLog.d("Skipping proposed auto-acquire of %s by %s", packageName, FinskyLog.scrubPii(accountName));
                            blacklistPackage(packageName);
                        } else {
                            FinskyLog.d("Proposed auto-acquire of %s by %s revealed higher version %d", packageName, FinskyLog.scrubPii(accountName), Integer.valueOf(versionCode));
                            startAutoAcquire(packageName, (AccountVersionDocument) entry2.getValue());
                            AppData appData = new AppData();
                            appData.oldVersion = nonAutoDocument.versionCode;
                            appData.version = versionCode;
                            FinskyApp.get().getEventLogger(nonAutoDocument.account).logBackgroundEvent(116, packageName, "auto-acquire", 0, null, appData);
                            FinskyApp.get().getEventLogger(accountName).logBackgroundEvent(117, packageName, "auto-acquire", 0, null, appData);
                        }
                    } else {
                        FinskyLog.d("Skipping proposed auto-acquire - Unexpected checkoutFlowRequired=true for %s by %s", packageName, FinskyLog.scrubPii(accountName));
                        blacklistPackage(packageName);
                    }
                }
            }
        }
    }

    private void startAutoAcquire(String packageName, AccountVersionDocument info) {
        Account purchaseAccount = AccountHandler.findAccount(info.account, FinskyApp.get());
        if (LibraryUtils.isOwned(info.document, this.mLibraries.getAccountLibrary(purchaseAccount))) {
            FinskyLog.w("Skip auto-acquire of %s by %s because already owned", packageName, FinskyLog.scrubPii(info.account));
            blacklistPackage(packageName);
            return;
        }
        PurchaseInitiator.makeFreePurchase(purchaseAccount, info.document, 1, null, null, false, false);
        blacklistPackage(packageName);
    }

    private void blacklistPackage(String packageName) {
        if (sPackageBlacklist == null) {
            sPackageBlacklist = Sets.newHashSet();
        }
        sPackageBlacklist.add(packageName);
    }

    public List<Document> getDocuments() {
        return this.mCollatedDocuments;
    }

    protected Map<String, AccountVersionDocument> generatePackageMap(boolean collectAutoAcquire) {
        Map<String, AccountVersionDocument> packageMap = Maps.newHashMap();
        for (DfeBulkDetails request : this.mRequests) {
            List<Document> results = request.getDocuments();
            if (results == null) {
                return null;
            }
            String accountName = request.getDfeApi().getAccountName();
            for (Document doc : results) {
                String packageName = doc.getAppDetails().packageName;
                int versionCode = doc.getAppDetails().versionCode;
                AccountVersionDocument foundPackage = (AccountVersionDocument) packageMap.get(packageName);
                if (collectAutoAcquire == isAutoAcquireAttempt(packageName, accountName)) {
                    if (foundPackage == null) {
                        packageMap.put(packageName, new AccountVersionDocument(accountName, versionCode, doc));
                    } else {
                        if (versionCode != foundPackage.versionCode) {
                            foundPackage.needAccountForUpdate = true;
                        }
                        if (versionCode > foundPackage.versionCode) {
                            foundPackage.versionCode = versionCode;
                            foundPackage.account = accountName;
                            foundPackage.document = doc;
                        }
                    }
                }
            }
        }
        return packageMap;
    }

    protected void captureVariesByAccount(Document document) {
        int newPersistentFlag;
        String packageName = document.getAppDetails().packageName;
        if (document.getAppDetails().variesByAccount) {
            newPersistentFlag = 1;
        } else {
            newPersistentFlag = 0;
        }
        InstallerData installerData = this.mInstallerDataStore.get(packageName);
        int oldPersistentFlags = installerData == null ? 0 : installerData.getPersistentFlags();
        if (newPersistentFlag != (oldPersistentFlags & 1)) {
            FinskyLog.d("Change varies-by-account for %s to %b", packageName, Boolean.valueOf(variesByAccount));
            if (installerData != null || newPersistentFlag != 0) {
                this.mInstallerDataStore.setPersistentFlags(packageName, (oldPersistentFlags & -2) | newPersistentFlag);
            }
        }
    }

    protected void captureAutoAcquireTags(Document document) {
        AppDetails appDetails = document.getAppDetails();
        String packageName = appDetails.packageName;
        String[] newTags = appDetails.autoAcquireFreeAppIfHigherVersionAvailableTag;
        InstallerData installerData = this.mInstallerDataStore.get(packageName);
        if (!Arrays.equals(newTags, installerData == null ? EMPTY_STRING_ARRAY : installerData.getAutoAcquireTags())) {
            FinskyLog.d("Change auto-acquire tags for %s from %s to %s", packageName, Utils.commaPackStrings(installerData == null ? EMPTY_STRING_ARRAY : installerData.getAutoAcquireTags()), Utils.commaPackStrings(newTags));
            if (installerData != null || newTags.length != 0) {
                this.mInstallerDataStore.setAutoAcquireTags(packageName, newTags);
            }
        }
    }

    private void captureUpdateAccount(String packageName, String accountName) {
        InstallerData installerData = this.mInstallerDataStore.get(packageName);
        if (!Objects.equal(installerData == null ? null : installerData.getAccountForUpdate(), accountName)) {
            FinskyLog.d("Capture account %s for next update of %s", FinskyLog.scrubPii(accountName), packageName);
            this.mInstallerDataStore.setAccountForUpdate(packageName, accountName);
        }
    }
}
