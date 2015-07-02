package com.google.android.finsky.search;

public class RefCountedOnCompleteListener implements OnCompleteListener {
    private int mRefCount;
    private OnCompleteListener mWrappedListener;

    public RefCountedOnCompleteListener(OnCompleteListener listener) {
        this.mRefCount = 1;
        this.mWrappedListener = listener;
    }

    public synchronized void onComplete() {
        if (this.mRefCount > 0) {
            this.mRefCount--;
            if (this.mRefCount <= 0) {
                this.mWrappedListener.onComplete();
            }
        }
    }

    public synchronized void addProducer() {
        this.mRefCount++;
    }
}
