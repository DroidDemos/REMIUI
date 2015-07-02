package com.google.android.wallet.instrumentmanager.common.address;

import com.google.location.country.Postaladdress.PostalAddress;
import java.util.List;

public interface AddressSource {
    PostalAddress getAddress(String str, String str2);

    List<AddressSourceResult> getAddresses(CharSequence charSequence, char c, char[] cArr, int i, String str);

    String getName();
}
