package com.miui.yellowpage.request;

import com.miui.yellowpage.R;

public abstract class BaseResult {
    private State mState;

    public enum State {
        NETWORK_ERROR(R.string.network_unavaliable),
        SERVICE_ERROR(R.string.service_unavailiable),
        DATA_ERROR(R.string.data_error),
        LOCATION_ERROR(R.string.locating_fail),
        OK(17039370);
        
        private int mDefaultDescription;

        private State(int i) {
            this.mDefaultDescription = i;
        }

        public int gL() {
            return this.mDefaultDescription;
        }
    }

    public abstract boolean hasData();

    public abstract BaseResult shallowClone();

    public BaseResult() {
        this.mState = State.OK;
    }

    public State getState() {
        return this.mState;
    }

    public int getCount() {
        return 0;
    }

    public void setState(State state) {
        this.mState = state;
    }
}
