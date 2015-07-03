package com.xiaomi.passport.ui;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import com.xiaomi.account.R;
import com.xiaomi.accounts.AccountManager;
import com.xiaomi.accountsdk.activate.ActivateStatusReceiver;
import com.xiaomi.accountsdk.request.InvalidResponseException;
import com.xiaomi.miui.analyticstracker.AnalyticsTracker;
import com.xiaomi.passport.Build;
import com.xiaomi.passport.Constants;
import com.xiaomi.passport.PassportExternal;
import com.xiaomi.passport.utils.AccountHelper;
import com.xiaomi.passport.utils.AnalyticsHelper;
import com.xiaomi.passport.utils.FriendlyFragmentUtils;
import com.xiaomi.passport.utils.SysHelper;
import com.xiaomi.passport.widget.AlertDialog;
import com.xiaomi.passport.widget.AlertDialog.Builder;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class InputPasswordFragment extends Fragment implements OnClickListener {
    private static final String TAG = "InputEmailFragment";
    private boolean isOtherPhone;
    private AnalyticsTracker mAnalyticsTracker;
    private Future<Bundle> mCheckFieldsTask;
    private Button mConfirmBtn;
    private CheckBox mLicenseCheckBox;
    private Map<String, Object> mLogParams;
    private TextView mOtherWaysRegisterView;
    private String mPackageName;
    private EditText mPasswordView;
    private String mPhoneNumber;
    private TextView mPhoneTextView;
    private RegByPhoneTask mRegByPhoneTask;
    private boolean mShowPassword;
    private ImageView mShowPwdImageView;
    private int mSlotIndexForRegister;
    private TextView mSmsAlertView;

    private class CheckPasswordCallable implements Callable<Bundle> {
        private CheckPasswordCallable() {
        }

        public Bundle call() throws Exception {
            Bundle bundle = new Bundle();
            String password = InputPasswordFragment.this.mPasswordView.getText().toString();
            if (TextUtils.isEmpty(password)) {
                InputPasswordFragment.this.mPasswordView.setError(InputPasswordFragment.this.getString(R.string.passport_error_empty_pwd));
                InputPasswordFragment.this.mPasswordView.requestFocus();
                bundle.putInt(Constants.KEY_RESULT, 2);
            } else if (SysHelper.checkPasswordPattern(password)) {
                bundle.putInt(Constants.KEY_RESULT, -1);
                bundle.putString(Constants.KEY_DATA, password);
            } else {
                InputPasswordFragment.this.mPasswordView.setError(InputPasswordFragment.this.getString(R.string.passport_error_illegal_pwd));
                InputPasswordFragment.this.mPasswordView.requestFocus();
                bundle.putInt(Constants.KEY_RESULT, 2);
            }
            return bundle;
        }
    }

    private class RegByPhoneTask extends RegTask {
        protected RegByPhoneTask(Runnable regSuccessRunnable, Activity activity, String packageName, AnalyticsTracker analyticsTracker) {
            super(regSuccessRunnable, activity, packageName, analyticsTracker);
        }

        protected void onPreExecute() {
            super.onPreExecute();
            InputPasswordFragment.this.getActivity().dismissDialog(2);
        }

        protected Integer doInBackground(String... params) {
            new HashMap().put(AnalyticsHelper.PACKAGE_NAME, this.mPackageName);
            try {
                AccountHelper.regByPhone(params[0], params[1], params[2]);
                AnalyticsHelper.trackEvent(this.mAnalyticsTracker, AnalyticsHelper.REG_BY_OTHER_PHONE_SUCCESS);
                return Integer.valueOf(-1);
            } catch (IOException e) {
                Log.e(InputPasswordFragment.TAG, "RegByPhoneTask error", e);
                return Integer.valueOf(2);
            } catch (InvalidResponseException e2) {
                Log.e(InputPasswordFragment.TAG, "RegByPhoneTask error", e2);
                return Integer.valueOf(3);
            }
        }
    }

    public InputPasswordFragment() {
        this.isOtherPhone = false;
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
        View v = inflater.inflate(R.layout.passport_input_password, container, false);
        this.mPasswordView = (EditText) v.findViewById(R.id.ev_password);
        this.mShowPwdImageView = (ImageView) v.findViewById(R.id.show_password_img);
        this.mConfirmBtn = (Button) v.findViewById(R.id.btn_password_confirm);
        this.mOtherWaysRegisterView = (TextView) v.findViewById(R.id.tv_register_by_other_ways);
        this.mSmsAlertView = (TextView) v.findViewById(R.id.sms_alert);
        this.mLicenseCheckBox = (CheckBox) v.findViewById(R.id.license);
        SysHelper.setLicense(getActivity(), this.mLicenseCheckBox);
        this.mLicenseCheckBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                InputPasswordFragment.this.mConfirmBtn.setEnabled(isChecked);
            }
        });
        this.mPasswordView.requestFocus();
        this.mPasswordView.setOnFocusChangeListener(new OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    InputPasswordFragment.this.checkPassword();
                }
            }
        });
        if (Build.IS_TABLET) {
            ((Button) v.findViewById(R.id.btn_cancel)).setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                    FriendlyFragmentUtils.popUpFragment(InputPasswordFragment.this);
                }
            });
        }
        this.mShowPwdImageView.setOnClickListener(this);
        this.mConfirmBtn.setOnClickListener(this);
        this.mOtherWaysRegisterView.setOnClickListener(this);
        this.mShowPassword = false;
        updateShowPasswordState();
        Bundle data = getArguments();
        if (data != null) {
            this.mPhoneNumber = data.getString("phone");
        }
        if (TextUtils.isEmpty(this.mPhoneNumber)) {
            int slotCount = PassportExternal.getPassportInterface().getSlotCount();
            for (int index = 0; index < slotCount; index++) {
                if (PassportExternal.getPassportInterface().isSimInserted(index)) {
                    setSlotIndexForRegister(index);
                    break;
                }
            }
            int simCount = PassportExternal.getPassportInterface().getSimCount();
            if (simCount > 1) {
                processMultiSimsForRegister(simCount, v);
            }
        } else {
            this.mPhoneTextView = (TextView) v.findViewById(R.id.ev_phone_notice);
            this.mPhoneTextView.setText(getString(R.string.passport_reg_using_other_phone_number_prompt) + this.mPhoneNumber);
            this.mConfirmBtn.setText(R.string.passport_completed);
            this.mSmsAlertView.setVisibility(8);
            this.mOtherWaysRegisterView.setVisibility(8);
            this.isOtherPhone = true;
        }
        return v;
    }

    private void setSlotIndexForRegister(int simIndex) {
        if (ActivateStatusReceiver.getActivateStatus(simIndex) == 1) {
            this.mSmsAlertView.setVisibility(0);
        }
        this.mSlotIndexForRegister = simIndex;
    }

    private String getSimInfoStr(int index) {
        String simOpetator = PassportExternal.getPassportInterface().getSimOperatorName(index);
        if (simOpetator == null) {
            simOpetator = "";
        }
        return String.format(getActivity().getResources().getString(R.string.passport_sim_index_info), new Object[]{Integer.valueOf(index + 1), simOpetator});
    }

    private void processMultiSimsForRegister(int simCount, View view) {
        Spinner simInfoView = (Spinner) view.findViewById(R.id.show_sim_info);
        simInfoView.setVisibility(0);
        String[] simIndexStr = new String[simCount];
        final int[] simIdToSlotId = new int[simCount];
        int index = 0;
        int slotCount = PassportExternal.getPassportInterface().getSlotCount();
        for (int i = 0; i < slotCount; i++) {
            if (PassportExternal.getPassportInterface().isSimInserted(i)) {
                simIndexStr[index] = getSimInfoStr(i);
                simIdToSlotId[index] = i;
                index++;
            }
        }
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter(getActivity(), 17367048, simIndexStr);
        adapter.setDropDownViewResource(R.layout.passport_simple_spinner_dropdown_item);
        simInfoView.setAdapter(adapter);
        simInfoView.setOnItemSelectedListener(new OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                InputPasswordFragment.this.setSlotIndexForRegister(simIdToSlotId[position]);
            }

            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

    public void onResume() {
        super.onResume();
        if (TextUtils.isEmpty(this.mPhoneNumber)) {
            getActivity().getWindow().setSoftInputMode(2);
        } else {
            SysHelper.displaySoftInputIfNeed(getActivity(), this.mPasswordView, true);
        }
    }

    public void onPause() {
        super.onPause();
        SysHelper.displaySoftInputIfNeed(getActivity(), this.mPasswordView, false);
    }

    public void onDestroy() {
        this.mAnalyticsTracker.endSession();
        super.onDestroy();
    }

    public void onStart() {
        AnalyticsHelper.trackEvent(this.mAnalyticsTracker, AnalyticsHelper.USER_ENTER_INPUT_PASSWORD_PAGE, this.mLogParams);
        super.onStart();
    }

    private void updateShowPasswordState() {
        this.mPasswordView.setInputType(SysHelper.getEditViewInputType(this.mShowPassword));
        this.mPasswordView.setSelection(this.mPasswordView.getText().length());
        this.mShowPwdImageView.setImageResource(this.mShowPassword ? R.drawable.passport_password_show : R.drawable.passport_password_not_show);
    }

    public Future<Bundle> checkFields() {
        FutureTask<Bundle> checkPasswordTask = new FutureTask(new CheckPasswordCallable());
        checkPasswordTask.run();
        return checkPasswordTask;
    }

    public String getPassword() {
        return this.mPasswordView.getText().toString();
    }

    public void onClick(View v) {
        boolean z = true;
        if (v == this.mShowPwdImageView) {
            if (this.mShowPassword) {
                z = false;
            }
            this.mShowPassword = z;
            updateShowPasswordState();
        } else if (v == this.mConfirmBtn) {
            if (!checkPassword()) {
                return;
            }
            if (this.isOtherPhone) {
                onOtherPhoneRegister();
            } else {
                onPhoneRegister();
            }
        } else if (v == this.mOtherWaysRegisterView) {
            processOtherWaysRegister();
        }
    }

    private void processOtherWaysRegister() {
        Activity activity = getActivity();
        Builder builder = new Builder(activity);
        View view = LayoutInflater.from(activity).inflate(R.layout.passport_reg_by_other_ways_dialog, null);
        builder.setView(view);
        builder.setTitle((int) R.string.passport_reg_type_other_ways_title);
        final AlertDialog dialog = builder.create();
        view.findViewById(R.id.reg_by_other_phone).setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                SysHelper.replaceToFragment(InputPasswordFragment.this.getActivity(), new InputPhoneFragment(), false, ((ViewGroup) InputPasswordFragment.this.getView().getParent()).getId());
                dialog.dismiss();
            }
        });
        view.findViewById(R.id.reg_by_email).setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                SysHelper.replaceToFragment(InputPasswordFragment.this.getActivity(), new InputEmailFragment(), false, ((ViewGroup) InputPasswordFragment.this.getView().getParent()).getId());
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private boolean checkPassword() {
        String password = this.mPasswordView.getText().toString();
        if (TextUtils.isEmpty(password)) {
            this.mPasswordView.setError(getString(R.string.passport_error_empty_pwd));
            return false;
        } else if (SysHelper.checkPasswordPattern(password)) {
            return true;
        } else {
            this.mPasswordView.setError(getString(R.string.passport_error_illegal_pwd));
            return false;
        }
    }

    private void showSmsAlertFragment(Bundle args) {
        SmsAlertFragment smsAlertFragment = new SmsAlertFragment();
        smsAlertFragment.setArguments(args);
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.add(smsAlertFragment, smsAlertFragment.getClass().getName());
        ft.commit();
    }

    public void onOtherPhoneRegister() {
        if (this.mCheckFieldsTask != null) {
            this.mCheckFieldsTask.cancel(true);
        }
        this.mCheckFieldsTask = checkFields();
        final Future<Bundle> future = this.mCheckFieldsTask;
        getActivity().showDialog(2);
        new Thread(new Runnable() {
            public void run() {
                final String pwd = InputPasswordFragment.this.getPassword();
                try {
                    final Bundle bundle = (Bundle) future.get();
                    AnalyticsHelper.trackEvent(InputPasswordFragment.this.mAnalyticsTracker, AnalyticsHelper.REG_BY_OTHER_PHONE_START);
                    InputPasswordFragment.this.getActivity().runOnUiThread(new Runnable() {
                        public void run() {
                            ((InputMethodManager) InputPasswordFragment.this.getActivity().getSystemService("input_method")).hideSoftInputFromWindow(InputPasswordFragment.this.getView().getWindowToken(), 0);
                            InputPasswordFragment.this.getActivity().dismissDialog(3);
                            if (bundle.getInt(Constants.KEY_RESULT) == -1) {
                                Bundle arg = InputPasswordFragment.this.getArguments();
                                if (arg != null) {
                                    final String phone = arg.getString("phone");
                                    InputPasswordFragment.this.asyncRegByPhone(phone, pwd, arg.getString(Constants.KEY_VCODE), new Runnable() {
                                        public void run() {
                                            AccountRegSuccessFragment f = new AccountRegSuccessFragment();
                                            Bundle args = new Bundle();
                                            args.putInt(AccountRegSuccessFragment.ARGS_REG_TYPE, 2);
                                            args.putString(AccountUnactivatedFragment.EXTRA_ACCOUNT, phone);
                                            args.putString(SmsAlertFragment.ARGS_PASSWORD, pwd);
                                            f.setArguments(args);
                                            SysHelper.replaceToFragment(InputPasswordFragment.this.getActivity(), f, true, ((ViewGroup) InputPasswordFragment.this.getView().getParent()).getId());
                                            InputPasswordFragment.this.getActivity().setResult(-1);
                                        }
                                    });
                                    return;
                                }
                                Log.w(InputPasswordFragment.TAG, "no argument found");
                            }
                        }
                    });
                } catch (InterruptedException e) {
                    Log.e(InputPasswordFragment.TAG, "onOtherPhoneRegister error", e);
                } catch (ExecutionException e2) {
                    Log.e(InputPasswordFragment.TAG, "onOtherPhoneRegister error", e2);
                }
            }
        }).start();
    }

    public void onPhoneRegister() {
        if (this.mCheckFieldsTask != null) {
            this.mCheckFieldsTask.cancel(true);
        }
        this.mCheckFieldsTask = checkFields();
        final Future<Bundle> future = this.mCheckFieldsTask;
        getActivity().showDialog(2);
        new Thread(new Runnable() {
            public void run() {
                ((InputMethodManager) InputPasswordFragment.this.getActivity().getSystemService("input_method")).hideSoftInputFromWindow(InputPasswordFragment.this.getView().getWindowToken(), 0);
                final String pwd = InputPasswordFragment.this.getPassword();
                try {
                    final Bundle bundle = (Bundle) future.get();
                    AnalyticsHelper.trackEvent(InputPasswordFragment.this.mAnalyticsTracker, AnalyticsHelper.REG_BY_PHONE_START);
                    InputPasswordFragment.this.getActivity().runOnUiThread(new Runnable() {
                        public void run() {
                            InputPasswordFragment.this.getActivity().dismissDialog(2);
                            if (bundle.getInt(Constants.KEY_RESULT) == -1) {
                                Bundle args = InputPasswordFragment.this.getActivity().getIntent().getExtras();
                                if (args == null) {
                                    args = new Bundle();
                                }
                                args.putString(SmsAlertFragment.ARGS_PASSWORD, pwd);
                                args.putInt(SmsAlertFragment.ARGS_SIM_INDEX, InputPasswordFragment.this.mSlotIndexForRegister);
                                InputPasswordFragment.this.showSmsAlertFragment(args);
                            }
                        }
                    });
                } catch (InterruptedException e) {
                    Log.e(InputPasswordFragment.TAG, "onPhoneRegister error", e);
                } catch (ExecutionException e2) {
                    Log.e(InputPasswordFragment.TAG, "onPhoneRegister error", e2);
                }
            }
        }).start();
    }

    private void asyncRegByPhone(String phone, String password, String vcode, Runnable onSuccess) {
        if (this.mRegByPhoneTask != null) {
            this.mRegByPhoneTask.cancel(true);
        }
        this.mRegByPhoneTask = new RegByPhoneTask(onSuccess, getActivity(), this.mPackageName, this.mAnalyticsTracker);
        this.mRegByPhoneTask.execute(new String[]{phone, password, vcode});
    }
}
