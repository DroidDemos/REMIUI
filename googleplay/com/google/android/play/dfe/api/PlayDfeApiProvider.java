package com.google.android.play.dfe.api;

import android.accounts.Account;

public interface PlayDfeApiProvider {
    PlayDfeApi getPlayDfeApi(Account account);
}
