package com.google.android.wallet.instrumentmanager.common.address;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseArray;
import com.google.android.play.R;
import com.google.location.country.Postaladdress.PostalAddress;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Locale;
import java.util.UnknownFormatConversionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONObject;

public final class AddressUtils {
    private static final char[] ALL_ADDRESS_FIELDS;
    private static final Pattern ID_SEPARATOR;
    public static Pattern LANGUAGE_CODE_SEPARATORS;
    private static final Pattern LANGUAGE_SEPARATOR;
    private static final Pattern POSTAL_CODE_NUMERIC_CHARS;
    private static final Pattern POSTAL_CODE_STATIC_VALUE;

    static {
        ID_SEPARATOR = Pattern.compile("/");
        LANGUAGE_SEPARATOR = Pattern.compile("--");
        ALL_ADDRESS_FIELDS = AddressField.values();
        POSTAL_CODE_NUMERIC_CHARS = Pattern.compile("(\\\\d|\\d|[^\\^\\w])");
        POSTAL_CODE_STATIC_VALUE = Pattern.compile("^[\\w \\-]+$");
        LANGUAGE_CODE_SEPARATORS = Pattern.compile("[_-]");
    }

    public static int[] stringArrayToRegionCodeArray(String[] regionCodeIds) {
        if (regionCodeIds == null) {
            return null;
        }
        int length = regionCodeIds.length;
        int[] regionCodes = new int[length];
        for (int i = 0; i < length; i++) {
            regionCodes[i] = RegionCode.safeToRegionCode(regionCodeIds[i]);
        }
        return regionCodes;
    }

    public static String getAddressData(JSONObject jsonObject, String addressDataKey) {
        if (jsonObject == null || addressDataKey == null) {
            return null;
        }
        return jsonObject.optString(addressDataKey, null);
    }

    public static String[] getAddressDataArray(JSONObject jsonObject, String addressDataKey) {
        String raw = getAddressData(jsonObject, addressDataKey);
        if (raw == null) {
            return null;
        }
        return raw.split("~");
    }

    public static int[] scrubAndSortRegionCodes(int[] regionCodes) {
        String EMPTY_LABEL = "";
        if (regionCodes == null) {
            return null;
        }
        int regionCode;
        int i;
        final SparseArray<String> labels = new SparseArray(274);
        labels.put(RegionCode.getUnknown(), "");
        ArrayList<Integer> validRegionCodes = new ArrayList(regionCodes.length);
        for (int regionCode2 : regionCodes) {
            if (!(regionCode2 == 0 || regionCode2 == RegionCode.getUnknown())) {
                validRegionCodes.add(Integer.valueOf(regionCode2));
            }
        }
        int numValidRegionCodes = validRegionCodes.size();
        Iterator i$ = validRegionCodes.iterator();
        while (i$.hasNext()) {
            regionCode2 = ((Integer) i$.next()).intValue();
            String label = getDisplayCountryForDefaultLocale(regionCode2);
            if (TextUtils.isEmpty(label)) {
                Log.w("AddressUtils", "Region code '" + regionCode2 + "' without label");
                label = "";
            }
            labels.put(regionCode2, label);
        }
        Collections.sort(validRegionCodes, new Comparator<Integer>() {
            public int compare(Integer lhs, Integer rhs) {
                if (lhs == rhs) {
                    return 0;
                }
                return ((String) labels.get(lhs.intValue())).compareTo((String) labels.get(rhs.intValue()));
            }
        });
        int newLength = 0;
        int last = 0;
        for (i = 0; i < numValidRegionCodes; i++) {
            regionCode2 = ((Integer) validRegionCodes.get(i)).intValue();
            if (regionCode2 != last) {
                last = regionCode2;
                newLength++;
            }
        }
        last = 0;
        int[] result = new int[newLength];
        int j = 0;
        for (i = 0; i < numValidRegionCodes; i++) {
            regionCode2 = ((Integer) validRegionCodes.get(i)).intValue();
            if (regionCode2 != last) {
                result[j] = regionCode2;
                last = regionCode2;
                j++;
            }
        }
        return result;
    }

