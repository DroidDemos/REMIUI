package com.miui.yellowpage.providers.yellowpage;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.alipay.sdk.cons.GlobalConstants;
import com.alipay.sdk.cons.MiniDefine;
import com.miui.yellowpage.base.request.JSONRequest;
import com.miui.yellowpage.base.utils.Cache;
import com.miui.yellowpage.base.utils.HostManager;
import com.miui.yellowpage.base.utils.Log;
import miui.yellowpage.YellowPageContract.Navigation;

/* compiled from: YellowPageProvider */
class v implements Runnable {
    final /* synthetic */ SQLiteDatabase Ag;
    final /* synthetic */ YellowPageProvider Ib;
    final /* synthetic */ Context val$context;

    v(YellowPageProvider yellowPageProvider, SQLiteDatabase sQLiteDatabase, Context context) {
        this.Ib = yellowPageProvider;
        this.Ag = sQLiteDatabase;
        this.val$context = context;
    }

    public void run() {
        JSONRequest jSONRequest = new JSONRequest(this.Ib.getContext(), HostManager.getNavigationDataUrl());
        this.Ib.b(jSONRequest);
        this.Ib.a(jSONRequest);
        jSONRequest.addParam("v", GlobalConstants.d);
        if (jSONRequest.getStatus() == 0) {
            String requestData = jSONRequest.requestData();
            Log.d("YellowPageProvider", "[retrieveNavigationData] json length:" + requestData.length());
            ContentValues contentValues = new ContentValues();
            contentValues.put("cache_key", Cache.NAVIGATION_DATA_CACHE_KEY);
            contentValues.put(MiniDefine.at, requestData);
            this.Ag.replace("cache", null, contentValues);
            this.val$context.getContentResolver().notifyChange(Navigation.CONTENT_URI, null);
            Log.d("YellowPageProvider", "notify navigation data change");
        }
    }
}
