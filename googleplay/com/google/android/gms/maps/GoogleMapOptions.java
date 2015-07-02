package com.google.android.gms.maps;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.maps.internal.a;
import com.google.android.gms.maps.internal.aa;
import com.google.android.gms.maps.model.CameraPosition;

public final class GoogleMapOptions implements SafeParcelable {
    public static final a CREATOR;
    private Boolean ayL;
    private Boolean ayM;
    private int ayN;
    private CameraPosition ayO;
    private Boolean ayP;
    private Boolean ayQ;
    private Boolean ayR;
    private Boolean ayS;
    private Boolean ayT;
    private Boolean ayU;
    private Boolean ayV;
    private Boolean ayW;
    private final int mVersionCode;

    static {
        CREATOR = new a();
    }

    public GoogleMapOptions() {
        this.ayN = -1;
        this.mVersionCode = 1;
    }

    GoogleMapOptions(int versionCode, byte zOrderOnTop, byte useViewLifecycleInFragment, int mapType, CameraPosition camera, byte zoomControlsEnabled, byte compassEnabled, byte scrollGesturesEnabled, byte zoomGesturesEnabled, byte tiltGesturesEnabled, byte rotateGesturesEnabled, byte liteMode, byte mapToolbarEnabled) {
        this.ayN = -1;
        this.mVersionCode = versionCode;
        this.ayL = a.a(zOrderOnTop);
        this.ayM = a.a(useViewLifecycleInFragment);
        this.ayN = mapType;
        this.ayO = camera;
        this.ayP = a.a(zoomControlsEnabled);
        this.ayQ = a.a(compassEnabled);
        this.ayR = a.a(scrollGesturesEnabled);
        this.ayS = a.a(zoomGesturesEnabled);
        this.ayT = a.a(tiltGesturesEnabled);
        this.ayU = a.a(rotateGesturesEnabled);
        this.ayV = a.a(liteMode);
        this.ayW = a.a(mapToolbarEnabled);
    }

    public int describeContents() {
        return 0;
    }

    public CameraPosition getCamera() {
        return this.ayO;
    }

    public int getMapType() {
        return this.ayN;
    }

    int getVersionCode() {
        return this.mVersionCode;
    }

    byte pR() {
        return a.c(this.ayL);
    }

    byte pS() {
        return a.c(this.ayM);
    }

    byte pT() {
        return a.c(this.ayP);
    }

    byte pU() {
        return a.c(this.ayQ);
    }

    byte pV() {
        return a.c(this.ayR);
    }

    byte pW() {
        return a.c(this.ayS);
    }

    byte pX() {
        return a.c(this.ayT);
    }

    byte pY() {
        return a.c(this.ayU);
    }

    byte pZ() {
        return a.c(this.ayV);
    }

    byte qa() {
        return a.c(this.ayW);
    }

    public void writeToParcel(Parcel out, int flags) {
        if (aa.qp()) {
            b.a(this, out, flags);
        } else {
            a.a(this, out, flags);
        }
    }
}
