package com.xiaomi.passport.ui;

import android.app.Activity;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.xiaomi.accounts.AccountManager;
import com.xiaomi.accountsdk.activate.ActivateManager;
import com.xiaomi.accountsdk.activate.CloudServiceFailureException;
import com.xiaomi.accountsdk.activate.OperationCancelledException;
import com.xiaomi.miui.analyticstracker.AnalyticsTracker;
import com.xiaomi.passport.PassportExternal;
import com.xiaomi.passport.R;
import com.xiaomi.passport.utils.AccountHelper;
import com.xiaomi.passport.utils.AnalyticsHelper;
import com.xiaomi.passport.widget.AlertDialog;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SmsAlertFragment extends Fragment {
    private static final String ACTION_ACTIVATE_SIM = "com.xiaomi.action.PROMPT_ACTIVATE_SIM";
    public static final String ACTION_REGISTER_PROGRESS = "com.xiaomi.xmsf.action.REGISTER_PROGRESS";
    public static final String ARGS_PASSWORD = "password";
    public static final String ARGS_SIM_INDEX = "simindex";
    private static final int START_PROMPT_ACTIVATE = 100;
    private static final String TAG = "SmsAlertDialogFragment";
    private int mActivateMethod;
    private AnalyticsTracker mAnalytics;
    private Map<String, Object> mLogParams;
    private String mPackageName;
    private String mPhone;
    private String mPwd;
    private RegBySmsTask mRegBySmsTask;
    private int mSimIndex;
    private Runnable mSmsRegCallback;
    private String mUserId;

    private class RegBySmsTask extends RegTask {
        private ProgressDialog sendSmsProgressDialog;

        protected RegBySmsTask(Activity activity, String packageName, AnalyticsTracker analyticsTracker, Runnable regSuccessRunnable) {
            super(regSuccessRunnable, activity, packageName, analyticsTracker);
        }

        protected Integer doInBackground(String... params) {
            try {
                Bundle result = (Bundle) ActivateManager.get(SmsAlertFragment.this.getActivity()).blockingActivateSim(SmsAlertFragment.this.mSimIndex, SmsAlertFragment.this.mActivateMethod, SmsAlertFragment.this.mPhone, false, params[0]).getResult();
                AnalyticsHelper.trackEvent(this.mAnalyticsTracker, AnalyticsHelper.REG_BY_PHONE_SUCCESS);
                if (result == null) {
                    return Integer.valueOf(5);
                }
                SmsAlertFragment.this.mUserId = result.getString("activate_xiaomi_user_id");
                return Integer.valueOf(-1);
            } catch (IOException e) {
                Log.e(SmsAlertFragment.TAG, "RegBySmsTask error", e);
                AnalyticsHelper.trackEvent(this.mAnalyticsTracker, AnalyticsHelper.REG_BY_PHONE_ERROR_NETWORK);
                return Integer.valueOf(2);
            } catch (OperationCancelledException e2) {
                Log.e(SmsAlertFragment.TAG, "RegBySmsTask error", e2);
                return Integer.valueOf(5);
            } catch (CloudServiceFailureException e3) {
                Log.e(SmsAlertFragment.TAG, "RegBySmsTask error", e3);
                switch (e3.getErrorCode()) {
                    case AlertDialog.THEME_LIGHT /*3*/:
                        AnalyticsHelper.trackEvent(this.mAnalyticsTracker, AnalyticsHelper.REG_BY_PHONE_ERROR_SIM_ABSENT);
                        return Integer.valueOf(4);
                    case RegTask.ERROR_REQUIRE_CAPTCHA /*10*/:
                        AnalyticsHelper.trackEvent(this.mAnalyticsTracker, AnalyticsHelper.REG_BY_PHONE_ERROR_PHONE_USED);
                        return Integer.valueOf(1);
                    case R.styleable.Passport_AlphabetFastIndexer_overlayTextColor /*11*/:
                        AnalyticsHelper.trackEvent(this.mAnalyticsTracker, AnalyticsHelper.REG_BY_PHONE_ERROR_PHONE_ERROR);
                        return Integer.valueOf(8);
                    default:
                        return Integer.valueOf(5);
                }
            }
        }

        protected void onPreExecute() {
            this.sendSmsProgressDialog = new ProgressDialog(SmsAlertFragment.this.getActivity());
            this.sendSmsProgressDialog.setMessage(SmsAlertFragment.this.getString(com.xiaomi.account.R.string.passport_reging));
            this.sendSmsProgressDialog.setCancelable(false);
            this.sendSmsProgressDialog.getWindow().setGravity(80);
            this.sendSmsProgressDialog.show();
        }

        protected void onPostExecute(Integer result) {
            this.sendSmsProgressDialog.dismiss();
            this.sendSmsProgressDialog = null;
            if (result.intValue() == -1) {
                this.mRegSuccessRunnable.run();
            } else {
                handleRegFailed(this.mActivity, this.mPackageName, this.mAnalyticsTracker, result.intValue());
            }
        }
    }

    public SmsAlertFragment() {
        this.mLogParams = new HashMap();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            this.mPackageName = args.getString(AccountManager.KEY_ANDROID_PACKAGE_NAME);
            this.mPwd = args.getString(ARGS_PASSWORD);
            this.mLogParams.put(AnalyticsHelper.PACKAGE_NAME, this.mPackageName);
        }
        this.mAnalytics = AnalyticsTracker.getInstance();
        this.mAnalytics.startSession(getActivity());
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = new View(getActivity());
        view.setBackgroundColor(0);
        return view;
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        this.mSimIndex = getArguments().getInt(ARGS_SIM_INDEX);
        Intent intent = new Intent(ACTION_ACTIVATE_SIM);
        intent.setPackage(PassportExternal.getPassportInterface().getActivatePackageName());
        intent.putExtra("extra_sim_index", this.mSimIndex);
        intent.putExtra("extra_activate_reason", 2);
        intent.putExtra("extra_activate_source", "Xmsf_Registration");
        intent.putExtra("extra_activate_prompt_only", true);
        startActivityForResult(intent, START_PROMPT_ACTIVATE);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case START_PROMPT_ACTIVATE /*100*/:
                if (resultCode == -1) {
                    this.mActivateMethod = data.getIntExtra("extra_activate_method", 2);
                    this.mPhone = data.getStringExtra("extra_activate_phone");
                    asyncRegBySms(getArguments().getString(ARGS_PASSWORD));
                    return;
                }
                return;
            default:
                return;
        }
    }

    public void onDestroy() {
        if (this.mRegBySmsTask != null) {
            this.mRegBySmsTask.cancel(true);
        }
        this.mAnalytics.endSession();
        this.mSmsRegCallback = null;
        super.onDestroy();
    }

    public void onStart() {
        AnalyticsHelper.trackEvent(this.mAnalytics, AnalyticsHelper.USER_ENTER_SMS_ALERT_PAGE, this.mLogParams);
        super.onStart();
    }

    private void asyncRegBySms(String password) {
        if (this.mRegBySmsTask != null) {
            this.mRegBySmsTask.cancel(true);
        }
        if (this.mSmsRegCallback == null) {
            this.mSmsRegCallback = new Runnable() {
                public void run() {
                    if (!TextUtils.isEmpty(SmsAlertFragment.this.mUserId)) {
                        AccountHelper.navigateToAutoLogin(SmsAlertFragment.this.getActivity(), SmsAlertFragment.this.mUserId, SmsAlertFragment.this.mPwd, SmsAlertFragment.this.mPackageName);
                        SmsAlertFragment.this.getActivity().setResult(-1);
                        SmsAlertFragment.this.getActivity().finish();
                    }
                }
            };
        }
        this.mRegBySmsTask = new RegBySmsTask(getActivity(), this.mPackageName, this.mAnalytics, this.mSmsRegCallback);
        this.mRegBySmsTask.execute(new String[]{password});
    }

    public void setSmsRegCallback(Runnable smsRegCallback) {
        this.mSmsRegCallback = smsRegCallback;
    }
}
