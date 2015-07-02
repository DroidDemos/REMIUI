package com.google.android.gms.wallet.fragment;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class WalletFragmentStyle implements SafeParcelable {
    public static final Creator<WalletFragmentStyle> CREATOR;
    Bundle aVe;
    int aVf;
    final int mVersionCode;

    static {
        CREATOR = new c();
    }

    public WalletFragmentStyle() {
        this.mVersionCode = 1;
        this.aVe = new Bundle();
    }

    WalletFragmentStyle(int versionCode, Bundle attributes, int styleResourceId) {
        this.mVersionCode = versionCode;
        this.aVe = attributes;
        this.aVf = styleResourceId;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        c.a(this, dest, flags);
    }
}
