package com.google.android.gms.fitness.service;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.j;
import com.google.android.gms.fitness.data.j.a;
import com.google.android.gms.internal.kl;

public class FitnessSensorServiceRequest implements SafeParcelable {
    public static final Creator<FitnessSensorServiceRequest> CREATOR;
    private final DataSource aga;
    private final long aiF;
    private final long aiG;
    private final j aid;
    private final int mVersionCode;

    static {
        CREATOR = new a();
    }

    FitnessSensorServiceRequest(int versionCode, DataSource dataSource, IBinder listenerBinder, long samplingRateMicros, long batchIntervalMicros) {
        this.mVersionCode = versionCode;
        this.aga = dataSource;
        this.aid = a.bK(listenerBinder);
        this.aiF = samplingRateMicros;
        this.aiG = batchIntervalMicros;
    }

    private boolean a(FitnessSensorServiceRequest fitnessSensorServiceRequest) {
        return kl.equal(this.aga, fitnessSensorServiceRequest.aga) && this.aiF == fitnessSensorServiceRequest.aiF && this.aiG == fitnessSensorServiceRequest.aiG;
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object that) {
        return this == that || ((that instanceof FitnessSensorServiceRequest) && a((FitnessSensorServiceRequest) that));
    }

    public DataSource getDataSource() {
        return this.aga;
    }

    public long getSamplingRateMicros() {
        return this.aiF;
    }

    int getVersionCode() {
        return this.mVersionCode;
    }

    public int hashCode() {
        return kl.hashCode(this.aga, Long.valueOf(this.aiF), Long.valueOf(this.aiG));
    }

    IBinder lZ() {
        return this.aid.asBinder();
    }

    public long mm() {
        return this.aiG;
    }

    public String toString() {
        return String.format("FitnessSensorServiceRequest{%s}", new Object[]{this.aga});
    }

    public void writeToParcel(Parcel parcel, int flags) {
        a.a(this, parcel, flags);
    }
}
