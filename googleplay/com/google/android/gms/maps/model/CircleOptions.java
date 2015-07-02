package com.google.android.gms.maps.model;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.maps.internal.aa;

public final class CircleOptions implements SafeParcelable {
    public static final c CREATOR;
    private int aAa;
    private int aAb;
    private float aAc;
    private boolean aAd;
    private LatLng azX;
    private double azY;
    private float azZ;
    private final int mVersionCode;

    static {
        CREATOR = new c();
    }

    public CircleOptions() {
        this.azX = null;
        this.azY = 0.0d;
        this.azZ = 10.0f;
        this.aAa = -16777216;
        this.aAb = 0;
        this.aAc = 0.0f;
        this.aAd = true;
        this.mVersionCode = 1;
    }

    CircleOptions(int versionCode, LatLng center, double radius, float strokeWidth, int strokeColor, int fillColor, float zIndex, boolean visible) {
        this.azX = null;
        this.azY = 0.0d;
        this.azZ = 10.0f;
        this.aAa = -16777216;
        this.aAb = 0;
        this.aAc = 0.0f;
        this.aAd = true;
        this.mVersionCode = versionCode;
        this.azX = center;
        this.azY = radius;
        this.azZ = strokeWidth;
        this.aAa = strokeColor;
        this.aAb = fillColor;
        this.aAc = zIndex;
        this.aAd = visible;
    }

    public int describeContents() {
        return 0;
    }

    public LatLng getCenter() {
        return this.azX;
    }

    public int getFillColor() {
        return this.aAb;
    }

    public double getRadius() {
        return this.azY;
    }

    public int getStrokeColor() {
        return this.aAa;
    }

    public float getStrokeWidth() {
        return this.azZ;
    }

    int getVersionCode() {
        return this.mVersionCode;
    }

    public float getZIndex() {
        return this.aAc;
    }

    public boolean isVisible() {
        return this.aAd;
    }

    public void writeToParcel(Parcel out, int flags) {
        if (aa.qp()) {
            d.a(this, out, flags);
        } else {
            c.a(this, out, flags);
        }
    }
}
