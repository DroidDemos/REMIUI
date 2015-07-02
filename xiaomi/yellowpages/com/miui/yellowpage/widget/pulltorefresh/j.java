package com.miui.yellowpage.widget.pulltorefresh;

import android.view.animation.Interpolator;

/* compiled from: PullToRefreshBase */
final class j implements Runnable {
    final /* synthetic */ PullToRefreshBase jq;
    private final long mDuration;
    private final Interpolator mInterpolator;
    private long mStartTime;
    private final int wr;
    private final int ws;
    private h wt;
    private boolean wu;
    private int wv;

    public j(PullToRefreshBase pullToRefreshBase, int i, int i2, long j, h hVar) {
        this.jq = pullToRefreshBase;
        this.wu = true;
        this.mStartTime = -1;
        this.wv = -1;
        this.ws = i;
        this.wr = i2;
        this.mInterpolator = pullToRefreshBase.wW;
        this.mDuration = j;
        this.wt = hVar;
    }

    public void run() {
        if (this.mStartTime == -1) {
            this.mStartTime = System.currentTimeMillis();
        } else {
            float f = (float) (this.ws - this.wr);
            this.wv = this.ws - Math.round(this.mInterpolator.getInterpolation(((float) Math.max(Math.min(((System.currentTimeMillis() - this.mStartTime) * 1000) / this.mDuration, 1000), 0)) / 1000.0f) * f);
            this.jq.K(this.wv);
        }
        if (this.wu && this.wr != this.wv) {
            f.a(this.jq, (Runnable) this);
        } else if (this.wt != null) {
            this.wt.aU();
        }
    }

    public void stop() {
        this.wu = false;
        this.jq.removeCallbacks(this);
    }
}
