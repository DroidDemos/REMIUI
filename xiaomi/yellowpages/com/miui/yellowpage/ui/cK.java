package com.miui.yellowpage.ui;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import com.miui.yellowpage.b.c;
import com.miui.yellowpage.base.model.YellowPageModuleEntry;

/* compiled from: YellowPageServiceContainerItem */
class cK implements OnItemClickListener {
    final /* synthetic */ c Es;
    final /* synthetic */ YellowPageServiceContainerItem Et;

    cK(YellowPageServiceContainerItem yellowPageServiceContainerItem, c cVar) {
        this.Et = yellowPageServiceContainerItem;
        this.Es = cVar;
    }

    public void onItemClick(AdapterView adapterView, View view, int i, long j) {
        this.Et.c((YellowPageModuleEntry) this.Es.getItem(i));
        if (this.Et.Do != null) {
            this.Et.Do.dismiss();
        }
    }
}
