package com.miui.yellowpage.sync.a.a;

import android.content.Context;
import com.alipay.sdk.cons.MiniDefine;
import com.miui.yellowpage.base.request.JSONRequest;
import com.miui.yellowpage.base.utils.HostManager;
import com.miui.yellowpage.utils.F;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: Ivr */
public class h extends d {
    protected JSONRequest m(Context context) {
        JSONRequest jSONRequest = new JSONRequest(context, HostManager.getIvrDataUrl());
        jSONRequest.addParam("version", String.valueOf(D(context)));
        return jSONRequest;
    }

    protected String n(Context context) {
        return "ivr";
    }

    public boolean e(Context context, String str) {
        JSONObject jSONObject = new JSONObject(str);
        long j = 0;
        boolean z = jSONObject.getBoolean("has_more");
        JSONArray jSONArray = jSONObject.getJSONArray("ivr_data");
        for (int i = 0; i < jSONArray.length(); i++) {
            Object obj;
            JSONObject jSONObject2 = jSONArray.getJSONObject(i);
            long j2 = jSONObject2.getLong("version");
            if (jSONObject2.getInt(MiniDefine.b) == 0) {
                obj = 1;
            } else {
                obj = null;
            }
            String string = jSONObject2.getString("phone");
            if (obj != null) {
                F.E(context, string);
            } else if (F.F(context, string)) {
                F.g(context, string, jSONObject2.getString("data"));
            } else {
                F.f(context, string, jSONObject2.getString("data"));
            }
            if (j2 > j) {
                j = j2;
            }
        }
        e(context, j);
        return z;
    }
}
