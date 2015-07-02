package com.google.android.gms.fitness.result;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.fitness.data.Subscription;
import com.google.android.gms.internal.kl;
import java.util.List;

public class ListSubscriptionsResult implements Result, SafeParcelable {
    public static final Creator<ListSubscriptionsResult> CREATOR;
    private final Status EU;
    private final List<Subscription> aiB;
    private final int mVersionCode;

    static {
        CREATOR = new e();
    }

    ListSubscriptionsResult(int versionCode, List<Subscription> subscriptions, Status status) {
        this.mVersionCode = versionCode;
        this.aiB = subscriptions;
        this.EU = status;
    }

    private boolean b(ListSubscriptionsResult listSubscriptionsResult) {
        return this.EU.equals(listSubscriptionsResult.EU) && kl.equal(this.aiB, listSubscriptionsResult.aiB);
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object that) {
        return this == that || ((that instanceof ListSubscriptionsResult) && b((ListSubscriptionsResult) that));
    }

    public Status getStatus() {
        return this.EU;
    }

    public List<Subscription> getSubscriptions() {
        return this.aiB;
    }

    int getVersionCode() {
        return this.mVersionCode;
    }

    public int hashCode() {
        return kl.hashCode(this.EU, this.aiB);
    }

    public String toString() {
        return kl.j(this).a("status", this.EU).a("subscriptions", this.aiB).toString();
    }

    public void writeToParcel(Parcel dest, int flags) {
        e.a(this, dest, flags);
    }
}
