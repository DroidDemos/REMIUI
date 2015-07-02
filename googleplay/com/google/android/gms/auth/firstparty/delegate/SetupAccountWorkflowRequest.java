package com.google.android.gms.auth.firstparty.delegate;

import android.os.Bundle;
import android.os.Parcel;
import com.google.android.gms.auth.firstparty.shared.AppDescription;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.kn;
import java.util.List;

public class SetupAccountWorkflowRequest implements SafeParcelable {
    public static final c CREATOR;
    boolean HX;
    boolean HY;
    List<String> HZ;
    Bundle Ho;
    boolean Ia;
    public final AppDescription callingAppDescription;
    final int version;

    static {
        CREATOR = new c();
    }

    SetupAccountWorkflowRequest(int version, boolean isMultiUser, boolean isSetupWizard, List<String> allowedDomains, Bundle options, AppDescription callingAppDescription, boolean isCreditCardAllowed) {
        this.version = version;
        this.HX = isMultiUser;
        this.HY = isSetupWizard;
        this.HZ = allowedDomains;
        this.Ho = options;
        this.callingAppDescription = (AppDescription) kn.k(callingAppDescription);
        this.Ia = isCreditCardAllowed;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        c.a(this, dest, flags);
    }
}
