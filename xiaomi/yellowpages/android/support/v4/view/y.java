package android.support.v4.view;

import java.util.Comparator;

/* compiled from: ViewPager */
final class y implements Comparator {
    y() {
    }

    public /* bridge */ /* synthetic */ int compare(Object obj, Object obj2) {
        return a((U) obj, (U) obj2);
    }

    public int a(U u, U u2) {
        return u.position - u2.position;
    }
}
