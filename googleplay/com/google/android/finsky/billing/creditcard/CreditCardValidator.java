package com.google.android.finsky.billing.creditcard;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Set;

public class CreditCardValidator {

    public enum InputField {
        NUMBER,
        CVC,
        EXP_MONTH,
        EXP_YEAR
    }

    public static CreditCardType validate(String number, String cvc, String expMonth, String expYear, int expYearBase, Set<InputField> errors) {
        CreditCardType type = CreditCardType.getTypeForNumber(number);
        if (type == null) {
            errors.add(InputField.NUMBER);
            type = CreditCardType.getTypeForPrefix(number);
        }
        if (type != null) {
            validateCvc(type, cvc, errors);
        }
        validateExpirationDate(expMonth, expYear, expYearBase, errors);
        return type;
    }

    private static void validateCvc(CreditCardType type, String cvc, Set<InputField> errors) {
        if (type != null && !checkNumber(cvc, type.cvcLength, type.cvcLength)) {
            errors.add(InputField.CVC);
        }
    }

    private static void validateExpirationDate(String expMonth, String expYear, int expYearBase, Set<InputField> errors) {
        boolean invalidYear = false;
        boolean invalidMonth = false;
        int year = -1;
        if (checkNumber(expYear, 2, 2)) {
            year = Integer.parseInt(expYear);
        }
        if (year < 0) {
            errors.add(InputField.EXP_YEAR);
            invalidYear = true;
        }
        year += expYearBase;
        int month = -1;
        if (checkNumber(expMonth, 1, 2)) {
            month = Integer.valueOf(expMonth).intValue();
        }
        if (month < 1 || month > 12) {
            errors.add(InputField.EXP_MONTH);
            invalidMonth = true;
        }
        if (!invalidYear) {
            Calendar today = new GregorianCalendar();
            Calendar today2 = new GregorianCalendar(today.get(1), today.get(2), 1);
            if (year > today2.get(1) + 20) {
                errors.add(InputField.EXP_YEAR);
            }
            if (!invalidMonth && today2.after(new GregorianCalendar(year, month - 1, 1))) {
                errors.add(InputField.EXP_MONTH);
                errors.add(InputField.EXP_YEAR);
            }
        }
    }

    private static boolean checkNumber(String input, int minLength, int maxLength) {
        if (input.length() < minLength || input.length() > maxLength) {
            return false;
        }
        int i = 0;
        while (i < input.length()) {
            if (input.charAt(i) < '0' || input.charAt(i) > '9') {
                return false;
            }
            i++;
        }
        return true;
    }
}
