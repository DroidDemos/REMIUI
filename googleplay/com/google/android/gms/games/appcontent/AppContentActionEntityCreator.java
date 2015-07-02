package com.google.android.gms.games.appcontent;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;
import java.util.ArrayList;

public class AppContentActionEntityCreator implements Creator<AppContentActionEntity> {
    static void a(AppContentActionEntity appContentActionEntity, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.d(parcel, 1, appContentActionEntity.mw(), false);
        b.c(parcel, 1000, appContentActionEntity.getVersionCode());
        b.a(parcel, 2, appContentActionEntity.mx(), false);
        b.a(parcel, 3, appContentActionEntity.getExtras(), false);
        b.a(parcel, 4, appContentActionEntity.getLabel(), false);
        b.a(parcel, 5, appContentActionEntity.my(), false);
        b.a(parcel, 6, appContentActionEntity.getType(), false);
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return eF(x0);
    }

    public AppContentActionEntity eF(Parcel parcel) {
        String str = null;
        int bT = a.bT(parcel);
        int i = 0;
        String str2 = null;
        String str3 = null;
        Bundle bundle = null;
        String str4 = null;
        ArrayList arrayList = null;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    arrayList = a.c(parcel, bS, AppContentConditionEntity.CREATOR);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    str4 = a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    bundle = a.r(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    str3 = a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                    str2 = a.p(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
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
            return new AppContentActionEntity(i, arrayList, str4, bundle, str3, str2, str);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public AppContentActionEntity[] gu(int i) {
        return new AppContentActionEntity[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return gu(x0);
    }
}
