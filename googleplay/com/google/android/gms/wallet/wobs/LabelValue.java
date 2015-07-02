package com.google.android.gms.wallet.wobs;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class LabelValue implements SafeParcelable {
    public static final Creator<LabelValue> CREATOR;
    String label;
    private final int mVersionCode;
    String value;

    static {
        CREATOR = new b();
    }

    LabelValue() {
        this.mVersionCode = 1;
    }

    LabelValue(int versionCode, String label, String value) {
        this.mVersionCode = versionCode;
        this.label = label;
        this.value = value;
    }

    public int describeContents() {
        return 0;
    }

    public int getVersionCode() {
        return this.mVersionCode;
    }

    public void writeToParcel(Parcel dest, int flags) {
        b.a(this, dest, flags);
    }
}
