package com.google.android.gms.udc;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class SettingState implements SafeParcelable {
    public static final b CREATOR;
    private int aQV;
    private int aQW;
    private final int mVersionCode;

    static {
        CREATOR = new b();
    }

    public SettingState() {
        this.mVersionCode = 2;
    }

    SettingState(int versionCode, int settingId, int settingValue) {
        this.mVersionCode = versionCode;
        this.aQV = settingId;
        this.aQW = settingValue;
    }

    public int describeContents() {
        b bVar = CREATOR;
        return 0;
    }

    public int getSettingId() {
        return this.aQV;
    }

    public int getSettingValue() {
        return this.aQW;
    }

    public int getVersionCode() {
        return this.mVersionCode;
    }

    public void writeToParcel(Parcel out, int flags) {
        b bVar = CREATOR;
        b.a(this, out, flags);
    }
}
