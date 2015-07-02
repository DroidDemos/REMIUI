package com.google.android.gms.appdatasearch;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.kl;

public class CorpusId implements SafeParcelable {
    public static final a CREATOR;
    public final String corpusName;
    final int mVersionCode;
    public final String packageName;

    static {
        CREATOR = new a();
    }

    CorpusId(int versionCode, String packageName, String corpusName) {
        this.mVersionCode = versionCode;
        this.packageName = packageName;
        this.corpusName = corpusName;
    }

    public int describeContents() {
        a aVar = CREATOR;
        return 0;
    }

    public boolean equals(Object object) {
        if (!(object instanceof CorpusId)) {
            return false;
        }
        CorpusId corpusId = (CorpusId) object;
        return kl.equal(this.packageName, corpusId.packageName) && kl.equal(this.corpusName, corpusId.corpusName);
    }

    public int hashCode() {
        return kl.hashCode(this.packageName, this.corpusName);
    }

    public String toString() {
        return "CorpusId[package=" + this.packageName + ", corpus=" + this.corpusName + "]";
    }

    public void writeToParcel(Parcel out, int flags) {
        a aVar = CREATOR;
        a.a(this, out, flags);
    }
}
