package com.google.android.gms.search.queries;

import android.os.Parcel;
import com.google.android.gms.appdatasearch.GlobalSearchQuerySpecification;
import com.google.android.gms.appdatasearch.SearchResults;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class GlobalQueryCall {

    public static class Response implements Result, SafeParcelable {
        public static final f CREATOR;
        public SearchResults documents;
        final int mVersionCode;
        public Status status;

        static {
            CREATOR = new f();
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
        public int aLZ;
        public GlobalSearchQuerySpecification aMa;
        final int mVersionCode;
        public String query;
        public int start;

        static {
            CREATOR = new e();
        }

        public b() {
            this.mVersionCode = 1;
        }

        b(int i, String str, int i2, int i3, GlobalSearchQuerySpecification globalSearchQuerySpecification) {
            this.mVersionCode = i;
            this.query = str;
            this.start = i2;
            this.aLZ = i3;
            this.aMa = globalSearchQuerySpecification;
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
