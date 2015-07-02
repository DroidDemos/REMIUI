package com.miui.yellowpage.activity;

import android.text.TextUtils;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: BaseActivity */
class aa {
    long mTimestamp;
    String mUrl;
    boolean vn;
    long vo;

    public aa() {
        this.vn = false;
        this.vo = 0;
        this.mUrl = null;
    }

    public boolean fb() {
        return this.vn;
    }

    public boolean fc() {
        return System.currentTimeMillis() < this.mTimestamp + (this.vo * 1000);
    }

    public String getUrl() {
        return this.mUrl;
    }

    public static aa bl(String str) {
        if (!TextUtils.isEmpty(str)) {
            try {
                return k(new JSONObject(str));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return new aa();
    }

    public static aa k(JSONObject jSONObject) {
        boolean z = true;
        aa aaVar = new aa();
        if (jSONObject != null) {
            try {
                if (jSONObject.getInt("showBanner") != 1) {
                    z = false;
                }
                long j = jSONObject.getLong("nextQueryInterval");
                long optLong = jSONObject.optLong("timestamp", System.currentTimeMillis());
                String optString = jSONObject.optString("url");
                aaVar.vo = j;
                aaVar.vn = z;
                aaVar.mUrl = optString;
                aaVar.mTimestamp = optLong;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return aaVar;
    }

    public String toString() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("showBanner", this.vn ? 1 : 0);
            jSONObject.put("nextQueryInterval", this.vo);
            jSONObject.put("url", this.mUrl);
            jSONObject.put("timestamp", this.mTimestamp);
            return jSONObject.toString();
        } catch (JSONException e) {
            e.printStackTrace();
            return "{}";
        }
    }
}
