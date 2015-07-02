package com.google.android.gms.appdatasearch;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class PhraseAffinityResponse implements SafeParcelable {
    public static final u CREATOR;
    final CorpusId[] DG;
    final int[] DH;
    final String mErrorMessage;
    final int mVersionCode;

    static {
        CREATOR = new u();
    }

    PhraseAffinityResponse(int versionCode, String errorMessage, CorpusId[] corpusIds, int[] affinityScoresPerPackageName) {
        this.mVersionCode = versionCode;
        this.mErrorMessage = errorMessage;
        this.DG = corpusIds;
        this.DH = affinityScoresPerPackageName;
    }

    public int describeContents() {
        u uVar = CREATOR;
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        u uVar = CREATOR;
        u.a(this, out, flags);
    }
}
