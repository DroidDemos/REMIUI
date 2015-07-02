package com.google.android.gms.auth.firstparty.dataservice;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class AccountRecoveryGuidance implements SafeParcelable {
    public static final e CREATOR;
    public final String accountName;
    public final boolean isRecoveryInfoNeeded;
    public final boolean isRecoveryInterstitialSuggested;
    public final boolean isRecoveryUpdateAllowed;
    final int version;

    static {
        CREATOR = new e();
    }

    AccountRecoveryGuidance(int version, String accountName, boolean isRecoveryInfoNeeded, boolean isRecoveryInterstitialSuggested, boolean isRecoveryInterstitialAllowed) {
        this.version = version;
        this.accountName = accountName;
        this.isRecoveryInfoNeeded = isRecoveryInfoNeeded;
        this.isRecoveryInterstitialSuggested = isRecoveryInterstitialSuggested;
        this.isRecoveryUpdateAllowed = isRecoveryInterstitialAllowed;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        e.a(this, dest, flags);
    }
}
