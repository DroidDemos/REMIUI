package com.google.android.gms.search.queries;

import android.os.Parcel;
import com.google.android.gms.appdatasearch.QuerySpecification;
import com.google.android.gms.appdatasearch.SearchResults;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class QueryCall {

    public static class Response implements Result, SafeParcelable {
        public static final h CREATOR;
        public SearchResults documents;
        final int mVersionCode;
        public Status status;

        static {
            CREATOR = new h();
        }

        public Response() {
            this.mVersionCode = 1;
        }

        Response(int versionCode, Status status, SearchResults documents) {
            this.mVersionCode = versionCode;
            this.status = status;
            this.documents = documents;
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
        public QuerySpecification aLU;
        public int aLZ;
        public String[] aMc;
        final int mVersionCode;
        public String packageName;
        public String query;
        public int start;

        static {
            CREATOR = new g();
        }

        public b() {
            this.mVersionCode = 1;
        }

        b(int i, String str, String str2, String[] strArr, int i2, int i3, QuerySpecification querySpecification) {
            this.mVersionCode = i;
            this.query = str;
            this.packageName = str2;
            this.aMc = strArr;
            this.start = i2;
            this.aLZ = i3;
            this.aLU = querySpecification;
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
