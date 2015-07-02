package com.google.android.gms.common.people.data;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.wallet.instrumentmanager.R;

public class b implements Creator<AudienceMember> {
    static void a(AudienceMember audienceMember, Parcel parcel, int i) {
        int bU = com.google.android.gms.common.internal.safeparcel.b.bU(parcel);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1, audienceMember.getType());
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1000, audienceMember.getVersionCode());
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 2, audienceMember.getCircleType());
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 3, audienceMember.getCircleId(), false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 4, audienceMember.getPeopleQualifiedId(), false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 5, audienceMember.getDisplayName(), false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 6, audienceMember.getAvatarUrl(), false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 7, audienceMember.getMetadata(), false);
        com.google.android.gms.common.internal.safeparcel.b.J(parcel, bU);
    }

    public AudienceMember bW(Parcel parcel) {
        int i = 0;
        Bundle bundle = null;
        int bT = a.bT(parcel);
        String str = null;
        String str2 = null;
        String str3 = null;
        String str4 = null;
        int i2 = 0;
        int i3 = 0;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i2 = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    i = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    str4 = a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    str3 = a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                    str2 = a.p(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                    str = a.p(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                    bundle = a.r(parcel, bS);
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
            return new AudienceMember(i3, i2, i, str4, str3, str2, str, bundle);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return bW(x0);
    }

    public AudienceMember[] do(int i) {
        return new AudienceMember[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return do(x0);
    }
}
