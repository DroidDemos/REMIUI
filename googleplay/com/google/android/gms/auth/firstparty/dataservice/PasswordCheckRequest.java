package com.google.android.gms.auth.firstparty.dataservice;

import android.os.Parcel;
import com.google.android.gms.auth.firstparty.shared.AppDescription;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class PasswordCheckRequest implements SafeParcelable {
    public static final z CREATOR;
    String Gs;
    String Gt;
    String HB;
    AppDescription HC;
    String accountName;
    final int version;

    static {
        CREATOR = new z();
    }

    PasswordCheckRequest(int version, String accountName, String password, String optionalFirstName, String optionalLastName, AppDescription appDescription) {
        this.version = version;
        this.accountName = accountName;
        this.HB = password;
        this.Gs = optionalFirstName;
        this.Gt = optionalLastName;
        this.HC = appDescription;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        z.a(this, dest, flags);
    }
}
