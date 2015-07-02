package com.google.android.gms.fitness.result;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.fitness.data.Subscription;
import com.google.android.wallet.instrumentmanager.R;
import java.util.List;

public class e implements Creator<ListSubscriptionsResult> {
    static void a(ListSubscriptionsResult listSubscriptionsResult, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.d(parcel, 1, listSubscriptionsResult.getSubscriptions(), false);
        b.c(parcel, 1000, listSubscriptionsResult.getVersionCode());
        b.a(parcel, 2, listSubscriptionsResult.getStatus(), i, false);
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return ez(x0);
    }

    public ListSubscriptionsResult ez(Parcel parcel) {
        Status status = null;
        int bT = a.bT(parcel);
        int i = 0;
        List list = null;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    list = a.c(parcel, bS, Subscription.CREATOR);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    status = (Status) a.a(parcel, bS, Status.CREATOR);
                    break;
                case 1000:
                    i = a.g(parcel, bS);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new ListSubscriptionsResult(i, list, status);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public ListSubscriptionsResult[] go(int i) {
        return new ListSubscriptionsResult[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return go(x0);
    }
}
