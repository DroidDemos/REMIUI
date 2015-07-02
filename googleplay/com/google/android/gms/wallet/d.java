package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class d implements Creator<CreateWalletObjectsRequest> {
    static void a(CreateWalletObjectsRequest createWalletObjectsRequest, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, createWalletObjectsRequest.getVersionCode());
        b.a(parcel, 2, createWalletObjectsRequest.aTj, i, false);
        b.a(parcel, 3, createWalletObjectsRequest.aTk, i, false);
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return hZ(x0);
    }

    public CreateWalletObjectsRequest hZ(Parcel parcel) {
        OfferWalletObject offerWalletObject = null;
        int bT = a.bT(parcel);
        int i = 0;
        LoyaltyWalletObject loyaltyWalletObject = null;
        while (parcel.dataPosition() < bT) {
            LoyaltyWalletObject loyaltyWalletObject2;
            int g;
            OfferWalletObject offerWalletObject2;
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    OfferWalletObject offerWalletObject3 = offerWalletObject;
                    loyaltyWalletObject2 = loyaltyWalletObject;
                    g = a.g(parcel, bS);
                    offerWalletObject2 = offerWalletObject3;
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    g = i;
                    LoyaltyWalletObject loyaltyWalletObject3 = (LoyaltyWalletObject) a.a(parcel, bS, LoyaltyWalletObject.CREATOR);
                    offerWalletObject2 = offerWalletObject;
                    loyaltyWalletObject2 = loyaltyWalletObject3;
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    offerWalletObject2 = (OfferWalletObject) a.a(parcel, bS, OfferWalletObject.CREATOR);
                    loyaltyWalletObject2 = loyaltyWalletObject;
                    g = i;
                    break;
                default:
                    a.b(parcel, bS);
                    offerWalletObject2 = offerWalletObject;
                    loyaltyWalletObject2 = loyaltyWalletObject;
                    g = i;
                    break;
            }
            i = g;
            loyaltyWalletObject = loyaltyWalletObject2;
            offerWalletObject = offerWalletObject2;
        }
        if (parcel.dataPosition() == bT) {
            return new CreateWalletObjectsRequest(i, loyaltyWalletObject, offerWalletObject);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public CreateWalletObjectsRequest[] kU(int i) {
        return new CreateWalletObjectsRequest[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return kU(x0);
    }
}
