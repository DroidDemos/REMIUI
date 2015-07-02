package android.support.v4.view;

import android.graphics.Paint;
import android.view.View;

/* compiled from: ViewCompat */
class V implements g {
    V() {
    }

    public boolean a(View view, int i) {
        return false;
    }

    public int a(View view) {
        return 2;
    }

    public void a(View view, Q q) {
    }

    public void b(View view) {
        view.postInvalidateDelayed(getFrameTime());
    }

    public void a(View view, Runnable runnable) {
        view.postDelayed(runnable, getFrameTime());
    }

    long getFrameTime() {
        return 10;
    }

    public int c(View view) {
        return 0;
    }

    public void b(View view, int i) {
    }

    public void a(View view, int i, Paint paint) {
    }
}
