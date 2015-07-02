package com.google.android.finsky.billing.genericinstrument;

import android.provider.Settings.Secure;
import android.util.Base64;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.protos.CreateInstrument.PaypalAuthResponse;
import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

public class PaypalPasswordEncryptor {
    public PaypalAuthResponse createPaypalAuthResponse(byte[] certificateBytes, String password, String deviceIdSalt) throws CertificateException {
        return createPaypalAuthResponse(certificateBytes, password, deviceIdSalt, getDeviceId(), System.currentTimeMillis());
    }

    PaypalAuthResponse createPaypalAuthResponse(byte[] certificateBytes, String password, String deviceIdSalt, String deviceId, long timestamp) throws CertificateException {
        PaypalAuthResponse response = new PaypalAuthResponse();
        String hashedDeviceId = getHashedDeviceId(deviceIdSalt, deviceId);
        response.hashedDeviceId = hashedDeviceId;
        response.hasHashedDeviceId = true;
        response.encryptedAuthMessage = encrypt(getX509Certificate(certificateBytes), timestamp, hashedDeviceId, password);
        response.hasEncryptedAuthMessage = true;
        return response;
    }

    private String getDeviceId() {
        return Secure.getString(FinskyApp.get().getContentResolver(), "android_id");
    }

    String getHashedDeviceId(String deviceIdSalt, String deviceId) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(deviceIdSalt.getBytes("UTF-8"));
            messageDigest.update(deviceId.getBytes("UTF-8"));
            return Base64.encodeToString(messageDigest.digest(), 2);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedEncodingException e2) {
            throw new RuntimeException(e2);
        }
    }

    byte[] encrypt(X509Certificate certificate, long timestamp, String hashedDeviceId, String password) throws CertificateException {
        byte[] certificateSerialNumber = getCertificateSerialNumber(certificate.getSerialNumber());
        SecretKey exchangeKey = createSessionKey();
        return createEncryptedPayload(certificateSerialNumber, encryptExchangeKey(certificate, exchangeKey), encryptInnerMessage(exchangeKey, createInnerMessage(timestamp, hashedDeviceId, password)));
    }

    byte[] getCertificateSerialNumber(BigInteger serialNumber) {
        byte[] bytes = serialNumber.toByteArray();
        if (bytes.length < 8) {
            byte[] paddedSerialNumber = new byte[8];
            System.arraycopy(bytes, 0, paddedSerialNumber, 8 - bytes.length, bytes.length);
            return paddedSerialNumber;
        } else if (bytes.length == 8) {
            return bytes;
        } else {
            byte[] truncatedSerialNumber = new byte[8];
            System.arraycopy(bytes, bytes.length - 8, truncatedSerialNumber, 0, 8);
            return truncatedSerialNumber;
        }
    }

    private SecretKey createSessionKey() {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(256);
            return keyGenerator.generateKey();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    private byte[] encryptExchangeKey(Certificate certificate, SecretKey exchangeKey) throws CertificateException {
        try {
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            try {
                cipher.init(1, certificate.getPublicKey());
                try {
                    byte[] encryptedKey = cipher.doFinal(exchangeKey.getEncoded());
                    reverseArray(encryptedKey);
                    return encryptedKey;
                } catch (IllegalBlockSizeException e) {
                    throw new RuntimeException(e);
                } catch (BadPaddingException e2) {
                    throw new RuntimeException(e2);
                }
            } catch (InvalidKeyException e3) {
                throw new CertificateException(e3);
            }
        } catch (NoSuchAlgorithmException e4) {
            throw new RuntimeException(e4);
        } catch (NoSuchPaddingException e5) {
            throw new RuntimeException(e5);
        }
    }

    private byte[] createInnerMessage(long timestamp, String hashedDeviceId, String password) {
        int timestampSeconds = (int) (timestamp / 1000);
        try {
            byte[] hashedDeviceIdBytes = hashedDeviceId.getBytes("UTF-8");
            int hashedDeviceIdNumBytes = hashedDeviceIdBytes.length;
            if (hashedDeviceIdNumBytes != hashedDeviceId.length()) {
                throw new IllegalArgumentException("Hashed device ID not 8 bits per char: " + hashedDeviceIdNumBytes + " " + hashedDeviceId.length());
            } else if (hashedDeviceIdNumBytes > 255) {
                throw new IllegalArgumentException("Hashed device ID too long: " + hashedDeviceIdNumBytes);
            } else {
                try {
                    byte[] passwordBytes = password.getBytes("UTF-16LE");
                    int passwordLength = password.length();
                    if (passwordLength > 255) {
                        throw new IllegalArgumentException("Password too long: " + passwordLength);
                    }
                    ByteBuffer buffer = ByteBuffer.allocate(((hashedDeviceIdNumBytes + 5) + 1) + passwordBytes.length);
                    buffer.order(ByteOrder.LITTLE_ENDIAN);
                    buffer.putInt(timestampSeconds);
                    buffer.put((byte) hashedDeviceIdNumBytes);
                    buffer.put(hashedDeviceIdBytes);
                    buffer.put((byte) passwordLength);
                    buffer.put(passwordBytes);
                    return buffer.array();
                } catch (UnsupportedEncodingException e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (UnsupportedEncodingException e2) {
            throw new RuntimeException(e2);
        }
    }

    private byte[] encryptInnerMessage(SecretKey exchangeKey, byte[] innerMessage) {
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            try {
                cipher.init(1, exchangeKey, new IvParameterSpec(new byte[16]));
                try {
                    return cipher.doFinal(innerMessage);
                } catch (IllegalBlockSizeException e) {
                    throw new RuntimeException(e);
                } catch (BadPaddingException e2) {
                    throw new RuntimeException(e2);
                }
            } catch (InvalidKeyException e3) {
                throw new RuntimeException(e3);
            } catch (InvalidAlgorithmParameterException e4) {
                throw new RuntimeException(e4);
            }
        } catch (NoSuchAlgorithmException e5) {
            throw new RuntimeException(e5);
        } catch (NoSuchPaddingException e6) {
            throw new RuntimeException(e6);
        }
    }

    private byte[] createEncryptedPayload(byte[] certificateSerialNumber, byte[] encryptedExchangeKey, byte[] encryptedInnerMessage) {
        int encryptedLength = encryptedExchangeKey.length + encryptedInnerMessage.length;
        if (encryptedLength > 65535) {
            throw new IllegalStateException("Encrypted message is too long: " + encryptedLength);
        }
        ByteBuffer buffer = ByteBuffer.allocate(((certificateSerialNumber.length + 2) + 2) + encryptedLength);
        buffer.order(ByteOrder.BIG_ENDIAN);
        buffer.putShort((short) 5);
        buffer.put(certificateSerialNumber);
        buffer.putChar((char) encryptedLength);
        buffer.put(encryptedExchangeKey);
        buffer.put(encryptedInnerMessage);
        return buffer.array();
    }

    static X509Certificate getX509Certificate(byte[] certificateBytes) throws CertificateException {
        return (X509Certificate) CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(certificateBytes));
    }

    static void reverseArray(byte[] array) {
        for (int i = 0; i < array.length / 2; i++) {
            int j = (array.length - 1) - i;
            byte temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }
}
