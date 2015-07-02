package com.alipay.mobilesecuritysdk.model;

import android.util.Log;
import com.alipay.mobilesecuritysdk.constant.ConfigConstant;
import com.alipay.mobilesecuritysdk.constant.ConfigNameEnum;
import com.alipay.mobilesecuritysdk.constant.LocationNameEnum;
import com.alipay.mobilesecuritysdk.datainfo.AppInfo;
import com.alipay.mobilesecuritysdk.datainfo.GeoResponseInfo;
import com.alipay.mobilesecuritysdk.datainfo.LocationInfo;
import com.alipay.mobilesecuritysdk.datainfo.SdkConfig;
import com.alipay.mobilesecuritysdk.datainfo.WifiCollectInfo;
import com.alipay.mobilesecuritysdk.face.SecurityClientMobile;
import com.alipay.mobilesecuritysdk.util.CommonUtils;
import com.alipay.sdk.cons.MiniDefine;
import java.io.File;
import java.io.StringReader;
import java.util.Date;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

public class DataProfile {
    private List tid;

    public List getTid() {
        return this.tid;
    }

    public void setTid(List list) {
        this.tid = list;
    }

    public SdkConfig getConfigs(String str) {
        if (str.length() == 0) {
            return null;
        }
        try {
            File file = new File(new StringBuilder(String.valueOf(str)).append(File.separator).append(ConfigConstant.CONFIG_FILENAME).toString());
            if (!file.exists()) {
                return GetDefaultConfig();
            }
            String ReadFile = CommonUtils.ReadFile(file.getPath());
            if (ReadFile.length() <= 0) {
                Log.d("read json", "file size o");
                return GetDefaultConfig();
            }
            SdkConfig instance = SdkConfig.getInstance();
            try {
                JSONObject jSONObject = new JSONObject(ReadFile).getJSONObject("configs");
                if (jSONObject == null) {
                    return GetDefaultConfig();
                }
                instance.setAppInterval(jSONObject.getInt(ConfigNameEnum.APP_INTERVAL.getValue()));
                instance.setAppLUT(jSONObject.getLong(ConfigNameEnum.APP_LUT.getValue()));
                instance.setLocateInterval(jSONObject.getInt(ConfigNameEnum.LOCATE_INTERVAL.getValue()));
                instance.setLocateLUT(jSONObject.getLong(ConfigNameEnum.LOCATE_LUT.getValue()));
                instance.setLocationMaxLines(jSONObject.getInt(ConfigNameEnum.LOCATION_MAX_LINES.getValue()));
                instance.setMainSwitchInterval(jSONObject.getInt(ConfigNameEnum.MAIN_SWITCH_INTERVAL.getValue()));
                instance.setMainSwitchLUT(jSONObject.getLong(ConfigNameEnum.MAIN_SWITCH_LUT.getValue()));
                instance.setMainSwitchState(jSONObject.getString(ConfigNameEnum.MAIN_SWITCH_STATE.getValue()));
                return instance;
            } catch (Exception e) {
                return GetDefaultConfig();
            }
        } catch (Exception e2) {
            SecurityClientMobile.setError(true);
            return GetDefaultConfig();
        }
    }

