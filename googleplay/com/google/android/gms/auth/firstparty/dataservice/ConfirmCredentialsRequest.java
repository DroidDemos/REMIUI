package com.google.android.gms.auth.firstparty.dataservice;

import android.os.Parcel;
import com.google.android.gms.auth.firstparty.shared.AccountCredentials;
import com.google.android.gms.auth.firstparty.shared.CaptchaSolution;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class ConfirmCredentialsRequest implements SafeParcelable {
    public static final r CREATOR;
    AccountCredentials GO;
    CaptchaSolution Gu;
    final int version;

    static {
        CREATOR = new r();
    }

    public ConfirmCredentialsRequest() {
        this.version = 1;
    }

    ConfirmCredentialsRequest(int version, AccountCredentials accountCredentials, CaptchaSolution optionalCaptchaSolution) {
        this.version = version;
        this.GO = accountCredentials;
        this.Gu = optionalCaptchaSolution;
    }

    public int describeContents() {
        return 0;
    }

    public ConfirmCredentialsRequest setAccountCredentials(AccountCredentials accountCredentials) {
        this.GO = accountCredentials;
        return this;
    }

    public void writeToParcel(Parcel dest, int flags) {
        r.a(this, dest, flags);
    }
}
