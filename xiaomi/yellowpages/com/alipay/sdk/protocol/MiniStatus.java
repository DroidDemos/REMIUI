package com.alipay.sdk.protocol;

import com.alipay.mobilesecuritysdk.deviceID.Profile;

public enum MiniStatus {
    SUCCESS(Profile.devicever),
    FORCE_EXIT("force_exit"),
    INITIAL_INVALID("initial_invalid"),
    TID_REFRESH("tid_refresh_invalid"),
    PAYMENT_RESULT("payment_result"),
    QUERY_RESULT("query_result"),
    MIX_OLDCARD_PAY_ILLEGAL("mix_oldcard_pay_illegal"),
    POP_TYPE("pop_type"),
    NOT_POP_TYPE("not_pop_type"),
    CLIENT_CONFIRM("client_confirm");
    
    private String k;

    private MiniStatus(String str) {
        this.k = str;
    }

    public final String a() {
        return this.k;
    }

    public static MiniStatus a(String str) {
        MiniStatus miniStatus = null;
        MiniStatus[] values = values();
        int length = values.length;
        int i = 0;
        while (i < length) {
            MiniStatus miniStatus2 = values[i];
            if (!str.startsWith(miniStatus2.a())) {
                miniStatus2 = miniStatus;
            }
            i++;
            miniStatus = miniStatus2;
        }
        return miniStatus;
    }
}
