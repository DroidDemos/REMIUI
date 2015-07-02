package com.miui.yellowpage.providers.yellowpage;

import android.database.sqlite.SQLiteDatabase;

/* compiled from: YellowPageProvider */
class s implements Runnable {
    final /* synthetic */ YellowPageProvider Ib;

    s(YellowPageProvider yellowPageProvider) {
        this.Ib = yellowPageProvider;
    }

    public void run() {
        SQLiteDatabase readableDatabase = YellowPageProvider.ta.getReadableDatabase();
        this.Ib.c(readableDatabase);
        this.Ib.b(readableDatabase);
        YellowPageProvider.td.set(true);
    }
}
