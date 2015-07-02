package com.alipay.sdk.app;

import com.alipay.mobilesecuritysdk.constant.ConfigConstant;

public enum ResultStatus {
    SUCCEEDED(9000, "\u652f\u4ed8\u6210\u529f"),
    FAILED(4000, "\u7cfb\u7edf\u7e41\u5fd9\uff0c\u8bf7\u7a0d\u540e\u518d\u8bd5"),
    CANCELED(6001, "\u7528\u6237\u53d6\u6d88"),
    NETWORK_ERROR(6002, "\u7f51\u7edc\u8fde\u63a5\u5f02\u5e38"),
    UNKNOWN(6003, ConfigConstant.WIRELESS_FILENAME),
    UNWORK(7001, "\u7f51\u9875\u652f\u4ed8\u5931\u8d25"),
    PARAMS_ERROR(4001, "\u53c2\u6570\u9519\u8bef");
    
    private int h;
    private String i;

    private ResultStatus(int i, String str) {
        this.h = i;
        this.i = str;
    }

    public void a(int i) {
        this.h = i;
    }

    public int a() {
        return this.h;
    }

    public void a(String str) {
        this.i = str;
    }

    public String b() {
        return this.i;
    }

    public static ResultStatus a(ResultStatus resultStatus) {
        switch (resultStatus.h) {
            case 4001:
                return PARAMS_ERROR;
            case 6001:
                return CANCELED;
            case 6002:
                return NETWORK_ERROR;
            case 6003:
                return UNKNOWN;
            case 7001:
                return UNWORK;
            case 9000:
                return SUCCEEDED;
            default:
                return FAILED;
        }
    }
}
