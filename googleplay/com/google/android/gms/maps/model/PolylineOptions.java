package com.google.android.gms.maps.model;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.maps.internal.aa;
import java.util.ArrayList;
import java.util.List;

public final class PolylineOptions implements SafeParcelable {
    public static final o CREATOR;
    private final List<LatLng> aAC;
    private boolean aAE;
    private float aAc;
    private boolean aAd;
    private float aAh;
    private int mColor;
    private final int mVersionCode;

    static {
        CREATOR = new o();
    }

    public PolylineOptions() {
        this.aAh = 10.0f;
        this.mColor = -16777216;
        this.aAc = 0.0f;
        this.aAd = true;
        this.aAE = false;
        this.mVersionCode = 1;
        this.aAC = new ArrayList();
    }

    PolylineOptions(int versionCode, List points, float width, int color, float zIndex, boolean visible, boolean geodesic) {
        this.aAh = 10.0f;
        this.mColor = -16777216;
        this.aAc = 0.0f;
        this.aAd = true;
        this.aAE = false;
        this.mVersionCode = versionCode;
        this.aAC = points;
        this.aAh = width;
        this.mColor = color;
        this.aAc = zIndex;
        this.aAd = visible;
        this.aAE = geodesic;
    }

    public int describeContents() {
        return 0;
    }

    public int getColor() {
        return this.mColor;
    }

    public List<LatLng> getPoints() {
        return this.aAC;
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

    public boolean isGeodesic() {
        return this.aAE;
    }

    public boolean isVisible() {
        return this.aAd;
    }

    public void writeToParcel(Parcel out, int flags) {
        if (aa.qp()) {
            p.a(this, out, flags);
        } else {
            o.a(this, out, flags);
        }
    }
}
