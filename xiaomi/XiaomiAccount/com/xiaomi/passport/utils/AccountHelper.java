package com.xiaomi.passport.utils;

import android.accounts.Account;
import android.accounts.AccountManagerFuture;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import com.mipay.sdk.Mipay;
import com.xiaomi.accounts.AccountManager;
import com.xiaomi.accountsdk.account.XMPassport;
import com.xiaomi.accountsdk.account.data.AccountInfo;
import com.xiaomi.accountsdk.account.data.MetaLoginData;
import com.xiaomi.accountsdk.account.exception.InvalidCredentialException;
import com.xiaomi.accountsdk.account.exception.InvalidUserNameException;
import com.xiaomi.accountsdk.account.exception.NeedCaptchaException;
import com.xiaomi.accountsdk.account.exception.NeedNotificationException;
import com.xiaomi.accountsdk.account.exception.NeedVerificationException;
import com.xiaomi.accountsdk.account.exception.RegisteredPhoneException;
import com.xiaomi.accountsdk.request.AccessDeniedException;
import com.xiaomi.accountsdk.request.AuthenticationFailureException;
import com.xiaomi.accountsdk.request.InvalidResponseException;
import com.xiaomi.accountsdk.request.SimpleRequest;
import com.xiaomi.accountsdk.request.SimpleRequest.MapContent;
import com.xiaomi.accountsdk.request.SimpleRequest.StreamContent;
import com.xiaomi.accountsdk.request.SimpleRequest.StringContent;
import com.xiaomi.accountsdk.utils.EasyMap;
import com.xiaomi.passport.Constants;
import com.xiaomi.passport.PassportExternal;
import com.xiaomi.passport.accountmanager.IAccountAuthenticatorResponse;
import com.xiaomi.passport.accountmanager.MiAccountManager;
import com.xiaomi.passport.data.SetupData;
import com.xiaomi.passport.exception.IllegalDeviceException;
import com.xiaomi.passport.ui.QuickLoginActivity;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class AccountHelper {
    private static final Object ACCOUNT_LOCK;
    private static final Integer INT_0;
    private static final String TAG = "AccountHelper";
    private static final String URL_RESEND_EMAIL;

    static {
        ACCOUNT_LOCK = new Object();
        URL_RESEND_EMAIL = XMPassport.URL_ACOUNT_API_BASE_SECURE + "/sendActivateMessage";
        INT_0 = Integer.valueOf(0);
    }

    private static String getDeviceId() throws IllegalDeviceException {
        String deviceId = PassportExternal.getPassportInterface().getDeviceId();
        if (!TextUtils.isEmpty(deviceId)) {
            return deviceId;
        }
        throw new IllegalDeviceException("get device id error: device is null");
    }

    public static AccountInfo getServiceTokenByPassword(String userId, String password, String captCode, String captIck, MetaLoginData metaLoginData, String serviceId) throws IOException, NeedCaptchaException, InvalidUserNameException, InvalidResponseException, AccessDeniedException, AuthenticationFailureException, InvalidCredentialException, NeedVerificationException, IllegalDeviceException {
        try {
            return XMPassport.loginByPassword(userId, serviceId, getDeviceId(), password, captCode, captIck, metaLoginData, false);
        } catch (NeedNotificationException e) {
            throw new InvalidResponseException("Unexpected NeedNotificationException");
        }
    }

    public static AccountInfo getServiceTokenByPassword(String userId, String password, String captCode, String captIck, MetaLoginData metaLoginData, String serviceId, boolean needProcessNotification) throws IOException, NeedCaptchaException, InvalidUserNameException, InvalidResponseException, AccessDeniedException, AuthenticationFailureException, InvalidCredentialException, NeedVerificationException, NeedNotificationException, IllegalDeviceException {
        return XMPassport.loginByPassword(userId, serviceId, getDeviceId(), password, captCode, captIck, metaLoginData, needProcessNotification);
    }

    public static AccountInfo parseLoginResult(String userId, StringContent loginContent, String serviceId, String serviceTokenLocation) throws InvalidResponseException, InvalidCredentialException, IOException, AccessDeniedException, NeedVerificationException, NeedCaptchaException, InvalidUserNameException {
        return XMPassport.parseLoginResult(userId, loginContent, serviceId, serviceTokenLocation);
    }

    public static MetaLoginData getMetaLoginData(String userId, String serviceId) throws IOException, InvalidResponseException, AccessDeniedException, AuthenticationFailureException, InvalidUserNameException {
        return XMPassport.getMetaLoginData(userId, serviceId);
    }

    public static AccountInfo getServiceTokenByPassToken(String userId, String passToken, String serviceId, String url) throws IOException, InvalidResponseException, InvalidCredentialException, AccessDeniedException, AuthenticationFailureException, InvalidUserNameException, IllegalDeviceException {
        return XMPassport.loginByPassToken(userId, serviceId, getDeviceId(), passToken, url);
    }

    public static AccountInfo getServiceTokenByPassToken(String userId, String passToken, String serviceId) throws IOException, InvalidResponseException, InvalidCredentialException, AccessDeniedException, AuthenticationFailureException, InvalidUserNameException, IllegalDeviceException {
        return XMPassport.loginByPassToken(userId, serviceId, getDeviceId(), passToken);
    }

    public static AccountInfo getServiceTokenByStep2(String userId, String vcode, MetaLoginData metaLoginData, boolean trust, String step1Token, String serviceId) throws IOException, AccessDeniedException, AuthenticationFailureException, InvalidCredentialException, InvalidResponseException, NeedVerificationException, InvalidUserNameException, IllegalDeviceException {
        return XMPassport.loginByStep2(userId, serviceId, getDeviceId(), vcode, metaLoginData, trust, step1Token);
    }

    public static Account getXiaomiAccount(Context context) {
        Account[] accounts = MiAccountManager.get(context).getAccountsByType(MiAccountManager.XIAOMI_ACCOUNT_TYPE);
        if (accounts.length > 0) {
            return accounts[0];
        }
        return null;
    }

    public static void addActiveXiaomiAccount(Context context, Account account, String pwd, Bundle data) {
        synchronized (ACCOUNT_LOCK) {
            Account currentAccount = getXiaomiAccount(context);
            if (currentAccount != null) {
                Log.w(TAG, "Xiaomi account already exists, ignore adding, current: " + currentAccount);
            } else {
                if (PassportExternal.getPassportInterface() != null) {
                    PassportExternal.getPassportInterface().onPreAddAccount(account);
                }
                MiAccountManager.get(context).addAccountExplicitly(account, pwd, data);
                if (PassportExternal.getPassportInterface() != null) {
                    PassportExternal.getPassportInterface().onPostAddAccount(account);
                }
            }
        }
    }

    public static void removeAccount(Context context, Account account) {
        AccountManagerFuture<Boolean> f = MiAccountManager.get(context).removeAccount(account, null, null);
        Boolean removed = Boolean.valueOf(false);
        try {
            removed = (Boolean) f.getResult();
        } catch (Exception e) {
            Log.e(TAG, "error when remove account", e);
        }
        if (removed.booleanValue() && PassportExternal.getPassportInterface() != null) {
            PassportExternal.getPassportInterface().onPostRemoveAccount(account);
        }
    }

    public static Pair<Bitmap, String> getCaptchaImage(String path) {
        return getIckImage("https://account.xiaomi.com" + path);
    }

    public static Pair<Bitmap, String> getIckImage(String url) {
        Pair<Bitmap, String> pair = null;
        StreamContent c = null;
        try {
            c = SimpleRequest.getAsStream(url, null, null);
        } catch (IOException e) {
            Log.w(TAG, "getCaptchaImage", e);
        } catch (AccessDeniedException e2) {
            Log.w(TAG, "getCaptchaImage", e2);
        } catch (AuthenticationFailureException e3) {
            Log.w(TAG, "getCaptchaImage", e3);
        }
        if (c != null) {
            try {
                pair = Pair.create(BitmapFactory.decodeStream(c.getStream()), c.getHeader("ick"));
            } finally {
                c.closeStream();
            }
        }
        return pair;
    }

    public static Intent newQuickLoginIntent(Context context, String serviceId, String captchaUrl, String step1Token, MetaLoginData metaLoginDataStep2, boolean verifyOnly, String title) {
        Intent quickLoginIntent = PassportExternal.getPassportInterface().getConfirmCredentialIntent();
        if (quickLoginIntent == null) {
            quickLoginIntent = new Intent(context, QuickLoginActivity.class);
        }
        quickLoginIntent.putExtra(Constants.EXTRA_SERVICE_URL, serviceId);
        quickLoginIntent.putExtra(Constants.EXTRA_CAPTCHA_URL, captchaUrl);
        quickLoginIntent.putExtra(Constants.EXTRA_STEP1_TOKEN, step1Token);
        quickLoginIntent.putExtra(Constants.EXTRA_VERIFY_ONLY, verifyOnly);
        quickLoginIntent.putExtra(Constants.EXTRA_TITLE, title);
        if (metaLoginDataStep2 != null) {
            quickLoginIntent.putExtra(Constants.EXTRA_SIGN, metaLoginDataStep2.sign);
            quickLoginIntent.putExtra(Constants.EXTRA_QS, metaLoginDataStep2.qs);
            quickLoginIntent.putExtra(Constants.EXTRA_CALLBACK, metaLoginDataStep2.callback);
        }
        quickLoginIntent.addFlags(67108864);
        quickLoginIntent.addFlags(268435456);
        return quickLoginIntent;
    }

    public static Intent newAddAccountIntent(Context context, String serviceUrl, Bundle options) {
        Intent newAccountIntent = new Intent(Constants.ACTION_WELCOME);
        newAccountIntent.setPackage(context.getPackageName());
        newAccountIntent.putExtra(Constants.EXTRA_SERVICE_URL, serviceUrl);
        if (options != null) {
            if (options.getBoolean(Constants.EXTRA_CLEAR_WHEN_RESET, true)) {
                newAccountIntent.addFlags(524288);
            }
            options.remove(Constants.EXTRA_CLEAR_WHEN_RESET);
            newAccountIntent.putExtras(options);
        }
        newAccountIntent.addFlags(67108864);
        return newAccountIntent;
    }

    public static void getRegisterVerifyCode(String phone) throws IOException, AccessDeniedException, AuthenticationFailureException, InvalidResponseException, RegisteredPhoneException {
        XMPassport.getRegisterVerifyCode(phone);
    }

    public static void checkRegisterVerifyCode(String phone, String verifyCode) throws IOException, AccessDeniedException, AuthenticationFailureException, InvalidResponseException {
        XMPassport.checkRegisterVerifyCode(phone, verifyCode);
    }

    public static String regByEmail(String email, String password, String captcha, String ick) throws IOException, InvalidResponseException, NeedCaptchaException {
        return XMPassport.regByEmail(email, password, captcha, ick);
    }

    public static String regByPhone(String phone, String password, String verifyCode) throws IOException, InvalidResponseException {
        return XMPassport.regByPhone(phone, password, verifyCode);
    }

    public static void sendActivateEmail(String userId, String email) throws IOException, InvalidResponseException {
        MapContent regContent = null;
        try {
            regContent = SimpleRequest.getAsMap(URL_RESEND_EMAIL, new EasyMap().easyPut("userId", userId).easyPut("addressType", "EM").easyPut("address", email), null, true);
        } catch (AccessDeniedException e) {
            e.printStackTrace();
        } catch (AuthenticationFailureException e2) {
            e2.printStackTrace();
        }
        if (regContent == null) {
            throw new IOException("failed to register, no response");
        }
        if (!INT_0.equals(regContent.getFromBody(Mipay.KEY_CODE))) {
            throw new InvalidResponseException("invalid response, failed to send activate email");
        }
    }

    public static void setLoginResultAndFinish(Activity activity, int returnValue) {
        setLoginResultAndFinish(activity, returnValue, null, null);
    }

    public static void setLoginResultAndFinish(Activity activity, int returnValue, String userId, String authToken) {
        activity.setResult(returnValue);
        IAccountAuthenticatorResponse response = SetupData.getAccountAuthenticatorResponse();
        if (response != null) {
            Bundle bundle = new Bundle();
            if (!TextUtils.isEmpty(userId)) {
                bundle.putString(MiAccountManager.KEY_ACCOUNT_NAME, userId);
                bundle.putString(MiAccountManager.KEY_ACCOUNT_TYPE, MiAccountManager.XIAOMI_ACCOUNT_TYPE);
            }
            if (!TextUtils.isEmpty(authToken)) {
                bundle.putString(MiAccountManager.KEY_AUTHTOKEN, authToken);
            }
            bundle.putBoolean(MiAccountManager.KEY_BOOLEAN_RESULT, returnValue == -1);
            response.onResult(bundle);
            SetupData.setAccountAuthenticatorResponse(null);
        }
        activity.finish();
    }

    public static void navigateToAutoLogin(Activity activity, String accountName, String pwd, String packageName) {
        Intent intent = new Intent(Constants.ACTION_LOGIN);
        intent.setPackage(activity.getPackageName());
        intent.putExtra(Constants.EXTRA_AUTO_LOGIN_NAME, accountName);
        intent.putExtra(Constants.EXTRA_AUTO_LOGIN_PWD, pwd);
        intent.putExtra(Constants.EXTRA_AUTO_LOGIN, true);
        intent.putExtra(AccountManager.KEY_ANDROID_PACKAGE_NAME, packageName);
        intent.putExtras(activity.getIntent().getExtras());
        activity.startActivity(intent);
    }

    public static boolean isTrustedWebSsoUrl(String urlstr) {
        if (TextUtils.isEmpty(urlstr)) {
            return false;
        }
        try {
            URL url = new URL(urlstr);
            try {
                URL urlLegal = new URL(XMPassport.ACCOUNT_DOMAIN);
                String protocalLegal = urlLegal.getProtocol();
                String protocal = url.getProtocol();
                String hostLegal = urlLegal.getHost();
                String host = url.getHost();
                if (protocalLegal.equalsIgnoreCase(protocal) && hostLegal.equalsIgnoreCase(host)) {
                    return true;
                }
                return false;
            } catch (MalformedURLException e) {
                URL url2 = url;
                return false;
            }
        } catch (MalformedURLException e2) {
            return false;
        }
    }
}
