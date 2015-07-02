package android.support.v4.view;

import android.content.Context;
import android.util.AttributeSet;

public class ViewPagerShim extends ViewPager {
    public ViewPagerShim(Context context) {
        super(context);
    }

    public ViewPagerShim(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    protected boolean pageLeft() {
        return super.pageLeft();
    }

    protected boolean pageRight() {
        return super.pageRight();
    }
}
