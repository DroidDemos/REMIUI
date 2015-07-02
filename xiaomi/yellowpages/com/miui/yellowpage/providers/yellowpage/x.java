package com.miui.yellowpage.providers.yellowpage;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.text.TextUtils;
import com.alipay.sdk.cons.MiniDefine;
import com.miui.yellowpage.base.request.JSONRequest;
import com.miui.yellowpage.base.utils.Cache;
import com.miui.yellowpage.base.utils.HostManager;
import com.miui.yellowpage.base.utils.Log;
import miui.yellowpage.YellowPageContract.Search;

/* compiled from: YellowPageProvider */
class x implements Runnable {
    final /* synthetic */ SQLiteDatabase Ag;
    final /* synthetic */ YellowPageProvider Ib;
    final /* synthetic */ Context val$context;

    x(YellowPageProvider yellowPageProvider, SQLiteDatabase sQLiteDatabase, Context context) {
        this.Ib = yellowPageProvider;
        this.Ag = sQLiteDatabase;
        this.val$context = context;
    }

    public void run() {
        JSONRequest jSONRequest = new JSONRequest(this.Ib.getContext(), HostManager.getNavigationSearchTipsUrl());
        Object b = this.Ib.getLocId();
        if (!TextUtils.isEmpty(b)) {
            jSONRequest.addParam("locid", b);
        }
        if (jSONRequest.getStatus() == 0) {
            String requestData = jSONRequest.requestData();
            Log.d("YellowPageProvider", "tip:" + requestData);
            ContentValues contentValues = new ContentValues();
            contentValues.put("cache_key", Cache.NAVIGATION_SEARCH_TIPS_CACHE_KEY);
            contentValues.put(MiniDefine.at, requestData);
            this.Ag.replace("cache", null, contentValues);
            this.val$context.getContentResolver().notifyChange(Uri.withAppendedPath(Search.CONTENT_URI, "tips"), null);
            Log.d("YellowPageProvider", "notify search tips data change");
        }
    }
}
