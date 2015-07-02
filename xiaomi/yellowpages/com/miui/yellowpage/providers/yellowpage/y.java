package com.miui.yellowpage.providers.yellowpage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import com.alipay.sdk.cons.MiniDefine;
import com.miui.yellowpage.base.exception.ClientException;
import com.miui.yellowpage.base.exception.NetworkUnavailableException;
import com.miui.yellowpage.base.exception.ServiceUnavailableException;
import com.miui.yellowpage.base.model.YellowPage;
import com.miui.yellowpage.base.request.JSONRequest;
import com.miui.yellowpage.base.utils.HostManager;
import com.miui.yellowpage.base.utils.Log;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import miui.yellowpage.YellowPagePhone;
import miui.yellowpage.YellowPageUtils;
import org.json.JSONObject;

/* compiled from: YellowPageHandler */
public class y {
    private static final String[] JJ;
    private static final Map JK;

    static {
        JJ = new String[]{"last_use_time", "favorite", "subscribe_stats", "yid", MiniDefine.m};
        JK = new HashMap();
    }

    public static boolean J(Context context, String str) {
        String normalizedNumber = o.getNormalizedNumber(context, str);
        if (JK.containsKey(normalizedNumber)) {
            return Math.abs(System.currentTimeMillis() - ((Long) JK.get(normalizedNumber)).longValue()) > TimeUnit.DAYS.toMillis(1);
        } else {
            return true;
        }
    }

