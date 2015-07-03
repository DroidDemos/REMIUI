package com.xiaomi.passport.ui;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import com.xiaomi.account.R;
import com.xiaomi.miui.analyticstracker.AnalyticsTracker;
import com.xiaomi.passport.Constants;

public class RegTask extends AsyncTask<String, Void, Integer> {
    public static final int ERROR_EMAIL_USED = 9;
    public static final int ERROR_INVALID_DEV_ID = 6;
    public static final int ERROR_NETWORK = 2;
    public static final int ERROR_PHONE_ERROR = 8;
    public static final int ERROR_PHONE_USED = 1;
    public static final int ERROR_REQUIRE_CAPTCHA = 10;
    public static final int ERROR_SERVER = 3;
    public static final int ERROR_SIM_NOT_READY = 4;
    public static final int ERROR_UNKNOWN = 5;
    public static final int ERROR_VERIFY_CODE = 7;
    protected Activity mActivity;
    protected AnalyticsTracker mAnalyticsTracker;
    protected String mPackageName;
    protected Runnable mRegSuccessRunnable;

    protected RegTask(Runnable regSuccessRunnable, Activity activity, String packageName, AnalyticsTracker analyticsTracker) {
        this.mRegSuccessRunnable = regSuccessRunnable;
        this.mPackageName = packageName;
        this.mAnalyticsTracker = analyticsTracker;
        this.mActivity = activity;
    }

    protected void onPreExecute() {
        this.mActivity.showDialog(ERROR_SERVER);
    }

    protected Integer doInBackground(String... params) {
        return Integer.valueOf(-1);
    }

    protected void onPostExecute(Integer result) {
        this.mActivity.dismissDialog(ERROR_SERVER);
        if (result != null) {
            if (result.intValue() != -1 || this.mRegSuccessRunnable == null) {
                handleRegFailed(this.mActivity, this.mPackageName, this.mAnalyticsTracker, result.intValue());
            } else {
                this.mRegSuccessRunnable.run();
            }
        }
    }

    protected void handleRegFailed(Activity activity, String packageName, AnalyticsTracker analyticsTracker, int error) {
        String reason;
        switch (error) {
            case ERROR_PHONE_USED /*1*/:
                reason = activity.getString(R.string.passport_error_dup_phone);
                break;
            case ERROR_NETWORK /*2*/:
                reason = activity.getString(R.string.passport_error_network);
                break;
            case ERROR_SERVER /*3*/:
                reason = activity.getString(R.string.passport_error_server);
                break;
            case ERROR_SIM_NOT_READY /*4*/:
                reason = activity.getString(R.string.passport_error_sim_not_ready);
                break;
            case ERROR_INVALID_DEV_ID /*6*/:
                reason = activity.getString(R.string.passport_error_invalid_dev_id);
                break;
            case ERROR_VERIFY_CODE /*7*/:
                reason = activity.getString(R.string.passport_error_verify_code);
                break;
            case ERROR_PHONE_ERROR /*8*/:
                reason = activity.getString(R.string.passport_error_phone_error);
                break;
            case ERROR_EMAIL_USED /*9*/:
                reason = activity.getString(R.string.passport_error_dup_email);
                break;
            default:
                reason = activity.getString(R.string.passport_error_unknown);
                break;
        }
        Bundle args = new Bundle();
        args.putString("reason", reason);
        args.putInt(Constants.KEY_RESULT, error);
        activity.showDialog(ERROR_SIM_NOT_READY, args);
    }
}
