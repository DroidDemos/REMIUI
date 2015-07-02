package com.google.android.gms.location.reporting;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class e implements Creator<UploadRequest> {
    static void a(UploadRequest uploadRequest, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, uploadRequest.getVersionCode());
        b.a(parcel, 2, uploadRequest.getAccount(), i, false);
        b.a(parcel, 3, uploadRequest.getReason(), false);
        b.a(parcel, 4, uploadRequest.getDurationMillis());
        b.a(parcel, 5, uploadRequest.getMovingLatencyMillis());
        b.a(parcel, 6, uploadRequest.getStationaryLatencyMillis());
        b.a(parcel, 7, uploadRequest.getAppSpecificKey(), false);
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return ge(x0);
    }

    public UploadRequest ge(Parcel parcel) {
        long j = 0;
        String str = null;
        int bT = a.bT(parcel);
        int i = 0;
        long j2 = 0;
        long j3 = 0;
        String str2 = null;
        Account account = null;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    account = (Account) a.a(parcel, bS, Account.CREATOR);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    str2 = a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    j3 = a.i(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                    j2 = a.i(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                    j = a.i(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                    str = a.p(parcel, bS);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new UploadRequest(i, account, str2, j3, j2, j, str);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public UploadRequest[] iz(int i) {
        return new UploadRequest[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return iz(x0);
    }
}
