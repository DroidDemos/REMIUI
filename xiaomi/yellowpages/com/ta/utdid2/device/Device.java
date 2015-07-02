package com.ta.utdid2.device;

import com.alipay.mobilesecuritysdk.constant.ConfigConstant;

public class Device {
    private String deviceId;
    private String imei;
    private String imsi;
    private long mCheckSum;
    private long mCreateTimestamp;
    private String utdid;

    public Device() {
        this.imei = ConfigConstant.WIRELESS_FILENAME;
        this.imsi = ConfigConstant.WIRELESS_FILENAME;
        this.deviceId = ConfigConstant.WIRELESS_FILENAME;
        this.utdid = ConfigConstant.WIRELESS_FILENAME;
        this.mCreateTimestamp = 0;
        this.mCheckSum = 0;
    }

    long getCheckSum() {
        return this.mCheckSum;
    }

    void setCheckSum(long j) {
        this.mCheckSum = j;
    }

    long getCreateTimestamp() {
        return this.mCreateTimestamp;
    }

    void setCreateTimestamp(long j) {
        this.mCreateTimestamp = j;
    }

    public String getImei() {
        return this.imei;
    }

    void setImei(String str) {
        this.imei = str;
    }

    public String getImsi() {
        return this.imsi;
    }

    void setImsi(String str) {
        this.imsi = str;
    }

    public String getDeviceId() {
        return this.deviceId;
    }

    void setDeviceId(String str) {
        this.deviceId = str;
    }

    public String getUtdid() {
        return this.utdid;
    }

    void setUtdid(String str) {
        this.utdid = str;
    }
}
