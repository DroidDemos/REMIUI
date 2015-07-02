package com.google.android.gms.fitness.request;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.fitness.data.DataPoint;
import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.fitness.data.Session;
import com.google.android.gms.internal.kl;
import java.util.Collections;
import java.util.List;

public class SessionInsertRequest implements SafeParcelable {
    public static final Creator<SessionInsertRequest> CREATOR;
    private final Session agd;
    private final List<DataSet> agl;
    private final List<DataPoint> aio;
    private final int mVersionCode;

    static {
        CREATOR = new r();
    }

    SessionInsertRequest(int versionCode, Session session, List<DataSet> dataSets, List<DataPoint> aggregateDataPoints) {
        this.mVersionCode = versionCode;
        this.agd = session;
        this.agl = Collections.unmodifiableList(dataSets);
        this.aio = Collections.unmodifiableList(aggregateDataPoints);
    }

    private boolean a(SessionInsertRequest sessionInsertRequest) {
        return kl.equal(this.agd, sessionInsertRequest.agd) && kl.equal(this.agl, sessionInsertRequest.agl) && kl.equal(this.aio, sessionInsertRequest.aio);
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object o) {
        return o == this || ((o instanceof SessionInsertRequest) && a((SessionInsertRequest) o));
    }

    public List<DataPoint> getAggregateDataPoints() {
        return this.aio;
    }

    public List<DataSet> getDataSets() {
        return this.agl;
    }

    public Session getSession() {
        return this.agd;
    }

    int getVersionCode() {
        return this.mVersionCode;
    }

    public int hashCode() {
        return kl.hashCode(this.agd, this.agl, this.aio);
    }

    public String toString() {
        return kl.j(this).a("session", this.agd).a("dataSets", this.agl).a("aggregateDataPoints", this.aio).toString();
    }

    public void writeToParcel(Parcel dest, int flags) {
        r.a(this, dest, flags);
    }
}
