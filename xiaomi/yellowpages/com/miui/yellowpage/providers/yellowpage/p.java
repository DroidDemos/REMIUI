package com.miui.yellowpage.providers.yellowpage;

import android.content.Context;
import com.miui.yellowpage.base.reference.RefMethods.Settings;
import com.miui.yellowpage.base.utils.Log;
import com.miui.yellowpage.base.utils.Preference;
import java.util.Locale;
import miui.os.Build;
import miui.provider.ExtraSettings.System;

/* compiled from: SettingsHandler */
public class p {
    public static boolean B(Context context, String str) {
        if ("remind_user_suspect_number".equals(str)) {
            return System.getBoolean(context.getContentResolver(), "yellow_page_ignore_remind_user_suspect_number", false);
        }
        if ("online_fraud_enable".equals(str)) {
            if (aa(context) && Preference.getBoolean(context, "pref_enable_fraud_num_identification_online", false)) {
                return true;
            }
            return false;
        } else if ("online_identify_enable".equals(str)) {
            return aa(context);
        } else {
            if ("yellowpage_available".equals(str)) {
                return hz();
            }
            if ("never_remind_user_enable_antispam".equals(str)) {
                return Settings.System.isNeverRemindSmartAntispamEnable(context);
            }
            Log.d("SettingsHandler", "Not supported setting key");
            return false;
        }
    }

    public static boolean aa(Context context) {
        return hz() && Settings.System.isCloudAntispamEnable(context);
    }

    public static boolean hz() {
        Locale locale = Locale.getDefault();
        return !Build.IS_INTERNATIONAL_BUILD && (Locale.SIMPLIFIED_CHINESE.equals(locale) || Locale.TRADITIONAL_CHINESE.equals(locale) || Locale.US.equals(locale));
    }
}
