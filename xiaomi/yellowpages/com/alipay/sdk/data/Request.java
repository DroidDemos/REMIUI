package com.alipay.sdk.data;

import android.os.Build;
import android.text.TextUtils;
import com.alipay.sdk.cons.MiniDefine;
import com.alipay.sdk.util.JsonUtils;
import com.alipay.sdk.util.LogUtils;
import java.lang.ref.WeakReference;
import org.json.JSONObject;

public class Request {
    private Envelope a;
    private JSONObject b;
    private JSONObject c;
    private long d;
    private WeakReference e;
    private boolean f;
    private boolean g;

    public void a(boolean z) {
        this.g = z;
    }

    public boolean a() {
        return this.g;
    }

    public Request(Envelope envelope, JSONObject jSONObject, InteractionData interactionData) {
        this(envelope, jSONObject, null, interactionData);
    }

    public Request(Envelope envelope, JSONObject jSONObject, JSONObject jSONObject2, InteractionData interactionData) {
        this.e = null;
        this.f = true;
        this.g = true;
        this.a = envelope;
        this.b = jSONObject;
        this.c = jSONObject2;
        this.e = new WeakReference(interactionData);
    }

    public String b() {
        return this.a.b();
    }

    public void b(boolean z) {
        this.f = z;
    }

    public long c() {
        return this.d;
    }

    public void a(long j) {
        this.d = j;
    }

    public InteractionData d() {
        return (InteractionData) this.e.get();
    }

    public void a(InteractionData interactionData) {
        this.e = new WeakReference(interactionData);
    }

    public void a(JSONObject jSONObject) {
        this.c = jSONObject;
    }

    public JSONObject a(String str) {
        JSONObject jSONObject = new JSONObject();
        try {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("device", Build.MODEL);
            JSONObject jSONObject3 = new JSONObject();
            jSONObject2 = JsonUtils.a(jSONObject2, this.c);
            jSONObject2.put(MiniDefine.aS, this.a.c());
            jSONObject2.put("api_name", this.a.a());
            jSONObject2.put("api_version", this.a.e());
            if (this.b == null) {
                this.b = new JSONObject();
            }
            this.b.put(MiniDefine.i, jSONObject3);
            Object d = this.a.d();
            if (!TextUtils.isEmpty(d)) {
                try {
                    String[] split = d.split("/");
                    jSONObject3.put(MiniDefine.m, split[1]);
                    if (split.length > 1) {
                        jSONObject3.put("method", split[2]);
                    }
                } catch (Exception e) {
                }
            }
            this.b.put("gzip", this.g);
            if (this.f) {
                jSONObject3 = new JSONObject();
                LogUtils.d("requestData before: " + this.b.toString());
                jSONObject3.put("req_data", JsonUtils.a(str, this.b.toString()));
                jSONObject2.put(MiniDefine.aM, jSONObject3);
            } else {
                jSONObject2.put(MiniDefine.aM, this.b);
            }
            jSONObject.put("data", jSONObject2);
        } catch (Object e2) {
            LogUtils.a(e2);
        }
        LogUtils.d("requestData : " + jSONObject.toString());
        return jSONObject;
    }

    public boolean e() {
        return this.f;
    }

    public String toString() {
        return this.a.toString() + ", requestData = " + JsonUtils.a(this.b, this.c) + ", timeStamp = " + this.d;
    }

    public Envelope f() {
        return this.a;
    }
}
