package com.miui.yellowpage.ui;

import android.database.Cursor;
import com.miui.yellowpage.request.BaseResult;

/* compiled from: InquiryHistoryFragment */
class aJ extends BaseResult {
    Cursor qf;
    final /* synthetic */ aH qg;

    private aJ(aH aHVar) {
        this.qg = aHVar;
    }

    public boolean hasData() {
        return (this.qf == null || this.qf.isClosed()) ? false : true;
    }

    public BaseResult shallowClone() {
        BaseResult aJVar = new aJ(this.qg);
        aJVar.qf = this.qf;
        return aJVar;
    }
}
