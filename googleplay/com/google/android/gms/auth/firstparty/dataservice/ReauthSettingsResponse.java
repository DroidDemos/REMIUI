package com.google.android.gms.auth.firstparty.dataservice;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class ReauthSettingsResponse implements SafeParcelable {
    public static final ae CREATOR;
    public final PasswordSettings password;
    public final PinSettings pin;
    public final int status;
    final int version;

    static {
        CREATOR = new ae();
    }

    ReauthSettingsResponse(int version, int status, PasswordSettings password, PinSettings pin) {
        this.version = version;
        this.status = status;
        this.password = password;
        this.pin = pin;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        ae.a(this, dest, flags);
    }
}
