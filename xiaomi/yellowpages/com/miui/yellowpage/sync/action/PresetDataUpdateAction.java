package com.miui.yellowpage.sync.action;

import android.text.TextUtils;
import com.alipay.sdk.cons.MiniDefine;
import com.miui.yellowpage.sync.action.UpdateAction.Action;
import java.io.Serializable;
import org.json.JSONException;
import org.json.JSONObject;

public class PresetDataUpdateAction extends UpdateAction implements Serializable {
    private String mData;
    private long mVersion;

    public static UpdateAction d(JSONObject jSONObject) {
        try {
            return new PresetDataUpdateAction().cq(jSONObject.optString("data")).r(jSONObject.getLong("version")).q(jSONObject.getBoolean("forced")).a(Action.cn(jSONObject.getString(MiniDefine.i)));
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public long iz() {
        return this.mVersion;
    }

    public PresetDataUpdateAction r(long j) {
        this.mVersion = j;
        return this;
    }

    public String getData() {
        return this.mData;
    }

    public boolean cH() {
        return super.cH() || !TextUtils.isEmpty(this.mData);
    }

    public PresetDataUpdateAction cq(String str) {
        this.mData = str;
        return this;
    }
}
