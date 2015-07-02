package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.mz;
import com.google.android.wallet.instrumentmanager.R;

public class DataSource implements SafeParcelable {
    public static final Creator<DataSource> CREATOR;
    private final int EB;
    private final DataType afZ;
    private final String agA;
    private final Device agw;
    private final Application agx;
    private final String agy;
    private final boolean agz;
    private final String mName;
    private final int mVersionCode;

    static {
        CREATOR = new f();
    }

    DataSource(int versionCode, DataType dataType, String name, int type, Device device, Application application, String streamName, boolean isObfuscated) {
        this.mVersionCode = versionCode;
        this.afZ = dataType;
        this.EB = type;
        this.mName = name;
        this.agw = device;
        this.agx = application;
        this.agy = streamName;
        this.agz = isObfuscated;
        this.agA = lu();
    }

    private boolean a(DataSource dataSource) {
        return this.agA.equals(dataSource.agA);
    }

    private String getTypeString() {
        switch (this.EB) {
            case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                return "raw";
            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                return "derived";
            default:
                throw new IllegalArgumentException("invalid type value");
        }
    }

    private String lu() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getTypeString());
        stringBuilder.append(":").append(this.afZ.getName());
        if (this.agx != null) {
            stringBuilder.append(":").append(this.agx.getPackageName());
        }
        if (this.agw != null) {
            stringBuilder.append(":").append(this.agw.getStreamIdentifier());
        }
        if (this.agy != null) {
            stringBuilder.append(":").append(this.agy);
        }
        return stringBuilder.toString();
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object that) {
        return this == that || ((that instanceof DataSource) && a((DataSource) that));
    }

    public String getAppPackageName() {
        return this.agx == null ? null : this.agx.getPackageName();
    }

    public Application getApplication() {
        return this.agx;
    }

    public DataType getDataType() {
        return this.afZ;
    }

    public Device getDevice() {
        return this.agw;
    }

    public String getName() {
        return this.mName;
    }

    public String getStreamName() {
        return this.agy;
    }

    public int getType() {
        return this.EB;
    }

    int getVersionCode() {
        return this.mVersionCode;
    }

    public int hashCode() {
        return this.agA.hashCode();
    }

    public boolean lv() {
        return this.agz;
    }

    public DataSource lw() {
        return new DataSource(3, this.afZ, this.mName, this.EB, this.agw == null ? null : this.agw.lz(), this.agx == null ? null : this.agx.lm(), mz.bP(this.agy), this.agz);
    }

    public String toDebugString() {
        StringBuilder append = new StringBuilder().append(this.EB == 0 ? "r" : "d").append(":").append(this.afZ.toShortName());
        String str = this.agx == null ? "" : this.agx.equals(Application.GOOGLE_PLAY_SERVICES) ? ":gms" : ":" + this.agx.getPackageName();
        return append.append(str).append(this.agw != null ? ":" + this.agw.getModel() + ":" + this.agw.getUid() : "").append(this.agy != null ? ":" + this.agy : "").toString();
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("DataSource{");
        stringBuilder.append(getTypeString());
        if (this.mName != null) {
            stringBuilder.append(":").append(this.mName);
        }
        if (this.agx != null) {
            stringBuilder.append(":").append(this.agx);
        }
        if (this.agw != null) {
            stringBuilder.append(":").append(this.agw);
        }
        if (this.agy != null) {
            stringBuilder.append(":").append(this.agy);
        }
        stringBuilder.append(":").append(this.afZ);
        return stringBuilder.append("}").toString();
    }

    public void writeToParcel(Parcel parcel, int flags) {
        f.a(mz.b(this), parcel, flags);
    }
}
