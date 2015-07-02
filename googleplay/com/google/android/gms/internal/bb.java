package com.google.android.gms.internal;

import android.location.Location;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;
import java.util.List;

public class bb implements Creator<ba> {
    static void a(ba baVar, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, baVar.versionCode);
        b.a(parcel, 2, baVar.of);
        b.a(parcel, 3, baVar.extras, false);
        b.c(parcel, 4, baVar.og);
        b.c(parcel, 5, baVar.oh, false);
        b.a(parcel, 6, baVar.oi);
        b.c(parcel, 7, baVar.oj);
        b.a(parcel, 8, baVar.ok);
        b.a(parcel, 9, baVar.ol, false);
        b.a(parcel, 10, baVar.om, i, false);
        b.a(parcel, 11, baVar.on, i, false);
        b.a(parcel, 12, baVar.oo, false);
        b.a(parcel, 13, baVar.op, false);
        b.J(parcel, bU);
    }

    public ba b(Parcel parcel) {
        int bT = a.bT(parcel);
        int i = 0;
        long j = 0;
        Bundle bundle = null;
        int i2 = 0;
        List list = null;
        boolean z = false;
        int i3 = 0;
        boolean z2 = false;
        String str = null;
        bo boVar = null;
        Location location = null;
        String str2 = null;
        Bundle bundle2 = null;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    j = a.i(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    bundle = a.r(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    i2 = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                    list = a.E(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                    z = a.c(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                    i3 = a.g(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                    z2 = a.c(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoTextColor /*9*/:
                    str = a.p(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                    boVar = (bo) a.a(parcel, bS, bo.CREATOR);
                    break;
                case R.styleable.MapAttrs_uiZoomControls /*11*/:
                    location = (Location) a.a(parcel, bS, Location.CREATOR);
                    break;
                case R.styleable.MapAttrs_uiZoomGestures /*12*/:
                    str2 = a.p(parcel, bS);
                    break;
                case R.styleable.MapAttrs_useViewLifecycle /*13*/:
                    bundle2 = a.r(parcel, bS);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new ba(i, j, bundle, i2, list, z, i3, z2, str, boVar, location, str2, bundle2);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return b(x0);
    }

    public ba[] f(int i) {
        return new ba[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return f(x0);
    }
}
