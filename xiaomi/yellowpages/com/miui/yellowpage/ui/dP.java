package com.miui.yellowpage.ui;

import android.content.Intent;
import android.database.Cursor;
import com.miui.yellowpage.a.c;
import com.miui.yellowpage.request.BaseResult;

/* compiled from: RegionPickerFragment */
class dP implements c {
    final /* synthetic */ RegionPickerFragment mQ;

    private dP(RegionPickerFragment regionPickerFragment) {
        this.mQ = regionPickerFragment;
    }

    public void onPreRequest(int i) {
    }

    public void onRequestFinished(int i, BaseResult baseResult) {
        cg cgVar = (cg) baseResult;
        try {
            Intent intent;
            if (cgVar.hasData()) {
                intent = new Intent("com.miui.yellowpage.action.PICK_REGION");
                intent.putExtra("extra_address", this.mQ.hj);
                intent.putExtra("extra_region_id", this.mQ.mU);
                this.mQ.startActivityForResult(intent, 2);
            } else {
                intent = new Intent();
                intent.putExtra("extra_address", this.mQ.hj);
                this.mQ.mActivity.setResult(-1, intent);
                this.mQ.mActivity.finish();
            }
            if (cgVar.mCursor != null && !cgVar.mCursor.isClosed()) {
                cgVar.mCursor.close();
            }
        } catch (Throwable th) {
            if (!(cgVar.mCursor == null || cgVar.mCursor.isClosed())) {
                cgVar.mCursor.close();
            }
        }
    }

    public BaseResult onParseRequest(int i, Object obj, BaseResult baseResult) {
        cg cgVar = (cg) baseResult;
        if (obj != null && (obj instanceof Cursor)) {
            cgVar.mCursor = (Cursor) obj;
        }
        return cgVar;
    }
}
