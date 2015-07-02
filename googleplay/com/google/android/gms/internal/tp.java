package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class tp implements Creator<to> {
    static void a(to toVar, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        Set set = toVar.aIz;
        if (set.contains(Integer.valueOf(1))) {
            b.c(parcel, 1, toVar.mVersionCode);
        }
        if (set.contains(Integer.valueOf(2))) {
            b.a(parcel, 2, toVar.aIA, i, true);
        }
        if (set.contains(Integer.valueOf(3))) {
            b.c(parcel, 3, toVar.aIB, true);
        }
        if (set.contains(Integer.valueOf(4))) {
            b.a(parcel, 4, toVar.aIC, i, true);
        }
        if (set.contains(Integer.valueOf(5))) {
            b.a(parcel, 5, toVar.aID, true);
        }
        if (set.contains(Integer.valueOf(6))) {
            b.a(parcel, 6, toVar.aIE, true);
        }
        if (set.contains(Integer.valueOf(7))) {
            b.a(parcel, 7, toVar.aIF, true);
        }
        if (set.contains(Integer.valueOf(8))) {
            b.d(parcel, 8, toVar.aIG, true);
        }
        if (set.contains(Integer.valueOf(9))) {
            b.c(parcel, 9, toVar.aIH);
        }
        if (set.contains(Integer.valueOf(10))) {
            b.d(parcel, 10, toVar.aII, true);
        }
        if (set.contains(Integer.valueOf(11))) {
            b.a(parcel, 11, toVar.aIJ, i, true);
        }
        if (set.contains(Integer.valueOf(12))) {
            b.d(parcel, 12, toVar.aIK, true);
        }
        if (set.contains(Integer.valueOf(13))) {
            b.a(parcel, 13, toVar.aIL, true);
        }
        if (set.contains(Integer.valueOf(14))) {
            b.a(parcel, 14, toVar.aIM, true);
        }
        if (set.contains(Integer.valueOf(15))) {
            b.a(parcel, 15, toVar.aIN, i, true);
        }
        if (set.contains(Integer.valueOf(17))) {
            b.a(parcel, 17, toVar.aIP, true);
        }
        if (set.contains(Integer.valueOf(16))) {
            b.a(parcel, 16, toVar.aIO, true);
        }
        if (set.contains(Integer.valueOf(19))) {
            b.d(parcel, 19, toVar.aIQ, true);
        }
        if (set.contains(Integer.valueOf(18))) {
            b.a(parcel, 18, toVar.ox, true);
        }
        if (set.contains(Integer.valueOf(21))) {
            b.a(parcel, 21, toVar.aIS, true);
        }
        if (set.contains(Integer.valueOf(20))) {
            b.a(parcel, 20, toVar.aIR, true);
        }
        if (set.contains(Integer.valueOf(23))) {
            b.a(parcel, 23, toVar.mDescription, true);
        }
        if (set.contains(Integer.valueOf(22))) {
            b.a(parcel, 22, toVar.aIT, true);
        }
        if (set.contains(Integer.valueOf(25))) {
            b.a(parcel, 25, toVar.aIV, true);
        }
        if (set.contains(Integer.valueOf(24))) {
            b.a(parcel, 24, toVar.aIU, true);
        }
        if (set.contains(Integer.valueOf(27))) {
            b.a(parcel, 27, toVar.aIX, true);
        }
        if (set.contains(Integer.valueOf(26))) {
            b.a(parcel, 26, toVar.aIW, true);
        }
        if (set.contains(Integer.valueOf(29))) {
            b.a(parcel, 29, toVar.aIZ, i, true);
        }
        if (set.contains(Integer.valueOf(28))) {
            b.a(parcel, 28, toVar.aIY, true);
        }
        if (set.contains(Integer.valueOf(31))) {
            b.a(parcel, 31, toVar.aJb, true);
        }
        if (set.contains(Integer.valueOf(30))) {
            b.a(parcel, 30, toVar.aJa, true);
        }
        if (set.contains(Integer.valueOf(34))) {
            b.a(parcel, 34, toVar.aJd, i, true);
        }
        if (set.contains(Integer.valueOf(32))) {
            b.a(parcel, 32, toVar.CB, true);
        }
        if (set.contains(Integer.valueOf(33))) {
            b.a(parcel, 33, toVar.aJc, true);
        }
        if (set.contains(Integer.valueOf(38))) {
            b.a(parcel, 38, toVar.atq);
        }
        if (set.contains(Integer.valueOf(39))) {
            b.a(parcel, 39, toVar.mName, true);
        }
        if (set.contains(Integer.valueOf(36))) {
            b.a(parcel, 36, toVar.atp);
        }
        if (set.contains(Integer.valueOf(37))) {
            b.a(parcel, 37, toVar.aJe, i, true);
        }
        if (set.contains(Integer.valueOf(42))) {
            b.a(parcel, 42, toVar.aJh, true);
        }
        if (set.contains(Integer.valueOf(43))) {
            b.a(parcel, 43, toVar.aJi, true);
        }
        if (set.contains(Integer.valueOf(40))) {
            b.a(parcel, 40, toVar.aJf, i, true);
        }
        if (set.contains(Integer.valueOf(41))) {
            b.d(parcel, 41, toVar.aJg, true);
        }
        if (set.contains(Integer.valueOf(46))) {
            b.a(parcel, 46, toVar.aJl, i, true);
        }
        if (set.contains(Integer.valueOf(47))) {
            b.a(parcel, 47, toVar.aJm, true);
        }
        if (set.contains(Integer.valueOf(44))) {
            b.a(parcel, 44, toVar.aJj, true);
        }
        if (set.contains(Integer.valueOf(45))) {
            b.a(parcel, 45, toVar.aJk, true);
        }
        if (set.contains(Integer.valueOf(51))) {
            b.a(parcel, 51, toVar.aJq, true);
        }
        if (set.contains(Integer.valueOf(50))) {
            b.a(parcel, 50, toVar.aJp, i, true);
        }
        if (set.contains(Integer.valueOf(49))) {
            b.a(parcel, 49, toVar.aJo, true);
        }
        if (set.contains(Integer.valueOf(48))) {
            b.a(parcel, 48, toVar.aJn, true);
        }
        if (set.contains(Integer.valueOf(55))) {
            b.a(parcel, 55, toVar.aJs, true);
        }
        if (set.contains(Integer.valueOf(54))) {
            b.a(parcel, 54, toVar.vf, true);
        }
        if (set.contains(Integer.valueOf(53))) {
            b.a(parcel, 53, toVar.vc, true);
        }
        if (set.contains(Integer.valueOf(52))) {
            b.a(parcel, 52, toVar.aJr, true);
        }
        if (set.contains(Integer.valueOf(56))) {
            b.a(parcel, 56, toVar.aJt, true);
        }
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return gW(x0);
    }

    public to gW(Parcel parcel) {
        int bT = a.bT(parcel);
        Set hashSet = new HashSet();
        int i = 0;
        to toVar = null;
        List list = null;
        to toVar2 = null;
        String str = null;
        String str2 = null;
        String str3 = null;
        List list2 = null;
        int i2 = 0;
        List list3 = null;
        to toVar3 = null;
        List list4 = null;
        String str4 = null;
        String str5 = null;
        to toVar4 = null;
        String str6 = null;
        String str7 = null;
        String str8 = null;
        List list5 = null;
        String str9 = null;
        String str10 = null;
        String str11 = null;
        String str12 = null;
        String str13 = null;
        String str14 = null;
        String str15 = null;
        String str16 = null;
        String str17 = null;
        to toVar5 = null;
        String str18 = null;
        String str19 = null;
        String str20 = null;
        String str21 = null;
        to toVar6 = null;
        double d = 0.0d;
        to toVar7 = null;
        double d2 = 0.0d;
        String str22 = null;
        to toVar8 = null;
        List list6 = null;
        String str23 = null;
        String str24 = null;
        String str25 = null;
        String str26 = null;
        to toVar9 = null;
        String str27 = null;
        String str28 = null;
        String str29 = null;
        to toVar10 = null;
        String str30 = null;
        String str31 = null;
        String str32 = null;
        String str33 = null;
        String str34 = null;
        String str35 = null;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            to toVar11;
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i = a.g(parcel, bS);
                    hashSet.add(Integer.valueOf(1));
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    toVar11 = (to) a.a(parcel, bS, to.CREATOR);
                    hashSet.add(Integer.valueOf(2));
                    toVar = toVar11;
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    list = a.E(parcel, bS);
                    hashSet.add(Integer.valueOf(3));
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    toVar11 = (to) a.a(parcel, bS, to.CREATOR);
                    hashSet.add(Integer.valueOf(4));
                    toVar2 = toVar11;
                    break;
                case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                    str = a.p(parcel, bS);
                    hashSet.add(Integer.valueOf(5));
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                    str2 = a.p(parcel, bS);
                    hashSet.add(Integer.valueOf(6));
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                    str3 = a.p(parcel, bS);
                    hashSet.add(Integer.valueOf(7));
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                    list2 = a.c(parcel, bS, to.CREATOR);
                    hashSet.add(Integer.valueOf(8));
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoTextColor /*9*/:
                    i2 = a.g(parcel, bS);
                    hashSet.add(Integer.valueOf(9));
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                    list3 = a.c(parcel, bS, to.CREATOR);
                    hashSet.add(Integer.valueOf(10));
                    break;
                case R.styleable.MapAttrs_uiZoomControls /*11*/:
                    toVar11 = (to) a.a(parcel, bS, to.CREATOR);
                    hashSet.add(Integer.valueOf(11));
                    toVar3 = toVar11;
                    break;
                case R.styleable.MapAttrs_uiZoomGestures /*12*/:
                    list4 = a.c(parcel, bS, to.CREATOR);
                    hashSet.add(Integer.valueOf(12));
                    break;
                case R.styleable.MapAttrs_useViewLifecycle /*13*/:
                    str4 = a.p(parcel, bS);
                    hashSet.add(Integer.valueOf(13));
                    break;
                case R.styleable.MapAttrs_zOrderOnTop /*14*/:
                    str5 = a.p(parcel, bS);
                    hashSet.add(Integer.valueOf(14));
                    break;
                case R.styleable.MapAttrs_uiMapToolbar /*15*/:
                    toVar11 = (to) a.a(parcel, bS, to.CREATOR);
                    hashSet.add(Integer.valueOf(15));
                    toVar4 = toVar11;
                    break;
                case com.google.android.play.R.styleable.Toolbar_maxButtonHeight /*16*/:
                    str6 = a.p(parcel, bS);
                    hashSet.add(Integer.valueOf(16));
                    break;
                case com.google.android.play.R.styleable.Toolbar_theme /*17*/:
                    str7 = a.p(parcel, bS);
                    hashSet.add(Integer.valueOf(17));
                    break;
                case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                    str8 = a.p(parcel, bS);
                    hashSet.add(Integer.valueOf(18));
                    break;
                case com.google.android.play.R.styleable.Toolbar_collapseContentDescription /*19*/:
                    list5 = a.c(parcel, bS, to.CREATOR);
                    hashSet.add(Integer.valueOf(19));
                    break;
                case com.google.android.play.R.styleable.Toolbar_navigationIcon /*20*/:
                    str9 = a.p(parcel, bS);
                    hashSet.add(Integer.valueOf(20));
                    break;
                case com.google.android.play.R.styleable.Toolbar_navigationContentDescription /*21*/:
                    str10 = a.p(parcel, bS);
                    hashSet.add(Integer.valueOf(21));
                    break;
                case com.google.android.play.R.styleable.Theme_actionMenuTextAppearance /*22*/:
                    str11 = a.p(parcel, bS);
                    hashSet.add(Integer.valueOf(22));
                    break;
                case com.google.android.play.R.styleable.Theme_actionMenuTextColor /*23*/:
                    str12 = a.p(parcel, bS);
                    hashSet.add(Integer.valueOf(23));
                    break;
                case com.google.android.play.R.styleable.Theme_actionModeStyle /*24*/:
                    str13 = a.p(parcel, bS);
                    hashSet.add(Integer.valueOf(24));
                    break;
                case com.google.android.play.R.styleable.Theme_actionModeCloseButtonStyle /*25*/:
                    str14 = a.p(parcel, bS);
                    hashSet.add(Integer.valueOf(25));
                    break;
                case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                    str15 = a.p(parcel, bS);
                    hashSet.add(Integer.valueOf(26));
                    break;
                case com.google.android.play.R.styleable.Theme_actionModeSplitBackground /*27*/:
                    str16 = a.p(parcel, bS);
                    hashSet.add(Integer.valueOf(27));
                    break;
                case com.google.android.play.R.styleable.Theme_actionModeCloseDrawable /*28*/:
                    str17 = a.p(parcel, bS);
                    hashSet.add(Integer.valueOf(28));
                    break;
                case com.google.android.play.R.styleable.Theme_actionModeCutDrawable /*29*/:
                    toVar11 = (to) a.a(parcel, bS, to.CREATOR);
                    hashSet.add(Integer.valueOf(29));
                    toVar5 = toVar11;
                    break;
                case com.google.android.play.R.styleable.Theme_actionModeCopyDrawable /*30*/:
                    str18 = a.p(parcel, bS);
                    hashSet.add(Integer.valueOf(30));
                    break;
                case com.google.android.play.R.styleable.Theme_actionModePasteDrawable /*31*/:
                    str19 = a.p(parcel, bS);
                    hashSet.add(Integer.valueOf(31));
                    break;
                case com.google.android.play.R.styleable.Theme_actionModeSelectAllDrawable /*32*/:
                    str20 = a.p(parcel, bS);
                    hashSet.add(Integer.valueOf(32));
                    break;
                case com.google.android.play.R.styleable.Theme_actionModeShareDrawable /*33*/:
                    str21 = a.p(parcel, bS);
                    hashSet.add(Integer.valueOf(33));
                    break;
                case com.google.android.play.R.styleable.Theme_actionModeFindDrawable /*34*/:
                    toVar11 = (to) a.a(parcel, bS, to.CREATOR);
                    hashSet.add(Integer.valueOf(34));
                    toVar6 = toVar11;
                    break;
                case com.google.android.play.R.styleable.Theme_actionModePopupWindowStyle /*36*/:
                    d = a.m(parcel, bS);
                    hashSet.add(Integer.valueOf(36));
                    break;
                case com.google.android.play.R.styleable.Theme_textAppearanceLargePopupMenu /*37*/:
                    toVar11 = (to) a.a(parcel, bS, to.CREATOR);
                    hashSet.add(Integer.valueOf(37));
                    toVar7 = toVar11;
                    break;
                case com.google.android.play.R.styleable.Theme_textAppearanceSmallPopupMenu /*38*/:
                    d2 = a.m(parcel, bS);
                    hashSet.add(Integer.valueOf(38));
                    break;
                case com.google.android.play.R.styleable.Theme_actionDropDownStyle /*39*/:
                    str22 = a.p(parcel, bS);
                    hashSet.add(Integer.valueOf(39));
                    break;
                case com.google.android.play.R.styleable.Theme_dropdownListPreferredItemHeight /*40*/:
                    toVar11 = (to) a.a(parcel, bS, to.CREATOR);
                    hashSet.add(Integer.valueOf(40));
                    toVar8 = toVar11;
                    break;
                case com.google.android.play.R.styleable.Theme_spinnerStyle /*41*/:
                    list6 = a.c(parcel, bS, to.CREATOR);
                    hashSet.add(Integer.valueOf(41));
                    break;
                case com.google.android.play.R.styleable.Theme_spinnerDropDownItemStyle /*42*/:
                    str23 = a.p(parcel, bS);
                    hashSet.add(Integer.valueOf(42));
                    break;
                case com.google.android.play.R.styleable.Theme_homeAsUpIndicator /*43*/:
                    str24 = a.p(parcel, bS);
                    hashSet.add(Integer.valueOf(43));
                    break;
                case com.google.android.play.R.styleable.Theme_actionButtonStyle /*44*/:
                    str25 = a.p(parcel, bS);
                    hashSet.add(Integer.valueOf(44));
                    break;
                case com.google.android.play.R.styleable.Theme_buttonBarStyle /*45*/:
                    str26 = a.p(parcel, bS);
                    hashSet.add(Integer.valueOf(45));
                    break;
                case com.google.android.play.R.styleable.Theme_buttonBarButtonStyle /*46*/:
                    toVar11 = (to) a.a(parcel, bS, to.CREATOR);
                    hashSet.add(Integer.valueOf(46));
                    toVar9 = toVar11;
                    break;
                case com.google.android.play.R.styleable.Theme_selectableItemBackground /*47*/:
                    str27 = a.p(parcel, bS);
                    hashSet.add(Integer.valueOf(47));
                    break;
                case com.google.android.play.R.styleable.Theme_selectableItemBackgroundBorderless /*48*/:
                    str28 = a.p(parcel, bS);
                    hashSet.add(Integer.valueOf(48));
                    break;
                case com.google.android.play.R.styleable.Theme_dividerVertical /*49*/:
                    str29 = a.p(parcel, bS);
                    hashSet.add(Integer.valueOf(49));
                    break;
                case com.google.android.play.R.styleable.Theme_dividerHorizontal /*50*/:
                    toVar11 = (to) a.a(parcel, bS, to.CREATOR);
                    hashSet.add(Integer.valueOf(50));
                    toVar10 = toVar11;
                    break;
                case com.google.android.play.R.styleable.Theme_activityChooserViewStyle /*51*/:
                    str30 = a.p(parcel, bS);
                    hashSet.add(Integer.valueOf(51));
                    break;
                case com.google.android.play.R.styleable.Theme_toolbarStyle /*52*/:
                    str31 = a.p(parcel, bS);
                    hashSet.add(Integer.valueOf(52));
                    break;
                case com.google.android.play.R.styleable.Theme_toolbarNavigationButtonStyle /*53*/:
                    str32 = a.p(parcel, bS);
                    hashSet.add(Integer.valueOf(53));
                    break;
                case com.google.android.play.R.styleable.Theme_popupMenuStyle /*54*/:
                    str33 = a.p(parcel, bS);
                    hashSet.add(Integer.valueOf(54));
                    break;
                case com.google.android.play.R.styleable.Theme_popupWindowStyle /*55*/:
                    str34 = a.p(parcel, bS);
                    hashSet.add(Integer.valueOf(55));
                    break;
                case com.google.android.play.R.styleable.Theme_editTextColor /*56*/:
                    str35 = a.p(parcel, bS);
                    hashSet.add(Integer.valueOf(56));
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new to(hashSet, i, toVar, list, toVar2, str, str2, str3, list2, i2, list3, toVar3, list4, str4, str5, toVar4, str6, str7, str8, list5, str9, str10, str11, str12, str13, str14, str15, str16, str17, toVar5, str18, str19, str20, str21, toVar6, d, toVar7, d2, str22, toVar8, list6, str23, str24, str25, str26, toVar9, str27, str28, str29, toVar10, str30, str31, str32, str33, str34, str35);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public to[] jH(int i) {
        return new to[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return jH(x0);
    }
}
