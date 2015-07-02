package com.google.android.gms.photos.autobackup.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class f implements Creator<UserQuota> {
    static void a(UserQuota userQuota, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, userQuota.mVersionCode);
        b.a(parcel, 2, userQuota.getQuotaLimit());
        b.a(parcel, 3, userQuota.getQuotaUsed());
        b.a(parcel, 4, userQuota.isQuotaUnlimited());
        b.a(parcel, 5, userQuota.areFullResUploadsDisabled());
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return gR(x0);
    }

    public UserQuota gR(Parcel parcel) {
        long j = 0;
        boolean z = false;
        int bT = a.bT(parcel);
        boolean z2 = false;
        long j2 = 0;
        int i = 0;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    j2 = a.i(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    j = a.i(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    z2 = a.c(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                    z = a.c(parcel, bS);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new UserQuota(i, j2, j, z2, z);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public UserQuota[] jA(int i) {
        return new UserQuota[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return jA(x0);
    }
}
