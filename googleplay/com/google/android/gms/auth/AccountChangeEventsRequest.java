package com.google.android.gms.auth;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class AccountChangeEventsRequest implements SafeParcelable {
    public static final AccountChangeEventsRequestCreator CREATOR;
    String Fl;
    final int Gf;
    int Gi;

    static {
        CREATOR = new AccountChangeEventsRequestCreator();
    }

    public AccountChangeEventsRequest() {
        this.Gf = 1;
    }

    AccountChangeEventsRequest(int version, int eventIndex, String accountName) {
        this.Gf = version;
        this.Gi = eventIndex;
        this.Fl = accountName;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        AccountChangeEventsRequestCreator.a(this, dest, flags);
    }
}
