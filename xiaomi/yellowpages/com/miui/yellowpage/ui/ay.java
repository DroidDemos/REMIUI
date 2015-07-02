package com.miui.yellowpage.ui;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import com.miui.yellowpage.utils.x;

/* compiled from: RechargeFragment */
class ay implements OnItemClickListener {
    final /* synthetic */ aL ie;

    ay(aL aLVar) {
        this.ie = aLVar;
    }

    public void onItemClick(AdapterView adapterView, View view, int i, long j) {
        x.a(this.ie.qo, false);
    }
}
