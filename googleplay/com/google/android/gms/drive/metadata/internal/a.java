package com.google.android.gms.drive.metadata.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;
import java.util.Collection;

public class a implements Creator<AppVisibleCustomProperties> {
    static void a(AppVisibleCustomProperties appVisibleCustomProperties, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, appVisibleCustomProperties.mVersionCode);
        b.d(parcel, 2, appVisibleCustomProperties.abm, false);
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return dc(x0);
    }

    public AppVisibleCustomProperties dc(Parcel parcel) {
        int bT = com.google.android.gms.common.internal.safeparcel.a.bT(parcel);
        int i = 0;
        Collection collection = null;
        while (parcel.dataPosition() < bT) {
            int bS = com.google.android.gms.common.internal.safeparcel.a.bS(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i = com.google.android.gms.common.internal.safeparcel.a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    collection = com.google.android.gms.common.internal.safeparcel.a.c(parcel, bS, CustomProperty.CREATOR);
                    break;
                default:
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new AppVisibleCustomProperties(i, collection);
        }
        throw new com.google.android.gms.common.internal.safeparcel.a.a("Overread allowed size end=" + bT, parcel);
    }

    public AppVisibleCustomProperties[] eK(int i) {
        return new AppVisibleCustomProperties[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return eK(x0);
    }
}
