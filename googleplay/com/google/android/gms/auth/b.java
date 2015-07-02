package com.google.android.gms.auth;

import android.app.PendingIntent;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.wallet.instrumentmanager.R;

public class b implements Creator<RecoveryDecision> {
    static void a(RecoveryDecision recoveryDecision, Parcel parcel, int i) {
        int bU = com.google.android.gms.common.internal.safeparcel.b.bU(parcel);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1, recoveryDecision.mVersionCode);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 2, recoveryDecision.recoveryIntent, i, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 3, recoveryDecision.showRecoveryInterstitial);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 4, recoveryDecision.isRecoveryInfoNeeded);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 5, recoveryDecision.isRecoveryInterstitialAllowed);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 6, recoveryDecision.recoveryIntentWithoutIntro, i, false);
        com.google.android.gms.common.internal.safeparcel.b.J(parcel, bU);
    }

    public RecoveryDecision[] aF(int i) {
        return new RecoveryDecision[i];
    }

    public RecoveryDecision ab(Parcel parcel) {
        PendingIntent pendingIntent = null;
        boolean z = false;
        int bT = a.bT(parcel);
        boolean z2 = false;
        boolean z3 = false;
        PendingIntent pendingIntent2 = null;
        int i = 0;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    pendingIntent2 = (PendingIntent) a.a(parcel, bS, PendingIntent.CREATOR);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    z3 = a.c(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    z2 = a.c(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                    z = a.c(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                    pendingIntent = (PendingIntent) a.a(parcel, bS, PendingIntent.CREATOR);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new RecoveryDecision(i, pendingIntent2, z3, z2, z, pendingIntent);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return ab(x0);
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return aF(x0);
    }
}
