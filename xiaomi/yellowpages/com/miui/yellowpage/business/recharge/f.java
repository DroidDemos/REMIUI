package com.miui.yellowpage.business.recharge;

import android.content.Loader;
import android.os.Bundle;
import com.miui.yellowpage.a.a;
import com.miui.yellowpage.a.b;
import com.miui.yellowpage.a.g;
import com.miui.yellowpage.a.k;
import com.miui.yellowpage.model.c;
import com.miui.yellowpage.request.BaseResult;
import org.json.JSONObject;

/* compiled from: RechargeOrderListFragment */
public class f implements b, k {
    final /* synthetic */ N eL;

    public f(N n) {
        this.eL = n;
    }

    public /* bridge */ /* synthetic */ void onLoadFinished(Loader loader, Object obj) {
        a(loader, (BaseResult) obj);
    }

    public Loader onCreateLoader(int i, Bundle bundle) {
        if (i == 1) {
            this.eL.mLoader = new a(this.eL.mActivity, this.eL.Ku, "pageindex", 20, 1);
            this.eL.mLoader.a(this.eL.cy());
            this.eL.mLoader.a((b) this);
        }
        return this.eL.mLoader;
    }

    public void a(Loader loader, BaseResult baseResult) {
        this.eL.Kt.updateData(((c) baseResult).eK);
    }

    public void onLoaderReset(Loader loader) {
    }

    public BaseResult a(int i, Object obj, BaseResult baseResult, boolean z) {
        c cVar = (c) baseResult;
        if (1 == i && obj != null) {
            cVar.eK = c.m(new JSONObject((String) obj).getString("data"));
        }
        return cVar;
    }

    public BaseResult r() {
        return new c(this.eL);
    }

    public BaseResult a(int i, BaseResult baseResult, BaseResult baseResult2, boolean z) {
        baseResult = (c) baseResult;
        c cVar = (c) baseResult2;
        if (baseResult.eK == null) {
            return cVar;
        }
        if (cVar.eK == null) {
            return baseResult;
        }
        cVar.eK.addAll(0, baseResult.eK);
        return cVar;
    }

    public g s() {
        return this.eL.mLoadingProgressView;
    }
}
