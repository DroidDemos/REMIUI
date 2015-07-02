package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.drive.DriveId;
import com.google.android.wallet.instrumentmanager.R;

public class b implements Creator<AuthorizeAccessRequest> {
    static void a(AuthorizeAccessRequest authorizeAccessRequest, Parcel parcel, int i) {
        int bU = com.google.android.gms.common.internal.safeparcel.b.bU(parcel);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1, authorizeAccessRequest.mVersionCode);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 2, authorizeAccessRequest.Za);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 3, authorizeAccessRequest.Yb, i, false);
        com.google.android.gms.common.internal.safeparcel.b.J(parcel, bU);
    }

    public AuthorizeAccessRequest cp(Parcel parcel) {
        int bT = a.bT(parcel);
        int i = 0;
        long j = 0;
        DriveId driveId = null;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    j = a.i(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    driveId = (DriveId) a.a(parcel, bS, DriveId.CREATOR);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new AuthorizeAccessRequest(i, j, driveId);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return cp(x0);
    }

    public AuthorizeAccessRequest[] dS(int i) {
        return new AuthorizeAccessRequest[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return dS(x0);
    }
}
