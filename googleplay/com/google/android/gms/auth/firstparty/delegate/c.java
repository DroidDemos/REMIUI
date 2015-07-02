package com.google.android.gms.auth.firstparty.delegate;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.auth.firstparty.shared.AppDescription;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;
import java.util.List;

public class c implements Creator<SetupAccountWorkflowRequest> {
    static void a(SetupAccountWorkflowRequest setupAccountWorkflowRequest, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, setupAccountWorkflowRequest.version);
        b.a(parcel, 2, setupAccountWorkflowRequest.HX);
        b.a(parcel, 3, setupAccountWorkflowRequest.HY);
        b.c(parcel, 4, setupAccountWorkflowRequest.HZ, false);
        b.a(parcel, 5, setupAccountWorkflowRequest.Ho, false);
        b.a(parcel, 6, setupAccountWorkflowRequest.callingAppDescription, i, false);
        b.a(parcel, 7, setupAccountWorkflowRequest.Ia);
        b.J(parcel, bU);
    }

    public SetupAccountWorkflowRequest aS(Parcel parcel) {
        AppDescription appDescription = null;
        boolean z = false;
        int bT = a.bT(parcel);
        Bundle bundle = new Bundle();
        List list = null;
        boolean z2 = false;
        boolean z3 = false;
        int i = 0;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    z3 = a.c(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    z2 = a.c(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    list = a.E(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                    bundle = a.r(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                    appDescription = (AppDescription) a.a(parcel, bS, AppDescription.CREATOR);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                    z = a.c(parcel, bS);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new SetupAccountWorkflowRequest(i, z3, z2, list, bundle, appDescription, z);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public SetupAccountWorkflowRequest[] bw(int i) {
        return new SetupAccountWorkflowRequest[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return aS(x0);
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return bw(x0);
    }
}
