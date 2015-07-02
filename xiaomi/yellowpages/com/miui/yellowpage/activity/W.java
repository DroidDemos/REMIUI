package com.miui.yellowpage.activity;

import android.content.ContentValues;
import com.miui.yellowpage.base.utils.BusinessStatistics;
import miui.yellowpage.YellowPageContract.YellowPage;

/* compiled from: YellowPageActivity */
class W implements Runnable {
    final /* synthetic */ YellowPageActivity uV;

    W(YellowPageActivity yellowPageActivity) {
        this.uV = yellowPageActivity;
    }

    public void run() {
        ContentValues contentValues = new ContentValues();
        contentValues.put("last_use_time", Long.valueOf(System.currentTimeMillis()));
        this.uV.getContentResolver().update(YellowPage.CONTENT_URI, contentValues, "yid = ?", new String[]{String.valueOf(this.uV.ld)});
        BusinessStatistics.viewYellowPageDetail(this.uV, String.valueOf(this.uV.ld), this.uV.getStatisticsContext().getSource(), this.uV.getStatisticsContext().getSourceModuleId());
    }
}
