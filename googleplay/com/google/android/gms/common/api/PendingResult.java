package com.google.android.gms.common.api;

public interface PendingResult<R extends Result> {

    public interface a {
        void s(Status status);
    }

    void setResultCallback(ResultCallback<R> resultCallback);
}
