package com.google.android.gms.fitness.request;

import android.app.PendingIntent;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.location.LocationRequest;
import com.google.android.wallet.instrumentmanager.R;
import java.util.List;

public class o implements Creator<n> {
    static void a(n nVar, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.a(parcel, 1, nVar.getDataSource(), i, false);
        b.c(parcel, 1000, nVar.getVersionCode());
        b.a(parcel, 2, nVar.getDataType(), i, false);
        b.a(parcel, 3, nVar.lZ(), false);
        b.c(parcel, 4, nVar.aie);
        b.c(parcel, 5, nVar.aif);
        b.a(parcel, 6, nVar.getSamplingRateMicros());
        b.a(parcel, 7, nVar.lW());
        b.a(parcel, 8, nVar.lU(), i, false);
        b.a(parcel, 9, nVar.lV());
        b.c(parcel, 10, nVar.getAccuracyMode());
        b.d(parcel, 11, nVar.lX(), false);
        b.a(parcel, 12, nVar.lY());
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return ei(x0);
    }

    public n ei(Parcel parcel) {
        int bT = a.bT(parcel);
        int i = 0;
        DataSource dataSource = null;
        DataType dataType = null;
        IBinder iBinder = null;
        int i2 = 0;
        int i3 = 0;
        long j = 0;
        long j2 = 0;
        PendingIntent pendingIntent = null;
        long j3 = 0;
        int i4 = 0;
        List list = null;
        long j4 = 0;
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
                    iBinder = a.q(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    i2 = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                    i3 = a.g(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                    j = a.i(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                    j2 = a.i(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                    pendingIntent = (PendingIntent) a.a(parcel, bS, PendingIntent.CREATOR);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoTextColor /*9*/:
                    j3 = a.i(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                    i4 = a.g(parcel, bS);
                    break;
                case R.styleable.MapAttrs_uiZoomControls /*11*/:
                    list = a.c(parcel, bS, LocationRequest.CREATOR);
                    break;
                case R.styleable.MapAttrs_uiZoomGestures /*12*/:
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
            return new n(i, dataSource, dataType, iBinder, i2, i3, j, j2, pendingIntent, j3, i4, list, j4);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public n[] fW(int i) {
        return new n[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return fW(x0);
    }
}
