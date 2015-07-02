package com.google.android.gms.googlehelp;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class OfflineSuggestion implements SafeParcelable {
    public static final Creator<OfflineSuggestion> CREATOR;
    final String CB;
    final String Yv;
    final String ase;
    final int mVersionCode;
    final String pE;

    static {
        CREATOR = new b();
    }

    OfflineSuggestion(int versionCode, String id, String title, String body, String browseUrl) {
        this.mVersionCode = versionCode;
        this.CB = id;
        this.Yv = title;
        this.pE = body;
        this.ase = browseUrl;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        b.a(this, out, flags);
    }
}