    public void saveConfigs(SdkConfig sdkConfig, String str) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(ConfigNameEnum.MAIN_SWITCH_LUT.getValue(), sdkConfig.getMainSwitchLUT());
            jSONObject.put(ConfigNameEnum.MAIN_SWITCH_STATE.getValue(), sdkConfig.getMainSwitchState());
            jSONObject.put(ConfigNameEnum.MAIN_SWITCH_INTERVAL.getValue(), sdkConfig.getMainSwitchInterval());
            jSONObject.put(ConfigNameEnum.LOCATE_LUT.getValue(), sdkConfig.getLocateLUT());
            jSONObject.put(ConfigNameEnum.LOCATE_INTERVAL.getValue(), sdkConfig.getLocateInterval());
            jSONObject.put(ConfigNameEnum.LOCATION_MAX_LINES.getValue(), sdkConfig.getLocationMaxLines());
            jSONObject.put(ConfigNameEnum.APP_LUT.getValue(), sdkConfig.getAppLUT());
            jSONObject.put(ConfigNameEnum.APP_INTERVAL.getValue(), sdkConfig.getAppInterval());
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put(ConfigNameEnum.CONFIGS.getValue(), jSONObject);
            if (SecurityClientMobile.isDebug()) {
                Log.i(ConfigConstant.LOG_TAG, "loadConfig" + jSONObject2.toString());
            }
            CommonUtils.WriteFile(str, jSONObject2.toString());
        } catch (Exception e) {
            SecurityClientMobile.setError(true);
        }
    }

    public GeoResponseInfo analysisServerRespond(String str) {
        GeoResponseInfo geoResponseInfo = new GeoResponseInfo();
        try {
            XmlPullParser newPullParser = XmlPullParserFactory.newInstance().newPullParser();
            newPullParser.setInput(new StringReader(str));
            int eventType = newPullParser.getEventType();
            while (eventType != 1) {
                try {
                    String name = newPullParser.getName();
                    if (eventType == 2) {
                        if (CommonUtils.equalsIgnoreCase(name, ConfigNameEnum.MAIN_SWITCH_STATE.getValue())) {
                            geoResponseInfo.setMainSwitchState(newPullParser.nextText());
                        } else if (CommonUtils.equalsIgnoreCase(name, ConfigNameEnum.MAIN_SWITCH_INTERVAL.getValue())) {
                            geoResponseInfo.setMainSwitchInterval(CommonUtils.string2int(newPullParser.nextText()));
                        } else if (CommonUtils.equalsIgnoreCase(name, ConfigNameEnum.LOCATE_INTERVAL.getValue())) {
                            geoResponseInfo.setLocateInterval(CommonUtils.string2int(newPullParser.nextText()));
                        } else if (CommonUtils.equalsIgnoreCase(name, ConfigNameEnum.LOCATION_MAX_LINES.getValue())) {
                            geoResponseInfo.setLocationMaxLines(CommonUtils.string2int(newPullParser.nextText()));
                        } else if (CommonUtils.equalsIgnoreCase(name, ConfigNameEnum.APP_INTERVAL.getValue())) {
                            geoResponseInfo.setAppInterval(CommonUtils.string2int(newPullParser.nextText()));
                        }
                    }
                    eventType = newPullParser.next();
                } catch (Exception e) {
                }
            }
        } catch (Exception e2) {
            Log.i(ConfigConstant.LOG_TAG, e2.getMessage());
        }
        geoResponseInfo.setSuccess(true);
        return geoResponseInfo;
    }

    private SdkConfig GetDefaultConfig() {
        SdkConfig instance = SdkConfig.getInstance();
        instance.setMainSwitchLUT(0);
        instance.setMainSwitchState(ConfigConstant.MAIN_SWITCH_STATE_ON);
        instance.setMainSwitchInterval(1);
        instance.setLocateLUT(0);
        instance.setLocateInterval(30);
        instance.setLocationMaxLines(24);
        instance.setAppLUT(0);
        instance.setAppInterval(7);
        return instance;
    }

    public String AppToString(String str, List list) {
        File file = new File(str);
        if (file.length() > ConfigConstant.MAX_SIZE_OF_FILE) {
            file.delete();
            Log.i("delete file", "app file size > 50k, file path is" + str);
        }
        JSONArray jSONArray = new JSONArray();
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        JSONArray jSONArray2 = new JSONArray();
        for (AppInfo appInfo : list) {
            try {
                JSONObject jSONObject3 = new JSONObject();
                jSONObject3.put(ConfigNameEnum.PKG_NAME.getValue(), appInfo.getPkgName());
                jSONObject3.put(ConfigNameEnum.PUB_KEY_HASH.getValue(), appInfo.getPkeyhash());
                jSONArray2.put(jSONObject3);
            } catch (JSONException e) {
                Log.d("appinfo", e.getLocalizedMessage());
            }
        }
        try {
            if (GetTIDJson() == null) {
                jSONObject2.put(MiniDefine.ak, ConfigConstant.WIRELESS_FILENAME);
            } else {
                jSONObject2.put(MiniDefine.ak, GetTIDJson());
            }
            jSONObject2.put("appList", jSONArray2);
            jSONObject2.put("timestamp", CommonUtils.convertDate2String(new Date()));
            jSONObject.put(MiniDefine.m, ConfigNameEnum.START_TAG.getValue());
            jSONObject.put("model", jSONObject2);
        } catch (JSONException e2) {
            Log.i("apptojason", e2.getLocalizedMessage());
        }
        jSONArray.put(jSONObject);
        return jSONArray.toString();
    }

    public String LocationToString(String str, List list) {
        JSONArray jSONArray;
        JSONArray jSONArray2;
        Log.i("LocationToString path is ", str);
        File file = new File(str);
        if (file.length() > ConfigConstant.MAX_SIZE_OF_FILE) {
            file.delete();
            Log.i("delete file", "lc file size > 50k");
            jSONArray = null;
        } else if (str.length() <= 0 || file.isDirectory() || !file.exists()) {
            jSONArray = null;
        } else {
            jSONArray = GetJsonFromFile(str);
        }
        if (jSONArray == null) {
            jSONArray2 = new JSONArray();
        } else {
            jSONArray2 = jSONArray;
        }
        JSONObject jSONObject = new JSONObject();
        for (LocationInfo locationInfo : list) {
            try {
                Object GetWifiToJson;
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put(LocationNameEnum.LOCATE_LATITUDE.getValue(), locationInfo.getLatitude());
                jSONObject2.put(LocationNameEnum.LOCATE_LONGITUDE.getValue(), locationInfo.getLongitude());
                jSONObject2.put(LocationNameEnum.LOCATE_CELL_ID.getValue(), locationInfo.getCid());
                jSONObject2.put(LocationNameEnum.LOCATE_LAC.getValue(), locationInfo.getLac());
                jSONObject2.put(LocationNameEnum.TIME_STAMP.getValue(), locationInfo.getTime());
                jSONObject2.put(MiniDefine.ak, GetTIDJson());
                jSONObject2.put(LocationNameEnum.MCC.getValue(), locationInfo.getMcc());
                jSONObject2.put(LocationNameEnum.MNC.getValue(), locationInfo.getMnc());
                jSONObject2.put(LocationNameEnum.PHONETYPE.getValue(), locationInfo.getPhonetype());
                if (locationInfo.getWifi() != null) {
                    GetWifiToJson = GetWifiToJson(locationInfo.getWifi());
                } else {
                    GetWifiToJson = null;
                }
                if (GetWifiToJson != null) {
                    jSONObject2.put(LocationNameEnum.LOCATE_WIFI.getValue(), GetWifiToJson);
                }
                jSONObject.put(MiniDefine.m, LocationNameEnum.START_TAG.getValue());
                jSONObject.put("model", jSONObject2);
            } catch (JSONException e) {
                Log.d("location", e.getLocalizedMessage());
            }
        }
        jSONArray2.put(jSONObject);
        return jSONArray2.toString();
    }

    private JSONArray GetWifiToJson(List list) {
        JSONArray jSONArray = new JSONArray();
        for (WifiCollectInfo wifiCollectInfo : list) {
            try {
                JSONObject jSONObject = new JSONObject();
                if (wifiCollectInfo.getMbssid() == null) {
                    jSONObject.put(LocationNameEnum.BSSID.getValue(), ConfigConstant.WIRELESS_FILENAME);
                } else {
                    jSONObject.put(LocationNameEnum.BSSID.getValue(), wifiCollectInfo.getMbssid());
                }
                if (wifiCollectInfo.getMssid() == null) {
                    jSONObject.put(LocationNameEnum.SSID.getValue(), ConfigConstant.WIRELESS_FILENAME);
                } else {
                    jSONObject.put(LocationNameEnum.SSID.getValue(), wifiCollectInfo.getMssid());
                }
                jSONObject.put(LocationNameEnum.CURRENT.getValue(), wifiCollectInfo.isMiscurrent());
                jSONObject.put(LocationNameEnum.LEVEL.getValue(), wifiCollectInfo.getMlevel());
                jSONArray.put(jSONObject);
            } catch (JSONException e) {
                Log.d("location", e.getLocalizedMessage());
            }
        }
        return jSONArray;
    }

    public JSONArray GetTIDJson() {
        if (this.tid == null || this.tid.isEmpty()) {
            return null;
        }
        JSONArray jSONArray = new JSONArray();
        for (String put : this.tid) {
            jSONArray.put(put);
        }
        return jSONArray;
    }

    public JSONArray GetJsonFromFile(String str) {
        if (str.length() <= 0) {
            return null;
        }
        String ReadFile = CommonUtils.ReadFile(str);
        if (ReadFile.length() <= 0) {
            return null;
        }
        JSONArray jSONArray;
        try {
            jSONArray = new JSONArray(ReadFile);
        } catch (JSONException e) {
            Log.d("getjsonfromfile", e.getLocalizedMessage());
            jSONArray = null;
        }
        return jSONArray;
    }

    public void cleanUploadFiles(String str) {
        try {
            File file = new File(str);
            if (file.exists()) {
                file.delete();
            }
        } catch (Exception e) {
            Log.i(ConfigConstant.LOG_TAG, e.getMessage());
        }
    }
}
