package com.miui.yellowpage.ui;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

/* compiled from: ExpressAddressEditorFragment */
class bT implements OnClickListener {
    final /* synthetic */ ExpressAddressEditorFragment tu;

    private bT(ExpressAddressEditorFragment expressAddressEditorFragment) {
        this.tu = expressAddressEditorFragment;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        this.tu.hl.a(this.tu.a(AddressOperation.DELETE), new EditResult());
    }
}
