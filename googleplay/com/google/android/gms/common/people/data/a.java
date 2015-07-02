package com.google.android.gms.common.people.data;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;
import java.util.List;

public class a implements Creator<Audience> {
    static void a(Audience audience, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.d(parcel, 1, audience.getAudienceMemberList(), false);
        b.c(parcel, 1000, audience.getVersionCode());
        b.c(parcel, 2, audience.getDomainRestricted());
        b.a(parcel, 3, audience.iY());
        b.a(parcel, 4, audience.isReadOnly());
        b.J(parcel, bU);
    }

    public Audience bV(Parcel parcel) {
        boolean z = false;
        int bT = com.google.android.gms.common.internal.safeparcel.a.bT(parcel);
        List list = null;
        boolean z2 = false;
        int i = 0;
        int i2 = 0;
        while (parcel.dataPosition() < bT) {
            int bS = com.google.android.gms.common.internal.safeparcel.a.bS(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    list = com.google.android.gms.common.internal.safeparcel.a.c(parcel, bS, AudienceMember.CREATOR);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    i = com.google.android.gms.common.internal.safeparcel.a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    z2 = com.google.android.gms.common.internal.safeparcel.a.c(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    z = com.google.android.gms.common.internal.safeparcel.a.c(parcel, bS);
                    break;
                case 1000:
                    i2 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, bS);
                    break;
                default:
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new Audience(i2, list, i, z2, z);
        }
        throw new com.google.android.gms.common.internal.safeparcel.a.a("Overread allowed size end=" + bT, parcel);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return bV(x0);
    }

    public Audience[] dn(int i) {
        return new Audience[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return dn(x0);
    }
}
