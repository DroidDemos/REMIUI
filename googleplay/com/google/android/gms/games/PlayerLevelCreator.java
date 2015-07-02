package com.google.android.gms.games;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class PlayerLevelCreator implements Creator<PlayerLevel> {
    static void a(PlayerLevel playerLevel, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, playerLevel.getLevelNumber());
        b.c(parcel, 1000, playerLevel.getVersionCode());
        b.a(parcel, 2, playerLevel.getMinXp());
        b.a(parcel, 3, playerLevel.getMaxXp());
        b.J(parcel, bU);
    }

    public PlayerLevel createFromParcel(Parcel parcel) {
        long j = 0;
        int i = 0;
        int bT = a.bT(parcel);
        long j2 = 0;
        int i2 = 0;
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
                case 1000:
                    i2 = a.g(parcel, bS);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new PlayerLevel(i2, i, j2, j);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public PlayerLevel[] newArray(int size) {
        return new PlayerLevel[size];
    }
}
