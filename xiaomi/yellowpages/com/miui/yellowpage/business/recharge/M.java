package com.miui.yellowpage.business.recharge;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;

/* compiled from: RechargeFragment */
class M implements OnClickListener {
    final /* synthetic */ D dw;

    M(D d) {
        this.dw = d;
    }

    public void onClick(View view) {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("vnd.android.cursor.item/phone_v2");
        intent.setPackage("com.android.contacts");
        this.dw.startActivityForResult(intent, 1);
    }
}
