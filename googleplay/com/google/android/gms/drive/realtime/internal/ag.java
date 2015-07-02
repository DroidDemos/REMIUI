package com.google.android.gms.drive.realtime.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class ag implements Creator<ParcelableCollaborator> {
    static void a(ParcelableCollaborator parcelableCollaborator, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, parcelableCollaborator.mVersionCode);
        b.a(parcel, 2, parcelableCollaborator.aeh);
        b.a(parcel, 3, parcelableCollaborator.aei);
        b.a(parcel, 4, parcelableCollaborator.vY, false);
        b.a(parcel, 5, parcelableCollaborator.ada, false);
        b.a(parcel, 6, parcelableCollaborator.WN, false);
        b.a(parcel, 7, parcelableCollaborator.aej, false);
        b.a(parcel, 8, parcelableCollaborator.aek, false);
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return dt(x0);
    }

    public ParcelableCollaborator dt(Parcel parcel) {
        boolean z = false;
        String str = null;
        int bT = a.bT(parcel);
        String str2 = null;
        String str3 = null;
        String str4 = null;
        String str5 = null;
        boolean z2 = false;
        int i = 0;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    z2 = a.c(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    z = a.c(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    str5 = a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                    str4 = a.p(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                    str3 = a.p(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                    str2 = a.p(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                    str = a.p(parcel, bS);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new ParcelableCollaborator(i, z2, z, str5, str4, str3, str2, str);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public ParcelableCollaborator[] fd(int i) {
        return new ParcelableCollaborator[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return fd(x0);
    }
}
