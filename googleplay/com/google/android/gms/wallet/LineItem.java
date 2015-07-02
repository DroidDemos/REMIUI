package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class LineItem implements SafeParcelable {
    public static final Creator<LineItem> CREATOR;
    String aTI;
    String aTJ;
    int aTK;
    String aTf;
    String aTg;
    String description;
    private final int mVersionCode;

    static {
        CREATOR = new i();
    }

    LineItem() {
        this.mVersionCode = 1;
        this.aTK = 0;
    }

    LineItem(int versionCode, String description, String quantity, String unitPrice, String totalPrice, int role, String currencyCode) {
        this.mVersionCode = versionCode;
        this.description = description;
        this.aTI = quantity;
        this.aTJ = unitPrice;
        this.aTf = totalPrice;
        this.aTK = role;
        this.aTg = currencyCode;
    }

    public int describeContents() {
        return 0;
    }

    public int getVersionCode() {
        return this.mVersionCode;
    }

    public void writeToParcel(Parcel dest, int flags) {
        i.a(this, dest, flags);
    }
}
