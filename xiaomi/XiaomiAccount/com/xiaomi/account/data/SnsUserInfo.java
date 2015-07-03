package com.xiaomi.account.data;

public class SnsUserInfo {
    private String mAvatarAbsPath;
    private String mAvatarUrl;
    private String mName;
    private String mUserId;

    public SnsUserInfo(String userId, String name, String avatarUrl) {
        this.mUserId = userId;
        this.mName = name;
        this.mAvatarUrl = avatarUrl;
    }

    public String getUserId() {
        return this.mUserId;
    }

    public void setAvatarAbsPath(String avatarAbsPath) {
        this.mAvatarAbsPath = avatarAbsPath;
    }

    public String getName() {
        return this.mName;
    }

    public String getAvatarUrl() {
        return this.mAvatarUrl;
    }

    public String getAvatarAbsPath() {
        return this.mAvatarAbsPath;
    }
}
