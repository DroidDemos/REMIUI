package com.google.android.gms.games.internal.player;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class MostRecentGameInfoEntityCreator implements Creator<MostRecentGameInfoEntity> {
    static void a(MostRecentGameInfoEntity mostRecentGameInfoEntity, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.a(parcel, 1, mostRecentGameInfoEntity.ol(), false);
        b.c(parcel, 1000, mostRecentGameInfoEntity.getVersionCode());
        b.a(parcel, 2, mostRecentGameInfoEntity.om(), false);
        b.a(parcel, 3, mostRecentGameInfoEntity.on());
        b.a(parcel, 4, mostRecentGameInfoEntity.oo(), i, false);
        b.a(parcel, 5, mostRecentGameInfoEntity.op(), i, false);
        b.a(parcel, 6, mostRecentGameInfoEntity.oq(), i, false);
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return eP(x0);
    }

    public MostRecentGameInfoEntity eP(Parcel parcel) {
        Uri uri = null;
        int bT = a.bT(parcel);
        int i = 0;
        long j = 0;
        Uri uri2 = null;
        Uri uri3 = null;
        String str = null;
        String str2 = null;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    str2 = a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    str = a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    j = a.i(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    uri3 = (Uri) a.a(parcel, bS, Uri.CREATOR);
                    break;
                case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                    uri2 = (Uri) a.a(parcel, bS, Uri.CREATOR);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                    uri = (Uri) a.a(parcel, bS, Uri.CREATOR);
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
            return new MostRecentGameInfoEntity(i, str2, str, j, uri3, uri2, uri);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public MostRecentGameInfoEntity[] gU(int i) {
        return new MostRecentGameInfoEntity[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return gU(x0);
    }
}
