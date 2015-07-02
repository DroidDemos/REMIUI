package com.miui.yellowpage.ui;

import com.miui.yellowpage.activity.A;
import java.util.Comparator;

/* compiled from: ExpressInquiryFragment */
class ct implements Comparator {
    final /* synthetic */ bS DE;

    ct(bS bSVar) {
        this.DE = bSVar;
    }

    public /* bridge */ /* synthetic */ int compare(Object obj, Object obj2) {
        return a((A) obj, (A) obj2);
    }

    public int a(A a, A a2) {
        return a.mIndex - a2.mIndex;
    }
}
