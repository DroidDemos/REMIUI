package com.google.android.gms.clearcut;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.internal.te;
import com.google.android.wallet.instrumentmanager.R;

public class a implements Creator<LogEventParcelable> {
    static void a(LogEventParcelable logEventParcelable, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, logEventParcelable.versionCode);
        b.a(parcel, 2, logEventParcelable.playLoggerContext, i, false);
        b.a(parcel, 3, logEventParcelable.logEventBytes, false);
        b.J(parcel, bU);
    }

    public LogEventParcelable bL(Parcel parcel) {
        byte[] bArr = null;
        int bT = com.google.android.gms.common.internal.safeparcel.a.bT(parcel);
        int i = 0;
        te teVar = null;
        while (parcel.dataPosition() < bT) {
            te teVar2;
            int g;
            byte[] bArr2;
            int bS = com.google.android.gms.common.internal.safeparcel.a.bS(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    byte[] bArr3 = bArr;
                    teVar2 = teVar;
                    g = com.google.android.gms.common.internal.safeparcel.a.g(parcel, bS);
                    bArr2 = bArr3;
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    g = i;
                    te teVar3 = (te) com.google.android.gms.common.internal.safeparcel.a.a(parcel, bS, te.CREATOR);
                    bArr2 = bArr;
                    teVar2 = teVar3;
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    bArr2 = com.google.android.gms.common.internal.safeparcel.a.s(parcel, bS);
                    teVar2 = teVar;
                    g = i;
                    break;
                default:
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, bS);
                    bArr2 = bArr;
                    teVar2 = teVar;
                    g = i;
                    break;
            }
            i = g;
            teVar = teVar2;
            bArr = bArr2;
        }
        if (parcel.dataPosition() == bT) {
            return new LogEventParcelable(i, teVar, bArr);
        }
        throw new com.google.android.gms.common.internal.safeparcel.a.a("Overread allowed size end=" + bT, parcel);
    }

    public LogEventParcelable[] cL(int i) {
        return new LogEventParcelable[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return bL(x0);
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return cL(x0);
    }
}
