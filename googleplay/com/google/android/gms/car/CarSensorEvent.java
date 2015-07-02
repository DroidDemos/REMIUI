package com.google.android.gms.car;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class CarSensorEvent implements SafeParcelable {
    public static final Creator<CarSensorEvent> CREATOR;
    public final byte[] byteValues;
    public final float[] floatValues;
    final int mVersionCode;
    public int sensorType;
    public long timeStampNs;

    static {
        CREATOR = new t();
    }

    public CarSensorEvent(int versionCode, int sensorType, long timeStampNs, float[] floatValues, byte[] byteValues) {
        this.mVersionCode = versionCode;
        this.sensorType = sensorType;
        this.timeStampNs = timeStampNs;
        this.floatValues = floatValues;
        this.byteValues = byteValues;
    }

    public int describeContents() {
        return 0;
    }

    public int getVersionCode() {
        return this.mVersionCode;
    }

    public String toString() {
        int i = 0;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getClass().getName() + "[");
        stringBuilder.append("type:" + Integer.toHexString(this.sensorType));
        if (this.floatValues != null && this.floatValues.length > 0) {
            stringBuilder.append(" float values:");
            for (float f : this.floatValues) {
                stringBuilder.append(" " + f);
            }
        }
        if (this.byteValues != null && this.byteValues.length > 0) {
            stringBuilder.append(" byte values:");
            byte[] bArr = this.byteValues;
            int length = bArr.length;
            while (i < length) {
                stringBuilder.append(" " + bArr[i]);
                i++;
            }
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    public void writeToParcel(Parcel dest, int flags) {
        t.a(this, dest, flags);
    }
}
