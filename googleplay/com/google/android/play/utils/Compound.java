package com.google.android.play.utils;

import android.content.res.TypedArray;
import android.util.TypedValue;
import com.google.android.wallet.instrumentmanager.R;

public final class Compound {
    private static final TypedValue sTypedValue;

    static {
        sTypedValue = new TypedValue();
    }

    public static boolean isCompoundInt(int compound) {
        switch (-16777216 & compound) {
            case -16777216:
            case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                return true;
            default:
                return false;
        }
    }

    public static boolean isCompoundFloat(int compound) {
        switch (-16777216 & compound) {
            case Integer.MIN_VALUE:
            case -16777216:
            case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
            case 2130706432:
                return false;
            default:
                return true;
        }
    }

    public static int floatLengthToCompound(float length) {
        if (length == 0.0f) {
            return 0;
        }
        int bits = Float.floatToIntBits(length);
        if (isCompoundFloat(bits)) {
            return bits;
        }
        throw new IllegalArgumentException("Float length " + length + " out of range or NaN");
    }

    public static int getCompound(TypedArray typedArray, String attrName, int index, boolean isSize) {
        int bits = 0;
        TypedValue value = sTypedValue;
        if (typedArray.getValue(index, value)) {
            switch (value.type) {
                case R.styleable.WalletImFormEditText_required /*4*/:
                    float floatLength = value.getFloat();
                    if (!isSize || floatLength >= 0.0f) {
                        if (floatLength != 0.0f) {
                            bits = Float.floatToIntBits(value.getFloat());
                        }
                        if (isCompoundFloat(bits)) {
                            return bits;
                        }
                        throw new IllegalArgumentException(typedArray.getPositionDescription() + ": out-of-range float length for " + attrName);
                    }
                    throw new IllegalArgumentException(typedArray.getPositionDescription() + ": negative float length not allowed for size attribute " + attrName);
                case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                    int intLength;
                    float pixelLength = value.getDimension(typedArray.getResources().getDisplayMetrics());
                    if (!isSize || pixelLength >= 1.0f) {
                        intLength = Math.round(pixelLength);
                    } else if (pixelLength < 0.0f) {
                        throw new IllegalArgumentException(typedArray.getPositionDescription() + ": negative dimen length not allowed for size attribute " + attrName);
                    } else {
                        intLength = pixelLength == 0.0f ? 0 : 1;
                    }
                    if (intLength >= -16777216 && intLength <= 16777215) {
                        return intLength;
                    }
                    throw new IllegalArgumentException(typedArray.getPositionDescription() + ": out-of-range dimension length for " + attrName);
                case com.google.android.play.R.styleable.Toolbar_maxButtonHeight /*16*/:
                case com.google.android.play.R.styleable.Toolbar_theme /*17*/:
                    int intValue = value.data;
                    int type = intValue & -16777216;
                    if (type == 2130706432 || (isSize && type == -16777216)) {
                        return intValue;
                    }
                    throw new IllegalArgumentException(typedArray.getPositionDescription() + ": invalid enum value " + intValue + " for " + attrName);
                default:
                    throw new IllegalArgumentException(typedArray.getPositionDescription() + ": unaccepted value for " + attrName + ": " + value.coerceToString());
            }
        }
        throw new IllegalArgumentException(typedArray.getPositionDescription() + ": missing " + attrName);
    }
}
