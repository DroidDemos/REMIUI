package com.miui.yellowpage.sync.action;

import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: UpdateAction */
public class a extends b {
    public UpdateAction i(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            UpdateAction updateAction = new UpdateAction();
            b.a(updateAction, jSONObject);
            return updateAction;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
