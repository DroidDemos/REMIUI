package com.google.android.gms.games.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class ConnectionInfo implements SafeParcelable {
    public static final ConnectionInfoCreator CREATOR;
    private final String akn;
    private final int ako;
    private final int mVersionCode;

    static {
        CREATOR = new ConnectionInfoCreator();
    }

    public ConnectionInfo(int versionCode, String clientAddress, int registrationLatency) {
        this.mVersionCode = versionCode;
        this.akn = clientAddress;
        this.ako = registrationLatency;
    }

    public int describeContents() {
        return 0;
    }

    public int getVersionCode() {
        return this.mVersionCode;
    }

    public String mT() {
        return this.akn;
    }

    public int mU() {
        return this.ako;
    }

    public void writeToParcel(Parcel out, int flags) {
        ConnectionInfoCreator.a(this, out, flags);
    }
}
