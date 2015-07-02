package com.google.android.gms.wallet.shared;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class LegalDocsForCountry implements SafeParcelable {
    public static final Creator<LegalDocsForCountry> CREATOR;
    final String aVH;
    final String aVI;
    final String[] aVJ;
    final int mVersionCode;

    static {
        CREATOR = new d();
    }

    LegalDocsForCountry(int versionCode, String countryCode, String legalDocumentUrl, String[] legalDocumentIds) {
        this.mVersionCode = versionCode;
        this.aVH = countryCode;
        this.aVI = legalDocumentUrl;
        if (legalDocumentIds == null) {
            legalDocumentIds = new String[0];
        }
        this.aVJ = legalDocumentIds;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        d.a(this, out, flags);
    }
}
