package com.google.android.gms.fitness.request;

import android.app.PendingIntent;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class aa implements Creator<z> {
    static void a(z zVar, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.a(parcel, 1, zVar.lU(), i, false);
        b.c(parcel, 1000, zVar.getVersionCode());
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return ep(x0);
    }

    public z ep(Parcel parcel) {
        int bT = a.bT(parcel);
        int i = 0;
        PendingIntent pendingIntent = null;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    pendingIntent = (PendingIntent) a.a(parcel, bS, PendingIntent.CREATOR);
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
            return new z(i, pendingIntent);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public z[] ge(int i) {
        return new z[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return ge(x0);
    }
}
