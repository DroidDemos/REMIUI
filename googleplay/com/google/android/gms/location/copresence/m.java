package com.google.android.gms.location.copresence;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class m implements Creator<SubscribedMessage> {
    static void a(SubscribedMessage subscribedMessage, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, subscribedMessage.getVersionCode());
        b.a(parcel, 2, subscribedMessage.getMessage(), i, false);
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return fv(x0);
    }

    public SubscribedMessage fv(Parcel parcel) {
        int bT = a.bT(parcel);
        int i = 0;
        Message message = null;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    message = (Message) a.a(parcel, bS, Message.CREATOR);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new SubscribedMessage(i, message);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public SubscribedMessage[] hM(int i) {
        return new SubscribedMessage[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return hM(x0);
    }
}
