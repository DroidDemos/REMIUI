package android.support.v4.view;

import android.animation.ValueAnimator;
import android.graphics.Paint;
import android.view.View;

/* compiled from: ViewCompatHC */
class aa {
    aa() {
    }

    static long getFrameTime() {
        return ValueAnimator.getFrameDelay();
    }

    public static void a(View view, int i, Paint paint) {
        view.setLayerType(i, paint);
    }
}
