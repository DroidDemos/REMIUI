package android.support.v4.view;

import android.view.animation.Interpolator;

/* compiled from: ViewPager */
final class x implements Interpolator {
    x() {
    }

    public float getInterpolation(float f) {
        float f2 = f - 1.0f;
        return (f2 * (((f2 * f2) * f2) * f2)) + 1.0f;
    }
}
