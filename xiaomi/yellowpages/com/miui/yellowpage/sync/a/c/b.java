package com.miui.yellowpage.sync.a.c;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;
import com.alipay.mobilesecuritysdk.constant.ConfigConstant;
import com.alipay.sdk.cons.MiniDefine;
import com.miui.yellowpage.base.request.JSONRequest;
import com.miui.yellowpage.base.utils.Device;
import com.miui.yellowpage.base.utils.HostManager;
import com.miui.yellowpage.base.utils.Log;
import com.miui.yellowpage.model.e;
import com.miui.yellowpage.model.x;
import com.miui.yellowpage.utils.o;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import miui.yellowpage.AntispamCustomCategory;
import miui.yellowpage.YellowPageContract.AntispamNumber;
import miui.yellowpage.YellowPageUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: MarkedNumber */
public class b extends c {
    public void E(Context context) {
        N(context);
        O(context);
    }

    private void N(Context context) {
        ArrayList L = o.L(context);
        if (L != null && L.size() != 0) {
            Iterator it = L.iterator();
            while (it.hasNext()) {
                String str = (String) it.next();
                Cursor query = context.getContentResolver().query(Uri.withAppendedPath(AntispamNumber.CONTENT_PHONE_LOOKUP_URI, str), null, "type = ?", new String[]{String.valueOf(4)}, null);
                if (query == null || query.getCount() == 0) {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("number", str);
                    contentValues.put("normalized_number", YellowPageUtils.getNormalizedNumber(context, str));
                    contentValues.put("cid", Integer.valueOf(7));
                    contentValues.put(MiniDefine.m, Integer.valueOf(4));
                    contentValues.put("pid", Integer.valueOf(0));
                    context.getContentResolver().insert(AntispamNumber.CONTENT_URI, contentValues);
                    Log.d("PushTask", "mark number " + str + " in call as one ringcall number");
                }
                if (query != null) {
                    query.close();
                }
            }
        }
    }

    private void O(Context context) {
        Cursor query = context.getContentResolver().query(AntispamNumber.CONTENT_URI, new String[]{"_id", "number", "cid", "normalized_number", MiniDefine.m}, "type IN (3,4) AND upload=?", new String[]{String.valueOf(0)}, null);
        Log.d("PushTask", "Push marked number");
        if (query != null) {
            try {
                if (query.getCount() == 0) {
                    Log.d("PushTask", "no marked numbers, quit");
                    return;
                }
                List arrayList = new ArrayList();
                JSONArray jSONArray = new JSONArray();
                a(context, query, arrayList, jSONArray);
                a(context, arrayList, jSONArray);
                query.close();
            } catch (JSONException e) {
                e.printStackTrace();
            } finally {
                query.close();
            }
        }
    }

    private void a(Context context, List list, JSONArray jSONArray) {
        JSONRequest jSONRequest = new JSONRequest(context, HostManager.getAntispamNumberPushUrl());
        jSONRequest.setDecryptDownloadData(false);
        jSONRequest.addParam("data", jSONArray.toString());
        jSONRequest.setHttpMethod("POST");
        int status = jSONRequest.getStatus();
        if (status == 0) {
            String join = TextUtils.join(",", list.toArray());
            Log.d("PushTask", "whereClause:" + join);
            ContentValues contentValues = new ContentValues();
            contentValues.put("upload", Integer.valueOf(1));
            Log.d("PushTask", "push marked number succeeded:" + context.getContentResolver().update(AntispamNumber.CONTENT_URI, contentValues, "_id IN (" + join + ")", null));
            return;
        }
        Log.e("PushTask", "push marked number failed, status: " + status);
    }

    private void a(Context context, Cursor cursor, List list, JSONArray jSONArray) {
        String imei = Device.getIMEI(context);
        while (cursor.moveToNext()) {
            long j = cursor.getLong(0);
            String string = cursor.getString(1);
            long j2 = cursor.getLong(2);
            String string2 = cursor.getString(3);
            AntispamCustomCategory antispamNumberCategory = YellowPageUtils.getAntispamNumberCategory(context, string);
            Object obj = ConfigConstant.WIRELESS_FILENAME;
            if (antispamNumberCategory != null) {
                obj = antispamNumberCategory.getCategoryName();
            }
            int i = cursor.getInt(4);
            list.add(Long.valueOf(j));
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("markedNo", string2);
            jSONObject.put("catId", j2 >= 10000 ? 0 : j2);
            jSONObject.put("catTitle", obj);
            jSONObject.put("device", imei);
            if (i == 4) {
                jSONObject.put("act", 4);
                Log.d("PushTask", "One ring call number " + string);
            } else {
                x j3 = o.j(context, string);
                if (j3 == null) {
                    e k = o.k(context, string);
                    if (k != null) {
                        if (k.aL()) {
                            jSONObject.put("act", 2);
                            Log.d("PushTask", "Sms in mark " + string);
                        } else if (k.aM()) {
                            jSONObject.put("act", 3);
                            Log.d("PushTask", "Sms out mark " + string);
                        }
                    }
                } else if (j3.iF()) {
                    jSONObject.put("act", 0);
                    jSONObject.put("duration", j3.getDuration());
                    Log.d("PushTask", "Call in mark " + string);
                } else if (j3.iG()) {
                    jSONObject.put("act", 1);
                    jSONObject.put("duration", j3.getDuration());
                    Log.d("PushTask", "Call out mark " + string);
                }
            }
            jSONArray.put(jSONObject);
            Log.d("PushTask", String.format("put %s, catId: %s, catTitle: %s", new Object[]{string, j2 + ConfigConstant.WIRELESS_FILENAME, obj}));
        }
    }
}
