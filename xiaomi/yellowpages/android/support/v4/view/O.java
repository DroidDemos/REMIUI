package android.support.v4.view;

import android.os.Build.VERSION;
import android.view.ViewConfiguration;

/* compiled from: ViewConfigurationCompat */
public class O {
    static final F Cg;

    static {
        if (VERSION.SDK_INT >= 11) {
            Cg = new ai();
        } else {
            Cg = new ab();
        }
    }

    public static int a(ViewConfiguration viewConfiguration) {
        return Cg.a(viewConfiguration);
    }
}
