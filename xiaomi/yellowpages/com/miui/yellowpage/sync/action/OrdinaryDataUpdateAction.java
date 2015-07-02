package com.miui.yellowpage.sync.action;

import com.alipay.sdk.cons.MiniDefine;
import com.miui.yellowpage.sync.action.UpdateAction.Action;
import java.io.Serializable;
import org.json.JSONException;
import org.json.JSONObject;

public class OrdinaryDataUpdateAction extends UpdateAction implements Serializable {
    private long mId;

    public static UpdateAction d(JSONObject jSONObject) {
        try {
            long j = jSONObject.getLong("id");
            return new OrdinaryDataUpdateAction().k(j).a(Action.cn(jSONObject.getString(MiniDefine.i)));
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public long getId() {
        return this.mId;
    }

    public OrdinaryDataUpdateAction k(long j) {
        this.mId = j;
        return this;
    }
}
