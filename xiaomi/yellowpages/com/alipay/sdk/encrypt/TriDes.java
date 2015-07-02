package com.alipay.sdk.encrypt;

import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class TriDes {
    private static String a;

    static {
        a = "DESede/ECB/PKCS5Padding";
    }

    public static String a(String str, String str2) {
        String str3 = null;
        try {
            Key secretKeySpec = new SecretKeySpec(str.getBytes(), "DESede");
            Cipher instance = Cipher.getInstance(a);
            instance.init(1, secretKeySpec);
            str3 = Base64.a(instance.doFinal(str2.getBytes()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str3;
    }

    public static String b(String str, String str2) {
        try {
            Key secretKeySpec = new SecretKeySpec(str.getBytes(), "DESede");
            Cipher instance = Cipher.getInstance(a);
            instance.init(2, secretKeySpec);
            return new String(instance.doFinal(Base64.a(str2)));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
