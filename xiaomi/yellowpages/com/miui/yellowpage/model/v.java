package com.miui.yellowpage.model;

import android.text.TextUtils;

/* compiled from: RechargeLocalHistoryEntry */
public class v {
    private String Lg;
    private boolean Lh;
    private String mName;

    public v() {
        this(null, null, false);
    }

    public v(String str, String str2) {
        this(str, str2, false);
    }

    public v(String str, String str2, boolean z) {
        this.mName = str;
        this.Lg = str2;
        this.Lh = z;
    }

    public String getName() {
        return this.mName;
    }

    public void setName(String str) {
        this.mName = str;
    }

    public String getPhone() {
        return this.Lg;
    }

    public boolean im() {
        return this.Lh;
    }

    public boolean equals(Object obj) {
        if (obj instanceof v) {
            return TextUtils.equals(((v) obj).getPhone(), this.Lg);
        }
        return false;
    }
}
