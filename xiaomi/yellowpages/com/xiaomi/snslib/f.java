package com.xiaomi.snslib;

import android.app.Activity;
import android.util.Log;
import com.miui.yellowpage.R;
import com.weibo.sdk.android.WeiboException;
import com.weibo.sdk.android.a.a;
import com.weibo.sdk.android.net.b;
import com.xiaomi.snslib.ShareToSNS.SNSType;

/* compiled from: ShareToSNS */
class f implements b {
    final /* synthetic */ String nq;
    final /* synthetic */ e nr;
    final /* synthetic */ Activity val$activity;

    f(e eVar, Activity activity, String str) {
        this.nr = eVar;
        this.val$activity = activity;
        this.nq = str;
    }

    public void T(String str) {
        if (this.nr.ng) {
            ShareToSNS.b(this.val$activity, (int) R.string.sina_share_success);
        }
        if (this.nr.nd != null) {
            this.val$activity.runOnUiThread(new h(this));
        }
    }

    public void a(WeiboException weiboException) {
        Log.e("ShareToSNS", "Request for sharing message to sina weibo has errors.", weiboException);
        String string = a.aB(weiboException.getMessage()).getString("error_code");
        if (this.nr.ng) {
            ShareToSNS.b(this.val$activity, ShareToSNS.a(SNSType.SINA, string));
        }
        if (ShareToSNS.ac(string)) {
            new g(this, this.val$activity.getApplicationContext()).execute(new Integer[]{Integer.valueOf(0)});
        }
        if (this.nr.nd != null) {
            this.val$activity.runOnUiThread(new i(this));
        }
    }
}
