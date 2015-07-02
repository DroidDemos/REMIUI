package com.google.android.gms.auth;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.kn;
import java.util.List;

public class AccountChangeEventsResponse implements SafeParcelable {
    public static final AccountChangeEventsResponseCreator CREATOR;
    final int Gf;
    final List<AccountChangeEvent> ms;

    static {
        CREATOR = new AccountChangeEventsResponseCreator();
    }

    AccountChangeEventsResponse(int version, List<AccountChangeEvent> events) {
        this.Gf = version;
        this.ms = (List) kn.k(events);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        AccountChangeEventsResponseCreator.a(this, dest, flags);
    }
}
