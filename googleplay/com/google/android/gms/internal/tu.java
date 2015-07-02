package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.internal.tt.c;
import com.google.android.gms.internal.tt.d;
import com.google.android.gms.internal.tt.f;
import com.google.android.gms.internal.tt.g;
import com.google.android.gms.internal.tt.h;
import com.google.android.wallet.instrumentmanager.R;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class tu implements Creator<tt> {
    static void a(tt ttVar, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        Set set = ttVar.aIz;
        if (set.contains(Integer.valueOf(1))) {
            b.c(parcel, 1, ttVar.mVersionCode);
        }
        if (set.contains(Integer.valueOf(2))) {
            b.a(parcel, 2, ttVar.aJx, true);
        }
        if (set.contains(Integer.valueOf(3))) {
            b.a(parcel, 3, ttVar.aJy, i, true);
        }
        if (set.contains(Integer.valueOf(4))) {
            b.a(parcel, 4, ttVar.aJz, true);
        }
        if (set.contains(Integer.valueOf(5))) {
            b.a(parcel, 5, ttVar.aJA, true);
        }
        if (set.contains(Integer.valueOf(6))) {
            b.c(parcel, 6, ttVar.aJB);
        }
        if (set.contains(Integer.valueOf(7))) {
            b.a(parcel, 7, ttVar.aJC, i, true);
        }
        if (set.contains(Integer.valueOf(8))) {
            b.a(parcel, 8, ttVar.aJD, true);
        }
        if (set.contains(Integer.valueOf(9))) {
            b.a(parcel, 9, ttVar.WN, true);
        }
        if (set.contains(Integer.valueOf(12))) {
            b.c(parcel, 12, ttVar.oy);
        }
        if (set.contains(Integer.valueOf(14))) {
            b.a(parcel, 14, ttVar.CB, true);
        }
        if (set.contains(Integer.valueOf(15))) {
            b.a(parcel, 15, ttVar.aJE, i, true);
        }
        if (set.contains(Integer.valueOf(16))) {
            b.a(parcel, 16, ttVar.aJF);
        }
        if (set.contains(Integer.valueOf(19))) {
            b.a(parcel, 19, ttVar.aJG, i, true);
        }
        if (set.contains(Integer.valueOf(18))) {
            b.a(parcel, 18, ttVar.Oe, true);
        }
        if (set.contains(Integer.valueOf(21))) {
            b.c(parcel, 21, ttVar.aJI);
        }
        if (set.contains(Integer.valueOf(20))) {
            b.a(parcel, 20, ttVar.aJH, true);
        }
        if (set.contains(Integer.valueOf(23))) {
            b.d(parcel, 23, ttVar.aJK, true);
        }
        if (set.contains(Integer.valueOf(22))) {
            b.d(parcel, 22, ttVar.aJJ, true);
        }
        if (set.contains(Integer.valueOf(25))) {
            b.c(parcel, 25, ttVar.aJM);
        }
        if (set.contains(Integer.valueOf(24))) {
            b.c(parcel, 24, ttVar.aJL);
        }
        if (set.contains(Integer.valueOf(27))) {
            b.a(parcel, 27, ttVar.vf, true);
        }
        if (set.contains(Integer.valueOf(26))) {
            b.a(parcel, 26, ttVar.aJN, true);
        }
        if (set.contains(Integer.valueOf(29))) {
            b.a(parcel, 29, ttVar.aJP);
        }
        if (set.contains(Integer.valueOf(28))) {
            b.d(parcel, 28, ttVar.aJO, true);
        }
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return gY(x0);
    }

    public tt gY(Parcel parcel) {
        int bT = a.bT(parcel);
        Set hashSet = new HashSet();
        int i = 0;
        String str = null;
        tt.a aVar = null;
        String str2 = null;
        String str3 = null;
        int i2 = 0;
        tt.b bVar = null;
        String str4 = null;
        String str5 = null;
        int i3 = 0;
        String str6 = null;
        c cVar = null;
        boolean z = false;
        String str7 = null;
        d dVar = null;
        String str8 = null;
        int i4 = 0;
        List list = null;
        List list2 = null;
        int i5 = 0;
        int i6 = 0;
        String str9 = null;
        String str10 = null;
        List list3 = null;
        boolean z2 = false;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i = a.g(parcel, bS);
                    hashSet.add(Integer.valueOf(1));
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    str = a.p(parcel, bS);
                    hashSet.add(Integer.valueOf(2));
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    tt.a aVar2 = (tt.a) a.a(parcel, bS, tt.a.CREATOR);
                    hashSet.add(Integer.valueOf(3));
                    aVar = aVar2;
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    str2 = a.p(parcel, bS);
                    hashSet.add(Integer.valueOf(4));
                    break;
                case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                    str3 = a.p(parcel, bS);
                    hashSet.add(Integer.valueOf(5));
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                    i2 = a.g(parcel, bS);
                    hashSet.add(Integer.valueOf(6));
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                    tt.b bVar2 = (tt.b) a.a(parcel, bS, tt.b.CREATOR);
                    hashSet.add(Integer.valueOf(7));
                    bVar = bVar2;
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                    str4 = a.p(parcel, bS);
                    hashSet.add(Integer.valueOf(8));
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoTextColor /*9*/:
                    str5 = a.p(parcel, bS);
                    hashSet.add(Integer.valueOf(9));
                    break;
                case R.styleable.MapAttrs_uiZoomGestures /*12*/:
                    i3 = a.g(parcel, bS);
                    hashSet.add(Integer.valueOf(12));
                    break;
                case R.styleable.MapAttrs_zOrderOnTop /*14*/:
                    str6 = a.p(parcel, bS);
                    hashSet.add(Integer.valueOf(14));
                    break;
                case R.styleable.MapAttrs_uiMapToolbar /*15*/:
                    c cVar2 = (c) a.a(parcel, bS, c.CREATOR);
                    hashSet.add(Integer.valueOf(15));
                    cVar = cVar2;
                    break;
                case com.google.android.play.R.styleable.Toolbar_maxButtonHeight /*16*/:
                    z = a.c(parcel, bS);
                    hashSet.add(Integer.valueOf(16));
                    break;
                case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                    str7 = a.p(parcel, bS);
                    hashSet.add(Integer.valueOf(18));
                    break;
                case com.google.android.play.R.styleable.Toolbar_collapseContentDescription /*19*/:
                    d dVar2 = (d) a.a(parcel, bS, d.CREATOR);
                    hashSet.add(Integer.valueOf(19));
                    dVar = dVar2;
                    break;
                case com.google.android.play.R.styleable.Toolbar_navigationIcon /*20*/:
                    str8 = a.p(parcel, bS);
                    hashSet.add(Integer.valueOf(20));
                    break;
                case com.google.android.play.R.styleable.Toolbar_navigationContentDescription /*21*/:
                    i4 = a.g(parcel, bS);
                    hashSet.add(Integer.valueOf(21));
                    break;
                case com.google.android.play.R.styleable.Theme_actionMenuTextAppearance /*22*/:
                    list = a.c(parcel, bS, f.CREATOR);
                    hashSet.add(Integer.valueOf(22));
                    break;
                case com.google.android.play.R.styleable.Theme_actionMenuTextColor /*23*/:
                    list2 = a.c(parcel, bS, g.CREATOR);
                    hashSet.add(Integer.valueOf(23));
                    break;
                case com.google.android.play.R.styleable.Theme_actionModeStyle /*24*/:
                    i5 = a.g(parcel, bS);
                    hashSet.add(Integer.valueOf(24));
                    break;
                case com.google.android.play.R.styleable.Theme_actionModeCloseButtonStyle /*25*/:
                    i6 = a.g(parcel, bS);
                    hashSet.add(Integer.valueOf(25));
                    break;
                case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                    str9 = a.p(parcel, bS);
                    hashSet.add(Integer.valueOf(26));
                    break;
                case com.google.android.play.R.styleable.Theme_actionModeSplitBackground /*27*/:
                    str10 = a.p(parcel, bS);
                    hashSet.add(Integer.valueOf(27));
                    break;
                case com.google.android.play.R.styleable.Theme_actionModeCloseDrawable /*28*/:
                    list3 = a.c(parcel, bS, h.CREATOR);
                    hashSet.add(Integer.valueOf(28));
                    break;
                case com.google.android.play.R.styleable.Theme_actionModeCutDrawable /*29*/:
                    z2 = a.c(parcel, bS);
                    hashSet.add(Integer.valueOf(29));
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new tt(hashSet, i, str, aVar, str2, str3, i2, bVar, str4, str5, i3, str6, cVar, z, str7, dVar, str8, i4, list, list2, i5, i6, str9, str10, list3, z2);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public tt[] jJ(int i) {
        return new tt[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return jJ(x0);
    }
}
