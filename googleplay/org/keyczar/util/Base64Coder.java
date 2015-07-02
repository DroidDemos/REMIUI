package org.keyczar.util;

import com.google.android.wallet.instrumentmanager.R;
import org.keyczar.exceptions.Base64DecodingException;
import org.keyczar.i18n.Messages;

public class Base64Coder {
    private static final char[] ALPHABET;
    private static final byte[] DECODE;
    private static final char[] WHITESPACE;

    static {
        int i;
        ALPHABET = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '-', '_'};
        DECODE = new byte[128];
        WHITESPACE = new char[]{'\t', '\n', '\r', ' ', '\f'};
        for (i = 0; i < DECODE.length; i++) {
            DECODE[i] = (byte) -1;
        }
        for (char c : WHITESPACE) {
            DECODE[c] = (byte) -2;
        }
        for (i = 0; i < ALPHABET.length; i++) {
            DECODE[ALPHABET[i]] = (byte) i;
        }
    }

    public static byte[] decodeWebSafe(String source) throws Base64DecodingException {
        int i;
        char[] input = source.toCharArray();
        int inLen = input.length;
        if (input[inLen - 1] == '=') {
            inLen--;
        }
        if (input[inLen - 1] == '=') {
            inLen--;
        }
        int whiteSpaceChars = 0;
        for (char c : input) {
            if (isWhiteSpace(c)) {
                whiteSpaceChars++;
            }
        }
        inLen -= whiteSpaceChars;
        int outputLen = (inLen / 4) * 3;
        switch (inLen % 4) {
            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                throw new Base64DecodingException(Messages.getString("Base64Coder.IllegalLength", Integer.valueOf(inLen)));
            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                outputLen++;
                break;
            case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                outputLen += 2;
                break;
        }
        byte[] out = new byte[outputLen];
        int buffer = 0;
        int buffCount = 0;
        int i2 = 0;
        int outPos = 0;
        while (i2 < inLen + whiteSpaceChars) {
            if (!isWhiteSpace(input[i2])) {
                buffer = (buffer << 6) | getByte(input[i2]);
                buffCount++;
            }
            if (buffCount == 4) {
                i = outPos + 1;
                out[outPos] = (byte) (buffer >> 16);
                outPos = i + 1;
                out[i] = (byte) (buffer >> 8);
                i = outPos + 1;
                out[outPos] = (byte) buffer;
                buffer = 0;
                buffCount = 0;
            } else {
                i = outPos;
            }
            i2++;
            outPos = i;
        }
        switch (buffCount) {
            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                i = outPos + 1;
                out[outPos] = (byte) (buffer >> 4);
                break;
            case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                i = outPos + 1;
                out[outPos] = (byte) (buffer >> 10);
                outPos = i + 1;
                out[i] = (byte) (buffer >> 2);
                break;
        }
        i = outPos;
        return out;
    }

    public static String encodeWebSafe(byte[] input) {
        int inputBlocks = input.length / 3;
        int remainder = input.length % 3;
        int outputLen = inputBlocks * 4;
        switch (remainder) {
            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                outputLen += 2;
                break;
            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                outputLen += 3;
                break;
        }
        char[] out = new char[outputLen];
        int i = 0;
        int inPos = 0;
        int outPos = 0;
        while (i < inputBlocks) {
            int inPos2 = inPos + 1;
            inPos = inPos2 + 1;
            inPos2 = inPos + 1;
            int buffer = (((input[inPos] & 255) << 16) | ((input[inPos2] & 255) << 8)) | (input[inPos] & 255);
            int i2 = outPos + 1;
            out[outPos] = ALPHABET[(buffer >> 18) & 63];
            outPos = i2 + 1;
            out[i2] = ALPHABET[(buffer >> 12) & 63];
            i2 = outPos + 1;
            out[outPos] = ALPHABET[(buffer >> 6) & 63];
            outPos = i2 + 1;
            out[i2] = ALPHABET[buffer & 63];
            i++;
            inPos = inPos2;
        }
        if (remainder > 0) {
            inPos2 = inPos + 1;
            buffer = (input[inPos] & 255) << 16;
            if (remainder == 2) {
                buffer |= (input[inPos2] & 255) << 8;
                inPos2++;
            }
            i2 = outPos + 1;
            out[outPos] = ALPHABET[(buffer >> 18) & 63];
            outPos = i2 + 1;
            out[i2] = ALPHABET[(buffer >> 12) & 63];
            if (remainder == 2) {
                i2 = outPos + 1;
                out[outPos] = ALPHABET[(buffer >> 6) & 63];
            }
        } else {
            i2 = outPos;
        }
        return new String(out);
    }

    private static byte getByte(int i) throws Base64DecodingException {
        if (i >= 0 && i <= 127 && DECODE[i] != (byte) -1) {
            return DECODE[i];
        }
        throw new Base64DecodingException(Messages.getString("Base64Coder.IllegalCharacter", Integer.valueOf(i)));
    }

    private static boolean isWhiteSpace(int i) {
        return DECODE[i] == (byte) -2;
    }
}
