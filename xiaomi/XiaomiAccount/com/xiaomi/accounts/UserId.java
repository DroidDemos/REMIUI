package com.xiaomi.accounts;

import android.os.Binder;

public final class UserId {
    public static final boolean MU_ENABLED = true;
    public static final int PER_USER_RANGE = 100000;
    public static final int USER_ALL = -1;

    public static final int getUserId(int uid) {
        return uid / PER_USER_RANGE;
    }

    public static final int getCallingUserId() {
        return getUserId(Binder.getCallingUid());
    }

    public static final int getUid(int userId, int appId) {
        return (userId * PER_USER_RANGE) + (appId % PER_USER_RANGE);
    }

    public static final int getAppId(int uid) {
        return uid % PER_USER_RANGE;
    }
}
