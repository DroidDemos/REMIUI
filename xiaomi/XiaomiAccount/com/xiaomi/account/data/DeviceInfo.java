package com.xiaomi.account.data;

import android.util.Log;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DeviceInfo implements Serializable {
    private static final String TAG = "DeviceInfo";
    private static final long serialVersionUID = 1;
    private String deviceId;
    private String deviceName;
    private boolean isTrustedDevice;
    private String loginTime;
    private String model;
    private String phoneNum;

    public static class DevSettingName {
        public static final String CAPABILITY = "capability";
        public static final String DEVICEID = "devId";
        public static final String DEVICENAME = "deviceName";
        public static final String MODEL = "model";
        public static final String OS_VERSION = "osVersion";
        public static final String PHONE_INFO = "_phoneInfo";
        public static final String STATUS = "status_micloud";
        public static final String TRUST_DEVICE = "trust_device";
    }

    public DeviceInfo() {
        this(null, null, null, null, false);
    }

    public DeviceInfo(String deviceName, String model, String loginTime, String phoneNum, boolean isTrust) {
        this.deviceName = deviceName;
        this.model = model;
        this.loginTime = loginTime;
        this.phoneNum = phoneNum;
        this.isTrustedDevice = isTrust;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setLoginTime(String time) {
        this.loginTime = time;
    }

    public void setPhoneInfo(String phoneInfo) {
        try {
            JSONArray jsonArray = new JSONArray(phoneInfo);
            ArrayList<Map<String, String>> info = new ArrayList();
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject item = jsonArray.optJSONObject(i);
                HashMap<String, String> map = new HashMap();
                map.put("phone", item.getString("phone"));
                map.put("simId", item.getString("simId"));
                info.add(map);
            }
            if (info.size() > 0) {
                this.phoneNum = (String) ((Map) info.get(0)).get("phone");
            }
        } catch (JSONException e) {
            Log.e(TAG, "setPhoneInfo", e);
        }
    }

    public void setDevId(String devId) {
        this.deviceId = devId;
    }

    public void setIsTrustedDevice(boolean isTrusted) {
        this.isTrustedDevice = isTrusted;
    }

    public String getDeviceName() {
        return this.deviceName;
    }

    public String getModel() {
        return this.model;
    }

    public String getLoginTime() {
        return this.loginTime;
    }

    public String getPhoneNumber() {
        return this.phoneNum;
    }

    public String getDevId() {
        return this.deviceId;
    }

    public boolean getIsTrustedDevice() {
        return this.isTrustedDevice;
    }
}
