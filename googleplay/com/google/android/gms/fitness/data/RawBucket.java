package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.kl;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public final class RawBucket implements SafeParcelable {
    public static final Creator<RawBucket> CREATOR;
    final long Va;
    final int agQ;
    final long agb;
    final Session agd;
    final List<RawDataSet> agl;
    final int agm;
    final boolean agn;
    final int mVersionCode;

    static {
        CREATOR = new l();
    }

    RawBucket(int versionCode, long startTimeMillis, long endTimeMillis, Session session, int activity, List<RawDataSet> dataSets, int bucketType, boolean serverHasMoreData) {
        this.mVersionCode = versionCode;
        this.Va = startTimeMillis;
        this.agb = endTimeMillis;
        this.agd = session;
        this.agQ = activity;
        this.agl = dataSets;
        this.agm = bucketType;
        this.agn = serverHasMoreData;
    }

    public RawBucket(Bucket bucket, List<DataSource> uniqueDataSources, List<DataType> uniqueDataTypes) {
        this.mVersionCode = 2;
        this.Va = bucket.getStartTime(TimeUnit.MILLISECONDS);
        this.agb = bucket.getEndTime(TimeUnit.MILLISECONDS);
        this.agd = bucket.getSession();
        this.agQ = bucket.ln();
        this.agm = bucket.getBucketType();
        this.agn = bucket.serverHasMoreData();
        List<DataSet> dataSets = bucket.getDataSets();
        this.agl = new ArrayList(dataSets.size());
        for (DataSet rawDataSet : dataSets) {
            this.agl.add(new RawDataSet(rawDataSet, uniqueDataSources, uniqueDataTypes));
        }
    }

    private boolean a(RawBucket rawBucket) {
        return this.Va == rawBucket.Va && this.agb == rawBucket.agb && this.agQ == rawBucket.agQ && kl.equal(this.agl, rawBucket.agl) && this.agm == rawBucket.agm && this.agn == rawBucket.agn;
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object o) {
        return this == o || ((o instanceof RawBucket) && a((RawBucket) o));
    }

    public int hashCode() {
        return kl.hashCode(Long.valueOf(this.Va), Long.valueOf(this.agb), Integer.valueOf(this.agm));
    }

    public String toString() {
        return kl.j(this).a("startTime", Long.valueOf(this.Va)).a("endTime", Long.valueOf(this.agb)).a("activity", Integer.valueOf(this.agQ)).a("dataSets", this.agl).a("bucketType", Integer.valueOf(this.agm)).a("serverHasMoreData", Boolean.valueOf(this.agn)).toString();
    }

    public void writeToParcel(Parcel parcel, int flags) {
        l.a(this, parcel, flags);
    }
}
