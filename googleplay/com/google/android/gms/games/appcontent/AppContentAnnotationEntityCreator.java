package com.google.android.gms.games.appcontent;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class AppContentAnnotationEntityCreator implements Creator<AppContentAnnotationEntity> {
    static void a(AppContentAnnotationEntity appContentAnnotationEntity, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.a(parcel, 1, appContentAnnotationEntity.getDescription(), false);
        b.c(parcel, 1000, appContentAnnotationEntity.getVersionCode());
        b.a(parcel, 2, appContentAnnotationEntity.mA(), i, false);
        b.a(parcel, 3, appContentAnnotationEntity.getTitle(), false);
        b.a(parcel, 4, appContentAnnotationEntity.getType(), false);
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return eG(x0);
    }

    public AppContentAnnotationEntity eG(Parcel parcel) {
        String str = null;
        int bT = a.bT(parcel);
        int i = 0;
        String str2 = null;
        Uri uri = null;
        String str3 = null;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    str3 = a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    uri = (Uri) a.a(parcel, bS, Uri.CREATOR);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    str2 = a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
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
            return new AppContentAnnotationEntity(i, str3, uri, str2, str);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public AppContentAnnotationEntity[] gv(int i) {
        return new AppContentAnnotationEntity[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return gv(x0);
    }
}
