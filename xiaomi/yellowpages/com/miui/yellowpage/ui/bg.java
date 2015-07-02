package com.miui.yellowpage.ui;

import com.miui.yellowpage.base.model.YellowPage;
import java.text.Collator;
import java.util.Comparator;
import java.util.Locale;

/* compiled from: FavoriteYellowPageFragment */
public class bg implements Comparator {
    final /* synthetic */ bc mE;

    public bg(bc bcVar) {
        this.mE = bcVar;
    }

    public /* bridge */ /* synthetic */ int compare(Object obj, Object obj2) {
        return a((YellowPage) obj, (YellowPage) obj2);
    }

    public int a(YellowPage yellowPage, YellowPage yellowPage2) {
        return Collator.getInstance(Locale.CHINESE).compare(yellowPage.getName(), yellowPage2.getName());
    }
}
