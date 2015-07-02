package com.miui.yellowpage.model;

import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: RechargeOrderDataEntry */
public class c {
    private final String dU;
    private final String dV;
    private final String dW;
    private final String dX;
    private final int dY;
    private final long dZ;
    private final String mPhoneNumber;

    public c(String str, long j, String str2, String str3, String str4, String str5) {
        this.mPhoneNumber = str;
        this.dZ = j;
        this.dU = str2;
        this.dV = str3;
        this.dW = str4;
        this.dX = str5;
        if (this.dZ == 1) {
            this.dY = 1;
        } else {
            this.dY = 0;
        }
    }

    public String getPhoneNumber() {
        return this.mPhoneNumber;
    }

    public String ab() {
        return this.dU;
    }

    public String ac() {
        return this.dV;
    }

    public String ad() {
        return this.dW;
    }

    public String ae() {
        return this.dX;
    }

    public int af() {
        return this.dY;
    }

    public static ArrayList m(String str) {
        ArrayList arrayList = new ArrayList();
        try {
            JSONArray jSONArray = new JSONArray(str);
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                arrayList.add(new c(jSONObject.getString("recharge_name"), jSONObject.getLong("order_status"), jSONObject.getString("order_status_info"), jSONObject.getString("order_id"), jSONObject.getString("originalprice"), jSONObject.getString("sellprice")));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return arrayList;
    }

    public String toString() {
        return String.format("[phone: %s, status: %s, price: %s]", new Object[]{this.mPhoneNumber, this.dU, this.dX});
    }
}
