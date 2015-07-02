package com.miui.yellowpage.model;

import org.json.JSONObject;

/* compiled from: Recharge */
public class j {
    private String dV;
    private String qM;
    private String qN;
    private double qO;
    private double qP;
    private String qQ;
    private long qR;
    private int qS;

    public j(String str, String str2, double d, double d2, String str3, String str4, long j, int i) {
        this.qM = str;
        this.qN = str2;
        this.qO = d2;
        this.qQ = str3;
        this.dV = str4;
        this.qR = j;
        this.qS = i;
        this.qP = d;
    }

    public String df() {
        return this.qM;
    }

    public String dg() {
        return this.qN;
    }

    public String dh() {
        return this.qO != Math.rint(this.qO) ? String.valueOf(this.qO) : String.valueOf((int) this.qO);
    }

    public String di() {
        return this.qP != Math.rint(this.qP) ? String.valueOf(this.qP) : String.valueOf((int) this.qP);
    }

    public String dj() {
        return this.qQ;
    }

    public String ac() {
        return this.dV;
    }

    public long dk() {
        return this.qR;
    }

    public int dl() {
        return this.qS;
    }

    public boolean dm() {
        return this.qS == 1;
    }

    public static j f(JSONObject jSONObject) {
        if (jSONObject != null) {
            return new j(jSONObject.getString("product_name"), jSONObject.getString("recharge_name"), jSONObject.optDouble("originalprice"), jSONObject.optDouble("sellprice"), jSONObject.getString("order_status_info"), jSONObject.getString("order_id"), jSONObject.getLong("add_time") * 1000, jSONObject.getInt("order_status"));
        }
        return null;
    }
}
