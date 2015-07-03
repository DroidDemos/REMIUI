package com.xiaomi.passport.ui;

import android.app.Fragment;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.AsyncTask.Status;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.xiaomi.account.R;
import com.xiaomi.accounts.AccountManager;
import com.xiaomi.accountsdk.account.XMPassport;
import com.xiaomi.accountsdk.request.InvalidResponseException;
import com.xiaomi.miui.analyticstracker.AnalyticsTracker;
import com.xiaomi.passport.Constants;
import com.xiaomi.passport.data.RegAccountInfo;
import com.xiaomi.passport.data.SetupData;
import com.xiaomi.passport.ui.SimpleDialogFragment.AlertDialogFragmentBuilder;
import com.xiaomi.passport.utils.AccountHelper;
import com.xiaomi.passport.utils.AnalyticsHelper;
import com.xiaomi.passport.utils.SysHelper;
import java.io.IOException;

public class AccountUnactivatedFragment extends Fragment implements OnClickListener {
    private static final boolean DEBUG = true;
    public static final String EXTRA_ACCOUNT = "account";
    private static final String TAG = "AccountUnactivatedFragment";
    private RegAccountInfo mAccountInfo;
    private AnalyticsTracker mAnalyticsTracker;
    private Button mBtnGoToEmail;
    private Button mBtnResendEmail;
    private Button mBtnVerifiedEmail;
    private CheckEmailActivateTask mCheckEmailActivateTask;
    private String mEmail;
    private View mEmailPanel;
    private TextView mEmailView;
    private TextView mNotActivitedNotice;
    private boolean mOnSetupGuide;
    private String mPackageName;
    private String mPwd;
    private CountDownTimer mResendEmailTimer;
    private SendActivateEmailTask mSendActivateEmailTask;
    private View mSmsPanel;
    private Button mbtnRemoveAccount;

    private class CheckEmailActivateTask extends AsyncTask<Void, Void, Boolean> {
        private final String mEmail;

        private CheckEmailActivateTask(String email) {
            this.mEmail = email;
        }

        protected Boolean doInBackground(Void... params) {
            try {
                return Boolean.valueOf(XMPassport.checkEmailAvailability(this.mEmail));
            } catch (IOException e) {
                e.printStackTrace();
                return Boolean.valueOf(AccountUnactivatedFragment.DEBUG);
            } catch (InvalidResponseException e2) {
                e2.printStackTrace();
                return Boolean.valueOf(AccountUnactivatedFragment.DEBUG);
            }
        }

        protected void onPreExecute() {
            AnalyticsHelper.trackEvent(AccountUnactivatedFragment.this.mAnalyticsTracker, AnalyticsHelper.REG_BY_EMAIL_CONFIRM_START);
            AccountUnactivatedFragment.this.mBtnVerifiedEmail.setEnabled(false);
        }

        protected void onPostExecute(Boolean emailAvailable) {
            if (emailAvailable.booleanValue()) {
                AnalyticsHelper.trackEvent(AccountUnactivatedFragment.this.mAnalyticsTracker, AnalyticsHelper.REG_BY_EMAIL_CONFIRM_FAIL);
                AccountUnactivatedFragment.this.mNotActivitedNotice.setVisibility(0);
                AlphaAnimation alp = new AlphaAnimation(1.0f, 0.0f);
                alp.setDuration(4000);
                AccountUnactivatedFragment.this.mNotActivitedNotice.setAnimation(alp);
                alp.setAnimationListener(new AnimationListener() {
                    public void onAnimationStart(Animation animation) {
                    }

                    public void onAnimationRepeat(Animation animation) {
                    }

                    public void onAnimationEnd(Animation animation) {
                        AccountUnactivatedFragment.this.mNotActivitedNotice.setVisibility(4);
                        AccountUnactivatedFragment.this.mBtnVerifiedEmail.setEnabled(AccountUnactivatedFragment.DEBUG);
                    }
                });
                return;
            }
            AnalyticsHelper.trackEvent(AccountUnactivatedFragment.this.mAnalyticsTracker, AnalyticsHelper.REG_BY_EMAIL_CONFIRM_SUCCESS);
            AccountRegSuccessFragment f = new AccountRegSuccessFragment();
            Bundle args = new Bundle();
            args.putInt(AccountRegSuccessFragment.ARGS_REG_TYPE, 1);
            args.putString(AccountUnactivatedFragment.EXTRA_ACCOUNT, this.mEmail);
            args.putString(SmsAlertFragment.ARGS_PASSWORD, AccountUnactivatedFragment.this.mPwd);
            args.putString(AccountManager.KEY_ANDROID_PACKAGE_NAME, AccountUnactivatedFragment.this.mPackageName);
            args.putBoolean(Constants.EXTRA_SHOW_SKIP_LOGIN, AccountUnactivatedFragment.this.mOnSetupGuide);
            f.setArguments(args);
            SysHelper.replaceToFragment(AccountUnactivatedFragment.this.getActivity(), f, AccountUnactivatedFragment.DEBUG, ((ViewGroup) AccountUnactivatedFragment.this.getView().getParent()).getId());
        }
    }

