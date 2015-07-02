package com.google.android.gms.fitness.request;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.wallet.instrumentmanager.R;
import java.util.List;

public class s implements Creator<SessionReadRequest> {
    static void a(SessionReadRequest sessionReadRequest, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.a(parcel, 1, sessionReadRequest.getSessionName(), false);
        b.c(parcel, 1000, sessionReadRequest.getVersionCode());
        b.a(parcel, 2, sessionReadRequest.getSessionId(), false);
        b.a(parcel, 3, sessionReadRequest.lo());
        b.a(parcel, 4, sessionReadRequest.lp());
        b.d(parcel, 5, sessionReadRequest.getDataTypes(), false);
        b.d(parcel, 6, sessionReadRequest.getDataSources(), false);
        b.a(parcel, 7, sessionReadRequest.mc());
        b.a(parcel, 8, sessionReadRequest.lO());
        b.c(parcel, 9, sessionReadRequest.getExcludedPackages(), false);
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return el(x0);
    }

    public SessionReadRequest el(Parcel parcel) {
        int bT = a.bT(parcel);
        int i = 0;
        String str = null;
        String str2 = null;
        long j = 0;
        long j2 = 0;
        List list = null;
        List list2 = null;
        boolean z = false;
        boolean z2 = false;
        List list3 = null;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    str = a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    str2 = a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    j = a.i(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    j2 = a.i(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                    list = a.c(parcel, bS, DataType.CREATOR);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                    list2 = a.c(parcel, bS, DataSource.CREATOR);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                    z = a.c(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                    z2 = a.c(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoTextColor /*9*/:
                    list3 = a.E(parcel, bS);
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
            return new SessionReadRequest(i, str, str2, j, j2, list, list2, z, z2, list3);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public SessionReadRequest[] ga(int i) {
        return new SessionReadRequest[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return ga(x0);
    }
}
