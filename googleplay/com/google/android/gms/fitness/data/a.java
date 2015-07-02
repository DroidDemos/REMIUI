package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class a implements Creator<Application> {
    static void a(Application application, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.a(parcel, 1, application.getPackageName(), false);
        b.c(parcel, 1000, application.getVersionCode());
        b.a(parcel, 2, application.getVersion(), false);
        b.a(parcel, 3, application.getDomainName(), false);
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return dI(x0);
    }

    public Application dI(Parcel parcel) {
        String str = null;
        int bT = com.google.android.gms.common.internal.safeparcel.a.bT(parcel);
        int i = 0;
        String str2 = null;
        String str3 = null;
        while (parcel.dataPosition() < bT) {
            int bS = com.google.android.gms.common.internal.safeparcel.a.bS(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    str3 = com.google.android.gms.common.internal.safeparcel.a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    str2 = com.google.android.gms.common.internal.safeparcel.a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    str = com.google.android.gms.common.internal.safeparcel.a.p(parcel, bS);
                    break;
                case 1000:
                    i = com.google.android.gms.common.internal.safeparcel.a.g(parcel, bS);
                    break;
                default:
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new Application(i, str3, str2, str);
        }
        throw new com.google.android.gms.common.internal.safeparcel.a.a("Overread allowed size end=" + bT, parcel);
    }

    public Application[] fv(int i) {
        return new Application[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return fv(x0);
    }
}
