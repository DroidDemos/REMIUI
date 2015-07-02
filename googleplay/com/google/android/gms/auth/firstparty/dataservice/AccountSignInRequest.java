package com.google.android.gms.auth.firstparty.dataservice;

import android.os.Parcel;
import com.google.android.gms.auth.firstparty.shared.AccountCredentials;
import com.google.android.gms.auth.firstparty.shared.AppDescription;
import com.google.android.gms.auth.firstparty.shared.CaptchaSolution;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class AccountSignInRequest implements SafeParcelable {
    public static final k CREATOR;
    boolean GM;
    boolean GN;
    AccountCredentials GO;
    CaptchaSolution Gu;
    AppDescription callingAppDescription;
    final int version;

    static {
        CREATOR = new k();
    }

    public AccountSignInRequest() {
        this.version = 1;
    }

    AccountSignInRequest(int version, AppDescription callingAppDescription, boolean isCreatingAccount, boolean isSetupWizardInProgress, CaptchaSolution optionalCaptchaSolution, AccountCredentials accountCredentials) {
        this.version = version;
        this.callingAppDescription = callingAppDescription;
        this.GM = isCreatingAccount;
        this.GN = isSetupWizardInProgress;
        this.Gu = optionalCaptchaSolution;
        this.GO = accountCredentials;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        k.a(this, dest, flags);
    }
}
