package com.miui.yellowpage.activity;

import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import com.miui.yellowpage.a.g;
import com.miui.yellowpage.a.k;
import com.miui.yellowpage.a.p;
import com.miui.yellowpage.base.model.YellowPage;
import com.miui.yellowpage.request.BaseResult;

/* compiled from: QuickYellowPageActivity */
class b implements k {
    final /* synthetic */ QuickYellowPageActivity bU;

    private b(QuickYellowPageActivity quickYellowPageActivity) {
        this.bU = quickYellowPageActivity;
    }

    public /* bridge */ /* synthetic */ void onLoadFinished(Loader loader, Object obj) {
        a(loader, (BaseResult) obj);
    }

    public BaseResult a(int i, Object obj, BaseResult baseResult, boolean z) {
        as asVar = (as) baseResult;
        if (i == 0 && obj != null) {
            Cursor cursor = (Cursor) obj;
            try {
                if (cursor.moveToNext()) {
                    Object string = cursor.getString(0);
                    if (!TextUtils.isEmpty(string)) {
                        asVar.Cd = YellowPage.fromJson(string);
                    }
                }
                cursor.close();
            } catch (Throwable th) {
                cursor.close();
            }
        }
        return asVar;
    }

    public BaseResult r() {
        return new as(this.bU);
    }

    public g s() {
        return null;
    }

    public Loader onCreateLoader(int i, Bundle bundle) {
        if (i != 0) {
            return null;
        }
        Loader pVar = new p(this.bU, this.bU.HL);
        pVar.a(this.bU.cy());
        return pVar;
    }

    public void a(Loader loader, BaseResult baseResult) {
        if (baseResult != null) {
            this.bU.lh = ((as) baseResult).Cd;
            this.bU.hN();
        }
    }

    public void onLoaderReset(Loader loader) {
    }
}
