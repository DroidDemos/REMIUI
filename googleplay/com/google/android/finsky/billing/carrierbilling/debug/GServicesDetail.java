package com.google.android.finsky.billing.carrierbilling.debug;

import com.google.android.play.utils.config.GservicesValue;

public class GServicesDetail<E> implements DcbDetail {
    private final GservicesValue<E> mValue;

    public GServicesDetail(GservicesValue<E> value) {
        this.mValue = value;
    }

    public String getTitle() {
        return this.mValue.getKey();
    }

    public String getValue() {
        E value = this.mValue.get();
        if (value == null) {
            return "null";
        }
        return value.toString();
    }
}
