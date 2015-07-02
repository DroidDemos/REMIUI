package com.google.android.gms.audiomodem;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.kl;

public class DsssEncoding implements SafeParcelable {
    public static final Creator<DsssEncoding> CREATOR;
    private final int FA;
    private final int FB;
    private final float FC;
    private final int FD;
    private final float FE;
    private final int FF;
    private final int FG;
    private final int FH;
    private final int FI;
    private final int Fx;
    private final boolean Fy;
    private final boolean Fz;
    private final int mVersionCode;

    static {
        CREATOR = new b();
    }

    DsssEncoding(int versionCode, int tokenLengthBytes, boolean shouldIncludeParitySymbol, boolean shouldUseSingleSideband, int numberOfTapsLfsr, int codeNumber, float coderSampleRate, int upsamplingFactor, float desiredCarrierFrequency, int bitsPerSymbol, int minCyclesPerFrame, int basebandDecimationFactor, int numCrcCheckBytes) {
        this.mVersionCode = versionCode;
        this.Fx = tokenLengthBytes;
        this.Fy = shouldIncludeParitySymbol;
        this.Fz = shouldUseSingleSideband;
        this.FA = numberOfTapsLfsr;
        this.FB = codeNumber;
        this.FC = coderSampleRate;
        this.FD = upsamplingFactor;
        this.FE = desiredCarrierFrequency;
        this.FF = bitsPerSymbol;
        this.FG = minCyclesPerFrame;
        this.FH = basebandDecimationFactor;
        this.FI = numCrcCheckBytes;
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof DsssEncoding)) {
            return false;
        }
        DsssEncoding dsssEncoding = (DsssEncoding) obj;
        return this.mVersionCode == dsssEncoding.getVersionCode() && this.Fx == dsssEncoding.getTokenLengthBytes() && this.Fy == dsssEncoding.shouldIncludeParitySymbol() && this.Fz == dsssEncoding.shouldUseSingleSideband() && this.FA == dsssEncoding.getNumberOfTapsLfsr() && this.FB == dsssEncoding.getCodeNumber() && this.FC == dsssEncoding.getCoderSampleRate() && this.FD == dsssEncoding.getUpsamplingFactor() && this.FE == dsssEncoding.getDesiredCarrierFrequency() && this.FF == dsssEncoding.getBitsPerSymbol() && this.FG == dsssEncoding.getMinCyclesPerFrame() && this.FH == dsssEncoding.getBasebandDecimationFactor() && this.FI == dsssEncoding.getNumCrcCheckBytes();
    }

    public int getBasebandDecimationFactor() {
        return this.FH;
    }

    public int getBitsPerSymbol() {
        return this.FF;
    }

    public int getCodeNumber() {
        return this.FB;
    }

    public float getCoderSampleRate() {
        return this.FC;
    }

    public float getDesiredCarrierFrequency() {
        return this.FE;
    }

    public int getMinCyclesPerFrame() {
        return this.FG;
    }

    public int getNumCrcCheckBytes() {
        return this.FI;
    }

    public int getNumberOfTapsLfsr() {
        return this.FA;
    }

    public int getTokenLengthBytes() {
        return this.Fx;
    }

    public int getUpsamplingFactor() {
        return this.FD;
    }

    int getVersionCode() {
        return this.mVersionCode;
    }

    public int hashCode() {
        return kl.hashCode(Integer.valueOf(this.mVersionCode), Integer.valueOf(this.Fx), Boolean.valueOf(this.Fy), Boolean.valueOf(this.Fz), Integer.valueOf(this.FA), Integer.valueOf(this.FB), Float.valueOf(this.FC), Integer.valueOf(this.FD), Float.valueOf(this.FE), Integer.valueOf(this.FF), Integer.valueOf(this.FG), Integer.valueOf(this.FH), Integer.valueOf(this.FI));
    }

    public boolean shouldIncludeParitySymbol() {
        return this.Fy;
    }

    public boolean shouldUseSingleSideband() {
        return this.Fz;
    }

    public void writeToParcel(Parcel dest, int flags) {
        b.a(this, dest, flags);
    }
}
