package com.xiaomi.activate;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import com.xiaomi.accountsdk.request.AccessDeniedException;
import com.xiaomi.accountsdk.request.AuthenticationFailureException;
import com.xiaomi.accountsdk.request.SimpleRequest;
import com.xiaomi.accountsdk.request.SimpleRequest.StringContent;
import com.xiaomi.accountsdk.utils.EasyMap;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ActivateUploadDiagnosisService extends IntentService {
    private static final String KEY_CATEGORY = "category";
    private static final String KEY_EXTRA_DATA = "extra_data";
    private static final String KEY_GATEWAY = "gateway";
    private static final String KEY_HASHED_CID = "hashedCid";
    private static final String KEY_HASHED_CONTENT = "hashedContent";
    private static final String KEY_HASHED_IMEI = "hashedImei";
    private static final String KEY_HASHED_LAC = "hashedLac";
    private static final String KEY_HASHED_NETWORK_OPERATOR = "hashedNetworkOperator";
    private static final String KEY_HASHED_OPERATOR_PATTERN = "hashedOperatorPattern";
    private static final String KEY_HASHED_SIM_OPERATOR = "hashedSimOperator";
    private static final String KEY_LAST_SIM_INDEX = "KEY_LAST_SIM_INDEX";
    private static final String KEY_SIM_INDEX = "simIndex";
    private static final String KEY_START_SIM_INDEX = "KEY_START_SIM_INDEX";
    private static final String KEY_TIMESTAMP = "timestamp";
    private static final String KEY_TRACEID = "traceId";
    private static final String TAG = "ActivateUploadDiagnoseService";
    private static final String TRACEID_SPLIT = ",";
    private static final String URL = "http://r.account.xiaomi.com/r/ac/t";
    private static final String USER_AGENT = "useragent";
    private static Map<Integer, DiagnosisInfo> sDiagnosisInfoMaps;

    public static class DiagnosisInfo {
        private Bundle mBundle;

        public static class Category {
            public static int FAIL_ACTIVATE_OTHER;
            public static int FAIL_GET_PHONE;
            public static int FAIL_SEND_SMS;

            static {
                FAIL_SEND_SMS = 3;
                FAIL_GET_PHONE = 5;
                FAIL_ACTIVATE_OTHER = 7;
            }
        }

        public DiagnosisInfo() {
            this.mBundle = new Bundle();
        }

        Bundle getBundle() {
            return this.mBundle;
        }

        public boolean hasCategory() {
            return this.mBundle.keySet().contains(ActivateUploadDiagnosisService.KEY_CATEGORY);
        }

        public boolean isValidErrorInfo() {
            return hasCategory();
        }

        public void setCategory(int category) {
            this.mBundle.putString(ActivateUploadDiagnosisService.KEY_CATEGORY, String.valueOf(category));
        }

        public void setHashedSimOperator(String hashedSimOperator) {
            this.mBundle.putString(ActivateUploadDiagnosisService.KEY_HASHED_SIM_OPERATOR, hashedSimOperator);
        }

        public void setHashedNetworkOperator(String hashedNetworkOperator) {
            this.mBundle.putString(ActivateUploadDiagnosisService.KEY_HASHED_NETWORK_OPERATOR, hashedNetworkOperator);
        }

        public void setHashedOperatorPattern(String hashedOperatorPattern) {
            this.mBundle.putString(ActivateUploadDiagnosisService.KEY_HASHED_OPERATOR_PATTERN, hashedOperatorPattern);
        }

        public void setHashedImei(String hashedImei) {
            this.mBundle.putString(ActivateUploadDiagnosisService.KEY_HASHED_IMEI, hashedImei);
        }

        public void setHashedContent(String hashedContent) {
            this.mBundle.putString(ActivateUploadDiagnosisService.KEY_HASHED_CONTENT, hashedContent);
        }

        public void setGateway(String gateway) {
            this.mBundle.putString(ActivateUploadDiagnosisService.KEY_GATEWAY, gateway);
        }

        public void setHashedLac(String hashedLac) {
            this.mBundle.putString(ActivateUploadDiagnosisService.KEY_HASHED_LAC, hashedLac);
        }

        public void setHashedCid(String hashedCid) {
            this.mBundle.putString(ActivateUploadDiagnosisService.KEY_HASHED_CID, hashedCid);
        }

        public void setUseragent(String useragent) {
            this.mBundle.putString(ActivateUploadDiagnosisService.USER_AGENT, useragent);
        }

        public void setSimIndex(int simIndex) {
            this.mBundle.putString(ActivateUploadDiagnosisService.KEY_SIM_INDEX, String.valueOf(simIndex));
        }

        public void setTimestamp(long timestamp) {
            this.mBundle.putString(ActivateUploadDiagnosisService.KEY_TIMESTAMP, String.valueOf(timestamp));
        }

        public void setTraceId(String traceId) {
            this.mBundle.putString(ActivateUploadDiagnosisService.KEY_TRACEID, traceId);
        }

        public String getTraceId() {
            return this.mBundle.getString(ActivateUploadDiagnosisService.KEY_TRACEID);
        }
    }

    static {
        sDiagnosisInfoMaps = new HashMap();
    }

    public static void saveDiagnosisInfo(int simIndex, DiagnosisInfo info) {
        sDiagnosisInfoMaps.put(Integer.valueOf(simIndex), info);
    }

    public static String getTraceIdOfDiagnosis(Context context, int firstSimIndex, int lastSimIndex) {
        StringBuilder sb = new StringBuilder();
        for (int i = firstSimIndex; i <= lastSimIndex; i++) {
            if (sDiagnosisInfoMaps.containsKey(Integer.valueOf(i))) {
                DiagnosisInfo info = (DiagnosisInfo) sDiagnosisInfoMaps.get(Integer.valueOf(i));
                if (info != null) {
                    String traceId = info.getTraceId();
                    if (sb.length() > 0) {
                        sb.append(TRACEID_SPLIT);
                    }
                    sb.append(traceId);
                }
            }
        }
        return sb.toString();
    }

    public static void startUploadDiagnosis(Context context, int firstSimIndex, int lastSimIndex) {
        Log.v(TAG, "start to upload activation diagnosis infomations, simindex " + firstSimIndex + " to " + lastSimIndex);
        if (firstSimIndex > lastSimIndex) {
            int tmp = firstSimIndex;
            firstSimIndex = lastSimIndex;
            lastSimIndex = tmp;
        }
        for (int i = firstSimIndex; i <= lastSimIndex; i++) {
            if (sDiagnosisInfoMaps.containsKey(Integer.valueOf(i))) {
                DiagnosisInfo info = (DiagnosisInfo) sDiagnosisInfoMaps.get(Integer.valueOf(i));
                if (info == null) {
                    Log.v(TAG, "no diagnosis infor for simindex " + i + ", bail.");
                } else {
                    Bundle bundle = info.getBundle();
                    if (bundle == null) {
                        Log.v(TAG, "no diagnosis infor for simindex " + i + ", bail.");
                    } else {
                        Intent intent = new Intent(context, ActivateUploadDiagnosisService.class);
                        intent.putExtra(KEY_EXTRA_DATA, bundle);
                        context.startService(intent);
                    }
                }
            } else {
                Log.v(TAG, "no diagnosis infor for simindex " + i + ", bail.");
            }
        }
    }

    private static Map<String, String> bundleToRequestParam(Bundle bundle) {
        return new EasyMap().easyPut(KEY_CATEGORY, bundle.getString(KEY_CATEGORY)).easyPut(KEY_HASHED_SIM_OPERATOR, bundle.getString(KEY_HASHED_SIM_OPERATOR)).easyPut(KEY_HASHED_NETWORK_OPERATOR, bundle.getString(KEY_HASHED_NETWORK_OPERATOR)).easyPut(KEY_HASHED_OPERATOR_PATTERN, bundle.getString(KEY_HASHED_OPERATOR_PATTERN)).easyPut(KEY_HASHED_IMEI, bundle.getString(KEY_HASHED_IMEI)).easyPut(KEY_HASHED_CONTENT, bundle.getString(KEY_HASHED_CONTENT)).easyPut(KEY_GATEWAY, bundle.getString(KEY_GATEWAY)).easyPut(KEY_HASHED_LAC, bundle.getString(KEY_HASHED_LAC)).easyPut(KEY_HASHED_CID, bundle.getString(KEY_HASHED_CID)).easyPut(USER_AGENT, bundle.getString(USER_AGENT)).easyPut(KEY_SIM_INDEX, bundle.getString(KEY_SIM_INDEX)).easyPut(KEY_TIMESTAMP, bundle.getString(KEY_TIMESTAMP)).easyPut(KEY_TRACEID, bundle.getString(KEY_TRACEID));
    }

    public ActivateUploadDiagnosisService() {
        super(TAG);
    }

    protected void onHandleIntent(Intent intent) {
        if (intent == null) {
            Log.w(TAG, "null intent received. Bail.");
            return;
        }
        Bundle bundle = intent.getBundleExtra(KEY_EXTRA_DATA);
        if (bundle == null || bundle.isEmpty()) {
            Log.w(TAG, "bundle is null or empty, Bail.");
            return;
        }
        try {
            StringContent mapContent = SimpleRequest.postAsString(URL, bundleToRequestParam(bundle), null, true);
            if (mapContent == null) {
                Log.e(TAG, "failed to get response from server when upload diagnosis info.");
                return;
            }
            String body = mapContent.getBody();
            if ("OK".equals(body)) {
                Log.v(TAG, "diagnosis info uploaded");
            } else {
                Log.v(TAG, "shen uploading diagnosis info, server returns unexpected error code : " + body);
            }
        } catch (AccessDeniedException e) {
            Log.e(TAG, "failed to upload diagnosis info.", e);
        } catch (AuthenticationFailureException e2) {
            Log.e(TAG, "failed to upload diagnosis info.", e2);
        } catch (IOException e3) {
            Log.e(TAG, "failed to upload diagnosis info.", e3);
        }
    }
}
