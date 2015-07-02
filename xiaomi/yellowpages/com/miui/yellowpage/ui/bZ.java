package com.miui.yellowpage.ui;

import android.content.Loader;
import android.os.Bundle;
import com.miui.yellowpage.a.g;
import com.miui.yellowpage.a.k;
import com.miui.yellowpage.a.p;
import com.miui.yellowpage.model.ExpressOrder;
import com.miui.yellowpage.model.ExpressOrder.State;
import com.miui.yellowpage.request.BaseResult;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ExpressOrderItemFragment */
class bZ implements k {
    final /* synthetic */ ExpressOrderItemFragment hJ;

    private bZ(ExpressOrderItemFragment expressOrderItemFragment) {
        this.hJ = expressOrderItemFragment;
    }

    public /* bridge */ /* synthetic */ void onLoadFinished(Loader loader, Object obj) {
        a(loader, (BaseResult) obj);
    }

    public Loader onCreateLoader(int i, Bundle bundle) {
        if (i == 0) {
            this.hJ.mLoader = new p(this.hJ.mActivity, this.hJ.cR);
            this.hJ.mLoader.a(this.hJ.G());
        }
        return this.hJ.mLoader;
    }

    public void a(Loader loader, BaseResult baseResult) {
        W w = (W) baseResult;
        if (w.hasData()) {
            if (!(w.kG == null || w.kG.as() == null)) {
                State iR = w.kG.as().iR();
                if (!(this.hJ.cO == null || iR == null || iR == this.hJ.cO || this.hJ.cS == null)) {
                    this.hJ.cS.aF();
                }
                this.hJ.cO = iR;
            }
            this.hJ.cP = w.kG;
            this.hJ.D();
            return;
        }
        this.hJ.mRoot.setVisibility(8);
        this.hJ.cD.setVisibility(0);
    }

    public void onLoaderReset(Loader loader) {
    }

    public BaseResult a(int i, Object obj, BaseResult baseResult, boolean z) {
        W w = (W) baseResult;
        if (obj != null) {
            try {
                w.kG = ExpressOrder.c(new JSONObject((String) obj));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return w;
    }

    public BaseResult r() {
        return new W(this.hJ);
    }

    public g s() {
        return this.hJ.mLoadingProgressView;
    }
}
