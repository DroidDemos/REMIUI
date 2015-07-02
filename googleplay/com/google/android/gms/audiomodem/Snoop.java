package com.google.android.gms.audiomodem;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class Snoop {

    public static class Params implements SafeParcelable {
        public static final Creator<Params> CREATOR;
        private final Encoding[] FO;
        private final boolean FP;
        private final boolean FQ;
        private final long FR;
        private final int mVersionCode;

        static {
            CREATOR = new e();
        }

        Params(int versionCode, Encoding[] encodings, boolean shouldCallbackOnBroadcasterDetected, boolean shouldCallbackOnNoBroadcasterDetected, long durationWithNoBroadcasterMillis) {
            this.mVersionCode = versionCode;
            this.FO = encodings;
            this.FP = shouldCallbackOnBroadcasterDetected;
            this.FQ = shouldCallbackOnNoBroadcasterDetected;
            this.FR = durationWithNoBroadcasterMillis;
        }

        public int describeContents() {
            return 0;
        }

        public long getDurationWithNoBroadcasterMillis() {
            return this.FR;
        }

        public Encoding[] getEncodings() {
            return this.FO;
        }

        int getVersionCode() {
            return this.mVersionCode;
        }

        public boolean shouldCallbackOnBroadcasterDetected() {
            return this.FP;
        }

        public boolean shouldCallbackOnNoBroadcasterDetected() {
            return this.FQ;
        }

        public void writeToParcel(Parcel dest, int flags) {
            e.a(this, dest, flags);
        }
    }
}
