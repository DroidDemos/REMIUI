package com.miui.yellowpage.ui;

import android.content.Loader;
import android.os.Bundle;
import com.miui.yellowpage.a.g;
import com.miui.yellowpage.a.k;
import com.miui.yellowpage.a.p;
import com.miui.yellowpage.model.l;
import com.miui.yellowpage.request.BaseResult;

/* compiled from: ExpressInquiryProgressFragment */
class dX implements k {
    final /* synthetic */ ExpressInquiryProgressFragment mY;

    private dX(ExpressInquiryProgressFragment expressInquiryProgressFragment) {
        this.mY = expressInquiryProgressFragment;
    }

    public /* bridge */ /* synthetic */ void onLoadFinished(Loader loader, Object obj) {
        a(loader, (BaseResult) obj);
    }

    public BaseResult a(int i, Object obj, BaseResult baseResult, boolean z) {
        dG dGVar = (dG) baseResult;
        if (obj != null) {
            dGVar.JQ = (String) obj;
        }
        return dGVar;
    }

    public BaseResult r() {
        return new dG(this.mY);
    }

    public g s() {
        return this.mY.mLoadingProgressView;
    }

    public Loader onCreateLoader(int i, Bundle bundle) {
        Loader pVar = new p(this.mY.JP, this.mY.su);
        pVar.a(this.mY.dS());
        return pVar;
    }

    public void a(Loader loader, BaseResult baseResult) {
        dG dGVar = (dG) baseResult;
        if (dGVar.hasData()) {
            if (this.mY.sJ == null) {
                this.mY.sJ = l.bm(dGVar.JQ);
            }
            if (this.mY.sJ != null && this.mY.sO == null) {
                this.mY.dU();
            }
        }
        if (this.mY.getLoaderManager().getLoader(1) == null && this.mY.sJ != null && this.mY.sO != null) {
            this.mY.dG();
        }
    }

    public void onLoaderReset(Loader loader) {
    }
}
