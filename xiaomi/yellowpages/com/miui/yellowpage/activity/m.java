package com.miui.yellowpage.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.a.a.h;

/* compiled from: QuickYellowPageActivity */
class m extends h {
    final /* synthetic */ QuickYellowPageActivity bU;

    private m(QuickYellowPageActivity quickYellowPageActivity, FragmentManager fragmentManager) {
        this.bU = quickYellowPageActivity;
        super(fragmentManager);
    }

    public Fragment r(int i) {
        return (Fragment) this.bU.HB.get(i);
    }

    public int getItemPosition(Object obj) {
        return -2;
    }

    public int getCount() {
        return this.bU.HB.size();
    }
}
