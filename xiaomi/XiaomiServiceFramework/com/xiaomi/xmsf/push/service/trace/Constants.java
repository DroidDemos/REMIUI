package com.xiaomi.xmsf.push.service.trace;

class Constants {
    public static final String ACTIONTYPE_CLICK = "click";
    public static final String ACTIONTYPE_CLOSE = "close";
    public static final String ACTIONTYPE_FIRSTSHOW = "show";
    public static final String ACTIONTYPE_RECEIVED = "received";
    public static final String ACTIONTYPE_REMOVE = "remove";
    public static final int ADS_TYPE_BUBBLE = 2;
    public static final int ADS_TYPE_NOTIFY = 1;
    public static final String JSON_TAG_ACTIONTIME = "actionTime";
    public static final String JSON_TAG_ACTIONTYPE = "actionType";
    public static final String JSON_TAG_ADID = "adId";
    public static final String JSON_TAG_ADLIST = "adList";
    public static final String JSON_TAG_CONTENT = "content";
    public static final String JSON_TAG_IMEI = "imei";
    public static final String JSON_TAG_INDEX = "index";
    public static final String JSON_TAG_IP = "ip";
    public static final String JSON_TAG_SHOWTYPE = "showType";
    public static final String JSON_TAG_USERID = "userId";
    public static final String LOG_TAG = "tracelogtag";
    public static final String LOG_TAG_STATUS = "tracestatus";
    public static final int RET_ERROR = 1;
    public static final int RET_OK = 0;
    static final String URL_NOTIFY_TRACKLOG_DEBUG = "http://test.new.api.ad.xiaomi.com/logNotificationAdActions";
    static final String URL_NOTIFY_TRACKLOG_OFFICIAL = "http://new.api.ad.xiaomi.com/logNotificationAdActions";

    Constants() {
    }
}
