package com.google.android.gms.car;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.view.Surface;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class DrawingSpec implements SafeParcelable {
    public static final Creator<DrawingSpec> CREATOR;
    public int dpi;
    public int height;
    final int mVersionCode;
    public Surface surface;
    public int width;

    static {
        CREATOR = new w();
    }

    public DrawingSpec(int versionCode, int width, int height, int dpi, Surface surface) {
        this.mVersionCode = versionCode;
        this.width = width;
        this.height = height;
        this.dpi = dpi;
        this.surface = surface;
    }

    public int describeContents() {
        return 0;
    }

    public int getVersionCode() {
        return this.mVersionCode;
    }

    public void writeToParcel(Parcel dest, int flags) {
        w.a(this, dest, flags);
    }
}
