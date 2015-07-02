package com.miui.yellowpage.activity;

import android.view.View;
import android.view.View.OnClickListener;
import com.miui.yellowpage.utils.x;

/* compiled from: ExpressAddressEditorActivity */
class an implements OnClickListener {
    final /* synthetic */ ExpressAddressEditorActivity ue;

    private an(ExpressAddressEditorActivity expressAddressEditorActivity) {
        this.ue = expressAddressEditorActivity;
    }

    public void onClick(View view) {
        x.a(view, false);
        if (this.ue.Jf != null && this.ue.Jf.ay()) {
            this.ue.Jf.aA();
        }
    }
}
