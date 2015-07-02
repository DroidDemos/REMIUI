package com.miui.yellowpage.ui;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

/* compiled from: CityPickerFragment */
class q implements OnItemClickListener {
    final /* synthetic */ bD ge;

    q(bD bDVar) {
        this.ge = bDVar;
    }

    public void onItemClick(AdapterView adapterView, View view, int i, long j) {
        String str = this.ge.N(i).getData().split(":")[1];
        this.ge.u(this.ge.bw(str), str);
    }
}
