package com.google.android.gms.fitness.request;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.internal.kl;
import java.util.Collections;
import java.util.List;

public class SessionReadRequest implements SafeParcelable {
    public static final Creator<SessionReadRequest> CREATOR;
    private final long Va;
    private final long agb;
    private final List<DataType> agj;
    private final List<DataSource> ahO;
    private final boolean ahY;
    private final String aiq;
    private boolean air;
    private final List<String> ais;
    private final int mVersionCode;
    private final String vY;

    static {
        CREATOR = new s();
    }

    SessionReadRequest(int versionCode, String sessionName, String sessionId, long startTimeMillis, long endTimeMillis, List<DataType> dataTypes, List<DataSource> dataSources, boolean getSessionsFromAllApps, boolean serverQueriesEnabled, List<String> excludedPackages) {
        this.mVersionCode = versionCode;
        this.aiq = sessionName;
        this.vY = sessionId;
        this.Va = startTimeMillis;
        this.agb = endTimeMillis;
        this.agj = Collections.unmodifiableList(dataTypes);
        this.ahO = Collections.unmodifiableList(dataSources);
        this.air = getSessionsFromAllApps;
        this.ahY = serverQueriesEnabled;
        this.ais = excludedPackages;
    }

    private boolean a(SessionReadRequest sessionReadRequest) {
        return kl.equal(this.aiq, sessionReadRequest.aiq) && this.vY.equals(sessionReadRequest.vY) && this.Va == sessionReadRequest.Va && this.agb == sessionReadRequest.agb && kl.equal(this.agj, sessionReadRequest.agj) && kl.equal(this.ahO, sessionReadRequest.ahO) && this.air == sessionReadRequest.air && this.ais.equals(sessionReadRequest.ais) && this.ahY == sessionReadRequest.ahY;
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object o) {
        return this == o || ((o instanceof SessionReadRequest) && a((SessionReadRequest) o));
    }

    public List<DataSource> getDataSources() {
        return this.ahO;
    }

    public List<DataType> getDataTypes() {
        return this.agj;
    }

    public List<String> getExcludedPackages() {
        return this.ais;
    }

    public String getSessionId() {
        return this.vY;
    }

    public String getSessionName() {
        return this.aiq;
    }

    int getVersionCode() {
        return this.mVersionCode;
    }

    public int hashCode() {
        return kl.hashCode(this.aiq, this.vY, Long.valueOf(this.Va), Long.valueOf(this.agb));
    }

    public boolean lO() {
        return this.ahY;
    }

    public long lo() {
        return this.Va;
    }

    public long lp() {
        return this.agb;
    }

    public boolean mc() {
        return this.air;
    }

    public String toString() {
        return kl.j(this).a("sessionName", this.aiq).a("sessionId", this.vY).a("startTimeMillis", Long.valueOf(this.Va)).a("endTimeMillis", Long.valueOf(this.agb)).a("dataTypes", this.agj).a("dataSources", this.ahO).a("sessionsFromAllApps", Boolean.valueOf(this.air)).a("excludedPackages", this.ais).a("useServer", Boolean.valueOf(this.ahY)).toString();
    }

    public void writeToParcel(Parcel dest, int flags) {
        s.a(this, dest, flags);
    }
}
