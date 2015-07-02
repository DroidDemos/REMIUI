package com.miui.yellowpage.ui;

import android.view.View;
import android.view.View.OnClickListener;
import com.miui.yellowpage.model.YellowPageDataDetailEntry;

/* compiled from: YellowPagePhonesListItem */
class aw implements OnClickListener {
    final /* synthetic */ YellowPageDataDetailEntry oh;
    final /* synthetic */ YellowPagePhonesListItem oi;

    aw(YellowPagePhonesListItem yellowPagePhonesListItem, YellowPageDataDetailEntry yellowPageDataDetailEntry) {
        this.oi = yellowPagePhonesListItem;
        this.oh = yellowPageDataDetailEntry;
    }

    public void onClick(View view) {
        this.oi.getContext().startActivity(this.oh.dt());
    }
}
