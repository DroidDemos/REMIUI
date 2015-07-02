package com.google.android.finsky.layout.scroll;

import android.content.Context;
import android.support.v4.view.GestureDetectorCompat;
import android.util.AttributeSet;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.widget.ScrollView;

public class GestureScrollView extends ScrollView {
    private FlingToDismissListener mFlingToDismissListener;
    private GestureDetectorCompat mGestureDetector;
    private SimpleOnGestureListener mOnGestureListener;

    public interface FlingToDismissListener {
        void onFlingToDismiss();
    }

    public GestureScrollView(Context context) {
        this(context, null);
    }

    public GestureScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mOnGestureListener = new SimpleOnGestureListener() {
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                if (Math.abs(velocityX) < Math.abs(velocityY) / 4.0f) {
                    int scrollY = GestureScrollView.this.getScrollY();
                    if (velocityY > 0.0f) {
                        if (scrollY <= 0 && GestureScrollView.this.mFlingToDismissListener != null) {
                            GestureScrollView.this.mFlingToDismissListener.onFlingToDismiss();
                        }
                    } else if (GestureScrollView.this.getHeight() + scrollY >= GestureScrollView.this.getChildAt(0).getHeight() && GestureScrollView.this.mFlingToDismissListener != null) {
                        GestureScrollView.this.mFlingToDismissListener.onFlingToDismiss();
                    }
                }
                return false;
            }
        };
        this.mGestureDetector = new GestureDetectorCompat(context, this.mOnGestureListener);
    }

    public void setFlingToDismissListener(FlingToDismissListener listener) {
        this.mFlingToDismissListener = listener;
    }

    public boolean onTouchEvent(MotionEvent event) {
        this.mGestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }
}
