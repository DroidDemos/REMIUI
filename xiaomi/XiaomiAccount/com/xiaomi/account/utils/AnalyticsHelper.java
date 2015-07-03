package com.xiaomi.account.utils;

import miui.analytics.Analytics;

public class AnalyticsHelper {
    public static final String ACCOUNT_SETTINGS_USER_AVATAR_UPDATE = "v6_account_settings_user_avatar_update";
    public static final String ACCOUNT_SETTINGS_USER_BIRTHDAY_UPDATE = "v6_account_settings_user_birthday_update";
    public static final String ACCOUNT_SETTINGS_USER_GENDER_UPDATE = "v6_account_settings_user_gender_update";
    public static final String ACCOUNT_SETTINGS_USER_NAME_UPDATE = "v6_account_settings_user_name_update";
    public static final String CONFIRM_ACTIVATE_POPUP_DOWNLINK = "v6_confirm_activate_popup_downlink";
    public static final String CONFIRM_ACTIVATE_POPUP_UPLINK = "v6_confirm_activate_popup_uplink";
    private static final boolean ENABLE_ANALYTICS = true;
    public static final String EXCEPTION_INFO = "exception_info";
    public static final String LOGIN_CORRECT_CAPTCHA = "v6_login_correct_captcha";
    public static final String LOGIN_ERROR_AUTH_FAILURE = "v6_login_err_auth";
    public static final String LOGIN_ERROR_CAPTCHA = "v6_login_err_captcha";
    public static final String LOGIN_ERROR_FORBIDDEN = "v6_login_err_forbidden";
    public static final String LOGIN_ERROR_ILLEGAL_DEVICE = "v6_login_err_illegal_device";
    public static final String LOGIN_ERROR_INVALID_RESPONSE = "v6_login_err_invalid_response";
    public static final String LOGIN_ERROR_IO = "v6_login_err_io";
    public static final String LOGIN_ERROR_PASS_TOKEN = "v6_login_err_pass_token";
    public static final String LOGIN_ERROR_PWD = "v6_login_err_pwd";
    public static final String LOGIN_ERROR_SSL_HANDSHAKE = "v6_login_err_ssl_handshake";
    public static final String LOGIN_ERROR_UNEXPECTED_INVALID_USER_NAME = "v6_login_err_unexpected_invalid_user_name";
    public static final String LOGIN_ERROR_USER_NAME = "v6_login_err_user_name";
    public static final String LOGIN_ERROR_VERIFICATION = "v6_login_err_verification";
    public static final String LOGIN_NEED_CAPTCHA = "v6_login_need_captcha";
    public static final String LOGIN_NEED_VERIFICATION_AFTER_PASSTOKEN = "v6_login_need_verification_after_passtoken";
    public static final String LOGIN_NEED_VERIFICATION_AFTER_PASSWORD = "v6_login_need_verification_after_password";
    public static final String LOGIN_SUCCESS_BY_PASS_TOKEN = "v6_login_success_by_pass_token";
    public static final String LOGIN_SUCCESS_BY_PWD = "v6_login_success_by_pwd";
    public static final String LOGIN_SUCCESS_BY_VERIFICATION = "v6_login_success_verification";
    public static final String PACKAGE_NAME = "package_name";
    public static final String REGISTER_BY_EMAIL = "v6_register_by_email";
    public static final String REGISTER_BY_SIM = "v6_register_by_sim";
    public static final String REGISTER_ERROR_CAPTCHA = "v6_register_error_captcha";
    public static final String REGISTER_ERROR_INVALID_DEV_ID = "v6_register_error_invalid_dev_id";
    public static final String REGISTER_ERROR_NETWORK = "v6_register_error_network";
    public static final String REGISTER_ERROR_PHONE_ERROR = "v6_register_error_phone_error";
    public static final String REGISTER_ERROR_PHONE_USED = "v6_register_error_phone_used";
    public static final String REGISTER_ERROR_SERVER = "v6_register_error_server";
    public static final String REGISTER_ERROR_SIM_NOT_READY = "v6_register_error_sim_not_ready";
    public static final String REGISTER_ERROR_UNKNOWN = "v6_register_error_unknown";
    public static final String REGISTER_ERROR_VCODE = "v6_register_error_invalid_vcode";
    public static final String SHOW_ACTIVATE_POPUP_DOWNLINK = "v6_show_activate_popup_downlink";
    public static final String SHOW_ACTIVATE_POPUP_UPLINK = "v6_show_activate_popup_uplink";
    public static final String USER_ACCOUNT_SIGN_OUT_SUCCESSFULLY = "v6_user_account_sign_out_successfully";
    public static final String USER_CLICK_ACCOUNT_SIGN_OUT_BTN = "v6_user_click_account_sign_out_btn";
    public static final String USER_ENTER_ACCOUNT_SETTINGS_PAGE = "v6_user_enter_account_settings_page";
    public static final String USER_ENTER_BINDED_DEVICES_PAGE = "v6_user_enter_binded_devices_page";
    public static final String USER_ENTER_BINDED_SNS_LIST_PAGE = "v6_user_enter_binded_sns_list_page";
    public static final String USER_ENTER_CLOUD_SERVICE_FROM_ACCOUNT_SETTINGS = "v6_user_enter_cloud_service_from_account_settings";
    public static final String USER_ENTER_DEVICE_LIST_PAGE = "v6_user_enter_binded_device_list_page";
    public static final String USER_ENTER_EMAIL_SENT_NOTICE_PAGE = "v6_user_enter_email_sent_notice_page";
    public static final String USER_ENTER_FIND_DEVICE_PAGE = "v6_user_enter_find_device_page";
    public static final String USER_ENTER_FOREIGN_LOGIN_PAGE = "v6_user_enter_foreign_login_page";
    public static final String USER_ENTER_INPUT_EMAIL_PAGE = "v6_user_enter_input_email_page";
    public static final String USER_ENTER_INPUT_PASSWORD_PAGE = "v6_user_enter_input_password_page";
    public static final String USER_ENTER_INPUT_PHONE_PAGE = "v6_user_enter_input_phone_page";
    public static final String USER_ENTER_INPUT_VERIFY_CODE_PAGE = "v6_user_enter_input_verify_code_page";
    public static final String USER_ENTER_LOGIN_PAGE = "v6_user_enter_login_page";
    public static final String USER_ENTER_LOGIN_STEP2_PAGE = "v6_user_enter_login_step2_page";
    public static final String USER_ENTER_MIBI_FROM_ACCOUNT_SETTINGS = "v6_user_enter_mibi_from_account_settings";
    public static final String USER_ENTER_MIPAY_FROM_ACCOUNT_SETTINGS = "v6_user_enter_mipay_from_account_settings";
    public static final String USER_ENTER_MISERVICE_FROM_ACCOUNT_SETTINGS = "v6_user_enter_miservice_from_account_settings";
    public static final String USER_ENTER_OTHER_DEVICE_DETAIL_INFO_PAGE = "v6_user_enter_binded_other_device_detail_info_page";
    public static final String USER_ENTER_SELECT_ACCOUNT_TYPE_PAGE = "v6_user_enter_select_account_type_page";
    public static final String USER_ENTER_SMS_ALERT_PAGE = "v6_user_enter_sms_alert_page";
    public static final String USER_ENTER_SYNC_SETTING_PAGE = "v6_user_enter_sync_setting_page";
    public static final String USER_ENTER_USER_DETAIL_INFO_PAGE = "v6_user_enter_user_detail_info_page";
    public static final String USER_ENTER_WELCOME_PAGE = "v6_user_enter_welcome_page";
    public static final String USER_FOREIGN_LOGIN = "v6_user_foreign_login";
    public static final String USER_FORGOT_PASSWORD = "v6_user_forgot_pwd";
    public static final String USER_OPEN_ACCOUNT_SETTINGS = "v6_user_open_account_settings";
    public static final String USER_SKIP_ON_SETUP_GUIDE = "v6_user_skip_on_setup_guide";
    public static final String USER_START_SIGN_IN = "v6_user_sign_in";
    public static final String USER_START_SIGN_IN_ON_GUIDE = "v6_user_sign_in_on_setup_guide";
    public static final String USER_START_SIGN_UP = "v6_user_sign_up";
    public static final String USER_START_SIGN_UP_ON_GUIDE = "v6_user_sign_up_on_setup_guide";

    public static void trackEvent(Analytics analytics, String eventId) {
        analytics.trackEvent(eventId);
    }

    public static void trackTimedEvent(Analytics analytics, String eventId) {
        analytics.trackTimedEvent(eventId, ENABLE_ANALYTICS);
    }

    public static void endTimedEvent(Analytics analytics, String eventId) {
        analytics.endTimedEvent(eventId);
    }

    public static void trackEvent(Analytics analytics, String eventId, Object obj) {
        analytics.trackEvent(eventId, obj);
    }
}
