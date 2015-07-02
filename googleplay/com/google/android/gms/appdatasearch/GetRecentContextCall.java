package com.google.android.gms.appdatasearch;

import android.accounts.Account;
import android.os.Parcel;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.List;

public class GetRecentContextCall {

    public static class Request implements SafeParcelable {
        public static final i CREATOR;
        public final Account filterAccount;
        final int mVersionCode;

        static {
            CREATOR = new i();
        }

        public Request() {
            this(null);
        }

        Request(int versionCode, Account filterAccount) {
            this.mVersionCode = versionCode;
            this.filterAccount = filterAccount;
        }

        public Request(Account filterAccount) {
            this(1, filterAccount);
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
        public List<UsageInfo> context;
        final int mVersionCode;
        public Status status;

        static {
            CREATOR = new j();
        }

        public Response() {
            this.mVersionCode = 1;
        }

        Response(int versionCode, Status status, List<UsageInfo> usageInfo) {
            this.mVersionCode = versionCode;
            this.status = status;
            this.context = usageInfo;
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
