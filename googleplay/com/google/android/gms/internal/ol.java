package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.location.copresence.AccessPolicy;
import com.google.android.gms.location.copresence.Message;

public final class ol implements SafeParcelable {
    public static final Creator<ol> CREATOR;
    private final String CB;
    private final Message auC;
    private final on auW;
    private final AccessPolicy auX;
    private final int mVersionCode;

    static {
        CREATOR = new om();
    }

    ol(int i, String str, on onVar, Message message, AccessPolicy accessPolicy) {
        this.mVersionCode = i;
        this.CB = str;
        this.auW = onVar;
        this.auC = message;
        this.auX = accessPolicy;
    }

    public int describeContents() {
        return 0;
    }

    public String getId() {
        return this.CB;
    }

    public Message getMessage() {
        return this.auC;
    }

    int getVersionCode() {
        return this.mVersionCode;
    }

    on pj() {
        return this.auW;
    }

    public AccessPolicy pk() {
        return this.auX;
    }

    public String toString() {
        return "PublishOperation: " + this.auC.toString();
    }

    public void writeToParcel(Parcel dest, int flags) {
        om.a(this, dest, flags);
    }
}
