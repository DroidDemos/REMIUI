package com.google.android.gms.games.internal.request;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.games.request.GameRequestEntity;
import com.google.android.wallet.instrumentmanager.R;
import java.util.ArrayList;

public class GameRequestClusterCreator implements Creator<GameRequestCluster> {
    static void a(GameRequestCluster gameRequestCluster, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.d(parcel, 1, gameRequestCluster.os(), false);
        b.c(parcel, 1000, gameRequestCluster.getVersionCode());
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return eQ(x0);
    }

    public GameRequestCluster eQ(Parcel parcel) {
        int bT = a.bT(parcel);
        int i = 0;
        ArrayList arrayList = null;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    arrayList = a.c(parcel, bS, GameRequestEntity.CREATOR);
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
            return new GameRequestCluster(i, arrayList);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public GameRequestCluster[] gV(int i) {
        return new GameRequestCluster[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return gV(x0);
    }
}
