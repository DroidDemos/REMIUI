package com.google.android.gms.games.quest;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.games.GameEntity;
import com.google.android.wallet.instrumentmanager.R;
import java.util.ArrayList;

public class QuestEntityCreator implements Creator<QuestEntity> {
    static void a(QuestEntity questEntity, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.a(parcel, 1, questEntity.getGame(), i, false);
        b.a(parcel, 2, questEntity.getQuestId(), false);
        b.a(parcel, 3, questEntity.getAcceptedTimestamp());
        b.a(parcel, 4, questEntity.getBannerImageUri(), i, false);
        b.a(parcel, 5, questEntity.getBannerImageUrl(), false);
        b.a(parcel, 6, questEntity.getDescription(), false);
        b.a(parcel, 7, questEntity.getEndTimestamp());
        b.a(parcel, 8, questEntity.getLastUpdatedTimestamp());
        b.a(parcel, 9, questEntity.getIconImageUri(), i, false);
        b.a(parcel, 10, questEntity.getIconImageUrl(), false);
        b.a(parcel, 12, questEntity.getName(), false);
        b.a(parcel, 13, questEntity.oG());
        b.a(parcel, 14, questEntity.getStartTimestamp());
        b.c(parcel, 15, questEntity.getState());
        b.d(parcel, 17, questEntity.oF(), false);
        b.c(parcel, 16, questEntity.getType());
        b.c(parcel, 1000, questEntity.getVersionCode());
        b.J(parcel, bU);
    }

    public QuestEntity createFromParcel(Parcel parcel) {
        int bT = a.bT(parcel);
        int i = 0;
        GameEntity gameEntity = null;
        String str = null;
        long j = 0;
        Uri uri = null;
        String str2 = null;
        String str3 = null;
        long j2 = 0;
        long j3 = 0;
        Uri uri2 = null;
        String str4 = null;
        String str5 = null;
        long j4 = 0;
        long j5 = 0;
        int i2 = 0;
        int i3 = 0;
        ArrayList arrayList = null;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    gameEntity = (GameEntity) a.a(parcel, bS, GameEntity.CREATOR);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    str = a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    j = a.i(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    uri = (Uri) a.a(parcel, bS, Uri.CREATOR);
                    break;
                case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                    str2 = a.p(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                    str3 = a.p(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                    j2 = a.i(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                    j3 = a.i(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoTextColor /*9*/:
                    uri2 = (Uri) a.a(parcel, bS, Uri.CREATOR);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                    str4 = a.p(parcel, bS);
                    break;
                case R.styleable.MapAttrs_uiZoomGestures /*12*/:
                    str5 = a.p(parcel, bS);
                    break;
                case R.styleable.MapAttrs_useViewLifecycle /*13*/:
                    j4 = a.i(parcel, bS);
                    break;
                case R.styleable.MapAttrs_zOrderOnTop /*14*/:
                    j5 = a.i(parcel, bS);
                    break;
                case R.styleable.MapAttrs_uiMapToolbar /*15*/:
                    i2 = a.g(parcel, bS);
                    break;
                case com.google.android.play.R.styleable.Toolbar_maxButtonHeight /*16*/:
                    i3 = a.g(parcel, bS);
                    break;
                case com.google.android.play.R.styleable.Toolbar_theme /*17*/:
                    arrayList = a.c(parcel, bS, MilestoneEntity.CREATOR);
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
            return new QuestEntity(i, gameEntity, str, j, uri, str2, str3, j2, j3, uri2, str4, str5, j4, j5, i2, i3, arrayList);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public QuestEntity[] newArray(int size) {
        return new QuestEntity[size];
    }
}
