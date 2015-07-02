package com.google.android.gms.appdatasearch;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;
import java.util.List;

public class w implements Creator<QuerySpecification> {
    static void a(QuerySpecification querySpecification, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.a(parcel, 1, querySpecification.wantUris);
        b.c(parcel, 1000, querySpecification.mVersionCode);
        b.c(parcel, 2, querySpecification.wantedTags, false);
        b.d(parcel, 3, querySpecification.wantedSections, false);
        b.a(parcel, 4, querySpecification.prefixMatch);
        b.c(parcel, 5, querySpecification.debugLevel);
        b.c(parcel, 6, querySpecification.rankingStrategy);
        b.a(parcel, 7, querySpecification.semanticSectionNames);
        b.c(parcel, 8, querySpecification.queryTokenizer);
        b.J(parcel, bU);
    }

    public QuerySpecification G(Parcel parcel) {
        List list = null;
        int i = 0;
        int bT = a.bT(parcel);
        boolean z = false;
        int i2 = 0;
        int i3 = 0;
        boolean z2 = false;
        List list2 = null;
        boolean z3 = false;
        int i4 = 0;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    z3 = a.c(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    list2 = a.E(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    list = a.c(parcel, bS, Section.CREATOR);
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    z2 = a.c(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                    i3 = a.g(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                    i2 = a.g(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                    z = a.c(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                    i = a.g(parcel, bS);
                    break;
                case 1000:
                    i4 = a.g(parcel, bS);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new QuerySpecification(i4, z3, list2, list, z2, i3, i2, z, i);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public QuerySpecification[] aa(int i) {
        return new QuerySpecification[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return G(x0);
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return aa(x0);
    }
}
