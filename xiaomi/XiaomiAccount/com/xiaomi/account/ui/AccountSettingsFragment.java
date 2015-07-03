package com.xiaomi.account.ui;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.accounts.AuthenticatorException;
import android.accounts.OperationCanceledException;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SyncStatusObserver;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.preference.Preference;
import android.preference.PreferenceCategory;
import android.preference.PreferenceFragment;
import android.preference.PreferenceScreen;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView.LayoutParams;
import android.widget.Button;
import android.widget.ListView;
import com.mipay.sdk.Mipay;
import com.xiaomi.account.Constants;
import com.xiaomi.account.R;
import com.xiaomi.account.XiaomiAccountTaskService;
import com.xiaomi.account.data.SetupData;
import com.xiaomi.account.utils.AnalyticsHelper;
import com.xiaomi.account.utils.CloudHelper;
import com.xiaomi.account.utils.LogoutModel;
import com.xiaomi.account.utils.SysHelper;
import com.xiaomi.accountsdk.account.data.ExtendedAuthToken;
import com.xiaomi.accountsdk.utils.DeviceModelUtil;
import com.xiaomi.micloudsdk.exception.CloudServerException;
import com.xiaomi.passport.accountmanager.MiAccountManager;
import com.xiaomi.passport.ui.LicenseActivity;
import com.xiaomi.passport.ui.RegTask;
import com.xiaomi.passport.widget.AlertDialog;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import miui.analytics.Analytics;
import miui.cloud.sync.MiCloudSyncState;
import miui.cloud.sync.SyncStateHelper;
import miui.content.res.IconCustomizer;
import miui.external.SdkConstants.SdkReturnCode;
import miui.os.Build;
import miui.payment.PaymentManager;
import miui.preference.ValuePreference;
import org.apache.http.client.ClientProtocolException;

public class AccountSettingsFragment extends PreferenceFragment implements OnClickListener, IQueryBalance {
    private static final String ACTION_VIEW_CLOUD = "com.xiaomi.action.MICLOUD_MAIN";
    private static final String ACTION_VIEW_MI_SUPPORT_SERVICE = "com.miservice.action.VIEW_MISERVICE_ROOT";
    public static final String EXTRA_ACCOUNT = "account";
    private static final String MI_SUPPORT_SERVICE_PACKAGE_NAME = "com.xiaomi.miservice";
    private static final String PREF_BIND_DEVICES = "pref_bind_devices";
    private static final String PREF_CLOUD_SERVICE = "pref_cloud_service";
    private static final String PREF_MILI_SERVICE = "pref_mili_service";
    private static final String PREF_MIPAY_SERVICE = "pref_mipay_service";
    private static final String PREF_MI_SERVICE_SETTINGS = "pref_mi_service_settings";
    private static final String PREF_MI_SUPPORT_SERVICE = "pref_mi_support_service";
    private static final String PREF_SNS_BIND = "pref_sns_bind";
    private static final String PREF_XIAOMI_SERVICES = "pref_xiaomi_services";
    private static final String TAG = "AccountSettingsFragment";
    private Account mAccount;
    private Activity mActivity;
    private Analytics mAnalytics;
    private Button mButtonDeleteAccount;
    private final Handler mHandler;
    private IntentFilter mIntentFilter;
    private ListView mListView;
    private LogoutModel mLogoutModel;
    private Preference mPerfMiliCenter;
    private PreferenceCategory mPrefCategoryServices;
    private ValuePreference mPrefCloudService;
    private BroadcastReceiver mReceiver;
    private RefreshUserInfoInterface mRefreshUserInfoInterface;
    private Object mSyncChangeHandle;
    private SyncStatusObserver mSyncStatusObserver;
    private UpdateSyncStateTask mUpdateSyncStateTask;

    public interface RefreshUserInfoInterface {
        void onRefreshUserInfo();

        void setListView(ListView listView);
    }

