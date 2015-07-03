package com.xiaomi.account.ui;

public interface IQueryBalance {
    public static final int QUERY_CODE_CANCELED = 2;
    public static final int QUERY_CODE_FAIL = 0;
    public static final int QUERY_CODE_SUCCESS = 1;
    public static final int QUERY_CODE_VERIFY_KEY_INVALID = 3;

    void onFinish(int i, long j);
}
