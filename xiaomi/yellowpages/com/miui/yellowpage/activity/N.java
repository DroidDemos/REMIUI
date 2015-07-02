package com.miui.yellowpage.activity;

import android.view.View;
import android.view.View.OnClickListener;

/* compiled from: ExpressAddressEditorActivity */
class N implements OnClickListener {
    final /* synthetic */ ExpressAddressEditorActivity ue;

    private N(ExpressAddressEditorActivity expressAddressEditorActivity) {
        this.ue = expressAddressEditorActivity;
    }

    public void onClick(View view) {
        this.ue.setResult(0);
        this.ue.finish();
    }
}
