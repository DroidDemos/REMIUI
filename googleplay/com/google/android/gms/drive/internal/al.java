package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.drive.Contents;
import com.google.android.wallet.instrumentmanager.R;

public class al implements Creator<OnContentsResponse> {
    static void a(OnContentsResponse onContentsResponse, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, onContentsResponse.mVersionCode);
        b.a(parcel, 2, onContentsResponse.ZL, i, false);
        b.a(parcel, 3, onContentsResponse.aaT);
        b.J(parcel, bU);
    }

    public OnContentsResponse cF(Parcel parcel) {
        boolean z = false;
        int bT = a.bT(parcel);
        Contents contents = null;
        int i = 0;
        while (parcel.dataPosition() < bT) {
            Contents contents2;
            int g;
            boolean z2;
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    boolean z3 = z;
                    contents2 = contents;
                    g = a.g(parcel, bS);
                    z2 = z3;
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    g = i;
                    Contents contents3 = (Contents) a.a(parcel, bS, Contents.CREATOR);
                    z2 = z;
                    contents2 = contents3;
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    z2 = a.c(parcel, bS);
                    contents2 = contents;
                    g = i;
                    break;
                default:
                    a.b(parcel, bS);
                    z2 = z;
                    contents2 = contents;
                    g = i;
                    break;
            }
            i = g;
            contents = contents2;
            z = z2;
        }
        if (parcel.dataPosition() == bT) {
            return new OnContentsResponse(i, contents, z);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return cF(x0);
    }

    public OnContentsResponse[] en(int i) {
        return new OnContentsResponse[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return en(x0);
    }
}