    public static String makeAddressFieldLabel(Context context, char addressField, JSONObject countryData) {
        switch (addressField) {
            case R.styleable.Theme_dividerVertical /*49*/:
                return context.getString(com.google.android.wallet.instrumentmanager.R.string.wallet_im_address_field_address_line_1);
            case R.styleable.Theme_dividerHorizontal /*50*/:
                return context.getString(com.google.android.wallet.instrumentmanager.R.string.wallet_im_address_field_address_line_2);
            case R.styleable.Theme_activityChooserViewStyle /*51*/:
                return context.getString(com.google.android.wallet.instrumentmanager.R.string.wallet_im_address_field_address_line_3);
            case R.styleable.Theme_listPreferredItemHeightLarge /*65*/:
                return context.getString(com.google.android.wallet.instrumentmanager.R.string.wallet_im_address_field_address_line_1);
            case R.styleable.Theme_listPreferredItemPaddingRight /*67*/:
                return context.getString(com.google.android.wallet.instrumentmanager.R.string.wallet_im_address_field_locality);
            case R.styleable.Theme_dropDownListViewStyle /*68*/:
                return context.getString(com.google.android.wallet.instrumentmanager.R.string.wallet_im_address_field_dependent_locality);
            case R.styleable.Theme_colorAccent /*78*/:
                throw new IllegalArgumentException("Recipient labels should be read from an AddressForm proto.");
            case R.styleable.Theme_colorControlNormal /*79*/:
                return context.getString(com.google.android.wallet.instrumentmanager.R.string.wallet_im_address_field_organization);
            case R.styleable.Theme_colorButtonNormal /*82*/:
                return context.getString(com.google.android.wallet.instrumentmanager.R.string.wallet_im_address_field_country);
            case R.styleable.Theme_colorSwitchThumbNormal /*83*/:
                String stateNameType = getAddressData(countryData, "state_name_type");
                if ("province".equals(stateNameType)) {
                    return context.getString(com.google.android.wallet.instrumentmanager.R.string.wallet_im_address_field_admin_area_province);
                }
                if ("state".equals(stateNameType)) {
                    return context.getString(com.google.android.wallet.instrumentmanager.R.string.wallet_im_address_field_admin_area_state);
                }
                if ("area".equals(stateNameType)) {
                    return context.getString(com.google.android.wallet.instrumentmanager.R.string.wallet_im_address_field_admin_area_area);
                }
                if ("county".equals(stateNameType)) {
                    return context.getString(com.google.android.wallet.instrumentmanager.R.string.wallet_im_address_field_admin_area_county);
                }
                if ("department".equals(stateNameType)) {
                    return context.getString(com.google.android.wallet.instrumentmanager.R.string.wallet_im_address_field_admin_area_department);
                }
                if ("district".equals(stateNameType)) {
                    return context.getString(com.google.android.wallet.instrumentmanager.R.string.wallet_im_address_field_admin_area_district);
                }
                if ("do_si".equals(stateNameType)) {
                    return context.getString(com.google.android.wallet.instrumentmanager.R.string.wallet_im_address_field_admin_area_do_si);
                }
                if ("emirate".equals(stateNameType)) {
                    return context.getString(com.google.android.wallet.instrumentmanager.R.string.wallet_im_address_field_admin_area_emirate);
                }
                if ("island".equals(stateNameType)) {
                    return context.getString(com.google.android.wallet.instrumentmanager.R.string.wallet_im_address_field_admin_area_island);
                }
                if ("parish".equals(stateNameType)) {
                    return context.getString(com.google.android.wallet.instrumentmanager.R.string.wallet_im_address_field_admin_area_parish);
                }
                if ("prefecture".equals(stateNameType)) {
                    return context.getString(com.google.android.wallet.instrumentmanager.R.string.wallet_im_address_field_admin_area_prefecture);
                }
                if ("region".equals(stateNameType)) {
                    return context.getString(com.google.android.wallet.instrumentmanager.R.string.wallet_im_address_field_admin_area_region);
                }
                return context.getString(com.google.android.wallet.instrumentmanager.R.string.wallet_im_address_field_admin_area_province);
            case 'X':
                return context.getString(com.google.android.wallet.instrumentmanager.R.string.wallet_im_address_field_sorting_code);
            case 'Z':
                String zipNameType = getAddressData(countryData, "zip_name_type");
                if ("postal".equals(zipNameType)) {
                    return context.getString(com.google.android.wallet.instrumentmanager.R.string.wallet_im_address_field_postal_code);
                }
                if ("zip".equals(zipNameType)) {
                    return context.getString(com.google.android.wallet.instrumentmanager.R.string.wallet_im_address_field_zip_code);
                }
                return context.getString(com.google.android.wallet.instrumentmanager.R.string.wallet_im_address_field_postal_code);
            default:
                return null;
        }
    }

