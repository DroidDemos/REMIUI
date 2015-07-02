package com.google.android.gms.games.internal.multiplayer;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.games.multiplayer.InvitationEntity;
import com.google.android.wallet.instrumentmanager.R;
import java.util.ArrayList;

public class InvitationClusterCreator implements Creator<ZInvitationCluster> {
    static void a(ZInvitationCluster zInvitationCluster, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.d(parcel, 1, zInvitationCluster.of(), false);
        b.c(parcel, 1000, zInvitationCluster.getVersionCode());
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return eO(x0);
    }

    public ZInvitationCluster eO(Parcel parcel) {
        int bT = a.bT(parcel);
        int i = 0;
        ArrayList arrayList = null;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    arrayList = a.c(parcel, bS, InvitationEntity.CREATOR);
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
            return new ZInvitationCluster(i, arrayList);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public ZInvitationCluster[] gS(int i) {
        return new ZInvitationCluster[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return gS(x0);
    }
}
