package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class h implements Creator<Device> {
    static void a(Device device, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.a(parcel, 1, device.getManufacturer(), false);
        b.c(parcel, 1000, device.getVersionCode());
        b.a(parcel, 2, device.getModel(), false);
        b.a(parcel, 3, device.getVersion(), false);
        b.a(parcel, 4, device.lA(), false);
        b.c(parcel, 5, device.getType());
        b.c(parcel, 6, device.lx());
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return dP(x0);
    }

    public Device dP(Parcel parcel) {
        int i = 0;
        String str = null;
        int bT = a.bT(parcel);
        int i2 = 0;
        String str2 = null;
        String str3 = null;
        String str4 = null;
        int i3 = 0;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    str4 = a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    str3 = a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    str2 = a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    str = a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                    i2 = a.g(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                    i = a.g(parcel, bS);
                    break;
                case 1000:
                    i3 = a.g(parcel, bS);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new Device(i3, str4, str3, str2, str, i2, i);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public Device[] fD(int i) {
        return new Device[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return fD(x0);
    }
}
