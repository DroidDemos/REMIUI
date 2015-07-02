package com.google.android.gms.fitness.request;

import android.app.PendingIntent;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class q implements Creator<p> {
    static void a(p pVar, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.a(parcel, 1, pVar.lZ(), false);
        b.c(parcel, 1000, pVar.getVersionCode());
        b.a(parcel, 2, pVar.lU(), i, false);
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return ej(x0);
    }

    public p ej(Parcel parcel) {
        PendingIntent pendingIntent = null;
        int bT = a.bT(parcel);
        int i = 0;
        IBinder iBinder = null;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    iBinder = a.q(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
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
            return new p(i, iBinder, pendingIntent);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public p[] fY(int i) {
        return new p[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return fY(x0);
    }
}
