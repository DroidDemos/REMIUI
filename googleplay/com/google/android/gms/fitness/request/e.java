package com.google.android.gms.fitness.request;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.internal.kl;

public class e implements SafeParcelable {
    public static final Creator<e> CREATOR;
    private final DataSet agW;
    private final int mVersionCode;

    static {
        CREATOR = new f();
    }

    e(int i, DataSet dataSet) {
        this.mVersionCode = i;
        this.agW = dataSet;
    }

    private boolean a(e eVar) {
        return kl.equal(this.agW, eVar.agW);
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object o) {
        return o == this || ((o instanceof e) && a((e) o));
    }

    int getVersionCode() {
        return this.mVersionCode;
    }

    public int hashCode() {
        return kl.hashCode(this.agW);
    }

    public DataSet lC() {
        return this.agW;
    }

    public String toString() {
        return kl.j(this).a("dataSet", this.agW).toString();
    }

    public void writeToParcel(Parcel dest, int flags) {
        f.a(this, dest, flags);
    }
}
