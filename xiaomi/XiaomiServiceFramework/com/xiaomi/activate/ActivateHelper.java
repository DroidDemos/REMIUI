package com.xiaomi.activate;

import android.app.Notification.Builder;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Looper;
import android.telephony.CellLocation;
import android.telephony.PhoneStateListener;
import android.telephony.ServiceState;
import android.telephony.cdma.CdmaCellLocation;
import android.telephony.gsm.GsmCellLocation;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import com.google.android.collect.Lists;
import com.google.android.collect.Maps;
import com.xiaomi.accountsdk.account.XMPassport;
import com.xiaomi.accountsdk.account.data.AccountInfo;
import com.xiaomi.accountsdk.account.exception.InvalidCredentialException;
import com.xiaomi.accountsdk.activate.ActivateManager;
import com.xiaomi.accountsdk.activate.CloudServiceFailureException;
import com.xiaomi.accountsdk.request.AccessDeniedException;
import com.xiaomi.accountsdk.request.AuthenticationFailureException;
import com.xiaomi.accountsdk.request.CipherException;
import com.xiaomi.accountsdk.request.InvalidResponseException;
import com.xiaomi.accountsdk.request.SecureRequest;
import com.xiaomi.accountsdk.request.SimpleRequest;
import com.xiaomi.accountsdk.request.SimpleRequest.StringContent;
import com.xiaomi.accountsdk.utils.CloudCoder;
import com.xiaomi.accountsdk.utils.EasyMap;
import com.xiaomi.accountsdk.utils.IOUtils;
import com.xiaomi.activate.ActivateUploadDiagnosisService.DiagnosisInfo;
import com.xiaomi.channel.commonutils.misc.DateTimeHelper;
import com.xiaomi.network.UploadHostStatHelper;
import com.xiaomi.xmsf.R;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Pattern;
import libcore.io.Base64;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ActivateHelper {
    private static final String ARG_CELL_TYPE = "cellType";
    private static final String ARG_CID = "cid";
    private static final String ARG_DEV_ID = "devId";
    private static final String ARG_EXTERNAL_ID = "externalId";
    private static final String ARG_GATEWAY_PLAIN = "_ucc";
    private static final String ARG_LAC = "lac";
    private static final String ARG_NETWORK_OPERATOR = "network_operator";
    private static final String ARG_NONCE = "nonce";
    private static final String ARG_PASSWORD = "password";
    private static final String ARG_PHONE = "phone";
    private static final String ARG_PHONE_OR_CODE = "phone_or_vcode";
    private static final String ARG_PSEUDO_DEVICE_ID_PLAIN = "_t2";
    private static final String ARG_SERVICE_ID = "sid";
    private static final String ARG_SIM_ID = "simId";
    private static final String ARG_SIM_OPERATOR = "sim_operator";
    private static final String ARG_SIM_OPERATOR_PLAIN = "_mnc";
    private static final String ARG_TRACE_ID = "traceId";
    private static final String ARG_USER_TYPE = "userType";
    private static final String ARG_USER_TYPE_ALT = "type";
    private static final String ARG_VKEY2 = "vkey2";
    private static final String ARG_VKEY2_PLAIN = "_vkey2";
    private static final String GW_KEY_DEFAULT = "default";
    private static final String J_CODE = "code";
    private static final String J_DATA = "data";
    private static final String J_MESSAGE_ID = "msgId";
    private static final String J_PASS_TOKEN = "passToken";
    private static final String J_PHONE = "phone";
    private static final String J_RESULT_LIST = "sendVkeyToPhoneResultList";
    private static final String J_STATUS = "status";
    private static final String J_USER_ID = "userId";
    private static final String J_VKEY2 = "vkey2";
    private static final boolean LOGV = true;
    public static final int MASK_PHONE_NUMBER_MODE_HEAD = 0;
    public static final int MASK_PHONE_NUMBER_MODE_MIDDLE = 2;
    public static final int MASK_PHONE_NUMBER_MODE_TAIL = 1;
    public static final String MICLOUD_SID = "micloud";
    private static final Random RANDOM;
    public static final int[] SERVER_RETRY_INTERVALS;
    public static final String SMS_GW_V2 = "sms_gw_config_v2";
    private static final String STATUS_INSERT_PHONE_ACTIVATED_ALREADY_EXIST = "INSERT_PHONE_ACTIVATED_ALREADY_EXIST";
    private static final String STATUS_OK = "OK";
    private static final String STATUS_PHONE_ERROR = "PHONE_ERROR";
    private static final String STATUS_SEND_DOWNLINK_MESSAGE_ERROR = "SEND_DOWNLINK_MESSAGE_ERROR";
    private static final String STATUS_SIM_ID_NOT_FOUND = "SIM_ID_NOT_FOUND";
    private static final String STATUS_VCODE_ERROR = "VCODE_ERROR";
    private static final String STATUS_VKEY_EXPIRED = "EXPIRATION";
    private static final String STATUS_VKEY_NOT_FOUND = "VKEY_NOT_FOUND";
    private static final String TAG = "ActivateHelper";
    public static final int UPLINK_VERIFY_DELAY = 3000;
    private static final String URL_CHECK_ACCOUNT_ON_ADDRESS;
    private static final String URL_QUERY_SMS_GW;
    private static AtomicBoolean sIsQueryingGateway;
    private static Object sQueryGatewayLock;

    public static class ActivationSmsReceiver extends BroadcastReceiver {
        private final LinkedList<Pair<String, String>> mResults;

        public ActivationSmsReceiver() {
            this.mResults = new LinkedList();
        }

        public void onReceive(Context context, Intent intent) {
            Log.v(ActivateHelper.TAG, "Received an activation sms");
            String vkey1 = intent.getStringExtra("extra_vkey1");
            String msgId = intent.getStringExtra("extra_msg_id");
            synchronized (this.mResults) {
                this.mResults.push(Pair.create(msgId, vkey1));
                this.mResults.notifyAll();
            }
        }

        public void register(Context context) {
            context.registerReceiver(this, new IntentFilter("com.xiaomi.action.ACTIVATION_SMS_RECEIVED"));
            Log.v(ActivateHelper.TAG, "Registered as receiver of com.xiaomi.action.ACTIVATION_SMS_RECEIVED");
        }

        public Pair<String, String> getResult(int timeLimit) throws InterruptedException {
            Pair<String, String> pair;
            synchronized (this.mResults) {
                if (this.mResults.isEmpty()) {
                    Log.v(ActivateHelper.TAG, "waiting for SMS_RECEIVED broadcast...");
                    this.mResults.wait((long) timeLimit);
                    if (this.mResults.isEmpty()) {
                        Log.v(ActivateHelper.TAG, "Timeout(" + timeLimit + "ms) when waiting for SMS_RECEIVED broadcast.");
                        pair = null;
                    } else {
                        pair = (Pair) this.mResults.pop();
                    }
                } else {
                    pair = (Pair) this.mResults.pop();
                }
            }
            return pair;
        }
    }

    private static class ConnectivityResumedReceiver extends BroadcastReceiver {
        private final Context mContext;
        private final CountDownLatch mLatch;

        private ConnectivityResumedReceiver(Context context) {
            this.mContext = context;
            this.mLatch = new CountDownLatch(ActivateHelper.MASK_PHONE_NUMBER_MODE_TAIL);
        }

        public void onReceive(Context context, Intent intent) {
            if (!intent.getBooleanExtra("noConnectivity", false)) {
                Log.d(ActivateHelper.TAG, "Network is ok");
                this.mLatch.countDown();
            }
        }

        public void await() throws InterruptedException {
            if (ActivateHelper.isNetworkConnected(this.mContext)) {
                this.mLatch.countDown();
            } else {
                Log.d(ActivateHelper.TAG, "Waiting network...");
            }
            this.mLatch.await();
        }
    }

    public static class DownlinkSmsFailedException extends Exception {
    }

    public static class NoPhoneInfoException extends Exception {
    }

    public static class PhoneErrorException extends Exception {
    }

    public static class PhoneServiceStateListener extends PhoneStateListener {
        private volatile int mPhoneState;

        public synchronized void onServiceStateChanged(ServiceState serviceState) {
            this.mPhoneState = serviceState.getState();
            if (this.mPhoneState == 0) {
                notify();
            }
        }

        public synchronized void waitForService() throws InterruptedException {
            if (this.mPhoneState != 0) {
                Log.v(ActivateHelper.TAG, "Waiting for phone service...");
                wait();
                Log.v(ActivateHelper.TAG, "Phone in service");
            }
        }
    }

    public static class VKeyExpiredException extends Exception {
    }

    private static String getUrlGetPhoneBySimidDevid(String activateHost) {
        Object[] objArr = new Object[MASK_PHONE_NUMBER_MODE_TAIL];
        objArr[MASK_PHONE_NUMBER_MODE_HEAD] = activateHost;
        return String.format("http://%s/pass/activation/getPhoneBySimIdDevId", objArr);
    }

    private static String getUrlSendVkeyToPhone(String activateHost) {
        Object[] objArr = new Object[MASK_PHONE_NUMBER_MODE_TAIL];
        objArr[MASK_PHONE_NUMBER_MODE_HEAD] = activateHost;
        return String.format("http://%s/pass/activation/sendVkeyToPhone", objArr);
    }

    private static String getUrlVerifyPhone(String activateHost) {
        Object[] objArr = new Object[MASK_PHONE_NUMBER_MODE_TAIL];
        objArr[MASK_PHONE_NUMBER_MODE_HEAD] = activateHost;
        return String.format("http://%s/pass/activation/safe/verify", objArr);
    }

    private static String getUrlCreateOrUpdateUser(String activateHost) {
        Object[] objArr = new Object[MASK_PHONE_NUMBER_MODE_TAIL];
        objArr[MASK_PHONE_NUMBER_MODE_HEAD] = activateHost;
        return String.format("http://%s/pass/activation/safe/createOrUpdateUser", objArr);
    }

    private static String getUrlInsertActivateInfoToServer(String activateHost) {
        Object[] objArr = new Object[MASK_PHONE_NUMBER_MODE_TAIL];
        objArr[MASK_PHONE_NUMBER_MODE_HEAD] = activateHost;
        return String.format("http://%s/pass/passportapi/safe/insertPhoneActivated", objArr);
    }

    static {
        URL_CHECK_ACCOUNT_ON_ADDRESS = XMPassport.URL_ACOUNT_API_BASE_SECURE + "/v3/user@id";
        URL_QUERY_SMS_GW = XMPassport.URL_ACOUNT_API_BASE + "/configuration";
        RANDOM = new Random(System.nanoTime());
        SERVER_RETRY_INTERVALS = new int[]{2000, 5000, UploadHostStatHelper.UPLOAD_RATIO_MULTIPLE, 20000, 20000, 20000, 20000};
        sQueryGatewayLock = new Object();
        sIsQueryingGateway = new AtomicBoolean(false);
    }

    private static JSONObject request(boolean isPost, String url, String security, EasyMap<String, String> params, EasyMap<String, String> cookies) throws CloudServiceFailureException, IOException, InterruptedException, AuthenticationFailureException {
        waitUntilNetworkConnected(ActivateExternal.getApp());
        String body = null;
        String requestMethod = isPost ? "POST" : "GET";
        if (security != null) {
            try {
                Log.v(TAG, "Sending a secure " + requestMethod + " request to " + url);
                params.put(ARG_NONCE, generateRandomHex(16));
                StringContent resp = isPost ? SecureRequest.postAsString(url, params, cookies, LOGV, security) : SecureRequest.getAsString(url, params, cookies, LOGV, security);
            } catch (CipherException e) {
                Log.e(TAG, requestMethod + " request error", e);
                throw new CloudServiceFailureException(e);
            } catch (AccessDeniedException e2) {
                Log.e(TAG, requestMethod + " request error", e2);
                throw new CloudServiceFailureException(e2);
            } catch (InvalidResponseException e3) {
                Log.e(TAG, requestMethod + " request error", e3);
                throw new CloudServiceFailureException(e3);
            } catch (JSONException e4) {
                Log.e(TAG, requestMethod + " request error" + body, e4);
                throw new CloudServiceFailureException(e4);
            }
        }
        Log.v(TAG, "Sending a simple " + requestMethod + " request to " + url);
        resp = isPost ? SimpleRequest.postAsString(url, params, cookies, LOGV) : SimpleRequest.getAsString(url, params, cookies, LOGV);
        if (resp == null) {
            throw new CloudServiceFailureException("null content");
        }
        body = resp.getBody();
        JSONObject o = new JSONObject(body);
        if (o.getInt(J_CODE) == 0) {
            return o.getJSONObject(J_DATA);
        }
        String msg = "Unexpected response " + body;
        Log.e(TAG, msg);
        throw new CloudServiceFailureException(msg);
    }

    public static HashMap<String, String> getPhoneBySimIdDevId(String serviceId, String traceId, String hashedDeviceId, String hashedSimId, String pseudoDeviceId, PhoneServiceStateListener listener, String activateHost) throws CloudServiceFailureException, InterruptedException, DownlinkSmsFailedException {
        Log.v(TAG, "getPhoneBySimIdDevId serviceId=" + serviceId + " traceId=" + traceId + " hashedDeviceId=" + hashedDeviceId + " hashedSimId=" + hashedSimId);
        EasyMap<String, String> params = new EasyMap().easyPut(ARG_SERVICE_ID, serviceId).easyPut(ARG_TRACE_ID, traceId).easyPut(ARG_SIM_ID, hashedSimId).easyPut(ARG_DEV_ID, hashedDeviceId).easyPut(ARG_PSEUDO_DEVICE_ID_PLAIN, pseudoDeviceId);
        int i = MASK_PHONE_NUMBER_MODE_HEAD;
        while (!Thread.currentThread().isInterrupted()) {
            JSONObject data = null;
            try {
                listener.waitForService();
                data = request(LOGV, getUrlGetPhoneBySimidDevid(activateHost), null, params, null);
                String status = data.getString(J_STATUS);
                if (STATUS_OK.equals(status)) {
                    JSONArray simList = data.getJSONArray(J_RESULT_LIST);
                    HashMap<String, String> newHashMap = Maps.newHashMap();
                    for (int j = MASK_PHONE_NUMBER_MODE_HEAD; j < simList.length(); j += MASK_PHONE_NUMBER_MODE_TAIL) {
                        JSONObject item = simList.getJSONObject(j);
                        String vkey2 = item.getString(J_VKEY2);
                        String messageId = item.getString(J_MESSAGE_ID);
                        Log.v(TAG, "getPhoneBySimIdDevId got item with vkey2=" + vkey2 + " and messageId=" + messageId);
                        newHashMap.put(messageId, vkey2);
                    }
                    return newHashMap;
                } else if (STATUS_SIM_ID_NOT_FOUND.equals(status)) {
                    Log.v(TAG, "getPhoneBySimIdDevId got no item");
                    return null;
                } else if (STATUS_SEND_DOWNLINK_MESSAGE_ERROR.equals(status)) {
                    Log.w(TAG, "getPhoneBySimIdDevId: server failed to send sms");
                    throw new DownlinkSmsFailedException();
                } else {
                    Log.w(TAG, "getPhoneBySimIdDevId got error status: " + status);
                    throw new CloudServiceFailureException("getPhoneBySimIdDevId error: " + status);
                }
            } catch (IOException e) {
                Log.e(TAG, "getPhoneBySimIdDevId error", e);
                if (i < SERVER_RETRY_INTERVALS.length) {
                    Log.i(TAG, "Wait " + SERVER_RETRY_INTERVALS[i] + " ms for retry[#" + i + "]");
                    Thread.sleep((long) SERVER_RETRY_INTERVALS[i]);
                    i += MASK_PHONE_NUMBER_MODE_TAIL;
                } else {
                    throw new CloudServiceFailureException("Too many failures from server");
                }
            } catch (JSONException e2) {
                Log.e(TAG, "getPhoneBySimIdDevId error: " + data, e2);
                throw new CloudServiceFailureException("invalid json data from server");
            } catch (IOException e3) {
                Log.e(TAG, "getPhoneBySimIdDevId error", e3);
                throw new CloudServiceFailureException(e3);
            }
        }
        throw new InterruptedException();
    }

    public static HashMap<String, String> sendVKeyToPhone(String serviceId, String traceId, String hashedDeviceId, String hashedSimId, String pseudoDeviceId, String phoneOrCode, PhoneServiceStateListener listener, String activateHost) throws CloudServiceFailureException, InterruptedException, PhoneErrorException {
        Log.v(TAG, "sendVKeyToPhone serviceId=" + serviceId + " traceId=" + traceId + " phoneOrCode=" + mask(phoneOrCode));
        EasyMap<String, String> params = new EasyMap().easyPut(ARG_SERVICE_ID, serviceId).easyPut(ARG_TRACE_ID, traceId).easyPut(ARG_SIM_ID, hashedSimId).easyPut(ARG_DEV_ID, hashedDeviceId).easyPut(ARG_PHONE_OR_CODE, phoneOrCode).easyPut(ARG_PSEUDO_DEVICE_ID_PLAIN, pseudoDeviceId);
        int i = MASK_PHONE_NUMBER_MODE_HEAD;
        while (!Thread.currentThread().isInterrupted()) {
            JSONObject data = null;
            try {
                listener.waitForService();
                data = request(LOGV, getUrlSendVkeyToPhone(activateHost), null, params, null);
                String status = data.getString(J_STATUS);
                if (STATUS_OK.equals(status)) {
                    String vkey2 = data.getString(J_VKEY2);
                    String messageId = data.getString(J_MESSAGE_ID);
                    HashMap<String, String> result = Maps.newHashMap();
                    result.put(messageId, vkey2);
                    return result;
                } else if (STATUS_PHONE_ERROR.equals(status) || STATUS_VCODE_ERROR.equals(status)) {
                    Log.w(TAG, "sendVKeyToPhone error");
                    throw new PhoneErrorException();
                } else {
                    Log.w(TAG, "sendVKeyToPhone got error status: " + status);
                    throw new CloudServiceFailureException("getPhoneBySimIdDevId error: " + status);
                }
            } catch (AuthenticationFailureException e) {
                Log.e(TAG, "sendVKeyToPhone error", e);
                throw new CloudServiceFailureException(e);
            } catch (IOException e2) {
                Log.e(TAG, "sendVKeyToPhone error", e2);
                if (i < SERVER_RETRY_INTERVALS.length) {
                    Log.e(TAG, "Wait " + SERVER_RETRY_INTERVALS[i] + " ms for retry[#" + i + "]");
                    Thread.sleep((long) SERVER_RETRY_INTERVALS[i]);
                    i += MASK_PHONE_NUMBER_MODE_TAIL;
                } else {
                    throw new CloudServiceFailureException("Too many failures from server");
                }
            } catch (IOException e22) {
                Log.e(TAG, "sendVKeyToPhone error: " + data, e22);
                throw new CloudServiceFailureException("invalid json data from server: ");
            }
        }
        throw new InterruptedException();
    }

    private static void fillMetaInfo(int simIndex, EasyMap<String, String> params) {
        SysInterface si = ActivateExternal.getSysInteface();
        params.easyPut(ARG_NETWORK_OPERATOR, si.getNetworkOperator(simIndex)).easyPut(ARG_SIM_OPERATOR, si.getSimOperator(simIndex));
        CellLocation location = si.getCellLocation(simIndex);
        if (location instanceof GsmCellLocation) {
            GsmCellLocation gcl = (GsmCellLocation) location;
            params.easyPut(ARG_CELL_TYPE, "GSM").easyPut(ARG_LAC, String.valueOf(gcl.getLac())).easyPut(ARG_CID, String.valueOf(gcl.getCid()));
        } else if (location instanceof CdmaCellLocation) {
            CdmaCellLocation ccl = (CdmaCellLocation) location;
            params.easyPut(ARG_CELL_TYPE, "CDMA").easyPut(ARG_LAC, String.valueOf(ccl.getNetworkId())).easyPut(ARG_CID, String.valueOf(ccl.getBaseStationId()));
        }
    }

    public static String verifyPhone(int simIndex, String serviceId, String traceId, String hashedDeviceId, String hashedSimId, String pseudoDeviceId, String vkey1, String vkey2, String simOperatorPattern, String gatewayNumber, DiagnosisInfo diagnosisInfo, String activateHost) throws CloudServiceFailureException, VKeyExpiredException, InterruptedException, NoPhoneInfoException {
        Log.v(TAG, "verifyPhone serviceId=" + serviceId + " traceId=" + traceId + " hashedDeviceId=" + hashedDeviceId + " hashedSimId=" + hashedSimId + " vkey1=" + mask(vkey1) + " vkey2=" + vkey2);
        String simOperator = ActivateExternal.getSysInteface().getNetworkOperator(simIndex);
        EasyMap<String, String> params = new EasyMap().easyPut(ARG_DEV_ID, hashedDeviceId).easyPut(ARG_SIM_ID, hashedSimId).easyPut(J_VKEY2, vkey2).easyPut(ARG_SERVICE_ID, serviceId).easyPut(ARG_TRACE_ID, traceId).easyPut(ARG_VKEY2_PLAIN, vkey2).easyPutOpt(ARG_SIM_OPERATOR_PLAIN, TextUtils.isEmpty(simOperator) ? null : CloudCoder.hashDeviceInfo(simOperator)).easyPutOpt(ARG_GATEWAY_PLAIN, TextUtils.isEmpty(gatewayNumber) ? null : CloudCoder.hashDeviceInfo(gatewayNumber)).easyPut(ARG_PSEUDO_DEVICE_ID_PLAIN, pseudoDeviceId);
        fillMetaInfo(simIndex, params);
        if (diagnosisInfo != null) {
            diagnosisInfo.setHashedImei(hashedDeviceId);
            diagnosisInfo.setTraceId(traceId);
            diagnosisInfo.setHashedNetworkOperator(CloudCoder.hashDeviceInfo((String) params.get(ARG_NETWORK_OPERATOR)));
            diagnosisInfo.setHashedSimOperator(CloudCoder.hashDeviceInfo((String) params.get(ARG_SIM_OPERATOR)));
            diagnosisInfo.setHashedLac(CloudCoder.hashDeviceInfo((String) params.get(ARG_LAC)));
            diagnosisInfo.setHashedCid(CloudCoder.hashDeviceInfo((String) params.get(ARG_CID)));
            diagnosisInfo.setTimestamp(System.currentTimeMillis());
            diagnosisInfo.setSimIndex(simIndex);
        }
        boolean isDownlinkSms = (simOperatorPattern == null || gatewayNumber == null) ? LOGV : false;
        if (!isDownlinkSms) {
            Log.v(TAG, "UplinkSms, waiting for 3000 ms before trying to get data from gateway...");
            Thread.sleep(3000);
            Log.v(TAG, "waiting done");
        }
        int i = MASK_PHONE_NUMBER_MODE_HEAD;
        boolean serverError = false;
        while (!Thread.currentThread().isInterrupted()) {
            JSONObject data = null;
            try {
                data = request(LOGV, getUrlVerifyPhone(activateHost), convertBase16ToBase64(vkey1), params, null);
                String status = data.getString(J_STATUS);
                if (STATUS_OK.equals(status)) {
                    String phone = data.getString(J_PHONE);
                    Log.v(TAG, "verifyPhone got phone=" + mask(phone));
                    return phone;
                } else if (STATUS_VKEY_NOT_FOUND.equals(status)) {
                    Log.w(TAG, "verifyPhone: key not found");
                    if (isDownlinkSms) {
                        Log.v(TAG, "verifyPhone: downlink, bail.");
                        return null;
                    }
                    if (i < SERVER_RETRY_INTERVALS.length) {
                        Log.e(TAG, "Wait " + SERVER_RETRY_INTERVALS[i] + " ms for retry[#" + i + "]");
                        Thread.sleep((long) SERVER_RETRY_INTERVALS[i]);
                        i += MASK_PHONE_NUMBER_MODE_TAIL;
                    } else if (serverError) {
                        throw new CloudServiceFailureException("Too many failures from server");
                    } else {
                        Log.e(TAG, "Still no phone info after " + SERVER_RETRY_INTERVALS.length + "trying.");
                        throw new NoPhoneInfoException();
                    }
                } else if (STATUS_VKEY_EXPIRED.equals(status)) {
                    Log.w(TAG, "verifyPhone: vkey expired");
                    throw new VKeyExpiredException();
                } else {
                    Log.w(TAG, "verifyPhone got error status: " + status);
                    throw new CloudServiceFailureException("getPhoneBySimIdDevId error: " + status);
                }
            } catch (IOException e) {
                Log.e(TAG, "verifyPhone error", e);
                serverError = LOGV;
            } catch (JSONException e2) {
                Log.e(TAG, "verifyPhone error " + data, e2);
                throw new CloudServiceFailureException("invalid json data from server");
            } catch (AuthenticationFailureException e3) {
                Log.i(TAG, "verifyPhone: vkey1 does not exist yet");
                serverError = false;
            } catch (DecoderException e4) {
                Log.e(TAG, "verifyPhone error", e4);
                throw new CloudServiceFailureException(e4);
            }
        }
        throw new InterruptedException();
    }

    public static boolean doesAccountExist(String type, String externalId) throws CloudServiceFailureException, InterruptedException {
        Log.v(TAG, "checkAccountOnAddress type=" + type + " externalId=" + mask(externalId));
        EasyMap<String, String> params = new EasyMap().easyPut(ARG_EXTERNAL_ID, externalId).easyPut(ARG_USER_TYPE_ALT, type);
        int i = MASK_PHONE_NUMBER_MODE_HEAD;
        while (!Thread.currentThread().isInterrupted()) {
            JSONObject data = null;
            try {
                data = request(false, URL_CHECK_ACCOUNT_ON_ADDRESS, null, params, null);
                if (data.getLong(J_USER_ID) != -1) {
                    return LOGV;
                }
                return false;
            } catch (IOException e) {
                Log.e(TAG, "checkAccountOnAddress error ", e);
                if (i < SERVER_RETRY_INTERVALS.length) {
                    Log.e(TAG, "Wait " + SERVER_RETRY_INTERVALS[i] + " ms for retry[#" + i + "]");
                    Thread.sleep((long) SERVER_RETRY_INTERVALS[i]);
                    i += MASK_PHONE_NUMBER_MODE_TAIL;
                } else {
                    throw new CloudServiceFailureException("Too many failures from server");
                }
            } catch (JSONException e2) {
                Log.e(TAG, "checkAccountOnAddress error " + data, e2);
                throw new CloudServiceFailureException("invalid json data from server");
            } catch (IOException e3) {
                Log.e(TAG, "checkAccountOnAddress error ", e3);
                throw new CloudServiceFailureException(e3);
            }
        }
        throw new InterruptedException();
    }

    public static Pair<String, String> createOrUpdateUser(int simIndex, String userType, String serviceId, String traceId, String hashedDeviceId, String hashedSimId, String pseudoDeviceId, String vkey1, String vkey2, String phone, String password, DiagnosisInfo diagnosisInfo, String activateHost) throws CloudServiceFailureException, VKeyExpiredException, InterruptedException {
        Log.v(TAG, "createOrUpdateUser serviceId=" + serviceId + " traceId=" + traceId + " hashedDeviceId=" + hashedDeviceId + " hashedSimId=" + hashedSimId + " userType=" + userType + " vkey1=" + mask(vkey1) + " vkey2=" + vkey2 + " phone=" + mask(phone) + (TextUtils.isEmpty(password) ? "noPassword" : "hasPassword"));
        EasyMap<String, String> params = new EasyMap().easyPut(ARG_USER_TYPE, userType).easyPut(ARG_SIM_ID, hashedSimId).easyPut(ARG_DEV_ID, hashedDeviceId).easyPut(J_VKEY2, vkey2).easyPut(J_PHONE, phone).easyPutOpt(ARG_PASSWORD, password).easyPut(ARG_SERVICE_ID, serviceId).easyPut(ARG_TRACE_ID, traceId).easyPut(ARG_VKEY2_PLAIN, vkey2).easyPut(ARG_PSEUDO_DEVICE_ID_PLAIN, pseudoDeviceId);
        fillMetaInfo(simIndex, params);
        if (diagnosisInfo != null) {
            diagnosisInfo.setHashedImei(hashedDeviceId);
            diagnosisInfo.setTraceId(traceId);
            diagnosisInfo.setHashedNetworkOperator(CloudCoder.hashDeviceInfo((String) params.get(ARG_NETWORK_OPERATOR)));
            diagnosisInfo.setHashedSimOperator(CloudCoder.hashDeviceInfo((String) params.get(ARG_SIM_OPERATOR)));
            diagnosisInfo.setHashedLac(CloudCoder.hashDeviceInfo((String) params.get(ARG_LAC)));
            diagnosisInfo.setHashedCid(CloudCoder.hashDeviceInfo((String) params.get(ARG_CID)));
            diagnosisInfo.setTimestamp(System.currentTimeMillis());
            diagnosisInfo.setSimIndex(simIndex);
        }
        int i = MASK_PHONE_NUMBER_MODE_HEAD;
        while (!Thread.currentThread().isInterrupted()) {
            JSONObject data = null;
            try {
                data = request(LOGV, getUrlCreateOrUpdateUser(activateHost), convertBase16ToBase64(vkey1), params, null);
                String status = data.getString(J_STATUS);
                if (STATUS_OK.equals(status)) {
                    String userId = data.getString(J_USER_ID);
                    String passToken = data.getString(J_PASS_TOKEN);
                    Log.v(TAG, "createOrUpdateUser got userId=" + mask(userId));
                    return Pair.create(userId, passToken);
                } else if (STATUS_VKEY_EXPIRED.equals(status)) {
                    Log.w(TAG, "vkey expired");
                    throw new VKeyExpiredException();
                } else {
                    Log.w(TAG, "createOrUpdateUser got error status: " + status);
                    throw new CloudServiceFailureException("createOrUpdateUser error: " + status);
                }
            } catch (IOException e) {
                Log.e(TAG, "createOrUpdateUser error", e);
                if (i < SERVER_RETRY_INTERVALS.length) {
                    Log.e(TAG, "Wait " + SERVER_RETRY_INTERVALS[i] + " ms for retry[#" + i + "]");
                    Thread.sleep((long) SERVER_RETRY_INTERVALS[i]);
                    i += MASK_PHONE_NUMBER_MODE_TAIL;
                } else {
                    throw new CloudServiceFailureException("Too many failures from server");
                }
            } catch (JSONException e2) {
                Log.e(TAG, "createOrUpdateUser error " + data, e2);
                throw new CloudServiceFailureException("invalid json data from server");
            } catch (AuthenticationFailureException e3) {
                Log.i(TAG, "createOrUpdateUser: vkey expired by 401", e3);
                throw new VKeyExpiredException();
            } catch (IOException e4) {
                Log.e(TAG, "createOrUpdateUser error", e4);
                throw new CloudServiceFailureException(e4);
            }
        }
        throw new InterruptedException();
    }

    public static AccountInfo verifyAndRenewPassToken(String userId, String passToken) throws InterruptedException, CloudServiceFailureException {
        if (userId == null || passToken == null) {
            Log.w(TAG, "User or passToken is null, check token skipped");
            return null;
        }
        Log.v(TAG, "Verifying sim passtoken");
        int i = MASK_PHONE_NUMBER_MODE_HEAD;
        while (!Thread.currentThread().isInterrupted()) {
            try {
                AccountInfo info = XMPassport.loginByPassToken(userId, "passportapi", safeGetHashedDeviceId(), passToken);
                Log.v(TAG, "SIM passtoken verfied.");
                return info;
            } catch (IOException e) {
                Log.e(TAG, "verifyAndRenewPassToken error", e);
                if (i < SERVER_RETRY_INTERVALS.length) {
                    Log.e(TAG, "Wait " + SERVER_RETRY_INTERVALS[i] + " ms for retry[#" + i + "]");
                    Thread.sleep((long) SERVER_RETRY_INTERVALS[i]);
                    i += MASK_PHONE_NUMBER_MODE_TAIL;
                } else {
                    throw new CloudServiceFailureException();
                }
            } catch (InvalidResponseException e2) {
                Log.e(TAG, "verifyAndRenewPassToken error", e2);
                throw new CloudServiceFailureException();
            } catch (InvalidCredentialException e3) {
                Log.e(TAG, "verifyAndRenewPassToken error, returning null instead", e3);
                return null;
            } catch (AccessDeniedException e4) {
                Log.e(TAG, "verifyAndRenewPassToken error", e4);
                throw new CloudServiceFailureException();
            } catch (AuthenticationFailureException e5) {
                Log.e(TAG, "verifyAndRenewPassToken error", e5);
                throw new CloudServiceFailureException();
            } catch (IOException e6) {
                Log.e(TAG, "verifyAndRenewPassToken error, returning null instead", e6);
                return null;
            }
        }
        Log.v(TAG, "Verification interrupted");
        throw new InterruptedException();
    }

    public static boolean insertActivateInfoToServer(String userId, String serviceToken, String serviceId, String traceId, String ssecurity, String hashedDeviceId, String hashedSimId, String pseudoDeviceId, String phone, String activateHost) throws CloudServiceFailureException, InterruptedException {
        Log.v(TAG, "insertActivateInfoToServer serviceId=" + serviceId + " traceId=" + traceId + " hashedDeviceId=" + hashedDeviceId + " hashedSimId=" + hashedSimId + " ssecurity=" + mask(ssecurity) + " phone=" + mask(phone));
        EasyMap<String, String> params = new EasyMap().easyPut(ARG_SERVICE_ID, serviceId).easyPut(ARG_SIM_ID, hashedSimId).easyPut(ARG_DEV_ID, hashedDeviceId).easyPut(J_PHONE, phone).easyPut(ARG_TRACE_ID, traceId).easyPut(ARG_PSEUDO_DEVICE_ID_PLAIN, pseudoDeviceId);
        EasyMap<String, String> cookies = new EasyMap().easyPut(J_USER_ID, userId).easyPut("serviceToken", serviceToken);
        int i = MASK_PHONE_NUMBER_MODE_HEAD;
        while (!Thread.currentThread().isInterrupted()) {
            try {
                String status = request(LOGV, getUrlInsertActivateInfoToServer(activateHost), ssecurity, params, cookies).getString(J_STATUS);
                if (STATUS_OK.equals(status)) {
                    Log.v(TAG, "insertActivateInfoToServer got result=" + status);
                    return LOGV;
                } else if (STATUS_INSERT_PHONE_ACTIVATED_ALREADY_EXIST.equals(status)) {
                    Log.v(TAG, "insertActivateInfoToServer got result=" + status);
                    return LOGV;
                } else {
                    Log.w(TAG, "insertActivateInfoToServer got error status: " + status);
                    throw new CloudServiceFailureException("createOrUpdateUser error: " + status);
                }
            } catch (IOException e) {
                Log.e(TAG, "insertActivateInfoToServer error", e);
                if (i < SERVER_RETRY_INTERVALS.length) {
                    Log.e(TAG, "Wait " + SERVER_RETRY_INTERVALS[i] + " ms for retry[#" + i + "]");
                    Thread.sleep((long) SERVER_RETRY_INTERVALS[i]);
                    i += MASK_PHONE_NUMBER_MODE_TAIL;
                } else {
                    throw new CloudServiceFailureException("Too many failures from server");
                }
            } catch (JSONException e2) {
                Log.e(TAG, "insertActivateInfoToServer error " + null, e2);
                throw new CloudServiceFailureException("invalid json data from server");
            } catch (IOException e3) {
                Log.i(TAG, "insertActivateInfoToServer error", e3);
                throw new CloudServiceFailureException(e3);
            }
        }
        throw new InterruptedException();
    }

    public static Pair<String, String> getCurrentSmsGateway(Context context, int simIndex) {
        Pair<String, ArrayList<String>> gatewayList = getSmsGatewayList(context, simIndex, LOGV);
        if (gatewayList == null || ((ArrayList) gatewayList.second).size() <= 0) {
            Log.e(TAG, "No gateway list for sim " + simIndex + ", bail.");
            return null;
        }
        int index = PrefUtils.getInt(context, Constants.PREF_CURRENT_GATEWAY_INDEX + ((String) gatewayList.first), MASK_PHONE_NUMBER_MODE_HEAD);
        String gatewayNumber = (String) ((ArrayList) gatewayList.second).get(index % ((ArrayList) gatewayList.second).size());
        Log.v(TAG, "Getting gateway[" + index + "] for " + ((String) gatewayList.first) + " as " + gatewayNumber);
        return Pair.create(gatewayList.first, gatewayNumber);
    }

    public static String sendActivateSms(int simIndex, PendingIntent sentIntent, String vkey1, String vkey2, String gw) {
        String smsBody = "AC/" + vkey1 + ":" + vkey2;
        Log.v(TAG, "sending reg sms for sim " + simIndex + " to " + gw);
        ActivateExternal.getSysInteface().sendTextMessage(simIndex, gw, null, smsBody, sentIntent, null);
        return smsBody;
    }

    public static void nextSmsGateway(Context context, String simOperatorPattern) {
        int index = PrefUtils.getInt(context, Constants.PREF_CURRENT_GATEWAY_INDEX + simOperatorPattern, MASK_PHONE_NUMBER_MODE_HEAD) + MASK_PHONE_NUMBER_MODE_TAIL;
        Log.v(TAG, "Incrementing gateway index for " + simOperatorPattern + " to " + index);
        PrefUtils.saveInt(context, Constants.PREF_CURRENT_GATEWAY_INDEX + simOperatorPattern, index);
    }

    public static String mask(String src) {
        if (src == null) {
            return "null";
        }
        return maskPhoneNumber(src, MASK_PHONE_NUMBER_MODE_TAIL);
    }

    public static String maskPhoneNumber(String phoneNumber, int cutMode) {
        if (phoneNumber == null) {
            return "";
        }
        int i;
        int alnumCount = MASK_PHONE_NUMBER_MODE_HEAD;
        for (i = MASK_PHONE_NUMBER_MODE_HEAD; i < phoneNumber.length(); i += MASK_PHONE_NUMBER_MODE_TAIL) {
            if (isAlnum(phoneNumber.charAt(i))) {
                alnumCount += MASK_PHONE_NUMBER_MODE_TAIL;
            }
        }
        if (alnumCount < 7) {
            return new String(phoneNumber);
        }
        int cutLength;
        int cutStart;
        if (alnumCount < 11) {
            cutLength = MASK_PHONE_NUMBER_MODE_MIDDLE;
        } else {
            cutLength = 3;
        }
        switch (cutMode) {
            case MASK_PHONE_NUMBER_MODE_HEAD /*0*/:
                cutStart = MASK_PHONE_NUMBER_MODE_HEAD;
                break;
            case MASK_PHONE_NUMBER_MODE_TAIL /*1*/:
                cutStart = alnumCount - cutLength;
                break;
            case MASK_PHONE_NUMBER_MODE_MIDDLE /*2*/:
                cutStart = (alnumCount - cutLength) / MASK_PHONE_NUMBER_MODE_MIDDLE;
                break;
            default:
                throw new IllegalArgumentException("Invalid cut mode");
        }
        StringBuilder result = new StringBuilder();
        int addedAlnumCount = MASK_PHONE_NUMBER_MODE_HEAD;
        for (i = MASK_PHONE_NUMBER_MODE_HEAD; i < phoneNumber.length(); i += MASK_PHONE_NUMBER_MODE_TAIL) {
            if (isAlnum(phoneNumber.charAt(i))) {
                if (addedAlnumCount < cutStart || cutLength <= 0) {
                    result.append(phoneNumber.charAt(i));
                } else {
                    result.append('?');
                    cutLength--;
                }
                addedAlnumCount += MASK_PHONE_NUMBER_MODE_TAIL;
            } else {
                result.append(phoneNumber.charAt(i));
            }
        }
        return result.toString();
    }

    private static boolean isAlnum(char ch) {
        return ((ch < '0' || ch > '9') && ((ch < 'a' || ch > 'z') && (ch < 'A' || ch > 'Z'))) ? false : LOGV;
    }

    public static String makeVKey1() {
        return generateRandomHex(32);
    }

    public static String makeVKey2() {
        Object[] objArr = new Object[MASK_PHONE_NUMBER_MODE_TAIL];
        objArr[MASK_PHONE_NUMBER_MODE_HEAD] = Long.valueOf(System.currentTimeMillis() / 1000);
        String first = String.format("%08x", objArr);
        return first + generateRandomHex(8);
    }

    public static String convertBase16ToBase64(String src) throws DecoderException {
        char[] buffer = new char[src.length()];
        src.getChars(MASK_PHONE_NUMBER_MODE_HEAD, src.length(), buffer, MASK_PHONE_NUMBER_MODE_HEAD);
        return Base64.encode(Hex.decodeHex(buffer));
    }

    public static void notifyUnactivatedSimCards(Context context) {
        NotificationManager nm = (NotificationManager) context.getSystemService("notification");
        Builder builder = new Builder(context);
        builder.setContentTitle(context.getString(R.string.sim_unactivated_title));
        builder.setSmallIcon(ActivateExternal.getSysInteface().getAppIconRes());
        builder.setAutoCancel(LOGV);
        builder.setWhen(System.currentTimeMillis());
        builder.setContentIntent(getActivatePopupPendingIntent(context, -1, MASK_PHONE_NUMBER_MODE_TAIL, null));
        nm.notify(5, builder.build());
    }

    public static void cancelNotifyUnactivatedSimCards(Context context) {
        ((NotificationManager) context.getSystemService("notification")).cancel(5);
        ActivatePopupActivity.dismiss();
    }

    public static PendingIntent getActivatePopupPendingIntent(Context context, int simIndex, int reason, String phone) {
        Intent intent = new Intent(context, ActivatePopupActivity.class);
        intent.putExtra("extra_sim_index", simIndex);
        intent.putExtra("extra_activate_reason", reason);
        intent.putExtra("extra_activate_feature_index", MASK_PHONE_NUMBER_MODE_HEAD);
        intent.putExtra("extra_activate_default_phone_number", phone);
        intent.putExtra("extra_activate_source", "ActivateService");
        return PendingIntent.getActivity(context, MASK_PHONE_NUMBER_MODE_HEAD, intent, 134217728);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void querySmsGateway(android.content.Context r10) throws java.lang.InterruptedException {
        /*
        r9 = 0;
        r4 = sIsQueryingGateway;
        r5 = 1;
        r4 = r4.getAndSet(r5);
        if (r4 == 0) goto L_0x0025;
    L_0x000a:
        r5 = sQueryGatewayLock;
        monitor-enter(r5);
        r4 = "ActivateHelper";
        r6 = "Waiting for an existing querySmsGateway to finish";
        android.util.Log.v(r4, r6);	 Catch:{ all -> 0x0022 }
        r4 = sQueryGatewayLock;	 Catch:{ all -> 0x0022 }
        r4.wait();	 Catch:{ all -> 0x0022 }
        r4 = "ActivateHelper";
        r6 = "The existing querySmsGateway finished";
        android.util.Log.v(r4, r6);	 Catch:{ all -> 0x0022 }
        monitor-exit(r5);	 Catch:{ all -> 0x0022 }
    L_0x0021:
        return;
    L_0x0022:
        r4 = move-exception;
        monitor-exit(r5);	 Catch:{ all -> 0x0022 }
        throw r4;
    L_0x0025:
        r2 = 0;
    L_0x0026:
        r4 = java.lang.Thread.currentThread();	 Catch:{ all -> 0x0036 }
        r4 = r4.isInterrupted();	 Catch:{ all -> 0x0036 }
        if (r4 == 0) goto L_0x0046;
    L_0x0030:
        r4 = new java.lang.InterruptedException;	 Catch:{ all -> 0x0036 }
        r4.<init>();	 Catch:{ all -> 0x0036 }
        throw r4;	 Catch:{ all -> 0x0036 }
    L_0x0036:
        r4 = move-exception;
        r5 = sIsQueryingGateway;
        r5.set(r9);
        r5 = sQueryGatewayLock;
        monitor-enter(r5);
        r6 = sQueryGatewayLock;	 Catch:{ all -> 0x00d7 }
        r6.notifyAll();	 Catch:{ all -> 0x00d7 }
        monitor-exit(r5);	 Catch:{ all -> 0x00d7 }
        throw r4;
    L_0x0046:
        r4 = 0;
        r5 = URL_QUERY_SMS_GW;	 Catch:{ IOException -> 0x0078, AuthenticationFailureException -> 0x00bd, CloudServiceFailureException -> 0x00c6 }
        r6 = 0;
        r7 = 0;
        r8 = 0;
        r0 = request(r4, r5, r6, r7, r8);	 Catch:{ IOException -> 0x0078, AuthenticationFailureException -> 0x00bd, CloudServiceFailureException -> 0x00c6 }
        r3 = r0.toString();	 Catch:{ IOException -> 0x0078, AuthenticationFailureException -> 0x00bd, CloudServiceFailureException -> 0x00c6 }
        r4 = android.text.TextUtils.isEmpty(r3);	 Catch:{ IOException -> 0x0078, AuthenticationFailureException -> 0x00bd, CloudServiceFailureException -> 0x00c6 }
        if (r4 != 0) goto L_0x0066;
    L_0x005a:
        r4 = "ActivateHelper";
        r5 = "QuerySmsGwTask: Fetched sms gateways, writing into system settings.";
        android.util.Log.d(r4, r5);	 Catch:{ IOException -> 0x0078, AuthenticationFailureException -> 0x00bd, CloudServiceFailureException -> 0x00c6 }
        r4 = "sms_gw_config_v2";
        com.xiaomi.activate.PrefUtils.saveString(r10, r4, r3);	 Catch:{ IOException -> 0x0078, AuthenticationFailureException -> 0x00bd, CloudServiceFailureException -> 0x00c6 }
    L_0x0066:
        r4 = sIsQueryingGateway;
        r4.set(r9);
        r5 = sQueryGatewayLock;
        monitor-enter(r5);
        r4 = sQueryGatewayLock;	 Catch:{ all -> 0x0075 }
        r4.notifyAll();	 Catch:{ all -> 0x0075 }
        monitor-exit(r5);	 Catch:{ all -> 0x0075 }
        goto L_0x0021;
    L_0x0075:
        r4 = move-exception;
        monitor-exit(r5);	 Catch:{ all -> 0x0075 }
        throw r4;
    L_0x0078:
        r1 = move-exception;
        r4 = "ActivateHelper";
        r5 = "querySmsGateway error";
        android.util.Log.e(r4, r5, r1);	 Catch:{ all -> 0x0036 }
        r4 = SERVER_RETRY_INTERVALS;	 Catch:{ all -> 0x0036 }
        r4 = r4.length;	 Catch:{ all -> 0x0036 }
        if (r2 >= r4) goto L_0x00cf;
    L_0x0085:
        r4 = "ActivateHelper";
        r5 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0036 }
        r5.<init>();	 Catch:{ all -> 0x0036 }
        r6 = "Wait ";
        r5 = r5.append(r6);	 Catch:{ all -> 0x0036 }
        r6 = SERVER_RETRY_INTERVALS;	 Catch:{ all -> 0x0036 }
        r6 = r6[r2];	 Catch:{ all -> 0x0036 }
        r5 = r5.append(r6);	 Catch:{ all -> 0x0036 }
        r6 = " ms for retry[#";
        r5 = r5.append(r6);	 Catch:{ all -> 0x0036 }
        r5 = r5.append(r2);	 Catch:{ all -> 0x0036 }
        r6 = "]";
        r5 = r5.append(r6);	 Catch:{ all -> 0x0036 }
        r5 = r5.toString();	 Catch:{ all -> 0x0036 }
        android.util.Log.e(r4, r5);	 Catch:{ all -> 0x0036 }
        r4 = SERVER_RETRY_INTERVALS;	 Catch:{ all -> 0x0036 }
        r4 = r4[r2];	 Catch:{ all -> 0x0036 }
        r4 = (long) r4;	 Catch:{ all -> 0x0036 }
        java.lang.Thread.sleep(r4);	 Catch:{ all -> 0x0036 }
        r2 = r2 + 1;
        goto L_0x0026;
    L_0x00bd:
        r1 = move-exception;
        r4 = "ActivateHelper";
        r5 = "querySmsGateway error ";
        android.util.Log.e(r4, r5, r1);	 Catch:{ all -> 0x0036 }
        goto L_0x0066;
    L_0x00c6:
        r1 = move-exception;
        r4 = "ActivateHelper";
        r5 = "querySmsGateway error ";
        android.util.Log.e(r4, r5, r1);	 Catch:{ all -> 0x0036 }
        goto L_0x0066;
    L_0x00cf:
        r4 = "ActivateHelper";
        r5 = "Too many failures from server";
        android.util.Log.e(r4, r5);	 Catch:{ all -> 0x0036 }
        goto L_0x0066;
    L_0x00d7:
        r4 = move-exception;
        monitor-exit(r5);	 Catch:{ all -> 0x00d7 }
        throw r4;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.activate.ActivateHelper.querySmsGateway(android.content.Context):void");
    }

    public static String safeGetHashedDeviceId() {
        String rawId = ActivateExternal.getSysInteface().blockingGetDeviceId(DateTimeHelper.sMinuteInMilliseconds);
        if (!TextUtils.isEmpty(rawId)) {
            return CloudCoder.hashDeviceInfo(rawId);
        }
        Log.e(TAG, "error: device id is empty");
        return null;
    }

    public static String generateRandomHex(int length) {
        StringBuilder sb = new StringBuilder();
        sb.append(RANDOM.nextInt());
        sb.append(RANDOM.nextInt());
        sb.append(RANDOM.nextInt());
        String deviceId = safeGetHashedDeviceId();
        if (deviceId != null) {
            sb.append(deviceId);
        }
        String plain = sb.toString();
        try {
            return new String(Hex.encodeHex(MessageDigest.getInstance("MD5").digest(plain.getBytes()))).substring(MASK_PHONE_NUMBER_MODE_HEAD, length);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new IllegalStateException("failed to get MD5");
        }
    }

    public static void waitUntilNetworkConnected(Context context) throws InterruptedException {
        ensureNotOnMainThread(context);
        IntentFilter filter = new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
        ConnectivityResumedReceiver r = new ConnectivityResumedReceiver(context);
        context.registerReceiver(r, filter);
        try {
            r.await();
        } finally {
            context.unregisterReceiver(r);
        }
    }

    public static boolean isNetworkConnected(Context context) {
        NetworkInfo info = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        return (info == null || !info.isConnected()) ? false : LOGV;
    }

    private static void ensureNotOnMainThread(Context context) {
        Looper looper = Looper.myLooper();
        if (looper != null && looper == context.getMainLooper()) {
            throw new IllegalStateException("calling this from your main thread can lead to deadlock");
        }
    }

    public static String getActivateHost(Context context, int simIndex) {
        String hostBackup = ActivateManager.URL_ACCOUNT_ACTIVATE_HOST;
        String s = PrefUtils.getString(context, SMS_GW_V2);
        if (s == null) {
            Log.d(TAG, "no sms gw table in pref.");
            return hostBackup;
        }
        String mccmnc = ActivateExternal.getSysInteface().getSimOperator(simIndex);
        Log.d(TAG, "sim operator mccmnc:" + mccmnc);
        if (mccmnc == null || mccmnc.length() < 4) {
            Log.w(TAG, "illegal mcc mnc: " + mccmnc);
            return hostBackup;
        }
        try {
            String host;
            JSONObject hostTable = new JSONObject(s).getJSONObject("ac.url");
            Iterator<String> iter = hostTable.keys();
            while (iter.hasNext()) {
                String key = (String) iter.next();
                if (!key.equals(GW_KEY_DEFAULT) && Pattern.matches(key, mccmnc)) {
                    host = hostTable.getString(key);
                    Log.d(TAG, "Matched pattern " + key + " and host are " + host);
                    return host;
                }
            }
            host = hostTable.getString(GW_KEY_DEFAULT);
            Log.d(TAG, "Use default host " + host);
            return host;
        } catch (JSONException e) {
            Log.e(TAG, "invalid sms gw string " + s);
            Log.d(TAG, "Found no match for current mccmnc");
            return hostBackup;
        }
    }

    public static Pair<String, ArrayList<String>> getSmsGatewayList(Context context, int simIndex, boolean allowInternational) {
        String s = PrefUtils.getString(context, SMS_GW_V2);
        if (s == null) {
            InputStream is = context.getResources().openRawResource(R.raw.sms_gw);
            if (is != null) {
                try {
                    s = convertStreamToString(is, 1024, Charset.forName("UTF-8"));
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    IOUtils.closeQuietly(is);
                }
            }
        }
        if (s == null) {
            return null;
        }
        String mccmnc = ActivateExternal.getSysInteface().getSimOperator(simIndex);
        Log.d(TAG, "sim operator mccmnc:" + mccmnc);
        if (mccmnc == null || mccmnc.length() < 5) {
            Log.w(TAG, "illegal mcc mnc: " + mccmnc);
            return null;
        }
        try {
            JSONArray valueArray;
            JSONObject mo = new JSONObject(s).getJSONObject("mo");
            Iterator<String> iter = mo.keys();
            while (iter.hasNext()) {
                String key = (String) iter.next();
                if (!key.equals(GW_KEY_DEFAULT) && Pattern.matches(key, mccmnc)) {
                    valueArray = mo.getJSONArray(key);
                    Log.d(TAG, "Matched pattern " + key + " and gateways are " + valueArray);
                    return Pair.create(key, JSONArrayToStringList(valueArray));
                }
            }
            if (allowInternational) {
                valueArray = mo.getJSONArray(GW_KEY_DEFAULT);
                Log.d(TAG, "Use default gateways " + valueArray);
                return Pair.create(GW_KEY_DEFAULT, JSONArrayToStringList(valueArray));
            }
        } catch (JSONException e2) {
            Log.e(TAG, "invalid sms gw string " + s);
        }
        Log.d(TAG, "Found no match for current mccmnc");
        return null;
    }

    public static boolean hasLocalGateway(Context context, int simIndex) {
        return getSmsGatewayList(context, simIndex, false) != null ? LOGV : false;
    }

    private static ArrayList<String> JSONArrayToStringList(JSONArray valueArray) throws JSONException {
        ArrayList<String> valueList = Lists.newArrayList();
        for (int i = MASK_PHONE_NUMBER_MODE_HEAD; i < valueArray.length(); i += MASK_PHONE_NUMBER_MODE_TAIL) {
            valueList.add(valueArray.getString(i));
        }
        return valueList;
    }

    private static String convertStreamToString(InputStream is, int bufferSize, Charset charset) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is, charset));
        StringBuffer content = new StringBuffer();
        char[] buffer = new char[bufferSize];
        while (true) {
            int n = reader.read(buffer);
            if (n == -1) {
                return content.toString();
            }
            content.append(buffer, MASK_PHONE_NUMBER_MODE_HEAD, n);
        }
    }
}
