package com.google.android.gms.auth.firstparty.dataservice;

import android.os.Parcel;
import com.google.android.gms.auth.firstparty.shared.AppDescription;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.kn;

public class WebSetupConfigRequest implements SafeParcelable {
    public static final al CREATOR;
    public final AppDescription callingAppDescription;
    final int version;

    static {
        CREATOR = new al();
    }

    WebSetupConfigRequest(int version, AppDescription callingAppDescription) {
        this.version = version;
        this.callingAppDescription = (AppDescription) kn.k(callingAppDescription);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        al.a(this, dest, flags);
    }
}
