package com.google.android.gms.internal;

import android.content.Context;
import android.os.IBinder;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.dynamic.d.a;
import com.google.android.gms.dynamic.e;

@fd
public final class ef implements SafeParcelable {
    public static final ee CREATOR;
    public final ep mg;
    public final Context sA;
    public final eo sB;
    public final er sz;
    public final int versionCode;

    static {
        CREATOR = new ee();
    }

    ef(int i, IBinder iBinder, IBinder iBinder2, IBinder iBinder3, IBinder iBinder4) {
        this.versionCode = i;
        this.mg = (ep) e.i(a.bH(iBinder));
        this.sz = (er) e.i(a.bH(iBinder2));
        this.sA = (Context) e.i(a.bH(iBinder3));
        this.sB = (eo) e.i(a.bH(iBinder4));
    }

    IBinder ct() {
        return e.q(this.sB).asBinder();
    }

    IBinder cu() {
        return e.q(this.mg).asBinder();
    }

    IBinder cv() {
        return e.q(this.sz).asBinder();
    }

    IBinder cw() {
        return e.q(this.sA).asBinder();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        ee.a(this, out, flags);
    }
}
