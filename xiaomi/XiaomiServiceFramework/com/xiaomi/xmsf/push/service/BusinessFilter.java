package com.xiaomi.xmsf.push.service;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.xiaomi.channel.commonutils.misc.DateTimeHelper;
import org.json.JSONException;
import org.json.JSONObject;

class BusinessFilter {

    public static class FilterRet {
        int adsStatusCode;
        JSONObject adsToBeDelivered;
        int adsType;
    }

    BusinessFilter() {
    }

    public static FilterRet filter(JSONObject adsJson, Context context) {
        FilterRet ret = new FilterRet();
        ret.adsStatusCode = checkAds(adsJson);
        if (ret.adsStatusCode == 0) {
            int showType = adsJson.optInt(Constants.JSON_TAG_SHOWTYPE);
            int upperBound = adsJson.optInt(Constants.JSON_TAG_UPPERBOUND);
            boolean reachUpperLimit = false;
            if (upperBound > 0) {
                reachUpperLimit = !mainAdsReachUpperLimit(context, upperBound, showType);
            }
            if (reachUpperLimit || (showType == 2 && false)) {
                long subAdId = adsJson.optLong(Constants.JSON_TAG_SUBADID);
                if (subAdId > 0) {
                    try {
                        JSONObject subAdJson = new JSONObject(adsJson.optString(Constants.JSON_TAG_SUBADINFO));
                        ret.adsStatusCode = checkAds(subAdJson);
                        if (ret.adsStatusCode == 0) {
                            subAdJson.put(Constants.JSON_TAG_ID, subAdId);
                            ret.adsToBeDelivered = subAdJson;
                            ret.adsType = subAdJson.optInt(Constants.JSON_TAG_SHOWTYPE);
                        }
                    } catch (JSONException e) {
                        ret.adsStatusCode = -1;
                    }
                } else if (reachUpperLimit) {
                    ret.adsStatusCode = -3;
                } else {
                    ret.adsStatusCode = -6;
                }
            } else {
                ret.adsToBeDelivered = adsJson;
                ret.adsType = showType;
            }
        }
        return ret;
    }

    private static int checkAds(JSONObject adsJson) {
        if (!adsJson.optString(Constants.JSON_TAG_STATUS).equals(Constants.HTTP_RESPONSE_STATUS_SUCCESS)) {
            return -1;
        }
        if (adsJson.optInt(Constants.JSON_TAG_NONSENSE) != 0) {
            return -2;
        }
        long lastShow = adsJson.optLong(Constants.JSON_TAG_LASTSHOWTIME, 0);
        if (lastShow == 0 || lastShow >= System.currentTimeMillis()) {
            return 0;
        }
        return -4;
    }

    private static boolean mainAdsReachUpperLimit(Context context, int upperBound, int showType) {
        long current = System.currentTimeMillis();
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        long start = pref.getLong(Constants.PREFER_KEY_STARTTIME, 0);
        if (start == 0) {
            pref.edit().putLong(Constants.PREFER_KEY_STARTTIME, current).commit();
            return true;
        } else if (current - start > DateTimeHelper.sDayInMilliseconds) {
            pref.edit().putLong(Constants.PREFER_KEY_STARTTIME, 0).commit();
            pref.edit().putInt(Constants.PREFER_KEY_NOTIFY_SUCCESS_COUNT, 0).commit();
            pref.edit().putInt(Constants.PREFER_KEY_BUBBLE_SUCCESS_COUNT, 0).commit();
            return true;
        } else {
            if (showType == 2) {
                if (pref.getInt(Constants.PREFER_KEY_NOTIFY_SUCCESS_COUNT, 0) < upperBound) {
                    return true;
                }
            } else if (showType == 1 && pref.getInt(Constants.PREFER_KEY_BUBBLE_SUCCESS_COUNT, 0) < upperBound * 4) {
                return true;
            }
            return false;
        }
    }
}
