package com.google.android.gms.audiomodem;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.kl;

public class DtmfEncoding implements SafeParcelable {
    public static final Creator<DtmfEncoding> CREATOR;
    private final float FC;
    private final int FH;
    private final int FI;
    private final int FJ;
    private final int FK;
    private final int Fx;
    private final boolean Fy;
    private final int mVersionCode;

    static {
        CREATOR = new c();
    }

    DtmfEncoding(int versionCode, int tokenLengthBytes, boolean shouldIncludeParitySymbol, float coderSampleRate, int basebandDecimationFactor, int windowDurationMillis, int frequenciesPerSymbol, int numCrcCheckBytes) {
        this.mVersionCode = versionCode;
        this.Fx = tokenLengthBytes;
        this.Fy = shouldIncludeParitySymbol;
        this.FC = coderSampleRate;
        this.FH = basebandDecimationFactor;
        this.FJ = windowDurationMillis;
        this.FK = frequenciesPerSymbol;
        this.FI = numCrcCheckBytes;
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof DtmfEncoding)) {
            return false;
        }
        DtmfEncoding dtmfEncoding = (DtmfEncoding) obj;
        return this.mVersionCode == dtmfEncoding.getVersionCode() && this.Fx == dtmfEncoding.getTokenLengthBytes() && this.Fy == dtmfEncoding.shouldIncludeParitySymbol() && this.FC == dtmfEncoding.getCoderSampleRate() && this.FH == dtmfEncoding.getBasebandDecimationFactor() && this.FJ == dtmfEncoding.getWindowDurationMillis() && this.FK == dtmfEncoding.getFrequenciesPerSymbol() && this.FI == dtmfEncoding.getNumCrcCheckBytes();
    }

    public int getBasebandDecimationFactor() {
        return this.FH;
    }

    public float getCoderSampleRate() {
        return this.FC;
    }

    public int getFrequenciesPerSymbol() {
        return this.FK;
    }

    public int getNumCrcCheckBytes() {
        return this.FI;
    }

    public int getTokenLengthBytes() {
        return this.Fx;
    }

    int getVersionCode() {
        return this.mVersionCode;
    }

    public int getWindowDurationMillis() {
        return this.FJ;
    }

    public int hashCode() {
        return kl.hashCode(Integer.valueOf(this.mVersionCode), Integer.valueOf(this.Fx), Boolean.valueOf(this.Fy), Float.valueOf(this.FC), Integer.valueOf(this.FH), Integer.valueOf(this.FJ), Integer.valueOf(this.FK), Integer.valueOf(this.FI));
    }

    public boolean shouldIncludeParitySymbol() {
        return this.Fy;
    }

    public void writeToParcel(Parcel dest, int flags) {
        c.a(this, dest, flags);
    }
}
