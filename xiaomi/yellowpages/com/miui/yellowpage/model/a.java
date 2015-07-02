package com.miui.yellowpage.model;

import com.alipay.sdk.cons.MiniDefine;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ExpressProgressDataEntry */
public class a {
    private String bC;
    private String bD;
    private boolean bE;
    private boolean bF;

    public a(String str, String str2, boolean z, boolean z2) {
        this.bC = str;
        this.bD = str2;
        this.bE = z;
        this.bF = z2;
    }

    public String getContext() {
        return this.bC;
    }

    public String getTime() {
        return this.bD;
    }

    public boolean isFirst() {
        return this.bE;
    }

    public boolean isLast() {
        return this.bF;
    }

    public static ArrayList a(JSONArray jSONArray) {
        ArrayList arrayList = new ArrayList();
        if (jSONArray != null) {
            int i = 0;
            while (i < jSONArray.length()) {
                try {
                    boolean z;
                    boolean z2;
                    JSONObject jSONObject = jSONArray.getJSONObject(i);
                    if (i == 0) {
                        z = true;
                    } else {
                        z = false;
                    }
                    if (i == jSONArray.length() - 1) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    arrayList.add(new a(jSONObject.getString("context"), jSONObject.getString(MiniDefine.E), z, z2));
                    i++;
                } catch (JSONException e) {
                    e.printStackTrace();
                    arrayList.clear();
                }
            }
        }
        return arrayList;
    }
}
