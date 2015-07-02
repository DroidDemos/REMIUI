package com.google.android.gms.fitness.result;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.fitness.data.Bucket;
import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.data.RawBucket;
import com.google.android.gms.fitness.data.RawDataSet;
import com.google.android.gms.internal.kl;
import java.util.ArrayList;
import java.util.List;

public class DataReadResult implements Result, SafeParcelable {
    public static final Creator<DataReadResult> CREATOR;
    private final Status EU;
    private final List<DataSet> agl;
    private final List<DataSource> agv;
    private final List<DataType> aiA;
    private final List<Bucket> aiy;
    private int aiz;
    private final int mVersionCode;

    static {
        CREATOR = new b();
    }

    DataReadResult(int versionCode, List<RawDataSet> dataSets, Status status, List<RawBucket> buckets, int batchCount, List<DataSource> uniqueDataSources, List<DataType> uniqueDataTypes) {
        this.mVersionCode = versionCode;
        this.EU = status;
        this.aiz = batchCount;
        this.agv = uniqueDataSources;
        this.aiA = uniqueDataTypes;
        this.agl = new ArrayList(dataSets.size());
        for (RawDataSet dataSet : dataSets) {
            this.agl.add(new DataSet(dataSet, uniqueDataSources, uniqueDataTypes));
        }
        this.aiy = new ArrayList(buckets.size());
        for (RawBucket bucket : buckets) {
            this.aiy.add(new Bucket(bucket, uniqueDataSources, uniqueDataTypes));
        }
    }

    private boolean c(DataReadResult dataReadResult) {
        return this.EU.equals(dataReadResult.EU) && kl.equal(this.agl, dataReadResult.agl) && kl.equal(this.aiy, dataReadResult.aiy);
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object that) {
        return this == that || ((that instanceof DataReadResult) && c((DataReadResult) that));
    }

    public Status getStatus() {
        return this.EU;
    }

    int getVersionCode() {
        return this.mVersionCode;
    }

    public int hashCode() {
        return kl.hashCode(this.EU, this.agl, this.aiy);
    }

    List<DataSource> lt() {
        return this.agv;
    }

    public int mg() {
        return this.aiz;
    }

    List<RawBucket> mh() {
        List<RawBucket> arrayList = new ArrayList(this.aiy.size());
        for (Bucket rawBucket : this.aiy) {
            arrayList.add(new RawBucket(rawBucket, this.agv, this.aiA));
        }
        return arrayList;
    }

    List<RawDataSet> mi() {
        List<RawDataSet> arrayList = new ArrayList(this.agl.size());
        for (DataSet rawDataSet : this.agl) {
            arrayList.add(new RawDataSet(rawDataSet, this.agv, this.aiA));
        }
        return arrayList;
    }

    List<DataType> mj() {
        return this.aiA;
    }

    public String toString() {
        return kl.j(this).a("status", this.EU).a("dataSets", this.agl.size() > 5 ? this.agl.size() + " data sets" : this.agl).a("buckets", this.aiy.size() > 5 ? this.aiy.size() + " buckets" : this.aiy).toString();
    }

    public void writeToParcel(Parcel dest, int flags) {
        b.a(this, dest, flags);
    }
}
