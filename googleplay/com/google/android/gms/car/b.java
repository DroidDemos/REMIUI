package com.google.android.gms.car;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.car.CarCall.Details;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.wallet.instrumentmanager.R;
import java.util.List;

public class b implements Creator<CarCall> {
    static void a(CarCall carCall, Parcel parcel, int i) {
        int bU = com.google.android.gms.common.internal.safeparcel.b.bU(parcel);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1, carCall.id);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1000, carCall.getVersionCode());
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 2, carCall.parent, i, false);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 3, carCall.cannedTextResponses, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 4, carCall.remainingPostDialSequence, false);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 5, carCall.state);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 6, carCall.details, i, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 7, carCall.hasChildren);
        com.google.android.gms.common.internal.safeparcel.b.J(parcel, bU);
    }

    public CarCall[] bY(int i) {
        return new CarCall[i];
    }

    public CarCall bk(Parcel parcel) {
        Details details = null;
        boolean z = false;
        int bT = a.bT(parcel);
        int i = 0;
        String str = null;
        List list = null;
        CarCall carCall = null;
        int i2 = 0;
        int i3 = 0;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i2 = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    carCall = (CarCall) a.a(parcel, bS, CarCall.CREATOR);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    list = a.E(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    str = a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                    i = a.g(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                    details = (Details) a.a(parcel, bS, Details.CREATOR);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                    z = a.c(parcel, bS);
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
            return new CarCall(i3, i2, carCall, list, str, i, details, z);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return bk(x0);
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return bY(x0);
    }
}
