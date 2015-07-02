package com.xiaomi.xmsf.activate;

import android.content.Context;
import android.util.Log;
import com.xiaomi.accountsdk.utils.CloudCoder;
import com.xiaomi.activate.ActivateInfo;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ActivateOldInfoReader {
    private static final String KEY_ACTIVATE_INFO = "activate_info";
    private static final String KEY_ACTIVATE_PHONE = "phone";
    private static final String KEY_SIM_ID = "sim_serial";
    private static final String KEY_SIM_PASS_TOKEN = "sim_pass_token";
    private static final String KEY_SIM_USER_ID = "sim_user_id";
    private static final String PREF_ACTIVATED_DEV_ID = "pref_activated_dev_id";
    private static final String PREF_ACTIVATED_SIM_ID = "pref_activated_sim_id";
    private static final String PREF_ACTIVATE_INFO = "pref_activate_info";
    private static final String PREF_PHONE = "pref_phone";
    private static final String PREF_SIM_ID = "pref_sim_id";
    private static final String PREF_SIM_PASS_TOKEN = "pref_sim_pass_token";
    private static final String PREF_SIM_PSECURITY = "pref_sim_psecurity";
    private static final String PREF_SIM_USER_ID = "pref_sim_uid";
    private static final String TAG = "ActivateOldInfoReader";

    public static boolean readActivateInfoV2(Context context, List<ActivateInfo> infoList) {
        String activateInfoStr = PrefUtils.getString(context, PREF_ACTIVATE_INFO);
        if (activateInfoStr == null) {
            Log.v(TAG, "Found no V2 activate info, bail.");
            return false;
        }
        try {
            JSONArray infoArray = new JSONObject(activateInfoStr).getJSONArray(KEY_ACTIVATE_INFO);
            Log.v(TAG, "Found " + infoArray.length() + " V2 activate info entries");
            int i = 0;
            while (i < infoArray.length()) {
                try {
                    JSONObject infoObj = infoArray.getJSONObject(i);
                    ActivateInfo info = new ActivateInfo();
                    String simId = infoObj.getString(KEY_SIM_ID);
                    if (simId == null) {
                        Log.v(TAG, "V2 activate info entry " + i + " has no simId. Skip.");
                    }
                    info.hashedSimId = CloudCoder.hashDeviceInfo(simId);
                    info.phone = infoObj.getString(KEY_ACTIVATE_PHONE);
                    info.simUserId = infoObj.getString(KEY_SIM_USER_ID);
                    info.simPassToken = infoObj.getString(KEY_SIM_PASS_TOKEN);
                    info.insertedToServer = false;
                    if (info.phone == null || info.simUserId == null || info.simPassToken == null) {
                        Log.v(TAG, "V2 activate info entry " + i + " is incomplete. Skip.");
                        i++;
                    } else {
                        infoList.add(info);
                        i++;
                    }
                } catch (JSONException e) {
                    Log.e(TAG, "failed to parse v2 activate info entry " + i);
                }
            }
            Log.v(TAG, "Integrated " + infoList.size() + " V2 activate info entries");
            return true;
        } catch (JSONException e2) {
            Log.e(TAG, "failed to parse activate info", e2);
            return false;
        }
    }

    public static void clearActivateInfoV2(Context context) {
        PrefUtils.remove(context, PREF_ACTIVATE_INFO);
        Log.v(TAG, "Cleared activate info v2");
    }

    public static boolean readActivateInfoV1(Context context, List<ActivateInfo> infoList) {
        String savedSimId = PrefUtils.getString(context, PREF_SIM_ID);
        String savedPhone = PrefUtils.getString(context, PREF_PHONE);
        String savedSimUserId = PrefUtils.getString(context, PREF_SIM_USER_ID);
        String savedSimPassToken = PrefUtils.getString(context, PREF_SIM_PASS_TOKEN);
        if (savedSimId == null || savedPhone == null || savedSimUserId == null || savedSimPassToken == null) {
            Log.v(TAG, "V1 activation info incomplete, bail.");
            return false;
        }
        Log.v(TAG, "V1 activation info found.");
        ActivateInfo activateInfo = new ActivateInfo();
        activateInfo.hashedSimId = CloudCoder.hashDeviceInfo(savedSimId);
        activateInfo.phone = savedPhone;
        activateInfo.simUserId = savedSimUserId;
        activateInfo.simPassToken = savedSimPassToken;
        activateInfo.insertedToServer = false;
        infoList.add(activateInfo);
        Log.v(TAG, "Integrated " + infoList.size() + " V1 activate info entries");
        return true;
    }

    public static void clearActivateInfoV1(Context context) {
        PrefUtils.remove(context, PREF_SIM_ID);
        PrefUtils.remove(context, PREF_PHONE);
        PrefUtils.remove(context, PREF_ACTIVATED_DEV_ID);
        PrefUtils.remove(context, PREF_ACTIVATED_SIM_ID);
        PrefUtils.remove(context, PREF_SIM_USER_ID);
        PrefUtils.remove(context, PREF_SIM_PASS_TOKEN);
        PrefUtils.remove(context, PREF_SIM_PSECURITY);
        Log.v(TAG, "Cleared activate info v1");
    }
}
