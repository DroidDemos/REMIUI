package com.google.android.gms.drive.realtime.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class a implements Creator<BeginCompoundOperationRequest> {
    static void a(BeginCompoundOperationRequest beginCompoundOperationRequest, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, beginCompoundOperationRequest.mVersionCode);
        b.a(parcel, 2, beginCompoundOperationRequest.adz);
        b.a(parcel, 3, beginCompoundOperationRequest.mName, false);
        b.a(parcel, 4, beginCompoundOperationRequest.adA);
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return dr(x0);
    }

    public BeginCompoundOperationRequest dr(Parcel parcel) {
        boolean z = false;
        int bT = com.google.android.gms.common.internal.safeparcel.a.bT(parcel);
        String str = null;
        boolean z2 = true;
        int i = 0;
        while (parcel.dataPosition() < bT) {
            int bS = com.google.android.gms.common.internal.safeparcel.a.bS(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i = com.google.android.gms.common.internal.safeparcel.a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    z = com.google.android.gms.common.internal.safeparcel.a.c(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    str = com.google.android.gms.common.internal.safeparcel.a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    z2 = com.google.android.gms.common.internal.safeparcel.a.c(parcel, bS);
                    break;
                default:
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new BeginCompoundOperationRequest(i, z, str, z2);
        }
        throw new com.google.android.gms.common.internal.safeparcel.a.a("Overread allowed size end=" + bT, parcel);
    }

    public BeginCompoundOperationRequest[] eZ(int i) {
        return new BeginCompoundOperationRequest[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return eZ(x0);
    }
}
