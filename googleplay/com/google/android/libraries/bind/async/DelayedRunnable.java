package com.google.android.libraries.bind.async;

import android.os.Handler;
import android.os.SystemClock;

public class DelayedRunnable {
    private final Runnable baseRunnable;
    private final Handler handler;
    private final Object lock;
    private long scheduledTime;
    private final Runnable wrapperRunnable;

    public DelayedRunnable(Handler handler, Runnable runnable) {
        this.baseRunnable = runnable;
        this.handler = handler;
        this.wrapperRunnable = new Runnable() {
            public void run() {
                synchronized (DelayedRunnable.this.lock) {
                    DelayedRunnable.this.unschedule();
                }
                DelayedRunnable.this.baseRunnable.run();
            }
        };
        this.lock = this.wrapperRunnable;
        unschedule();
    }

    public boolean postDelayed(long delayMillis, int runMode) {
        boolean z = true;
        long thisTime = SystemClock.uptimeMillis() + delayMillis;
        synchronized (this.lock) {
            if (isScheduled()) {
                if (runMode == 3 || ((runMode == 0 && thisTime < this.scheduledTime) || (runMode == 1 && thisTime > this.scheduledTime))) {
                    rescheduleAtTime(thisTime);
                }
            } else {
                rescheduleAtTime(thisTime);
                z = false;
            }
        }
        return z;
    }

    private boolean isScheduled() {
        return this.scheduledTime > 0;
    }

    private void unschedule() {
        this.scheduledTime = -1;
    }

    private void rescheduleAtTime(long uptimeMillis) {
        this.scheduledTime = uptimeMillis;
        this.handler.removeCallbacks(this.wrapperRunnable);
        if (!this.handler.postAtTime(this.wrapperRunnable, this.scheduledTime)) {
            unschedule();
        }
    }
}
