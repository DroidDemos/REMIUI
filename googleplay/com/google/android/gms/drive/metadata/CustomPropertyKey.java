package com.google.android.gms.drive.metadata;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.kn;
import java.util.regex.Pattern;

public class CustomPropertyKey implements SafeParcelable {
    public static final Creator<CustomPropertyKey> CREATOR;
    private static final Pattern abk;
    final String TN;
    final int mVersionCode;
    final int mVisibility;

    static {
        CREATOR = new c();
        abk = Pattern.compile("[\\w.!@$%^&*()/-]+");
    }

    CustomPropertyKey(int versionCode, String key, int visibility) {
        boolean z = true;
        kn.b((Object) key, (Object) "key");
        kn.b(abk.matcher(key).matches(), (Object) "key name characters must be alphanumeric or one of .!@$%^&*()-_/");
        if (!(visibility == 0 || visibility == 1)) {
            z = false;
        }
        kn.b(z, (Object) "visibility must be either PUBLIC or PRIVATE");
        this.mVersionCode = versionCode;
        this.TN = key;
        this.mVisibility = visibility;
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof CustomPropertyKey)) {
            return false;
        }
        CustomPropertyKey customPropertyKey = (CustomPropertyKey) obj;
        if (!(customPropertyKey.getKey().equals(this.TN) && customPropertyKey.getVisibility() == this.mVisibility)) {
            z = false;
        }
        return z;
    }

    public String getKey() {
        return this.TN;
    }

    public int getVisibility() {
        return this.mVisibility;
    }

    public int hashCode() {
        return (this.TN + this.mVisibility).hashCode();
    }

    public String toString() {
        return "CustomPropertyKey(" + this.TN + "," + this.mVisibility + ")";
    }

    public void writeToParcel(Parcel dest, int flags) {
        c.a(this, dest, flags);
    }
}
