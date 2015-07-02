package com.miui.yellowpage.ui;

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
public class ad implements b, k {
    final /* synthetic */ dR lL;

    public ad(dR dRVar) {
        this.lL = dRVar;
    }

    public /* bridge */ /* synthetic */ void onLoadFinished(Loader loader, Object obj) {
        a(loader, (BaseResult) obj);
    }

    public Loader onCreateLoader(int i, Bundle bundle) {
        if (i == 1) {
            this.lL.mLoader = new a(this.lL.mActivity, this.lL.Le, "pageindex", 20, 1);
            this.lL.mLoader.a(this.lL.cy());
            this.lL.mLoader.a((b) this);
        }
        return this.lL.mLoader;
    }

    public void a(Loader loader, BaseResult baseResult) {
        this.lL.Kt.updateData(((ck) baseResult).eK);
    }

    public void onLoaderReset(Loader loader) {
    }

    public BaseResult a(int i, Object obj, BaseResult baseResult, boolean z) {
        ck ckVar = (ck) baseResult;
        if (1 == i && obj != null) {
            ckVar.eK = c.m(new JSONObject((String) obj).getString("data"));
        }
        return ckVar;
    }

    public BaseResult r() {
        return new ck(this.lL);
    }

    public BaseResult a(int i, BaseResult baseResult, BaseResult baseResult2, boolean z) {
        baseResult = (ck) baseResult;
        ck ckVar = (ck) baseResult2;
        if (baseResult.eK == null) {
            return ckVar;
        }
        if (ckVar.eK == null) {
            return baseResult;
        }
        ckVar.eK.addAll(0, baseResult.eK);
        return ckVar;
    }

    public g s() {
        return this.lL.mLoadingProgressView;
    }
}
