package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class m implements Creator<RawDataPoint> {
    static void a(RawDataPoint rawDataPoint, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.a(parcel, 1, rawDataPoint.ago);
        b.c(parcel, 1000, rawDataPoint.mVersionCode);
        b.a(parcel, 2, rawDataPoint.agp);
        b.a(parcel, 3, rawDataPoint.agq, i, false);
        b.c(parcel, 4, rawDataPoint.agR);
        b.c(parcel, 5, rawDataPoint.agS);
        b.a(parcel, 6, rawDataPoint.ags);
        b.a(parcel, 7, rawDataPoint.agt);
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return dS(x0);
    }

    public RawDataPoint dS(Parcel parcel) {
        int bT = a.bT(parcel);
        int i = 0;
        long j = 0;
        long j2 = 0;
        Value[] valueArr = null;
        int i2 = 0;
        int i3 = 0;
        long j3 = 0;
        long j4 = 0;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    j = a.i(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    j2 = a.i(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    valueArr = (Value[]) a.b(parcel, bS, Value.CREATOR);
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    i2 = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                    i3 = a.g(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                    j3 = a.i(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                    j4 = a.i(parcel, bS);
                    break;
                case 1000:
                    i = a.g(parcel, bS);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new RawDataPoint(i, j, j2, valueArr, i2, i3, j3, j4);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public RawDataPoint[] fG(int i) {
        return new RawDataPoint[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return fG(x0);
    }
}
