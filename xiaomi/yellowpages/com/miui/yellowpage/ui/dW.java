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
import miui.yellowpage.YellowPageContract.YellowPage;

/* compiled from: RecentYellowPageFragment */
class dW implements k {
    final /* synthetic */ cm hS;

    private dW(cm cmVar) {
        this.hS = cmVar;
    }

    public /* bridge */ /* synthetic */ void onLoadFinished(Loader loader, Object obj) {
        a(loader, (BaseResult) obj);
    }

    public Loader onCreateLoader(int i, Bundle bundle) {
        if (i == 0) {
            this.hS.mLoader = new p(this.hS.mActivity, this.hS.Du);
            BaseRequest localRequest = new LocalRequest(this.hS.mActivity, 0);
            localRequest.setUri(YellowPage.CONTENT_URI);
            localRequest.setSelection("last_use_time > 0");
            localRequest.setProjection(new String[]{MiniDefine.at});
            localRequest.setSortOrder("last_use_time DESC LIMIT 100");
            this.hS.mLoader.a(localRequest);
        }
        return this.hS.mLoader;
    }

    public void a(Loader loader, BaseResult baseResult) {
        this.hS.Dv.updateData(((F) baseResult).hR);
    }

    public void onLoaderReset(Loader loader) {
    }

    public BaseResult a(int i, Object obj, BaseResult baseResult, boolean z) {
        F f = (F) baseResult;
        Cursor cursor = (Cursor) obj;
        if (cursor != null) {
            while (cursor.moveToNext()) {
                try {
                    com.miui.yellowpage.base.model.YellowPage fromJson = com.miui.yellowpage.base.model.YellowPage.fromJson(cursor.getString(0));
                    if (f.hR == null) {
                        f.hR = new ArrayList();
                    }
                    f.hR.add(fromJson);
                } finally {
                    cursor.close();
                }
            }
        }
        return f;
    }

    public BaseResult r() {
        return new F(this.hS);
    }

    public g s() {
        return this.hS.hh;
    }
}
