package com.miui.yellowpage.model;

/* compiled from: SmsDataItem */
public class e {
    private long iD;
    private String mAddress;
    private long mId;
    private int mType;

    public e(long j, int i, long j2, String str) {
        this.mId = j;
        this.mType = i;
        this.iD = j2;
        this.mAddress = str;
    }

    public long getDate() {
        return this.iD;
    }

    public String getAddress() {
        return this.mAddress;
    }

    public boolean aL() {
        return this.mType == 1;
    }

    public boolean aM() {
        return this.mType == 4;
    }
}
