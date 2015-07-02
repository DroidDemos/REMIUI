package com.google.android.gms.maps;

import android.os.Parcel;

public class b {
    static void a(GoogleMapOptions googleMapOptions, Parcel parcel, int i) {
        int bU = com.google.android.gms.common.internal.safeparcel.b.bU(parcel);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1, googleMapOptions.getVersionCode());
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 2, googleMapOptions.pR());
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 3, googleMapOptions.pS());
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 4, googleMapOptions.getMapType());
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 5, googleMapOptions.getCamera(), i, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 6, googleMapOptions.pT());
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 7, googleMapOptions.pU());
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 8, googleMapOptions.pV());
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 9, googleMapOptions.pW());
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 10, googleMapOptions.pX());
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 11, googleMapOptions.pY());
        com.google.android.gms.common.internal.safeparcel.b.J(parcel, bU);
    }
}
