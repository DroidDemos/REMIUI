package com.google.android.gms.wallet.shared;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class BuyFlowConfig implements SafeParcelable {
    public static final Creator<BuyFlowConfig> CREATOR;
    String aVB;
    ApplicationParameters aVC;
    String aVD;
    String aVE;
    String aVF;
    final int mVersionCode;

    static {
        CREATOR = new c();
    }

    BuyFlowConfig() {
        this.mVersionCode = 2;
    }

    BuyFlowConfig(int versionCode, String transactionId, ApplicationParameters appParams, String callingPackage, String flowName, String callingAppIdentifier) {
        this.mVersionCode = versionCode;
        this.aVB = transactionId;
        this.aVC = appParams;
        this.aVD = callingPackage;
        this.aVE = flowName;
        this.aVF = callingAppIdentifier;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        c.a(this, out, flags);
    }
}
