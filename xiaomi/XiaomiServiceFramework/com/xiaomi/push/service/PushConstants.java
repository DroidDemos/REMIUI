package com.xiaomi.push.service;

public abstract class PushConstants {
    public static String ACTION_BATCH_SEND_MESSAGE = null;
    public static String ACTION_CHANGE_HOST = null;
    public static final String ACTION_CHANNEL_CLOSED = "com.xiaomi.push.channel_closed";
    public static final String ACTION_CHANNEL_OPENED = "com.xiaomi.push.channel_opened";
    public static String ACTION_CLOSE_CHANNEL = null;
    public static String ACTION_FORCE_RECONNECT = null;
    public static final String ACTION_KICKED_BY_SERVER = "com.xiaomi.push.kicked";
    public static String ACTION_OPEN_CHANNEL = null;
    public static String ACTION_PING_TIMER = null;
    public static final String ACTION_RECEIVE_NEW_IQ = "com.xiaomi.push.new_iq";
    public static final String ACTION_RECEIVE_NEW_MESSAGE = "com.xiaomi.push.new_msg";
    public static final String ACTION_RECEIVE_NEW_PRESENCE = "com.xiaomi.push.new_pres";
    public static String ACTION_RESET_CONNECTION = null;
    public static String ACTION_SEND_IQ = null;
    public static String ACTION_SEND_MESSAGE = null;
    public static String ACTION_SEND_PRESENCE = null;
    public static final String ACTION_SERVICE_STARTED = "com.xiaomi.push.service_started";
    public static String ACTION_UPDATE_CHANNEL_INFO = null;
    public static final int ERROR_ACCESS_DENIED = 4;
    public static final int ERROR_AUTH_FAILED = 5;
    public static final int ERROR_BIND_TIMEOUT = 21;
    public static final int ERROR_CONNECTIING_TIMEOUT = 18;
    public static final int ERROR_MULTI_LOGIN = 6;
    public static final int ERROR_NETWORK_FAILED = 3;
    public static final int ERROR_NETWORK_NOT_AVAILABLE = 2;
    public static final int ERROR_NO_CLIENT = 12;
    public static final int ERROR_OK = 0;
    public static final int ERROR_PING_TIMEOUT = 22;
    public static final int ERROR_READ_ERROR = 9;
    public static final int ERROR_READ_TIMEOUT = 17;
    public static final int ERROR_RECEIVE_TIMEOUT = 8;
    public static final int ERROR_REDIRECT = 20;
    public static final int ERROR_RESET = 11;
    public static final int ERROR_SEND_ERROR = 10;
    public static final int ERROR_SERVER_ERROR = 7;
    public static final int ERROR_SERVER_STREAM = 13;
    public static final int ERROR_SERVICE_DESTROY = 15;
    public static final int ERROR_SERVICE_NOT_INSTALLED = 1;
    public static final int ERROR_SESSION_CHANGED = 16;
    public static final int ERROR_THREAD_BLOCK = 14;
    public static final int ERROR_USER_BLOCKED = 19;
    public static String EXTRA_AUTH_METHOD = null;
    public static final String EXTRA_BODY_ENCODE = "ext_body_encode";
    public static String EXTRA_CHANNEL_ID = null;
    public static final String EXTRA_CHID = "ext_chid";
    public static String EXTRA_CLIENT_ATTR = null;
    public static String EXTRA_CLOUD_ATTR = null;
    public static final String EXTRA_ENCYPT = "ext_encrypt";
    public static final String EXTRA_ERROR = "ext_ERROR";
    public static final String EXTRA_ERROR_CODE = "ext_err_code";
    public static final String EXTRA_ERROR_CONDITION = "ext_err_cond";
    public static final String EXTRA_ERROR_MESSAGE = "ext_err_msg";
    public static final String EXTRA_ERROR_REASON = "ext_err_reason";
    public static final String EXTRA_ERROR_TYPE = "ext_err_type";
    public static final String EXTRA_EXTENSIONS = "ext_exts";
    public static final String EXTRA_EXTENSION_ELEMENT_NAME = "ext_ele_name";
    public static final String EXTRA_EXTENSION_NAMESPACE = "ext_ns";
    public static final String EXTRA_EXTENSION_TEXT = "ext_text";
    public static final String EXTRA_FROM = "ext_from";
    public static final String EXTRA_IQ_TYPE = "ext_iq_type";
    public static String EXTRA_KICK = null;
    public static final String EXTRA_KICK_REASON = "ext_kick_reason";
    public static final String EXTRA_KICK_TYPE = "ext_kick_type";
    public static final String EXTRA_MESSAGE_APPID = "ext_msg_appid";
    public static final String EXTRA_MESSAGE_BODY = "ext_msg_body";
    public static final String EXTRA_MESSAGE_ENCRYPT = "ext_msg_encrypt";
    public static final String EXTRA_MESSAGE_FSEQ = "ext_msg_fseq";
    public static final String EXTRA_MESSAGE_LANGUAGE = "ext_msg_lang";
    public static final String EXTRA_MESSAGE_MSEQ = "ext_msg_mseq";
    public static final String EXTRA_MESSAGE_SEQ = "ext_msg_seq";
    public static final String EXTRA_MESSAGE_STATUS = "ext_msg_status";
    public static final String EXTRA_MESSAGE_SUBJECT = "ext_msg_sub";
    public static final String EXTRA_MESSAGE_THREAD = "ext_msg_thread";
    public static final String EXTRA_MESSAGE_TRANSIENT = "ext_msg_trans";
    public static final String EXTRA_MESSAGE_TYPE = "ext_msg_type";
    public static String EXTRA_NOTIFY_ID = null;
    public static String EXTRA_PACKAGE_NAME = null;
    public static final String EXTRA_PACKET = "ext_packet";
    public static final String EXTRA_PACKETS = "ext_packets";
    public static final String EXTRA_PACKET_ID = "ext_pkt_id";
    public static final String EXTRA_PRES_MODE = "ext_pres_mode";
    public static final String EXTRA_PRES_PRIORITY = "ext_pres_prio";
    public static final String EXTRA_PRES_STATUS = "ext_pres_status";
    public static final String EXTRA_PRES_TYPE = "ext_pres_type";
    public static final String EXTRA_REASON = "ext_reason";
    public static final String EXTRA_REASON_MSG = "ext_reason_msg";
    public static String EXTRA_SECURITY = null;
    public static String EXTRA_SESSION = null;
    public static String EXTRA_SID = null;
    public static final String EXTRA_SUCCEEDED = "ext_succeeded";
    public static final String EXTRA_TO = "ext_to";
    public static String EXTRA_TOKEN = null;
    public static String EXTRA_USER_ID = null;
    public static final String MIPUSH_ACTION_CLEAR_NOTIFICATION = "com.xiaomi.mipush.CLEAR_NOTIFICATION";
    public static final String MIPUSH_ACTION_ERROR = "com.xiaomi.mipush.ERROR";
    public static final String MIPUSH_ACTION_NEW_MESSAGE = "com.xiaomi.mipush.RECEIVE_MESSAGE";
    public static final String MIPUSH_ACTION_REGISTER_APP = "com.xiaomi.mipush.REGISTER_APP";
    public static final String MIPUSH_ACTION_SEND_MESSAGE = "com.xiaomi.mipush.SEND_MESSAGE";
    public static final String MIPUSH_ACTION_UNREGISTER_APP = "com.xiaomi.mipush.UNREGISTER_APP";
    public static final String MIPUSH_CHANNEL = "5";
    public static final int MIPUSH_ERROR_AUTHERICATION_ERROR = 70000002;
    public static final int MIPUSH_ERROR_INTERNAL_ERROR = 70000004;
    public static final int MIPUSH_ERROR_INVALID_PAYLOAD = 70000003;
    public static final int MIPUSH_ERROR_SERVICE_UNAVAILABLE = 70000001;
    public static final String MIPUSH_EXTRA_APP_ID = "mipush_app_id";
    public static final String MIPUSH_EXTRA_APP_PACKAGE = "mipush_app_package";
    public static final String MIPUSH_EXTRA_ENV_CHANAGE = "mipush_env_chanage";
    public static final String MIPUSH_EXTRA_ENV_TYPE = "mipush_env_type";
    public static final String MIPUSH_EXTRA_ERROR_CODE = "mipush_error_code";
    public static final String MIPUSH_EXTRA_ERROR_MSG = "mipush_error_msg";
    public static final String MIPUSH_EXTRA_PAYLOAD = "mipush_payload";
    public static final String MIPUSH_EXTRA_SESSION = "mipush_session";
    public static final String MIPUSH_SDK_VERSION = "2";
    public static final String OMS_CHANNEL = "6";
    public static final String PUSH_SERVICE_CLASS_NAME = "com.xiaomi.xmsf.push.service.XMPushService";
    public static final String PUSH_SERVICE_CLASS_NAME_JAR = "com.xiaomi.push.service.XMPushService";
    public static final String PUSH_SERVICE_PACKAGE_NAME = "com.xiaomi.xmsf";

