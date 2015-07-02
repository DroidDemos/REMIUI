package com.xiaomi.xmsf.push.service;

public final class Constants {
    public static final int ADS_TYPE_BUBBLE = 1;
    public static final int ADS_TYPE_NOTIFY = 2;
    public static final int BUBBLE_LIMIT_TIMES = 4;
    public static final boolean DEBUG_MODE = false;
    public static int DOWNLOAD_BUFFER = 0;
    public static final String EXTRA_KEY_ADS = "ads_message";
    public static final String EXTRA_KEY_PKGNAME = "miui_package_name";
    public static final String HTTP_RESPONSE_STATUS_FAILURE = "failure";
    public static final String HTTP_RESPONSE_STATUS_SUCCESS = "success";
    public static final String INTENT_ACTION_MIPUSH_MIUI_CLICK_MESSAGE = "com.xiaomi.mipush.miui.CLICK_MESSAGE";
    public static final String INTENT_ACTION_MIPUSH_MIUI_RECEIVE_MESSAGE = "com.xiaomi.mipush.miui.RECEIVE_MESSAGE";
    public static final String INTENT_EXTRA_MIPUSHMESSAGE = "pendingintent";
    public static final String INTENT_FLAG_NOTIFYID = "notifyid";
    public static final String INTENT_FLAG_TYPE = "intenttype";
    public static final int INTENT_TYPE_ACTIONCLICK = 3;
    public static final int INTENT_TYPE_CLICK = 2;
    public static final int INTENT_TYPE_DELETE = 1;
    public static final String JSON_TAG_ACTIONTEXT = "actionText";
    public static final String JSON_TAG_ACTION_URL = "actionUrl";
    public static final String JSON_TAG_ADSTYPE = "type";
    public static final String JSON_TAG_CONTENT = "content";
    public static final String JSON_TAG_DOWNLOAD_IMG_PATH = "downloadImagePath";
    public static final String JSON_TAG_ID = "id";
    public static final String JSON_TAG_IMAURL = "imgUrl";
    public static final String JSON_TAG_LASTSHOWTIME = "lastShowTime";
    public static final String JSON_TAG_NONSENSE = "nonsense";
    public static final String JSON_TAG_PRITEXT = "priText";
    public static final String JSON_TAG_SECTEXT = "secText";
    public static final String JSON_TAG_SHOWTYPE = "showType";
    public static final String JSON_TAG_STATUS = "status";
    public static final String JSON_TAG_SUBADID = "subAdId";
    public static final String JSON_TAG_SUBADINFO = "subAdInfo";
    public static final String JSON_TAG_TITTEXT = "titText";
    public static final String JSON_TAG_UPPERBOUND = "receiveUpperBound";
    public static final boolean LOG_OPEN = true;
    public static final String PREFER_KEY_BUBBLE_SUCCESS_COUNT = "bubblecount";
    public static final String PREFER_KEY_NOTIFY_SUCCESS_COUNT = "notifycount";
    public static final String PREFER_KEY_STARTTIME = "starttime";
    public static final int RET_ERROR = -1;
    public static final int RET_EXPIRED = -4;
    public static final int RET_LIMITREACH = -3;
    public static final int RET_NOSENSE = -2;
    public static final int RET_NOTMATCHED = -5;
    public static final int RET_NOT_PERMITTED = -6;
    public static final int RET_OK = 0;
    public static final String TAG = "MIUIADSPUSH";
    public static final String TYPE_ADS_APP = "app";
    public static final String TYPE_ADS_OPEN = "open";
    public static final String TYPE_ADS_WEB = "web";
    public static final int VALUE_MAX_FAILEDCOUNT = 10;
    public static final String XMSF_ALIAS_PREFIX = "com.xiaomi.miui.pushads.sdk";
    public static final String XMSF_TOPIC_PREFIX = "MIUI_XMSF_271828_TOPIC_";

    static {
        DOWNLOAD_BUFFER = 8192;
    }
}
