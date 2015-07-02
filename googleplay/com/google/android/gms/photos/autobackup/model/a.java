package com.google.android.gms.photos.autobackup.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class a implements Creator<AutoBackupSettings> {
    static void a(AutoBackupSettings autoBackupSettings, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, autoBackupSettings.mVersionCode);
        b.a(parcel, 2, autoBackupSettings.getAccountName(), false);
        b.a(parcel, 3, autoBackupSettings.isEnabled());
        b.a(parcel, 4, autoBackupSettings.isWifiOnly());
        b.a(parcel, 5, autoBackupSettings.isRoamingUpload());
        b.a(parcel, 6, autoBackupSettings.isChargingOnly());
        b.a(parcel, 7, autoBackupSettings.isWifiOnlyVideo());
        b.a(parcel, 8, autoBackupSettings.isUploadFullResolution());
        b.a(parcel, 9, autoBackupSettings.isLocalFoldersAutoBackup());
        b.a(parcel, 10, autoBackupSettings.isPhotosStorageManaged());
        b.a(parcel, 11, autoBackupSettings.getUserQuota(), i, false);
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return gM(x0);
    }

    public AutoBackupSettings gM(Parcel parcel) {
        UserQuota userQuota = null;
        boolean z = false;
        int bT = com.google.android.gms.common.internal.safeparcel.a.bT(parcel);
        boolean z2 = false;
        boolean z3 = false;
        boolean z4 = false;
        boolean z5 = false;
        boolean z6 = false;
        boolean z7 = false;
        boolean z8 = false;
        String str = null;
        int i = 0;
        while (parcel.dataPosition() < bT) {
            int bS = com.google.android.gms.common.internal.safeparcel.a.bS(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i = com.google.android.gms.common.internal.safeparcel.a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    str = com.google.android.gms.common.internal.safeparcel.a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    z8 = com.google.android.gms.common.internal.safeparcel.a.c(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    z7 = com.google.android.gms.common.internal.safeparcel.a.c(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                    z6 = com.google.android.gms.common.internal.safeparcel.a.c(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                    z5 = com.google.android.gms.common.internal.safeparcel.a.c(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                    z4 = com.google.android.gms.common.internal.safeparcel.a.c(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                    z3 = com.google.android.gms.common.internal.safeparcel.a.c(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoTextColor /*9*/:
                    z2 = com.google.android.gms.common.internal.safeparcel.a.c(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                    z = com.google.android.gms.common.internal.safeparcel.a.c(parcel, bS);
                    break;
                case R.styleable.MapAttrs_uiZoomControls /*11*/:
                    userQuota = (UserQuota) com.google.android.gms.common.internal.safeparcel.a.a(parcel, bS, UserQuota.CREATOR);
                    break;
                default:
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new AutoBackupSettings(i, str, z8, z7, z6, z5, z4, z3, z2, z, userQuota);
        }
        throw new com.google.android.gms.common.internal.safeparcel.a.a("Overread allowed size end=" + bT, parcel);
    }

    public AutoBackupSettings[] jv(int i) {
        return new AutoBackupSettings[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return jv(x0);
    }
}
