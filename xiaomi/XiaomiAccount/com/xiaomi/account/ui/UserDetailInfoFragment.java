package com.xiaomi.account.ui;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceScreen;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;
import com.xiaomi.account.R;
import com.xiaomi.account.XiaomiAccountTaskService;
import com.xiaomi.account.data.AsyncTaskResult;
import com.xiaomi.account.data.XiaomiUserProfile.Gender;
import com.xiaomi.account.utils.AnalyticsHelper;
import com.xiaomi.account.utils.SysHelper;
import com.xiaomi.accountsdk.request.AccessDeniedException;
import com.xiaomi.accountsdk.request.AuthenticationFailureException;
import com.xiaomi.accountsdk.request.CipherException;
import com.xiaomi.accountsdk.request.InvalidResponseException;
import com.xiaomi.passport.Constants;
import com.xiaomi.passport.ui.AccountUnactivatedFragment;
import java.io.IOException;
import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import miui.analytics.Analytics;
import miui.app.AlertDialog.Builder;
import miui.app.DatePickerDialog;
import miui.app.DatePickerDialog.OnDateSetListener;
import miui.preference.ValuePreference;
import miui.widget.DatePicker;

public class UserDetailInfoFragment extends PreferenceFragment {
    private static final String PREF_USER_AVATAR = "pref_micloud_avatar";
    private static final String PREF_USER_BIRTHDAY = "pref_micloud_user_birthday";
    private static final String PREF_USER_EMAIL = "pref_micloud_useremail";
    private static final String PREF_USER_GENDER = "pref_micloud_user_gender";
    private static final String PREF_USER_ID = "pref_micloud_userid";
    private static final String PREF_USER_NAME = "pref_micloud_username";
    private static final String PREF_USER_PHONE = "pref_micloud_userphone";
    private static final String TAG = "UserDetailInfoFragment";
    private Account mAccount;
    private Analytics mAnalytics;
    private IntentFilter mIntentFilter;
    private OnDateSetListener mOnDateSetListener;
    private ImageValuePreference mPrefUserAvatar;
    private ValuePreference mPrefUserBirthday;
    private ValuePreference mPrefUserEmail;
    private ValuePreference mPrefUserGender;
    private ValuePreference mPrefUserId;
    private ValuePreference mPrefUserName;
    private ValuePreference mPrefUserPhone;
    private BroadcastReceiver mReceiver;

    private class UploadUserInfoTask extends AsyncTask<Void, Void, Integer> {
        private String mDisplayValue;
        private ValuePreference mValuePreference;
        private HashMap<String, Object> userInfo;

        public UploadUserInfoTask(ValuePreference preference, String displayValue, String key, String value) {
            this.userInfo = new HashMap();
            this.userInfo.put(key, value);
            this.mValuePreference = preference;
            this.mDisplayValue = displayValue;
        }

        protected Integer doInBackground(Void... params) {
            int exceptionType = 0;
            for (int count = 0; count < 2; count++) {
                try {
                    exceptionType = SysHelper.uploadInfoToServer(UserDetailInfoFragment.this.getActivity(), this.userInfo, 1, Constants.PASSPORT_API_SID) ? 0 : 5;
                } catch (IOException e) {
                    Log.e(UserDetailInfoFragment.TAG, "UploadUserInfoTask error", e);
                    exceptionType = 2;
                } catch (AuthenticationFailureException e2) {
                    Log.e(UserDetailInfoFragment.TAG, "UploadUserInfoTask error", e2);
                    exceptionType = 1;
                } catch (AccessDeniedException e3) {
                    Log.e(UserDetailInfoFragment.TAG, "UploadUserInfoTask error", e3);
                    exceptionType = 4;
                } catch (InvalidResponseException e4) {
                    Log.e(UserDetailInfoFragment.TAG, "UploadUserInfoTask error", e4);
                    exceptionType = 3;
                } catch (CipherException e5) {
                    Log.e(UserDetailInfoFragment.TAG, "UploadUserInfoTask error", e5);
                    exceptionType = 3;
                }
            }
            return Integer.valueOf(exceptionType);
        }

        protected void onPostExecute(Integer result) {
            super.onPostExecute(result);
            AsyncTaskResult taskResult = new AsyncTaskResult(result.intValue());
            if (taskResult.hasException()) {
                Toast.makeText(UserDetailInfoFragment.this.getActivity(), taskResult.getExceptionReason(), 0).show();
            } else {
                this.mValuePreference.setValue(this.mDisplayValue);
            }
        }
    }

