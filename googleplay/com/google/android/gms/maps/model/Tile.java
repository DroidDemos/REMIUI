package com.google.android.gms.maps.model;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.maps.internal.aa;

public final class Tile implements SafeParcelable {
    public static final u CREATOR;
    public final byte[] data;
    public final int height;
    private final int mVersionCode;
    public final int width;

    static {
        CREATOR = new u();
    }

    Tile(int versionCode, int width, int height, byte[] data) {
        this.mVersionCode = versionCode;
        this.width = width;
        this.height = height;
        this.data = data;
    }

    public Tile(int width, int height, byte[] data) {
        this(1, width, height, data);
    }

    public int describeContents() {
        return 0;
    }

    int getVersionCode() {
        return this.mVersionCode;
    }

    public void writeToParcel(Parcel out, int flags) {
        if (aa.qp()) {
            v.a(this, out, flags);
        } else {
            u.a(this, out, flags);
        }
    }
}
