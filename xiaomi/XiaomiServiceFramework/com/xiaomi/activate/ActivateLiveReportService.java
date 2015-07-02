package com.xiaomi.activate;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import com.xiaomi.accountsdk.utils.MiuiLiveReport;
import java.util.HashMap;

public class ActivateLiveReportService extends IntentService {
    private static final String EVENT_ACTIVATE_FAILURE = "sms_activate_fail";
    private static final String EXTRA_DATA = "extra_data";
    private static final String EXTRA_EVENT = "extra_event";
    private static final String TAG = "ActivateLiveReportService.java";
    private static final String TYPE_XMSF = "miui_xmsf";

    public static class LiveReportInfo {
        private String activateName;
        private String details;
        private String errorReason;
        private String gateway;
        private String simOperatorPattern;

        public static class ErrorDetail {
            public static final String NO_PHONE_INFO_IN_SERVER = "no_phone_on_server";
            public static final String VKEY_EXPIRED = "vkey_expired";
        }

        public static class ErrorReason {
            public static final String CREATE_MX_ACCOUNT_FAIL = "v6_create_mx_account_fail";
            public static final String CREATE_XIAOMI_ACCOUNT_FAIL = "v6_create_xiaomi_account_fail";
            public static final String GET_PHONE_FAIL = "v6_get_phone_fail";
            public static final String GET_VKEY_FAIL = "v6_get_vkey_fail";
        }

        public void setErrorReason(String errorReason) {
            this.errorReason = errorReason;
        }

        public void setDetails(String details) {
            this.details = details;
        }

        public void setActivateName(String activateName) {
            this.activateName = activateName;
        }

        public void setGateway(String gateway) {
            this.gateway = gateway;
        }

        public void setSimOperatorPattern(String simOperatorPattern) {
            this.simOperatorPattern = simOperatorPattern;
        }

        public boolean isValidErrorInfo() {
            return (this.errorReason == null || this.errorReason.isEmpty()) ? false : true;
        }
    }

    public static void reportActivationFailure(Context context, LiveReportInfo info) {
        Log.v(TAG, "start to live report activation failures");
        if (info == null || !info.isValidErrorInfo()) {
            Log.w(TAG, "invalid activation error info, skipping Live Report.");
            return;
        }
        Bundle data = new Bundle();
        addToBundleIfNotEmpty(data, "reason", info.errorReason);
        addToBundleIfNotEmpty(data, "details", info.details);
        addToBundleIfNotEmpty(data, "activateName", info.activateName);
        addToBundleIfNotEmpty(data, "gateway", info.gateway);
        addToBundleIfNotEmpty(data, "simOperatorPattern", info.simOperatorPattern);
        Intent intent = new Intent(context, ActivateLiveReportService.class);
        intent.putExtra(EXTRA_DATA, data);
        intent.putExtra(EXTRA_EVENT, EVENT_ACTIVATE_FAILURE);
        context.startService(intent);
    }

    public ActivateLiveReportService() {
        super(TAG);
    }

    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            Bundle bundle = intent.getBundleExtra(EXTRA_DATA);
            if (bundle == null || bundle.isEmpty()) {
                Log.w(TAG, "bundle is null or empty, report ignored");
                return;
            }
            HashMap<String, String> dataMap = new HashMap();
            for (String key : bundle.keySet()) {
                dataMap.put(key, bundle.getString(key));
            }
            String event = intent.getStringExtra(EXTRA_EVENT);
            dataMap.put("evt", event);
            Log.v(TAG, "result of live report for " + event + ":" + MiuiLiveReport.report(this, TYPE_XMSF, dataMap));
        }
    }

    private static void addToBundleIfNotEmpty(Bundle bundle, String key, String value) {
        if (value != null && !value.isEmpty()) {
            bundle.putString(key, value);
        }
    }
}
