package com.google.android.gms.auth.firstparty.dataservice;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.auth.firstparty.shared.AppDescription;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class al implements Creator<WebSetupConfigRequest> {
    static void a(WebSetupConfigRequest webSetupConfigRequest, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, webSetupConfigRequest.version);
        b.a(parcel, 2, webSetupConfigRequest.callingAppDescription, i, false);
        b.J(parcel, bU);
    }

    public WebSetupConfigRequest aQ(Parcel parcel) {
        int bT = a.bT(parcel);
        int i = 0;
        AppDescription appDescription = null;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    appDescription = (AppDescription) a.a(parcel, bS, AppDescription.CREATOR);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new WebSetupConfigRequest(i, appDescription);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public WebSetupConfigRequest[] bu(int i) {
        return new WebSetupConfigRequest[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return aQ(x0);
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return bu(x0);
    }
}
