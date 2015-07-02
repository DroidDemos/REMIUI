package android.support.v4.view;

import android.view.MotionEvent;

/* compiled from: MotionEventCompatEclair */
class b {
    b() {
    }

    public static int a(MotionEvent motionEvent, int i) {
        return motionEvent.findPointerIndex(i);
    }

    public static int b(MotionEvent motionEvent, int i) {
        return motionEvent.getPointerId(i);
    }

    public static float c(MotionEvent motionEvent, int i) {
        return motionEvent.getX(i);
    }

    public static float d(MotionEvent motionEvent, int i) {
        return motionEvent.getY(i);
    }
}