    public UserDetailInfoFragment() {
        this.mOnDateSetListener = new OnDateSetListener() {
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(year, monthOfYear, dayOfMonth);
                if (calendar.compareTo(Calendar.getInstance()) == 1) {
                    Toast.makeText(UserDetailInfoFragment.this.getActivity(), UserDetailInfoFragment.this.getString(R.string.account_error_user_birthday), 1).show();
                    return;
                }
                String birthday = new SimpleDateFormat(com.xiaomi.account.Constants.SIMPLE_DATE_FORMAT).format(calendar.getTime());
                new UploadUserInfoTask(UserDetailInfoFragment.this.mPrefUserBirthday, birthday, "birthday", birthday).execute(new Void[0]);
            }
        };
        this.mReceiver = new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                if (com.xiaomi.account.Constants.ACTION_XIAOMI_USER_PROFILE_CHANGED.equals(intent.getAction())) {
                    UserDetailInfoFragment.this.refreshUserInfo();
                }
            }
        };
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mAnalytics = Analytics.getInstance();
        this.mAnalytics.startSession(getActivity());
        AnalyticsHelper.trackEvent(this.mAnalytics, AnalyticsHelper.USER_ENTER_USER_DETAIL_INFO_PAGE);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        SysHelper.setMiuiActionBarTitle(getActivity(), getString(R.string.account_user_details));
        View v = inflater.inflate(R.layout.user_detail_info, container, false);
        addPreferencesFromResource(R.xml.user_detail_info_preference);
        this.mPrefUserEmail = (ValuePreference) findPreference(PREF_USER_EMAIL);
        this.mPrefUserPhone = (ValuePreference) findPreference(PREF_USER_PHONE);
        this.mPrefUserName = (ValuePreference) findPreference(PREF_USER_NAME);
        this.mPrefUserName.setShowRightArrow(true);
        this.mPrefUserAvatar = (ImageValuePreference) findPreference(PREF_USER_AVATAR);
        this.mPrefUserAvatar.setShowRightArrow(true);
        this.mPrefUserGender = (ValuePreference) findPreference(PREF_USER_GENDER);
        this.mPrefUserGender.setShowRightArrow(true);
        this.mPrefUserBirthday = (ValuePreference) findPreference(PREF_USER_BIRTHDAY);
        this.mPrefUserBirthday.setShowRightArrow(true);
        this.mPrefUserId = (ValuePreference) findPreference(PREF_USER_ID);
        return v;
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.mIntentFilter = new IntentFilter();
        this.mIntentFilter.addAction(com.xiaomi.account.Constants.ACTION_XIAOMI_USER_PROFILE_CHANGED);
        Bundle args = getArguments();
        if (args == null) {
            Log.w(TAG, "no extras");
            getActivity().finish();
            return;
        }
        this.mAccount = (Account) args.getParcelable(Constants.EXTRA_ACCOUNT);
        if (this.mAccount == null) {
            Log.w(TAG, "no Xiaomi account");
            getActivity().finish();
        }
    }

    public void onDestroy() {
        this.mAnalytics.endSession();
        super.onDestroy();
    }

    public void onResume() {
        super.onResume();
        if (this.mAccount != null) {
            getActivity().registerReceiver(this.mReceiver, this.mIntentFilter);
            refreshUserInfo();
            XiaomiAccountTaskService.startQueryUserData(getActivity());
        }
    }

    public void onPause() {
        getActivity().unregisterReceiver(this.mReceiver);
        super.onPause();
    }

    private void refreshUserInfo() {
        Account account = this.mAccount;
        if (account != null) {
            AccountManager am = (AccountManager) getActivity().getSystemService(AccountUnactivatedFragment.EXTRA_ACCOUNT);
            String email = am.getUserData(account, Constants.ACCOUNT_USER_EMAIL);
            if (TextUtils.isEmpty(email)) {
                email = getString(R.string.account_none);
            }
            this.mPrefUserEmail.setValue(email);
            String phone = am.getUserData(account, Constants.ACCOUNT_USER_PHONE);
            if (TextUtils.isEmpty(phone)) {
                phone = getString(R.string.account_none);
            }
            this.mPrefUserPhone.setValue(phone);
            String name = am.getUserData(account, Constants.ACCOUNT_USER_NAME);
            if (TextUtils.isEmpty(name)) {
                name = getString(R.string.account_none_user_name);
            }
            this.mPrefUserName.setValue(name);
            this.mPrefUserId.setValue(account.name);
            String gender = am.getUserData(account, com.xiaomi.account.Constants.ACCOUNT_USER_GENDER);
            if (TextUtils.isEmpty(gender)) {
                gender = getString(R.string.account_no_set);
            } else if (gender.equals("f")) {
                gender = getString(R.string.account_user_gender_female);
            } else {
                gender = getString(R.string.account_user_gender_male);
            }
            this.mPrefUserGender.setValue(gender);
            String birthday = am.getUserData(account, com.xiaomi.account.Constants.ACCOUNT_USER_BIRTHDAY);
            if (TextUtils.isEmpty(birthday)) {
                birthday = getString(R.string.account_no_set);
            }
            this.mPrefUserBirthday.setValue(birthday);
            Bitmap avatar = SysHelper.createPhoto(getActivity(), am.getUserData(account, Constants.ACCOUNT_AVATAR_FILE_NAME));
            if (avatar != null) {
                this.mPrefUserAvatar.setImageValueDrawable(new BitmapDrawable(avatar));
            }
        }
    }

    public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen, Preference preference) {
        String eventId = null;
        if (PREF_USER_NAME.equals(preference.getKey())) {
            eventId = AnalyticsHelper.ACCOUNT_SETTINGS_USER_NAME_UPDATE;
            showUserNameUpdateDialog();
        } else if (PREF_USER_GENDER.equals(preference.getKey())) {
            eventId = AnalyticsHelper.ACCOUNT_SETTINGS_USER_GENDER_UPDATE;
            showUserGenderUpdateDialog();
        } else if (PREF_USER_BIRTHDAY.equals(preference.getKey())) {
            eventId = AnalyticsHelper.ACCOUNT_SETTINGS_USER_BIRTHDAY_UPDATE;
            showUserBirthdayUpdateDialog();
        } else if (PREF_USER_AVATAR.equals(preference.getKey())) {
            eventId = AnalyticsHelper.ACCOUNT_SETTINGS_USER_AVATAR_UPDATE;
            Intent intent = new Intent(getActivity(), UserAvatarUpdateActivity.class);
            intent.setPackage(getActivity().getPackageName());
            startActivity(intent);
        }
        if (!TextUtils.isEmpty(eventId)) {
            AnalyticsHelper.trackEvent(this.mAnalytics, eventId);
        }
        return super.onPreferenceTreeClick(preferenceScreen, preference);
    }

    private void showUserNameUpdateDialog() {
        Builder builder = new Builder(getActivity());
        builder.setTitle(R.string.account_user_name_dialog_title);
        final EditText editText = new EditText(getActivity());
        editText.setText(this.mPrefUserName.getValue());
        editText.setSelection(editText.getText().length());
        builder.setView(editText);
        builder.setPositiveButton(R.string.confirm, new OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                String userName = editText.getText().toString();
                String errorMessage = null;
                if (TextUtils.isEmpty(userName)) {
                    errorMessage = UserDetailInfoFragment.this.getString(R.string.account_empty_user_name);
                } else if (userName.length() < 2) {
                    errorMessage = UserDetailInfoFragment.this.getString(R.string.account_error_shorter_user_name);
                } else if (userName.length() > 20) {
                    errorMessage = UserDetailInfoFragment.this.getString(R.string.account_error_longer_user_name);
                }
                if (TextUtils.isEmpty(errorMessage)) {
                    UserDetailInfoFragment.closeDialog(dialog, true);
                    new UploadUserInfoTask(UserDetailInfoFragment.this.mPrefUserName, userName, "userName", userName).execute(new Void[0]);
                    return;
                }
                editText.setError(errorMessage);
                UserDetailInfoFragment.closeDialog(dialog, false);
            }
        });
        builder.setNegativeButton(R.string.button_cancel, new OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                UserDetailInfoFragment.closeDialog(dialog, true);
            }
        });
        builder.create().show();
    }

    private static void closeDialog(DialogInterface dialog, boolean isClosed) {
        try {
            Field field = dialog.getClass().getSuperclass().getDeclaredField("mShowing");
            field.setAccessible(true);
            field.set(dialog, Boolean.valueOf(isClosed));
        } catch (Exception e) {
            Log.e(TAG, "closeDialog", e);
        }
    }

    private void showUserGenderUpdateDialog() {
        Builder builder = new Builder(getActivity());
        builder.setTitle(R.string.account_user_gender);
        final String[] genderStr = new String[]{getString(R.string.account_user_gender_male), getString(R.string.account_user_gender_female)};
        int index = -1;
        for (int i = 0; i < genderStr.length; i++) {
            if (this.mPrefUserGender.getValue().equals(genderStr[i])) {
                index = i;
                break;
            }
        }
        builder.setSingleChoiceItems(genderStr, index, new OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                new UploadUserInfoTask(UserDetailInfoFragment.this.mPrefUserGender, genderStr[which], "gender", (which == 0 ? Gender.MALE : Gender.FEMALE).getType()).execute(new Void[0]);
                dialog.dismiss();
            }
        });
        builder.create().show();
    }

    private void showUserBirthdayUpdateDialog() {
        Calendar c = Calendar.getInstance();
        CharSequence birthday = this.mPrefUserBirthday.getValue();
        if (!TextUtils.isEmpty(birthday)) {
            try {
                c.setTime(new SimpleDateFormat(com.xiaomi.account.Constants.SIMPLE_DATE_FORMAT).parse((String) birthday));
            } catch (ParseException e) {
                Log.e(TAG, "showUserBirthdayUpdateDialog", e);
            }
        }
        new DatePickerDialog(getActivity(), this.mOnDateSetListener, c.get(1), c.get(2), c.get(5)).show();
    }
}