    public static int getAddressFieldViewId(char addressField) {
        switch (addressField) {
            case R.styleable.Theme_dividerVertical /*49*/:
                return com.google.android.wallet.instrumentmanager.R.id.address_field_address_line_1;
            case R.styleable.Theme_dividerHorizontal /*50*/:
                return com.google.android.wallet.instrumentmanager.R.id.address_field_address_line_2;
            case R.styleable.Theme_activityChooserViewStyle /*51*/:
                return com.google.android.wallet.instrumentmanager.R.id.address_field_address_line_3;
            case R.styleable.Theme_listPreferredItemHeightLarge /*65*/:
                return com.google.android.wallet.instrumentmanager.R.id.address_field_address_line_1;
            case R.styleable.Theme_listPreferredItemPaddingRight /*67*/:
                return com.google.android.wallet.instrumentmanager.R.id.address_field_locality;
            case R.styleable.Theme_dropDownListViewStyle /*68*/:
                return com.google.android.wallet.instrumentmanager.R.id.address_field_dependent_locality;
            case R.styleable.Theme_colorAccent /*78*/:
                return com.google.android.wallet.instrumentmanager.R.id.address_field_recipient;
            case R.styleable.Theme_colorControlNormal /*79*/:
                return com.google.android.wallet.instrumentmanager.R.id.address_field_organization;
            case R.styleable.Theme_colorButtonNormal /*82*/:
                return com.google.android.wallet.instrumentmanager.R.id.address_field_country;
            case R.styleable.Theme_colorSwitchThumbNormal /*83*/:
                return com.google.android.wallet.instrumentmanager.R.id.address_field_admin_area;
            case 'X':
                return com.google.android.wallet.instrumentmanager.R.id.address_field_sorting_code;
            case 'Z':
                return com.google.android.wallet.instrumentmanager.R.id.address_field_postal_code;
            default:
                return 0;
        }
    }

    public static char[] determineAddressFieldsToDisplay(String format) throws UnknownFormatConversionException {
        if (TextUtils.isEmpty(format)) {
            throw new UnknownFormatConversionException("Cannot convert null/empty formats");
        }
        ArrayList<Character> fieldOrder = new ArrayList();
        Iterator i$ = getFormatSubStrings(format).iterator();
        while (i$.hasNext()) {
            String substring = (String) i$.next();
            if (substring.matches("%.") && !substring.equals("%n")) {
                fieldOrder.add(Character.valueOf(substring.charAt(1)));
            }
        }
        ArrayList<Character> finalFieldOrder = new ArrayList();
        i$ = fieldOrder.iterator();
        while (i$.hasNext()) {
            char field = ((Character) i$.next()).charValue();
            if (field == 'A') {
                finalFieldOrder.add(Character.valueOf('1'));
                finalFieldOrder.add(Character.valueOf('2'));
                finalFieldOrder.add(Character.valueOf('3'));
            } else {
                finalFieldOrder.add(Character.valueOf(field));
            }
        }
        char[] fields = new char[finalFieldOrder.size()];
        for (int i = 0; i < fields.length; i++) {
            fields[i] = ((Character) finalFieldOrder.get(i)).charValue();
        }
        return fields;
    }

    public static boolean isAddressFieldRequired(char addressField, JSONObject countryData) {
        if (addressField == 'N') {
            return true;
        }
        String required = getAddressData(countryData, "require");
        if (TextUtils.isEmpty(required)) {
            return false;
        }
        char field = addressField;
        if (addressField == '1') {
            field = 'A';
        }
        return required.contains(String.valueOf(field));
    }

    public static int getRegionCodeFromAddressData(JSONObject data) {
        if (data == null) {
            return 0;
        }
        String id = getAddressData(data, "id");
        if (id == null) {
            return 0;
        }
        String[] idPieces = ID_SEPARATOR.split(id);
        switch (idPieces.length) {
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                return RegionCode.safeToRegionCode(LANGUAGE_SEPARATOR.split(idPieces[1])[0]);
            default:
                throw new IllegalArgumentException("Invalid address data id: " + id);
        }
    }

