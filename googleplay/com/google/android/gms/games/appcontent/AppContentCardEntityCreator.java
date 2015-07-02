package com.google.android.gms.games.appcontent;

import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;
import java.util.ArrayList;

public class AppContentCardEntityCreator implements Creator<AppContentCardEntity> {
    static void a(AppContentCardEntity appContentCardEntity, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.d(parcel, 1, appContentCardEntity.getActions(), false);
        b.c(parcel, 1000, appContentCardEntity.getVersionCode());
        b.d(parcel, 2, appContentCardEntity.mC(), false);
        b.d(parcel, 3, appContentCardEntity.mw(), false);
        b.a(parcel, 4, appContentCardEntity.mx(), false);
        b.c(parcel, 5, appContentCardEntity.mD());
        b.a(parcel, 6, appContentCardEntity.getDescription(), false);
        b.a(parcel, 7, appContentCardEntity.mE(), false);
        b.a(parcel, 8, appContentCardEntity.getIconImageUri(), i, false);
        b.a(parcel, 9, appContentCardEntity.mA(), i, false);
        b.a(parcel, 10, appContentCardEntity.mF(), false);
        b.a(parcel, 11, appContentCardEntity.getTitle(), false);
        b.c(parcel, 12, appContentCardEntity.mG());
        b.a(parcel, 13, appContentCardEntity.getType(), false);
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return eH(x0);
    }

    public AppContentCardEntity eH(Parcel parcel) {
        int bT = a.bT(parcel);
        int i = 0;
        ArrayList arrayList = null;
        ArrayList arrayList2 = null;
        ArrayList arrayList3 = null;
        String str = null;
        int i2 = 0;
        String str2 = null;
        Bundle bundle = null;
        Uri uri = null;
        Uri uri2 = null;
        String str3 = null;
        String str4 = null;
        int i3 = 0;
        String str5 = null;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    arrayList = a.c(parcel, bS, AppContentActionEntity.CREATOR);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    arrayList2 = a.c(parcel, bS, AppContentAnnotationEntity.CREATOR);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    arrayList3 = a.c(parcel, bS, AppContentConditionEntity.CREATOR);
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    str = a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                    i2 = a.g(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                    str2 = a.p(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                    bundle = a.r(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                    uri = (Uri) a.a(parcel, bS, Uri.CREATOR);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoTextColor /*9*/:
                    uri2 = (Uri) a.a(parcel, bS, Uri.CREATOR);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                    str3 = a.p(parcel, bS);
                    break;
                case R.styleable.MapAttrs_uiZoomControls /*11*/:
                    str4 = a.p(parcel, bS);
                    break;
                case R.styleable.MapAttrs_uiZoomGestures /*12*/:
                    i3 = a.g(parcel, bS);
                    break;
                case R.styleable.MapAttrs_useViewLifecycle /*13*/:
                    str5 = a.p(parcel, bS);
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
            return new AppContentCardEntity(i, arrayList, arrayList2, arrayList3, str, i2, str2, bundle, uri, uri2, str3, str4, i3, str5);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public AppContentCardEntity[] gw(int i) {
        return new AppContentCardEntity[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return gw(x0);
    }
}
