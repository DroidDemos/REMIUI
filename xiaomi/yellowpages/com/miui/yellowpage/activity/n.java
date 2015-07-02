package com.miui.yellowpage.activity;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.widget.EditText;
import com.miui.yellowpage.base.utils.Cache;

/* compiled from: RemarkActivity */
class n implements OnClickListener {
    final /* synthetic */ EditText lw;
    final /* synthetic */ RemarkActivity lx;

    n(RemarkActivity remarkActivity, EditText editText) {
        this.lx = remarkActivity;
        this.lw = editText;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        this.lx.iT = this.lw.getText().toString();
        Cache.remark(this.lx, this.lx.mKey, this.lx.iT);
        Intent intent = new Intent();
        intent.putExtra("order_remark", this.lx.iT);
        this.lx.setResult(-1, intent);
    }
}
