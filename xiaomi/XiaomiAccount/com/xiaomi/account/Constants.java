package com.xiaomi.account;

import com.xiaomi.accountsdk.account.XMPassport;

public class Constants {
    public static final String ACCOUNT_AVATAR_FILE_NAME = "acc_avatar_file_name";
    public static final String ACCOUNT_AVATAR_URL = "acc_avatar_url";
    public static final String ACCOUNT_MIBI_SIGN = "acc_mibi_sign";
    public static final String ACCOUNT_NICK_NAME = "acc_nick_name";
    public static final String ACCOUNT_TYPE = "com.xiaomi";
    public static final String ACCOUNT_USER_BIRTHDAY = "acc_user_birthday";
    public static final String ACCOUNT_USER_EMAIL = "acc_user_email";
    public static final String ACCOUNT_USER_GENDER = "acc_user_gender";
    public static final String ACCOUNT_USER_NAME = "acc_user_name";
    public static final String ACCOUNT_USER_PHONE = "acc_user_phone";
    public static final String ACTION_LOGIN = "com.xiaomi.account.action.XIAOMI_ACCOUNT_LOGIN";
    public static final String ACTION_MICLOUD_SPACE_INFO_CHANGED = "com.xiaomi.action.MICLOUD_SPACE_INFO_CHANGED";
    public static final String ACTION_QUERY_DEVICE_INFO_SUCCEED = "com.xiaomi.action.QUERY_DEVICE_INFO_SUCCEED";
    public static final String ACTION_QUERY_DEVICE_LIST_SUCCEED = "com.xiaomi.action.QUERY_DEVICE_LIST_SUCCEED";
    public static final String ACTION_REG = "com.xiaomi.account.action.XIAOMI_ACCOUNT_REG";
    public static final String ACTION_WELCOME = "com.xiaomi.account.action.XIAOMI_ACCOUNT_WELCOME";
    public static final String ACTION_XIAOMI_SNS_INFO_CHANGED = "com.xiaomi.action.XIAOMI_SNS_INFO_CHANGED";
    public static final String ACTION_XIAOMI_USER_PROFILE_CHANGED = "com.xiaomi.action.XIAOMI_USER_PROFILE_CHANGED";
    public static final String AVATAR_FILE_NAME = "xiaomi_user_avatar_";
    public static final boolean DEBUG = true;
    public static final String DEVICE_INFO_QUERY_RESULT = "device_info_query_result";
    public static final String DEVICE_INFO_SID = "deviceinfo";
    public static final boolean ENABLE_ANALYTICS = true;
    public static final String EXTRA_ACCOUNT = "extra_account";
    public static final String EXTRA_ACTIVATED_IMSI = "extra_activated_imsi";
    public static final String EXTRA_ADD_ACCOUNT_PROMPT = "extra_add_account_prompt";
    public static final String EXTRA_CALLBACK = "extra_callback";
    public static final String EXTRA_CAPTCHA_URL = "extra_captcha_url";
    public static final String EXTRA_ERROR = "extra_error";
    public static final String EXTRA_FACEBOOK_ACCESSTOKEN = "extra_facebook_access_token";
    public static final String EXTRA_FACEBOOK_USER_AVATAR_ABS_PATH = "extra_facebook_user_avatar_abs_path";
    public static final String EXTRA_FACEBOOK_USER_ID = "extra_facebook_user_id";
    public static final String EXTRA_FACEBOOK_USER_NAME = "extra_facebook_user_name";
    public static final String EXTRA_FIND_DEVICE_ENABLED = "extra_find_device_enabled";
    public static final String EXTRA_FROM_BACK_NAVIGATION = "flag_from_back_nav";
    public static final String EXTRA_MICLOUD_SPACE_STATUS = "extra_micloud_space_status";
    public static final String EXTRA_MICLOUD_STATUS_INFO_QUOTA = "extra_micloud_status_info_quota";
    public static final String EXTRA_NOTIFICATON_URL = "notification_url";
    public static final String EXTRA_QQ_ACCESSTOKEN = "extra_qq_access_token";
    public static final String EXTRA_QQ_USER_AVATAR_ABS_PATH = "extra_qq_user_avatar_abs_path";
    public static final String EXTRA_QQ_USER_ID = "extra_qq_user_id";
    public static final String EXTRA_QQ_USER_NAME = "extra_qq_user_name";
    public static final String EXTRA_QS = "extra_qs";
    public static final String EXTRA_RESET_COUNT = "extra_reset_count";
    public static final String EXTRA_SERVICE_URL = "extra_service_url";
    public static final String EXTRA_SIGN = "extra_sign";
    public static final String EXTRA_SINA_WEIBO_ACCESSTOKEN = "extra_sina_weibo_access_token";
    public static final String EXTRA_SINA_WEIBO_USER_AVATAR_ABS_PATH = "extra_sina_weibo_user_avatar_abs_path";
    public static final String EXTRA_SINA_WEIBO_USER_ID = "extra_sina_weibo_user_id";
    public static final String EXTRA_SINA_WEIBO_USER_NAME = "extra_sina_weibo_user_name";
    public static final String EXTRA_STEP1_TOKEN = "extra_step1_token";
    public static final String EXTRA_TITLE = "extra_title";
    public static final String EXTRA_USER_NAME = "extra_username";
    public static final String EXTRA_USER_PWD = "extra_pwd";
    public static final String EXTRA_VERIFY_ONLY = "verify_only";
    public static final String FACEBOOK_SNS_TYPE = "FB_XIAOMI";
    public static final String KEY_RESULT = "result";
    public static final String LIST_POSITION_PARAM = "list_position_param";
    public static final String MASK = "*********";
    public static final String PASSPORT_API_SID = "passportapi";
    public static final String PASSWORD_RECOVERY_URL;
    public static final String QQ_SNS_TYPE = "OPEN_QQ_XIAOMI";
    public static final String SIMPLE_DATE_FORMAT = "yyyy-MM-dd";
    public static final String SINA_WEIBO_SNS_TYPE = "SINA_WEIBO_MIUI";
    public static final int UPLOAD_DEVICE_INFO_TYPE = 0;
    public static final int UPLOAD_XIAOMI_USER_INFO_TYPE = 1;
    public static final String XIAOMI_ACCOUNT_MIBI_MARKET_ID = "118";
    public static final String XIAOMI_ACCOUNT_PACKAGE_NAME = "com.xiaomi.account";

    static {
        PASSWORD_RECOVERY_URL = XMPassport.URL_ACCOUNT_BASE + "/forgetPassword";
    }
}
