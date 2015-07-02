package com.google.android.gms.auth.firstparty.delegate;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.auth.firstparty.shared.AppDescription;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class e implements Creator<UpdateCredentialsWorkflowRequest> {
    static void a(UpdateCredentialsWorkflowRequest updateCredentialsWorkflowRequest, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, updateCredentialsWorkflowRequest.version);
        b.a(parcel, 2, updateCredentialsWorkflowRequest.accountName, false);
        b.a(parcel, 3, updateCredentialsWorkflowRequest.callingAppDescription, i, false);
        b.a(parcel, 4, updateCredentialsWorkflowRequest.Ho, false);
        b.J(parcel, bU);
    }

    public UpdateCredentialsWorkflowRequest aU(Parcel parcel) {
        AppDescription appDescription = null;
        int bT = a.bT(parcel);
        int i = 0;
        Bundle bundle = new Bundle();
        String str = null;
        while (parcel.dataPosition() < bT) {
            AppDescription appDescription2;
            String str2;
            int g;
            Bundle bundle2;
            int bS = a.bS(parcel);
            Bundle bundle3;
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    bundle3 = bundle;
                    appDescription2 = appDescription;
                    str2 = str;
                    g = a.g(parcel, bS);
                    bundle2 = bundle3;
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    g = i;
                    AppDescription appDescription3 = appDescription;
                    str2 = a.p(parcel, bS);
                    bundle2 = bundle;
                    appDescription2 = appDescription3;
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    str2 = str;
                    g = i;
                    bundle3 = bundle;
                    appDescription2 = (AppDescription) a.a(parcel, bS, AppDescription.CREATOR);
                    bundle2 = bundle3;
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    bundle2 = a.r(parcel, bS);
                    appDescription2 = appDescription;
                    str2 = str;
                    g = i;
                    break;
                default:
                    a.b(parcel, bS);
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
            return new UpdateCredentialsWorkflowRequest(i, str, appDescription, bundle);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public UpdateCredentialsWorkflowRequest[] by(int i) {
        return new UpdateCredentialsWorkflowRequest[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return aU(x0);
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return by(x0);
    }
}
