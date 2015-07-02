package com.google.android.gms.car;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.List;

public class CarCall implements SafeParcelable {
    public static final Creator<CarCall> CREATOR;
    public List<String> cannedTextResponses;
    public Details details;
    public boolean hasChildren;
    public final int id;
    final int mVersionCode;
    public CarCall parent;
    public String remainingPostDialSequence;
    public int state;

    public static final class Details implements SafeParcelable {
        public static final Creator<Details> CREATOR;
        public String callerDisplayName;
        public long connectTimeMillis;
        public String disconnectCause;
        public Uri gatewayInfoGatewayAddress;
        public Uri gatewayInfoOriginalAddress;
        public Uri handle;
        final int mVersionCode;

        static {
            CREATOR = new c();
        }

        public Details(int versionCode, Uri handle, String callerDisplayName, String disconnectCause, long connectTimeMillis, Uri gatewayInfoOriginalAddress, Uri gatewayInfoGatewayAddress) {
            this.mVersionCode = versionCode;
            this.handle = handle;
            this.callerDisplayName = callerDisplayName;
            this.disconnectCause = disconnectCause;
            this.connectTimeMillis = connectTimeMillis;
            this.gatewayInfoOriginalAddress = gatewayInfoOriginalAddress;
            this.gatewayInfoGatewayAddress = gatewayInfoGatewayAddress;
        }

        public int describeContents() {
            return this.mVersionCode;
        }

        public int getVersionCode() {
            return this.mVersionCode;
        }

        public String toString() {
            return "Details{handle=" + this.handle + ", callerDisplayName='" + this.callerDisplayName + '\'' + ", disconnectCause='" + this.disconnectCause + '\'' + ", connectTimeMillis=" + this.connectTimeMillis + ", gatewayInfoOriginal=" + this.gatewayInfoOriginalAddress + ", gatewayInfoGateway=" + this.gatewayInfoGatewayAddress + '}';
        }

        public void writeToParcel(Parcel dest, int flags) {
            c.a(this, dest, flags);
        }
    }

    static {
        CREATOR = new b();
    }

    public CarCall(int versionCode, int id, CarCall parent, List<String> cannedTextResponses, String remainingPostDialSequence, int state, Details details, boolean hasChildren) {
        this.mVersionCode = versionCode;
        this.id = id;
        this.parent = parent;
        this.cannedTextResponses = cannedTextResponses;
        this.remainingPostDialSequence = remainingPostDialSequence;
        this.state = state;
        this.details = details;
        this.hasChildren = hasChildren;
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object o) {
        return (o instanceof CarCall) && this.id == ((CarCall) o).id;
    }

    public int getVersionCode() {
        return this.mVersionCode;
    }

    public int hashCode() {
        return this.id;
    }

    public String toString() {
        return "CarCall{id=" + this.id + ", parent=" + this.parent + ", cannedTextResponses=" + this.cannedTextResponses + ", remainingPostDialSequence='" + this.remainingPostDialSequence + '\'' + ", state=" + this.state + ", details=" + this.details + ", hasChildren=" + this.hasChildren + '}';
    }

    public void writeToParcel(Parcel dest, int flags) {
        b.a(this, dest, flags);
    }
}
