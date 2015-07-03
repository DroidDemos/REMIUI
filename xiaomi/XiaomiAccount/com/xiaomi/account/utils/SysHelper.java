package com.xiaomi.account.utils;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AuthenticatorException;
import android.accounts.OperationCanceledException;
import android.app.Activity;
import android.app.WallpaperManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import com.android.internal.http.multipart.ByteArrayPartSource;
import com.android.internal.http.multipart.FilePart;
import com.android.internal.http.multipart.MultipartEntity;
import com.android.internal.http.multipart.Part;
import com.google.android.collect.Maps;
import com.xiaomi.account.R;
import com.xiaomi.account.XiaomiAccountTaskService;
import com.xiaomi.account.data.XiaomiUserProfile;
import com.xiaomi.account.ui.IQueryBalance;
import com.xiaomi.accountsdk.account.data.ExtendedAuthToken;
import com.xiaomi.accountsdk.account.data.XiaomiUserInfo;
import com.xiaomi.accountsdk.request.AccessDeniedException;
import com.xiaomi.accountsdk.request.AuthenticationFailureException;
import com.xiaomi.accountsdk.request.CipherException;
import com.xiaomi.accountsdk.request.InvalidResponseException;
import com.xiaomi.passport.Constants;
import com.xiaomi.passport.accountmanager.MiAccountManager;
import com.xiaomi.passport.ui.AccountUnactivatedFragment;
import com.xiaomi.passport.ui.LicenseActivity;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import miui.accounts.ExtraAccountManager;
import miui.app.ActionBar;
import miui.external.SdkConstants.SdkReturnCode;
import miui.graphics.BitmapFactory;
import miui.os.Build;
import miui.payment.PaymentManager;
import miui.payment.PaymentManager.PaymentListener;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class SysHelper {
    private static final String TAG = "SysHelper";

    public static Intent getCustomSyncSettings(Context context, Account account, String authority) {
        Intent intent = new Intent(authority + ".SYNC_SETTINGS");
        intent.putExtra(AccountUnactivatedFragment.EXTRA_ACCOUNT, account);
        intent.putExtra("authority", authority);
        return context.getPackageManager().queryIntentActivities(intent, 32).isEmpty() ? null : intent;
    }

    public static Intent buildPreviousActivityIntent(Context context, Intent current, Class<? extends Activity> previous) {
        Intent intent = new Intent(context, previous);
        if (!(current == null || current.getExtras() == null)) {
            intent.putExtras(current.getExtras());
        }
        intent.putExtra(Constants.EXTRA_FROM_BACK_NAVIGATION, true);
        intent.addFlags(67108864);
        return intent;
    }

    public static File saveAsFile(Context context, InputStream imgStream, String filename) throws IOException {
        BufferedInputStream bis = new BufferedInputStream(imgStream);
        OutputStream os = new BufferedOutputStream(context.openFileOutput(filename, 0));
        byte[] buffer = new byte[1024];
        while (true) {
            try {
                int count = bis.read(buffer);
                if (count == -1) {
                    break;
                }
                os.write(buffer, 0, count);
            } finally {
                try {
                    os.flush();
                } catch (IOException e) {
                }
                try {
                    os.close();
                } catch (IOException e2) {
                }
            }
        }
        try {
            os.close();
        } catch (IOException e3) {
        }
        return context.getFileStreamPath(filename);
    }

    public static int getEditViewInputType(boolean showPassword) {
        return (showPassword ? 144 : 128) | 1;
    }

    public static void getbackPassword(Activity activity) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse(com.xiaomi.account.Constants.PASSWORD_RECOVERY_URL));
        intent.addFlags(67108864);
        intent.addFlags(268435456);
        intent.addFlags(8388608);
        activity.startActivity(intent);
    }

    public static String addMaskToAString(String address, int startIndex, int endIndex) {
        if (address.length() <= endIndex) {
            endIndex = address.length();
        }
        if (Constants.MASK.length() + startIndex <= endIndex) {
            endIndex = startIndex + Constants.MASK.length();
        }
        endIndex = Math.min(Constants.MASK.length() + startIndex, Math.min(address.length(), endIndex));
        startIndex = Math.min(startIndex, endIndex);
        StringBuilder buffer = new StringBuilder();
        buffer.append(address.substring(0, startIndex));
        buffer.append(Constants.MASK.substring(0, endIndex - startIndex));
        buffer.append(address.substring(endIndex));
        return buffer.toString();
    }

    public static String addMaskToAPhone(String phone) {
        return addMaskToAString(phone, 3, 9);
    }

    public static String addMaskToAnEmail(String email) {
        int endIndex = email.indexOf("@");
        if (endIndex > 5) {
            endIndex--;
        }
        int startIndex = email.indexOf("@") + 2;
        return addMaskToAString(addMaskToAString(email, Math.min(2, endIndex), endIndex), startIndex, email.indexOf(".", startIndex));
    }

    public static void blurWallpaper(Activity activity) {
        activity.getWindow().setBackgroundDrawable(WallpaperManager.getInstance(activity).getFastDrawable());
    }

    public static Bitmap createRoundSquareAvatar(Activity activity, Bitmap origin) {
        return BitmapFactory.createPhoto(activity, origin);
    }

    public static void setMiuiActionBarTitle(Activity activity, String title) {
        if (Build.IS_TABLET) {
            ActionBar actionBar = (ActionBar) activity.getActionBar();
            if (title.length() != 0 && actionBar != null) {
                actionBar.setTitle(title);
            }
        }
    }

    public static void showMiuiActionBar(Activity activity, boolean show) {
        if (Build.IS_TABLET) {
            ActionBar actionBar = (ActionBar) activity.getActionBar();
            if (actionBar == null) {
                return;
            }
            if (show) {
                actionBar.show();
            } else {
                actionBar.hide();
            }
        }
    }

    public static void setSoftInputVisibility(Activity activity, int status) {
        ((InputMethodManager) activity.getSystemService("input_method")).toggleSoftInput(0, status);
    }

    public static void goToEmailPage(Activity activity, String url) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse(url));
        intent.addFlags(67108864);
        intent.addFlags(268435456);
        intent.addFlags(8388608);
        activity.startActivity(intent);
    }

    public static void setOrientationPortrait(Activity activity) {
        activity.setRequestedOrientation(1);
        activity.setRequestedOrientation(14);
    }

    public static void displaySoftInputIfNeed(Context context, View focusedView, boolean tryDisplay) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService("input_method");
        if (tryDisplay && context.getResources().getConfiguration().orientation == 1) {
            imm.showSoftInput(focusedView, 0);
        } else {
            imm.hideSoftInputFromWindow(focusedView.getWindowToken(), 0);
        }
    }

    public static void queryMibiBalance(final Activity activity, final IQueryBalance cb, final String verify) {
        new Thread() {
            public void run() {
                String paramString = SysHelper.getMibiVerifyString(activity, verify);
                if (!TextUtils.isEmpty(paramString)) {
                    PaymentManager.get(activity).getMiliBalance(activity, "", com.xiaomi.account.Constants.XIAOMI_ACCOUNT_MIBI_MARKET_ID, paramString, new PaymentListener() {
                        public void onSuccess(String paymentId, Bundle result) {
                            cb.onFinish(1, result.getLong("payment_trade_balance"));
                        }

                        public void onFailed(String paymentId, int code, String message, Bundle result) {
                            Log.e(SysHelper.TAG, message);
                            if (code == 4) {
                                cb.onFinish(2, 0);
                            } else if (code == 12) {
                                cb.onFinish(3, 0);
                            } else {
                                cb.onFinish(0, 0);
                            }
                        }
                    });
                } else if (cb != null) {
                    cb.onFinish(0, -1);
                }
            }
        }.start();
    }

    public static String getPubKey(Context context, String packageName) {
        try {
            return parseSignature(context.getPackageManager().getPackageInfo(packageName, 64).signatures[0].toByteArray());
        } catch (NameNotFoundException e) {
            Log.e(TAG, "Error when get pub key ", e);
            return null;
        }
    }

    private static String parseSignature(byte[] signature) {
        try {
            return ((X509Certificate) CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(signature))).getPublicKey().toString();
        } catch (CertificateException e) {
            Log.e(TAG, "Error when parseSignature ", e);
            return null;
        }
    }

    public static String getVerifyString(Context context) {
        String callerSignature = getPubKey(context, XiaomiAccountTaskService.XIAOMI_ACCOUNT_TASK_SERVICE_PACKAGENAME);
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("market", com.xiaomi.account.Constants.XIAOMI_ACCOUNT_MIBI_MARKET_ID);
            jsonObject.put("callerPackage", XiaomiAccountTaskService.XIAOMI_ACCOUNT_TASK_SERVICE_PACKAGENAME);
            jsonObject.put("callerSign", callerSignature);
            return jsonObject.toString();
        } catch (JSONException e) {
            Log.e(TAG, "Error when generate json ", e);
            return null;
        }
    }

    private static String getMibiVerifyString(Context context, String sign) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("verify", getVerifyString(context).toString());
            jsonObject.put("sign", sign);
        } catch (JSONException e) {
            Log.e(TAG, "Error when generate json ", e);
        }
        return jsonObject.toString();
    }

    public static XiaomiUserInfo queryXiaomiUserInfo(Context context, Account account) {
        XiaomiUserInfo xiaomiUserInfo = null;
        if (account != null) {
            String userId = account.name;
            AccountManager am = AccountManager.get(context);
            int count = 0;
            while (count < 2) {
                String authToken = getAuthToken(am, account, Constants.PASSPORT_API_SID);
                ExtendedAuthToken extendedAuthToken = ExtendedAuthToken.parse(authToken);
                if (extendedAuthToken == null) {
                    break;
                }
                String serviceToken = extendedAuthToken.authToken;
                String security = extendedAuthToken.security;
                if (serviceToken == null || security == null) {
                    break;
                }
                try {
                    xiaomiUserInfo = CloudHelper.getXiaomiUserInfo(userId, am.getUserData(account, Constants.KEY_ENCRYPTED_USER_ID), serviceToken, security);
                    break;
                } catch (InvalidResponseException e) {
                    Log.e(TAG, "invalid response when get user info", e);
                } catch (CipherException e2) {
                    Log.e(TAG, "CipherException when get user info", e2);
                } catch (IOException e3) {
                    Log.e(TAG, "IOException when get user info", e3);
                } catch (AuthenticationFailureException e4) {
                    Log.e(TAG, "auth failure when get user info", e4);
                    am.invalidateAuthToken(account.type, authToken);
                    count++;
                } catch (AccessDeniedException e5) {
                    Log.e(TAG, "access denied when get user info", e5);
                }
            }
        } else {
            Log.w(TAG, "no Xiaomi account, skip to query user info");
        }
        return xiaomiUserInfo;
    }

    public static HashMap<String, String> querySnsInfoFromServer(Context context, Account account) {
        if (account == null) {
            Log.w(TAG, "no Xiaomi account, skip to querySnsInfoFromServer");
            return null;
        }
        String userId = account.name;
        AccountManager am = AccountManager.get(context);
        int count = 0;
        while (count < 2) {
            String authToken = getAuthToken(am, account, Constants.PASSPORT_API_SID);
            ExtendedAuthToken extendedAuthToken = ExtendedAuthToken.parse(authToken);
            if (extendedAuthToken != null) {
                String serviceToken = extendedAuthToken.authToken;
                String security = extendedAuthToken.security;
                if (serviceToken == null || security == null) {
                    break;
                }
                String encryptedUserId = am.getUserData(account, Constants.KEY_ENCRYPTED_USER_ID);
                try {
                    HashMap<String, String> data = Maps.newHashMap();
                    data.put(com.xiaomi.account.Constants.EXTRA_SINA_WEIBO_ACCESSTOKEN, CloudHelper.getBindedAccessToken(userId, encryptedUserId, com.xiaomi.account.Constants.SINA_WEIBO_SNS_TYPE, serviceToken, security));
                    data.put(com.xiaomi.account.Constants.EXTRA_QQ_ACCESSTOKEN, CloudHelper.getBindedAccessToken(userId, encryptedUserId, com.xiaomi.account.Constants.QQ_SNS_TYPE, serviceToken, security));
                    if (!Build.IS_INTERNATIONAL_BUILD) {
                        return data;
                    }
                    data.put(com.xiaomi.account.Constants.EXTRA_FACEBOOK_ACCESSTOKEN, CloudHelper.getBindedAccessToken(userId, encryptedUserId, com.xiaomi.account.Constants.FACEBOOK_SNS_TYPE, serviceToken, security));
                    return data;
                } catch (InvalidResponseException e) {
                    Log.e(TAG, "invalid response when get user info", e);
                } catch (CipherException e2) {
                    Log.e(TAG, "CipherException when get user info", e2);
                } catch (IOException e3) {
                    Log.e(TAG, "IOException when get user info", e3);
                } catch (AuthenticationFailureException e4) {
                    Log.e(TAG, "auth failure when get user info", e4);
                    am.invalidateAuthToken(account.type, authToken);
                    count++;
                } catch (AccessDeniedException e5) {
                    Log.e(TAG, "access denied when get user info", e5);
                }
            } else {
                break;
            }
        }
        return null;
    }

    public static XiaomiUserProfile queryXiaomiUserProfile(Context context, Account account) {
        XiaomiUserProfile xiaomiUserProfile = null;
        if (account != null) {
            AccountManager am = AccountManager.get(context);
            int count = 0;
            while (count < 2) {
                String authToken = getAuthToken(am, account, Constants.PASSPORT_API_SID);
                ExtendedAuthToken extendedAuthToken = ExtendedAuthToken.parse(authToken);
                if (extendedAuthToken == null) {
                    break;
                }
                String serviceToken = extendedAuthToken.authToken;
                String security = extendedAuthToken.security;
                if (serviceToken == null || security == null) {
                    break;
                }
                try {
                    xiaomiUserProfile = CloudHelper.getXiaomiUserProfile(account.name, am.getUserData(account, Constants.KEY_ENCRYPTED_USER_ID), serviceToken, security);
                    break;
                } catch (InvalidResponseException e) {
                    Log.e(TAG, "invalid response when get user info", e);
                } catch (CipherException e2) {
                    Log.e(TAG, "CipherException when get user info", e2);
                } catch (IOException e3) {
                    Log.e(TAG, "IOException when get user info", e3);
                } catch (AuthenticationFailureException e4) {
                    Log.e(TAG, "auth failure when get user info", e4);
                    am.invalidateAuthToken(account.type, authToken);
                    count++;
                } catch (AccessDeniedException e5) {
                    Log.e(TAG, "access denied when get user info", e5);
                }
            }
        } else {
            Log.w(TAG, "no Xiaomi account, skip to query user profile");
        }
        return xiaomiUserProfile;
    }

    public static String getAuthToken(AccountManager am, Account account, String sid) {
        String str = null;
        if (account == null) {
            Log.w(TAG, "no xiaomi account, failed to get auth token");
        } else {
            try {
                str = ((Bundle) am.getAuthToken(account, sid, null, true, null, null).getResult()).getString(MiAccountManager.KEY_AUTHTOKEN);
            } catch (OperationCanceledException e) {
                Log.e(TAG, "getAuthToken", e);
            } catch (AuthenticatorException e2) {
                Log.e(TAG, "getAuthToken", e2);
            } catch (IOException e3) {
                Log.e(TAG, "getAuthToken", e3);
            }
        }
        return str;
    }

    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService("connectivity");
        if (cm == null) {
            return false;
        }
        NetworkInfo ni = cm.getActiveNetworkInfo();
        if (ni == null || !ni.isConnectedOrConnecting()) {
            return false;
        }
        return true;
    }

    public static Bitmap getImageBitmap(Context context, String imageName) {
        File cachedImageFile = context.getFileStreamPath(imageName);
        if (cachedImageFile.isFile() && cachedImageFile.exists()) {
            return android.graphics.BitmapFactory.decodeFile(cachedImageFile.getAbsolutePath());
        }
        return null;
    }

    public static boolean deleteBindedDevice(Context context, String devId) {
        boolean z = false;
        Account account = ExtraAccountManager.getXiaomiAccount(context);
        if (account != null) {
            AccountManager am = AccountManager.get(context);
            int count = 0;
            while (count < 2) {
                String authToken = getAuthToken(am, account, com.xiaomi.account.Constants.DEVICE_INFO_SID);
                ExtendedAuthToken extendedAuthToken = ExtendedAuthToken.parse(authToken);
                if (extendedAuthToken == null) {
                    break;
                }
                String serviceToken = extendedAuthToken.authToken;
                String security = extendedAuthToken.security;
                if (serviceToken == null || security == null) {
                    break;
                }
                try {
                    z = CloudHelper.deleteBindedDevice(account.name, am.getUserData(account, Constants.KEY_ENCRYPTED_USER_ID), devId, serviceToken, security);
                    break;
                } catch (AuthenticationFailureException e) {
                    Log.e(TAG, "auth failure when delete device", e);
                    am.invalidateAuthToken(account.type, authToken);
                    count++;
                }
            }
        } else {
            Log.w(TAG, "no Xiaomi account");
        }
        return z;
    }

    public static boolean uploadInfoToServer(Context context, HashMap<String, Object> uploadInfo, int uploadType, String sid) throws InvalidResponseException, CipherException, IOException, AuthenticationFailureException, AccessDeniedException {
        Account account = ExtraAccountManager.getXiaomiAccount(context);
        if (account == null) {
            Log.w(TAG, "no Xiaomi account");
            return false;
        }
        AccountManager am = AccountManager.get(context);
        ExtendedAuthToken extToken = ExtendedAuthToken.parse(getAuthToken(am, account, sid));
        if (extToken == null) {
            Log.d(TAG, "uploadInfoToServer extToken is null");
            return false;
        }
        String serviceToken = extToken.authToken;
        String security = extToken.security;
        String encryptedUserId = am.getUserData(account, Constants.KEY_ENCRYPTED_USER_ID);
        switch (uploadType) {
            case LicenseActivity.PRIVACY_POLICY /*0*/:
                return CloudHelper.uploadDeviceInfo(account.name, encryptedUserId, serviceToken, security, uploadInfo);
            case SdkReturnCode.LOW_SDK_VERSION /*1*/:
                return CloudHelper.uploadXiaomiUserInfo(account.name, encryptedUserId, serviceToken, security, uploadInfo);
            default:
                return false;
        }
    }

    public static JSONObject uploadImageToServer(Context context, String userId, String uploadUrl, Bitmap bitmap) throws ClientProtocolException, IOException {
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(uploadUrl);
        Bitmap bitmap2 = bitmap;
        bitmap2.compress(CompressFormat.JPEG, 80, new ByteArrayOutputStream());
        httpPost.setEntity(new MultipartEntity(new Part[]{new FilePart("userfile", new ByteArrayPartSource("icon.jpg", outStream.toByteArray()))}));
        HttpResponse httpResponse = httpClient.execute(httpPost);
        int code = httpResponse.getStatusLine().getStatusCode();
        if (code == 200) {
            JSONObject uploadResult = null;
            try {
                uploadResult = new JSONObject(EntityUtils.toString(httpResponse.getEntity()));
            } catch (ParseException e) {
                Log.e(TAG, "uploadImageToServer error", e);
            } catch (JSONException e2) {
                Log.e(TAG, "uploadImageToServer error", e2);
            }
            if (uploadResult != null) {
                return uploadResult;
            }
            Log.d(TAG, "uploadImageToServer uploadResult is null");
            return uploadResult;
        }
        Log.d(TAG, "uploadImageToServer status code :" + code);
        return null;
    }

    public static Bitmap createPhoto(Activity activity, String avatarFileName) {
        Bitmap origin = null;
        if (avatarFileName != null) {
            origin = SnsUtils.getUserAvatarByAbsPath(activity, avatarFileName);
        }
        if (origin == null) {
            origin = android.graphics.BitmapFactory.decodeResource(activity.getResources(), R.drawable.default_avatar_in_settings);
        }
        if (origin == null) {
            return null;
        }
        Bitmap avatar = BitmapFactory.createPhoto(activity, origin);
        origin.recycle();
        return avatar;
    }
}
