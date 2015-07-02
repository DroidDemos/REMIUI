package com.google.android.finsky.utils;

import android.util.Base64;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5Util {
    public static String secureHash(byte[] input) {
        return Base64.encodeToString(secureHashBytes(input), 11);
    }

    public static byte[] secureHashBytes(byte[] input) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(input);
            return messageDigest.digest();
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }
}
