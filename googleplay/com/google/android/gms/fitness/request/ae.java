package com.google.android.gms.fitness.request;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.fitness.data.Subscription;
import com.google.android.wallet.instrumentmanager.R;

public class ae implements Creator<SubscribeRequest> {
    static void a(SubscribeRequest subscribeRequest, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.a(parcel, 1, subscribeRequest.getSubscription(), i, false);
        b.c(parcel, 1000, subscribeRequest.getVersionCode());
        b.a(parcel, 2, subscribeRequest.isServerOnly());
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return es(x0);
    }

    public SubscribeRequest es(Parcel parcel) {
        boolean z = false;
        int bT = a.bT(parcel);
        Subscription subscription = null;
        int i = 0;
        while (parcel.dataPosition() < bT) {
            int i2;
            boolean z2;
            Subscription subscription2;
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i2 = i;
                    Subscription subscription3 = (Subscription) a.a(parcel, bS, Subscription.CREATOR);
                    z2 = z;
                    subscription2 = subscription3;
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    z2 = a.c(parcel, bS);
                    subscription2 = subscription;
                    i2 = i;
                    break;
                case 1000:
                    boolean z3 = z;
                    subscription2 = subscription;
                    i2 = a.g(parcel, bS);
                    z2 = z3;
                    break;
                default:
                    a.b(parcel, bS);
                    z2 = z;
                    subscription2 = subscription;
                    i2 = i;
                    break;
            }
            i = i2;
            subscription = subscription2;
            z = z2;
        }
        if (parcel.dataPosition() == bT) {
            return new SubscribeRequest(i, subscription, z);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public SubscribeRequest[] gh(int i) {
        return new SubscribeRequest[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return gh(x0);
    }
}
