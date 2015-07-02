package com.google.android.gms.people.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class a implements Creator<AccountMetadata> {
    static void a(AccountMetadata accountMetadata, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, accountMetadata.mVersionCode);
        b.a(parcel, 2, accountMetadata.isPlusEnabled);
        b.a(parcel, 3, accountMetadata.isSyncEnabled);
        b.a(parcel, 4, accountMetadata.isPagePeriodicSyncEnabled);
        b.a(parcel, 5, accountMetadata.isPageTickleSyncEnabled);
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return gK(x0);
    }

    public AccountMetadata gK(Parcel parcel) {
        boolean z = false;
        int bT = com.google.android.gms.common.internal.safeparcel.a.bT(parcel);
        boolean z2 = false;
        boolean z3 = false;
        boolean z4 = false;
        int i = 0;
        while (parcel.dataPosition() < bT) {
            int bS = com.google.android.gms.common.internal.safeparcel.a.bS(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i = com.google.android.gms.common.internal.safeparcel.a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    z4 = com.google.android.gms.common.internal.safeparcel.a.c(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    z3 = com.google.android.gms.common.internal.safeparcel.a.c(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    z2 = com.google.android.gms.common.internal.safeparcel.a.c(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                    z = com.google.android.gms.common.internal.safeparcel.a.c(parcel, bS);
                    break;
                default:
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new AccountMetadata(i, z4, z3, z2, z);
        }
        throw new com.google.android.gms.common.internal.safeparcel.a.a("Overread allowed size end=" + bT, parcel);
    }

    public AccountMetadata[] jp(int i) {
        return new AccountMetadata[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return jp(x0);
    }
}
