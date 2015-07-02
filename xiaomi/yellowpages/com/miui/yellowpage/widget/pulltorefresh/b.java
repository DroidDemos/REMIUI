package com.miui.yellowpage.widget.pulltorefresh;

import com.miui.yellowpage.widget.pulltorefresh.PullToRefreshBase.Mode;
import com.miui.yellowpage.widget.pulltorefresh.PullToRefreshBase.Orientation;

/* compiled from: LoadingLayout */
/* synthetic */ class b {
    static final /* synthetic */ int[] gc;
    static final /* synthetic */ int[] gd;

    static {
        gd = new int[Mode.values().length];
        try {
            gd[Mode.PULL_FROM_START.ordinal()] = 1;
        } catch (NoSuchFieldError e) {
        }
        try {
            gd[Mode.PULL_FROM_END.ordinal()] = 2;
        } catch (NoSuchFieldError e2) {
        }
        gc = new int[Orientation.values().length];
        try {
            gc[Orientation.VERTICAL.ordinal()] = 1;
        } catch (NoSuchFieldError e3) {
        }
        try {
            gc[Orientation.HORIZONTAL.ordinal()] = 2;
        } catch (NoSuchFieldError e4) {
        }
    }
}
