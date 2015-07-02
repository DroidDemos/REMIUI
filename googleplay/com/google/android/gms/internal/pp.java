package com.google.android.gms.internal;

import android.os.Handler;
import android.util.Log;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class pp {
    private static final String TAG;
    private static final long axo;
    private final Runnable axq;
    private ArrayList<String> axr;
    private ArrayList<String> axs;
    private final Handler mHandler;
    private final Object mK;

    static {
        TAG = pp.class.getSimpleName();
        axo = TimeUnit.SECONDS.toMillis(1);
    }

    public void log(String placeId, String methodName) {
        synchronized (this.mK) {
            if (this.axr == null) {
                this.axr = new ArrayList();
                this.axs = new ArrayList();
                this.mHandler.postDelayed(this.axq, axo);
            }
            this.axr.add(placeId);
            this.axs.add(methodName);
            if (this.axr.size() >= 10000) {
                if (Log.isLoggable(TAG, 5)) {
                    Log.w(TAG, "Event buffer full, flushing");
                }
                this.axq.run();
                this.mHandler.removeCallbacks(this.axq);
                return;
            }
        }
    }
}
