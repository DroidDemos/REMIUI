package com.google.android.gms.auth.firstparty.dataservice;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.List;

public class GoogleAccountData implements SafeParcelable {
    public static final s CREATOR;
    boolean GQ;
    String accountName;
    public String firstName;
    public String lastName;
    public List<String> services;
    final int version;

    static {
        CREATOR = new s();
    }

    GoogleAccountData(int version, String accountName, boolean isBrowserFlowRequired, List<String> services, String firstName, String lastName) {
        this.version = version;
        this.accountName = accountName;
        this.GQ = isBrowserFlowRequired;
        this.services = services;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        s.a(this, dest, flags);
    }
}
