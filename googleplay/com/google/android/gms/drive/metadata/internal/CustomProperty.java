package com.google.android.gms.drive.metadata.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.metadata.CustomPropertyKey;
import com.google.android.gms.internal.kn;

public class CustomProperty implements SafeParcelable {
    public static final Creator<CustomProperty> CREATOR;
    final CustomPropertyKey abo;
    final String mValue;
    final int mVersionCode;

    static {
        CREATOR = new c();
    }

    CustomProperty(int versionCode, CustomPropertyKey key, String value) {
        this.mVersionCode = versionCode;
        kn.b((Object) key, (Object) "key");
        this.abo = key;
        this.mValue = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        c.a(this, dest, flags);
    }
}
