package com.xiaomi.passport.accountmanager;

import android.accounts.AbstractAccountAuthenticator;
import android.accounts.Account;
import android.accounts.AccountAuthenticatorResponse;
import android.accounts.NetworkErrorException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Process;
import android.text.TextUtils;
import android.util.Log;
import com.xiaomi.accounts.AccountManager;
import com.xiaomi.accountsdk.account.data.AccountInfo;
import com.xiaomi.accountsdk.account.data.ExtendedAuthToken;
import com.xiaomi.accountsdk.account.data.MetaLoginData;
import com.xiaomi.accountsdk.account.exception.InvalidCredentialException;
import com.xiaomi.accountsdk.account.exception.InvalidUserNameException;
import com.xiaomi.accountsdk.account.exception.NeedCaptchaException;
import com.xiaomi.accountsdk.account.exception.NeedVerificationException;
import com.xiaomi.accountsdk.request.AccessDeniedException;
import com.xiaomi.accountsdk.request.AuthenticationFailureException;
import com.xiaomi.accountsdk.request.InvalidResponseException;
import com.xiaomi.miui.analyticstracker.AnalyticsTracker;
import com.xiaomi.passport.Constants;
import com.xiaomi.passport.PassportExternal;
import com.xiaomi.passport.data.SetupData;
import com.xiaomi.passport.exception.IllegalDeviceException;
import com.xiaomi.passport.ui.SimpleDialogFragment;
import com.xiaomi.passport.ui.SmsAlertFragment;
import com.xiaomi.passport.utils.AccountHelper;
import com.xiaomi.passport.utils.AnalyticsHelper;
import java.io.IOException;

public class SystemAccountAuthenticator extends AbstractAccountAuthenticator {
    private static final boolean DEBUG = true;
    private static final String TAG = "MiSystemAccountAuthenticator";
    private static final String WEB_LOGIN_PREFIX = "weblogin:";
    private volatile AnalyticsTracker mAnalyticsTracker;
    private Context mContext;

    public SystemAccountAuthenticator(Context context) {
        super(context);
        this.mContext = context;
    }

    public Bundle editProperties(AccountAuthenticatorResponse accountAuthenticatorResponse, String s) {
        return null;
    }

    public Bundle addAccount(AccountAuthenticatorResponse response, String accountType, String authTokenType, String[] requiredFeatures, Bundle options) throws NetworkErrorException {
        Account[] accounts = MiAccountManager.get(this.mContext).getAccountsByType(MiAccountManager.XIAOMI_ACCOUNT_TYPE);
        Bundle resBundle = new Bundle();
        if (accounts.length > 0) {
            Log.d(TAG, "a xiaomi account already exists");
            Account account = accounts[0];
            resBundle.putString(MiAccountManager.KEY_ACCOUNT_NAME, account.name);
            resBundle.putString(MiAccountManager.KEY_ACCOUNT_TYPE, account.type);
        } else {
            String serviceId = authTokenType;
            if (TextUtils.isEmpty(serviceId)) {
                Log.w(TAG, "no service id contained, use micloud");
                serviceId = Constants.PASSPORT_API_SID;
            }
            Intent newAccountIntent = AccountHelper.newAddAccountIntent(this.mContext, serviceId, options);
            SetupData.setAccountAuthenticatorResponse(new SystemAccountAuthenticatorResponse(response));
            resBundle.putParcelable(MiAccountManager.KEY_INTENT, newAccountIntent);
        }
        return resBundle;
    }

