package com.google.android.gms.fitness.request;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.data.Session;
import com.google.android.gms.internal.kl;
import java.util.Collections;
import java.util.List;

public class DataDeleteRequest implements SafeParcelable {
    public static final Creator<DataDeleteRequest> CREATOR;
    private final long Va;
    private final long agb;
    private final List<DataType> agj;
    private final List<DataSource> ahO;
    private final List<Session> ahP;
    private final boolean ahQ;
    private final boolean ahR;
    private final int mVersionCode;

    static {
        CREATOR = new d();
    }

    DataDeleteRequest(int versionCode, long startTimeMillis, long endTimeMillis, List<DataSource> dataSources, List<DataType> dataTypes, List<Session> sessions, boolean deleteAllData, boolean deleteAllSessions) {
        this.mVersionCode = versionCode;
        this.Va = startTimeMillis;
        this.agb = endTimeMillis;
        this.ahO = Collections.unmodifiableList(dataSources);
        this.agj = Collections.unmodifiableList(dataTypes);
        this.ahP = sessions;
        this.ahQ = deleteAllData;
        this.ahR = deleteAllSessions;
    }

    private boolean a(DataDeleteRequest dataDeleteRequest) {
        return this.Va == dataDeleteRequest.Va && this.agb == dataDeleteRequest.agb && kl.equal(this.ahO, dataDeleteRequest.ahO) && kl.equal(this.agj, dataDeleteRequest.agj) && kl.equal(this.ahP, dataDeleteRequest.ahP) && this.ahQ == dataDeleteRequest.ahQ && this.ahR == dataDeleteRequest.ahR;
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object o) {
        return o == this || ((o instanceof DataDeleteRequest) && a((DataDeleteRequest) o));
    }

    public List<DataSource> getDataSources() {
        return this.ahO;
    }

    public List<DataType> getDataTypes() {
        return this.agj;
    }

    public List<Session> getSessions() {
        return this.ahP;
    }

    int getVersionCode() {
        return this.mVersionCode;
    }

    public int hashCode() {
        return kl.hashCode(Long.valueOf(this.Va), Long.valueOf(this.agb));
    }

    public boolean lK() {
        return this.ahQ;
    }

    public boolean lL() {
        return this.ahR;
    }

    public long lo() {
        return this.Va;
    }

    public long lp() {
        return this.agb;
    }

    public String toString() {
        return kl.j(this).a("startTimeMillis", Long.valueOf(this.Va)).a("endTimeMillis", Long.valueOf(this.agb)).a("dataSources", this.ahO).a("dateTypes", this.agj).a("sessions", this.ahP).a("deleteAllData", Boolean.valueOf(this.ahQ)).a("deleteAllSessions", Boolean.valueOf(this.ahR)).toString();
    }

    public void writeToParcel(Parcel dest, int flags) {
        d.a(this, dest, flags);
    }
}
