package com.google.android.wallet.instrumentmanager.sidecar;

import android.os.Bundle;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.util.Log;

public class SidecarFragment extends Fragment {
    private boolean mCreated;
    private Listener mListener;
    boolean mNotifyListenerOfStateChange;
    private int mState;
    private int mSubstate;

    public interface Listener {
        void onStateChange(SidecarFragment sidecarFragment);
    }

    public SidecarFragment() {
        this.mState = 0;
        this.mNotifyListenerOfStateChange = false;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        if (savedInstanceState != null) {
            restoreFromSavedInstanceState(savedInstanceState);
        }
        if (this.mListener != null) {
            notifyListener();
        }
        this.mCreated = true;
    }

    public void onDestroy() {
        this.mCreated = false;
        super.onDestroy();
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("SidecarFragment.state", this.mState);
        outState.putInt("SidecarFragment.substate", this.mSubstate);
        outState.putBoolean("SidecarFragment.notifyListenerOfStateChange", this.mNotifyListenerOfStateChange);
    }

    protected void restoreFromSavedInstanceState(Bundle savedInstanceState) {
        this.mState = savedInstanceState.getInt("SidecarFragment.state");
        this.mSubstate = savedInstanceState.getInt("SidecarFragment.substate");
        this.mNotifyListenerOfStateChange = savedInstanceState.getBoolean("SidecarFragment.notifyListenerOfStateChange");
        if (this.mState == 1) {
            Log.d("SidecarFragment", "Restoring after serialization in RUNNING, resetting to INIT.");
            setState(0, 0);
        }
    }

    public void setListener(Listener listener) {
        this.mListener = listener;
        if (this.mListener != null && this.mCreated && this.mNotifyListenerOfStateChange) {
            notifyListener();
        }
    }

    public int getState() {
        return this.mState;
    }

    public int getSubstate() {
        return this.mSubstate;
    }

    protected void setState(int state, int substate) {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            throw new IllegalStateException("This method must be called from the UI thread.");
        } else if (state != this.mState || substate != this.mSubstate) {
            this.mState = state;
            this.mSubstate = substate;
            this.mNotifyListenerOfStateChange = true;
            notifyListener();
        }
    }

    private void notifyListener() {
        if (this.mListener != null) {
            this.mListener.onStateChange(this);
            this.mNotifyListenerOfStateChange = false;
        }
    }
}
