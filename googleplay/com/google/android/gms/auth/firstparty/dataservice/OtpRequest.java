package com.google.android.gms.auth.firstparty.dataservice;

import android.os.Parcel;
import com.google.android.gms.auth.firstparty.shared.AppDescription;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.kn;

public class OtpRequest implements SafeParcelable {
    public static final x CREATOR;
    final int Gf;
    public final String accountName;
    public final AppDescription callerDescription;
    public final byte[] challenge;
    public final boolean isLegacyRequest;

    static {
        CREATOR = new x();
    }

    OtpRequest(int version, String accountName, AppDescription callerDescription, byte[] challenge, boolean isLegacyRequest) {
        this.Gf = version;
        this.accountName = accountName;
        this.challenge = challenge;
        this.callerDescription = (AppDescription) kn.b((Object) callerDescription, (Object) "Caller's app description cannot be null!");
        this.isLegacyRequest = isLegacyRequest;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        x.a(this, dest, flags);
    }
}
