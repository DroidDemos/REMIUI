package com.google.android.gms.reminders.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class h implements Creator<i> {
    static void a(i iVar, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, iVar.mVersionCode);
        b.a(parcel, 2, iVar.getServerAssignedId(), false);
        b.a(parcel, 3, iVar.getClientAssignedId(), false);
        b.a(parcel, 4, iVar.getClientAssignedThreadId(), false);
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return hn(x0);
    }

    public i hn(Parcel parcel) {
        String str = null;
        int bT = a.bT(parcel);
        int i = 0;
        String str2 = null;
        Long l = null;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    l = a.j(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    str2 = a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    str = a.p(parcel, bS);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new i(i, l, str2, str);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public i[] jY(int i) {
        return new i[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return jY(x0);
    }
}
