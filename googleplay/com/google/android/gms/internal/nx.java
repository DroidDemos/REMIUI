package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.ArrayList;

public final class nx implements SafeParcelable {
    public static final Creator<nx> CREATOR;
    public final ArrayList<oj> auF;
    private final int mVersionCode;

    static {
        CREATOR = new ny();
    }

    public nx() {
        this.mVersionCode = 1;
        this.auF = new ArrayList();
    }

    nx(int i, ArrayList<oj> arrayList) {
        this.mVersionCode = i;
        this.auF = arrayList;
    }

    public int describeContents() {
        return 0;
    }

    int getVersionCode() {
        return this.mVersionCode;
    }

    public void writeToParcel(Parcel dest, int flags) {
        ny.a(this, dest, flags);
    }
}
