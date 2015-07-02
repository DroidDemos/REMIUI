package com.google.android.gms.auth.firstparty.dataservice;

import android.os.Parcel;
import com.google.android.gms.auth.firstparty.shared.AppDescription;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.kn;

public class AccountRecoveryDataRequest implements SafeParcelable {
    public static final d CREATOR;
    private static final String GI;
    public final String accountName;
    public final AppDescription callingAppDescription;
    public final boolean isClearUpdateSuggested;
    public final String requestDescription;
    final int version;

    static {
        GI = "[" + AccountRecoveryDataRequest.class.getSimpleName() + "]";
        CREATOR = new d();
    }

    AccountRecoveryDataRequest(int version, String accountName, boolean isClearUpdateSuggestion, AppDescription callingAppDescription, String requestDescription) {
        kn.b(accountName, GI + " accountName cannot be empty or null!");
        kn.b(requestDescription, GI + " requestDescription cannot be empty or null!");
        this.version = version;
        this.accountName = kn.bk(accountName);
        this.isClearUpdateSuggested = isClearUpdateSuggestion;
        this.callingAppDescription = (AppDescription) kn.k(callingAppDescription);
        this.requestDescription = requestDescription;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        d.a(this, dest, flags);
    }
}
