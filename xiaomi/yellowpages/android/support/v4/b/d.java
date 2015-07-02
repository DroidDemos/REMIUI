package android.support.v4.b;

import android.content.Context;
import android.graphics.Canvas;
import android.widget.EdgeEffect;

/* compiled from: EdgeEffectCompatIcs */
class d {
    d() {
    }

    public static Object o(Context context) {
        return new EdgeEffect(context);
    }

    public static void a(Object obj, int i, int i2) {
        ((EdgeEffect) obj).setSize(i, i2);
    }

    public static boolean d(Object obj) {
        return ((EdgeEffect) obj).isFinished();
    }

    public static void e(Object obj) {
        ((EdgeEffect) obj).finish();
    }

    public static boolean a(Object obj, float f) {
        ((EdgeEffect) obj).onPull(f);
        return true;
    }

    public static boolean f(Object obj) {
        EdgeEffect edgeEffect = (EdgeEffect) obj;
        edgeEffect.onRelease();
        return edgeEffect.isFinished();
    }

    public static boolean a(Object obj, Canvas canvas) {
        return ((EdgeEffect) obj).draw(canvas);
    }
}
