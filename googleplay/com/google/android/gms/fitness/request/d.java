package com.google.android.gms.fitness.request;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.data.Session;
import com.google.android.wallet.instrumentmanager.R;
import java.util.List;

public class d implements Creator<DataDeleteRequest> {
    static void a(DataDeleteRequest dataDeleteRequest, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.a(parcel, 1, dataDeleteRequest.lo());
        b.c(parcel, 1000, dataDeleteRequest.getVersionCode());
        b.a(parcel, 2, dataDeleteRequest.lp());
        b.d(parcel, 3, dataDeleteRequest.getDataSources(), false);
        b.d(parcel, 4, dataDeleteRequest.getDataTypes(), false);
        b.d(parcel, 5, dataDeleteRequest.getSessions(), false);
        b.a(parcel, 6, dataDeleteRequest.lK());
        b.a(parcel, 7, dataDeleteRequest.lL());
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return eb(x0);
    }

    public DataDeleteRequest eb(Parcel parcel) {
        long j = 0;
        List list = null;
        boolean z = false;
        int bT = a.bT(parcel);
        boolean z2 = false;
        List list2 = null;
        List list3 = null;
        long j2 = 0;
        int i = 0;
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
                    list3 = a.c(parcel, bS, DataSource.CREATOR);
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    list2 = a.c(parcel, bS, DataType.CREATOR);
                    break;
                case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                    list = a.c(parcel, bS, Session.CREATOR);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                    z2 = a.c(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                    z = a.c(parcel, bS);
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
            return new DataDeleteRequest(i, j2, j, list3, list2, list, z2, z);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public DataDeleteRequest[] fP(int i) {
        return new DataDeleteRequest[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return fP(x0);
    }
}
