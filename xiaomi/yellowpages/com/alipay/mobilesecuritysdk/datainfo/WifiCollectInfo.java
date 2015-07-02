package com.alipay.mobilesecuritysdk.datainfo;

import com.alipay.mobilesecuritysdk.util.CommonUtils;

public class WifiCollectInfo {
    private String mbssid;
    private boolean miscurrent;
    private int mlevel;
    private String mssid;
    private String time;

    public WifiCollectInfo() {
        this.miscurrent = false;
    }

    public String getTime() {
        return this.time;
    }

    public void setTime(String str) {
        this.time = str;
    }

    public boolean isMiscurrent() {
        return this.miscurrent;
    }

    public void setMiscurrent(boolean z) {
        this.miscurrent = z;
    }

    public String getMbssid() {
        return this.mbssid;
    }

    public void setMbssid(String str) {
        this.mbssid = str;
    }

    public String getMssid() {
        return this.mssid;
    }

    public void setMssid(String str) {
        this.mssid = str;
    }

    public int getMlevel() {
        return this.mlevel;
    }

    public void setMlevel(int i) {
        this.mlevel = i;
    }

    public boolean validate() {
        if (CommonUtils.isBlank(this.mbssid) || CommonUtils.isBlank(this.mssid)) {
            return false;
        }
        return true;
    }
}
