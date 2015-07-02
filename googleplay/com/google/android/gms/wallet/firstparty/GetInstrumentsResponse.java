package com.google.android.gms.wallet.firstparty;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class GetInstrumentsResponse implements SafeParcelable {
    public static final Creator<GetInstrumentsResponse> CREATOR;
    String[] aUC;
    byte[][] aUD;
    private final int mVersionCode;

    static {
        CREATOR = new b();
    }

    GetInstrumentsResponse() {
        this(1, new String[0], new byte[0][]);
    }

    GetInstrumentsResponse(int versionCode, String[] instrumentIds, byte[][] paymentInstruments) {
        this.mVersionCode = versionCode;
        this.aUC = instrumentIds;
        this.aUD = paymentInstruments;
    }

    public int describeContents() {
        return 0;
    }

    public int getVersionCode() {
        return this.mVersionCode;
    }

    public void writeToParcel(Parcel out, int flags) {
        b.a(this, out, flags);
    }
}
