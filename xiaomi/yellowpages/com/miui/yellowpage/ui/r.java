package com.miui.yellowpage.ui;

import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;

/* compiled from: CityPickerFragment */
class r implements OnClickListener {
    final /* synthetic */ bD ge;

    r(bD bDVar) {
        this.ge = bDVar;
    }

    public void onClick(View view) {
        String a = this.ge.bw(this.ge.yy);
        if (TextUtils.isEmpty(a)) {
            a = this.ge.yx;
        }
        this.ge.u(a, this.ge.yy);
    }
}
