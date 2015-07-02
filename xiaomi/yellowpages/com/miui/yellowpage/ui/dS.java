package com.miui.yellowpage.ui;

import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import com.alipay.sdk.cons.MiniDefine;
import com.miui.yellowpage.a.g;
import com.miui.yellowpage.a.k;
import com.miui.yellowpage.a.p;
import com.miui.yellowpage.base.request.BaseRequest;
import com.miui.yellowpage.base.request.LocalRequest;
import com.miui.yellowpage.request.BaseResult;
import java.util.ArrayList;
import java.util.Collections;
import miui.yellowpage.YellowPageContract.YellowPage;

/* compiled from: FavoriteYellowPageFragment */
class dS implements k {
    final /* synthetic */ bc mE;

    private dS(bc bcVar) {
        this.mE = bcVar;
    }

    public /* bridge */ /* synthetic */ void onLoadFinished(Loader loader, Object obj) {
        a(loader, (BaseResult) obj);
    }

    public Loader onCreateLoader(int i, Bundle bundle) {
        if (i == 0) {
            this.mE.mLoader = new p(this.mE.mActivity, this.mE.uB);
            BaseRequest localRequest = new LocalRequest(this.mE.mActivity, 0);
            localRequest.setUri(YellowPage.CONTENT_URI);
            localRequest.setSelection("favorite = 1");
            localRequest.setProjection(new String[]{MiniDefine.at});
            this.mE.mLoader.a(localRequest);
        }
        return this.mE.mLoader;
    }

    public void a(Loader loader, BaseResult baseResult) {
        Object obj = ((aj) baseResult).hR;
        if (obj != null) {
            Collections.sort(obj, new bg(this.mE));
        }
        this.mE.uC.updateData(obj);
    }

    public void onLoaderReset(Loader loader) {
    }

    public BaseResult a(int i, Object obj, BaseResult baseResult, boolean z) {
        aj ajVar = (aj) baseResult;
        Cursor cursor = (Cursor) obj;
        if (cursor != null) {
            while (cursor.moveToNext()) {
                try {
                    com.miui.yellowpage.base.model.YellowPage fromJson = com.miui.yellowpage.base.model.YellowPage.fromJson(cursor.getString(0));
                    if (ajVar.hR == null) {
                        ajVar.hR = new ArrayList();
                    }
                    ajVar.hR.add(fromJson);
                } finally {
                    cursor.close();
                }
            }
        }
        return ajVar;
    }

    public BaseResult r() {
        return new aj(this.mE);
    }

    public g s() {
        return this.mE.hh;
    }
}
