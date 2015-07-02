package com.google.android.gms.car;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.car.CarPhoneStatus.CarCall;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class q implements Creator<CarCall> {
    static void a(CarCall carCall, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, carCall.state);
        b.c(parcel, 1000, carCall.getVersionCode());
        b.c(parcel, 2, carCall.callDurationSeconds);
        b.a(parcel, 3, carCall.callerNumber, false);
        b.a(parcel, 4, carCall.callerId, false);
        b.a(parcel, 5, carCall.callerNumberType, false);
        b.a(parcel, 6, carCall.callerThumbnail, false);
        b.J(parcel, bU);
    }

    public CarCall bw(Parcel parcel) {
        int i = 0;
        byte[] bArr = null;
        int bT = a.bT(parcel);
        String str = null;
        String str2 = null;
        String str3 = null;
        int i2 = 0;
        int i3 = 0;
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
                    str3 = a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    str2 = a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                    str = a.p(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                    bArr = a.s(parcel, bS);
                    break;
                case 1000:
                    i3 = a.g(parcel, bS);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new CarCall(i3, i2, i, str3, str2, str, bArr);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public CarCall[] cl(int i) {
        return new CarCall[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return bw(x0);
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return cl(x0);
    }
}
