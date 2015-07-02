package com.alipay.sdk.encrypt;

import com.alipay.mobilesecuritysdk.constant.ConfigConstant;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {
    public static String a(String str) {
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.update(str.getBytes());
            return b(instance.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return ConfigConstant.WIRELESS_FILENAME;
        }
    }

    public static String a(byte[] bArr) {
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.update(bArr);
            return b(instance.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return ConfigConstant.WIRELESS_FILENAME;
        }
    }

    private static String b(byte[] bArr) {
        StringBuffer stringBuffer = new StringBuffer(bArr.length * 2);
        for (int i = 0; i < bArr.length; i++) {
            stringBuffer.append(Character.forDigit((bArr[i] & 240) >> 4, 16));
            stringBuffer.append(Character.forDigit(bArr[i] & 15, 16));
        }
        return stringBuffer.toString();
    }
}
