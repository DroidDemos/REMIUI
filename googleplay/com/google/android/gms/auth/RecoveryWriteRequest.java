package com.google.android.gms.auth;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class RecoveryWriteRequest implements SafeParcelable {
    public static final d CREATOR;
    public String mAccount;
    public boolean mIsBroadUse;
    public String mPhoneCountryCode;
    public String mPhoneNumber;
    public String mSecondaryEmail;
    final int mVersionCode;

    static {
        CREATOR = new d();
    }

    public RecoveryWriteRequest() {
        this.mVersionCode = 1;
    }

    RecoveryWriteRequest(int versionCode, String account, String secondaryEmail, String phoneNumber, String phoneCountryCode, boolean isBroadUse) {
        this.mVersionCode = versionCode;
        this.mAccount = account;
        this.mSecondaryEmail = secondaryEmail;
        this.mPhoneNumber = phoneNumber;
        this.mPhoneCountryCode = phoneCountryCode;
        this.mIsBroadUse = isBroadUse;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        d.a(this, out, flags);
    }
}
