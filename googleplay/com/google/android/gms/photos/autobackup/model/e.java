package com.google.android.gms.photos.autobackup.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class e implements Creator<SyncSettings> {
    static void a(SyncSettings syncSettings, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, syncSettings.mVersionCode);
        b.a(parcel, 2, syncSettings.isMasterSyncEnabled());
        b.a(parcel, 3, syncSettings.isAccountSyncEnabled());
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return gQ(x0);
    }

    public SyncSettings gQ(Parcel parcel) {
        boolean z = false;
        int bT = a.bT(parcel);
        boolean z2 = false;
        int i = 0;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    z2 = a.c(parcel, bS);
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
            return new SyncSettings(i, z2, z);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public SyncSettings[] jz(int i) {
        return new SyncSettings[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return jz(x0);
    }
}
