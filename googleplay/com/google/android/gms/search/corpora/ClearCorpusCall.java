package com.google.android.gms.search.corpora;

import android.os.Parcel;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class ClearCorpusCall {

    public static class Response implements Result, SafeParcelable {
        public static final b CREATOR;
        final int mVersionCode;
        public Status status;

        static {
            CREATOR = new b();
        }

        public Response() {
            this.mVersionCode = 1;
        }

        Response(int versionCode, Status status) {
            this.mVersionCode = versionCode;
            this.status = status;
        }

        public int describeContents() {
            b bVar = CREATOR;
            return 0;
        }

        public Status getStatus() {
            return this.status;
        }

        public void writeToParcel(Parcel out, int flags) {
            b bVar = CREATOR;
            b.a(this, out, flags);
        }
    }

    public static class b implements SafeParcelable {
        public static final a CREATOR;
        public String corpusName;
        final int mVersionCode;
        public String packageName;

        static {
            CREATOR = new a();
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
            a aVar = CREATOR;
            return 0;
        }

        public void writeToParcel(Parcel out, int flags) {
            a aVar = CREATOR;
            a.a(this, out, flags);
        }
    }
}
