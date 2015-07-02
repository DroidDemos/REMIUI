package com.miui.yellowpage.utils;

import android.content.Context;
import com.alipay.mobilesecuritysdk.constant.ConfigConstant;
import com.miui.yellowpage.base.request.HttpRequest;
import com.miui.yellowpage.base.utils.HostManager;
import com.miui.yellowpage.base.utils.Preference;

/* compiled from: ExpressServiceProvider */
public class n {
    public static void F(Context context) {
        Preference.setLong(context, "pref_express_provider_list", Long.valueOf(System.currentTimeMillis()));
    }

    public static boolean G(Context context) {
        return Math.abs(System.currentTimeMillis() - Preference.getLong(context, "pref_express_provider_list", 0)) > ConfigConstant.MAIN_SWITCH_INTERVAL_UINT;
    }

    public static HttpRequest a(Context context, int i) {
        return new HttpRequest(context, HostManager.getExpressServiceProviderInfoUrl(), i);
    }
}
