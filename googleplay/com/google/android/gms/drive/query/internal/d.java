package com.google.android.gms.drive.query.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class d implements Creator<FilterHolder> {
    static void a(FilterHolder filterHolder, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.a(parcel, 1, filterHolder.act, i, false);
        b.c(parcel, 1000, filterHolder.mVersionCode);
        b.a(parcel, 2, filterHolder.acu, i, false);
        b.a(parcel, 3, filterHolder.acv, i, false);
        b.a(parcel, 4, filterHolder.acw, i, false);
        b.a(parcel, 5, filterHolder.acx, i, false);
        b.a(parcel, 6, filterHolder.acy, i, false);
        b.a(parcel, 7, filterHolder.acz, i, false);
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return dk(x0);
    }

    public FilterHolder dk(Parcel parcel) {
        HasFilter hasFilter = null;
        int bT = a.bT(parcel);
        int i = 0;
        MatchAllFilter matchAllFilter = null;
        InFilter inFilter = null;
        NotFilter notFilter = null;
        LogicalFilter logicalFilter = null;
        FieldOnlyFilter fieldOnlyFilter = null;
        ComparisonFilter comparisonFilter = null;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    comparisonFilter = (ComparisonFilter) a.a(parcel, bS, ComparisonFilter.CREATOR);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    fieldOnlyFilter = (FieldOnlyFilter) a.a(parcel, bS, FieldOnlyFilter.CREATOR);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    logicalFilter = (LogicalFilter) a.a(parcel, bS, LogicalFilter.CREATOR);
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    notFilter = (NotFilter) a.a(parcel, bS, NotFilter.CREATOR);
                    break;
                case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                    inFilter = (InFilter) a.a(parcel, bS, InFilter.CREATOR);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                    matchAllFilter = (MatchAllFilter) a.a(parcel, bS, MatchAllFilter.CREATOR);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                    hasFilter = (HasFilter) a.a(parcel, bS, HasFilter.CREATOR);
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
            return new FilterHolder(i, comparisonFilter, fieldOnlyFilter, logicalFilter, notFilter, inFilter, matchAllFilter, hasFilter);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public FilterHolder[] eS(int i) {
        return new FilterHolder[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return eS(x0);
    }
}
