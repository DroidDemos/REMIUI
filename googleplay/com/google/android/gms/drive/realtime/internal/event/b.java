package com.google.android.gms.drive.realtime.internal.event;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.wallet.instrumentmanager.R;
import java.util.List;

public class b implements Creator<ParcelableEvent> {
    static void a(ParcelableEvent parcelableEvent, Parcel parcel, int i) {
        int bU = com.google.android.gms.common.internal.safeparcel.b.bU(parcel);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1, parcelableEvent.mVersionCode);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 2, parcelableEvent.vY, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 3, parcelableEvent.ada, false);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 4, parcelableEvent.adc, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 5, parcelableEvent.adb);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 6, parcelableEvent.ael, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 7, parcelableEvent.aeN, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 8, parcelableEvent.aeO, i, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 9, parcelableEvent.aeP, i, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 10, parcelableEvent.aeQ, i, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 11, parcelableEvent.aeR, i, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 12, parcelableEvent.aeS, i, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 13, parcelableEvent.aeT, i, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 14, parcelableEvent.aeU, i, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 15, parcelableEvent.aeV, i, false);
        com.google.android.gms.common.internal.safeparcel.b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return dw(x0);
    }

    public ParcelableEvent dw(Parcel parcel) {
        int bT = a.bT(parcel);
        int i = 0;
        String str = null;
        String str2 = null;
        List list = null;
        boolean z = false;
        String str3 = null;
        String str4 = null;
        TextInsertedDetails textInsertedDetails = null;
        TextDeletedDetails textDeletedDetails = null;
        ValuesAddedDetails valuesAddedDetails = null;
        ValuesRemovedDetails valuesRemovedDetails = null;
        ValuesSetDetails valuesSetDetails = null;
        ValueChangedDetails valueChangedDetails = null;
        ReferenceShiftedDetails referenceShiftedDetails = null;
        ObjectChangedDetails objectChangedDetails = null;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    str = a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    str2 = a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    list = a.E(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                    z = a.c(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                    str3 = a.p(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                    str4 = a.p(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                    textInsertedDetails = (TextInsertedDetails) a.a(parcel, bS, TextInsertedDetails.CREATOR);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoTextColor /*9*/:
                    textDeletedDetails = (TextDeletedDetails) a.a(parcel, bS, TextDeletedDetails.CREATOR);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                    valuesAddedDetails = (ValuesAddedDetails) a.a(parcel, bS, ValuesAddedDetails.CREATOR);
                    break;
                case R.styleable.MapAttrs_uiZoomControls /*11*/:
                    valuesRemovedDetails = (ValuesRemovedDetails) a.a(parcel, bS, ValuesRemovedDetails.CREATOR);
                    break;
                case R.styleable.MapAttrs_uiZoomGestures /*12*/:
                    valuesSetDetails = (ValuesSetDetails) a.a(parcel, bS, ValuesSetDetails.CREATOR);
                    break;
                case R.styleable.MapAttrs_useViewLifecycle /*13*/:
                    valueChangedDetails = (ValueChangedDetails) a.a(parcel, bS, ValueChangedDetails.CREATOR);
                    break;
                case R.styleable.MapAttrs_zOrderOnTop /*14*/:
                    referenceShiftedDetails = (ReferenceShiftedDetails) a.a(parcel, bS, ReferenceShiftedDetails.CREATOR);
                    break;
                case R.styleable.MapAttrs_uiMapToolbar /*15*/:
                    objectChangedDetails = (ObjectChangedDetails) a.a(parcel, bS, ObjectChangedDetails.CREATOR);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new ParcelableEvent(i, str, str2, list, z, str3, str4, textInsertedDetails, textDeletedDetails, valuesAddedDetails, valuesRemovedDetails, valuesSetDetails, valueChangedDetails, referenceShiftedDetails, objectChangedDetails);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public ParcelableEvent[] fi(int i) {
        return new ParcelableEvent[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return fi(x0);
    }
}
