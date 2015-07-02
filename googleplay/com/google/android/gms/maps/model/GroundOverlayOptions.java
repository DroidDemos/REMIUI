package com.google.android.gms.maps.model;

import android.os.IBinder;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.dynamic.d.a;
import com.google.android.gms.maps.internal.aa;

public final class GroundOverlayOptions implements SafeParcelable {
    public static final e CREATOR;
    private float aAc;
    private boolean aAd;
    private BitmapDescriptor aAf;
    private LatLng aAg;
    private float aAh;
    private float aAi;
    private LatLngBounds aAj;
    private float aAk;
    private float aAl;
    private float aAm;
    private float azV;
    private final int mVersionCode;

    static {
        CREATOR = new e();
    }

    public GroundOverlayOptions() {
        this.aAd = true;
        this.aAk = 0.0f;
        this.aAl = 0.5f;
        this.aAm = 0.5f;
        this.mVersionCode = 1;
    }

    GroundOverlayOptions(int versionCode, IBinder wrappedImage, LatLng location, float width, float height, LatLngBounds bounds, float bearing, float zIndex, boolean visible, float transparency, float anchorU, float anchorV) {
        this.aAd = true;
        this.aAk = 0.0f;
        this.aAl = 0.5f;
        this.aAm = 0.5f;
        this.mVersionCode = versionCode;
        this.aAf = new BitmapDescriptor(a.bH(wrappedImage));
        this.aAg = location;
        this.aAh = width;
        this.aAi = height;
        this.aAj = bounds;
        this.azV = bearing;
        this.aAc = zIndex;
        this.aAd = visible;
        this.aAk = transparency;
        this.aAl = anchorU;
        this.aAm = anchorV;
    }

    public int describeContents() {
        return 0;
    }

    public float getAnchorU() {
        return this.aAl;
    }

    public float getAnchorV() {
        return this.aAm;
    }

    public float getBearing() {
        return this.azV;
    }

    public LatLngBounds getBounds() {
        return this.aAj;
    }

    public float getHeight() {
        return this.aAi;
    }

    public LatLng getLocation() {
        return this.aAg;
    }

    public float getTransparency() {
        return this.aAk;
    }

    int getVersionCode() {
        return this.mVersionCode;
    }

    public float getWidth() {
        return this.aAh;
    }

    public float getZIndex() {
        return this.aAc;
    }

    public boolean isVisible() {
        return this.aAd;
    }

    IBinder qr() {
        return this.aAf.pO().asBinder();
    }

    public void writeToParcel(Parcel out, int flags) {
        if (aa.qp()) {
            f.a(this, out, flags);
        } else {
            e.a(this, out, flags);
        }
    }
}
