package com.google.android.gms.location.copresence;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.common.people.data.Audience;
import com.google.android.wallet.instrumentmanager.R;

public class c implements Creator<AccessPolicy> {
    static void a(AccessPolicy accessPolicy, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, accessPolicy.getVersionCode());
        b.c(parcel, 2, accessPolicy.pa());
        b.a(parcel, 3, accessPolicy.getName(), false);
        b.a(parcel, 4, accessPolicy.getTimeToLiveMillis());
        b.a(parcel, 5, accessPolicy.getAccessLock(), i, false);
        b.a(parcel, 6, accessPolicy.getAudience(), i, false);
        b.c(parcel, 7, accessPolicy.getDistanceType());
        b.c(parcel, 8, accessPolicy.getCopresenceType());
        b.a(parcel, 9, accessPolicy.getAclResourceId(), i, false);
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return fn(x0);
    }

    public AccessPolicy fn(Parcel parcel) {
        AclResourceId aclResourceId = null;
        int i = 0;
        int bT = a.bT(parcel);
        long j = 0;
        int i2 = 0;
        Audience audience = null;
        AccessLock accessLock = null;
        String str = null;
        int i3 = 0;
        int i4 = 0;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i4 = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    i3 = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    str = a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    j = a.i(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                    accessLock = (AccessLock) a.a(parcel, bS, AccessLock.CREATOR);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                    audience = (Audience) a.a(parcel, bS, Audience.CREATOR);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                    i2 = a.g(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                    i = a.g(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoTextColor /*9*/:
                    aclResourceId = (AclResourceId) a.a(parcel, bS, AclResourceId.CREATOR);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new AccessPolicy(i4, i3, str, j, accessLock, audience, i2, i, aclResourceId);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public AccessPolicy[] hE(int i) {
        return new AccessPolicy[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return hE(x0);
    }
}
