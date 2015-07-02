package com.miui.yellowpage.ui;

import android.widget.AbsListView;
import com.miui.yellowpage.base.utils.YellowPageImgLoader;
import com.miui.yellowpage.widget.a;

/* compiled from: NearbyYellowPageFragment */
class b extends a {
    final /* synthetic */ db cg;

    public b(db dbVar, Runnable runnable) {
        this.cg = dbVar;
        super(runnable);
    }

    public void onScrollStateChanged(AbsListView absListView, int i) {
        if (i == 2) {
            YellowPageImgLoader.pauseLoading(this.cg.mActivity);
        } else {
            YellowPageImgLoader.resumeLoading(this.cg.mActivity);
        }
        super.onScrollStateChanged(absListView, i);
    }
}
