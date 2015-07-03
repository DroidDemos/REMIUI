package com.xiaomi.account.ui;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.xiaomi.account.Constants;
import com.xiaomi.account.R;
import com.xiaomi.account.data.DeviceInfo;
import com.xiaomi.account.data.SetupData;
import com.xiaomi.account.utils.AnalyticsHelper;
import com.xiaomi.account.utils.CloudHelper;
import com.xiaomi.account.utils.SysHelper;
import com.xiaomi.accountsdk.account.data.ExtendedAuthToken;
import com.xiaomi.accountsdk.request.AuthenticationFailureException;
import com.xiaomi.accountsdk.utils.DeviceModelUtil;
import java.io.File;
import java.util.ArrayList;
import miui.accounts.ExtraAccountManager;
import miui.analytics.Analytics;
import miui.app.ActionBar;
import miui.app.Activity;
import miui.app.AlertDialog.Builder;
import miui.app.ProgressDialog;

public class OtherDeviceDetailInfoActivity extends Activity implements OnClickListener {
    private static final int DELETE_DEVICE_RESULT = 2;
    private static final int DIALOG_DELETE_PROGRESS = 0;
    private static final int DIALOG_DEVICE_DELETE_FAILED = 2;
    private static final int DIALOG_DEVICE_DELETE_SUCCESS = 1;
    private static final int REMOVE_TRUST_AND_DELETE_DEVICE_RESULT = 3;
    private static final int REMOVE_TRUST_DEVICE_RESULT = 1;
    private static final String TAG = "OtherDeviceDetailInfoActivity";
    private Analytics mAnalytics;
    private Button mDeleteBtn;
    private TextView mDeleteDeviceNotice;
    private ProgressDialog mDeleteDialog;
    private String mDevId;
    private int mPosition;
    private CheckBox mRemoveTrustCheckBox;
    private TextView mTrustDevice;

    private abstract class DeleteTask extends AsyncTask<Void, Void, Boolean> {
        private DeleteTask() {
        }

