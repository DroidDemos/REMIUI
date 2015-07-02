package com.google.android.gms.appdatasearch;

import android.os.Bundle;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class PhraseAffinityCorpusSpec implements SafeParcelable {
    public static final t CREATOR;
    final Bundle DE;
    public final CorpusId corpus;
    final int mVersionCode;

    static {
        CREATOR = new t();
    }

    PhraseAffinityCorpusSpec(int versionCode, CorpusId corpus, Bundle sectionWeights) {
        this.mVersionCode = versionCode;
        this.corpus = corpus;
        this.DE = sectionWeights;
    }

    public int describeContents() {
        t tVar = CREATOR;
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        t tVar = CREATOR;
        t.a(this, out, flags);
    }
}
