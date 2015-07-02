package com.google.android.gms.location.copresence;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.kl;
import com.google.android.gms.internal.kn;
import java.util.Arrays;

public final class Message implements SafeParcelable {
    public static final Creator<Message> CREATOR;
    @Deprecated
    private final String Pr;
    private final byte[] auh;
    private final int mVersionCode;
    private final String vc;

    static {
        CREATOR = new h();
    }

    Message(int versionCode, String namespace, String type, byte[] payload) {
        this.mVersionCode = versionCode;
        this.Pr = namespace;
        this.vc = type;
        this.auh = payload;
        kn.a(payload.length <= 1000, "Payloads may be at most 1000 bytes");
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof Message)) {
            return false;
        }
        Message message = (Message) object;
        return TextUtils.equals(this.vc, message.vc) && Arrays.equals(this.auh, message.auh);
    }

    public String getNamespace() {
        return this.Pr;
    }

    public byte[] getPayload() {
        return this.auh;
    }

    public String getType() {
        return this.vc;
    }

    int getVersionCode() {
        return this.mVersionCode;
    }

    public int hashCode() {
        return kl.hashCode(this.vc, Integer.valueOf(Arrays.hashCode(this.auh)));
    }

    public String toString() {
        return "Message[" + this.vc + "]";
    }

    public void writeToParcel(Parcel parcel, int flags) {
        h.a(this, parcel, flags);
    }
}
