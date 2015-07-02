package com.google.android.gms.maps.model;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.b;

public class n {
    static void a(PolygonOptions polygonOptions, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, polygonOptions.getVersionCode());
        b.d(parcel, 2, polygonOptions.getPoints(), false);
        b.f(parcel, 3, polygonOptions.qt(), false);
        b.a(parcel, 4, polygonOptions.getStrokeWidth());
        b.c(parcel, 5, polygonOptions.getStrokeColor());
        b.c(parcel, 6, polygonOptions.getFillColor());
        b.a(parcel, 7, polygonOptions.getZIndex());
        b.a(parcel, 8, polygonOptions.isVisible());
        b.a(parcel, 9, polygonOptions.isGeodesic());
        b.J(parcel, bU);
    }
}
