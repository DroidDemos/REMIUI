package com.google.android.gms.games.quest;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class MilestoneEntityCreator implements Creator<MilestoneEntity> {
    static void a(MilestoneEntity milestoneEntity, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.a(parcel, 1, milestoneEntity.getMilestoneId(), false);
        b.c(parcel, 1000, milestoneEntity.getVersionCode());
        b.a(parcel, 2, milestoneEntity.getCurrentProgress());
        b.a(parcel, 3, milestoneEntity.getTargetProgress());
        b.a(parcel, 4, milestoneEntity.getCompletionRewardData(), false);
        b.c(parcel, 5, milestoneEntity.getState());
        b.a(parcel, 6, milestoneEntity.getEventId(), false);
        b.J(parcel, bU);
    }

    public MilestoneEntity createFromParcel(Parcel parcel) {
        long j = 0;
        int i = 0;
        String str = null;
        int bT = a.bT(parcel);
        byte[] bArr = null;
        long j2 = 0;
        String str2 = null;
        int i2 = 0;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    str2 = a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    j2 = a.i(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    j = a.i(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    bArr = a.s(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                    i = a.g(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                    str = a.p(parcel, bS);
                    break;
                case 1000:
                    i2 = a.g(parcel, bS);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new MilestoneEntity(i2, str2, j2, j, bArr, i, str);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public MilestoneEntity[] newArray(int size) {
        return new MilestoneEntity[size];
    }
}
