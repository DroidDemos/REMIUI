package com.google.android.gms.fitness.result;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.fitness.data.Session;
import com.google.android.gms.internal.kl;
import java.util.Collections;
import java.util.List;

public class SessionStopResult implements Result, SafeParcelable {
    public static final Creator<SessionStopResult> CREATOR;
    private final Status EU;
    private final List<Session> ahP;
    private final int mVersionCode;

    static {
        CREATOR = new g();
    }

    SessionStopResult(int versionCode, Status status, List<Session> sessions) {
        this.mVersionCode = versionCode;
        this.EU = status;
        this.ahP = Collections.unmodifiableList(sessions);
    }

    private boolean b(SessionStopResult sessionStopResult) {
        return this.EU.equals(sessionStopResult.EU) && kl.equal(this.ahP, sessionStopResult.ahP);
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object o) {
        return this == o || ((o instanceof SessionStopResult) && b((SessionStopResult) o));
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
        return kl.hashCode(this.EU, this.ahP);
    }

    public String toString() {
        return kl.j(this).a("status", this.EU).a("sessions", this.ahP).toString();
    }

    public void writeToParcel(Parcel dest, int flags) {
        g.a(this, dest, flags);
    }
}
