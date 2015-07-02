package com.google.android.wallet.instrumentmanager.common.address;

import android.text.TextUtils;
import android.util.Log;
import android.util.SparseBooleanArray;
import com.google.android.play.R;
import com.google.location.country.Postaladdress.PostalAddress;
import java.util.regex.Pattern;

public final class AddressFormatter {
    private static final Pattern FORMATTING_CHARS_PATTERN;

    static String getFormat(int regionCode, boolean latin, String languageCode) {
        String rawI18nFormat;
        switch (regionCode) {
            case R.styleable.Theme_actionModePopupWindowStyle /*36*/:
                rawI18nFormat = "%N%n%O%n%A%n%Z %S";
                break;
            case R.styleable.Theme_textAppearanceLargePopupMenu /*37*/:
            case 442:
                rawI18nFormat = "%N%n%O%n%A%n%C";
                break;
            case R.styleable.Theme_buttonBarStyle /*45*/:
                rawI18nFormat = "%N%n%O%n%A%n%Z%n%C%n%S";
                break;
            case R.styleable.Theme_dividerHorizontal /*50*/:
            case 108:
            case 118:
            case 520:
            case 698:
                rawI18nFormat = "%N%n%O%n%A%n%Z %C%n%S";
                break;
            case R.styleable.Theme_activityChooserViewStyle /*51*/:
            case 97:
            case 205:
            case 245:
            case 424:
            case 432:
            case 535:
            case 685:
            case 691:
            case 713:
                rawI18nFormat = "%N%n%O%n%A%n%C %S %Z";
                break;
            case R.styleable.Theme_toolbarStyle /*52*/:
            case R.styleable.Theme_listPopupWindowStyle /*69*/:
            case 139:
            case 460:
                rawI18nFormat = "%O%n%N%n%A%n%Z %C";
                break;
            case R.styleable.Theme_toolbarNavigationButtonStyle /*53*/:
            case 99:
            case 120:
            case 269:
            case 454:
                rawI18nFormat = "%O%n%N%n%A%n%C %S %Z";
                break;
            case R.styleable.Theme_editTextColor /*56*/:
                rawI18nFormat = "%O%n%N%n%A%nAX-%Z %C%n\u00c5LAND";
                break;
            case R.styleable.Theme_switchStyle /*58*/:
                rawI18nFormat = "%N%n%O%n%A%nAZ %Z %C";
                break;
            case R.styleable.Theme_listPreferredItemHeightLarge /*65*/:
            case R.styleable.Theme_textAppearanceListItemSmall /*71*/:
            case 114:
            case 115:
            case 121:
            case 122:
            case 133:
            case 143:
            case 154:
            case 165:
            case 168:
            case 180:
            case 229:
            case 236:
            case 242:
            case 247:
            case 307:
            case 375:
            case 385:
            case 417:
            case 421:
            case 423:
            case 427:
            case 453:
            case 463:
            case 524:
            case 532:
            case 537:
            case 591:
            case 595:
            case 618:
            case 619:
            case 621:
            case 622:
            case 650:
            case 653:
            case 654:
            case 705:
            case 779:
            case 845:
                rawI18nFormat = "%N%n%O%n%A%n%Z %C";
                break;
            case R.styleable.Theme_dropDownListViewStyle /*68*/:
                rawI18nFormat = "%N%n%O%n%A%n%C - %Z";
                break;
            case R.styleable.Theme_textAppearanceListItem /*70*/:
            case 100:
            case 439:
            case 628:
                rawI18nFormat = "%N%n%O%n%A%n%C %X";
                break;
            case R.styleable.Theme_panelBackground /*72*/:
            case R.styleable.Theme_colorPrimaryDark /*77*/:
            case R.styleable.Theme_colorAccent /*78*/:
            case 107:
            case 300:
            case 335:
            case 360:
            case 386:
            case 403:
            case 436:
            case 438:
            case 464:
            case 474:
            case 609:
                rawI18nFormat = "%N%n%O%n%A%n%C %Z";
                break;
            case R.styleable.Theme_colorPrimary /*76*/:
            case 210:
            case 230:
            case 240:
            case 422:
            case 433:
            case 451:
            case 525:
            case 581:
            case 742:
            case 820:
                rawI18nFormat = "%O%n%N%n%A%n%Z %C %X";
                break;
            case R.styleable.Theme_colorButtonNormal /*82*/:
                rawI18nFormat = "%O%n%N%n%A%n%C-%S%n%Z";
                break;
            case R.styleable.Theme_colorSwitchThumbNormal /*83*/:
            case 111:
            case 366:
                rawI18nFormat = "%N%n%O%n%A%n%C, %S";
                break;
            case 89:
                rawI18nFormat = "%S%n%Z %C %X%n%A%n%O%n%N";
                break;
            case 104:
                rawI18nFormat = "%O%n%N%n%A%nCH-%Z %C";
                break;
            case 105:
                rawI18nFormat = "%N%n%O%n%X %A %C %X";
                break;
            case 110:
                rawI18nFormat = latin ? "%N%n%O%n%A%n%D%n%C%n%S, %Z" : "%Z%n%S%C%D%n%A%n%O%n%N";
                break;
            case 163:
            case 437:
            case 493:
                rawI18nFormat = "%N%n%O%n%A%n%Z%n%C";
                break;
            case 167:
            case 226:
                rawI18nFormat = "%N%n%O%n%A%n%C%n%S%n%Z";
                break;
            case 179:
            case 308:
            case 518:
            case 697:
                rawI18nFormat = "%N%n%O%n%A%n%Z %C %S";
                break;
            case 201:
                rawI18nFormat = "%O%n%N%n%A%nFI-%Z %C";
                break;
            case 203:
            case 243:
            case 301:
            case 303:
            case 526:
            case 616:
            case 643:
                rawI18nFormat = "%N%n%O%n%A%n%X%n%C%n%Z";
                break;
            case 207:
                rawI18nFormat = "%N%n%O%n%A%nFO%Z %C";
                break;
            case 231:
                rawI18nFormat = "%N%n%O%n%A%n%X%n%C%nGUERNSEY%n%Z";
                break;
            case 233:
                rawI18nFormat = "%N%n%O%n%A";
                break;
            case 238:
                rawI18nFormat = "%N%n%O%n%Z %A %C";
                break;
            case 244:
                rawI18nFormat = "%N%n%O%n%A%n%Z- %C";
                break;
            case 267:
                rawI18nFormat = latin ? "%N%n%O%n%A%n%S" : "%S%n%A%n%O%n%N";
                break;
            case 270:
                rawI18nFormat = "%N%n%O%n%A%n%C, %S%n%Z";
                break;
            case 274:
                rawI18nFormat = "%N%n%O%n%A%nHR-%Z %C";
                break;
            case 276:
                rawI18nFormat = "%N%n%O%n%A%nHT%Z %C %X";
                break;
            case 277:
                rawI18nFormat = "%N%n%O%n%C%n%A%n%Z";
                break;
            case 292:
                rawI18nFormat = "%N%n%O%n%A%n%C%n%S %Z";
                break;
            case 293:
            case 513:
            case 611:
            case 718:
                rawI18nFormat = "%N%n%O%n%A%n%C%n%S";
                break;
            case 302:
            case 455:
                rawI18nFormat = "%N%n%O%n%A%n%C %Z%n%S";
                break;
            case 305:
                rawI18nFormat = "%O%n%N%n%A%n%C, %S%n%Z";
                break;
            case 325:
                rawI18nFormat = "%N%n%O%n%A%n%X%n%C%nJERSEY%n%Z";
                break;
            case 333:
                rawI18nFormat = "%N%n%O%n%A%n%C%n%S %X";
                break;
            case 336:
                rawI18nFormat = latin ? "%N%n%O%n%A%n%C, %S%n%Z" : "\u3012%Z%n%S%C%n%A%n%O%n%N";
                break;
            case 357:
            case 395:
            case 634:
            case 833:
                rawI18nFormat = "%N%n%O%n%A%n%C%n%Z";
                break;
            case 359:
                rawI18nFormat = "%Z %C %X%n%A%n%O%n%N";
                break;
            case 361:
                rawI18nFormat = "%N%n%O%n%A%n%S%n%C";
                break;
            case 370:
                rawI18nFormat = latin ? "%N%n%O%n%A%n%D%n%C%n%S%n%Z" : "%S %C%D%n%A%n%O%n%N%n%Z";
                break;
            case 377:
            case 466:
                rawI18nFormat = "%N%n%O%n%A%n%S";
                break;
            case 378:
                rawI18nFormat = "%Z%n%S%n%C%n%A%n%O%n%N";
                break;
            case 393:
                rawI18nFormat = "%O%n%N%n%A%nFL-%Z %C";
                break;
            case 402:
                rawI18nFormat = "%N%n%O%n%A%n%Z %C %X";
                break;
            case 404:
                rawI18nFormat = "%O%n%N%n%A%nLT-%Z %C";
                break;
            case 405:
                rawI18nFormat = "%O%n%N%n%A%nL-%Z %C";
                break;
            case 406:
                rawI18nFormat = "%N%n%O%n%A%n%C, %Z";
                break;
            case 419:
                rawI18nFormat = "%N%n%O%n%A%nMC-%Z %C %X";
                break;
            case 420:
                rawI18nFormat = "%N%n%O%n%A%nMD-%Z %C";
                break;
            case 430:
                rawI18nFormat = "%N%n%O%n%A%n%S %C-%X%n%Z";
                break;
            case 431:
                rawI18nFormat = latin ? "%N%n%O%n%A" : "%A%n%O%n%N";
                break;
            case 440:
                rawI18nFormat = "%N%n%O%n%A%n%D%n%Z %C, %S";
                break;
            case 441:
                rawI18nFormat = "%N%n%O%n%A%n%Z %C, %S";
                break;
            case 457:
                rawI18nFormat = "%N%n%O%n%A%n%Z%n%C, %S";
                break;
            case 519:
                rawI18nFormat = "%N%n%O%n%A%n%C %Z %S";
                break;
            case 523:
                rawI18nFormat = "%N%n%O%n%A%n%C-%Z";
                break;
            case 530:
                rawI18nFormat = "%N%n%O%n%A%n%C PR %Z";
                break;
            case 597:
                rawI18nFormat = "%Z %C  %n%A%n%O%n%N";
                break;
            case 613:
                rawI18nFormat = "%O%n%N%n%A%nSE-%Z %C";
                break;
            case 615:
                rawI18nFormat = "%N%n%O%n%A%nSINGAPORE %Z";
                break;
            case 617:
                rawI18nFormat = "%N%n%O%n%A%nSI- %Z %C";
                break;
            case 623:
                rawI18nFormat = "%N%n%O%n%A%n%C, %S %Z";
                break;
            case 626:
                rawI18nFormat = "%N%n%O%n%A%n%C %X%n%S";
                break;
            case 630:
                rawI18nFormat = "%N%n%O%n%A%n%Z-%C%n%S";
                break;
            case 648:
                rawI18nFormat = latin ? "%N%n%O%n%A%n%D, %C%n%S %Z" : "%N%n%O%n%A%n%D %C%n%S %Z";
                break;
            case 658:
                rawI18nFormat = "%N%n%O%n%A%n%Z %C/%S";
                break;
            case 662:
                rawI18nFormat = "%N%n%O%n%A%n%X%n%C%n%S";
                break;
            case 663:
                rawI18nFormat = latin ? "%N%n%O%n%A%n%C, %S %Z" : "%Z%n%S%C%n%A%n%O%n%N";
                break;
            case 673:
                rawI18nFormat = "%Z %C%n%A%n%O%n%N";
                break;
            case 709:
                rawI18nFormat = "%N%n%O%n%A%n%C %Z, %S";
                break;
            default:
                rawI18nFormat = "%N%n%O%n%A%n%C";
                break;
        }
        StringBuilder i18nFormatWithCountry = new StringBuilder(rawI18nFormat.length() + 4);
        if (latin || !(AddressUtils.isSameLanguage("ja", languageCode) || AddressUtils.isSameLanguage("ko", languageCode) || AddressUtils.isSameLanguage("zh", languageCode))) {
            i18nFormatWithCountry.append(rawI18nFormat);
            i18nFormatWithCountry.append("%n%R");
        } else {
            i18nFormatWithCountry.append("%R%n");
            i18nFormatWithCountry.append(rawI18nFormat);
        }
        return i18nFormatWithCountry.toString();
    }

