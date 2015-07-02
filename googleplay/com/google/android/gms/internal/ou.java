package com.google.android.gms.internal;

import android.app.PendingIntent;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class ou implements Creator<ot> {
    static void a(ot otVar, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, otVar.getVersionCode());
        b.c(parcel, 2, otVar.type);
        b.a(parcel, 3, otVar.pg(), false);
        b.a(parcel, 4, otVar.avf, i, false);
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return fG(x0);
    }

    public ot fG(Parcel parcel) {
        PendingIntent pendingIntent = null;
        int i = 0;
        int bT = a.bT(parcel);
        IBinder iBinder = null;
        int i2 = 0;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i2 = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    i = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    iBinder = a.q(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    pendingIntent = (PendingIntent) a.a(parcel, bS, PendingIntent.CREATOR);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new ot(i2, i, iBinder, pendingIntent);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public ot[] hX(int i) {
        return new ot[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return hX(x0);
    }
}
