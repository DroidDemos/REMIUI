package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class o implements Creator<Session> {
    static void a(Session session, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.a(parcel, 1, session.lo());
        b.c(parcel, 1000, session.getVersionCode());
        b.a(parcel, 2, session.lp());
        b.a(parcel, 3, session.getName(), false);
        b.a(parcel, 4, session.getIdentifier(), false);
        b.a(parcel, 5, session.getDescription(), false);
        b.c(parcel, 7, session.ln());
        b.a(parcel, 8, session.getApplication(), i, false);
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return dU(x0);
    }

    public Session dU(Parcel parcel) {
        long j = 0;
        int i = 0;
        Application application = null;
        int bT = a.bT(parcel);
        String str = null;
        String str2 = null;
        String str3 = null;
        long j2 = 0;
        int i2 = 0;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    j2 = a.i(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    j = a.i(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    str3 = a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    str2 = a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                    str = a.p(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                    i = a.g(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                    application = (Application) a.a(parcel, bS, Application.CREATOR);
                    break;
                case 1000:
                    i2 = a.g(parcel, bS);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new Session(i2, j2, j, str3, str2, str, i, application);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public Session[] fI(int i) {
        return new Session[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return fI(x0);
    }
}
