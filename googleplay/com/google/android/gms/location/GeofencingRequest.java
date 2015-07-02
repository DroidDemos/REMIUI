package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.ph;
import java.util.List;

public class GeofencingRequest implements SafeParcelable {
    public static final Creator<GeofencingRequest> CREATOR;
    private final List<ph> atx;
    private final int aty;
    private final int mVersionCode;

    static {
        CREATOR = new a();
    }

    GeofencingRequest(int version, List<ph> geofences, int initialTrigger) {
        this.mVersionCode = version;
        this.atx = geofences;
        this.aty = initialTrigger;
    }

    public int describeContents() {
        return 0;
    }

    public int getInitialTrigger() {
        return this.aty;
    }

    public int getVersionCode() {
        return this.mVersionCode;
    }

    public List<ph> oZ() {
        return this.atx;
    }

    public void writeToParcel(Parcel dest, int flags) {
        a.a(this, dest, flags);
    }
}
