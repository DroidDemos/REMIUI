package com.xiaomi.passport.data;

import com.xiaomi.accountsdk.account.data.AccountInfo;
import com.xiaomi.accountsdk.account.data.MetaLoginData;

public class LoginResult {
    final AccountInfo accountInfo;
    final String captchaUrl;
    final int error;
    final MetaLoginData metaLoginDataStep2;
    final String notificationUrl;
    final String password;
    final String serviceId;
    final String step1Token;
    final String username;

    public LoginResult(AccountInfo accountInfo, int error) {
        this(accountInfo, error, null, null, null, null);
    }

    public LoginResult(AccountInfo accountInfo, int error, String captchaUrl, MetaLoginData metaLoginDataStep2, String step1Token, String notificationUrl) {
        this(accountInfo, error, null, null, null, captchaUrl, metaLoginDataStep2, step1Token, notificationUrl);
    }

    public LoginResult(AccountInfo accountInfo, int error, String serviceId, String username, String pwd) {
        this(accountInfo, error, serviceId, username, pwd, null, null, null);
    }

    public LoginResult(AccountInfo accountInfo, int error, String serviceId, String username, String pwd, String captchaUrl, MetaLoginData metaLoginDataStep2, String step1Token) {
        this(accountInfo, error, serviceId, username, pwd, captchaUrl, metaLoginDataStep2, step1Token, null);
    }

    public LoginResult(AccountInfo accountInfo, int error, String serviceId, String username, String pwd, String captchaUrl, MetaLoginData metaLoginDataStep2, String step1Token, String notificationUrl) {
        this.accountInfo = accountInfo;
        this.error = error;
        this.serviceId = serviceId;
        this.username = username;
        this.password = pwd;
        this.captchaUrl = captchaUrl;
        this.metaLoginDataStep2 = metaLoginDataStep2;
        this.step1Token = step1Token;
        this.notificationUrl = notificationUrl;
    }

    public AccountInfo getAccountInfo() {
        return this.accountInfo;
    }

    public int getError() {
        return this.error;
    }

    public String getServiceId() {
        return this.serviceId;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public String getCaptchaUrl() {
        return this.captchaUrl;
    }

    public MetaLoginData getMetaLoginDataStep2() {
        return this.metaLoginDataStep2;
    }

    public String getStep1Token() {
        return this.step1Token;
    }

    public String getNotificationUrl() {
        return this.notificationUrl;
    }
}
