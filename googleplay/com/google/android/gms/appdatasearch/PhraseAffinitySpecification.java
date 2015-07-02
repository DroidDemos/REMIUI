package com.google.android.gms.appdatasearch;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class PhraseAffinitySpecification implements SafeParcelable {
    public static final v CREATOR;
    final PhraseAffinityCorpusSpec[] DI;
    final int mVersionCode;

    static {
        CREATOR = new v();
    }

    PhraseAffinitySpecification(int versionCode, PhraseAffinityCorpusSpec[] corpusSpecs) {
        this.mVersionCode = versionCode;
        this.DI = corpusSpecs;
    }

    public int describeContents() {
        v vVar = CREATOR;
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        v vVar = CREATOR;
        v.a(this, out, flags);
    }
}
