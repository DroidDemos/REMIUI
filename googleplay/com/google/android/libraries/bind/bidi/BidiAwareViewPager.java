package com.google.android.libraries.bind.bidi;

import android.content.Context;
import android.os.Build.VERSION;
import android.support.v4.view.ViewPagerShim;
import android.util.AttributeSet;

public class BidiAwareViewPager extends ViewPagerShim {
    public BidiAwareViewPager(Context context) {
        super(context);
    }

    public BidiAwareViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public boolean isRtl() {
        return VERSION.SDK_INT >= 18 && getLayoutDirection() == 1;
    }

    public int getCurrentLogicalItem() {
        return BidiPagingHelper.getLogicalPosition(getAdapter(), super.getCurrentItem());
    }

    public int getCurrentVisualItem() {
        return super.getCurrentItem();
    }

    public void setCurrentLogicalItem(int logicalItem) {
        setCurrentItem(BidiPagingHelper.getVisualPosition(getAdapter(), logicalItem));
    }

    public void onRtlPropertiesChanged(int layoutDirection) {
        boolean z = true;
        super.onRtlPropertiesChanged(layoutDirection);
        int currentLogicalItem = getCurrentLogicalItem();
        if (getAdapter() instanceof BidiAwarePagerAdapter) {
            BidiAwarePagerAdapter bidiAwarePagerAdapter = (BidiAwarePagerAdapter) getAdapter();
            if (layoutDirection != 1) {
                z = false;
            }
            bidiAwarePagerAdapter.setRtl(z);
        }
        setCurrentLogicalItem(currentLogicalItem);
    }
}
