package com.google.android.gms.wearable;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.kl;

public class ConnectionConfiguration implements SafeParcelable {
    public static final Creator<ConnectionConfiguration> CREATOR;
    private final int EB;
    private final int aWh;
    private final boolean aWi;
    private boolean aWj;
    private String aWk;
    private final String agh;
    private final String mName;
    final int mVersionCode;

    static {
        CREATOR = new b();
    }

    ConnectionConfiguration(int versionCode, String name, String address, int type, int role, boolean connectionEnabled, boolean isConnected, String peerNodeId) {
        this.mVersionCode = versionCode;
        this.mName = name;
        this.agh = address;
        this.EB = type;
        this.aWh = role;
        this.aWi = connectionEnabled;
        this.aWj = isConnected;
        this.aWk = peerNodeId;
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object o) {
        if (!(o instanceof ConnectionConfiguration)) {
            return false;
        }
        ConnectionConfiguration connectionConfiguration = (ConnectionConfiguration) o;
        return kl.equal(Integer.valueOf(this.mVersionCode), Integer.valueOf(connectionConfiguration.mVersionCode)) && kl.equal(this.mName, connectionConfiguration.mName) && kl.equal(this.agh, connectionConfiguration.agh) && kl.equal(Integer.valueOf(this.EB), Integer.valueOf(connectionConfiguration.EB)) && kl.equal(Integer.valueOf(this.aWh), Integer.valueOf(connectionConfiguration.aWh)) && kl.equal(Boolean.valueOf(this.aWi), Boolean.valueOf(connectionConfiguration.aWi));
    }

    public String getAddress() {
        return this.agh;
    }

    public String getName() {
        return this.mName;
    }

    public String getPeerNodeId() {
        return this.aWk;
    }

    public int getRole() {
        return this.aWh;
    }

    public int getType() {
        return this.EB;
    }

    public int hashCode() {
        return kl.hashCode(Integer.valueOf(this.mVersionCode), this.mName, this.agh, Integer.valueOf(this.EB), Integer.valueOf(this.aWh), Boolean.valueOf(this.aWi));
    }

    public boolean isConnected() {
        return this.aWj;
    }

    public boolean isEnabled() {
        return this.aWi;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("ConnectionConfiguration[ ");
        stringBuilder.append("mName=" + this.mName);
        stringBuilder.append(", mAddress=" + this.agh);
        stringBuilder.append(", mType=" + this.EB);
        stringBuilder.append(", mRole=" + this.aWh);
        stringBuilder.append(", mEnabled=" + this.aWi);
        stringBuilder.append(", mIsConnected=" + this.aWj);
        stringBuilder.append(", mEnabled=" + this.aWk);
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    public void writeToParcel(Parcel dest, int flags) {
        b.a(this, dest, flags);
    }
}
