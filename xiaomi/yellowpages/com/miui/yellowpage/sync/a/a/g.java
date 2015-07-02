package com.miui.yellowpage.sync.a.a;

import android.content.Context;
import com.miui.yellowpage.base.request.JSONRequest;
import com.miui.yellowpage.base.utils.HostManager;
import com.miui.yellowpage.base.utils.Log;
import com.miui.yellowpage.utils.G;
import org.json.JSONObject;

/* compiled from: ServiceSetting */
public class g extends d {
    protected JSONRequest m(Context context) {
        return new JSONRequest(context, HostManager.getServiceMonitorSettingsUrl());
    }

    public boolean e(Context context, String str) {
        if (G.b(context, new JSONObject(str))) {
            Log.d("PullTask", "successfully updated service setting");
        }
        return false;
    }

    public long D(Context context) {
        return 0;
    }

    protected void e(Context context, long j) {
    }

    public boolean A(Context context) {
        return true;
    }
}
