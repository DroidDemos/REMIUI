package com.miui.yellowpage.ui;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import com.miui.yellowpage.base.model.YellowPage;

/* compiled from: RecentYellowPageFragment */
class ah implements OnItemClickListener {
    final /* synthetic */ cm hS;

    ah(cm cmVar) {
        this.hS = cmVar;
    }

    public void onItemClick(AdapterView adapterView, View view, int i, long j) {
        YellowPage yellowPage = (YellowPage) this.hS.Dv.getItem(i);
        Intent intent = new Intent("com.miui.yellowpage.action.VIEW");
        intent.putExtra("com.miui.yellowpage.extra.yid", yellowPage.getId());
        intent.putExtra("source", "recent");
        this.hS.mActivity.startActivity(intent);
    }
}
