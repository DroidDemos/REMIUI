package com.miui.yellowpage.ui;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;

/* compiled from: RechargeFragment */
class G implements OnClickListener {
    final /* synthetic */ aL ie;

    G(aL aLVar) {
        this.ie = aLVar;
    }

    public void onClick(View view) {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("vnd.android.cursor.item/phone_v2");
        intent.setPackage("com.android.contacts");
        this.ie.startActivityForResult(intent, 1);
    }
}
