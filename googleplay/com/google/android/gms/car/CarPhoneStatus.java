package com.google.android.gms.car;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class CarPhoneStatus implements SafeParcelable {
    public static final Creator<CarPhoneStatus> CREATOR;
    public CarCall[] calls;
    final int mVersionCode;
    public int signalStrength;

    public static class CarCall implements SafeParcelable {
        public static final Creator<CarCall> CREATOR;
        public int callDurationSeconds;
        public String callerId;
        public String callerNumber;
        public String callerNumberType;
        public byte[] callerThumbnail;
        final int mVersionCode;
        public int state;

        static {
            CREATOR = new q();
        }

        public CarCall() {
            this.mVersionCode = 1;
        }

        public CarCall(int versionCode, int state, int callDurationSeconds, String callerNumber, String callerId, String callerNumberType, byte[] callerThumbnail) {
            this.mVersionCode = versionCode;
            this.state = state;
            this.callerNumber = callerNumber;
            this.callDurationSeconds = callDurationSeconds;
            this.callerId = callerId;
            this.callerNumberType = callerNumberType;
            this.callerThumbnail = callerThumbnail;
        }

        public int describeContents() {
            return 0;
        }

        public int getVersionCode() {
            return this.mVersionCode;
        }

        public void writeToParcel(Parcel dest, int flags) {
            q.a(this, dest, flags);
        }
    }

    static {
        CREATOR = new r();
    }

    public CarPhoneStatus() {
        this.mVersionCode = 1;
    }

    public CarPhoneStatus(int versionCode, CarCall[] calls, int signalStrength) {
        this.mVersionCode = versionCode;
        this.calls = calls;
        this.signalStrength = signalStrength;
    }

    public int describeContents() {
        return 0;
    }

    public int getVersionCode() {
        return this.mVersionCode;
    }

    public void writeToParcel(Parcel dest, int flags) {
        r.a(this, dest, flags);
    }
}
