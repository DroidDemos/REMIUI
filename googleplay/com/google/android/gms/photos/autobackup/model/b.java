package com.google.android.gms.photos.autobackup.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.wallet.instrumentmanager.R;

public class b implements Creator<AutoBackupStatus> {
    static void a(AutoBackupStatus autoBackupStatus, Parcel parcel, int i) {
        int bU = com.google.android.gms.common.internal.safeparcel.b.bU(parcel);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1, autoBackupStatus.mVersionCode);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 2, autoBackupStatus.getAutoBackupState());
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 3, autoBackupStatus.getAccountName(), false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 4, autoBackupStatus.getCurrentItem(), false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 5, autoBackupStatus.getCurrentItemProgress());
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 6, autoBackupStatus.getNumCompleted());
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 7, autoBackupStatus.getNumPending());
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 8, autoBackupStatus.getNumFailed());
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 9, autoBackupStatus.getFailedItems(), false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 10, autoBackupStatus.getEnabledAccountName(), false);
        com.google.android.gms.common.internal.safeparcel.b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return gN(x0);
    }

    public AutoBackupStatus gN(Parcel parcel) {
        String str = null;
        int i = 0;
        int bT = a.bT(parcel);
        float f = 0.0f;
        String[] strArr = null;
        int i2 = 0;
        int i3 = 0;
        String str2 = null;
        String str3 = null;
        int i4 = 0;
        int i5 = 0;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i5 = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    i4 = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    str3 = a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    str2 = a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                    f = a.l(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                    i3 = a.g(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                    i2 = a.g(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                    i = a.g(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoTextColor /*9*/:
                    strArr = a.B(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                    str = a.p(parcel, bS);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new AutoBackupStatus(i5, i4, str3, str2, f, i3, i2, i, strArr, str);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public AutoBackupStatus[] jw(int i) {
        return new AutoBackupStatus[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return jw(x0);
    }
}
