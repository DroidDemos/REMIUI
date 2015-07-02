package com.miui.yellowpage.ui;

import android.database.Cursor;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import com.miui.yellowpage.a.d;

/* compiled from: RegionPickerFragment */
class ak implements OnItemClickListener {
    final /* synthetic */ RegionPickerFragment mQ;

    private ak(RegionPickerFragment regionPickerFragment) {
        this.mQ = regionPickerFragment;
    }

    public void onItemClick(AdapterView adapterView, View view, int i, long j) {
        this.mQ.a(this.mQ.hj, (Cursor) this.mQ.mX.getItem(i));
        this.mQ.mW = new dP(this.mQ);
        this.mQ.mRequestLoader = new d();
        this.mQ.mRequestLoader.a(this.mQ.mW);
        this.mQ.mRequestLoader.a(this.mQ.a(String.valueOf(j), RequestPurpose.CHECK_CHILDREN), new cg());
        this.mQ.mU = String.valueOf(j);
    }
}
