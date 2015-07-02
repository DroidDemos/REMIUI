package com.miui.yellowpage.ui;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import com.miui.yellowpage.base.model.YellowPageModuleEntry;

/* compiled from: YellowPageServiceContainerItem */
class cM implements OnItemClickListener {
    final /* synthetic */ YellowPageServiceContainerItem Et;

    cM(YellowPageServiceContainerItem yellowPageServiceContainerItem) {
        this.Et = yellowPageServiceContainerItem;
    }

    public void onItemClick(AdapterView adapterView, View view, int i, long j) {
        if (this.Et.Dn.u(i)) {
            this.Et.gZ();
        } else {
            this.Et.c((YellowPageModuleEntry) this.Et.Dn.getItem(i));
        }
    }
}
