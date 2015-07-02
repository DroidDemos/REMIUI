package com.google.android.wallet.instrumentmanager.common.address;

import java.util.Arrays;
import java.util.HashSet;

public final class AddressField {
    private static final char[] ALL_ADDRESS_FIELDS;
    private static final HashSet<Character> sFields;

    static {
        ALL_ADDRESS_FIELDS = new char[]{'S', 'C', 'N', 'O', '1', '2', '3', 'D', 'Z', 'X', 'A', 'U', 'F', 'P', 'T', 'B', 'R'};
        sFields = new HashSet(ALL_ADDRESS_FIELDS.length);
        for (char addressField : ALL_ADDRESS_FIELDS) {
            sFields.add(Character.valueOf(addressField));
        }
    }

    public static boolean exists(char field) {
        return sFields.contains(Character.valueOf(field));
    }

    public static char[] values() {
        return Arrays.copyOf(ALL_ADDRESS_FIELDS, ALL_ADDRESS_FIELDS.length);
    }

    public static int count() {
        return ALL_ADDRESS_FIELDS.length;
    }
}
