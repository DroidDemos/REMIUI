package com.google.android.gms.appdatasearch;

import android.os.Bundle;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class DocumentResults implements SafeParcelable {
    public static final f CREATOR;
    final Bundle CV;
    final Bundle CX;
    final Bundle CY;
    final String mErrorMessage;
    final int mVersionCode;

    static {
        CREATOR = new f();
    }

    DocumentResults(int versionCode, String errorMessage, Bundle foundUris, Bundle tagUriSet, Bundle sectionContent) {
        this.mVersionCode = versionCode;
        this.mErrorMessage = errorMessage;
        this.CV = foundUris;
        this.CX = tagUriSet;
        this.CY = sectionContent;
    }

    public int describeContents() {
        f fVar = CREATOR;
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        f fVar = CREATOR;
        f.a(this, out, flags);
    }
}
