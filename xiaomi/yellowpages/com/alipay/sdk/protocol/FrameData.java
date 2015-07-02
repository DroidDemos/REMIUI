package com.alipay.sdk.protocol;

import com.alipay.sdk.data.Request;
import com.alipay.sdk.data.Response;
import org.json.JSONObject;

public class FrameData {
    private Request a;
    private Response b;
    private int c;
    private JSONObject d;

    public FrameData(Request request, Response response) {
        this.a = request;
        this.b = response;
    }

    public void a(Request request) {
        this.a = request;
    }

    public void a(Response response) {
        this.b = response;
    }

    public Request a() {
        return this.a;
    }

    public Response b() {
        return this.b;
    }

    public int c() {
        return this.c;
    }

    public void a(String str) {
        try {
            this.c = Integer.valueOf(str).intValue();
        } catch (Exception e) {
            this.c = 0;
        }
    }

    public JSONObject d() {
        return this.d;
    }

    public void a(JSONObject jSONObject) {
        this.d = jSONObject;
    }

    public void e() {
        this.a = null;
        this.b = null;
        this.d = null;
    }
}