    public static String formatAddress(PostalAddress address, String newLineReplacementSeparator, char[] includeAddressFields, char[] excludeAddressFields) {
        return formatAddress(address, newLineReplacementSeparator, includeAddressFields, excludeAddressFields, null);
    }

    static {
        FORMATTING_CHARS_PATTERN = Pattern.compile("^[\\-,\\s]+|[\\-,\\s]+$");
    }

    static String formatAddress(PostalAddress address, String newLineReplacementSeparator, char[] includeAddressFields, char[] excludeAddressFields, String format) {
        SparseBooleanArray eligibleAddressFields;
        if (newLineReplacementSeparator == null) {
            newLineReplacementSeparator = "\n";
        }
        if (includeAddressFields == null && excludeAddressFields != null && excludeAddressFields.length > 0) {
            includeAddressFields = AddressField.values();
        }
        if (format == null) {
            int regionCode = RegionCode.safeToRegionCode(address.countryNameCode);
            if (regionCode == 0) {
                regionCode = RegionCode.getUnknown();
            }
            String languageCode = address.languageCode;
            format = getFormat(regionCode, AddressUtils.shouldUseLatinScript(regionCode, languageCode), languageCode);
        }
        StringBuilder formatted = new StringBuilder();
        StringBuilder line = new StringBuilder();
        StringBuilder staticChars = new StringBuilder();
        int newLineReplacementSeparatorLength = newLineReplacementSeparator.length();
        if (includeAddressFields != null) {
            eligibleAddressFields = new SparseBooleanArray(AddressField.count() + 1);
            for (char field : includeAddressFields) {
                if (AddressField.exists(field)) {
                    eligibleAddressFields.put(field, true);
                }
            }
            if (excludeAddressFields != null) {
                for (char field2 : excludeAddressFields) {
                    if (AddressField.exists(field2)) {
                        eligibleAddressFields.put(field2, false);
                    }
                }
            }
        } else {
            eligibleAddressFields = null;
        }
        boolean escaped = false;
        boolean appendedNewLineSeparator = false;
        boolean appendedRecipientName = false;
        boolean appendedOtherField = false;
        boolean appendedStaticLine = false;
        boolean encounteredEmptyValueOnLine = false;
        for (char c : format.replaceAll("%A", "%1%n%2%n%3").toCharArray()) {
            if (escaped) {
                escaped = false;
                if ('n' == c) {
                    boolean appendNewline = false;
                    if (line.length() > 0) {
                        line.append(staticChars);
                        formatted.append(FORMATTING_CHARS_PATTERN.matcher(line).replaceAll(""));
                        appendNewline = true;
                        line.setLength(0);
                    } else if (staticChars.length() > 0 && !encounteredEmptyValueOnLine) {
                        String staticLine = FORMATTING_CHARS_PATTERN.matcher(staticChars).replaceAll("");
                        if (staticLine.length() > 0) {
                            formatted.append(staticLine);
                            appendNewline = true;
                            appendedStaticLine = true;
                        }
                    }
                    if (appendNewline && newLineReplacementSeparatorLength > 0) {
                        formatted.append(newLineReplacementSeparator);
                        appendedNewLineSeparator = true;
                    }
                    encounteredEmptyValueOnLine = false;
                } else if (!AddressField.exists(c)) {
                    Log.w("AddressFormatter", "Could not format AddressField." + c);
                    encounteredEmptyValueOnLine = true;
                } else if (eligibleAddressFields == null || eligibleAddressFields.get(c)) {
                    String value = formatAddressValue(address, c, newLineReplacementSeparator);
                    if (value != null) {
                        value = value.trim();
                    }
                    if (TextUtils.isEmpty(value)) {
                        encounteredEmptyValueOnLine = true;
                    } else {
                        line.append(staticChars.toString());
                        line.append(value);
                        if ('N' == c) {
                            appendedRecipientName = true;
                        } else {
                            appendedOtherField = true;
                        }
                    }
                } else {
                    encounteredEmptyValueOnLine = true;
                }
                staticChars.setLength(0);
            } else if (c == '%') {
                escaped = true;
            } else {
                staticChars.append(c);
            }
        }
        if (line.length() > 0 || !encounteredEmptyValueOnLine) {
            if (line.length() == 0 && staticChars.length() > 0) {
                appendedStaticLine = true;
            }
            line.append(staticChars);
            formatted.append(FORMATTING_CHARS_PATTERN.matcher(line).replaceAll(""));
        } else if (appendedNewLineSeparator) {
            formatted.delete(formatted.length() - newLineReplacementSeparatorLength, formatted.length());
        }
        if ((eligibleAddressFields == null || eligibleAddressFields.get(82)) && (formatted.length() == 0 || !(!appendedRecipientName || appendedOtherField || appendedStaticLine))) {
            String country = formatAddressValue(address, 'R', newLineReplacementSeparator);
            if (!TextUtils.isEmpty(country)) {
                if (formatted.length() > 0 && newLineReplacementSeparatorLength > 0) {
                    formatted.append(newLineReplacementSeparator);
                }
                formatted.append(country);
            }
        }
        return formatted.toString();
    }

