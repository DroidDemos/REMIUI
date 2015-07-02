package com.google.android.gms.drive;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.kn;

public abstract class g implements Parcelable {
    private volatile transient boolean YJ;

    public g() {
        this.YJ = false;
    }

    protected abstract void K(Parcel parcel, int i);

    public final boolean jM() {
        return this.YJ;
    }

    public void writeToParcel(Parcel dest, int flags) {
        kn.K(!jM());
        this.YJ = true;
        K(dest, flags);
    }
}
