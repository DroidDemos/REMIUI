package android.support.v4.view;

import android.os.Build.VERSION;
import android.view.VelocityTracker;

/* compiled from: VelocityTrackerCompat */
public class aj {
    static final K LE;

    static {
        if (VERSION.SDK_INT >= 11) {
            LE = new X();
        } else {
            LE = new k();
        }
    }

    public static float a(VelocityTracker velocityTracker, int i) {
        return LE.a(velocityTracker, i);
    }
}
