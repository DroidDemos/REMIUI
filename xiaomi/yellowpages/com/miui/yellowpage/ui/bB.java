package com.miui.yellowpage.ui;

import com.miui.yellowpage.request.BaseResult.State;

/* compiled from: SendExpressResultFragment */
/* synthetic */ class bB {
    static final /* synthetic */ int[] xn;

    static {
        xn = new int[State.values().length];
        try {
            xn[State.NETWORK_ERROR.ordinal()] = 1;
        } catch (NoSuchFieldError e) {
        }
        try {
            xn[State.DATA_ERROR.ordinal()] = 2;
        } catch (NoSuchFieldError e2) {
        }
        try {
            xn[State.SERVICE_ERROR.ordinal()] = 3;
        } catch (NoSuchFieldError e3) {
        }
    }
}