    static {
        ACTION_OPEN_CHANNEL = "com.xiaomi.push.OPEN_CHANNEL";
        ACTION_SEND_MESSAGE = "com.xiaomi.push.SEND_MESSAGE";
        ACTION_SEND_IQ = "com.xiaomi.push.SEND_IQ";
        ACTION_BATCH_SEND_MESSAGE = "com.xiaomi.push.BATCH_SEND_MESSAGE";
        ACTION_SEND_PRESENCE = "com.xiaomi.push.SEND_PRES";
        ACTION_CLOSE_CHANNEL = "com.xiaomi.push.CLOSE_CHANNEL";
        ACTION_FORCE_RECONNECT = "com.xiaomi.push.FORCE_RECONN";
        ACTION_RESET_CONNECTION = "com.xiaomi.push.RESET_CONN";
        ACTION_UPDATE_CHANNEL_INFO = "com.xiaomi.push.UPDATE_CHANNEL_INFO";
        ACTION_CHANGE_HOST = "com.xiaomi.push.CHANGE_HOST";
        ACTION_PING_TIMER = "com.xiaomi.push.PING_TIMER";
        EXTRA_USER_ID = "ext_user_id";
        EXTRA_CHANNEL_ID = EXTRA_CHID;
        EXTRA_SID = "ext_sid";
        EXTRA_TOKEN = "ext_token";
        EXTRA_AUTH_METHOD = "ext_auth_method";
        EXTRA_SECURITY = "ext_security";
        EXTRA_KICK = "ext_kick";
        EXTRA_CLIENT_ATTR = "ext_client_attr";
        EXTRA_CLOUD_ATTR = "ext_cloud_attr";
        EXTRA_PACKAGE_NAME = "ext_pkg_name";
        EXTRA_NOTIFY_ID = "ext_notify_id";
        EXTRA_SESSION = "ext_session";
    }

