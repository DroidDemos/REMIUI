package com.google.android.gms.wallet;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.identity.intents.model.CountrySpecification;
import java.util.ArrayList;

public final class ImmediateFullWalletRequest implements SafeParcelable {
    public static final Creator<ImmediateFullWalletRequest> CREATOR;
    boolean aTA;
    boolean aTB;
    boolean aTC;
    CountrySpecification[] aTD;
    ArrayList<CountrySpecification> aTE;
    String aTx;
    int aTy;
    boolean aTz;
    Account account;
    int environment;
    private final int mVersionCode;
    String tP;

    static {
        CREATOR = new g();
    }

    ImmediateFullWalletRequest() {
        this.mVersionCode = 2;
    }

    ImmediateFullWalletRequest(int versionCode, int environment, Account account, String merchantDomain, int feature, boolean phoneNumberRequired, boolean shippingAddressRequired, boolean useMinimalBillingAddress, String sessionId, boolean shouldAllowSaveToChromeOption, CountrySpecification[] allowedShippingCountrySpecifications, ArrayList<CountrySpecification> allowedCountrySpecificationsForShipping) {
        this.mVersionCode = versionCode;
        this.environment = environment;
        this.account = account;
        this.aTx = merchantDomain;
        this.aTy = feature;
        this.aTz = phoneNumberRequired;
        this.aTA = shippingAddressRequired;
        this.aTB = useMinimalBillingAddress;
        this.tP = sessionId;
        this.aTC = shouldAllowSaveToChromeOption;
        this.aTD = allowedShippingCountrySpecifications;
        this.aTE = allowedCountrySpecificationsForShipping;
    }

    public int describeContents() {
        return 0;
    }

    public int getVersionCode() {
        return this.mVersionCode;
    }

    public void writeToParcel(Parcel dest, int flags) {
        g.a(this, dest, flags);
    }
}
