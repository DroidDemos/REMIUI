package com.google.android.gms.usagereporting;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class a implements Creator<UsageReportingOptInOptions> {
    static void a(UsageReportingOptInOptions usageReportingOptInOptions, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, usageReportingOptInOptions.mVersionCode);
        b.c(parcel, 2, usageReportingOptInOptions.aSR);
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return hV(x0);
    }

    public UsageReportingOptInOptions hV(Parcel parcel) {
        int i = 0;
        int bT = com.google.android.gms.common.internal.safeparcel.a.bT(parcel);
        int i2 = 0;
        while (parcel.dataPosition() < bT) {
            int bS = com.google.android.gms.common.internal.safeparcel.a.bS(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i2 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    i = com.google.android.gms.common.internal.safeparcel.a.g(parcel, bS);
                    break;
                default:
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new UsageReportingOptInOptions(i2, i);
        }
        throw new com.google.android.gms.common.internal.safeparcel.a.a("Overread allowed size end=" + bT, parcel);
    }

    public UsageReportingOptInOptions[] kQ(int i) {
        return new UsageReportingOptInOptions[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return kQ(x0);
    }
}
