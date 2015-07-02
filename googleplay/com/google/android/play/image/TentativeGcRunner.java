package com.google.android.play.image;

import android.os.Build.VERSION;
import android.os.Handler;
import android.os.HandlerThread;
import com.google.android.play.utils.config.PlayG;

public class TentativeGcRunner {
    private int mAllocatedSinceLastRun;
    private boolean mEnabled;
    private Runnable mGcRunnable;
    private Handler mHandler;
    private HandlerThread mHandlerThread;

    public TentativeGcRunner() {
        this.mGcRunnable = new Runnable() {
            public void run() {
                System.gc();
            }
        };
        boolean z = VERSION.SDK_INT >= 11 && Runtime.getRuntime().availableProcessors() > 1 && ((Boolean) PlayG.tentativeGcRunnerEnabled.get()).booleanValue();
        this.mEnabled = z;
        if (this.mEnabled) {
            this.mHandlerThread = new HandlerThread("tentative-gc-runner", 10);
            this.mHandlerThread.start();
            this.mHandler = new Handler(this.mHandlerThread.getLooper());
        }
    }

    public void onAllocatingSoon(int size) {
        if (this.mEnabled) {
            this.mAllocatedSinceLastRun += size;
            if (size > 81920 && this.mAllocatedSinceLastRun > 524288) {
                this.mHandler.post(this.mGcRunnable);
                this.mAllocatedSinceLastRun = 0;
            }
        }
    }
}
