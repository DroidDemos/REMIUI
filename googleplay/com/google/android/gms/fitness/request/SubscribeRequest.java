package com.google.android.gms.fitness.request;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.fitness.data.Subscription;
import com.google.android.gms.internal.kl;

public class SubscribeRequest implements SafeParcelable {
    public static final Creator<SubscribeRequest> CREATOR;
    private final Subscription aiv;
    private final boolean aiw;
    private final int mVersionCode;

    static {
        CREATOR = new ae();
    }

    SubscribeRequest(int versionCode, Subscription subscription, boolean serverOnly) {
        this.mVersionCode = versionCode;
        this.aiv = subscription;
        this.aiw = serverOnly;
    }

    public int describeContents() {
        return 0;
    }

    public Subscription getSubscription() {
        return this.aiv;
    }

    int getVersionCode() {
        return this.mVersionCode;
    }

    public boolean isServerOnly() {
        return this.aiw;
    }

    public String toString() {
        return kl.j(this).a("subscription", this.aiv).toString();
    }

    public void writeToParcel(Parcel dest, int flags) {
        ae.a(this, dest, flags);
    }
}
