package com.miui.yellowpage.ui;

import android.text.TextUtils;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;

/* compiled from: YellowPagePickerFragment */
class br implements OnScrollListener {
    final /* synthetic */ dv vM;

    br(dv dvVar) {
        this.vM = dvVar;
    }

    public void onScrollStateChanged(AbsListView absListView, int i) {
    }

    public void onScroll(AbsListView absListView, int i, int i2, int i3) {
        int i4 = 1;
        if (absListView.getChildCount() <= 1) {
            i4 = 0;
        }
        View childAt = absListView.getChildAt(i4);
        if (childAt instanceof YellowPagePickerListItem) {
            Object a = this.vM.a((YellowPagePickerListItem) childAt);
            if (a != null && !TextUtils.equals(a, this.vM.Jz)) {
                this.vM.JB.drawThumb(a);
                this.vM.Jz = a;
            }
        }
    }
}
