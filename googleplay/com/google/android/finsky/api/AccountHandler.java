package com.google.android.finsky.api;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;
import android.text.TextUtils;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.config.G;
import com.google.android.finsky.config.PreferenceFile.SharedPreference;
import com.google.android.finsky.utils.Utils;
import com.google.android.gms.common.GooglePlayServicesUtil;

public class AccountHandler {
    private static String[] sSupportedAccountTypes;

    public static synchronized String[] getAccountTypes() {
        String[] strArr;
        synchronized (AccountHandler.class) {
            if (sSupportedAccountTypes == null) {
                if (GooglePlayServicesUtil.isSidewinderDevice(FinskyApp.get())) {
                    sSupportedAccountTypes = new String[]{"com.sidewinder"};
                } else {
                    String[] extraTypes = Utils.commaUnpackStrings((String) G.supportedAccountTypes.get());
                    int numExtraTypes = extraTypes.length;
                    sSupportedAccountTypes = new String[(numExtraTypes + 1)];
                    sSupportedAccountTypes[0] = "com.google";
                    System.arraycopy(extraTypes, 0, sSupportedAccountTypes, 1, numExtraTypes);
                }
            }
            strArr = sSupportedAccountTypes;
        }
        return strArr;
    }

    public static Account[] getAccounts(Context context) {
        AccountManager am = AccountManager.get(context);
        Account[] result = null;
        for (String accountType : getAccountTypes()) {
            Account[] accounts = am.getAccountsByType(accountType);
            if (accounts.length != 0) {
                if (result == null) {
                    result = accounts;
                } else {
                    Account[] newResult = new Account[(result.length + accounts.length)];
                    System.arraycopy(result, 0, newResult, 0, result.length);
                    System.arraycopy(accounts, 0, newResult, result.length, accounts.length);
                    result = newResult;
                }
            }
        }
        if (result == null) {
            return new Account[0];
        }
        return result;
    }

    public static void saveAccountToPreferences(Account account, SharedPreference<String> preference) {
        if (account != null) {
            preference.put(account.name);
        }
    }

    public static Account getFirstAccount(Context context) {
        AccountManager am = AccountManager.get(context);
        for (String accountType : getAccountTypes()) {
            Account[] accounts = am.getAccountsByType(accountType);
            if (accounts.length > 0) {
                return accounts[0];
            }
        }
        return null;
    }

    public static Account findAccount(String accountName, Context context) {
        if (TextUtils.isEmpty(accountName)) {
            return null;
        }
        AccountManager am = AccountManager.get(context);
        for (String accountType : getAccountTypes()) {
            for (Account a : am.getAccountsByType(accountType)) {
                if (a.name.equalsIgnoreCase(accountName)) {
                    return a;
                }
            }
        }
        return null;
    }

    public static boolean hasAccount(String accountName, Context context) {
        return findAccount(accountName, context) != null;
    }

    public static Account getAccountFromPreferences(Context context, SharedPreference<String> preference) {
        Account result = findAccount((String) preference.get(), context);
        if (result != null) {
            return result;
        }
        result = getFirstAccount(context);
        saveAccountToPreferences(result, preference);
        return result;
    }
}
