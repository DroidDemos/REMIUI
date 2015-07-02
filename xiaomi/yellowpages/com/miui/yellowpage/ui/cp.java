package com.miui.yellowpage.ui;

import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import com.miui.yellowpage.a.g;
import com.miui.yellowpage.a.k;
import com.miui.yellowpage.a.p;
import com.miui.yellowpage.base.model.YellowPage;
import com.miui.yellowpage.model.YellowPagePhonesEntry;
import com.miui.yellowpage.request.BaseResult;
import com.miui.yellowpage.utils.e;
import java.util.ArrayList;
import java.util.List;
import miui.yellowpage.YellowPagePhone;

/* compiled from: YellowPagePhonesFragment */
class cp implements k {
    final /* synthetic */ ap Dy;

    private cp(ap apVar) {
        this.Dy = apVar;
    }

    public /* bridge */ /* synthetic */ void onLoadFinished(Loader loader, Object obj) {
        a(loader, (BaseResult) obj);
    }

    public BaseResult a(int i, Object obj, BaseResult baseResult, boolean z) {
        if (i == 0 && obj != null) {
            dg dgVar = (dg) baseResult;
            Cursor cursor = (Cursor) obj;
            try {
                if (cursor.moveToNext()) {
                    this.Dy.lh = YellowPage.fromJson(cursor.getString(0));
                    dgVar.list = YellowPagePhonesEntry.a(this.Dy.mActivity, this.Dy.lh);
                }
                cursor.close();
                List<YellowPagePhone> c = e.c(this.Dy.mActivity, this.Dy.ld);
                if (c != null) {
                    if (dgVar.list == null) {
                        dgVar.list = new ArrayList();
                    }
                    for (YellowPagePhone yellowPagePhone : c) {
                        if (this.Dy.lh.getPhoneInfo(this.Dy.mActivity, yellowPagePhone.getNumber()) == null) {
                            dgVar.list.add(YellowPagePhonesEntry.a(this.Dy.mActivity, yellowPagePhone));
                        }
                    }
                }
            } catch (Throwable th) {
                cursor.close();
            }
        }
        return baseResult;
    }

    public BaseResult r() {
        return new dg(this.Dy);
    }

    public g s() {
        return null;
    }

    public Loader onCreateLoader(int i, Bundle bundle) {
        if (i == 0) {
            this.Dy.mLoader = new p(this.Dy.mActivity, this.Dy.nj);
            this.Dy.mLoader.a(this.Dy.bR());
        }
        return this.Dy.mLoader;
    }

    public void a(Loader loader, BaseResult baseResult) {
        dg dgVar = (dg) baseResult;
        if (dgVar.hasData()) {
            this.Dy.ni.updateData(dgVar.list);
        }
    }

    public void onLoaderReset(Loader loader) {
    }
}
