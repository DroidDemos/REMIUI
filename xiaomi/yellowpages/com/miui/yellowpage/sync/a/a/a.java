package com.miui.yellowpage.sync.a.a;

import android.content.Context;
import com.alipay.sdk.cons.MiniDefine;
import com.miui.yellowpage.base.model.YellowPage;
import com.miui.yellowpage.base.request.JSONRequest;
import com.miui.yellowpage.base.utils.HostManager;
import com.miui.yellowpage.base.utils.Log;
import com.miui.yellowpage.base.utils.YellowPageHandler;
import com.miui.yellowpage.base.utils.YellowPageImgLoader;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: YellowPage */
public class a extends d {
    private String bV;

    protected JSONRequest m(Context context) {
        JSONRequest jSONRequest = new JSONRequest(context, HostManager.getYellowPageUpdateUrl());
        jSONRequest.addParam("version", String.valueOf(D(context)));
        return jSONRequest;
    }

    protected String n(Context context) {
        return "yellow_page_v2";
    }

    public boolean e(Context context, String str) {
        JSONObject jSONObject = new JSONObject(str);
        a(context, jSONObject.getJSONArray("ypdata"));
        boolean z = jSONObject.getBoolean("has_more");
        long j = jSONObject.getLong("max_version");
        long D = D(context);
        if (j < D) {
            Log.e("PullTask", "update failed, server version=" + j + ", current version=" + D);
            return false;
        }
        if (this.bV == null && jSONObject.has("image_domain")) {
            this.bV = jSONObject.getString("image_domain");
            HostManager.setImageDomain(context, this.bV);
        }
        Log.d("PullTask", "update succeeded, store version back, current version:" + j);
        e(context, j);
        Log.d("PullTask", "has more data: " + z);
        return z;
    }

    private void a(Context context, JSONArray jSONArray) {
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject jSONObject = jSONArray.getJSONObject(i);
            int i2 = jSONObject.getInt("sid");
            if (jSONObject.getInt(MiniDefine.b) == 0) {
                YellowPageHandler.deleteYellowPage(context, (long) i2);
                Log.d("PullTask", "delete yid: " + i2);
            } else {
                YellowPage fromJson = YellowPage.fromJson(jSONObject.toString());
                if (fromJson != null) {
                    YellowPageHandler.insertYellowPage(context, fromJson);
                    YellowPageImgLoader.loadThumbnailByName(context, fromJson.getThumbnailName(), true);
                }
            }
        }
    }
}
