package com.google.android.gms.people.model;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.kl;
import com.google.android.gms.internal.kn;

public final class AvatarReference implements SafeParcelable {
    public static final b CREATOR;
    final String aFR;
    final int awn;
    private final int mVersionCode;

    static {
        CREATOR = new b();
    }

    AvatarReference(int versionCode, int source, String location) {
        kn.K(source != 0);
        this.mVersionCode = versionCode;
        this.awn = source;
        this.aFR = location;
    }

    public int describeContents() {
        return 0;
    }

    public String getLocation() {
        return this.aFR;
    }

    public int getSource() {
        return this.awn;
    }

    public int getVersionCode() {
        return this.mVersionCode;
    }

    public String toString() {
        return kl.j(this).a("source", Integer.valueOf(this.awn)).a("location", this.aFR).toString();
    }

    public void writeToParcel(Parcel out, int flags) {
        b.a(this, out, flags);
    }
}
