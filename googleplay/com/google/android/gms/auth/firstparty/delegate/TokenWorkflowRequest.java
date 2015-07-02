package com.google.android.gms.auth.firstparty.delegate;

import android.os.Bundle;
import android.os.Parcel;
import com.google.android.gms.auth.firstparty.shared.AppDescription;
import com.google.android.gms.auth.firstparty.shared.FACLConfig;
import com.google.android.gms.auth.firstparty.shared.PACLConfig;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class TokenWorkflowRequest implements SafeParcelable {
    public static final d CREATOR;
    volatile String HE;
    volatile FACLConfig HF;
    volatile PACLConfig HG;
    volatile Bundle Ho;
    volatile boolean Ib;
    volatile String accountName;
    volatile AppDescription callingAppDescription;
    final int version;

    static {
        CREATOR = new d();
    }

    public TokenWorkflowRequest() {
        this.version = 1;
        this.Ho = new Bundle();
    }

    TokenWorkflowRequest(int version, String service, String accountName, Bundle options, FACLConfig faclData, PACLConfig paclData, boolean isSuppressingProgressUx, AppDescription callingAppDescription) {
        this.version = version;
        this.HE = service;
        this.accountName = accountName;
        this.Ho = options;
        this.HF = faclData;
        this.HG = paclData;
        this.Ib = isSuppressingProgressUx;
        this.callingAppDescription = callingAppDescription;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        d.a(this, dest, flags);
    }
}
