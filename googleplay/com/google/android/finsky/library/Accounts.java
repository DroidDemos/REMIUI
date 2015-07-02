package com.google.android.finsky.library;

import android.accounts.Account;
import java.util.List;

public interface Accounts {
    Account getAccount(String str);

    List<Account> getAccounts();
}
