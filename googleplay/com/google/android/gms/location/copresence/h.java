package com.google.android.gms.location.copresence;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class h implements Creator<Message> {
    static void a(Message message, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, message.getVersionCode());
        b.a(parcel, 2, message.getNamespace(), false);
        b.a(parcel, 3, message.getType(), false);
        b.a(parcel, 4, message.getPayload(), false);
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return fq(x0);
    }

    public Message fq(Parcel parcel) {
        byte[] bArr = null;
        int bT = a.bT(parcel);
        int i = 0;
        String str = null;
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
                    bArr = a.s(parcel, bS);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new Message(i, str2, str, bArr);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public Message[] hH(int i) {
        return new Message[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return hH(x0);
    }
}
