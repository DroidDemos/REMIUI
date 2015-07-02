package com.miui.yellowpage.widget.pulltorefresh;

import android.annotation.TargetApi;
import android.graphics.drawable.Drawable;
import android.view.View;

@TargetApi(16)
/* compiled from: ViewCompat */
class g {
    g() {
    }

    public static void a(View view, Runnable runnable) {
        view.postOnAnimation(runnable);
    }

    public static void a(View view, Drawable drawable) {
        view.setBackground(drawable);
    }
}
