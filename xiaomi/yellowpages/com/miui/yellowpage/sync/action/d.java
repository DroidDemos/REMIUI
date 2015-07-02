package com.miui.yellowpage.sync.action;

import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: UpdateAction */
public class d extends b {
    public UpdateAction i(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            UpdateAction d = PresetDataUpdateAction.d(jSONObject);
            b.a(d, jSONObject);
            return d;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
