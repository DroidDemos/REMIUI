package com.android.i18n.addressinput;

public class NotifyingListener implements DataLoadListener {
    private boolean mDone;
    private Object mSleeper;

    NotifyingListener(Object sleeper) {
        this.mSleeper = sleeper;
        this.mDone = false;
    }

    public void dataLoadingBegin() {
    }

    public void dataLoadingEnd() {
        synchronized (this) {
            this.mDone = true;
        }
        synchronized (this.mSleeper) {
            this.mSleeper.notify();
        }
    }

    void waitLoadingEnd() throws InterruptedException {
        synchronized (this) {
            if (this.mDone) {
                return;
            }
            synchronized (this.mSleeper) {
                this.mSleeper.wait();
            }
        }
    }
}
