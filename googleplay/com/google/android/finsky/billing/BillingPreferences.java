package com.google.android.finsky.billing;

import com.google.android.finsky.config.PreferenceFile;
import com.google.android.finsky.config.PreferenceFile.SharedPreference;

public class BillingPreferences {
    public static SharedPreference<String> ACCEPTED_CARRIER_TOS_VERSION;
    public static SharedPreference<String> BILLING_COUNTRIES;
    public static SharedPreference<Boolean> DEVICE_ASSOCIATION_NEEDED;
    public static SharedPreference<Long> EARLIEST_PROVISIONING_CHECK_TIME_MILLIS;
    public static SharedPreference<Long> LAST_BILLING_COUNTRIES_REFRESH_MILLIS;
    public static SharedPreference<Long> LAST_DCB3_PROVISIONING_TIME_MILLIS;
    public static SharedPreference<Long> LAST_PROVISIONING_TIME_MILLIS;
    private static PreferenceFile sBillingPrefs;

    static {
        sBillingPrefs = new PreferenceFile("billing_preferences", 0);
        LAST_PROVISIONING_TIME_MILLIS = sBillingPrefs.value("last_dcb_provisioning_time_millis", Long.valueOf(0));
        EARLIEST_PROVISIONING_CHECK_TIME_MILLIS = sBillingPrefs.value("earliest_dcb_provisioning_check_time_millis", Long.valueOf(0));
        BILLING_COUNTRIES = sBillingPrefs.value("billing_countries_v2", (String) null);
        LAST_BILLING_COUNTRIES_REFRESH_MILLIS = sBillingPrefs.value("last_billing_countries_check_v2", Long.valueOf(0));
        ACCEPTED_CARRIER_TOS_VERSION = sBillingPrefs.value("accepted_carrier_tos_version", (String) null);
        DEVICE_ASSOCIATION_NEEDED = sBillingPrefs.value("device_association_needed", Boolean.valueOf(false));
        LAST_DCB3_PROVISIONING_TIME_MILLIS = sBillingPrefs.value("last_dcb3_provisioning_time_millis", Long.valueOf(0));
    }
}
