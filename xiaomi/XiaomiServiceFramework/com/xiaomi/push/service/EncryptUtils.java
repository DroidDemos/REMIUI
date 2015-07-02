package com.xiaomi.push.service;

import com.xiaomi.channel.commonutils.string.Base64Coder;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class EncryptUtils {
    private static final byte[] IV;

    static {
        IV = new byte[]{(byte) 100, (byte) 23, (byte) 84, (byte) 114, (byte) 72, (byte) 0, (byte) 4, (byte) 97, (byte) 73, (byte) 97, (byte) 2, (byte) 52, (byte) 84, (byte) 102, (byte) 18, (byte) 32};
    }

    public static String decrypt(String security, String src) {
        try {
            SecretKeySpec skeySpec = new SecretKeySpec(Base64Coder.decode(security), "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(2, skeySpec, new IvParameterSpec(IV));
            return new String(cipher.doFinal(Base64Coder.decode(src)), "UTF-8");
        } catch (Exception ex) {
            System.out.println(ex.toString());
            return null;
        }
    }

    public static String encrypt(String security, String src) throws Exception {
        SecretKeySpec skeySpec = new SecretKeySpec(Base64Coder.decode(security), "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(1, skeySpec, new IvParameterSpec(IV));
        return new String(Base64Coder.encode(cipher.doFinal(src.getBytes("UTF-8"))));
    }
}
