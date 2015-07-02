package android.support.v4.app;

import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

/* compiled from: FragmentManager */
class b implements AnimationListener {
    final /* synthetic */ Fragment ht;
    final /* synthetic */ p hu;

    b(p pVar, Fragment fragment) {
        this.hu = pVar;
        this.ht = fragment;
    }

    public void onAnimationEnd(Animation animation) {
        if (this.ht.tw != null) {
            this.ht.tw = null;
            this.hu.a(this.ht, this.ht.mStateAfterAnimating, 0, 0, false);
        }
    }

    public void onAnimationRepeat(Animation animation) {
    }

    public void onAnimationStart(Animation animation) {
    }
}
