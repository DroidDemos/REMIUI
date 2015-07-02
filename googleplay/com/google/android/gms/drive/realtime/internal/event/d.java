package com.google.android.gms.drive.realtime.internal.event;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;
import java.util.List;

public class d implements Creator<ParcelableEventList> {
    static void a(ParcelableEventList parcelableEventList, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, parcelableEventList.mVersionCode);
        b.d(parcel, 2, parcelableEventList.ms, false);
        b.a(parcel, 3, parcelableEventList.aeW, i, false);
        b.a(parcel, 4, parcelableEventList.aeX);
        b.c(parcel, 5, parcelableEventList.aeY, false);
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return dx(x0);
    }

    public ParcelableEventList dx(Parcel parcel) {
        boolean z = false;
        List list = null;
        int bT = a.bT(parcel);
        DataHolder dataHolder = null;
        List list2 = null;
        int i = 0;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    list2 = a.c(parcel, bS, ParcelableEvent.CREATOR);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    dataHolder = (DataHolder) a.a(parcel, bS, DataHolder.CREATOR);
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    z = a.c(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                    list = a.E(parcel, bS);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new ParcelableEventList(i, list2, dataHolder, z, list);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public ParcelableEventList[] fj(int i) {
        return new ParcelableEventList[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return fj(x0);
    }
}
