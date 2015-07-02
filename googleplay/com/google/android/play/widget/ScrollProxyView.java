package com.google.android.play.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class ScrollProxyView extends View {
    public ScrollProxyView(Context context) {
        this(context, null, 0);
    }

    public ScrollProxyView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ScrollProxyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if (getVisibility() != 8) {
            setVisibility(8);
        }
    }

    public boolean dispatchTouchEvent(MotionEvent event) {
        return false;
    }

    public boolean canScrollVertically(int direction) {
        return direction >= 0 || getScrollY() > 0;
    }
}
