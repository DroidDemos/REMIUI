package com.google.android.gms.location.copresence;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class d implements Creator<AclResourceId> {
    static void a(AclResourceId aclResourceId, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, aclResourceId.getVersionCode());
        b.a(parcel, 2, aclResourceId.getApplication(), false);
        b.a(parcel, 3, aclResourceId.getId(), false);
        b.a(parcel, 4, aclResourceId.getPart(), false);
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return fo(x0);
    }

    public AclResourceId fo(Parcel parcel) {
        String str = null;
        int bT = a.bT(parcel);
        int i = 0;
        String str2 = null;
        String str3 = null;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    str3 = a.p(parcel, bS);
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
            return new AclResourceId(i, str3, str2, str);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public AclResourceId[] hF(int i) {
        return new AclResourceId[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return hF(x0);
    }
}
