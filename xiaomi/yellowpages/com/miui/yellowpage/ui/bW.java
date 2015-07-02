package com.miui.yellowpage.ui;

import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import com.miui.yellowpage.a.g;
import com.miui.yellowpage.a.k;
import com.miui.yellowpage.a.p;
import com.miui.yellowpage.request.BaseResult;

/* compiled from: InquiryHistoryFragment */
class bW implements k {
    final /* synthetic */ aH qg;

    private bW(aH aHVar) {
        this.qg = aHVar;
    }

    public /* bridge */ /* synthetic */ void onLoadFinished(Loader loader, Object obj) {
        a(loader, (BaseResult) obj);
    }

    public Loader onCreateLoader(int i, Bundle bundle) {
        if (i == 0) {
            this.qg.mLoader = new p(this.qg.mActivity, this.qg.oT);
            this.qg.mLoader.a(this.qg.cy());
        }
        return this.qg.mLoader;
    }

    public void a(Loader loader, BaseResult baseResult) {
        aJ aJVar = (aJ) baseResult;
        if (aJVar.hasData()) {
            this.qg.oS.changeCursor(aJVar.qf);
        }
    }

    public void onLoaderReset(Loader loader) {
    }

    public BaseResult a(int i, Object obj, BaseResult baseResult, boolean z) {
        aJ aJVar = (aJ) baseResult;
        aJVar.qf = (Cursor) obj;
        return aJVar;
    }

    public BaseResult r() {
        return new aJ(this.qg);
    }

    public g s() {
        return null;
    }
}
