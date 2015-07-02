package com.google.android.gms.auth.firstparty.dataservice;

import android.os.Parcel;
import com.google.android.gms.auth.Country;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.Collections;
import java.util.List;

public class AccountRecoveryData implements SafeParcelable {
    public static final c CREATOR;
    public final String accountName;
    public final String action;
    public final String allowedRecoveryOption;
    public final List<Country> countries;
    public final String defaultCountryCode;
    public final String error;
    public final AccountRecoveryGuidance guidance;
    public final String phoneNumber;
    public final String secondaryEmail;
    public final int version;

    static {
        CREATOR = new c();
    }

    AccountRecoveryData(int version, AccountRecoveryGuidance guidance, String action, String detail, String accountName, String secondaryEmail, String phoneNumber, List<Country> countries, String defaultCountryCode, String error) {
        this.version = version;
        this.guidance = guidance;
        this.action = action;
        this.allowedRecoveryOption = detail;
        this.accountName = accountName;
        this.secondaryEmail = secondaryEmail;
        this.phoneNumber = phoneNumber;
        this.countries = countries == null ? Collections.EMPTY_LIST : Collections.unmodifiableList(countries);
        this.defaultCountryCode = defaultCountryCode;
        this.error = error;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        c.a(this, dest, flags);
    }
}
