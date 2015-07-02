package com.miui.yellowpage.ui;

import android.content.Loader;
import android.os.Bundle;
import com.miui.yellowpage.a.a;
import com.miui.yellowpage.a.b;
import com.miui.yellowpage.a.g;
import com.miui.yellowpage.a.k;
import com.miui.yellowpage.model.o;
import com.miui.yellowpage.request.BaseResult;
import com.miui.yellowpage.request.BaseResult.State;
import com.miui.yellowpage.utils.x;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import miui.yellowpage.YellowPageUtils;

/* compiled from: OrderFragment */
class bl implements b, k {
    final /* synthetic */ OrderFragment nh;

    private bl(OrderFragment orderFragment) {
        this.nh = orderFragment;
    }

    public /* bridge */ /* synthetic */ void onLoadFinished(Loader loader, Object obj) {
        a(loader, (BaseResult) obj);
    }

    public Loader onCreateLoader(int i, Bundle bundle) {
        if (i == 1) {
            this.nh.mLoader = new a(this.nh.mActivity, this.nh.zD, "pn", 20, 0);
            this.nh.mLoader.a(this.nh.gx());
            this.nh.mLoader.a((b) this);
        }
        return this.nh.mLoader;
    }

    public void a(Loader loader, BaseResult baseResult) {
        if (baseResult.getState() == State.OK) {
            this.nh.zC.updateData(((dl) baseResult).Ic);
        }
    }

    public void onLoaderReset(Loader loader) {
    }

    public BaseResult a(int i, Object obj, BaseResult baseResult, boolean z) {
        dl dlVar = (dl) baseResult;
        if (i == 1) {
            ArrayList bD = o.bD((String) obj);
            if (bD != null) {
                if (!z) {
                    Set hashSet = new HashSet();
                    Iterator it = bD.iterator();
                    while (it.hasNext()) {
                        hashSet.add(String.valueOf(((o) it.next()).ga()));
                    }
                    x.a(this.nh.mActivity, hashSet);
                }
                Iterator it2 = bD.iterator();
                while (it2.hasNext()) {
                    o oVar = (o) it2.next();
                    oVar.bC(YellowPageUtils.getProvider(this.nh.mActivity, Integer.valueOf(oVar.ga()).intValue()).getName());
                }
                dlVar.Ic = bD;
            }
        }
        return dlVar;
    }

    public BaseResult r() {
        return new dl(this.nh);
    }

    public g s() {
        return this.nh.hh;
    }

    public BaseResult a(int i, BaseResult baseResult, BaseResult baseResult2, boolean z) {
        baseResult = (dl) baseResult;
        dl dlVar = (dl) baseResult2;
        if (baseResult.Ic == null) {
            return dlVar;
        }
        if (dlVar.Ic == null) {
            return baseResult;
        }
        dlVar.Ic.addAll(0, baseResult.Ic);
        return dlVar;
    }
}
