package com.google.android.gms.appdatasearch;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class d implements Creator<DocumentContents> {
    static void a(DocumentContents documentContents, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.a(parcel, 1, documentContents.CO, i, false);
        b.c(parcel, 1000, documentContents.mVersionCode);
        b.a(parcel, 2, documentContents.schemaOrgType, false);
        b.a(parcel, 3, documentContents.globalSearchEnabled);
        b.a(parcel, 4, documentContents.account, i, false);
        b.J(parcel, bU);
    }

    public DocumentContents[] I(int i) {
        return new DocumentContents[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return o(x0);
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return I(x0);
    }

    public DocumentContents o(Parcel parcel) {
        boolean z = false;
        Account account = null;
        int bT = a.bT(parcel);
        String str = null;
        DocumentSection[] documentSectionArr = null;
        int i = 0;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    documentSectionArr = (DocumentSection[]) a.b(parcel, bS, DocumentSection.CREATOR);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    str = a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    z = a.c(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    account = (Account) a.a(parcel, bS, Account.CREATOR);
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
            return new DocumentContents(i, documentSectionArr, str, z, account);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }
}
