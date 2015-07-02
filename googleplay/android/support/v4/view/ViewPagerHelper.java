package android.support.v4.view;

import android.view.View;

public class ViewPagerHelper {
    public static Integer getChildViewPosition(ViewPager viewPager, View view) {
        ItemInfo ii = viewPager.infoForChild(view);
        return ii == null ? null : Integer.valueOf(ii.position);
    }
}
