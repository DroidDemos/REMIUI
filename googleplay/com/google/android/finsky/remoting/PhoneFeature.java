package com.google.android.finsky.remoting;

public enum PhoneFeature {
    ENABLE_HIPRI("enableHIPRI"),
    ENABLE_MMS("enableMMS"),
    ENABLE_SUPL("enableSUPL"),
    ENABLE_DUN("enableDUN");
    
    private final String mValue;

    private PhoneFeature(String value) {
        this.mValue = value;
    }

    public String getValue() {
        return this.mValue;
    }
}
