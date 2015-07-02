package com.miui.yellowpage.model;

import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: InquiryHistoryDataEntry */
public class g {
    private String dg;
    private String dh;
    private String iS;
    private String iT;
    private int iU;
    private int mState;
    private long mTimestamp;

    public g(String str, String str2, String str3, int i, int i2) {
        this.dg = str;
        this.dh = str2;
        this.iS = str3;
        this.mState = i2;
        this.iU = i;
        this.mTimestamp = System.currentTimeMillis();
    }

    public String aP() {
        return this.dg;
    }

    public String aQ() {
        return this.dh;
    }

    public String getSerialNumber() {
        return this.iS;
    }

    public String getKey() {
        return "inquiry_history_item_" + this.dg + "_" + this.iS;
    }

    public boolean aR() {
        return this.mState == 3;
    }

    public String aS() {
        return this.iT;
    }

    public void J(String str) {
        this.iT = str;
    }

    public String getContent() {
        String str = "{}";
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("bizCode", this.dg);
            jSONObject.put("logistics_name", this.dh);
            jSONObject.put("hotCatId", this.iU);
            jSONObject.put("order", this.iS);
            jSONObject.put("state_nu", this.mState);
            str = jSONObject.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return str;
    }

    public String getTag() {
        return String.valueOf(this.mTimestamp);
    }

    public static g K(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            return new g(jSONObject.getString("bizCode"), jSONObject.optString("logistics_name"), jSONObject.getString("order"), jSONObject.getInt("hotCatId"), jSONObject.optInt("state_nu"));
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
