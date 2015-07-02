package com.google.android.gms.auth.firstparty.dataservice;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class PinSettings implements SafeParcelable {
    public static final ac CREATOR;
    public final int length;
    public final String resetUrl;
    public final String setupUrl;
    public final String status;
    final int version;

    static {
        CREATOR = new ac();
    }

    PinSettings(int version, String status, String resetUrl, String setupUrl, int length) {
        this.version = version;
        this.status = status;
        this.resetUrl = resetUrl;
        this.setupUrl = setupUrl;
        this.length = length;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        ac.a(this, dest, flags);
    }
}
