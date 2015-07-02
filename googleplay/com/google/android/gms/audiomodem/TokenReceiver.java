package com.google.android.gms.audiomodem;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class TokenReceiver {

    public static class Params implements SafeParcelable {
        public static final Creator<Params> CREATOR;
        private final Encoding[] FO;
        private final int mVersionCode;

        static {
            CREATOR = new g();
        }

        Params(int versionCode, Encoding[] encodings) {
            this.mVersionCode = versionCode;
            this.FO = encodings;
        }

        public int describeContents() {
            return 0;
        }

        public Encoding[] getEncodings() {
            return this.FO;
        }

        int getVersionCode() {
            return this.mVersionCode;
        }

        public void writeToParcel(Parcel dest, int flags) {
            g.a(this, dest, flags);
        }
    }
}
