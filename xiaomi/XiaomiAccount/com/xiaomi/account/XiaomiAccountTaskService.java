package com.xiaomi.account;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import com.xiaomi.account.data.AsyncTaskResult;
import com.xiaomi.account.data.DeviceInfo;
import com.xiaomi.account.data.SetupData;
import com.xiaomi.account.data.XiaomiUserProfile;
import com.xiaomi.account.utils.CloudHelper;
import com.xiaomi.account.utils.SysHelper;
import com.xiaomi.accountsdk.account.data.ExtendedAuthToken;
import com.xiaomi.accountsdk.account.data.XiaomiUserInfo;
import com.xiaomi.accountsdk.request.AccessDeniedException;
import com.xiaomi.accountsdk.request.AuthenticationFailureException;
import com.xiaomi.accountsdk.request.CipherException;
import com.xiaomi.accountsdk.request.InvalidResponseException;
import com.xiaomi.accountsdk.request.SimpleRequest;
import com.xiaomi.accountsdk.request.SimpleRequest.StreamContent;
import com.xiaomi.accountsdk.utils.DeviceModelUtil;
import com.xiaomi.passport.Constants;
import com.xiaomi.passport.ui.AccountUnactivatedFragment;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;
import miui.accounts.ExtraAccountManager;
import miui.cloud.sync.MiCloudStatusInfo;
import miui.cloud.sync.MiCloudStatusInfo.QuotaInfo;

