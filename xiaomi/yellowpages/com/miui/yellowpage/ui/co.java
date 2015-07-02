package com.miui.yellowpage.ui;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import com.miui.yellowpage.base.model.YellowPage;

/* compiled from: FavoriteYellowPageFragment */
class co implements OnItemClickListener {
    final /* synthetic */ bc mE;

    co(bc bcVar) {
        this.mE = bcVar;
    }

    public void onItemClick(AdapterView adapterView, View view, int i, long j) {
        YellowPage yellowPage = (YellowPage) this.mE.uC.getItem(i);
        Intent intent = new Intent("com.miui.yellowpage.action.VIEW");
        intent.putExtra("com.miui.yellowpage.extra.yid", yellowPage.getId());
        intent.putExtra("source", "favorite");
        this.mE.mActivity.startActivity(intent);
    }
}
