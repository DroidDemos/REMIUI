package com.miui.yellowpage.ui;

import android.content.Loader;
import android.os.Bundle;
import com.miui.yellowpage.a.g;
import com.miui.yellowpage.a.k;
import com.miui.yellowpage.a.p;
import com.miui.yellowpage.model.q;
import com.miui.yellowpage.request.BaseResult;

/* compiled from: AccountFragment */
class dQ implements k {
    final /* synthetic */ aT mS;

    private dQ(aT aTVar) {
        this.mS = aTVar;
    }

    public /* bridge */ /* synthetic */ void onLoadFinished(Loader loader, Object obj) {
        a(loader, (BaseResult) obj);
    }

    public BaseResult a(int i, Object obj, BaseResult baseResult, boolean z) {
        if (i == 0 && obj != null) {
            ((al) baseResult).mR = q.w(this.mS.mActivity, (String) obj);
        }
        return baseResult;
    }

    public BaseResult r() {
        return new al(this.mS);
    }

    public g s() {
        return null;
    }

    public Loader onCreateLoader(int i, Bundle bundle) {
        if (i == 0) {
            this.mS.mLoader = new p(this.mS.mActivity, this.mS.tg);
            this.mS.mLoader.a(this.mS.cy());
        }
        return this.mS.mLoader;
    }

    public void a(Loader loader, BaseResult baseResult) {
        al alVar = (al) baseResult;
        if (alVar.hasData()) {
            this.mS.getPreferenceScreen().removeAll();
            alVar.mR.a(this.mS.mActivity, this.mS.getPreferenceScreen());
            this.mS.ec();
        }
    }

    public void onLoaderReset(Loader loader) {
    }
}
