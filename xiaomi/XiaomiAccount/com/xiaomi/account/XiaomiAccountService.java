package com.xiaomi.account;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import com.xiaomi.account.data.SnsAccountInfo;
import com.xiaomi.account.utils.CloudHelper;
import com.xiaomi.account.utils.SysHelper;
import com.xiaomi.accountsdk.account.IXiaomiAccountService.Stub;
import com.xiaomi.accountsdk.account.XMPassport;
import com.xiaomi.accountsdk.account.XiaomiAccount;
import com.xiaomi.accountsdk.account.data.ExtendedAuthToken;
import com.xiaomi.accountsdk.request.AccessDeniedException;
import com.xiaomi.accountsdk.request.AuthenticationFailureException;
import com.xiaomi.accountsdk.request.CipherException;
import com.xiaomi.accountsdk.request.InvalidResponseException;
import com.xiaomi.passport.Constants;
import com.xiaomi.passport.accountmanager.MiAccountManager;
import com.xiaomi.passport.ui.AccountUnactivatedFragment;
import java.io.FileNotFoundException;
import java.io.IOException;
import miui.accounts.ExtraAccountManager;

public class XiaomiAccountService extends Service {
    private static final String TAG = "XiaomiAccountService";
    private final Object UPDATE_USER_ACCESS_TOKEN_LOCK;
    private Stub mStub;