    public Bundle confirmCredentials(AccountAuthenticatorResponse response, Account account, Bundle options) throws NetworkErrorException {
        Bundle resBundle = new Bundle();
        if (options != null) {
            if (options.containsKey(SmsAlertFragment.ARGS_PASSWORD)) {
                String userId = account.name;
                AccountInfo accountInfo = null;
                String captchaUrl = null;
                try {
                    accountInfo = AccountHelper.getServiceTokenByPassword(userId, options.getString(SmsAlertFragment.ARGS_PASSWORD), options.getString(Constants.KEY_CAPTCHA_CODE), options.getString(Constants.KEY_CAPTCHA_ICK), null, null);
                } catch (Throwable e) {
                    throw new NetworkErrorException("IO exception when sending request to passport server", e);
                } catch (IllegalDeviceException e2) {
                    e2.printStackTrace();
                } catch (InvalidResponseException e3) {
                    e3.printStackTrace();
                } catch (InvalidCredentialException e4) {
                    captchaUrl = e4.getCaptchaUrl();
                    e4.printStackTrace();
                } catch (AccessDeniedException e5) {
                    e5.printStackTrace();
                } catch (AuthenticationFailureException e6) {
                    e6.printStackTrace();
                } catch (NeedVerificationException e7) {
                    AccountInfo accountInfo2 = new AccountInfo(userId, null, null, null);
                } catch (NeedCaptchaException e8) {
                    captchaUrl = e8.getCaptchaUrl();
                    e8.printStackTrace();
                } catch (InvalidUserNameException e9) {
                    e9.printStackTrace();
                }
                resBundle.putString(MiAccountManager.KEY_ACCOUNT_NAME, userId);
                resBundle.putString(MiAccountManager.KEY_ACCOUNT_TYPE, MiAccountManager.XIAOMI_ACCOUNT_TYPE);
                resBundle.putBoolean(MiAccountManager.KEY_BOOLEAN_RESULT, accountInfo != null ? DEBUG : false);
                resBundle.putString(Constants.KEY_CAPTCHA_URL, captchaUrl);
                return resBundle;
            }
        }
        fillQuickLoginIntent(resBundle, new SystemAccountAuthenticatorResponse(response), options == null ? Constants.PASSPORT_API_SID : options.getString(Constants.KEY_SERVICE_ID), null, null, null, DEBUG, options == null ? null : options.getString(SimpleDialogFragment.ARG_TITLE));
        return resBundle;
    }

    private String getRealPasstoken(Account account) {
        String passToken = MiAccountManager.get(this.mContext).getPassword(account);
        if (TextUtils.isEmpty(passToken)) {
            return null;
        }
        ExtendedAuthToken extPass = ExtendedAuthToken.parse(passToken);
        if (extPass != null) {
            return extPass.authToken;
        }
        return null;
    }

