package com.miui.yellowpage.model;

import com.alipay.sdk.cons.MiniDefine;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: PrepaidRechargeInfo */
public class b {
    private String dD;
    private String dE;
    private String mMin;
    private String mName;

    public b(String str, String str2, String str3, String str4) {
        this.mName = str;
        this.dD = str2;
        this.mMin = str3;
        this.dE = str4;
    }

    public String getName() {
        return this.mName;
    }

    public String Y() {
        return this.dD;
    }

    public String Z() {
        return this.dE;
    }

    public String aa() {
        return this.mMin;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof b)) {
            return false;
        }
        b bVar = (b) obj;
        if (this.mName.equals(bVar.mName) && this.dD.equals(bVar.dD) && this.mMin.equals(bVar.mMin) && this.dE.equals(bVar.dE)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return ((((((this.mName.hashCode() + 527) * 31) + this.dD.hashCode()) * 31) + this.mMin.hashCode()) * 31) + this.dE.hashCode();
    }

    public static ArrayList l(String str) {
        JSONArray jSONArray = new JSONArray(str);
        if (jSONArray == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            if (!jSONArray.isNull(i)) {
                String string;
                String string2;
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                String string3 = jSONObject.getString(MiniDefine.l);
                String string4 = jSONObject.getString("prevalue");
                JSONObject jSONObject2 = jSONObject.getJSONObject("sellprice");
                if (jSONObject2 != null) {
                    string = jSONObject2.getString("min");
                    string2 = jSONObject2.getString("max");
                } else {
                    string = null;
                    string2 = null;
                }
                arrayList.add(new b(string3, string4, string, string2));
            }
        }
        return arrayList;
    }
}
