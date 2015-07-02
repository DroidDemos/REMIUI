package com.google.android.gms.auth.firstparty.dataservice;

import android.os.Parcel;
import com.google.android.gms.auth.firstparty.shared.AccountCredentials;
import com.google.android.gms.auth.firstparty.shared.CaptchaSolution;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class UpdateCredentialsRequest implements SafeParcelable {
    public static final ah CREATOR;
    AccountCredentials GO;
    CaptchaSolution Gu;
    final int version;

    static {
        CREATOR = new ah();
    }

    public UpdateCredentialsRequest() {
        this.version = 1;
    }

    UpdateCredentialsRequest(int version, AccountCredentials accountCredentials, CaptchaSolution optionalCaptchaSolution) {
        this.version = version;
        this.GO = accountCredentials;
        this.Gu = optionalCaptchaSolution;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        ah.a(this, dest, flags);
    }
}
