package com.miui.yellowpage.openapi;

import android.text.TextUtils;

public class MerchantInfo {
    private String mMerchantId;
    private String mMerchantName;

    public MerchantInfo(String str, String str2) {
        this.mMerchantId = str;
        this.mMerchantName = str2;
    }

    public String getMerchantId() {
        return this.mMerchantId;
    }

    public void setMerchantId(String str) {
        this.mMerchantId = str;
    }

    public String getMerchantName() {
        return this.mMerchantName;
    }

    public void setMerchantName(String str) {
        this.mMerchantName = str;
    }

    public boolean isValid() {
        return (TextUtils.isEmpty(this.mMerchantId) || TextUtils.isEmpty(this.mMerchantName)) ? false : true;
    }
}
