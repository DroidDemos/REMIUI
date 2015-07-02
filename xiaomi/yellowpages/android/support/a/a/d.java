package android.support.a.a;

import android.app.Fragment;
import android.os.Build.VERSION;

/* compiled from: FragmentCompat */
public class d {
    static final g HV;

    static {
        if (VERSION.SDK_INT >= 15) {
            HV = new c();
        } else if (VERSION.SDK_INT >= 14) {
            HV = new b();
        } else {
            HV = new f();
        }
    }

    public static void b(Fragment fragment, boolean z) {
        HV.b(fragment, z);
    }

    public static void a(Fragment fragment, boolean z) {
        HV.a(fragment, z);
    }
}
