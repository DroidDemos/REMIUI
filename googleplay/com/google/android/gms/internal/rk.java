package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class rk implements SafeParcelable {
    public static final rl CREATOR;
    private final int aBD;
    private final int aBE;
    private final boolean mUseLargePictureForCp2Images;
    private final int mVersionCode;

    static {
        CREATOR = new rl();
    }

    rk(int i, int i2, int i3, boolean z) {
        this.mVersionCode = i;
        this.aBD = i2;
        this.aBE = i3;
        this.mUseLargePictureForCp2Images = z;
    }

    public int describeContents() {
        return 0;
    }

    public int getVersionCode() {
        return this.mVersionCode;
    }

    public int qI() {
        return this.aBD;
    }

    public int qJ() {
        return this.aBE;
    }

    public boolean qK() {
        return this.mUseLargePictureForCp2Images;
    }

    public String toString() {
        return kl.j(this).a("imageSize", Integer.valueOf(this.aBD)).a("avatarOptions", Integer.valueOf(this.aBE)).a("useLargePictureForCp2Images", Boolean.valueOf(this.mUseLargePictureForCp2Images)).toString();
    }

    public void writeToParcel(Parcel out, int flags) {
        rl.a(this, out, flags);
    }
}
