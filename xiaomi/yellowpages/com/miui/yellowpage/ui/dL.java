package com.miui.yellowpage.ui;

import com.miui.yellowpage.model.ExpressAddress.Type;
import com.miui.yellowpage.ui.ExpressAddressListFragment.ActionType;

/* compiled from: ExpressAddressListFragment */
/* synthetic */ class dL {
    static final /* synthetic */ int[] DW;
    static final /* synthetic */ int[] Kh;

    static {
        Kh = new int[ActionType.values().length];
        try {
            Kh[ActionType.MANAGE.ordinal()] = 1;
        } catch (NoSuchFieldError e) {
        }
        try {
            Kh[ActionType.PICK.ordinal()] = 2;
        } catch (NoSuchFieldError e2) {
        }
        DW = new int[Type.values().length];
        try {
            DW[Type.SENDER.ordinal()] = 1;
        } catch (NoSuchFieldError e3) {
        }
        try {
            DW[Type.ADDRESSEE.ordinal()] = 2;
        } catch (NoSuchFieldError e4) {
        }
        try {
            DW[Type.ANY.ordinal()] = 3;
        } catch (NoSuchFieldError e5) {
        }
    }
}
