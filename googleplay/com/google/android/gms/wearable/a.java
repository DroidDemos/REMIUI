package com.google.android.gms.wearable;

import android.net.Uri;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class a implements Creator<Asset> {
    static void a(Asset asset, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, asset.mVersionCode);
        b.a(parcel, 2, asset.getData(), false);
        b.a(parcel, 3, asset.getDigest(), false);
        b.a(parcel, 4, asset.aWg, i, false);
        b.a(parcel, 5, asset.uri, i, false);
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return iD(x0);
    }

    public Asset iD(Parcel parcel) {
        Uri uri = null;
        int bT = com.google.android.gms.common.internal.safeparcel.a.bT(parcel);
        int i = 0;
        ParcelFileDescriptor parcelFileDescriptor = null;
        String str = null;
        byte[] bArr = null;
        while (parcel.dataPosition() < bT) {
            int bS = com.google.android.gms.common.internal.safeparcel.a.bS(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i = com.google.android.gms.common.internal.safeparcel.a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    bArr = com.google.android.gms.common.internal.safeparcel.a.s(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    str = com.google.android.gms.common.internal.safeparcel.a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    parcelFileDescriptor = (ParcelFileDescriptor) com.google.android.gms.common.internal.safeparcel.a.a(parcel, bS, ParcelFileDescriptor.CREATOR);
                    break;
                case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                    uri = (Uri) com.google.android.gms.common.internal.safeparcel.a.a(parcel, bS, Uri.CREATOR);
                    break;
                default:
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new Asset(i, bArr, str, parcelFileDescriptor, uri);
        }
        throw new com.google.android.gms.common.internal.safeparcel.a.a("Overread allowed size end=" + bT, parcel);
    }

    public Asset[] lD(int i) {
        return new Asset[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return lD(x0);
    }
}
