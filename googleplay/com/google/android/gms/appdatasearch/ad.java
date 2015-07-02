package com.google.android.gms.appdatasearch;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class ad implements Creator<ac> {
    static void a(ac acVar, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.a(parcel, 1, acVar.xM, false);
        b.c(parcel, 1000, acVar.mVersionCode);
        b.a(parcel, 2, acVar.Ed, i, false);
        b.c(parcel, 3, acVar.Ee);
        b.J(parcel, bU);
    }

    public ac M(Parcel parcel) {
        DocumentId[] documentIdArr = null;
        int i = 0;
        int bT = a.bT(parcel);
        String str = null;
        int i2 = 0;
        while (parcel.dataPosition() < bT) {
            int i3;
            String p;
            DocumentId[] documentIdArr2;
            int bS = a.bS(parcel);
            int i4;
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i3 = i2;
                    DocumentId[] documentIdArr3 = documentIdArr;
                    p = a.p(parcel, bS);
                    bS = i;
                    documentIdArr2 = documentIdArr3;
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    p = str;
                    i3 = i2;
                    i4 = i;
                    documentIdArr2 = (DocumentId[]) a.b(parcel, bS, DocumentId.CREATOR);
                    bS = i4;
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    bS = a.g(parcel, bS);
                    documentIdArr2 = documentIdArr;
                    p = str;
                    i3 = i2;
                    break;
                case 1000:
                    i4 = i;
                    documentIdArr2 = documentIdArr;
                    p = str;
                    i3 = a.g(parcel, bS);
                    bS = i4;
                    break;
                default:
                    a.b(parcel, bS);
                    bS = i;
                    documentIdArr2 = documentIdArr;
                    p = str;
                    i3 = i2;
                    break;
            }
            i2 = i3;
            str = p;
            documentIdArr = documentIdArr2;
            i = bS;
        }
        if (parcel.dataPosition() == bT) {
            return new ac(i2, str, documentIdArr, i);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public ac[] ag(int i) {
        return new ac[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return M(x0);
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return ag(x0);
    }
}
