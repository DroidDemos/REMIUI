package com.miui.yellowpage.ui;

import com.miui.yellowpage.activity.ExpressAddressEditorActivity.EditType;
import com.miui.yellowpage.model.ExpressAddress.Type;

/* compiled from: ExpressAddressEditorFragment */
/* synthetic */ class cy {
    static final /* synthetic */ int[] DW;
    static final /* synthetic */ int[] DX;
    static final /* synthetic */ int[] DY;

    static {
        DY = new int[AddressOperation.values().length];
        try {
            DY[AddressOperation.ADD.ordinal()] = 1;
        } catch (NoSuchFieldError e) {
        }
        try {
            DY[AddressOperation.DELETE.ordinal()] = 2;
        } catch (NoSuchFieldError e2) {
        }
        try {
            DY[AddressOperation.UPDATE.ordinal()] = 3;
        } catch (NoSuchFieldError e3) {
        }
        DX = new int[EditType.values().length];
        try {
            DX[EditType.NEW.ordinal()] = 1;
        } catch (NoSuchFieldError e4) {
        }
        try {
            DX[EditType.EDIT.ordinal()] = 2;
        } catch (NoSuchFieldError e5) {
        }
        DW = new int[Type.values().length];
        try {
            DW[Type.SENDER.ordinal()] = 1;
        } catch (NoSuchFieldError e6) {
        }
        try {
            DW[Type.ADDRESSEE.ordinal()] = 2;
        } catch (NoSuchFieldError e7) {
        }
        try {
            DW[Type.ANY.ordinal()] = 3;
        } catch (NoSuchFieldError e8) {
        }
    }
}