    public static String getErrorDesc(int errorCode) {
        switch (errorCode) {
            case ERROR_OK /*0*/:
                return "ERROR_OK";
            case ERROR_SERVICE_NOT_INSTALLED /*1*/:
                return "ERROR_SERVICE_NOT_INSTALLED";
            case ERROR_NETWORK_NOT_AVAILABLE /*2*/:
                return "ERROR_NETWORK_NOT_AVAILABLE";
            case ERROR_NETWORK_FAILED /*3*/:
                return "ERROR_NETWORK_FAILED";
            case ERROR_ACCESS_DENIED /*4*/:
                return "ERROR_ACCESS_DENIED";
            case ERROR_AUTH_FAILED /*5*/:
                return "ERROR_AUTH_FAILED";
            case ERROR_MULTI_LOGIN /*6*/:
                return "ERROR_MULTI_LOGIN";
            case ERROR_SERVER_ERROR /*7*/:
                return "ERROR_SERVER_ERROR";
            case ERROR_RECEIVE_TIMEOUT /*8*/:
                return "ERROR_RECEIVE_TIMEOUT";
            case ERROR_READ_ERROR /*9*/:
                return "ERROR_READ_ERROR";
            case ERROR_SEND_ERROR /*10*/:
                return "ERROR_SEND_ERROR";
            case ERROR_RESET /*11*/:
                return "ERROR_RESET";
            case ERROR_NO_CLIENT /*12*/:
                return "ERROR_NO_CLIENT";
            case ERROR_SERVER_STREAM /*13*/:
                return "ERROR_SERVER_STREAM";
            case ERROR_THREAD_BLOCK /*14*/:
                return "ERROR_THREAD_BLOCK";
            case ERROR_SERVICE_DESTROY /*15*/:
                return "ERROR_SERVICE_DESTROY";
            case ERROR_SESSION_CHANGED /*16*/:
                return "ERROR_SESSION_CHANGED";
            case ERROR_READ_TIMEOUT /*17*/:
                return "ERROR_READ_TIMEOUT";
            case ERROR_CONNECTIING_TIMEOUT /*18*/:
                return "ERROR_CONNECTIING_TIMEOUT";
            case ERROR_USER_BLOCKED /*19*/:
                return "ERROR_USER_BLOCKED";
            case ERROR_REDIRECT /*20*/:
                return "ERROR_REDIRECT";
            case ERROR_BIND_TIMEOUT /*21*/:
                return "ERROR_BIND_TIMEOUT";
            case ERROR_PING_TIMEOUT /*22*/:
                return "ERROR_PING_TIMEOUT";
            default:
                return String.valueOf(errorCode);
        }
    }
}
