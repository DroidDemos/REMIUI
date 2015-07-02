package com.google.android.gms.car;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class CarAudioConfiguration implements SafeParcelable {
    public static final Creator<CarAudioConfiguration> CREATOR;
    public int audioFormat;
    public int channelConfig;
    final int mVersionCode;
    public int sampleRate;

    static {
        CREATOR = new a();
    }

    public CarAudioConfiguration(int versionCode, int sampleRate, int channelConfig, int audioFormat) {
        this.mVersionCode = versionCode;
        this.sampleRate = sampleRate;
        this.channelConfig = channelConfig;
        this.audioFormat = audioFormat;
    }

    public int describeContents() {
        return 0;
    }

    public int getVersionCode() {
        return this.mVersionCode;
    }

    public String toString() {
        return getClass().getName() + "[" + "sampleRate=" + this.sampleRate + ",channelConfig=" + this.channelConfig + ",audioFormat=" + this.audioFormat + "]";
    }

    public void writeToParcel(Parcel dest, int flags) {
        a.a(this, dest, flags);
    }
}
