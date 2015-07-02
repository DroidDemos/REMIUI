package com.google.android.gms.search.corpora;

import android.os.Parcel;
import com.google.android.gms.appdatasearch.CorpusStatus;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class GetCorpusStatusCall {

    public static class Response implements Result, SafeParcelable {
        public static final f CREATOR;
        public CorpusStatus corpusStatus;
        final int mVersionCode;
        public Status status;

        static {
            CREATOR = new f();
        }

        public Response() {
            this.mVersionCode = 1;
        }

        Response(int versionCode, Status status, CorpusStatus corpusStatus) {
            this.mVersionCode = versionCode;
            this.status = status;
            this.corpusStatus = corpusStatus;
        }

        public int describeContents() {
            f fVar = CREATOR;
            return 0;
        }

        public Status getStatus() {
            return this.status;
        }

        public void writeToParcel(Parcel out, int flags) {
            f fVar = CREATOR;
            f.a(this, out, flags);
        }
    }

    public static class b implements SafeParcelable {
        public static final e CREATOR;
        public String corpusName;
        final int mVersionCode;
        public String packageName;

        static {
            CREATOR = new e();
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
            e eVar = CREATOR;
            return 0;
        }

        public void writeToParcel(Parcel out, int flags) {
            e eVar = CREATOR;
            e.a(this, out, flags);
        }
    }
}