public class XiaomiAccountTaskService extends Service {
    public static final String ACTION_QUERY_DEVICE_INFO = "com.xiaomi.account.action.QUERY_DEVICE_INFO";
    public static final String ACTION_QUERY_DEVICE_LIST = "com.xiaomi.account.action.QUERY_DEVICE_LIST";
    public static final String ACTION_QUERY_MICLOUD_STATUS = "com.xiaomi.account.action.QUERY_MICLOUD_STATUS";
    public static final String ACTION_QUERY_USER_INFO = "com.xiaomi.account.action.QUERY_USER_INFO";
    public static final String ACTION_UPLOAD_DEVICE_INFO = "com.xiaomi.account.action.UPLOAD_DEVICE_INFO";
    private static final int MSG_ARG_NOT_USED_PLACEHOLDER = -1;
    private static final int MSG_QUERY_DEVICE_INFO = 4;
    private static final int MSG_QUERY_DEVICE_LIST = 5;
    private static final int MSG_QUERY_MICLOUD_STATUS = 2;
    private static final int MSG_QUERY_USER_INFO = 1;
    private static final int MSG_UPLOAD_DEVICE_INFO = 3;
    private static final String TAG = "XiaomiAccountTaskService";
    public static final String XIAOMI_ACCOUNT_TASK_SERVICE_PACKAGENAME = "com.xiaomi.account";
    private static HashMap<String, Object> mDeviceInfo;
    private HandlerThread mHandlerThread;
    private Handler mWorkHandler;

    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onCreate() {
        super.onCreate();
        this.mHandlerThread = new HandlerThread(XiaomiAccountTaskService.class.getSimpleName());
        this.mHandlerThread.start();
        this.mWorkHandler = new Handler(this.mHandlerThread.getLooper()) {
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case XiaomiAccountTaskService.MSG_QUERY_USER_INFO /*1*/:
                        XiaomiAccountTaskService.this.handleQueryUserInfo(msg.arg1);
                        return;
                    case XiaomiAccountTaskService.MSG_QUERY_MICLOUD_STATUS /*2*/:
                        XiaomiAccountTaskService.this.handleQueryMiCloudStatus(msg.arg1);
                        return;
                    case XiaomiAccountTaskService.MSG_UPLOAD_DEVICE_INFO /*3*/:
                        XiaomiAccountTaskService.this.handleUploadDeviceInfo(msg.arg1);
                        return;
                    case XiaomiAccountTaskService.MSG_QUERY_DEVICE_INFO /*4*/:
                        XiaomiAccountTaskService.this.handleQueryDeviceInfo(msg.arg1);
                        return;
                    case XiaomiAccountTaskService.MSG_QUERY_DEVICE_LIST /*5*/:
                        XiaomiAccountTaskService.this.handleQueryDeviceList(msg.arg1);
                        return;
                    default:
                        return;
                }
            }
        };
    }

    public void onDestroy() {
        this.mHandlerThread.quit();
        super.onDestroy();
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent == null) {
            stopSelf(startId);
        } else {
            String action = intent.getAction();
            if (ACTION_QUERY_USER_INFO.equals(action)) {
                this.mWorkHandler.removeMessages(MSG_QUERY_USER_INFO);
                this.mWorkHandler.obtainMessage(MSG_QUERY_USER_INFO, startId, MSG_ARG_NOT_USED_PLACEHOLDER).sendToTarget();
            } else if (ACTION_QUERY_MICLOUD_STATUS.equals(action)) {
                this.mWorkHandler.removeMessages(MSG_QUERY_MICLOUD_STATUS);
                this.mWorkHandler.obtainMessage(MSG_QUERY_MICLOUD_STATUS, startId, MSG_ARG_NOT_USED_PLACEHOLDER).sendToTarget();
            } else if (ACTION_UPLOAD_DEVICE_INFO.equals(action)) {
                mDeviceInfo = (HashMap) intent.getSerializableExtra("uploadDeviceInfo");
                this.mWorkHandler.removeMessages(MSG_UPLOAD_DEVICE_INFO);
                this.mWorkHandler.obtainMessage(MSG_UPLOAD_DEVICE_INFO, startId, MSG_ARG_NOT_USED_PLACEHOLDER).sendToTarget();
            } else if (ACTION_QUERY_DEVICE_INFO.equals(action)) {
                this.mWorkHandler.removeMessages(MSG_QUERY_DEVICE_INFO);
                this.mWorkHandler.obtainMessage(MSG_QUERY_DEVICE_INFO, startId, MSG_ARG_NOT_USED_PLACEHOLDER).sendToTarget();
            } else if (ACTION_QUERY_DEVICE_LIST.equals(action)) {
                this.mWorkHandler.removeMessages(MSG_QUERY_DEVICE_LIST);
                this.mWorkHandler.obtainMessage(MSG_QUERY_DEVICE_LIST, startId, MSG_ARG_NOT_USED_PLACEHOLDER).sendToTarget();
            } else {
                stopSelf(startId);
            }
        }
        return MSG_QUERY_MICLOUD_STATUS;
    }

    private void handleQueryMiCloudStatus(int startCmdId) {
        Account account = ExtraAccountManager.getXiaomiAccount(this);
        if (account == null) {
            Log.w(TAG, "no Xiaomi account, skip to query user info");
            return;
        }
        notifyMiCloudStorageChanged(queryMiCloudStorageInfo(account));
        stopSelf(startCmdId);
    }

    private long queryMiCloudStorageInfo(Account account) {
        long remainingSpace = -1;
        if (account == null) {
            Log.w(TAG, "no Xiaomi account");
            return -1;
        }
        String quota = ((AccountManager) getSystemService(AccountUnactivatedFragment.EXTRA_ACCOUNT)).getUserData(account, Constants.EXTRA_MICLOUD_STATUS_INFO_QUOTA);
        if (!TextUtils.isEmpty(quota)) {
            MiCloudStatusInfo info = new MiCloudStatusInfo(account.name);
            info.parseQuotaString(quota);
            QuotaInfo qInfo = info.getQuotaInfo();
            if (qInfo != null) {
                remainingSpace = qInfo.getTotal() - qInfo.getUsed();
            }
        }
        return remainingSpace;
    }

    private void handleQueryUserInfo(int startCmdId) {
        Account account = ExtraAccountManager.getXiaomiAccount(this);
        if (account == null) {
            Log.w(TAG, "no Xiaomi account, skip to query user info");
            stopSelf(startCmdId);
            return;
        }
        XiaomiUserInfo userInfo = SysHelper.queryXiaomiUserInfo(this, account);
        if (userInfo != null) {
            saveXiaomiUserInfo(account, userInfo);
            notifyUserInfoChanged();
        }
        HashMap<String, String> snsInfo = SysHelper.querySnsInfoFromServer(this, account);
        if (snsInfo != null) {
            saveSnsInfo(account, snsInfo);
            notifySnsInfoChanged();
        }
        XiaomiUserProfile userProfile = SysHelper.queryXiaomiUserProfile(this, account);
        if (userProfile != null) {
            saveXiaomiUserProfile(account, userProfile);
            notifyUserProfileChanged();
        }
        stopSelf(startCmdId);
    }

    private void saveXiaomiUserInfo(Account account, XiaomiUserInfo userInfo) {
        if (account == null) {
            Log.w(TAG, "no Xiaomi account, skip to save user info");
            return;
        }
        AccountManager am = (AccountManager) getSystemService(AccountUnactivatedFragment.EXTRA_ACCOUNT);
        am.setUserData(account, Constants.ACCOUNT_USER_NAME, userInfo.getUserName());
        am.setUserData(account, Constants.ACCOUNT_NICK_NAME, userInfo.getNickName());
        am.setUserData(account, Constants.ACCOUNT_USER_EMAIL, userInfo.getEmail());
        am.setUserData(account, Constants.ACCOUNT_USER_PHONE, userInfo.getPhone());
        String avatarAddr = userInfo.getAvatarAddress();
        String cachedAvatarAddr = am.getUserData(account, Constants.ACCOUNT_AVATAR_URL);
        String avatarFileName = Constants.AVATAR_FILE_NAME + account.name;
        File cachedAvatarFile = getFileStreamPath(avatarFileName);
        if (avatarAddr == null) {
            return;
        }
        if (!TextUtils.equals(avatarAddr, cachedAvatarAddr) || !cachedAvatarFile.isFile() || !cachedAvatarFile.exists()) {
            StreamContent avatarContent = null;
            try {
                avatarContent = SimpleRequest.getAsStream(avatarAddr, null, null);
            } catch (IOException e) {
                Log.e(TAG, "IO error when download avatar", e);
            } catch (AccessDeniedException e2) {
                Log.e(TAG, "access denied when download avatar", e2);
            } catch (AuthenticationFailureException e3) {
                Log.e(TAG, "auth failed when download avatar", e3);
            }
            File avatarFile = null;
            if (avatarContent != null) {
                try {
                    avatarFile = SysHelper.saveAsFile(this, avatarContent.getStream(), avatarFileName);
                } catch (IOException e4) {
                    Log.e(TAG, "failed to save avatar", e4);
                } finally {
                    avatarContent.closeStream();
                }
            }
            if (avatarFile != null) {
                am.setUserData(account, Constants.ACCOUNT_AVATAR_URL, avatarAddr);
                am.setUserData(account, Constants.ACCOUNT_AVATAR_FILE_NAME, avatarFileName);
            }
        }
    }

    private void saveSnsInfo(Account account, HashMap<String, String> snsInfo) {
        if (account == null) {
            Log.w(TAG, "no Xiaomi account, skip to save sns info");
            return;
        }
        AccountManager am = (AccountManager) getSystemService(AccountUnactivatedFragment.EXTRA_ACCOUNT);
        if (snsInfo != null) {
            for (Entry<String, String> entry : snsInfo.entrySet()) {
                am.setUserData(account, (String) entry.getKey(), (String) entry.getValue());
            }
        }
    }

    private void saveXiaomiUserProfile(Account account, XiaomiUserProfile userProfile) {
        if (account == null) {
            Log.w(TAG, "no Xiaomi account, skip to save user profile");
            return;
        }
        AccountManager am = (AccountManager) getSystemService(AccountUnactivatedFragment.EXTRA_ACCOUNT);
        if (userProfile.getGender() != null) {
            am.setUserData(account, Constants.ACCOUNT_USER_GENDER, userProfile.getGender().getType());
        }
        if (userProfile.getBirthday() != null) {
            am.setUserData(account, Constants.ACCOUNT_USER_BIRTHDAY, new SimpleDateFormat(Constants.SIMPLE_DATE_FORMAT).format(userProfile.getBirthday().getTime()));
        }
    }

    private void notifyUserInfoChanged() {
        sendBroadcast(new Intent("com.xiaomi.action.XIAOMI_USER_INFO_CHANGED"));
    }

    private void notifySnsInfoChanged() {
        Intent intent = new Intent(Constants.ACTION_XIAOMI_SNS_INFO_CHANGED);
        intent.setPackage(getPackageName());
        sendBroadcast(intent);
    }

    private void notifyUserProfileChanged() {
        Intent intent = new Intent(Constants.ACTION_XIAOMI_USER_PROFILE_CHANGED);
        intent.setPackage(getPackageName());
        sendBroadcast(intent);
    }

    private void notifyMiCloudStorageChanged(long remainingSpace) {
        Intent intent = new Intent(Constants.ACTION_MICLOUD_SPACE_INFO_CHANGED);
        intent.setPackage(getPackageName());
        intent.putExtra(Constants.EXTRA_MICLOUD_SPACE_STATUS, remainingSpace);
        sendBroadcast(intent);
    }

    private void handleUploadDeviceInfo(int startCmdId) {
        int exceptionType = 0;
        for (int count = 0; count < MSG_QUERY_MICLOUD_STATUS; count += MSG_QUERY_USER_INFO) {
            try {
                exceptionType = SysHelper.uploadInfoToServer(this, mDeviceInfo, 0, Constants.DEVICE_INFO_SID) ? 0 : MSG_QUERY_DEVICE_LIST;
            } catch (IOException e) {
                Log.e(TAG, "handleUploadDeviceInfo error", e);
                exceptionType = MSG_QUERY_MICLOUD_STATUS;
            } catch (AuthenticationFailureException e2) {
                Log.e(TAG, "handleUploadDeviceInfo error", e2);
                exceptionType = MSG_QUERY_USER_INFO;
            } catch (AccessDeniedException e3) {
                Log.e(TAG, "handleUploadDeviceInfo error", e3);
                exceptionType = MSG_QUERY_DEVICE_INFO;
            } catch (InvalidResponseException e4) {
                Log.e(TAG, "handleUploadDeviceInfo error", e4);
                exceptionType = MSG_UPLOAD_DEVICE_INFO;
            } catch (CipherException e5) {
                Log.e(TAG, "handleUploadDeviceInfo error", e5);
                exceptionType = MSG_UPLOAD_DEVICE_INFO;
            }
        }
        AsyncTaskResult taskResult = new AsyncTaskResult(exceptionType);
        if (taskResult.hasException()) {
            Log.i(TAG, getString(taskResult.getExceptionReason()));
        }
        stopSelf(startCmdId);
    }

    private void handleQueryDeviceInfo(int startCmdId) {
        queryDeviceInfoOrDeviceList(ACTION_QUERY_DEVICE_INFO);
        stopSelf(startCmdId);
    }

    private void handleQueryDeviceList(int startCmdId) {
        queryDeviceInfoOrDeviceList(ACTION_QUERY_DEVICE_LIST);
        stopSelf(startCmdId);
    }

    private void queryDeviceInfoOrDeviceList(String action) {
        Account account = ExtraAccountManager.getXiaomiAccount(this);
        if (account == null) {
            Log.w(TAG, "no Xiaomi account");
            return;
        }
        AccountManager am = AccountManager.get(this);
        int count = 0;
        while (count < MSG_QUERY_MICLOUD_STATUS) {
            ExtendedAuthToken extendedAuthToken = ExtendedAuthToken.parse(SysHelper.getAuthToken(am, account, Constants.DEVICE_INFO_SID));
            String encryptedUserId = am.getUserData(account, Constants.KEY_ENCRYPTED_USER_ID);
            if (extendedAuthToken != null) {
                String serviceToken = extendedAuthToken.authToken;
                String security = extendedAuthToken.security;
                if (serviceToken != null && security != null) {
                    try {
                        if (ACTION_QUERY_DEVICE_INFO.equals(action)) {
                            DeviceInfo deviceInfo = CloudHelper.getDeviceInfo(account.name, encryptedUserId, serviceToken, security);
                            if (deviceInfo == null) {
                                Log.i(TAG, "device info is null");
                                return;
                            }
                            DeviceModelUtil.initializeDeviceModelData(this);
                            saveImageInfo(deviceInfo.getModel());
                            notifyDeviceInfoChanged(deviceInfo);
                            return;
                        } else if (ACTION_QUERY_DEVICE_LIST.equals(action)) {
                            ArrayList<DeviceInfo> deviceList = CloudHelper.getDeviceList(account.name, encryptedUserId, serviceToken, security);
                            if (deviceList == null) {
                                Log.i(TAG, "device list is null");
                            } else {
                                DeviceModelUtil.initializeDeviceModelData(this);
                                for (int i = 0; i < deviceList.size(); i += MSG_QUERY_USER_INFO) {
                                    saveImageInfo(((DeviceInfo) deviceList.get(i)).getModel());
                                }
                            }
                            SetupData.setDeviceList(deviceList);
                            notifyDeviceListChanged();
                            return;
                        } else {
                            return;
                        }
                    } catch (InvalidResponseException e) {
                        Log.e(TAG, "invalid response " + action, e);
                        return;
                    } catch (CipherException e2) {
                        Log.e(TAG, "CipherException " + action, e2);
                        return;
                    } catch (IOException e3) {
                        Log.e(TAG, "IOException " + action, e3);
                        return;
                    } catch (AuthenticationFailureException e4) {
                        Log.e(TAG, "auth failure " + action, e4);
                        am.invalidateAuthToken(account.type, null);
                        count += MSG_QUERY_USER_INFO;
                    } catch (AccessDeniedException e5) {
                        Log.e(TAG, "access denied " + action, e5);
                        return;
                    }
                }
                return;
            }
            return;
        }
    }

    private void saveImageInfo(String model) {
        if (model != null) {
            String imageFileName = model;
            String url = DeviceModelUtil.getDeviceImage(model);
            if (TextUtils.isEmpty(url)) {
                Log.d(TAG, "image url is null");
            } else if (needDownloadImageFile(getFileStreamPath(imageFileName))) {
                StreamContent imageContent = null;
                try {
                    imageContent = SimpleRequest.getAsStream(url, null, null);
                    if (imageContent != null) {
                        SysHelper.saveAsFile(this, imageContent.getStream(), imageFileName);
                    }
                    if (imageContent != null) {
                        imageContent.closeStream();
                    }
                } catch (IOException e) {
                    Log.e(TAG, "IO error when download image", e);
                    if (imageContent != null) {
                        imageContent.closeStream();
                    }
                } catch (AccessDeniedException e2) {
                    Log.e(TAG, "access denied when download image", e2);
                    if (imageContent != null) {
                        imageContent.closeStream();
                    }
                } catch (AuthenticationFailureException e3) {
                    Log.e(TAG, "auth failed when download image", e3);
                    if (imageContent != null) {
                        imageContent.closeStream();
                    }
                } catch (Throwable th) {
                    if (imageContent != null) {
                        imageContent.closeStream();
                    }
                }
            } else {
                Log.d(TAG, "image file exists and less than 7 days, does not need to download image");
            }
        }
    }

    private static boolean needDownloadImageFile(File file) {
        return (file.exists() && file.isFile() && System.currentTimeMillis() - file.lastModified() < TimeUnit.DAYS.toMillis(7)) ? false : true;
    }

    private void notifyDeviceInfoChanged(DeviceInfo deviceInfo) {
        Intent intent = new Intent(Constants.ACTION_QUERY_DEVICE_INFO_SUCCEED);
        intent.putExtra(Constants.DEVICE_INFO_QUERY_RESULT, deviceInfo);
        intent.setPackage(getPackageName());
        sendBroadcast(intent);
    }

    private void notifyDeviceListChanged() {
        Intent intent = new Intent(Constants.ACTION_QUERY_DEVICE_LIST_SUCCEED);
        intent.setPackage(getPackageName());
        sendBroadcast(intent);
    }

    public static void startQueryMiCloudStatus(Context context) {
        Intent intent = new Intent(context, XiaomiAccountTaskService.class);
        intent.setAction(ACTION_QUERY_MICLOUD_STATUS);
        intent.setPackage(XIAOMI_ACCOUNT_TASK_SERVICE_PACKAGENAME);
        context.startService(intent);
    }

    public static void startQueryUserData(Context context) {
        Intent intent = new Intent(context, XiaomiAccountTaskService.class);
        intent.setAction(ACTION_QUERY_USER_INFO);
        intent.setPackage(XIAOMI_ACCOUNT_TASK_SERVICE_PACKAGENAME);
        context.startService(intent);
    }

    public static void startUploadDeviceInfo(Context context, HashMap<String, String> info) {
        Intent intent = new Intent();
        intent.setAction(ACTION_UPLOAD_DEVICE_INFO);
        intent.putExtra("uploadDeviceInfo", info);
        intent.setPackage(XIAOMI_ACCOUNT_TASK_SERVICE_PACKAGENAME);
        context.startService(intent);
    }

    public static void startQueryDeviceInfo(Context context) {
        Intent intent = new Intent();
        intent.setAction(ACTION_QUERY_DEVICE_INFO);
        intent.setPackage(XIAOMI_ACCOUNT_TASK_SERVICE_PACKAGENAME);
        context.startService(intent);
    }

    public static void startQueryDevicesList(Context context) {
        Intent intent = new Intent();
        intent.setAction(ACTION_QUERY_DEVICE_LIST);
        intent.setPackage(XIAOMI_ACCOUNT_TASK_SERVICE_PACKAGENAME);
        context.startService(intent);
    }
}
