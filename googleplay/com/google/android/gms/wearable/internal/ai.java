package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class ai implements SafeParcelable {
    public static final Creator<ai> CREATOR;
    private final String aXa;
    private final String awo;
    private final byte[] mData;
    final int mVersionCode;
    private final int ve;

    static {
        CREATOR = new aj();
    }

    ai(int i, int i2, String str, byte[] bArr, String str2) {
        this.mVersionCode = i;
        this.ve = i2;
        this.aXa = str;
        this.mData = bArr;
        this.awo = str2;
    }

    public int describeContents() {
        return 0;
    }

    public byte[] getData() {
        return this.mData;
    }

    public String getPath() {
        return this.aXa;
    }

    public int getRequestId() {
        return this.ve;
    }

    public String getSourceNodeId() {
        return this.awo;
    }

    public String toString() {
        return "MessageEventParcelable[" + this.ve + "," + this.aXa + ", size=" + (this.mData == null ? "null" : Integer.valueOf(this.mData.length)) + "]";
    }

    public void writeToParcel(Parcel dest, int flags) {
        aj.a(this, dest, flags);
    }
}
