package com.miui.yellowpage.ui;

import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import com.miui.yellowpage.a.g;
import com.miui.yellowpage.a.k;
import com.miui.yellowpage.a.p;
import com.miui.yellowpage.model.f;
import com.miui.yellowpage.model.n;
import com.miui.yellowpage.model.s;
import com.miui.yellowpage.model.t;
import com.miui.yellowpage.model.x;
import com.miui.yellowpage.request.BaseResult;

/* compiled from: CallLogFragment */
class bp implements k {
    final /* synthetic */ cO cC;

    private bp(cO cOVar) {
        this.cC = cOVar;
    }

    public /* bridge */ /* synthetic */ void onLoadFinished(Loader loader, Object obj) {
        a(loader, (BaseResult) obj);
    }

    public Loader onCreateLoader(int i, Bundle bundle) {
        if (i == 1) {
            this.cC.mLoader = new p(this.cC.mActivity, this.cC.ER);
            this.cC.mLoader.g(this.cC.bk());
        }
        return this.cC.mLoader;
    }

    public void a(Loader loader, BaseResult baseResult) {
        f fVar = (f) baseResult;
        if (this.cC.EQ != null) {
            this.cC.EQ.s(fVar.hasData());
        }
        this.cC.ET.updateData(fVar.cB);
    }

    public void onLoaderReset(Loader loader) {
    }

    public BaseResult a(int i, Object obj, BaseResult baseResult, boolean z) {
        f fVar = (f) baseResult;
        if (i == 1) {
            Cursor cursor = (Cursor) obj;
            if (cursor != null) {
                try {
                    n nVar = new n(this.cC.mActivity, this.cC.ES);
                    f fVar2 = new f(this.cC.mActivity, this.cC.ES);
                    while (cursor.moveToNext()) {
                        long j = cursor.getLong(4);
                        long j2 = cursor.getLong(0);
                        long j3 = cursor.getLong(1);
                        String string = cursor.getString(2);
                        int i2 = cursor.getInt(3);
                        String string2 = cursor.getString(5);
                        int i3 = cursor.getInt(6);
                        int i4 = cursor.getInt(8);
                        t a = nVar.a(Long.valueOf(j2), string);
                        s a2 = fVar2.a(Long.valueOf(j2), Long.valueOf((1000 * j3) + j2), string);
                        String str = null;
                        if (a2 != null) {
                            str = a2.Da;
                        }
                        f fVar3 = fVar;
                        fVar3.a(new x().u(j).ah(i4).s(j2).t(j3).cs(string).ai(i2).ct(string2).aj(i3).a(a).cr(str));
                    }
                } finally {
                    cursor.close();
                }
            }
        }
        return fVar;
    }

    public BaseResult r() {
        return new f(this.cC);
    }

    public g s() {
        return null;
    }
}
