package com.google.android.gms.search.global;

import android.os.Parcel;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class SetExperimentIdsCall {

    public static class Request implements SafeParcelable {
        public static final i CREATOR;
        public boolean emergency;
        final int mVersionCode;
        public byte[] serializedExperimentConfig;

        static {
            CREATOR = new i();
        }

        public Request() {
            this.mVersionCode = 1;
        }

        Request(int versionCode, byte[] serializedExperimentConfig, boolean emergency) {
            this.mVersionCode = versionCode;
            this.serializedExperimentConfig = serializedExperimentConfig;
            this.emergency = emergency;
        }

        public int describeContents() {
            i iVar = CREATOR;
            return 0;
        }

        public void writeToParcel(Parcel out, int flags) {
            i iVar = CREATOR;
            i.a(this, out, flags);
        }
    }

    public static class Response implements Result, SafeParcelable {
        public static final j CREATOR;
        final int mVersionCode;
        public Status status;

        static {
            CREATOR = new j();
        }

        public Response() {
            this.mVersionCode = 1;
        }

        Response(int versionCode, Status status) {
            this.mVersionCode = versionCode;
            this.status = status;
        }

        public int describeContents() {
            j jVar = CREATOR;
            return 0;
        }

        public Status getStatus() {
            return this.status;
        }

        public void writeToParcel(Parcel out, int flags) {
            j jVar = CREATOR;
            j.a(this, out, flags);
        }
    }
}
