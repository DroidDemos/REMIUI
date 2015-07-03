package com.xiaomi.account;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import com.xiaomi.account.IXiaomiAuthService.Stub;
import com.xiaomi.account.ui.AuthorizeActivity;
import com.xiaomi.account.utils.CloudHelper;
import com.xiaomi.account.utils.PrefUtils;
import com.xiaomi.account.utils.SysHelper;
import com.xiaomi.accountsdk.account.data.ExtendedAuthToken;
import com.xiaomi.accountsdk.account.data.MiCloudAuthInfo;
import com.xiaomi.accountsdk.account.data.XiaomiUserInfo;
import com.xiaomi.accountsdk.account.exception.NeedOAuthorizeException;
import com.xiaomi.accountsdk.request.AccessDeniedException;
import com.xiaomi.accountsdk.request.AuthenticationFailureException;
import com.xiaomi.passport.accountmanager.MiAccountManager;
import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import miui.telephony.exception.IllegalDeviceException;

public class XiaomiAuthService extends Service {
    private static final long DAY_TIME_IN_MILLIS = 86400000;
    private static final int ERROR_AUTH_FAILED = -1003;
    private static final int ERROR_LOGIN_FAILED = -1002;
    private static final int ERROR_NEED_AUTHORIZE = -1001;
    private static final int ERROR_OK = 0;
    private static final String EXTRA_ACCESS_TOKEN = "extra_access_token";
    private static final String EXTRA_AVATAR_URL = "extra_avatar_url";
    private static final String EXTRA_CLIENT_ID = "extra_client_id";
    private static final String EXTRA_ERROR_CODE = "extra_error_code";
    private static final String EXTRA_ERROR_DESCRIPTION = "extra_error_description";
    private static final String EXTRA_EXPIRES_IN = "extra_expires_in";
    private static final String EXTRA_INTENT = "extra_intent";
    private static final String EXTRA_MAC_ALGORITHM = "extra_mac_algorithm";
    private static final String EXTRA_MAC_KEY = "extra_mac_key";
    private static final String EXTRA_NICK_NAME = "extra_nick_name";
    private static final String EXTRA_REDIRECT_URI = "extra_redirect_uri";
    private static final String EXTRA_TOKEN_TYPE = "extra_token_type";
    private static final String EXTRA_USER_ID = "extra_user_id";
    private static final String EXTRA_USER_NAME = "extra_user_name";
    private static final String OAUTH_TOKEN_TYPE = "oauth2.0";
    private static final String PREF_LAST_UPDATE_OAUTH2_SERVICE_TOKEN_TIME = "pref_last_update_oauth2_service_token_time";
    private static final String TAG = "XiaomiAuthService";
    private ThreadPoolExecutor mTaskExecutor;
    private Transport mTransport;

    private static class AsyncFuture<V> extends FutureTask<V> {
        public AsyncFuture() {
            super(new Callable<V>() {
                public V call() throws Exception {
                    throw new IllegalStateException("this should never be called");
                }
            });
        }

        public void setResult(V v) {
            set(v);
        }
    }

    private class GetMiCloudUserInfo implements Runnable {
        private Account mAccount;
        private Bundle mOptions;
        private AsyncFuture<Bundle> mResponse;

        public GetMiCloudUserInfo(Account account, Bundle options, AsyncFuture<Bundle> response) {
            this.mAccount = account;
            this.mOptions = options;
            this.mResponse = response;
        }

        public void run() {
            Bundle resBundle = new Bundle();
            XiaomiUserInfo userInfo = SysHelper.queryXiaomiUserInfo(XiaomiAuthService.this, this.mAccount);
            if (userInfo != null) {
                resBundle.putInt(XiaomiAuthService.EXTRA_ERROR_CODE, XiaomiAuthService.ERROR_OK);
                resBundle.putString(XiaomiAuthService.EXTRA_ERROR_DESCRIPTION, "success");
                resBundle.putString(XiaomiAuthService.EXTRA_USER_NAME, userInfo.getUserName());
                resBundle.putString(XiaomiAuthService.EXTRA_NICK_NAME, userInfo.getNickName());
                resBundle.putString(XiaomiAuthService.EXTRA_AVATAR_URL, userInfo.getAvatarAddress());
            } else {
                resBundle.putInt(XiaomiAuthService.EXTRA_ERROR_CODE, XiaomiAuthService.ERROR_AUTH_FAILED);
                resBundle.putString(XiaomiAuthService.EXTRA_ERROR_DESCRIPTION, "cannot get user info from system");
            }
            this.mResponse.setResult(resBundle);
        }
    }

