package com.miui.yellowpage.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import com.miui.yellowpage.model.g;

/* compiled from: InquiryHistoryFragment */
class aO implements OnItemClickListener {
    final /* synthetic */ aH qg;

    private aO(aH aHVar) {
        this.qg = aHVar;
    }

    public void onItemClick(AdapterView adapterView, View view, int i, long j) {
        g fo = ((InquiryHistoryListItem) view).fo();
        Bundle bundle = new Bundle();
        bundle.putString("order", fo.getSerialNumber());
        bundle.putString("bizCode", fo.aP());
        bundle.putString("logistics_name", fo.aQ());
        if (this.qg.cZ != null) {
            this.qg.cZ.g(bundle);
        }
    }
}