    public static long c(SQLiteDatabase sQLiteDatabase, YellowPage yellowPage) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("yid", Long.valueOf(yellowPage.getId()));
        contentValues.put("miid", yellowPage.getMiId());
        contentValues.put(MiniDefine.at, yellowPage.getContent());
        contentValues.put("hotCatId", yellowPage.getHotCatId());
        contentValues.put("hotSort", Integer.valueOf(yellowPage.getHotSort()));
        contentValues.put("update_time", Long.valueOf(System.currentTimeMillis()));
        a(sQLiteDatabase, contentValues, yellowPage.getId());
        if (!contentValues.containsKey("subscribe_stats")) {
            contentValues.put("subscribe_stats", Integer.valueOf(yellowPage.isPreset() ? 1 : 2));
        }
        a(sQLiteDatabase, contentValues, yellowPage);
        long replace = sQLiteDatabase.replace("yellow_page", null, contentValues);
        if (replace > 0) {
            a.a(sQLiteDatabase, yellowPage);
            a.b(sQLiteDatabase, yellowPage);
        }
        return replace;
    }

    public static int a(Context context, SQLiteDatabase sQLiteDatabase, String str, String[] strArr) {
        List<Long> linkedList = new LinkedList();
        Cursor query = sQLiteDatabase.query("yellow_page", JJ, str, strArr, null, null, null);
        if (query != null) {
            while (query.moveToNext()) {
                try {
                    long j = query.getLong(3);
                    long j2 = query.getLong(4);
                    long j3 = query.getLong(2);
                    Log.d("YellowPageHandler", "yid:" + j + ", type:" + j2 + ", stats:" + j3);
                    a.a(context, sQLiteDatabase, j);
                    if (j2 != 3) {
                        linkedList.add(Long.valueOf(j));
                    } else if (j3 == 1) {
                        ContentValues contentValues = new ContentValues();
                        contentValues.put("subscribe_stats", Integer.valueOf(3));
                        sQLiteDatabase.update("yellow_page", contentValues, "yid=?", new String[]{String.valueOf(j)});
                    } else if (j3 == 2 || j3 == 4) {
                        linkedList.add(Long.valueOf(j));
                    }
                } finally {
                    query.close();
                }
            }
        }
        int i = 0;
        for (Long longValue : linkedList) {
            long longValue2 = longValue.longValue();
            i = sQLiteDatabase.delete("yellow_page", "yid=?", new String[]{String.valueOf(longValue2)}) + i;
        }
        return i;
    }

    public static long b(Context context, SQLiteDatabase sQLiteDatabase, String str) {
        Cursor query = sQLiteDatabase.query(a.h(context, str), new String[]{"yid"}, null, null, null, null, null);
        if (query != null) {
            try {
                if (query.moveToFirst()) {
                    if (a(context, sQLiteDatabase, "yid =? ", new String[]{String.valueOf(query.getLong(0))}) > 0) {
                        return r0;
                    }
                }
                query.close();
            } finally {
                query.close();
            }
        }
        return 0;
    }

    public static YellowPage K(Context context, String str) {
        String normalizedNumber = YellowPageUtils.getNormalizedNumber(context, str);
        Log.d("YellowPageHandler", "The requested phone normalized number is " + normalizedNumber);
        if (TextUtils.isEmpty(normalizedNumber)) {
            return null;
        }
        JSONRequest jSONRequest = new JSONRequest(context, HostManager.getYellowPageQueryUrl());
        jSONRequest.addParam("phone", normalizedNumber);
        jSONRequest.addParam("raw_phone", str);
        int status = jSONRequest.getStatus();
        if (status == 0) {
            JK.put(normalizedNumber, Long.valueOf(System.currentTimeMillis()));
            JSONObject jSONObject = new JSONObject(jSONRequest.requestData());
            Object optString = jSONObject.optString("yp");
            if (TextUtils.isEmpty(optString)) {
                JSONObject optJSONObject = jSONObject.optJSONObject("atd");
                if (optJSONObject != null) {
                    Log.d("YellowPageHandler", "The number " + str + "is a remote antispam");
                    int i = optJSONObject.getInt("count");
                    int i2 = optJSONObject.getInt("catId");
                    int i3 = optJSONObject.getInt("provider");
                    Object cidName = YellowPageUtils.getCidName(context, i2);
                    if (TextUtils.isEmpty(cidName)) {
                        return null;
                    }
                    YellowPagePhone yellowPagePhone = new YellowPagePhone(-1, null, cidName, str, normalizedNumber, 2, i3, i, true, null, null, i2);
                    List arrayList = new ArrayList();
                    arrayList.add(yellowPagePhone);
                    return new YellowPage().setPhones(arrayList).setProviderId(i3);
                }
                Log.d("YellowPageHandler", "There is no yp or atd data for number " + str);
                return null;
            }
            Log.d("YellowPageHandler", "The number " + str + " is a remote yellowpage ");
            return YellowPage.fromJson(optString);
        } else if (status == 3) {
            throw new ClientException();
        } else if (status == 1) {
            throw new NetworkUnavailableException();
        } else if (status != 4 && status != 2) {
            return null;
        } else {
            throw new ServiceUnavailableException();
        }
    }

    private static int e(int i, int i2) {
        if (i2 == 1 && i == 1) {
            return 1;
        }
        if (i2 == 1 && i == 0) {
            return 2;
        }
        return 3;
    }

    private static ContentValues a(SQLiteDatabase sQLiteDatabase, ContentValues contentValues, YellowPage yellowPage) {
        int i;
        int i2 = 1;
        if (yellowPage.isHot()) {
            i = 1;
        } else {
            i = 0;
        }
        if (!yellowPage.isPreset()) {
            i2 = 0;
        }
        contentValues.put(MiniDefine.m, Integer.valueOf(e(i, i2)));
        return contentValues;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static android.content.ContentValues a(android.database.sqlite.SQLiteDatabase r8, android.content.ContentValues r9, long r10) {
        /*
        r0 = 1;
        r6 = 0;
        r5 = 0;
        r1 = "yellow_page";
        r2 = JJ;
        r3 = "yid = ? ";
        r4 = new java.lang.String[r0];
        r0 = java.lang.String.valueOf(r10);
        r4[r6] = r0;
        r0 = r8;
        r6 = r5;
        r7 = r5;
        r1 = r0.query(r1, r2, r3, r4, r5, r6, r7);
        if (r1 == 0) goto L_0x004d;
    L_0x001a:
        r0 = r1.moveToFirst();	 Catch:{ Exception -> 0x004e }
        if (r0 == 0) goto L_0x004a;
    L_0x0020:
        r0 = 0;
        r2 = r1.getLong(r0);	 Catch:{ Exception -> 0x004e }
        r0 = 1;
        r0 = r1.getInt(r0);	 Catch:{ Exception -> 0x004e }
        r4 = 2;
        r4 = r1.getInt(r4);	 Catch:{ Exception -> 0x004e }
        r5 = "last_use_time";
        r2 = java.lang.Long.valueOf(r2);	 Catch:{ Exception -> 0x004e }
        r9.put(r5, r2);	 Catch:{ Exception -> 0x004e }
        r2 = "favorite";
        r0 = java.lang.Integer.valueOf(r0);	 Catch:{ Exception -> 0x004e }
        r9.put(r2, r0);	 Catch:{ Exception -> 0x004e }
        r0 = "subscribe_stats";
        r2 = java.lang.Integer.valueOf(r4);	 Catch:{ Exception -> 0x004e }
        r9.put(r0, r2);	 Catch:{ Exception -> 0x004e }
    L_0x004a:
        r1.close();
    L_0x004d:
        return r9;
    L_0x004e:
        r0 = move-exception;
        r0.printStackTrace();	 Catch:{ all -> 0x0056 }
        r1.close();
        goto L_0x004d;
    L_0x0056:
        r0 = move-exception;
        r1.close();
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.miui.yellowpage.providers.yellowpage.y.a(android.database.sqlite.SQLiteDatabase, android.content.ContentValues, long):android.content.ContentValues");
    }
}
