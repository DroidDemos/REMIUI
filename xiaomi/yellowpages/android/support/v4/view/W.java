package android.support.v4.view;

import android.view.MotionEvent;

/* compiled from: MotionEventCompat */
class W implements d {
    W() {
    }

    public int a(MotionEvent motionEvent, int i) {
        if (i == 0) {
            return 0;
        }
        return -1;
    }

    public int b(MotionEvent motionEvent, int i) {
        if (i == 0) {
            return 0;
        }
        throw new IndexOutOfBoundsException("Pre-Eclair does not support multiple pointers");
    }

    public float c(MotionEvent motionEvent, int i) {
        if (i == 0) {
            return motionEvent.getX();
        }
        throw new IndexOutOfBoundsException("Pre-Eclair does not support multiple pointers");
    }

    public float d(MotionEvent motionEvent, int i) {
        if (i == 0) {
            return motionEvent.getY();
        }
        throw new IndexOutOfBoundsException("Pre-Eclair does not support multiple pointers");
    }
}
