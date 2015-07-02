package com.google.android.gms.fitness.request;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.fitness.data.Session;
import com.google.android.gms.internal.kl;

public class v implements SafeParcelable {
    public static final Creator<v> CREATOR;
    private final Session agd;
    private final int mVersionCode;

    static {
        CREATOR = new w();
    }

    v(int i, Session session) {
        this.mVersionCode = i;
        this.agd = session;
    }

    private boolean a(v vVar) {
        return kl.equal(this.agd, vVar.agd);
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object o) {
        return o == this || ((o instanceof v) && a((v) o));
    }

    public Session getSession() {
        return this.agd;
    }

    int getVersionCode() {
        return this.mVersionCode;
    }

    public int hashCode() {
        return kl.hashCode(this.agd);
    }

    public String toString() {
        return kl.j(this).a("session", this.agd).toString();
    }

    public void writeToParcel(Parcel dest, int flags) {
        w.a(this, dest, flags);
    }
}
