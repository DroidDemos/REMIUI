package com.xiaomi.passport.utils;

import com.xiaomi.miui.analyticstracker.AnalyticsTracker;

public class AnalyticsHelper {
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
    public static final String REG_BY_EMAIL_CONFIRM_FAIL = "reg_by_email_confirm_fail";
    public static final String REG_BY_EMAIL_CONFIRM_START = "reg_by_email_confirm_start";
    public static final String REG_BY_EMAIL_CONFIRM_SUCCESS = "reg_by_email_confirm_success";
    public static final String REG_BY_EMAIL_ERROR_EMAIL_USED = "reg_by_email_error_email_used";
    public static final String REG_BY_EMAIL_ERROR_NEED_CAPTCHA = "reg_by_email_error_need_captcha";
    public static final String REG_BY_EMAIL_ERROR_NETWORK = "reg_by_email_error_network";
    public static final String REG_BY_EMAIL_RESEND_FAIL = "reg_by_email_resend_fail";
    public static final String REG_BY_EMAIL_RESEND_START = "reg_by_email_resend_start";
    public static final String REG_BY_EMAIL_RESEND_SUCCESS = "reg_by_email_resend_success";
    public static final String REG_BY_EMAIL_START = "reg_by_email_start";
    public static final String REG_BY_EMAIL_SUCCESS = "reg_by_email_success";
    public static final String REG_BY_OTHER_PHONE_START = "reg_by_other_phone_start";
    public static final String REG_BY_OTHER_PHONE_SUCCESS = "reg_by_other_phone_sucess";
    public static final String REG_BY_PHONE_ERROR_NETWORK = "reg_by_phone_error_network";
    public static final String REG_BY_PHONE_ERROR_PHONE_ERROR = "reg_by_phone_error_phone_error";
    public static final String REG_BY_PHONE_ERROR_PHONE_USED = "reg_by_phone_error_phone_used";
    public static final String REG_BY_PHONE_ERROR_SIM_ABSENT = "reg_by_phone_error_sim_absent";
    public static final String REG_BY_PHONE_START = "reg_by_phone_start";
    public static final String REG_BY_PHONE_SUCCESS = "reg_by_phone_success";
    public static final String REG_CHECK_VERIFY_CODE_ERROR_NETWORK = "reg_check_verify_code_error_network";
    public static final String REG_CHECK_VERIFY_CODE_START = "reg_check_verify_code_start";
    public static final String REG_CHECK_VERIFY_CODE_SUCCESS = "reg_check_verify_code_sucess";
    public static final String REG_GET_VERIFY_CODE_ERROR_NETWORK = "reg_get_verify_code_error_network";
    public static final String REG_GET_VERIFY_CODE_ERROR_PHONE_USED = "reg_get_verify_code_error_phone_used";
    public static final String REG_GET_VERIFY_CODE_START = "reg_get_verify_code_start";
    public static final String REG_GET_VERIFY_CODE_SUCCESS = "reg_get_verify_code_success";
    public static final String REG_REFRESH_VERIFY_CODE_ERROR_NETWORK = "reg_refresh_verify_code_error_network";
    public static final String REG_REFRESH_VERIFY_CODE_ERROR_PHONE_USED = "reg_refresh_verify_code_error_phone_used";
    public static final String REG_REFRESH_VERIFY_CODE_START = "reg_refresh_verify_code_start";
    public static final String REG_REFRESH_VERIFY_CODE_SUCCESS = "reg_refresh_verify_code_success";
    public static final String SHOW_ACTIVATE_POPUP_DOWNLINK = "v6_show_activate_popup_downlink";
    public static final String SHOW_ACTIVATE_POPUP_UPLINK = "v6_show_activate_popup_uplink";
    public static final String USER_ENTER_EMAIL_SENT_NOTICE_PAGE = "v6_user_enter_email_sent_notice_page";
    public static final String USER_ENTER_FIND_DEVICE_PAGE = "v6_user_enter_find_device_page";
    public static final String USER_ENTER_FOREIGN_LOGIN_PAGE = "v6_user_enter_foreign_login_page";
    public static final String USER_ENTER_INPUT_EMAIL_PAGE = "v6_user_enter_input_email_page";
    public static final String USER_ENTER_INPUT_PASSWORD_PAGE = "v6_user_enter_input_password_page";
    public static final String USER_ENTER_INPUT_PHONE_PAGE = "v6_user_enter_input_phone_page";
    public static final String USER_ENTER_INPUT_VERIFY_CODE_PAGE = "v6_user_enter_input_verify_code_page";
    public static final String USER_ENTER_LOGIN_PAGE = "v6_user_enter_login_page";
    public static final String USER_ENTER_LOGIN_STEP2_PAGE = "v6_user_enter_login_step2_page";
    public static final String USER_ENTER_SELECT_ACCOUNT_TYPE_PAGE = "v6_user_enter_select_account_type_page";
    public static final String USER_ENTER_SMS_ALERT_PAGE = "v6_user_enter_sms_alert_page";
    public static final String USER_ENTER_SYNC_SETTING_PAGE = "v6_user_enter_sync_setting_page";
    public static final String USER_ENTER_WELCOME_PAGE = "v6_user_enter_welcome_page";
    public static final String USER_FOREIGN_LOGIN = "v6_user_foreign_login";
    public static final String USER_FORGOT_PASSWORD = "v6_user_forgot_pwd";
    public static final String USER_SKIP_ON_SETUP_GUIDE = "v6_user_skip_on_setup_guide";
    public static final String USER_START_SIGN_IN = "v6_user_sign_in";
    public static final String USER_START_SIGN_IN_ON_GUIDE = "v6_user_sign_in_on_setup_guide";
    public static final String USER_START_SIGN_UP = "v6_user_sign_up";
    public static final String USER_START_SIGN_UP_ON_GUIDE = "v6_user_sign_up_on_setup_guide";

    public static void trackEvent(AnalyticsTracker analyticsTracker, String eventId) {
        analyticsTracker.trackEvent(eventId);
    }

    public static void trackTimedEvent(AnalyticsTracker analyticsTracker, String eventId) {
        analyticsTracker.trackTimedEvent(eventId, ENABLE_ANALYTICS);
    }

    public static void endTimedEvent(AnalyticsTracker analyticsTracker, String eventId) {
        analyticsTracker.endTimedEvent(eventId);
    }

    public static void trackEvent(AnalyticsTracker analyticsTracker, String eventId, Object obj) {
        analyticsTracker.trackEvent(eventId, obj);
    }
}
