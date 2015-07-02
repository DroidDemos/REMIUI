package org.apache.thrift;

public class EncodingUtils {
    public static final void encodeBigEndian(int integer, byte[] buf) {
        encodeBigEndian(integer, buf, 0);
    }

    public static final void encodeBigEndian(int integer, byte[] buf, int offset) {
        buf[offset] = (byte) ((integer >> 24) & 255);
        buf[offset + 1] = (byte) ((integer >> 16) & 255);
        buf[offset + 2] = (byte) ((integer >> 8) & 255);
        buf[offset + 3] = (byte) (integer & 255);
    }

    public static final int decodeBigEndian(byte[] buf) {
        return decodeBigEndian(buf, 0);
    }

    public static final int decodeBigEndian(byte[] buf, int offset) {
        return ((((buf[offset] & 255) << 24) | ((buf[offset + 1] & 255) << 16)) | ((buf[offset + 2] & 255) << 8)) | (buf[offset + 3] & 255);
    }
}