        protected void onPreExecute() {
            super.onPreExecute();
            OtherDeviceDetailInfoActivity.this.showDialog(OtherDeviceDetailInfoActivity.DIALOG_DELETE_PROGRESS);
        }

        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);
            OtherDeviceDetailInfoActivity.this.dismissDialog();
        }
    }

    private class DeleteDeviceTask extends DeleteTask {
        private DeleteDeviceTask() {
            super();
        }

        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);
            if (result.booleanValue()) {
                OtherDeviceDetailInfoActivity.this.showDialog(OtherDeviceDetailInfoActivity.REMOVE_TRUST_DEVICE_RESULT);
                DeviceInfo info = OtherDeviceDetailInfoActivity.getDeviceInfo(OtherDeviceDetailInfoActivity.this.mPosition);
                if (info == null) {
                    Log.d(OtherDeviceDetailInfoActivity.TAG, " DeleteDeviceTask the device info is null");
                    return;
                }
                File cachedImageFile = OtherDeviceDetailInfoActivity.this.getFileStreamPath(info.getModel());
                if (cachedImageFile.isFile() && cachedImageFile.exists()) {
                    cachedImageFile.delete();
                    return;
                }
                return;
            }
            Log.e(OtherDeviceDetailInfoActivity.TAG, "failed to delete the device ");
            OtherDeviceDetailInfoActivity.this.showDialog(OtherDeviceDetailInfoActivity.DIALOG_DEVICE_DELETE_FAILED);
        }

        protected Boolean doInBackground(Void... params) {
            return Boolean.valueOf(SysHelper.deleteBindedDevice(OtherDeviceDetailInfoActivity.this, OtherDeviceDetailInfoActivity.this.mDevId));
        }
    }

    private class RemoveTrustDeviceTask extends DeleteTask {
        private Boolean isDeviceDeleted;

        public RemoveTrustDeviceTask(boolean isDeleted) {
            super();
            this.isDeviceDeleted = Boolean.valueOf(isDeleted);
        }

        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);
            if (!result.booleanValue()) {
                Log.e(OtherDeviceDetailInfoActivity.TAG, "failed to remove the trust device ");
            } else if (this.isDeviceDeleted.booleanValue()) {
                new DeleteDeviceTask().execute(new Void[OtherDeviceDetailInfoActivity.DIALOG_DELETE_PROGRESS]);
            } else {
                ArrayList<DeviceInfo> devicesList = SetupData.getDeviceList();
                if (devicesList != null) {
                    ((DeviceInfo) devicesList.get(OtherDeviceDetailInfoActivity.this.mPosition)).setIsTrustedDevice(false);
                }
                OtherDeviceDetailInfoActivity.this.mTrustDevice.setVisibility(8);
                OtherDeviceDetailInfoActivity.this.resetViewStatus(false);
                Toast.makeText(OtherDeviceDetailInfoActivity.this, OtherDeviceDetailInfoActivity.this.getString(R.string.remove_device_success_notice), OtherDeviceDetailInfoActivity.REMOVE_TRUST_DEVICE_RESULT).show();
            }
        }

        protected Boolean doInBackground(Void... params) {
            Account account = ExtraAccountManager.getXiaomiAccount(OtherDeviceDetailInfoActivity.this);
            if (account == null) {
                Log.w(OtherDeviceDetailInfoActivity.TAG, "no Xiaomi account");
                return Boolean.valueOf(false);
            }
            AccountManager am = AccountManager.get(OtherDeviceDetailInfoActivity.this);
            int count = OtherDeviceDetailInfoActivity.DIALOG_DELETE_PROGRESS;
            while (count < OtherDeviceDetailInfoActivity.DIALOG_DEVICE_DELETE_FAILED) {
                String authToken = SysHelper.getAuthToken(am, account, Constants.DEVICE_INFO_SID);
                ExtendedAuthToken extendedAuthToken = ExtendedAuthToken.parse(authToken);
                if (extendedAuthToken != null) {
                    String serviceToken = extendedAuthToken.authToken;
                    String security = extendedAuthToken.security;
                    if (serviceToken == null || security == null) {
                        break;
                    }
                    try {
                        return Boolean.valueOf(CloudHelper.removeTrustDevice(account.name, am.getUserData(account, com.xiaomi.passport.Constants.KEY_ENCRYPTED_USER_ID), OtherDeviceDetailInfoActivity.this.mDevId, serviceToken, security));
                    } catch (AuthenticationFailureException e) {
                        Log.e(OtherDeviceDetailInfoActivity.TAG, "auth failure when remove trust device", e);
                        am.invalidateAuthToken(account.type, authToken);
                        count += OtherDeviceDetailInfoActivity.REMOVE_TRUST_DEVICE_RESULT;
                    }
                } else {
                    break;
                }
            }
            return Boolean.valueOf(false);
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.other_device_detail_info);
        this.mTrustDevice = (TextView) findViewById(R.id.trust_device);
        this.mDeleteDeviceNotice = (TextView) findViewById(R.id.delete_device_notice);
        this.mRemoveTrustCheckBox = (CheckBox) findViewById(R.id.remove_trust_notice);
        this.mDeleteBtn = (Button) findViewById(R.id.delete_btn);
        this.mDeleteBtn.setOnClickListener(this);
        initDeviceInfo();
        this.mAnalytics = Analytics.getInstance();
        this.mAnalytics.startSession(this);
        AnalyticsHelper.trackEvent(this.mAnalytics, AnalyticsHelper.USER_ENTER_OTHER_DEVICE_DETAIL_INFO_PAGE);
    }

    private static DeviceInfo getDeviceInfo(int index) {
        ArrayList<DeviceInfo> deviceList = SetupData.getDeviceList();
        if (index != -1 && deviceList != null && index < deviceList.size()) {
            return (DeviceInfo) deviceList.get(index);
        }
        Log.d(TAG, "invalid position or deviceList is null");
        return null;
    }

    private void initDeviceInfo() {
        int i = DIALOG_DELETE_PROGRESS;
        this.mPosition = getIntent().getIntExtra(Constants.LIST_POSITION_PARAM, -1);
        DeviceInfo info = getDeviceInfo(this.mPosition);
        if (info == null) {
            Log.d(TAG, "initDeviceInfo the device info is null");
            finish();
            return;
        }
        String deviceName;
        String phoneNum;
        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.setTitle(DeviceModelUtil.getModelName(info.getModel()));
        }
        this.mDevId = info.getDevId();
        if (TextUtils.isEmpty(info.getDeviceName())) {
            deviceName = DeviceModelUtil.getDeviceName(info.getModel());
        } else {
            deviceName = info.getDeviceName();
        }
        ((TextView) findViewById(R.id.device_name)).setText(deviceName);
        if (TextUtils.isEmpty(info.getPhoneNumber())) {
            phoneNum = getString(R.string.phone_unknown);
        } else {
            String string = getResources().getString(R.string.phone_activated);
            Object[] objArr = new Object[REMOVE_TRUST_DEVICE_RESULT];
            objArr[DIALOG_DELETE_PROGRESS] = info.getPhoneNumber();
            phoneNum = String.format(string, objArr);
        }
        ((TextView) findViewById(R.id.phone_number)).setText(phoneNum);
        ((TextView) findViewById(R.id.device_model)).setText(DeviceModelUtil.getModelName(info.getModel()));
        ImageView deviceImage = (ImageView) findViewById(R.id.device_image);
        Bitmap image = SysHelper.getImageBitmap(this, info.getModel());
        if (image != null) {
            deviceImage.setImageBitmap(image);
        }
        TextView textView = this.mTrustDevice;
        if (!info.getIsTrustedDevice()) {
            i = 8;
        }
        textView.setVisibility(i);
        resetViewStatus(info.getIsTrustedDevice());
    }

    private void resetViewStatus(boolean isTrusted) {
        if (isTrusted) {
            this.mDeleteDeviceNotice.setVisibility(8);
            this.mRemoveTrustCheckBox.setVisibility(DIALOG_DELETE_PROGRESS);
            this.mDeleteBtn.setText(R.string.remove_trust_btn);
            return;
        }
        this.mDeleteDeviceNotice.setVisibility(DIALOG_DELETE_PROGRESS);
        this.mRemoveTrustCheckBox.setVisibility(8);
        this.mDeleteBtn.setText(R.string.delete_device_btn);
    }

    protected void onDestroy() {
        this.mAnalytics.endSession();
        super.onDestroy();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REMOVE_TRUST_DEVICE_RESULT /*1*/:
                if (resultCode == -1) {
                    new RemoveTrustDeviceTask(false).execute(new Void[DIALOG_DELETE_PROGRESS]);
                    break;
                }
                break;
            case DIALOG_DEVICE_DELETE_FAILED /*2*/:
                if (resultCode == -1) {
                    new DeleteDeviceTask().execute(new Void[DIALOG_DELETE_PROGRESS]);
                    break;
                }
                break;
            case REMOVE_TRUST_AND_DELETE_DEVICE_RESULT /*3*/:
                if (resultCode == -1) {
                    resetViewStatus(false);
                    new RemoveTrustDeviceTask(true).execute(new Void[DIALOG_DELETE_PROGRESS]);
                    break;
                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void onClick(View v) {
        if (v == this.mDeleteBtn) {
            int requestCode;
            Intent intent = new Intent(this, QuickLoginActivity.class);
            intent.putExtra(com.xiaomi.passport.Constants.EXTRA_VERIFY_ONLY, true);
            if (this.mRemoveTrustCheckBox.getVisibility() != 0) {
                requestCode = DIALOG_DEVICE_DELETE_FAILED;
            } else if (this.mRemoveTrustCheckBox.isChecked()) {
                requestCode = REMOVE_TRUST_AND_DELETE_DEVICE_RESULT;
            } else {
                requestCode = REMOVE_TRUST_DEVICE_RESULT;
            }
            startActivityForResult(intent, requestCode);
        }
    }

    protected Dialog onCreateDialog(int id, Bundle args) {
        switch (id) {
            case DIALOG_DELETE_PROGRESS /*0*/:
                this.mDeleteDialog = new ProgressDialog(this);
                this.mDeleteDialog.setMessage(getString(R.string.device_delete_loading));
                this.mDeleteDialog.setCancelable(false);
                return this.mDeleteDialog;
            case REMOVE_TRUST_DEVICE_RESULT /*1*/:
                Builder builder = new Builder(this);
                builder.setTitle(R.string.device_delete_success_title);
                builder.setMessage(R.string.device_delete_success_message);
                View view = LayoutInflater.from(this).inflate(R.layout.device_delete_success_dialog, null);
                builder.setView(view);
                view.findViewById(R.id.change_password).setOnClickListener(new OnClickListener() {
                    public void onClick(View v) {
                        SysHelper.getbackPassword(OtherDeviceDetailInfoActivity.this);
                    }
                });
                view.findViewById(R.id.completed).setOnClickListener(new OnClickListener() {
                    public void onClick(View v) {
                        Intent intent = new Intent();
                        intent.putExtra(Constants.LIST_POSITION_PARAM, OtherDeviceDetailInfoActivity.this.mPosition);
                        OtherDeviceDetailInfoActivity.this.setResult(-1, intent);
                        OtherDeviceDetailInfoActivity.this.finish();
                    }
                });
                return builder.create();
            case DIALOG_DEVICE_DELETE_FAILED /*2*/:
                Builder dialogBuilder = new Builder(this);
                dialogBuilder.setTitle(R.string.delete_device_failed);
                dialogBuilder.setMessage(R.string.no_response_retry);
                dialogBuilder.setPositiveButton(17039370, null);
                return dialogBuilder.create();
            default:
                return super.onCreateDialog(id, args);
        }
    }

    private void dismissDialog() {
        if (this.mDeleteDialog != null && this.mDeleteDialog.isShowing()) {
            removeDialog(DIALOG_DELETE_PROGRESS);
        }
        this.mDeleteDialog = null;
    }
}
