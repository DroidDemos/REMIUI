package com.google.android.gms.games.snapshot;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.drive.Contents;
import com.google.android.wallet.instrumentmanager.R;

public class SnapshotContentsEntityCreator implements Creator<SnapshotContentsEntity> {
    static void a(SnapshotContentsEntity snapshotContentsEntity, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.a(parcel, 1, snapshotContentsEntity.jC(), i, false);
        b.c(parcel, 1000, snapshotContentsEntity.getVersionCode());
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return eV(x0);
    }

    public SnapshotContentsEntity eV(Parcel parcel) {
        int bT = a.bT(parcel);
        int i = 0;
        Contents contents = null;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    contents = (Contents) a.a(parcel, bS, Contents.CREATOR);
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
            return new SnapshotContentsEntity(i, contents);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public SnapshotContentsEntity[] hc(int i) {
        return new SnapshotContentsEntity[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return hc(x0);
    }
}
