package android.support.v4.view;

import android.graphics.Paint;
import android.os.Build.VERSION;
import android.view.View;

/* compiled from: ViewCompat */
public class j {
    static final g hL;

    static {
        int i = VERSION.SDK_INT;
        if (i >= 19) {
            hL = new h();
        } else if (i >= 17) {
            hL = new u();
        } else if (i >= 16) {
            hL = new r();
        } else if (i >= 14) {
            hL = new R();
        } else if (i >= 11) {
            hL = new P();
        } else if (i >= 9) {
            hL = new Y();
        } else {
            hL = new V();
        }
    }

    public static boolean a(View view, int i) {
        return hL.a(view, i);
    }

    public static int a(View view) {
        return hL.a(view);
    }

    public static void a(View view, Q q) {
        hL.a(view, q);
    }

    public static void b(View view) {
        hL.b(view);
    }

    public static void a(View view, Runnable runnable) {
        hL.a(view, runnable);
    }

    public static int c(View view) {
        return hL.c(view);
    }

    public static void b(View view, int i) {
        hL.b(view, i);
    }

    public static void a(View view, int i, Paint paint) {
        hL.a(view, i, paint);
    }
}
