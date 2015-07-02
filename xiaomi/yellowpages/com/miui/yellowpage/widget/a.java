package com.miui.yellowpage.widget;

import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;

/* compiled from: PageScrollListener */
public class a implements OnScrollListener {
    private Runnable ls;
    private boolean lt;
    private boolean lu;

    public a(Runnable runnable) {
        this.ls = runnable;
        this.lt = false;
        this.lu = false;
    }

    public void onScroll(AbsListView absListView, int i, int i2, int i3) {
        boolean z;
        boolean z2 = true;
        if (i3 == i + i2) {
            z = true;
        } else {
            z = false;
        }
        this.lt = z;
        if (i3 != i2) {
            z2 = false;
        }
        this.lu = z2;
    }

    public void onScrollStateChanged(AbsListView absListView, int i) {
        if (i == 0 && this.lt && this.ls != null && !this.lu) {
            this.ls.run();
        }
    }
}
