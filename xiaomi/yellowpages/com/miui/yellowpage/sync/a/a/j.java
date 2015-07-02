package com.miui.yellowpage.sync.a.a;

import android.content.ContentValues;
import android.content.Context;
import com.alipay.sdk.cons.MiniDefine;
import com.miui.yellowpage.base.request.JSONRequest;
import com.miui.yellowpage.base.utils.HostManager;
import miui.yellowpage.YellowPageContract.AntispamWhiteList;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: WhiteList */
public class j extends d {
    protected JSONRequest m(Context context) {
        long D = D(context);
        JSONRequest jSONRequest = new JSONRequest(context, HostManager.getAntispamWhiteListUrl());
        jSONRequest.addParam("version", String.valueOf(D));
        jSONRequest.setDecryptDownloadData(false);
        return jSONRequest;
    }

    public boolean e(Context context, String str) {
        JSONArray jSONArray = new JSONArray(str);
        if (jSONArray != null && jSONArray.length() > 0) {
            for (int i = 0; i < jSONArray.length(); i++) {
                int i2;
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                if (jSONObject.getInt(MiniDefine.b) == 0) {
                    i2 = 1;
                } else {
                    boolean z = false;
                }
                String string = jSONObject.getString("number");
                if (i2 != 0) {
                    context.getContentResolver().delete(AntispamWhiteList.CONTNET_URI, "number = ?", new String[]{string});
                } else {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("number", string);
                    context.getContentResolver().insert(AntispamWhiteList.CONTNET_URI, contentValues);
                }
            }
        }
        e(context, this.mVersion);
        return false;
    }

    protected String n(Context context) {
        return "antispam_white_list";
    }
}
