package com.google.android.gms.maps.model.internal;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class a implements SafeParcelable {
    public static final b CREATOR;
    private byte aAO;
    private Bundle aAP;
    private Bitmap aAQ;
    private final int mVersionCode;

    static {
        CREATOR = new b();
    }

    a(int i, byte b, Bundle bundle, Bitmap bitmap) {
        this.mVersionCode = i;
        this.aAO = b;
        this.aAP = bundle;
        this.aAQ = bitmap;
    }

    public int describeContents() {
        return 0;
    }

    public Bitmap getBitmap() {
        return this.aAQ;
    }

    public int getVersionCode() {
        return this.mVersionCode;
    }

    public byte qv() {
        return this.aAO;
    }

    public Bundle qw() {
        return this.aAP;
    }

    public void writeToParcel(Parcel out, int flags) {
        b.a(this, out, flags);
    }
}
