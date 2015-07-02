package com.miui.yellowpage.ui;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import java.util.List;

/* compiled from: ExpressInquiryProgressFragment */
class bJ implements OnClickListener {
    final /* synthetic */ ExpressInquiryProgressFragment mY;
    final /* synthetic */ String[] zd;
    final /* synthetic */ List ze;

    bJ(ExpressInquiryProgressFragment expressInquiryProgressFragment, String[] strArr, List list) {
        this.mY = expressInquiryProgressFragment;
        this.zd = strArr;
        this.ze = list;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        if (i == this.zd.length - 1) {
            this.mY.startActivityForResult(this.mY.sO, 4);
        } else {
            this.mY.n((String) this.ze.get(i), this.zd[i]);
        }
        this.mY.sB = false;
        this.mY.sK.dismiss();
    }
}
