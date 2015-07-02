package com.google.android.gms.fitness.request;

import android.app.PendingIntent;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class u implements Creator<t> {
    static void a(t tVar, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.a(parcel, 1, tVar.lU(), i, false);
        b.c(parcel, 1000, tVar.getVersionCode());
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return em(x0);
    }

    public t em(Parcel parcel) {
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
            return new t(i, pendingIntent);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public t[] gb(int i) {
        return new t[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return gb(x0);
    }
}
