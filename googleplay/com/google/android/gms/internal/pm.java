package com.google.android.gms.internal;

import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.location.places.PlaceType;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.wallet.instrumentmanager.R;
import java.util.List;

public class pm implements Creator<pl> {
    static void a(pl plVar, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.a(parcel, 1, plVar.getId(), false);
        b.a(parcel, 2, plVar.pG(), false);
        b.a(parcel, 3, plVar.pI(), i, false);
        b.a(parcel, 4, plVar.getLatLng(), i, false);
        b.a(parcel, 5, plVar.getLevelNumber());
        b.a(parcel, 6, plVar.getViewport(), i, false);
        b.a(parcel, 7, plVar.pH(), false);
        b.a(parcel, 8, plVar.getWebsiteUri(), i, false);
        b.a(parcel, 9, plVar.isPermanentlyClosed());
        b.a(parcel, 10, plVar.getRating());
        b.c(parcel, 11, plVar.getPriceLevel());
        b.a(parcel, 12, plVar.pE());
        b.d(parcel, 13, plVar.getTypes(), false);
        b.a(parcel, 14, plVar.getAddress(), false);
        b.a(parcel, 15, plVar.getPhoneNumber(), false);
        b.c(parcel, 17, plVar.pD(), false);
        b.a(parcel, 16, plVar.pC(), false);
        b.c(parcel, 1000, plVar.mVersionCode);
        b.a(parcel, 19, plVar.getName(), false);
        b.a(parcel, 18, plVar.pF());
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return fV(x0);
    }

    public pl fV(Parcel parcel) {
        int bT = a.bT(parcel);
        int i = 0;
        String str = null;
        List list = null;
        Bundle bundle = null;
        String str2 = null;
        String str3 = null;
        String str4 = null;
        String str5 = null;
        List list2 = null;
        LatLng latLng = null;
        float f = 0.0f;
        LatLngBounds latLngBounds = null;
        String str6 = null;
        Uri uri = null;
        boolean z = false;
        float f2 = 0.0f;
        int i2 = 0;
        long j = 0;
        boolean z2 = false;
        pn pnVar = null;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    str = a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    bundle = a.r(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    pnVar = (pn) a.a(parcel, bS, pn.CREATOR);
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    latLng = (LatLng) a.a(parcel, bS, LatLng.CREATOR);
                    break;
                case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                    f = a.l(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                    latLngBounds = (LatLngBounds) a.a(parcel, bS, LatLngBounds.CREATOR);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                    str6 = a.p(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                    uri = (Uri) a.a(parcel, bS, Uri.CREATOR);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoTextColor /*9*/:
                    z = a.c(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                    f2 = a.l(parcel, bS);
                    break;
                case R.styleable.MapAttrs_uiZoomControls /*11*/:
                    i2 = a.g(parcel, bS);
                    break;
                case R.styleable.MapAttrs_uiZoomGestures /*12*/:
                    j = a.i(parcel, bS);
                    break;
                case R.styleable.MapAttrs_useViewLifecycle /*13*/:
                    list = a.c(parcel, bS, PlaceType.CREATOR);
                    break;
                case R.styleable.MapAttrs_zOrderOnTop /*14*/:
                    str3 = a.p(parcel, bS);
                    break;
                case R.styleable.MapAttrs_uiMapToolbar /*15*/:
                    str4 = a.p(parcel, bS);
                    break;
                case com.google.android.play.R.styleable.Toolbar_maxButtonHeight /*16*/:
                    str5 = a.p(parcel, bS);
                    break;
                case com.google.android.play.R.styleable.Toolbar_theme /*17*/:
                    list2 = a.E(parcel, bS);
                    break;
                case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                    z2 = a.c(parcel, bS);
                    break;
                case com.google.android.play.R.styleable.Toolbar_collapseContentDescription /*19*/:
                    str2 = a.p(parcel, bS);
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
            return new pl(i, str, list, bundle, str2, str3, str4, str5, list2, latLng, f, latLngBounds, str6, uri, z, f2, i2, j, z2, pnVar);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public pl[] iq(int i) {
        return new pl[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return iq(x0);
    }
}
