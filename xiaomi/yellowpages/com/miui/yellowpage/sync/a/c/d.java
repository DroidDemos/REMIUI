package com.miui.yellowpage.sync.a.c;

import android.content.Context;
import com.alipay.sdk.cons.GlobalDefine;
import com.miui.yellowpage.base.request.JSONRequest;
import com.miui.yellowpage.base.utils.HostManager;
import com.miui.yellowpage.base.utils.Log;
import com.miui.yellowpage.model.e;
import com.miui.yellowpage.model.x;
import com.miui.yellowpage.utils.o;
import miui.yellowpage.YellowPagePhone;
import miui.yellowpage.YellowPageUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: DailyHitInfo */
public class d extends c {
    public void E(Context context) {
        JSONObject a;
        String userAreaCode = YellowPageUtils.getUserAreaCode(context);
        JSONArray jSONArray = new JSONArray();
        Object<x> J = o.J(context);
        if (!com.miui.yellowpage.utils.x.a(J)) {
            for (x xVar : J) {
                a = a(context, xVar.getNumber(), xVar.iF() ? 0 : 1, xVar.getDate(), userAreaCode);
                if (a != null) {
                    jSONArray.put(a);
                }
            }
            Log.d("PushTask", jSONArray.length() + " new call record");
        }
        Object<e> K = o.K(context);
        if (!com.miui.yellowpage.utils.x.a(K)) {
            for (e eVar : K) {
                a = a(context, eVar.getAddress(), eVar.aL() ? 2 : 3, eVar.getDate(), userAreaCode);
                if (a != null) {
                    jSONArray.put(a);
                }
            }
            Log.d("PushTask", jSONArray.length() + " new sms and call record");
        }
        if (jSONArray.length() > 0) {
            JSONRequest jSONRequest = new JSONRequest(context, HostManager.getHitStatisticUrl());
            jSONRequest.setDecryptDownloadData(false);
            jSONRequest.addParam("data", jSONArray.toString());
            jSONRequest.setHttpMethod("POST");
            Log.d("PushTask", "pushDailyHitInfoToCloud status " + jSONRequest.getStatus());
        }
    }

    private JSONObject a(Context context, String str, int i, long j, String str2) {
        String normalizedNumber = YellowPageUtils.getNormalizedNumber(context, str);
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("phone", normalizedNumber);
            jSONObject.put("raw_phone", str);
            jSONObject.put("act", i);
            jSONObject.put("actTime", j);
            jSONObject.put("areaCode", str2);
            YellowPagePhone phoneInfo = YellowPageUtils.getPhoneInfo(context, str, false);
            if (phoneInfo != null) {
                jSONObject.put(GlobalDefine.g, 1);
                if (!phoneInfo.isYellowPage()) {
                    return jSONObject;
                }
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("sid", phoneInfo.getYellowPageId());
                jSONObject2.put("provider", phoneInfo.getProviderId());
                jSONObject.put("resultInfo", jSONObject2);
                return jSONObject;
            }
            jSONObject.put(GlobalDefine.g, 0);
            return jSONObject;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
