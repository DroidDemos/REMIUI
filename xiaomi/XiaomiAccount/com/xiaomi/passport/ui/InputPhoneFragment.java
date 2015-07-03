package com.xiaomi.passport.ui;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.TextView;
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
import com.xiaomi.passport.utils.PhoneNumUtil;
import com.xiaomi.passport.utils.PhoneNumUtil.CountryPhoneNumData;
import com.xiaomi.passport.utils.SysHelper;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class InputPhoneFragment extends Fragment implements OnClickListener {
    private static final String AREA_ISO_DEFAULT = "CN";
    private static final boolean DEBUG = true;
    private static final int REQUEST_CODE_FOREIGN_LOGIN = 1;
    private static final String TAG = "InputPhoneFragment";
    private AnalyticsTracker mAnalyticsTracker;
    private TextView mAreaCodeView;
    private CountryPhoneNumData mCountryPhoneNumData;
    private TextView mEmailRegisterView;
    private final ExecutorService mExecutorService;
    private GetVerifyCodeTask mGetVerifyCodeTask;
    private CheckBox mLicenseCheckBox;
    private Map<String, Object> mLogParams;
    private Button mNextBtn;
    private String mPackageName;
    private EditText mPhoneView;

    private class GetVerifyCodeTask extends RegTask {
        protected GetVerifyCodeTask(Activity activity, String packageName, AnalyticsTracker analyticsTracker, Runnable regSuccessRunnable) {
            super(regSuccessRunnable, activity, packageName, analyticsTracker);
        }

        protected Integer doInBackground(String... params) {
            int result = -1;
            try {
                AccountHelper.getRegisterVerifyCode(params[0]);
                AnalyticsHelper.trackEvent(this.mAnalyticsTracker, AnalyticsHelper.REG_GET_VERIFY_CODE_SUCCESS);
            } catch (IOException e) {
                Log.e(InputPhoneFragment.TAG, "GetVerifyCodeTask", e);
                result = 2;
                AnalyticsHelper.trackEvent(this.mAnalyticsTracker, AnalyticsHelper.REG_GET_VERIFY_CODE_ERROR_NETWORK);
            } catch (AccessDeniedException e2) {
                Log.e(InputPhoneFragment.TAG, "GetVerifyCodeTask", e2);
                result = 5;
            } catch (AuthenticationFailureException e3) {
                Log.e(InputPhoneFragment.TAG, "GetVerifyCodeTask", e3);
                result = 5;
            } catch (InvalidResponseException e4) {
                Log.e(InputPhoneFragment.TAG, "GetVerifyCodeTask", e4);
                result = 5;
            } catch (RegisteredPhoneException e5) {
                Log.e(InputPhoneFragment.TAG, "GetVerifyCodeTask", e5);
                AnalyticsHelper.trackEvent(this.mAnalyticsTracker, AnalyticsHelper.REG_GET_VERIFY_CODE_ERROR_PHONE_USED);
                result = InputPhoneFragment.REQUEST_CODE_FOREIGN_LOGIN;
            }
            return Integer.valueOf(result);
        }
    }

    public InputPhoneFragment() {
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
        View v = inflater.inflate(R.layout.passport_input_phone, container, false);
        this.mAreaCodeView = (TextView) v.findViewById(R.id.tv_area_code);
        this.mEmailRegisterView = (TextView) v.findViewById(R.id.tv_register_by_email);
        this.mNextBtn = (Button) v.findViewById(R.id.btn_phone_next);
        this.mPhoneView = (EditText) v.findViewById(R.id.ev_phone);
        this.mLicenseCheckBox = (CheckBox) v.findViewById(R.id.license);
        SysHelper.setLicense(getActivity(), this.mLicenseCheckBox);
        this.mLicenseCheckBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                InputPhoneFragment.this.mNextBtn.setEnabled(isChecked);
            }
        });
        this.mPhoneView.requestFocus();
        this.mPhoneView.setOnFocusChangeListener(new OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    InputPhoneFragment.this.getPhoneNumber();
                }
            }
        });
        if (Build.IS_TABLET) {
            ((Button) v.findViewById(R.id.btn_cancel)).setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                    FriendlyFragmentUtils.popUpFragment(InputPhoneFragment.this);
                }
            });
        }
        this.mAreaCodeView.setOnClickListener(this);
        this.mEmailRegisterView.setOnClickListener(this);
        this.mNextBtn.setOnClickListener(this);
        PhoneNumUtil.initializeCountryPhoneData(getActivity());
        refreshViewStateByISO(AREA_ISO_DEFAULT);
        return v;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_CODE_FOREIGN_LOGIN /*1*/:
                if (-1 == resultCode) {
                    refreshViewStateByISO(data.getStringExtra(AreaCodePickerFragment.KEY_COUNTRY_ISO));
                    return;
                }
                return;
            default:
                return;
        }
    }

    public void onClick(View v) {
        if (v == this.mNextBtn) {
            if (!TextUtils.isEmpty(getPhoneNumber())) {
                onGetVerifyCode(getPhoneNumber());
            }
        } else if (v == this.mAreaCodeView) {
            Intent intent = new Intent(Constants.ACTION_AREA_CODE);
            intent.setPackage(getActivity().getPackageName());
            startActivityForResult(intent, REQUEST_CODE_FOREIGN_LOGIN);
        } else if (v == this.mEmailRegisterView) {
            SysHelper.replaceToFragment(getActivity(), new InputEmailFragment(), false, ((ViewGroup) getView().getParent()).getId());
        }
    }

    public void onResume() {
        super.onResume();
        SysHelper.displaySoftInputIfNeed(getActivity(), this.mPhoneView, DEBUG);
    }

    public void onPause() {
        super.onPause();
        SysHelper.displaySoftInputIfNeed(getActivity(), this.mPhoneView, false);
    }

    public void onStart() {
        AnalyticsHelper.trackEvent(this.mAnalyticsTracker, AnalyticsHelper.USER_ENTER_INPUT_PHONE_PAGE, this.mLogParams);
        super.onStart();
    }

    public void onDestroy() {
        this.mExecutorService.shutdownNow();
        this.mAnalyticsTracker.endSession();
        super.onDestroy();
    }

    private String getPhoneNumber() {
        String phonenumber = this.mPhoneView.getText().toString();
        if (TextUtils.isEmpty(phonenumber)) {
            this.mPhoneView.setError(getString(R.string.passport_error_empty_phone_num));
            return null;
        }
        if (this.mCountryPhoneNumData != null) {
            phonenumber = PhoneNumUtil.checkNumber(phonenumber, this.mCountryPhoneNumData);
            if (TextUtils.isEmpty(phonenumber)) {
                this.mPhoneView.setError(getString(R.string.passport_wrong_phone_number_format));
                return null;
            }
        }
        return phonenumber;
    }

    private void refreshViewStateByISO(String areaISO) {
        this.mCountryPhoneNumData = PhoneNumUtil.getCounrtyPhoneDataFromIso(areaISO);
        this.mAreaCodeView.setText(this.mCountryPhoneNumData.countryName + "(+" + this.mCountryPhoneNumData.countryCode + ")");
    }

    public void onGetVerifyCode(final String phoneNumber) {
        if (this.mGetVerifyCodeTask != null) {
            this.mGetVerifyCodeTask.cancel(DEBUG);
        }
        AnalyticsHelper.trackEvent(this.mAnalyticsTracker, AnalyticsHelper.REG_GET_VERIFY_CODE_START);
        this.mGetVerifyCodeTask = new GetVerifyCodeTask(getActivity(), this.mPackageName, this.mAnalyticsTracker, new Runnable() {
            public void run() {
                InputVerifyCodeFragment inputVerifyCodeFragment = new InputVerifyCodeFragment();
                Bundle data = new Bundle();
                data.putString("phone", phoneNumber);
                inputVerifyCodeFragment.setArguments(data);
                SysHelper.replaceToFragment(InputPhoneFragment.this.getActivity(), inputVerifyCodeFragment, false, ((ViewGroup) InputPhoneFragment.this.getView().getParent()).getId());
            }
        });
        GetVerifyCodeTask getVerifyCodeTask = this.mGetVerifyCodeTask;
        String[] strArr = new String[REQUEST_CODE_FOREIGN_LOGIN];
        strArr[0] = phoneNumber;
        getVerifyCodeTask.execute(strArr);
    }
}
