package com.google.android.gms.wallet.shared;

import android.accounts.Account;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class ApplicationParameters implements SafeParcelable {
    public static final Creator<ApplicationParameters> CREATOR;
    int aUz;
    Account aVv;
    boolean aVw;
    Bundle mArgs;
    int mTheme;
    final int mVersionCode;

    static {
        CREATOR = new a();
    }

    ApplicationParameters() {
        this.aVw = false;
        this.mVersionCode = 2;
        this.aUz = 1;
        this.mTheme = 0;
    }

    ApplicationParameters(int versionCode, int environment, Account buyerAccount, Bundle args, boolean allowAccountSelection, int theme) {
        this.aVw = false;
        this.mVersionCode = versionCode;
        this.aUz = environment;
        this.aVv = buyerAccount;
        this.mArgs = args;
        this.aVw = allowAccountSelection;
        this.mTheme = theme;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        a.a(this, out, flags);
    }
}
