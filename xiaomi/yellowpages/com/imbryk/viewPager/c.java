package com.imbryk.viewPager;

import android.database.DataSetObserver;

/* compiled from: LoopViewPager */
class c extends DataSetObserver {
    final /* synthetic */ LoopViewPager vU;

    c(LoopViewPager loopViewPager) {
        this.vU = loopViewPager;
    }

    public void onChanged() {
        this.vU.tE.notifyDataSetChanged();
    }
}
