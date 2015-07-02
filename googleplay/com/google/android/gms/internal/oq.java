package com.google.android.gms.internal;

import android.app.PendingIntent;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.location.copresence.MessageFilter;
import com.google.android.wallet.instrumentmanager.R;

public class oq implements Creator<op> {
    static void a(op opVar, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, opVar.getVersionCode());
        b.c(parcel, 2, opVar.type);
        b.a(parcel, 3, opVar.avd, i, false);
        b.a(parcel, 4, opVar.ave, i, false);
        b.a(parcel, 5, opVar.pg(), false);
        b.a(parcel, 6, opVar.avf, i, false);
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return fE(x0);
    }

    public op fE(Parcel parcel) {
        int i = 0;
        PendingIntent pendingIntent = null;
        int bT = a.bT(parcel);
        IBinder iBinder = null;
        MessageFilter messageFilter = null;
        on onVar = null;
        int i2 = 0;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i2 = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    i = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    onVar = (on) a.a(parcel, bS, on.CREATOR);
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    messageFilter = (MessageFilter) a.a(parcel, bS, MessageFilter.CREATOR);
                    break;
                case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                    iBinder = a.q(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                    pendingIntent = (PendingIntent) a.a(parcel, bS, PendingIntent.CREATOR);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new op(i2, i, onVar, messageFilter, iBinder, pendingIntent);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public op[] hV(int i) {
        return new op[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return hV(x0);
    }
}
