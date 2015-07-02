package com.google.android.gms.appdatasearch;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class aj implements Creator<UsageInfo> {
    static void a(UsageInfo usageInfo, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.a(parcel, 1, usageInfo.EF, i, false);
        b.c(parcel, 1000, usageInfo.mVersionCode);
        b.a(parcel, 2, usageInfo.EG);
        b.c(parcel, 3, usageInfo.EH);
        b.a(parcel, 4, usageInfo.query, false);
        b.a(parcel, 5, usageInfo.EI, i, false);
        b.J(parcel, bU);
    }

    public UsageInfo S(Parcel parcel) {
        int i = 0;
        DocumentContents documentContents = null;
        int bT = a.bT(parcel);
        long j = 0;
        String str = null;
        DocumentId documentId = null;
        int i2 = 0;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    documentId = (DocumentId) a.a(parcel, bS, DocumentId.CREATOR);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    j = a.i(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    i = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    str = a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                    documentContents = (DocumentContents) a.a(parcel, bS, DocumentContents.CREATOR);
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
            return new UsageInfo(i2, documentId, j, i, str, documentContents);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public UsageInfo[] aq(int i) {
        return new UsageInfo[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return S(x0);
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return aq(x0);
    }
}