    private class GetOAuthInfo implements Runnable {
        private Account mAccount;
        private Bundle mOptions;
        private AsyncFuture<Bundle> mResponse;

        public GetOAuthInfo(Account account, Bundle options, AsyncFuture<Bundle> response) {
            this.mAccount = account;
            this.mOptions = options;
            this.mResponse = response;
        }

        private String getAuthToken(AccountManager am) {
            String str = null;
            try {
                str = ((Bundle) am.getAuthToken(this.mAccount, XiaomiAuthService.OAUTH_TOKEN_TYPE, null, true, null, null).getResult()).getString(MiAccountManager.KEY_AUTHTOKEN);
            } catch (Exception e) {
                Log.e(XiaomiAuthService.TAG, "getAuthToken ", e);
            }
            return str;
        }

        private boolean hasTokenExpired(Context context, AccountManager am, String authToken) {
            if (Math.abs(System.currentTimeMillis() - PrefUtils.getLong(context, XiaomiAuthService.PREF_LAST_UPDATE_OAUTH2_SERVICE_TOKEN_TIME, 0)) <= XiaomiAuthService.DAY_TIME_IN_MILLIS) {
                return false;
            }
            am.invalidateAuthToken(this.mAccount.type, authToken);
            Log.d(XiaomiAuthService.TAG, "Refresh oauth service token time: " + System.currentTimeMillis());
            PrefUtils.saveLong(context, XiaomiAuthService.PREF_LAST_UPDATE_OAUTH2_SERVICE_TOKEN_TIME, System.currentTimeMillis());
            return true;
        }

        public void run() {
            AccountManager am = AccountManager.get(XiaomiAuthService.this);
            String clientId = this.mOptions.getString(XiaomiAuthService.EXTRA_CLIENT_ID);
            String redirectUri = this.mOptions.getString(XiaomiAuthService.EXTRA_REDIRECT_URI);
            MiCloudAuthInfo info = null;
            Bundle resBundle = new Bundle();
            String authToken = getAuthToken(am);
            if (TextUtils.isEmpty(authToken) || hasTokenExpired(XiaomiAuthService.this, am, authToken)) {
                authToken = getAuthToken(am);
            }
            ExtendedAuthToken serviceToken = ExtendedAuthToken.parse(authToken);
            if (serviceToken == null || serviceToken.authToken == null || serviceToken.security == null) {
                resBundle.putInt(XiaomiAuthService.EXTRA_ERROR_CODE, XiaomiAuthService.ERROR_LOGIN_FAILED);
                resBundle.putString(XiaomiAuthService.EXTRA_ERROR_DESCRIPTION, "login using system account failed");
                this.mResponse.setResult(resBundle);
                return;
            }
            try {
                info = CloudHelper.getOAuthInfo(XiaomiAuthService.this, this.mAccount.name, clientId, redirectUri, this.mOptions, serviceToken.authToken, serviceToken.security);
            } catch (NeedOAuthorizeException e) {
                setupCookieForAuthorize(AuthorizeActivity.ACCOUNT_OAUTH_BASE, this.mAccount.name, serviceToken.authToken);
                resBundle.putInt(XiaomiAuthService.EXTRA_ERROR_CODE, XiaomiAuthService.ERROR_NEED_AUTHORIZE);
                resBundle.putString(XiaomiAuthService.EXTRA_ERROR_DESCRIPTION, "need authorized by user");
                resBundle.putParcelable(XiaomiAuthService.EXTRA_INTENT, AuthorizeActivity.getAuthorizeIntent(XiaomiAuthService.this, clientId, redirectUri, this.mOptions));
                this.mResponse.setResult(resBundle);
                return;
            } catch (AuthenticationFailureException e2) {
                Log.e(XiaomiAuthService.TAG, "auth failure when get oauth info", e2);
            } catch (AccessDeniedException e3) {
                Log.e(XiaomiAuthService.TAG, "access denied when get oauth info", e3);
            } catch (IOException e4) {
                Log.e(XiaomiAuthService.TAG, "io error when get oauth info", e4);
            } catch (RuntimeException e5) {
                Log.e(XiaomiAuthService.TAG, "exception when get oauth info", e5);
            }
            if (info != null) {
                resBundle.putString(XiaomiAuthService.EXTRA_USER_ID, this.mAccount.name);
                resBundle.putInt(XiaomiAuthService.EXTRA_ERROR_CODE, XiaomiAuthService.ERROR_OK);
                resBundle.putString(XiaomiAuthService.EXTRA_ERROR_DESCRIPTION, "success");
                resBundle.putString(XiaomiAuthService.EXTRA_ACCESS_TOKEN, info.getAccessToken());
                resBundle.putString(XiaomiAuthService.EXTRA_TOKEN_TYPE, info.getTokenType());
                resBundle.putString(XiaomiAuthService.EXTRA_MAC_KEY, info.getMacKey());
                resBundle.putString(XiaomiAuthService.EXTRA_MAC_ALGORITHM, info.getMacAlgorithm());
                resBundle.putInt(XiaomiAuthService.EXTRA_EXPIRES_IN, info.getExpires());
            } else {
                resBundle.putInt(XiaomiAuthService.EXTRA_ERROR_CODE, XiaomiAuthService.ERROR_AUTH_FAILED);
                resBundle.putString(XiaomiAuthService.EXTRA_ERROR_DESCRIPTION, "cannot get auth info from system");
            }
            this.mResponse.setResult(resBundle);
        }

