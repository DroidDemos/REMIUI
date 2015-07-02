package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.ArrayList;

public final class Cart implements SafeParcelable {
    public static final Creator<Cart> CREATOR;
    String aTf;
    String aTg;
    ArrayList<LineItem> aTh;
    private final int mVersionCode;

    static {
        CREATOR = new b();
    }

    Cart() {
        this.mVersionCode = 1;
        this.aTh = new ArrayList();
    }

    Cart(int versionCode, String totalPrice, String currencyCode, ArrayList<LineItem> lineItems) {
        this.mVersionCode = versionCode;
        this.aTf = totalPrice;
        this.aTg = currencyCode;
        this.aTh = lineItems;
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
