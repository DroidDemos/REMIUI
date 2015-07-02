package com.google.android.gms.auth.firstparty.dataservice;

import android.os.Bundle;
import android.os.Parcel;
import com.google.android.gms.auth.firstparty.shared.AccountCredentials;
import com.google.android.gms.auth.firstparty.shared.AppDescription;
import com.google.android.gms.auth.firstparty.shared.CaptchaSolution;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class GoogleAccountSetupRequest implements SafeParcelable {
    public static final t CREATOR;
    boolean GM;
    boolean GN;
    AccountCredentials GO;
    CaptchaSolution Gu;
    Bundle Ho;
    boolean Hp;
    boolean Hq;
    boolean Hr;
    String Hs;
    boolean Ht;
    String Hu;
    AppDescription callingAppDescription;
    String firstName;
    String lastName;
    String phoneCountryCode;
    String phoneNumber;
    String secondaryEmail;
    final int version;

    static {
        CREATOR = new t();
    }

    public GoogleAccountSetupRequest() {
        this.version = 1;
        this.Ho = new Bundle();
    }

    GoogleAccountSetupRequest(int version, Bundle options, boolean isAgreedToWebHistory, boolean isAgreedToPersonalizedContent, boolean isAgreedToMobileTos, String firstName, String lastName, String secondaryEmail, String gender, boolean isCreatingAccount, boolean isAddingAccount, boolean isSetupWizardInProgress, String ropRevision, AppDescription callingAppDescription, AccountCredentials accountCredentials, CaptchaSolution optionalCaptchaSolution, String phoneNumber, String phoneCountryCode) {
        this.version = version;
        this.Ho = options;
        this.Hp = isAgreedToWebHistory;
        this.Hq = isAgreedToPersonalizedContent;
        this.Hr = isAgreedToMobileTos;
        this.firstName = firstName;
        this.lastName = lastName;
        this.secondaryEmail = secondaryEmail;
        this.Hs = gender;
        this.GM = isCreatingAccount;
        this.Ht = isAddingAccount;
        this.GN = isSetupWizardInProgress;
        this.Hu = ropRevision;
        this.callingAppDescription = callingAppDescription;
        this.GO = accountCredentials;
        this.Gu = optionalCaptchaSolution;
        this.phoneNumber = phoneNumber;
        this.phoneCountryCode = phoneCountryCode;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        t.a(this, dest, flags);
    }
}