    public Bundle getAuthToken(AccountAuthenticatorResponse response, Account account, String authTokenType, Bundle options) throws NetworkErrorException {
        Log.d(TAG, "getting AuthToken, type:" + authTokenType);
        Bundle resBundle = new Bundle();
        Account currentAccount = AccountHelper.getXiaomiAccount(this.mContext);
        if (currentAccount == null || !currentAccount.name.equals(account.name)) {
            resBundle.putBoolean(MiAccountManager.KEY_BOOLEAN_RESULT, false);
        } else {
            String serviceId = authTokenType;
            String webAutoLoginUrl = null;
            if (TextUtils.isEmpty(authTokenType)) {
                Log.w(TAG, "getting auth token, but no service url contained, use micloud");
                serviceId = Constants.PASSPORT_API_SID;
            } else {
                if (authTokenType.startsWith(WEB_LOGIN_PREFIX)) {
                    if (verifyCallerUidAgainstWebSso(options)) {
                        serviceId = Constants.PASSPORT_API_SID;
                        webAutoLoginUrl = authTokenType.substring(WEB_LOGIN_PREFIX.length());
                        if (!AccountHelper.isTrustedWebSsoUrl(webAutoLoginUrl)) {
                            resBundle.putInt(MiAccountManager.KEY_ERROR_CODE, 7);
                            resBundle.putString(MiAccountManager.KEY_ERROR_MESSAGE, "untrusted web sso url");
                        }
                    } else {
                        String msg = "caller is not allowed for Web Sso";
                        Log.w(TAG, msg);
                        throw new SecurityException(msg);
                    }
                }
            }
            String passToken = getRealPasstoken(account);
            if (TextUtils.isEmpty(passToken)) {
                fillQuickLoginIntent(resBundle, getQuickLoginResponse(response, account, authTokenType, options), serviceId, null, null, null, false, null);
            } else if (webAutoLoginUrl != null) {
                try {
                    accountInfo = AccountHelper.getServiceTokenByPassToken(account.name, passToken, null, webAutoLoginUrl);
                    AnalyticsHelper.trackEvent(this.mAnalyticsTracker, AnalyticsHelper.LOGIN_SUCCESS_BY_PASS_TOKEN);
                    resBundle.putString(MiAccountManager.KEY_ACCOUNT_NAME, accountInfo.getUserId());
                    resBundle.putString(MiAccountManager.KEY_ACCOUNT_TYPE, webAutoLoginUrl);
                    resBundle.putString(MiAccountManager.KEY_AUTHTOKEN, accountInfo.getAutoLoginUrl());
                } catch (IOException e) {
                    Log.w(TAG, "io exception when getting service token", e);
                    AnalyticsHelper.trackEvent(this.mAnalyticsTracker, AnalyticsHelper.LOGIN_ERROR_IO, e);
                    throw new NetworkErrorException("error when getting service token");
                } catch (IllegalDeviceException e2) {
                    Log.w(TAG, "get device id failed when getting service token", e2);
                    AnalyticsHelper.trackEvent(this.mAnalyticsTracker, AnalyticsHelper.LOGIN_ERROR_ILLEGAL_DEVICE, e2);
                    throw new NetworkErrorException("error when getting service token");
                } catch (InvalidResponseException e3) {
                    Log.w(TAG, "invalid response received when getting service token", e3);
                    resBundle.putInt(MiAccountManager.KEY_ERROR_CODE, 5);
                    resBundle.putString(MiAccountManager.KEY_ERROR_MESSAGE, "invalid response from server");
                    AnalyticsHelper.trackEvent(this.mAnalyticsTracker, AnalyticsHelper.LOGIN_ERROR_INVALID_RESPONSE, e3);
                } catch (InvalidCredentialException e4) {
                    Log.w(TAG, "invalid credential, passToken is invalid", e4);
                    MiAccountManager.get(this.mContext).clearPassword(account);
                    fillQuickLoginIntent(resBundle, getQuickLoginResponse(response, account, authTokenType, options), serviceId, e4.getCaptchaUrl(), null, null, false, null);
                    AnalyticsHelper.trackEvent(this.mAnalyticsTracker, passToken != null ? AnalyticsHelper.LOGIN_ERROR_PASS_TOKEN : AnalyticsHelper.LOGIN_ERROR_PWD);
                } catch (AccessDeniedException e5) {
                    Log.w(TAG, "access denied", e5);
                    resBundle.putInt(MiAccountManager.KEY_ERROR_CODE, 5);
                    resBundle.putString(MiAccountManager.KEY_ERROR_MESSAGE, "access denied");
                    AnalyticsHelper.trackEvent(this.mAnalyticsTracker, AnalyticsHelper.LOGIN_ERROR_FORBIDDEN);
                } catch (AuthenticationFailureException e6) {
                    Log.w(TAG, "auth failure", e6);
                    resBundle.putInt(MiAccountManager.KEY_ERROR_CODE, 5);
                    resBundle.putString(MiAccountManager.KEY_ERROR_MESSAGE, "auth failure");
                    AnalyticsHelper.trackEvent(this.mAnalyticsTracker, AnalyticsHelper.LOGIN_ERROR_AUTH_FAILURE);
                } catch (InvalidUserNameException e7) {
                    AnalyticsHelper.trackEvent(this.mAnalyticsTracker, AnalyticsHelper.LOGIN_ERROR_UNEXPECTED_INVALID_USER_NAME);
                    Log.e(TAG, "no such a user", e7);
                }
            } else {
                accountInfo = AccountHelper.getServiceTokenByPassToken(account.name, passToken, serviceId);
                AnalyticsHelper.trackEvent(this.mAnalyticsTracker, AnalyticsHelper.LOGIN_SUCCESS_BY_PASS_TOKEN);
                String extToken = ExtendedAuthToken.build(accountInfo.getServiceToken(), accountInfo.getSecurity()).toPlain();
                String encryptedUserId = accountInfo.getEncryptedUserId();
                MiAccountManager am = MiAccountManager.get(this.mContext);
                am.setAuthToken(account, serviceId, extToken);
                if (!TextUtils.isEmpty(encryptedUserId)) {
                    am.setUserData(account, Constants.KEY_ENCRYPTED_USER_ID, encryptedUserId);
                }
                resBundle.putString(MiAccountManager.KEY_ACCOUNT_NAME, accountInfo.getUserId());
                resBundle.putString(MiAccountManager.KEY_ACCOUNT_TYPE, MiAccountManager.XIAOMI_ACCOUNT_TYPE);
                resBundle.putString(MiAccountManager.KEY_AUTHTOKEN, extToken);
                resBundle.putString(Constants.KEY_ENCRYPTED_USER_ID, encryptedUserId);
            }
        }
        return resBundle;
    }

