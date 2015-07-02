package com.miui.yellowpage.openapi;

public class PermissionInfo {
    private String mKey;
    private String mLabel;

    public PermissionInfo(String str, String str2) {
        this.mKey = str;
        this.mLabel = str2;
    }

    public String getKey() {
        return this.mKey;
    }

    public String getLabel() {
        return this.mLabel;
    }
}
