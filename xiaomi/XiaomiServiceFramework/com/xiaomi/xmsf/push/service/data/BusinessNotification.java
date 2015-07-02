package com.xiaomi.xmsf.push.service.data;

import android.os.Bundle;
import com.xiaomi.xmsf.push.service.Constants;
import org.json.JSONException;
import org.json.JSONObject;

public class BusinessNotification extends BusinessMessage {
    public String actionText;
    public String actionUrl;
    public String imgUrl;
    public String priText;
    public String secText;
    public String titText;
    public String type;

    public BusinessNotification(JSONObject adsCellJson) {
        super(adsCellJson);
        this.actionUrl = adsCellJson.optString(Constants.JSON_TAG_ACTION_URL);
        this.imgUrl = adsCellJson.optString(Constants.JSON_TAG_IMAURL);
        this.titText = adsCellJson.optString(Constants.JSON_TAG_TITTEXT);
        this.priText = adsCellJson.optString(Constants.JSON_TAG_PRITEXT);
        this.secText = adsCellJson.optString(Constants.JSON_TAG_SECTEXT);
        this.type = adsCellJson.optString(Constants.JSON_TAG_ADSTYPE);
        this.actionText = adsCellJson.optString(Constants.JSON_TAG_ACTIONTEXT);
    }

    public JSONObject toJSON() {
        JSONObject ret = super.toJSON();
        try {
            ret.put(Constants.JSON_TAG_ACTION_URL, this.actionUrl);
            ret.put(Constants.JSON_TAG_IMAURL, this.imgUrl);
            ret.put(Constants.JSON_TAG_TITTEXT, this.titText);
            ret.put(Constants.JSON_TAG_PRITEXT, this.priText);
            ret.put(Constants.JSON_TAG_SECTEXT, this.secText);
            ret.put(Constants.JSON_TAG_ADSTYPE, this.type);
            ret.put(Constants.JSON_TAG_ACTIONTEXT, this.actionText);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return ret;
    }

    public Bundle toBundle() {
        Bundle bundle = super.toBundle();
        bundle.putString(Constants.JSON_TAG_ACTION_URL, this.actionUrl);
        bundle.putString(Constants.JSON_TAG_IMAURL, this.imgUrl);
        bundle.putString(Constants.JSON_TAG_TITTEXT, this.titText);
        bundle.putString(Constants.JSON_TAG_PRITEXT, this.priText);
        bundle.putString(Constants.JSON_TAG_SECTEXT, this.secText);
        bundle.putString(Constants.JSON_TAG_ADSTYPE, this.type);
        bundle.putString(Constants.JSON_TAG_ACTIONTEXT, this.actionText);
        return bundle;
    }
}
