package com.miui.yellowpage.sync.a.a;

import android.content.ContentValues;
import android.content.Context;
import android.text.TextUtils;
import com.alipay.mobilesecuritysdk.constant.ConfigConstant;
import com.alipay.sdk.cons.MiniDefine;
import com.miui.yellowpage.base.request.JSONRequest;
import com.miui.yellowpage.base.utils.HostManager;
import com.miui.yellowpage.base.utils.Log;
import com.miui.yellowpage.ui.bw;
import java.util.ArrayList;
import java.util.Iterator;
import miui.yellowpage.YellowPageContract.AntispamCategory;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: Category */
public class b extends d {
    public JSONRequest m(Context context) {
        JSONRequest jSONRequest = new JSONRequest(context, HostManager.getCategoryUpdateUrl());
        jSONRequest.addParam("version", String.valueOf(D(context)));
        return jSONRequest;
    }

    public boolean e(Context context, String str) {
        JSONArray jSONArray = new JSONArray(str);
        context.getContentResolver().delete(AntispamCategory.CONTENT_URI, "cid< ?", new String[]{String.valueOf(bw.FILE_CHOOSER_RESULT_CODE)});
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject jSONObject = jSONArray.getJSONObject(i);
            long j = jSONObject.getLong("catId");
            String D = D(jSONObject.getString("catTitle"));
            int i2 = jSONObject.getInt(MiniDefine.m);
            ContentValues contentValues = new ContentValues();
            contentValues.put("cid", Long.valueOf(j));
            contentValues.put("names", D);
            contentValues.put(MiniDefine.m, Integer.valueOf(i2));
            context.getContentResolver().insert(AntispamCategory.CONTENT_URI, contentValues);
            Log.d("PullTask", "input category: " + j);
        }
        e(context, this.mVersion);
        return false;
    }

    protected String n(Context context) {
        return "antispam_category";
    }

    private String D(String str) {
        String str2 = ConfigConstant.WIRELESS_FILENAME;
        try {
            JSONObject jSONObject = new JSONObject(str);
            Iterable arrayList = new ArrayList();
            Iterator keys = jSONObject.keys();
            while (keys.hasNext()) {
                String str3 = (String) keys.next();
                arrayList.add(str3 + ":" + jSONObject.getString(str3));
            }
            return TextUtils.join(";", arrayList);
        } catch (JSONException e) {
            e.printStackTrace();
            return str2;
        }
    }
}
