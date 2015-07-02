package com.miui.yellowpage.ui;

import android.database.Cursor;
import com.miui.yellowpage.R;
import com.miui.yellowpage.a.c;
import com.miui.yellowpage.request.BaseResult;

/* compiled from: CityPickerFragment */
class cD implements c {
    final /* synthetic */ bD ge;

    private cD(bD bDVar) {
        this.ge = bDVar;
    }

    public void onPreRequest(int i) {
        this.ge.yz.setVisibility(0);
        this.ge.yw.setText(this.ge.getString(R.string.navigation_search_positioning));
    }

    public void onRequestFinished(int i, BaseResult baseResult) {
        bC bCVar = (bC) baseResult;
        this.ge.yz.setVisibility(8);
        if (i == 1 && bCVar != null && bCVar.hasData()) {
            this.ge.a(bCVar);
        } else {
            this.ge.onError();
        }
    }

    public BaseResult onParseRequest(int i, Object obj, BaseResult baseResult) {
        BaseResult bCVar = new bC(this.ge);
        if (i == 1 && obj != null) {
            Cursor cursor = (Cursor) obj;
            try {
                if (cursor.moveToNext()) {
                    bCVar.yn = cursor.getString(1);
                    bCVar.ym = cursor.getString(0);
                }
                cursor.close();
            } catch (Throwable th) {
                cursor.close();
            }
        }
        return bCVar;
    }
}
