package com.xiaomi.channel.commonutils.string;

import android.net.Uri;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.MyLog;
import java.io.UnsupportedEncodingException;
import java.security.InvalidParameterException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class CloudCoder {
    private static final String RC4_ALGORITHM_NAME = "RC4";

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static javax.crypto.Cipher newAESCipher(java.lang.String r6, int r7) {
        /*
        r3 = com.xiaomi.channel.commonutils.string.Base64Coder.decode(r6);
        r4 = new javax.crypto.spec.SecretKeySpec;
        r5 = "AES";
        r4.<init>(r3, r5);
        r5 = "AES/CBC/PKCS5Padding";
        r0 = javax.crypto.Cipher.getInstance(r5);	 Catch:{ NoSuchAlgorithmException -> 0x0020, NoSuchPaddingException -> 0x0026, InvalidAlgorithmParameterException -> 0x002b, InvalidKeyException -> 0x0030 }
        r2 = new javax.crypto.spec.IvParameterSpec;	 Catch:{ NoSuchAlgorithmException -> 0x0020, NoSuchPaddingException -> 0x0026, InvalidAlgorithmParameterException -> 0x002b, InvalidKeyException -> 0x0030 }
        r5 = "0102030405060708";
        r5 = r5.getBytes();	 Catch:{ NoSuchAlgorithmException -> 0x0020, NoSuchPaddingException -> 0x0026, InvalidAlgorithmParameterException -> 0x002b, InvalidKeyException -> 0x0030 }
        r2.<init>(r5);	 Catch:{ NoSuchAlgorithmException -> 0x0020, NoSuchPaddingException -> 0x0026, InvalidAlgorithmParameterException -> 0x002b, InvalidKeyException -> 0x0030 }
        r0.init(r7, r4, r2);	 Catch:{ NoSuchAlgorithmException -> 0x0020, NoSuchPaddingException -> 0x0026, InvalidAlgorithmParameterException -> 0x002b, InvalidKeyException -> 0x0030 }
    L_0x001f:
        return r0;
    L_0x0020:
        r1 = move-exception;
        r1.printStackTrace();
    L_0x0024:
        r0 = 0;
        goto L_0x001f;
    L_0x0026:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x0024;
    L_0x002b:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x0024;
    L_0x0030:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x0024;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.channel.commonutils.string.CloudCoder.newAESCipher(java.lang.String, int):javax.crypto.Cipher");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static javax.crypto.Cipher newRC4Cipher(byte[] r4, int r5) {
        /*
        r2 = new javax.crypto.spec.SecretKeySpec;
        r3 = "RC4";
        r2.<init>(r4, r3);
        r3 = "RC4";
        r0 = javax.crypto.Cipher.getInstance(r3);	 Catch:{ NoSuchAlgorithmException -> 0x0011, NoSuchPaddingException -> 0x0017, InvalidKeyException -> 0x001c }
        r0.init(r5, r2);	 Catch:{ NoSuchAlgorithmException -> 0x0011, NoSuchPaddingException -> 0x0017, InvalidKeyException -> 0x001c }
    L_0x0010:
        return r0;
    L_0x0011:
        r1 = move-exception;
        r1.printStackTrace();
    L_0x0015:
        r0 = 0;
        goto L_0x0010;
    L_0x0017:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x0015;
    L_0x001c:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x0015;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.channel.commonutils.string.CloudCoder.newRC4Cipher(byte[], int):javax.crypto.Cipher");
    }

    public static String hash4SHA1(String plain) {
        try {
            return String.valueOf(Base64Coder.encode(MessageDigest.getInstance("SHA1").digest(plain.getBytes("UTF-8"))));
        } catch (NoSuchAlgorithmException e) {
            MyLog.e("CloudCoder.hash4SHA1 ", e);
            throw new IllegalStateException("failed to SHA1");
        } catch (UnsupportedEncodingException e2) {
            MyLog.e("CloudCoder.hash4SHA1 ", e2);
            throw new IllegalStateException("failed to SHA1");
        } catch (Exception e3) {
            MyLog.e("CloudCoder.hash4SHA1 ", e3);
            throw new IllegalStateException("failed to SHA1");
        }
    }

    public static String generateSignature(String method, String requestUrl, Map<String, String> params, String security) {
        if (TextUtils.isEmpty(security)) {
            throw new InvalidParameterException("security is not nullable");
        }
        List<String> exps = new ArrayList();
        if (method != null) {
            exps.add(method.toUpperCase());
        }
        if (requestUrl != null) {
            exps.add(Uri.parse(requestUrl).getEncodedPath());
        }
        if (!(params == null || params.isEmpty())) {
            for (Entry<String, String> entry : new TreeMap(params).entrySet()) {
                exps.add(String.format("%s=%s", new Object[]{entry.getKey(), entry.getValue()}));
            }
        }
        exps.add(security);
        boolean first = true;
        StringBuilder sb = new StringBuilder();
        for (String s : exps) {
            if (!first) {
                sb.append('&');
            }
            sb.append(s);
            first = false;
        }
        return hash4SHA1(sb.toString());
    }
}
