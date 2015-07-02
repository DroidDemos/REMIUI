package com.google.android.wallet.instrumentmanager.common.address;

import android.text.TextUtils;
import com.google.location.country.Postaladdress.PostalAddress;
import java.util.Comparator;

public class AddressSourceResult {
    public static final Comparator<AddressSourceResult> DEFAULT_COMPARATOR;
    public static final char[] EXCLUDED_ADDRESS_FIELDS;
    public static final String NEW_LINE_REPLACEMENT_SEPARATOR;
    public final PostalAddress address;
    public final CharSequence description;
    public final String matchingTerm;
    public final String reference;
    public final String sourceName;

    static {
        EXCLUDED_ADDRESS_FIELDS = new char[]{'R'};
        NEW_LINE_REPLACEMENT_SEPARATOR = null;
        DEFAULT_COMPARATOR = new Comparator<AddressSourceResult>() {
            public int compare(AddressSourceResult lhs, AddressSourceResult rhs) {
                int matchingTerm = String.CASE_INSENSITIVE_ORDER.compare(lhs.matchingTerm, rhs.matchingTerm);
                if (matchingTerm != 0) {
                    return matchingTerm;
                }
                if (!(lhs.description == null || rhs.description == null)) {
                    int description = String.CASE_INSENSITIVE_ORDER.compare(lhs.description.toString(), rhs.description.toString());
                    if (description != 0) {
                        return description;
                    }
                }
                return 0;
            }
        };
    }

    public AddressSourceResult(String matchingTermAndDescription, String sourceName) {
        this(matchingTermAndDescription, (CharSequence) matchingTermAndDescription, sourceName, null);
    }

    public AddressSourceResult(String matchingTerm, PostalAddress address, CharSequence description, String sourceName) {
        if (TextUtils.isEmpty(sourceName)) {
            throw new IllegalArgumentException("source name should not be empty");
        }
        this.matchingTerm = matchingTerm;
        this.address = address;
        this.description = description;
        this.sourceName = sourceName;
        this.reference = null;
    }

    public AddressSourceResult(String matchingTerm, CharSequence description, String sourceName, String reference) {
        if (TextUtils.isEmpty(sourceName)) {
            throw new IllegalArgumentException("source name should not be empty");
        }
        this.matchingTerm = matchingTerm;
        this.address = null;
        this.description = description;
        this.sourceName = sourceName;
        this.reference = reference;
    }
}
