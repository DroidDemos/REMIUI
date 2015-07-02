package com.miui.yellowpage.widget.pulltorefresh;

import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.view.View;

/* compiled from: ViewCompat */
public class f {
    public static void a(View view, Runnable runnable) {
        if (VERSION.SDK_INT >= 16) {
            g.a(view, runnable);
        } else {
            view.postDelayed(runnable, 16);
        }
    }

    public static void a(View view, Drawable drawable) {
        if (VERSION.SDK_INT >= 16) {
            g.a(view, drawable);
        } else {
            view.setBackgroundDrawable(drawable);
        }
    }
}
