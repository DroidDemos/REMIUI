package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.identity.intents.model.CountrySpecification;
import java.util.ArrayList;

public final class MaskedWalletRequest implements SafeParcelable {
    public static final Creator<MaskedWalletRequest> CREATOR;
    boolean aTA;
    boolean aTB;
    CountrySpecification[] aTD;
    ArrayList<CountrySpecification> aTE;
    String aTg;
    String aTm;
    Cart aTv;
    boolean aTz;
    String aUi;
    String aUj;
    boolean aUk;
    boolean aUl;
    boolean aUm;
    boolean aUn;
    private final int mVersionCode;

    static {
        CREATOR = new l();
    }

    MaskedWalletRequest() {
        this.mVersionCode = 3;
        this.aUm = true;
        this.aUn = true;
    }

    MaskedWalletRequest(int versionCode, String merchantTransactionId, boolean phoneNumberRequired, boolean shippingAddressRequired, boolean useMinimalBillingAddress, String estimatedTotalPrice, String currencyCode, String merchantName, Cart cart, boolean shouldRetrieveWalletObjects, boolean isBillingAgreement, CountrySpecification[] allowedShippingCountrySpecifications, boolean allowPrepaidCard, boolean allowDebitCard, ArrayList<CountrySpecification> allowedCountrySpecificationsForShipping) {
        this.mVersionCode = versionCode;
        this.aTm = merchantTransactionId;
        this.aTz = phoneNumberRequired;
        this.aTA = shippingAddressRequired;
        this.aTB = useMinimalBillingAddress;
        this.aUi = estimatedTotalPrice;
        this.aTg = currencyCode;
        this.aUj = merchantName;
        this.aTv = cart;
        this.aUk = shouldRetrieveWalletObjects;
        this.aUl = isBillingAgreement;
        this.aTD = allowedShippingCountrySpecifications;
        this.aUm = allowPrepaidCard;
        this.aUn = allowDebitCard;
        this.aTE = allowedCountrySpecificationsForShipping;
    }

    public int describeContents() {
        return 0;
    }

    public int getVersionCode() {
        return this.mVersionCode;
    }

    public void writeToParcel(Parcel dest, int flags) {
        l.a(this, dest, flags);
    }
}
