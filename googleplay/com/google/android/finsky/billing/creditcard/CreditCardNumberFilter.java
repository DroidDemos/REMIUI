package com.google.android.finsky.billing.creditcard;

import android.text.InputFilter;
import android.text.Spanned;

public class CreditCardNumberFilter implements InputFilter {
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
        char lastChar = dstart > 0 ? dest.charAt(dstart - 1) : '\u0000';
        int len = end - start;
        if (len == 1) {
            if (isAllowed(source.charAt(start), lastChar)) {
                return null;
            }
            return "";
        } else if (len == 0) {
            return null;
        } else {
            char[] out = new char[len];
            boolean filtered = false;
            for (int i = start; i < end; i++) {
                char c = source.charAt(i);
                if (isAllowed(c, lastChar)) {
                    out[0] = c;
                    lastChar = c;
                } else {
                    filtered = true;
                }
            }
            if (filtered) {
                return new String(out, 0, 0);
            }
            return null;
        }
    }

    private static boolean isNumber(char c) {
        return c >= '0' && c <= '9';
    }

    private static boolean isSeparator(char c) {
        return c == ' ' || c == '-';
    }

    private static boolean isAllowed(char c, char lastChar) {
        return isNumber(c) || (isSeparator(c) && isNumber(lastChar));
    }

    public static String getNumbers(CharSequence cc) {
        StringBuilder sb = new StringBuilder(16);
        int count = cc.length();
        for (int i = 0; i < count; i++) {
            char c = cc.charAt(i);
            if (isNumber(c)) {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
