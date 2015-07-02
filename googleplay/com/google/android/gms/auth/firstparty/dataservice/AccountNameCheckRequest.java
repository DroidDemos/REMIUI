package com.google.android.gms.auth.firstparty.dataservice;

import android.os.Parcel;
import com.google.android.gms.auth.firstparty.shared.AppDescription;
import com.google.android.gms.auth.firstparty.shared.CaptchaSolution;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class AccountNameCheckRequest implements SafeParcelable {
    public static final a CREATOR;
    String Gr;
    String Gs;
    String Gt;
    CaptchaSolution Gu;
    AppDescription callingAppDescription;
    final int version;

    static {
        CREATOR = new a();
    }

    public AccountNameCheckRequest() {
        this.version = 1;
    }

    AccountNameCheckRequest(int version, String accountNameToCheck, String optionalFirstName, String optionalLastName, AppDescription callingAppDescription, CaptchaSolution optionalCaptchaSolution) {
        this.version = version;
        this.Gr = accountNameToCheck;
        this.Gs = optionalFirstName;
        this.Gt = optionalLastName;
        this.callingAppDescription = callingAppDescription;
        this.Gu = optionalCaptchaSolution;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        a.a(this, dest, flags);
    }
}
