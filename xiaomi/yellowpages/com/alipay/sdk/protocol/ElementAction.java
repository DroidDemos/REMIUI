package com.alipay.sdk.protocol;

import android.text.TextUtils;
import com.alipay.mobilesecuritysdk.constant.ConfigConstant;
import com.alipay.sdk.cons.GlobalConstants;
import com.alipay.sdk.cons.MiniDefine;
import org.json.JSONObject;

public class ElementAction {
    private String a;
    private String b;
    private String c;
    private String d;
    private String e;
    private boolean f;
    private boolean g;
    private boolean h;
    private String i;
    private String j;
    private String k;
    private JSONObject l;

    public ElementAction(String str) {
        this.g = true;
        this.h = true;
        this.a = str;
    }

    public JSONObject a() {
        return this.l;
    }

    public String b() {
        return this.k;
    }

    public String c() {
        return this.i;
    }

    public String d() {
        return this.j;
    }

    public static ElementAction a(JSONObject jSONObject) {
        String str;
        String str2;
        String str3;
        String str4;
        boolean z;
        String str5 = null;
        boolean z2 = true;
        if (jSONObject == null || !jSONObject.has(MiniDefine.l)) {
            str = null;
        } else {
            str = jSONObject.optString(MiniDefine.l);
        }
        if (jSONObject == null || !jSONObject.has(MiniDefine.aL)) {
            str2 = null;
        } else {
            str2 = jSONObject.optString(MiniDefine.aL);
        }
        if (jSONObject == null || !jSONObject.has(MiniDefine.aM)) {
            str3 = null;
        } else {
            str3 = jSONObject.optString(MiniDefine.aM);
        }
        if (jSONObject == null || !jSONObject.has(MiniDefine.aN)) {
            str4 = null;
        } else {
            str4 = jSONObject.optString(MiniDefine.aN);
        }
        if (jSONObject != null && jSONObject.has(MiniDefine.aO)) {
            str5 = jSONObject.optString(MiniDefine.aO);
        }
        if (jSONObject == null || !jSONObject.has(MiniDefine.aP)) {
            z = true;
        } else {
            z = jSONObject.optBoolean(MiniDefine.aP, true);
        }
        boolean z3 = (jSONObject == null || !jSONObject.has(MiniDefine.aQ)) ? true : !jSONObject.optBoolean(MiniDefine.aQ);
        if (jSONObject != null && jSONObject.has(MiniDefine.aR)) {
            z2 = jSONObject.optBoolean(MiniDefine.aR);
        }
        String str6 = ConfigConstant.WIRELESS_FILENAME;
        if (jSONObject != null && jSONObject.has(MiniDefine.aS)) {
            str6 = jSONObject.optString(MiniDefine.aS);
        }
        String str7 = ConfigConstant.WIRELESS_FILENAME;
        if (jSONObject != null && jSONObject.has(MiniDefine.aT)) {
            str7 = jSONObject.optString(MiniDefine.aT);
        }
        String str8 = ConfigConstant.WIRELESS_FILENAME;
        if (jSONObject != null && jSONObject.has(MiniDefine.aU)) {
            str8 = jSONObject.optString(MiniDefine.aU);
        }
        return a(str, str2, str3, str4, str5, z, z3, z2, str6, str7, str8, jSONObject);
    }

    public static ElementAction a(JSONObject jSONObject, String str) {
        return a(jSONObject.optJSONObject(str));
    }

    public static ElementAction a(String str, String str2, String str3, String str4, String str5, boolean z, boolean z2, boolean z3, String str6, String str7, String str8, JSONObject jSONObject) {
        String str9 = null;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        ElementAction elementAction = new ElementAction(str);
        elementAction.a = str;
        if (!TextUtils.isEmpty(str2)) {
            str9 = str2.trim();
        }
        elementAction.b = str9;
        elementAction.c = str3;
        elementAction.d = str4;
        elementAction.e = str5;
        elementAction.f = z;
        elementAction.g = z2;
        elementAction.h = z3;
        elementAction.i = str6;
        elementAction.j = str7;
        elementAction.k = str8;
        elementAction.l = jSONObject;
        return elementAction;
    }

    public static ElementAction a(String str, ActionType actionType) {
        return a(str, actionType.f(), actionType.m(), actionType.h(), actionType.i(), actionType.j(), actionType.k(), actionType.l(), actionType.c(), actionType.d(), actionType.b(), actionType.a());
    }

    public String e() {
        return this.a;
    }

    public String f() {
        if (TextUtils.isEmpty(this.b)) {
            this.b = GlobalConstants.b;
        }
        return this.b;
    }

    public String g() {
        return this.c;
    }

    public JSONObject h() {
        try {
            return new JSONObject(this.c);
        } catch (Exception e) {
            return null;
        }
    }

    public String i() {
        return this.d;
    }

    public String j() {
        return this.e;
    }

    public boolean k() {
        return this.f;
    }

    public boolean l() {
        return this.g;
    }

    public boolean m() {
        return this.h;
    }
}
