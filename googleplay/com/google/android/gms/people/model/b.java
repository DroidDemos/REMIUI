package com.google.android.gms.people.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.wallet.instrumentmanager.R;

public class b implements Creator<AvatarReference> {
    static void a(AvatarReference avatarReference, Parcel parcel, int i) {
        int bU = com.google.android.gms.common.internal.safeparcel.b.bU(parcel);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1, avatarReference.getSource());
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1000, avatarReference.getVersionCode());
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 2, avatarReference.getLocation(), false);
        com.google.android.gms.common.internal.safeparcel.b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return gL(x0);
    }

    public AvatarReference gL(Parcel parcel) {
        int i = 0;
        int bT = a.bT(parcel);
        String str = null;
        int i2 = 0;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    str = a.p(parcel, bS);
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
            return new AvatarReference(i2, i, str);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public AvatarReference[] jq(int i) {
        return new AvatarReference[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return jq(x0);
    }
}
