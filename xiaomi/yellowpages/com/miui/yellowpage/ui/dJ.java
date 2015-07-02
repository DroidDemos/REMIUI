package com.miui.yellowpage.ui;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.webkit.JsResult;

/* compiled from: BaseWebFragment */
class dJ implements OnClickListener {
    final /* synthetic */ JsResult JR;
    final /* synthetic */ C JS;

    dJ(C c, JsResult jsResult) {
        this.JS = c;
        this.JR = jsResult;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        this.JR.confirm();
        this.JS.this$0.mCurJsResult = null;
    }
}
