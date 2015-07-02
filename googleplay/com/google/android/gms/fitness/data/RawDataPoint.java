package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.kl;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public final class RawDataPoint implements SafeParcelable {
    public static final Creator<RawDataPoint> CREATOR;
    final int agR;
    final int agS;
    final long ago;
    final long agp;
    final Value[] agq;
    final long ags;
    final long agt;
    final int mVersionCode;

    static {
        CREATOR = new m();
    }

    RawDataPoint(int versionCode, long timestampNanos, long startTimeNanos, Value[] values, int dataSourceIndex, int originalDataSourceIndex, long rawTimestamp, long insertionTimeMillis) {
        this.mVersionCode = versionCode;
        this.ago = timestampNanos;
        this.agp = startTimeNanos;
        this.agR = dataSourceIndex;
        this.agS = originalDataSourceIndex;
        this.ags = rawTimestamp;
        this.agt = insertionTimeMillis;
        this.agq = values;
    }

    RawDataPoint(DataPoint dataPoint, List<DataSource> dataSources) {
        this.mVersionCode = 4;
        this.ago = dataPoint.getTimestamp(TimeUnit.NANOSECONDS);
        this.agp = dataPoint.getStartTime(TimeUnit.NANOSECONDS);
        this.agq = dataPoint.getValues();
        this.agR = s.a(dataPoint.getDataSource(), dataSources);
        this.agS = s.a(dataPoint.getOriginalDataSource(), dataSources);
        this.ags = dataPoint.getRawTimestamp();
        this.agt = dataPoint.getInsertionTimeMillis();
    }

    private boolean a(RawDataPoint rawDataPoint) {
        return this.ago == rawDataPoint.ago && this.agp == rawDataPoint.agp && Arrays.equals(this.agq, rawDataPoint.agq) && this.agR == rawDataPoint.agR && this.agS == rawDataPoint.agS && this.ags == rawDataPoint.ags;
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object o) {
        return this == o || ((o instanceof RawDataPoint) && a((RawDataPoint) o));
    }

    public int hashCode() {
        return kl.hashCode(Long.valueOf(this.ago), Long.valueOf(this.agp));
    }

    public String toString() {
        return String.format("RawDataPoint{%s@[%s, %s](%d,%d)}", new Object[]{Arrays.toString(this.agq), Long.valueOf(this.agp), Long.valueOf(this.ago), Integer.valueOf(this.agR), Integer.valueOf(this.agS)});
    }

    public void writeToParcel(Parcel parcel, int flags) {
        m.a(this, parcel, flags);
    }
}
