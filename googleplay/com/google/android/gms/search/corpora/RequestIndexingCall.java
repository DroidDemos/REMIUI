package com.google.android.gms.search.corpora;

import android.os.Parcel;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class RequestIndexingCall {

    public static class Response implements Result, SafeParcelable {
        public static final h CREATOR;
        final int mVersionCode;
        public boolean scheduled;
        public Status status;

        static {
            CREATOR = new h();
        }

        public Response() {
            this.mVersionCode = 1;
        }

        Response(int versionCode, Status status, boolean scheduled) {
            this.mVersionCode = versionCode;
            this.status = status;
            this.scheduled = scheduled;
        }

        public int describeContents() {
            h hVar = CREATOR;
            return 0;
        }

        public Status getStatus() {
            return this.status;
        }

        public void writeToParcel(Parcel out, int flags) {
            h hVar = CREATOR;
            h.a(this, out, flags);
        }
    }

    public static class b implements SafeParcelable {
        public static final g CREATOR;
        public long aLM;
        public String corpusName;
        final int mVersionCode;
        public String packageName;

        static {
            CREATOR = new g();
        }

        public b() {
            this.mVersionCode = 1;
        }

        b(int i, String str, String str2, long j) {
            this.mVersionCode = i;
            this.packageName = str;
            this.corpusName = str2;
            this.aLM = j;
        }

        public int describeContents() {
            g gVar = CREATOR;
            return 0;
        }

        public void writeToParcel(Parcel out, int flags) {
            g gVar = CREATOR;
            g.a(this, out, flags);
        }
    }
}
