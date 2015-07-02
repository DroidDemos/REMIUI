package com.google.android.gms.wallet.firstparty;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class GetInstrumentsRequest implements SafeParcelable {
    public static final Creator<GetInstrumentsRequest> CREATOR;
    int[] aUA;
    private final int mVersionCode;

    static {
        CREATOR = new a();
    }

    GetInstrumentsRequest() {
        this(1, null);
    }

    GetInstrumentsRequest(int versionCode, int[] familyFilter) {
        this.mVersionCode = versionCode;
        this.aUA = familyFilter;
    }

    public int describeContents() {
        return 0;
    }

    public int getVersionCode() {
        return this.mVersionCode;
    }

    public void writeToParcel(Parcel out, int flags) {
        a.a(this, out, flags);
    }
}
