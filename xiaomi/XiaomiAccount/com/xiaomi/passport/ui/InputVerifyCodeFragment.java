package com.xiaomi.passport.ui;

import android.app.Activity;
import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.xiaomi.account.R;
import com.xiaomi.accounts.AccountManager;
import com.xiaomi.accountsdk.account.exception.RegisteredPhoneException;
import com.xiaomi.accountsdk.request.AccessDeniedException;
import com.xiaomi.accountsdk.request.AuthenticationFailureException;
import com.xiaomi.accountsdk.request.InvalidResponseException;
import com.xiaomi.miui.analyticstracker.AnalyticsTracker;
import com.xiaomi.passport.Build;
import com.xiaomi.passport.Constants;
import com.xiaomi.passport.utils.AccountHelper;
import com.xiaomi.passport.utils.AnalyticsHelper;
import com.xiaomi.passport.utils.FriendlyFragmentUtils;
import com.xiaomi.passport.utils.SysHelper;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class InputVerifyCodeFragment extends Fragment implements OnClickListener {
    private static final long COUNT_DOWN_TOTAL_TIME = 60000;
    private static final boolean DEBUG = true;
    private static final String TAG = "InputVerifyCodeFragment";
    private AnalyticsTracker mAnalyticsTracker;
    private final ExecutorService mExecutorService;
    private GetVerifyCodeTask mGetVerifyCodeTask;
    private TextView mGetVerifyCodeView;
    private Map<String, Object> mLogParams;
    private String mPackageName;
    private String mPhoneNumber;
    private CountDownTimer mReGetCountDownTimer;
    private TextView mSmsSendNotice;
    private Button mVerifyBtn;
    private VerifyCodeTask mVerifyCodeTask;
    private EditText mVerifyCodeView;

    private class GetVerifyCodeTask extends AsyncTask<Void, Void, Integer> {
        private static final int RESULT_CODE_ERROR = 1;
        private static final int RESULT_CODE_NETWORK_ERROR = 3;
        private static final int RESULT_CODE_PHONE_REGISTERED = 2;
        private static final int RESULT_CODE_SUCCESS = 0;

        private GetVerifyCodeTask() {
        }

        protected void onPostExecute(Integer result) {
            switch (result.intValue()) {
                case LicenseActivity.PRIVACY_POLICY /*0*/:
                    InputVerifyCodeFragment.this.startGetVerifyCodeTimer();
                    return;
                case RESULT_CODE_NETWORK_ERROR /*3*/:
                    Toast.makeText(InputVerifyCodeFragment.this.getActivity(), R.string.passport_error_network, 0).show();
                    return;
                default:
                    Log.d(InputVerifyCodeFragment.TAG, "GetVerifyCodeTask result is " + result);
                    return;
            }
        }

        protected Integer doInBackground(Void... params) {
            try {
                if (InputVerifyCodeFragment.this.mPhoneNumber == null) {
                    return Integer.valueOf(RESULT_CODE_ERROR);
                }
                AccountHelper.getRegisterVerifyCode(InputVerifyCodeFragment.this.mPhoneNumber);
                AnalyticsHelper.trackEvent(InputVerifyCodeFragment.this.mAnalyticsTracker, AnalyticsHelper.REG_REFRESH_VERIFY_CODE_SUCCESS);
                return Integer.valueOf(0);
            } catch (IOException e) {
                Log.e(InputVerifyCodeFragment.TAG, "GetVerifyCodeTask ", e);
                AnalyticsHelper.trackEvent(InputVerifyCodeFragment.this.mAnalyticsTracker, AnalyticsHelper.REG_REFRESH_VERIFY_CODE_ERROR_NETWORK);
                return Integer.valueOf(RESULT_CODE_NETWORK_ERROR);
            } catch (AccessDeniedException e2) {
                Log.e(InputVerifyCodeFragment.TAG, "GetVerifyCodeTask ", e2);
                return Integer.valueOf(RESULT_CODE_ERROR);
            } catch (AuthenticationFailureException e3) {
                Log.e(InputVerifyCodeFragment.TAG, "GetVerifyCodeTask ", e3);
                return Integer.valueOf(RESULT_CODE_ERROR);
            } catch (InvalidResponseException e4) {
                Log.e(InputVerifyCodeFragment.TAG, "GetVerifyCodeTask ", e4);
                return Integer.valueOf(RESULT_CODE_ERROR);
            } catch (RegisteredPhoneException e5) {
                Log.e(InputVerifyCodeFragment.TAG, "GetVerifyCodeTask ", e5);
                AnalyticsHelper.trackEvent(InputVerifyCodeFragment.this.mAnalyticsTracker, AnalyticsHelper.REG_REFRESH_VERIFY_CODE_ERROR_PHONE_USED);
                return Integer.valueOf(RESULT_CODE_PHONE_REGISTERED);
            }
        }
    }

    private class VerifyCodeTask extends RegTask {
        protected VerifyCodeTask(Activity activity, String packageName, AnalyticsTracker analyticsTracker, Runnable regSuccessRunnable) {
            super(regSuccessRunnable, activity, packageName, analyticsTracker);
        }

        protected Integer doInBackground(String... params) {
            int result = -1;
            try {
                AccountHelper.checkRegisterVerifyCode(params[0], params[1]);
                AnalyticsHelper.trackEvent(this.mAnalyticsTracker, AnalyticsHelper.REG_CHECK_VERIFY_CODE_SUCCESS);
            } catch (IOException e) {
                Log.e(InputVerifyCodeFragment.TAG, "VerifyCodeTask", e);
                AnalyticsHelper.trackEvent(this.mAnalyticsTracker, AnalyticsHelper.REG_CHECK_VERIFY_CODE_ERROR_NETWORK);
                result = 2;
            } catch (AccessDeniedException e2) {
                Log.e(InputVerifyCodeFragment.TAG, "VerifyCodeTask", e2);
                result = 7;
            } catch (AuthenticationFailureException e3) {
                Log.e(InputVerifyCodeFragment.TAG, "VerifyCodeTask", e3);
                result = 7;
            } catch (InvalidResponseException e4) {
                Log.e(InputVerifyCodeFragment.TAG, "VerifyCodeTask", e4);
                result = 7;
            }
            return Integer.valueOf(result);
        }
    }

    public InputVerifyCodeFragment() {
        this.mExecutorService = Executors.newSingleThreadExecutor();
        this.mLogParams = new HashMap();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            this.mPackageName = args.getString(AccountManager.KEY_ANDROID_PACKAGE_NAME);
        }
        this.mLogParams.put(AnalyticsHelper.PACKAGE_NAME, this.mPackageName);
        this.mAnalyticsTracker = AnalyticsTracker.getInstance();
        this.mAnalyticsTracker.startSession(getActivity());
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.passport_input_verify_code, container, false);
        this.mSmsSendNotice = (TextView) v.findViewById(R.id.sms_send_notice);
        this.mVerifyCodeView = (EditText) v.findViewById(R.id.ev_verify_code);
        this.mGetVerifyCodeView = (TextView) v.findViewById(R.id.btn_verify_code);
        this.mVerifyBtn = (Button) v.findViewById(R.id.btn_verify);
        this.mVerifyCodeView.requestFocus();
        this.mVerifyCodeView.setOnFocusChangeListener(new OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    InputVerifyCodeFragment.this.getVerifyCode();
                }
            }
        });
        this.mGetVerifyCodeView.setOnClickListener(this);
        this.mVerifyBtn.setOnClickListener(this);
        if (Build.IS_TABLET) {
            ((Button) v.findViewById(R.id.btn_cancel)).setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                    FriendlyFragmentUtils.popUpFragment(InputVerifyCodeFragment.this);
                }
            });
        }
        this.mPhoneNumber = getArguments().getString("phone");
        this.mSmsSendNotice.setText(String.format(getResources().getString(R.string.passport_reg_sms_send_prompt), new Object[]{this.mPhoneNumber}));
        startGetVerifyCodeTimer();
        return v;
    }

    public void onResume() {
        super.onResume();
        SysHelper.displaySoftInputIfNeed(getActivity(), this.mVerifyCodeView, DEBUG);
    }

    public void onStart() {
        AnalyticsHelper.trackEvent(this.mAnalyticsTracker, AnalyticsHelper.USER_ENTER_INPUT_VERIFY_CODE_PAGE, this.mLogParams);
        super.onStart();
    }

    public void onDestroyView() {
        super.onDestroyView();
        if (this.mReGetCountDownTimer != null) {
            this.mReGetCountDownTimer.cancel();
        }
        this.mReGetCountDownTimer = null;
        if (this.mGetVerifyCodeTask != null) {
            this.mGetVerifyCodeTask.cancel(DEBUG);
        }
        this.mGetVerifyCodeTask = null;
    }

    public void onDestroy() {
        this.mExecutorService.shutdownNow();
        this.mAnalyticsTracker.endSession();
        super.onDestroy();
    }

    public void onClick(View v) {
        if (v == this.mGetVerifyCodeView) {
            if (this.mGetVerifyCodeTask != null) {
                this.mGetVerifyCodeTask.cancel(DEBUG);
            }
            AnalyticsHelper.trackEvent(this.mAnalyticsTracker, AnalyticsHelper.REG_REFRESH_VERIFY_CODE_START);
            this.mGetVerifyCodeTask = new GetVerifyCodeTask();
            this.mGetVerifyCodeTask.execute(new Void[0]);
        } else if (v == this.mVerifyBtn && !TextUtils.isEmpty(getVerifyCode())) {
            onCheckVerifyCode(this.mPhoneNumber, getVerifyCode());
        }
    }

    private String getVerifyCode() {
        String verifyCode = this.mVerifyCodeView.getText().toString();
        if (!TextUtils.isEmpty(verifyCode)) {
            return verifyCode;
        }
        this.mVerifyCodeView.setError(getString(R.string.passport_error_empty_vcode));
        return null;
    }

    private void startGetVerifyCodeTimer() {
        if (this.mReGetCountDownTimer != null) {
            this.mReGetCountDownTimer.cancel();
        }
        this.mGetVerifyCodeView.setEnabled(false);
        this.mReGetCountDownTimer = new CountDownTimer(COUNT_DOWN_TOTAL_TIME, 1000) {
            public void onFinish() {
                InputVerifyCodeFragment.this.mGetVerifyCodeView.setText(InputVerifyCodeFragment.this.getString(R.string.passport_re_get_verify_code));
                InputVerifyCodeFragment.this.mGetVerifyCodeView.setEnabled(InputVerifyCodeFragment.DEBUG);
                InputVerifyCodeFragment.this.mReGetCountDownTimer = null;
            }

            public void onTick(long millisUntilFinished) {
                InputVerifyCodeFragment.this.mGetVerifyCodeView.setText(InputVerifyCodeFragment.this.getString(R.string.passport_getting_verify_code) + " (" + (millisUntilFinished / 1000) + ")");
            }
        }.start();
    }

    private void setErrorMsgOnUi(final EditText view, final String msg) {
        getActivity().runOnUiThread(new Runnable() {
            public void run() {
                view.setError(msg);
            }
        });
    }

    public void onCheckVerifyCode(final String phoneNumber, final String verifyCode) {
        if (this.mVerifyCodeTask != null) {
            this.mVerifyCodeTask.cancel(DEBUG);
        }
        AnalyticsHelper.trackEvent(this.mAnalyticsTracker, AnalyticsHelper.REG_CHECK_VERIFY_CODE_START);
        this.mVerifyCodeTask = new VerifyCodeTask(getActivity(), this.mPackageName, this.mAnalyticsTracker, new Runnable() {
            public void run() {
                Bundle data = new Bundle();
                data.putString("phone", phoneNumber);
                data.putString(Constants.KEY_VCODE, verifyCode);
                InputPasswordFragment passwordFragment = new InputPasswordFragment();
                passwordFragment.setArguments(data);
                SysHelper.replaceToFragment(InputVerifyCodeFragment.this.getActivity(), passwordFragment, false, ((ViewGroup) InputVerifyCodeFragment.this.getView().getParent()).getId());
            }
        });
        this.mVerifyCodeTask.execute(new String[]{phoneNumber, verifyCode});
    }
}
