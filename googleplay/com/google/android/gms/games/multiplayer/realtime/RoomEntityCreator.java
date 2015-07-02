package com.google.android.gms.games.multiplayer.realtime;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.games.multiplayer.ParticipantEntity;
import com.google.android.wallet.instrumentmanager.R;
import java.util.ArrayList;

public class RoomEntityCreator implements Creator<RoomEntity> {
    static void a(RoomEntity roomEntity, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.a(parcel, 1, roomEntity.getRoomId(), false);
        b.c(parcel, 1000, roomEntity.getVersionCode());
        b.a(parcel, 2, roomEntity.getCreatorId(), false);
        b.a(parcel, 3, roomEntity.getCreationTimestamp());
        b.c(parcel, 4, roomEntity.getStatus());
        b.a(parcel, 5, roomEntity.getDescription(), false);
        b.c(parcel, 6, roomEntity.getVariant());
        b.a(parcel, 7, roomEntity.getAutoMatchCriteria(), false);
        b.d(parcel, 8, roomEntity.getParticipants(), false);
        b.c(parcel, 9, roomEntity.getAutoMatchWaitEstimateSeconds());
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return eU(x0);
    }

    public RoomEntity eU(Parcel parcel) {
        int i = 0;
        ArrayList arrayList = null;
        int bT = a.bT(parcel);
        long j = 0;
        Bundle bundle = null;
        int i2 = 0;
        String str = null;
        int i3 = 0;
        String str2 = null;
        String str3 = null;
        int i4 = 0;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    str3 = a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    str2 = a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    j = a.i(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    i3 = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                    str = a.p(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                    i2 = a.g(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                    bundle = a.r(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                    arrayList = a.c(parcel, bS, ParticipantEntity.CREATOR);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoTextColor /*9*/:
                    i = a.g(parcel, bS);
                    break;
                case 1000:
                    i4 = a.g(parcel, bS);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new RoomEntity(i4, str3, str2, j, i3, str, i2, bundle, arrayList, i);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public RoomEntity[] ha(int i) {
        return new RoomEntity[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return ha(x0);
    }
}