    public static Pattern getPostalCodeRegexpPattern(JSONObject data) {
        if (data == null) {
            return null;
        }
        String zip = getAddressData(data, "zip");
        if (TextUtils.isEmpty(zip)) {
            return null;
        }
        String id = getAddressData(data, "id");
        if (TextUtils.isEmpty(id)) {
            return null;
        }
        switch (ID_SEPARATOR.split(id).length) {
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                return Pattern.compile(zip, 2);
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                return getPostalCodeRegexpPatternForAdminArea(zip);
            default:
                return null;
        }
    }

    public static Pattern getPostalCodeRegexpPatternForAdminArea(String zipStartsWithRegEx) {
        if (TextUtils.isEmpty(zipStartsWithRegEx)) {
            return null;
        }
        return Pattern.compile("(" + zipStartsWithRegEx + ").*", 2);
    }

    public static boolean doesCountryUseNumericPostalCode(JSONObject countryData) {
        if (countryData == null) {
            return false;
        }
        return doesPostalCodeRegexIndicateNumericPostalCode(getAddressData(countryData, "zip"));
    }

    static boolean doesPostalCodeRegexIndicateNumericPostalCode(String zip) {
        if (!TextUtils.isEmpty(zip) && POSTAL_CODE_NUMERIC_CHARS.matcher(zip).replaceAll("").length() == 0) {
            return true;
        }
        return false;
    }

    public static String getDefaultPostalCodeForCountry(JSONObject countryData) {
        if (countryData == null) {
            return null;
        }
        String zip = getAddressData(countryData, "zip");
        if (doesZipCodeOnlyAllowOneOption(zip)) {
            return zip;
        }
        return null;
    }

    static boolean doesZipCodeOnlyAllowOneOption(String zip) {
        if (TextUtils.isEmpty(zip)) {
            return false;
        }
        return POSTAL_CODE_STATIC_VALUE.matcher(zip).matches();
    }

    public static String getAdminAreaForPostalCode(JSONObject countryData, String postalCode) {
        if (countryData == null || TextUtils.isEmpty(postalCode)) {
            return null;
        }
        return getAdminAreasForPostalCodes(countryData, new String[]{postalCode})[0];
    }

    public static String[] getAdminAreasForPostalCodes(JSONObject countryData, String[] postalCodes) {
        if (postalCodes == null) {
            return null;
        }
        String[] adminAreas = new String[postalCodes.length];
        Pattern countryZip = getPostalCodeRegexpPattern(countryData);
        if (countryZip == null) {
            return adminAreas;
        }
        String[] subZips = getAddressDataArray(countryData, "sub_zips");
        if (subZips == null || subZips.length == 0) {
            return adminAreas;
        }
        for (int i = 0; i < postalCodes.length; i++) {
            String postalCode = postalCodes[i];
            if (!TextUtils.isEmpty(postalCode) && countryZip.matcher(postalCode).matches()) {
                int bestMatchIndex = -1;
                int bestMatchScore = 0;
                int length = subZips.length;
                for (int j = 0; j < length; j++) {
                    Matcher matcher = getPostalCodeRegexpPatternForAdminArea(subZips[j]).matcher(postalCode);
                    if (matcher.matches()) {
                        int score = matcher.group(1).length();
                        if (bestMatchIndex == -1 || score > bestMatchScore) {
                            bestMatchIndex = j;
                            bestMatchScore = score;
                        }
                    }
                }
                if (bestMatchIndex >= 0) {
                    String[] subKeys = getAddressDataArray(countryData, "sub_keys");
                    if (subKeys != null && bestMatchIndex < subKeys.length) {
                        adminAreas[i] = subKeys[bestMatchIndex];
                    }
                }
            }
        }
        return adminAreas;
    }

    public static ArrayList<PostalAddress> mergeAddresses(Collection<PostalAddress> addresses, char[] addressFields) {
        if (addresses == null) {
            return null;
        }
        if (addressFields == null) {
            addressFields = ALL_ADDRESS_FIELDS;
        } else {
            for (int i = 0; i < addressFields.length; i++) {
                if (!AddressField.exists(addressFields[i])) {
                    addressFields[i] = '\u0000';
                }
            }
        }
        ArrayList<PostalAddress> result = new ArrayList(addresses);
        int iSubset = result.size() - 1;
        while (iSubset >= 0) {
            int iSuperset = result.size() - 1;
            while (iSuperset >= 0) {
                if (iSubset != iSuperset && isSubsetOf((PostalAddress) result.get(iSubset), (PostalAddress) result.get(iSuperset), addressFields)) {
                    result.remove(iSubset);
                    break;
                }
                iSuperset--;
            }
            iSubset--;
        }
        return result;
    }

