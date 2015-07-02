package com.google.android.finsky.remoting;

import com.google.android.finsky.utils.FinskyLog;

public class StateHandleRadioConnection implements RadioConnection {
    private final RadioConnection mDelegate;
    private int mStartCount;
    State mState;

    enum State {
        INIT,
        STARTED,
        STOPPED
    }

    public StateHandleRadioConnection(RadioConnection delegate) {
        this.mState = State.INIT;
        this.mStartCount = 0;
        this.mDelegate = delegate;
    }

    public synchronized void start() throws RadioConnectionException {
        this.mStartCount++;
        if (this.mState == State.INIT) {
            try {
                this.mDelegate.start();
                this.mState = State.STARTED;
            } catch (RadioConnectionException e) {
                FinskyLog.e("Unable to start radio: %s", e.getMessage());
                this.mState = State.STOPPED;
                throw e;
            }
        }
    }

    public synchronized void stop() throws RadioConnectionException {
        if (this.mState == State.STARTED) {
            this.mStartCount--;
            if (this.mStartCount == 0) {
                this.mState = State.STOPPED;
                try {
                    this.mDelegate.stop();
                } catch (RadioConnectionException e) {
                    FinskyLog.e("Unable to start radio: %s", e.getMessage());
                    throw e;
                }
            }
        }
    }

    public void ensureRouteToHost(String url) throws RadioConnectionException {
        this.mDelegate.ensureRouteToHost(url);
    }
}
