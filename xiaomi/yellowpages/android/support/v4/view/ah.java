package android.support.v4.view;

import android.os.Build.VERSION;
import android.view.MotionEvent;

/* compiled from: MotionEventCompat */
public class ah {
    static final d Lm;

    static {
        if (VERSION.SDK_INT >= 5) {
            Lm = new ag();
        } else {
            Lm = new W();
        }
    }

    public static int b(MotionEvent motionEvent) {
        return (motionEvent.getAction() & 65280) >> 8;
    }

    public static int a(MotionEvent motionEvent, int i) {
        return Lm.a(motionEvent, i);
    }

    public static int b(MotionEvent motionEvent, int i) {
        return Lm.b(motionEvent, i);
    }

    public static float c(MotionEvent motionEvent, int i) {
        return Lm.c(motionEvent, i);
    }

    public static float d(MotionEvent motionEvent, int i) {
        return Lm.d(motionEvent, i);
    }
}
