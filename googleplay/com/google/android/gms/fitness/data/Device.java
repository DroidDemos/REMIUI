package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.kl;
import com.google.android.gms.internal.kn;
import com.google.android.gms.internal.mz;

public final class Device implements SafeParcelable {
    public static final Creator<Device> CREATOR;
    private final String CN;
    private final int EB;
    private final String agE;
    private final String agF;
    private final String agG;
    private final int agH;
    private final int mVersionCode;

    static {
        CREATOR = new h();
    }

    Device(int versionCode, String manufacturer, String model, String version, String uid, int type, int platformType) {
        this.mVersionCode = versionCode;
        this.agE = (String) kn.k(manufacturer);
        this.agF = (String) kn.k(model);
        this.CN = "";
        this.agG = (String) kn.k(uid);
        this.EB = type;
        this.agH = platformType;
    }

    public Device(String manufacturer, String model, String uid, int type) {
        this(manufacturer, model, "", uid, type, 0);
    }

    public Device(String manufacturer, String model, String version, String uid, int type) {
        this(manufacturer, model, uid, type);
    }

    public Device(String manufacturer, String model, String version, String uid, int type, int platformType) {
        this(1, manufacturer, model, "", uid, type, platformType);
    }

    private boolean a(Device device) {
        return kl.equal(this.agE, device.agE) && kl.equal(this.agF, device.agF) && kl.equal(this.CN, device.CN) && kl.equal(this.agG, device.agG) && this.EB == device.EB && this.agH == device.agH;
    }

    private boolean ly() {
        return lx() == 1;
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object that) {
        return this == that || ((that instanceof Device) && a((Device) that));
    }

    public String getManufacturer() {
        return this.agE;
    }

    public String getModel() {
        return this.agF;
    }

    String getStreamIdentifier() {
        return String.format("%s:%s:%s", new Object[]{this.agE, this.agF, this.agG});
    }

    public int getType() {
        return this.EB;
    }

    public String getUid() {
        return this.agG;
    }

    public String getVersion() {
        return this.CN;
    }

    int getVersionCode() {
        return this.mVersionCode;
    }

    public int hashCode() {
        return kl.hashCode(this.agE, this.agF, this.CN, this.agG, Integer.valueOf(this.EB));
    }

    public String lA() {
        return (mz.lG() || ly()) ? this.agG : mz.bP(this.agG);
    }

    public int lx() {
        return this.agH;
    }

    Device lz() {
        return new Device(mz.bP(this.agE), mz.bP(this.agF), mz.bP(this.CN), this.agG, this.EB);
    }

    public String toString() {
        return String.format("Device{%s:%s:%s:%s}", new Object[]{getStreamIdentifier(), this.CN, Integer.valueOf(this.EB), Integer.valueOf(this.agH)});
    }

    public void writeToParcel(Parcel parcel, int flags) {
        h.a(this, parcel, flags);
    }
}
