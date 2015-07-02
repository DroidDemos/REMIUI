package com.miui.yellowpage.widget.pulltorefresh;

import com.miui.yellowpage.widget.pulltorefresh.PullToRefreshBase.Mode;

/* compiled from: FlipLoadingLayout */
/* synthetic */ class l {
    static final /* synthetic */ int[] gd;

    static {
        gd = new int[Mode.values().length];
        try {
            gd[Mode.PULL_FROM_END.ordinal()] = 1;
        } catch (NoSuchFieldError e) {
        }
        try {
            gd[Mode.PULL_FROM_START.ordinal()] = 2;
        } catch (NoSuchFieldError e2) {
        }
    }
}
