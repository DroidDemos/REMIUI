package com.google.android.gms.internal;

import android.app.Activity;
import android.app.Application.ActivityLifecycleCallbacks;
import android.content.Context;
import android.os.Bundle;

@fd
public class ar implements ActivityLifecycleCallbacks {
    private Activity mActivity;
    private Context mContext;
    private final Object mK;

    private void setActivity(Activity activity) {
        synchronized (this.mK) {
            if (!activity.getClass().getName().startsWith("com.google.android.gms.ads")) {
                this.mActivity = activity;
            }
        }
    }

    public Activity getActivity() {
        return this.mActivity;
    }

    public Context getContext() {
        return this.mContext;
    }

    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
    }

    public void onActivityDestroyed(Activity activity) {
        synchronized (this.mK) {
            if (this.mActivity == null) {
                return;
            }
            if (this.mActivity.equals(activity)) {
                this.mActivity = null;
            }
        }
    }

    public void onActivityPaused(Activity activity) {
        setActivity(activity);
    }

    public void onActivityResumed(Activity activity) {
        setActivity(activity);
    }

    public void onActivitySaveInstanceState(Activity activity, Bundle savedInstanceState) {
    }

    public void onActivityStarted(Activity activity) {
        setActivity(activity);
    }

    public void onActivityStopped(Activity activity) {
    }
}
