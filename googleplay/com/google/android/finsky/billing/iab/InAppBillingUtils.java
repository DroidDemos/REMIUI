package com.google.android.finsky.billing.iab;

import android.accounts.Account;
import android.content.Context;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.api.AccountHandler;
import com.google.android.finsky.appstate.AppStates;
import com.google.android.finsky.appstate.AppStates.AppState;
import com.google.android.finsky.library.Libraries;
import com.google.android.finsky.protos.Common.Docid;
import com.google.android.finsky.utils.FinskyLog;
import java.util.List;

public class InAppBillingUtils {

    public enum ResponseCode {
        RESULT_OK(0),
        RESULT_USER_CANCELED(1),
        RESULT_SERVICE_UNAVAILABLE(2),
        RESULT_BILLING_UNAVAILABLE(3),
        RESULT_ITEM_UNAVAILABLE(4),
        RESULT_DEVELOPER_ERROR(5),
        RESULT_ERROR(6),
        RESULT_ITEM_ALREADY_OWNED(7),
        RESULT_ITEM_NOT_OWNED(8),
        RESULT_PROMO_ELIGIBLE(9),
        RESULT_NOT_PROMO_ELIGIBLE(10);
        
        private final int responseCode;

        private ResponseCode(int responseCode) {
            this.responseCode = responseCode;
        }

        public int responseCode() {
            return this.responseCode;
        }

        public String toString() {
            return new StringBuilder(super.toString()).append('(').append(this.responseCode).append(')').toString();
        }
    }

    public static Docid buildDocid(String docidStr, String itemType) {
        int docType = 0;
        if ("inapp".equals(itemType)) {
            docType = 11;
        } else if ("subs".equals(itemType)) {
            docType = 15;
        }
        Docid docId = new Docid();
        docId.backend = 3;
        docId.hasBackend = true;
        docId.type = docType;
        docId.hasType = true;
        docId.backendDocid = docidStr;
        docId.hasBackendDocid = true;
        return docId;
    }

    public static String buildDocidStr(String itemId, String itemType, String packageName) {
        return itemType + ":" + packageName + ":" + itemId;
    }

    public static Account getPreferredAccount(String packageName, Context context) {
        Account preferredAccount;
        AppStates appStates = FinskyApp.get().getAppStates();
        appStates.blockingLoad();
        AppState appState = appStates.getApp(packageName);
        if (!(appState == null || appState.packageManagerState == null)) {
            Libraries libraries = FinskyApp.get().getLibraries();
            libraries.blockingLoad();
            preferredAccount = pickAccount(appState, libraries.getAppOwners(packageName, appState.packageManagerState.certificateHashes));
            if (preferredAccount != null) {
                return preferredAccount;
            }
        }
        preferredAccount = AccountHandler.getFirstAccount(context);
        if (preferredAccount != null) {
            FinskyLog.d("%s: Account from first account - %s", packageName, FinskyLog.scrubPii(preferredAccount.name));
            return preferredAccount;
        }
        FinskyLog.w("%s: No account found.", packageName);
        return null;
    }

    public static Account pickAccount(AppState appState, List<Account> ownerAccounts) {
        if (ownerAccounts.size() <= 0) {
            return null;
        }
        if (appState.installerData != null) {
            String accountName = appState.installerData.getAccountName();
            for (Account ownerAcct : ownerAccounts) {
                if (TextUtils.equals(ownerAcct.name, accountName)) {
                    FinskyLog.d("%s: Account determined from installer data - %s", appState.packageName, FinskyLog.scrubPii(accountName));
                    return ownerAcct;
                }
            }
        }
        FinskyLog.d("%s: Account determined from library ownership - %s", appState.packageName, FinskyLog.scrubPii(((Account) ownerAccounts.get(0)).name));
        return (Account) ownerAccounts.get(0);
    }

    public static ResponseCode validatePackageName(String packageName, PackageManager packageManager, int callingUid) {
        if (TextUtils.isEmpty(packageName)) {
            FinskyLog.w("Input Error: Non empty/null argument expected for packageName.", new Object[0]);
            return ResponseCode.RESULT_DEVELOPER_ERROR;
        }
        for (String uidPackageName : packageManager.getPackagesForUid(callingUid)) {
            if (packageName.equals(uidPackageName)) {
                return ResponseCode.RESULT_OK;
            }
        }
        FinskyLog.w("Package name %s does not match UID %d", packageName, Integer.valueOf(callingUid));
        return ResponseCode.RESULT_DEVELOPER_ERROR;
    }
}
