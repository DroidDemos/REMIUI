package com.google.android.gms.auth.firstparty.dataservice;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.auth.firstparty.shared.AccountCredentials;
import com.google.android.gms.auth.firstparty.shared.CaptchaSolution;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class ah implements Creator<UpdateCredentialsRequest> {
    static void a(UpdateCredentialsRequest updateCredentialsRequest, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, updateCredentialsRequest.version);
        b.a(parcel, 2, updateCredentialsRequest.GO, i, false);
        b.a(parcel, 3, updateCredentialsRequest.Gu, i, false);
        b.J(parcel, bU);
    }

    public UpdateCredentialsRequest aM(Parcel parcel) {
        CaptchaSolution captchaSolution = null;
        int bT = a.bT(parcel);
        int i = 0;
        AccountCredentials accountCredentials = null;
        while (parcel.dataPosition() < bT) {
            AccountCredentials accountCredentials2;
            int g;
            CaptchaSolution captchaSolution2;
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    CaptchaSolution captchaSolution3 = captchaSolution;
                    accountCredentials2 = accountCredentials;
                    g = a.g(parcel, bS);
                    captchaSolution2 = captchaSolution3;
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    g = i;
                    AccountCredentials accountCredentials3 = (AccountCredentials) a.a(parcel, bS, AccountCredentials.CREATOR);
                    captchaSolution2 = captchaSolution;
                    accountCredentials2 = accountCredentials3;
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    captchaSolution2 = (CaptchaSolution) a.a(parcel, bS, CaptchaSolution.CREATOR);
                    accountCredentials2 = accountCredentials;
                    g = i;
                    break;
                default:
                    a.b(parcel, bS);
                    captchaSolution2 = captchaSolution;
                    accountCredentials2 = accountCredentials;
                    g = i;
                    break;
            }
            i = g;
            accountCredentials = accountCredentials2;
            captchaSolution = captchaSolution2;
        }
        if (parcel.dataPosition() == bT) {
            return new UpdateCredentialsRequest(i, accountCredentials, captchaSolution);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public UpdateCredentialsRequest[] bq(int i) {
        return new UpdateCredentialsRequest[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return aM(x0);
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return bq(x0);
    }
}