    private class SendActivateEmailTask extends AsyncTask<Void, Void, Integer> {
        private static final int RESULT_IO_ERROR = 1;
        private static final int RESULT_OK = 0;
        private static final int RESULT_SERVER_ERROR = 2;
        private final String mEmail;
        private final String mUserId;

        private SendActivateEmailTask(String userId, String email) {
            this.mUserId = userId;
            this.mEmail = email;
        }

        protected Integer doInBackground(Void... params) {
            try {
                AccountHelper.sendActivateEmail(this.mUserId, this.mEmail);
                return Integer.valueOf(RESULT_OK);
            } catch (IOException e) {
                e.printStackTrace();
                return Integer.valueOf(RESULT_IO_ERROR);
            } catch (InvalidResponseException e2) {
                e2.printStackTrace();
                return Integer.valueOf(RESULT_SERVER_ERROR);
            }
        }

        protected void onPreExecute() {
            AnalyticsHelper.trackEvent(AccountUnactivatedFragment.this.mAnalyticsTracker, AnalyticsHelper.REG_BY_EMAIL_RESEND_START);
            AccountUnactivatedFragment.this.mBtnResendEmail.setEnabled(false);
        }

        protected void onPostExecute(Integer result) {
            switch (result.intValue()) {
                case RESULT_OK /*0*/:
                    AnalyticsHelper.trackEvent(AccountUnactivatedFragment.this.mAnalyticsTracker, AnalyticsHelper.REG_BY_EMAIL_RESEND_SUCCESS);
                    AccountUnactivatedFragment.this.mAccountInfo.setLastActivateTime(System.currentTimeMillis());
                    AccountUnactivatedFragment.this.startCountDown(Constants.RESEND_ACTIVATE_EMAIL_INTERVAL);
                    return;
                default:
                    AnalyticsHelper.trackEvent(AccountUnactivatedFragment.this.mAnalyticsTracker, AnalyticsHelper.REG_BY_EMAIL_RESEND_FAIL);
                    Toast.makeText(AccountUnactivatedFragment.this.getActivity(), R.string.passport_failed_to_send_activate_email, RESULT_OK).show();
                    AccountUnactivatedFragment.this.mBtnResendEmail.setEnabled(AccountUnactivatedFragment.DEBUG);
                    return;
            }
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.passport_account_unactivated, container, false);
        this.mBtnResendEmail = (Button) v.findViewById(R.id.btn_resend_email);
        this.mBtnResendEmail.setOnClickListener(this);
        this.mBtnVerifiedEmail = (Button) v.findViewById(R.id.btn_verify_email);
        this.mBtnVerifiedEmail.setOnClickListener(this);
        this.mBtnGoToEmail = (Button) v.findViewById(R.id.btn_goto_email);
        this.mNotActivitedNotice = (TextView) v.findViewById(R.id.tv_account_not_activate);
        this.mEmailPanel = v.findViewById(R.id.activate_email_panel);
        this.mSmsPanel = v.findViewById(R.id.activate_sms_panel);
        this.mEmailView = (TextView) v.findViewById(R.id.tv_email);
        Bundle args = getArguments();
        this.mOnSetupGuide = args.getBoolean(Constants.EXTRA_SHOW_SKIP_LOGIN, false);
        this.mPackageName = args.getString(AccountManager.KEY_ANDROID_PACKAGE_NAME);
        if (this.mOnSetupGuide) {
            this.mBtnGoToEmail.setVisibility(8);
        }
        this.mBtnGoToEmail.setOnClickListener(this);
        this.mbtnRemoveAccount = (Button) v.findViewById(R.id.btn_remove_account);
        this.mbtnRemoveAccount.setOnClickListener(this);
        this.mAnalyticsTracker = AnalyticsTracker.getInstance();
        this.mAnalyticsTracker.startSession(getActivity());
        return v;
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.mAccountInfo = SetupData.getRegAccount();
        if (this.mAccountInfo == null) {
            Log.w(TAG, "no account contains");
            finishActivity();
            return;
        }
        String regType = this.mAccountInfo.getRegType();
        this.mEmail = this.mAccountInfo.getEmail();
        this.mPwd = this.mAccountInfo.getPassword();
        if (Constants.REG_TYPE_EMAIL.equals(regType) && this.mEmail != null) {
            this.mSmsPanel.setVisibility(8);
            this.mEmailView.setText(getString(R.string.passport_active_email_visit, new Object[]{this.mEmail}));
        } else if (Constants.REG_TYPE_PHONE_NUMBER.equals(regType)) {
            this.mEmailPanel.setVisibility(8);
            this.mBtnResendEmail.setVisibility(8);
            this.mBtnVerifiedEmail.setVisibility(8);
        } else {
            Log.w(TAG, "unknown reg type: " + regType);
            finishActivity();
            return;
        }
        long expired = System.currentTimeMillis() - this.mAccountInfo.getLastActivateTime();
        Log.d(TAG, "time left:" + (Constants.RESEND_ACTIVATE_EMAIL_INTERVAL - expired));
        if (expired < Constants.RESEND_ACTIVATE_EMAIL_INTERVAL && expired > 0) {
            this.mBtnResendEmail.setEnabled(false);
            startCountDown(Constants.RESEND_ACTIVATE_EMAIL_INTERVAL - expired);
        }
    }

