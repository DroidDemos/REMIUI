package com.xiaomi.account.ui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleAdapter.ViewBinder;
import com.xiaomi.account.Constants;
import com.xiaomi.account.R;
import com.xiaomi.account.XiaomiAccountTaskService;
import com.xiaomi.account.data.DeviceInfo;
import com.xiaomi.account.data.SetupData;
import com.xiaomi.account.utils.AnalyticsHelper;
import com.xiaomi.account.utils.SysHelper;
import com.xiaomi.accountsdk.utils.DeviceModelUtil;
import com.xiaomi.passport.ui.LicenseActivity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import miui.analytics.Analytics;
import miui.app.ActionBar;
import miui.app.Activity;
import miui.app.AlertDialog.Builder;

public class MyDeviceListActivity extends Activity implements OnItemClickListener {
    private static final String ACTION_DEVICE_LIST_ACTIVITY = "com.xiaomi.account.action.DEVICE_LIST";
    private static final int REQUEST_DELETE_DEVICE = 0;
    private SimpleAdapter mAdapter;
    private Analytics mAnalytics;
    private List<Map<String, Object>> mDataList;
    private View mDeviceListContainer;
    private ListView mDevicesList;
    private View mEmptyListContainer;
    private View mLoadingContainer;
    private BroadcastReceiver mReceiver;

    private class ImageViewBinder implements ViewBinder {
        private ImageViewBinder() {
        }

        public boolean setViewValue(View view, Object data, String textRepresentation) {
            if ((view instanceof ImageView) && (data instanceof Bitmap)) {
                ImageView imageView = (ImageView) view;
                Bitmap bmp = (Bitmap) data;
                if (bmp != null) {
                    imageView.setImageBitmap(bmp);
                    return true;
                }
            }
            return false;
        }
    }

    public MyDeviceListActivity() {
        this.mReceiver = new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                if (Constants.ACTION_QUERY_DEVICE_LIST_SUCCEED.equals(intent.getAction())) {
                    ArrayList<DeviceInfo> deviceList = SetupData.getDeviceList();
                    if (deviceList == null) {
                        MyDeviceListActivity.this.showAlertDailog();
                        return;
                    }
                    MyDeviceListActivity.this.mDataList = MyDeviceListActivity.this.getDeviceList(deviceList);
                    MyDeviceListActivity.this.mAdapter = new SimpleAdapter(MyDeviceListActivity.this, MyDeviceListActivity.this.mDataList, R.layout.device_list_item, new String[]{"device_icon", "device_model", "device_login_time", "device_login_notice"}, new int[]{R.id.device_icon, R.id.device_model, R.id.device_login_time, R.id.device_login_notice});
                    MyDeviceListActivity.this.mAdapter.setViewBinder(new ImageViewBinder());
                    MyDeviceListActivity.this.mDevicesList.setAdapter(MyDeviceListActivity.this.mAdapter);
                    MyDeviceListActivity.this.resetViewsState();
                }
            }
        };
    }

    public static void start(Context context) {
        context.startActivity(new Intent(context, MyDeviceListActivity.class));
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_device_list);
        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.setTitle(getString(R.string.device_list_title));
        }
        this.mLoadingContainer = findViewById(R.id.loading_container);
        this.mDeviceListContainer = findViewById(R.id.device_list_container);
        this.mEmptyListContainer = findViewById(R.id.empty_list_container);
        resetViewsState();
        this.mDevicesList = (ListView) findViewById(16908298);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Constants.ACTION_QUERY_DEVICE_LIST_SUCCEED);
        registerReceiver(this.mReceiver, intentFilter);
        SetupData.setDeviceList(null);
        XiaomiAccountTaskService.startQueryDevicesList(this);
        this.mDevicesList.setOnItemClickListener(this);
        this.mAnalytics = Analytics.getInstance();
        this.mAnalytics.startSession(this);
        AnalyticsHelper.trackEvent(this.mAnalytics, AnalyticsHelper.USER_ENTER_DEVICE_LIST_PAGE);
    }

    private List<Map<String, Object>> getDeviceList(ArrayList<DeviceInfo> deviceList) {
        List<Map<String, Object>> list = new ArrayList();
        Iterator i$ = deviceList.iterator();
        while (i$.hasNext()) {
            String model = ((DeviceInfo) i$.next()).getModel();
            Map<String, Object> item = new HashMap();
            item.put("device_icon", SysHelper.getImageBitmap(this, model));
            item.put("device_model", DeviceModelUtil.getModelName(model));
            item.put("device_login_time", "");
            item.put("device_login_notice", "");
            list.add(item);
        }
        return list;
    }

    protected void onResume() {
        super.onResume();
        if (!SysHelper.isNetworkConnected(this)) {
            NoNetworkActivity.start(this, getString(R.string.device_list_title), ACTION_DEVICE_LIST_ACTIVITY);
            finish();
        }
    }

    protected void onDestroy() {
        this.mAnalytics.endSession();
        unregisterReceiver(this.mReceiver);
        super.onDestroy();
    }

    private void showAlertDailog() {
        Builder builder = new Builder(this);
        builder.setTitle(R.string.get_device_list_failed);
        builder.setMessage(R.string.error_server);
        builder.setPositiveButton(17039370, null);
        builder.show();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case LicenseActivity.PRIVACY_POLICY /*0*/:
                if (resultCode == -1) {
                    int position = data.getIntExtra(Constants.LIST_POSITION_PARAM, -1);
                    if (position != -1) {
                        SetupData.getDeviceList().remove(position);
                        this.mDataList.remove(position);
                        this.mAdapter.notifyDataSetChanged();
                    }
                    resetViewsState();
                    break;
                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        Intent intent = new Intent(this, OtherDeviceDetailInfoActivity.class);
        intent.putExtra(Constants.LIST_POSITION_PARAM, position);
        startActivityForResult(intent, 0);
    }

    private void resetViewsState() {
        this.mLoadingContainer.setVisibility(8);
        this.mDeviceListContainer.setVisibility(8);
        this.mEmptyListContainer.setVisibility(8);
        if (this.mDataList == null) {
            this.mLoadingContainer.setVisibility(0);
        } else if (this.mDataList.isEmpty()) {
            this.mEmptyListContainer.setVisibility(0);
        } else {
            this.mDeviceListContainer.setVisibility(0);
        }
    }
}
