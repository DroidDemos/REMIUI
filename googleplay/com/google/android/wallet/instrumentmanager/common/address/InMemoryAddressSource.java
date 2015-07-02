package com.google.android.wallet.instrumentmanager.common.address;

import android.text.TextUtils;
import android.util.Log;
import com.google.android.wallet.instrumentmanager.common.util.ArrayUtils;
import com.google.android.wallet.instrumentmanager.common.util.ProtoUtils;
import com.google.location.country.Postaladdress.PostalAddress;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.TreeMap;

public abstract class InMemoryAddressSource implements AddressSource {
    ArrayList<PostalAddress> mAddresses;
    private final HashMap<String, ArrayList<PostalAddress>> mMergedAddressesByFields;
    private final String mName;

    protected abstract ArrayList<PostalAddress> getAddresses() throws Throwable;

    protected InMemoryAddressSource(String name) {
        this.mName = name;
        this.mMergedAddressesByFields = new HashMap();
    }

    synchronized void initializeIfNecessary() {
        if (this.mAddresses == null) {
            ArrayList<PostalAddress> addresses;
            try {
                addresses = getAddresses();
            } catch (Throwable e) {
                Log.e(getName(), "Could not retrieve addresses", e);
                addresses = null;
            }
            if (addresses != null) {
                this.mAddresses = AddressUtils.mergeAddresses(addresses, null);
            } else {
                this.mAddresses = new ArrayList();
            }
        }
    }

    ArrayList<PostalAddress> getMergedAddressesByRemainingFields(char[] remainingFields) {
        ArrayList<PostalAddress> mergedAddresses;
        String remainingFieldsKey = remainingFields != null ? new String(remainingFields) : "*";
        synchronized (this.mMergedAddressesByFields) {
            mergedAddresses = (ArrayList) this.mMergedAddressesByFields.get(remainingFieldsKey);
            if (mergedAddresses == null) {
                mergedAddresses = AddressUtils.mergeAddresses(this.mAddresses, remainingFields);
                int length = mergedAddresses.size();
                for (int i = 0; i < length; i++) {
                    PostalAddress address = (PostalAddress) mergedAddresses.get(i);
                    if (address.addressLine.length == 1) {
                        address.addressLine = (String[]) ArrayUtils.appendToArray(address.addressLine, "");
                    }
                }
                this.mMergedAddressesByFields.put(remainingFieldsKey, mergedAddresses);
            }
        }
        return mergedAddresses;
    }

    public final String getName() {
        return this.mName;
    }

    private boolean allowMatchingTermOnly(char addressField) {
        return addressField == 'N';
    }

    public final List<AddressSourceResult> getAddresses(CharSequence prefix, char addressField, char[] remainingFields, int regionCode, String languageCode) {
        initializeIfNecessary();
        if (this.mAddresses.isEmpty()) {
            return Collections.emptyList();
        }
        List<AddressSourceResult> addresses = new ArrayList();
        String sourceName = getName();
        String countryNameCode = RegionCode.toCountryCode(regionCode);
        ArrayList<PostalAddress> mergedAddresses = getMergedAddressesByRemainingFields(remainingFields);
        TreeMap<String, Boolean> additionalMatchedTerms = new TreeMap(String.CASE_INSENSITIVE_ORDER);
        boolean matchingTermOnlyAllowed = allowMatchingTermOnly(addressField);
        int length = mergedAddresses.size();
        for (int i = 0; i < length; i++) {
            PostalAddress address = (PostalAddress) mergedAddresses.get(i);
            if (address != null) {
                String formattedAddressValue = AddressFormatter.formatAddressValue(address, addressField);
                if (SourceUtils.startsWithOrContainsWordPrefix(formattedAddressValue, prefix)) {
                    boolean copied = false;
                    if (regionCode != 0) {
                        int addressCountry;
                        if (TextUtils.isEmpty(address.countryNameCode)) {
                            addressCountry = RegionCode.getUnknown();
                        } else {
                            addressCountry = RegionCode.safeToRegionCode(address.countryNameCode);
                        }
                        if (addressCountry == 0 || addressCountry == RegionCode.getUnknown()) {
                            if (regionCode != RegionCode.getUnknown()) {
                                if (null == null) {
                                    address = (PostalAddress) ProtoUtils.copyFrom(address);
                                }
                                address.countryNameCode = countryNameCode;
                                copied = true;
                            }
                        } else if (addressCountry != regionCode) {
                            if (matchingTermOnlyAllowed && !additionalMatchedTerms.containsKey(formattedAddressValue)) {
                                additionalMatchedTerms.put(formattedAddressValue, Boolean.valueOf(true));
                            }
                        }
                    }
                    if (!TextUtils.isEmpty(languageCode)) {
                        if (TextUtils.isEmpty(address.languageCode)) {
                            if (!copied) {
                                address = (PostalAddress) ProtoUtils.copyFrom(address);
                            }
                            address.languageCode = languageCode;
                        } else if (!AddressUtils.isSameLanguage(address.languageCode, languageCode)) {
                            if (matchingTermOnlyAllowed && !additionalMatchedTerms.containsKey(formattedAddressValue)) {
                                additionalMatchedTerms.put(formattedAddressValue, Boolean.valueOf(true));
                            }
                        }
                    }
                    additionalMatchedTerms.put(formattedAddressValue, Boolean.valueOf(false));
                    addresses.add(new AddressSourceResult(formattedAddressValue, address, AddressFormatter.formatAddress(address, AddressSourceResult.NEW_LINE_REPLACEMENT_SEPARATOR, remainingFields, AddressSourceResult.EXCLUDED_ADDRESS_FIELDS), sourceName));
                }
            }
        }
        for (Entry<String, Boolean> entry : additionalMatchedTerms.entrySet()) {
            if (((Boolean) entry.getValue()).booleanValue()) {
                addresses.add(new AddressSourceResult((String) entry.getKey(), sourceName));
            }
        }
        Collections.sort(addresses, AddressSourceResult.DEFAULT_COMPARATOR);
        return addresses;
    }

    public final PostalAddress getAddress(String reference, String languageCode) {
        throw new UnsupportedOperationException(getName() + " does not use reference identifiers");
    }
}
