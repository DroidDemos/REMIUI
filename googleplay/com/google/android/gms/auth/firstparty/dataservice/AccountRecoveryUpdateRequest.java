package com.google.android.gms.auth.firstparty.dataservice;

import android.os.Parcel;
import com.google.android.gms.auth.firstparty.shared.AppDescription;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class AccountRecoveryUpdateRequest implements SafeParcelable {
    public static final g CREATOR;
    public final String accountName;
    public final AppDescription callingAppDescription;
    public final boolean isBroadUse;
    public final String phoneCountryCode;
    public final String phoneNumber;
    public final String secondaryEmail;
    final int version;

    static {
        CREATOR = new g();
    }

    AccountRecoveryUpdateRequest(int version, String accountName, String secondaryEmail, String phoneNumber, String phoneCountryCode, boolean isBroadUse, AppDescription callingAppDescription) {
        this.version = version;
        this.accountName = accountName;
        this.secondaryEmail = secondaryEmail;
        this.phoneNumber = phoneNumber;
        this.phoneCountryCode = phoneCountryCode;
        this.isBroadUse = isBroadUse;
        this.callingAppDescription = callingAppDescription;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        g.a(this, dest, flags);
    }
}
