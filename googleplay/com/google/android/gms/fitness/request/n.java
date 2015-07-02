package com.google.android.gms.fitness.request;

import android.app.PendingIntent;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.data.j;
import com.google.android.gms.fitness.data.j.a;
import com.google.android.gms.internal.kl;
import com.google.android.gms.location.LocationRequest;
import java.util.Collections;
import java.util.List;

public class n implements SafeParcelable {
    public static final Creator<n> CREATOR;
    private final DataType afZ;
    private final long agX;
    private final int agY;
    private final DataSource aga;
    private j aid;
    int aie;
    int aif;
    private final long aig;
    private final long aih;
    private final List<LocationRequest> aii;
    private final long aij;
    private final List aik;
    private final PendingIntent mPendingIntent;
    private final int mVersionCode;

    static {
        CREATOR = new o();
    }

    n(int i, DataSource dataSource, DataType dataType, IBinder iBinder, int i2, int i3, long j, long j2, PendingIntent pendingIntent, long j3, int i4, List<LocationRequest> list, long j4) {
        this.mVersionCode = i;
        this.aga = dataSource;
        this.afZ = dataType;
        this.aid = iBinder == null ? null : a.bK(iBinder);
        if (j == 0) {
            j = (long) i2;
        }
        this.agX = j;
        this.aih = j3;
        if (j2 == 0) {
            j2 = (long) i3;
        }
        this.aig = j2;
        this.aii = list;
        this.mPendingIntent = pendingIntent;
        this.agY = i4;
        this.aik = Collections.emptyList();
        this.aij = j4;
    }

    private boolean a(n nVar) {
        return kl.equal(this.aga, nVar.aga) && kl.equal(this.afZ, nVar.afZ) && this.agX == nVar.agX && this.aih == nVar.aih && this.aig == nVar.aig && this.agY == nVar.agY && kl.equal(this.aii, nVar.aii);
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object that) {
        return this == that || ((that instanceof n) && a((n) that));
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
        return kl.hashCode(this.aga, this.afZ, this.aid, Long.valueOf(this.agX), Long.valueOf(this.aih), Long.valueOf(this.aig), Integer.valueOf(this.agY), this.aii);
    }

    public PendingIntent lU() {
        return this.mPendingIntent;
    }

    public long lV() {
        return this.aih;
    }

    public long lW() {
        return this.aig;
    }

    public List<LocationRequest> lX() {
        return this.aii;
    }

    public long lY() {
        return this.aij;
    }

    IBinder lZ() {
        return this.aid == null ? null : this.aid.asBinder();
    }

    public String toString() {
        return String.format("SensorRegistrationRequest{type %s source %s interval %s fastest %s latency %s}", new Object[]{this.afZ, this.aga, Long.valueOf(this.agX), Long.valueOf(this.aih), Long.valueOf(this.aig)});
    }

    public void writeToParcel(Parcel parcel, int flags) {
        o.a(this, parcel, flags);
    }
}