    public XiaomiAccountService() {
        this.UPDATE_USER_ACCESS_TOKEN_LOCK = new Object();
        this.mStub = new Stub() {
            public String getUserName(Account account) throws RemoteException {
                return AccountManager.get(XiaomiAccountService.this).getUserData(account, Constants.ACCOUNT_USER_NAME);
            }

            public String getNickName(Account account) throws RemoteException {
                return AccountManager.get(XiaomiAccountService.this).getUserData(account, Constants.ACCOUNT_NICK_NAME);
            }

            public String getEncryptedUserId(Account account) throws RemoteException {
                return AccountManager.get(XiaomiAccountService.this).getUserData(account, Constants.KEY_ENCRYPTED_USER_ID);
            }

            public String getEmail(Account account) throws RemoteException {
                return AccountManager.get(XiaomiAccountService.this).getUserData(account, Constants.ACCOUNT_USER_EMAIL);
            }

            public String getPhone(Account account) throws RemoteException {
                return AccountManager.get(XiaomiAccountService.this).getUserData(account, Constants.ACCOUNT_USER_PHONE);
            }

            public ParcelFileDescriptor getAvatarFd(Account account) throws RemoteException {
                ParcelFileDescriptor parcelFileDescriptor = null;
                String avatarFileName = AccountManager.get(XiaomiAccountService.this).getUserData(account, Constants.ACCOUNT_AVATAR_FILE_NAME);
                if (avatarFileName != null) {
                    try {
                        parcelFileDescriptor = ParcelFileDescriptor.open(XiaomiAccountService.this.getFileStreamPath(avatarFileName), 268435456);
                    } catch (FileNotFoundException e) {
                        Log.e(XiaomiAccountService.TAG, "Cannot find file " + avatarFileName);
                    }
                }
                return parcelFileDescriptor;
            }

            public XiaomiAccount getXiaomiAccount(boolean onlyLocal, Account account) throws RemoteException {
                AccountManager am = AccountManager.get(XiaomiAccountService.this);
                String name = am.getUserData(account, Constants.ACCOUNT_USER_NAME);
                String nickname = am.getUserData(account, Constants.ACCOUNT_NICK_NAME);
                String email = am.getUserData(account, Constants.ACCOUNT_USER_EMAIL);
                String phone = am.getUserData(account, Constants.ACCOUNT_USER_PHONE);
                String avatarfilename = am.getUserData(account, Constants.ACCOUNT_AVATAR_FILE_NAME);
                if (!onlyLocal) {
                    XiaomiAccountTaskService.startQueryUserData(XiaomiAccountService.this);
                }
                return new XiaomiAccount(name, nickname, email, phone, avatarfilename);
            }

            public ParcelFileDescriptor getAvatarFdByFileName(String filename) throws RemoteException {
                ParcelFileDescriptor parcelFileDescriptor = null;
                if (filename != null) {
                    try {
                        parcelFileDescriptor = ParcelFileDescriptor.open(XiaomiAccountService.this.getFileStreamPath(filename), 268435456);
                    } catch (FileNotFoundException e) {
                        Log.e(XiaomiAccountService.TAG, "Cannot find file " + filename);
                    }
                }
                return parcelFileDescriptor;
            }

            public String getAccessToken(Account account, String serviceId, String snsType, boolean refresh) throws RemoteException {
                if (account == null || TextUtils.isEmpty(serviceId) || TextUtils.isEmpty(snsType)) {
                    Log.e(XiaomiAccountService.TAG, "invalid parameter when getting access token");
                    return null;
                }
                String tokenStr = null;
                try {
                    tokenStr = ((Bundle) AccountManager.get(XiaomiAccountService.this).getAuthToken(account, serviceId, null, true, null, null).getResult()).getString(MiAccountManager.KEY_AUTHTOKEN);
                } catch (Exception e) {
                    Log.e(XiaomiAccountService.TAG, "get service token failed when getting access token: ", e);
                }
                ExtendedAuthToken authToken = ExtendedAuthToken.parse(tokenStr);
                if (authToken == null || authToken.authToken == null) {
                    Log.e(XiaomiAccountService.TAG, "get no service token when getting access token");
                    return null;
                } else if (!refresh) {
                    return XMPassport.getThirdPartyAccessToken(account.name, serviceId, snsType, authToken.authToken);
                } else {
                    try {
                        return XMPassport.refreshThirdPartyAccessToken(account.name, serviceId, snsType, authToken.authToken);
                    } catch (IOException e2) {
                        Log.e(XiaomiAccountService.TAG, "io exception when getting access token: ", e2);
                        return null;
                    } catch (AccessDeniedException e3) {
                        Log.e(XiaomiAccountService.TAG, "access denied when getting access token: ", e3);
                        return null;
                    } catch (AuthenticationFailureException e4) {
                        Log.e(XiaomiAccountService.TAG, "authenticate failure when getting access token: ", e4);
                        return null;
                    }
                }
            }

            public String getSnsAccessToken(String type) throws RemoteException {
                AccountManager am = (AccountManager) XiaomiAccountService.this.getSystemService(AccountUnactivatedFragment.EXTRA_ACCOUNT);
                Account xiaomiAccount = ExtraAccountManager.getXiaomiAccount(XiaomiAccountService.this.getApplicationContext());
                if (xiaomiAccount != null) {
                    return am.getUserData(xiaomiAccount, type);
                }
                Log.w(XiaomiAccountService.TAG, "getAccessToken: no Xiaomi account");
                return null;
            }

            public boolean invalidateSnsAccessToken(String type, String accessToken) throws RemoteException {
                boolean z = false;
                AccountManager am = (AccountManager) XiaomiAccountService.this.getSystemService(AccountUnactivatedFragment.EXTRA_ACCOUNT);
                Account xiaomiAccount = ExtraAccountManager.getXiaomiAccount(XiaomiAccountService.this.getApplicationContext());
                if (xiaomiAccount == null) {
                    Log.w(XiaomiAccountService.TAG, "getAccessToken: no Xiaomi account");
                } else {
                    String oldAccessToken;
                    SnsAccountInfo snsAccountInfo = SnsAccountInfo.newSnsAccountInfo(type);
                    synchronized (XiaomiAccountService.this.UPDATE_USER_ACCESS_TOKEN_LOCK) {
                        oldAccessToken = am.getUserData(xiaomiAccount, snsAccountInfo.getAccessTokenKey());
                    }
                    if (oldAccessToken != null && oldAccessToken.equals(accessToken)) {
                        z = false;
                        try {
                            ExtendedAuthToken serviceToken = ExtendedAuthToken.parse(SysHelper.getAuthToken(am, xiaomiAccount, Constants.PASSPORT_API_SID));
                            z = CloudHelper.deleteBindedAccessToken(xiaomiAccount.name, am.getUserData(xiaomiAccount, Constants.KEY_ENCRYPTED_USER_ID), snsAccountInfo.getSnsType(), serviceToken.authToken, serviceToken.security);
                        } catch (NullPointerException e) {
                            Log.e(XiaomiAccountService.TAG, "error while invalidateSnsAccessToken", e);
                        } catch (IOException e2) {
                            Log.e(XiaomiAccountService.TAG, "error while invalidateSnsAccessToken", e2);
                        } catch (AccessDeniedException e3) {
                            Log.e(XiaomiAccountService.TAG, "error while invalidateSnsAccessToken", e3);
                        } catch (AuthenticationFailureException e4) {
                            Log.e(XiaomiAccountService.TAG, "error while invalidateSnsAccessToken", e4);
                        } catch (InvalidResponseException e5) {
                            Log.e(XiaomiAccountService.TAG, "error while invalidateSnsAccessToken", e5);
                        } catch (CipherException e6) {
                            Log.e(XiaomiAccountService.TAG, "error while invalidateSnsAccessToken", e6);
                        }
                        if (z) {
                            synchronized (XiaomiAccountService.this.UPDATE_USER_ACCESS_TOKEN_LOCK) {
                                am.setUserData(xiaomiAccount, snsAccountInfo.getAccessTokenKey(), null);
                                am.setUserData(xiaomiAccount, snsAccountInfo.getUserIdKey(), null);
                                am.setUserData(xiaomiAccount, snsAccountInfo.getUserNameKey(), null);
                            }
                            XiaomiAccountService.this.sendBroadcast(new Intent("miui.intent.action.ACTION_IMPORT_SINA_WEIBO_CANCELED"));
                        }
                    }
                }
                return z;
            }
        };
    }

    public IBinder onBind(Intent intent) {
        return this.mStub;
    }
}
