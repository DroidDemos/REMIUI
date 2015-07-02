package com.miui.yellowpage.business.recharge;

import android.content.Loader;
import android.os.Bundle;
import com.miui.yellowpage.a.g;
import com.miui.yellowpage.a.k;
import com.miui.yellowpage.a.p;
import com.miui.yellowpage.base.utils.Log;
import com.miui.yellowpage.model.b;
import com.miui.yellowpage.request.BaseResult;

/* compiled from: RechargeFragment */
class e implements k {
    final /* synthetic */ D dw;

    private e(D d) {
        this.dw = d;
    }

    public /* bridge */ /* synthetic */ void onLoadFinished(Loader loader, Object obj) {
        a(loader, (BaseResult) obj);
    }

    public Loader onCreateLoader(int i, Bundle bundle) {
        if (i == 1) {
            this.dw.mLoader = new p(this.dw.mActivity, this.dw.DH);
            this.dw.mLoader.a(this.dw.cW());
        }
        return this.dw.mLoader;
    }

    public void a(Loader loader, BaseResult baseResult) {
        this.dw.DL = (E) baseResult;
        if (this.dw.DL.hasData()) {
            this.dw.mRootView.setVisibility(0);
            this.dw.cZ();
            return;
        }
        this.dw.mRootView.setVisibility(8);
    }

    public void onLoaderReset(Loader loader) {
    }

    public BaseResult a(int i, Object obj, BaseResult baseResult, boolean z) {
        E e = (E) baseResult;
        Log.d("MiPayRechargeFragment", "Recharge product list info " + obj);
        if (i == 1) {
            e.DO = b.l((String) obj);
        }
        return e;
    }

    public BaseResult r() {
        return new E(this.dw);
    }

    public g s() {
        return this.dw.hh;
    }
}
