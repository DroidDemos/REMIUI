package com.miui.yellowpage.utils;

import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: Antispam */
public class t {
    private String mUrl;
    private long uA;
    private boolean uu;
    private long uv;
    private long uw;
    private String ux;
    private String uy;
    private String uz;

    public boolean eE() {
        return this.uu;
    }

    public long eF() {
        return this.uv;
    }

    public long eG() {
        return this.uw;
    }

    public String eH() {
        return this.uy;
    }

    public String eI() {
        return this.uz;
    }

    public String getUrl() {
        return this.mUrl;
    }

    public t(boolean z, long j, long j2, String str, String str2, String str3, String str4, long j3) {
        this.uu = z;
        this.uv = j;
        this.uw = j2;
        this.ux = str;
        this.uy = str2;
        this.uz = str3;
        this.mUrl = str4;
        this.uA = j3;
    }

    public static t i(JSONObject jSONObject) {
        try {
            int i = jSONObject.getInt("patchType");
            int i2 = jSONObject.getInt("oldVersion");
            int i3 = jSONObject.getInt("newVersion");
            return new t(i == 0, (long) i2, (long) i3, jSONObject.optString("oldMd5Sum"), jSONObject.optString("newMd5Sum"), jSONObject.optString("md5Sum"), jSONObject.getString("fileURL"), jSONObject.getLong("fileSize"));
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
