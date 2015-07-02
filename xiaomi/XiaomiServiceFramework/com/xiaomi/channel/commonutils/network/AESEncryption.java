package com.xiaomi.channel.commonutils.network;

import com.xiaomi.channel.commonutils.string.UrlBase64Coder;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AESEncryption {

    public static class AESDecodeException extends Exception {
        private static final long serialVersionUID = 8671822568355001199L;

        public AESDecodeException(String str) {
            super(str);
        }

        public AESDecodeException(String msg, Exception e) {
            super(msg, e);
        }
    }

    public static class AESEncodeException extends Exception {
        private static final long serialVersionUID = 4826692804389845727L;

        public AESEncodeException(String str) {
            super(str);
        }

        public AESEncodeException(String msg, Exception e) {
            super(msg, e);
        }
    }

    public static byte[] encrypt(String sSrc, byte[] sKey) throws AESEncodeException {
        if (sKey == null) {
            try {
                throw new AESEncodeException("Key\u4e3a\u7a7anull");
            } catch (Exception e) {
                throw new AESEncodeException("AES\u52a0\u5bc6\u9519\u8bef", e);
            }
        } else if (sKey.length != 16) {
            throw new AESEncodeException("Key\u957f\u5ea6\u4e0d\u662f16\u4f4d");
        } else {
            SecretKeySpec skeySpec = new SecretKeySpec(sKey, "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            try {
                MessageDigest md5 = MessageDigest.getInstance("MD5");
                md5.update(sKey, 0, sKey.length);
                cipher.init(1, skeySpec, new IvParameterSpec(md5.digest()));
                return cipher.doFinal(sSrc.getBytes());
            } catch (NoSuchAlgorithmException e2) {
                return null;
            }
        }
    }

    public static byte[] decrypt(byte[] src, byte[] sKey) throws AESDecodeException {
        if (sKey == null) {
            try {
                throw new AESEncodeException("Key\u4e3a\u7a7anull");
            } catch (Exception e) {
                throw new AESDecodeException("AES\u52a0\u5bc6\u9519\u8bef", e);
            }
        } else if (sKey.length != 16) {
            throw new AESEncodeException("Key\u957f\u5ea6\u4e0d\u662f16\u4f4d");
        } else {
            SecretKeySpec skeySpec = new SecretKeySpec(sKey, "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            try {
                MessageDigest md5 = MessageDigest.getInstance("MD5");
                md5.update(sKey, 0, sKey.length);
                cipher.init(2, skeySpec, new IvParameterSpec(md5.digest()));
                return cipher.doFinal(src);
            } catch (NoSuchAlgorithmException e2) {
                return null;
            }
        }
    }

    public static byte[] hex2byte(String strhex) {
        byte[] bArr = null;
        if (strhex != null) {
            int l = strhex.length();
            if (l % 2 != 1) {
                bArr = new byte[(l / 2)];
                for (int i = 0; i != l / 2; i++) {
                    bArr[i] = (byte) Integer.parseInt(strhex.substring(i * 2, (i * 2) + 2), 16);
                }
            }
        }
        return bArr;
    }

    public static String byte2hex(byte[] b) {
        String hs = "";
        String stmp = "";
        for (byte b2 : b) {
            stmp = Integer.toHexString(b2 & 255);
            if (stmp.length() == 1) {
                hs = hs + "0" + stmp;
            } else {
                hs = hs + stmp;
            }
        }
        return hs.toUpperCase();
    }

    public static String hexEncrypt(String content, String hexAkey) throws AESEncodeException, UnsupportedEncodingException {
        return new String(UrlBase64Coder.encode(encrypt(content, hex2byte(hexAkey))));
    }

    public static String hexDecrypt(String base64Content, String hexAkey) throws AESDecodeException, UnsupportedEncodingException {
        return new String(decrypt(UrlBase64Coder.decode(base64Content), hex2byte(hexAkey)));
    }

    public static void test() throws UnsupportedEncodingException, AESDecodeException {
    }
}
