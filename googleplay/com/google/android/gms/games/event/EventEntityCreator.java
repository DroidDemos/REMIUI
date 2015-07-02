package com.google.android.gms.games.event;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.PlayerEntity;
import com.google.android.wallet.instrumentmanager.R;

public class EventEntityCreator implements Creator<EventEntity> {
    static void a(EventEntity eventEntity, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.a(parcel, 1, eventEntity.getEventId(), false);
        b.c(parcel, 1000, eventEntity.getVersionCode());
        b.a(parcel, 2, eventEntity.getName(), false);
        b.a(parcel, 3, eventEntity.getDescription(), false);
        b.a(parcel, 4, eventEntity.getIconImageUri(), i, false);
        b.a(parcel, 5, eventEntity.getIconImageUrl(), false);
        b.a(parcel, 6, eventEntity.getPlayer(), i, false);
        b.a(parcel, 7, eventEntity.getValue());
        b.a(parcel, 8, eventEntity.getFormattedValue(), false);
        b.a(parcel, 9, eventEntity.isVisible());
        b.J(parcel, bU);
    }

    public EventEntity createFromParcel(Parcel parcel) {
        boolean z = false;
        String str = null;
        int bT = a.bT(parcel);
        long j = 0;
        Player player = null;
        String str2 = null;
        Uri uri = null;
        String str3 = null;
        String str4 = null;
        String str5 = null;
        int i = 0;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    str5 = a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    str4 = a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    str3 = a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    uri = (Uri) a.a(parcel, bS, Uri.CREATOR);
                    break;
                case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                    str2 = a.p(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                    player = (PlayerEntity) a.a(parcel, bS, PlayerEntity.CREATOR);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                    j = a.i(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                    str = a.p(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoTextColor /*9*/:
                    z = a.c(parcel, bS);
                    break;
                case 1000:
                    i = a.g(parcel, bS);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new EventEntity(i, str5, str4, str3, uri, str2, player, j, str, z);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public EventEntity[] newArray(int size) {
        return new EventEntity[size];
    }
}
