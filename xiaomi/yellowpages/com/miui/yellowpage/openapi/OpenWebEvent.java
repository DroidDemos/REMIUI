package com.miui.yellowpage.openapi;

import android.content.Context;
import android.os.Handler;
import android.webkit.JavascriptInterface;
import com.a.a.a;
import com.alipay.mobilesecuritysdk.constant.ConfigConstant;
import com.miui.yellowpage.base.utils.BaseWebEvent;
import com.miui.yellowpage.base.utils.BaseWebEvent.CurrentUrlSource;
import com.miui.yellowpage.base.utils.Log;
import com.miui.yellowpage.base.utils.Network;
import com.miui.yellowpage.utils.c;
import com.miui.yellowpage.utils.e;
import com.miui.yellowpage.utils.z;

public class OpenWebEvent extends BaseWebEvent {
    private static final String TAG = "OpenWebEvent";

    public OpenWebEvent(Context context, Handler handler, CurrentUrlSource currentUrlSource) {
        super(context, handler, currentUrlSource);
    }

    @JavascriptInterface
    public void log(String str) {
        Log.d(TAG, str);
    }

    @JavascriptInterface
    public String getNetworkType() {
        if (Network.isMobileConnected(this.mContext)) {
            return "mobile";
        }
        if (Network.isWifiConnected(this.mContext)) {
            return ConfigConstant.JSON_SECTION_WIFI;
        }
        return "fail";
    }

    @JavascriptInterface
    public void saveEDaiJiaDriver(String str, String str2, String str3) {
        e.a(this.mContext, str, str2, str3);
    }

    @JavascriptInterface
    public void setBottomBarVisible(boolean z) {
        sendAsyncCallbackMessage(13, String.valueOf(z));
    }

    @JavascriptInterface
    public boolean isMipayExist() {
        return a.t(this.mContext);
    }

    @JavascriptInterface
    public boolean isAliMSPayExist() {
        return c.r(this.mContext);
    }

    @JavascriptInterface
    public void saveImage(String str) {
        z.e(this.mContext, str, null);
    }
}
