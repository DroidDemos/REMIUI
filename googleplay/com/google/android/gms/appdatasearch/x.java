package com.google.android.gms.appdatasearch;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class x implements Creator<RegisterCorpusIMEInfo> {
    static void a(RegisterCorpusIMEInfo registerCorpusIMEInfo, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, registerCorpusIMEInfo.sourceClass);
        b.c(parcel, 1000, registerCorpusIMEInfo.mVersionCode);
        b.a(parcel, 2, registerCorpusIMEInfo.sectionNames, false);
        b.a(parcel, 3, registerCorpusIMEInfo.userInputTag, false);
        b.a(parcel, 4, registerCorpusIMEInfo.userInputSectionName, false);
        b.a(parcel, 6, registerCorpusIMEInfo.toAddressesSectionName, false);
        b.a(parcel, 7, registerCorpusIMEInfo.userInputSectionValues, false);
        b.J(parcel, bU);
    }

    public RegisterCorpusIMEInfo H(Parcel parcel) {
        int i = 0;
        String str = null;
        int bT = a.bT(parcel);
        String[] strArr = null;
        String str2 = null;
        String str3 = null;
        String[] strArr2 = null;
        int i2 = 0;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    strArr2 = a.B(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    str3 = a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    str2 = a.p(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                    str = a.p(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                    strArr = a.B(parcel, bS);
                    break;
                case 1000:
                    i2 = a.g(parcel, bS);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new RegisterCorpusIMEInfo(i2, i, strArr2, str3, str2, strArr, str);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public RegisterCorpusIMEInfo[] ab(int i) {
        return new RegisterCorpusIMEInfo[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return H(x0);
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return ab(x0);
    }
}
