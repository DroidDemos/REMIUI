package com.miui.yellowpage.ui;

import android.database.Cursor;
import com.miui.yellowpage.request.BaseResult;

/* compiled from: RegionPickerFragment */
class cg extends BaseResult {
    Cursor mCursor;

    private cg() {
    }

    public boolean hasData() {
        return this.mCursor != null && this.mCursor.getCount() > 0;
    }

    public BaseResult shallowClone() {
        BaseResult cgVar = new cg();
        cgVar.mCursor = this.mCursor;
        return cgVar;
    }
}
