package com.alipay.mobilesecuritysdk.constant;

import com.alipay.sdk.cons.MiniDefine;

public enum LocationNameEnum {
    LOCATE_LATITUDE("t"),
    LOCATE_LONGITUDE("g"),
    LOCATE_CELL_ID("c"),
    LOCATE_LAC("l"),
    TIME_STAMP("s"),
    LOCATE_WIFI("w"),
    LOCATION_ITEM("locationitem"),
    START_TAG("locations"),
    VERSION("ver"),
    MCC("mcc"),
    MNC("mnc"),
    PHONETYPE("phoneType"),
    CDMA("cdma"),
    BSSID("bssid"),
    SSID("ssid"),
    LEVEL("level"),
    CURRENT("isCurrent"),
    TIME(MiniDefine.E),
    GSM("gsm");
    
    private String value;

    private LocationNameEnum(String str) {
        setValue(str);
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String str) {
        this.value = str;
    }
}
