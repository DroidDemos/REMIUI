package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.kl;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class DataSet implements SafeParcelable {
    public static final Creator<DataSet> CREATOR;
    private final DataType afZ;
    private final DataSource aga;
    private boolean agn;
    private final List<DataPoint> agu;
    private final List<DataSource> agv;
    private final int mVersionCode;

    static {
        CREATOR = new e();
    }

    DataSet(int versionCode, DataSource dataSource, DataType dataType, List<RawDataPoint> dataPoints, List<DataSource> uniqueDataSources, boolean serverHasMoreData) {
        this.agn = false;
        this.mVersionCode = versionCode;
        this.aga = dataSource;
        this.afZ = dataType;
        this.agn = serverHasMoreData;
        this.agu = new ArrayList(dataPoints.size());
        if (versionCode < 2) {
            uniqueDataSources = Collections.singletonList(dataSource);
        }
        this.agv = uniqueDataSources;
        for (RawDataPoint dataPoint : dataPoints) {
            this.agu.add(new DataPoint(this.agv, dataPoint));
        }
    }

    public DataSet(RawDataSet dataSet, List<DataSource> uniqueDataSources, List<DataType> uniqueDataTypes) {
        this(3, (DataSource) d(uniqueDataSources, dataSet.agR), (DataType) d(uniqueDataTypes, dataSet.agT), dataSet.agU, uniqueDataSources, dataSet.agn);
    }

    private boolean a(DataSet dataSet) {
        return kl.equal(this.afZ, dataSet.afZ) && kl.equal(this.aga, dataSet.aga) && kl.equal(this.agu, dataSet.agu) && this.agn == dataSet.agn;
    }

    private static <T> T d(List<T> list, int i) {
        return (i < 0 || i >= list.size()) ? null : list.get(i);
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object o) {
        return o == this || ((o instanceof DataSet) && a((DataSet) o));
    }

    public DataSource getDataSource() {
        return this.aga;
    }

    public DataType getDataType() {
        return this.afZ;
    }

    int getVersionCode() {
        return this.mVersionCode;
    }

    public int hashCode() {
        return kl.hashCode(this.afZ, this.aga);
    }

    List<RawDataPoint> i(List<DataSource> list) {
        List<RawDataPoint> arrayList = new ArrayList(this.agu.size());
        for (DataPoint rawDataPoint : this.agu) {
            arrayList.add(new RawDataPoint(rawDataPoint, list));
        }
        return arrayList;
    }

    List<RawDataPoint> ls() {
        return i(this.agv);
    }

    List<DataSource> lt() {
        return this.agv;
    }

    public boolean serverHasMoreData() {
        return this.agn;
    }

    public String toString() {
        List ls = ls();
        String str = "DataSet{%s %s}";
        Object[] objArr = new Object[2];
        objArr[0] = this.aga.toDebugString();
        if (this.agu.size() >= 10) {
            ls = String.format("%d data points, first 5: %s", new Object[]{Integer.valueOf(this.agu.size()), ls.subList(0, 5)});
        }
        objArr[1] = ls;
        return String.format(str, objArr);
    }

    public void writeToParcel(Parcel parcel, int flags) {
        e.a(this, parcel, flags);
    }
}
