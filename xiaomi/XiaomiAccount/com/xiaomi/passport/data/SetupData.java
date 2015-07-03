package com.xiaomi.passport.data;

import com.xiaomi.passport.accountmanager.IAccountAuthenticatorResponse;

public class SetupData {
    private static SetupData ourInstance;
    private IAccountAuthenticatorResponse mAccountAuthenticatorResponse;
    private volatile String mCachedPassword;
    private volatile RegAccountInfo mRegisterAccount;
    private volatile boolean mUserVerified;

    static {
        ourInstance = new SetupData();
    }

    public static synchronized SetupData getInstance() {
        SetupData setupData;
        synchronized (SetupData.class) {
            setupData = ourInstance;
        }
        return setupData;
    }

    private SetupData() {
    }

    public static void setAccountAuthenticatorResponse(IAccountAuthenticatorResponse response) {
        getInstance().mAccountAuthenticatorResponse = response;
    }

    public static IAccountAuthenticatorResponse getAccountAuthenticatorResponse() {
        return getInstance().mAccountAuthenticatorResponse;
    }

    public static void setRegAccount(RegAccountInfo account) {
        getInstance().mRegisterAccount = account;
    }

    public static RegAccountInfo getRegAccount() {
        return getInstance().mRegisterAccount;
    }
}
