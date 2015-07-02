package com.miui.yellowpage.ui;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;

/* compiled from: ExpressAddressEditorFragment */
class cC implements OnClickListener {
    final /* synthetic */ ExpressAddressEditorFragment tu;

    cC(ExpressAddressEditorFragment expressAddressEditorFragment) {
        this.tu = expressAddressEditorFragment;
    }

    public void onClick(View view) {
        this.tu.startActivityForResult(new Intent("com.miui.yellowpage.action.PICK_REGION"), 0);
    }
}
