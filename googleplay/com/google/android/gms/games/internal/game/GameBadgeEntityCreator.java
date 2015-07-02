package com.google.android.gms.games.internal.game;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class GameBadgeEntityCreator implements Creator<GameBadgeEntity> {
    static void a(GameBadgeEntity gameBadgeEntity, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, gameBadgeEntity.getType());
        b.c(parcel, 1000, gameBadgeEntity.getVersionCode());
        b.a(parcel, 2, gameBadgeEntity.getTitle(), false);
        b.a(parcel, 3, gameBadgeEntity.getDescription(), false);
        b.a(parcel, 4, gameBadgeEntity.getIconImageUri(), i, false);
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return eN(x0);
    }

    public GameBadgeEntity eN(Parcel parcel) {
        int i = 0;
        Uri uri = null;
        int bT = a.bT(parcel);
        String str = null;
        String str2 = null;
        int i2 = 0;
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
                    uri = (Uri) a.a(parcel, bS, Uri.CREATOR);
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
            return new GameBadgeEntity(i2, i, str2, str, uri);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public GameBadgeEntity[] gP(int i) {
        return new GameBadgeEntity[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return gP(x0);
    }
}
