package com.xiaomi.passport.ui;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ImageView;
import com.xiaomi.account.R;
import com.xiaomi.accounts.AccountManager;
import com.xiaomi.accountsdk.account.XMPassport;
import com.xiaomi.accountsdk.account.exception.NeedCaptchaException;
import com.xiaomi.accountsdk.request.InvalidResponseException;
import com.xiaomi.miui.analyticstracker.AnalyticsTracker;
import com.xiaomi.passport.Build;
import com.xiaomi.passport.Constants;
import com.xiaomi.passport.data.RegAccountInfo;
import com.xiaomi.passport.data.SetupData;
import com.xiaomi.passport.ui.CaptchaDialogController.CaptchaInterface;
import com.xiaomi.passport.utils.AccountHelper;
import com.xiaomi.passport.utils.AnalyticsHelper;
import com.xiaomi.passport.utils.FriendlyFragmentUtils;
import com.xiaomi.passport.utils.SysHelper;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class InputEmailFragment extends Fragment implements OnClickListener, CaptchaInterface {
    public static final String KEY_REG_EMAIL_USED = "key_reg_email_used";
    private static final String TAG = "InputEmailFragment";
    private AnalyticsTracker mAnalyticsTracker;
    private CaptchaDialogController mCaptchaDialogController;
    private Future<Bundle> mCheckFieldsTask;
    private Button mConfirmBtn;
    private EditText mEmailView;
    private ExecutorService mExecutorService;
    private CheckBox mLicenseCheckBox;
    private Map<String, Object> mLogParams;
    private String mPackageName;
    private EditText mPasswordView;
    private RegByEmailTask mRegByEmailTask;
    private boolean mShowPassword;
    private ImageView mShowPwdImageView;

    private class CheckEmailCallable implements Callable<Bundle> {
        private String email;
        private String password;

        private CheckEmailCallable(String email, String password) {
            this.email = email;
            this.password = password;
        }

        private boolean CheckEmailAndUpdateBundle(Bundle bundle) throws Exception {
            if (TextUtils.isEmpty(this.email)) {
                bundle.putInt(Constants.KEY_RESULT, 2);
                InputEmailFragment.this.setErrorMsgOnUi(InputEmailFragment.this.mEmailView, InputEmailFragment.this.getString(R.string.passport_error_empty_email));
            } else if (Patterns.EMAIL_ADDRESS.matcher(this.email).matches()) {
                try {
                    if (XMPassport.checkEmailAvailability(this.email)) {
                        return true;
                    }
                    AnalyticsHelper.trackEvent(InputEmailFragment.this.mAnalyticsTracker, AnalyticsHelper.REG_BY_EMAIL_ERROR_EMAIL_USED);
                    bundle.putInt(Constants.KEY_RESULT, 1);
                    bundle.putInt(InputEmailFragment.KEY_REG_EMAIL_USED, 9);
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                    bundle.putInt(Constants.KEY_RESULT, 1);
                    InputEmailFragment.this.setErrorMsgOnUi(InputEmailFragment.this.mEmailView, InputEmailFragment.this.getString(R.string.passport_error_network));
                } catch (InvalidResponseException ire) {
                    ire.printStackTrace();
                    bundle.putInt(Constants.KEY_RESULT, 1);
                    InputEmailFragment.this.setErrorMsgOnUi(InputEmailFragment.this.mEmailView, InputEmailFragment.this.getString(R.string.passport_error_server));
                }
            } else {
                bundle.putInt(Constants.KEY_RESULT, 2);
                InputEmailFragment.this.setErrorMsgOnUi(InputEmailFragment.this.mEmailView, InputEmailFragment.this.getString(R.string.passport_error_email));
            }
            return false;
        }

        public Bundle call() throws Exception {
            InputEmailFragment.this.setErrorMsgOnUi(InputEmailFragment.this.mEmailView, null);
            Bundle bundle = new Bundle();
            boolean emailOk = CheckEmailAndUpdateBundle(bundle);
            boolean passwordOk = InputEmailFragment.this.checkPassword(this.password);
            if (emailOk && passwordOk) {
                bundle.putInt(Constants.KEY_RESULT, -1);
                bundle.putString(Constants.KEY_EMAIL, this.email);
                bundle.putString(Constants.KEY_DATA, this.password);
            } else if (!passwordOk) {
                bundle.putInt(Constants.KEY_RESULT, 2);
            }
            View viewToFocusNext = null;
            if (emailOk && !passwordOk) {
                viewToFocusNext = InputEmailFragment.this.mPasswordView;
            } else if (!emailOk && passwordOk) {
                viewToFocusNext = InputEmailFragment.this.mEmailView;
            }
            if (viewToFocusNext != null) {
                InputEmailFragment.this.requestFocusOnUi(viewToFocusNext);
            }
            return bundle;
        }
    }

    private class RegByEmailTask extends RegTask {
        private final String captcha;
        private final CaptchaInterface captchaInterface;
        private final String email;
        private final String ick;
        private final String password;

        private RegByEmailTask(String email, String password, CaptchaDialogController captchaDialogController, Runnable regSuccessRunnable, Activity activity, String packageName, AnalyticsTracker analyticsTracker) {
            super(regSuccessRunnable, activity, packageName, analyticsTracker);
            this.email = email;
            this.password = password;
            this.captcha = captchaDialogController.getCaptchaCode();
            this.ick = captchaDialogController.getIck();
            this.captchaInterface = captchaDialogController.getCaptchaInterface();
        }

        protected Integer doInBackground(String... params) {
            try {
                SetupData.setRegAccount(new RegAccountInfo(AccountHelper.regByEmail(this.email, this.password, this.captcha, this.ick), this.password, Constants.REG_TYPE_EMAIL, this.email));
                AnalyticsHelper.trackEvent(this.mAnalyticsTracker, AnalyticsHelper.REG_BY_EMAIL_SUCCESS);
                return Integer.valueOf(-1);
            } catch (IOException e) {
                AnalyticsHelper.trackEvent(this.mAnalyticsTracker, AnalyticsHelper.REG_BY_EMAIL_ERROR_NETWORK);
                Log.e(InputEmailFragment.TAG, "RegByEmailTask error", e);
                return Integer.valueOf(2);
            } catch (InvalidResponseException e2) {
                Log.e(InputEmailFragment.TAG, "RegByEmailTask error", e2);
                return Integer.valueOf(3);
            } catch (NeedCaptchaException e3) {
                Log.e(InputEmailFragment.TAG, "RegByEmailTask error", e3);
                AnalyticsHelper.trackEvent(this.mAnalyticsTracker, AnalyticsHelper.REG_BY_EMAIL_ERROR_NEED_CAPTCHA);
                return Integer.valueOf(10);
            }
        }

        protected void onPostExecute(Integer result) {
            if (result.intValue() == 10) {
                this.captchaInterface.onCaptchaRequired();
                super.onPostExecute(null);
                return;
            }
            this.captchaInterface.onCaptchaFinished();
            super.onPostExecute(result);
        }
    }

    public InputEmailFragment() {
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

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.mExecutorService = Executors.newSingleThreadExecutor();
        this.mCaptchaDialogController = new CaptchaDialogController(getActivity(), this);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.passport_input_email, container, false);
        this.mEmailView = (EditText) v.findViewById(R.id.email);
        this.mEmailView.requestFocus();
        this.mConfirmBtn = (Button) v.findViewById(R.id.btn_password_confirm);
        this.mPasswordView = (EditText) v.findViewById(R.id.ev_password);
        this.mShowPwdImageView = (ImageView) v.findViewById(R.id.show_password_img);
        this.mLicenseCheckBox = (CheckBox) v.findViewById(R.id.license);
        SysHelper.setLicense(getActivity(), this.mLicenseCheckBox);
        this.mLicenseCheckBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                InputEmailFragment.this.mConfirmBtn.setEnabled(isChecked);
            }
        });
        this.mPasswordView.setOnFocusChangeListener(new OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    InputEmailFragment.this.checkPassword();
                }
            }
        });
        if (Build.IS_TABLET) {
            ((Button) v.findViewById(R.id.btn_cancel)).setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                    FriendlyFragmentUtils.popUpFragment(InputEmailFragment.this);
                }
            });
        }
        this.mShowPwdImageView.setOnClickListener(this);
        this.mConfirmBtn.setOnClickListener(this);
        this.mShowPassword = false;
        updateShowPasswordState();
        return v;
    }

    public void onClick(View v) {
        if (v == this.mShowPwdImageView) {
            this.mShowPassword = !this.mShowPassword;
            updateShowPasswordState();
        } else if (v == this.mConfirmBtn) {
            onEmailRegister();
        }
    }

    public void onResume() {
        super.onResume();
        if (TextUtils.isEmpty(this.mEmailView.getText())) {
            SysHelper.displaySoftInputIfNeed(getActivity(), this.mEmailView, true);
            this.mEmailView.requestFocus();
            return;
        }
        SysHelper.displaySoftInputIfNeed(getActivity(), this.mPasswordView, true);
        this.mPasswordView.requestFocus();
    }

    public void onDestroy() {
        this.mExecutorService.shutdownNow();
        this.mAnalyticsTracker.endSession();
        if (this.mRegByEmailTask != null) {
            this.mRegByEmailTask.cancel(true);
        }
        super.onDestroy();
    }

    public void onStart() {
        AnalyticsHelper.trackEvent(this.mAnalyticsTracker, AnalyticsHelper.USER_ENTER_INPUT_EMAIL_PAGE, this.mLogParams);
        super.onStart();
    }

    public Future<Bundle> checkFields() {
        FutureTask<Bundle> checkEmailTask = new FutureTask(new CheckEmailCallable(this.mEmailView.getText().toString(), this.mPasswordView.getText().toString()));
        this.mExecutorService.submit(checkEmailTask);
        return checkEmailTask;
    }

    private void setErrorMsgOnUi(final EditText view, final String msg) {
        getActivity().runOnUiThread(new Runnable() {
            public void run() {
                view.setError(msg);
            }
        });
    }

    private void requestFocusOnUi(final View view) {
        getActivity().runOnUiThread(new Runnable() {
            public void run() {
                view.requestFocus();
            }
        });
    }

    private boolean checkPassword() {
        return checkPassword(this.mPasswordView.getText().toString());
    }

    private boolean checkPassword(String password) {
        int msgResId = -1;
        if (TextUtils.isEmpty(password)) {
            msgResId = R.string.passport_error_empty_pwd;
        } else if (!SysHelper.checkPasswordPattern(password)) {
            msgResId = R.string.passport_error_illegal_pwd;
        }
        if (msgResId == -1) {
            return true;
        }
        setErrorMsgOnUi(this.mPasswordView, getString(msgResId));
        return false;
    }

    private void updateShowPasswordState() {
        this.mPasswordView.setInputType(SysHelper.getEditViewInputType(this.mShowPassword));
        this.mPasswordView.setSelection(this.mPasswordView.getText().length());
        this.mShowPwdImageView.setImageResource(this.mShowPassword ? R.drawable.passport_password_show : R.drawable.passport_password_not_show);
    }

    public void onCaptchaFinished() {
        this.mCaptchaDialogController.dismiss();
    }

    public void onCaptchaRequired() {
        this.mCaptchaDialogController.triggerNewCaptchaTask();
    }

    public void onEmailRegister() {
        if (this.mCheckFieldsTask != null) {
            this.mCheckFieldsTask.cancel(true);
        }
        AnalyticsHelper.trackEvent(this.mAnalyticsTracker, AnalyticsHelper.REG_BY_EMAIL_START);
        this.mCheckFieldsTask = checkFields();
        try {
            final Bundle bundle = (Bundle) this.mCheckFieldsTask.get();
            getActivity().runOnUiThread(new Runnable() {
                public void run() {
                    Activity activity = InputEmailFragment.this.getActivity();
                    InputEmailFragment.this.getActivity();
                    ((InputMethodManager) activity.getSystemService("input_method")).hideSoftInputFromWindow(InputEmailFragment.this.getView().getWindowToken(), 0);
                    if (bundle.getInt(Constants.KEY_RESULT) == -1) {
                        InputEmailFragment.this.asyncRegByEmail(bundle.getString(Constants.KEY_EMAIL), bundle.getString(Constants.KEY_DATA), InputEmailFragment.this.mCaptchaDialogController, new Runnable() {
                            public void run() {
                                InputEmailFragment.this.startActivity(SysHelper.buildPreviousActivityIntent(InputEmailFragment.this.getActivity(), InputEmailFragment.this.getActivity().getIntent(), Constants.ACTION_WELCOME));
                                InputEmailFragment.this.getActivity().finish();
                            }
                        });
                    } else if (bundle.getInt(InputEmailFragment.KEY_REG_EMAIL_USED) == 9) {
                        Bundle args = new Bundle();
                        args.putString("reason", InputEmailFragment.this.getString(R.string.passport_error_dup_email));
                        args.putInt(Constants.KEY_RESULT, 9);
                        InputEmailFragment.this.getActivity().showDialog(4, args);
                    }
                }
            });
        } catch (InterruptedException e) {
            Log.e(TAG, "onEmailRegister error", e);
        } catch (ExecutionException e2) {
            Log.e(TAG, "onEmailRegister error", e2);
        }
    }

    public void tryCaptcha(String captcha, String ick) {
        onEmailRegister();
    }

    private void asyncRegByEmail(String email, String password, CaptchaDialogController captchaDialogController, Runnable onSuccess) {
        if (this.mRegByEmailTask != null) {
            this.mRegByEmailTask.cancel(true);
        }
        if (captchaDialogController.shouldForceNewCaptcha()) {
            captchaDialogController.triggerNewCaptchaTask();
            return;
        }
        this.mRegByEmailTask = new RegByEmailTask(email, password, captchaDialogController, onSuccess, getActivity(), this.mPackageName, this.mAnalyticsTracker);
        this.mRegByEmailTask.execute(new String[0]);
    }
}