    static /* synthetic */ class AnonymousClass4 {
        static final /* synthetic */ int[] $SwitchMap$miui$cloud$sync$MiCloudSyncState;

        static {
            $SwitchMap$miui$cloud$sync$MiCloudSyncState = new int[MiCloudSyncState.values().length];
            try {
                $SwitchMap$miui$cloud$sync$MiCloudSyncState[MiCloudSyncState.NO_NETWORK.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$miui$cloud$sync$MiCloudSyncState[MiCloudSyncState.SPACE_LOW_PERCENT.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$miui$cloud$sync$MiCloudSyncState[MiCloudSyncState.SPACE_FULL.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$miui$cloud$sync$MiCloudSyncState[MiCloudSyncState.MASTER_SYNC_TURNED_OFF.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$miui$cloud$sync$MiCloudSyncState[MiCloudSyncState.ALL_MICLOUD_SYNC_OFF.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$miui$cloud$sync$MiCloudSyncState[MiCloudSyncState.SYNC_PENDING.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                $SwitchMap$miui$cloud$sync$MiCloudSyncState[MiCloudSyncState.SYNC_SYNCING.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                $SwitchMap$miui$cloud$sync$MiCloudSyncState[MiCloudSyncState.SYNC_NEED_WIFI.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
            try {
                $SwitchMap$miui$cloud$sync$MiCloudSyncState[MiCloudSyncState.SYNC_OK_BUT_UNSYNCED_COUNT.ordinal()] = 9;
            } catch (NoSuchFieldError e9) {
            }
            try {
                $SwitchMap$miui$cloud$sync$MiCloudSyncState[MiCloudSyncState.LAST_SYNC_ERROR.ordinal()] = 10;
            } catch (NoSuchFieldError e10) {
            }
            try {
                $SwitchMap$miui$cloud$sync$MiCloudSyncState[MiCloudSyncState.SYNC_OK.ordinal()] = 11;
            } catch (NoSuchFieldError e11) {
            }
        }
    }

    private class GetMibiSignTask extends AsyncTask<Void, Void, String> {
        private String userName;

        private GetMibiSignTask(String userName) {
            this.userName = userName;
        }

        protected void onPostExecute(String result) {
            if (TextUtils.isEmpty(result)) {
                AccountSettingsFragment.this.onFinish(0, 0);
                return;
            }
            AccountSettingsFragment.this.setMibiSignValue(result);
            SysHelper.queryMibiBalance(AccountSettingsFragment.this.mActivity, AccountSettingsFragment.this, result);
        }

        protected String doInBackground(Void... params) {
            return getSignValue(this.userName);
        }

        private String getSignValue(String userId) {
            String value = null;
            Account account = AccountSettingsFragment.this.mAccount;
            if (account == null) {
                return null;
            }
            try {
                String token = SysHelper.getAuthToken(AccountManager.get(AccountSettingsFragment.this.mActivity), account, CloudHelper.MICLOUD_SID);
                if (TextUtils.isEmpty(token)) {
                    return null;
                }
                ExtendedAuthToken serviceToken = ExtendedAuthToken.parse(token);
                value = CloudHelper.getMipaySign(AccountSettingsFragment.this.mActivity, userId, serviceToken.authToken, serviceToken.security);
                return value;
            } catch (IllegalBlockSizeException e) {
                Log.e(AccountSettingsFragment.TAG, "error when get sign value ", e);
            } catch (BadPaddingException e2) {
                Log.e(AccountSettingsFragment.TAG, "error when get sign value ", e2);
            } catch (CloudServerException e3) {
                Log.e(AccountSettingsFragment.TAG, "error when get sign value ", e3);
            } catch (UnsupportedEncodingException e4) {
                Log.e(AccountSettingsFragment.TAG, "error when get sign value ", e4);
            } catch (ClientProtocolException e5) {
                Log.e(AccountSettingsFragment.TAG, "error when get sign value ", e5);
            } catch (IOException e6) {
                Log.e(AccountSettingsFragment.TAG, "error when get sign value ", e6);
            }
        }
    }

    private class StartMyDeviceTask extends AsyncTask<Void, Void, Void> {
        private StartMyDeviceTask() {
        }

        protected Void doInBackground(Void... params) {
            DeviceModelUtil.initializeDeviceModelData(AccountSettingsFragment.this.mActivity);
            return null;
        }

        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            AccountSettingsFragment.this.startActivity(new Intent(AccountSettingsFragment.this.mActivity, MyDeviceActivity.class));
        }
    }

    private class UpdateSyncStateTask extends AsyncTask<Void, Void, Void> {
        MiCloudSyncState mMiCloudSyncState;

        private UpdateSyncStateTask() {
        }

        protected Void doInBackground(Void... params) {
            this.mMiCloudSyncState = SyncStateHelper.getCurrentSyncState(AccountSettingsFragment.this.mActivity);
            return null;
        }

        protected void onPostExecute(Void result) {
            AccountSettingsFragment.this.refreshSyncingInfo(this.mMiCloudSyncState);
            AccountSettingsFragment.this.mUpdateSyncStateTask = null;
            super.onPostExecute(result);
        }
    }

    public AccountSettingsFragment() {
        this.mReceiver = new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                if ("com.xiaomi.action.XIAOMI_USER_INFO_CHANGED".equals(action)) {
                    if (AccountSettingsFragment.this.mRefreshUserInfoInterface != null) {
                        AccountSettingsFragment.this.mRefreshUserInfoInterface.onRefreshUserInfo();
                    }
                } else if (Constants.ACTION_MICLOUD_SPACE_INFO_CHANGED.equals(action)) {
                    if (intent.getLongExtra(Constants.EXTRA_MICLOUD_SPACE_STATUS, -1) >= 0) {
                        String spaceStatus = String.format(AccountSettingsFragment.this.getResources().getString(R.string.micloud_space_info), new Object[]{miui.cloud.util.SysHelper.getQuantityStringWithUnit(remainingSpace)});
                        if (AccountSettingsFragment.this.mPrefCloudService != null && !TextUtils.isEmpty(spaceStatus)) {
                            AccountSettingsFragment.this.mPrefCloudService.setValue(spaceStatus);
                        }
                    }
                } else if ("android.net.conn.CONNECTIVITY_CHANGE".equals(action) && SysHelper.isNetworkConnected(AccountSettingsFragment.this.getActivity())) {
                    XiaomiAccountTaskService.startQueryUserData(AccountSettingsFragment.this.getActivity());
                }
            }
        };
        this.mHandler = new Handler();
        this.mSyncStatusObserver = new SyncStatusObserver() {
            public void onStatusChanged(int which) {
                AccountSettingsFragment.this.mHandler.post(new Runnable() {
                    public void run() {
                        if (AccountSettingsFragment.this.isVisible()) {
                            AccountSettingsFragment.this.updateMiCloudSyncState();
                        }
                    }
                });
            }
        };
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mAnalytics = Analytics.getInstance();
        this.mAnalytics.startSession(getActivity());
        AnalyticsHelper.trackEvent(this.mAnalytics, AnalyticsHelper.USER_OPEN_ACCOUNT_SETTINGS);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.mActivity = getActivity();
        if (Build.IS_TABLET) {
            SysHelper.setMiuiActionBarTitle(this.mActivity, getString(R.string.micloud_account_name));
        }
        View rootView = inflater.inflate(R.layout.micloud_settings, container, false);
        addPreferencesFromResource(R.xml.micloud_settings_preference);
        this.mPrefCategoryServices = (PreferenceCategory) findPreference(PREF_XIAOMI_SERVICES);
        PackageManager pm = this.mActivity.getPackageManager();
        this.mPrefCloudService = (ValuePreference) findPreference(PREF_CLOUD_SERVICE);
        setPreferenceAppIcon(this.mPrefCloudService, "com.miui.cloudservice", pm);
        this.mPrefCloudService.setShowRightArrow(true);
        this.mPrefCloudService.setSummary(this.mActivity.getResources().getString(R.string.loading_micloud_sync_data));
        this.mPerfMiliCenter = findPreference(PREF_MILI_SERVICE);
        if (PaymentManager.get(this.mActivity).isMibiServiceDisabled()) {
            this.mPrefCategoryServices.removePreference(this.mPerfMiliCenter);
        } else {
            setPreferenceAppIcon(this.mPerfMiliCenter, "com.xiaomi.payment", pm);
            refreshUserMibiInfo(this.mActivity.getResources().getString(R.string.loading_mili_center));
        }
        Preference prefMiPayCenter = findPreference(PREF_MIPAY_SERVICE);
        if (Mipay.isMipayInstalled(this.mActivity)) {
            setPreferenceAppIcon(prefMiPayCenter, "com.mipay.wallet", pm);
        } else {
            this.mPrefCategoryServices.removePreference(prefMiPayCenter);
        }
        Preference prefMiService = findPreference(PREF_MI_SUPPORT_SERVICE);
        try {
            PackageInfo packageInfo = pm.getPackageInfo(MI_SUPPORT_SERVICE_PACKAGE_NAME, 0);
            setPreferenceAppIcon(prefMiService, MI_SUPPORT_SERVICE_PACKAGE_NAME, pm);
        } catch (NameNotFoundException e) {
            Log.d(TAG, "mi support service is not installed");
            this.mPrefCategoryServices.removePreference(prefMiService);
        }
        ValuePreference perfBindDevices = (ValuePreference) findPreference(PREF_BIND_DEVICES);
        perfBindDevices.setIcon(IconCustomizer.getCustomizedIcon(this.mActivity, "com.xiaomi.account.ui", PREF_BIND_DEVICES, getResources().getDrawable(R.drawable.bind_devices_ico)));
        perfBindDevices.setShowRightArrow(true);
        findPreference(PREF_SNS_BIND).setIcon(IconCustomizer.getCustomizedIcon(this.mActivity, "com.xiaomi.account.ui", PREF_SNS_BIND, getResources().getDrawable(R.drawable.bind_account_oauth_ico)));
        View footerView = inflater.inflate(R.layout.log_off_button, container, false);
        this.mButtonDeleteAccount = (Button) footerView.findViewById(R.id.btn_delete_account);
        this.mButtonDeleteAccount.setOnClickListener(this);
        if (Build.IS_TABLET) {
            Activity activity = this.mActivity;
            if (activity instanceof AccountSettingsActivity) {
                ((AccountSettingsActivity) activity).setUserInfoView(rootView);
            }
        }
        this.mListView = (ListView) rootView.findViewById(16908298);
        if (this.mListView != null) {
            footerView.setLayoutParams(new LayoutParams(-1, -2));
            this.mListView.addFooterView(footerView);
            if (this.mRefreshUserInfoInterface != null) {
                this.mRefreshUserInfoInterface.setListView(this.mListView);
            }
        }
        return rootView;
    }

    private void setPreferenceAppIcon(Preference preference, String packageName, PackageManager pm) {
        try {
            preference.setIcon(pm.getApplicationIcon(packageName));
        } catch (Exception e) {
            Log.e(TAG, "set icon for package " + packageName + e.getMessage());
        }
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle args = getArguments();
        if (args == null) {
            Log.e(TAG, "no args provided when starting intent. EXTRA_ACCOUNT needed.");
            finishActivity();
            return;
        }
        this.mAccount = (Account) args.getParcelable(EXTRA_ACCOUNT);
        if (this.mAccount == null) {
            Log.e(TAG, "no account contained");
            finishActivity();
            return;
        }
        if (this.mRefreshUserInfoInterface != null) {
            this.mRefreshUserInfoInterface.onRefreshUserInfo();
        }
        this.mIntentFilter = new IntentFilter();
        this.mIntentFilter.addAction("com.xiaomi.action.XIAOMI_USER_INFO_CHANGED");
        this.mIntentFilter.addAction(Constants.ACTION_MICLOUD_SPACE_INFO_CHANGED);
        this.mIntentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
    }

    public void onDestroy() {
        this.mAnalytics.endSession();
        if (this.mLogoutModel != null) {
            this.mLogoutModel.cancelLogout();
            this.mLogoutModel = null;
        }
        if (this.mUpdateSyncStateTask != null) {
            this.mUpdateSyncStateTask.cancel(true);
            this.mUpdateSyncStateTask = null;
        }
        super.onDestroy();
    }

    public void onResume() {
        super.onResume();
        AnalyticsHelper.trackEvent(this.mAnalytics, AnalyticsHelper.USER_ENTER_ACCOUNT_SETTINGS_PAGE);
        this.mActivity.registerReceiver(this.mReceiver, this.mIntentFilter);
        if (this.mAccount != null) {
            fetchMibiInfo();
            XiaomiAccountTaskService.startQueryMiCloudStatus(this.mActivity);
        }
        if (this.mPrefCloudService != null) {
            updateMiCloudSyncState();
            this.mSyncChangeHandle = ContentResolver.addStatusChangeListener(4, this.mSyncStatusObserver);
        }
    }

    private synchronized void updateMiCloudSyncState() {
        if (this.mUpdateSyncStateTask == null) {
            this.mUpdateSyncStateTask = new UpdateSyncStateTask();
            this.mUpdateSyncStateTask.execute(new Void[0]);
        }
    }

    public void onPause() {
        super.onPause();
        this.mActivity.unregisterReceiver(this.mReceiver);
        if (this.mSyncChangeHandle != null) {
            ContentResolver.removeStatusChangeListener(this.mSyncChangeHandle);
            this.mSyncChangeHandle = null;
        }
    }

    public boolean onPreferenceTreeClick(PreferenceScreen preferences, Preference preference) {
        String eventId = null;
        if (PREF_MILI_SERVICE.equals(preference.getKey())) {
            eventId = AnalyticsHelper.USER_ENTER_MIBI_FROM_ACCOUNT_SETTINGS;
            PaymentManager.get(this.mActivity).gotoMiliCenter(this.mActivity);
        } else if (PREF_CLOUD_SERVICE.equals(preference.getKey())) {
            eventId = AnalyticsHelper.USER_ENTER_CLOUD_SERVICE_FROM_ACCOUNT_SETTINGS;
            intent = new Intent(ACTION_VIEW_CLOUD);
            intent.putExtra(EXTRA_ACCOUNT, this.mAccount);
            startActivity(intent);
        } else if (PREF_SNS_BIND.equals(preference.getKey())) {
            eventId = AnalyticsHelper.USER_ENTER_BINDED_SNS_LIST_PAGE;
            startActivity(new Intent(this.mActivity, SnsListActivity.class));
        } else if (PREF_BIND_DEVICES.equals(preference.getKey())) {
            eventId = AnalyticsHelper.USER_ENTER_BINDED_DEVICES_PAGE;
            new StartMyDeviceTask().execute(new Void[0]);
        } else if (PREF_MIPAY_SERVICE.equals(preference.getKey())) {
            eventId = AnalyticsHelper.USER_ENTER_MIPAY_FROM_ACCOUNT_SETTINGS;
            Mipay.get(this.mActivity).showMipayCenter(this.mActivity);
        } else if (PREF_MI_SUPPORT_SERVICE.equals(preference.getKey())) {
            eventId = AnalyticsHelper.USER_ENTER_MISERVICE_FROM_ACCOUNT_SETTINGS;
            intent = new Intent(ACTION_VIEW_MI_SUPPORT_SERVICE);
            intent.setPackage(MI_SUPPORT_SERVICE_PACKAGE_NAME);
            startActivity(intent);
        }
        AnalyticsHelper.trackEvent(this.mAnalytics, eventId);
        return true;
    }

    public void onClick(View view) {
        if (this.mButtonDeleteAccount == view) {
            SetupData.setUserVerified(false);
            AnalyticsHelper.trackEvent(this.mAnalytics, AnalyticsHelper.USER_CLICK_ACCOUNT_SIGN_OUT_BTN);
            AccountManager.get(getActivity()).confirmCredentials(this.mAccount, null, this.mActivity, new AccountManagerCallback<Bundle>() {
                public void run(AccountManagerFuture<Bundle> future) {
                    try {
                        if (((Bundle) future.getResult()).getBoolean(MiAccountManager.KEY_BOOLEAN_RESULT)) {
                            AccountSettingsFragment.this.mActivity = AccountSettingsFragment.this.getActivity();
                            if (AccountSettingsFragment.this.mActivity != null && !AccountSettingsFragment.this.mActivity.isFinishing()) {
                                SetupData.setUserVerified(true);
                                AccountSettingsFragment.this.mLogoutModel = new LogoutModel(AccountSettingsFragment.this.mAccount);
                                AccountSettingsFragment.this.mLogoutModel.confirmRemoveAccount(AccountSettingsFragment.this.mActivity, AccountSettingsFragment.this.mButtonDeleteAccount, AccountSettingsFragment.this.mAnalytics);
                            }
                        }
                    } catch (OperationCanceledException e) {
                        e.printStackTrace();
                    } catch (AuthenticatorException e2) {
                        e2.printStackTrace();
                    } catch (IOException e3) {
                        e3.printStackTrace();
                    }
                }
            }, null);
        }
    }

    public void onFinish(int code, long balance) {
        switch (code) {
            case LicenseActivity.PRIVACY_POLICY /*0*/:
                refreshUserMibiInfo(this.mActivity.getResources().getString(R.string.failed_get_mibi_info));
                return;
            case SdkReturnCode.LOW_SDK_VERSION /*1*/:
                refreshUserMibiInfo(String.format(this.mActivity.getResources().getString(R.string.mibi_balance), new Object[]{String.valueOf(((double) balance) / 100.0d)}));
                return;
            case AlertDialog.THEME_DARK /*2*/:
                refreshUserMibiInfo(this.mActivity.getResources().getString(R.string.hide_mibi_info));
                return;
            case AlertDialog.THEME_LIGHT /*3*/:
                setMibiSignValue("");
                return;
            default:
                return;
        }
    }

    private void setMibiSignValue(String value) {
        if (value != null) {
            AccountManager.get(this.mActivity).setUserData(this.mAccount, Constants.ACCOUNT_MIBI_SIGN, value);
        }
    }

    private void refreshUserMibiInfo(String value) {
        if (this.mPerfMiliCenter != null) {
            this.mPerfMiliCenter.setSummary(value);
        }
    }

    private void refreshSyncingInfo(MiCloudSyncState syncState) {
        String syncInfo = "";
        switch (AnonymousClass4.$SwitchMap$miui$cloud$sync$MiCloudSyncState[syncState.ordinal()]) {
            case SdkReturnCode.LOW_SDK_VERSION /*1*/:
            case AlertDialog.THEME_DARK /*2*/:
            case AlertDialog.THEME_LIGHT /*3*/:
            case AlertDialog.THEME_DARK_EDIT /*4*/:
            case AlertDialog.THEME_LIGHT_EDIT /*5*/:
                syncInfo = getString(R.string.micloud_sync_state_stop);
                break;
            case AlertDialog.THEME_DARK_EDIT_DEFAULT /*6*/:
                syncInfo = getString(R.string.micloud_sync_state_pending);
                break;
            case AlertDialog.THEME_LIGHT_EDIT_DEFAULT /*7*/:
                syncInfo = getString(R.string.micloud_sync_state_syncing);
                break;
            case RegTask.ERROR_PHONE_ERROR /*8*/:
                syncInfo = getString(R.string.micloud_sync_state_need_wifi);
                break;
            default:
                syncInfo = getString(R.string.micloud_sync_state_synced);
                break;
        }
        if (!TextUtils.isEmpty(syncInfo)) {
            this.mPrefCloudService.setSummary(syncInfo);
        }
    }

    private void fetchMibiInfo() {
        if (!PaymentManager.get(this.mActivity).isMibiServiceDisabled()) {
            String userData = AccountManager.get(this.mActivity).getUserData(this.mAccount, Constants.ACCOUNT_MIBI_SIGN);
            if (TextUtils.isEmpty(userData)) {
                new GetMibiSignTask(this.mAccount.name).execute(new Void[0]);
            } else {
                SysHelper.queryMibiBalance(this.mActivity, this, userData);
            }
        }
    }

    private void finishActivity() {
        this.mActivity.finish();
    }

    public void setRefreshUserInfoInterface(RefreshUserInfoInterface refreshUserInfoInterface) {
        this.mRefreshUserInfoInterface = refreshUserInfoInterface;
        if (this.mListView != null) {
            this.mRefreshUserInfoInterface.setListView(this.mListView);
        }
    }
}
