package com.google.android.gms.auth.firstparty.proximity.data;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.wallet.instrumentmanager.R;

public class b implements Creator<AuthorizationRequest> {
    static void a(AuthorizationRequest authorizationRequest, Parcel parcel, int i) {
        int bU = com.google.android.gms.common.internal.safeparcel.b.bU(parcel);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1, authorizationRequest.Gf);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 2, authorizationRequest.mPermitId, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 3, authorizationRequest.mPermitAccessId, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 4, authorizationRequest.mData, false);
        com.google.android.gms.common.internal.safeparcel.b.J(parcel, bU);
    }

    public AuthorizationRequest aW(Parcel parcel) {
        byte[] bArr = null;
        int bT = a.bT(parcel);
        int i = 0;
        String str = null;
        String str2 = null;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    str2 = a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    str = a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    bArr = a.s(parcel, bS);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new AuthorizationRequest(i, str2, str, bArr);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public AuthorizationRequest[] bA(int i) {
        return new AuthorizationRequest[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return aW(x0);
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return bA(x0);
    }
}
