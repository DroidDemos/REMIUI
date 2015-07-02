package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class r implements Creator<Subscription> {
    static void a(Subscription subscription, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.a(parcel, 1, subscription.getDataSource(), i, false);
        b.c(parcel, 1000, subscription.getVersionCode());
        b.a(parcel, 2, subscription.getDataType(), i, false);
        b.a(parcel, 3, subscription.getSamplingRateMicros());
        b.c(parcel, 4, subscription.getAccuracyMode());
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return dW(x0);
    }

    public Subscription dW(Parcel parcel) {
        DataType dataType = null;
        int i = 0;
        int bT = a.bT(parcel);
        long j = 0;
        DataSource dataSource = null;
        int i2 = 0;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    dataSource = (DataSource) a.a(parcel, bS, DataSource.CREATOR);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    dataType = (DataType) a.a(parcel, bS, DataType.CREATOR);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    j = a.i(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    i = a.g(parcel, bS);
                    break;
                case 1000:
                    i2 = a.g(parcel, bS);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new Subscription(i2, dataSource, dataType, j, i);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public Subscription[] fK(int i) {
        return new Subscription[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return fK(x0);
    }
}
