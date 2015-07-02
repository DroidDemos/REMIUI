package com.miui.yellowpage.model;

import java.io.Serializable;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ExpressCompany implements Serializable {
    private String mCode;
    private String mDetailedDescription;
    private String mHighlightInfo;
    private String mLogoUrl;
    private String mName;
    private String mPhoneNumber;
    private String mShortDescription;

    public ExpressCompany aP(String str) {
        this.mCode = str;
        return this;
    }

    public String ed() {
        return this.mCode;
    }

    public ExpressCompany aQ(String str) {
        this.mName = str;
        return this;
    }

    public String ee() {
        return this.mName;
    }

    public ExpressCompany aR(String str) {
        this.mShortDescription = str;
        return this;
    }

    public String ef() {
        return this.mShortDescription;
    }

    public ExpressCompany aS(String str) {
        this.mDetailedDescription = str;
        return this;
    }

    public String eg() {
        return this.mDetailedDescription;
    }

    public ExpressCompany aT(String str) {
        this.mHighlightInfo = str;
        return this;
    }

    public String eh() {
        return this.mHighlightInfo;
    }

    public ExpressCompany aU(String str) {
        this.mPhoneNumber = str;
        return this;
    }

    public String getPhoneNumber() {
        return this.mPhoneNumber;
    }

    public ExpressCompany aV(String str) {
        this.mLogoUrl = str;
        return this;
    }

    public String ei() {
        return this.mLogoUrl;
    }

    public static ExpressCompany h(JSONObject jSONObject) {
        try {
            if (!jSONObject.has("cargo")) {
                return new ExpressCompany();
            }
            JSONObject jSONObject2 = jSONObject.getJSONObject("cargo");
            String string = jSONObject2.getString("companyName");
            String string2 = jSONObject2.getString("companyCode");
            String optString = jSONObject2.optString("serviceInfo");
            String optString2 = jSONObject2.optString("detailInfo");
            String optString3 = jSONObject2.optString("highlight");
            String optString4 = jSONObject2.optString("servicePhone");
            return new ExpressCompany().aP(string2).aQ(string).aU(optString4).aV(jSONObject2.optString("imageUrl")).aR(optString).aS(optString2).aT(optString3);
        } catch (JSONException e) {
            e.printStackTrace();
            return new ExpressCompany();
        }
    }

    public static ArrayList b(JSONArray jSONArray) {
        ArrayList arrayList = new ArrayList();
        if (jSONArray == null || jSONArray.length() <= 0) {
            return arrayList;
        }
        for (int i = 0; i < jSONArray.length(); i++) {
            try {
                ExpressCompany h = h(jSONArray.getJSONObject(i));
                if (h != null) {
                    arrayList.add(h);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return arrayList;
    }
}
