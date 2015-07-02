package com.google.android.gms.search.corpora;

import android.os.Parcel;
import com.google.android.gms.appdatasearch.RegisterCorpusInfo;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class GetCorpusInfoCall {

    public static class Response implements Result, SafeParcelable {
        public static final d CREATOR;
        public RegisterCorpusInfo info;
        final int mVersionCode;
        public Status status;

        static {
            CREATOR = new d();
        }

        public Response() {
            this.mVersionCode = 1;
        }

        Response(int versionCode, Status status, RegisterCorpusInfo info) {
            this.mVersionCode = versionCode;
            this.status = status;
            this.info = info;
        }

        public int describeContents() {
            d dVar = CREATOR;
            return 0;
        }

        public Status getStatus() {
            return this.status;
        }

        public void writeToParcel(Parcel out, int flags) {
            d dVar = CREATOR;
            d.a(this, out, flags);
        }
    }

    public static class b implements SafeParcelable {
        public static final c CREATOR;
        public String corpusName;
        final int mVersionCode;
        public String packageName;

        static {
            CREATOR = new c();
        }

        public b() {
            this.mVersionCode = 1;
        }

        b(int i, String str, String str2) {
            this.mVersionCode = i;
            this.packageName = str;
            this.corpusName = str2;
        }

        public int describeContents() {
            c cVar = CREATOR;
            return 0;
        }

        public void writeToParcel(Parcel out, int flags) {
            c cVar = CREATOR;
            c.a(this, out, flags);
        }
    }
}
