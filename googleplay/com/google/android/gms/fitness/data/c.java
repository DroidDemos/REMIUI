package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;
import java.util.List;

public class c implements Creator<Bucket> {
    static void a(Bucket bucket, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.a(parcel, 1, bucket.lo());
        b.c(parcel, 1000, bucket.getVersionCode());
        b.a(parcel, 2, bucket.lp());
        b.a(parcel, 3, bucket.getSession(), i, false);
        b.c(parcel, 4, bucket.ln());
        b.d(parcel, 5, bucket.getDataSets(), false);
        b.c(parcel, 6, bucket.getBucketType());
        b.a(parcel, 7, bucket.serverHasMoreData());
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return dK(x0);
    }

    public Bucket dK(Parcel parcel) {
        long j = 0;
        List list = null;
        boolean z = false;
        int bT = a.bT(parcel);
        int i = 0;
        int i2 = 0;
        Session session = null;
        long j2 = 0;
        int i3 = 0;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    j2 = a.i(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    j = a.i(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    session = (Session) a.a(parcel, bS, Session.CREATOR);
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    i2 = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                    list = a.c(parcel, bS, DataSet.CREATOR);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                    i = a.g(parcel, bS);
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
            return new Bucket(i3, j2, j, session, i2, list, i, z);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public Bucket[] fx(int i) {
        return new Bucket[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return fx(x0);
    }
}
