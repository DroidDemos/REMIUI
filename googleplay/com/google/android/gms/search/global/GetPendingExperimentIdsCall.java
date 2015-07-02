package com.google.android.gms.search.global;

import android.os.Parcel;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class GetPendingExperimentIdsCall {

    public static class Request implements SafeParcelable {
        public static final g CREATOR;
        final int mVersionCode;

        static {
            CREATOR = new g();
        }

        public Request() {
            this.mVersionCode = 1;
        }

        Request(int versionCode) {
            this.mVersionCode = versionCode;
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

    public static class Response implements Result, SafeParcelable {
        public static final h CREATOR;
        public int[] experimentIds;
        final int mVersionCode;
        public Status status;

        static {
            CREATOR = new h();
        }

        public Response() {
            this.mVersionCode = 1;
        }

        Response(int versionCode, Status status, int[] experimentIds) {
            this.mVersionCode = versionCode;
            this.status = status;
            this.experimentIds = experimentIds;
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
}
