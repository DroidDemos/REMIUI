package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.kl;
import com.google.android.wallet.instrumentmanager.R;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Bucket implements SafeParcelable {
    public static final Creator<Bucket> CREATOR;
    private final long Va;
    private final long agb;
    private final Session agd;
    private final int agk;
    private final List<DataSet> agl;
    private final int agm;
    private boolean agn;
    private final int mVersionCode;

    static {
        CREATOR = new c();
    }

    Bucket(int versionCode, long startTimeMillis, long endTimeMillis, Session session, int activityType, List<DataSet> dataSets, int bucketType, boolean serverHasMoreData) {
        this.agn = false;
        this.mVersionCode = versionCode;
        this.Va = startTimeMillis;
        this.agb = endTimeMillis;
        this.agd = session;
        this.agk = activityType;
        this.agl = dataSets;
        this.agm = bucketType;
        this.agn = serverHasMoreData;
    }

    public Bucket(RawBucket bucket, List<DataSource> uniqueDataSources, List<DataType> uniqueDataTypes) {
        this(2, bucket.Va, bucket.agb, bucket.agd, bucket.agQ, a(bucket.agl, uniqueDataSources, uniqueDataTypes), bucket.agm, bucket.agn);
    }

    private static List<DataSet> a(List<RawDataSet> list, List<DataSource> list2, List<DataType> list3) {
        List<DataSet> arrayList = new ArrayList(list.size());
        for (RawDataSet dataSet : list) {
            arrayList.add(new DataSet(dataSet, list2, list3));
        }
        return arrayList;
    }

    private boolean a(Bucket bucket) {
        return this.Va == bucket.Va && this.agb == bucket.agb && this.agk == bucket.agk && kl.equal(this.agl, bucket.agl) && this.agm == bucket.agm && this.agn == bucket.agn;
    }

    public static String getBucketString(int bucketType) {
        switch (bucketType) {
            case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                return "unknown";
            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                return "time";
            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                return "session";
            case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                return "type";
            case R.styleable.WalletImFormEditText_required /*4*/:
                return "segment";
            default:
                return "bug";
        }
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object o) {
        return o == this || ((o instanceof Bucket) && a((Bucket) o));
    }

    public int getBucketType() {
        return this.agm;
    }

    public List<DataSet> getDataSets() {
        return this.agl;
    }

    public long getEndTime(TimeUnit timeUnit) {
        return timeUnit.convert(this.agb, TimeUnit.MILLISECONDS);
    }

    public Session getSession() {
        return this.agd;
    }

    public long getStartTime(TimeUnit timeUnit) {
        return timeUnit.convert(this.Va, TimeUnit.MILLISECONDS);
    }

    int getVersionCode() {
        return this.mVersionCode;
    }

    public int hashCode() {
        return kl.hashCode(Long.valueOf(this.Va), Long.valueOf(this.agb), Integer.valueOf(this.agk), Integer.valueOf(this.agm));
    }

    public int ln() {
        return this.agk;
    }

    public long lo() {
        return this.Va;
    }

    public long lp() {
        return this.agb;
    }

    public boolean serverHasMoreData() {
        if (this.agn) {
            return true;
        }
        for (DataSet serverHasMoreData : this.agl) {
            if (serverHasMoreData.serverHasMoreData()) {
                return true;
            }
        }
        return false;
    }

    public String toString() {
        return kl.j(this).a("startTime", Long.valueOf(this.Va)).a("endTime", Long.valueOf(this.agb)).a("activity", Integer.valueOf(this.agk)).a("dataSets", this.agl).a("bucketType", getBucketString(this.agm)).a("serverHasMoreData", Boolean.valueOf(this.agn)).toString();
    }

    public void writeToParcel(Parcel dest, int flags) {
        c.a(this, dest, flags);
    }
}
