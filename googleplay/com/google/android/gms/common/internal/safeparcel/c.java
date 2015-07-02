package com.google.android.gms.common.internal.safeparcel;

import android.content.Intent;
import android.os.Parcel;

public final class c {
    public static <T extends SafeParcelable> void a(T t, Intent intent, String str) {
        intent.putExtra(str, a(t));
    }

    public static <T extends SafeParcelable> byte[] a(T t) {
        Parcel obtain = Parcel.obtain();
        t.writeToParcel(obtain, 0);
        byte[] marshall = obtain.marshall();
        obtain.recycle();
        return marshall;
    }
}
