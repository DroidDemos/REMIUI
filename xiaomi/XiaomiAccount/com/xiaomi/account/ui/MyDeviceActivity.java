package com.xiaomi.account.ui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.MiuiSettings.System;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import com.xiaomi.account.Constants;
import com.xiaomi.account.R;
import com.xiaomi.account.XiaomiAccountTaskService;
import com.xiaomi.account.data.DeviceInfo;
import com.xiaomi.account.data.DeviceInfo.DevSettingName;
import com.xiaomi.account.utils.SysHelper;
import com.xiaomi.accountsdk.activate.ActivateManager;
import com.xiaomi.accountsdk.activate.ActivateManager.ActivateManagerFuture;
import com.xiaomi.accountsdk.activate.CloudServiceFailureException;
import com.xiaomi.accountsdk.activate.OperationCancelledException;
import com.xiaomi.accountsdk.utils.DeviceModelUtil;
import java.io.IOException;
import java.util.HashMap;
import miui.app.ActionBar;
import miui.app.Activity;
import miui.telephony.CloudTelephonyManager;

public class MyDeviceActivity extends Activity implements OnClickListener {
    private static final String ACTION_ABOUT_DEVICE_INFO = "android.settings.DEVICE_INFO_SETTINGS";
    private static final String ACTION_MY_DEVICE_INFO = "com.xiaomi.account.action.MY_DEVICE";
    private static final String TAG = "MyDeviceActivity";
    private ImageView mDeviceImage;
    private TextView mDeviceNameView;
    private View mOtherDeviceView;
    private TextView mPhoneNumView;
    private BroadcastReceiver mReceiver;
    private TextView mTrustDevice;

    private class GetPhoneNumberTask extends AsyncTask<Void, Void, String> {
        private GetPhoneNumberTask() {
        }

        protected String doInBackground(Void... params) {
            String phoneNum = null;
            for (int i = 0; i < CloudTelephonyManager.getMultiSimCount(); i++) {
                ActivateManagerFuture<Bundle> c = ActivateManager.get(MyDeviceActivity.this).getActivateInfo(i);
                if (c == null) {
                    Log.i(MyDeviceActivity.TAG, "getPhoneNumber: Null ActivateManagerFuture.");
                } else {
                    try {
                        Bundle b = (Bundle) c.getResult();
                        if (b == null) {
                            Log.e(MyDeviceActivity.TAG, "getPhoneNumber: Null ActivateManagerFuture bundle.");
                        } else if (b.getBoolean("sim_inserted")) {
                            phoneNum = b.getString("activate_phone");
                            if (!TextUtils.isEmpty(phoneNum)) {
                                break;
                            }
                        } else {
                            Log.e(MyDeviceActivity.TAG, "getPhoneNumber: SIM not inserted");
                        }
                    } catch (IOException e) {
                        Log.e(MyDeviceActivity.TAG, "getPhoneNumber: ", e);
                    } catch (OperationCancelledException e2) {
                        Log.e(MyDeviceActivity.TAG, "getPhoneNumber: ", e2);
                    } catch (CloudServiceFailureException e3) {
                        Log.e(MyDeviceActivity.TAG, "getPhoneNumber: ", e3);
                    }
                }
            }
            return phoneNum;
        }

        protected void onPostExecute(String result) {
            String phoneStr;
            super.onPostExecute(result);
            if (CloudTelephonyManager.getAvailableSimCount() <= 0) {
                phoneStr = MyDeviceActivity.this.getString(R.string.no_sim_card);
            } else if (TextUtils.isEmpty(result)) {
                phoneStr = MyDeviceActivity.this.getString(R.string.phone_unactivated);
            } else {
                phoneStr = String.format(MyDeviceActivity.this.getString(R.string.phone_activated), new Object[]{result});
            }
            MyDeviceActivity.this.mPhoneNumView.setText(phoneStr);
        }
    }

