package android.support.v4.view;

import android.animation.ValueAnimator;
import android.graphics.Paint;
import android.view.View;

class ViewCompatHC {
    static long getFrameTime() {
        return ValueAnimator.getFrameDelay();
    }

    public static float getAlpha(View view) {
        return view.getAlpha();
    }

    public static void setLayerType(View view, int layerType, Paint paint) {
        view.setLayerType(layerType, paint);
    }

    public static int getLayerType(View view) {
        return view.getLayerType();
    }

    public static int resolveSizeAndState(int size, int measureSpec, int childMeasuredState) {
        return View.resolveSizeAndState(size, measureSpec, childMeasuredState);
    }

    public static int getMeasuredWidthAndState(View view) {
        return view.getMeasuredWidthAndState();
    }

    public static int getMeasuredState(View view) {
        return view.getMeasuredState();
    }

    public static float getTranslationX(View view) {
        return view.getTranslationX();
    }

    public static float getTranslationY(View view) {
        return view.getTranslationY();
    }

    public static float getScaleX(View view) {
        return view.getScaleX();
    }

    public static void setTranslationX(View view, float value) {
        view.setTranslationX(value);
    }

    public static void setTranslationY(View view, float value) {
        view.setTranslationY(value);
    }

    public static void setAlpha(View view, float value) {
        view.setAlpha(value);
    }

    public static void setScaleX(View view, float value) {
        view.setScaleX(value);
    }

    public static void setScaleY(View view, float value) {
        view.setScaleY(value);
    }

    public static void jumpDrawablesToCurrentState(View view) {
        view.jumpDrawablesToCurrentState();
    }
}
