package com.google.android.gms.games.appcontent;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class AppContentConditionEntityCreator implements Creator<AppContentConditionEntity> {
    static void a(AppContentConditionEntity appContentConditionEntity, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.a(parcel, 1, appContentConditionEntity.mI(), false);
        b.c(parcel, 1000, appContentConditionEntity.getVersionCode());
        b.a(parcel, 2, appContentConditionEntity.mJ(), false);
        b.a(parcel, 3, appContentConditionEntity.mK(), false);
        b.a(parcel, 4, appContentConditionEntity.mL(), false);
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return eI(x0);
    }

    public AppContentConditionEntity eI(Parcel parcel) {
        Bundle bundle = null;
        int bT = a.bT(parcel);
        int i = 0;
        String str = null;
        String str2 = null;
        String str3 = null;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    str3 = a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    str2 = a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    str = a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    bundle = a.r(parcel, bS);
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
            return new AppContentConditionEntity(i, str3, str2, str, bundle);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public AppContentConditionEntity[] gx(int i) {
        return new AppContentConditionEntity[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return gx(x0);
    }
}