    public MyDeviceActivity() {
        this.mReceiver = new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                if (Constants.ACTION_QUERY_DEVICE_INFO_SUCCEED.equals(intent.getAction())) {
                    DeviceInfo deviceInfo = (DeviceInfo) intent.getSerializableExtra(Constants.DEVICE_INFO_QUERY_RESULT);
                    String deviceName = System.getDeviceName(MyDeviceActivity.this);
                    String name = deviceInfo.getDeviceName();
                    if (!(deviceInfo == null || deviceName == null || name == null || deviceName.equals(deviceInfo.getDeviceName()))) {
                        MyDeviceActivity.this.uploadDeviceName(deviceName);
                    }
                    if (deviceInfo != null && deviceInfo.getIsTrustedDevice()) {
                        MyDeviceActivity.this.mTrustDevice.setVisibility(0);
                    }
                    Bitmap image = SysHelper.getImageBitmap(MyDeviceActivity.this, Build.MODEL);
                    if (image != null) {
                        MyDeviceActivity.this.mDeviceImage.setImageBitmap(image);
                    }
                }
            }
        };
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_device_info);
        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.setTitle(getString(R.string.my_device_title));
            setImmersionMenuEnabled(true);
        }
        this.mDeviceNameView = (TextView) findViewById(R.id.device_name);
        this.mPhoneNumView = (TextView) findViewById(R.id.phone_number);
        this.mDeviceImage = (ImageView) findViewById(R.id.device_image);
        this.mOtherDeviceView = findViewById(R.id.other_device_layout);
        this.mOtherDeviceView.setOnClickListener(this);
        ((TextView) findViewById(R.id.device_model)).setText(DeviceModelUtil.getModelName(Build.MODEL));
        this.mTrustDevice = (TextView) findViewById(R.id.trust_device);
        this.mTrustDevice.setVisibility(8);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Constants.ACTION_QUERY_DEVICE_INFO_SUCCEED);
        registerReceiver(this.mReceiver, intentFilter);
        XiaomiAccountTaskService.startQueryDeviceInfo(this);
    }

    public void onClick(View v) {
        if (v == this.mOtherDeviceView) {
            MyDeviceListActivity.start(this);
        }
    }

    protected void onResume() {
        super.onResume();
        if (!SysHelper.isNetworkConnected(this)) {
            NoNetworkActivity.start(this, getString(R.string.my_device_title), ACTION_MY_DEVICE_INFO);
            finish();
        }
        setViewsStatus();
    }

    private void setViewsStatus() {
        this.mDeviceNameView.setText(System.getDeviceName(this));
        if (miui.os.Build.IS_TABLET) {
            this.mPhoneNumView.setText(R.string.no_sim_card);
        } else {
            new GetPhoneNumberTask().execute(new Void[0]);
        }
    }

    protected void onDestroy() {
        unregisterReceiver(this.mReceiver);
        super.onDestroy();
    }

    private void uploadDeviceName(String name) {
        HashMap<String, String> map = new HashMap();
        map.put(DevSettingName.DEVICENAME, name);
        XiaomiAccountTaskService.startUploadDeviceInfo(this, map);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.devices_immersion_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.update_device_name:
                Intent updateDeviceNameintent = new Intent();
                updateDeviceNameintent.setClassName("com.android.settings", "com.android.settings.Settings");
                updateDeviceNameintent.putExtra(":android:show_fragment", "com.android.settings.MiuiDeviceNameEditFragment");
                updateDeviceNameintent.putExtra(":android:show_fragment_title", 0);
                updateDeviceNameintent.putExtra(":android:show_fragment_short_title", 0);
                updateDeviceNameintent.putExtra(":android:no_headers", true);
                updateDeviceNameintent.addFlags(268435456);
                startActivity(updateDeviceNameintent);
                return true;
            case R.id.about_device_info:
                Intent intent = new Intent();
                intent.setAction(ACTION_ABOUT_DEVICE_INFO);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
