package android.support.v7.internal.view;

import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.support.v4.view.ViewPropertyAnimatorListenerAdapter;
import android.view.View;
import android.view.animation.Interpolator;
import java.util.ArrayList;
import java.util.Iterator;

public class ViewPropertyAnimatorCompatSet {
    private final ArrayList<ViewPropertyAnimatorCompat> mAnimators;
    private long mDuration;
    private Interpolator mInterpolator;
    private boolean mIsStarted;
    private ViewPropertyAnimatorListener mListener;
    private final ViewPropertyAnimatorListenerAdapter mProxyListener;

    public ViewPropertyAnimatorCompatSet() {
        this.mDuration = -1;
        this.mProxyListener = new ViewPropertyAnimatorListenerAdapter() {
            private int mProxyEndCount;
            private boolean mProxyStarted;

            {
                this.mProxyStarted = false;
                this.mProxyEndCount = 0;
            }

            public void onAnimationStart(View view) {
                if (!this.mProxyStarted) {
                    this.mProxyStarted = true;
                    if (ViewPropertyAnimatorCompatSet.this.mListener != null) {
                        ViewPropertyAnimatorCompatSet.this.mListener.onAnimationStart(null);
                    }
                }
            }

            void onEnd() {
                this.mProxyEndCount = 0;
                this.mProxyStarted = false;
                ViewPropertyAnimatorCompatSet.this.onAnimationsEnded();
            }

            public void onAnimationEnd(View view) {
                int i = this.mProxyEndCount + 1;
                this.mProxyEndCount = i;
                if (i == ViewPropertyAnimatorCompatSet.this.mAnimators.size()) {
                    if (ViewPropertyAnimatorCompatSet.this.mListener != null) {
                        ViewPropertyAnimatorCompatSet.this.mListener.onAnimationEnd(null);
                    }
                    onEnd();
                }
            }
        };
        this.mAnimators = new ArrayList();
    }

    public ViewPropertyAnimatorCompatSet play(ViewPropertyAnimatorCompat animator) {
        if (!this.mIsStarted) {
            this.mAnimators.add(animator);
        }
        return this;
    }

    public void start() {
        if (!this.mIsStarted) {
            Iterator i$ = this.mAnimators.iterator();
            while (i$.hasNext()) {
                ViewPropertyAnimatorCompat animator = (ViewPropertyAnimatorCompat) i$.next();
                if (this.mDuration >= 0) {
                    animator.setDuration(this.mDuration);
                }
                if (this.mInterpolator != null) {
                    animator.setInterpolator(this.mInterpolator);
                }
                if (this.mListener != null) {
                    animator.setListener(this.mProxyListener);
                }
                animator.start();
            }
            this.mIsStarted = true;
        }
    }

    private void onAnimationsEnded() {
        this.mIsStarted = false;
    }

    public void cancel() {
        if (this.mIsStarted) {
            Iterator i$ = this.mAnimators.iterator();
            while (i$.hasNext()) {
                ((ViewPropertyAnimatorCompat) i$.next()).cancel();
            }
            this.mIsStarted = false;
        }
    }

    public ViewPropertyAnimatorCompatSet setDuration(long duration) {
        if (!this.mIsStarted) {
            this.mDuration = duration;
        }
        return this;
    }

    public ViewPropertyAnimatorCompatSet setInterpolator(Interpolator interpolator) {
        if (!this.mIsStarted) {
            this.mInterpolator = interpolator;
        }
        return this;
    }

    public ViewPropertyAnimatorCompatSet setListener(ViewPropertyAnimatorListener listener) {
        if (!this.mIsStarted) {
            this.mListener = listener;
        }
        return this;
    }
}