    public void onDestroyView() {
        super.onDestroyView();
        if (this.mResendEmailTimer != null) {
            this.mResendEmailTimer.cancel();
            this.mResendEmailTimer = null;
        }
        if (this.mSendActivateEmailTask != null) {
            this.mSendActivateEmailTask.cancel(DEBUG);
            this.mSendActivateEmailTask = null;
        }
        if (this.mCheckEmailActivateTask != null) {
            this.mCheckEmailActivateTask.cancel(DEBUG);
            this.mCheckEmailActivateTask = null;
        }
        this.mAnalyticsTracker.endSession();
    }

    public void onClick(View v) {
        if (this.mBtnGoToEmail == v) {
            SysHelper.goToEmailPage(getActivity(), "http://www." + this.mEmail.substring(this.mEmail.indexOf("@") + 1));
        } else if (this.mbtnRemoveAccount == v) {
            AlertDialogFragmentBuilder b = new AlertDialogFragmentBuilder(1);
            b.setTitle(getString(R.string.passport_delete_account));
            b.setMessage(getString(R.string.passport_remove_unactivated_account_notice));
            SimpleDialogFragment f = b.create();
            f.setPositiveButton(R.string.passport_remove_confirm, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    SetupData.setRegAccount(null);
                    AccountUnactivatedFragment.this.startActivity(SysHelper.buildPreviousActivityIntent(AccountUnactivatedFragment.this.getActivity(), AccountUnactivatedFragment.this.getActivity().getIntent(), Constants.ACTION_WELCOME));
                    AccountUnactivatedFragment.this.getActivity().finish();
                }
            });
            f.setNegativeButton(17039360, null);
            f.show(getFragmentManager(), null);
        } else if (this.mBtnResendEmail == v) {
            String userId = this.mAccountInfo.getUserId();
            String email = this.mEmail;
            if (this.mSendActivateEmailTask == null || Status.FINISHED == this.mSendActivateEmailTask.getStatus()) {
                this.mSendActivateEmailTask = new SendActivateEmailTask(userId, email);
                this.mSendActivateEmailTask.execute(new Void[0]);
            }
        } else if (this.mBtnVerifiedEmail != v) {
        } else {
            if (this.mCheckEmailActivateTask == null || Status.FINISHED == this.mCheckEmailActivateTask.getStatus()) {
                this.mCheckEmailActivateTask = new CheckEmailActivateTask(this.mEmail);
                this.mCheckEmailActivateTask.execute(new Void[0]);
            }
        }
    }

    private void finishActivity() {
        getActivity().finish();
    }

    private void startCountDown(long timeLeft) {
        this.mResendEmailTimer = new CountDownTimer(timeLeft, 1000) {
            public void onTick(long millisUntilFinished) {
                AccountUnactivatedFragment.this.mBtnResendEmail.setText(AccountUnactivatedFragment.this.getString(R.string.passport_resend_active_email) + " (" + (millisUntilFinished / 1000) + ")");
            }

            public void onFinish() {
                AccountUnactivatedFragment.this.mBtnResendEmail.setText(R.string.passport_resend_active_email);
                AccountUnactivatedFragment.this.mBtnResendEmail.setEnabled(AccountUnactivatedFragment.DEBUG);
            }
        }.start();
    }
}
