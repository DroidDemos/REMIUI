package com.google.android.gms.drive.events;

import android.os.IBinder;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;
import com.google.android.wallet.instrumentmanager.R;
import java.util.List;

public class b implements Creator<CompletionEvent> {
    static void a(CompletionEvent completionEvent, Parcel parcel, int i) {
        int bU = com.google.android.gms.common.internal.safeparcel.b.bU(parcel);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1, completionEvent.mVersionCode);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 2, completionEvent.Yb, i, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 3, completionEvent.Fl, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 4, completionEvent.YL, i, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 5, completionEvent.YM, i, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 6, completionEvent.YN, i, false);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 7, completionEvent.YO, false);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 8, completionEvent.Ob);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 9, completionEvent.YP, false);
        com.google.android.gms.common.internal.safeparcel.b.J(parcel, bU);
    }

    public CompletionEvent cn(Parcel parcel) {
        int i = 0;
        IBinder iBinder = null;
        int bT = a.bT(parcel);
        List list = null;
        MetadataBundle metadataBundle = null;
        ParcelFileDescriptor parcelFileDescriptor = null;
        ParcelFileDescriptor parcelFileDescriptor2 = null;
        String str = null;
        DriveId driveId = null;
        int i2 = 0;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i2 = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    driveId = (DriveId) a.a(parcel, bS, DriveId.CREATOR);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    str = a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    parcelFileDescriptor2 = (ParcelFileDescriptor) a.a(parcel, bS, ParcelFileDescriptor.CREATOR);
                    break;
                case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                    parcelFileDescriptor = (ParcelFileDescriptor) a.a(parcel, bS, ParcelFileDescriptor.CREATOR);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                    metadataBundle = (MetadataBundle) a.a(parcel, bS, MetadataBundle.CREATOR);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                    list = a.E(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                    i = a.g(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoTextColor /*9*/:
                    iBinder = a.q(parcel, bS);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new CompletionEvent(i2, driveId, str, parcelFileDescriptor2, parcelFileDescriptor, metadataBundle, list, i, iBinder);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return cn(x0);
    }

    public CompletionEvent[] dO(int i) {
        return new CompletionEvent[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return dO(x0);
    }
}
