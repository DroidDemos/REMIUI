package com.google.android.gms.auth;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.List;

public class RecoveryReadResponse implements SafeParcelable {
    public static final c CREATOR;
    public String mAction;
    public String mAllowedOptions;
    public List<Country> mCountryList;
    public String mError;
    public String mPhoneCountryCode;
    public String mPhoneNumber;
    public String mSecondaryEmail;
    final int mVersionCode;

    static {
        CREATOR = new c();
    }

    public RecoveryReadResponse() {
        this.mVersionCode = 1;
    }

    RecoveryReadResponse(int versionCode, String secondaryEmail, String phoneNumber, String phoneCountryCode, List<Country> countryList, String error, String action, String allowedOptions) {
        this.mVersionCode = versionCode;
        this.mSecondaryEmail = secondaryEmail;
        this.mPhoneNumber = phoneNumber;
        this.mPhoneCountryCode = phoneCountryCode;
        this.mCountryList = countryList;
        this.mError = error;
        this.mAction = action;
        this.mAllowedOptions = allowedOptions;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        c.a(this, out, flags);
    }
}
