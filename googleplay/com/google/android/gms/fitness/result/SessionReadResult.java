package com.google.android.gms.fitness.result;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.fitness.data.Session;
import com.google.android.gms.fitness.data.p;
import com.google.android.gms.internal.kl;
import java.util.Collections;
import java.util.List;

public class SessionReadResult implements Result, SafeParcelable {
    public static final Creator<SessionReadResult> CREATOR;
    private final Status EU;
    private final List<Session> ahP;
    private final List<p> aiC;
    private final int mVersionCode;

    static {
        CREATOR = new f();
    }

    SessionReadResult(int versionCode, List<Session> sessions, List<p> sessionDataSets, Status status) {
        this.mVersionCode = versionCode;
        this.ahP = sessions;
        this.aiC = Collections.unmodifiableList(sessionDataSets);
        this.EU = status;
    }

    private boolean b(SessionReadResult sessionReadResult) {
        return this.EU.equals(sessionReadResult.EU) && kl.equal(this.ahP, sessionReadResult.ahP) && kl.equal(this.aiC, sessionReadResult.aiC);
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object that) {
        return this == that || ((that instanceof SessionReadResult) && b((SessionReadResult) that));
    }

    public List<Session> getSessions() {
        return this.ahP;
    }

    public Status getStatus() {
        return this.EU;
    }

    int getVersionCode() {
        return this.mVersionCode;
    }

    public int hashCode() {
        return kl.hashCode(this.EU, this.ahP, this.aiC);
    }

    public List<p> mk() {
        return this.aiC;
    }

    public String toString() {
        return kl.j(this).a("status", this.EU).a("sessions", this.ahP).a("sessionDataSets", this.aiC).toString();
    }

    public void writeToParcel(Parcel dest, int flags) {
        f.a(this, dest, flags);
    }
}
