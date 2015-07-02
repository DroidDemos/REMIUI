package com.miui.yellowpage.ui;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;

/* compiled from: ExpressContactEditorFragment */
public class au implements OnClickListener {
    final /* synthetic */ dO nX;

    protected au(dO dOVar) {
        this.nX = dOVar;
    }

    public void onClick(View view) {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("vnd.android.cursor.item/contact");
        intent.setPackage("com.android.contacts");
        this.nX.startActivityForResult(intent, 103);
    }
}
