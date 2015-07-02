package com.google.android.finsky.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.Utils;

public class SidecarFragment extends Fragment {
    private boolean mCreated;
    private Listener mListener;
    private int mState;
    private int mStateInstance;
    private int mSubstate;

    public interface Listener {
        void onStateChange(SidecarFragment sidecarFragment);
    }

    public SidecarFragment() {
        this.mState = 0;
        this.mStateInstance = 1;
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
        outState.putInt("SidecarFragment.stateInstance", this.mStateInstance);
    }

    protected void restoreFromSavedInstanceState(Bundle savedInstanceState) {
        this.mState = savedInstanceState.getInt("SidecarFragment.state");
        this.mSubstate = savedInstanceState.getInt("SidecarFragment.substate");
        this.mStateInstance = savedInstanceState.getInt("SidecarFragment.stateInstance");
        if (this.mState == 1) {
            FinskyLog.d("Restoring after serialization in RUNNING, resetting to INIT.", new Object[0]);
            setState(0, 0);
        }
    }

    public void setListener(Listener listener) {
        this.mListener = listener;
        if (this.mListener != null && this.mCreated) {
            notifyListener();
        }
    }

    public int getState() {
        return this.mState;
    }

    public int getSubstate() {
        return this.mSubstate;
    }

    public int getStateInstance() {
        return this.mStateInstance;
    }

    protected void setState(int state, int substate) {
        Utils.ensureOnMainThread();
        this.mState = state;
        this.mSubstate = substate;
        this.mStateInstance++;
        notifyListener();
    }

    private void notifyListener() {
        if (this.mListener != null) {
            this.mListener.onStateChange(this);
        }
    }
}
