package com.miui.yellowpage.ui;

import android.content.Loader;
import android.os.Bundle;
import com.miui.yellowpage.a.g;
import com.miui.yellowpage.a.k;
import com.miui.yellowpage.a.p;
import com.miui.yellowpage.base.utils.Log;
import com.miui.yellowpage.model.b;
import com.miui.yellowpage.request.BaseResult;

/* compiled from: RechargeFragment */
class aU implements k {
    final /* synthetic */ aL ie;

    private aU(aL aLVar) {
        this.ie = aLVar;
    }

    public /* bridge */ /* synthetic */ void onLoadFinished(Loader loader, Object obj) {
        a(loader, (BaseResult) obj);
    }

    public Loader onCreateLoader(int i, Bundle bundle) {
        if (i == 1) {
            this.ie.mLoader = new p(this.ie.mActivity, this.ie.qj);
            this.ie.mLoader.a(this.ie.cW());
        }
        return this.ie.mLoader;
    }

    public void a(Loader loader, BaseResult baseResult) {
        this.ie.qw = (cY) baseResult;
        if (this.ie.qw.hasData()) {
            this.ie.mRootView.setVisibility(0);
            this.ie.cZ();
            return;
        }
        this.ie.mRootView.setVisibility(8);
    }

    public void onLoaderReset(Loader loader) {
    }

    public BaseResult a(int i, Object obj, BaseResult baseResult, boolean z) {
        cY cYVar = (cY) baseResult;
        Log.d("RechargeFragment", "Recharge product list info " + obj);
        if (i == 1) {
            cYVar.DO = b.l((String) obj);
        }
        return cYVar;
    }

    public BaseResult r() {
        return new cY(this.ie);
    }

    public g s() {
        return this.ie.hh;
    }
}
