package com.miui.yellowpage.ui;

import android.view.View;
import android.view.View.OnClickListener;
import com.miui.yellowpage.activity.A;

/* compiled from: ExpressInquiryFragment */
class cv implements OnClickListener {
    final /* synthetic */ bS DE;

    cv(bS bSVar) {
        this.DE = bSVar;
    }

    public void onClick(View view) {
        Object tag = view.getTag();
        if (tag instanceof A) {
            A a = (A) tag;
            if (a.mIntent != null) {
                this.DE.mContext.startActivity(a.mIntent);
            }
        }
    }
}
