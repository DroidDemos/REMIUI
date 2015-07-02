package com.xiaomi.activate;

import com.xiaomi.miui.analyticstracker.AnalyticsTracker;

public class AnalyticsHelper {
    public static final String ACTIVATE_CREATE_ACCOUNT_FAIL = "v6_%s_create_account_fail";
    public static final String ACTIVATE_CREATE_ACCOUNT_VKEY_EXPIRATION = "v6_%s_create_account_vkey_expiration";
    public static final String ACTIVATE_CREATE_ACCOUNT_VKEY_UNEXPECTED_EXPIRATION = "v6_%s_create_account_unexpected_vkey_expiration";
    public static final String ACTIVATE_CREATE_USER_FAIL = "v6_%s_create_user_fail";
    public static final String ACTIVATE_CREATE_USER_START = "v6_%s_create_user_start";
    public static final String ACTIVATE_CREATE_XIAOMI_ACCOUNT_EXIST = "v6_%s_create_xiaomi_account_exist";
    public static final String ACTIVATE_CREATE_XIAOMI_ACCOUNT_FAIL = "v6_%s_create_xiaomi_account_fail";
    public static final String ACTIVATE_CREATE_XIAOMI_ACCOUNT_START = "v6_%s_create_xiaomi_account_start";
    public static final String ACTIVATE_ERROR_NOTIFY_SIM_NOT_ACTIVATED = "v6_activate_sim_not_activated";
    public static final String ACTIVATE_GET_PHONE_START = "v6_%s_get_phone_start";
    public static final String ACTIVATE_GET_PHONE_VKEY_EXPIRATION = "v6_%s_get_vkey_expiration";
    public static final String ACTIVATE_GET_PHONE_VKEY_NOT_EXIST = "v6_%s_get_vkey_not_exist";
    public static final String ACTIVATE_GET_PHONE_VKEY_UNEXPECTED_EXPIRATION = "v6_%s_get_vkey_unexpected_vkey_expiration";
    public static final String ACTIVATE_GET_VKEY_FAIL = "v6_%s_get_vkey_fail";
    public static final String ACTIVATE_GET_VKEY_START = "v6_%s_get_vkey_start";
    public static final String ACTIVATE_SEND_SMS_FAIL = "v6_activate_send_sms_fail";
    public static final String ACTIVATE_SEND_SMS_START = "v6_activate_send_sms_start";
    public static final String ACTIVATE_SIM_ID_CHANGE = "v6_activate_sim_id_change";
    public static final String ACTIVATE_START = "v6_%s_start";
    public static final String ACTIVATE_SUCCESS = "v6_%s_success";
    public static final String ACTIVATE_WAIT_FOR_SERVER_INFO_FAIL = "v6_activate_wait_for_server_info_fail";
    public static final String ACTIVATE_WAIT_FOR_SERVER_INFO_START = "v6_activate_wait_for_server_info_start";
    public static final String ACTIVATE_WAIT_FOR_SMS = "v6_%s_wait_for_sms";
    public static final String ACTIVATE_WAIT_FOR_SMS_FAIL = "v6_%s_wait_for_sms_fail";
    public static final String ACTIVATE_WAIT_FOR_SMS_SUCCESS = "v6_%s_wait_for_sms_success";
    public static final String CANCEL_ACTIVATE_UPLOAD_DIAGNOSIS_INFO = "cancel_activate_upload_diagnosis_info";
    public static final String CONFIRM_ACTIVATE_POPUP_DOWNLINK = "confirm_activate_popup_downlink";
    public static final String CONFIRM_ACTIVATE_POPUP_UPLINK = "confirm_activate_popup_uplink";
    public static final String CONFIRM_ACTIVATE_UPLOAD_DIAGNOSIS_INFO = "confirm_activate_upload_diagnosis_info";
    public static final boolean ENABLE_ANALYTICS = true;
    public static final String SHOW_ACTIVATE_POPUP_DOWNLINK = "show_activate_popup_downlink";
    public static final String SHOW_ACTIVATE_POPUP_UPLINK = "show_activate_popup_uplink";
    public static final String SIM_PASS_TOKEN_EXPIRED = "v6_sim_pass_token_expired";
    public static final String SIM_PASS_TOKEN_VALID = "v6_sim_pass_token_valid";

    public static void trackEvent(AnalyticsTracker analytics, String eventId) {
        analytics.trackEvent(eventId);
    }

    public static void trackTimedEvent(AnalyticsTracker analytics, String eventId) {
        analytics.trackTimedEvent(eventId, ENABLE_ANALYTICS);
    }

    public static void endTimedEvent(AnalyticsTracker analytics, String eventId) {
        analytics.endTimedEvent(eventId);
    }

    public static void trackEvent(AnalyticsTracker analytics, String eventId, Object obj) {
        analytics.trackEvent(eventId, obj);
    }
}
