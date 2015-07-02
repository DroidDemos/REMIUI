package com.google.android.libraries.bind.bidi;

import android.support.v4.view.PagerAdapter;

public class BidiPagingHelper {
    protected static int swapPosition(PagerAdapter pagerAdapter, int initialPosition) {
        return initialPosition < 0 ? initialPosition : (pagerAdapter.getCount() - 1) - initialPosition;
    }

    public static int getLogicalPosition(PagerAdapter pagerAdapter, int visualPosition) {
        if ((pagerAdapter instanceof BidiAwarePagerAdapter) && ((BidiAwarePagerAdapter) pagerAdapter).isRtl()) {
            return swapPosition(pagerAdapter, visualPosition);
        }
        return visualPosition;
    }

    public static int getVisualPosition(PagerAdapter pagerAdapter, int logicalPosition) {
        if ((pagerAdapter instanceof BidiAwarePagerAdapter) && ((BidiAwarePagerAdapter) pagerAdapter).isRtl()) {
            return swapPosition(pagerAdapter, logicalPosition);
        }
        return logicalPosition;
    }
}
