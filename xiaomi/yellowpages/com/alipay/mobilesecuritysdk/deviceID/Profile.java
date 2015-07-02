package com.alipay.mobilesecuritysdk.deviceID;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;
import com.alipay.mobilesecuritysdk.constant.ConfigConstant;
import com.alipay.sdk.cons.MiniDefine;
import com.alipay.sdk.util.DeviceInfo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.json.JSONException;
import org.json.JSONObject;

public class Profile {
    public static final String devicever = "0";

    public String generatePrivateData(Map map) {
        return MaptoString(map);
    }

    private String MaptoString(Map map) {
        if (map == null || map.size() <= 0) {
            return null;
        }
        JSONObject jSONObject = new JSONObject();
        for (Entry entry : map.entrySet()) {
            try {
                jSONObject.put((String) entry.getKey(), (String) entry.getValue());
            } catch (JSONException e) {
            }
        }
        return jSONObject.toString();
    }

    public String generateUploadData(Map map) {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        JSONObject jSONObject3 = new JSONObject();
        if (map != null) {
            try {
                if (map.size() > 0) {
                    for (Entry entry : map.entrySet()) {
                        String str = (String) entry.getKey();
                        if (str.equals(DeviceIdModel.mDeviceInfo)) {
                            jSONObject3.put(str, new JSONObject(MaptoString((Map) entry.getValue())));
                        } else {
                            jSONObject3.put(str, (String) entry.getValue());
                        }
                    }
                }
            } catch (JSONException e) {
            }
        }
        jSONObject2.put("os", DeviceInfo.d);
        jSONObject2.put("data", jSONObject3);
        jSONObject.put(MiniDefine.m, "deviceinfo");
        jSONObject.put("model", jSONObject2);
        return jSONObject.toString();
    }

    public IdResponseInfo ParseResponse(String str) {
        if (str == null) {
            return null;
        }
        Log.i(DeviceIdModel.PRIVATE_NAME, "server response is" + str);
        IdResponseInfo idResponseInfo = new IdResponseInfo();
        try {
            JSONObject jSONObject = new JSONObject(str);
            idResponseInfo.setMsuccess(jSONObject.getBoolean("success"));
            if (!idResponseInfo.isMsuccess()) {
                return idResponseInfo;
            }
            jSONObject = jSONObject.getJSONObject("data");
            if (jSONObject == null) {
                return idResponseInfo;
            }
            idResponseInfo.setMversion(jSONObject.getString("version"));
            idResponseInfo.setMapdid(jSONObject.getString("apdid"));
            idResponseInfo.setMapdtk(jSONObject.getString(DeviceIdModel.mApdtk));
            JSONObject jSONObject2 = jSONObject.getJSONObject(DeviceIdModel.mRule);
            if (jSONObject2 != null) {
                idResponseInfo.setFuction(jSONObject2.getString("function"));
            }
            idResponseInfo.setMrule(jSONObject2.toString());
            idResponseInfo.setMtime(jSONObject.getString(MiniDefine.E));
            idResponseInfo.setMcheckcode(jSONObject.getString(DeviceIdModel.mCheckCode));
            return idResponseInfo;
        } catch (Throwable e) {
            List arrayList = new ArrayList();
            arrayList.add(ConfigConstant.WIRELESS_FILENAME);
            arrayList.add(ConfigConstant.WIRELESS_FILENAME);
            arrayList.add(ConfigConstant.WIRELESS_FILENAME);
            arrayList.add(LOG.getStackString(e));
            LOG.logMessage(arrayList);
            return idResponseInfo;
        }
    }

    public Map getMap(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            Iterator keys = jSONObject.keys();
            Map hashMap = new HashMap();
            while (keys.hasNext()) {
                String str2 = (String) keys.next();
                hashMap.put(str2, (String) jSONObject.get(str2));
            }
            return hashMap;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    String GetDataFromSharedPre(SharedPreferences sharedPreferences, String str) {
        return sharedPreferences.getString(str, ConfigConstant.WIRELESS_FILENAME);
    }

    void SetDataToSharePre(SharedPreferences sharedPreferences, Map map) {
        if (sharedPreferences != null && map != null) {
            Editor edit = sharedPreferences.edit();
            if (edit != null) {
                edit.clear();
                for (Entry entry : map.entrySet()) {
                    String str = (String) entry.getKey();
                    Object value = entry.getValue();
                    if (value instanceof String) {
                        edit.putString(str, (String) value);
                    } else if (value instanceof Integer) {
                        edit.putInt(str, ((Integer) value).intValue());
                    } else if (value instanceof Long) {
                        edit.putLong(str, ((Long) value).longValue());
                    } else if (value instanceof Float) {
                        edit.putFloat(str, ((Float) value).floatValue());
                    } else if (value instanceof Boolean) {
                        edit.putBoolean(str, ((Boolean) value).booleanValue());
                    }
                }
                edit.commit();
            }
        }
    }
}
