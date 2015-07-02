package com.miui.yellowpage.business.recharge;

import com.miui.yellowpage.model.v;
import com.miui.yellowpage.ui.c;

/* compiled from: RechargeFragment */
class i implements c {
    final /* synthetic */ D dw;

    i(D d) {
        this.dw = d;
    }

    public void a(v vVar) {
        this.dw.qA.remove(vVar);
        this.dw.qF.updateData(this.dw.qA);
    }
}
