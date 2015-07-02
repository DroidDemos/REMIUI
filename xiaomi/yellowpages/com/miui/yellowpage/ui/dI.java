package com.miui.yellowpage.ui;

import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.webkit.JsResult;

/* compiled from: BaseWebFragment */
class dI implements OnCancelListener {
    final /* synthetic */ JsResult JR;
    final /* synthetic */ C JS;

    dI(C c, JsResult jsResult) {
        this.JS = c;
        this.JR = jsResult;
    }

    public void onCancel(DialogInterface dialogInterface) {
        this.JR.cancel();
        this.JS.this$0.mCurJsResult = null;
    }
}
