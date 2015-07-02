package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class ab implements Creator<FileUploadPreferencesImpl> {
    static void a(FileUploadPreferencesImpl fileUploadPreferencesImpl, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, fileUploadPreferencesImpl.mVersionCode);
        b.c(parcel, 2, fileUploadPreferencesImpl.aaJ);
        b.c(parcel, 3, fileUploadPreferencesImpl.aaK);
        b.a(parcel, 4, fileUploadPreferencesImpl.aaL);
        b.J(parcel, bU);
    }

    public FileUploadPreferencesImpl cA(Parcel parcel) {
        boolean z = false;
        int bT = a.bT(parcel);
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i3 = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    i2 = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    i = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    z = a.c(parcel, bS);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new FileUploadPreferencesImpl(i3, i2, i, z);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return cA(x0);
    }

    public FileUploadPreferencesImpl[] ei(int i) {
        return new FileUploadPreferencesImpl[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return ei(x0);
    }
}
