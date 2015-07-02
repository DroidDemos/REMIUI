package com.alipay.sdk.encrypt;

import com.alipay.sdk.util.LogUtils;
import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class Des {
    public static String a(String str, String str2) {
        return a(1, str, str2);
    }

    public static String b(String str, String str2) {
        return a(2, str, str2);
    }

    public static String a(int i, String str, String str2) {
        try {
            byte[] a;
            Key secretKeySpec = new SecretKeySpec(str2.getBytes(), "DES");
            Cipher instance = Cipher.getInstance("DES");
            instance.init(i, secretKeySpec);
            if (i == 2) {
                a = Base64.a(str);
            } else {
                a = str.getBytes("UTF-8");
            }
            byte[] doFinal = instance.doFinal(a);
            if (i == 2) {
                return new String(doFinal);
            }
            return Base64.a(doFinal);
        } catch (Object e) {
            LogUtils.a(e);
            return null;
        }
    }
}
