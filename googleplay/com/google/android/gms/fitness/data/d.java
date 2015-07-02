package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class d implements Creator<DataPoint> {
    static void a(DataPoint dataPoint, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.a(parcel, 1, dataPoint.getDataSource(), i, false);
        b.c(parcel, 1000, dataPoint.getVersionCode());
        b.a(parcel, 3, dataPoint.getTimestampNanos());
        b.a(parcel, 4, dataPoint.lr());
        b.a(parcel, 5, dataPoint.getValues(), i, false);
        b.a(parcel, 6, dataPoint.getOriginalDataSource(), i, false);
        b.a(parcel, 7, dataPoint.getRawTimestamp());
        b.a(parcel, 8, dataPoint.getInsertionTimeMillis());
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return dL(x0);
    }

    public DataPoint dL(Parcel parcel) {
        int bT = a.bT(parcel);
        int i = 0;
        DataSource dataSource = null;
        long j = 0;
        long j2 = 0;
        Value[] valueArr = null;
        DataSource dataSource2 = null;
        long j3 = 0;
        long j4 = 0;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    dataSource = (DataSource) a.a(parcel, bS, DataSource.CREATOR);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    j = a.i(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    j2 = a.i(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                    valueArr = (Value[]) a.b(parcel, bS, Value.CREATOR);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                    dataSource2 = (DataSource) a.a(parcel, bS, DataSource.CREATOR);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                    j3 = a.i(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
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
            return new DataPoint(i, dataSource, j, j2, valueArr, dataSource2, j3, j4);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public DataPoint[] fz(int i) {
        return new DataPoint[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return fz(x0);
    }
}
