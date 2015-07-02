package com.google.android.gms.games;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class PlayerLevelInfoCreator implements Creator<PlayerLevelInfo> {
    static void a(PlayerLevelInfo playerLevelInfo, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.a(parcel, 1, playerLevelInfo.getCurrentXpTotal());
        b.c(parcel, 1000, playerLevelInfo.getVersionCode());
        b.a(parcel, 2, playerLevelInfo.getLastLevelUpTimestamp());
        b.a(parcel, 3, playerLevelInfo.getCurrentLevel(), i, false);
        b.a(parcel, 4, playerLevelInfo.getNextLevel(), i, false);
        b.J(parcel, bU);
    }

    public PlayerLevelInfo createFromParcel(Parcel parcel) {
        long j = 0;
        PlayerLevel playerLevel = null;
        int bT = a.bT(parcel);
        int i = 0;
        PlayerLevel playerLevel2 = null;
        long j2 = 0;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    j2 = a.i(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    j = a.i(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    playerLevel2 = (PlayerLevel) a.a(parcel, bS, PlayerLevel.CREATOR);
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    playerLevel = (PlayerLevel) a.a(parcel, bS, PlayerLevel.CREATOR);
                    break;
                case 1000:
                    i = a.g(parcel, bS);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new PlayerLevelInfo(i, j2, j, playerLevel2, playerLevel);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public PlayerLevelInfo[] newArray(int size) {
        return new PlayerLevelInfo[size];
    }
}
