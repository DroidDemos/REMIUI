package com.xiaomi.passport.accountmanager;

import android.accounts.Account;
import android.accounts.NetworkErrorException;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import com.xiaomi.accounts.AbstractAccountAuthenticator;
import com.xiaomi.accounts.AccountAuthenticatorResponse;
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
import com.xiaomi.passport.data.SetupData;
import com.xiaomi.passport.exception.IllegalDeviceException;
import com.xiaomi.passport.ui.SimpleDialogFragment;
import com.xiaomi.passport.ui.SmsAlertFragment;
import com.xiaomi.passport.utils.AccountHelper;
import com.xiaomi.passport.utils.AnalyticsHelper;

public class LocalAccountAuthenticator extends AbstractAccountAuthenticator {
    private static final boolean DEBUG = true;
    private static final String TAG = "MiLocalAuthenticator";
    private volatile AnalyticsTracker mAnalyticsTracker;
    private Context mContext;

    public LocalAccountAuthenticator(Context context) {
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
                Log.w(TAG, "no service id contained, use passportapi");
                serviceId = Constants.PASSPORT_API_SID;
            }
            Intent newAccountIntent = AccountHelper.newAddAccountIntent(this.mContext, serviceId, options);
            SetupData.setAccountAuthenticatorResponse(new LocalAccountAuthenticatorResponse(response));
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
        fillQuickLoginIntent(resBundle, response, options == null ? Constants.PASSPORT_API_SID : options.getString(Constants.KEY_SERVICE_ID), null, null, null, DEBUG, options == null ? null : options.getString(SimpleDialogFragment.ARG_TITLE));
        return resBundle;
    }

    public Bundle getAuthToken(AccountAuthenticatorResponse response, Account account, String authTokenType, Bundle options) throws NetworkErrorException {
        Log.d(TAG, "getting AuthToken, type:" + authTokenType);
        String serviceId = authTokenType;
        if (TextUtils.isEmpty(serviceId)) {
            Log.w(TAG, "getting auth token, but no service url contained, use micloud");
            serviceId = Constants.PASSPORT_API_SID;
        }
        Bundle resBundle = new Bundle();
        Account currentAccount = AccountHelper.getXiaomiAccount(this.mContext);
        if (currentAccount == null || !currentAccount.name.equals(account.name)) {
            resBundle.putBoolean(MiAccountManager.KEY_BOOLEAN_RESULT, false);
        } else {
            MiAccountManager am = MiAccountManager.get(this.mContext);
            String passToken = am.getPassword(account);
            if (TextUtils.isEmpty(passToken)) {
                fillQuickLoginIntent(resBundle, response, serviceId, null, null, null, false, null);
            } else {
                ExtendedAuthToken extPass = ExtendedAuthToken.parse(passToken);
                if (extPass != null) {
                    passToken = extPass.authToken;
                }
                AccountInfo accountInfo = null;
                if (passToken != null) {
                    try {
                        accountInfo = AccountHelper.getServiceTokenByPassToken(account.name, passToken, serviceId);
                        AnalyticsHelper.trackEvent(this.mAnalyticsTracker, AnalyticsHelper.LOGIN_SUCCESS_BY_PASS_TOKEN);
                    } catch (Throwable e) {
                        Log.w(TAG, "io exception when getting service token", e);
                        AnalyticsHelper.trackEvent(this.mAnalyticsTracker, AnalyticsHelper.LOGIN_ERROR_IO, e);
                        throw new NetworkErrorException("error when getting service token");
                    } catch (Throwable e2) {
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
                        am.clearPassword(account);
                        fillQuickLoginIntent(resBundle, response, serviceId, e4.getCaptchaUrl(), null, null, false, null);
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
                }
                String extToken = ExtendedAuthToken.build(accountInfo.getServiceToken(), accountInfo.getSecurity()).toPlain();
                String encryptedUserId = accountInfo.getEncryptedUserId();
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
        result.putBoolean(MiAccountManager.KEY_BOOLEAN_RESULT, DEBUG);
        return result;
    }

    private void fillQuickLoginIntent(Bundle resBundle, AccountAuthenticatorResponse response, String serviceId, String captchaUrl, String step1Token, MetaLoginData metaLoginDataStep2, boolean verifyOnly, String title) {
        Intent quickLoginIntent = AccountHelper.newQuickLoginIntent(this.mContext, serviceId, captchaUrl, step1Token, metaLoginDataStep2, verifyOnly, title);
        SetupData.setAccountAuthenticatorResponse(new LocalAccountAuthenticatorResponse(response));
        resBundle.putParcelable(MiAccountManager.KEY_INTENT, quickLoginIntent);
    }

    public void setAnalytics(AnalyticsTracker analyticsTracker) {
        this.mAnalyticsTracker = analyticsTracker;
    }
}
