package com.google.android.gms.location;

import android.os.Parcel;
import android.os.SystemClock;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.kl;

public final class LocationRequest implements SafeParcelable {
    public static final c CREATOR;
    boolean ain;
    long atA;
    int atB;
    float atC;
    long atD;
    long atn;
    long atz;
    int mPriority;
    private final int mVersionCode;

    static {
        CREATOR = new c();
    }

    public LocationRequest() {
        this.mVersionCode = 1;
        this.mPriority = 102;
        this.atz = 3600000;
        this.atA = 600000;
        this.ain = false;
        this.atn = Long.MAX_VALUE;
        this.atB = Integer.MAX_VALUE;
        this.atC = 0.0f;
        this.atD = 0;
    }

    LocationRequest(int versionCode, int priority, long interval, long fastestInterval, boolean explicitFastestInterval, long expireAt, int numUpdates, float smallestDisplacement, long maxWaitTime) {
        this.mVersionCode = versionCode;
        this.mPriority = priority;
        this.atz = interval;
        this.atA = fastestInterval;
        this.ain = explicitFastestInterval;
        this.atn = expireAt;
        this.atB = numUpdates;
        this.atC = smallestDisplacement;
        this.atD = maxWaitTime;
    }

    public static String hu(int i) {
        switch (i) {
            case 100:
                return "PRIORITY_HIGH_ACCURACY";
            case 102:
                return "PRIORITY_BALANCED_POWER_ACCURACY";
            case 104:
                return "PRIORITY_LOW_POWER";
            case 105:
                return "PRIORITY_NO_POWER";
            default:
                return "???";
        }
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof LocationRequest)) {
            return false;
        }
        LocationRequest locationRequest = (LocationRequest) object;
        return this.mPriority == locationRequest.mPriority && this.atz == locationRequest.atz && this.atA == locationRequest.atA && this.ain == locationRequest.ain && this.atn == locationRequest.atn && this.atB == locationRequest.atB && this.atC == locationRequest.atC;
    }

    int getVersionCode() {
        return this.mVersionCode;
    }

    public int hashCode() {
        return kl.hashCode(Integer.valueOf(this.mPriority), Long.valueOf(this.atz), Long.valueOf(this.atA), Boolean.valueOf(this.ain), Long.valueOf(this.atn), Integer.valueOf(this.atB), Float.valueOf(this.atC));
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Request[").append(hu(this.mPriority));
        if (this.mPriority != 105) {
            stringBuilder.append(" requested=");
            stringBuilder.append(this.atz + "ms");
        }
        stringBuilder.append(" fastest=");
        stringBuilder.append(this.atA + "ms");
        if (this.atn != Long.MAX_VALUE) {
            long elapsedRealtime = this.atn - SystemClock.elapsedRealtime();
            stringBuilder.append(" expireIn=");
            stringBuilder.append(elapsedRealtime + "ms");
        }
        if (this.atB != Integer.MAX_VALUE) {
            stringBuilder.append(" num=").append(this.atB);
        }
        stringBuilder.append(']');
        return stringBuilder.toString();
    }

    public void writeToParcel(Parcel parcel, int flags) {
        c.a(this, parcel, flags);
    }
}
