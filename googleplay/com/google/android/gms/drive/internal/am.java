package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class am implements Creator<OnDeviceUsagePreferenceResponse> {
    static void a(OnDeviceUsagePreferenceResponse onDeviceUsagePreferenceResponse, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, onDeviceUsagePreferenceResponse.mVersionCode);
        b.a(parcel, 2, onDeviceUsagePreferenceResponse.aaU, i, false);
        b.J(parcel, bU);
    }

    public OnDeviceUsagePreferenceResponse cG(Parcel parcel) {
        int bT = a.bT(parcel);
        int i = 0;
        FileUploadPreferencesImpl fileUploadPreferencesImpl = null;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    fileUploadPreferencesImpl = (FileUploadPreferencesImpl) a.a(parcel, bS, FileUploadPreferencesImpl.CREATOR);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new OnDeviceUsagePreferenceResponse(i, fileUploadPreferencesImpl);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return cG(x0);
    }

    public OnDeviceUsagePreferenceResponse[] eo(int i) {
        return new OnDeviceUsagePreferenceResponse[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return eo(x0);
    }
}
