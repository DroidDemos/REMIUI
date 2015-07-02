package com.miui.yellowpage.ui;

import android.view.View;
import android.view.View.OnClickListener;
import com.miui.yellowpage.model.v;

/* compiled from: RechargeLocalHistoryListItem */
class ar implements OnClickListener {
    final /* synthetic */ v no;
    final /* synthetic */ RechargeLocalHistoryListItem np;

    ar(RechargeLocalHistoryListItem rechargeLocalHistoryListItem, v vVar) {
        this.np = rechargeLocalHistoryListItem;
        this.no = vVar;
    }

    public void onClick(View view) {
        this.np.gz.a(this.no);
    }
}