    private boolean verifyCallerUidAgainstWebSso(Bundle options) {
        if (options == null || !options.containsKey(AccountManager.KEY_CALLER_UID)) {
            return false;
        }
        int callerUid = options.getInt(AccountManager.KEY_CALLER_UID);
        PackageManager pm = this.mContext.getPackageManager();
        if (pm.checkSignatures(callerUid, Process.myUid()) == 0) {
            return DEBUG;
        }
        for (String packageName : pm.getPackagesForUid(callerUid)) {
            try {
                PackageInfo pi = pm.getPackageInfo(packageName, 0);
                if (!(pi == null || (pi.applicationInfo.flags & 1) == 0)) {
                    return DEBUG;
                }
            } catch (NameNotFoundException e) {
                Log.w(TAG, "NameNotFoundException for packageName=" + packageName);
            }
        }
        return false;
    }

    private boolean isWebSsoAuthTokenType(String authTokenType) {
        return (authTokenType == null || !authTokenType.startsWith(WEB_LOGIN_PREFIX)) ? false : DEBUG;
    }

    private IAccountAuthenticatorResponse getQuickLoginResponse(AccountAuthenticatorResponse response, Account account, String authTokenType, Bundle options) {
        if (!isWebSsoAuthTokenType(authTokenType)) {
            return new SystemAccountAuthenticatorResponse(response);
        }
        final AccountAuthenticatorResponse accountAuthenticatorResponse = response;
        final Account account2 = account;
        final String str = authTokenType;
        final Bundle bundle = options;
        return new SystemAccountAuthenticatorResponse(response) {
            public void onResult(Bundle result) {
                if (result == null || !result.getBoolean(MiAccountManager.KEY_BOOLEAN_RESULT, false)) {
                    super.onResult(result);
                } else {
                    startBackgroundGetAuthToken();
                }
            }

            private void startBackgroundGetAuthToken() {
                new AsyncTask<Void, Void, Void>() {
                    public Void doInBackground(Void... param) {
                        try {
                            AnonymousClass1.this.onResult(SystemAccountAuthenticator.this.getAuthToken(accountAuthenticatorResponse, account2, str, bundle));
                        } catch (NetworkErrorException e) {
                            AnonymousClass1.this.onError(3, e.getMessage());
                        }
                        return null;
                    }
                }.execute(new Void[0]);
            }
        };
    }

    public String getAuthTokenLabel(String s) {
        return null;
    }

    public Bundle updateCredentials(AccountAuthenticatorResponse accountAuthenticatorResponse, Account account, String s, Bundle bundle) throws NetworkErrorException {
        throw new UnsupportedOperationException("updateCredentials not supported");
    }

    public Bundle hasFeatures(AccountAuthenticatorResponse accountAuthenticatorResponse, Account account, String[] strings) throws NetworkErrorException {
        return null;
    }

    public Bundle getAccountRemovalAllowed(AccountAuthenticatorResponse response, Account account) throws NetworkErrorException {
        Bundle result = new Bundle();
        if (PassportExternal.getPassportInterface() == null) {
            return super.getAccountRemovalAllowed(response, account);
        }
        result.putBoolean(MiAccountManager.KEY_BOOLEAN_RESULT, PassportExternal.getPassportInterface().onGetRemoveAccountAllowed(account));
        return result;
    }

    private void fillQuickLoginIntent(Bundle resBundle, IAccountAuthenticatorResponse response, String serviceId, String captchaUrl, String step1Token, MetaLoginData metaLoginDataStep2, boolean verifyOnly, String title) {
        Intent quickLoginIntent = AccountHelper.newQuickLoginIntent(this.mContext, serviceId, captchaUrl, step1Token, metaLoginDataStep2, verifyOnly, title);
        SetupData.setAccountAuthenticatorResponse(response);
        resBundle.putParcelable(MiAccountManager.KEY_INTENT, quickLoginIntent);
    }

    public void setAnalytics(AnalyticsTracker analyticsTracker) {
        this.mAnalyticsTracker = analyticsTracker;
    }
}
