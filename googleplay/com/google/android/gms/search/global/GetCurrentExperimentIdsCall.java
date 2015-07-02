package com.google.android.gms.search.global;

import android.os.Parcel;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class GetCurrentExperimentIdsCall {

    public static class Request implements SafeParcelable {
        public static final a CREATOR;
        final int mVersionCode;

        static {
            CREATOR = new a();
        }

        public Request() {
            this.mVersionCode = 1;
        }

        Request(int versionCode) {
            this.mVersionCode = versionCode;
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

    public static class Response implements Result, SafeParcelable {
        public static final b CREATOR;
        public int[] experimentIds;
        final int mVersionCode;
        public Status status;

        static {
            CREATOR = new b();
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
}
