package android.support.v4.view;

import android.graphics.Paint;
import android.view.View;

class ViewCompatJellybeanMr1 {
    public static void setLayerPaint(View view, Paint paint) {
        view.setLayerPaint(paint);
    }

    public static int getLayoutDirection(View view) {
        return view.getLayoutDirection();
    }

    public static int getPaddingStart(View view) {
        return view.getPaddingStart();
    }

    public static int getPaddingEnd(View view) {
        return view.getPaddingEnd();
    }

    public static int getWindowSystemUiVisibility(View view) {
        return view.getWindowSystemUiVisibility();
    }
}
