package com.google.android.gms.auth.firstparty.delegate;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.auth.firstparty.shared.AppDescription;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class a implements Creator<ConfirmCredentialsWorkflowRequest> {
    static void a(ConfirmCredentialsWorkflowRequest confirmCredentialsWorkflowRequest, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, confirmCredentialsWorkflowRequest.version);
        b.a(parcel, 2, confirmCredentialsWorkflowRequest.accountName, false);
        b.a(parcel, 3, confirmCredentialsWorkflowRequest.callingAppDescription, i, false);
        b.a(parcel, 4, confirmCredentialsWorkflowRequest.Ho, false);
        b.J(parcel, bU);
    }

    public ConfirmCredentialsWorkflowRequest aR(Parcel parcel) {
        AppDescription appDescription = null;
        int bT = com.google.android.gms.common.internal.safeparcel.a.bT(parcel);
        int i = 0;
        Bundle bundle = new Bundle();
        String str = null;
        while (parcel.dataPosition() < bT) {
            AppDescription appDescription2;
            String str2;
            int g;
            Bundle bundle2;
            int bS = com.google.android.gms.common.internal.safeparcel.a.bS(parcel);
            Bundle bundle3;
            switch (com.google.android.gms.common.internal.safeparcel.a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    bundle3 = bundle;
                    appDescription2 = appDescription;
                    str2 = str;
                    g = com.google.android.gms.common.internal.safeparcel.a.g(parcel, bS);
                    bundle2 = bundle3;
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    g = i;
                    AppDescription appDescription3 = appDescription;
                    str2 = com.google.android.gms.common.internal.safeparcel.a.p(parcel, bS);
                    bundle2 = bundle;
                    appDescription2 = appDescription3;
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    str2 = str;
                    g = i;
                    bundle3 = bundle;
                    appDescription2 = (AppDescription) com.google.android.gms.common.internal.safeparcel.a.a(parcel, bS, AppDescription.CREATOR);
                    bundle2 = bundle3;
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    bundle2 = com.google.android.gms.common.internal.safeparcel.a.r(parcel, bS);
                    appDescription2 = appDescription;
                    str2 = str;
                    g = i;
                    break;
                default:
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, bS);
                    bundle2 = bundle;
                    appDescription2 = appDescription;
                    str2 = str;
                    g = i;
                    break;
            }
            i = g;
            str = str2;
            appDescription = appDescription2;
            bundle = bundle2;
        }
        if (parcel.dataPosition() == bT) {
            return new ConfirmCredentialsWorkflowRequest(i, str, appDescription, bundle);
        }
        throw new com.google.android.gms.common.internal.safeparcel.a.a("Overread allowed size end=" + bT, parcel);
    }

    public ConfirmCredentialsWorkflowRequest[] bv(int i) {
        return new ConfirmCredentialsWorkflowRequest[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return aR(x0);
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return bv(x0);
    }
}
