package com.google.android.play.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;

public class TouchBlockingFrameLayout extends FrameLayout {
    public TouchBlockingFrameLayout(Context context) {
        super(context);
    }

    public TouchBlockingFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TouchBlockingFrameLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public boolean dispatchTouchEvent(MotionEvent ev) {
        return false;
    }
}
