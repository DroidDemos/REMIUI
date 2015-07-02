package com.miui.yellowpage.ui;

import android.content.Loader;
import android.os.Bundle;
import com.miui.yellowpage.a.g;
import com.miui.yellowpage.a.k;
import com.miui.yellowpage.a.p;
import com.miui.yellowpage.model.ExpressCompany;
import com.miui.yellowpage.request.BaseResult;
import org.json.JSONArray;

/* compiled from: ExpressCompanyListBaseFragment */
public class X implements k {
    final /* synthetic */ i kR;

    protected X(i iVar) {
        this.kR = iVar;
    }

    public /* bridge */ /* synthetic */ void onLoadFinished(Loader loader, Object obj) {
        a(loader, (BaseResult) obj);
    }

    public Loader onCreateLoader(int i, Bundle bundle) {
        if (i == this.kR.J()) {
            this.kR.mLoader = new p(this.kR.JP, this.kR.L());
            this.kR.mLoader.a(this.kR.M());
        }
        return this.kR.mLoader;
    }

    public void a(Loader loader, BaseResult baseResult) {
        this.kR.a((dU) baseResult);
    }

    public BaseResult a(int i, Object obj, BaseResult baseResult, boolean z) {
        dU dUVar = (dU) baseResult;
        dUVar.list = ExpressCompany.b(new JSONArray((String) obj));
        return dUVar;
    }

    public BaseResult r() {
        return new dU(this.kR);
    }

    public g s() {
        return this.kR.I();
    }

    public void onLoaderReset(Loader loader) {
    }
}
