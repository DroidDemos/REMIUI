package com.miui.yellowpage.ui;

import android.content.Loader;
import android.os.Bundle;
import com.miui.yellowpage.a.g;
import com.miui.yellowpage.a.k;
import com.miui.yellowpage.a.p;
import com.miui.yellowpage.model.ExpressAddress;
import com.miui.yellowpage.request.BaseResult;
import com.ta.utdid2.core.persistent.TransactionXMLFile;

/* compiled from: ExpressAddressListFragment */
class aZ implements k {
    final /* synthetic */ ExpressAddressListFragment ep;

    private aZ(ExpressAddressListFragment expressAddressListFragment) {
        this.ep = expressAddressListFragment;
    }

    public /* bridge */ /* synthetic */ void onLoadFinished(Loader loader, Object obj) {
        a(loader, (BaseResult) obj);
    }

    public Loader onCreateLoader(int i, Bundle bundle) {
        if (i == 0) {
            this.ep.mLoader = new p(this.ep.mActivity, this.ep.bO);
            this.ep.mLoader.a(this.ep.q());
        }
        return this.ep.mLoader;
    }

    public void a(Loader loader, BaseResult baseResult) {
        aS aSVar = (aS) baseResult;
        if (aSVar.hasData()) {
            this.ep.bL.updateData(aSVar.rS);
            this.ep.bN = aSVar.rS;
            this.ep.mRootView.setVisibility(0);
            this.ep.setHasOptionsMenu(true);
            return;
        }
        this.ep.mRootView.setVisibility(8);
        this.ep.setHasOptionsMenu(false);
    }

    public BaseResult a(int i, Object obj, BaseResult baseResult, boolean z) {
        aS aSVar = (aS) baseResult;
        switch (i) {
            case TransactionXMLFile.MODE_WORLD_WRITEABLE /*2*/:
                if (obj != null) {
                    aSVar.rS = ExpressAddress.bk((String) obj);
                    break;
                }
                break;
        }
        return aSVar;
    }

    public BaseResult r() {
        return new aS(this.ep);
    }

    public g s() {
        return this.ep.mLoadingProgressView;
    }

    public void onLoaderReset(Loader loader) {
    }
}
