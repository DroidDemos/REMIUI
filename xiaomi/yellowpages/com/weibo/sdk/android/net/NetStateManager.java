package com.weibo.sdk.android.net;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import org.apache.http.HttpHost;

public class NetStateManager {
    public static NetState IW;
    private static Context mContext;

    public enum NetState {
        Mobile,
        WIFI,
        NOWAY
    }

    static {
        IW = NetState.Mobile;
    }

    public static HttpHost if() {
        Cursor query;
        HttpHost httpHost = null;
        Uri parse = Uri.parse("content://telephony/carriers/preferapn");
        if (mContext != null) {
            query = mContext.getContentResolver().query(parse, null, null, null, null);
        } else {
            query = null;
        }
        if (query != null && query.moveToFirst()) {
            String string = query.getString(query.getColumnIndex("proxy"));
            if (string != null && string.trim().length() > 0) {
                httpHost = new HttpHost(string, 80);
            }
            query.close();
        }
        return httpHost;
    }
}
