package com.miui.yellowpage.activity;

import android.os.Bundle;
import com.miui.yellowpage.R;
import com.miui.yellowpage.utils.C;

/* compiled from: FlowOfPackageActivity */
class x implements C {
    final /* synthetic */ Bundle ns;
    final /* synthetic */ FlowOfPackageActivity nt;

    x(FlowOfPackageActivity flowOfPackageActivity, Bundle bundle) {
        this.nt = flowOfPackageActivity;
        this.ns = bundle;
    }

    public void v(int i) {
        this.ns.putInt("sim_index", i);
        this.nt.getStatisticsContext().attach(this.ns);
        this.nt.showFragment("FlowOfPackageFragment", this.nt.getString(R.string.packages_title), this.ns, false);
    }

    public void onCancel() {
        this.nt.finish();
    }
}
