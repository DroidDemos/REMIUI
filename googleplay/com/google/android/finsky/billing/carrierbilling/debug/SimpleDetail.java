package com.google.android.finsky.billing.carrierbilling.debug;

public class SimpleDetail implements DcbDetail {
    private final String mTitle;
    private final String mValue;

    public SimpleDetail(String title, String value) {
        this.mTitle = title;
        this.mValue = value;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public String getValue() {
        return this.mValue;
    }
}
