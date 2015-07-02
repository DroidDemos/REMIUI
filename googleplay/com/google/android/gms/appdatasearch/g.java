package com.google.android.gms.appdatasearch;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class g implements Creator<DocumentSection> {
    static void a(DocumentSection documentSection, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.a(parcel, 1, documentSection.content, false);
        b.c(parcel, 1000, documentSection.mVersionCode);
        b.a(parcel, 3, documentSection.Da, i, false);
        b.c(parcel, 4, documentSection.globalSearchSectionType);
        b.a(parcel, 5, documentSection.blobContent, false);
        b.J(parcel, bU);
    }

    public DocumentSection[] L(int i) {
        return new DocumentSection[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return r(x0);
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return L(x0);
    }

    public DocumentSection r(Parcel parcel) {
        byte[] bArr = null;
        int bT = a.bT(parcel);
        int i = 0;
        int i2 = -1;
        RegisterSectionInfo registerSectionInfo = null;
        String str = null;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    str = a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    registerSectionInfo = (RegisterSectionInfo) a.a(parcel, bS, RegisterSectionInfo.CREATOR);
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    i2 = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                    bArr = a.s(parcel, bS);
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
            return new DocumentSection(i, str, registerSectionInfo, i2, bArr);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }
}
