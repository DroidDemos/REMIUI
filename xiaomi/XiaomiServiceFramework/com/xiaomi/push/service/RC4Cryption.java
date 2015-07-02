package com.xiaomi.push.service;

import com.xiaomi.channel.commonutils.string.Base64Coder;

public class RC4Cryption {
    private static int keylength;
    private byte[] S;
    private int next_j;
    private int the_i;
    private int the_j;

    static {
        keylength = 8;
    }

    public RC4Cryption() {
        this.next_j = -666;
        this.S = new byte[256];
        this.the_j = 0;
        this.the_i = 0;
    }

    public RC4Cryption(byte[] S) {
        this.next_j = -666;
        this.S = S;
        this.the_j = 0;
        this.the_i = 0;
    }

    private void ksa(int n, byte[] key, boolean printstats) {
        int keylength = key.length;
        for (int i = 0; i < 256; i++) {
            this.S[i] = (byte) i;
        }
        this.the_j = 0;
        this.the_i = 0;
        while (this.the_i < n) {
            this.the_j = ((this.the_j + posify(this.S[this.the_i])) + posify(key[this.the_i % keylength])) % 256;
            sswap(this.S, this.the_i, this.the_j);
            this.the_i++;
        }
        if (n != 256) {
            this.next_j = ((this.the_j + posify(this.S[n])) + posify(key[n % keylength])) % 256;
        }
        if (printstats) {
            System.out.print("S_" + (n - 1) + ":");
            for (int k = 0; k <= n; k++) {
                System.out.print(" " + posify(this.S[k]));
            }
            System.out.print("   j_" + (n - 1) + "=" + this.the_j);
            System.out.print("   j_" + n + "=" + this.next_j);
            System.out.print("   S_" + (n - 1) + "[j_" + (n - 1) + "]=" + posify(this.S[this.the_j]));
            System.out.print("   S_" + (n - 1) + "[j_" + n + "]=" + posify(this.S[this.next_j]));
            if (this.S[1] != (byte) 0) {
                System.out.print("   S[1]!=0");
            }
            System.out.println();
        }
    }

    private void ksa(byte[] key) {
        ksa(256, key, false);
    }

    private void init() {
        this.the_j = 0;
        this.the_i = 0;
    }

    byte nextVal() {
        this.the_i = (this.the_i + 1) % 256;
        this.the_j = (this.the_j + posify(this.S[this.the_i])) % 256;
        sswap(this.S, this.the_i, this.the_j);
        return this.S[(posify(this.S[this.the_i]) + posify(this.S[this.the_j])) % 256];
    }

    byte inverse(byte x) {
        for (int i = 0; i < 256; i++) {
            if (x == this.S[i]) {
                return (byte) i;
            }
        }
        return (byte) 0;
    }

    int the_i() {
        return this.the_i;
    }

    int the_j() {
        return this.the_j;
    }

    int next_j() {
        return this.next_j;
    }

    int S(int n) {
        return posify(this.S[(byte) n]);
    }

    private static void sswap(byte[] S, int i, int j) {
        byte temp = S[i];
        S[i] = S[j];
        S[j] = temp;
    }

    public static int posify(byte b) {
        return b >= (byte) 0 ? b : b + 256;
    }

    public static byte[] buildkey(byte[] IV, byte[] shortkey) {
        int i;
        byte[] key = new byte[keylength];
        int ivlen = IV.length;
        for (i = 0; i < ivlen; i++) {
            key[i] = IV[i];
        }
        for (i = ivlen; i < keylength; i++) {
            key[i] = shortkey[i - ivlen];
        }
        return key;
    }

    public static String byte2string(byte b) {
        int high = (b >> 4) & 15;
        int low = b & 15;
        String convert = "0123456789abcdef";
        return ("" + convert.charAt(high)) + convert.charAt(low);
    }

    public static String byte2string(byte[] b) {
        String result = "";
        for (byte byte2string : b) {
            result = result + byte2string(byte2string);
        }
        return result;
    }

    private static int hexval(char c) {
        if ('0' <= c && c <= '9') {
            return c - 48;
        }
        if ('a' <= c && c <= 'f') {
            return (c - 97) + 10;
        }
        if ('A' > c || c > 'F') {
            return 0;
        }
        return (c - 65) + 10;
    }

    public static byte[] string2byte(String s) {
        int length = s.length() / 2;
        byte[] buf = new byte[length];
        for (int i = 0; i < length; i++) {
            int nyb1 = hexval(s.charAt(i * 2));
            buf[i] = (byte) ((nyb1 * 16) + hexval(s.charAt((i * 2) + 1)));
        }
        return buf;
    }

    public static byte[] encrypt(byte[] key, byte[] content) {
        byte[] outbuf = new byte[content.length];
        RC4Cryption r = new RC4Cryption();
        r.ksa(key);
        r.init();
        for (int i = 0; i < content.length; i++) {
            outbuf[i] = (byte) (content[i] ^ r.nextVal());
        }
        return outbuf;
    }

    public static String encrypt(byte[] key, String content) {
        return String.valueOf(Base64Coder.encode(encrypt(key, content.getBytes())));
    }

    public static byte[] decrypt(byte[] key, String content) {
        return encrypt(key, Base64Coder.decode(content));
    }

    public static byte[] generateKeyForRC4(String secretKey, String id) {
        int i;
        byte[] keyBytes = Base64Coder.decode(secretKey);
        byte[] idbytes = id.getBytes();
        byte[] result = new byte[((keyBytes.length + 1) + idbytes.length)];
        for (i = 0; i < keyBytes.length; i++) {
            result[i] = keyBytes[i];
        }
        result[keyBytes.length] = (byte) 95;
        for (i = 0; i < idbytes.length; i++) {
            result[(keyBytes.length + 1) + i] = idbytes[i];
        }
        return result;
    }
}
