package org.apache.thrift.protocol;

class TBase64Utils {
    private static final byte[] DECODE_TABLE;
    private static final String ENCODE_TABLE = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";

    TBase64Utils() {
    }

    static final void encode(byte[] src, int srcOff, int len, byte[] dst, int dstOff) {
        dst[dstOff] = (byte) ENCODE_TABLE.charAt((src[srcOff] >> 2) & 63);
        if (len == 3) {
            dst[dstOff + 1] = (byte) ENCODE_TABLE.charAt(((src[srcOff] << 4) + (src[srcOff + 1] >> 4)) & 63);
            dst[dstOff + 2] = (byte) ENCODE_TABLE.charAt(((src[srcOff + 1] << 2) + (src[srcOff + 2] >> 6)) & 63);
            dst[dstOff + 3] = (byte) ENCODE_TABLE.charAt(src[srcOff + 2] & 63);
        } else if (len == 2) {
            dst[dstOff + 1] = (byte) ENCODE_TABLE.charAt(((src[srcOff] << 4) + (src[srcOff + 1] >> 4)) & 63);
            dst[dstOff + 2] = (byte) ENCODE_TABLE.charAt((src[srcOff + 1] << 2) & 63);
        } else {
            dst[dstOff + 1] = (byte) ENCODE_TABLE.charAt((src[srcOff] << 4) & 63);
        }
    }

    static {
        DECODE_TABLE = new byte[]{(byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) 62, (byte) -1, (byte) -1, (byte) -1, (byte) 63, (byte) 52, (byte) 53, (byte) 54, (byte) 55, (byte) 56, (byte) 57, (byte) 58, (byte) 59, (byte) 60, (byte) 61, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) 0, (byte) 1, (byte) 2, (byte) 3, (byte) 4, (byte) 5, (byte) 6, (byte) 7, (byte) 8, (byte) 9, (byte) 10, TType.STRING, TType.STRUCT, TType.MAP, TType.SET, TType.LIST, TType.ENUM, (byte) 17, (byte) 18, (byte) 19, (byte) 20, (byte) 21, (byte) 22, (byte) 23, (byte) 24, (byte) 25, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) 26, (byte) 27, (byte) 28, (byte) 29, (byte) 30, (byte) 31, (byte) 32, (byte) 33, (byte) 34, (byte) 35, (byte) 36, (byte) 37, (byte) 38, (byte) 39, (byte) 40, (byte) 41, (byte) 42, (byte) 43, (byte) 44, (byte) 45, (byte) 46, (byte) 47, (byte) 48, (byte) 49, (byte) 50, (byte) 51, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1};
    }

    static final void decode(byte[] src, int srcOff, int len, byte[] dst, int dstOff) {
        dst[dstOff] = (byte) ((DECODE_TABLE[src[srcOff] & 255] << 2) | (DECODE_TABLE[src[srcOff + 1] & 255] >> 4));
        if (len > 2) {
            dst[dstOff + 1] = (byte) (((DECODE_TABLE[src[srcOff + 1] & 255] << 4) & 240) | (DECODE_TABLE[src[srcOff + 2] & 255] >> 2));
            if (len > 3) {
                dst[dstOff + 2] = (byte) (((DECODE_TABLE[src[srcOff + 2] & 255] << 6) & 192) | DECODE_TABLE[src[srcOff + 3] & 255]);
            }
        }
    }
}
