package com.xiaomi.c;

import com.alipay.sdk.cons.MiniDefine;
import org.json.JSONObject;

public class k {
    private int a;
    private long b;
    private String d;
    private long e;
    private long vG;

    public k() {
        this(0, 0, 0, null);
    }

    public k(int i, long j, long j2, Exception exception) {
        this.a = i;
        this.b = j;
        this.e = j2;
        this.vG = System.currentTimeMillis();
        if (exception != null) {
            this.d = exception.getClass().getSimpleName();
        }
    }

    public int a() {
        return this.a;
    }

    public JSONObject ai() {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("cost", this.b);
        jSONObject.put(MiniDefine.q, this.e);
        jSONObject.put("ts", this.vG);
        jSONObject.put("wt", this.a);
        jSONObject.put("expt", this.d);
        return jSONObject;
    }

    public long b() {
        return this.b;
    }

    public long bW() {
        return this.e;
    }

    public long c() {
        return this.vG;
    }

    public String e() {
        return this.d;
    }

    public k l(JSONObject jSONObject) {
        this.b = jSONObject.getLong("cost");
        this.e = jSONObject.getLong(MiniDefine.q);
        this.vG = jSONObject.getLong("ts");
        this.a = jSONObject.getInt("wt");
        this.d = jSONObject.optString("expt");
        return this;
    }
}
