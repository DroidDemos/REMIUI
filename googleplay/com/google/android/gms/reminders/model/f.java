package com.google.android.gms.reminders.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class f implements Creator<g> {
    static void a(g gVar, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, gVar.mVersionCode);
        b.a(parcel, 2, gVar.getTaskId(), i, false);
        b.a(parcel, 3, gVar.getTaskList(), i, false);
        b.a(parcel, 4, gVar.getTitle(), false);
        b.a(parcel, 6, gVar.getDueDate(), i, false);
        b.a(parcel, 7, gVar.getLocation(), i, false);
        b.a(parcel, 8, gVar.getEventDate(), i, false);
        b.a(parcel, 9, gVar.getArchived(), false);
        b.a(parcel, 11, gVar.getDeleted(), false);
        b.a(parcel, 12, gVar.getArchivedTimeMs(), false);
        b.a(parcel, 15, gVar.getLocationSnoozedUntilMs(), false);
        b.a(parcel, 19, gVar.getCreatedTimeMillis(), false);
        b.a(parcel, 23, gVar.getSnoozed(), false);
        b.a(parcel, 22, gVar.getPinned(), false);
        b.a(parcel, 24, gVar.getSnoozedTimeMillis(), false);
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return hm(x0);
    }

    public g hm(Parcel parcel) {
        int bT = a.bT(parcel);
        int i = 0;
        i iVar = null;
        k kVar = null;
        String str = null;
        Long l = null;
        Long l2 = null;
        Boolean bool = null;
        Boolean bool2 = null;
        Boolean bool3 = null;
        Boolean bool4 = null;
        Long l3 = null;
        b bVar = null;
        b bVar2 = null;
        d dVar = null;
        Long l4 = null;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    iVar = (i) a.a(parcel, bS, i.CREATOR);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    kVar = (k) a.a(parcel, bS, k.CREATOR);
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    str = a.p(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                    bVar = (b) a.a(parcel, bS, b.CREATOR);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                    dVar = (d) a.a(parcel, bS, d.CREATOR);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                    bVar2 = (b) a.a(parcel, bS, b.CREATOR);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoTextColor /*9*/:
                    bool = a.d(parcel, bS);
                    break;
                case R.styleable.MapAttrs_uiZoomControls /*11*/:
                    bool2 = a.d(parcel, bS);
                    break;
                case R.styleable.MapAttrs_uiZoomGestures /*12*/:
                    l2 = a.j(parcel, bS);
                    break;
                case R.styleable.MapAttrs_uiMapToolbar /*15*/:
                    l4 = a.j(parcel, bS);
                    break;
                case com.google.android.play.R.styleable.Toolbar_collapseContentDescription /*19*/:
                    l = a.j(parcel, bS);
                    break;
                case com.google.android.play.R.styleable.Theme_actionMenuTextAppearance /*22*/:
                    bool3 = a.d(parcel, bS);
                    break;
                case com.google.android.play.R.styleable.Theme_actionMenuTextColor /*23*/:
                    bool4 = a.d(parcel, bS);
                    break;
                case com.google.android.play.R.styleable.Theme_actionModeStyle /*24*/:
                    l3 = a.j(parcel, bS);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new g(i, iVar, kVar, str, l, l2, bool, bool2, bool3, bool4, l3, bVar, bVar2, dVar, l4);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public g[] jX(int i) {
        return new g[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return jX(x0);
    }
}
