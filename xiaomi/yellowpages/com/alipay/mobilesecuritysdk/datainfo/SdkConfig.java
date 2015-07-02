package com.alipay.mobilesecuritysdk.datainfo;

public class SdkConfig {
    private static SdkConfig configSingleton;
    private int appInterval;
    private long appLUT;
    private int locateInterval;
    private long locateLUT;
    private int locationMaxLines;
    private int mainSwitchInterval;
    private long mainSwitchLUT;
    private String mainSwitchState;

    static {
        configSingleton = new SdkConfig();
    }

    private SdkConfig() {
    }

    public static SdkConfig getInstance() {
        return configSingleton;
    }

    public long getMainSwitchLUT() {
        return this.mainSwitchLUT;
    }

    public void setMainSwitchLUT(long j) {
        this.mainSwitchLUT = j;
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

    public long getLocateLUT() {
        return this.locateLUT;
    }

    public void setLocateLUT(long j) {
        this.locateLUT = j;
    }

    public int getLocateInterval() {
        return this.locateInterval;
    }

    public void setLocateInterval(int i) {
        this.locateInterval = i;
    }

    public long getAppLUT() {
        return this.appLUT;
    }

    public void setAppLUT(long j) {
        this.appLUT = j;
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
