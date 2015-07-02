package com.alipay.sdk.encrypt;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.Cipher;

public class Rsa {
    public static final String a = "SHA1WithRSA";
    private static final String b = "RSA";

    private static PublicKey d(String str, String str2) {
        return KeyFactory.getInstance(str).generatePublic(new X509EncodedKeySpec(Base64.a(str2)));
    }

    public static String a(String str, String str2) {
        Exception e;
        Throwable th;
        ByteArrayOutputStream byteArrayOutputStream;
        try {
            Key d = d(b, str2);
            Cipher instance = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            instance.init(1, d);
            byte[] bytes = str.getBytes("UTF-8");
            int blockSize = instance.getBlockSize();
            byteArrayOutputStream = new ByteArrayOutputStream();
            int i = 0;
            while (i < bytes.length) {
                try {
                    int length;
                    if (bytes.length - i < blockSize) {
                        length = bytes.length - i;
                    } else {
                        length = blockSize;
                    }
                    byteArrayOutputStream.write(instance.doFinal(bytes, i, length));
                    i += blockSize;
                } catch (Exception e2) {
                    e = e2;
                }
            }
            String str3 = new String(Base64.a(byteArrayOutputStream.toByteArray()));
            if (byteArrayOutputStream == null) {
                return str3;
            }
            try {
                byteArrayOutputStream.close();
                return str3;
            } catch (IOException e3) {
                e3.printStackTrace();
                return str3;
            }
        } catch (Exception e4) {
            e = e4;
            byteArrayOutputStream = null;
            try {
                e.printStackTrace();
                if (byteArrayOutputStream == null) {
                    return null;
                }
                try {
                    byteArrayOutputStream.close();
                } catch (IOException e5) {
                    e5.printStackTrace();
                }
                return null;
            } catch (Throwable th2) {
                th = th2;
                if (byteArrayOutputStream != null) {
                    try {
                        byteArrayOutputStream.close();
                    } catch (IOException e32) {
                        e32.printStackTrace();
                    }
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            byteArrayOutputStream = null;
            if (byteArrayOutputStream != null) {
                byteArrayOutputStream.close();
            }
            throw th;
        }
    }

    public static String b(String str, String str2) {
        Exception e;
        Throwable th;
        ByteArrayOutputStream byteArrayOutputStream = null;
        ByteArrayOutputStream byteArrayOutputStream2;
        try {
            Key generatePrivate = KeyFactory.getInstance(b).generatePrivate(new PKCS8EncodedKeySpec(Base64.a(str2)));
            Cipher instance = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            instance.init(2, generatePrivate);
            byte[] a = Base64.a(str);
            int blockSize = instance.getBlockSize();
            byteArrayOutputStream2 = new ByteArrayOutputStream();
            int i = 0;
            while (i < a.length) {
                try {
                    int length;
                    if (a.length - i < blockSize) {
                        length = a.length - i;
                    } else {
                        length = blockSize;
                    }
                    byteArrayOutputStream2.write(instance.doFinal(a, i, length));
                    i += blockSize;
                } catch (Exception e2) {
                    e = e2;
                }
            }
            String str3 = new String(byteArrayOutputStream2.toByteArray());
            if (byteArrayOutputStream2 == null) {
                return str3;
            }
            try {
                byteArrayOutputStream2.close();
                return str3;
            } catch (IOException e3) {
                e3.printStackTrace();
                return str3;
            }
        } catch (Exception e4) {
            e = e4;
            byteArrayOutputStream2 = null;
            try {
                e.printStackTrace();
                if (byteArrayOutputStream2 == null) {
                    return null;
                }
                try {
                    byteArrayOutputStream2.close();
                } catch (IOException e5) {
                    e5.printStackTrace();
                }
                return null;
            } catch (Throwable th2) {
                th = th2;
                byteArrayOutputStream = byteArrayOutputStream2;
                if (byteArrayOutputStream != null) {
                    try {
                        byteArrayOutputStream.close();
                    } catch (IOException e32) {
                        e32.printStackTrace();
                    }
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            if (byteArrayOutputStream != null) {
                byteArrayOutputStream.close();
            }
            throw th;
        }
    }

    public static String c(String str, String str2) {
        String str3 = "utf-8";
        try {
            PrivateKey generatePrivate = KeyFactory.getInstance(b).generatePrivate(new PKCS8EncodedKeySpec(Base64.a(str2)));
            Signature instance = Signature.getInstance(a);
            instance.initSign(generatePrivate);
            instance.update(str.getBytes(str3));
            return Base64.a(instance.sign());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean a(String str, String str2, String str3) {
        try {
            PublicKey generatePublic = KeyFactory.getInstance(b).generatePublic(new X509EncodedKeySpec(Base64.a(str3)));
            Signature instance = Signature.getInstance(a);
            instance.initVerify(generatePublic);
            instance.update(str.getBytes("utf-8"));
            return instance.verify(Base64.a(str2));
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
