package com.google.android.finsky.billing.creditcard;

import android.text.TextUtils;
import com.google.android.finsky.utils.Lists;
import java.util.Arrays;
import java.util.List;

public enum CreditCardType {
    JCB(27, 3, new String[]{"352800-358999"}, new int[]{4, 4, 4, 4}),
    DISCOVER(4, 3, new String[]{"6011", "650"}, new int[]{4, 4, 4, 4}),
    AMEX(3, 4, new String[]{"34", "37"}, new int[]{4, 6, 5}),
    MC(2, 3, new String[]{"51-55"}, new int[]{4, 4, 4, 4}),
    VISA(1, 3, new String[]{"4"}, new int[]{4, 4, 4, 4});
    
    public final int cvcLength;
    public final int[] groupLengths;
    public final int length;
    public final String[] numberPrefixRanges;
    public final int protobufType;

    public static CreditCardType getTypeForProtobufType(int protobufType) {
        for (CreditCardType cardType : values()) {
            if (cardType.protobufType == protobufType) {
                return cardType;
            }
        }
        return null;
    }

    public static CreditCardType getTypeForNumber(String number) {
        for (CreditCardType cardType : values()) {
            if (cardType.isValidNumber(number)) {
                return cardType;
            }
        }
        return null;
    }

    public static CreditCardType getTypeForPrefix(String prefix) {
        for (CreditCardType cardType : values()) {
            if (cardType.isValidPrefix(prefix)) {
                return cardType;
            }
        }
        return null;
    }

    private CreditCardType(int protobufType, int cvcLength, String[] prefixRanges, int[] groupLengths) {
        this.protobufType = protobufType;
        this.length = arraySum(groupLengths);
        this.cvcLength = cvcLength;
        this.numberPrefixRanges = prefixRanges;
        this.groupLengths = groupLengths;
    }

    public boolean isValidNumber(String number) {
        return hasValidLength(number) && hasValidChecksum(number) && isValidPrefix(number);
    }

    public boolean isValidPrefix(String prefix) {
        if (!TextUtils.isEmpty(prefix)) {
            for (String split : this.numberPrefixRanges) {
                String[] parts = split.split("-", 2);
                String commonPrefix = parts[0];
                if (parts.length == 2) {
                    commonPrefix = parts[0].substring(0, parts[0].length() - parts[1].length());
                }
                if (prefix.length() >= commonPrefix.length() && commonPrefix.equals(prefix.substring(0, commonPrefix.length()))) {
                    int start = Integer.parseInt(parts[0]);
                    int end = start;
                    if (parts.length == 2) {
                        end = Integer.parseInt(commonPrefix + parts[1]);
                    }
                    if (prefix.length() >= parts[0].length()) {
                        int numberPrefix = Integer.parseInt(prefix.substring(0, parts[0].length()));
                        if (numberPrefix >= start && numberPrefix <= end) {
                            return true;
                        }
                    }
                    continue;
                }
            }
        }
        return false;
    }

    protected boolean hasValidChecksum(String number) {
        if (TextUtils.isEmpty(number)) {
            return false;
        }
        int chk = 0;
        int timesTwo = 0;
        for (int i = number.length() - 1; i >= 0; i--) {
            int digit = Integer.parseInt(String.valueOf(number.charAt(i)));
            digit += timesTwo * digit;
            chk += (int) (((double) digit) + Math.floor((double) (digit / 10)));
            timesTwo = 1 - timesTwo;
        }
        if (chk % 10 == 0) {
            return true;
        }
        return false;
    }

    public boolean hasValidLength(String number) {
        return number.length() == this.length;
    }

    public String formatNumber(String number) {
        int inputLength = number.length();
        int index = 0;
        List<String> groups = Lists.newArrayList();
        int i = 0;
        while (i < this.groupLengths.length && this.groupLengths[i] + index <= inputLength) {
            groups.add(number.substring(index, this.groupLengths[i] + index));
            index += this.groupLengths[i];
            i++;
        }
        StringBuilder result = new StringBuilder(TextUtils.join(" ", groups));
        if (index < inputLength && groups.size() < this.groupLengths.length) {
            if (groups.size() > 0) {
                result.append(' ');
            }
            result.append(number.substring(index, inputLength));
        }
        return result.toString();
    }

    public String concealNumber(String number) {
        int concealCount = Math.min(number.length(), this.length - 4);
        char[] stars = new char[concealCount];
        Arrays.fill(stars, '\u2022');
        String concealed = new String(stars);
        if (concealCount < number.length()) {
            concealed = concealed + number.substring(concealCount);
        }
        return formatNumber(concealed);
    }

    public static String normalizeNumber(String number) {
        return number.replace(" ", "");
    }

    public String limitLength(String number) {
        return number.substring(0, Math.min(this.length, number.length()));
    }

    private static int arraySum(int[] array) {
        int sum = 0;
        for (int x : array) {
            sum += x;
        }
        return sum;
    }

    public static int getMaxCvcLength() {
        int max = Integer.MIN_VALUE;
        for (CreditCardType ccDef : values()) {
            max = Math.max(max, ccDef.cvcLength);
        }
        return max;
    }
}
