package com.miui.yellowpage.widget;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;

/* compiled from: FloatingChildLayout */
class b implements AnimatorListener {
    final /* synthetic */ Runnable qh;
    final /* synthetic */ FloatingChildLayout qi;

    b(FloatingChildLayout floatingChildLayout, Runnable runnable) {
        this.qi = floatingChildLayout;
        this.qh = runnable;
    }

    public void onAnimationStart(Animator animator) {
    }

    public void onAnimationRepeat(Animator animator) {
    }

    public void onAnimationCancel(Animator animator) {
    }

    public void onAnimationEnd(Animator animator) {
        this.qh.run();
    }
}
