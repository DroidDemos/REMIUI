package com.android.i18n.addressinput;

import com.google.android.wallet.instrumentmanager.R;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

class FormatInterpreter {
    private final String mDefaultFormat;
    private final FormOptions mFormOptions;

    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$android$i18n$addressinput$AddressField;

        static {
            $SwitchMap$com$android$i18n$addressinput$AddressField = new int[AddressField.values().length];
            try {
                $SwitchMap$com$android$i18n$addressinput$AddressField[AddressField.STREET_ADDRESS.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$android$i18n$addressinput$AddressField[AddressField.COUNTRY.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$android$i18n$addressinput$AddressField[AddressField.ADMIN_AREA.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$android$i18n$addressinput$AddressField[AddressField.LOCALITY.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$com$android$i18n$addressinput$AddressField[AddressField.DEPENDENT_LOCALITY.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$com$android$i18n$addressinput$AddressField[AddressField.RECIPIENT.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                $SwitchMap$com$android$i18n$addressinput$AddressField[AddressField.ORGANIZATION.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                $SwitchMap$com$android$i18n$addressinput$AddressField[AddressField.POSTAL_CODE.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
        }
    }

    FormatInterpreter(FormOptions options) {
        Util.checkNotNull(RegionDataConstants.getCountryFormatMap(), "null country name map not allowed");
        Util.checkNotNull(options);
        this.mFormOptions = options;
        this.mDefaultFormat = getJsonValue("ZZ", AddressDataKey.FMT);
        Util.checkNotNull(this.mDefaultFormat, "null default format not allowed");
    }

    List<AddressField> getAddressFieldOrder(ScriptType scriptType, String regionCode) {
        Util.checkNotNull(scriptType);
        Util.checkNotNull(regionCode);
        List<AddressField> fieldOrder = new ArrayList();
        for (String substring : getFormatSubStrings(scriptType, regionCode)) {
            if (substring.matches("%.") && !substring.equals("%n")) {
                fieldOrder.add(AddressField.of(substring.charAt(1)));
            }
        }
        overrideFieldOrder(regionCode, fieldOrder);
        List<AddressField> finalFieldOrder = new ArrayList();
        for (AddressField field : fieldOrder) {
            if (field == AddressField.STREET_ADDRESS) {
                finalFieldOrder.add(AddressField.ADDRESS_LINE_1);
                finalFieldOrder.add(AddressField.ADDRESS_LINE_2);
            } else {
                finalFieldOrder.add(field);
            }
        }
        return finalFieldOrder;
    }

    private void overrideFieldOrder(String regionCode, List<AddressField> fieldOrder) {
        if (this.mFormOptions.getCustomFieldOrder(regionCode) != null) {
            final Map<AddressField, Integer> fieldPriority = new HashMap();
            int i = 0;
            for (AddressField field : this.mFormOptions.getCustomFieldOrder(regionCode)) {
                fieldPriority.put(field, Integer.valueOf(i));
                i++;
            }
            List<AddressField> union = new ArrayList();
            List<Integer> slots = new ArrayList();
            i = 0;
            for (AddressField field2 : fieldOrder) {
                if (fieldPriority.containsKey(field2)) {
                    union.add(field2);
                    slots.add(Integer.valueOf(i));
                }
                i++;
            }
            Collections.sort(union, new Comparator<AddressField>() {
                public int compare(AddressField o1, AddressField o2) {
                    return ((Integer) fieldPriority.get(o1)).intValue() - ((Integer) fieldPriority.get(o2)).intValue();
                }
            });
            for (int j = 0; j < union.size(); j++) {
                fieldOrder.set(((Integer) slots.get(j)).intValue(), union.get(j));
            }
        }
    }

    List<String> getEnvelopeAddress(AddressData address, String defaultCountry) {
        String normalizedStr;
        Util.checkNotNull(address, "null input address not allowed");
        String regionCode = address.getPostalCountry();
        if (!AddressWidget.isValidRegionCode(regionCode)) {
            regionCode = defaultCountry;
        }
        String lc = address.getLanguageCode();
        ScriptType scriptType = ScriptType.LOCAL;
        if (lc != null) {
            scriptType = Util.isExplicitLatinScript(lc) ? ScriptType.LATIN : ScriptType.LOCAL;
        }
        List<String> lines = new ArrayList();
        StringBuilder currentLine = new StringBuilder();
        for (String substr : getFormatSubStrings(scriptType, regionCode)) {
            if (substr.equals("%n")) {
                normalizedStr = removeAllRedundantSpaces(currentLine.toString());
                if (normalizedStr.length() > 0) {
                    lines.add(normalizedStr);
                    currentLine.setLength(0);
                }
            } else if (substr.startsWith("%")) {
                char c = substr.charAt(1);
                AddressField field = AddressField.of(c);
                Util.checkNotNull(field, "null address field for character " + c);
                String value = null;
                switch (AnonymousClass2.$SwitchMap$com$android$i18n$addressinput$AddressField[field.ordinal()]) {
                    case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                        value = Util.joinAndSkipNulls("\n", address.getAddressLine1(), address.getAddressLine2());
                        break;
                    case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                        value = address.getAdministrativeArea();
                        break;
                    case R.styleable.WalletImFormEditText_required /*4*/:
                        value = address.getLocality();
                        break;
                    case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                        value = address.getDependentLocality();
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                        value = address.getRecipient();
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                        value = address.getOrganization();
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                        value = address.getPostalCode();
                        break;
                }
                if (value != null) {
                    currentLine.append(value);
                }
            } else {
                currentLine.append(substr);
            }
        }
        normalizedStr = removeAllRedundantSpaces(currentLine.toString());
        if (normalizedStr.length() > 0) {
            lines.add(normalizedStr);
        }
        return lines;
    }

    private List<String> getFormatSubStrings(ScriptType scriptType, String regionCode) {
        String formatString = getFormatString(scriptType, regionCode);
        List<String> parts = new ArrayList();
        boolean escaped = false;
        for (char c : formatString.toCharArray()) {
            if (escaped) {
                escaped = false;
                if ("%n".equals("%" + c)) {
                    parts.add("%n");
                } else {
                    Util.checkNotNull(AddressField.of(c), "Unrecognized character '" + c + "' in format pattern: " + formatString);
                    parts.add("%" + c);
                }
            } else if (c == '%') {
                escaped = true;
            } else {
                parts.add(c + "");
            }
        }
        return parts;
    }

    private String removeAllRedundantSpaces(String str) {
        return str.trim().replaceAll(" +", " ");
    }

    private String getFormatString(ScriptType scriptType, String regionCode) {
        return scriptType == ScriptType.LOCAL ? getJsonValue(regionCode, AddressDataKey.FMT) : getJsonValue(regionCode, AddressDataKey.LFMT);
    }

    private String getJsonValue(String regionCode, AddressDataKey key) {
        Util.checkNotNull(regionCode);
        String jsonString = (String) RegionDataConstants.getCountryFormatMap().get(regionCode);
        if (jsonString == null) {
            return this.mDefaultFormat;
        }
        try {
            JSONObject jsonObj = new JSONObject(new JSONTokener(jsonString));
            if (jsonObj.has(key.name().toLowerCase())) {
                return jsonObj.getString(key.name().toLowerCase());
            }
            return this.mDefaultFormat;
        } catch (JSONException e) {
            throw new RuntimeException("Invalid json for region code " + regionCode + ": " + jsonString);
        }
    }
}
