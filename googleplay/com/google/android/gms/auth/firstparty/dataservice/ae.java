package com.google.android.gms.auth.firstparty.dataservice;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class ae implements Creator<ReauthSettingsResponse> {
    static void a(ReauthSettingsResponse reauthSettingsResponse, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, reauthSettingsResponse.version);
        b.c(parcel, 2, reauthSettingsResponse.status);
        b.a(parcel, 3, reauthSettingsResponse.password, i, false);
        b.a(parcel, 4, reauthSettingsResponse.pin, i, false);
        b.J(parcel, bU);
    }

    public ReauthSettingsResponse aJ(Parcel parcel) {
        PinSettings pinSettings = null;
        int i = 0;
        int bT = a.bT(parcel);
        PasswordSettings passwordSettings = null;
        int i2 = 0;
        while (parcel.dataPosition() < bT) {
            PasswordSettings passwordSettings2;
            int i3;
            PinSettings pinSettings2;
            int bS = a.bS(parcel);
            PinSettings pinSettings3;
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    pinSettings3 = pinSettings;
                    passwordSettings2 = passwordSettings;
                    i3 = i;
                    i = a.g(parcel, bS);
                    pinSettings2 = pinSettings3;
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    i = i2;
                    PasswordSettings passwordSettings3 = passwordSettings;
                    i3 = a.g(parcel, bS);
                    pinSettings2 = pinSettings;
                    passwordSettings2 = passwordSettings3;
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    i3 = i;
                    i = i2;
                    pinSettings3 = pinSettings;
                    passwordSettings2 = (PasswordSettings) a.a(parcel, bS, PasswordSettings.CREATOR);
                    pinSettings2 = pinSettings3;
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    pinSettings2 = (PinSettings) a.a(parcel, bS, PinSettings.CREATOR);
                    passwordSettings2 = passwordSettings;
                    i3 = i;
                    i = i2;
                    break;
                default:
                    a.b(parcel, bS);
                    pinSettings2 = pinSettings;
                    passwordSettings2 = passwordSettings;
                    i3 = i;
                    i = i2;
                    break;
            }
            i2 = i;
            i = i3;
            passwordSettings = passwordSettings2;
            pinSettings = pinSettings2;
        }
        if (parcel.dataPosition() == bT) {
            return new ReauthSettingsResponse(i2, i, passwordSettings, pinSettings);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public ReauthSettingsResponse[] bn(int i) {
        return new ReauthSettingsResponse[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return aJ(x0);
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return bn(x0);
    }
}
