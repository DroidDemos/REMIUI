package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.location.copresence.AccessPolicy;
import com.google.android.gms.location.copresence.Message;
import com.google.android.wallet.instrumentmanager.R;

public class om implements Creator<ol> {
    static void a(ol olVar, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, olVar.getVersionCode());
        b.a(parcel, 2, olVar.getId(), false);
        b.a(parcel, 3, olVar.pj(), i, false);
        b.a(parcel, 4, olVar.getMessage(), i, false);
        b.a(parcel, 5, olVar.pk(), i, false);
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return fC(x0);
    }

    public ol fC(Parcel parcel) {
        AccessPolicy accessPolicy = null;
        int bT = a.bT(parcel);
        int i = 0;
        Message message = null;
        on onVar = null;
        String str = null;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    str = a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    onVar = (on) a.a(parcel, bS, on.CREATOR);
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    message = (Message) a.a(parcel, bS, Message.CREATOR);
                    break;
                case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                    accessPolicy = (AccessPolicy) a.a(parcel, bS, AccessPolicy.CREATOR);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new ol(i, str, onVar, message, accessPolicy);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public ol[] hT(int i) {
        return new ol[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return hT(x0);
    }
}
