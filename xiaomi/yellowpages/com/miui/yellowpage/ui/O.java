package com.miui.yellowpage.ui;

import android.content.Loader;
import android.os.Bundle;
import com.miui.yellowpage.R;
import com.miui.yellowpage.a.g;
import com.miui.yellowpage.a.k;
import com.miui.yellowpage.a.p;
import com.miui.yellowpage.base.reference.RefMethods.DateUtils;
import com.miui.yellowpage.model.j;
import com.miui.yellowpage.request.BaseResult;
import org.json.JSONObject;

/* compiled from: RechargeOrderDetailFragment */
public class O implements k {
    final /* synthetic */ af dz;

    public O(af afVar) {
        this.dz = afVar;
    }

    public /* bridge */ /* synthetic */ void onLoadFinished(Loader loader, Object obj) {
        a(loader, (BaseResult) obj);
    }

    public Loader onCreateLoader(int i, Bundle bundle) {
        if (i == 0) {
            this.dz.mLoader = new p(this.dz.mActivity, this.dz.mj);
            this.dz.mLoader.a(this.dz.R(this.dz.mId));
        }
        return this.dz.mLoader;
    }

    public void a(Loader loader, BaseResult baseResult) {
        this.dz.mi = ((k) baseResult).dy;
        if (this.dz.mi != null) {
            this.dz.mb.setText(this.dz.mi.df());
            this.dz.mc.setText(this.dz.mi.dg());
            this.dz.md.setText(this.dz.mActivity.getString(R.string.order_status_label, new Object[]{this.dz.mi.dj()}));
            this.dz.me.setText(this.dz.mActivity.getString(R.string.order_price_label, new Object[]{this.dz.mi.dh()}));
            this.dz.ia.setText(this.dz.mActivity.getString(R.string.order_id_label, new Object[]{this.dz.mi.ac()}));
            this.dz.mf.setText(this.dz.mActivity.getString(R.string.order_date_label, new Object[]{DateUtils.getFormattedTime(this.dz.mActivity, this.dz.mi.dk())}));
            if (this.dz.mi.dm()) {
                this.dz.mg.setVisibility(0);
            } else {
                this.dz.mg.setVisibility(8);
            }
            this.dz.mRootView.setVisibility(0);
            return;
        }
        this.dz.mRootView.setVisibility(8);
    }

    public void onLoaderReset(Loader loader) {
    }

    public BaseResult a(int i, Object obj, BaseResult baseResult, boolean z) {
        k kVar = (k) baseResult;
        kVar.dy = j.f(new JSONObject((String) obj));
        return kVar;
    }

    public BaseResult r() {
        return new k(this.dz);
    }

    public g s() {
        return this.dz.mLoadingProgressView;
    }
}
