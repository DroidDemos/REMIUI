package com.miui.yellowpage.model;

import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: Order */
public class o {
    private String dV;
    private String mMerchantId;
    private int qS;
    private String yD;
    private int yE;
    private String yF;
    private String yG;
    private String yH;
    private String yI;
    private String yJ;
    private String yK;
    private long yL;
    private String yM;
    private String yN;

    public o(String str, String str2, int i, String str3, String str4, String str5, int i2, String str6, String str7, String str8, long j, String str9, String str10) {
        this.dV = str;
        this.yD = str2;
        this.yE = i;
        this.yG = str4;
        this.yH = str5;
        this.qS = i2;
        this.yI = str6;
        this.yJ = str7;
        this.yK = str8;
        this.yL = j;
        this.yF = str3;
        this.mMerchantId = str9;
        this.yM = str10;
    }

    public void bC(String str) {
        this.yN = str;
    }

    public String ga() {
        return this.yM;
    }

    public String getProviderName() {
        return this.yN;
    }

    public String ac() {
        return this.dV;
    }

    public String gb() {
        return this.yD;
    }

    public boolean gd() {
        return this.yE == 7;
    }

    public boolean ge() {
        return this.yE == 6;
    }

    public String gf() {
        return this.yG;
    }

    public String gg() {
        return this.yH;
    }

    public String gh() {
        return this.yI;
    }

    public String gi() {
        return this.yK;
    }

    public long gj() {
        return this.yL;
    }

    public static ArrayList bD(String str) {
        JSONArray jSONArray = new JSONArray(str);
        if (jSONArray == null || jSONArray.length() <= 0) {
            return null;
        }
        int i = 0;
        ArrayList arrayList = null;
        while (i < jSONArray.length()) {
            ArrayList arrayList2;
            JSONObject jSONObject = jSONArray.getJSONObject(i);
            String string = jSONObject.getString("oid");
            String string2 = jSONObject.getString("orderRefId");
            int i2 = jSONObject.getInt("orderType");
            String string3 = jSONObject.getString("orderTypeName");
            String string4 = jSONObject.getString("orderTitle");
            String string5 = jSONObject.getString("orderShortInfo");
            int i3 = jSONObject.getInt("orderStatus");
            String string6 = jSONObject.getString("orderStatusName");
            String string7 = jSONObject.getString("orderPrice");
            String string8 = jSONObject.getString("orderUrl");
            long j = jSONObject.getLong("orderCreateTime") * 1000;
            String string9 = jSONObject.getString("clientId");
            String string10 = jSONObject.getString("providerId");
            if (arrayList == null) {
                arrayList2 = new ArrayList();
            } else {
                arrayList2 = arrayList;
            }
            arrayList2.add(new o(string, string2, i2, string3, string4, string5, i3, string6, string7, string8, j, string9, string10));
            i++;
            arrayList = arrayList2;
        }
        return arrayList;
    }
}
