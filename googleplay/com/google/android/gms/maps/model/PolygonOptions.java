package com.google.android.gms.maps.model;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.maps.internal.aa;
import java.util.ArrayList;
import java.util.List;

public final class PolygonOptions implements SafeParcelable {
    public static final m CREATOR;
    private final List<LatLng> aAC;
    private final List<List<LatLng>> aAD;
    private boolean aAE;
    private int aAa;
    private int aAb;
    private float aAc;
    private boolean aAd;
    private float azZ;
    private final int mVersionCode;

    static {
        CREATOR = new m();
    }

    public PolygonOptions() {
        this.azZ = 10.0f;
        this.aAa = -16777216;
        this.aAb = 0;
        this.aAc = 0.0f;
        this.aAd = true;
        this.aAE = false;
        this.mVersionCode = 1;
        this.aAC = new ArrayList();
        this.aAD = new ArrayList();
    }

    PolygonOptions(int versionCode, List<LatLng> points, List holes, float strokeWidth, int strokeColor, int fillColor, float zIndex, boolean visible, boolean geodesic) {
        this.azZ = 10.0f;
        this.aAa = -16777216;
        this.aAb = 0;
        this.aAc = 0.0f;
        this.aAd = true;
        this.aAE = false;
        this.mVersionCode = versionCode;
        this.aAC = points;
        this.aAD = holes;
        this.azZ = strokeWidth;
        this.aAa = strokeColor;
        this.aAb = fillColor;
        this.aAc = zIndex;
        this.aAd = visible;
        this.aAE = geodesic;
    }

    public int describeContents() {
        return 0;
    }

    public int getFillColor() {
        return this.aAb;
    }

    public List<LatLng> getPoints() {
        return this.aAC;
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

    public boolean isGeodesic() {
        return this.aAE;
    }

    public boolean isVisible() {
        return this.aAd;
    }

    List qt() {
        return this.aAD;
    }

    public void writeToParcel(Parcel out, int flags) {
        if (aa.qp()) {
            n.a(this, out, flags);
        } else {
            m.a(this, out, flags);
        }
    }
}
