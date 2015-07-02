package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.request.l.a;
import com.google.android.gms.internal.kl;
import java.util.Collections;
import java.util.List;

public class StartBleScanRequest implements SafeParcelable {
    public static final Creator<StartBleScanRequest> CREATOR;
    private final List<DataType> agj;
    private final l ait;
    private final int aiu;
    private final int mVersionCode;

    static {
        CREATOR = new ab();
    }

    StartBleScanRequest(int versionCode, List<DataType> dataTypes, IBinder bleScanCallback, int timeoutSecs) {
        this.mVersionCode = versionCode;
        this.agj = dataTypes;
        this.ait = a.bV(bleScanCallback);
        this.aiu = timeoutSecs;
    }

    public int describeContents() {
        return 0;
    }

    public List<DataType> getDataTypes() {
        return Collections.unmodifiableList(this.agj);
    }

    public int getTimeoutSecs() {
        return this.aiu;
    }

    int getVersionCode() {
        return this.mVersionCode;
    }

    public IBinder mf() {
        return this.ait.asBinder();
    }

    public String toString() {
        return kl.j(this).a("dataTypes", this.agj).a("timeoutSecs", Integer.valueOf(this.aiu)).toString();
    }

    public void writeToParcel(Parcel parcel, int flags) {
        ab.a(this, parcel, flags);
    }
}
