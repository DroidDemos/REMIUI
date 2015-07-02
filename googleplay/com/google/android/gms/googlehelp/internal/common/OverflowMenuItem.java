package com.google.android.gms.googlehelp.internal.common;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class OverflowMenuItem implements SafeParcelable {
    public static final Creator<OverflowMenuItem> CREATOR;
    final String Yv;
    final int mId;
    final Intent mIntent;
    final int mVersionCode;

    static {
        CREATOR = new a();
    }

    OverflowMenuItem(int versionCode, int id, String title, Intent intent) {
        this.mVersionCode = versionCode;
        this.mId = id;
        this.Yv = title;
        this.mIntent = intent;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        a.a(this, out, flags);
    }
}
