package com.xiaomi.account.data;

import com.xiaomi.account.R;

public class AsyncTaskResult {
    public static final int EXCEPTION_TYPE_FORBIDDEN = 4;
    public static final int EXCEPTION_TYPE_NETWORK = 2;
    public static final int EXCEPTION_TYPE_NO = 0;
    public static final int EXCEPTION_TYPE_PASSWORD = 1;
    public static final int EXCEPTION_TYPE_SERVER = 3;
    public static final int EXCEPTION_TYPE_TOKEN_EXPIRED = 6;
    public static final int EXCEPTION_TYPE_UNKNOWN = 5;
    private int mExceptionType;

    public AsyncTaskResult() {
        this.mExceptionType = EXCEPTION_TYPE_NO;
    }

    public AsyncTaskResult(int exceptionType) {
        this.mExceptionType = exceptionType;
    }

    public boolean hasException() {
        return this.mExceptionType != 0;
    }

    public int getExceptionReason() {
        switch (this.mExceptionType) {
            case EXCEPTION_TYPE_NO /*0*/:
                return EXCEPTION_TYPE_NO;
            case EXCEPTION_TYPE_PASSWORD /*1*/:
                return R.string.bad_authentication;
            case EXCEPTION_TYPE_NETWORK /*2*/:
                return R.string.error_network;
            case EXCEPTION_TYPE_SERVER /*3*/:
                return R.string.error_server;
            case EXCEPTION_TYPE_FORBIDDEN /*4*/:
                return R.string.access_denied;
            case EXCEPTION_TYPE_TOKEN_EXPIRED /*6*/:
                return R.string.sns_access_token_expired_warning;
            default:
                return R.string.error_unknown;
        }
    }

    public int getExceptionType() {
        return this.mExceptionType;
    }
}
