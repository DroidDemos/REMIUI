package com.google.android.gms.wallet.wobs;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;
import java.util.ArrayList;

public class c implements Creator<LabelValueRow> {
    static void a(LabelValueRow labelValueRow, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, labelValueRow.getVersionCode());
        b.a(parcel, 2, labelValueRow.aVL, false);
        b.a(parcel, 3, labelValueRow.aVM, false);
        b.d(parcel, 4, labelValueRow.aVN, false);
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return iw(x0);
    }

    public LabelValueRow iw(Parcel parcel) {
        String str = null;
        int bT = a.bT(parcel);
        int i = 0;
        ArrayList ji = com.google.android.gms.common.util.a.ji();
        String str2 = null;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    str2 = a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    str = a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    ji = a.c(parcel, bS, LabelValue.CREATOR);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new LabelValueRow(i, str2, str, ji);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public LabelValueRow[] lw(int i) {
        return new LabelValueRow[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return lw(x0);
    }
}
