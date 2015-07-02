package com.miui.yellowpage.ui;

import android.view.View;
import android.view.View.OnClickListener;
import com.miui.yellowpage.base.model.FlowOfPackages.Packages;
import com.miui.yellowpage.base.utils.Preference;

/* compiled from: FlowOfPackageFragment */
class aW implements OnClickListener {
    final /* synthetic */ cf fb;
    final /* synthetic */ FlowOfPackagesItem tr;

    aW(cf cfVar, FlowOfPackagesItem flowOfPackagesItem) {
        this.fb = cfVar;
        this.tr = flowOfPackagesItem;
    }

    public void onClick(View view) {
        this.fb.CX = (Packages) view.getTag();
        Preference.setString(this.fb.mActivity, "pref_last_package_id", this.fb.CX.getSubscribeCode());
        this.tr.E(true);
        if (this.fb.CY != null) {
            this.fb.CY.E(false);
        }
        this.fb.CY = this.tr;
    }
}
