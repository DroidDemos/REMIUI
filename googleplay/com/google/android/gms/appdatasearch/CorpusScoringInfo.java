package com.google.android.gms.appdatasearch;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class CorpusScoringInfo implements SafeParcelable {
    public static final b CREATOR;
    public final CorpusId corpus;
    final int mVersionCode;
    public final int weight;

    static {
        CREATOR = new b();
    }

    CorpusScoringInfo(int versionCode, CorpusId corpus, int weight) {
        this.mVersionCode = versionCode;
        this.corpus = corpus;
        this.weight = weight;
    }

    public int describeContents() {
        b bVar = CREATOR;
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        b bVar = CREATOR;
        b.a(this, out, flags);
    }
}
