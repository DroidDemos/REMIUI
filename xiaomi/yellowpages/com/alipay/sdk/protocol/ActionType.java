package com.alipay.sdk.protocol;

import android.text.TextUtils;
import com.alipay.sdk.cons.MiniDefine;
import org.json.JSONObject;

public enum ActionType {
    WapPay("js://wappay"),
    MspApp("js://msp_app"),
    DownLoad("js://download"),
    Submit(MiniDefine.bj),
    Confirm("js://confirm"),
    Alert("js://alert"),
    Update("js://update"),
    Exit("js://exit"),
    Alipay("js://alipay");
    
    private String j;
    private String k;
    private String l;
    private JSONObject m;
    private String n;
    private String o;
    private String p;
    private boolean q;
    private boolean r;
    private boolean s;
    private String t;
    private String u;
    private String v;
    private JSONObject w;

    private ActionType(String str) {
        this.j = str;
    }

    public JSONObject a() {
        return this.w;
    }

    public String b() {
        return this.v;
    }

    public String c() {
        return this.t;
    }

    public String d() {
        return this.u;
    }

    public String e() {
        return this.k;
    }

    public String f() {
        return this.l;
    }

    public JSONObject g() {
        return this.m;
    }

    public String h() {
        return this.o;
    }

    public String i() {
        return this.p;
    }

    public boolean j() {
        return this.q;
    }

    public boolean k() {
        return this.r;
    }

    public boolean l() {
        return this.s;
    }

    public String m() {
        return this.n;
    }

    private static String[] a(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return str.split(";");
    }

    public static ActionType[] a(ElementAction elementAction) {
        if (elementAction != null) {
            String[] a = a(elementAction.e());
            if (a == null) {
                return new ActionType[]{Submit};
            }
            ActionType[] actionTypeArr = new ActionType[a.length];
            int length = a.length;
            int i = 0;
            int i2 = 0;
            while (i < length) {
                String str = a[i];
                ActionType actionType = Submit;
                for (ActionType actionType2 : values()) {
                    if (str.startsWith(actionType2.j)) {
                        break;
                    }
                }
                ActionType actionType22 = actionType;
                actionType22.k = str;
                actionType22.l = elementAction.f();
                actionType22.m = elementAction.h();
                actionType22.n = elementAction.g();
                actionType22.o = elementAction.i();
                actionType22.p = elementAction.j();
                actionType22.q = elementAction.k();
                actionType22.r = elementAction.l();
                actionType22.s = elementAction.m();
                actionType22.t = elementAction.c();
                actionType22.u = elementAction.d();
                actionType22.v = elementAction.b();
                actionType22.w = elementAction.a();
                actionTypeArr[i2] = actionType22;
                i++;
                i2++;
            }
            return actionTypeArr;
        }
        return new ActionType[]{Submit};
    }
}
