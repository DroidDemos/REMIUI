package com.google.android.gms.drive.realtime.internal.event;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class e implements Creator<ReferenceShiftedDetails> {
    static void a(ReferenceShiftedDetails referenceShiftedDetails, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, referenceShiftedDetails.mVersionCode);
        b.a(parcel, 2, referenceShiftedDetails.ado, false);
        b.a(parcel, 3, referenceShiftedDetails.adn, false);
        b.c(parcel, 4, referenceShiftedDetails.adq);
        b.c(parcel, 5, referenceShiftedDetails.adp);
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return dy(x0);
    }

    public ReferenceShiftedDetails dy(Parcel parcel) {
        String str = null;
        int i = 0;
        int bT = a.bT(parcel);
        int i2 = 0;
        String str2 = null;
        int i3 = 0;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i3 = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    str2 = a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    str = a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    i2 = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                    i = a.g(parcel, bS);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new ReferenceShiftedDetails(i3, str2, str, i2, i);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public ReferenceShiftedDetails[] fk(int i) {
        return new ReferenceShiftedDetails[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return fk(x0);
    }
}
