package com.miui.yellowpage.providers.yellowpage;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.alipay.sdk.cons.MiniDefine;
import com.miui.yellowpage.base.exception.NetworkUnavailableException;
import com.miui.yellowpage.base.exception.ServerException;
import com.miui.yellowpage.base.provider.InternalYellowPageContract.HotWord;
import com.miui.yellowpage.base.request.HttpRequest;
import com.miui.yellowpage.base.utils.Cache;
import com.miui.yellowpage.base.utils.HostManager;
import com.miui.yellowpage.base.utils.Log;
import java.net.UnknownServiceException;

/* compiled from: YellowPageProvider */
class t implements Runnable {
    final /* synthetic */ SQLiteDatabase Ag;
    final /* synthetic */ YellowPageProvider Ib;
    final /* synthetic */ Context val$context;

    t(YellowPageProvider yellowPageProvider, Context context, SQLiteDatabase sQLiteDatabase) {
        this.Ib = yellowPageProvider;
        this.val$context = context;
        this.Ag = sQLiteDatabase;
    }

    public void run() {
        HttpRequest httpRequest = new HttpRequest(this.val$context, HostManager.getSearchHotWordsUrl(), 0);
        httpRequest.setRequireLogin(true);
        httpRequest.setRequireLocId(true);
        try {
            String requestServer = httpRequest.requestServer();
            Log.v("YellowPageProvider", "hot words:" + requestServer);
            ContentValues contentValues = new ContentValues();
            contentValues.put("cache_key", Cache.HOT_WORDS_CACHE_KEY);
            contentValues.put(MiniDefine.at, requestServer);
            this.Ag.replace("cache", null, contentValues);
            this.val$context.getContentResolver().notifyChange(HotWord.CONTENT_URI, null);
        } catch (NetworkUnavailableException e) {
            e.printStackTrace();
        } catch (ServerException e2) {
            e2.printStackTrace();
        } catch (UnknownServiceException e3) {
            e3.printStackTrace();
        }
    }
}
