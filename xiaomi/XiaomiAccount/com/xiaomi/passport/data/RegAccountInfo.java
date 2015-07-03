package com.xiaomi.passport.data;

public class RegAccountInfo {
    private String mEmail;
    private long mLastActivateTime;
    private String mPassword;
    private int mPollSeq;
    private String mRegType;
    private String mUserId;

    public RegAccountInfo(String userId, String password, String regType, String email) {
        this.mUserId = userId;
        this.mPassword = password;
        this.mRegType = regType;
        this.mEmail = email;
        this.mPollSeq = 0;
    }

    public String getUserId() {
        return this.mUserId;
    }

    public String getPassword() {
        return this.mPassword;
    }

    public String getEmail() {
        return this.mEmail;
    }

    public String getRegType() {
        return this.mRegType;
    }

    public int getPollSeq() {
        return this.mPollSeq;
    }

    public void setPollSeq(int pollSeq) {
        this.mPollSeq = pollSeq;
    }

    public long getLastActivateTime() {
        return this.mLastActivateTime;
    }

    public void setLastActivateTime(long lastActivateTime) {
        this.mLastActivateTime = lastActivateTime;
    }
}
