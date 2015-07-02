package com.google.android.gms.maps.model;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.kl;
import com.google.android.gms.internal.kn;
import com.google.android.gms.maps.model.StreetViewPanoramaOrientation.Builder;

public class StreetViewPanoramaCamera implements SafeParcelable {
    public static final q CREATOR;
    private StreetViewPanoramaOrientation aAG;
    public final float bearing;
    private final int mVersionCode;
    public final float tilt;
    public final float zoom;

    static {
        CREATOR = new q();
    }

    StreetViewPanoramaCamera(int versionCode, float zoom, float tilt, float bearing) {
        boolean z = -90.0f <= tilt && tilt <= 90.0f;
        kn.b(z, (Object) "Tilt needs to be between -90 and 90 inclusive");
        this.mVersionCode = versionCode;
        if (((double) zoom) <= 0.0d) {
            zoom = 0.0f;
        }
        this.zoom = zoom;
        this.tilt = tilt + 0.0f;
        this.bearing = (((double) bearing) <= 0.0d ? (bearing % 360.0f) + 360.0f : bearing) % 360.0f;
        this.aAG = new Builder().tilt(tilt).bearing(bearing).build();
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof StreetViewPanoramaCamera)) {
            return false;
        }
        StreetViewPanoramaCamera streetViewPanoramaCamera = (StreetViewPanoramaCamera) o;
        return Float.floatToIntBits(this.zoom) == Float.floatToIntBits(streetViewPanoramaCamera.zoom) && Float.floatToIntBits(this.tilt) == Float.floatToIntBits(streetViewPanoramaCamera.tilt) && Float.floatToIntBits(this.bearing) == Float.floatToIntBits(streetViewPanoramaCamera.bearing);
    }

    int getVersionCode() {
        return this.mVersionCode;
    }

    public int hashCode() {
        return kl.hashCode(Float.valueOf(this.zoom), Float.valueOf(this.tilt), Float.valueOf(this.bearing));
    }

    public String toString() {
        return kl.j(this).a("zoom", Float.valueOf(this.zoom)).a("tilt", Float.valueOf(this.tilt)).a("bearing", Float.valueOf(this.bearing)).toString();
    }

    public void writeToParcel(Parcel out, int flags) {
        q.a(this, out, flags);
    }
}
