package com.miui.yellowpage.sync.action;

import com.alipay.sdk.cons.MiniDefine;
import com.miui.yellowpage.sync.action.UpdateAction.Type;
import org.json.JSONObject;

/* compiled from: UpdateAction */
public abstract class b {
    public abstract UpdateAction i(String str);

    protected static void a(UpdateAction updateAction, JSONObject jSONObject) {
        updateAction.a(Type.F(jSONObject.getString(MiniDefine.m)));
        updateAction.q(jSONObject.optBoolean("forced", false));
        updateAction.p(jSONObject.optBoolean("buildin", false));
        updateAction.o(jSONObject.optBoolean("wifiOnly", true));
    }
}