        private void setupCookieForAuthorize(String url, String userId, String serviceToken) {
            if (!TextUtils.isEmpty(url)) {
                String hashedDeviceId = null;
                try {
                    hashedDeviceId = CloudHelper.safeGetHashedDeviceId();
                } catch (IllegalDeviceException e) {
                }
                CookieSyncManager.createInstance(XiaomiAuthService.this);
                CookieManager cookieMgr = CookieManager.getInstance();
                cookieMgr.removeAllCookie();
                cookieMgr.setCookie(url, getCookie("userId", userId));
                cookieMgr.setCookie(url, getCookie("serviceToken", serviceToken));
                if (!TextUtils.isEmpty(hashedDeviceId)) {
                    cookieMgr.setCookie(url, getCookie("deviceId", hashedDeviceId));
                }
                CookieSyncManager.getInstance().sync();
            }
        }

        private String getCookie(String name, String value) {
            return name + "=" + value + "; domain = account.xiaomi.com; path=/";
        }
    }

    private class Transport extends Stub {
        private Transport() {
        }

        public Bundle getMiCloudUserInfo(Account account, Bundle options) throws RemoteException {
            AsyncFuture<Bundle> future = new AsyncFuture();
            XiaomiAuthService.this.mTaskExecutor.execute(new GetMiCloudUserInfo(account, options, future));
            try {
                return (Bundle) future.get();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return null;
            } catch (Exception e2) {
                return null;
            }
        }

        public Bundle getMiCloudAccessToken(Account account, Bundle options) throws RemoteException {
            AsyncFuture<Bundle> future = new AsyncFuture();
            XiaomiAuthService.this.mTaskExecutor.execute(new GetOAuthInfo(account, options, future));
            try {
                return (Bundle) future.get();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return null;
            } catch (Exception e2) {
                return null;
            }
        }

        public Bundle getSnsAccessToken(Account account, Bundle options) throws RemoteException {
            throw new RuntimeException("don't call this");
        }

        public void invalidateAccessToken(Account account, Bundle options) throws RemoteException {
        }
    }

    public XiaomiAuthService() {
        this.mTransport = new Transport();
        this.mTaskExecutor = new ThreadPoolExecutor(1, 5, 30, TimeUnit.SECONDS, new LinkedBlockingQueue());
    }

    public IBinder onBind(Intent intent) {
        return this.mTransport.asBinder();
    }
}
