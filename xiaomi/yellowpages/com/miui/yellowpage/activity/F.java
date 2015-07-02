package com.miui.yellowpage.activity;

import java.util.Comparator;
import miui.yellowpage.AntispamCategory;

/* compiled from: MarkNumberActivity */
class F implements Comparator {
    final /* synthetic */ MarkNumberActivity rR;

    F(MarkNumberActivity markNumberActivity) {
        this.rR = markNumberActivity;
    }

    public /* bridge */ /* synthetic */ int compare(Object obj, Object obj2) {
        return a((AntispamCategory) obj, (AntispamCategory) obj2);
    }

    public int a(AntispamCategory antispamCategory, AntispamCategory antispamCategory2) {
        if (antispamCategory.getCategoryType() != antispamCategory2.getCategoryType()) {
            return antispamCategory.getCategoryType() - antispamCategory2.getCategoryType();
        }
        return antispamCategory.getCategoryId() - antispamCategory2.getCategoryId();
    }
}
