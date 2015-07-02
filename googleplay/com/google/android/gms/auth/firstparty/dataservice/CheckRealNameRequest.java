package com.google.android.gms.auth.firstparty.dataservice;

import android.os.Parcel;
import com.google.android.gms.auth.firstparty.shared.AppDescription;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class CheckRealNameRequest implements SafeParcelable {
    public static final n CREATOR;
    AppDescription callingAppDescription;
    String firstName;
    String lastName;
    final int version;

    static {
        CREATOR = new n();
    }

    public CheckRealNameRequest() {
        this.version = 1;
    }

    CheckRealNameRequest(int version, AppDescription callingAppDescription, String firstName, String lastName) {
        this.version = version;
        this.callingAppDescription = callingAppDescription;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        n.a(this, dest, flags);
    }
}
