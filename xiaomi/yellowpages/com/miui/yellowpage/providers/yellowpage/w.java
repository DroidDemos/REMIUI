package com.miui.yellowpage.providers.yellowpage;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.alipay.sdk.cons.MiniDefine;
import com.miui.yellowpage.base.exception.NetworkUnavailableException;
import com.miui.yellowpage.base.exception.ServerException;
import com.miui.yellowpage.base.provider.InternalYellowPageContract.Profile;
import com.miui.yellowpage.base.request.HttpRequest;
import com.miui.yellowpage.base.utils.Cache;
import com.miui.yellowpage.base.utils.HostManager;
import com.miui.yellowpage.base.utils.Log;
import java.net.UnknownServiceException;

/* compiled from: YellowPageProvider */
class w implements Runnable {
    final /* synthetic */ SQLiteDatabase Ag;
    final /* synthetic */ YellowPageProvider Ib;
    final /* synthetic */ Context val$context;

    w(YellowPageProvider yellowPageProvider, SQLiteDatabase sQLiteDatabase, Context context) {
        this.Ib = yellowPageProvider;
        this.Ag = sQLiteDatabase;
        this.val$context = context;
    }

    public void run() {
        HttpRequest httpRequest = new HttpRequest(this.Ib.getContext(), HostManager.getProfileUrl(), 0);
        httpRequest.setRequireLogin(true);
        httpRequest.setRequireLocId(true);
        httpRequest.setRequestCache(false);
        try {
            String requestServer = httpRequest.requestServer();
            Log.d("YellowPageProvider", "[retrieveProfile] json length:" + requestServer.length());
            ContentValues contentValues = new ContentValues();
            contentValues.put("cache_key", Cache.PROFILE_CACHE_KEY);
            contentValues.put(MiniDefine.at, requestServer);
            this.Ag.replace("cache", null, contentValues);
            this.val$context.getContentResolver().notifyChange(Profile.CONTENT_URI, null);
            Log.d("YellowPageProvider", "notify profile data change");
        } catch (NetworkUnavailableException e) {
            e.printStackTrace();
        } catch (ServerException e2) {
            e2.printStackTrace();
        } catch (UnknownServiceException e3) {
            e3.printStackTrace();
        }
    }
}
