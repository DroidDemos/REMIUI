package com.google.android.gms.photos.autobackup.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class c implements Creator<LocalFolder> {
    static void a(LocalFolder localFolder, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, localFolder.mVersionCode);
        b.a(parcel, 2, localFolder.getFolderName(), false);
        b.a(parcel, 3, localFolder.getBucketId(), false);
        b.a(parcel, 4, localFolder.isAutoBackupEnabled());
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return gO(x0);
    }

    public LocalFolder gO(Parcel parcel) {
        String str = null;
        boolean z = false;
        int bT = a.bT(parcel);
        String str2 = null;
        int i = 0;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    str2 = a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    str = a.p(parcel, bS);
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
            return new LocalFolder(i, str2, str, z);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public LocalFolder[] jx(int i) {
        return new LocalFolder[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return jx(x0);
    }
}
