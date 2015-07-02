package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class ac implements Creator<GetDriveIdFromUniqueIdentifierRequest> {
    static void a(GetDriveIdFromUniqueIdentifierRequest getDriveIdFromUniqueIdentifierRequest, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, getDriveIdFromUniqueIdentifierRequest.mVersionCode);
        b.a(parcel, 2, getDriveIdFromUniqueIdentifierRequest.aaM, false);
        b.a(parcel, 3, getDriveIdFromUniqueIdentifierRequest.aaN);
        b.J(parcel, bU);
    }

    public GetDriveIdFromUniqueIdentifierRequest cB(Parcel parcel) {
        boolean z = false;
        int bT = a.bT(parcel);
        String str = null;
        int i = 0;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    str = a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    z = a.c(parcel, bS);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new GetDriveIdFromUniqueIdentifierRequest(i, str, z);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return cB(x0);
    }

    public GetDriveIdFromUniqueIdentifierRequest[] ej(int i) {
        return new GetDriveIdFromUniqueIdentifierRequest[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return ej(x0);
    }
}
