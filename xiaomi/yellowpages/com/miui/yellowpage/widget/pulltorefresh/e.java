package com.miui.yellowpage.widget.pulltorefresh;

import com.miui.yellowpage.widget.pulltorefresh.PullToRefreshBase.AnimationStyle;
import com.miui.yellowpage.widget.pulltorefresh.PullToRefreshBase.Mode;
import com.miui.yellowpage.widget.pulltorefresh.PullToRefreshBase.Orientation;
import com.miui.yellowpage.widget.pulltorefresh.PullToRefreshBase.State;

/* compiled from: PullToRefreshBase */
/* synthetic */ class e {
    static final /* synthetic */ int[] gc;
    static final /* synthetic */ int[] gd;
    static final /* synthetic */ int[] kE;
    static final /* synthetic */ int[] kF;

    static {
        kF = new int[AnimationStyle.values().length];
        try {
            kF[AnimationStyle.FLIP.ordinal()] = 1;
        } catch (NoSuchFieldError e) {
        }
        gd = new int[Mode.values().length];
        try {
            gd[Mode.PULL_FROM_END.ordinal()] = 1;
        } catch (NoSuchFieldError e2) {
        }
        try {
            gd[Mode.PULL_FROM_START.ordinal()] = 2;
        } catch (NoSuchFieldError e3) {
        }
        try {
            gd[Mode.MANUAL_REFRESH_ONLY.ordinal()] = 3;
        } catch (NoSuchFieldError e4) {
        }
        try {
            gd[Mode.BOTH.ordinal()] = 4;
        } catch (NoSuchFieldError e5) {
        }
        kE = new int[State.values().length];
        try {
            kE[State.RESET.ordinal()] = 1;
        } catch (NoSuchFieldError e6) {
        }
        try {
            kE[State.PULL_TO_REFRESH.ordinal()] = 2;
        } catch (NoSuchFieldError e7) {
        }
        try {
            kE[State.RELEASE_TO_REFRESH.ordinal()] = 3;
        } catch (NoSuchFieldError e8) {
        }
        try {
            kE[State.REFRESHING.ordinal()] = 4;
        } catch (NoSuchFieldError e9) {
        }
        try {
            kE[State.MANUAL_REFRESHING.ordinal()] = 5;
        } catch (NoSuchFieldError e10) {
        }
        try {
            kE[State.OVERSCROLLING.ordinal()] = 6;
        } catch (NoSuchFieldError e11) {
        }
        gc = new int[Orientation.values().length];
        try {
            gc[Orientation.HORIZONTAL.ordinal()] = 1;
        } catch (NoSuchFieldError e12) {
        }
        try {
            gc[Orientation.VERTICAL.ordinal()] = 2;
        } catch (NoSuchFieldError e13) {
        }
    }
}