    static boolean isSubsetOf(PostalAddress subset, PostalAddress superset, char[] addressFields) {
        if (subset == null || superset == null || addressFields == null) {
            return false;
        }
        boolean foundAtLeastOneMatchingField = false;
        for (char addressField : addressFields) {
            if (addressField != '\u0000') {
                String subsetValue = AddressFormatter.formatAddressValue(subset, addressField);
                if (TextUtils.isEmpty(subsetValue)) {
                    continue;
                } else if (!subsetValue.equals(AddressFormatter.formatAddressValue(superset, addressField))) {
                    return false;
                } else {
                    foundAtLeastOneMatchingField = true;
                }
            }
        }
        return foundAtLeastOneMatchingField;
    }

    public static boolean shouldUseLatinScript(JSONObject data, String languageCode) {
        boolean z = true;
        if (data == null || TextUtils.isEmpty(languageCode)) {
            return false;
        }
        if (!data.has("lname") && !data.has("sub_lnames") && !data.has("lfmt")) {
            return false;
        }
        String addressDataLanguage = getAddressData(data, "lang");
        if (TextUtils.isEmpty(addressDataLanguage)) {
            return shouldUseLatinScript(getRegionCodeFromAddressData(data), languageCode);
        }
        if (isSameLanguage(addressDataLanguage, Locale.ENGLISH.getLanguage())) {
            return true;
        }
        if (isSameLanguage(addressDataLanguage, languageCode)) {
            z = false;
        }
        return z;
    }

    public static String getAlternativeLanguageCode(JSONObject data, String languageCode) {
        String[] languages = getAddressDataArray(data, "languages");
        if (languages == null || languages.length <= 1) {
            return null;
        }
        String lang = getAddressData(data, "lang");
        if (TextUtils.isEmpty(lang)) {
            return null;
        }
        if (isSameLanguage(lang, languageCode)) {
            return null;
        }
        for (String supportedLang : languages) {
            if (isSameLanguage(supportedLang, languageCode)) {
                return supportedLang;
            }
        }
        return null;
    }

    private static ArrayList<String> getFormatSubStrings(String formatString) throws UnknownFormatConversionException {
        ArrayList<String> parts = new ArrayList();
        boolean escaped = false;
        for (char c : formatString.toCharArray()) {
            if (escaped) {
                escaped = false;
                if ("%n".equals("%" + c)) {
                    parts.add("%n");
                } else if (AddressField.exists(c)) {
                    parts.add("%" + c);
                } else {
                    throw new UnknownFormatConversionException("Cannot determine AddressField for '" + c + "'");
                }
            } else if (c == '%') {
                escaped = true;
            } else {
                parts.add(Character.toString(c));
            }
        }
        return parts;
    }

    public static String getDisplayCountryForDefaultLocale(int regionCode) {
        Locale defaultLocale = Locale.getDefault();
        return new Locale(defaultLocale.getLanguage(), RegionCode.toCountryCode(regionCode), defaultLocale.getVariant()).getDisplayCountry();
    }

    public static boolean isSameLanguage(String languageCode1, String languageCode2) {
        if (TextUtils.isEmpty(languageCode1) || TextUtils.isEmpty(languageCode2)) {
            return false;
        }
        return LANGUAGE_CODE_SEPARATORS.split(languageCode1)[0].equalsIgnoreCase(LANGUAGE_CODE_SEPARATORS.split(languageCode2)[0]);
    }

    public static boolean shouldUseLatinScript(int regionCode, String languageCode) {
        if (TextUtils.isEmpty(languageCode)) {
            return false;
        }
        String regionCodeLanguage = getDefaultLanguageForNonLatinRegionCode(regionCode);
        if (TextUtils.isEmpty(regionCodeLanguage) || isSameLanguage(regionCodeLanguage, languageCode)) {
            return false;
        }
        return true;
    }

    public static String getDefaultLanguageForNonLatinRegionCode(int regionCode) {
        switch (regionCode) {
            case R.styleable.Theme_buttonBarStyle /*45*/:
                return "hy";
            case 110:
            case 267:
            case 431:
            case 663:
                return "zh";
            case 336:
                return "ja";
            case 368:
            case 370:
                return "ko";
            case 648:
                return "th";
            case 718:
                return "vi";
            default:
                return null;
        }
    }
}
