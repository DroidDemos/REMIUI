package com.miui.yellowpage.ui;

import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import com.miui.yellowpage.a.g;
import com.miui.yellowpage.a.k;
import com.miui.yellowpage.a.p;
import com.miui.yellowpage.request.BaseResult;

/* compiled from: ExpressContactEditorFragment */
public class aQ implements k {
    final /* synthetic */ dO nX;

    protected aQ(dO dOVar) {
        this.nX = dOVar;
    }

    public /* bridge */ /* synthetic */ void onLoadFinished(Loader loader, Object obj) {
        a(loader, (BaseResult) obj);
    }

    public Loader onCreateLoader(int i, Bundle bundle) {
        if (i == 101) {
            Uri uri = (Uri) bundle.getParcelable("uri");
            this.nX.KB = new p(this.nX.mActivity, this.nX.KC);
            this.nX.KB.a(dO.a(this.nX.mActivity, uri, 101));
            return this.nX.KB;
        } else if (i != 102) {
            return null;
        } else {
            String string = bundle.getString("lookupkey");
            this.nX.KB = new p(this.nX.mActivity, this.nX.KC);
            this.nX.KB.a(dO.a(this.nX.mActivity, string, 102));
            return this.nX.KB;
        }
    }

    public void a(Loader loader, BaseResult baseResult) {
        dd ddVar = (dd) baseResult;
        if (!ddVar.hasData()) {
            return;
        }
        if (ddVar.Hd == 101) {
            Bundle bundle = new Bundle();
            bundle.putString("lookupkey", ddVar.Hc);
            this.nX.getLoaderManager().restartLoader(102, bundle, this.nX.KC);
        } else if (ddVar.Hd == 102) {
            this.nX.a(ddVar);
        }
    }

    public BaseResult a(int i, Object obj, BaseResult baseResult, boolean z) {
        dd ddVar = (dd) baseResult;
        ddVar.Hd = i;
        Cursor cursor;
        if (i == 101) {
            if (obj != null) {
                cursor = (Cursor) obj;
                try {
                    if (cursor.moveToNext()) {
                        ddVar.Hc = cursor.getString(0);
                    }
                    cursor.close();
                } catch (Throwable th) {
                    cursor.close();
                }
            }
        } else if (i == 102 && obj != null) {
            cursor = (Cursor) obj;
            while (cursor.moveToNext()) {
                this.nX.a(cursor);
                String string = cursor.getString(1);
                if ("vnd.android.cursor.item/phone_v2".equals(string)) {
                    ddVar.Hb = dO.cp(cursor.getString(2));
                } else {
                    try {
                        if ("vnd.android.cursor.item/postal-address_v2".equals(string)) {
                            ddVar.address = cursor.getString(2);
                        } else if ("vnd.android.cursor.item/name".equals(string)) {
                            ddVar.name = cursor.getString(2);
                        }
                    } finally {
                        cursor.close();
                    }
                }
            }
        }
        return ddVar;
    }

    public BaseResult r() {
        return new dd();
    }

    public void onLoaderReset(Loader loader) {
    }

    public g s() {
        return null;
    }
}
