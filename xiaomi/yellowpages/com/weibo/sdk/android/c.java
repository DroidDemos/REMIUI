package com.weibo.sdk.android;

import android.os.Handler;
import android.os.Message;
import com.alipay.mobilesecuritysdk.constant.ConfigConstant;
import com.alipay.sdk.data.Response;

/* compiled from: Weibo */
class c extends Handler {
    final /* synthetic */ a wg;

    c(a aVar) {
        this.wg = aVar;
    }

    public void handleMessage(Message message) {
        switch (message.what) {
            case Response.a /*1000*/:
                if (message.getData() != null) {
                    this.wg.sX.c(message.getData());
                    return;
                } else {
                    this.wg.sX.b(new WeiboException("Failed to receive access token."));
                    return;
                }
            case 1001:
                if (message.obj != null) {
                    this.wg.sX.b((WeiboException) message.obj);
                }
                if (message.getData() != null) {
                    String string = message.getData().getString(ConfigConstant.LOG_JSON_STR_ERROR);
                    String string2 = message.getData().getString("error_code");
                    this.wg.sX.b(new WeiboException(new StringBuilder(String.valueOf(string)).append("-").append(message.getData().getString("error_description")).toString(), Integer.parseInt(string2)));
                    return;
                }
                return;
            default:
                return;
        }
    }
}
