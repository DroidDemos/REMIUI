package com.alipay.mobilesecuritysdk.datainfo;

public class GeoResponseInfo {
    private int appInterval;
    private boolean isSuccess;
    private int locateInterval;
    private int locationMaxLines;
    private int mainSwitchInterval;
    private String mainSwitchState;

    public boolean isSuccess() {
        return this.isSuccess;
    }

    public void setSuccess(boolean z) {
        this.isSuccess = z;
    }

    public String getMainSwitchState() {
        return this.mainSwitchState;
    }

    public void setMainSwitchState(String str) {
        this.mainSwitchState = str;
    }

    public int getMainSwitchInterval() {
        return this.mainSwitchInterval;
    }

    public void setMainSwitchInterval(int i) {
        this.mainSwitchInterval = i;
    }

    public int getLocateInterval() {
        return this.locateInterval;
    }

    public void setLocateInterval(int i) {
        this.locateInterval = i;
    }

    public int getAppInterval() {
        return this.appInterval;
    }

    public void setAppInterval(int i) {
        this.appInterval = i;
    }

    public int getLocationMaxLines() {
        return this.locationMaxLines;
    }

    public void setLocationMaxLines(int i) {
        this.locationMaxLines = i;
    }
}
