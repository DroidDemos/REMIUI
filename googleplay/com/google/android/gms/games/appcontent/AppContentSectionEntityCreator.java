package com.google.android.gms.games.appcontent;

import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;
import java.util.ArrayList;

public class AppContentSectionEntityCreator implements Creator<AppContentSectionEntity> {
    static void a(AppContentSectionEntity appContentSectionEntity, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.d(parcel, 1, appContentSectionEntity.getActions(), false);
        b.c(parcel, 1000, appContentSectionEntity.getVersionCode());
        b.a(parcel, 2, appContentSectionEntity.mN(), i, false);
        b.d(parcel, 3, appContentSectionEntity.mO(), false);
        b.a(parcel, 4, appContentSectionEntity.mx(), false);
        b.a(parcel, 5, appContentSectionEntity.mE(), false);
        b.a(parcel, 6, appContentSectionEntity.mF(), false);
        b.a(parcel, 7, appContentSectionEntity.getTitle(), false);
        b.a(parcel, 8, appContentSectionEntity.getType(), false);
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return eJ(x0);
    }

    public AppContentSectionEntity eJ(Parcel parcel) {
        String str = null;
        int bT = a.bT(parcel);
        int i = 0;
        String str2 = null;
        String str3 = null;
        Bundle bundle = null;
        String str4 = null;
        ArrayList arrayList = null;
        Uri uri = null;
        ArrayList arrayList2 = null;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    arrayList2 = a.c(parcel, bS, AppContentActionEntity.CREATOR);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    uri = (Uri) a.a(parcel, bS, Uri.CREATOR);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    arrayList = a.c(parcel, bS, AppContentCardEntity.CREATOR);
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    str4 = a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                    bundle = a.r(parcel, bS);
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
                case 1000:
                    i = a.g(parcel, bS);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new AppContentSectionEntity(i, arrayList2, uri, arrayList, str4, bundle, str3, str2, str);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public AppContentSectionEntity[] gy(int i) {
        return new AppContentSectionEntity[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return gy(x0);
    }
}
