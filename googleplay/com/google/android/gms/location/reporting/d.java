package com.google.android.gms.location.reporting;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class d implements Creator<ReportingState> {
    static void a(ReportingState reportingState, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, reportingState.getVersionCode());
        b.c(parcel, 2, reportingState.getReportingEnabled());
        b.c(parcel, 3, reportingState.getHistoryEnabled());
        b.a(parcel, 4, reportingState.isAllowed());
        b.a(parcel, 5, reportingState.isActive());
        b.a(parcel, 6, reportingState.isDeferringToMaps());
        b.c(parcel, 7, reportingState.getExpectedOptInResult());
        b.a(parcel, 8, reportingState.pJ(), false);
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return gd(x0);
    }

    public ReportingState gd(Parcel parcel) {
        int i = 0;
        int bT = a.bT(parcel);
        Integer num = null;
        boolean z = false;
        boolean z2 = false;
        boolean z3 = false;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i4 = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    i3 = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    i2 = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    z3 = a.c(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                    z2 = a.c(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                    z = a.c(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                    i = a.g(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                    num = a.h(parcel, bS);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new ReportingState(i4, i3, i2, z3, z2, z, i, num);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public ReportingState[] iy(int i) {
        return new ReportingState[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return iy(x0);
    }
}
