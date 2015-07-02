package com.miui.yellowpage.ui;

import android.content.Loader;
import android.os.Bundle;
import com.miui.yellowpage.R;
import com.miui.yellowpage.a.g;
import com.miui.yellowpage.a.k;
import com.miui.yellowpage.a.p;
import com.miui.yellowpage.base.model.FlowOfPackages;
import com.miui.yellowpage.base.utils.Log;
import com.miui.yellowpage.request.BaseResult;

/* compiled from: FlowOfPackageFragment */
class bu implements k {
    final /* synthetic */ cf fb;

    private bu(cf cfVar) {
        this.fb = cfVar;
    }

    public /* bridge */ /* synthetic */ void onLoadFinished(Loader loader, Object obj) {
        a(loader, (BaseResult) obj);
    }

    public Loader onCreateLoader(int i, Bundle bundle) {
        if (i == 1) {
            this.fb.mLoader = new p(this.fb.mActivity, this.fb.CZ);
            this.fb.mLoader.a(this.fb.gV());
        }
        return this.fb.mLoader;
    }

    public void a(Loader loader, BaseResult baseResult) {
        if (baseResult.hasData()) {
            this.fb.CW = ((n) baseResult).fa;
            this.fb.CT.setText(this.fb.CW.getTitle());
            this.fb.CU.setText(this.fb.CW.getDesc());
            this.fb.a(this.fb.CW);
            this.fb.mRootView.setVisibility(0);
            return;
        }
        this.fb.CW = null;
        this.fb.mRootView.setVisibility(8);
        this.fb.hh.t(R.string.packages_no_package);
    }

    public void onLoaderReset(Loader loader) {
    }

    public BaseResult a(int i, Object obj, BaseResult baseResult, boolean z) {
        n nVar = (n) baseResult;
        Log.d("FlowOfPackageFragment", "Flow of packages info " + obj);
        if (i == 1) {
            if (obj != null) {
                nVar.fa = FlowOfPackages.valueOf((String) obj);
            } else {
                nVar.fa = null;
            }
        }
        return nVar;
    }

    public BaseResult r() {
        return new n(this.fb);
    }

    public g s() {
        return this.fb.hh;
    }
}
