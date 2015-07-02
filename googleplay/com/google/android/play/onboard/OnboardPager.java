package com.google.android.play.onboard;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import com.google.android.play.widget.UserAwareViewPager;
import com.google.android.wallet.instrumentmanager.R;

public class OnboardPager extends UserAwareViewPager {
    protected boolean mAllowSwipeToNext;
    protected boolean mAllowSwipeToPrevious;
    protected float mLastX;

    public OnboardPager(Context context) {
        super(context);
        this.mAllowSwipeToNext = true;
        this.mAllowSwipeToPrevious = true;
    }

    public OnboardPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mAllowSwipeToNext = true;
        this.mAllowSwipeToPrevious = true;
    }

    public void setAllowSwipeToNext(boolean allow) {
        this.mAllowSwipeToNext = allow;
    }

    public void setAllowSwipeToPrevious(boolean allow) {
        this.mAllowSwipeToPrevious = allow;
    }

    protected boolean pageLeft() {
        return allowScrolling(-1) ? super.pageLeft() : false;
    }

    protected boolean pageRight() {
        return allowScrolling(1) ? super.pageRight() : false;
    }

    public boolean onInterceptTouchEvent(MotionEvent event) {
        if (event.getPointerCount() > 1 || !shouldAllowTouchEvent(event)) {
            return false;
        }
        try {
            return super.onInterceptTouchEvent(event);
        } catch (IllegalArgumentException e) {
            event.setAction(3);
            return super.onInterceptTouchEvent(event);
        }
    }

    public boolean onTouchEvent(MotionEvent event) {
        if (!shouldAllowTouchEvent(event)) {
            return true;
        }
        try {
            return super.onTouchEvent(event);
        } catch (IllegalArgumentException e) {
            event.setAction(3);
            return super.onTouchEvent(event);
        }
    }

    protected boolean shouldAllowTouchEvent(MotionEvent event) {
        boolean multiTouch;
        if (event.getPointerCount() > 1) {
            multiTouch = true;
        } else {
            multiTouch = false;
        }
        switch (event.getActionMasked()) {
            case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                this.mLastX = event.getX(0);
                break;
            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                if (multiTouch) {
                    return false;
                }
                float x = event.getX(0);
                float dX = x - this.mLastX;
                this.mLastX = x;
                if (!allowScrolling((int) Math.signum(-dX))) {
                    return false;
                }
                break;
            case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                return false;
        }
        return true;
    }

    protected boolean allowScrolling(int direction) {
        int currentVisualItem = getCurrentVisualItem();
        int count = getAdapter() == null ? 0 : getAdapter().getCount();
        if (direction < 0 && currentVisualItem > 0) {
            return isRtl() ? this.mAllowSwipeToNext : this.mAllowSwipeToPrevious;
        } else {
            if (direction <= 0 || currentVisualItem >= count - 1) {
                return true;
            }
            return isRtl() ? this.mAllowSwipeToPrevious : this.mAllowSwipeToNext;
        }
    }
}
