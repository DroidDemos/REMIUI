package android.support.v4.view;

import android.database.DataSetObserver;

/* compiled from: ViewPager */
class E extends DataSetObserver {
    final /* synthetic */ ViewPager rT;

    private E(ViewPager viewPager) {
        this.rT = viewPager;
    }

    public void onChanged() {
        this.rT.dataSetChanged();
    }

    public void onInvalidated() {
        this.rT.dataSetChanged();
    }
}
