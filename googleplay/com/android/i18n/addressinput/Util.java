package com.android.i18n.addressinput;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Util {
    private static final Map<String, String> nonLatinLocalLanguageCountries;

    static {
        nonLatinLocalLanguageCountries = new HashMap();
        nonLatinLocalLanguageCountries.put("AM", "hy");
        nonLatinLocalLanguageCountries.put("CN", "zh");
        nonLatinLocalLanguageCountries.put("HK", "zh");
        nonLatinLocalLanguageCountries.put("JP", "ja");
        nonLatinLocalLanguageCountries.put("KP", "ko");
        nonLatinLocalLanguageCountries.put("KR", "ko");
        nonLatinLocalLanguageCountries.put("MO", "zh");
        nonLatinLocalLanguageCountries.put("TH", "th");
        nonLatinLocalLanguageCountries.put("TW", "zh");
        nonLatinLocalLanguageCountries.put("VN", "vi");
    }

    static boolean isExplicitLatinScript(String languageCode) {
        Matcher m = Pattern.compile("\\w{2,3}[-_](\\w{4})").matcher(languageCode.toUpperCase());
        if (m.lookingAt() && m.group(1).equals("LATN")) {
            return true;
        }
        return false;
    }

    static String getLanguageSubtag(String languageCode) {
        Matcher m = Pattern.compile("(\\w{2,3})(?:[-_]\\w{4})?(?:[-_]\\w{2})?").matcher(languageCode);
        if (m.matches()) {
            return m.group(1).toLowerCase();
        }
        return "und";
    }

    static String trimToNull(String originalStr) {
        if (originalStr == null) {
            return null;
        }
        String trimmedString = originalStr.trim();
        if (trimmedString.length() == 0) {
            trimmedString = null;
        }
        return trimmedString;
    }

    static void checkNotNull(Object o) throws NullPointerException {
        checkNotNull(o, "This object should not be null.");
    }

    static void checkNotNull(Object o, String message) throws NullPointerException {
        if (o == null) {
            throw new NullPointerException(message);
        }
    }

    static String joinAndSkipNulls(String separator, String... strings) {
        StringBuilder sb = null;
        for (String s : strings) {
            String s2;
            if (s2 != null) {
                s2 = s2.trim();
                if (s2.length() > 0) {
                    if (sb == null) {
                        sb = new StringBuilder(s2);
                    } else {
                        sb.append(separator).append(s2);
                    }
                }
            }
        }
        return sb == null ? null : sb.toString();
    }

    static Map<String, String> buildNameToKeyMap(String[] keys, String[] names, String[] lnames) {
        if (keys == null) {
            return null;
        }
        int i;
        Map<String, String> nameToKeyMap = new HashMap();
        int keyLength = keys.length;
        for (String k : keys) {
            nameToKeyMap.put(k.toLowerCase(), k);
        }
        if (names != null) {
            if (names.length > keyLength) {
                throw new IllegalStateException("names length (" + names.length + ") is greater than keys length (" + keys.length + ")");
            }
            i = 0;
            while (i < keyLength) {
                if (i < names.length && names[i].length() > 0) {
                    nameToKeyMap.put(names[i].toLowerCase(), keys[i]);
                }
                i++;
            }
        }
        if (lnames == null) {
            return nameToKeyMap;
        }
        if (lnames.length > keyLength) {
            throw new IllegalStateException("lnames length (" + lnames.length + ") is greater than keys length (" + keys.length + ")");
        }
        i = 0;
        while (i < keyLength) {
            if (i < lnames.length && lnames[i].length() > 0) {
                nameToKeyMap.put(lnames[i].toLowerCase(), keys[i]);
            }
            i++;
        }
        return nameToKeyMap;
    }

    static String getWidgetCompatibleLanguageCode(Locale language, String currentCountry) {
        String country = currentCountry.toUpperCase();
        if (nonLatinLocalLanguageCountries.containsKey(country)) {
            String languageTag = language.getLanguage();
            if (!languageTag.equals(nonLatinLocalLanguageCountries.get(country))) {
                StringBuilder languageTagBuilder = new StringBuilder(languageTag);
                languageTagBuilder.append("_latn");
                if (language.getCountry().length() > 0) {
                    languageTagBuilder.append("_");
                    languageTagBuilder.append(language.getCountry());
                }
                return languageTagBuilder.toString();
            }
        }
        return language.toString();
    }
}
