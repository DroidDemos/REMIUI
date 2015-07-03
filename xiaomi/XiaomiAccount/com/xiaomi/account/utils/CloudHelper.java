package com.xiaomi.account.utils;

import android.accounts.Account;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.util.Pair;
import com.mipay.sdk.Mipay;
import com.xiaomi.account.XiaomiAccountAppDelegate;
import com.xiaomi.account.data.DeviceInfo;
import com.xiaomi.account.data.DeviceInfo.DevSettingName;
import com.xiaomi.account.data.SnsUserInfo;
import com.xiaomi.account.data.XiaomiUserProfile;
import com.xiaomi.account.data.XiaomiUserProfile.Gender;
import com.xiaomi.account.exception.SNSAccessTokenExpiredException;
import com.xiaomi.account.exception.SNSBindedInfoException;
import com.xiaomi.accountsdk.account.XMPassport;
import com.xiaomi.accountsdk.account.data.MiCloudAuthInfo;
import com.xiaomi.accountsdk.account.data.XiaomiUserInfo;
import com.xiaomi.accountsdk.account.exception.NeedOAuthorizeException;
import com.xiaomi.accountsdk.account.exception.RegisteredPhoneException;
import com.xiaomi.accountsdk.request.AccessDeniedException;
import com.xiaomi.accountsdk.request.AuthenticationFailureException;
import com.xiaomi.accountsdk.request.CipherException;
import com.xiaomi.accountsdk.request.InvalidResponseException;
import com.xiaomi.accountsdk.request.SecureRequest;
import com.xiaomi.accountsdk.request.SimpleRequest;
import com.xiaomi.accountsdk.request.SimpleRequest.MapContent;
import com.xiaomi.accountsdk.request.SimpleRequest.StreamContent;
import com.xiaomi.accountsdk.request.SimpleRequest.StringContent;
import com.xiaomi.accountsdk.utils.CloudCoder;
import com.xiaomi.accountsdk.utils.EasyMap;
import com.xiaomi.micloudsdk.exception.CloudServerException;
import com.xiaomi.micloudsdk.request.Request;
import com.xiaomi.passport.Constants;
import com.xiaomi.passport.ui.AccountUnactivatedFragment;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import miui.telephony.CloudTelephonyManager;
import miui.telephony.exception.IllegalDeviceException;
import org.apache.http.client.ClientProtocolException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class CloudHelper {
    public static final String ACCOUNT_URL_BASE;
    public static final String API_SAFE_URL_BASE;
    private static final boolean DEBUG = true;
    private static final String DEVICE_STATUS_DELETED = "deleted";
    private static final String DEVICE_STATUS_DELETING = "deleting";
    private static final int ERROR_CODE_QQ_ACCESS_TOKEN_AUTHENTICATE_FAILED = 100016;
    private static final int ERROR_CODE_QQ_ACCESS_TOKEN_EXPIRED = 100014;
    private static final int ERROR_CODE_QQ_ACCESS_TOKEN_REVOKED = 100015;
    private static final String ICON_SIZE_SUFFIX_320 = "_320";
    private static final Integer INT_0;
    public static final String MICLOUD_SID = "micloud";
    public static final String PARAM_GET_BINDED_INFO = "access_token";
    private static final String TAG = "CloudHelper";
    private static final String URL_ACCOUNT_USER_PROFILE;
    private static final String URL_COMMIT_UPDATE_ICON;
    public static final String URL_DELETE_DEVICE;
    public static final String URL_GET_DEVICE_INFO;
    public static final String URL_GET_DEVICE_LIST;
    public static final String URL_GET_MIBI_SIGN = "http://statusapi.micloud.xiaomi.net/mic/status/v2/user/%s/balancesign";
    public static final String URL_GET_MIBI_SIGN_BASE = "http://statusapi.micloud.xiaomi.net";
    public static final String URL_REMOVE_TRUST_DEVICE;
    private static final String URL_REQUEST_UPDATE_ICON;
    public static final String URL_SNS;
    public static final String URL_SNS_BIND_AUTH;
    public static final String URL_SNS_BIND_FINISH;
    public static final String URL_SNS_DELETE_ACCESSTOKEN;
    public static final String URL_SNS_GET_ACCESSTOKEN;
    public static final String URL_SNS_GET_UID_FB = "https://graph.facebook.com/me";
    public static final String URL_SNS_GET_UID_QQ = "https://graph.qq.com/oauth2.0/me";
    public static final String URL_SNS_GET_UID_WEIBO = "https://api.weibo.com/2/account/get_uid.json";
    public static final String URL_SNS_GET_USER_INFO_FB = "https://graph.facebook.com/%s?fields=id,name,picture";
    public static final String URL_SNS_GET_USER_INFO_QQ = "https://graph.qq.com/user/get_user_info";
    public static final String URL_SNS_GET_USER_INFO_WEIBO = "https://api.weibo.com/2/users/show.json";
    public static final String URL_SNS_SERVER_FB = "https://graph.facebook.com";
    public static final String URL_SNS_SERVER_WEIBO = "https://api.weibo.com/2";

    static {
        ACCOUNT_URL_BASE = XMPassport.URL_ACCOUNT_BASE;
        API_SAFE_URL_BASE = XMPassport.URL_ACCOUNT_SAFE_API_BASE;
        INT_0 = Integer.valueOf(0);
        URL_SNS = ACCOUNT_URL_BASE + "/sns";
        URL_SNS_BIND_AUTH = URL_SNS + "/bind/auth";
        URL_SNS_BIND_FINISH = URL_SNS + "/bind/finish";
        URL_SNS_GET_ACCESSTOKEN = API_SAFE_URL_BASE + "/user/accessToken";
        URL_SNS_DELETE_ACCESSTOKEN = API_SAFE_URL_BASE + "/user/accessToken/full/delete";
        URL_GET_DEVICE_INFO = XMPassport.URL_DEV_SETTING;
        URL_GET_DEVICE_LIST = XMPassport.URL_DEV_BASE + "/api/user/devices/setting";
        URL_DELETE_DEVICE = XMPassport.URL_DEV_BASE + "/api/user/device/delete";
        URL_REMOVE_TRUST_DEVICE = XMPassport.URL_DEV_BASE + "/api/user/device/mistrust";
        URL_ACCOUNT_USER_PROFILE = XMPassport.URL_ACCOUNT_SAFE_API_BASE + "/user/profile";
        URL_REQUEST_UPDATE_ICON = XMPassport.URL_ACCOUNT_SAFE_API_BASE + "/user/updateIconRequest";
        URL_COMMIT_UPDATE_ICON = XMPassport.URL_ACCOUNT_SAFE_API_BASE + "/user/updateIconCommit";
    }

    public static boolean turnOffFindDevice(Context context, Account account) {
        final CountDownLatch latch = new CountDownLatch(1);
        IntentFilter filter = new IntentFilter("com.xiaomi.action.ENABLE_FIND_DEVICE_COMPLETED");
        BroadcastReceiver receiver = new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                latch.countDown();
            }
        };
        context.registerReceiver(receiver, filter);
        try {
            Intent intent = new Intent("com.xiaomi.action.DISABLE_FIND_DEVICE");
            intent.putExtra(AccountUnactivatedFragment.EXTRA_ACCOUNT, account);
            intent.setPackage("com.miui.cloudservice");
            context.startService(intent);
            try {
                boolean result = latch.await(30, TimeUnit.SECONDS);
                return result;
            } catch (InterruptedException e) {
                context.unregisterReceiver(receiver);
                return false;
            }
        } finally {
            context.unregisterReceiver(receiver);
        }
    }

    public static XiaomiUserInfo getXiaomiUserInfo(String userId, String encryptedUserId, String serviceToken, String security) throws InvalidResponseException, CipherException, IOException, AuthenticationFailureException, AccessDeniedException {
        return XMPassport.getXiaomiUserInfo(userId, encryptedUserId, serviceToken, security);
    }

    public static XiaomiUserProfile getXiaomiUserProfile(String userId, String encryptedUserId, String serviceToken, String security) throws InvalidResponseException, CipherException, IOException, AuthenticationFailureException, AccessDeniedException {
        String transId = UUID.randomUUID().toString().substring(0, 15);
        EasyMap<String, String> params = new EasyMap().easyPut("userId", userId).easyPut("sid", Constants.PASSPORT_API_SID).easyPut("transId", transId);
        EasyMap<String, String> cookies = new EasyMap().easyPut("cUserId", encryptedUserId).easyPut("serviceToken", serviceToken);
        MapContent content = SecureRequest.getAsMap(URL_ACCOUNT_USER_PROFILE, params, cookies, DEBUG, security);
        if (content == null) {
            throw new IOException("failed to get xiaomi user profile");
        }
        Object code = content.getFromBody(Mipay.KEY_CODE);
        Log.w(TAG, "getXiaomiUserProfile code : " + code);
        if (INT_0.equals(code)) {
            XiaomiUserProfile xiaomiUserProfile = new XiaomiUserProfile(userId);
            Object data = content.getFromBody(Constants.KEY_DATA);
            if (data instanceof Map) {
                Object birthday = ((Map) data).get("birthday");
                if ((birthday instanceof String) && !TextUtils.isEmpty((String) birthday)) {
                    Calendar calender = Calendar.getInstance();
                    try {
                        calender.setTime(new SimpleDateFormat(com.xiaomi.account.Constants.SIMPLE_DATE_FORMAT).parse((String) birthday));
                        xiaomiUserProfile.setBirthday(calender);
                    } catch (ParseException e) {
                        Log.e(TAG, "getXiaomiUserProfile", e);
                    }
                }
                String gender = ((Map) data).get("gender");
                if ((gender instanceof String) && !TextUtils.isEmpty(gender)) {
                    String genderStr = gender;
                    if ("m".equals(genderStr)) {
                        xiaomiUserProfile.setGender(Gender.MALE);
                    } else {
                        if ("f".equals(genderStr)) {
                            xiaomiUserProfile.setGender(Gender.FEMALE);
                        }
                    }
                }
                return xiaomiUserProfile;
            }
        }
        Object description = content.getFromBody("description");
        Log.d(TAG, "getXiaomiUserProfile failed, code: " + code + "; description: " + description);
        throw new InvalidResponseException("failed to get user profile");
    }

    public static SnsUserInfo getBindedUserInfo(String snsType, String accessToken) throws IOException, AccessDeniedException, AuthenticationFailureException, SNSAccessTokenExpiredException, SNSBindedInfoException {
        if (snsType.equals(com.xiaomi.account.Constants.SINA_WEIBO_SNS_TYPE)) {
            return getBindedWeiboUserInfo(accessToken);
        }
        if (snsType.equals(com.xiaomi.account.Constants.QQ_SNS_TYPE)) {
            return getBindedQQUserInfo(accessToken);
        }
        if (snsType.equals(com.xiaomi.account.Constants.FACEBOOK_SNS_TYPE)) {
            return getBindedFBUserInfo(accessToken);
        }
        return null;
    }

    private static SnsUserInfo getBindedWeiboUserInfo(String accessToken) throws IOException, AccessDeniedException, AuthenticationFailureException, SNSAccessTokenExpiredException, SNSBindedInfoException {
        EasyMap<String, String> params = new EasyMap();
        if (accessToken == null) {
            throw new SNSBindedInfoException("getBindedWeiboUserInfo: accessToken is null");
        }
        params.easyPut(PARAM_GET_BINDED_INFO, accessToken);
        try {
            MapContent uidContent = SimpleRequest.getAsMap(URL_SNS_GET_UID_WEIBO, params, null, DEBUG);
            if (uidContent == null) {
                throw new IOException("failed to get response to get uid");
            }
            Object uid = uidContent.getFromBody("uid");
            if (uid != null) {
                params.easyPut("uid", uid.toString());
                MapContent userInfoContent = SimpleRequest.getAsMap(URL_SNS_GET_USER_INFO_WEIBO, params, null, DEBUG);
                if (userInfoContent == null) {
                    throw new IOException("failed to get response to get user Information");
                }
                Object name = userInfoContent.getFromBody("name");
                Object avatar_large_url = userInfoContent.getFromBody("avatar_large");
                if (!(name == null || avatar_large_url == null)) {
                    return new SnsUserInfo(uid.toString(), name.toString(), avatar_large_url.toString());
                }
            }
            throw new SNSBindedInfoException("getBindedWeiboUserInfo ");
        } catch (AccessDeniedException e) {
            Log.e(TAG, "getBindedWeiboUserInfo", e);
            throw new SNSAccessTokenExpiredException("sina weibo accessToken is expired");
        } catch (AuthenticationFailureException e2) {
            Log.e(TAG, "getBindedWeiboUserInfo", e2);
            throw new SNSAccessTokenExpiredException("sina weibo accessToken is expired");
        }
    }

    private static SnsUserInfo getBindedQQUserInfo(String accessToken) throws IOException, AccessDeniedException, AuthenticationFailureException, SNSAccessTokenExpiredException, SNSBindedInfoException {
        JSONException e;
        EasyMap<String, String> params = new EasyMap();
        if (accessToken == null) {
            throw new SNSBindedInfoException("getBindedQQUserInfo: accessToken is null");
        }
        params.easyPut(PARAM_GET_BINDED_INFO, accessToken);
        StringContent stringContent = SimpleRequest.getAsString(URL_SNS_GET_UID_QQ, params, null, DEBUG);
        if (stringContent == null) {
            throw new IOException("failed to get response to get uid");
        }
        String realBody;
        String bodyString = stringContent.getBody();
        String prefix = "callback(";
        if (bodyString.startsWith(prefix)) {
            realBody = bodyString.substring(prefix.length());
        } else {
            realBody = bodyString;
        }
        try {
            JSONObject jsonObject = new JSONObject(realBody);
            JSONObject jSONObject;
            if (jsonObject == null) {
                try {
                    throw new IOException("failed to convert String to JSONObject");
                } catch (JSONException e2) {
                    e = e2;
                    jSONObject = jsonObject;
                    Log.e(TAG, "getBindedQQUserInfo", e);
                    throw new SNSBindedInfoException("getBindedQQUserInfo");
                }
            }
            String openId = jsonObject.optString("openid");
            String clientId = jsonObject.optString("client_id");
            if (TextUtils.isEmpty(openId) || TextUtils.isEmpty(clientId)) {
                int errorCode = jsonObject.optInt("error");
                String errorDescription = jsonObject.optString("error_description");
                Log.i(TAG, "code : " + errorCode + "errorDescription : " + errorDescription);
                if (errorCode == ERROR_CODE_QQ_ACCESS_TOKEN_EXPIRED || errorCode == ERROR_CODE_QQ_ACCESS_TOKEN_REVOKED || errorCode == ERROR_CODE_QQ_ACCESS_TOKEN_AUTHENTICATE_FAILED) {
                    throw new SNSAccessTokenExpiredException(errorDescription);
                }
                throw new SNSBindedInfoException(errorDescription);
            }
            params.easyPut("oauth_consumer_key", clientId);
            params.easyPut("openid", openId);
            MapContent userInfoContent = SimpleRequest.getAsMap(URL_SNS_GET_USER_INFO_QQ, params, null, DEBUG);
            if (userInfoContent == null) {
                throw new IOException("failed to get response to get user Information");
            }
            Object name = userInfoContent.getFromBody("nickname");
            Object avatar_url = userInfoContent.getFromBody("figureurl_qq_1");
            if (name == null || avatar_url == null) {
                jSONObject = jsonObject;
                throw new SNSBindedInfoException("getBindedQQUserInfo");
            }
            return new SnsUserInfo(openId, name.toString(), avatar_url.toString());
        } catch (JSONException e3) {
            e = e3;
            Log.e(TAG, "getBindedQQUserInfo", e);
            throw new SNSBindedInfoException("getBindedQQUserInfo");
        }
    }

    private static SnsUserInfo getBindedFBUserInfo(String accessToken) throws IOException, AccessDeniedException, AuthenticationFailureException, SNSAccessTokenExpiredException, SNSBindedInfoException {
        EasyMap<String, String> params = new EasyMap();
        if (accessToken == null) {
            throw new SNSBindedInfoException("getBindedFBUserInfo: accessToken is null");
        }
        params.easyPut(PARAM_GET_BINDED_INFO, accessToken);
        MapContent uidContent = SimpleRequest.getAsMap(URL_SNS_GET_UID_FB, params, null, DEBUG);
        if (uidContent == null) {
            throw new IOException("failed to get response to get uid");
        }
        Object uid = uidContent.getFromBody("id");
        if (uid != null) {
            MapContent userInfoContent = SimpleRequest.getAsMap(String.format(URL_SNS_GET_USER_INFO_FB, new Object[]{uid.toString()}), null, null, DEBUG);
            if (userInfoContent == null) {
                throw new IOException("failed to get response to get user information");
            }
            Object name = userInfoContent.getFromBody("name");
            Object avatar_url = userInfoContent.getFromBody("picture");
            if (!(name == null || avatar_url == null)) {
                return new SnsUserInfo(uid.toString(), name.toString(), avatar_url.toString());
            }
        }
        Object error = uidContent.getFromBody("error");
        if (error != null && (error instanceof Map)) {
            Object errorCode = ((Map) error).get("error_subcode");
            if (errorCode != null && (errorCode instanceof Integer)) {
                if (((Integer) errorCode).intValue() == 467) {
                    throw new SNSAccessTokenExpiredException("FB accessToken expired");
                }
                throw new SNSBindedInfoException("getBindedFBUserInfo Code: " + errorCode.toString());
            }
        }
        throw new SNSBindedInfoException("getBindedFBUserInfo");
    }

    public static String getBindedAccessToken(String userId, String encryptedUserId, String snsType, String serviceToken, String security) throws IOException, AccessDeniedException, AuthenticationFailureException, InvalidResponseException, CipherException {
        if (TextUtils.isDigitsOnly(userId)) {
            MapContent mapContent = SecureRequest.getAsMap(URL_SNS_GET_ACCESSTOKEN, new EasyMap().easyPut("snsType", snsType).easyPut("userId", userId), new EasyMap().easyPut("cUserId", encryptedUserId).easyPut("serviceToken", serviceToken), DEBUG, security);
            if (mapContent == null) {
                throw new IOException("failed to get response to get sns accesstoken");
            }
            if (!INT_0.equals(mapContent.getFromBody(Mipay.KEY_CODE))) {
                return null;
            }
            Map data = mapContent.getFromBody(Constants.KEY_DATA);
            if (data instanceof Map) {
                return data.get("key").toString();
            }
            return null;
        }
        throw new IllegalArgumentException("userId is not only digits");
    }

    public static boolean deleteBindedAccessToken(String userId, String encryptedUserId, String snsType, String serviceToken, String security) throws IOException, AccessDeniedException, AuthenticationFailureException, InvalidResponseException, CipherException {
        if (TextUtils.isDigitsOnly(userId)) {
            MapContent mapContent = SecureRequest.postAsMap(URL_SNS_DELETE_ACCESSTOKEN, new EasyMap().easyPut("snsType", snsType).easyPut("userId", userId), new EasyMap().easyPut("cUserId", encryptedUserId).easyPut("serviceToken", serviceToken), DEBUG, security);
            if (mapContent == null) {
                throw new IOException("failed to get response to delete sns accesstoken");
            }
            if (INT_0.equals(mapContent.getFromBody(Mipay.KEY_CODE))) {
                return DEBUG;
            }
            return false;
        }
        throw new IllegalArgumentException("userId is not only digits");
    }

    public static String safeGetHashedDeviceId() throws IllegalDeviceException {
        return CloudCoder.hashDeviceInfo(CloudTelephonyManager.blockingGetDeviceId(XiaomiAccountAppDelegate.getApp()));
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

    public static void getRegisterVerifyCode(String phone) throws IOException, AccessDeniedException, AuthenticationFailureException, InvalidResponseException, RegisteredPhoneException {
        XMPassport.getRegisterVerifyCode(phone);
    }

    public static void checkRegisterVerifyCode(String phone, String verifyCode) throws IOException, AccessDeniedException, AuthenticationFailureException, InvalidResponseException {
        XMPassport.checkRegisterVerifyCode(phone, verifyCode);
    }

    public static MiCloudAuthInfo getOAuthInfo(Context context, String userId, String clientId, String redirectUri, Bundle options, String serviceToken, String security) throws NeedOAuthorizeException, IOException, AuthenticationFailureException, AccessDeniedException {
        try {
            return XMPassport.getOAuthInfo(context, userId, clientId, redirectUri, safeGetHashedDeviceId(), options, serviceToken, security);
        } catch (IllegalDeviceException e) {
            Log.e(TAG, "get device id error when get oauth info: ", e);
            return null;
        }
    }

    public static String getMipaySign(Context context, String userId, String serviceToken, String security) throws UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException, ClientProtocolException, IOException, CloudServerException {
        String result = Request.secureGet(String.format(URL_GET_MIBI_SIGN, new Object[]{userId}), new EasyMap().easyPut("sid", MICLOUD_SID).easyPut("json", SysHelper.getVerifyString(context)));
        if (TextUtils.isEmpty(result)) {
            Log.e(TAG, "getMipaySign response empty, return null");
            return null;
        }
        try {
            JSONObject resultJSON = new JSONObject(result);
            if (INT_0.equals(Integer.valueOf(resultJSON.getInt(Mipay.KEY_CODE)))) {
                return resultJSON.getJSONObject(Constants.KEY_DATA).getString("sign");
            }
            Log.e(TAG, "log reason" + String.format("failed to get Sign info, code:%s, reason:%s, description:%s", new Object[]{Integer.valueOf(resultJSON.getInt(Mipay.KEY_CODE)), resultJSON.getString("reason"), resultJSON.getString("description")}));
            return null;
        } catch (JSONException e) {
            Log.e(TAG, "no invalid sign contained in response");
            return null;
        }
    }

    public static boolean uploadDeviceInfo(String userId, String encryptedUserId, String serviceToken, String security, Map<String, Object> deviceInfo) throws IOException, AccessDeniedException, InvalidResponseException, CipherException, AuthenticationFailureException {
        try {
            return XMPassport.uploadDeviceInfo(userId, encryptedUserId, serviceToken, security, safeGetHashedDeviceId(), deviceInfo);
        } catch (IllegalDeviceException e) {
            Log.e(TAG, "uploadDeviceInfo", e);
            return false;
        }
    }

    public static DeviceInfo getDeviceInfo(String userId, String encryptedUserId, String serviceToken, String Security) throws IOException, AccessDeniedException, CipherException, InvalidResponseException, AuthenticationFailureException {
        try {
            String deviceId = safeGetHashedDeviceId();
            JSONArray jsonArray = new JSONArray();
            jsonArray.put(DevSettingName.MODEL);
            jsonArray.put(DevSettingName.PHONE_INFO);
            jsonArray.put(DevSettingName.DEVICENAME);
            jsonArray.put(DevSettingName.TRUST_DEVICE);
            MapContent deviceInfoContent = SecureRequest.getAsMap(URL_GET_DEVICE_INFO, new EasyMap().easyPut("userId", userId).easyPut(DevSettingName.DEVICEID, deviceId).easyPut("meta", jsonArray.toString()), new EasyMap().easyPut("cUserId", encryptedUserId).easyPut("serviceToken", serviceToken), DEBUG, Security);
            if (deviceInfoContent == null) {
                throw new IOException("failed to get device info");
            }
            Object code = deviceInfoContent.getFromBody(Mipay.KEY_CODE);
            Log.w(TAG, "getDeviceInfo code : " + code);
            if (INT_0.equals(code)) {
                Object data = deviceInfoContent.getFromBody(Constants.KEY_DATA);
                if (data instanceof Map) {
                    Object settings = ((Map) data).get("settings");
                    if (settings instanceof ArrayList) {
                        HashMap<String, Object> result = new HashMap();
                        Iterator<HashMap<String, Object>> iter = ((ArrayList) settings).iterator();
                        while (iter.hasNext()) {
                            HashMap<String, Object> item = (HashMap) iter.next();
                            result.put((String) item.get("name"), item.get("value"));
                        }
                        return convertDeviceInfoValues(result);
                    }
                }
            }
            return null;
        } catch (IllegalDeviceException e) {
            Log.e(TAG, "failed to get device id when get device info", e);
        }
    }

    public static ArrayList<DeviceInfo> getDeviceList(String userId, String encryptedUserId, String serviceToken, String security) throws IOException, AccessDeniedException, AuthenticationFailureException, CipherException, InvalidResponseException {
        JSONArray jsonArray = new JSONArray();
        jsonArray.put(DevSettingName.MODEL);
        jsonArray.put(DevSettingName.PHONE_INFO);
        jsonArray.put(DevSettingName.DEVICENAME);
        jsonArray.put(DevSettingName.DEVICEID);
        jsonArray.put(DevSettingName.STATUS);
        jsonArray.put(DevSettingName.TRUST_DEVICE);
        EasyMap<String, String> params = new EasyMap().easyPut("userId", userId).easyPut("meta", jsonArray.toString());
        EasyMap<String, String> cookies = new EasyMap().easyPut("cUserId", encryptedUserId).easyPut("serviceToken", serviceToken);
        MapContent mapContent = SecureRequest.getAsMap(URL_GET_DEVICE_LIST, params, cookies, DEBUG, security);
        if (mapContent == null) {
            throw new IOException("failed to get devices list");
        }
        Object code = mapContent.getFromBody(Mipay.KEY_CODE);
        Log.w(TAG, "getDeviceList code : " + code);
        if (INT_0.equals(code)) {
            Object data = mapContent.getFromBody(Constants.KEY_DATA);
            if (data instanceof Map) {
                Object allDevicesSettings = ((Map) data).get("all_device_settings");
                if (allDevicesSettings instanceof ArrayList) {
                    try {
                        String deviceId = safeGetHashedDeviceId();
                        ArrayList<HashMap<String, Object>> deviceListSettings = (ArrayList) allDevicesSettings;
                        ArrayList<DeviceInfo> arrayList = new ArrayList(deviceListSettings.size());
                        Iterator<HashMap<String, Object>> iter = deviceListSettings.iterator();
                        while (iter.hasNext()) {
                            HashMap<String, Object> info = (HashMap) iter.next();
                            Object status = info.get(DevSettingName.STATUS);
                            if (status != null) {
                                if (!(DEVICE_STATUS_DELETED.equals(status.toString()) || DEVICE_STATUS_DELETING.equals(status.toString()))) {
                                    if (deviceId.equals(info.get(DevSettingName.DEVICEID).toString())) {
                                    }
                                }
                            }
                            if (info.get(DevSettingName.MODEL) != null) {
                                arrayList.add(convertDeviceInfoValues(info));
                            }
                        }
                        return arrayList;
                    } catch (IllegalDeviceException e) {
                        Log.e(TAG, "failed to get device id when get device info", e);
                    }
                }
            }
        }
        return null;
    }

    private static DeviceInfo convertDeviceInfoValues(HashMap<String, Object> info) {
        DeviceInfo deviceInfo = new DeviceInfo();
        Object deviceName = info.get(DevSettingName.DEVICENAME);
        if (deviceName != null) {
            deviceInfo.setDeviceName(deviceName.toString());
        }
        Object model = info.get(DevSettingName.MODEL);
        if (model != null) {
            deviceInfo.setModel(model.toString());
        }
        Object phoneInfo = info.get(DevSettingName.PHONE_INFO);
        if (phoneInfo != null) {
            deviceInfo.setPhoneInfo(phoneInfo.toString());
        }
        Object devId = info.get(DevSettingName.DEVICEID);
        if (devId != null) {
            deviceInfo.setDevId(devId.toString());
        }
        Object trustedDevice = info.get(DevSettingName.TRUST_DEVICE);
        if (trustedDevice != null && "1".equals(trustedDevice.toString())) {
            deviceInfo.setIsTrustedDevice(DEBUG);
        }
        return deviceInfo;
    }

    public static boolean deleteBindedDevice(String userId, String encryptedUserId, String devId, String serviceToken, String security) throws AuthenticationFailureException {
        try {
            MapContent content = SecureRequest.postAsMap(URL_DELETE_DEVICE, new EasyMap().easyPut("userId", userId).easyPut(DevSettingName.DEVICEID, devId).easyPut("sid", com.xiaomi.account.Constants.DEVICE_INFO_SID).easyPut("traceId", UUID.randomUUID().toString().substring(0, 15)), new EasyMap().easyPut("cUserId", encryptedUserId).easyPut("serviceToken", serviceToken), DEBUG, security);
            if (content == null) {
                throw new IOException("failed to delete device");
            }
            Object code = content.getFromBody(Mipay.KEY_CODE);
            Log.w(TAG, "deleteBindedDevice code : " + code);
            if (INT_0.equals(code)) {
                return DEBUG;
            }
            Log.d(TAG, "failed deleteBindedDevice, code: " + code + "; description: " + content.getFromBody("description"));
            return false;
        } catch (InvalidResponseException e) {
            Log.e(TAG, "invalid response when delete device", e);
        } catch (CipherException e2) {
            Log.e(TAG, "CipherException when delete device", e2);
        } catch (IOException e3) {
            Log.e(TAG, "IOException when delete device", e3);
        } catch (AccessDeniedException e4) {
            Log.e(TAG, "access denied when delete device", e4);
        }
    }

    public static boolean removeTrustDevice(String userId, String encryptedUserId, String devId, String serviceToken, String security) throws AuthenticationFailureException {
        try {
            MapContent content = SecureRequest.postAsMap(URL_REMOVE_TRUST_DEVICE, new EasyMap().easyPut("userId", userId).easyPut(DevSettingName.DEVICEID, devId).easyPut("sid", com.xiaomi.account.Constants.DEVICE_INFO_SID).easyPut("traceId", UUID.randomUUID().toString().substring(0, 15)), new EasyMap().easyPut("cUserId", encryptedUserId).easyPut("serviceToken", serviceToken), DEBUG, security);
            if (content == null) {
                throw new IOException("failed to delete device");
            }
            Object code = content.getFromBody(Mipay.KEY_CODE);
            Log.w(TAG, "removeTrustDevice code : " + code);
            if (INT_0.equals(code)) {
                return DEBUG;
            }
            Log.d(TAG, "failed removeTrustDevice, code: " + code + "; description: " + content.getFromBody("description"));
            return false;
        } catch (InvalidResponseException e) {
            Log.e(TAG, "invalid response when remove trust device", e);
        } catch (CipherException e2) {
            Log.e(TAG, "CipherException when remove trust device", e2);
        } catch (IOException e3) {
            Log.e(TAG, "IOException when remove trust device", e3);
        } catch (AccessDeniedException e4) {
            Log.e(TAG, "access denied when remove trust device", e4);
        }
    }

    public static boolean uploadXiaomiUserInfo(String userId, String encryptedUserId, String serviceToken, String security, HashMap<String, Object> info) throws InvalidResponseException, CipherException, IOException, AuthenticationFailureException, AccessDeniedException {
        EasyMap<String, String> params = new EasyMap().easyPut("userId", userId).easyPut("sid", Constants.PASSPORT_API_SID).easyPut("transId", UUID.randomUUID().toString().substring(0, 15));
        for (Entry<String, Object> entry : info.entrySet()) {
            if (entry.getValue() != null) {
                params.easyPut(entry.getKey(), entry.getValue().toString());
            }
        }
        MapContent content = SecureRequest.postAsMap(URL_ACCOUNT_USER_PROFILE, params, new EasyMap().easyPut("cUserId", encryptedUserId).easyPut("serviceToken", serviceToken), DEBUG, security);
        if (content == null) {
            throw new IOException("failed to get xiaomi user profile");
        }
        Object code = content.getFromBody(Mipay.KEY_CODE);
        Log.w(TAG, "setXiaomiUserProfile code : " + code);
        if (INT_0.equals(code)) {
            return DEBUG;
        }
        Log.d(TAG, "failed upload xiaomi user info, code: " + code + "; description: " + content.getFromBody("description"));
        return false;
    }

    public static String requestUploadUserIcon(String userId, String encryptedUserId, String serviceToken, String security) throws InvalidResponseException, CipherException, IOException, AuthenticationFailureException, AccessDeniedException {
        MapContent content = SecureRequest.getAsMap(URL_REQUEST_UPDATE_ICON, new EasyMap().easyPut("userId", userId).easyPut("method", "json"), new EasyMap().easyPut("cUserId", encryptedUserId).easyPut("serviceToken", serviceToken), DEBUG, security);
        if (content == null) {
            throw new IOException("failed to requestUploadUserIcon");
        }
        Object code = content.getFromBody(Mipay.KEY_CODE);
        if (INT_0.equals(code)) {
            Object data = content.getFromBody(Constants.KEY_DATA);
            if (data instanceof Map) {
                Object uploadUrl = ((Map) data).get("uploadUrl");
                if (uploadUrl != null) {
                    return uploadUrl.toString();
                }
                throw new InvalidResponseException("uploadUrl is null");
            }
        }
        Log.d(TAG, "requestUploadUserIcon failed, code: " + code + "; description: " + content.getFromBody("description"));
        return null;
    }

    public static String commitUploadUserIcon(String userId, String encryptedUserId, String serviceToken, String security, JSONObject uploadResult) throws InvalidResponseException, CipherException, IOException, AuthenticationFailureException, AccessDeniedException {
        if (uploadResult == null) {
            Log.d(TAG, "commitUploadUserIcon uploadResult is null");
        }
        MapContent content = SecureRequest.postAsMap(URL_COMMIT_UPDATE_ICON, new EasyMap().easyPut("userId", userId).easyPut("sid", Constants.PASSPORT_API_SID).easyPut("transId", UUID.randomUUID().toString().substring(0, 15)).easyPut("json", Base64.encodeToString(uploadResult.toString().getBytes(), 2)), new EasyMap().easyPut("cUserId", encryptedUserId).easyPut("serviceToken", serviceToken), DEBUG, security);
        if (content == null) {
            throw new IOException("failed to commitUploadUserIcon");
        }
        Object code = content.getFromBody(Mipay.KEY_CODE);
        if (INT_0.equals(code)) {
            Object data = content.getFromBody(Constants.KEY_DATA);
            if (data instanceof Map) {
                Object downloadUrl = ((Map) data).get("downloadUrl");
                if (downloadUrl != null) {
                    return downloadUrl.toString();
                }
                throw new InvalidResponseException("downloadUrl is null");
            }
        }
        Log.d(TAG, "commitUploadUserIcon failed, code: " + code + "; description: " + content.getFromBody("description"));
        return null;
    }
}
