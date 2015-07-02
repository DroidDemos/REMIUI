package com.google.android.gms.maps;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.maps.internal.a;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.StreetViewPanoramaCamera;

public final class StreetViewPanoramaOptions implements SafeParcelable {
    public static final c CREATOR;
    private Boolean ayM;
    private Boolean ayS;
    private Integer azA;
    private Boolean azB;
    private Boolean azC;
    private Boolean azD;
    private StreetViewPanoramaCamera azx;
    private String azy;
    private LatLng azz;
    private final int mVersionCode;

    static {
        CREATOR = new c();
    }

    public StreetViewPanoramaOptions() {
        this.azB = Boolean.valueOf(true);
        this.ayS = Boolean.valueOf(true);
        this.azC = Boolean.valueOf(true);
        this.azD = Boolean.valueOf(true);
        this.mVersionCode = 1;
    }

    StreetViewPanoramaOptions(int versionCode, StreetViewPanoramaCamera camera, String panoId, LatLng position, Integer radius, byte userNavigationEnabled, byte zoomGesturesEnabled, byte panningGesturesEnabled, byte streetNamesEnabled, byte useViewLifecycleInFragment) {
        this.azB = Boolean.valueOf(true);
        this.ayS = Boolean.valueOf(true);
        this.azC = Boolean.valueOf(true);
        this.azD = Boolean.valueOf(true);
        this.mVersionCode = versionCode;
        this.azx = camera;
        this.azz = position;
        this.azA = radius;
        this.azy = panoId;
        this.azB = a.a(userNavigationEnabled);
        this.ayS = a.a(zoomGesturesEnabled);
        this.azC = a.a(panningGesturesEnabled);
        this.azD = a.a(streetNamesEnabled);
        this.ayM = a.a(useViewLifecycleInFragment);
    }

    public int describeContents() {
        return 0;
    }

    public String getPanoramaId() {
        return this.azy;
    }

    public LatLng getPosition() {
        return this.azz;
    }

    public Integer getRadius() {
        return this.azA;
    }

    public StreetViewPanoramaCamera getStreetViewPanoramaCamera() {
        return this.azx;
    }

    int getVersionCode() {
        return this.mVersionCode;
    }

    byte pS() {
        return a.c(this.ayM);
    }

    byte pW() {
        return a.c(this.ayS);
    }

    byte qg() {
        return a.c(this.azB);
    }

    byte qh() {
        return a.c(this.azC);
    }

    byte qi() {
        return a.c(this.azD);
    }

    public void writeToParcel(Parcel out, int flags) {
        c.a(this, out, flags);
    }
}
