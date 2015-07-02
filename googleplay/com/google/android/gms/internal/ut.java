package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class ut {

    public static class a implements SafeParcelable {
        public static final uu CREATOR;
        public long aLC;
        public long aLD;
        public boolean blocked;
        final int mVersionCode;
        public String packageName;

        static {
            CREATOR = new uu();
        }

        public a() {
            this.mVersionCode = 1;
        }

        a(int i, String str, long j, boolean z, long j2) {
            this.mVersionCode = i;
            this.packageName = str;
            this.aLC = j;
            this.blocked = z;
            this.aLD = j2;
        }

        public int describeContents() {
            uu uuVar = CREATOR;
            return 0;
        }

        public void writeToParcel(Parcel out, int flags) {
            uu uuVar = CREATOR;
            uu.a(this, out, flags);
        }
    }

    public static class b implements SafeParcelable {
        public static final uv CREATOR;
        final int mVersionCode;

        static {
            CREATOR = new uv();
        }

        public b() {
            this.mVersionCode = 1;
        }

        b(int i) {
            this.mVersionCode = i;
        }

        public int describeContents() {
            uv uvVar = CREATOR;
            return 0;
        }

        public void writeToParcel(Parcel out, int flags) {
            uv uvVar = CREATOR;
            uv.a(this, out, flags);
        }
    }

    public static class c implements Result, SafeParcelable {
        public static final uw CREATOR;
        public a[] aLE;
        public long aLF;
        public long aLG;
        public long aLH;
        final int mVersionCode;
        public Status status;

        static {
            CREATOR = new uw();
        }

        public c() {
            this.mVersionCode = 1;
        }

        c(int i, Status status, a[] aVarArr, long j, long j2, long j3) {
            this.mVersionCode = i;
            this.status = status;
            this.aLE = aVarArr;
            this.aLF = j;
            this.aLG = j2;
            this.aLH = j3;
        }

        public int describeContents() {
            uw uwVar = CREATOR;
            return 0;
        }

        public Status getStatus() {
            return this.status;
        }

        public void writeToParcel(Parcel out, int flags) {
            uw uwVar = CREATOR;
            uw.a(this, out, flags);
        }
    }
}
