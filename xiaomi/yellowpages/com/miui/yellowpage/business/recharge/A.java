package com.miui.yellowpage.business.recharge;

import android.view.View;
import android.view.View.OnClickListener;
import com.miui.yellowpage.model.c;
import com.miui.yellowpage.request.BaseResult;
import com.miui.yellowpage.ui.RechargeOrderListPendingItem;

/* compiled from: RechargeOrderListFragment */
class A implements OnClickListener {
    final /* synthetic */ N eL;

    A(N n) {
        this.eL = n;
    }

    public void onClick(View view) {
        c aG = ((RechargeOrderListPendingItem) view.getParent().getParent()).aG();
        BaseResult tVar = new t(this.eL);
        tVar.lD = aG.ac();
        tVar.nm = aG.ad();
        this.eL.mRequestLoader.a(this.eL.S(aG.ac()), tVar);
    }
}
