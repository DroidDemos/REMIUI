package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class f implements Creator<DataSource> {
    static void a(DataSource dataSource, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.a(parcel, 1, dataSource.getDataType(), i, false);
        b.c(parcel, 1000, dataSource.getVersionCode());
        b.a(parcel, 2, dataSource.getName(), false);
        b.c(parcel, 3, dataSource.getType());
        b.a(parcel, 4, dataSource.getDevice(), i, false);
        b.a(parcel, 5, dataSource.getApplication(), i, false);
        b.a(parcel, 6, dataSource.getStreamName(), false);
        b.a(parcel, 7, dataSource.lv());
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return dN(x0);
    }

    public DataSource dN(Parcel parcel) {
        boolean z = false;
        String str = null;
        int bT = a.bT(parcel);
        Application application = null;
        Device device = null;
        int i = 0;
        String str2 = null;
        DataType dataType = null;
        int i2 = 0;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    dataType = (DataType) a.a(parcel, bS, DataType.CREATOR);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    str2 = a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    i = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    device = (Device) a.a(parcel, bS, Device.CREATOR);
                    break;
                case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                    application = (Application) a.a(parcel, bS, Application.CREATOR);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                    str = a.p(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                    z = a.c(parcel, bS);
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
            return new DataSource(i2, dataType, str2, i, device, application, str, z);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public DataSource[] fB(int i) {
        return new DataSource[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return fB(x0);
    }
}
