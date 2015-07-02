package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.drive.Contents;
import com.google.android.wallet.instrumentmanager.R;

public class g implements Creator<CloseContentsRequest> {
    static void a(CloseContentsRequest closeContentsRequest, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, closeContentsRequest.mVersionCode);
        b.a(parcel, 2, closeContentsRequest.Ze, i, false);
        b.a(parcel, 3, closeContentsRequest.Zg, false);
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return ct(x0);
    }

    public CloseContentsRequest ct(Parcel parcel) {
        Boolean bool = null;
        int bT = a.bT(parcel);
        int i = 0;
        Contents contents = null;
        while (parcel.dataPosition() < bT) {
            Contents contents2;
            int g;
            Boolean bool2;
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    Boolean bool3 = bool;
                    contents2 = contents;
                    g = a.g(parcel, bS);
                    bool2 = bool3;
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    g = i;
                    Contents contents3 = (Contents) a.a(parcel, bS, Contents.CREATOR);
                    bool2 = bool;
                    contents2 = contents3;
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    bool2 = a.d(parcel, bS);
                    contents2 = contents;
                    g = i;
                    break;
                default:
                    a.b(parcel, bS);
                    bool2 = bool;
                    contents2 = contents;
                    g = i;
                    break;
            }
            i = g;
            contents = contents2;
            bool = bool2;
        }
        if (parcel.dataPosition() == bT) {
            return new CloseContentsRequest(i, contents, bool);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public CloseContentsRequest[] dW(int i) {
        return new CloseContentsRequest[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return dW(x0);
    }
}
