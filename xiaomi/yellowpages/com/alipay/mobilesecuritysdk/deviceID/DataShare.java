package com.alipay.mobilesecuritysdk.deviceID;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.alipay.mobilesecuritysdk.constant.ConfigConstant;
import java.util.Map;
import java.util.Map.Entry;

public class DataShare {
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