    public static String formatAddressValue(PostalAddress address, char addressField) {
        return formatAddressValue(address, addressField, null);
    }

    static String formatAddressValue(PostalAddress address, char addressField, String newLineReplacementSeparator) {
        if (address == null) {
            return null;
        }
        if (newLineReplacementSeparator == null) {
            newLineReplacementSeparator = "\n";
        }
        switch (addressField) {
            case R.styleable.Theme_dividerVertical /*49*/:
                if (address.addressLine.length >= 1) {
                    return address.addressLine[0];
                }
                return null;
            case R.styleable.Theme_dividerHorizontal /*50*/:
                if (address.addressLine.length >= 2) {
                    return address.addressLine[1];
                }
                return null;
            case R.styleable.Theme_activityChooserViewStyle /*51*/:
                if (address.addressLine.length >= 3) {
                    return address.addressLine[2];
                }
                return null;
            case R.styleable.Theme_listPreferredItemHeightLarge /*65*/:
                if (address.addressLine.length > 0) {
                    return TextUtils.join(newLineReplacementSeparator, address.addressLine);
                }
                return null;
            case R.styleable.Theme_listPreferredItemPaddingLeft /*66*/:
                if (TextUtils.isEmpty(address.thoroughfareNumber)) {
                    return null;
                }
                return address.thoroughfareNumber;
            case R.styleable.Theme_listPreferredItemPaddingRight /*67*/:
                if (TextUtils.isEmpty(address.localityName)) {
                    return null;
                }
                return address.localityName;
            case R.styleable.Theme_dropDownListViewStyle /*68*/:
                if (TextUtils.isEmpty(address.dependentLocalityName)) {
                    return null;
                }
                return address.dependentLocalityName;
            case R.styleable.Theme_textAppearanceListItem /*70*/:
                if (TextUtils.isEmpty(address.subPremiseName)) {
                    return null;
                }
                return address.subPremiseName;
            case R.styleable.Theme_colorAccent /*78*/:
                if (TextUtils.isEmpty(address.recipientName)) {
                    return null;
                }
                return address.recipientName;
            case R.styleable.Theme_colorControlNormal /*79*/:
                if (TextUtils.isEmpty(address.firmName)) {
                    return null;
                }
                return address.firmName;
            case R.styleable.Theme_colorControlActivated /*80*/:
                if (TextUtils.isEmpty(address.premiseName)) {
                    return null;
                }
                return address.premiseName;
            case R.styleable.Theme_colorButtonNormal /*82*/:
                if (!TextUtils.isEmpty(address.countryName)) {
                    return address.countryName;
                }
                int regionCode = RegionCode.safeToRegionCode(address.countryNameCode);
                if (regionCode == 0 || RegionCode.getUnknown() == regionCode) {
                    return null;
                }
                return AddressUtils.getDisplayCountryForDefaultLocale(regionCode);
            case R.styleable.Theme_colorSwitchThumbNormal /*83*/:
                if (TextUtils.isEmpty(address.administrativeAreaName)) {
                    return null;
                }
                return address.administrativeAreaName;
            case 'T':
                if (TextUtils.isEmpty(address.thoroughfareName)) {
                    return null;
                }
                return address.thoroughfareName;
            case 'U':
                if (TextUtils.isEmpty(address.subAdministrativeAreaName)) {
                    return null;
                }
                return address.subAdministrativeAreaName;
            case 'X':
                if (TextUtils.isEmpty(address.sortingCode)) {
                    return null;
                }
                return address.sortingCode;
            case 'Z':
                if (TextUtils.isEmpty(address.postalCodeNumber)) {
                    return null;
                }
                return address.postalCodeNumber;
            default:
                return null;
        }
    }
}
