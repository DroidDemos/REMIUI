package com.google.android.gms.drive.query.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;
import com.google.android.wallet.instrumentmanager.R;

public class h implements Creator<InFilter> {
    static void a(InFilter inFilter, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1000, inFilter.mVersionCode);
        b.a(parcel, 1, inFilter.acq, i, false);
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return dm(x0);
    }

    public InFilter dm(Parcel parcel) {
        int bT = a.bT(parcel);
        int i = 0;
        MetadataBundle metadataBundle = null;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    metadataBundle = (MetadataBundle) a.a(parcel, bS, MetadataBundle.CREATOR);
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
            return new InFilter(i, metadataBundle);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public InFilter[] eU(int i) {
        return new InFilter[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return eU(x0);
    }
}
