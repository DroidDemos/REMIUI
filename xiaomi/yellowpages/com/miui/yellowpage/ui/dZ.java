package com.miui.yellowpage.ui;

import android.view.View;
import android.view.View.OnClickListener;
import com.miui.yellowpage.model.c;
import com.miui.yellowpage.request.BaseResult;

/* compiled from: RechargeOrderListFragment */
class dZ implements OnClickListener {
    final /* synthetic */ dR lL;

    dZ(dR dRVar) {
        this.lL = dRVar;
    }

    public void onClick(View view) {
        c aG = ((RechargeOrderListPendingItem) view.getParent().getParent()).aG();
        BaseResult bbVar = new bb(this.lL);
        bbVar.lD = aG.ac();
        bbVar.nm = aG.ad();
        this.lL.mRequestLoader.a(this.lL.S(aG.ac()), bbVar);
    }
}
