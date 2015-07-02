package com.google.android.gms.auth.firstparty.dataservice;

import android.os.Bundle;
import android.os.Parcel;
import com.google.android.gms.auth.firstparty.shared.AppDescription;
import com.google.android.gms.auth.firstparty.shared.CaptchaSolution;
import com.google.android.gms.auth.firstparty.shared.FACLConfig;
import com.google.android.gms.auth.firstparty.shared.PACLConfig;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class TokenRequest implements SafeParcelable {
    public static final af CREATOR;
    volatile boolean GM;
    CaptchaSolution Gu;
    String HE;
    volatile FACLConfig HF;
    volatile PACLConfig HG;
    String HH;
    volatile boolean HI;
    volatile boolean HJ;
    volatile boolean HK;
    Bundle Ho;
    volatile boolean Ht;
    String accountName;
    AppDescription callingAppDescription;
    final int version;

    public enum Consent {
        UNKNOWN,
        GRANTED,
        REJECTED
    }

    static {
        CREATOR = new af();
    }

    public TokenRequest() {
        this.Ho = new Bundle();
        this.HH = Consent.UNKNOWN.toString();
        this.version = 1;
    }

    TokenRequest(int version, String service, String accountName, Bundle options, FACLConfig faclData, PACLConfig paclData, boolean isAddingAccount, boolean isCreatingAccount, String consent, AppDescription callingAppDescription, CaptchaSolution optionalCaptchaSolution, boolean isForcingEmailCheck, boolean isForcingDroidguardRun, boolean isUsingCache) {
        this.Ho = new Bundle();
        this.HH = Consent.UNKNOWN.toString();
        this.version = version;
        this.HE = service;
        this.accountName = accountName;
        this.Ho = options;
        this.HF = faclData;
        this.HG = paclData;
        this.Ht = isAddingAccount;
        this.GM = isCreatingAccount;
        this.HH = consent;
        this.callingAppDescription = callingAppDescription;
        this.Gu = optionalCaptchaSolution;
        this.HI = isForcingEmailCheck;
        this.HJ = isForcingDroidguardRun;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        af.a(this, dest, flags);
    }
}
