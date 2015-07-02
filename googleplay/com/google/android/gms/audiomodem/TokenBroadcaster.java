package com.google.android.gms.audiomodem;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class TokenBroadcaster {

    public static class Params implements SafeParcelable {
        public static final Creator<Params> CREATOR;
        private final Encoding[] FO;
        private final int FS;
        private final byte[] Fv;
        private final int mVersionCode;

        static {
            CREATOR = new f();
        }

        Params(int versionCode, byte[] token, int volume, Encoding[] encodings) {
            this.mVersionCode = versionCode;
            this.Fv = token;
            this.FS = volume;
            this.FO = encodings;
        }

        public int describeContents() {
            return 0;
        }

        public Encoding[] getEncodings() {
            return this.FO;
        }

        public byte[] getToken() {
            return this.Fv;
        }

        int getVersionCode() {
            return this.mVersionCode;
        }

        public int getVolume() {
            return this.FS;
        }

        public void writeToParcel(Parcel dest, int flags) {
            f.a(this, dest, flags);
        }
    }
}
