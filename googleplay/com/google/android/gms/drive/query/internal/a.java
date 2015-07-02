package com.google.android.gms.drive.query.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;
import com.google.android.wallet.instrumentmanager.R;

public class a implements Creator<ComparisonFilter> {
    static void a(ComparisonFilter comparisonFilter, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1000, comparisonFilter.mVersionCode);
        b.a(parcel, 1, comparisonFilter.acp, i, false);
        b.a(parcel, 2, comparisonFilter.acq, i, false);
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return dh(x0);
    }

    public ComparisonFilter dh(Parcel parcel) {
        MetadataBundle metadataBundle = null;
        int bT = com.google.android.gms.common.internal.safeparcel.a.bT(parcel);
        int i = 0;
        Operator operator = null;
        while (parcel.dataPosition() < bT) {
            int i2;
            MetadataBundle metadataBundle2;
            Operator operator2;
            int bS = com.google.android.gms.common.internal.safeparcel.a.bS(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i2 = i;
                    Operator operator3 = (Operator) com.google.android.gms.common.internal.safeparcel.a.a(parcel, bS, Operator.CREATOR);
                    metadataBundle2 = metadataBundle;
                    operator2 = operator3;
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    metadataBundle2 = (MetadataBundle) com.google.android.gms.common.internal.safeparcel.a.a(parcel, bS, MetadataBundle.CREATOR);
                    operator2 = operator;
                    i2 = i;
                    break;
                case 1000:
                    MetadataBundle metadataBundle3 = metadataBundle;
                    operator2 = operator;
                    i2 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, bS);
                    metadataBundle2 = metadataBundle3;
                    break;
                default:
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, bS);
                    metadataBundle2 = metadataBundle;
                    operator2 = operator;
                    i2 = i;
                    break;
            }
            i = i2;
            operator = operator2;
            metadataBundle = metadataBundle2;
        }
        if (parcel.dataPosition() == bT) {
            return new ComparisonFilter(i, operator, metadataBundle);
        }
        throw new com.google.android.gms.common.internal.safeparcel.a.a("Overread allowed size end=" + bT, parcel);
    }

    public ComparisonFilter[] eP(int i) {
        return new ComparisonFilter[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return eP(x0);
    }
}
