package com.miui.yellowpage.model;

import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: WebBuildFile */
public class k {
    private String rh;
    private String ri;
    private String rj;

    public k(String str, String str2, String str3) {
        this.rh = str;
        this.ri = str2;
        this.rj = str3;
    }

    public String getSha1() {
        return this.rh;
    }

    public String dn() {
        return this.ri;
    }

    public String do() {
        return this.rj;
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof k)) {
            return false;
        }
        return TextUtils.equals(((k) obj).rh, this.rh);
    }

    public int hashCode() {
        return this.rh.hashCode();
    }

    public static List aF(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        List arrayList = new ArrayList();
        try {
            JSONObject jSONObject = new JSONObject(str);
            Iterator keys = jSONObject.keys();
            while (keys.hasNext()) {
                String str2 = (String) keys.next();
                JSONObject jSONObject2 = jSONObject.getJSONObject(str2);
                arrayList.add(new k(jSONObject2.getString("hash"), jSONObject2.getString("url"), str2));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return arrayList;
    }
}
