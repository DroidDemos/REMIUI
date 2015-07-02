package com.google.android.gms.location.places;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.kl;
import java.util.concurrent.TimeUnit;

public final class PlaceRequest implements SafeParcelable {
    public static final g CREATOR;
    public static final long DEFAULT_INTERVAL;
    private final long atz;
    private final PlaceFilter awp;
    private final int mPriority;
    final int mVersionCode;

    static {
        CREATOR = new g();
        DEFAULT_INTERVAL = TimeUnit.HOURS.toMillis(1);
    }

    public PlaceRequest(int versionCode, PlaceFilter filter, long interval, int priority) {
        this.mVersionCode = versionCode;
        this.awp = filter;
        this.atz = interval;
        this.mPriority = priority;
    }

    public int describeContents() {
        g gVar = CREATOR;
        return 0;
    }

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof PlaceRequest)) {
            return false;
        }
        PlaceRequest placeRequest = (PlaceRequest) object;
        return kl.equal(this.awp, placeRequest.awp) && this.atz == placeRequest.atz && this.mPriority == placeRequest.mPriority;
    }

    public PlaceFilter getFilter() {
        return this.awp;
    }

    public long getInterval() {
        return this.atz;
    }

    public int getPriority() {
        return this.mPriority;
    }

    public int hashCode() {
        return kl.hashCode(this.awp, Long.valueOf(this.atz), Integer.valueOf(this.mPriority));
    }

    public String toString() {
        return kl.j(this).a("filter", this.awp).a("interval", Long.valueOf(this.atz)).a("priority", Integer.valueOf(this.mPriority)).toString();
    }

    public void writeToParcel(Parcel parcel, int flags) {
        g gVar = CREATOR;
        g.a(this, parcel, flags);
    }
}
