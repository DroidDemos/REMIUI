package com.miui.yellowpage.business.recharge;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import com.miui.yellowpage.utils.x;

/* compiled from: RechargeFragment */
class m implements OnItemClickListener {
    final /* synthetic */ D dw;

    m(D d) {
        this.dw = d;
    }

    public void onItemClick(AdapterView adapterView, View view, int i, long j) {
        x.a(this.dw.qo, false);
    }
}
