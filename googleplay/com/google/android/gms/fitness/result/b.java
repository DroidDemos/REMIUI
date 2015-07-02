package com.google.android.gms.fitness.result;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.wallet.instrumentmanager.R;
import java.util.ArrayList;
import java.util.List;

public class b implements Creator<DataReadResult> {
    static void a(DataReadResult dataReadResult, Parcel parcel, int i) {
        int bU = com.google.android.gms.common.internal.safeparcel.b.bU(parcel);
        com.google.android.gms.common.internal.safeparcel.b.f(parcel, 1, dataReadResult.mi(), false);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1000, dataReadResult.getVersionCode());
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 2, dataReadResult.getStatus(), i, false);
        com.google.android.gms.common.internal.safeparcel.b.f(parcel, 3, dataReadResult.mh(), false);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 5, dataReadResult.mg());
        com.google.android.gms.common.internal.safeparcel.b.d(parcel, 6, dataReadResult.lt(), false);
        com.google.android.gms.common.internal.safeparcel.b.d(parcel, 7, dataReadResult.mj(), false);
        com.google.android.gms.common.internal.safeparcel.b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return ew(x0);
    }

    public DataReadResult ew(Parcel parcel) {
        int i = 0;
        List list = null;
        int bT = a.bT(parcel);
        List arrayList = new ArrayList();
        List arrayList2 = new ArrayList();
        List list2 = null;
        Status status = null;
        int i2 = 0;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    a.a(parcel, bS, arrayList, getClass().getClassLoader());
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    status = (Status) a.a(parcel, bS, Status.CREATOR);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    a.a(parcel, bS, arrayList2, getClass().getClassLoader());
                    break;
                case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                    i = a.g(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                    list2 = a.c(parcel, bS, DataSource.CREATOR);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                    list = a.c(parcel, bS, DataType.CREATOR);
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
            return new DataReadResult(i2, arrayList, status, arrayList2, i, list2, list);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public DataReadResult[] gl(int i) {
        return new DataReadResult[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return gl(x0);
    }
}
