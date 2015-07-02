package com.google.android.gms.identity.accounts.api;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.kn;

public final class AccountData implements SafeParcelable {
    public static final Creator<AccountData> CREATOR;
    private final String Fl;
    private final String asK;
    private final int mVersionCode;

    static {
        CREATOR = new a();
    }

    public AccountData(int versionCode, String accountName, String plusPageId) {
        kn.b(accountName, (Object) "Account name must not be empty.");
        this.mVersionCode = versionCode;
        this.Fl = accountName;
        this.asK = plusPageId;
    }

    public int describeContents() {
        return 0;
    }

    public String getAccountName() {
        return this.Fl;
    }

    public String getPlusPageId() {
        return this.asK;
    }

    public int getVersionCode() {
        return this.mVersionCode;
    }

    public void writeToParcel(Parcel out, int flags) {
        a.a(this, out, flags);
    }
}
