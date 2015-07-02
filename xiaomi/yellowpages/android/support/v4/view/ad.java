package android.support.v4.view;

import android.view.View;

/* compiled from: ViewCompatJB */
class ad {
    ad() {
    }

    public static void b(View view) {
        view.postInvalidateOnAnimation();
    }

    public static void a(View view, Runnable runnable) {
        view.postOnAnimation(runnable);
    }

    public static int c(View view) {
        return view.getImportantForAccessibility();
    }

    public static void b(View view, int i) {
        view.setImportantForAccessibility(i);
    }
}
