package com.google.android.gms.internal;

import android.accounts.Account;
import android.util.Log;

public class pu {
    public static String a(Account account) {
        return account == null ? "null" : Log.isLoggable("GCoreUlr", 2) ? account.name : "account#" + (account.name.hashCode() % 20) + "#";
    }

    public static String d(Integer num) {
        return num == null ? "(null)" : Log.isLoggable("GCoreUlr", 2) ? String.valueOf(num) : "tag#" + (num.intValue() % 20);
    }
}
