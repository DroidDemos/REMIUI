package android.support.v4.view;

import android.view.View;
import java.util.Comparator;

/* compiled from: ViewPager */
class w implements Comparator {
    w() {
    }

    public int compare(View view, View view2) {
        S s = (S) view.getLayoutParams();
        S s2 = (S) view2.getLayoutParams();
        if (s.isDecor != s2.isDecor) {
            return s.isDecor ? 1 : -1;
        } else {
            return s.position - s2.position;
        }
    }
}
