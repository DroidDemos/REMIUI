package com.google.android.wallet.instrumentmanager.common.address;

import com.google.location.country.Postaladdress.PostalAddress;
import java.util.ArrayList;

public class LocalAddressSource extends InMemoryAddressSource {
    private final ArrayList<PostalAddress> mRawAddresses;

    public LocalAddressSource(ArrayList<PostalAddress> addresses) {
        super("LocalAddressSource");
        this.mRawAddresses = addresses;
    }

    protected ArrayList<PostalAddress> getAddresses() throws Throwable {
        return this.mRawAddresses;
    }
}
