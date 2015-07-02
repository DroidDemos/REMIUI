package com.android.i18n.addressinput;

import java.util.HashMap;
import java.util.Map;

public enum AddressField {
    ADMIN_AREA('S', "state"),
    LOCALITY('C', "city"),
    RECIPIENT('N', "name"),
    ORGANIZATION('O', "organization"),
    ADDRESS_LINE_1('1', "street1"),
    ADDRESS_LINE_2('2', "street2"),
    DEPENDENT_LOCALITY('D'),
    POSTAL_CODE('Z'),
    SORTING_CODE('X'),
    STREET_ADDRESS('A'),
    COUNTRY('R');
    
    private static final Map<Character, AddressField> FIELD_MAPPING;
    private final String mAttributeName;
    private final char mField;

    static {
        FIELD_MAPPING = new HashMap();
        for (AddressField value : values()) {
            FIELD_MAPPING.put(Character.valueOf(value.getChar()), value);
        }
    }

    private AddressField(char field, String attributeName) {
        this.mField = field;
        this.mAttributeName = attributeName;
    }

    private AddressField(char field) {
        this(r2, r3, field, null);
    }

    static AddressField of(char field) {
        return (AddressField) FIELD_MAPPING.get(Character.valueOf(field));
    }

    char getChar() {
        return this.mField;
    }
}
