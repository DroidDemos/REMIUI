package com.miui.yellowpage.ui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.miui.yellowpage.base.utils.Log;

/* compiled from: BaseWebFragment */
class cV extends BroadcastReceiver {
    final /* synthetic */ bw this$0;

    cV(bw bwVar) {
        this.this$0 = bwVar;
    }

    public void onReceive(Context context, Intent intent) {
        Log.d("BaseWebFragment", "mWebResBroadcastReceiver onReceive");
        bw.INJECTED_JS_CONTENT = null;
        if (this.this$0.mWebView != null) {
            this.this$0.mWebView.clearCache(true);
            this.this$0.loadUrl(this.this$0.mWebView.getUrl());
        }
    }
}
