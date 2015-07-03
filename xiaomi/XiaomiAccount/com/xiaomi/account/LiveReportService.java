package com.xiaomi.account;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import com.xiaomi.accountsdk.utils.MiuiLiveReport;
import java.util.HashMap;

public class LiveReportService extends IntentService {
    private static final String EVENT_LOGIN_FAILURE = "login_fail";
    private static final String EXTRA_DATA = "extra_data";
    private static final String EXTRA_EVENT = "extra_event";
    public static final String LIVE_REPORT_PAKAGENAME = "com.xiaomi.account";
    private static final String TAG = "LiveReportService";
    private static final String TYPE_XIAOMI_ACCOUNT = "miui_account";

    public static void reportLoginError(Context context, boolean byPwd, String reason, Throwable t) {
        Bundle data = new Bundle();
        data.putString("reason", reason);
        data.putString("login_type", byPwd ? "pwd" : "passtoken");
        data.putString("error", t == null ? null : t.getMessage());
        Intent intent = new Intent(context, LiveReportService.class);
        intent.putExtra(EXTRA_DATA, data);
        intent.putExtra(EXTRA_EVENT, EVENT_LOGIN_FAILURE);
        intent.setPackage(LIVE_REPORT_PAKAGENAME);
        context.startService(intent);
    }

    public LiveReportService() {
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
            Log.v(TAG, "result of live report for " + event + ":" + MiuiLiveReport.report(this, TYPE_XIAOMI_ACCOUNT, dataMap));
        }
    }
}
