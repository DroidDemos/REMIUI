package com.google.android.gms.location.copresence;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.kl;

public final class SubscribedMessage implements SafeParcelable {
    public static final Creator<SubscribedMessage> CREATOR;
    private final Message auC;
    private final int mVersionCode;

    static {
        CREATOR = new m();
    }

    SubscribedMessage(int versionCode, Message message) {
        this.mVersionCode = versionCode;
        this.auC = message;
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        return kl.equal(this.auC, ((SubscribedMessage) object).auC);
    }

    public Message getMessage() {
        return this.auC;
    }

    int getVersionCode() {
        return this.mVersionCode;
    }

    public int hashCode() {
        return kl.hashCode(this.auC);
    }

    public void writeToParcel(Parcel dest, int flags) {
        m.a(this, dest, flags);
    }
}
