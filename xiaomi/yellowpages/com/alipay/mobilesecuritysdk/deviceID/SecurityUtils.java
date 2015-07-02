package com.alipay.mobilesecuritysdk.deviceID;

import com.alipay.mobilesecuritysdk.constant.ConfigConstant;
import java.security.Key;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class SecurityUtils {
    public static final String TAG = "SecurityUtils";
    private static String str;

    static {
        str = new String("idnjfhncnsfuobcnt847y929o449u474w7j3h22aoddc98euk#%&&)*&^%#");
    }

    public static String getSeed() {
        String str = new String();
        for (int i = 0; i < str.length() - 1; i += 4) {
            str = new StringBuilder(String.valueOf(str)).append(str.charAt(i)).toString();
        }
        return str;
    }

    public static String encrypt(String str, String str2) {
        byte[] bArr = null;
        try {
            bArr = encrypt(getRawKey(str.getBytes()), str2.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return toHex(bArr);
    }

    public static String decrypt(String str, String str2) {
        try {
            return new String(decrypt(getRawKey(str.getBytes()), toByte(str2)));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static byte[] getRawKey(byte[] bArr) {
        KeyGenerator instance = KeyGenerator.getInstance("AES");
        SecureRandom instance2 = SecureRandom.getInstance("SHA1PRNG", "Crypto");
        instance2.setSeed(bArr);
        instance.init(128, instance2);
        return instance.generateKey().getEncoded();
    }

    private static byte[] encrypt(byte[] bArr, byte[] bArr2) {
        Key secretKeySpec = new SecretKeySpec(bArr, "AES");
        Cipher instance = Cipher.getInstance("AES/CBC/PKCS5Padding");
        instance.init(1, secretKeySpec, new IvParameterSpec(new byte[instance.getBlockSize()]));
        return instance.doFinal(bArr2);
    }

    private static byte[] decrypt(byte[] bArr, byte[] bArr2) {
        Key secretKeySpec = new SecretKeySpec(bArr, "AES");
        Cipher instance = Cipher.getInstance("AES/CBC/PKCS5Padding");
        instance.init(2, secretKeySpec, new IvParameterSpec(new byte[instance.getBlockSize()]));
        return instance.doFinal(bArr2);
    }

    public static String toHex(String str) {
        return toHex(str.getBytes());
    }

    public static String fromHex(String str) {
        return new String(toByte(str));
    }

    public static byte[] toByte(String str) {
        int length = str.length() / 2;
        byte[] bArr = new byte[length];
        for (int i = 0; i < length; i++) {
            bArr[i] = Integer.valueOf(str.substring(i * 2, (i * 2) + 2), 16).byteValue();
        }
        return bArr;
    }

    public static String toHex(byte[] bArr) {
        if (bArr == null) {
            return ConfigConstant.WIRELESS_FILENAME;
        }
        StringBuffer stringBuffer = new StringBuffer(bArr.length * 2);
        for (byte appendHex : bArr) {
            appendHex(stringBuffer, appendHex);
        }
        return stringBuffer.toString();
    }

    private static void appendHex(StringBuffer stringBuffer, byte b) {
        String str = "0123456789ABCDEF";
        stringBuffer.append("0123456789ABCDEF".charAt((b >> 4) & 15)).append("0123456789ABCDEF".charAt(b & 15));
    }
}
