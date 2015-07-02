package com.google.android.gms.maps.model;

import android.os.IBinder;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.dynamic.d.a;
import com.google.android.gms.maps.internal.aa;

public final class MarkerOptions implements SafeParcelable {
    public static final k CREATOR;
    private String Yv;
    private float aAA;
    private boolean aAd;
    private float aAl;
    private float aAm;
    private String aAu;
    private BitmapDescriptor aAv;
    private boolean aAw;
    private boolean aAx;
    private float aAy;
    private float aAz;
    private LatLng azz;
    private float mAlpha;
    private final int mVersionCode;

    static {
        CREATOR = new k();
    }

    public MarkerOptions() {
        this.aAl = 0.5f;
        this.aAm = 1.0f;
        this.aAd = true;
        this.aAx = false;
        this.aAy = 0.0f;
        this.aAz = 0.5f;
        this.aAA = 0.0f;
        this.mAlpha = 1.0f;
        this.mVersionCode = 1;
    }

    MarkerOptions(int versionCode, LatLng position, String title, String snippet, IBinder wrappedIcon, float anchorU, float anchorV, boolean draggable, boolean visible, boolean flat, float rotation, float infoWindowAnchorU, float infoWindowAnchorV, float alpha) {
        this.aAl = 0.5f;
        this.aAm = 1.0f;
        this.aAd = true;
        this.aAx = false;
        this.aAy = 0.0f;
        this.aAz = 0.5f;
        this.aAA = 0.0f;
        this.mAlpha = 1.0f;
        this.mVersionCode = versionCode;
        this.azz = position;
        this.Yv = title;
        this.aAu = snippet;
        this.aAv = wrappedIcon == null ? null : new BitmapDescriptor(a.bH(wrappedIcon));
        this.aAl = anchorU;
        this.aAm = anchorV;
        this.aAw = draggable;
        this.aAd = visible;
        this.aAx = flat;
        this.aAy = rotation;
        this.aAz = infoWindowAnchorU;
        this.aAA = infoWindowAnchorV;
        this.mAlpha = alpha;
    }

    public int describeContents() {
        return 0;
    }

    public float getAlpha() {
        return this.mAlpha;
    }

    public float getAnchorU() {
        return this.aAl;
    }

    public float getAnchorV() {
        return this.aAm;
    }

    public float getInfoWindowAnchorU() {
        return this.aAz;
    }

    public float getInfoWindowAnchorV() {
        return this.aAA;
    }

    public LatLng getPosition() {
        return this.azz;
    }

    public float getRotation() {
        return this.aAy;
    }

    public String getSnippet() {
        return this.aAu;
    }

    public String getTitle() {
        return this.Yv;
    }

    int getVersionCode() {
        return this.mVersionCode;
    }

    public boolean isDraggable() {
        return this.aAw;
    }

    public boolean isFlat() {
        return this.aAx;
    }

    public boolean isVisible() {
        return this.aAd;
    }

    IBinder qs() {
        return this.aAv == null ? null : this.aAv.pO().asBinder();
    }

    public void writeToParcel(Parcel out, int flags) {
        if (aa.qp()) {
            l.a(this, out, flags);
        } else {
            k.a(this, out, flags);
        }
    }
}
