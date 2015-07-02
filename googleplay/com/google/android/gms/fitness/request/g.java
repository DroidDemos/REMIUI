package com.google.android.gms.fitness.request;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.wallet.instrumentmanager.R;
import java.util.List;

public class g implements Creator<DataReadRequest> {
    static void a(DataReadRequest dataReadRequest, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.d(parcel, 1, dataReadRequest.getDataTypes(), false);
        b.c(parcel, 1000, dataReadRequest.getVersionCode());
        b.d(parcel, 2, dataReadRequest.getDataSources(), false);
        b.a(parcel, 3, dataReadRequest.lo());
        b.a(parcel, 4, dataReadRequest.lp());
        b.d(parcel, 5, dataReadRequest.getAggregatedDataTypes(), false);
        b.d(parcel, 6, dataReadRequest.getAggregatedDataSources(), false);
        b.c(parcel, 7, dataReadRequest.getBucketType());
        b.a(parcel, 8, dataReadRequest.lQ());
        b.a(parcel, 9, dataReadRequest.getActivityDataSource(), i, false);
        b.c(parcel, 10, dataReadRequest.getLimit());
        b.a(parcel, 12, dataReadRequest.lP());
        b.a(parcel, 13, dataReadRequest.lO());
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return ed(x0);
    }

    public DataReadRequest ed(Parcel parcel) {
        int bT = a.bT(parcel);
        int i = 0;
        List list = null;
        List list2 = null;
        long j = 0;
        long j2 = 0;
        List list3 = null;
        List list4 = null;
        int i2 = 0;
        long j3 = 0;
        DataSource dataSource = null;
        int i3 = 0;
        boolean z = false;
        boolean z2 = false;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    list = a.c(parcel, bS, DataType.CREATOR);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    list2 = a.c(parcel, bS, DataSource.CREATOR);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    j = a.i(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    j2 = a.i(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                    list3 = a.c(parcel, bS, DataType.CREATOR);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                    list4 = a.c(parcel, bS, DataSource.CREATOR);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                    i2 = a.g(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                    j3 = a.i(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoTextColor /*9*/:
                    dataSource = (DataSource) a.a(parcel, bS, DataSource.CREATOR);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                    i3 = a.g(parcel, bS);
                    break;
                case R.styleable.MapAttrs_uiZoomGestures /*12*/:
                    z = a.c(parcel, bS);
                    break;
                case R.styleable.MapAttrs_useViewLifecycle /*13*/:
                    z2 = a.c(parcel, bS);
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
            return new DataReadRequest(i, list, list2, j, j2, list3, list4, i2, j3, dataSource, i3, z, z2);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public DataReadRequest[] fR(int i) {
        return new DataReadRequest[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return fR(x0);
    }
}
