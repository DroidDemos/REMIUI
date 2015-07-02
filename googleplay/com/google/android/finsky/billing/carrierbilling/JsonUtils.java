package com.google.android.finsky.billing.carrierbilling;

import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonUtils {
    public static JSONObject toLowerCase(JSONObject input) throws JSONException {
        Iterator<String> inputIter = input.keys();
        JSONObject resultObj = new JSONObject();
        while (inputIter.hasNext()) {
            String key = (String) inputIter.next();
            Object obj = input.get(key);
            if (obj instanceof JSONObject) {
                obj = toLowerCase((JSONObject) obj);
            }
            resultObj.put(key.toLowerCase(), obj);
        }
        return resultObj;
    }

    public static JSONObject getObject(JSONObject jsonResult, String key) {
        try {
            return jsonResult.getJSONObject(key);
        } catch (JSONException e) {
            return null;
        }
    }

    public static Integer getInt(JSONObject jsonResult, String key) {
        Integer num = null;
        try {
            num = Integer.valueOf(Integer.parseInt(jsonResult.getString(key)));
        } catch (NumberFormatException e) {
        } catch (JSONException e2) {
        }
        return num;
    }

    public static Long getLong(JSONObject jsonResult, String key) {
        Long l = null;
        try {
            l = Long.valueOf(Long.parseLong(jsonResult.getString(key)));
        } catch (NumberFormatException e) {
        } catch (JSONException e2) {
        }
        return l;
    }

    public static String getString(JSONObject jsonResult, String key) {
        try {
            String value = jsonResult.getString(key);
            if ("null".equals(value)) {
                return null;
            }
            return value;
        } catch (JSONException e) {
            return null;
        }
    }

    public static Boolean getBoolean(JSONObject jsonResult, String key) {
        try {
            return Boolean.valueOf(jsonResult.getBoolean(key));
        } catch (JSONException e) {
            return null;
        }
    }
}
