package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.kl;

public class p implements SafeParcelable {
    public static final Creator<p> CREATOR;
    private final DataSet agW;
    private final Session agd;
    final int mVersionCode;

    static {
        CREATOR = new q();
    }

    p(int i, Session session, DataSet dataSet) {
        this.mVersionCode = i;
        this.agd = session;
        this.agW = dataSet;
    }

    private boolean a(p pVar) {
        return kl.equal(this.agd, pVar.agd) && kl.equal(this.agW, pVar.agW);
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object o) {
        return o == this || ((o instanceof p) && a((p) o));
    }

    public Session getSession() {
        return this.agd;
    }

    public int hashCode() {
        return kl.hashCode(this.agd, this.agW);
    }

    public DataSet lC() {
        return this.agW;
    }

    public String toString() {
        return kl.j(this).a("session", this.agd).a("dataSet", this.agW).toString();
    }

    public void writeToParcel(Parcel dest, int flags) {
        q.a(this, dest, flags);
    }
}
