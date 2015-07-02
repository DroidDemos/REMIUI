package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.kl;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public final class DataPoint implements SafeParcelable {
    public static final Creator<DataPoint> CREATOR;
    private final DataSource aga;
    private long ago;
    private long agp;
    private final Value[] agq;
    private DataSource agr;
    private long ags;
    private long agt;
    private final int mVersionCode;

    static {
        CREATOR = new d();
    }

    DataPoint(int versionCode, DataSource dataSource, long timestampNanos, long startTimeNanos, Value[] values, DataSource originalDataSource, long rawTimestamp, long insertionTimeMillis) {
        this.mVersionCode = versionCode;
        this.aga = dataSource;
        this.agr = originalDataSource;
        this.ago = timestampNanos;
        this.agp = startTimeNanos;
        this.agq = values;
        this.ags = rawTimestamp;
        this.agt = insertionTimeMillis;
    }

    DataPoint(List<DataSource> dataSources, RawDataPoint rawDataPoint) {
        this(4, c(dataSources, rawDataPoint.agR), rawDataPoint.ago, rawDataPoint.agp, rawDataPoint.agq, c(dataSources, rawDataPoint.agS), rawDataPoint.ags, rawDataPoint.agt);
    }

    private boolean a(DataPoint dataPoint) {
        return kl.equal(this.aga, dataPoint.aga) && this.ago == dataPoint.ago && this.agp == dataPoint.agp && Arrays.equals(this.agq, dataPoint.agq) && kl.equal(this.agr, dataPoint.agr);
    }

    private static DataSource c(List<DataSource> list, int i) {
        return (i < 0 || i >= list.size()) ? null : (DataSource) list.get(i);
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object o) {
        return this == o || ((o instanceof DataPoint) && a((DataPoint) o));
    }

    public DataSource getDataSource() {
        return this.aga;
    }

    public long getInsertionTimeMillis() {
        return this.agt;
    }

    public DataSource getOriginalDataSource() {
        return this.agr;
    }

    public long getRawTimestamp() {
        return this.ags;
    }

    public long getStartTime(TimeUnit timeUnit) {
        return timeUnit.convert(this.agp, TimeUnit.NANOSECONDS);
    }

    public long getTimestamp(TimeUnit timeUnit) {
        return timeUnit.convert(this.ago, TimeUnit.NANOSECONDS);
    }

    public long getTimestampNanos() {
        return this.ago;
    }

    public Value[] getValues() {
        return this.agq;
    }

    public int getVersionCode() {
        return this.mVersionCode;
    }

    public int hashCode() {
        return kl.hashCode(this.aga, Long.valueOf(this.ago), Long.valueOf(this.agp));
    }

    public long lr() {
        return this.agp;
    }

    public String toString() {
        return String.format("DataPoint{%s@[%s, %s,raw=%s,insert=%s](%s %s)}", new Object[]{Arrays.toString(this.agq), Long.valueOf(this.agp), Long.valueOf(this.ago), Long.valueOf(this.ags), Long.valueOf(this.agt), this.aga, this.agr});
    }

    public void writeToParcel(Parcel parcel, int flags) {
        d.a(this, parcel, flags);
    }
}
