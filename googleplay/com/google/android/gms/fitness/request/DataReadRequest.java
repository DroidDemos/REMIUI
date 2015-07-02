package com.google.android.gms.fitness.request;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.fitness.data.Bucket;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.internal.kl;
import java.util.Collections;
import java.util.List;

public class DataReadRequest implements SafeParcelable {
    public static final Creator<DataReadRequest> CREATOR;
    private final long Va;
    private final long agb;
    private final List<DataType> agj;
    private final int agm;
    private final List<DataSource> ahO;
    private final List<DataType> ahS;
    private final List<DataSource> ahT;
    private final long ahU;
    private final DataSource ahV;
    private final int ahW;
    private final boolean ahX;
    private final boolean ahY;
    private final int mVersionCode;

    static {
        CREATOR = new g();
    }

    DataReadRequest(int versionCode, List<DataType> dataTypes, List<DataSource> dataSources, long startTimeMillis, long endTimeMillis, List<DataType> aggregatedDataTypes, List<DataSource> aggregatedDataSources, int bucketType, long bucketDurationMillis, DataSource activityDataSource, int limit, boolean flushBeforeRead, boolean serverQueriesEnabled) {
        this.mVersionCode = versionCode;
        this.agj = Collections.unmodifiableList(dataTypes);
        this.ahO = Collections.unmodifiableList(dataSources);
        this.Va = startTimeMillis;
        this.agb = endTimeMillis;
        this.ahS = Collections.unmodifiableList(aggregatedDataTypes);
        this.ahT = Collections.unmodifiableList(aggregatedDataSources);
        this.agm = bucketType;
        this.ahU = bucketDurationMillis;
        this.ahV = activityDataSource;
        this.ahW = limit;
        this.ahX = flushBeforeRead;
        this.ahY = serverQueriesEnabled;
    }

    private boolean a(DataReadRequest dataReadRequest) {
        return this.agj.equals(dataReadRequest.agj) && this.ahO.equals(dataReadRequest.ahO) && this.Va == dataReadRequest.Va && this.agb == dataReadRequest.agb && this.agm == dataReadRequest.agm && this.ahT.equals(dataReadRequest.ahT) && this.ahS.equals(dataReadRequest.ahS) && kl.equal(this.ahV, dataReadRequest.ahV) && this.ahU == dataReadRequest.ahU && this.ahY == dataReadRequest.ahY;
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object that) {
        return this == that || ((that instanceof DataReadRequest) && a((DataReadRequest) that));
    }

    public DataSource getActivityDataSource() {
        return this.ahV;
    }

    public List<DataSource> getAggregatedDataSources() {
        return this.ahT;
    }

    public List<DataType> getAggregatedDataTypes() {
        return this.ahS;
    }

    public int getBucketType() {
        return this.agm;
    }

    public List<DataSource> getDataSources() {
        return this.ahO;
    }

    public List<DataType> getDataTypes() {
        return this.agj;
    }

    public int getLimit() {
        return this.ahW;
    }

    int getVersionCode() {
        return this.mVersionCode;
    }

    public int hashCode() {
        return kl.hashCode(Integer.valueOf(this.agm), Long.valueOf(this.Va), Long.valueOf(this.agb));
    }

    public boolean lO() {
        return this.ahY;
    }

    public boolean lP() {
        return this.ahX;
    }

    public long lQ() {
        return this.ahU;
    }

    public long lo() {
        return this.Va;
    }

    public long lp() {
        return this.agb;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("DataReadRequest{");
        if (!this.agj.isEmpty()) {
            for (DataType toShortName : this.agj) {
                stringBuilder.append(toShortName.toShortName()).append(" ");
            }
        }
        if (!this.ahO.isEmpty()) {
            for (DataSource toDebugString : this.ahO) {
                stringBuilder.append(toDebugString.toDebugString()).append(" ");
            }
        }
        if (this.agm != 0) {
            stringBuilder.append("bucket by ").append(Bucket.getBucketString(this.agm));
            if (this.ahU > 0) {
                stringBuilder.append(" >").append(this.ahU).append("ms");
            }
            stringBuilder.append(": ");
        }
        if (!this.ahS.isEmpty()) {
            for (DataType toShortName2 : this.ahS) {
                stringBuilder.append(toShortName2.toShortName()).append(" ");
            }
        }
        if (!this.ahT.isEmpty()) {
            for (DataSource toDebugString2 : this.ahT) {
                stringBuilder.append(toDebugString2.toDebugString()).append(" ");
            }
        }
        stringBuilder.append(String.format("(%tF %tT - %tF %tT)", new Object[]{Long.valueOf(this.Va), Long.valueOf(this.Va), Long.valueOf(this.agb), Long.valueOf(this.agb)}));
        if (this.ahV != null) {
            stringBuilder.append("activities: ").append(this.ahV.toDebugString());
        }
        if (this.ahY) {
            stringBuilder.append(" +server");
        }
        stringBuilder.append("}");
        return stringBuilder.toString();
    }

    public void writeToParcel(Parcel dest, int flags) {
        g.a(this, dest, flags);
    }
}
