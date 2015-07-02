package com.google.android.gms.common.server.response;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.common.server.response.FastJsonResponse.Field;
import com.google.android.gms.internal.lf;
import com.google.android.wallet.instrumentmanager.R;

public class a implements Creator<Field> {
    static void a(Field field, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, field.getVersionCode());
        b.c(parcel, 2, field.getTypeIn());
        b.a(parcel, 3, field.isTypeInArray());
        b.c(parcel, 4, field.getTypeOut());
        b.a(parcel, 5, field.isTypeOutArray());
        b.a(parcel, 6, field.getOutputFieldName(), false);
        b.c(parcel, 7, field.getSafeParcelableFieldId());
        b.a(parcel, 8, field.jc(), false);
        b.a(parcel, 9, field.jd(), i, false);
        b.J(parcel, bU);
    }

    public Field cb(Parcel parcel) {
        lf lfVar = null;
        int i = 0;
        int bT = com.google.android.gms.common.internal.safeparcel.a.bT(parcel);
        String str = null;
        String str2 = null;
        boolean z = false;
        int i2 = 0;
        boolean z2 = false;
        int i3 = 0;
        int i4 = 0;
        while (parcel.dataPosition() < bT) {
            int bS = com.google.android.gms.common.internal.safeparcel.a.bS(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i4 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    i3 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    z2 = com.google.android.gms.common.internal.safeparcel.a.c(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    i2 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                    z = com.google.android.gms.common.internal.safeparcel.a.c(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                    str2 = com.google.android.gms.common.internal.safeparcel.a.p(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                    i = com.google.android.gms.common.internal.safeparcel.a.g(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                    str = com.google.android.gms.common.internal.safeparcel.a.p(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoTextColor /*9*/:
                    lfVar = (lf) com.google.android.gms.common.internal.safeparcel.a.a(parcel, bS, lf.CREATOR);
                    break;
                default:
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new Field(i4, i3, z2, i2, z, str2, i, str, lfVar);
        }
        throw new com.google.android.gms.common.internal.safeparcel.a.a("Overread allowed size end=" + bT, parcel);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return cb(x0);
    }

    public Field[] du(int i) {
        return new Field[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return du(x0);
    }
}
