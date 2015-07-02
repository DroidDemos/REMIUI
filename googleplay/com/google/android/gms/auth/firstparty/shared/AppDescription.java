package com.google.android.gms.auth.firstparty.shared;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.kn;

public class AppDescription implements SafeParcelable {
    public static final b CREATOR;
    private static final String GI;
    boolean GN;
    private final String Iq;
    int Ir;
    String Is;
    String It;
    String tP;
    final int version;

    static {
        GI = "[" + AppDescription.class.getSimpleName() + "]";
        CREATOR = new b();
    }

    AppDescription(int version, int callingUid, String sessionId, String sessionSig, String callingPkg, boolean isSetupWizardInProgress) {
        this.Iq = "[" + getClass().getSimpleName() + "] %s - %s: %s";
        this.version = version;
        this.tP = sessionId;
        this.Is = sessionSig;
        this.It = kn.b(callingPkg, GI + " callingPkg cannot be null or empty!");
        kn.b(callingUid != 0, (Object) "Invalid callingUid! Cannot be 0!");
        this.Ir = callingUid;
        this.GN = isSetupWizardInProgress;
    }

    public int describeContents() {
        return 0;
    }

    public String toString() {
        return new StringBuilder(getClass().getSimpleName()).append("<").append(this.It).append(", ").append(this.Ir).append(">").toString();
    }

    public void writeToParcel(Parcel dest, int flags) {
        b.a(this, dest, flags);
    }
}
