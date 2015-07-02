package com.alipay.sdk.data;

import android.text.TextUtils;
import com.alipay.mobilesecuritysdk.constant.ConfigConstant;
import com.alipay.mobilesecuritysdk.deviceID.Profile;
import com.alipay.sdk.cons.GlobalDefine;
import org.apache.http.Header;
import org.json.JSONObject;

public class Response {
    public static final int a = 1000;
    public static final int b = 503;
    public static final int c = 0;
    Envelope d;
    Header[] e;
    private int f;
    private String g;
    private long h;
    private String i;
    private String j;
    private String k;
    private JSONObject l;
    private String m;
    private boolean n;
    private boolean o;

    public Response() {
        this.f = 0;
        this.g = ConfigConstant.WIRELESS_FILENAME;
        this.h = 0;
        this.i = ConfigConstant.WIRELESS_FILENAME;
        this.j = null;
        this.k = null;
        this.l = null;
        this.n = true;
        this.o = true;
        this.d = null;
        this.e = null;
    }

    public Envelope a() {
        return this.d;
    }

    public void a(boolean z) {
        this.o = z;
    }

    public boolean b() {
        return this.o;
    }

    public void b(boolean z) {
        this.n = z;
    }

    public boolean c() {
        return this.n;
    }

    public JSONObject d() {
        return this.l;
    }

    public long e() {
        return this.h;
    }

    public boolean f() {
        return !TextUtils.equals(this.i, Profile.devicever);
    }

    public String g() {
        return o();
    }

    public String h() {
        return this.m;
    }

    public int i() {
        return this.f;
    }

    public void a(int i) {
        this.f = i;
    }

    public String j() {
        return this.g;
    }

    public void a(String str) {
        this.g = str;
    }

    public String k() {
        return this.i;
    }

    public void b(String str) {
        this.i = str;
    }

    public String l() {
        return this.j;
    }

    public void c(String str) {
        this.j = str;
    }

    public String m() {
        return this.k;
    }

    public void d(String str) {
        this.k = str;
    }

    public Header[] n() {
        return this.e;
    }

    public void a(Header[] headerArr) {
        this.e = headerArr;
    }

    public void a(long j) {
        this.h = j;
    }

    public void a(JSONObject jSONObject) {
        this.l = jSONObject;
    }

    public void e(String str) {
        this.m = str;
    }

    public void a(Envelope envelope) {
        this.d = envelope;
    }

    private String o() {
        String str = ConfigConstant.WIRELESS_FILENAME;
        try {
            str = (((("resultStatus={" + this.i + "}") + ";") + "memo={" + this.k + "}") + ";") + "result={" + this.j + "}";
            if (!this.j.contains("success=\"true\"")) {
                return str;
            }
            int indexOf = this.j.indexOf(GlobalDefine.j);
            if (indexOf == -1) {
                return str;
            }
            indexOf = this.j.indexOf("\"", indexOf) + 1;
            int indexOf2 = this.j.indexOf("\"", indexOf);
            if (indexOf <= 0 || indexOf2 <= indexOf) {
                return str;
            }
            return str + ";callBackUrl={" + this.j.substring(indexOf, indexOf2) + "}";
        } catch (Exception e) {
            Exception exception = e;
            str = this.j;
            exception.printStackTrace();
            return str;
        }
    }

    public String toString() {
        String str = this.d.toString() + ", code = " + this.f + ", errorMsg = " + this.g + ", timeStamp = " + this.h + ", endCode = " + this.i;
        if (this.l != null) {
            return str + ", reflectedData = " + this.l;
        }
        return str;
    }
}
