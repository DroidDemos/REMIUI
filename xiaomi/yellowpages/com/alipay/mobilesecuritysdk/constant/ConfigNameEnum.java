package com.alipay.mobilesecuritysdk.constant;

public enum ConfigNameEnum {
    MAIN_SWITCH_LUT("mainSwitchLUT"),
    MAIN_SWITCH_STATE("mainSwitchState"),
    MAIN_SWITCH_INTERVAL("mainSwitchInterval"),
    LOCATE_LUT("locateLUT"),
    LOCATE_INTERVAL("locateInterval"),
    APP_LUT("appLUT"),
    APP_INTERVAL("appInterval"),
    PACKAGE_CHANGED("pkgchanged"),
    LOCATION_MAX_LINES("locationMaxLines"),
    CONFIGS("configs"),
    PKG_NAME("n"),
    PUB_KEY_HASH("h"),
    APP_ITEM("appitem"),
    START_TAG(ConfigConstant.JSON_SECTION_APP);
    
    private String value;

    private ConfigNameEnum(String str) {
        setValue(str);
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String str) {
        this.value = str;
    }
}
