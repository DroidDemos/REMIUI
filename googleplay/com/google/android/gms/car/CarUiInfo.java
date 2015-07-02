package com.google.android.gms.car;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class CarUiInfo implements SafeParcelable {
    public static final Creator<CarUiInfo> CREATOR;
    private boolean LR;
    private boolean LS;
    final int mVersionCode;

    static {
        CREATOR = new u();
    }

    CarUiInfo(int versionCode, boolean hasRotaryController, boolean hasTouchscreen) {
        this.mVersionCode = versionCode;
        this.LR = hasRotaryController;
        this.LS = hasTouchscreen;
    }

    public int describeContents() {
        return 0;
    }

    public int getVersionCode() {
        return this.mVersionCode;
    }

    public boolean hasRotaryController() {
        return this.LR;
    }

    public boolean hasTouchscreen() {
        return this.LS;
    }

    public String toString() {
        return String.format("CarUiInfo (hasRotaryController: %b, hasTouchscreen: %b)", new Object[]{Boolean.valueOf(this.LR), Boolean.valueOf(this.LS)});
    }

    public void writeToParcel(Parcel dest, int flags) {
        u.a(this, dest, flags);
    }
}
