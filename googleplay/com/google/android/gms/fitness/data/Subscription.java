package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.kl;

public class Subscription implements SafeParcelable {
    public static final Creator<Subscription> CREATOR;
    private final DataType afZ;
    private final long agX;
    private final int agY;
    private final DataSource aga;
    private final int mVersionCode;

    static {
        CREATOR = new r();
    }

    Subscription(int versionCode, DataSource dataSource, DataType dataType, long samplingIntervalMicros, int accuracyMode) {
        this.mVersionCode = versionCode;
        this.aga = dataSource;
        this.afZ = dataType;
        this.agX = samplingIntervalMicros;
        this.agY = accuracyMode;
    }

    private boolean a(Subscription subscription) {
        return kl.equal(this.aga, subscription.aga) && kl.equal(this.afZ, subscription.afZ) && this.agX == subscription.agX && this.agY == subscription.agY;
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object that) {
        return this == that || ((that instanceof Subscription) && a((Subscription) that));
    }

    public int getAccuracyMode() {
        return this.agY;
    }

    public DataSource getDataSource() {
        return this.aga;
    }

    public DataType getDataType() {
        return this.afZ;
    }

    public long getSamplingRateMicros() {
        return this.agX;
    }

    int getVersionCode() {
        return this.mVersionCode;
    }

    public int hashCode() {
        return kl.hashCode(this.aga, this.aga, Long.valueOf(this.agX), Integer.valueOf(this.agY));
    }

    public String toString() {
        return kl.j(this).a("dataSource", this.aga).a("dataType", this.afZ).a("samplingIntervalMicros", Long.valueOf(this.agX)).a("accuracyMode", Integer.valueOf(this.agY)).toString();
    }

    public void writeToParcel(Parcel dest, int flags) {
        r.a(this, dest, flags);
    }
}
