package com.xiaomi.xmsf.push.service.data;

import android.os.Bundle;
import com.xiaomi.xmsf.push.service.Constants;
import org.json.JSONException;
import org.json.JSONObject;

public class BusinessMessage implements Cloneable {
    public String adsJsonString;
    public String downloadedImgPath;
    public int failedCount;
    public long id;
    public long lastShowTime;
    public int multi;
    public int nonsense;
    public int receiveUpperBound;
    public int showType;

    public BusinessMessage(JSONObject adsCellJson) {
        this.id = adsCellJson.optLong(Constants.JSON_TAG_ID);
        this.showType = adsCellJson.optInt(Constants.JSON_TAG_SHOWTYPE);
        this.nonsense = adsCellJson.optInt(Constants.JSON_TAG_NONSENSE);
        this.receiveUpperBound = adsCellJson.optInt(Constants.JSON_TAG_UPPERBOUND);
        this.lastShowTime = adsCellJson.optLong(Constants.JSON_TAG_LASTSHOWTIME);
        this.downloadedImgPath = adsCellJson.optString(Constants.JSON_TAG_DOWNLOAD_IMG_PATH);
    }

    public BusinessMessage clone() {
        try {
            return (BusinessMessage) super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    public JSONObject toJSON() {
        JSONObject ret = new JSONObject();
        try {
            ret.put(Constants.JSON_TAG_ID, this.id);
            ret.put(Constants.JSON_TAG_SHOWTYPE, this.showType);
            ret.put(Constants.JSON_TAG_NONSENSE, this.nonsense);
            ret.put(Constants.JSON_TAG_UPPERBOUND, this.receiveUpperBound);
            ret.put(Constants.JSON_TAG_LASTSHOWTIME, this.lastShowTime);
            ret.put(Constants.JSON_TAG_DOWNLOAD_IMG_PATH, this.downloadedImgPath);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return ret;
    }

    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putLong(Constants.JSON_TAG_ID, this.id);
        bundle.putInt(Constants.JSON_TAG_SHOWTYPE, this.showType);
        bundle.putInt(Constants.JSON_TAG_NONSENSE, this.nonsense);
        bundle.putInt(Constants.JSON_TAG_UPPERBOUND, this.receiveUpperBound);
        bundle.putLong(Constants.JSON_TAG_LASTSHOWTIME, this.lastShowTime);
        bundle.putString(Constants.JSON_TAG_DOWNLOAD_IMG_PATH, this.downloadedImgPath);
        return bundle;
    }
}
