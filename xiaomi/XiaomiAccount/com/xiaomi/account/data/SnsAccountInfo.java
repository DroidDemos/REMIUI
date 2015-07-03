package com.xiaomi.account.data;

import com.xiaomi.account.Constants;
import com.xiaomi.account.R;

public abstract class SnsAccountInfo {
    public static final String FB_SNS_TYPE = "facebook";
    public static final String QQ_SNS_TYPE = "qq";
    public static final String SINA_SNS_TYPE = "sina";

    static class FacebookAccountInfo extends SnsAccountInfo {
        FacebookAccountInfo() {
        }

        public int getTypeName() {
            return R.string.facebook;
        }

        public String getType() {
            return SnsAccountInfo.FB_SNS_TYPE;
        }

        public String getAppId() {
            return "360453284041819";
        }

        public String getServiceId() {
            return "passport";
        }

        public String getSnsType() {
            return Constants.FACEBOOK_SNS_TYPE;
        }

        public int getTitleResId() {
            return R.string.bind_facebook;
        }

        public int getAddImgResId() {
            return R.drawable.ic_bind_facebook;
        }

        public String getAccountPath() {
            return "FB";
        }

        public String getAccessTokenKey() {
            return Constants.EXTRA_FACEBOOK_ACCESSTOKEN;
        }

        public String getUserIdKey() {
            return Constants.EXTRA_FACEBOOK_USER_ID;
        }

        public String getUserNameKey() {
            return Constants.EXTRA_FACEBOOK_USER_NAME;
        }

        public String getUserAvatarAbsPathKey() {
            return Constants.EXTRA_FACEBOOK_USER_AVATAR_ABS_PATH;
        }
    }

    static class QQAccountInfo extends SnsAccountInfo {
        QQAccountInfo() {
        }

        public int getTypeName() {
            return R.string.qq;
        }

        public String getType() {
            return SnsAccountInfo.QQ_SNS_TYPE;
        }

        public String getAppId() {
            return "100284651";
        }

        public String getServiceId() {
            return "passport";
        }

        public String getSnsType() {
            return Constants.QQ_SNS_TYPE;
        }

        public int getTitleResId() {
            return R.string.bind_qq;
        }

        public int getAddImgResId() {
            return R.drawable.ic_bind_qq;
        }

        public String getAccountPath() {
            return "OPEN_QQ";
        }

        public String getAccessTokenKey() {
            return Constants.EXTRA_QQ_ACCESSTOKEN;
        }

        public String getUserIdKey() {
            return Constants.EXTRA_QQ_USER_ID;
        }

        public String getUserNameKey() {
            return Constants.EXTRA_QQ_USER_NAME;
        }

        public String getUserAvatarAbsPathKey() {
            return Constants.EXTRA_QQ_USER_AVATAR_ABS_PATH;
        }
    }

    static class SinaAccountInfo extends SnsAccountInfo {
        SinaAccountInfo() {
        }

        public int getTypeName() {
            return R.string.sina_weibo;
        }

        public String getType() {
            return SnsAccountInfo.SINA_SNS_TYPE;
        }

        public String getAppId() {
            return "3942312923";
        }

        public String getServiceId() {
            return "weibobind";
        }

        public String getSnsType() {
            return Constants.SINA_WEIBO_SNS_TYPE;
        }

        public int getTitleResId() {
            return R.string.bind_sina_weibo;
        }

        public int getAddImgResId() {
            return R.drawable.ic_bind_sina_weibo;
        }

        public String getAccountPath() {
            return SnsAccountInfo.SINA_SNS_TYPE;
        }

        public String getAccessTokenKey() {
            return Constants.EXTRA_SINA_WEIBO_ACCESSTOKEN;
        }

        public String getUserIdKey() {
            return Constants.EXTRA_SINA_WEIBO_USER_ID;
        }

        public String getUserNameKey() {
            return Constants.EXTRA_SINA_WEIBO_USER_NAME;
        }

        public String getUserAvatarAbsPathKey() {
            return Constants.EXTRA_SINA_WEIBO_USER_AVATAR_ABS_PATH;
        }
    }

    public abstract String getAccessTokenKey();

    public abstract String getAccountPath();

    public abstract int getAddImgResId();

    public abstract String getAppId();

    public abstract String getServiceId();

    public abstract String getSnsType();

    public abstract int getTitleResId();

    public abstract String getType();

    public abstract int getTypeName();

    public abstract String getUserAvatarAbsPathKey();

    public abstract String getUserIdKey();

    public abstract String getUserNameKey();

    public static SnsAccountInfo newSnsAccountInfo(String type) {
        if (SINA_SNS_TYPE.equals(type)) {
            return new SinaAccountInfo();
        }
        if (QQ_SNS_TYPE.equals(type)) {
            return new QQAccountInfo();
        }
        if (FB_SNS_TYPE.equals(type)) {
            return new FacebookAccountInfo();
        }
        return null;
    }
}
