package com.google.android.gms.wallet.fragment;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class WalletFragmentOptions implements SafeParcelable {
    public static final Creator<WalletFragmentOptions> CREATOR;
    private int Ya;
    private int aUz;
    private WalletFragmentStyle aVc;
    private int mTheme;
    final int mVersionCode;

    static {
        CREATOR = new b();
    }

    private WalletFragmentOptions() {
        this.mVersionCode = 1;
    }

    WalletFragmentOptions(int versionCode, int environment, int theme, WalletFragmentStyle fragmentStyle, int mode) {
        this.mVersionCode = versionCode;
        this.aUz = environment;
        this.mTheme = theme;
        this.aVc = fragmentStyle;
        this.Ya = mode;
    }

    public int describeContents() {
        return 0;
    }

    public int getEnvironment() {
        return this.aUz;
    }

    public WalletFragmentStyle getFragmentStyle() {
        return this.aVc;
    }

    public int getMode() {
        return this.Ya;
    }

    public int getTheme() {
        return this.mTheme;
    }

    public void writeToParcel(Parcel dest, int flags) {
        b.a(this, dest, flags);
    }
}
