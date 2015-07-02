package com.miui.yellowpage.ui;

import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import com.miui.yellowpage.a.g;
import com.miui.yellowpage.a.k;
import com.miui.yellowpage.a.p;
import com.miui.yellowpage.request.BaseResult;

/* compiled from: RegionPickerFragment */
class cd implements k {
    private String CG;
    final /* synthetic */ RegionPickerFragment mQ;

    private cd(RegionPickerFragment regionPickerFragment) {
        this.mQ = regionPickerFragment;
    }

    public /* bridge */ /* synthetic */ void onLoadFinished(Loader loader, Object obj) {
        a(loader, (BaseResult) obj);
    }

    public Loader onCreateLoader(int i, Bundle bundle) {
        if (i == 0) {
            this.CG = bundle.getString("parent_id");
            RequestPurpose requestPurpose = (RequestPurpose) bundle.getSerializable("purpose");
            this.mQ.mLoader = new p(this.mQ.mActivity, this.mQ.mV);
            this.mQ.mLoader.a(this.mQ.a(this.CG, requestPurpose));
        }
        return this.mQ.mLoader;
    }

    public void a(Loader loader, BaseResult baseResult) {
        this.mQ.mX.changeCursor(((cg) baseResult).mCursor);
    }

    public BaseResult a(int i, Object obj, BaseResult baseResult, boolean z) {
        cg cgVar = (cg) baseResult;
        if (i == 0 && obj != null && (obj instanceof Cursor)) {
            cgVar.mCursor = (Cursor) obj;
        }
        return cgVar;
    }

    public BaseResult r() {
        return new cg();
    }

    public g s() {
        return null;
    }

    public void onLoaderReset(Loader loader) {
    }
}
