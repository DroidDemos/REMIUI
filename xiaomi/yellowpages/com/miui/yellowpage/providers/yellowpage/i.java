package com.miui.yellowpage.providers.yellowpage;

import android.database.sqlite.SQLiteDatabase;
import com.miui.yellowpage.base.utils.Files;
import com.miui.yellowpage.base.utils.Log;
import com.miui.yellowpage.utils.F;

/* compiled from: YellowPageDatabaseHelper */
class i implements Runnable {
    final /* synthetic */ SQLiteDatabase Ag;
    final /* synthetic */ q Ah;

    i(q qVar, SQLiteDatabase sQLiteDatabase) {
        this.Ah = qVar;
        this.Ag = sQLiteDatabase;
    }

    public void run() {
        Log.d("YellowPageDatabaseHelper", "import ivr data");
        Files.copyWebAssetDirectory(this.Ah.mContext.getAssets(), "ivr_v90", F.ab(this.Ah.mContext));
        this.Ah.c(this.Ag, "ivr", F.hB());
    }
}
