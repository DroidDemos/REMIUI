package com.google.android.finsky.utils;

import android.util.Base64;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Sha1Util {

    public static class DigestResult {
        public final long byteCount;
        public final String hashBase64;

        private DigestResult(String hash, long byteCount) {
            this.hashBase64 = hash;
            this.byteCount = byteCount;
        }
    }

    public static class DigestStream extends FilterOutputStream {
        private long mBytesWritten;
        private MessageDigest mDigest;
        private final OutputStream mOutputStream;

        public DigestStream(OutputStream output) {
            super(output);
            this.mOutputStream = output;
            try {
                this.mDigest = MessageDigest.getInstance("SHA-1");
                this.mBytesWritten = 0;
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
        }

        public DigestResult getDigest() {
            return new DigestResult(Base64.encodeToString(this.mDigest.digest(), 11), this.mBytesWritten);
        }

        public void write(byte[] bytesToWrite, int offset, int length) throws IOException {
            this.mDigest.update(bytesToWrite, offset, length);
            this.mBytesWritten += (long) length;
            this.mOutputStream.write(bytesToWrite, offset, length);
        }

        public void write(int oneByte) throws IOException {
            this.mDigest.update((byte) oneByte);
            this.mBytesWritten++;
            this.mOutputStream.write(oneByte);
        }
    }

    public static String secureHash(String input) {
        return secureHash(input.getBytes());
    }

    public static String secureHash(byte[] input) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
            messageDigest.update(input);
            return Base64.encodeToString(messageDigest.digest(), 11);
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }

    public static DigestResult secureHash(InputStream input) throws IOException {
        return digest(input);
    }

    private static DigestResult digest(InputStream input) throws IOException {
        DigestResult digestResult = null;
        byte[] inputBuf = new byte[8192];
        long totalBytesRead = 0;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
            while (true) {
                try {
                    int bytesRead = input.read(inputBuf);
                    if (bytesRead <= -1) {
                        break;
                    } else if (bytesRead > 0) {
                        messageDigest.update(inputBuf, 0, bytesRead);
                        totalBytesRead += (long) bytesRead;
                    }
                } finally {
                    Utils.safeClose(input);
                }
            }
            digestResult = new DigestResult(Base64.encodeToString(messageDigest.digest(), 11), totalBytesRead);
        } catch (NoSuchAlgorithmException e) {
            FinskyLog.wtf("Unable to access SHA-1 hash", new Object[0]);
            Utils.safeClose(input);
        }
        return digestResult;
    }
}
